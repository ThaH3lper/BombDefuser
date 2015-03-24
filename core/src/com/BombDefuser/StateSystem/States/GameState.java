package com.BombDefuser.StateSystem.States;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState extends State implements IState {
	
	private Texture img;
	
	public GameState(AssetManager assets) {
		super(assets);
		
		img = assets.get("badlogic.jpg", Texture.class);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(img, 0, 0);
	}
}
