package com.BombDefuser.SoundManager;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	
	// Global volume value;
	private float soundVolume;
	private float musicVolume;
	
	private boolean isMuted;

	// All sounds in projekt
	private Sound select, jump, puff, fan, taser;
	private Music music, music2;
	private boolean isFanPlaying, isTaserPlaying;
	
	public SoundManager(){
		initialize();
	}
	
	private void initialize(){
		// Set default values
		soundVolume = 1f;
		musicVolume = 0.5f;
		// Load Sound
		select = BombMain.assets.get("sfx/select.wav", Sound.class);
		music = BombMain.assets.get("sfx/BombDefuser.mp3", Music.class);
		music2 = BombMain.assets.get("sfx/music2.mp3", Music.class);
		jump = BombMain.assets.get("sfx/jump.wav", Sound.class);
		puff = BombMain.assets.get("sfx/enemypuff.wav", Sound.class);
		fan = BombMain.assets.get("sfx/fan.mp3", Sound.class);
		taser = BombMain.assets.get("sfx/taser.mp3", Sound.class);
		
		isMuted = true;
		
		isFanPlaying = false;
	}
	
	public void stopMusic(){
		music.stop();
	}
	
	public void stopFan(){
		fan.stop();
		isFanPlaying = false;
	}
	
	public void stopTaser(){
		isTaserPlaying = false;
		
		taser.stop();
	}
	
	public void playSound(ESounds sound){
		if(!isMuted)
		switch (sound) {
		case select: 
			select.play(soundVolume);
			break; 
		case taser:
			if(!isTaserPlaying)
				taser.play(0.6f);
			isTaserPlaying = true;
			break; 
		case music:
			music.setVolume(musicVolume);
			music.play();
			break;
		case music2:
			music2.setVolume(musicVolume);
			music2.play();
			break;
		case jump:
			jump.play(soundVolume);
			break;
		case enemypuff:
			puff.play(soundVolume);
			break;
		case fan:
			if(!isFanPlaying){
				fan.play(0.5f);
				isFanPlaying = true;
			}
			break;
		}
	}
	
	public boolean isFanPlaying(){
		return isFanPlaying;
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
