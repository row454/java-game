package com.pixelandbitgames.tiles;

import com.pixelandbitgames.game.Handler;

public class BottomRightCornerWallTile extends Tile {

	public BottomRightCornerWallTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
