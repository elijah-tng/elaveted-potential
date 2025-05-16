package tripleo.elijah_elevated_durable.comp_process;

import org.jetbrains.annotations.NotNull;
import tripleo.elijah_fluffy.util.*;

import java.util.ArrayList;
import java.util.List;

public abstract class _AbstractEventualRegister implements EventualRegister {
	protected final List<Eventual<?>> _eventuals = new ArrayList<>();

	@Override
	public <P> void register(final Eventual<P> e) {
		_eventuals.add(e);
	}

	@Override
	public void checkFinishEventuals() {
		for (Eventual<?> eventual : _eventuals) {
			if (eventual.isResolved()) {
			} else {
				SimplePrintLoggerToRemoveSoon.println_err_4("[" + _host() + "] failed for " + eventual.description());
			}
		}
	}

	@Override
	public abstract @NotNull String _host();

	@Override
	public abstract Operation<Ok> maybeCheckFinishEventuals();
}
