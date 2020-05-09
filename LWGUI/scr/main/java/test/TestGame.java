package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import comp.Frame;
import comp.GUIComponent;

public class TestGame extends GUIComponent {

	private double ballX = 10;
	private double ballY = 10;
	private double ballVelX = 1.5;
	private double ballVelY = 0.5;
	private double ballSize = 20;
	private double gravity = 0.5;

	public TestGame(Frame frame) {
		setSizeEditable(true);
	}

	@Override
	public void revise() {

		ballVelY -= gravity;

		ballX -= ballVelX;
		ballY -= ballVelY;
		
		
		//ballY -= gravity;

		if (ballX < 0) {
			ballVelX *= -1;
		}
		if (ballY < 0) {
			ballVelY *= -1;
		}
		if (ballX > width - ballSize) {
			ballVelX *= -1;
		}
		if (ballY > height - ballSize) {
			ballY -= gravity;
			ballVelY *= -1;
		}

	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);

		g.setColor(Color.BLUE);
		g.fillOval((int) ballX, (int) ballY, (int) ballSize, (int) ballSize);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

	}
}
