package com.BombDefuser.World.Hud;

import com.BombDefuser.BombMain;
import com.BombDefuser.World.World;
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
	Texture textureDot;
	public Hud(World world){
		this.world = world;
		this.recBGGun = new Rectangle(50, 50, 200, 10);
		this.recGun = new Rectangle(50, 50, 200, 10);
		this.textureDot = BombMain.assets.get("dot.png", Texture.class);
	}
	
	public void update(float delta){
		recGun.width = (world.getHero().getGun().getFireTime()/TaserGun.maxTazerTime) * recBGGun.width;
	}
	
	public void render(SpriteBatch batch)
	{
		batch.setColor(Color.GRAY);
		batch.draw(textureDot, recBGGun.x, recBGGun.y, recBGGun.width, recBGGun.height);
		batch.setColor(Color.BLUE);
		batch.draw(textureDot, recGun.x, recGun.y, recGun.width, recGun.height);
		batch.setColor(Color.WHITE);

        if(Gdx.app.getType() == Application.ApplicationType.Android)
            world.getHero().renderAndroidButtons(batch);
	}
}
