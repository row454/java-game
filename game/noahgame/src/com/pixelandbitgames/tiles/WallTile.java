package com.pixelandbitgames.tiles;

import com.pixelandbitgames.game.Handler;

public class WallTile extends Tile {

	public WallTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
