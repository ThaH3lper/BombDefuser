package com.BombDefuser.World;
import java.util.ArrayList;
import java.util.List;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.Particle.ParticleManager;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.Utilities.BackgroundLayer;
import com.BombDefuser.World.Bomb.Bomb;
import com.BombDefuser.World.Entity.Entity;
import com.BombDefuser.World.Entity.Hero;
import com.BombDefuser.World.Entity.Enemy.Enemy;
import com.BombDefuser.World.Fans.FanTile;
import com.BombDefuser.World.Tiles.ITile;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class World {
	
	protected List<ITile> topLayer;
	protected List<ITile> collisionLayer;
	protected List<ITile> lowerLayer;
	protected Bomb bomb;
	
	private int enemySpawned;
	private List<Enemy> enemy;
	private List<Vector2> enemySpawn;
	
	private Hero hero;
	private float gravity, bombTime;
	private BackgroundLayer lower, middle, top;
	private Vector2 heroSpawn, bombSpawn;
	
	public World(float gravity)
	{
		this.gravity = gravity;
		
		lowerLayer = new ArrayList<ITile>();
		collisionLayer = new ArrayList<ITile>();
		topLayer = new ArrayList<ITile>();
		enemy = new ArrayList<Enemy>();
		enemySpawn = new ArrayList<Vector2>();
		
		lower = new BackgroundLayer(BombMain.assets.get("background/skyline1_layer3_sky.png", Texture.class), 1f, -200);
		middle = new BackgroundLayer(BombMain.assets.get("background/skyline1_layer2_houses.png", Texture.class), 0.5f, -200);
		top = new BackgroundLayer(BombMain.assets.get("background/skyline1_layer1_houses.png", Texture.class), 0f, -200);
	}
	
	private void spawnEnemy(float x, float y){
		this.enemy.add(new Enemy(enemySpawned, x, y, this));
		enemySpawned++;
	}
	
	public void init(){
		enemySpawned = 0;
		
		if(bombSpawn != null)
			bomb = new Bomb(bombSpawn.x, bombSpawn.y, bombTime);
		if(heroSpawn != null)
			hero = new Hero(heroSpawn.x, heroSpawn.y, this);
		for(Vector2 spawn : enemySpawn)
			spawnEnemy(spawn.x, spawn.y);
	}
	
	public void update(float delta, OrthographicCamera camera)
	{	
		ParticleManager.update(delta);
		hero.update(delta);
		
		// Enemy logic
		for(int i = 0; i < enemy.size(); i++){
			if(enemy.get(i).deathWish()){
				enemy.remove(i);
				BombMain.soundBank.playSound(ESounds.enemypuff);
				continue;
			}
			enemy.get(i).update(delta);
		}
		
		// Bomb logic
		bomb.update(delta);
		if(bomb.isExploded()){
			Globals.failed = true;
			Globals.runOutOfTime = true;
			BombMain.stateManager.setState(EScreen.endscreen);
		}
		
		// Fan logic
		boolean inReachOfFan = false;
		for(ITile tile : collisionLayer){
			tile.update(delta);
			if(tile instanceof FanTile)
			{
				FanTile fTile = (FanTile) tile;
				if(!fTile.isActivated())
					continue;
				Vector2 power = fTile.getPower(hero.getCenterPosition());
				hero.addVelocity(power.x, power.y);
				
				// Sound
				if(Vector2.dst(hero.getCenterPosition().x, hero.getCenterPosition().y, ((FanTile) tile).getCenterPos().x, ((FanTile) tile).getCenterPos().y) < 100){
					BombMain.soundBank.playSound(ESounds.fan);
					inReachOfFan = true;
				}
			}
		}
		if(!inReachOfFan)
			BombMain.soundBank.stopFan();
		
		// Scrolling background
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
		ParticleManager.render(batch);
		for(ITile tile : collisionLayer)
			tile.render(batch);
		bomb.render(batch);
		for(Enemy e : enemy)
			e.render(batch);
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
	
	public Bomb getBomb(){
		return bomb;
	}
	
	public float getGravity(){
		return gravity;
	}
	
	public void setGravity(float newGravity){
		this.gravity = newGravity;
	}
	
	public List<Enemy> getEnemies(){
		return enemy;
	}
	
	public List<ITile> getCollisionLayer(){
		return collisionLayer;
	}
	public List<ITile> getLowerLayer(){
		return lowerLayer;
	}
	public List<ITile> getTopLayer(){
		return topLayer;
	}
	public void setHeroSpawn(Vector2 spawn)
	{
		this.heroSpawn = spawn;
	}
	public void setBomb(Vector2 spawn, float time)
	{
		this.bombSpawn = spawn;
		this.bombTime = time;
	}
	public void addEnemy(Vector2 spawn)
	{
		this.enemySpawn.add(spawn);
	}

}
