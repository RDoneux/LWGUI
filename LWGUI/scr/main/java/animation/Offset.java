package animation;

/**
 * 
 * An exception of {@link Animation} this class provides an initial offset to a
 * {@link GUIComponent}. It can also be used to 'jump' a component off to the
 * side of a parent clip space without any animation making it seem like it's
 * disappeared
 * 
 * @author Robert Doneux
 * @version 0.1
 *
 */

public class Offset extends Animation {

	private animationConstraint constraint;

	public Offset(animationConstraint constraint) {
		// ensure that the user has entered a valid animationConstraint
		if (constraint != animationConstraint.NORTH && constraint != animationConstraint.EAST
				&& constraint != animationConstraint.SOUTH && constraint != animationConstraint.WEST) {
			throw new IllegalArgumentException(
					"animationConstraint must be NORTH, EAST, SOUTH, WEST. Current constraint: " + constraint);
		}
		this.type = animationType.OFFSET;
		this.constraint = constraint;
	}

	@Override
	public void run() {

		int refresh = 50;

		while (running) {
			if (parent.getParent() != null) {
				parent.setShow(false);
				switch (constraint) {
				case NORTH:
					parent.setAnimationY(
							-parent.getY() - (parent.getParent().getVisualY() + parent.getParent().getVisualHeight()));
					break;
				case EAST:
					parent.setAnimationX(parent.getParent().getWidth() - parent.getX());
					break;
				case SOUTH:
					parent.setAnimationY(parent.getParent().getHeight() - parent.getY());
					break;
				case WEST:
					parent.setAnimationX(
							-parent.getX() - (parent.getParent().getVisualX() + parent.getParent().getVisualWidth()));
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
