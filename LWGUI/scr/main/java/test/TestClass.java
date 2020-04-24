package test;

import java.awt.Color;
import java.awt.Dimension;

import comp.Frame;
import comp.GUIComponent.alignment;
import comp.Panel;
import comp.ProgressBar;
import comp.TextArea;
import comp.TextSpace;
import constraints.PercentLayout;

public class TestClass {

	public static void main(String args[]) {

		PercentLayout layout = new PercentLayout(5, 5);

		Frame frame = new Frame();
		frame.setSize(new Dimension(600, 400));
		frame.setLayout(layout);

		Panel basePanel = new Panel();
		basePanel.setBackground(Color.GREEN);
		basePanel.setGridx(0);
		basePanel.setGridy(0);
		basePanel.setGridHeight(4);

		Panel centrePanel = new Panel();
		centrePanel.setBackground(Color.BLUE);
		centrePanel.setGridx(1);
		centrePanel.setGridy(0);
		centrePanel.setGridWidth(3);
		centrePanel.setGridHeight(4);
		centrePanel.setLayout(new PercentLayout(1, 1));

		Panel intPanel = new Panel();
		intPanel.setBackground(Color.MAGENTA);
		intPanel.setGridx(0);
		intPanel.setGridy(0);
		intPanel.setWeightX(0.9);
		intPanel.setWeightY(0.5);
		intPanel.setAlignmentX(alignment.CENTRE);
		intPanel.setAlignmentY(alignment.CENTRE);
		intPanel.setLayout(new PercentLayout(1, 4));

		TestLabel label = new TestLabel("This is a test label");
		label.setAlignmentX(alignment.CENTRE);
		label.setAlignmentY(alignment.CENTRE);

		TestButton button = new TestButton("This is a test button");
		button.setGridy(1);
		button.setAlignmentX(alignment.CENTRE);
		button.setAlignmentY(alignment.CENTRE);

		ProgressBar bar = new ProgressBar();
		bar.setGridy(2);
		bar.setWeightX(0.8);
		bar.setWeightY(0.4);
		bar.updateValue(80, 100);
		bar.displayProgress(true);
		bar.gradientColour(new Color(75, 0, 130), new Color(251, 206, 177));
		bar.setAlignmentX(alignment.CENTRE);
		bar.setAlignmentY(alignment.CENTRE);

		intPanel.add(label);
		intPanel.add(bar);
		intPanel.add(button);
		centrePanel.add(intPanel);

		Panel rightPanel = new Panel();
		rightPanel.setBackground(Color.RED);
		rightPanel.setGridx(4);
		rightPanel.setGridy(0);
		rightPanel.setGridHeight(4);

		Panel bottomPanel = new Panel();
		bottomPanel.setBackground(Color.YELLOW);
		bottomPanel.setGridx(0);
		bottomPanel.setGridy(4);
		bottomPanel.setGridWidth(5);
		bottomPanel.setWeightX(0.5);
		bottomPanel.setLayout(new PercentLayout(1, 1));

		TextArea textArea = new TextArea();

		bottomPanel.add(textArea);
		
		Panel bottomRightPanel = new Panel();
		bottomRightPanel.setBackground(Color.PINK);
		bottomRightPanel.setGridx(0);
		bottomRightPanel.setGridy(4);
		bottomRightPanel.setGridWidth(5);
		bottomRightPanel.setAlignmentX(alignment.EAST);
		bottomRightPanel.setWeightX(0.5);
		bottomRightPanel.setLayout(new PercentLayout(1, 1));

		frame.add(basePanel);
		frame.add(centrePanel);
		frame.add(rightPanel);
		frame.add(bottomPanel);
		frame.add(bottomRightPanel);

		for (int i = 0; i <= 53; i++) {
			bar.updateValue(i, 53);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
