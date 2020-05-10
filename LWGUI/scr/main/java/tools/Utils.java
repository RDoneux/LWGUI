package tools;

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
