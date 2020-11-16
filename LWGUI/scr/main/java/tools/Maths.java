package tools;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Maths {

	/**
	 * 
	 * @param value  The value to round
	 * @param places The number of places to round to
	 * @return The rounded value
	 */
	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException("Places must be greater than 0");
		}
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	/**
	 * Draw a String centred in the middle of a Rectangle.
	 *
	 * @param g    The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to centre the text in.
	 */
	public static void drawCentredString(Graphics g, String text, Rectangle rect) {
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java
		// 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(g.getFont());
		g.drawString(text, x, y);
	}

	/**
	 * returns a scaled dimension calculated from the original image size and the
	 * size of the boundary it is being placed into
	 * 
	 * @param imgSize
	 * @param boundary
	 * @return new scaled boundary
	 */
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

		int original_width = imgSize.width;
		int original_height = imgSize.height;
		int bound_width = boundary.width;
		int bound_height = boundary.height;
		int new_width = original_width;
		int new_height = original_height;

		// first check if we need to scale width
		if (original_width > bound_width) {
			// scale width to fit
			new_width = bound_width;
			// scale height to maintain aspect ratio
			new_height = (new_width * original_height) / original_width;
		}

		// then check if we need to scale even with the new height
		if (new_height > bound_height) {
			// scale height to fit instead
			new_height = bound_height;
			// scale width to maintain aspect ratio
			new_width = (new_height * original_width) / original_height;
		}
		return new Dimension(new_width, new_height);
	}

	/**
	 * maps valueCoord1 between a minimum and maximum value and an end minimum and
	 * maximum value;
	 */
	final static double EPSILON = 1e-12;

	public static double map(double valueCoord1, double startCoord1, double endCoord1, double startCoord2,
			double endCoord2) {

		if (Math.abs(endCoord1 - startCoord1) < EPSILON) {
			throw new ArithmeticException("/ 0");
		}

		double offset = startCoord2;
		double ratio = (endCoord2 - startCoord2) / (endCoord1 - startCoord1);
		return ratio * (valueCoord1 - startCoord1) + offset;
	}

	public static int scaleFont(Graphics g, String title, Rectangle bounds) {
		Rectangle2D rect = null;

		int fontSize = 30; // initial value
		do {
			fontSize--;
			Font font = new Font("Arial", Font.PLAIN, fontSize);
			rect = getStringBoundsRectangle2D(g, title, font);
		} while (rect.getWidth() >= bounds.width || rect.getHeight() >= bounds.height);

		System.out.println(fontSize);
		return fontSize;
	}

	private static Rectangle2D getStringBoundsRectangle2D(Graphics g, String title, Font font) {
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D rect = fm.getStringBounds(title, g);
		return rect;
	}

}
