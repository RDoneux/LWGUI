package tools;

import java.util.Random;

public class IDGenerator {

	private static Random r = new Random();

	public static int generateID() {
		return r.nextInt(999999999 - -999999999) + 999999999;
	}

}
