package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.BombMain;
import com.BombDefuser.StateSystem.BaseState;
import com.BombDefuser.StateSystem.EStates;
import com.BombDefuser.StateSystem.IState;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class LoadingScreen extends BaseState implements IState {
	
	private Texture img;
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
			BombMain.stateManager.setState(EStates.game);
		
		if(BombMain.assets.isLoaded("logo.png") && img == null)
			img = BombMain.assets.get("logo.png", Texture.class);
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		if(img != null)
			batch.draw(img, logoFrame.x, logoFrame.y, logoFrame.width, logoFrame.height);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
