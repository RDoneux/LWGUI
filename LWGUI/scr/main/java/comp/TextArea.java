package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * This will eventually be the TextSpace mk2
 * 
 * @author Rober
 *
 */

public class TextArea extends Component {

	private int textX;
	private int textY;
	private int roundEdge;
	private int flatCursorPosition;

	private String[] lines;

	private Color background;
	private Color shadow;
	private Color boarder;

	private Point cursorLocation;

	private boolean focused;
	private boolean backwardsCheckCursor;

	public TextArea() {
		roundEdge = 5;
		background = Color.LIGHT_GRAY;
		shadow = Color.DARK_GRAY;
		boarder = Color.BLACK;
		foreground = Color.BLACK;
		sizeEditable = true;
		cursorLocation = new Point(0, 0);
		setText("");
	}

	@Override
	public void revise() {

		textX = x + 10;
		textY = y + 5;
		
		System.out.println(flatCursorPosition);

	}

	private long timer = System.currentTimeMillis();
	private boolean show;

	private void wrapString(Graphics g) {

		int lineTotal = 0;
		int line = 0;
		int lineWidth = 0;
		int lineHeight = 0;
		int textHeight = g.getFontMetrics(font).getHeight() + 2;
		String words[] = protectedText.split("\f");
		int lineBreaks = protectedText.split("\n").length;
		lines = new String[(g.getFontMetrics().stringWidth(protectedText) / (getBounds().width - 10)) + 2 + lineBreaks];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String space = " ";
			if (lineWidth + g.getFontMetrics().stringWidth(word) > (getBounds().width - 10)) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth("");
				lines[line] = sb.toString();
				lineTotal += lines[line].length();
				sb = new StringBuilder();
				line++;
			}

			if (word.equals("\n")) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth("\n\f");
				lines[line] = sb.toString();
				lineTotal += lines[line].length();
				sb = new StringBuilder();
				line++;
				space = "\n";
			}

			sb.append(word + space);

			g.setColor(foreground);
			g.drawString(word + " ", textX + lineWidth, textY + g.getFontMetrics().getAscent() + lineHeight);
			lineWidth += g.getFontMetrics().stringWidth(word + " ");

			if (i == words.length - 1) {
				lines[line] = sb.toString();
			}

			if (backwardsCheckCursor) {
				if (line == cursorLocation.y) {
					flatCursorPosition = lineTotal + cursorLocation.x;
					backwardsCheckCursor = false;
				}
			} else {
				normaliseXandY(lines);
			}

		}

		int cursorX = g.getFontMetrics().stringWidth(lines[cursorLocation.y].substring(0, cursorLocation.x));
		int cursorY = cursorLocation.y * textHeight;

		if (timer + 500 < System.currentTimeMillis()) {
			show = !show;
			timer = System.currentTimeMillis();
		}
		if (show && focused) {
			g.setColor(Color.BLACK);
			g.fillRect(textX + cursorX, textY + cursorY, 2, g.getFontMetrics(font).getHeight());
		}

	}

	/**
	 * provides backwards checking from the flatCursorPosition (which considers the
	 * size of the text as a 1D array) to the cursorLocation Point, which considers
	 * the text size as a 2D array. This is particularly important when shortening
	 * words at the beginning and end of sentences which are in danger of being
	 * wrapped onto a different line. Without this method, the cursor will stay at
	 * it's 2D location, despite the 1D location changing.
	 * 
	 * @param lines: the 2D array of text
	 */
	private void normaliseXandY(String lines[]) {

		int y = 0;
		int x = 0;
		int total = 0;

		// check each line to see if it contains the flatCursorPosition. If it doesn't,
		// increase the cursorLocation.y variable and continue. If it does, set the
		// cursorLocation.x variable to the left over text
		for (String l : lines) {
			if (l != null) {
				total += l.length();
				// System.out.println(lines.length + ": " + total + " ~ " + flatCursorPosition);
				if (total < flatCursorPosition) {
					y++;
				} else {
					x = l.length() - (total - flatCursorPosition);
					break;
				}
			}
		}

		// update the 2D location from the 1D location
		cursorLocation.x = x;
		cursorLocation.y = y;

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(shadow);
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		g.setColor(background);
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		wrapString(g);

		g.setColor(boarder);
		g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (getBounds().contains(arg0.getPoint())) {
			focused = true;
			timer += 1000;
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

	@Override
	public void keyPressed(KeyEvent arg0) {

		if (focused) {
			StringBuilder sb = new StringBuilder(protectedText);
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (cursorLocation.y > 0) {
					cursorLocation.y--;
				}
				if (cursorLocation.x > lines[cursorLocation.y].length()) {
					cursorLocation.x = lines[cursorLocation.y].length() - 1;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_DOWN:
				if (cursorLocation.y < lines.length - 3) {
					cursorLocation.y++;
				}
				if (cursorLocation.x > lines[cursorLocation.y].length()) {
					cursorLocation.x = lines[cursorLocation.y].length() - 1;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_LEFT:
				if (flatCursorPosition > 0) {
					cursorLocation.x--;
					flatCursorPosition--;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_RIGHT:
				if (flatCursorPosition < protectedText.length()) {
					cursorLocation.x++;
					flatCursorPosition++;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_SHIFT:
				break;
			case KeyEvent.VK_BACK_SPACE:
				if (cursorLocation.x > 0) {
					sb.deleteCharAt(flatCursorPosition - 1);
					cursorLocation.x--;
					flatCursorPosition--;
				} else {
					cursorLocation.x--;
					flatCursorPosition--;
				}
				break;
			case KeyEvent.VK_SPACE:
				sb.insert(flatCursorPosition, ("\f"));
				cursorLocation.x++;
				flatCursorPosition++;
				break;
			case KeyEvent.VK_ENTER:
				sb.insert(flatCursorPosition, ("\f\n\f"));
				flatCursorPosition += 3;
				cursorLocation.y++;
				cursorLocation.x = 0;

				break;
			default:
				backwardsCheckCursor = true;
				sb.insert(flatCursorPosition, arg0.getKeyChar());
				cursorLocation.x++;

				break;
			}
			
			
			setText(sb.toString());

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
