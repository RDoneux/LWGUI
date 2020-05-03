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
		int counter = 0;
		while (running) {
			counter ++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
						
			if (parent.isLoaded()) {
				if (!complete) {
					if ((-parent.getAnimationX() < (parent.getX() + parent.getWidth()))) {

						if (constraint == animationConstraint.LEFT_TO_RIGHT) {
							parent.incrementAnimationX(-1);
						}
						if (constraint == animationConstraint.RIGHT_TO_LEFT) {
							parent.incrementAnimationX(-1);
						}

					} else {
						complete = true;
					}
				} else {
						parent.setAnimationX(-(parent.getX() + parent.getWidth()));
					}
				}
			
			



		}

	}

	@Override
	public void excecute() {
		// TODO Auto-generated method stub

	}

}
