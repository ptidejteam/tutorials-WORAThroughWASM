// See https://docs.oracle.com/javase/tutorial/deployment/applet/invokingAppletMethodsFromJavaScript.html
package net.ptidej.teamvm.applet;

import java.applet.Applet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MathApplet extends Applet {
	private static final long serialVersionUID = 7204571981749946950L;

	public String userName = null;

	public String getGreeting() {
		return "Hello " + userName;
	}

	public Calculator getCalculator() {
		return new Calculator();
	}

	public DateHelper getDateHelper() {
		return new DateHelper();
	}

	public void printOut(String text) {
		System.out.println(text);
	}
}

class Calculator {
	private int a = 0;
	private int b = 0; // assume b > a

	public void setNums(int numA, int numB) {
		a = numA;
		b = numB;
	}

	public int add() {
		return a + b;
	}

	public int[] getNumInRange() {
		int x = a;
		int len = (b - a) + 1;
		int[] range = new int[len];
		for (int i = 0; i < len; i++) {
			range[i] = x++;
			System.out.println("i: " + i + " ; range[i]: " + range[i]);
		}
		return range;
	}
}

class DateHelper {
	public static String label = null;

	public String getDate() {
		return label + " " + new SimpleDateFormat().format(new Date());
	}

}