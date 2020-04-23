package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * 
 * VERY Light weight text editor. Component is size editable and will fill to
 * fit a given space as dictated by a layout manager. Currently has
 * functionality to wrap text when it exceeds the bounds width. Text that
 * extends out of the height axis won't be rendered however will be processed.
 * User can create new lines using enter and can navigate through text using
 * left and right arrows. Currently up and down arrows do not function. User can
 * also insert text at cursor location
 * 
 * @author Robert Doneux
 * @version 0.1
 *
 */

public class TextSpace extends Component {

	private Color background;
	private Color boarder;

	private int roundEdge;
	private int textX;
	private int textY;
	private int textHeight;
	private int cursorLocation;

	private StringBuilder sb;

	private boolean focused;

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
		sb = new StringBuilder(protectedText);
	}

	/**
	 * Draw the cursor in the correct x and y location, given the current
	 * cursorLocation.
	 */

	private long timer = System.currentTimeMillis();
	private boolean show;

	private void drawCursor(Graphics g) {

		textX = x + 10;
		textY = y + 5;

		String length = protectedText.substring(0, cursorLocation);

		int lineWidth = 0;
		int lineHeight = 0;
		String split[] = length.split("\f");

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

		Point cursorPoint = new Point(lineWidth, lineHeight);

		if (timer + 500 < System.currentTimeMillis()) {
			show = !show;
			timer = System.currentTimeMillis();
		}
		if (show && focused) {
			g.setColor(Color.BLACK);
			g.fillRect(textX + (cursorPoint.x), textY + cursorPoint.y, 2, g.getFontMetrics(font).getHeight());
		}
	}

	/**
	 * Draw the current protected text wrapped within the current bounds
	 */
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
		g.setClip(getBounds());
		g.setColor(foreground);
		// render the text to the window and render the cursor
		wrapString(g);
		drawCursor(g);
		g.setClip(oldClip);

	}

	/**
	 * If there are multiple text panels added to a single frame, key presses will
	 * be registered by them all and the user will not be able to type into
	 * individual text boxes. The focus variable is triggered if the user presses
	 * the mouse within the window. If the mouse click is outside of the bounds of
	 * this component, the focus variable is set to false, if it is within the
	 * bounds, the variable is set to true. The key inputs below are only registered
	 * if the focus variable is true
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (getBounds().contains(arg0.getPoint())) {
			focused = true;
		} else {
			focused = false;
		}
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

	/**
	 * register and update the users typed input. delete and back space variables
	 * are also held here.
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {

		if (focused) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_BACK_SPACE:
				if (cursorLocation - 1 >= 0) {
					if (sb.charAt(cursorLocation - 1) == '\f') {
						cursorLocation--;
						sb.replace(cursorLocation, cursorLocation + 1, "");
					}
					cursorLocation--;
					sb.replace(cursorLocation, cursorLocation + 1, "");
				}
				break;
			case KeyEvent.VK_DELETE:
				if (cursorLocation + 1 < protectedText.length()) {
					if (sb.charAt(cursorLocation + 1) == '\f') {
						sb.replace(cursorLocation, cursorLocation + 1, "");
					}
					sb.replace(cursorLocation, cursorLocation + 1, "");
				} else {
					sb.replace(cursorLocation, cursorLocation + 1, "");
				}
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
				sb.insert(cursorLocation, ("\f\n\f"));
				cursorLocation += 3;
				break;
			case KeyEvent.VK_LEFT:
				if (cursorLocation > 0) {
					if (sb.charAt(cursorLocation - 1) == '\f') {
						cursorLocation--;
					}
					cursorLocation--;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (cursorLocation < protectedText.length()) {
					if (sb.charAt(cursorLocation) == '\f') {
						cursorLocation++;
					}
					cursorLocation++;
				}
				break;
			case KeyEvent.VK_UP:
				if (cursorLocation - 10 > 0) {
					cursorLocation -= 10;
				} else {
					cursorLocation = 0;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (cursorLocation + 10 < protectedText.length()) {
					cursorLocation += 10;
				} else {
					cursorLocation = protectedText.length();
				}
				break;
			case KeyEvent.VK_SPACE:
				sb.insert(cursorLocation, ("\f"));
				cursorLocation++;
			default:
				sb.insert(cursorLocation, arg0.getKeyChar());
				cursorLocation++;
				break;
			}
			text = sb.toString();
			protectedText = text;
			System.out.println(protectedText);
		}
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
