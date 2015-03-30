package com.BombDefuser.World.Tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class TextureTile {
	
	//Work in progress
	private Texture texture;
	Rectangle recHit;
	public TextureTile(Texture texture, float x, float y, float width, float height, float tileX, float tileY, float tileWidth, float tileHeight)
	{
		float xSource = x % width;
		float ySource = y % height;
		this.texture = texture;
		recHit = new Rectangle(x, y, width, height);
	}
	
	public void render(SpriteBatch batch)
	{
		batch.setColor(Color.RED);
		batch.draw(texture, recHit.x, recHit.y, recHit.width, recHit.height);
		batch.setColor(Color.WHITE);
	}

}
