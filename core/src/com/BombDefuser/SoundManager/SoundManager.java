package com.BombDefuser.SoundManager;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	
	// Global volume value;
	private float volume;
	
	// All sounds in projekt
	private Sound select;
	
	public SoundManager(){
		initialize();
	}
	
	private void initialize(){
		// Set default values
		volume = 1f;
		// Load Sound
		select = BombMain.assets.get("select.wav", Sound.class);
	}
	
	public void playSound(ESounds sound){
		switch (sound) {
		case select: 
			select.play(volume);
			break; 
		}
	}
	
	public float getVolume(){
		return this.volume;
	}
	
	public void setVolume(float volume){
		this.volume = volume;
	}
}
