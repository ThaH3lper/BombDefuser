package com.BombDefuser.World.Entity.Enemy;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.GameObject;
import com.badlogic.gdx.graphics.Texture;

public class EnemyBullet extends GameObject {

	public EnemyBullet() {
		super(BombMain.assets.get("bullet.png", Texture.class));
		
	}

}
