package animation;

public class Bounce extends Animation {

	@Override
	public void run() {

		int refresh = 20;
		int x = parent.getX();
		int y = parent.getY();
		double xVel = 0;
		double yVel = -6;
		double gravity = 0.6;

		while (running) {

			yVel += gravity;

			parent.incrementAnimationY((int) yVel);
			parent.incrementAnimationX((int) xVel);



			if (parent.getAnimationY() >= 0) {

				yVel *= -1;
				yVel -= gravity;

			}

			try {
				Thread.sleep(refresh);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
