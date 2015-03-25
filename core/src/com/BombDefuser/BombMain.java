package com.BombDefuser;

import com.BombDefuser.StateSystem.EStates;
import com.BombDefuser.StateSystem.StateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BombMain extends ApplicationAdapter {
	
	public static AssetManager assets;
	
	public static StateManager stateManager;
	
	@Override
	public void create () {
		Globals.load();
		assets = new AssetManager();
		loadContent();
		
		stateManager = new StateManager();
		stateManager.setState(EStates.loading);
	}
	
	public void loadContent()
	{
		//Load these first
		BombMain.assets.load("logo.png", Texture.class);
		BombMain.assets.load("arial64white.fnt", BitmapFont.class);
		
		//Then the rest
		BombMain.assets.load("badlogic.jpg", Texture.class);
		BombMain.assets.load("dot.png", Texture.class);
	}
	
	private void update()
	{
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		
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
