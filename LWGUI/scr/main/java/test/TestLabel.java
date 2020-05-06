package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import animation.FlyIn;
import animation.FlyOut;
import animation.Animation.animationConstraint;
import comp.Label;

public class TestLabel extends Label{

	public TestLabel(String text) {
		super(text);
		setFont(new Font("Lucia Console", Font.BOLD,25));
		queAnimation(new FlyOut(animationConstraint.LEFT_TO_RIGHT));
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.getBounds().contains(e.getPoint())) {
			foreground = Color.BLUE;
		}else {
			foreground = Color.BLACK;
		}
		if (getBounds().contains(e.getPoint())) {
			queAnimation(new FlyIn());
		} else {
			queAnimation(new FlyOut(animationConstraint.LEFT_TO_RIGHT));
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(this.getBounds().contains(e.getPoint())) {
			System.out.println(this.getText());
		}
	}

}
