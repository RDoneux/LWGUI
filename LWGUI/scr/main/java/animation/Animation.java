package animation;

import comp.GUIComponent;
import tools.IDGenerator;

public abstract class Animation implements Runnable {

	protected boolean running;
	protected Thread thread;
	
	protected int ID;
	protected animationType type;
	
	protected boolean complete;

	public enum animationConstraint {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT, TOP_TO_BOTTOM, BOTTOM_TO_TOP;
	}
	
	public enum animationType {
		FLY_IN, FLY_OUT;
	}
	
	public Animation() {
		ID = IDGenerator.generateID();
	}

	protected GUIComponent parent;

	public abstract void excecute();

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

	public void setParent(GUIComponent parent) {
		this.parent = parent;
	}
	
	public int getID() {
		return ID;
	}

	public animationType getType() {
		return type;
	}
}
