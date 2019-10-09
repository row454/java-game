package com.pixelandbitgames.entities.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.pixelandbitgames.entities.Entity;
import com.pixelandbitgames.entities.projectiles.Projectile;
import com.pixelandbitgames.entities.projectiles.Snowball;
import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.inventory.Inventory;

public class Player extends Creature {

	private Rectangle ar;
	private int damage = 5;
	private Inventory inventory;
	private int projectileCooldown = 0, meleeCooldown = 0;
	public Player(Handler handler, int x, int y) {
		super(handler, x, y);
		bounds.y = 24;
		bounds.height = 40;
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		getInput();
		move();
		if (projectileCooldown == 0)
			fireSnowball();
		//checkMeleeAttacks();
		handler.getCamera().centerOnEntity(this);
		if (meleeCooldown != 0) {
			meleeCooldown--;
		}
		if (projectileCooldown != 0) {
			projectileCooldown--;
		}
		inventory.tick();
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}
	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getRoom().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e instanceof Projectile)
				if(((Projectile) e).getCreator().equals(this))
					continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
			
		}
		return false;
	}
	private void checkMeleeAttacks() {
		Rectangle cb = getCollisionBounds(0, 0);
		/* Rectangle */ ar = new Rectangle();
		
		if (handler.getKeyManager().upFire) {
			ar.x = cb.x + cb.width / 2 - 12 / 2;
			ar.y = cb.y - 60;
			ar.setSize(12, 60);
		} else if (handler.getKeyManager().downFire) {
			ar.x = cb.x + cb.width / 2 - 12 / 2;
			ar.y = cb.y + cb.height;
			ar.setSize(12, 60);
		} else if (handler.getKeyManager().leftFire) {
			ar.y = cb.y + cb.height / 2 - 12 / 2;
			ar.x = cb.x - 60;
			ar.setSize(60, 12);
			
		} else if (handler.getKeyManager().rightFire) {
			ar.y = cb.y + cb.height / 2 - 12 / 2;
			ar.x = cb.x + cb.width;
			ar.setSize(60, 12);
		} else {
			return;
		}
		meleeCooldown = 60;
	}
	private void fireSnowball() {
		Rectangle cb = getCollisionBounds(0, 0);
		int x;
		int y;
		int direction;
		ArrayList<Integer> creatorDirections = new ArrayList<Integer>();
		
		if (handler.getKeyManager().upFire) {
			x = cb.x - cb.width / 2 + 16;
			y = cb.y - 64;
			direction = Projectile.UP;
		} else if (handler.getKeyManager().downFire) {
			x = cb.x - cb.width / 2 + 16;
			y = cb.y + cb.height;
			direction = Projectile.DOWN;
		} else if (handler.getKeyManager().leftFire) {
			y = cb.y - cb.height / 2 - 16;
			x = cb.x - 64;
			direction = Projectile.LEFT;
		} else if (handler.getKeyManager().rightFire) {
			y = cb.y - cb.height / 2 - 16;
			x = cb.x + cb.width;
			direction = Projectile.RIGHT;
		} else {
			return;
		}
		
		
		if(handler.getKeyManager().down)
			creatorDirections.add(Projectile.DOWN);
		else if(handler.getKeyManager().up)
			creatorDirections.add(Projectile.UP);
		if(handler.getKeyManager().right)
			creatorDirections.add(Projectile.RIGHT);
		else if(handler.getKeyManager().left)
			creatorDirections.add(Projectile.LEFT);
		
		
		handler.getRoom().getEntityManager().addEntity(new Snowball(handler, x, y, direction, creatorDirections, this, damage));
		projectileCooldown = 180;
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(Handler.getAsset(0), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
		inventory.render(g);
	}
	public void postRender(Graphics g) {
		inventory.render(g);
	}

	@Override
	public void die() {
		active = false;
		
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
