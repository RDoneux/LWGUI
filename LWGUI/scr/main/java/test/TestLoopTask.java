package test;

import tools.CustomLoopTask;

public class TestLoopTask extends CustomLoopTask {

	public TestLoopTask() {
		setName("Packets");
		setTargetCallsPerSecond(1.0);
	}

	@Override
	public void excecute() {

		System.out.println("custom loop task excecuted");

	}

}
