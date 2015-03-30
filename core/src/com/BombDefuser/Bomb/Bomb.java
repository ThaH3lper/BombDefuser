package com.BombDefuser.Bomb;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bomb extends GameObject {
	
	private BitmapFont font;
	private float explodeTime, explodeTimer;
	
	public Bomb(float x, float y, float explodeTime) {
		super(BombMain.assets.get("case.png", Texture.class));
		this.pos.x = x;
		this.pos.y = y;
		recDraw.x = x;
		recDraw.y = y;
		this.explodeTime = explodeTime;
		this.font = BombMain.assets.get("font.fnt", BitmapFont.class);
		font.setScale(0.5f);
		color = Color.WHITE;
	}
	
	public void reset(){
		explodeTimer = 0;
	}
	
	public boolean isExploded(){
		return explodeTimer >= explodeTime;
	}
	
	public void update(float delta){
		explodeTimer += delta;
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
		font.draw(batch, "" + (int)(explodeTime - explodeTimer), pos.x, pos.y + 10);
	}
}