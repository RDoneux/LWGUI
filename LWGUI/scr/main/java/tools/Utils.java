package tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {

	public static BufferedImage loadImageFromResources(String fileName) {
		try {
			return ImageIO.read(Utils.class.getResourceAsStream("/assets/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * DOESN'T WORK!
	 * 
	 * scales a font to fit vertically within a rectangle. Requires some work so
	 * horizontal works as welL!
	 * 
	 * @param text - the input text
	 * @param rect - the bounds to scale to
	 * @param g    - the graphics object that will draw the string
	 * @return - the correct font size
	 */
	public static Font scaleFont(String text, Rectangle rect, Graphics g) {

		// if the text already fits the rectangle in width or height, the font has been
		// scaled correctly before so return it.
		if (g.getFontMetrics(g.getFont()).stringWidth(text) == rect.width
				|| g.getFontMetrics(g.getFont()).getAscent() == rect.height) {
			return g.getFont();
		}

		int fontSize = 0;
		// if the text fits the width of the rectangle, increase the height to the size
		// of the container
		if (g.getFontMetrics(g.getFont()).stringWidth(text) < rect.width) {
			fontSize = rect.height;
		}
		// if the width is larger than the rect width, reduce the font size
		// until it fits
		Font font = new Font(g.getFont().getFamily(), g.getFont().getStyle(), fontSize);
		while (g.getFontMetrics(font).stringWidth(text) >= rect.width) {
			fontSize--;
			font = new Font(g.getFont().getFamily(), g.getFont().getStyle(), fontSize);
		}
		return new Font(g.getFont().getFamily(), g.getFont().getStyle(), fontSize);
	}

	/**
	 * 
	 * rounds the edges of the BufferedImage argument
	 * 
	 * @param image        - image to be rounded
	 * @param cornerRadius - the radius of the rounded corners
	 * @return - the rounded image
	 */
	public static BufferedImage makeRoundedCorner(Image image, int cornerRadius) {

		int w = image.getWidth(null);
		int h = image.getHeight(null);

		BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = output.createGraphics();

		// This is what we want, but it only does hard-clipping, i.e. aliasing
		// g2.setClip(new RoundRectangle2D ...)
		// so instead fake soft-clipping by first drawing the desired clip shape
		// in fully opaque white with antialiasing enabled...

		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.fillRoundRect(0, 0, w, h, cornerRadius, cornerRadius);
		// g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

		// ... then compositing the image on top,
		// using the white shape from above as alpha source
		g2.setComposite(AlphaComposite.SrcIn);
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		return output;

	}

	/**
	 * 
	 * rounds the edges of the BufferedImage argument based upon the width and
	 * height of the given values
	 * 
	 * @param image        - image to be rounded
	 * @param cornerRadius - the radius of the rounded corners
	 * @return - the rounded image
	 */
	public static BufferedImage makeRoundedCorner(Image image, int width, int height, int cornerRadius) {

		int w = width;
		int h = height;

		BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = output.createGraphics();

		// This is what we want, but it only does hard-clipping, i.e. aliasing
		// g2.setClip(new RoundRectangle2D ...)
		// so instead fake soft-clipping by first drawing the desired clip shape
		// in fully opaque white with antialiasing enabled...

		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.fillRoundRect(0, 0, w, h, cornerRadius, cornerRadius);
		// g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

		// ... then compositing the image on top,
		// using the white shape from above as alpha source
		g2.setComposite(AlphaComposite.SrcIn);
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		return output;

	}
}
