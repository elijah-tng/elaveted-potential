package tripleo.elijah_durable_elevated.comp.internal;

import tripleo.elijah.ci.*;
import tripleo.elijah.comp.*;
import tripleo.elijah.comp.i.*;
import tripleo.elijah.nextgen.inputtree.*;
import tripleo.elijah_durable_elevated.*;
import tripleo.elijah_durable_elevated.comp.*;
import tripleo.elijah_elevated_durable.comp.*;
import tripleo.elijah_fluffy.util.*;

import java.util.*;

/// fixme clojuify
public /* static */ class CCI_Acceptor__CompilerInputListener implements CompilerInputListener {
	public final  InstructionDoer       id;
	private final EDL_ICompilation      compilation;
	private final EDL_CompilationRunner cr;
	private       CCI                   cci;
	private       IProgressSink         _progressSink;

	public CCI_Acceptor__CompilerInputListener(EDL_Compilation aCompilation) {
		this.compilation = aCompilation;

		this.id = new InstructionDoer(aCompilation);

		cr = (EDL_CompilationRunner) compilation.getCompilationEnclosure().getCompilationRunner();
	}

	public CompilerInstructions _root() {
		return id.root;
	}

	@Override
	public void change(CompilerInput i, CompilerInput.CompilerInputField field) {
		if (compilation.getCompilerInputListener() instanceof CCI_Acceptor__CompilerInputListener) {
			CCI_Acceptor__CompilerInputListener cci_listener = (CCI_Acceptor__CompilerInputListener) compilation.getCompilerInputListener();
			// TODO figure this out some time in the future
			if (DebugFlags.CCI_gate) {
				if (cci == null) {
					cci = new EDL_CCI(compilation, compilation._cis(), _progressSink);
				}
				if (_progressSink == null) {
					_progressSink = compilation.getCompilationEnclosure().getCompilationBus().defaultProgressSink();
				}
				cci_listener.set(cci, _progressSink);
			}
		}


		var inputTree = compilation.getInputTree();

		compilation.getCompilationEnclosure().logProgress(CompProgress.__CCI_Acceptor__CompilerInputListener__change__logInput, i);

		switch (field) {
		case TY -> cacil__TY(i, inputTree);
		case ACCEPT_CI -> cacil__ACCEPT_CI(i);
		case HASH -> cacil__HASH();
		case DIRECTORY_RESULTS -> cacil__DIRECTORY_RESULTS(i);
		default -> throw new IllegalStateException("Unexpected value: " + field);
		}
	}

	private void cacil__TY(final CompilerInput i, final EIT_InputTree inputTree) {
		switch (i.ty()) {
		case NULL -> cacil__TY__NULL();
		case SOURCE_ROOT -> cacil__TY__SOURCE_ROOT(i, inputTree);
		case ROOT -> cacil__TY__ROOT(i, inputTree);
		// README has to wait for ACCEPT_CI, as it is assigned after `ty` is changed
		//				hasInstructions(List_of(i....));
		case ARG -> cacil__TY__ARG();
		case STDLIB -> cacil__TY__STDLIB();
		}
	}

	private static void cacil__TY__STDLIB() {
		int y4 = 4;
	}

	private static void cacil__TY__ARG() {
		// inputTree.addNode(i); README skip ARGS

		// FIXME processOption here (ie apply compiler change)
		int yyy = 3;
	}

	private void cacil__TY__ROOT(final CompilerInput i, final EIT_InputTree inputTree) {
		inputTree.addNode(i);

		final Maybe<ILazyCompilerInstructions> instructionsMaybe = i.acceptance_ci();
		if (instructionsMaybe != null) {
			var ci = instructionsMaybe.o.get();

			assert ci != null;

			cr._cis().onNext(ci);
			id.add(ci);
		}
	}

	private static void cacil__TY__SOURCE_ROOT(final CompilerInput i, final EIT_InputTree inputTree) {
		int y3 = 2;
		inputTree.addNode(i);
	}

	private static void cacil__TY__NULL() {
		int y2 = 2;
		// inputTree.addNode(i); README obviously skip nulls
	}

	private void cacil__ACCEPT_CI(final CompilerInput i) {
		if (i.ty() == CompilerInput.Ty.ROOT) {
			final Maybe<ILazyCompilerInstructions> instructionsMaybe = i.acceptance_ci();
			if (instructionsMaybe != null) {
				final Eventual<CompilerInstructions> e = new Eventual<>();
				ILazyCompilerInstructions_.ofEventual(i,
													  compilation.getCompilationClosure(),
													  e);
				e.then(id::add);
			}
		} else if (i.ty() == CompilerInput.Ty.SOURCE_ROOT) {
			System.err.println("9997-102 hit SOURCE_ROOT");
		} else {
			throw new UnintendedUseException();
		}
	}

	private static void cacil__HASH() {
		int yy = 2;
		// FIXME latch all create/commit inputs.txt -> should be Buffer!!
	}

	private void cacil__DIRECTORY_RESULTS(final CompilerInput i) {
		int y = 2;

		if (i.getDirectoryResults() != null) {
			List<Operation2<CompilerInstructions>> directoryResults = i.getDirectoryResults().getDirectoryResult();

			for (Operation2<CompilerInstructions> directoryResult : directoryResults) {
				if (directoryResult.mode() == Mode.SUCCESS) {
					ILazyCompilerInstructions iLazyCompilerInstructions = ILazyCompilerInstructions_.of(directoryResult.success());

					id.add(iLazyCompilerInstructions.get());

					if (DebugFlags.CCI_gate) {
						cci.accept(Maybe.of(iLazyCompilerInstructions), _progressSink);
					}
				}
			}
		}
	}

	public void set(CCI aCci, IProgressSink aPs) {
		cci           = aCci;
		_progressSink = aPs;
	}
}
