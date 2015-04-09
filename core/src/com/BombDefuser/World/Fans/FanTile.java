package com.BombDefuser.World.Fans;

import java.util.Random;

import com.BombDefuser.BombMain;
import com.BombDefuser.Particle.ParticleManager;
import com.BombDefuser.World.Tiles.ETileTexture;
import com.BombDefuser.World.Tiles.TileRec;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class FanTile extends TileRec{

	private static final float PARTICLESPEED = 80, PARTICLEINTERVAL = 0.1f;
	private static final float POWER = 70;
	private Rectangle recFan;
	private EDirection direction;
	private boolean activated = true;
	private Random random;
	private float particleTime, activationTime, activationInterval; 
	public FanTile(EDirection direction, float x, float y, float width, float height, float distance) {
		this(direction, x, y, width, height, distance, 0f);
	}
	public FanTile(EDirection direction, float x, float y, float width, float height, float distance, float activationInterval) {
		super(ETileTexture.FAN, x, y, width, height);
		this.direction = direction;
		this.recFan = rectangleAlgoritm(distance);
		this.random = new Random();
		this.activationInterval = activationInterval;
	}
	
	@Override
	public void update(float delta) {
		particleTime += delta;
		activationTime += delta;
		if(particleTime >= PARTICLEINTERVAL && activated){
			particleTime = 0;
			spawnParticles();
		}
		if(activationTime >= activationInterval){
			activationTime = 0;
			activated = (activated) ? false : true;
		}
		super.update(delta);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		/*if (activated)
			batch.setColor(0f, 1f, 0f, 0.2f);
		else
			batch.setColor(1f, 0f, 0f, 0.2f);
		batch.draw(BombMain.assets.get("dot.png", Texture.class), recFan.x, recFan.y, recFan.width, recFan.height);
		batch.setColor(Color.WHITE);*/
		super.render(batch);
	}
	
	private void spawnParticles()
	{
		if(!activated)
			return;
		Vector2 spawn = new Vector2();
		switch (direction) {
		case UP:
			spawn = new Vector2(recHit.x + random.nextInt((int)recHit.width), recHit.y + recHit.height);
			ParticleManager.addFanParticle(spawn, new Vector2(0, PARTICLESPEED), recFan.height);
			break;
		case DOWN:
			spawn = new Vector2(recHit.x + random.nextInt((int)recHit.width), recHit.y);
			ParticleManager.addFanParticle(spawn, new Vector2(0, -PARTICLESPEED), recFan.height);
			break;
		case LEFT:
			spawn = new Vector2(recHit.x, recHit.y + random.nextInt((int) recHit.height));
			ParticleManager.addFanParticle(spawn, new Vector2( -PARTICLESPEED, 0), recFan.width);
			break;
		case RIGHT:
			spawn = new Vector2(recHit.x + recHit.width, recHit.y + random.nextInt((int) recHit.height));
			ParticleManager.addFanParticle(spawn, new Vector2( PARTICLESPEED, 0), recFan.width);
			break;
		}
	}
	//A Algorithm that calculates the rectangle of the fan
	private Rectangle rectangleAlgoritm(float distance)
	{
		Rectangle rectangle = new Rectangle();
		switch (direction) {
		case UP:
			rectangle = new Rectangle(recHit.x, recHit.y + recHit.height, recHit.width, distance);
			break;
		case DOWN:
			rectangle = new Rectangle(recHit.x, recHit.y - distance, recHit.width, distance);
			break;
		case RIGHT:
			rectangle = new Rectangle(recHit.x + recHit.width, recHit.y, distance, recHit.height);
			break;
		case LEFT:
			rectangle = new Rectangle(recHit.x - distance, recHit.y, distance, recHit.height);
			break;
		}
		return rectangle;
	}
	
	//A Algorithm that calculates the current power of position
	public Vector2 getPower(Vector2 position)
	{
		if(recFan.contains(position)){
			switch (direction) {
			case UP:
				return new Vector2(0, (1-((position.y - recFan.y)/(recFan.height))) * POWER);
			case DOWN:
				return new Vector2(0, 1-(((recFan.y - recFan.height) - position.y)/(recHit.height)) * POWER * -1);
			case RIGHT:
				return new Vector2((1-((position.x - recFan.x)/(recFan.width))) * POWER, 0);
			case LEFT:
				return new Vector2((1-(((recFan.x + recFan.width) - position.x)/recFan.width)) * POWER * -1, 0);
			}
		}
		return new Vector2(0, 0);
	}
	
	public boolean isActivated(){
		return activated;
	}

}
