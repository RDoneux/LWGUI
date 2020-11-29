package animation;

import comp.GUIComponent;
import tools.IDGenerator;

public abstract class Animation implements Runnable {

	protected volatile boolean running;
	protected Thread thread;

	protected int ID;
	protected animationType type;

	protected boolean complete;

	protected GUIComponent parent;

	public enum animationConstraint {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT, TOP_TO_BOTTOM, BOTTOM_TO_TOP, NORTH, EAST, SOUTH, WEST;
	}

	public enum animationType {
		FLY_IN, FLY_OUT, OFFSET, GROW, SHRINK;
	}

	public Animation() {
		ID = IDGenerator.generateID();
	}

	public void start() {
		thread = new Thread(this);
		running = true;
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			Thread.currentThread().interrupt();
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}

	public void setParent(GUIComponent parent) {
		this.parent = parent;
	}

	public int getID() {
		return ID;
	}

	public animationType getType() {
		return type;
	}

	public boolean isRunning() {
		return running;
	}
}
