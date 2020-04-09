package test;

import java.awt.Color;
import java.awt.Dimension;

import comp.Frame;
import comp.Panel;
import constraints.WindowConstraint;
import constraints.WindowLayout;

public class TestClass {

	public static void main(String args[]) {

		Frame frame = new Frame();
		frame.setSize(new Dimension(400, 200));
		frame.setLayout(new WindowLayout());

		Panel basePanel = new Panel();
		basePanel.setBackground(Color.GREEN);

		WindowConstraint wc = new WindowConstraint();

		wc.setIndexX(4);
		wc.setIndexY(7);
		frame.add(basePanel, wc);

	}

}
