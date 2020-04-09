package comp;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import tools.IDGenerator;
import tools.Maths;

public abstract class GUIComponent implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int id;
	
	protected int gridx;
	protected int gridy;

	protected double weightX;
	protected double weightY;

	protected String name;

	protected GUIComponent parent;
	protected JFrame topLevelParent;

	protected alignment alignmentX;
	protected alignment alignmentY;
	protected alignment bias = alignment.EAST;

	public enum alignment {
		NORTH, EAST, SOUTH, WEST, CENTRE;
	}

	public GUIComponent() {
		id = IDGenerator.generateID();
	}

	public abstract void revise();

	public abstract void paint(Graphics g);

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void setSize(Dimension size) {
		this.width = size.width;
		this.height = size.height;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

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

	public int getID() {
		return id;
	}

	public GUIComponent getParent() {
		return parent;
	}

	public void setParent(GUIComponent parent) {
		this.parent = parent;
	}

	public void setParent(JFrame topLevelParent) {
		this.topLevelParent = topLevelParent;
	}

	public void setWeightX(double weightX) {
		if (weightX > 1) {
			throw new IllegalArgumentException("weightX cannot be greater than 1");
		}
		this.weightX = Maths.round(weightX, 2);
	}

	public void setWeightY(double weightY) {
		if (weightX > 1) {
			throw new IllegalArgumentException("weightY cannot be greater than 1");
		}
		this.weightY = Maths.round(weightY, 2);
	}

	public double getWeightX() {
		return weightX;
	}

	public double getWeightY() {
		return weightY;
	}

	public void setAlignmentX(alignment alignmentX) {
		if (alignmentX != alignment.CENTRE && alignmentX != alignment.EAST && alignmentX != alignment.WEST) {
			throw new IllegalArgumentException(
					"x alignment must be CENTRE, EAST or WEST. Current Alignment: " + alignmentX);
		}
		this.alignmentX = alignmentX;
	}

	public alignment getAlignmentX() {
		return alignmentX;
	}

	public void setAlignmentY(alignment alignmentY) {
		if (alignmentY != alignment.CENTRE && alignmentY != alignment.NORTH && alignmentY != alignment.SOUTH) {
			throw new IllegalArgumentException(
					"y alignment must be CENTRE, NORTH or SOUTH. Current Alignment: " + alignmentY);
		}
		this.alignmentY = alignmentY;
	}

	public alignment getAlignmentY() {
		return alignmentY;
	}
	
	public alignment getBias() {
		return bias;
	}

	public void setBias(alignment bias) {
		this.bias = bias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGridx() {
		return gridx;
	}

	public void setGridx(int gridx) {
		this.gridx = gridx;
	}

	public int getGridy() {
		return gridy;
	}

	public void setGridy(int gridy) {
		this.gridy = gridy;
	}
	
}
