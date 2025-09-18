// See https://profs.etsmtl.ca/mmcguffin/learn/java/09-clocks/
package net.ptidej.teamvm.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Clock extends Applet implements Runnable {
	private static final long serialVersionUID = 1821261987629047567L;

	private int width, height;
	private Thread t = null;
	private boolean threadSuspended;
	private int hours = 0, minutes = 0, seconds = 0;
	private String timeString = "";

	public void init() {
		this.width = this.getSize().width;
		this.height = this.getSize().height;
		this.setBackground(Color.black);
	}

	public void start() {
		if (this.t == null) {
			this.t = new Thread(this);
			this.t.setPriority(Thread.MIN_PRIORITY);
			this.threadSuspended = false;
			this.t.start();
		}
		else {
			if (this.threadSuspended) {
				this.threadSuspended = false;
				synchronized (this) {
					this.notify();
				}
			}
		}
	}

	public void stop() {
		this.threadSuspended = true;
	}

	public void run() {
		try {
			while (true) {

				// Here's where the thread does some work
				final Calendar cal = Calendar.getInstance();
				this.hours = cal.get(Calendar.HOUR_OF_DAY);
				if (this.hours > 12) {
					this.hours -= 12;
				}
				this.minutes = cal.get(Calendar.MINUTE);
				this.seconds = cal.get(Calendar.SECOND);

				final SimpleDateFormat formatter = new SimpleDateFormat(
						"hh:mm:ss", Locale.getDefault());
				final Date date = cal.getTime();
				this.timeString = formatter.format(date);

				// Now the thread checks to see if it should suspend itself
				if (this.threadSuspended) {
					synchronized (this) {
						while (this.threadSuspended) {
							this.wait();
						}
					}
				}
				this.repaint();
				Thread.sleep(1000); // interval given in milliseconds
			}
		}
		catch (final InterruptedException e) {
		}
	}

	void drawHand(final double angle, final int radius, final Graphics g) {
		final double newAngle = angle - 0.5 * Math.PI;
		int x = (int) (radius * Math.cos(newAngle));
		int y = (int) (radius * Math.sin(newAngle));

		g.drawLine(this.width / 2, this.height / 2, this.width / 2 + x,
				this.height / 2 + y);
	}

	void drawWedge(final double angle, final int radius, final Graphics g) {
		double newAngle;
		
		newAngle = angle - 0.5 * Math.PI;
		int x = (int) (radius * Math.cos(newAngle));
		int y = (int) (radius * Math.sin(newAngle));
		newAngle += 2 * Math.PI / 3;
		int x2 = (int) (5 * Math.cos(newAngle));
		int y2 = (int) (5 * Math.sin(newAngle));
		newAngle += 2 * Math.PI / 3;
		int x3 = (int) (5 * Math.cos(newAngle));
		int y3 = (int) (5 * Math.sin(newAngle));

		g.drawLine(this.width / 2 + x2, this.height / 2 + y2,
				this.width / 2 + x, this.height / 2 + y);
		g.drawLine(this.width / 2 + x3, this.height / 2 + y3,
				this.width / 2 + x, this.height / 2 + y);
		g.drawLine(this.width / 2 + x2, this.height / 2 + y2,
				this.width / 2 + x3, this.height / 2 + y3);
	}

	public void paint(final Graphics g) {
		g.setColor(Color.gray);
		this.drawWedge(2 * Math.PI * this.hours / 12, this.width / 5, g);
		this.drawWedge(2 * Math.PI * this.minutes / 60, this.width / 3, g);
		this.drawHand(2 * Math.PI * this.seconds / 60, this.width / 2, g);
		g.setColor(Color.white);
		g.drawString(this.timeString, 10, this.height - 10);
	}
}
