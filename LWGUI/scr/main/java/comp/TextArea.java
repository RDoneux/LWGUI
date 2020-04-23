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

	private Color background;
	private Color shadow;
	private Color boarder;

	private Point cursorLocation;

	private boolean focused;
	private boolean update;

	private int typedWord;

	public TextArea() {

		roundEdge = 5;
		background = Color.LIGHT_GRAY;
		shadow = Color.DARK_GRAY;
		boarder = Color.BLACK;
		foreground = Color.BLACK;
		sizeEditable = true;
		cursorLocation = new Point(0, 0);
		setText("this is the text that will be displayed and ideally i would like it to move over onto the next line so that that number goes up");

	}

	@Override
	public void revise() {

		textX = x + 10;
		textY = y + 5;

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
		String[] lines = new String[(g.getFontMetrics().stringWidth(protectedText) / (getBounds().width - 100)) + 1];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
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
				lineWidth = -g.getFontMetrics().stringWidth("\n");
				lines[line] = sb.toString();
				lineTotal += lines[line].length();
				sb = new StringBuilder();
				line++;
				lineTotal = i;
			}
			sb.append(word + " ");

			if (update) {
				if (line == cursorLocation.y) {
					flatCursorPosition = lineTotal + cursorLocation.x;
					update = false;
				}
			} else {
				normaliseXandY(lines);
			}
			
			g.setColor(foreground);
			g.drawString(word + " ", textX + lineWidth, textY + g.getFontMetrics().getAscent() + lineHeight);
			lineWidth += g.getFontMetrics().stringWidth(word + " ");

		}

		lines[line] = sb.toString();

		if (cursorLocation.y >= lines.length) {
			cursorLocation.y = lines.length - 1;
		}
		if (cursorLocation.y < 0) {
			cursorLocation.y = 0;
		}
		if (cursorLocation.x > lines[cursorLocation.y].length() - 1) {
			if (cursorLocation.y < lines.length - 1) {
				cursorLocation.y++;
				cursorLocation.x = typedWord;
			} else {
				cursorLocation.x = lines[cursorLocation.y].length();
			}
		}
		if (cursorLocation.x < 0) {
			if (cursorLocation.y > 0) {
				cursorLocation.y--;
				cursorLocation.x = lines[cursorLocation.y].length() - 1;
			} else {
				cursorLocation.x = 0;
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

		// System.out.println(cursorLocation.x);

	}

	private void normaliseXandY(String lines[]) {

		int y = 0;
		int x = 0;
		int total = 0;
		for (String l : lines) {
			if (l != null) {
				total += l.length();
				if (total <= flatCursorPosition) {
					y++;
				} else {
					// System.out.println(total + " ~ " + flatCursorPosition);
					// System.out.println(l.length() - (total - flatCursorPosition ));
					x = l.length() - (total - flatCursorPosition);
					break;
				}
			}
		}

		cursorLocation.x = x;
		cursorLocation.y = y;
		// System.out.println(x + " ~ " + y);

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
				cursorLocation.y--;
				typedWord = 0;
				update = true;
				break;
			case KeyEvent.VK_DOWN:
				cursorLocation.y++;
				typedWord = 0;
				update = true;
				break;
			case KeyEvent.VK_LEFT:
				// flatCursorPosition --;
				cursorLocation.x--;
				typedWord = 0;
				update = true;
				break;
			case KeyEvent.VK_RIGHT:
				// flatCursorPosition ++;
				cursorLocation.x++;
				typedWord = 0;
				update = true;
				break;
			case KeyEvent.VK_BACK_SPACE:
				// cursorLocation.x--;
				if (cursorLocation.x > 0) {
					sb.deleteCharAt(flatCursorPosition - 1);

					// sb.insert(flatCursorPosition, "\f");
					cursorLocation.x--;
					flatCursorPosition --;
				} else {
					cursorLocation.x--;
					flatCursorPosition --;
				}
				// sb.replace(flatCursorPosition, flatCursorPosition + 1, "");
				break;
			case KeyEvent.VK_SPACE:
				sb.insert(flatCursorPosition, ("\f"));
				cursorLocation.x++;
				typedWord = 0;
				update = true;
				break;
			default:
				sb.insert(flatCursorPosition, arg0.getKeyChar());
				cursorLocation.x++;
				typedWord++;
				update = true;
				break;
			}
			setText(sb.toString());
			// protectedText = text;
			// System.out.println(protectedText);
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
