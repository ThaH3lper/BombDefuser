package com.BombDefuser.World;
import java.util.ArrayList;
import java.util.List;

import com.BombDefuser.BombMain;
import com.BombDefuser.World.Tiles.ITile;
import com.BombDefuser.World.Tiles.TileRec;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class World {
	
	protected List<ITile> topLayer;
	protected List<ITile> collisionLayer;
	protected List<ITile> lowerLayer;
	
	private float gravity;
	
	public World(float gravity)
	{
		this.gravity = gravity;
		
		lowerLayer = new ArrayList<ITile>();
		collisionLayer = new ArrayList<ITile>();
		topLayer = new ArrayList<ITile>();
		
		Texture dot = BombMain.assets.get("dot.png", Texture.class);		
		collisionLayer.add(new TileRec(dot, 0, 0, 1, 1, Color.DARK_GRAY));
		collisionLayer.add(new TileRec(dot, 2, 0, 2.7f, 1, Color.DARK_GRAY));
		collisionLayer.add(new TileRec(dot, 5, 0, 2.4f, 1, Color.DARK_GRAY));
		collisionLayer.add(new TileRec(dot, 0, -5f, 5f, 0.1f, Color.DARK_GRAY));
	}
	
	public void update(float delta)
	{	
		for(ITile tile : collisionLayer)
			tile.update(delta);
	}
	
	public void render(SpriteBatch batch)
	{
		for(ITile tile : lowerLayer)
			tile.render(batch);
		for(ITile tile : collisionLayer)
			tile.render(batch);
		for(ITile tile : topLayer)
			tile.render(batch);
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
