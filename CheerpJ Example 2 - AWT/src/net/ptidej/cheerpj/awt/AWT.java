package net.ptidej.cheerpj.awt;

import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWT {
	public static void main(final String[] args) {
		final int n;
		if (args.length == 0) {
			n = 10;
		}
		else {
			n = Integer.parseInt(args[0]);
		}

		final Frame frame = new Frame("Fibonacci");
		final Label label = new Label("F(" + n + ") = " + fib(n));
		label.setAlignment(Label.CENTER);
		frame.add(label);
		frame.setSize(300, 300);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private static int fib(final int n) {
		if (n <= 1) {
			return n;
		}
		return fib(n - 1) + fib(n - 2);
	}
}
