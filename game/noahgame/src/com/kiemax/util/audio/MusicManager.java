package com.kiemax.util.audio;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MusicManager implements Runnable {
	private ArrayList<AudioFile> songList;
	private int currentSongIndex;
	private boolean running;
	public MusicManager(String... songs) {
		songList = new ArrayList<AudioFile>();
		for(String song : songs) {
			songList.add(new AudioFile("./noahgame/assets/sounds/music/" + song + ".wav"));
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
