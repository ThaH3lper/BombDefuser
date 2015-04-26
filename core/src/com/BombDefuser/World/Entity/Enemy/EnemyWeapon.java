package com.BombDefuser.World.Entity.Enemy;

import com.BombDefuser.BombMain;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.Utilities.GameObject;
import com.BombDefuser.World.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

// Rename later
public class EnemyWeapon extends GameObject {
	
	public static final float defualtWeaponDamage = 10;
	public static final float defualtNextShotTime = 0.5f;
	public static final int defaultMagazineSize = 10;
	
	private World world;
	private Enemy enemy;
	
	// Weapon values
	private float timer; // Time until next shot can be fired
	private int shotsFired;
	
	public EnemyWeapon(Enemy enemy, World world) {
		super(BombMain.assets.get("dot.png", Texture.class));
		this.enemy = enemy;
		this.world = world;
		this.width = 1;
		this.height = 1;
	}
	
	public void update(float delta){
		
		// Update weapon position
		this.pos.y = enemy.getY() + 18.5f;
		
		// Flips the weapon to the right direction
		switch(enemy.currentDir){
		case LEFT:
			this.pos.x = enemy.getX() + 4;
			this.xFliped = false;
			break;
		case RIGHT:
			this.pos.x = enemy.getX() + enemy.getWidth() - 4;
			this.xFliped = true;
			break;
		case NONE:
			break;
		default:
			break;
		}
		
		if(timer >= 0)
			timer -= delta;
		
		// Shoot logic
		if(enemy.seesPlayer && !enemy.isReloading && !enemy.isHit()){
			// Calc direction to hero
			float bulletSpeed = 100;
			Vector2 a = enemy.getCenterPosition();
			Vector2 b = world.getHero().getCenterPosition();
			Vector2 deltaAB = new Vector2(b.x - a.x, b.y - a.y);
			deltaAB.nor();
			Vector2 velocity = new Vector2();
			velocity.x = deltaAB.x * bulletSpeed;
			velocity.y = deltaAB.y * bulletSpeed;
			
			// Shoot
			if(timer <= 0 && shotsFired < defaultMagazineSize){
				BombMain.soundBank.playSound(ESounds.shot);
				shotsFired++;
				world.addBullet(pos.x, pos.y, velocity);
				timer = defualtNextShotTime;
			}
		}
	}
	
	public void resetShotsFired(){
		shotsFired = 0;
	}
	
	// Returns true if the weapon is out of "bullets"
	public boolean needToReload(){
		return shotsFired >= defaultMagazineSize;
	}
	
	@Override
	public void render(SpriteBatch batch) { }
}
