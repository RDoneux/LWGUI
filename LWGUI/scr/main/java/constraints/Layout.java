package constraints;

import comp.Container;
import comp.Frame;
import comp.GUIComponent.alignment;

public abstract class Layout {

	protected Container container;
	protected Frame topLevelContainer;
	
	protected alignment alignmentX;
	protected alignment alignmentY;
	
	public abstract void updateLayout();

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}
	
	public void setTopLevelContainer(Frame frame) {
		this.topLevelContainer = frame;
	}

}
