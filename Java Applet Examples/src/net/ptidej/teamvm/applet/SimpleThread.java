// See https://profs.etsmtl.ca/mmcguffin/learn/java/06-threads/
package net.ptidej.teamvm.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class SimpleThread extends Applet implements Runnable {
	private static final long serialVersionUID = -2008330339353193565L;

	private int width, height;
	private int i = 0;
	private Thread t = null;
	private boolean threadSuspended;

	// Executed when the applet is first created.
	public void init() {
		System.out.println("init(): begin");
		this.width = this.getSize().width;
		this.height = this.getSize().height;
		this.setBackground(Color.black);
		System.out.println("init(): end");
	}

	// Executed when the applet is destroyed.
	public void destroy() {
		System.out.println("destroy()");
	}

	// Executed after the applet is created; and also whenever
	// the browser returns to the page containing the applet.
	public void start() {
		System.out.println("start(): begin");
		if (this.t == null) {
			System.out.println("start(): creating thread");
			this.t = new Thread(this);
			System.out.println("start(): starting thread");
			this.threadSuspended = false;
			this.t.start();
		}
		else {
			if (this.threadSuspended) {
				this.threadSuspended = false;
				System.out.println("start(): notifying thread");
				synchronized (this) {
					this.notify();
				}
			}
		}
		System.out.println("start(): end");
	}

	// Executed whenever the browser leaves the page containing the applet.
	public void stop() {
		System.out.println("stop(): begin");
		this.threadSuspended = true;
	}

	// Executed within the thread that this applet created.
	public void run() {
		System.out.println("run(): begin");
		try {
			while (true) {
				System.out.println("run(): awake");

				// Here's where the thread does some work
				this.i++; // this is shorthand for "i = i+1;"
				if (this.i == 10) {
					this.i = 0;
				}
				this.showStatus("i is " + this.i);

				// Now the thread checks to see if it should suspend itself
				if (this.threadSuspended) {
					synchronized (this) {
						while (this.threadSuspended) {
							System.out.println("run(): waiting");
							this.wait();
						}
					}
				}
				System.out.println("run(): requesting repaint");
				this.repaint();
				System.out.println("run(): sleeping");
				Thread.sleep(500); // interval given in milliseconds
			}
		}
		catch (final InterruptedException e) {
		}
		System.out.println("run(): end");
	}

	// Executed whenever the applet is asked to redraw itself.
	public void paint(final Graphics g) {
		System.out.println("paint()");
		g.setColor(Color.green);
		g.drawLine(this.width, this.height, this.i * this.width / 10, 0);
	}
}
