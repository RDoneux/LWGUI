package comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class Panel extends Container {

	private Color background;

	public Panel() {
		setName("Panel");
		background = Color.LIGHT_GRAY;
		sizeEditable = true;
	}

	@Override
	public void revise() {

		for (GUIComponent child : children) {
			child.revise();
		}

		if (layout != null) {
			layout.updateLayout();
		}

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(new Color(background.getRed(), background.getGreen(), background.getBlue(), transparency));
		g.fillRect(x, y, width, height);

		for (GUIComponent child : children) {
			child.paint(g);
		}

		if (layout != null) {
			if (layout.isDebugging()) {
				layout.debug(g);
			}
		}

	}

	public void setBackground(Color colour) {
		this.background = colour;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseDragged(arg0);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		for (GUIComponent child : children) {
			child.mouseMoved(arg0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		for (GUIComponent child : children) {
			child.mouseClicked(arg0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseEntered(arg0);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		for (GUIComponent child : children) {
			child.mouseExited(arg0);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		for (GUIComponent child : children) {
			child.mousePressed(arg0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseReleased(arg0);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		for (GUIComponent child : children) {
			child.keyPressed(arg0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		for (GUIComponent child : children) {
			child.keyReleased(arg0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		for (GUIComponent child : children) {
			child.keyTyped(arg0);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		for (GUIComponent child : children) {
			if (child.getBounds().contains(arg0.getPoint())) {
				child.mouseWheelMoved(arg0);
			}
		}
	}
}
