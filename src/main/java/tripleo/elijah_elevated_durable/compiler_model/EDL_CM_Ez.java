package tripleo.elijah_elevated_durable.compiler_model;

import lombok.*;
import org.jdeferred2.*;
import org.jetbrains.annotations.*;
import tripleo.elijah.ci.*;
import tripleo.elijah.comp.queries.*;
import tripleo.elijah.comp.specs.*;
import tripleo.elijah.compiler_model.*;
import tripleo.elijah_fluffy.diagnostic.*;
import tripleo.elijah_fluffy.util.*;
import tripleo.graph.*;

public class EDL_CM_Ez implements CM_Ez {
	@Getter
	private EzSpec                           spec;
	@Getter
	private Operation2<CompilerInstructions> cio;
	@Getter
	private QSEZ_Reasoning                   reasoning;
	@Getter
	private String                           hash;
	@Getter
	private CK_SourceFile                    sourceFile;

	@Override
	public void advise(final EzSpec aSpec) {
		this.spec = aSpec;
	}

	@Override
	public void advise(final Operation2<CompilerInstructions> aCio) {
		this.cio = aCio;
	}

	@Override
	public void advise(final @NotNull CK_ObjectTree aObjectTree) {
		aObjectTree.asseverate(this, Asseverate.CI_SPECCED);
		aObjectTree.asseverate(this, Asseverate.CI_PARSED);
	}

	@Override
	public void advise(final QSEZ_Reasoning aReasoning) {
		this.reasoning = aReasoning;
	}

	@Override
	public EzSpec spec() {
		return this.spec;
	}

	@Override
	public CK_SourceFile sourceFile() {
		return getSourceFile();
	}

	// antilombok
	private CK_SourceFile getSourceFile() {
		return this.sourceFile;
	}

	@Override
	public String hash() {
		return getHash();
	}

	// antilombok
	private String getHash() {
		return this.hash;
	}

	public interface GetCio {
		public static GetCio of(Eventual<CompilerInstructions> aCiEv) {
			return new GetCio() {
				private final Eventual<Operation2<CompilerInstructions>> ooe = new Eventual<>();

				{
					aCiEv.then(result -> ooe.resolve(Operation2.success(result)));
					aCiEv.onFail(new FailCallback<Diagnostic>() {
						@Override
						public void onFail(final Diagnostic result) {
							ooe.reject(result); // should we resolve here instead because its retarted?
						}
					});
				}

				@Override
				public Eventual<Operation2<CompilerInstructions>> operation2Eventual() {
					return ooe;
				}

				@Override
				public Eventual<CompilerInstructions> compilerInstructionsEventual() {
					return aCiEv;
				}
			};
		}

		public static GetCio of(CompilerInstructions aCompilerInstructions) {
			return new GetCio() {
				private Eventual<Operation2<CompilerInstructions>> ooe = new Eventual<>();
				private Eventual<CompilerInstructions>             eci = new Eventual<>();

				//because this is wrong
				{
					if (aCompilerInstructions != null) {
						ooe.resolve(Operation2.success(aCompilerInstructions));
						eci.resolve(aCompilerInstructions);
					} else {
						//ooe.reject(/*Operation2.failure*/(new Exception("unknown")));
						final Exception           exc = new Exception("unknown");
						final ExceptionDiagnostic d   = new ExceptionDiagnostic(exc);
						ooe.resolve(Operation2.failure(d));
						eci.reject(d);
					}
				}

				@Override
				public Eventual<Operation2<CompilerInstructions>> operation2Eventual() {
					return ooe;
				}

				@Override
				public Eventual<CompilerInstructions> compilerInstructionsEventual() {
					return eci;
				}
			};
		}

		Eventual<Operation2<CompilerInstructions>> operation2Eventual();

		Eventual<CompilerInstructions> compilerInstructionsEventual();
	}
}
