package comp;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class TextSpace extends Component {

	private Color background;
	private Color boarder;

	private int roundEdge;
	private int textX;
	private int textY;

	private StringBuilder sb;

	public TextSpace() {
		background = new Color(220, 220, 220);
		boarder = Color.BLACK;
		foreground = Color.BLACK;
		sizeEditable = true;
		roundEdge = 5;
		sb = new StringBuilder();
		setText("");
	}

	@Override
	public void revise() {
		textX = x + 10;
		textY = y + 5;
	}

	private long timer = System.currentTimeMillis();
	private boolean show;

	private void drawCursor(Graphics g) {

		if (timer + 500 < System.currentTimeMillis()) {
			show = !show;
			timer = System.currentTimeMillis();
		}
		if (show) {
			g.setColor(Color.BLACK);
			g.fillRect(textX, textY, 2, g.getFontMetrics(font).getHeight());
		}
	}

	@Override
	public void paint(Graphics g) {

		// draw the background
		g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), transparency));
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		// draw the boarder
		g.setColor(new Color(boarder.getRed(), boarder.getGreen(), boarder.getBlue(), transparency));
		g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

		drawCursor(g);
		g.setColor(foreground);
		g.drawString(protectedText, textX, textY + g.getFontMetrics().getAscent());

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

		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			sb.setLength(sb.length() - 1);
			break;
		default:
			sb.append(arg0.getKeyChar());
			break;
		}
		text = sb.toString();
		protectedText = text;
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
