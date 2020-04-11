package constraints;

import java.awt.Graphics;

import comp.Container;
import comp.Frame;
import comp.GUIComponent.alignment;

public abstract class Layout {

	/**
	 * Abstract class for all Layouts. Contains abstract methods, 'updateLayout()'
	 * and 'debug();
	 * 
	 * @param container
	 * @param frame
	 * 
	 * @author Robert Doneux
	 * @version 0.1
	 */

	protected Container container;
	protected Frame topLevelContainer;

	protected alignment alignmentX;
	protected alignment alignmentY;

	protected boolean debugging;

	public abstract void updateLayout();

	public abstract void debug(Graphics g);

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public void setTopLevelContainer(Frame frame) {
		this.topLevelContainer = frame;
	}

	public Boolean isDebugging() {
		return debugging;
	}

	public void setDebugging(boolean debugging) {
		this.debugging = debugging;
	}

}
