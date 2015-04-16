package com.BombDefuser.Utilities;

import com.BombDefuser.BombMain;
import com.BombDefuser.World.Entity.Hero;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TaserGun {

	public final static float maxTazerTime = 6;

	private boolean isActive;
	private float fireTime;
	
	private Hero hero;
	private TaserBullet bullet;
	private Texture bulletTexture;
	
	public TaserGun(Hero hero){
		this.hero = hero;
		this.bulletTexture = BombMain.assets.get("taser_lightning.png", Texture.class);
		
		bullet = new TaserBullet(bulletTexture, hero);
	}
	
	public void update(float delta){
		if(isActive){
			bullet.update(delta);
			
			if(fireTime > 0)
				fireTime -= delta;
			if(fireTime <= 0)
				isActive = false;
			
		}else{
			fireTime += delta;
			if(fireTime > maxTazerTime)
				fireTime = maxTazerTime;
		}
		
	}
	public void render(SpriteBatch batch)
	{
		if(isActive == true)
			bullet.render(batch);
	}
	
	public float getFireTime(){
		return fireTime;
	}
	
	public void deactivate(){
		isActive = false;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void fire(float delta){
		isActive = true;
		if(fireTime <= 0)
			isActive = false;
	}
	
	public TaserBullet getBullet(){
		return bullet;
	}
}
