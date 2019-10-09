package com.pixelandbitgames.entities.statics;

import java.awt.Graphics;

import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.tiles.Tile;

public class Bush extends StaticEntity{

	public Bush(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Handler.getAsset(800), (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
	}

}
