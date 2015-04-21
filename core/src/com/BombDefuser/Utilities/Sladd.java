package com.BombDefuser.Utilities;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Sladd extends GameObject {
	
	private boolean klippt;
	private Color activeColor;
	
	public Sladd(float x, float y, Color activeColor) {
		super(BombMain.assets.get("slad.png", Texture.class));
		this.pos = new Vector2(x, y);
		this.recDraw.x = pos.x;
		this.recDraw.y = pos.y;
		this.activeColor = activeColor;
		this.color = Color.LIGHT_GRAY;
	}
	
	public void klippt(){
		klippt = true;
	}
	
	public boolean getKlippt(){
		return klippt;
	}
}
