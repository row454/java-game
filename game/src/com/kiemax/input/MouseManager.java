package com.kiemax.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.kiemax.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener{

	private boolean leftPressed, scrollPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	public MouseManager() {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null)
			uiManager.onMouseMove(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
			if (uiManager != null)
				uiManager.onMousePressed(e);
		} else if (e.getButton() == MouseEvent.BUTTON2)
			scrollPressed = true;
		else if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
			if (uiManager != null)
				uiManager.onMouseReleased(e);
		else if (e.getButton() == MouseEvent.BUTTON2)
			scrollPressed = false;
		else if (e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	// Getters
	public boolean isLeftPressed() {
		return leftPressed;
	}
	public boolean isScrollPressed() {
		return scrollPressed;
	}
	public boolean isRightPressed() {
		return rightPressed;
	}
	public int getMouseX() {
		return mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}

}
