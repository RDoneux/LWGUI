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

		while (running) {

			if (parent.isLoaded()) {
				if (!complete) {
					System.out.println(parent.getAnimationX());
					if (-parent.getAnimationX() < (parent.getX() + parent.getWidth())) {

						if (constraint == animationConstraint.LEFT_TO_RIGHT) {
							parent.setAnimationX(parent.getAnimationX() + 2);
						}
						if (constraint == animationConstraint.RIGHT_TO_LEFT) {
							parent.setAnimationX(parent.getAnimationX() - 2);
						}

					} else {
						complete = true;
					}
				} else {
					if (parent.getAnimationX() != -(parent.getX() + parent.getWidth())) {
						parent.setAnimationX(-(parent.getX() + parent.getWidth()));
					}
				}
			}

			try {
				Thread.sleep(1);
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
