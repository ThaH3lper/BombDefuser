package com.BombDefuser.Utilities;

import com.BombDefuser.BombMain;
import com.BombDefuser.World.Entity.Hero;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TaserGun {

	private final static float reloadTime = 3f;
	private float time;
	private float loaded;
	private Hero hero;
	private TaserBullet bullet;
	private Texture bulletTexture;
	
	public TaserGun(Hero hero){
		this.hero = hero;
		this.bulletTexture = BombMain.assets.get("taser_lightning.png", Texture.class);
	}
	public void update(float delta){
		if(loaded != 1)
			time += delta;
		if(time >= reloadTime && loaded != 1f)
		{
			time = 0;
			loaded = 1;
		}
		else if (loaded != 1){
			loaded = time/reloadTime;
		}
		if(bullet != null){
			bullet.update(delta);
			if(bullet.isDead())
				bullet = null;
		}
	}
	public void render(SpriteBatch batch)
	{
		if(bullet != null)
			bullet.render(batch);
	}
	
	public float getLoaded(){
		return loaded;
	}
	public void fire(){
		if(loaded == 1){
			loaded = 0;
			bullet = new TaserBullet(bulletTexture, hero);
		}
	}
	
	public TaserBullet getBullet(){
		return bullet;
	}
}
