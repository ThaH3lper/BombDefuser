package com.BombDefuser;

import com.badlogic.gdx.Gdx;

public class Globals {
	public static final int VIRTUAL_WIDTH = 480, VIRTUAL_HEIGHT = 320;
	public static int NEW_VIRTUAL_WIDTH, CAMERA_SIDE_PADDING;
	public static float GRAVITY = -600;
	public static String currentLevel;
	
	public static final boolean AI_DEBUG_MODE = false;

	// Game variables
	public static boolean failed;
	public static boolean cutWrongWire;
	public static boolean runOutOfTime;
	public static boolean deadFromEnemies;
	
	// Meny variables
	public static int startIndex, endIndex;
	
	public static void load()
	{
		NEW_VIRTUAL_WIDTH = (int)(((float)Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight()) * (float)VIRTUAL_HEIGHT);
		CAMERA_SIDE_PADDING = VIRTUAL_WIDTH - NEW_VIRTUAL_WIDTH;
		
		startIndex = 0;
		endIndex = 4;
		
		gameReset();
	}
	
	public static void gameReset(){
		failed = false;
		cutWrongWire = false;
		runOutOfTime = false;
		deadFromEnemies = false;
	}
}
