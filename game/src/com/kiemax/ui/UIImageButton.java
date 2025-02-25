package com.kiemax.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

	private BufferedImage[] images;
	private ClickListener clicker;
	

	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if (held)
			g.drawImage(images[2], (int) x, (int) y, width, height, null);
		else if (hovering)
				g.drawImage(images[1], (int) x, (int) y, width, height, null);
		else 
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
		
	}

	@Override
	public void onClick() {
		clicker.onClick();
		
	}

	@Override
	public void onHeld() {
		held = true;
		
	}
	public void notHeld() {
		held = false;
		
	}

}
