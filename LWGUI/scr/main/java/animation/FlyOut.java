package animation;

public class FlyOut extends Animation {

	private animationConstraint constraint;

	public FlyOut(animationConstraint constraint) {
		this.constraint = constraint;
		this.type = animationType.FLY_OUT;
	}

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
