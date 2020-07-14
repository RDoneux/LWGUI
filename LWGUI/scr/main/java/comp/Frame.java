package comp;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import constraints.Layout;
import tools.CustomLoopTask;

/**
 * 
 * Top level container for all GUIComponents. Requires a {@link Layout} manager
 * to position components.
 * 
 * @author Robert Doneux
 * @version 0.1
 *
 */

public class Frame extends Canvas
		implements Runnable, MouseWheelListener, MouseListener, MouseMotionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2122532236586028185L;

	public static int FRAME_WIDTH;
	public static int FRAME_HEIGHT;
	// public static Point MOUSE_LOCATION;

	private JFrame frame;

	private Layout layout;

	private boolean running;
	private boolean decorated;
	private Thread thread;

	private CopyOnWriteArrayList<GUIComponent> children = new CopyOnWriteArrayList<>();

	public Point mouseLocation; // the current mouse location on the screen mapped to the frame's content pane
								// size

	public Frame() {

		frame = new JFrame();
		frame.setSize(new Dimension(600, 400));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		setListeners();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stop(); // if multiple windows are running concurrently, stop the thread on the closed
						// window
			}
		});

		frame.add(this);

		start();

	}

	public Frame(Boolean decorate) {

		this.decorated = decorate;

		frame = new JFrame();
		frame.setSize(new Dimension(600, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(decorate);
		frame.setVisible(true);
		// the extended state of the frame must be set after it has loaded if hardware
		// Acceleration is enabled
		setListeners();

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stop(); // if multiple windows are running concurrently, stop the thread on the closed
						// window
			}
		});

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

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stop(); // if multiple windows are running concurrently, stop the thread on the closed
						// window
			}
		});

		frame.add(this);

		start();

	}

	public void setFullScreen() {

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		requestFocus();

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
			thread.interrupt();
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

		Graphics g = bs.getDrawGraphics();

		g.setClip(frame.getBounds());
		g.fillRect(0, 0, getWidth(), getHeight());

		for (GUIComponent child : children) {
			child.drawEffect(g);
			child.paint(g);
		}

		if (layout != null && layout.isDebugging()) {
			layout.debug(g);
		}
		bs.show();
		g.dispose();

	}

	private boolean inside;

	public void revise() {

		// ensure that the static variables are up to date in the event of a window size
		// change.
		// MOUSE_LOCATION = new Point(MouseInfo.getPointerInfo().getLocation());

		FRAME_WIDTH = frame.getContentPane().getWidth();
		FRAME_HEIGHT = frame.getContentPane().getHeight();

		// identify if the mouse has left the Frame
		mouseLocation = new Point(MouseInfo.getPointerInfo().getLocation());
		SwingUtilities.convertPointFromScreen(mouseLocation, this);
		if (!getBounds().contains(mouseLocation) && inside) {
			// mouseExited(new MouseEvent(this, MouseEvent.MOUSE_EXITED,
			// System.currentTimeMillis(), 0, 0, mouseLocation.x,
			// mouseLocation.y, false));
			inside = false;
		} else if (getBounds().contains(mouseLocation) && !inside) {
			// mouseEntered(new MouseEvent(this, MouseEvent.MOUSE_ENTERED,
			// System.currentTimeMillis(), 0, 0,
			// mouseLocation.x, mouseLocation.y, false));
			inside = true;
		}

		if (layout != null) {
			layout.updateLayout();
		}

		for (GUIComponent child : children) {
			child.revise();
		}

	}

	public void add(GUIComponent child) {

		child.setDisplayWindow(this);
		child.setParent(this);

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

	public CopyOnWriteArrayList<GUIComponent> getChildren() {
		return children;
	}

	public void setSize(Dimension size) {
		frame.setSize(size);
		frame.setLocationRelativeTo(null);
	}

	public JFrame getDisplayFrame() {
		return frame;
	}

	public Rectangle getBounds() {
		return new Rectangle(frame.getContentPane().getX(), frame.getContentPane().getY(),
				frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
	}

	public void debugLayout() {
		layout.setDebugging(true);
	}

	public double getTargetUPS() {
		return targetUPS;
	}

	public void setTargetUPS(double targetUPS) {
		this.targetUPS = targetUPS;
		us = 1000000000 / targetUPS;
	}

	public double getTargetFPS() {
		return targetFPS;
	}

	public void setTargetFPS(double targetFPS) {
		this.targetFPS = targetFPS;
		fs = 1000000000 / targetFPS;
	}

	public void addCustomLoopTask(CustomLoopTask task) {
		customTasks.add(task);
	}

	public CustomLoopTask getCustomLoopTask(int i) {
		return customTasks.get(i);
	}

	public ArrayList<CustomLoopTask> getCustomLoopTasks() {
		return customTasks;
	}

	private ArrayList<CustomLoopTask> customTasks = new ArrayList<>();
	private double targetUPS = 30.0;
	private double targetFPS = 30.0;
	private double us;
	private double fs;

	/**
	 * Animation loop
	 */
	@Override
	public void run() {

		long lastTime = System.nanoTime();
		us = 1000000000 / targetUPS;
		fs = 1000000000 / targetFPS;
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

			for (CustomLoopTask task : customTasks) {
				task.setDeltaTasks((now - lastTime) / task.getTc());
				if (task.getDeltaTasks() >= 1) {
					task.excecute();
					task.completed();
					task.resetDeltaTasks();
				}
			}

			// set waiting to free up CPU
			long wait = (long) ((now + fs) - System.nanoTime()) / 1000000;
			if (wait < 0)
				wait = 0;
			lastTime = now;

			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
			}

			// update title information
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				String title = "FPS: " + frames + " UPS: " + updates;

				frames = 0;
				updates = 0;
				for (CustomLoopTask task : customTasks) {
					title = title + " " + task.getName() + ": " + task.getCalls();
					task.resetCalls();
				}
				frame.setTitle(title);

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
	public synchronized void mouseMoved(MouseEvent arg0) {
		for (GUIComponent child : children) {
			child.mouseMoved(arg0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		for (GUIComponent child : children) {
			child.mouseClicked(arg0);
		}
	}

	@Override
	public synchronized void mouseEntered(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseEntered(arg0);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		for (GUIComponent child : children) {
			child.mouseExited(arg0);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		for (GUIComponent child : children) {
			child.mousePressed(arg0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		for (GUIComponent child : children) {
			child.mouseReleased(arg0);
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