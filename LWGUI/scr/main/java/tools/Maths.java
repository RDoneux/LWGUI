package tools;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

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
	public static void drawCentredString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java
		// 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}

}
