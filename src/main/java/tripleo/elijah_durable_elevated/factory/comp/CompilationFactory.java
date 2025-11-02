package tripleo.elijah_durable_elevated.factory.comp;

import org.jetbrains.annotations.*;
import tripleo.elijah.comp.*;
import tripleo.elijah.comp.i.*;
import tripleo.elijah_durable_elevated.comp.*;
import tripleo.elijah_durable_elevated.stages.deduce.*;
import tripleo.elijah_elevated_durable.comp.*;

import java.util.*;

public enum CompilationFactory {
	;

	@Contract(" -> new")
	public static @NotNull EDL_Compilation mkCompilation0() {
		return mkCompilation(new EDL_ErrSink(), new EDL_IO());
	}

	@Contract("_, _ -> new")
	public static @NotNull EDL_Compilation mkCompilation(final ErrSink eee, final IO io) {
		final EDL_Compilation comp = new EDL_Compilation(eee, io);
		comp.doPost();
		return comp;
	}

	public static @NotNull EDL_Compilation mkCompilation2(final List<IFunctionMapHook> aMapHooks) {
		final EDL_ErrSink errSink = new EDL_ErrSink();
		final IO         io      = new EDL_IO();

		final @NotNull EDL_Compilation c = mkCompilation(errSink, io);

		c.testMapHooks(aMapHooks);

		return c;
	}
}
