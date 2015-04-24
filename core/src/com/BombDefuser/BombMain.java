package com.BombDefuser;

import java.util.Random;

import com.BombDefuser.SoundManager.SoundManager;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.StateSystem.ScreenManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BombMain extends ApplicationAdapter {
	
	public static AssetManager assets;
	public static ScreenManager stateManager;
	public static SoundManager soundBank;
	public static Random rnd;
	
	public static boolean failed;
	
	@Override
	public void create () {
		failed = false;
		
		Globals.load();
		rnd = new Random();
		assets = new AssetManager();
		loadContent();
		initialize();
	}
	
	public void loadContent()
	{
		//Load these first
		BombMain.assets.load("logo.png", Texture.class);
		BombMain.assets.load("arial64white.fnt", BitmapFont.class);
		BombMain.assets.load("font.fnt", BitmapFont.class);
		
		//Then the rest
		BombMain.assets.load("dot.png", Texture.class);
		BombMain.assets.load("case.png", Texture.class);
		BombMain.assets.load("bg.png", Texture.class);
		BombMain.assets.load("europe.png", Texture.class);
		BombMain.assets.load("dotselect.png", Texture.class);
		BombMain.assets.load("background/skyline1_layer1_houses.png", Texture.class);
		BombMain.assets.load("background/skyline1_layer2_houses.png", Texture.class);
		BombMain.assets.load("background/skyline1_layer3_sky.png", Texture.class);
		BombMain.assets.load("tiles/tile_gray.png", Texture.class);
		BombMain.assets.load("tiles/tile_green.png", Texture.class);
		BombMain.assets.load("tiles/tile_red.png", Texture.class);
		BombMain.assets.load("tiles/tile_fan.png", Texture.class);
		BombMain.assets.load("tiles/tile_house.png", Texture.class);
		BombMain.assets.load("tiles/tile_house_broken.png", Texture.class);
		BombMain.assets.load("fans/fan_horisontal.png", Texture.class);
		BombMain.assets.load("fans/fan_vertical.png", Texture.class);
		BombMain.assets.load("props/prop_sheet.png", Texture.class);
		BombMain.assets.load("taser_lightning.png", Texture.class);
        BombMain.assets.load("arrow.png", Texture.class);
        BombMain.assets.load("A.png", Texture.class);
        BombMain.assets.load("B.png", Texture.class);
        BombMain.assets.load("gameart.png", Texture.class);
        BombMain.assets.load("Hero/Hero_sprite.png", Texture.class);
        BombMain.assets.load("slad.png", Texture.class);
        BombMain.assets.load("klipp.png", Texture.class);
        
        // buttons
        BombMain.assets.load("btn/btnplay.png", Texture.class);
        BombMain.assets.load("btn/btnretry.png", Texture.class);
        BombMain.assets.load("btn/btnmenu.png", Texture.class);
        BombMain.assets.load("btn/btnlevels.png", Texture.class);
        BombMain.assets.load("btn/btncredits.png", Texture.class);
        BombMain.assets.load("btn/btnsettings.png", Texture.class);
        BombMain.assets.load("btn/btnpause.png", Texture.class);
        
		// load bomb cutscene
		for(int i = 0; i < 70; i++){
			String t = "" + i;
			if(i < 10)
				t = "0" + i;
			BombMain.assets.load("bombcutscene/Final_Animation00" + t + ".jpg", Texture.class);
		}
		
		// load sound
		BombMain.assets.load("sfx/select.wav", Sound.class);
		BombMain.assets.load("sfx/BombDefuser.mp3", Music.class);
		BombMain.assets.load("sfx/jump.wav", Sound.class);
		BombMain.assets.load("sfx/enemypuff.wav", Sound.class);
		BombMain.assets.load("sfx/fan.mp3", Sound.class);
		BombMain.assets.load("sfx/music2.mp3", Music.class);
		BombMain.assets.load("sfx/taser.mp3", Sound.class);
	}
	
	public void initialize(){
		stateManager = new ScreenManager();
		stateManager.setState(EScreen.loading);
	}
	
	private void update()
	{
		// Exit
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		
		// Restart
		if(Gdx.input.isKeyJustPressed(Keys.R))
			this.initialize();
		
		// 
		float delta = Gdx.graphics.getDeltaTime();
		stateManager.update(delta);
	}
	
	@Override
	public void render () {
		update();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stateManager.render();
	}
}
