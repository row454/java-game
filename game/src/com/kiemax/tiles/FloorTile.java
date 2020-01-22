package com.kiemax.tiles;

import com.kiemax.game.Handler;

public class FloorTile extends Tile {

	public FloorTile(int id) {
		super(Handler.getAsset(400 + id), id);
	}
}
