package com.BombDefuser.Utilities;

import com.BombDefuser.World.Entity.Hero;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TaserBullet extends GameObject{

	private final static float drawWidth = 32, drawHeight = 32, offsetCenter = 16;
	private boolean dead;
	private Animation animation;
	private Hero hero;
	
	public TaserBullet(Texture texture, Hero hero){
		super(texture, 2, 2, 64, 64, hero.getPosition().x, hero.getPosition().y, drawWidth, drawHeight, Color.WHITE);
		this.hero = hero;
		this.dead = false;
		this.animation = new Animation(texture, 0, 4, 0, 64, 64, 0.05f);
	}

	public void update(float delta) {
		if(!dead){
			animation.update(delta);
			setRecSource(animation.getRecSource());
			xFliped = hero.getXFliper();
			if(xFliped)
				super.setPos(new Vector2(hero.getCenterPosition().x - offsetCenter - drawWidth, hero.getPosition().y));
			else
				super.setPos(new Vector2(hero.getCenterPosition().x + offsetCenter, hero.getPosition().y));
		}
		if(animation.getTimes() >= 1)
			dead = true;
	}

	@Override
	public void render(SpriteBatch batch) {
			super.render(batch);
	}

	public boolean isDead() {
		return dead;
	}
}
