package com.BombDefuser.Particle;

import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.World.Fans.EDirection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TaserParticle implements IParticle{

	private final static float drawWidth = 32, drawHeight = 32;
	private Vector2 position;
	private boolean dead;
	private Texture texture;
	private Animation animation;
	
	public TaserParticle(Texture texture, Vector2 position, EDirection direction){
		this.position = position;
		this.dead = false;
		this.texture = texture;
		this.animation = new Animation(texture, 0, 4, 0, 64, 64, 0.05f);
	}

	@Override
	public void update(float delta) {
		if(!dead)
			animation.update(delta);
		if(animation.getTimes() >= 1)
			dead = true;
	}

	@Override
	public void render(SpriteBatch batch) {
			
	}

	@Override
	public boolean isDead() {
		return dead;
	}
}
