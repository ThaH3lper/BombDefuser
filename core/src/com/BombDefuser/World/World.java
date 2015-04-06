package com.BombDefuser.World;
import java.util.ArrayList;
import java.util.List;

import com.BombDefuser.BombMain;
import com.BombDefuser.Bomb.Bomb;
import com.BombDefuser.Utilities.BackgroundLayer;
import com.BombDefuser.World.Entity.Enemy;
import com.BombDefuser.World.Entity.Entity;
import com.BombDefuser.World.Entity.Hero;
import com.BombDefuser.World.Tiles.ETileTexture;
import com.BombDefuser.World.Tiles.ITile;
import com.BombDefuser.World.Tiles.PropTile;
import com.BombDefuser.World.Tiles.TileRec;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
	BackgroundLayer lower, middle, top;
	
	public World(float gravity)
	{
		this.gravity = gravity;
		
		lowerLayer = new ArrayList<ITile>();
		collisionLayer = new ArrayList<ITile>();
		topLayer = new ArrayList<ITile>();
		enemies = new ArrayList<Enemy>();
		
		collisionLayer.add(new TileRec(ETileTexture.GREEN, -1200, -555, 5000, 420));
		collisionLayer.add(new TileRec(ETileTexture.GRAY, -48, -135, 20, 45));
		collisionLayer.add(new TileRec(ETileTexture.GRAY, -48, -90, 10, 50));
		collisionLayer.add(new TileRec(ETileTexture.GRAY, -38, -100, 120, 10));
		collisionLayer.add(new TileRec(ETileTexture.RED, -150, -100, 50, 10));
		collisionLayer.add(new PropTile(0, 0, 0, -90, 20, 20));
		collisionLayer.add(new PropTile(2, 0, 30, -90, 20, 20));
		collisionLayer.add(new PropTile(3, 1, -38, -90, 20, 20));
		
		lower = new BackgroundLayer(BombMain.assets.get("background/skyline1_layer3_sky.png", Texture.class), 1f, -200);
		middle = new BackgroundLayer(BombMain.assets.get("background/skyline1_layer2_houses.png", Texture.class), 0.5f, -200);
		top = new BackgroundLayer(BombMain.assets.get("background/skyline1_layer1_houses.png", Texture.class), 0f, -200);
		

		init();
	}
	
	public void addEnemy(float x, float y, float width, float height, float speed, Color color){
		enemies.add(new Enemy(enemies.size(), new Vector2(x, y), width, height, color, this, speed));
	}
	
	private void init(){
		bomb = new Bomb(240, -135, 60);
		hero = new Hero(0, 100, this);
	}
	
	public void update(float delta, OrthographicCamera camera)
	{	
		
		for(Enemy i : enemies)
			i.update(delta);

		hero.update(delta);
		bomb.update(delta);
		
		if(bomb.isExploded())
			init();
		
		for(ITile tile : collisionLayer)
			tile.update(delta);
		
		lower.update(delta, camera);
		middle.update(delta, camera);
		top.update(delta, camera);
	}
	
	public void render(SpriteBatch batch)
	{
		lower.render(batch);
		middle.render(batch);
		top.render(batch);
		
		for(ITile tile : lowerLayer)
			tile.render(batch);
		for(ITile tile : collisionLayer)
			tile.render(batch);
		for(Enemy i : enemies)
			i.render(batch);
		bomb.render(batch);
		hero.render(batch);
		for(ITile tile : topLayer)
			tile.render(batch);
	}
	public ITile CollisionEntityTile(Entity entity)
	{
		for(ITile tile : collisionLayer)
		{
			if(!tile.getIsCollision())
				continue;
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
