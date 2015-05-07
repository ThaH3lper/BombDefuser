package com.BombDefuser.World.Hud;

import com.BombDefuser.BombMain;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Entity.Entity;
import com.BombDefuser.World.Entity.Weapon.TaserGun;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Hud {

	World world;
	Rectangle recBGGun, recGun;
	Rectangle recBGHealth, recHealth;
	Texture textureDot;
	public Hud(World world){
		this.world = world;
		this.recBGGun = new Rectangle(250, 50, 200, 10);
		this.recGun = new Rectangle(250, 50, 200, 10);
		this.recBGHealth = new Rectangle(250, 60, 200, 10);
		this.recHealth = new Rectangle(250, 60, 200, 10);
		this.textureDot = BombMain.assets.get("dot.png", Texture.class);
	}
	
	public void update(float delta){
		recGun.width = (world.getHero().getGun().getFireTime()/TaserGun.maxTazerTime) * recBGGun.width;
		recHealth.width = (world.getHero().getHealth()/Entity.DEFAULT_HEALTH) * (float)recBGHealth.width;
	}
	
	public void render(SpriteBatch batch)
	{
		// Tazer batery BG
		batch.setColor(Color.GRAY);
		batch.draw(textureDot, recBGGun.x, recBGGun.y, recBGGun.width, recBGGun.height);
		// Tazer batery
		batch.setColor(Color.BLUE);
		batch.draw(textureDot, recGun.x, recGun.y, recGun.width, recGun.height);
		
		// Health BG
		batch.setColor(Color.GRAY);
		batch.draw(textureDot, recBGHealth.x, recBGHealth.y, recBGHealth.width, recBGHealth.height);
		// Health bar
		batch.setColor(Color.RED);
		batch.draw(textureDot, recHealth.x, recHealth.y, recHealth.width, recHealth.height);
		batch.setColor(Color.WHITE);
		
        if(Gdx.app.getType() == Application.ApplicationType.Android)
            world.getHero().renderAndroidButtons(batch);
	}
}
