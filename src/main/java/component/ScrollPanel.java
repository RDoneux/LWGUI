package component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ScrollPanel extends Component {

	private Container child;
	
	private Color background;

	public ScrollPanel(Container child) {
		this.child = child;
		setName("Scroll Panel");
		sizeEditable = true;
		background = Color.blue;
	}

	@Override
	public void revise() {

		child.setX(x);
		child.setY(y);
		child.setWidth(width);
		child.setHeight(height);

		child.revise();

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(background);
		g.fillRect(x, y, width, height);
		
		child.paint(g);


	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		child.mouseClicked(arg0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

		child.mouseEntered(arg0);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		child.mouseExited(arg0);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		child.mousePressed(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		child.mouseReleased(arg0);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		child.mouseDragged(arg0);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		child.mouseMoved(arg0);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		child.keyPressed(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		child.keyReleased(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		child.keyTyped(arg0);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		child.mouseWheelMoved(arg0);
	}

}
