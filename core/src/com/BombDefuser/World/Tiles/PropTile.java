package com.BombDefuser.World.Tiles;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PropTile extends GameObject implements ITile{

	private static final int size = 32;
	private Rectangle hitBox;
	
	public PropTile(int xSource, int ySource, float x, float y, float width, float height) {
		super(BombMain.assets.get("props/prop_sheet.png", Texture.class),(xSource * (size+4)) + 2, (ySource * (size+4)) + 2, size, size, x, y, width, height, Color.WHITE);
		this.hitBox = new Rectangle(x, y, width, height);
		
	}
	@Override
	public int getId() {
		return 0;
	}

	@Override
	public Boolean getIsCollision() {
		return false;
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
	}

	@Override
	public Rectangle getHitBox() {
		return hitBox;
	}

}
