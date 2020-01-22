package com.kiemax.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.kiemax.game.Handler;
import com.kiemax.game.Launcher;
import com.kiemax.rooms.Room;

public class GameState extends State {
	
	private int roomId = 1;
	private Room room;
	public GameState(Handler handler) {
		super(handler);
		if (roomId == 1) {
			room = new Room(handler, "worlds/world1.rm");
		} else if (roomId == 2) {
			room = new Room(handler, "worlds/world2.rm");
		}
		handler.setRoom(room);
		

	}
	@Override
	public void tick() {
		room.tick();
		if (Launcher.musicManager.getIndex() == 0) {
			Launcher.musicManager.skip();
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			Launcher.musicManager.pause();
			StateManager.setState(handler.getGame().menuState);
		}
	}

	@Override
	public void render(Graphics g) {
		room.render(g);
	}
	
}
