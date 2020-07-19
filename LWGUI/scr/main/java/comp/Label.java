package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import tools.Utils;

/**
 * A class that displays a given string within a parent {@link Container}. The
 * Class respects the parent {@link Container}'s {@link Tile} boundary size and
 * will display a shortened version of the given string if the container size
 * reduces below the desired width and height. The width and height are not
 * changed when User alters size of window however
 * 
 * Mouse and Key Events are passed to this parent class. To utilise them, extend
 * this class and @Override the appropriate method
 * 
 * @author Robert Doneux
 * @version 0.1
 */
public class Label extends Component {

	public Label(String text) {
		this.text = text;
		this.protectedText = text;
		foreground = Color.BLACK;
		name = "Label";

		sizeEditable = false;

	}

	public Label() {
		foreground = Color.BLACK;
		name = "Label";

		sizeEditable = false;
	}

	@Override
	public void revise() {
		// the update method. The label requires no updates currently as the width and
		// height variables are set within the paint method
	}

	/**
	 * Paints the desired string to the screen. The width and height values are also
	 * set here
	 */
	@Override
	public void paint(Graphics g) {

		g.setColor(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(), transparency));
		g.setFont(font);

		if (scaleFont && layoutBounds != null) {
			font = Utils.scaleFont(protectedText, layoutBounds, g, maxFont, minFont);
		}

		if (text != null && text.length() > 0) {
			width = g.getFontMetrics(font).stringWidth(text);
			height = g.getFontMetrics(font).getHeight();

		} else {
			width = 1;
			height = 1;
		}

		int assent = g.getFontMetrics(font).getAscent();

		// save the previous clip bounds so that it can be reset after this components
		// clip bounds are set
		Rectangle clipArea = g.getClipBounds();

		// clip the paint call to the size of the parent container
		if (topLevelParent == null) {
			g.setClip(parent.getBounds());
		} else {
			g.setClip(topLevelParent.getBounds());
		}

		if (show && text != null) {
			g.drawString(text, x, y + assent);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}

}
