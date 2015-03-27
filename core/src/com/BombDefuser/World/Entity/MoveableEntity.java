package com.BombDefuser.World.Entity;

import com.BombDefuser.World.World;
import com.badlogic.gdx.math.Rectangle;
/**
 * @author Patrik
 * MoveableEntity is a object that you can move left, right and jump.
 * MovableEntity is a base for all "Human like" objects
 */
public abstract class MoveableEntity extends Entity{
	
	private static final  float defaultSpeed = 100, defaultJumpPower = 100, footHeight = 10;
	
	protected float speed, jumpPower;
	protected Rectangle recFoot;
	
	public MoveableEntity(float x, float y, float width, float height, World world){
		this(x, y, width, height, world, defaultSpeed, defaultJumpPower);
	}
	public MoveableEntity(float x, float y, float width, float height, World world, float speed, float jumpPower){
		super(x, y, width, height, world);
		this.speed = speed;
		this.jumpPower = jumpPower;
		this.recFoot = new Rectangle(x, y - footHeight/2, width, footHeight);	
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		recFoot.x = position.x;
		recFoot.y = position.y - recFoot.height/2;
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
		if(world.CollisionWithAnyTile(recFoot))
			addVelocity(0, jumpPower);
	}
}
