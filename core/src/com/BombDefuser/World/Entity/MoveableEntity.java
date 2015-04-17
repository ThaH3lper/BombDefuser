package com.BombDefuser.World.Entity;

import com.BombDefuser.BombMain;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.World.World;
import com.badlogic.gdx.math.Rectangle;
/**
 * @author Patrik
 * MoveableEntity is a object that you can move left, right and jump.
 * MovableEntity is a base for all "Human like" objects
 */
public abstract class MoveableEntity extends Entity{
	
	private static final  float defaultSpeed = 100, defaultJumpPower = 270, footHeight = 2;
	
	protected float speed, jumpPower;
	protected Rectangle recFoot;
	protected boolean isOnGround = false;
	
	public MoveableEntity(float drawWidth, float drawHeight, float x, float y, float width, float height, World world){
		this(drawWidth, drawHeight, x, y, width, height, world, defaultSpeed, defaultJumpPower);
	}
	public MoveableEntity(float drawWidth, float drawHeight, float x, float y, float width, float height, World world, float speed, float jumpPower){
		super(drawWidth, drawHeight, x, y, width, height, world);
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
		if(world.CollisionWithAnyTile(recFoot) && velocity.y == 0)
			isOnGround = true;
		else
			isOnGround = false;
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
		if(isOnGround){
			addVelocity(0, jumpPower);
			BombMain.soundBank.playSound(ESounds.jump);
		}
	}
}
