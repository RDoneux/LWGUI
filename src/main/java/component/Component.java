package component;

import tools.IDGenerator;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public abstract class Component extends GUIComponent {

	protected boolean scaleFont; // scale the font to the size of this lable's parent container
	protected int maxFont;
	protected int minFont;
	protected String text;
	protected String protectedText;
	protected Font font = new Font("Times Roman", Font.PLAIN, 12);
	protected Color foreground;
	protected FontRenderContext frc = new FontRenderContext(null, false, false);

	/**
	 * Receives a boundary from the Parent {@link Container}'s {@link Layout}. The
	 * Class will minimise the string size to the first three chars followed by
	 * "[...]" if the width of the string is larger than this given boundary
	 * 
	 * @param Rectangle parentSpace
	 */
	public void minimise(Rectangle parentSpace) {

		if (protectedText == null) {
			return;
		}

		Rectangle2D bounds = font.getStringBounds(protectedText, frc);

		if (bounds.getWidth() >= parentSpace.width && !minimised) {
			if (!protectedText.equals("") && protectedText.length() > 3) {
				text = protectedText.substring(0, 3) + "[...]";
				minimised = true;
			}
		}
		if (bounds.getWidth() < parentSpace.width && minimised) {
			text = protectedText;
			minimised = false;
		}

	}

	public Component() {
		id = IDGenerator.generateID();
	}

	public String getText() {
		return protectedText;
	}

	public void setText(String text) {
		this.text = text;
		this.protectedText = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	public boolean isScaledFont() {
		return scaleFont;
	}

	public void scaleFont(boolean scaleFont) {
		this.scaleFont = scaleFont;
	}

	public void scaleFont(boolean scaleFont, int maxFont, int minFont) {
		this.scaleFont = scaleFont;
		this.maxFont = maxFont;
		this.minFont = minFont;
	}

}
