package com.BombDefuser.StateSystem.States;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class State {

	private AssetManager assets;
	private OrthographicCamera camera;
	
	public State(AssetManager assets){
		this.assets = assets;
		camera = new OrthographicCamera(BombMain.WIDTH, BombMain.HEIGHT);
	}
}
