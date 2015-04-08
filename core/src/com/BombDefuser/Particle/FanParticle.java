package com.BombDefuser.Particle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FanParticle implements IParticle{

	private Vector2 velocity, position;
	private float opacity, size;
	private Color color;
	private boolean dead;
	private Texture texture;
	
	public FanParticle(Texture texture, Vector2 velocity, Vector2 position, float size, Color color){
		this.velocity = velocity;
		this.position = position;
		this.opacity = 1f;
		this.size = size;
		this.color = color;
		this.dead = false;
		this.texture = texture;
	}

	@Override
	public void update(float delta) {
		position.x += velocity.x * delta;
		position.y += velocity.y * delta;
		opacity -= 0.01f;
		if(opacity <= 0)
			dead = true;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setColor(color.r, color.g, color.b, opacity);
		batch.draw(texture, position.x - size/2, position.y - size/2, size, size);
		batch.setColor(Color.WHITE);
	}

	@Override
	public boolean isDead() {
		return dead;
	}
}
