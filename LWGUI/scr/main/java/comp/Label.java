package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

public class Label extends Component {

	public Label(String text) {
		this.text = text;
		this.protectedText = text;
		foreground = Color.BLACK;
		name = "Label";

		sizeEditable = false;

	}

	@Override
	public void revise() {

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(foreground);
		g.setFont(font);
		fullWidth = g.getFontMetrics().stringWidth(protectedText);
		width = g.getFontMetrics().stringWidth(text);
		height = g.getFontMetrics().getHeight();
		int assent = g.getFontMetrics().getAscent();

		// clip the paint call to the size of the parent container
		Rectangle clipArea = g.getClipBounds();

		if (topLevelParent == null) {
			g.setClip(parent.getBounds());
		} else {
			g.setClip(topLevelParent.getBounds());
		}

		g.drawString(text, x, y + assent);

		// set the clip space back to default
		g.setClip(clipArea);

	}

	@Override
	public void minimise(Rectangle parentSpace) {
		if (fullWidth > parentSpace.width && !minimised) {
			text = protectedText.substring(0, 3) + "[...]";
			minimised = true;
		}
		if (fullWidth < parentSpace.width && minimised) {
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
