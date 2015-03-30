package com.BombDefuser.World.Tiles;

import com.BombDefuser.Utilities.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class TileRec extends GameObject implements ITile{

	private static final float textureWidth = 48, textureHeight = 32;
	
	private TextureTile[][] innerTiles;
	public TileRec(Texture texture, float x, float y, float width, float height) {
		super(texture, 0, 0, 1, 1, x, y, width, height, Color.GRAY);
		
		//Algorithm that defines close "inner tiles" and adds them to list.
		//Link to picture over variables:
		//https://malmuniversity.basecamphq.com/projects/12749126/file/202032377/IMG_20150330_145751.jpg
		
		float x2 = (int) (x / textureWidth);
		float y2 = (int) (y / textureHeight);
		if(x < 0 && x % textureWidth != 0)
			x2--;
		if(y < 0 && y % textureHeight != 0)
			y2--;
		
		float x3 = x - (x2 * textureWidth);
		float y3 = y - (y2 * textureHeight);
		
		int xAmount = (int)((x3 + width)/textureWidth);
		int yAmount = (int)((y3 + height)/textureHeight);
		if(x % textureWidth != 0)
			xAmount++;
		if(y % textureHeight != 0)
			yAmount++;
		
		innerTiles = new TextureTile[xAmount][yAmount];
		for (int currentX = 0; currentX < innerTiles.length; currentX++) {
			for (int currentY = 0; currentY < innerTiles[0].length; currentY++) {
				innerTiles[currentX][currentY] = new TextureTile(texture, (x2 + currentX) * textureWidth, (y2 + currentY) * textureHeight, textureWidth, textureHeight, x, y, width, height);
			}
		}
		//End of algorithm!
	}
	
	public void updatePos(float x, float y){
		this.pos.x = x;
		this.pos.y = y;
	}
	
	@Override
	public int getId() {
		return 0;
	}


	@Override
	public Boolean getIsCollision() {
		return true;
	}

	@Override
	public void update(float delta) {
		//Moving Platforms needs update etc...
	}

	@Override
	public void render(SpriteBatch batch) {
		for (int currentX = 0; currentX < innerTiles.length; currentX++) {
			for (int currentY = 0; currentY < innerTiles[0].length; currentY++) {
				innerTiles[currentX][currentY].render(batch);
				//System.out.println(currentX + " " + currentY);
			}
		}
		super.render(batch);
	}

	@Override
	public Rectangle getHitBox() {
		return super.getRecDraw();
	}
}