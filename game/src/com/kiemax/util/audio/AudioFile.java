package com.kiemax.util.audio;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioFile implements LineListener {
	private File audioFile;
	private boolean paused = false;
	private AudioInputStream ais;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip;
	private volatile boolean playing;
	public AudioFile(String fileName) {
		try {
		audioFile = new File(fileName);
		ais = AudioSystem.getAudioInputStream(audioFile);
		format = ais.getFormat();
		info = new DataLine.Info(Clip.class, format);
		clip = (Clip) AudioSystem.getLine(info);
		clip.addLineListener(this);
		clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void play() {
		clip.start();
		playing = true;
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	public void stop() {
		clip.stop();
		clip.flush();
		clip.setFramePosition(0);
		playing = false;
	}
	
	public void pause() {
		if (!paused) {
		paused = true;
		clip.stop();
		}
	}
	public void resume() {
		if (paused) {
			clip.start();
			paused = false;
		} 
	}
	@Override
	public void update(LineEvent event) {
		if (event.getType() == LineEvent.Type.START && !paused) {
			playing = true;
		}
		else if (event.getType() == LineEvent.Type.STOP && !paused) {
			clip.stop();
			clip.flush();
			clip.setFramePosition(0);
			playing = false;
			paused = false;
		}
		
	} 
}
