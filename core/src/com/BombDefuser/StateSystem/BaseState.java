package com.BombDefuser.StateSystem;

import com.BombDefuser.Globals;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BaseState{

	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	
	public BaseState(){
		camera = new OrthographicCamera(Globals.VIRTUAL_WIDTH, Globals.VIRTUAL_HEIGHT);
		batch = new SpriteBatch();
	}
}
