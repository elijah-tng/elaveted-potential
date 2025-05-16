package tripleo.elijah_fluffy.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DefaultEventualRegister implements EventualRegister {
	private final          List<Eventual<?>> _eventuals = new ArrayList<>();
	private final @NotNull String            host;

	public DefaultEventualRegister(final @NotNull String aHostString) {
		this.host = aHostString;
	}

	@Override
	public <P> void register(final Eventual<P> e) {
		_eventuals.add(e);
	}

	@Override
	public void checkFinishEventuals() {
		for (Eventual<?> eventual : _eventuals) {
			if (eventual.isResolved()) {
			} else {
				System.err.println("[" + _host() + "] failed for " + eventual.description());
			}
		}
	}

	@Override
	public @NotNull String _host() {
		return this.host;
	}

	@Override
	public Operation<Ok> maybeCheckFinishEventuals() {
		return null;
	}
}
