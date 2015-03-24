package com.BombDefuser.StateSystem.Screens;

import sun.java2d.pipe.SpanClipRenderer;

import com.BombDefuser.BombMain;
import com.BombDefuser.StateSystem.BaseState;
import com.BombDefuser.StateSystem.IState;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends BaseState implements IState {
	
	private Texture img;
	
	public GameScreen() {
		
		img = BombMain.assets.get("badlogic.jpg", Texture.class);
	}

	@Override
	public void update(float delta) {
		camera.update();
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
