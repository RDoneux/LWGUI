package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import comp.Label;

public class TestLabel extends Label{

	public TestLabel(String text) {
		super(text);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.getBounds().contains(e.getPoint())) {
			foreground = Color.BLUE;
		}else {
			foreground = Color.BLACK;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(this.getBounds().contains(e.getPoint())) {
			System.out.println(this.getText());
		}
	}

}
