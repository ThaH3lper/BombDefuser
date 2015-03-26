package com.BombDefuser.World.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IEntity {

	void update(float delta);
	
	void render(SpriteBatch batch);
	
	boolean isDead();
}
