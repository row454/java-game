package com.pixelandbitgames.game;

import com.pixelandbitgames.util.audio.MusicManager;
import com.pixelandbitgames.util.audio.SoundEffectManager;

public class Launcher {
	public volatile static MusicManager musicManager;
	public volatile static SoundEffectManager sem;
	public static void main(String[] args) {
		Game game = new Game("NightLight", 800, 600);
		game.start();
		musicManager = new MusicManager("blank8", "building_together", "surprise");
		new Thread(musicManager).start();
		musicManager.pause();
		sem = new SoundEffectManager("beep");
		new Thread(sem).start();
	}

}
