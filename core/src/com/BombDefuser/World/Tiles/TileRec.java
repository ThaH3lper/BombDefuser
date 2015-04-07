package com.BombDefuser.World.Tiles;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class TileRec implements ITile{

	private static final float textureWidth = 16, textureHeight = 16;
	
	private TextureTile[][] innerTiles;
	protected Rectangle recHit;
	public TileRec(ETileTexture type, float x, float y, float width, float height) {
		
		Texture texture = null;
		switch (type) {
		case GRAY:
			texture = BombMain.assets.get("tiles/tile_gray.png", Texture.class);
			break;
		case GREEN:
			texture = BombMain.assets.get("tiles/tile_green.png", Texture.class);
			break;
		case RED:
			texture = BombMain.assets.get("tiles/tile_red.png", Texture.class);
			break;
		case FAN:
			texture = BombMain.assets.get("tiles/tile_fan.png", Texture.class);
			break;
		}
		
		this.recHit = new Rectangle(x, y, width, height);
		
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
		
		int xAmount = (int)((x3 + width)/textureWidth) + 1;
		int yAmount = (int)((y3 + height)/textureHeight) + 1;
		
		innerTiles = new TextureTile[xAmount][yAmount];
		for (int currentX = 0; currentX < innerTiles.length; currentX++) {
			for (int currentY = 0; currentY < innerTiles[0].length; currentY++) {
				Rectangle innerTile = new Rectangle((x2 + currentX) * textureWidth, (y2 + currentY) * textureHeight, textureWidth, textureHeight);
				innerTiles[currentX][currentY] = new TextureTile(texture, innerTile, new Rectangle(x, y, width, height));
			}
		}
		//End of algorithm!
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
			}
		}
	}

	@Override
	public Rectangle getHitBox() {
		return recHit;
	}
}