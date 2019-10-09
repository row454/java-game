package com.pixelandbitgames.tiles;

import com.pixelandbitgames.game.Handler;

public class TopLeftCornerWallTile extends Tile {

	public TopLeftCornerWallTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
