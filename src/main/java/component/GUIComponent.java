package component;

import animation.Animation;
import animation.FlyIn;
import animation.FlyOut;
import effects.Effect;
import tools.IDGenerator;
import tools.Maths;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public abstract class GUIComponent implements MouseListener, MouseMotionListener, KeyListener, MouseWheelListener {

	protected volatile int x;
	protected volatile int y;
	protected volatile int width;
	protected volatile int height;

	protected volatile int animationX;
	protected volatile int animationY;
	protected volatile int animationWidth;
	protected volatile int animationHeight;

	protected int id;

	protected int gridx;
	protected int gridy;
	protected int gridWidth;
	protected int gridHeight;

	protected int xOffset;
	protected int yOffset;

	protected int transparency;

	protected int edge;

	protected int level; // specify in which order the item should be rendered at

	protected double weightX;
	protected double weightY;

	protected String name;

	protected Rectangle layoutBounds;

	protected GUIComponent parent;
	protected Frame topLevelParent;
	protected Frame displayWindow;

	protected alignment alignmentX;
	protected alignment alignmentY;
	protected alignment bias;

	protected boolean sizeEditable;
	protected boolean minimised;
	protected boolean loaded;
	protected boolean show;
	protected boolean autoLayout; // indicate if the layout manager should control the x, y, width & variables for
									// this component. Set to true for default

	protected Animation currentAnimation;
	protected Effect currentEffect;

	public enum alignment {
		NORTH, EAST, SOUTH, WEST, CENTRE
    }

	public GUIComponent() {
		id = IDGenerator.generateID();
		weightX = 1;
		weightY = 1;
		alignmentX = alignment.WEST;
		alignmentY = alignment.NORTH;
		gridWidth = 1;
		gridHeight = 1;
		transparency = 255;
		show = true;
		autoLayout = true;
	}

	public abstract void revise();

	public abstract void paint(Graphics g);

	public void drawEffect(Graphics g) {
		if (currentEffect != null) {
			currentEffect.paint(g);
		}
	}

	/**
	 * This method is called when the user adds an animation to a particular
	 * GUIComponent. Only one animation can be added at once currently
	 * 
	 * @see Animation
	 * @see FlyIn
	 * @see FlyOut
	 * 
	 * @param animation
	 */
	public void queAnimation(Animation animation) {

		// if the animation hasn't been set yet, create a new animation and start the
		// animation loop
		if (currentAnimation == null) {
			currentAnimation = animation;
			currentAnimation.setParent(this);
			currentAnimation.start();
			return;
		}
		// if an animation has been set, check to see if it is the same type of
		// animation that has already been set. If it is different, replace the current
		// animation with the new one and start the loop.
		if (currentAnimation.getType() != animation.getType()) {
			stopAnimation();

			currentAnimation = animation;
			currentAnimation.setParent(this);
			currentAnimation.start();
		}

	}

	public synchronized void stopAnimation() {
		if (currentAnimation != null) {
			currentAnimation.stop();
		}
	}

	public void setEffect(Effect effect) {
		if (effect == null) {
			currentEffect = null;
			return;
		}
		if (currentEffect == null || currentEffect.getType() != effect.getType()) {
			currentEffect = effect;
			currentEffect.setParent(this);
		}
	}

	// this is the desired display bounds of the component
	public Rectangle getBounds() {
		return new Rectangle(x - animationX, y - animationY, width - animationWidth, height - animationHeight);
	}

	// this is the actual display bounds of the component
	public Rectangle getAnimationBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void setSize(Dimension size) {
		this.width = size.width;
		this.height = size.height;
	}

	public synchronized void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public synchronized int getX() {
		return x - animationX;
	}

	public synchronized void setX(int x) {
		this.x = x + animationX;
		this.loaded = true;
	}

	public synchronized int getY() {
		return y - animationY;
	}

	public synchronized void setY(int y) {
		this.y = y + animationY;
		this.loaded = true;
	}

	public synchronized int getWidth() {
		return width - animationWidth;
	}

	public synchronized void setWidth(int width) {
		this.width = width + animationWidth;
	}

	public synchronized int getHeight() {
		return height - animationHeight;
	}

	public synchronized void setHeight(int height) {
		this.height = height + animationHeight;
	}

	public int getVisualX() {
		return x;
	}

	public int getVisualY() {
		return y;
	}

	public int getVisualWidth() {
		return width;
	}

	public int getVisualHeight() {
		return height;
	}

	public synchronized int getAnimationX() {
		return animationX;
	}

	public synchronized void setAnimationX(int animationX) {
		this.animationX = animationX;
	}

	public synchronized int getAnimationY() {
		return animationY;
	}

	public synchronized void setAnimationY(int animationY) {
		this.animationY = animationY;
	}

	public int getAnimationWidth() {
		return animationWidth;
	}

	public synchronized void incrementAnimationX(int increment) {
		this.animationX += increment;
	}

	public synchronized void incrementAnimationY(int increment) {
		this.animationY += increment;
	}

	public synchronized void incrementAnimationWidth(int increment) {
		this.animationWidth += increment;
	}

	public synchronized void incrementAnimationHeight(int increment) {
		this.animationHeight += increment;
	}

	public void setAnimationWidth(int animationWidth) {
		this.animationWidth = animationWidth;
		this.width = width + this.animationWidth;
	}

	public int getAnimationHeight() {
		return animationHeight;
	}

	public void setAnimationHeight(int animationHeight) {
		this.animationHeight = animationHeight;
		this.height = height + animationHeight;
	}

	public int getID() {
		return id;
	}

	public GUIComponent getParent() {
		return parent;
	}

	public Frame getTopLevelParent() {
		return topLevelParent;
	}

	public void setParent(GUIComponent parent) {
		this.parent = parent;
	}

	public void setParent(Frame topLevelParent) {
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
		if (gridx < 0) {
			throw new IllegalArgumentException("Gridx must be a positive number");
		}
		this.gridx = gridx;
	}

	public int getGridy() {
		return gridy;
	}

	public void setGridy(int gridy) {
		if (gridy < 0) {
			throw new IllegalArgumentException("Gridy must be a positive number");
		}
		this.gridy = gridy;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int gridWidth) {
		if (gridWidth < 0) {
			throw new IllegalArgumentException("gridWidth must be a positive number");
		}
		this.gridWidth = gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(int gridHeight) {
		if (gridHeight < 0) {
			throw new IllegalArgumentException("gridHeight must be a positive number");
		}
		this.gridHeight = gridHeight;
	}

	public boolean isSizeEditable() {
		return sizeEditable;
	}

	public void setSizeEditable(boolean sizeEditable) {
		this.sizeEditable = sizeEditable;
	}

	public int getTransparency() {
		return transparency;
	}

	public void setTransparency(int transparency) {
		this.transparency = transparency;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public Animation getCurrentAnimation() {
		return currentAnimation;
	}

	public void setCurrentAnimation(Animation currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public int getRoundedEdge() {
		return edge;
	}

	public void setRoundedEdge(int edge) {
		this.edge = edge;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public boolean isAutoLayout() {
		return autoLayout;
	}

	public void setAutoLayout(boolean layout) {
		this.autoLayout = layout;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Frame getDisplayWindow() {
		return displayWindow;
	}

	public void setDisplayWindow(Frame displayWindow) {
		this.displayWindow = displayWindow;
	}

	public Rectangle getLayoutBounds() {
		return layoutBounds;
	}

	public void setLayoutBounds(Rectangle layoutBounds) {
		this.layoutBounds = layoutBounds;
	}

}
