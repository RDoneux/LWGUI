package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import comp.Button;

public class TestButton extends Button {

	public TestButton(String text) {
		super(text);
		setFont(new Font("Lucida Console", Font.PLAIN, 20));
		setForeground(new Color(10,20,235));
		try {
			setImage(ImageIO.read(new File("C:\\Users\\Rober\\Desktop\\Untitled.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void action() {
		System.out.println("button has been pressed");
	}
	
}
