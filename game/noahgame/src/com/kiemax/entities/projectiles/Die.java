package com.kiemax.entities.projectiles;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import com.kiemax.entities.creature.Creature;
import com.kiemax.game.Handler;

public class Die extends Projectile {
	int roll;
	Random rand = new Random();
	public Die(Handler handler, float x, float y, int direction, ArrayList<Integer> creatorDirections, Creature creator, int lvl) {
		super(handler, x, y, direction, creatorDirections, creator);
		bounds.x = 2;
		bounds.width = 28;
		bounds.y = 2;
		bounds.height = 28;
		dynamic = true;
		if (lvl == 1) {
			roll = rand.nextInt(2) + 1;
		}
		if (lvl == 2) {
			roll = rand.nextInt(4) + 1;
		}
		if (lvl == 3) {
			roll = rand.nextInt(6) + 1;
		}
		if (lvl == 4) {
			roll = rand.nextInt(8) + 1;
		}
		if (lvl == 1) {
			roll = rand.nextInt(10) + 1;
		}
	}
	
	@Override
	public void tick() {
		move();
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Handler.getAsset(3), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
	}
}
