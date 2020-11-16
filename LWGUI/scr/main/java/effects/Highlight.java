package effects;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Highlight extends Effect {

	private Color colour;

	public Highlight(Color colour) {
		this.colour = colour;
		type = effectType.HIGHLIGHT;
	}

	public Highlight() {
		colour = Color.BLUE;
	}

	@Override
	public void paint(Graphics g) {

		if (parent != null) {
			x = parent.getVisualX() - 10;
			y = parent.getVisualY() - 10;
			width = parent.getVisualWidth() + 20;
			height = parent.getVisualHeight() + 20;
		}

		blurBorder(g, 15);
	}

	private void blurBorder(Graphics input, double border) {
		int w = width;
		int h = height;

		Graphics2D g = (Graphics2D) input;
		// g.drawImage(input, 0, 0, null);

		// g.setComposite(AlphaComposite.DstOut);
		Color c0 = new Color(0, 0, 0, 0);
		Color c1 = new Color(colour.getRed(), colour.getGreen(), colour.getBlue(), 255);

		double cy = border;
		double cx = border;

		// Left
		g.setPaint(new GradientPaint(new Point2D.Double(x, cy + y), c0, new Point2D.Double(cx + x, cy + y), c1));
		g.fill(new Rectangle2D.Double(x, cy + y, cx, h - cy - cy));

		// Right
		g.setPaint(
				new GradientPaint(new Point2D.Double(x + w - cx, y + cy), c1, new Point2D.Double(x + w, y + cy), c0));
		g.fill(new Rectangle2D.Double(x + w - cx, y + cy, cx, h - cy - cy));

		// Top
		g.setPaint(new GradientPaint(new Point2D.Double(x + cx, y), c0, new Point2D.Double(x + cx, y + cy), c1));
		g.fill(new Rectangle2D.Double(x + cx, y, w - cx - cx, cy));

		// Bottom
		g.setPaint(
				new GradientPaint(new Point2D.Double(x + cx, y + h - cy), c1, new Point2D.Double(x + cx, y + h), c0));
		g.fill(new Rectangle2D.Double(x + cx, y + h - cy, w - cx - cx, cy));

		// Top Left
		g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(x, y, cx + cx, cy + cy), new float[] { 0, 1 },
				new Color[] { c1, c0 }, CycleMethod.NO_CYCLE));
		g.fill(new Rectangle2D.Double(x, y, cx, cy));

		// Top Right
		g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(x + w - cx - cx, y, cx + cx, cy + cy),
				new float[] { 0, 1 }, new Color[] { c1, c0 }, CycleMethod.NO_CYCLE));
		g.fill(new Rectangle2D.Double(x + w - cx, y, cx, cy));

		// Bottom Left
		g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(x, y + h - cy - cy, cx + cx, cy + cy),
				new float[] { 0, 1 }, new Color[] { c1, c0 }, CycleMethod.NO_CYCLE));
		g.fill(new Rectangle2D.Double(x, y + h - cy, cx, cy));

		// Bottom Right
		g.setPaint(new RadialGradientPaint(new Rectangle2D.Double(x + w - cx - cx, y + h - cy - cy, cx + cx, cy + cy),
				new float[] { 0, 1 }, new Color[] { c1, c0 }, CycleMethod.NO_CYCLE));
		g.fill(new Rectangle2D.Double(x + w - cx, y + h - cy, cx, cy));

	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public Color getColour() {
		return colour;
	}

}
