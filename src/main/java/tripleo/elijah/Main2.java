package tripleo.elijah;

import clojure.java.api.*;
import clojure.lang.*;

public class Main2 {
	//	{
	final static private Symbol CLOJURE_MAIN  = Symbol.intern("clojure.main");
	final static private Var    REQUIRE       = RT.var("clojure.core", "require");
	final static private Var    LEGACY_REPL   = RT.var("clojure.main", "legacy-repl");
	final static private Var    LEGACY_SCRIPT = RT.var("clojure.main", "legacy-script");
	final static private Var    MAIN          = RT.var("clojure.main", "main");

	/// pretend leiningen
	public static void main(String[] args) throws Exception {
		if (false) {
			IFn REQUIRE = Clojure.var("clojure.core", "require");
			REQUIRE.invoke(Clojure.read("leiningen"));
		} else {
			if (args.length == 0) {
				args = new String[]{"-m", "leiningen.core.main", "run"};
			}
			main2(args);
		}
	}

	public static void main2(String[] args) {
		RT.init();
		REQUIRE.invoke(CLOJURE_MAIN);
		MAIN.applyTo(RT.seq(args));
	}

	public static void legacy_repl(String[] args) {
		RT.init();
		REQUIRE.invoke(CLOJURE_MAIN);
		LEGACY_REPL.invoke(RT.seq(args));
	}

	public static void legacy_script(String[] args) {
		RT.init();
		REQUIRE.invoke(CLOJURE_MAIN);
		LEGACY_SCRIPT.invoke(RT.seq(args));
	}

}
