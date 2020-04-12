package comp;

import java.awt.Color;
import java.awt.Font;

import tools.IDGenerator;

public abstract class Component extends GUIComponent {

	protected String text;
	protected String protectedText;
	protected Font font;
	protected Color foreground;

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

}
