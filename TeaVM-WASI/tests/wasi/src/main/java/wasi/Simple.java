package wasi;

public class Simple {
	public static void main(final String[] args) {
		final int n1 = Integer.parseInt(args[0]);
		final int n2 = Integer.parseInt(args[1]);
		System.out.println(n1 + n2);
	}
}
