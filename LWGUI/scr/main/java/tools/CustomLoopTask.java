package tools;

/**
 * 
 * Provides the user with the option of adding tasks within the animation loop
 * in the {@link Frame} class. This should be used where the animation loop
 * needs greater functionality than just rendering and updating. Examples could
 * be within a networked game, the animation loop should call a 'send packet'
 * method
 * 
 * @author Robert Doneux
 * @version 0.1
 */

public abstract class CustomLoopTask {

	protected double targetCallsPerSecond;
	protected double tc;
	protected double deltaTasks = 0;
	protected int calls = 0;
	protected String name = "Custom Loop Task";
	
	public abstract void excecute();

	public double getTargetCallsPerSecond() {
		return targetCallsPerSecond;
	}

	public void setTargetCallsPerSecond(double targetCallsPerSecond) {
		this.targetCallsPerSecond = targetCallsPerSecond;
		tc = 1000000000 / targetCallsPerSecond;
	}

	public double getDeltaTasks() {
		return deltaTasks;
	}

	public void resetDeltaTasks() {
		deltaTasks--;
	}

	public void setDeltaTasks(double deltaTasks) {
		this.deltaTasks += deltaTasks;
	}

	public double getTc() {
		return tc;
	}

	public void completed() {
		calls++;
	}

	public void resetCalls() {
		calls = 0;
	}
	
	public int getCalls() {
		return calls;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
