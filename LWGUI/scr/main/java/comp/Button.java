package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import tools.Maths;

public class Button extends Component {

	private Color background;
	private Color boarder;

	private int roundEdge;

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

	@Override
	public void revise() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {

		g.setFont(font);
		width = g.getFontMetrics().stringWidth(text) + 20;
		height = g.getFontMetrics().getHeight() + 10;

		// save the previous clip bounds so that it can be reset after this components
		// clip bounds are set
		Rectangle clipArea = g.getClipBounds();

		// clip the paint call to the size of the parent container
		if (topLevelParent == null) {
			g.setClip(parent.getBounds());
		} else {
			g.setClip(topLevelParent.getBounds());
		}

		// draw the background
		g.setColor(background);
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		// draw the boarder
		g.setColor(boarder);
		g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

		// draw the string in the centre of the button
		g.setColor(foreground);
		Maths.drawCentredString(g, text, getBounds(), g.getFont());

		// reset the clip area
		g.setClip(clipArea);

	}

	/**
	 * Receives a boundary from the Parent {@link Container}'s {@link Layout}. The
	 * Class will minimise the string size to the first three chars followed by
	 * "[...]" if the width of the string is larger than this given boundary
	 * 
	 * @param Rectangle parentSpace
	 */
	@Override
	public void minimise(Rectangle parentSpace) {

		FontRenderContext frc = new FontRenderContext(null, false, false);
		TextLayout layout = new TextLayout(protectedText, font, frc);

		if (layout.getBounds().getWidth() > parentSpace.width && !minimised) {
			text = protectedText.substring(0, 3) + "[...]";
			minimised = true;
		}
		if (layout.getBounds().getWidth() < parentSpace.width && minimised) {
			text = protectedText;
			minimised = false;
		}
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
		// the location
		width -= 3;
		height -= 3;
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
