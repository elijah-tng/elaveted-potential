package tripleo.elijah_fluffy.util;

import org.jdeferred2.DoneCallback;
import org.jdeferred2.FailCallback;
import org.jdeferred2.impl.DeferredObject;
import org.jetbrains.annotations.NotNull;
import tripleo.elijah_fluffy.diagnostic.*;

public class Eventual<P> {
	private final DeferredObject<P, Diagnostic, Void> prom = new DeferredObject<>();
	private final String _defaultDescription = "GENERIC-DESCRIPTION";
	private final String mDescription;

	public Eventual(String aDescription) {
		mDescription = aDescription;
	}

	public Eventual() {
		mDescription = _defaultDescription;
	}

	public void resolve(final P p) {
		Eventuals.logResolve(this, p);
		prom.resolve(p);
	}

	public void then(final DoneCallback<? super P> cb) {
		Eventuals.logThen(this, cb);
		prom.then(cb);
	}

	public void register(final @NotNull tripleo.elijah_fluffy.util.EventualRegister ev) {
		Eventuals.logRegister(this, ev);
		ev.register(this);
	}

	@Deprecated
	public void fail(final Diagnostic d) {
		reject(d);
	}

	public void reject(final Diagnostic aReject) {
		prom.reject(aReject);
	}

	public boolean isResolved() {
		return prom.isResolved();
	}

	/**
	 * Please overload this
	 */
	public String description() {
		return mDescription;
	}

    public boolean isFailed() {
		return prom.isRejected();
    }

	public boolean isPending() {
		return prom.isPending();
	}

	public void onFail(final FailCallback<Diagnostic> fcb) {
		prom.fail(fcb);
	}

	public boolean isRejected() {
		return prom.isRejected();
	}

	public void reject(final Exception exc) {
		reject(new ExceptionDiagnostic(exc));
	}
}
