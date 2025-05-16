package tripleo.elijah_fluffy.util;

import org.jetbrains.annotations.Contract;

public class SimplePrintLoggerToRemoveSoon {
	private SimplePrintLoggerToRemoveSoon() {
	}

	public static boolean SPL_PRINTING = false;

	@Contract(pure = true)
	public static void println_err(final String message) {
		if (SPL_PRINTING) System.err.println(message);
	}

	@Contract(pure = true)
	public static void println_err_2(final String message) {
		if (SPL_PRINTING) System.err.println(message);
	}

	@Contract(pure = true) // ??
	public static void println_err_3(final String message) {
		if (SPL_PRINTING) System.err.println(message);
	}

	@Contract(pure = true)
	public static void println_err2(final String message) {
		if (SPL_PRINTING) System.err.println(message);
	}

	@Contract(pure = true)
	public static void println_out(final String message) {
		if (SPL_PRINTING) System.out.println(message);
	}

	@Contract(pure = true)
	public static void println_out_2(final Object aS) {
		if (SPL_PRINTING) System.out.println("" + aS);
	}

	@Contract(pure = true)
	public static void println_out_3(final String message) {
		if (SPL_PRINTING) System.out.println(message);
	}

	@Contract(pure = true)
	public static void println2(final String message) {
		if (SPL_PRINTING) System.out.println(message);
	}

	@Contract(pure = true)
	public static void println_err_4(final Object aS) {
		if (SPL_PRINTING) System.err.println("" + aS);
	}

	@Contract(pure = true)
	public static void println_out_4(final Object aS) {
		if (SPL_PRINTING) System.out.println(aS);
	}

	@Contract(pure = true)
	public static void println_out_4(final int code, final String message) {
		if (SPL_PRINTING) SimplePrintLoggerToRemoveSoon.println_out_4(code + " " + message);
	}
}
