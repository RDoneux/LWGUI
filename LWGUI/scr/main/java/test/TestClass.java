package test;

import java.awt.Color;
import java.awt.Dimension;

import comp.Frame;
import comp.Panel;
import constraints.PercentLayout;

public class TestClass {

	public static void main(String args[]) {

		Frame frame = new Frame();
		frame.setSize(new Dimension(400, 400));
		frame.setLayout(new PercentLayout(1, 1));

		Panel basePanel = new Panel();
		basePanel.setBackground(Color.GREEN);
		basePanel.setGridx(0);
		basePanel.setGridy(0);
		basePanel.setWeightX(0.1);
		basePanel.setWeightY(0.1);
		
		frame.add(basePanel);

	}

}
