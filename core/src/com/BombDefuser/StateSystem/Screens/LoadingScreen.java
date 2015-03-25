package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.BombMain;
import com.BombDefuser.StateSystem.BaseState;
import com.BombDefuser.StateSystem.EStates;
import com.BombDefuser.StateSystem.IState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class LoadingScreen extends BaseState implements IState {
	
	private Texture img, dot;
	private Rectangle logoFrame;
	
	//Variables for delay, loading screen need to ba atleas X sec long
	private final float minLoadTime = 2;
	private float currentTime;
	
	public LoadingScreen() {
		logoFrame = new Rectangle(-150, -100, 300, 200);
	}

	@Override
	public void update(float delta) {
		camera.update();
		currentTime += delta;
		
		if(BombMain.assets.update() && currentTime >= minLoadTime)
			BombMain.stateManager.setState(EStates.meny);
		
		if(BombMain.assets.isLoaded("logo.png") && img == null)
			img = BombMain.assets.get("logo.png", Texture.class);
		
		if(BombMain.assets.isLoaded("dot.png") && dot == null)
			dot = BombMain.assets.get("dot.png", Texture.class);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		if(img != null)
			batch.draw(img, logoFrame.x, logoFrame.y, logoFrame.width, logoFrame.height);
		batch.setColor(Color.LIGHT_GRAY);
		if(dot != null)
			batch.draw(dot, -camera.viewportWidth/2, -100, camera.viewportWidth * BombMain.assets.getProgress(), 10);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		//img.dispose(); Får ej disposa saker tagna ifrån assetsmanager
	}
}
