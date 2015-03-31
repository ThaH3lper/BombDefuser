package com.BombDefuser.World.Tiles;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class TextureTile {
	
	private Rectangle recDraw;
	private TextureRegion region;
	private Random r;
	public TextureTile(Texture texture, Rectangle innerTile, Rectangle tile)
	{
		r = new Random();		
		recDraw = new Rectangle();
		Intersector.intersectRectangles(innerTile, tile, recDraw);
		
		region = new TextureRegion(texture, (int)((recDraw.x - innerTile.x)*2)+2, 32 - (int)((recDraw.y - innerTile.y)*2) - (int)(recDraw.height*2) + 2, (int)(recDraw.width*2), (int)(recDraw.height*2));
		
		if(recDraw.height == innerTile.height && recDraw.width == innerTile.width)
		{
			float i = r.nextFloat();
			if(i > 0.5f && i < 0.7f)
				region = new TextureRegion(texture, (int)((recDraw.x - innerTile.x)*2)+ 2 + 36, 32 - (int)((recDraw.y - innerTile.y)*2) - (int)(recDraw.height*2) + 2, (int)(recDraw.width*2), (int)(recDraw.height*2));
			else if (i > 0.7f && i < 0.85f)
				region = new TextureRegion(texture, (int)((recDraw.x - innerTile.x)*2)+2 + 36, 32 - (int)((recDraw.y - innerTile.y)*2) - (int)(recDraw.height*2) + 2 + 36, (int)(recDraw.width*2), (int)(recDraw.height*2));
			else if(i > 0.85)
				region = new TextureRegion(texture, (int)((recDraw.x - innerTile.x)*2)+2, 32 - (int)((recDraw.y - innerTile.y)*2) - (int)(recDraw.height*2) + 2 + 36, (int)(recDraw.width*2), (int)(recDraw.height*2));
		}
	}
	
	public void render(SpriteBatch batch)
	{
		batch.draw(region, recDraw.x, recDraw.y, recDraw.width, recDraw.height);
	}

}
