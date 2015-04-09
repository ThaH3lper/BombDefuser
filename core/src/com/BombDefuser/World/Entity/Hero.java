package com.BombDefuser.World.Entity;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.World.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero extends MoveableEntity{

	private final static float drawWidth = 32, drawHeight = 32;
	private final static float hitWidth = 22, hitHeight = 32;
	
	Animation run, runTaser, idle, turn, current;
	boolean turnDone, hasTaser;
	
	public Hero(float x, float y, World world) {
		super(drawWidth, drawHeight, x, y, hitWidth, hitHeight, world);
		
		run = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 0, 64, 64, 0.06f);
		runTaser = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 1, 64, 64, 0.06f);
		idle = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 22, 2, 64, 64, 0.08f);
		turn = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 4, 0, 64, 64, 0.04f);
		
		current = turn;
		
		setTexture(current.getTexture());
		this.hasTaser = true;
	}
	
	@Override
	public void update(float delta){
		updateControls();
		current.update(delta);
		setRecSource(current.getRecSource());
		super.update(delta);
	}
	
	public void updateControls(){
		if(Gdx.input.isKeyPressed(Keys.UP))
			Jump();
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			if(turnDone){
				if(hasTaser)
					current = runTaser;
				else
					current = run;
			}
			else
			{
				current = turn;
				if(turn.getTimes() >= 1)
				{
					turnDone = true;
				}
			}
			if(Gdx.input.isKeyPressed(Keys.LEFT)){
				MoveLeft();
				setXFliper(true);
			}
			else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				MoveRight();
				setXFliper(false);
			}
		}
		else{
			if(current != idle){
				idle.resetTimes();
				current = idle;
			}
			turnDone = false;
			turn.resetTimes();
		}
	}
	
	@Override
	public void render(SpriteBatch batch)
	{
		super.render(batch);
	}
}
