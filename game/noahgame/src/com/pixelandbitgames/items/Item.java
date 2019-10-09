package com.pixelandbitgames.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.gfx.PreLoaderImage;

public class Item {
	
	public static Item[] items = new Item[256];
	public static Item darkOrb = new Item(PreLoaderImage.getAssets().get(1400), "Dark Orb", 0, "+1 damage");
	public static final int ITEMWIDTH = 48, ITEMHEIGHT = 48;
	protected Handler handler;
	protected Rectangle bounds;
	protected BufferedImage texture;
	protected String name;
	protected final int ID;
	protected int x, y;
	
	protected int count;
	protected String description;
	protected boolean pickedUp;
	
	public Item(BufferedImage texture, String name, int id, String description) {
		this.ID = id;
		this.name = name;
		this.texture = texture;
		this.description = description;
		bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
		items[id] = this;
		
		count = 1;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void tick() {
		if (handler.getRoom().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
			pickedUp = true;
			handler.getRoom().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}
	public void render(Graphics g, int x , int y) {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	public void render(Graphics g) {
		if (handler == null)
			return;
		render(g, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()));
	}
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, ID, description);
		i.setPosition(x, y);
		return i;
	}
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	public BufferedImage getTexture() {
		return texture;
	}
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getID() {
		return ID;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
