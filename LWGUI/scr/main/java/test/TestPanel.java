package test;

import java.awt.event.MouseEvent;

import animation.Bounce;
import animation.FlyIn;
import comp.GUIComponent;
import comp.Panel;

public class TestPanel extends Panel {

	public TestPanel() {
		//queAnimation(new Offset(animationConstraint.WEST));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (GUIComponent child : children) {
			child.mouseMoved(e);
		}
		if (getBounds().contains(e.getPoint())) {
			this.queAnimation(new Bounce());
		} else {
			this.queAnimation(new FlyIn(4));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseMoved(e);
	}

}
