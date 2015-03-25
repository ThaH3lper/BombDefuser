package com.BombDefuser.Utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Button {
	
	private OrthographicCamera camera;
	private Texture tex;
	private Vector2 pos;
	private Rectangle bounds;
	
	public Button(OrthographicCamera camera, Texture tex, float x, float y) {
		this.camera = camera;
		this.tex = tex;
		this.pos = new Vector2(x, y);
		this.bounds = new Rectangle(x, y, tex.getWidth(), tex.getHeight());
	}
	
	public boolean isPressed(){
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouse);
		
		if(Gdx.input.isTouched() && bounds.contains(mouse.x, mouse.y)){
			return true;
		}
		
		return false;
	}
	
	public void render(SpriteBatch batch){
		batch.draw(tex, pos.x, pos.y);
	}
	
	public void dispose(){
		tex.dispose();
	}
	
	public void setPosition(float x, float y){
		this.pos = new Vector2(x, y);
		bounds.x = x;
		bounds.y = y;
	}
	
	public float getWidth(){
		return bounds.width;
	}
	
	public float getHeight(){
		return bounds.height;
	}
}
