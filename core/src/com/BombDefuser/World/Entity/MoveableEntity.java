package com.BombDefuser.World.Entity;

import com.BombDefuser.World.Force;
import com.BombDefuser.World.World;
import com.badlogic.gdx.math.Vector2;
/**
 * @author Patrik
 * MoveableEntity is a object that you can move left, right and jump.
 * MovableEntity is a base for all "Human like" objects
 */
public class MoveableEntity extends Entity{
	
	private static final  float defaultSpeed = 100, defaultJumpPower = 100;
	
	protected float speed, jumpPower;
	
	public MoveableEntity(float x, float y, float width, float height, World world){
		this(x, y, width, height, world, defaultSpeed, defaultJumpPower);
	}
	public MoveableEntity(float x, float y, float width, float height, World world, float speed, float jumpPower){
		super(x, y, width, height, world);
		this.speed = speed;
		this.jumpPower = jumpPower;
		
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
	}
	
	public void MoveLeft()
	{
		forces.put("move", new Force(new Vector2(-speed, 0), 0.01f));
	}
	
	public void MoveRight()
	{
		forces.put("move", new Force(new Vector2(speed, 0), 0.01f));
	}
	
	public void Jump()
	{
		forces.put("jump", new Force(new Vector2(0, jumpPower), 0f));
	}

}
