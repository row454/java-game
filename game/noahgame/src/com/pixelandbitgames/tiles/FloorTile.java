package com.pixelandbitgames.tiles;

import com.pixelandbitgames.game.Handler;

public class FloorTile extends Tile {

	public FloorTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
}
