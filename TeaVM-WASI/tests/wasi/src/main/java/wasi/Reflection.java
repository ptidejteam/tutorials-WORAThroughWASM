package wasi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
	public static void main(final String[] args) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		final int n = Integer.parseInt(args[0]);

		final Class<?> thisClass = Class.forName("wasi.Reflection");
		final Method fibMethod = thisClass.getDeclaredMethod("fib", int.class);

		System.out.println(fibMethod.invoke(null, n));
	}

	@SuppressWarnings("unused")
	private static int fib(final int n) {
		if (n <= 1) {
			return n;
		}
		return Reflection.fib(n - 1) + Reflection.fib(n - 2);
	}
}
