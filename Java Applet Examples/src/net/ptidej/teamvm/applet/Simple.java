// See https://www.tutorialspoint.com/java/java_applet_basics.htm
package net.ptidej.teamvm.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

public class Simple extends Applet {
	private static final long serialVersionUID = 1850362738599189009L;

	private String userName;
	private int mouseX, mouseY;
	private Color colour;

	@Override
	public void init() {
		// Called after the param tags inside the applet tag have been processed
		super.init();

		/*
		 *	<applet code="Simple" width=50 height=50>
		 *	<param name="UserName" value="yann">
		 *	</applet>
		 */

		this.userName = super.getParameter("UserName");

		this.mouseX = 20;
		this.mouseY = 20;
		this.colour = Color.BLUE;
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(final MouseEvent e) {
			}

			@Override
			public void mousePressed(final MouseEvent e) {
				Simple.this.mouseX = e.getPoint().x;
				Simple.this.mouseY = e.getPoint().y;

				if (Simple.this.colour == Color.BLUE) {
					Simple.this.colour = Color.RED;
				}
				else {
					Simple.this.colour = Color.BLUE;
				}
				
				Simple.this.repaint();
			}

			@Override
			public void mouseExited(final MouseEvent e) {
			}

			@Override
			public void mouseEntered(final MouseEvent e) {
			}

			@Override
			public void mouseClicked(final MouseEvent e) {
			}
		});
	}

	@Override
	public void start() {
		// Called after the browser calls the init method and whenever the user sees returns to the page containing the applet after having gone off to other pages
		super.start();

		super.resize(300, 150);
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
		g.setColor(this.colour);
		g.drawString("Hello, World from " + this.userName, this.mouseX,
				this.mouseY);
		g.drawString("Running from " + this.getCodeBase(), this.mouseX,
				this.mouseY + 11);

		final Date dt = new Date();
		super.showStatus("Today is" + dt);
	}

}
