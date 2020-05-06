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

		while (running) {
			if (parent.isLoaded()) {
				if (parent.getParent() == null) {
					return;
				}
				if (parent.getAnimationX() < -(parent.getParent().getVisualWidth() / 20)) {
					parent.setAnimationX(parent.getAnimationX() + parent.getParent().getVisualWidth() / 20);
				} else if (parent.getAnimationX() > parent.getParent().getVisualWidth() / 20) {
					parent.setAnimationX(parent.getAnimationX() - parent.getParent().getVisualWidth() / 20);
				}
				if (parent.getAnimationY() < -(parent.getParent().getVisualHeight() / 20)) {
					parent.setAnimationY(parent.getAnimationY() + parent.getParent().getVisualHeight() / 20);

				} else if (parent.getAnimationY() > parent.getParent().getVisualHeight() / 20) {
					parent.setAnimationY(parent.getAnimationY() - parent.getParent().getVisualHeight() / 20);
				}
				if (parent.getAnimationX() <= (parent.getParent().getVisualWidth() / 20)
						&& parent.getAnimationX() >= -(parent.getParent().getVisualWidth() / 20)
						&& parent.getAnimationY() <= (parent.getParent().getVisualHeight() / 20)
						&& parent.getAnimationY() >= -(parent.getParent().getVisualHeight()) / 20) {
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
}
