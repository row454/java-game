package com.kiemax.tiles;

import com.kiemax.game.Handler;

public class TopLeftCornerWallTile extends Tile {

	public TopLeftCornerWallTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
