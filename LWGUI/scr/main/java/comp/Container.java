package comp;

import java.util.ArrayList;

import constraints.Layout;

public abstract class Container extends GUIComponent {

	/**
	 * Abstract class for all Container objects. This differentiates a
	 * {@link GUIComponent} as being either a container or {@link Component}. Containers can
	 * have layout managers and can contain children GUIComponents, components
	 * cannot.
	 * 
	 * @author Robert Doneux
	 * @version 0.1
	 */

	protected Layout layout;
	protected ArrayList<GUIComponent> children = new ArrayList<>();

	public void add(GUIComponent child) {
		child.setParent(this);
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

	public ArrayList<GUIComponent> getChildren() {
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

}
