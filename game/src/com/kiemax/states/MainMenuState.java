package com.kiemax.states;

import java.awt.Color;
import java.awt.Graphics;

import com.kiemax.game.Handler;
import com.kiemax.game.Launcher;
import com.kiemax.gfx.Assets;
import com.kiemax.ui.ClickListener;
import com.kiemax.ui.UIImageButton;
import com.kiemax.ui.UIManager;

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
