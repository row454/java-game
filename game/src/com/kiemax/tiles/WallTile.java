package com.kiemax.tiles;

import com.kiemax.game.Handler;

public class WallTile extends Tile {

	public WallTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
