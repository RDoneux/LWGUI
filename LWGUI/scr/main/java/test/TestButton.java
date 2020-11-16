package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import animation.Bounce;
import animation.FlyIn;
import comp.Button;
import effects.Highlight;

public class TestButton extends Button {

	public TestButton(String text) {
		super(text);
		setFont(new Font("Lucida Console", Font.PLAIN, 20));
		setForeground(new Color(10, 20, 235));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (getBounds().contains(e.getPoint())) {
			queAnimation(new Bounce());
			setEffect(new Highlight(new Color(50, 50, 200)));

		} else {
			queAnimation(new FlyIn(2));
			setEffect(null);
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
