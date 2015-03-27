package com.BombDefuser.SoundManager;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	
	// Global volume value;
	private float soundVolume;
	private float musicVolume;

	// All sounds in projekt
	private Sound select, music;
	
	public SoundManager(){
		initialize();
	}
	
	private void initialize(){
		// Set default values
		soundVolume = 1f;
		musicVolume = 1f;
		// Load Sound
		select = BombMain.assets.get("sfx/select.wav", Sound.class);
		music = BombMain.assets.get("sfx/BombDefuser.mp3", Sound.class);
	}
	
	public void stopMusic(){
		music.stop();
	}
	
	public void playSound(ESounds sound){
		switch (sound) {
		case select: 
			select.play(soundVolume);
			break; 
		case music:
			music.play(musicVolume);
			break;
		}
	}
	
	public float getMusicVolume() {
		return musicVolume;
	}

	public void setMusicVolume(float music_volume) {
		this.musicVolume = music_volume;
	}
	
	public float getSoundVolume(){
		return this.soundVolume;
	}
	
	public void setSoundVolume(float volume){
		this.soundVolume = volume;
	}
}
