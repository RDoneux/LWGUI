package animation;

import comp.GUIComponent;

public class FlyIn extends Animation {

	private animationConstraint constraint;

	public FlyIn(animationConstraint constraint) {
		this.constraint = constraint;
		this.type = animationType.FLY_IN;
	}

	@Override
	public  void run() {
		// parent.setAnimationX(0);
		// parent.setAnimationY(0);

		complete = false;

		while (running) {

			if (parent.isLoaded()) {
				if (!complete) {
					//System.out.println(parent.getAnimationX());
					if (parent.getAnimationX() <= 0) {
						if (constraint == animationConstraint.LEFT_TO_RIGHT) {
							parent.setAnimationX(parent.getAnimationX() + 1);
						}
						if (constraint == animationConstraint.RIGHT_TO_LEFT) {
							parent.setAnimationX(parent.getAnimationX() - 1);
						}
					} else {
						complete = true;
					}
				} else {
					parent.setAnimationX(0);
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
