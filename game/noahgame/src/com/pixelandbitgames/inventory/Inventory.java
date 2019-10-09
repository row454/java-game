package com.pixelandbitgames.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.gfx.Assets;
import com.pixelandbitgames.items.Item;
public class Inventory {
	
	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	private BufferedImage texture = Assets.inventory;
	private int selectedItem = 0 , len;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		
		inventoryItems = new ArrayList<Item>();
	}
	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = !active;
		if(!active)
			return;
		len = inventoryItems.size() + 1;
		System.out.println("INVENTORY:");
		
		for(Item i : inventoryItems) { 
			System.out.println(i.getName() + "   " + i.getCount() + "\r\n" + i.getDescription());
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) && selectedItem > 3) {
			selectedItem -= 4;
			
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S) && selectedItem  < len - 4) {
			selectedItem += 4;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A) && selectedItem != 0) {
			selectedItem -= 1;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_D) && selectedItem < len - 2) {
			selectedItem += 1;
		}
		if(selectedItem >= len)
			selectedItem = len-1;
		if(selectedItem == 15 || selectedItem == 11)
			selectedItem += 1;
	}
	public void render(Graphics g) {
		if(!active)
			return;
		g.drawImage(texture, handler.getWidth()/2 - 8 * texture.getWidth()/2, handler.getHeight()/2 - 8 * texture.getHeight()/2, 8 * texture.getWidth(), 8 * texture.getHeight(), null);
		
		if (len == 0)
			return;
		int spacingX = 12;
		int spacingY = 12;
		int offset = 0;
		for(int y = 0 ; y < 6 ; y++) {
			for(int x = 0 ; x < 4 ; x++) {
				
				if(len <= x+4*y - offset || x+4*y == 24) {
					
					return;
				}
				if(x+4*y == 15 || x+4*y == 11) {
					offset++;
					continue;
				}
				System.out.println(selectedItem);
				g.setColor(Color.white);
				g.drawRect(handler.getWidth()/2 - 8 * texture.getWidth()/2 + ((selectedItem % 4)+1)*(48+spacingX)-48, handler.getHeight()/2 - 8 * texture.getHeight()/2 + ((int)Math.floor(selectedItem/4)+1)*(48+spacingY)-48, 54, 54);
				
				g.drawImage(inventoryItems.get(x+ 4*y - offset).getTexture(), handler.getWidth()/2 - 8 * texture.getWidth()/2 + x*(48+spacingX)+15, handler.getHeight()/2 - 8 * texture.getHeight()/2 + y*(48+spacingY)+15, 48, 48, null);
			}
		}
		
		
	}
	
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			//if(i.getID() == item.getID()) {
			//	i.setCount(i.getCount() + item.getCount());
			//	return;
			//}
		}
		inventoryItems.add(item);
	}
	
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
