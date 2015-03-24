package com.BombDefuser;

import com.BombDefuser.StateSystem.StateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BombMain extends ApplicationAdapter {
	
	/*
	 * Loading screen scene
	 * 
	 */
	
	private SpriteBatch batch;
	private AssetManager assets;
	private StateManager states;
	
	private BitmapFont font;
	
	@Override
	public void create () {
		assets = new AssetManager();
		batch = new SpriteBatch();
		
		font = new BitmapFont(Gdx.files.internal("arial64white.fnt"));
		
		loadContent();
		
		states = new StateManager(assets);
	}

	private void loadContent(){
		assets.load("badlogic.jpg", Texture.class);
	}
	
	@Override
	public void render () {
		/*
		 * Update Logic
		 */
		
		float delta = Gdx.graphics.getRawDeltaTime();
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();
		
		float progress = assets.getProgress();
		
		if(assets.update())
			states.update(delta);
		
		/*
		 * Render logic
		 */
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		if(!assets.update())
			font.draw(batch, "Progress: " + (int)(progress * 100), 200, 200);
		if(assets.update())
			states.render(batch);
		batch.end();
	}
}
