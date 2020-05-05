package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import comp.Frame;
import comp.GUIComponent;

public class TestGame extends GUIComponent {

	private int ballX = 0;
	private int ballY = 0;
	private int ballVelX = 5;
	private int ballVelY = 5;
	
	public TestGame(Frame frame) {
		setSizeEditable(true);
	}

	@Override
	public void revise() {

		ballX += ballVelX;
		ballY += ballVelY;
		
		if(ballX < 0) {
			ballVelX *= -1;
		}
		if(ballY < 0) {
			ballVelY *= -1;
		}
		if(ballX > width - 5) {
			ballVelX *= -1;
		}
		if(ballY > height - 5) {
			ballVelY *= -1;
		}
		
	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.WHITE);
		g.fillOval(ballX, ballY, 5, 5);
			
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
