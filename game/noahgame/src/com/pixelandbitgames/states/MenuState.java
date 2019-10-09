package com.pixelandbitgames.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.game.Launcher;

public class MenuState extends State {
	public MenuState(Handler handler) {
		super(handler);
	}
	@Override
	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			StateManager.setState(handler.getGame().gameState);
			Launcher.musicManager.resume();
		}
	}

	@Override
	public void render(Graphics g) {
	}
}
