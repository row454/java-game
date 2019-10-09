package com.pixelandbitgames.entities.creature;

import java.awt.Graphics;

import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.items.Item;

public class Zombie extends Creature {
	private float speed = 1f;
	public Zombie(Handler handler, int x, int y) {
		super(handler, x, y);
		health = 10;
	}

	@Override
	public void tick() {
		moveToPlayer();
	}

	private void moveToPlayer() {
		if (handler.getRoom().entityManager.getPlayer().getX() > x)
			xMove = speed;
		if (handler.getRoom().entityManager.getPlayer().getX() < x)
			xMove = -speed;
		if (handler.getRoom().entityManager.getPlayer().getY() > y)
			yMove = speed;
		if (handler.getRoom().entityManager.getPlayer().getY() < y)
			yMove = -speed;
		move();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Handler.getAsset(1), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
		handler.getRoom().getItemManager().addItem(Item.darkOrb.createNew((int) x, (int) y));
	}
}
