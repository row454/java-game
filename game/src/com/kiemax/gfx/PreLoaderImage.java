package com.kiemax.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PreLoaderImage {
	private static final int size = 16;
	private static final int buttonX = 32;
	private static final int sheetSide = size * 20;
	public static BufferedImage inventory;
	public static Font font28;
	private static ArrayList<BufferedImage> assets = new ArrayList<>();




		public static void init(){
			font28 = FontLoader.loadFont("/fonts/PolygonPixel5x7Standard.ttf", 28);
			
			inventory = ImageLoader.loadImage("/textures/inventory_screen.png");
	        SpriteSheet entities = new SpriteSheet(ImageLoader.loadImage("/textures/sheet_entities.png"));
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / size; x++) {
	                assets.add(entities.crop(x * size, y * size, false));
	            }
	        }
	        SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/textures/sheet_tiles.png"));
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / size; x++) {
	                assets.add(tiles.crop(x * size, y * size, false));
	            }
	        }
	        SpriteSheet staticEnt = new SpriteSheet(ImageLoader.loadImage("/textures/sheet_static_entities.png"));
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / size; x++) {
	                assets.add(staticEnt.crop(x * size, y * size, false));
	            }
	        }
	        SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("/textures/sheet_buttons.png"));
	        
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / buttonX; x++) {
	                assets.add(buttons.crop(x * buttonX, y * size, true));
	            }
	        }
	        SpriteSheet items = new SpriteSheet(ImageLoader.loadImage("/textures/sheet_items.png"));
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / size; x++) {
	                assets.add(items.crop(x * size, y * size, false));
	            }
	        }
	        System.out.println(PreLoaderImage.getAssets().size());
	    }

	    public static ArrayList<BufferedImage> getAssets() {
	        return assets;
	    }
	    
}
