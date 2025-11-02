package tripleo.elijah_fluffy.util;

import com.google.common.collect.*;
import org.jdeferred2.*;
import org.jetbrains.annotations.*;
import tripleo.elijah_fluffy.anno.*;

@SuppressWarnings("rawtypes")
public class Eventuals {
	private final static Multimap<Eventual, Object> evs = ArrayListMultimap.create();

	public static <P> void logResolve(final Eventual aKey, final P aValue) {
		if (evs.containsKey(aKey)) {
			//evs.put(aKey, aValue);
			System.err.println("[TRACE:Eventual] found and removed "+String.format("`%s' for `%s'", aValue, aKey.description()));
			evs.remove(aKey, aValue);
		} else {
			throw new NeverReached();
		}
	}

	public static <P> void logThen(final Eventual<P> aPEventual, final DoneCallback<? super P> aCb) {
		throw new NeverReached();
	}

	public static <P> void logRegister(final Eventual<P> aPEventual, final @NotNull EventualRegister aEv) {
		throw new NeverReached();
	}
}
