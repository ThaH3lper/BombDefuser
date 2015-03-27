package com.BombDefuser.StateSystem;

import com.BombDefuser.Globals;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BaseScreen{

	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	
	public BaseScreen(){
		camera = new OrthographicCamera(Globals.VIRTUAL_WIDTH, Globals.NEW_VIRTUAL_HEIGHT);
		batch = new SpriteBatch();
	}
}
