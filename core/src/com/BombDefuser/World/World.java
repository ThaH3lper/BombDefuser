package com.BombDefuser.World;
import java.util.ArrayList;
import java.util.List;

import com.BombDefuser.BombMain;
import com.BombDefuser.Bomb.Bomb;
import com.BombDefuser.World.Entity.Enemy;
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
import com.badlogic.gdx.math.Vector2;

public class World {
	
	protected List<Enemy> enemies;
	protected List<ITile> topLayer;
	protected List<ITile> collisionLayer;
	protected List<ITile> lowerLayer;
	protected Bomb bomb;
	
	Hero hero;
	private float gravity;
	
	public World(float gravity)
	{
		this.gravity = gravity;
		
		lowerLayer = new ArrayList<ITile>();
		collisionLayer = new ArrayList<ITile>();
		topLayer = new ArrayList<ITile>();
		enemies = new ArrayList<Enemy>();
		
		Texture dot = BombMain.assets.get("tiles/tile_gray.png", Texture.class);		
		collisionLayer.add(new TileRec(dot, 10, -100, 100, 100));
		//collisionLayer.add(new TileRec(dot, -150, 0, 50, 400));
		collisionLayer.add(new TileRec(dot, 200, -30, 100, 10));

		init();
	}
	
	public void addEnemy(float x, float y, float width, float height, float speed, Color color){
		enemies.add(new Enemy(enemies.size(), new Vector2(x, y), width, height, color, this, speed));
	}
	
	private void init(){
		bomb = new Bomb(240, 10, 20);
		hero = new Hero(0, 100, this);
	}
	
	public void update(float delta)
	{	
		for(Enemy i : enemies)
			i.update(delta);
		
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
		bomb.update(delta);
		
		if(bomb.isExploded())
			init();
		
		for(ITile tile : collisionLayer)
			tile.update(delta);
	}
	
	public void render(SpriteBatch batch)
	{
		for(ITile tile : lowerLayer)
			tile.render(batch);
		for(Enemy i : enemies)
			i.render(batch);
		bomb.render(batch);
		hero.render(batch);
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
	
	public Hero getHero(){
		return hero;
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
