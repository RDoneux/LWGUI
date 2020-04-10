package comp;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import constraints.Layout;

public class Frame extends Canvas
		implements Runnable, MouseWheelListener, MouseListener, MouseMotionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2122532236586028185L;
	private JFrame frame;

	private Layout layout;

	private boolean running;
	private Thread thread;

	private ArrayList<GUIComponent> children = new ArrayList<>();

	public Frame() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		setListeners();

		frame.add(this);

		start();

	}

	public Frame(Dimension size) {

		frame = new JFrame();
		frame.setSize(size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		setListeners();

		frame.add(this);

		start();

	}

	private void setListeners() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		this.addKeyListener(this);
	}

	public void setLayout(Layout layout) {
		layout.setTopLevelContainer(this);
		this.layout = layout;
	}

	public synchronized void start() {
		thread = new Thread(this);
		running = true;
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void paint() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Graphics g = bs.getDrawGraphics();

				for (GUIComponent child : children) {
					child.paint(g);
				}

				if (layout.isDebugging()) {
					layout.debug(g);
				}

				bs.show();
				g.dispose();
			}
		});
	}

	public void revise() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (layout != null) {
					layout.updateLayout();
				}

				for (GUIComponent child : children) {
					child.revise();
				}
			}
		});
	}

	public void add(GUIComponent child) {
		child.setParent(frame);
		children.add(child);
	}

	public GUIComponent getChild(int i) {
		return children.get(i);
	}

	public GUIComponent getChildByID(int id) {
		for (GUIComponent child : children) {
			if (child.id == id) {
				return child;
			}
		}
		return null;
	}

	public ArrayList<GUIComponent> getChildren() {
		return children;
	}

	public void setSize(Dimension size) {
		frame.setSize(size);
	}

	public JFrame getDisplayFrame() {
		return frame;
	}

	public Rectangle getFrameBounds() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	public void debugLayout() {
		layout.setDebugging(true);
	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		double targetUPS = 30.0;
		double us = 1000000000 / targetUPS;
		double targetFPS = 30.0;
		double fs = 1000000000 / targetFPS;
		double deltaUpdates = 0;
		double deltaFrames = 0;
		int frames = 0;
		int updates = 0;
		long timer = System.currentTimeMillis();

		requestFocus();

		while (running) {

			long now = System.nanoTime();

			// update
			deltaUpdates += (now - lastTime) / us;
			while (deltaUpdates >= 1) {
				revise();
				updates++;
				deltaUpdates--;
			}

			// render
			deltaFrames += (now - lastTime) / fs;
			if (deltaFrames >= 1) {
				paint();
				frames++;
				deltaFrames--;
			}

			// set waiting to free up CPU
			long wait = (long) ((now + fs) - System.nanoTime()) / 1000000;
			if (wait < 0)
				wait = 0;
			lastTime = now;

			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				String title = "FPS: " + frames + " UPS: " + updates;

				frame.setTitle(title);
				frames = 0;
				updates = 0;
			}

		}

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseDragged(arg0);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseMoved(arg0);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseClicked(arg0);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseEntered(arg0);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseExited(arg0);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mousePressed(arg0);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseReleased(arg0);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		for (GUIComponent child : children) {
			child.keyPressed(arg0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		for (GUIComponent child : children) {
			child.keyReleased(arg0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		for (GUIComponent child : children) {
			child.keyTyped(arg0);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseWheelMoved(arg0);
			}
		}
	}
}