package animation;

/**
 * 
 * Returns the target component to it's original position within the window by
 * increasing or decreasing the animation values until they are 0. This
 * animation doesn't require a direction as it will automatically detect the
 * animation value and return it to 0
 * 
 * @author Robert Doneux
 * @version 0.1
 *
 */

public class FlyIn extends Animation {

	private int speed;

	public FlyIn(int speed) {
		this.speed = speed;
		this.type = animationType.FLY_IN;
	}

	public FlyIn() {
		this.type = animationType.FLY_IN;
	}

	/**
	 * creates a new thread which moves the attached parent component's animation
	 * values to return the component to it's intended location on the screen
	 */
	@Override
	public void run() {

		complete = false;
		int refresh = 20;
		if(speed <= 0) {
			speed = 5;
		}

		while (running) {
			
			if (parent.isLoaded()) {
				if (parent.getParent() == null) {
					return;
				}
				parent.setShow(true);
				if (parent.getAnimationX() < -speed) {
					parent.incrementAnimationX(speed);
				} else if (parent.getAnimationX() > speed) {
					parent.incrementAnimationX(-speed);
				}
				if (parent.getAnimationY() < -speed) {
					parent.incrementAnimationY(speed);
				} else if (parent.getAnimationY() > speed) {
					parent.incrementAnimationY(-speed);
				}
				if (parent.getAnimationX() <= speed
						&& parent.getAnimationX() >= -speed
						&& parent.getAnimationY() <= speed
						&& parent.getAnimationY() >= -speed) {
					parent.setAnimationX(0);
					parent.setAnimationY(0);
					break;
				}
			}
			try {
				Thread.sleep(refresh);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
