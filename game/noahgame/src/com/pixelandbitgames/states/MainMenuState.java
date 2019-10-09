package com.pixelandbitgames.states;

import java.awt.Color;
import java.awt.Graphics;

import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.game.Launcher;
import com.pixelandbitgames.gfx.Assets;
import com.pixelandbitgames.ui.ClickListener;
import com.pixelandbitgames.ui.UIImageButton;
import com.pixelandbitgames.ui.UIManager;

public class MainMenuState extends State {
	
	private UIManager uiManager;

	public MainMenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 200, 64, 32, Assets.getStart(), new ClickListener(){

			@Override
			public void onClick() {
				StateManager.setState(handler.getGame().gameState);
				Launcher.musicManager.setSong(2);
				Launcher.sem.play(0);
				System.out.println("started");
				
			}
			
		}));
	}
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());
		uiManager.render(g);
	}


}
