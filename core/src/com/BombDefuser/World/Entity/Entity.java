package com.BombDefuser.World.Entity;

import com.BombDefuser.Utilities.GameObject;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Tiles.ITile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Patrik
 * A Entity is a object that is affected by the gravity and other forces.
 * And thats all.
 */
public abstract class Entity extends GameObject implements IEntity{

	protected Vector2 velocity, velocityNonConstant, position;
	protected World world;
	protected Boolean isOnGround;
	protected Rectangle hitBox;
	
	public Entity(float x, float y, float width, float height, World world) {
		super(x, y, width, height);
		this.world = world;
		this.velocity = new Vector2(0, 0);
		this.velocityNonConstant = new Vector2(0, 0);
		this.position = new Vector2(x, y);
		this.hitBox = new Rectangle(x, y, width, height);
	}

	public void update(float delta) {
		moveHorizontal(delta);
		moveVertical(delta);
		velocityNonConstant = new Vector2(0, 0);
		super.pos = position;
	}
	
	protected void moveVertical(float delta){
		velocity.y += world.getGravity();
		position.y += (velocity.y) * delta;
		position.y += (velocityNonConstant.y) * delta;
		hitBox.y = position.y;
		ITile tile = world.CollisionEntityTile(this);
		if(tile != null)
		{
			Rectangle tileHitBox = tile.getHitBox();
			if(velocity.y + velocityNonConstant.y < 0){
				position.y = tileHitBox.y + tileHitBox.height;
				isOnGround = true;
			}
			else
				isOnGround = false;
			if(velocity.y + velocityNonConstant.y > 0)
				position.y = tileHitBox.y - hitBox.height;
			velocity.y = 0;
			velocityNonConstant.y = 0;
		}
		else
			isOnGround = false;
		hitBox.y = position.y;
	}
	
	protected void moveHorizontal(float delta){
		position.x += (velocity.x) * delta;
		position.x += (velocityNonConstant.x) * delta;
		hitBox.x = position.x;
		ITile tile = world.CollisionEntityTile(this);
		if(tile != null)
		{
			Rectangle tileHitBox = tile.getHitBox();
			if(velocity.x + velocityNonConstant.x < 0)
				position.x = tileHitBox.x + tileHitBox.width;
			if(velocity.x + velocityNonConstant.x > 0)
				position.x = tileHitBox.x - hitBox.width;
			velocity.x = 0;
			velocityNonConstant.x = 0;
		}
		hitBox.x = position.x;
	}
	
	public void setVelocity(float x, float y){
		velocity = new Vector2(x, y);
	}	
	public void addVelocityNonConstant(float x, float y){
		velocityNonConstant.x += x;
		velocityNonConstant.y += y;
	}
	public void addVelocity(float x, float y){
		velocity.x += x;
		velocity.y += y;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public boolean isOnGround(){
		return isOnGround;
	}
	@Override
	public boolean isDead() {
		return false;
	}
	
	public Vector2 getPosition(){
		return position;
	}
}