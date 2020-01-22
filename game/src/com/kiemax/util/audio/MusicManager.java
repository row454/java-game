package com.kiemax.util.audio;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MusicManager implements Runnable {
	private ArrayList<AudioFile> songList;
	private int currentSongIndex;
	private boolean running;
	public MusicManager(String... songs) {
		songList = new ArrayList<AudioFile>();
		for(String song : songs) {
			songList.add(new AudioFile(this.getClass().getClassLoader().getResource("sounds/music/" + song + ".wav").getPath()));
			
			
		}
		System.out.println(songList.size());
	}
	public void setSong(int index) {
		AudioFile currentSong = songList.get(currentSongIndex);
		currentSongIndex = index - 1;
		currentSong.stop();
	}
	public void skip() {
		songList.get(currentSongIndex).stop();
	}
	public int getIndex() {
		return currentSongIndex;
	}
	public void pause() {
		songList.get(currentSongIndex).pause();
	}
	public void resume() {
		songList.get(currentSongIndex).play();;
	}
	@Override
	public synchronized void run() {
		running = true;
		AudioFile song = songList.get(currentSongIndex);
		song.play();
		while (running) {
			if(!song.isPlaying()) { 
				currentSongIndex++;
				if (currentSongIndex >= songList.size())
					currentSongIndex = 0;
				song = songList.get(currentSongIndex);
				song.play();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
