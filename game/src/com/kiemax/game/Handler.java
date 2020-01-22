package com.kiemax.game;

import java.awt.image.BufferedImage;

import com.kiemax.gfx.Camera;
import com.kiemax.gfx.PreLoaderImage;
import com.kiemax.input.KeyManager;
import com.kiemax.input.MouseManager;
import com.kiemax.rooms.Room;
import com.kiemax.util.collisions.QuadTree;

public class Handler {
	
	private Game game;
	private Room room;
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public Handler(Game game) {
		this.game = game;
	}
	public QuadTree getQuadTree() {
		return game.getQuadTree();
	}
	public static BufferedImage getAsset(int index) {
		return PreLoaderImage.getAssets().get(index);
	}
	
	public Camera getCamera() {
		return game.getCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public int getWidth() {
		return game.getWidth();
			
	}
	
	public int getHeight() {
		return game.getHeight();
			
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
