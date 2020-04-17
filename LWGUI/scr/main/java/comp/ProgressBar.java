package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ProgressBar extends Component {

	private Color background;
	private Color boarder;
	private int roundEdge;

	private int value;
	private int max;
	private int min;

	public ProgressBar() {
		background = Color.LIGHT_GRAY;
		foreground = new Color(30, 100, 255);
		boarder = Color.BLACK;
		roundEdge = 5;
		name = "Progress Bar";
		sizeEditable = true;
	}

	@Override
	public void revise() {

	}

	@Override
	public void paint(Graphics g) {

		// draw background
		g.setColor(background);
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		// calculate value
		int drawValue = value * width / max;

		// draw foreground
		g.setColor(foreground);
		g.fillRoundRect(x, y, drawValue, height, roundEdge, roundEdge);

		// draw boarder
		g.setColor(boarder);
		g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

	}

	public void updateValue(int value, int max) {
		this.value = value;
		this.max = max;
	}

	@Override
	public void minimise(Rectangle parentSpace) {
		// TODO Auto-generated method stub

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
