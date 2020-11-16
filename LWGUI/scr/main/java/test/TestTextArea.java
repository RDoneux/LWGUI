package test;

import java.awt.Font;
import java.awt.event.MouseEvent;

import animation.Animation.animationConstraint;
import animation.FlyIn;
import animation.FlyOut;
import animation.Offset;
import comp.TextArea;

public class TestTextArea extends TextArea {

	public TestTextArea() {
		setText("The Cyborg looks conserned...");
		setFont(new Font("Lucida Console", Font.BOLD, 36));
		// setEditable(false);
//		try {
//			setImage(ImageIO.read(new File("C:\\Users\\Rober\\Desktop\\wooden background.jpg")));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		queAnimation(new Offset(animationConstraint.WEST));
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		if (getBounds().contains(e.getPoint())) {
			queAnimation(new FlyIn());
		} else {
			if (!isFocused()) {
				queAnimation(new FlyOut(animationConstraint.RIGHT_TO_LEFT));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseMoved(e);
	}

}
