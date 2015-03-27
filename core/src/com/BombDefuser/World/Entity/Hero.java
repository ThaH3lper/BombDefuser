package com.BombDefuser.World.Entity;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.World.World;
import com.badlogic.gdx.graphics.Texture;

public class Hero extends MoveableEntity{

	Animation run;
	public Hero(float x, float y, float width, float height, World world) {
		super(x, y, width, height, world);
		
		run = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 9, 0, 64, 64, 0.05f);
		setTexture(run.getTexture());
	}
	
	@Override
	public void update(float delta)
	{
		run.update(delta);
		setRecSource(run.getRecSource());
		super.update(delta);
	}
}
