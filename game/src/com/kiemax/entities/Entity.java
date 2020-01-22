package com.kiemax.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.kiemax.game.Handler;

public abstract class Entity {
	protected boolean active = true;
	protected float x, y;
	protected int width, height;
	protected Handler handler;
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		
		bounds = new Rectangle(0, 0, width - 1, height - 1);
	}
	public abstract void tick();
	
	public abstract void render(Graphics g);
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x+bounds.x+xOffset), (int) (y+bounds.y+yOffset), bounds.width, bounds.height);
		
	}
	public boolean checkEntityQuadCollisions(float xOffset, float yOffset) {
		handler.getGame().getQuadTree().clear();
		ArrayList<Entity> allObjects = new ArrayList();
		for (Entity e : handler.getRoom().getEntityManager().getEntities()) {
			allObjects.add(e);
		}
		for (int i = 0; i < allObjects.size(); i++) {
			handler.getGame().getQuadTree().insert((allObjects.get(i).bounds));
		}
		List returnObjects = new ArrayList();
		for (int i = 0; i <= allObjects.size(); i++) {
		  returnObjects.clear();
		  handler.getGame().getQuadTree().retrieve(returnObjects, this.bounds);
		 
		  for (int x = 0; x <= returnObjects.size(); x++) {
		    if(allObjects.get(x).equals(this)) {
				continue;
			}
			if(allObjects.get(x).getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
		  }
		  
		}
		return false;
	}
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getRoom().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
			
		}
		return false;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
