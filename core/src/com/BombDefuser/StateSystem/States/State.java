package com.BombDefuser.StateSystem.States;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class State {

	protected AssetManager assets;
	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	
	public State(AssetManager assets){
		this.assets = assets;
		camera = new OrthographicCamera(BombMain.WIDTH, BombMain.HEIGHT);
		batch = new SpriteBatch();
	}
}
