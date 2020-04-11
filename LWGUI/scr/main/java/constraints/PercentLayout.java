package constraints;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import comp.GUIComponent;
import comp.GUIComponent.alignment;
import tools.Maths;

/**
 * Basic grid based layout manager. Creates a grid of {@link Tile}, splitting
 * the width and height of the parent window evenly. The user specifies the
 * width and height of the grid (and thus the size of the {@link Tile} grid)
 * within the constructor.
 * 
 * @author Robert Doneux
 * 
 * @version 0.1
 */

public class PercentLayout extends Layout {

	private Tile[][] tiles;

	private int rows;
	private int columns;

	public PercentLayout(int gridWidth, int gridHeight) {
		this.columns = gridWidth;
		this.rows = gridHeight;
		tiles = new Tile[columns][rows];
	}

	/**
	 * inherited method that identifies which type of container the layout manager
	 * has been added to, {@link Container} or {@link Frame}
	 */
	@Override
	public void updateLayout() {

		if (topLevelContainer != null) {
			layout(topLevelContainer.getBounds(), topLevelContainer.getChildren());
		} else {
			layout(container.getBounds(), container.getChildren());
		}

	}

	/**
	 * 
	 * completes the layout of each {@link GUIComponent} from the parent
	 * {@link Container}
	 * 
	 * @param bounds
	 * @param children
	 */
	private void layout(Rectangle bounds, ArrayList<GUIComponent> children) {

		// set up-to-date tile locations and sizes
		int setWidth = (int) bounds.getWidth() / columns;
		int setHeight = (int) bounds.getHeight() / rows;
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				tiles[i][j] = new Tile(setWidth, setHeight);
				tiles[i][j].setLocation(
						new Point((int) bounds.getX() + (setWidth * i), (int) bounds.getY() + (setHeight * j)));
			}
		}

		// find the highest x and y grid locations of all the children
		for (GUIComponent child : children) {

			// ensure that the child's grid value is within the managers grid range
			if (child.getGridx() >= columns) {
				throw new IllegalArgumentException("GUIComponent: " + child.getName()
						+ " has a gridX value outside of Layout Managers range. Current range = " + columns
						+ " Current value = " + child.getGridx());
			}
			if (child.getGridy() >= rows) {
				throw new IllegalArgumentException("GUIComponent: " + child.getName()
						+ " has a gridY value outside of Layout Managers range. Current range = " + rows
						+ " Current value = " + child.getGridy());
			}

			Tile targetTile = tiles[child.getGridx()][child.getGridy()];

			// calculate the size of the component based upon its weight and grid width /
			// height
			int percentWidth = (int) Maths.round(child.getWeightX() * 100, 2)
					* (targetTile.width * child.getGridWidth()) / 100;
			int percentHeight = (int) Maths.round(child.getWeightY() * 100, 2)
					* (targetTile.height * child.getGridHeight()) / 100;

			child.setWidth(percentWidth);
			child.setHeight(percentHeight);

			// set the alignment of the child component within the window frame. This will
			// only have an effect if the child component is smaller than the window frame
			if (child.getAlignmentX() == alignment.WEST) {
				child.setX(targetTile.x);
			} else if (child.getAlignmentX() == alignment.CENTRE) {
				child.setX(targetTile.x + (targetTile.width * child.getGridWidth() / 2) - (child.getWidth() / 2));
			} else if (child.getAlignmentX() == alignment.EAST) {
				child.setX(targetTile.x + targetTile.width * child.getGridWidth() - child.getWidth());
			}

			if (child.getAlignmentY() == alignment.NORTH) {
				child.setY(targetTile.y);
			} else if (child.getAlignmentY() == alignment.CENTRE) {
				child.setY(targetTile.y + (targetTile.height * child.getGridHeight() / 2) - (child.getHeight() / 2));
			} else if (child.getAlignmentY() == alignment.SOUTH) {
				child.setY(targetTile.y - child.getHeight() * child.getGridHeight());
			}

		}

	}

	/**
	 * Shows the tile grid to the user for debugging purposes. This should never be
	 * directly called from this class. The container class has a debugging boolean
	 * which, when set to true will call this method within it's paint method
	 */
	@Override
	public void debug(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i < columns; i++) {
			for (int h = 0; h < rows; h++) {
				tiles[i][h].showGrid(g);
			}
		}
	}

	/**
	 * enables the user to update the {@link Tile} width and height after the layout
	 * manager has been created and added to a {@link Container} or {@link Frame}
	 * 
	 * @param x
	 * @param y
	 */
	public void updateGrid(int width, int height) {
		columns = width;
		rows = height;
		tiles = new Tile[columns][rows];
	}

	/**
	 * holds an x, y, width and height value which the {@link PercentLayout} layout
	 * manager uses to split the screen evenly and layout the attached
	 * {@link Container}'s or {@link Frame}'s children {@link GUIComponent}s
	 * 
	 * @author Rober
	 *
	 */
	private class Tile {

		private int x;
		private int y;
		private int width;
		private int height;

		private Tile(int width, int height) {
			this.width = width;
			this.height = height;
		}

		private void setLocation(Point p) {
			x = (int) p.getX();
			y = (int) p.getY();
		}

		private void showGrid(Graphics g) {
			g.drawRect(x, y, width, height);
		}
	}
}