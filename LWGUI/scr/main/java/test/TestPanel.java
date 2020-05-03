package test;

import java.awt.event.MouseEvent;

import animation.Animation.animationConstraint;
import animation.FlyIn;
import animation.FlyOut;
import comp.GUIComponent;
import comp.Panel;

public class TestPanel extends Panel {

	public TestPanel() {
		queAnimation(new FlyOut(animationConstraint.RIGHT_TO_LEFT));
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		for(GUIComponent child: children) {
			child.mouseMoved(e);
		}
		if(getBounds().contains(e.getPoint())) {
			queAnimation(new FlyIn(animationConstraint.LEFT_TO_RIGHT));
		} else {
			queAnimation(new FlyOut(animationConstraint.RIGHT_TO_LEFT));
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		mouseMoved(e);
	}
	
}
