package com.pixelandbitgames.gfx;

import com.pixelandbitgames.entities.Entity;
import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.tiles.Tile;

public class Camera {
	
	private Handler handler;
	private float xOffset, yOffset;
	
	public Camera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getWidth() / 2;
		checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	public void checkBlankSpace() {
		if(xOffset < 0) {
			xOffset = 0;
		} else if(xOffset > handler.getRoom().getWidth() *Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = handler.getRoom().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		if(yOffset < 0) {
			yOffset = 0;
		} else if(yOffset > handler.getRoom().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffset = handler.getRoom().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
		
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
}
