package com.pixelandbitgames.entities.statics;

import com.pixelandbitgames.entities.Entity;
import com.pixelandbitgames.game.Handler;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
}
