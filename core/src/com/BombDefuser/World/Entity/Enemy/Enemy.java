package com.BombDefuser.World.Entity.Enemy;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Entity.MoveableEntity;
import com.BombDefuser.World.Tiles.ITile;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends MoveableEntity {
	
	private final static float drawWidth = 32, drawHeight = 32;
	private final static float hitWidth = 22, hitHeight = 32;
	
	private final static float defaultSpeed = 40, defaultHealth = 100;
	private final static float defualtDeathTime = 1, defaultTimeUntilIdle = 5;
	
	private int ID;
	private Animation run, idle, reload, electric;
	private Rectangle leftRec, rightRec;

	private float health;
	
	protected EDirection currentDir;
	protected boolean standStill, seesPlayer, isReloading;
	
	private boolean deathWish;
	private float deathTimer;
	private boolean isHit;
	private float shakeRot = 5;
	private float timeUntilNextShake = 0;
	private float timeUntilIdle = defaultTimeUntilIdle;
	
	private EnemyWeapon weapon;
	
	public Enemy(int ID, float x, float y, World world) {
		super(drawWidth, drawHeight, x, y, hitWidth, hitHeight, world);
		this.ID = ID;
		
		this.weapon = new EnemyWeapon(this, world);
		
		deathTimer = defualtDeathTime;
		
		run = new Animation(BombMain.assets.get("Enemy/enemysprite.png", Texture.class), 0, 9, 0, 64, 64, 4, 4, 2, 2, 0.15f);
		idle = new Animation(BombMain.assets.get("Enemy/enemysprite.png", Texture.class), 0, 6, 2, 64, 64, 4, 4, 2, 2, 0.3f);
		reload = new Animation(BombMain.assets.get("Enemy/enemysprite.png", Texture.class), 0, 5, 1, 64, 64, 4, 4, 2, 2, 0.15f);
		electric = new Animation(BombMain.assets.get("Enemy/enemysprite.png", Texture.class), 0, 5, 3, 64, 64, 4, 4, 2, 2, 0.15f);
		this.setTexture(run.getTexture());
		
		leftRec = new Rectangle(0, 0, hitWidth, hitHeight);
		rightRec = new Rectangle(0, 0, hitWidth, hitHeight);
		
		currentDir = EDirection.LEFT;
		this.setXFliper(true);
		this.speed = defaultSpeed;
		this.health = defaultHealth;
	}
	
	// returns true if dead
	public boolean takeDamage(float damage){
		health -= damage;
		if(health <= 0)
			return true;
		
		return false;
	}
	
	@Override
	protected void moveHorizontal(float delta){
		position.x += (velocity.x) * delta;
		position.x += (velocityNonConstant.x) * delta;
		hitBox.x = position.x;
		ITile tile = world.CollisionEntityTile(this);
		if(tile != null)
		{
			Rectangle tileHitBox = tile.getHitBox();
			if(velocity.x + velocityNonConstant.x < 0){
				position.x = tileHitBox.x + tileHitBox.width;
				
				if(Globals.AI_DEBUG_MODE)
					System.out.println("Enemy " + this.ID + " has collided with left wall/object, changes direction to right.");
				currentDir = EDirection.RIGHT;
				this.setXFliper(false);
				velocity.x = speed;
				pos.x = tileHitBox.x + tileHitBox.width;
			}
			if(velocity.x + velocityNonConstant.x > 0){
				position.x = tileHitBox.x - hitBox.width;
				
				if(Globals.AI_DEBUG_MODE)
					System.out.println("Enemy " + this.ID + " has collided with right wall/object, changes direction to left.");
				currentDir = EDirection.LEFT;
				this.setXFliper(true);
				velocity.x = -speed;
				pos.x = tileHitBox.x - hitWidth;
			}
			velocityNonConstant.x = 0;
		}
		if(velocity.x > 0){
			velocity.x -= FRICTION;
			if(velocity.x < 0)
				velocity.x = 0;
		}
		else if(velocity.x < 0){
			velocity.x += FRICTION;
			if(velocity.x > 0)
				velocity.x = 0;
		}
		hitBox.x = position.x;
		
	}
	
	@Override
	public void update(float delta){
		weapon.update(delta);
		
		if(isHit){
			// Hit zone
			
			// Set electric animation
			electric.update(delta);
			this.setRecSource(electric.getRecSource());
			
			// Time until death
			deathTimer -= delta;
			if(deathTimer <= 0)
				deathWish = true;
			
			// Shaky state
			timeUntilNextShake -= delta;
			if(timeUntilNextShake <= 0){
				timeUntilNextShake = 0.05f;
				shakeRot = -shakeRot;
				this.rotation = shakeRot;
			}
		}else{
			// Regular zone
			this.rotation = 0;
			this.setRecSource(run.getRecSource());
			super.update(delta);
			
			// Update location
			if(this.isOnGround && !standStill){
				run.update(delta);
				if(currentDir == EDirection.LEFT){
					MoveLeft();
				}
				if(currentDir == EDirection.RIGHT){
					MoveRight();
				}
			}
			
			// Check if we are in reach of the hero
			Vector2 hero = this.world.getHero().getPos();
			if(Vector2.dst(hero.x, hero.y, pos.x, pos.y) < 100){
				// Hero is in reach
				if(Globals.AI_DEBUG_MODE)
					System.out.println("Enemy is in reach of player. Checking if there is any tiles in the way.");
				
				// If there has been no intersection with world tiles 
				if(!world.TileBetweenVectors(world.getHero().getCenterPosition(), this.getCenterPosition())){
					if(Globals.AI_DEBUG_MODE)
						System.out.println("Enemy have an eye on the player.");
					
					// Stand still and define that the enemy has spotted the hero
					seesPlayer = true;
					standStill = true;
					run.setEnemyAimingPose();
					
					// Calculate which side the hero is on relative to the enemy
					float deltaX = world.getHero().getPos().x - pos.x;
					if(deltaX > 0){
						// Hero is on the right side relative to the enemy
						currentDir = EDirection.RIGHT;
						xFliped = false;
					} else {
						currentDir = EDirection.LEFT;
						xFliped = true;
					}
				} else {
					// A tile is in the way of the hero
					
					if(Globals.AI_DEBUG_MODE)
						System.out.println("Enemy DONT have an eye on the player.");
					
					// Define that the enemy don't see the hero and keep walking
					seesPlayer = false;
					standStill = false;
				}
			} else {
				// Hero not in reach
				standStill = false;
				seesPlayer = false;
			}
			
			// Update pit death fall detectors
			leftRec.x = pos.x - hitWidth;
			leftRec.y = pos.y - 2;
			rightRec.x = pos.x + hitWidth;
			rightRec.y = pos.y - 2;
			
			// Check if we are close to pitfall
			if(!world.CollisionWithAnyTile(leftRec) && currentDir == EDirection.LEFT && isOnGround){
				if(Globals.AI_DEBUG_MODE){
					String msg = "Enemy " + this.ID + " has encountered pit fall, changes direction to right.";
					System.out.println(msg);
				}
				
				currentDir = EDirection.RIGHT;
				this.setXFliper(false);
			}
			if(!world.CollisionWithAnyTile(rightRec) && currentDir == EDirection.RIGHT && isOnGround){
				if(Globals.AI_DEBUG_MODE){
					String msg = "Enemy " + this.ID + " has encountered pit fall, changes direction to left.";
					System.out.println(msg);
				}
				
				currentDir = EDirection.LEFT;
				this.setXFliper(true);
			}
			
			// Reload animation - This code is connected with the EnemyWeapon Class
			if(weapon.needToReload()){
				if(!reload.update(delta)){
					this.isReloading = true;
					this.standStill = true;
					this.setRecSource(reload.getRecSource());
				} else {
					this.isReloading = false;
					if(!seesPlayer)
						this.standStill = false;
					weapon.resetShotsFired();
				}
			}
			
			// be able to idle if hero is not visible to the enemy
			if(!seesPlayer)
				idleFeature(delta);
		}
	}
	
	// Will make the enemy idle Enemey will idle
	private void idleFeature(float delta){
		timeUntilIdle -= delta;
		if(timeUntilIdle < 0){
			this.standStill = true;
			this.setRecSource(idle.getRecSource());
			if(idle.update(delta)){
				standStill = false;
				timeUntilIdle = defaultTimeUntilIdle;
			}
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		// render weapon
		weapon.render(batch);
	}
	
	public boolean isStandingStill() {
		return standStill;
	}
	
	public boolean seesPlayer() {
		return seesPlayer;
	}
	
	public boolean deathWish(){
		return deathWish;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setHit(boolean value){
		isHit = value;
	}
	
	public boolean isHit(){
		return isHit;
	}
}
