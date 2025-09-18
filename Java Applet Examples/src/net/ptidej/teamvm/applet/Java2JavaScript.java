// See https://www.codejava.net/java-se/applet/liveconnect-the-api-for-communication-between-java-applet-and-javascript
package net.ptidej.teamvm.applet;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

public class Java2JavaScript extends Applet {
	private static final long serialVersionUID = -3697537745845844133L;

	private JButton button = new JButton("Call Javascript");
	private JLabel label = new JLabel();

	public void init() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Thread runner = new Thread(new Runnable() {
					public void run() {
						try {
							Java2JavaScript.this.testLiveConnect();
						}
						catch (final JSException jse) {
							jse.printStackTrace();
						}
					}
				});
				runner.start();
			}
		});
	}

	private void testLiveConnect() throws JSException {
		final JSObject jso = JSObject.getWindow(this);

		// call Javascript's method foo() with no argument
		String result = (String) jso.call("foo", null);
		label.setText(result);

		// delay 2 seconds to see the result
		try {
			Thread.sleep(2000);
		}
		catch (final InterruptedException ie) {
		}

		// call Javascript's method foo() with two arguments
		result = (String) jso.call("bar", new String[] { "Alice", "Alisa" });
		label.setText(result);
		try {
			Thread.sleep(2000);
		}
		catch (final InterruptedException ie) {
		}

		// execute a Javascript expression
		final String expression = "alert('Hi, I am from Javascript.');";
		jso.eval(expression);
		try {
			Thread.sleep(2000);
		}
		catch (InterruptedException ie) {
		}

		// get value of a named member from Javascript
		result = (String) jso.getMember("coop");
		label.setText(result);
		try {
			Thread.sleep(2000);
		}
		catch (final InterruptedException ie) {
		}

		// get value of an indexed member from Javascript
		result = (String) jso.getSlot(1);
		label.setText(result);
	}
}
