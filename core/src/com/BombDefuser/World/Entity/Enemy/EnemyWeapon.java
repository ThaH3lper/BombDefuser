package com.BombDefuser.World.Entity.Enemy;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.GameObject;
import com.badlogic.gdx.graphics.Texture;

// Rename later
public class EnemyWeapon extends GameObject {
	
	private Enemy enemy;
	
	public EnemyWeapon(Enemy enemy) {
		super(BombMain.assets.get("gun.png", Texture.class));
		this.enemy = enemy;
		
		this.scaleX = 0.8f;
		this.scaleY = 0.8f;
	}
	
	public void update(float delta){
		// Update weapon position
		this.pos.x = enemy.getX() + 3;
		this.pos.y = enemy.getY() + 10;
		
		// Flips the weapon to the right direction
		switch(enemy.currentDir){
		case LEFT:
			this.xFliped = false;
			break;
		case RIGHT:
			this.xFliped = true;
			break;
		case NONE:
			break;
		default:
			break;
		}
	}
}
