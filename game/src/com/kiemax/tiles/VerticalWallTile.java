package com.kiemax.tiles;

import com.kiemax.game.Handler;

public class VerticalWallTile extends Tile {

	public VerticalWallTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
