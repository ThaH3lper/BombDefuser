package com.BombDefuser;

import com.BombDefuser.SoundManager.SoundManager;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.StateSystem.ScreenManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BombMain extends ApplicationAdapter {
	
	public static AssetManager assets;
	public static ScreenManager stateManager;
	public static SoundManager soundBank;
	
	@Override
	public void create () {
		Globals.load();
		assets = new AssetManager();
		loadContent();
		initialize();
	}
	
	public void loadContent()
	{
		//Load these first
		BombMain.assets.load("logo.png", Texture.class);
		BombMain.assets.load("arial64white.fnt", BitmapFont.class);
		
		//Then the rest
		BombMain.assets.load("badlogic.jpg", Texture.class);
		BombMain.assets.load("dot.png", Texture.class);
		BombMain.assets.load("btnplay.png", Texture.class);
		BombMain.assets.load("bg.png", Texture.class);
		BombMain.assets.load("europe.png", Texture.class);
		BombMain.assets.load("dotselect.png", Texture.class);
		BombMain.assets.load("Hero/Hero_sprite.png", Texture.class);
		BombMain.assets.load("select.wav", Sound.class);
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
