package com.BombDefuser;

import com.badlogic.gdx.Gdx;

public class Globals {
	public static final int VIRTUAL_WIDTH = 480, VIRTUAL_HEIGHT = 320;
	public static int NEW_VIRTUAL_HEIGHT, CAMERA_TOP_PADDING;
	
	public static void load()
	{
		NEW_VIRTUAL_HEIGHT = (int)(((float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth()) * (float)VIRTUAL_WIDTH);
		CAMERA_TOP_PADDING = VIRTUAL_HEIGHT - NEW_VIRTUAL_HEIGHT;
	}
}
