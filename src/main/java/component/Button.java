package component;

import tools.Maths;
import tools.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

/**
 * 
 * Standard button class that draws an interactive {@link Component} to the
 * screen. To program the button pressed method, override the 'action()' method.
 * 
 * @author Robert Doneux
 * @version 0.1
 *
 */

public class Button extends Component {

	private Color background;
	private Color border;

	private final int roundEdge;
	private int foregroundTransparency; // the transparency of the text

	private Image image;

	public Button(String text) {

		this.text = text;
		this.protectedText = text;
		this.foreground = Color.BLACK;
		this.setName("Button");

		this.setSizeEditable(false);

		foregroundTransparency = 255;
		background = Color.LIGHT_GRAY;
		border = Color.BLACK;
		roundEdge = 5;

	}

	public Button() {
		this.foreground = Color.BLACK;
		this.setName("Button");
		this.setSizeEditable(false);

		text = " ";
		protectedText = text;
		foregroundTransparency = 255;
		background = Color.LIGHT_GRAY;
		border = Color.BLACK;
		roundEdge = 5;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * use this method to program the button press action.
	 */
	public void action() {

	}

	@Override
	public void revise() {

	}

	@Override
	public void paint(Graphics g) {

		g.setFont(font);
		if (scaleFont && width > 0 && height > 0) {
			Utils.scaleFont(protectedText, getBounds(), g, maxFont, minFont); // scale the font to fit with the size of
																				// the button
		}
		if (width == 0 && height == 0) {
			width = g.getFontMetrics().stringWidth(text) + 50;
			height = g.getFontMetrics().getHeight() + 10;
		}
		// save the previous clip bounds so that it can be reset after this components
		// clip bounds are set
		Rectangle clipArea = g.getClipBounds();

		// clip the paint call to the size of the parent container
		if (topLevelParent == null) {
			g.setClip(parent.getBounds());
		} else {
			g.setClip(topLevelParent.getBounds());
		}

		if (show) {
			// if an image has been set, use that as the button. If not, use the defualt
			// layout.

			// draw the shadow.
			g.setColor(new Color(Color.DARK_GRAY.getRed(), Color.DARK_GRAY.getGreen(), Color.DARK_GRAY.getBlue(),
					transparency));
			g.fillRoundRect(x + 2, y + 2, width, height, roundEdge + 2, roundEdge + 2);

			// draw the background
			g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), transparency));
			g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

			// draw the boarder
			g.setColor(new Color(border.getRed(), border.getGreen(), border.getBlue(), transparency));
			g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

			if (image != null) {
				// if an image has been set as the background, draw it under the text
				g.drawImage(image, x, y, width, height, null);
			}

			// draw the string in the centre of the button
			g.setColor(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(),
					foregroundTransparency));
			Maths.drawCentredString(g, text, getAnimationBounds());

		}
		// reset the clip area
		g.setClip(clipArea);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// simulate button press by slightly moving button. The layout manager resets
		// the location. Also call user action method. This should be overridden in
		// parent class
		if (getBounds().contains(arg0.getPoint())) {
			// width = width - 3;
			// height = height - 3;
			animationX = 2;
			animationY = 2;
			action();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		animationX = 0;
		animationY = 0;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public int getForegroundTransparency() {
		return foregroundTransparency;
	}

	public void setForegroundTransparency(int foregroundTransparency) {
		this.foregroundTransparency = foregroundTransparency;
	}

	public Color getBorder() {
		return border;
	}

	public void setBorder(Color boarder) {
		this.border = boarder;
	}

}
