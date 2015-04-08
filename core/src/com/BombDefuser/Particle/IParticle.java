package com.BombDefuser.Particle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IParticle {
	
	void update(float delta);
	
	void render(SpriteBatch batch);
	
	boolean isDead();

}
