package com.kiemax.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile horizontalWallTile = new HorizontalWallTile(0);
	public static Tile floorTile = new FloorTile(1);
	public static Tile verticalWallTile = new VerticalWallTile(2);
	public static Tile bottomLeftCornerWallTile = new BottomLeftCornerWallTile(5);
	public static Tile bottomRightCornerWallTile = new BottomRightCornerWallTile(6);
	public static Tile topRightCornerWallTile = new TopRightCornerWallTile(3);
	public static Tile topLeftCornerWallTile = new TopLeftCornerWallTile(4);
	public static Tile wallTile = new WallTile(7);
	
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}
}
