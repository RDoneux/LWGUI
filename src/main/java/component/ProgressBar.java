package component;

import tools.Maths;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * 
 * this class displays the percentage of completion calculated from a current
 * and maximum value, factoring in the width of the component
 * 
 * @author Robert Doneux
 * @version 0.1
 *
 */

public class ProgressBar extends Component {

	private final Color background;
	private final Color boarder;
	private Color gradientMin;
	private Color gradientMax;

	private boolean colourGradient;
	private boolean displayText;
	private boolean scale;
	private boolean displayProgress;

	private int value;
	private int holdValue;
	private int max;
	private final int roundEdge;

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
		if (max > 0) {
			this.value = holdValue * width / max;
		}
		if (displayProgress) {
			text = holdValue * 100 / max + "%";
			protectedText = text;
		}
	}

	@Override
	public void paint(Graphics g) {

		Rectangle previousClip = g.getClipBounds();
		// g.setClip(parent.getBounds());
		// draw background

		if (show) {
			g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), transparency));
			g.fillRoundRect(x, y, width, height, roundEdge, roundEdge);

			// draw foreground
			g.setColor(new Color(foreground.getRed(), foreground.getGreen(), foreground.getBlue(), transparency));
			g.fillRoundRect(x, y, value, height, roundEdge, roundEdge);

			// draw boarder
			g.setColor(new Color(boarder.getRed(), boarder.getGreen(), boarder.getBlue(), transparency));
			g.drawRoundRect(x, y, width, height, roundEdge, roundEdge);

			if (displayText) {
				if (scale) {
					Font newfont = new Font(font.getFontName(), font.getStyle(),
							Maths.scaleFont(g, protectedText, getBounds()));
					g.setFont(newfont);
				} else {
					minimise(getBounds());
				}
				g.setColor(new Color(255 - (background.getRed() + foreground.getRed()) / 2,
						255 - (background.getGreen() + foreground.getGreen()) / 2,
						255 - (background.getBlue() + foreground.getBlue()) / 2, transparency));
				Maths.drawCentredString(g, text, getAnimationBounds());
			}
		}
		g.setClip(previousClip);

	}

	/**
	 * the colour of the foreground will gradient between the two colours given
	 * below.
	 * 
	 * @param low  - colour displayed when value is lowest
	 * @param high - colour displayed when value is highest
	 */
	public void gradientColour(Color low, Color high) {
		this.gradientMin = low;
		this.gradientMax = high;
		colourGradient = true;
	}

	/**
	 * update the value of the display. If the user has requested the colour to
	 * gradient, calculate the new colour value as well
	 * 
	 * @param value
	 * @param max
	 */
	public void updateValue(int value, int max) {
		this.max = max;
		this.holdValue = value;

		if (colourGradient) {

			int lowR = Math.min(gradientMin.getRed(), gradientMax.getRed());
			int lowG = Math.min(gradientMin.getGreen(), gradientMax.getGreen());
			int lowB = Math.min(gradientMin.getBlue(), gradientMax.getBlue());

			int highR = Math.max(gradientMin.getRed(), gradientMax.getRed());
			int highG = Math.max(gradientMin.getGreen(), gradientMax.getGreen());
			int highB = Math.max(gradientMin.getBlue(), gradientMax.getBlue());

			int diffR = highR - lowR;
			int diffG = highG - lowG;
			int diffB = highB - lowB;

			if (diffR > 0) {
				int changeR = (value * diffR / max);
				if (gradientMin.getRed() < gradientMax.getRed()) {
					foreground = new Color(gradientMin.getRed() + changeR, foreground.getGreen(), foreground.getBlue());
				} else {
					foreground = new Color(gradientMin.getRed() - changeR, foreground.getGreen(), foreground.getBlue());
				}
			} else {
				foreground = new Color(gradientMin.getRed(), foreground.getGreen(), foreground.getBlue());
			}
			if (diffG > 0) {
				int changeG = (value * diffG / max);
				if (gradientMin.getGreen() < gradientMax.getGreen()) {
					foreground = new Color(foreground.getRed(), gradientMin.getGreen() + changeG, foreground.getBlue());
				} else {
					foreground = new Color(foreground.getRed(), gradientMin.getGreen() - changeG, foreground.getBlue());
				}
			} else {
				foreground = new Color(foreground.getRed(), gradientMin.getGreen(), foreground.getBlue());
			}
			if (diffB > 0) {
				int changeB = (value * diffB / max);
				if (gradientMin.getBlue() < gradientMax.getBlue()) {
					foreground = new Color(foreground.getRed(), foreground.getGreen(), gradientMin.getBlue() + changeB);
				} else {
					foreground = new Color(foreground.getRed(), foreground.getGreen(), gradientMin.getBlue() - changeB);
				}
			} else {
				foreground = new Color(foreground.getRed(), foreground.getGreen(), gradientMin.getBlue());
			}
		}
	}

	/**
	 * 
	 * displays the specified string on top of the foreground colour. The colour of
	 * the text will change as the colour of the foreground changes to ensure that
	 * it is always visible.
	 * 
	 * The scale value (if set to true) will scale the font size to fit into the
	 * current width and height of the component.
	 * 
	 * @param displayText
	 * @param scale
	 */
	public void setDisplayText(String displayText, boolean scale) {
		this.scale = scale;
		this.text = displayText;
		this.protectedText = displayText;
		this.displayText = true;
	}

	/**
	 * 
	 * displays the progress of the value variable, calculated as a percentage from
	 * the max value
	 * 
	 * @param display
	 */
	public void displayProgress(boolean display) {
		displayText = display;
		displayProgress = display;
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
