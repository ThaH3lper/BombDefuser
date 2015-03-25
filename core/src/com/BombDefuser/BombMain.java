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
	private BitmapFont font;
	
	@Override
	public void create () {
		assets = new AssetManager();
		loadContent();
		
		stateManager = new StateManager();
		
		//Startup state
		stateManager.setState(EStates.loading);
		
		font = new BitmapFont(Gdx.files.internal("arial64white.fnt"));	
	}
	
	public void loadContent()
	{
		BombMain.assets.load("logo.png", Texture.class);
		BombMain.assets.load("badlogic.jpg", Texture.class);
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
