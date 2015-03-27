package com.BombDefuser.World.Entity;

import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.World.World;

public class Hero extends MoveableEntity{

	Animation run;
	public Hero(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
		
	}
	
	@Override
	public void update(float delta)
	{
		
	}
}
