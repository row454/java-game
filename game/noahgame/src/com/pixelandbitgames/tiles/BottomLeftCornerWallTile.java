package com.pixelandbitgames.tiles;

import com.pixelandbitgames.game.Handler;

public class BottomLeftCornerWallTile extends Tile {

	public BottomLeftCornerWallTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
