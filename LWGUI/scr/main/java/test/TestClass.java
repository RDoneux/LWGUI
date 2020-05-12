package test;

import java.awt.Color;
import java.awt.Dimension;

import comp.Button;
import comp.ComboBox;
import comp.Frame;
import comp.GUIComponent.alignment;
import comp.Panel;
import comp.ProgressBar;
import comp.TextArea;
import constraints.PercentLayout;

public class TestClass {

	public static void testSetup() {

		PercentLayout layout = new PercentLayout(5, 5);

		Frame frame = new Frame();
		frame.setSize(new Dimension(600, 400));
		frame.setLayout(new PercentLayout(1, 1));

		TestPanel veryBottomPanel = new TestPanel();
		veryBottomPanel.setLayout(layout);
		veryBottomPanel.setBackground(Color.MAGENTA);

		frame.add(veryBottomPanel);

		TestPanel basePanel = new TestPanel();
		basePanel.setBackground(Color.GREEN);
		basePanel.setGridx(0);
		basePanel.setGridy(0);
		basePanel.setGridHeight(4);
		basePanel.setLayout(new PercentLayout(1, 1));

		TestPanel centrePanel = new TestPanel();
		centrePanel.setBackground(Color.BLUE);
		centrePanel.setGridx(1);
		centrePanel.setGridy(0);
		centrePanel.setGridWidth(3);
		centrePanel.setGridHeight(4);
		centrePanel.setLayout(new PercentLayout(1, 1));

		TestPanel intPanel = new TestPanel();
		intPanel.setBackground(Color.darkGray);
		intPanel.setGridx(0);
		intPanel.setGridy(0);
		intPanel.setWeightX(1);
		intPanel.setWeightY(0.5);
		intPanel.setAlignmentX(alignment.CENTRE);
		intPanel.setAlignmentY(alignment.CENTRE);
		intPanel.setLayout(new PercentLayout(1, 4));

		TestLabel label = new TestLabel("This is a test label");
		label.setAlignmentX(alignment.CENTRE);
		label.setAlignmentY(alignment.CENTRE);

		Button button = new Button("This is a test button");
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

		TestTextArea testArea = new TestTextArea();
		bottomPanel.add(testArea);

		TestPanel bottomRightPanel = new TestPanel();
		bottomRightPanel.setBackground(Color.PINK);
		bottomRightPanel.setGridx(0);
		bottomRightPanel.setGridy(4);
		bottomRightPanel.setGridWidth(5);
		bottomRightPanel.setAlignmentX(alignment.EAST);
		bottomRightPanel.setWeightX(0.5);
		bottomRightPanel.setLayout(new PercentLayout(1, 1));

		veryBottomPanel.add(basePanel);
		veryBottomPanel.add(centrePanel);
		veryBottomPanel.add(rightPanel);
		veryBottomPanel.add(bottomPanel);
		veryBottomPanel.add(bottomRightPanel);

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
		frame.setTargetFPS(120);
		frame.setTargetUPS(60);
		frame.addCustomLoopTask(new TestLoopTask());
		// frame.debugLayout();

		Panel panel = new Panel();
		panel.setLayout(new PercentLayout(4, 5));
		panel.debugLayout();

		Panel gameDisplayPanel = new Panel();
		gameDisplayPanel.setGridHeight(5);
		gameDisplayPanel.setGridWidth(4);
		gameDisplayPanel.setLayout(new PercentLayout(1, 1));

		gameDisplayPanel.add(new TestGame(frame));

		Panel writingPanel = new Panel();
		writingPanel.setGridy(4);
		writingPanel.setGridWidth(4);
		writingPanel.setGridHeight(1);
		writingPanel.setBackground(Color.PINK);
		writingPanel.setTransparency(100);
		writingPanel.setLayout(new PercentLayout(1, 1));

		SecondTestPanel menu = new SecondTestPanel();
		menu.setGridHeight(5);
		menu.setBackground(Color.pink);
		menu.setLayout(new PercentLayout(1, 5));

		TestButton button0 = new TestButton("Start Game");
		button0.setAlignmentX(alignment.CENTRE);
		button0.setAlignmentY(alignment.CENTRE);
		button0.setSize(new Dimension(180, 40));
		TestButton button1 = new TestButton("Settings");
		button1.setGridy(1);
		button1.setAlignmentX(alignment.CENTRE);
		button1.setAlignmentY(alignment.CENTRE);
		button1.setSize(new Dimension(180, 40));
		TestButton button2 = new TestButton("Exit");
		button2.setGridy(2);
		button2.setAlignmentX(alignment.CENTRE);
		button2.setAlignmentY(alignment.CENTRE);
		button2.setSize(new Dimension(180, 40));
		TestButton button3 = new TestButton("Do Something Else");
		button3.setGridy(3);
		button3.setAlignmentX(alignment.CENTRE);
		button3.setAlignmentY(alignment.CENTRE);
		button3.setSize(new Dimension(180, 40));
		TestButton button4 = new TestButton("Quit");
		button4.setGridy(4);
		button4.setAlignmentX(alignment.CENTRE);
		button4.setAlignmentY(alignment.CENTRE);
		button4.setSize(new Dimension(180, 40));

		menu.add(button0);
		menu.add(button1);
		menu.add(button2);
		menu.add(button3);
		menu.add(button4);

		writingPanel.add(new TestTextArea());

		ComboBox combo = new ComboBox();
		combo.setGridx(3);
		combo.setAlignmentX(alignment.CENTRE);
		combo.setAlignmentY(alignment.CENTRE);
		
		panel.add(gameDisplayPanel);
		panel.add(writingPanel);
		panel.add(menu);
		panel.add(combo);

		frame.add(panel);

	}

}
