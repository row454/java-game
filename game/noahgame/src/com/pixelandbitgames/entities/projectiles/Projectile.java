package com.pixelandbitgames.entities.projectiles;

import java.util.ArrayList;

import com.pixelandbitgames.entities.Entity;
import com.pixelandbitgames.entities.creature.Creature;
import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.tiles.Tile;

public abstract class Projectile extends Entity {
	public static final float DEFAULT_SPEED = 5.0f;
	public static final int DEFAULT_PROJECTILE_WIDTH = 32,
							DEFAULT_PROJECTILE_HEIGHT = 32;
	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	public static final int DEFAULT_DAMAGE = 1;
	protected int damage;
	protected float speed;
	protected float xMove, yMove;
	protected boolean dynamic = false;
	protected int direction;
	protected ArrayList<Integer> creatorDirection = new ArrayList<Integer>();
	protected Creature creator;
	public Projectile(Handler handler, float x, float y, int direction, ArrayList<Integer> creatorDirection, Creature creator) {
		super(handler, x, y, DEFAULT_PROJECTILE_WIDTH, DEFAULT_PROJECTILE_HEIGHT);
		this.creator = creator;
		speed = DEFAULT_SPEED;
		damage = DEFAULT_DAMAGE;
		xMove = 0;
		yMove = 0;
		if (!(direction < 0 || direction > 3))
			this.direction = direction;
		else {
			Exception e = new Exception();
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.exit(1);
		}
		for (int i = 0;i<creatorDirection.size();i++) {
			if (!(creatorDirection.get(i) < 0 || creatorDirection.get(i) > 3))
				this.creatorDirection.add(creatorDirection.get(i));
			else {
				Exception e = new Exception();
				try {
					throw e;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	@Override
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getRoom().getEntityManager().getEntities()) {
			if(e.equals(this) || e.equals(creator)) {
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
			
		}
		return false;
	}
	public void move() {
		if (direction == UP)
			yMove = -speed;
		if (direction == DOWN)
			yMove = speed;
		if (direction == LEFT)
			xMove = -speed;
		if (direction == RIGHT)
			xMove = speed;
		if (dynamic) { 
			if (creatorDirection.contains(UP) && direction != DOWN)
				yMove += -speed/3;
			if (creatorDirection.contains(DOWN) && direction != UP)
				yMove += speed/3;
			if (creatorDirection.contains(LEFT) && direction != RIGHT)
				xMove += -speed/3;
			if (creatorDirection.contains(RIGHT) && direction != LEFT)
				xMove += speed/3;
		}
		if (!checkEntityCollisions(xMove, 0f)) {
			moveX();
		} else if (getEntityHit(xMove, 0f) != null) {
			if (getEntityHit(xMove, 0f) instanceof Creature) {
				((Creature) getEntityHit(xMove, 0f)).hurt(damage);
				active = false;
				return;
			} else {
				active = false;
				return;
			}
		}
		
		if (!checkEntityCollisions(0f, yMove)) {
			moveY();
		} else if (getEntityHit(0f, yMove) != null) {
			if (getEntityHit(0f, yMove) instanceof Creature) {
				((Creature) getEntityHit(0f, yMove)).hurt(damage);
				active = false;
				return;
			} else {
				active = false;
				return;
			}
		}
		yMove = 0;
		xMove = 0;
	}
	public Entity getEntityHit(float xOffset, float yOffset) {
		for(Entity e : handler.getRoom().getEntityManager().getEntities()) {
			if(e.equals(this) || e.equals(creator)) {
				continue;
			}
			if(e instanceof Projectile)
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return e;
			}
			
		}
		return null;
	}
	
	public Creature getCreator() {
		return creator;
	}

	public void moveX(){
		if(xMove > 0) { //Moving right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				active = false;
			}
		} else if(xMove < 0) { //Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				active = false;
				return;
			}
		}
	}
	
	public void moveY(){
		if(yMove < 0){//Up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
				y += yMove;
			} else {
				active = false;
				return;
			}
		}else if(yMove > 0){//Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
				y += yMove;
			} else {
				active = false;
				return;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getRoom().getTile(x, y).isSolid();
			
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getxMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float getyMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

}
