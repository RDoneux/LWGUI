package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import animation.FlyIn;
import animation.FlyOut;
import animation.Animation.animationConstraint;
import comp.Button;

public class TestButton extends Button {

	public TestButton(String text) {
		super(text);
		setFont(new Font("Lucida Console", Font.PLAIN, 20));
		setForeground(new Color(10,20,235));
		queAnimation(new FlyOut(animationConstraint.TOP_TO_BOTTOM));

//		try {
//			setImage(ImageIO.read(new File("C:\\Users\\Rober\\Desktop\\Untitled.png")));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (getBounds().contains(e.getPoint())) {
			queAnimation(new FlyIn());
		} else {
			queAnimation(new FlyOut(animationConstraint.TOP_TO_BOTTOM));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseMoved(e);
	}
	
	@Override
	public void action() {
		System.out.println("button has been pressed");
	}
	
}
