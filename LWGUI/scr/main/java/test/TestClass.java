package test;

import java.awt.Color;
import java.awt.Dimension;

import animation.FlyIn;
import comp.Frame;
import comp.GUIComponent.alignment;
import comp.Panel;
import comp.ProgressBar;
import comp.ScrollPanel;
import comp.TextArea;
import constraints.PercentLayout;

public class TestClass {

	public static void testSetup() {

		PercentLayout layout = new PercentLayout(5, 5);

		Frame frame = new Frame();
		frame.setSize(new Dimension(600, 400));
		frame.setLayout(layout);

		Panel basePanel = new Panel();
		basePanel.setBackground(Color.GREEN);
		basePanel.setGridx(0);
		basePanel.setGridy(0);
		basePanel.setGridHeight(4);
		basePanel.setLayout(new PercentLayout(1, 1));

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

		TestPanel rightPanel = new TestPanel();
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

		TextArea testArea = new TextArea();
		bottomPanel.add(testArea);

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

	public static void writingSetup() {
		Frame frame = new Frame();
		frame.setSize(new Dimension(800, 600));
		frame.setLayout(new PercentLayout(1, 1));

		Panel basePanel = new Panel();
		basePanel.setLayout(new PercentLayout(1, 1));
		basePanel.debugLayout();

		TestTextArea area = new TestTextArea();
		// area.setWeightX(0.8);
		// area.setWeightY(0.5);
		area.setAlignmentX(alignment.CENTRE);
		area.setAlignmentY(alignment.CENTRE);

		basePanel.add(area);

		frame.add(basePanel);
	}

	public static void gameFrameSetup() {
		Frame frame = new Frame();
		frame.setSize(new Dimension(800, 600));
		frame.setLayout(new PercentLayout(1, 1));
		//frame.debugLayout();

		Panel panel = new Panel();
		panel.setLayout(new PercentLayout(1,5));
		
		Panel gameDisplayPanel = new Panel();
		gameDisplayPanel.setGridHeight(4);

		TestPanel writingPanel = new TestPanel();
		writingPanel.setGridy(4);
		writingPanel.setGridx(0);
		writingPanel.setLayout(new PercentLayout(1, 1));
		writingPanel.setBackground(Color.PINK);

		//writingPanel.add(new TestTextArea());

		frame.add(panel);
		
		panel.add(gameDisplayPanel);
		panel.add(writingPanel);
	}

	public static void main(String args[]) {
		testSetup();
	}

}
