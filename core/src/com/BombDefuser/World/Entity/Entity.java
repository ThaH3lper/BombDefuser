package com.BombDefuser.World.Entity;

import java.util.HashMap;

import com.BombDefuser.BombMain;
import com.BombDefuser.World.Force;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Tiles.ITile;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Patrik
 * A Entity is a object that is affected by the gravity and other forces.
 * And thats all.
 */
public abstract class Entity implements IEntity{

	protected Vector2 velocity, position;
	protected World world;
	protected Boolean isOnGround;
	protected Rectangle hitBox;
	protected HashMap<String, Force> forces;
	
	//debug
	protected Texture texture;
	
	public Entity(float x, float y, float width, float height, World world) {
		this.world = world;
		this.velocity = new Vector2(0, 0);
		this.position = new Vector2(x, y);
		this.hitBox = new Rectangle(x, y, width, height);
		this.forces = new HashMap<String, Force>();
		
		forces.put("gravity", new Force(new Vector2(0, world.getGravity()), 0));
		
		//debug
		this.texture = BombMain.assets.get("dot.png", Texture.class);
	}

	@Override
	public void update(float delta) {
		Vector2 sumForces = sumForces();
		moveVertical(delta, sumForces);
		moveHorizontal(delta, sumForces);
		velocity = new Vector2(0, 0);
	}
	
	protected void moveVertical(float delta, Vector2 sumForces){
		velocity.y += sumForces.y;
		position.y += (velocity.y) * delta;
		hitBox.y = position.y;
		ITile tile = world.CollisionEntityTile(this);
		if(tile != null)
		{
			Rectangle tileHitBox = tile.getHitBox();
			if(velocity.y < 0){
				position.y = tileHitBox.y + tileHitBox.height;
				isOnGround = true;
			}
			else
				isOnGround = false;
			if(velocity.y > 0)
				position.y = tileHitBox.y - hitBox.height;
			velocity.y = 0;
		}
		else
			isOnGround = false;
	}
	
	protected void moveHorizontal(float delta, Vector2 sumForces){
		velocity.x += sumForces.x;
		position.x += (velocity.x) * delta;
		hitBox.x = position.x;
		hitBox.y = position.y;
		ITile tile = world.CollisionEntityTile(this);
		if(tile != null)
		{
			Rectangle tileHitBox = tile.getHitBox();
			if(velocity.x < 0)
				position.x = tileHitBox.x + tileHitBox.width;
			if(velocity.x > 0)
				position.x = tileHitBox.x - hitBox.width;
			velocity.x = 0;
		}
	}
	
	private Vector2 sumForces()
	{
		Vector2 sumForces = new Vector2(0, 0);
		for(String key : forces.keySet())
		{
			//System.out.println(f.getForceAmount().x + " " + f.getForceAmount().y);
			sumForces.x += forces.get(key).getForceAmount().x;
			sumForces.y += forces.get(key).getForceAmount().y;
			forces.get(key).update();
		}
		return sumForces;
	}
	
	//debug
	@Override
	public void render(SpriteBatch batch) {
		batch.setColor(Color.RED);
		batch.draw(texture, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		batch.setColor(Color.WHITE);
	}
	
	public void setVelocity(float x, float y){
		velocity = new Vector2(x, y);
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
}