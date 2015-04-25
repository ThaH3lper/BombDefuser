package com.BombDefuser.World.Entity.Enemy;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Entity.MoveableEntity;
import com.BombDefuser.World.Tiles.ITile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends MoveableEntity {
	
	private final static float drawWidth = 32, drawHeight = 32;
	private final static float hitWidth = 22, hitHeight = 32;
	private final static float defaultSpeed = 40, defaultHealth = 100;
	private final static float defualtDeathTimer = 2;
	
	private int ID;
	private Animation run;
	private Rectangle leftRec, rightRec;
	private float health;
	
	protected EDirection currentDir;
	protected boolean standStill;
	
	private boolean deathWish;
	private float deathTimer;
	private boolean isHit;
	
	private EnemyWeapon weapon;
	
	public Enemy(int ID, float x, float y, World world) {
		super(drawWidth, drawHeight, x, y, hitWidth, hitHeight, world);
		this.ID = ID;
		
		this.weapon = new EnemyWeapon(this);
		
		deathTimer = defualtDeathTimer;
		
		run = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 0, 64, 64, 0.12f);
		this.color = Color.RED;
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
				
				String msg = "Enemy " + this.ID + " has collided with left wall/object, changes direction to right.";
				System.out.println(msg);
				currentDir = EDirection.RIGHT;
				this.setXFliper(false);
				velocity.x = speed;
				pos.x = tileHitBox.x + tileHitBox.width;
			}
			if(velocity.x + velocityNonConstant.x > 0){
				position.x = tileHitBox.x - hitBox.width;
				
				String msg = "Enemy " + this.ID + " has collided with right wall/object, changes direction to left.";
				System.out.println(msg);
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
	
	private float shakeRot = 5;
	private float timeUntilNextShake = 0;
	
	@Override
	public void update(float delta){
		weapon.update(delta);
		
		if(isHit){
			// Hit zone
			
			this.color = Color.BLUE;
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
			
			this.color = Color.RED;
			this.rotation = 0;
			this.setRecSource(run.getRecSource());
			super.update(delta);
			
			// Update location
			if(this.isOnGround){
				if(currentDir == EDirection.LEFT){
					run.update(delta);
					MoveLeft();
				}
				if(currentDir == EDirection.RIGHT){
					run.update(delta);
					MoveRight();
				}
			}
			
			// Check if we are in reach of the hero
			Vector2 hero = this.world.getHero().getPos();
			if(Vector2.dst(hero.x, hero.y, pos.x, pos.y) < 100){
				// Hero is in reach
				
				currentDir = EDirection.NONE;
				run.setCurrentFrame(5);
				
				// Calculate which side the hero is on relative to the enemy
				float deltaX = world.getHero().getPos().x - pos.x;
				if(deltaX > 0){
					// Hero is on the right side relative to the enemy
					currentDir = EDirection.NONE;
				}
			}
			
			// Update pit death fall detectors
			leftRec.x = pos.x - hitWidth;
			leftRec.y = pos.y - 2;
			rightRec.x = pos.x + hitWidth;
			rightRec.y = pos.y - 2;
			
			// Check if we are close to pitfall
			if(!world.CollisionWithAnyTile(leftRec) && currentDir == EDirection.LEFT && isOnGround){
				String msg = "Enemy " + this.ID + " has encountered pit fall, changes direction to right.";
				System.out.println(msg);
				
				currentDir = EDirection.RIGHT;
				this.setXFliper(false);
			}
			if(!world.CollisionWithAnyTile(rightRec) && currentDir == EDirection.RIGHT && isOnGround){
				String msg = "Enemy " + this.ID + " has encountered pit fall, changes direction to left.";
				System.out.println(msg);
				
				currentDir = EDirection.LEFT;
				this.setXFliper(true);
			}
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		// render weapon
		weapon.render(batch);
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
