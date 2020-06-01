package comp;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import constraints.Layout;

public abstract class Container extends GUIComponent {

	/**
	 * Abstract class for all Container objects. This differentiates a
	 * {@link GUIComponent} as being either a container or {@link Component}.
	 * Containers can have layout managers and can contain children GUIComponents,
	 * components cannot.
	 * 
	 * @author Robert Doneux
	 * @version 0.1
	 */

	protected Layout layout;
	protected volatile CopyOnWriteArrayList<GUIComponent> children = new CopyOnWriteArrayList<>();

	public synchronized void add(GUIComponent child) {
		if (layout == null) {
			System.err.print("Container component: " + name + " does not have a valid layout.");
		}
		child.setParent(this);
		// the animation constraints are only really relevant if the container has been
		// offset at the point of adding children
		child.setAnimationX(animationX);
		child.setAnimationY(animationY);
		children.add(child);
	}

	public GUIComponent getChild(int i) {
		return children.get(i);
	}

	public GUIComponent getChildByID(int id) {
		for (GUIComponent child : children) {
			if (child.id == id) {
				return child;
			}
		}
		return null;
	}

	public CopyOnWriteArrayList<GUIComponent> getChildren() {
		return children;
	}

	public void setLayout(Layout layout) {
		layout.setContainer(this);
		this.layout = layout;
	}

	public void debugLayout() {
		if (layout != null) {
			layout.setDebugging(true);
		} else {
			throw new NullPointerException(name + " does not have a specified layout");
		}
	}

	/**
	 * removes all children from the container. This method should be used to avoid
	 * ConcurrentModificationException
	 */
	public synchronized void removeAllChildren() {

		CopyOnWriteArrayList<GUIComponent> toRemove = new CopyOnWriteArrayList<>();
		for (GUIComponent child : children) {
			toRemove.add(child);
		}
		children.removeAll(toRemove);

	}

	@Override
	public void setAnimationX(int x) {
		for (Iterator<GUIComponent> iterator = children.iterator(); iterator.hasNext();) {
			GUIComponent child = iterator.next();
			child.setAnimationX(x);
		}
		this.animationX = x;
	}

	@Override
	public void setAnimationY(int y) {
		for (Iterator<GUIComponent> iterator = children.iterator(); iterator.hasNext();) {
			GUIComponent child = iterator.next();
			child.setAnimationY(y);
		}
		this.animationY = y;
	}

	@Override
	public void incrementAnimationX(int x) {
		for (Iterator<GUIComponent> iterator = children.iterator(); iterator.hasNext();) {
			GUIComponent child = iterator.next();
			child.incrementAnimationX(x);
		}
		this.animationX += x;
	}

	@Override
	public void incrementAnimationY(int y) {

		for (Iterator<GUIComponent> iterator = children.iterator(); iterator.hasNext();) {
			GUIComponent child = iterator.next();
			child.incrementAnimationY(y);
		}
		this.animationY += y;
	}

	@Override
	public void incrementAnimationWidth(int increment) {

		for (Iterator<GUIComponent> iterator = children.iterator(); iterator.hasNext();) {
			GUIComponent child = iterator.next();
			child.incrementAnimationWidth(increment);
		}
		this.animationY += increment;
	}

	@Override
	public void incrementAnimationHeight(int increment) {
		for (GUIComponent child : children) {
			child.incrementAnimationHeight(increment);
		}
		this.animationY += increment;
	}

	@Override
	public void setAnimationWidth(int width) {
		for (GUIComponent child : children) {
			child.animationWidth = width;
		}
		this.animationWidth = width;
	}

	@Override
	public void setAnimationHeight(int height) {
		for (GUIComponent child : children) {
			child.animationHeight = height;
		}
		this.animationHeight = height;
	}

}
