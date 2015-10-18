package com.BombDefuser.Utilities;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
	private float width, height;
	private Rectangle bounds;
	private Rectangle source;
	private Color color;
	
	public Button(OrthographicCamera camera, Texture tex, float x, float y) {
		this.camera = camera;
		this.tex = tex;
		this.width = tex.getWidth();
		this.height = tex.getHeight();
		this.pos = new Vector2(x, y);
		this.bounds = new Rectangle(x, y, tex.getWidth(), tex.getHeight());
		this.source = new Rectangle(0, 0, tex.getWidth(), tex.getHeight());
		
		color = new Color(1, 1, 1, 1);
	}

    Vector3 p;
	
	public boolean isPressed(){
        if(Gdx.app.getType() == Application.ApplicationType.Android){
            return isPressedAndroid();
        }

        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
        	return isPressedDesktop();
        }
		
		return false;
	}
	
	private boolean isPressedDesktop(){
        Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mouse);

        if(Gdx.input.isTouched() && bounds.contains(mouse.x, mouse.y)){
            return true;
        }
		
		return false;
	}
	
	private boolean isPressedAndroid(){
		
		for(int i = 0; i < 4; i++){
			if(Gdx.input.isTouched(i)){
				Vector3 p = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0);
				camera.unproject(p);
				
				if(bounds.contains(p.x, p.y))
					return true;
			}
		}
		
		return false;
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(color);
		batch.draw(tex, pos.x, pos.y, bounds.width, bounds.height);
		batch.setColor(Color.WHITE);
	}
	
	public void renderWithSourceRec(SpriteBatch batch)
	{
		batch.setColor(color);
		batch.draw(tex, pos.x, pos.y, source.width/2, source.height/2, bounds.width, bounds.height, 1, 1, 0, (int)source.x, (int)source.y, (int)source.width, (int)source.height, false, false);
		batch.setColor(Color.WHITE);
	}
	
	public void dispose(){
		tex.dispose();
	}
	
	public void setSource(int x, int y, int width, int height)
	{
		this.source = new Rectangle(x, y, width, height);
		this.bounds = new Rectangle(pos.x, pos.y, width, width);
	}
	
	public void setBounds(float width, float height){
		this.bounds.width = width;
		this.bounds.height = height;
	}

    public void setAlpha(float value){
        color.a = value;
    }

	public void setScale(float value){
		this.bounds.width = width * value;
		this.bounds.height = height * value;
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
