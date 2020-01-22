package com.kiemax.entities.statics;

import com.kiemax.entities.Entity;
import com.kiemax.game.Handler;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
}
