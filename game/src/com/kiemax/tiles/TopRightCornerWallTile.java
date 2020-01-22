package com.kiemax.tiles;

import com.kiemax.game.Handler;

public class TopRightCornerWallTile extends Tile {

	public TopRightCornerWallTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
