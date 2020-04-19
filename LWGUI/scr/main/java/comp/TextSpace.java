package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class TextSpace extends Component {

	private Color background;
	private Color boarder;

	private String chars[];

	private int roundEdge;
	private int textX;
	private int textY;
	private int textHeight;

	private int cursorLocation;

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
	}

	private long timer = System.currentTimeMillis();
	private boolean show;

	private void drawCursor(Graphics g) {

		textX = x + 10;
		textY = y + 5;

		String length = protectedText.substring(0, cursorLocation);

		String split[] = length.split("\f");

		int lineWidth = 0;
		int lineHeight = 0;
		for (String word : split) {
			if (lineWidth + g.getFontMetrics().stringWidth(word) > getBounds().width - 10) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth(" ");
			}
			if (word.equals("\n")) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth("\n");
			}

			lineWidth += g.getFontMetrics().stringWidth(word);
		}
//		int width = g.getFontMetrics().stringWidth(length);
//		int height = width / (getBounds().width);
//		int rem = width % (getBounds().width);

		Point cursorPoint = new Point(lineWidth, lineHeight);

		if (timer + 500 < System.currentTimeMillis()) {
			show = !show;
			timer = System.currentTimeMillis();
		}
		if (show) {
			g.setColor(Color.BLACK);
			g.fillRect(textX + (cursorPoint.x), textY + cursorPoint.y, 2, g.getFontMetrics(font).getHeight());
		}
	}

	private void wrapString(Graphics g) {

		int lineWidth = 0;
		int lineHeight = 0;
		String words[] = protectedText.split("\f");

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (lineWidth + g.getFontMetrics().stringWidth(word) > getBounds().width - 10) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth(" ");
			}
			if (word.equals("\n")) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth("\n");
			}

			g.drawString(word, textX + lineWidth, textY + g.getFontMetrics().getAscent() + lineHeight);
			lineWidth += g.getFontMetrics().stringWidth(word);

		}

	}

	@Override
	public void paint(Graphics g) {

		// set the global height value for setting enter
		textHeight = g.getFontMetrics().getHeight();

		// draw the background
		g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), transparency));
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		// draw the boarder
		g.setColor(new Color(boarder.getRed(), boarder.getGreen(), boarder.getBlue(), transparency));
		g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

		// save the old clip bounds
		Rectangle oldClip = g.getClipBounds();
		// g.setClip(getBounds());
		g.setColor(foreground);
		// render the text to the window and render the cursor
		wrapString(g);
		drawCursor(g);
		g.setClip(oldClip);

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

	AffineTransform affinetransform = new AffineTransform();
	FontRenderContext frc = new FontRenderContext(affinetransform, true, true);

	@Override
	public void keyPressed(KeyEvent arg0) {

		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			if (sb.charAt(sb.length() - 1) == '\f') {
				System.out.println("this is the spot");
				cursorLocation -= 1;
			sb.setLength(sb.length() - 1);
			}
			cursorLocation--;
			sb.setLength(sb.length() - 1);
			break;
		case KeyEvent.VK_SHIFT:
			break;
		case KeyEvent.VK_WINDOWS:
			break;
		case KeyEvent.VK_CAPS_LOCK:
			break;
		case KeyEvent.VK_CONTROL:
			break;
		case KeyEvent.VK_ALT:
			break;
		case KeyEvent.VK_ENTER:
			sb.append("\f\n\f");
			cursorLocation += 3;
			break;
		case KeyEvent.VK_LEFT:
			if (cursorLocation > 0) {
				cursorLocation--;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (cursorLocation < chars.length) {
				cursorLocation++;
			}
			break;
		case KeyEvent.VK_SPACE:
			sb.append("\f");
			cursorLocation += 1;
		default:
			sb.append(arg0.getKeyChar());
			cursorLocation++;
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
