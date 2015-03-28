package com.BombDefuser.World;
import java.util.ArrayList;
import java.util.List;

import com.BombDefuser.BombMain;
import com.BombDefuser.World.Entity.Entity;
import com.BombDefuser.World.Entity.Hero;
import com.BombDefuser.World.Tiles.ITile;
import com.BombDefuser.World.Tiles.TileRec;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class World {
	
	protected List<ITile> topLayer;
	protected List<ITile> collisionLayer;
	protected List<ITile> lowerLayer;
	
	Hero hero;
	private float gravity;
	
	public World(float gravity)
	{
		this.gravity = gravity;
		
		lowerLayer = new ArrayList<ITile>();
		collisionLayer = new ArrayList<ITile>();
		topLayer = new ArrayList<ITile>();
		
		Texture dot = BombMain.assets.get("dot.png", Texture.class);		
		collisionLayer.add(new TileRec(dot, -100, 0, 400, 10, Color.DARK_GRAY));
		collisionLayer.add(new TileRec(dot, -150, 0, 50, 400, Color.DARK_GRAY));
		
		hero = new Hero(0, 100, 32, 32, this);
	}
	
	public void update(float delta)
	{	
		if(Gdx.input.isKeyPressed(Keys.UP))
			hero.Jump();
		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			hero.MoveLeft();
			hero.setXFliper(true);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			hero.MoveRight();
			hero.setXFliper(false);
		}
		hero.update(delta);
		for(ITile tile : collisionLayer)
			tile.update(delta);
	}
	
	public void render(SpriteBatch batch)
	{
		hero.render(batch);
		for(ITile tile : lowerLayer)
			tile.render(batch);
		for(ITile tile : collisionLayer)
			tile.render(batch);
		for(ITile tile : topLayer)
			tile.render(batch);
	}
	public ITile CollisionEntityTile(Entity entity)
	{
		for(ITile tile : collisionLayer)
		{
			Rectangle tempRec = new Rectangle();
			if(Intersector.intersectRectangles(entity.getHitBox(), tile.getHitBox(), tempRec))
				return tile;
		}
		return null;
	}
	
	public boolean CollisionWithAnyTile(Rectangle hitbox){
		for(ITile tile : collisionLayer){
			Rectangle tempRec = new Rectangle();
			if(Intersector.intersectRectangles(hitbox, tile.getHitBox(), tempRec))
				return true;
		}
		return false;
	}
	
	public float getGravity(){
		return gravity;
	}
	
	public void setGravity(float newGravity){
		this.gravity = newGravity;
	}
	
	public List<ITile> getCollisionLayer(){
		return collisionLayer;
	}

}
