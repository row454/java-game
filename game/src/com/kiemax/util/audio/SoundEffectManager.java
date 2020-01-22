package com.kiemax.util.audio;

import java.util.ArrayList;
import java.util.Iterator;

public class SoundEffectManager implements Runnable {
	private ArrayList<AudioFile> soundList;
	private volatile boolean running;
	public volatile ArrayList<AudioFile> playingSounds;
	public SoundEffectManager(String... sounds) {
		soundList = new ArrayList<AudioFile>();
		for(String sound : sounds) {
			
			soundList.add(new AudioFile(this.getClass().getClassLoader().getResource("sounds/sfx/" + sound + ".wav").getPath()));
			
		}
		
	}
	
	@Override
	public synchronized void run() {
		running = true;
		playingSounds = new ArrayList<AudioFile>();
		while (running) {
			if (!playingSounds.isEmpty()) {
				Iterator<AudioFile> it = playingSounds.iterator();
				while (it.hasNext()) {
					AudioFile sfx = it.next();
					sfx.play();
					it.remove();
				} try {
				Thread.sleep(1);
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
			}
		}
	}
	public void play(int index) {
		playingSounds.add(soundList.get(index));
	}
	public boolean isRunning() {
		return running;
	}

	public ArrayList<AudioFile> getPlayingSounds() {
		return playingSounds;
	}
}
