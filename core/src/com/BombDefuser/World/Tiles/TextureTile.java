package com.BombDefuser.World.Tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class TextureTile {
	
	private Rectangle recDraw;
	private TextureRegion region;
	public TextureTile(Texture texture, Rectangle innerTile, Rectangle tile)
	{
		recDraw = new Rectangle();
		Intersector.intersectRectangles(innerTile, tile, recDraw);
		region = new TextureRegion(texture, (int)((recDraw.x - innerTile.x)*2), 64 - (int)((recDraw.y - innerTile.y)*2) - (int)(recDraw.height*2), (int)(recDraw.width*2), (int)(recDraw.height*2));
	}
	
	public void render(SpriteBatch batch)
	{
		batch.draw(region, recDraw.x, recDraw.y, recDraw.width, recDraw.height);
	}

}
