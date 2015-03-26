package com.BombDefuser.World.Entity;

import com.BombDefuser.World.World;
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
		addVelocityNonConstant(-speed, 0);
	}
	
	public void MoveRight()
	{
		addVelocityNonConstant(speed, 0);
	}
	
	public void Jump()
	{
		addVelocity(0, jumpPower);
	}

}
