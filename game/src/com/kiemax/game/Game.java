package com.kiemax.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;

import com.kiemax.display.Display;
import com.kiemax.gfx.Camera;
import com.kiemax.gfx.PreLoaderImage;
import com.kiemax.input.KeyManager;
import com.kiemax.input.MouseManager;
import com.kiemax.states.GameState;
import com.kiemax.states.MainMenuState;
import com.kiemax.states.MenuState;
import com.kiemax.states.State;
import com.kiemax.states.StateManager;
import com.kiemax.util.audio.MusicManager;
import com.kiemax.util.audio.SoundEffectManager;
import com.kiemax.util.collisions.QuadTree;

public class Game implements Runnable {
	
	private Display display;
	public int width, height;
	public Camera getCamera() {
		return camera;
	}

	private String title;
	
	private Boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public State gameState;
	public State menuState;
	public State mainMenuState;
	
	private QuadTree quadTree;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private volatile MusicManager musicManager = Launcher.musicManager;
	private volatile SoundEffectManager sem = Launcher.sem;
	
	


	private Camera camera;
	
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
		
		

	}
	
	
	int x = 0;
	
	
	private void init() {
		display = new Display(title, width, height);
		display.getJFrame().addKeyListener(keyManager);
		display.getJFrame().addMouseListener(mouseManager);
		display.getJFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		PreLoaderImage.init();
		handler = new Handler(this);
		camera = new Camera(handler, 0, 0);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		mainMenuState = new MainMenuState(handler);
		quadTree = new QuadTree(0, new Rectangle(0, 0, handler.getRoom().getWidth(), handler.getRoom().getHeight()));
		StateManager.setState(mainMenuState);
	}
	
	private void tick() {
		keyManager.tick();
		
		if(StateManager.getState() != null)
			StateManager.getState().tick();
			
	}
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear
		g.clearRect(0, 0, width, height);
		//draw	
		
		if(StateManager.getState() != null)
			StateManager.getState().render(g);
		
		//end
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		int tps = 60;
		double timePerTick = 1000000000 / tps;
		double timePerFrame = 1000000000 / fps;
		double tickDelta = 0;
		double frameDelta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		int frames = 0;
		while(running) {
			now = System.nanoTime();
			tickDelta += (now - lastTime) / timePerTick;
			frameDelta += (now - lastTime) / timePerFrame;
			timer += now - lastTime;
			lastTime = now;
			
			if(tickDelta >= 1) {
				tick();
				ticks++;
				tickDelta--;
			}
			if(frameDelta >= 1) {
				render();
				frames++;
				frameDelta--;
			}
			
			if(timer>=1000000000) {
				System.out.println("FPS:" + frames);
				System.out.println("TPS:" + ticks);
				ticks = 0;
				frames = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	public MusicManager getMusicManager() {
		return musicManager;
	}
	public SoundEffectManager getSoundEffectManager() {
		return sem;
	}
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public QuadTree getQuadTree() {
		return quadTree;
	}

	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
