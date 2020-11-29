package effects;

import java.awt.Graphics;

import comp.GUIComponent;

public abstract class Effect {

	protected int x;
	protected int y;
	protected int width;
	protected int height;

	protected effectType type;
	
	protected GUIComponent parent;

	public enum effectType {
		HIGHLIGHT;
	}

	public abstract void paint(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public effectType getType() {
		return type;
	}

	public void setType(effectType type) {
		this.type = type;
	}

	public GUIComponent getParent() {
		return parent;
	}

	public void setParent(GUIComponent parent) {
		this.parent = parent;
	}
		
}
