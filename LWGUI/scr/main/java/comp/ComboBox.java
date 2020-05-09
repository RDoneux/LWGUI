package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import tools.Utils;

public class ComboBox extends Component {

	private int roundedEdgeValue;
	private BufferedImage downArrow;

	public ComboBox() {

		setName("ComboBox");
		roundedEdgeValue = 6;
		downArrow = Utils.loadImageFromResources("DownArrow.png");

		width = 20;
		height = 20;

	}

	@Override
	public void revise() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(x, y, width, height, roundedEdgeValue, roundedEdgeValue);
		g.drawImage(downArrow, x + 4, y + 4, width - 8, height - 8, null);

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
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

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
