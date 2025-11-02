package tripleo.elijah_fluffy.anno;

import tripleo.elijah_fluffy.util.*;

public class NeverReached extends RuntimeException {
	public NeverReached(){
		NotImplementedException.raise_stop();
	}
}
