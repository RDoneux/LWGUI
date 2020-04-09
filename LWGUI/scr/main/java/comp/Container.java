package comp;

import java.util.ArrayList;

import constraints.Layout;
import constraints.WindowConstraint;
import constraints.WindowLayout;

public abstract class Container extends GUIComponent {

	protected Layout layout;
	protected ArrayList<GUIComponent> children = new ArrayList<>();

	public void add(GUIComponent child) {
		child.setParent(this);
		children.add(child);
	}

	public void add(GUIComponent child, WindowConstraint w) {
		if (layout instanceof WindowLayout) {
			child.setParent(this);
			children.add(child);
			((WindowLayout) layout).add(child, w);
		} else {
			throw new IllegalArgumentException(
					"In order to use WindowConstraint, the containers layout must be set to WindowLayout. Current Layout: "
							+ layout);
		}

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

}
