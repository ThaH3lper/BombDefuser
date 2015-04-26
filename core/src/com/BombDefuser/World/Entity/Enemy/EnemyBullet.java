package com.BombDefuser.World.Entity.Enemy;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyBullet extends GameObject {
	
	// Time to live
	public static final float TTL = 5;
	
	private Vector2 velocity;
	private float timer;
	
	public EnemyBullet(float x, float y, Vector2 velocity) {
		super(BombMain.assets.get("dot.png", Texture.class));
		this.pos.x = x;
		this.pos.y = y;
		this.velocity = velocity;
		width = 2;
		height = 2;
		
		this.color = new Color(Color.YELLOW.r, Color.YELLOW.g, Color.YELLOW.b, 0.35f);
	}
	
	// Returns true if it wants to die
	public boolean update(float delta){
		timer += delta;
		if(timer >= TTL)
			return true;
		
		pos.x += velocity.x * delta;
		pos.y += velocity.y * delta;
		return false;
	}
	
	// return the hitbox of the bullet
	public Rectangle getHitbox(){
		return new Rectangle(pos.x, pos.y, width, height);
	}
}
