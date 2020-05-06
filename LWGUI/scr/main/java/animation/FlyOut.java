package animation;

/**
 * 
 * This animation increases or decreases a components offset until it is
 * positioned outside of the parent component's clip space. Requires an
 * animationConstraint to identify which direction the user wants the component
 * to leave the space. Once left, the component will remain just outside of the
 * clip space
 * 
 * @author Robert Doneux
 * @version 0.1
 */

public class FlyOut extends Animation {

	private animationConstraint constraint;

	public FlyOut(animationConstraint constraint) {
		this.constraint = constraint;
		this.type = animationType.FLY_OUT;
	}

	/**
	 * creates a separate thread to complete the animation. Once the animation has
	 * been completed the refresh rate drops to once every 50 milliseconds to ensure
	 * that the component remains outside of the clip space if the frame is resized.
	 */
	@Override
	public void run() {

		complete = false;
		int refresh = 20;

		while (running) {
			if (parent.isLoaded() && parent.getParent() != null) {
				switch (constraint) {
				case RIGHT_TO_LEFT:
					if (parent.getVisualX() > -(parent.getParent().getX() + parent.getParent().getWidth())
							&& !complete) {
						parent.setAnimationX(parent.getAnimationX() - parent.getParent().getVisualWidth() / 20);
					} else {
						complete = true;
						refresh = 50;
						parent.setAnimationX(
								-parent.getX() - (parent.getParent().getX() + parent.getParent().getWidth()));
					}
					break;
				case LEFT_TO_RIGHT:
					if (parent.getVisualX() < (parent.getParent().getX() + parent.getParent().getWidth())
							&& !complete) {
						parent.setAnimationX(parent.getAnimationX() + parent.getParent().getVisualWidth() / 20);
					} else {
						complete = true;
						refresh = 50;
						parent.setAnimationX(parent.getParent().getX() + parent.getParent().getWidth());
					}
					break;
				case TOP_TO_BOTTOM:
					if (parent.getVisualY() < (parent.getParent().getY() + parent.getParent().getHeight())
							&& !complete) {
						parent.setAnimationY(parent.getAnimationY() + parent.getParent().getVisualHeight() / 20);
					} else {
						complete = true;
						refresh = 50;
						parent.setAnimationY(parent.getParent().getY() + parent.getParent().getHeight());
					}
					break;
				case BOTTOM_TO_TOP:
					if (parent.getVisualY() > -(parent.getParent().getY() + parent.getParent().getHeight())
							&& !complete) {
						parent.setAnimationY(parent.getAnimationY() - parent.getParent().getVisualHeight() / 20);
					} else {
						complete = true;
						refresh = 50;
						parent.setAnimationY(
								-parent.getY() - (parent.getParent().getY() + parent.getParent().getHeight()));
					}
					break;
				default:
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
