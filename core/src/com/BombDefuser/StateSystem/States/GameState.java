package com.BombDefuser.StateSystem.States;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class GameState extends State implements IState {
	
	private Texture img;
	
	public GameState(AssetManager assets) {
		super(assets);
		
		img = assets.get("badlogic.jpg", Texture.class);
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
}
