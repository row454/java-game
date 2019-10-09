package com.pixelandbitgames.tiles;

import com.pixelandbitgames.game.Handler;

public class VerticalWallTile extends Tile {

	public VerticalWallTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
