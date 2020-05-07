package animation;

/**
 * 
 * increases the size of the given component by the specified percent
 * 
 * Input should be in the form of a percentage of the current size of the
 * component. E.g. new Grow(200) would make the component 200% bigger
 * 
 * @author Robert Doneux
 * @version 0.1
 *
 */

public class Grow extends Animation {

	private double magnitude;

	public Grow(int magnitude) {
		if (magnitude < 0) {
			throw new IllegalArgumentException("magnitude must be positive. Current magnitude: " + magnitude);
		}
		this.magnitude = magnitude;
		this.type = animationType.GROW;
	}

	@Override
	public void run() {

		int refresh = 20;
		int totalWidth = (int) ((magnitude / 100) * parent.getWidth());
		int totalHeight = (int) ((magnitude / 100) * parent.getHeight());
		
		while (running) {

			// System.out.println(totalWidth + " ~ " + totalHeight);

			if (parent.getAnimationWidth() < totalWidth) {
				parent.incrementAnimationWidth(2);
				parent.incrementAnimationX(-1);
				//parent.setWidth(parent.getWidth() + 2);
			}
			if (parent.getAnimationHeight() < totalHeight) {
				parent.incrementAnimationHeight(2);
				parent.incrementAnimationY(-1);
				parent.setHeight(parent.getHeight());
			}

			if (parent.getAnimationHeight() >= totalHeight && parent.getAnimationWidth() >= totalWidth) {
				System.out.println("lets get out of here!");
				break;
			}

			try {
				Thread.sleep(refresh);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
