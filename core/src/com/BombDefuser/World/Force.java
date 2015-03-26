package com.BombDefuser.World;

import com.badlogic.gdx.math.Vector2;

public class Force {

	private Vector2 forceAmount, orginalAmount;
	private float smooth;
	public Force(Vector2 forceAmount, float smooth)
	{
		this.orginalAmount = new Vector2(forceAmount.x, forceAmount.y);
		this.forceAmount = forceAmount;
		this.smooth = smooth;
	}
	
	public void update()
	{
		if(forceAmount.x > 0)
		{
			System.out.println(forceAmount.x);
			forceAmount.x -= (orginalAmount.x * smooth);
			if(forceAmount.x < 0)
				forceAmount.x = 0;
		}
		else if(forceAmount.x < 0)
		{
			forceAmount.x -= (orginalAmount.x * smooth);
			if(forceAmount.x > 0)
				forceAmount.x = 0;
		}
		
		if(forceAmount.y > 0)
		{
			forceAmount.y -= (orginalAmount.y * smooth);
			if(forceAmount.y < 0)
				forceAmount.y = 0;
		}
		else if(forceAmount.y < 0)
		{
			forceAmount.y += (orginalAmount.y * smooth);
			if(forceAmount.y > 0)
				forceAmount.y = 0;
		}
			
	}
	
	public Vector2 getForceAmount(){
		return forceAmount;
	}
	
	public float getSmooth(){
		return smooth;
	}
}
