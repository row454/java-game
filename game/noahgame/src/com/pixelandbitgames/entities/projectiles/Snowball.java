package com.pixelandbitgames.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.pixelandbitgames.entities.creature.Creature;
import com.pixelandbitgames.game.Handler;

public class Snowball extends Projectile {

	public Snowball(Handler handler, float x, float y, int direction, ArrayList<Integer> creatorDirections, Creature creator, int damage) {
		super(handler, x, y, direction, creatorDirections, creator);
		this.damage = damage;
		bounds.x = 2;
		bounds.width = 28;
		bounds.y = 2;
		bounds.height = 28;
		dynamic = true;
	}
	
	@Override
	public void tick() {
		move();
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Handler.getAsset(2), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
	}

}
