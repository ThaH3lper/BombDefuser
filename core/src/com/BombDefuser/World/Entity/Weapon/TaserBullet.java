package com.BombDefuser.World.Entity.Weapon;

import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.Utilities.GameObject;
import com.BombDefuser.World.Entity.Hero;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TaserBullet extends GameObject{

	private final static float drawWidth = 32, drawHeight = 32, offsetCenter = 16;
	
	private Animation animation;
	private Hero hero;
	
	public TaserBullet(Texture texture, Hero hero){
		super(texture, 2, 2, 64, 64, hero.getPosition().x, hero.getPosition().y, drawWidth, drawHeight, Color.WHITE);
		this.hero = hero;
		this.animation = new Animation(texture, 0, 4, 0, 64, 64, 4, 4, 2, 2, 0.02f);
	}

	public void update(float delta) {
		animation.update(delta);
		setRecSource(animation.getRecSource());
		xFliped = hero.getXFliper();
		if(xFliped)
			super.setPos(new Vector2(hero.getCenterPosition().x - offsetCenter - drawWidth, hero.getPosition().y));
		else
			super.setPos(new Vector2(hero.getCenterPosition().x + offsetCenter, hero.getPosition().y));
		
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
	}
}
