package tripleo.elijah_durable_elevated.comp.nextgen;

//import io.smallrye.mutiny.*;

import clojure.lang.*;
import org.jdeferred2.*;
import org.jetbrains.annotations.*;
import tripleo.elijah.nextgen.*;
import tripleo.elijah_elevated_durable.comp.*;
import tripleo.elijah_fluffy.util.*;
import tripleo.paths.*;

import java.util.*;

@SuppressWarnings("CommentedOutCode")
public class EDL_CP_Paths implements CP_Paths {
	@SuppressWarnings("FieldCanBeLocal")
	private final          EDL_ICompilation _c;
	private final @NotNull CP_StdlibPath    stdlibRoot;
	private final          CP_OutputPath    outputRoot;
	private final @NotNull List<ER_Node>    outputNodes = new ArrayList<>();

	public EDL_CP_Paths(final EDL_ICompilation aC) {
		_c         = aC;
		outputRoot = new CP_OutputPath(_c);
		stdlibRoot = new CP_StdlibPath__(_c);
	}

	@Override
	public void addNode(CP_RootType t, final ER_Node aNode) {
//		if (aNode.getPath().)
		if (t == CP_RootType.OUTPUT) {
			outputNodes.add(aNode);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public CP_Path outputRoot() {
		return outputRoot;
	}

	@Override
	public CP_Path preludeRoot() {
		throw new UnintendedUseException("TODO 12/07 implement me");
	}

	@Override
	public @NotNull CP_StdlibPath stdlibRoot() {
		return stdlibRoot;
	}

	@Override
	public CP_Path sourcesRoot() {
		throw new UnintendedUseException("TODO 12/07 implement me");
	}

	@Override
	public void renderNodes() {
		outputRoot._renderNodes(outputNodes);
	}

	@Override
	public void signalCalculateFinishParse() {
		outputRoot.signalCalculateFinishParse();
	}

	@Override
	public void subscribeCalculateFinishParse(CPX_CalculateFinishParse cp_OutputPath) {
		_c.onConfig(new DoneCallback<IPersistentMap>() {
			@Override
			public void onDone(final IPersistentMap result) {
				final String S     = "CompilerController-deref";
				final Object atom0 = result.valAt(S, null);
				if (atom0 != null) {
					if (atom0 instanceof IAtom) {
						IAtom atom = (IAtom) atom0;
						NotImplementedException.raise_stop();
						System.err.println("[TRACE] Clojure atom found for " + S + " is a IFn");
						return;
					} else if (atom0 instanceof IFn) {
						IFn fn = (IFn) atom0;
						if (false) fn.invoke(cp_OutputPath);
						NotImplementedException.raise_stop();
						System.err.println("[TRACE] Clojure atom found for " + S + " is a IFn");
						return;
					} else {
						NotImplementedException.raise_stop();
						System.err.println("[TRACE] Clojure atom found for " + S + " is not of class IAtom");
						return;
					}
				} else {
					System.err.println("[TRACE] No clojure atom found for " + S);
				}
			}
		});
	}
}
