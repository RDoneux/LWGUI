package tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
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
}
