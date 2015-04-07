package com.BombDefuser.World.Fans;

import com.BombDefuser.BombMain;
import com.BombDefuser.World.Tiles.ETileTexture;
import com.BombDefuser.World.Tiles.TileRec;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class FanTile extends TileRec{

	private static final float POWER = 70;
	Rectangle recFan;
	EDirection direction;
	boolean activated = true;
	public FanTile(EDirection direction, float x, float y, float width, float height, float distance) {
		super(ETileTexture.FAN, x, y, width, height);
		this.direction = direction;
		this.recFan = rectangleAlgoritm(distance);
	}
	
	@Override
	public void update(float delta) {
		
		super.update(delta);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		if (activated)
			batch.setColor(Color.GREEN);
		else
			batch.setColor(Color.RED);
		batch.draw(BombMain.assets.get("dot.png", Texture.class), recFan.x, recFan.y, recFan.width, recFan.height);
		batch.setColor(Color.WHITE);
		super.render(batch);
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

}
