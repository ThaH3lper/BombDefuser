package com.BombDefuser.World.Entity;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.World.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero extends MoveableEntity{

	private final static float drawWidth = 32, drawHeight = 32;
	private final static float hitWidth = 22, hitHeight = 32;
	
	Animation run;
	
	public Hero(float x, float y, World world) {
		super(drawWidth, drawHeight, x, y, hitWidth, hitHeight, world);
		
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
	
	@Override
	public void render(SpriteBatch batch)
	{
		//DEBUG HitBox!
		//batch.setColor(Color.RED);
		//batch.draw(BombMain.assets.get("dot.png", Texture.class), hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		//batch.setColor(Color.WHITE);
		super.render(batch);
	}
}
