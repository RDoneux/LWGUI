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

		if(parent.getParent() != null && parent.getParent().getCurrentAnimation() != null) {
			//parent.setCurrentAnimation(null);
			//return;
		}
		
		while (running) {
			if (parent.isLoaded()) {

				if (parent.getAnimationX() > -(parent.getX() + parent.getWidth()) && !complete) {
					if (constraint == animationConstraint.LEFT_TO_RIGHT) {
						parent.setAnimationX(parent.getAnimationX() + 20);
					}
					if (constraint == animationConstraint.RIGHT_TO_LEFT) {
						parent.setAnimationX(parent.getAnimationX() - 20);
					}
				} else {
					complete = true;
					refresh = 50;
					parent.setAnimationX(-(parent.getX() + parent.getWidth()));
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
