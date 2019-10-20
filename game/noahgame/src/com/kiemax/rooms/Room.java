package com.kiemax.rooms;

import java.awt.Graphics;

import com.kiemax.entities.EntityManager;
import com.kiemax.entities.creature.Player;
import com.kiemax.entities.creature.Zombie;
import com.kiemax.entities.projectiles.Snowball;
import com.kiemax.entities.statics.Bush;
import com.kiemax.game.Handler;
import com.kiemax.items.ItemManager;
import com.kiemax.tiles.Tile;
import com.kiemax.util.Utils;

public class Room {
	private int [][] tiles;
	private Handler handler;
	private int width, height;
	private static int spawnX;
	private static int spawnY;
	
	public EntityManager entityManager;
	
	public ItemManager itemManager;
	
	public static int getSpawnX() {
		return spawnX;
	}

	public static int getSpawnY() {
		return spawnY;
	}




	
	

	public Room(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		entityManager.addEntity(new Bush(handler, 32, 32));
		entityManager.addEntity(new Bush(handler, 500, 400));
		entityManager.addEntity(new Zombie(handler, 500, 200));
		entityManager.addEntity(new Zombie(handler, 500, 300));
		loadWorld(path);
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
	}
	
	public void tick() {
		itemManager.tick();
		entityManager.tick();
		
	}
	
	public void render(Graphics g) {
		
		
		int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getCamera().getyOffset()));
			}
			
		}
		itemManager.render(g);
		entityManager.render(g);
		
	}
	public ItemManager getItemManager() {
		return itemManager;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = 32 * Utils.parseInt(tokens[2]);
		spawnY = 32 * Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++) {
			for(int x = 0;x < width;x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
		
	
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.floorTile;
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.floorTile;
		return t;
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
