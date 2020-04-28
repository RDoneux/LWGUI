package test;

import java.awt.Font;

import comp.TextArea;

public class TestTextArea extends TextArea{

	public TestTextArea() {
		setText("this is a text area");
		setFont(new Font("Lucida Console", Font.BOLD, 36));
		setEditable(false);
	}
	
}
