package constraints;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;

import comp.Frame;
import comp.GUIComponent;
import comp.GUIComponent.alignment;

/**
 * lays out the components of the parent {@link GUIComponent} or {@link Frame}
 * in a horizontal arrangement. The layout takes the individual component's
 * weight and uses it to create a percentage of coverage within the parent
 * container.
 * 
 * @author Robert Doneux
 * @version 1.0
 */

public class WindowLayout extends Layout {

	private ArrayList<Window> tiles = new ArrayList<>();
	private Window grid[][];

	int rows = 0;
	int columns = 0;

	@Override
	public void updateLayout() {
		/**
		 * checks to see if the layout has been added to a top level container
		 * {@link Frame} or a normal container. The code is the same for both
		 */
		if (topLevelContainer != null) {
			layoutComponents(topLevelContainer.getChildren(), topLevelContainer.getFrameBounds());
		} else {
			layoutComponents(container.getChildren(), container.getBounds());
		}
	}

	public void add(GUIComponent comp, WindowConstraint cont) {
		Window window;
		for (Window tile : tiles) {
			if (tile.indexX == cont.getIndexX() && tile.indexY == cont.getIndexY()) {
				tile.children.add(comp);
				// add other specific elements here
				return;
			}
		}
		window = new Window();
		window.indexX = cont.getIndexX();
		window.indexY = cont.getIndexY();
		window.children.add(comp);
		tiles.add(window);

		for (int i = 0; i < tiles.size(); i++) {
			Window tile = tiles.get(i);

			if (tile.indexX > rows) {
				rows = tile.indexX;
			}

			if (tile.indexY > columns) {
				columns = tile.indexY;
			}
		}
		
		grid = new Window[columns][rows];
		System.out.println("rows: " + rows + " ~ columns: " + columns);
	}

	private void layoutComponents(ArrayList<GUIComponent> children, Rectangle bounds) {

		for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				
				
				
			}
		}

	}

	public class Window {

		private int x;
		private int y;
		private int indexX;
		private int indexY;
		private ArrayList<GUIComponent> children = new ArrayList<>();

	}

}
