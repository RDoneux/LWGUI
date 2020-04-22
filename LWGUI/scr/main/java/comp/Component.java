package comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import constraints.Layout;
import tools.IDGenerator;

public abstract class Component extends GUIComponent {

	protected String text;
	protected String protectedText;
	protected Font font = new Font("Times Roman", Font.PLAIN, 12);
	protected Color foreground;

	/**
	 * Receives a boundary from the Parent {@link Container}'s {@link Layout}. The
	 * Class will minimise the string size to the first three chars followed by
	 * "[...]" if the width of the string is larger than this given boundary
	 * 
	 * @param Rectangle parentSpace
	 */
	public void minimise(Rectangle parentSpace) {

		FontRenderContext frc = new FontRenderContext(null, false, false);
		TextLayout layout = new TextLayout(protectedText, font, frc);

		if (layout.getBounds().getWidth() > parentSpace.width && !minimised) {
			if (!protectedText.equals("")) {
				text = protectedText.substring(0, 3) + "[...]";
				minimised = true;
			}
		}
		if (layout.getBounds().getWidth() < parentSpace.width && minimised) {
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
		protectedText = protectedText.replace(" ", "\f ");
		text = text.replace(" ", "\f ");
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

}
