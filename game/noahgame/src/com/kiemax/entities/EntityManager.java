package com.kiemax.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

import com.kiemax.entities.creature.Player;
import com.kiemax.game.Handler;
import com.kiemax.tiles.Tile;
import com.kiemax.util.collisions.QuadTree;


public class EntityManager {
	
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	public ListIterator<Entity> it;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if (a.getY()+a.getHeight()<b.getY()+b.getHeight()) {
				return -1;
			}
			return 1;
		}
		
	};
	
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	public void tick() {
		it = entities.listIterator();

		while(it.hasNext()) {
			Entity e = it.next();
			
			e.tick();
			if (!e.active)
				it.remove();
		}
		entities.sort(renderSorter);
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	public void render(Graphics g) {
		for(Entity e : entities) {
			
			e.render(g);
		}
		player.postRender(g);
	}
	public void addEntity(Entity e) {
		e.setX(e.x+Tile.TILEWIDTH/2);
		e.setY(e.y+Tile.TILEHEIGHT/2);
		if (it != null)
			it.add(e);
		else
			entities.add(e);
	}
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
}
