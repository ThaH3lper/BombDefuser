package com.BombDefuser.Enemy;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.Animation;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Entity.Entity;
import com.BombDefuser.World.Entity.MoveableEntity;
import com.BombDefuser.World.Tiles.ITile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends MoveableEntity {
	
	private final static float drawWidth = 32, drawHeight = 32;
	private final static float hitWidth = 22, hitHeight = 32;
	private final static float defaultSpeed = 80, defaultHealth = 100;
	
	private int ID;
	private Animation run;
	private Rectangle leftRec, rightRec;
	private EDirection currentDir;
	private float health;
	
	public Enemy(int ID, float x, float y, World world) {
		super(drawWidth, drawHeight, x, y, hitWidth, hitHeight, world);
		this.ID = ID;
		
		run = new Animation(BombMain.assets.get("Hero/Hero_sprite.png", Texture.class), 5, 14, 0, 64, 64, 0.06f);
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
	
	@Override
	public void update(float delta){
		run.update(delta);
		this.setRecSource(run.getRecSource());
		super.update(delta);
		
		// Update location
		if(this.isOnGround){
			if(currentDir == EDirection.LEFT)
				MoveLeft();
			if(currentDir == EDirection.RIGHT)
				MoveRight();
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
