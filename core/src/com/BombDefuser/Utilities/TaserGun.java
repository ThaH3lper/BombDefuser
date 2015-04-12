package com.BombDefuser.Utilities;

public class TaserGun {

	private final static float reloadTime = 4f;
	private float time;
	float loaded;
	public TaserGun(){
		
	}
	public void update(float delta){
		if(loaded != 1)
			time += delta;
		if(time >= reloadTime && loaded != 1f)
		{
			time = 0;
			loaded = 1;
		}
		else if (loaded != 1){
			loaded = time/reloadTime;
		}
	}
	
	public float getLoaded(){
		return loaded;
	}
	public void fire(){
		if(loaded == 1){
			loaded = 0;
		}
	}
}
