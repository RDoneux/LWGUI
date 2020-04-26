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

/**
 * A very light weight text editor used for displaying and making simple edits
 * to text
 * 
 * @bugs
 * 
 *       when user moves cursor from first line down to a returned line,
 *       cursorLocation.x will be at 0 when it should be a 1
 * 
 * @author Robert Doneux
 * @version 0.2
 *
 */

public class TextArea extends Component {

	private int textX;
	private int textY;
	private int roundEdge;
	private int flatCursorPosition;
	private int charCount;

	private String[] lines;

	private Color background;
	private Color shadow;
	private Color boarder;

	private Point cursorLocation;

	private boolean focused;
	private boolean backwardsCheckCursor;

	private Graphics g;

	public TextArea() {
		roundEdge = 5;
		background = Color.LIGHT_GRAY;
		shadow = Color.DARK_GRAY;
		boarder = Color.BLACK;
		foreground = Color.BLACK;
		sizeEditable = true;
		cursorLocation = new Point(0, 0);
		charCount = 0;
		setText("But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?"
				+ "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?");
	}

	@Override
	public void revise() {
		textX = x + 10;
		textY = y + 5;

		// set the protectedText string length to the max charCount variable. If the
		// charCount is 0, there is no maximum char count - user can input as many chars
		// as they want
		if (charCount > 0 && protectedText.length() > charCount) {
			protectedText = protectedText.substring(0, charCount);
		}
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
		lines = new String[(((g.getFontMetrics().stringWidth(protectedText)) / (getBounds().width - 10)) + lineBreaks)
				* 2];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String space = " ";
			if (word.contains("\n")) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth("");
				lines[line] = sb.toString();
				lineTotal += lines[line].length();
				sb = new StringBuilder();
				line++;

			} else if (lineWidth + g.getFontMetrics().stringWidth(word) > (getBounds().width - 10)) {
				lineHeight += textHeight;
				lineWidth = -g.getFontMetrics().stringWidth("");
				lines[line] = sb.toString();
				lineTotal += lines[line].length();
				sb = new StringBuilder();
				line++;
			}

			g.setColor(foreground);
			g.drawString(word + space, textX + lineWidth, textY + g.getFontMetrics().getAscent() + lineHeight);
			lineWidth += g.getFontMetrics().stringWidth(word + space);
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

		}

		if (cursorLocation.x > -1) {
			int cursorX = 0;
			if (lines[cursorLocation.y] != null) {
				cursorX = g.getFontMetrics().stringWidth(lines[cursorLocation.y].substring(0, cursorLocation.x));
			}
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

		if (cursorLocation.x == 0) {
			// System.out.println();
		}

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

		int height = g.getFontMetrics().getHeight() - 3;

		for (int i = 0; i < lines.length; i++) {
			if (lines[i] != null) {
				int totalWidth = 0;
				if (new Rectangle(textX, textY + ((g.getFontMetrics().getHeight() - 3) * i), getBounds().width, height)
						.contains(e.getPoint())) {
					for (int j = 0; j < lines[i].length(); j++) {

						int width = g.getFontMetrics().stringWidth(String.valueOf(lines[i].charAt(j)));

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
					cursorLocation.x = lines[i].length();
					backwardsCheckCursor = true;
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

		wrapString(g);

		g.setColor(boarder);
		g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

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
		if (getBounds().contains(arg0.getPoint())) {
			focused = true;
			// half the timer value so that it shows immediately
			timer /= 2;
		} else {
			// if the user clicks outside of the area, don't update the values
			focused = false;
		}

		// find the user click location and set the x and y cusor values to that
		// location.
		findXandYfromMouseLocation(arg0);

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
					cursorLocation.x = lines[cursorLocation.y].length();
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
				if (flatCursorPosition < protectedText.length()) {
					cursorLocation.x++;
					flatCursorPosition++;
				}
				backwardsCheckCursor = true;
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
				if (cursorLocation.x == lines[cursorLocation.y].length()) {
					flatCursorPosition--;
				}
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}

}
