package com.BombDefuser.Utilities;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Sladd extends GameObject {
	
	private boolean klippt;
	
	public Sladd(float x, float y) {
		super(BombMain.assets.get("slad.png", Texture.class));
		this.pos = new Vector2(x, y);
		this.recDraw.x = pos.x;
		this.recDraw.y = pos.y;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void klippt(){
		klippt = true;
	}
	
	public boolean getKlippt(){
		return klippt;
	}
}
