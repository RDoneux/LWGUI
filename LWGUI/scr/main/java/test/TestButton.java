package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import animation.FlyIn;
import animation.FlyOut;
import animation.Animation.animationConstraint;
import comp.Button;
import comp.GUIComponent;

public class TestButton extends Button {

	public TestButton(String text) {
		super(text);
		setFont(new Font("Lucida Console", Font.PLAIN, 20));
		setForeground(new Color(10,20,235));
		queAnimation(new FlyOut(animationConstraint.RIGHT_TO_LEFT));

//		try {
//			setImage(ImageIO.read(new File("C:\\Users\\Rober\\Desktop\\Untitled.png")));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (getBounds().contains(e.getPoint())) {
			queAnimation(new FlyIn(animationConstraint.LEFT_TO_RIGHT));
		} else {
			queAnimation(new FlyOut(animationConstraint.RIGHT_TO_LEFT));
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
