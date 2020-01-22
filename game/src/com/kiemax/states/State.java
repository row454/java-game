package com.kiemax.states;

import java.awt.Graphics;

import com.kiemax.game.Handler;

public abstract class State {
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
}
