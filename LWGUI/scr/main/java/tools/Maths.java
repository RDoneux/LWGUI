package tools;

public class Maths {

	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException("Places must be greater than 0");
		}
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}


}
