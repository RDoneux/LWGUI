package constraints;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import comp.GUIComponent;
import tools.Maths;

public class PercentLayout extends Layout {

	private Tile[][] tiles;

	private int rows;
	private int columns;

	private boolean setUp = false;

	public PercentLayout(int gridWidth, int gridHeight) {
		this.columns = gridWidth;
		this.rows = gridHeight;
	}

	@Override
	public void updateLayout() {

		if (topLevelContainer != null) {
			if (!setUp) {
				init(topLevelContainer.getBounds());
			}
			layout(topLevelContainer.getBounds(), topLevelContainer.getChildren());
		} else {
			if (!setUp) {
				init(container.getBounds());
			}
			layout(container.getBounds(), container.getChildren());
		}

		setUp = true;

	}

	private void init(Rectangle bounds) {

		tiles = new Tile[columns][rows];

	}

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

			int gridx = child.getGridx();
			int gridy = child.getGridy();

			child.setLocation(tiles[gridx][gridy].x, tiles[gridx][gridy].y);

			int percentWidth = (int) Maths.round(child.getWeightX() * 100, 2) * tiles[gridx][gridy].width / 100;
			int percentHeight = (int) Maths.round(child.getWeightY() * 100, 2) * tiles[gridx][gridy].height / 100;
			
			child.setWidth(percentWidth);
			child.setHeight(percentHeight);

		}

	}

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

	}

}
