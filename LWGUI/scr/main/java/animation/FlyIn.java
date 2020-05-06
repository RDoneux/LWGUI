package animation;

public class FlyIn extends Animation {

	private animationConstraint constraint;

	public FlyIn(animationConstraint constraint) {
		this.constraint = constraint;
		this.type = animationType.FLY_IN;
	}

	@Override
	public void run() {

		complete = false;
		int refresh = 20;

		while (running) {
			if (parent.isLoaded()) {
				if (parent.getAnimationX() < 0 && !complete) {
					if (constraint == animationConstraint.LEFT_TO_RIGHT) {
						parent.setAnimationX(parent.getAnimationX() + parent.getParent().getVisualWidth() / 17);
					}
					if (constraint == animationConstraint.RIGHT_TO_LEFT) {
						parent.setAnimationX(parent.getAnimationX() - parent.getParent().getVisualWidth() / 17);
					}
				} else {
					complete = true;
					refresh = 50;
					parent.setAnimationX(0);
				}
			}
			
			try {
				Thread.sleep(refresh);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void excecute() {
		// TODO Auto-generated method stub

	}

}
