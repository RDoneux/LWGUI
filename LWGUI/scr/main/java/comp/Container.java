package comp;

import java.util.ArrayList;

import constraints.Layout;

public abstract class Container extends GUIComponent {

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

}
