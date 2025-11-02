package tripleo.paths;

import tripleo.graph.*;

/// fixme: great for clojure
public interface Asseveration {
	Object target();

	Asseverate code();

	void onLogProgress(Asseverable ce);
}
