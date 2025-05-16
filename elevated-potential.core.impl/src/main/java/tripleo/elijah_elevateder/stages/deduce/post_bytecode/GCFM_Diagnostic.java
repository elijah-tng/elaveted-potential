package tripleo.elijah_elevateder.stages.deduce.post_bytecode;

import org.jetbrains.annotations.NotNull;
import tripleo.elijah_fluffy.diagnostic.ElDiagnostic;
import tripleo.elijah_fluffy.diagnostic.ElLocatable;

import java.io.PrintStream;
import java.util.List;

public interface GCFM_Diagnostic extends ElDiagnostic {
	static @NotNull GCFM_Diagnostic forThis(final @NotNull String aMessage, final @NotNull String aCode,
			final @NotNull Severity aSeverity) {
		return new GCFM_Diagnostic() {
			@Override
			public String _message() {
				return aMessage;
			}

			@Override
			public String code() {
				return aCode;
			}

			@Override
			public @NotNull ElLocatable primary() {
				return null;
			}

			@Override
			public void report(final @NotNull PrintStream stream) {
				stream.printf("%s %s%n", code(), _message());
			}

			@Override
			public @NotNull List<ElLocatable> secondary() {
				return null;
			}

			@Override
			public Severity severity() {
				return aSeverity;
			}
		};
	}

	String _message();
}
