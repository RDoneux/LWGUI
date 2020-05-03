package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;

import constraints.Layout;
import tools.Maths;

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
	private Color boarder;

	private int roundEdge;

	private Image image;

	public Button(String text) {

		this.text = text;
		this.protectedText = text;
		this.foreground = Color.BLACK;
		this.setName("Button");

		this.setSizeEditable(false);

		background = Color.LIGHT_GRAY;
		boarder = Color.BLACK;
		roundEdge = 5;

	}

	public Button() {
		this.foreground = Color.BLACK;
		this.setName("Button");

		this.setSizeEditable(false);

		background = Color.LIGHT_GRAY;
		boarder = Color.BLACK;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {

		g.setFont(font);
		width.set(g.getFontMetrics().stringWidth(text) + 50);
		height.set(g.getFontMetrics().getHeight() + 10);

		// save the previous clip bounds so that it can be reset after this components
		// clip bounds are set
		Rectangle clipArea = g.getClipBounds();

		// clip the paint call to the size of the parent container
		if (topLevelParent == null) {
			g.setClip(parent.getAnimationBounds());
		} else {
			g.setClip(topLevelParent.getBounds());
		}

		// if an image has been set, use that as the button. If not, use the defualt
		// layout.
		if (image == null) {
			// draw the shadow.
			g.setColor(new Color(Color.DARK_GRAY.getRed(), Color.DARK_GRAY.getGreen(), Color.DARK_GRAY.getBlue(),
					transparency));
			g.fillRoundRect(x.get() + 3, y.get() + 3, width.get(), height.get(), roundEdge + 3, roundEdge + 3);

			// draw the background
			g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), transparency));
			g.fillRoundRect(x.get(), y.get(), width.get(), height.get(), roundEdge, roundEdge);

			// draw the boarder
			g.setColor(new Color(boarder.getRed(), boarder.getGreen(), boarder.getBlue(), transparency));
			g.drawRoundRect(x.get(), y.get(), width.get(), height.get(), roundEdge, roundEdge);
		} else {
			// if an image has been set as the background, draw it under the text
			g.drawImage(image, x.get(), y.get(), width.get(), height.get(), null);
		}

		// draw the string in the centre of the button
		g.setColor(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(), transparency));
		Maths.drawCentredString(g, text, getAnimationBounds());

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
			width.set(width.get()- 3);
			height.set(height.get()- 3);
			action();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

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

}
