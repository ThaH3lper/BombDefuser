package com.BombDefuser.World.Fans;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.Utilities.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FanRec extends GameObject{
	
	Animation animation;
	
	public FanRec(float x, float y, float width, float height, EDirection direction) {
		super(x, y, width, height);
		switch (direction) {
		case LEFT:
			animation = new Animation(BombMain.assets.get("fans/fan_vertical.png", Texture.class), 0, 2, 0, 16, 40, 0.05f);
			xFliped = true;
			break;
		case RIGHT:
			animation = new Animation(BombMain.assets.get("fans/fan_vertical.png", Texture.class), 0, 2, 0, 16, 40, 0.05f);
			break;
		case UP:
			animation = new Animation(BombMain.assets.get("fans/fan_horisontal.png", Texture.class), 0, 2, 0, 40, 16, 0.05f);
			break;
		case DOWN:
			animation = new Animation(BombMain.assets.get("fans/fan_horisontal.png", Texture.class), 0, 2, 0, 40, 16, 0.05f);
			yFliped = true;
			break;
		}
		setTexture(animation.getTexture());
	}
	public void update(float delta)
	{
		animation.update(delta);
		recSource = animation.getRecSource();
	}
	@Override
	public void render(SpriteBatch batch)
	{
		super.render(batch);
	}

}
