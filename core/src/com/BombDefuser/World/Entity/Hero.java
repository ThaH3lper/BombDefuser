package com.BombDefuser.World.Entity;

import com.BombDefuser.BombMain;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.Utilities.TaserGun;
import com.BombDefuser.World.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero extends MoveableEntity{

	private final static float drawWidth = 32, drawHeight = 32;
	private final static float hitWidth = 22, hitHeight = 32;
	
	TaserGun taser;
	
	Animation run, runTaser, idle, turn, current, idleFire;
	boolean turnDone;
	
	public Hero(float x, float y, World world) {
		super(drawWidth, drawHeight, x, y, hitWidth, hitHeight, world);
		
		run = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 0, 64, 64, 0.06f);
		runTaser = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 1, 64, 64, 0.06f);
		idle = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 22, 2, 64, 64, 0.08f);
		turn = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 4, 0, 64, 64, 0.04f);
		idleFire = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 0, 0, 3, 64, 64, 1f);
		
		current = turn;
		setTexture(current.getTexture());
		taser = new TaserGun(this);
	}
	
	@Override
	public void update(float delta){
		updateControls();
		current.update(delta);
		setRecSource(current.getRecSource());
		super.update(delta);
		taser.update(delta);
	}
	
	public void updateControls(){
		// A button
		if(Gdx.input.isKeyPressed(Keys.UP))
			Jump();
		// B button
		if(Gdx.input.isKeyPressed(Keys.X)){
			if(world.getBomb().getHitbox().overlaps(hitBox)){
				BombMain.stateManager.setState(EScreen.defuse);
			}else{
				taser.fire();
			}
		}
		
		// C & D buttons
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			if(taser.getBullet() != null)
				current = runTaser;
			else{
				if(turnDone){
					if(taser.getLoaded() == 1f)
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
			if(taser.getBullet() != null)
				current = idleFire;
			else{
				if(current != idle){
					idle.resetTimes();
					current = idle;
				}
				turnDone = false;
				turn.resetTimes();
			}
		}
	}
	
	@Override
	public void render(SpriteBatch batch)
	{
		taser.render(batch);
		super.render(batch);
	}
	
	public TaserGun getGun(){
		return taser;
	}
}
