package test;

import java.awt.event.MouseEvent;

import animation.Animation.animationConstraint;
import animation.FlyIn;
import animation.FlyOut;
import animation.Offset;
import comp.GUIComponent;
import comp.Panel;

public class SecondTestPanel extends Panel {

	public SecondTestPanel() {
		queAnimation(new Offset(animationConstraint.WEST));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (GUIComponent child : children) {
			child.mouseMoved(e);
		}
		if (getBounds().contains(e.getPoint())) {
			this.queAnimation(new FlyIn());

		} else {
			this.queAnimation(new FlyOut(animationConstraint.RIGHT_TO_LEFT));

		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseMoved(e);
	}

}
