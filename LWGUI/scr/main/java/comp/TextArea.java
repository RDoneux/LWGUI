package comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * A very light weight text editor used for displaying and making simple edits
 * to text. Currently does not support text scrolling or highlighting.
 * 
 * @author Robert Doneux
 * @version 0.2
 */

public class TextArea extends Component {

	private int textX;
	private int textY;
	private int textHeight;
	private int cursorY;
	private int roundEdge;
	private int flatCursorPosition;
	private int charCount;
	private int xScroll;
	private int yScroll;
	private int scrollHeight;
	private int linesTotal;

	private String[] lines;

	private Color background;
	private Color shadow;
	private Color boarder;

	private Point cursorLocation;

	private boolean focused;
	private boolean editable;
	private boolean backwardsCheckCursor;

	private Graphics g;

	public TextArea() {
		roundEdge = 5;
		background = Color.WHITE;
		shadow = Color.DARK_GRAY;
		boarder = Color.BLACK;
		foreground = Color.BLACK;
		sizeEditable = true;
		editable = true;
		cursorLocation = new Point(0, 0);
		charCount = 0;
		setText("");
	}

	@Override
	public void revise() {

		textX = (x + 10) + xScroll;
		textY = (y + 5) + yScroll;

		if (focused) {
			if (cursorY >= y + (-textY + height)) {
				yScroll -= textHeight;
			}
			if (cursorY <= y + -textY) {
				yScroll += textHeight;
			}
		}

		// set the protectedText string length to the max charCount variable. If the
		// charCount is 0, there is no maximum char count - user can input as many chars
		// as they want
		if (charCount > 0 && protectedText.length() > charCount) {
			protectedText = protectedText.substring(0, charCount);
		}
	}

	/**
	 * wrap the protectedText string within the bounds of this component and draw
	 * the cursor position.
	 */
	private long timer = System.currentTimeMillis();
	private boolean show;

	private void wrapString(Graphics g) {

		int lineTotal = 0;
		int line = 0;
		int lineWidth = 0;
		int lineHeight = 0;
		textHeight = g.getFontMetrics(font).getHeight() + 2;
		setScrollMagnitude(textHeight);
		String words[] = protectedText.split("\f");
		int lineBreaks = protectedText.split("\n").length;
		lines = new String[(((g.getFontMetrics(font).stringWidth(protectedText)) / (getBounds().width - 15)) + 3
				+ lineBreaks) * 2];
		// System.out.println(cursorLocation.y + " ~ " + lines.length);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < words.length; i++) {

			if ((lineHeight - -yScroll) < (y + height) + (textHeight * 2)) {
				String word = words[i];
				String space = " ";
				if (word.contains("\n")) {
					lineHeight += textHeight;
					lineWidth = -g.getFontMetrics(font).stringWidth("");
					lines[line] = sb.toString();
					lineTotal += lines[line].length();
					sb = new StringBuilder();
					line++;

				} else if (lineWidth + g.getFontMetrics(font).stringWidth(word) >= (getBounds().width - 15)) {
					lineHeight += textHeight;
					lineWidth = -g.getFontMetrics(font).stringWidth("");
					lines[line] = sb.toString();
					lineTotal += lines[line].length();
					sb = new StringBuilder();
					line++;
				}

				if (textY + g.getFontMetrics(font).getAscent() + lineHeight > y) {
					g.setColor(foreground);
					g.drawString(word + space, textX + lineWidth,
							textY + g.getFontMetrics(font).getAscent() + lineHeight);
				}
				lineWidth += g.getFontMetrics(font).stringWidth(word + space);
				sb.append(word + space);

				if (i == words.length - 1) {
					lines[line] = sb.toString();
				}

				if (backwardsCheckCursor) {
					if (line == cursorLocation.y) {
						flatCursorPosition = lineTotal + cursorLocation.x;
						backwardsCheckCursor = false;
					}
				} else {
					if (i == words.length - 1) {
						normaliseXandY(lines);
					}
				}
			} else {
				// break;
			}
			linesTotal = line;
		}

