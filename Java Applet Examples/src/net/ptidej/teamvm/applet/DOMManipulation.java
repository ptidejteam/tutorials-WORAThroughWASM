// See https://www.tutorialspoint.com/java/java_applet_basics.htm
package net.ptidej.teamvm.applet;

import java.applet.Applet;
import java.awt.Graphics;
import java.lang.reflect.Method;

import org.w3c.dom.html.HTMLBodyElement;
import org.w3c.dom.html.HTMLDocument;

public class DOMManipulation extends Applet {
	private static final long serialVersionUID = 1850362738599189009L;

	@Override
	public void init() {
		// Called after the param tags inside the applet tag have been processed
		super.init();
	}

	@Override
	public void start() {
		// Called after the browser calls the init method and whenever the user sees returns to the page containing the applet after having gone off to other pages
		super.start();

		try {
			final Class<?> c = Class.forName("com.sun.java.browser.plugin2.DOM");
			final Method m = c.getMethod("getDocument",
					new Class[] { java.applet.Applet.class });

			// cast object returned as HTMLDocument;
			// then traverse or modify DOM
			final HTMLDocument doc = (HTMLDocument) m.invoke(null,
					new Object[] { this });
			@SuppressWarnings("unused")
			final HTMLBodyElement body = (HTMLBodyElement) doc.getBody();

			// ...
		}
		catch (Exception e) {
			System.out.println("New Java Plug-In not available");
			// In this case, you could fallback to the old
			// bootstrapping mechanism available in the
			// com.sun.java.browser.plugin.dom package
		}
	}

	@Override
	public void stop() {
		// Called when the user moves off the page on which the applet sits
		super.stop();
	}

	@Override
	public void destroy() {
		// Called when the browser shuts down normally
		super.destroy();
	}

	// Overriding paint() method
	@Override
	public void paint(final Graphics g) {
		// Invoked immediately after the start() method, and also any time the applet needs to repaint itself in the browser
	}

}
