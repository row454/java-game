package com.pixelandbitgames.game;

import java.awt.image.BufferedImage;

import com.pixelandbitgames.entities.EntityManager;
import com.pixelandbitgames.gfx.Camera;
import com.pixelandbitgames.gfx.PreLoaderImage;
import com.pixelandbitgames.input.KeyManager;
import com.pixelandbitgames.input.MouseManager;
import com.pixelandbitgames.rooms.Room;
import com.pixelandbitgames.util.audio.MusicManager;
import com.pixelandbitgames.util.collisions.QuadTree;

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