		if (cursorLocation.x > -1) {
			int cursorX = 0;
			if (lines[cursorLocation.y] != null && cursorLocation.x < lines[cursorLocation.y].length()) {
				cursorX = g.getFontMetrics(font).stringWidth(lines[cursorLocation.y].substring(0, cursorLocation.x));
			}
			cursorY = cursorLocation.y * textHeight;

			// System.out.println(cursorY + " ~ " + textY + " ~ " + scrollHeight + " ~ " +
			// (-textY + (height + 2)));
			if (timer + 500 < System.currentTimeMillis()) {
				show = !show;
				timer = System.currentTimeMillis();
			}
			if (show && focused) {
				g.setColor(Color.BLACK);
				g.fillRect(textX + cursorX, textY + cursorY, 2, g.getFontMetrics(font).getHeight());
			}
		}
		scrollHeight = lineHeight;
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
			if (l != null && l.length() > 0) {
				total += l.length();
				if (total < flatCursorPosition) {
					y++;
				} else {
					if (cursorLocation.x == 0 && lines.length > 3 && cursorLocation.y > 0) {
						y++;
						x = 0;
					} else {
						x = l.length() - (total - flatCursorPosition);
					}
					break;
				}
			}
		}

		// update the 2D location from the 1D location
		cursorLocation.x = x;
		cursorLocation.y = y;

	}

	/**
	 * 
	 * searches the whole text displayed and checks if each character is within the
	 * passed mouse click. If it finds a location, set the x and y cursor Location
	 * to that point
	 * 
	 * @param e the mouse location
	 */
	private void findXandYfromMouseLocation(MouseEvent e) {

		int height = g.getFontMetrics(font).getHeight() + 2;

		for (int i = 0; i < lines.length; i++) {
			int totalWidth = 0;
			if (lines[i] != null) {
				if (new Rectangle(textX, textY + (height * i), getBounds().width, height).contains(e.getPoint())) {
					for (int j = 0; j < lines[i].length(); j++) {

						int width = g.getFontMetrics(font).stringWidth(String.valueOf(lines[i].charAt(j)));

						Rectangle rect = new Rectangle(textX + totalWidth, textY + (height * i), width, height);
						if (rect.contains(e.getPoint())) {
							cursorLocation.y = i;
							cursorLocation.x = j;
							backwardsCheckCursor = true;
							return;
						}
						totalWidth += width;
					}
					// if the x location isn't found then the user has clicked outside of the width
					// bounds. Set the x location to the maximum string length
					cursorLocation.y = i;
					cursorLocation.x = lines[i].length() - 1;
					backwardsCheckCursor = true;
					return;
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {

		// the graphics variable is necessary for finding the width and height values of
		// each line of text from the getXandYfromMouseLocation method
		this.g = g;

		Rectangle clipBounds = g.getClipBounds();
		g.setClip(getBounds());

		g.setColor(shadow);
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		g.setColor(background);
		g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

		g.setFont(font);
		wrapString(g);

		g.setColor(boarder);
		g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

		if (focused) {
			g.setColor(new Color(50, 150, 200));
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
		}

		g.setClip(clipBounds);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

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

		// check to see if the user has clicked within the bounds of the TextArea. if
		// they have, set the focused variable to true. This is to ensure that not all
		// text areas in the frame are updated at the same time with the users key
		// presses
		if (getBounds().contains(arg0.getPoint()) && editable) {
			focused = true;
			// half the timer value so that it shows immediately
			timer /= 2;
		} else {
			// if the user clicks outside of the area, don't update the values
			focused = false;
		}

		// find the user click location and set the x and y cusor values to that
		// location.
		if (focused) {
			findXandYfromMouseLocation(arg0);
		}
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

		// if the TextArea is focused, update the text values from the user input
		if (focused) {
			if (lines[cursorLocation.y].equals("\n ")) {
				cursorLocation.x = 1;
			}

			StringBuilder sb = new StringBuilder(protectedText);
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (cursorLocation.y > 0) {
					cursorLocation.y--;
				}
				if (cursorLocation.y == 0 && lines[0].equals(" ")) {
					cursorLocation.x = 0;
				}
				if (cursorLocation.x > lines[cursorLocation.y].length()) {
					cursorLocation.x = lines[cursorLocation.y].length() - 1;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_DOWN:
				if (lines[cursorLocation.y + 1] != null) {
					cursorLocation.y++;
				} else {
					break;
				}
				if (cursorLocation.x > lines[cursorLocation.y].length()) {
					cursorLocation.x = lines[cursorLocation.y].length() - 1;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_LEFT:
				if (flatCursorPosition > 0) {
					cursorLocation.x--;
				}
				if (lines[cursorLocation.y].startsWith("\n") && cursorLocation.x == 0) {
					cursorLocation.x--;
				}
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_RIGHT:
				if (flatCursorPosition < protectedText.length() - 1) {
					cursorLocation.x++;
					backwardsCheckCursor = true;
				}
				break;
			case KeyEvent.VK_SHIFT:
				break;
			case KeyEvent.VK_CAPS_LOCK:
				break;
			case KeyEvent.VK_CONTROL:
				break;
			case KeyEvent.VK_END:
				cursorLocation.x = lines[cursorLocation.y].length() - 1;
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_HOME:
				cursorLocation.x = 0;
				backwardsCheckCursor = true;
				break;
			case KeyEvent.VK_BACK_SPACE:
				if (cursorLocation.x > 0) {
					sb.deleteCharAt(flatCursorPosition - 1);
					cursorLocation.x--;
					flatCursorPosition--;
				} else if (cursorLocation.y > 0) {
					cursorLocation.x--;
					flatCursorPosition--;
				}
				if (lines[cursorLocation.y] != null && lines[cursorLocation.y].startsWith("\n")
						&& cursorLocation.x == 0) {
					sb.deleteCharAt(flatCursorPosition - 1);
					cursorLocation.x--;
					flatCursorPosition--;
				}
				break;
			case KeyEvent.VK_DELETE:
				if (flatCursorPosition < protectedText.length()) {
					sb.deleteCharAt(flatCursorPosition);
					if (cursorLocation.x == lines[cursorLocation.y].length() - 1) {
						sb.deleteCharAt(flatCursorPosition);
					}
					if (lines[cursorLocation.y].equals(" ") || lines[cursorLocation.y].equals("\n ")) {
						sb.deleteCharAt(flatCursorPosition);
						sb.deleteCharAt(flatCursorPosition);
					}
				}
				break;
			case KeyEvent.VK_SPACE:
				if (cursorLocation.x < lines[cursorLocation.y].length()
						|| cursorLocation.y == 0 && cursorLocation.y == lines.length + 3) {
					sb.insert(flatCursorPosition, ("\f"));
					cursorLocation.x++;
					flatCursorPosition++;
				}
				break;
			case KeyEvent.VK_ENTER:
				sb.insert(flatCursorPosition, ("\f\n"));
				flatCursorPosition += 2;
				break;
			default:
				sb.insert(flatCursorPosition, arg0.getKeyChar());
				cursorLocation.x++;
				backwardsCheckCursor = true;
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

	private int scrollMagnitude;

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {

		if (focused) {
			// limit the scrolling bounds to the protectedText size
			int scrollRate = arg0.getWheelRotation();
			if (-(y - textY) + scrollMagnitude >= 5 && scrollRate < 0) {
				yScroll = 0;
			} else if (-(y - textY) - scrollMagnitude <= -scrollHeight && scrollRate > 0) {
				yScroll = -scrollHeight;
			} else {
				yScroll -= scrollRate * scrollMagnitude;

			}
			if (cursorLocation.y + scrollRate >= 0 && cursorLocation.y + scrollRate <= linesTotal) {
				cursorLocation.y += scrollRate;
				backwardsCheckCursor = true;
			} else if (cursorLocation.y < 0) {
				cursorLocation.y = 0;
				return;
			} else if (cursorLocation.y > linesTotal) {
				cursorLocation.y = linesTotal;
			}
		}

	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setScrollMagnitude(int magnitude) {
		this.scrollMagnitude = magnitude;
	}

}
