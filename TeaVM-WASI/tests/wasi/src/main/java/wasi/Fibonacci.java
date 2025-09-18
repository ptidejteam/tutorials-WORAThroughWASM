package wasi;

public class Fibonacci {
	public static void main(final String[] args) {
		final int n = Integer.parseInt(args[0]);
		System.out.println(Fibonacci.fib(n));
	}

	private static int fib(final int n) {
		if (n <= 1) {
			return n;
		}
		return Fibonacci.fib(n - 1) + Fibonacci.fib(n - 2);
	}
}
