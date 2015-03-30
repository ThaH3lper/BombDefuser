package com.BombDefuser.Utilities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ScrollingBackground {
	
	private OrthographicCamera camera;
	
	private List<Texture> layer;
	private List<Vector2> pos;
	private List<Float> speed;
	
	public ScrollingBackground(OrthographicCamera camera){
		this.camera = camera;
		
		layer = new ArrayList<Texture>();
		pos = new ArrayList<Vector2>();
		speed = new ArrayList<Float>();
	}
	
	public void addBackground(Texture tex, Vector2 pos, float speed){
		layer.add(tex);
		this.pos.add(pos);
		this.speed.add(speed);
	}
	
	public void update(float delta){
		for(int i = 0; i < layer.size(); i++){
			
			if(speed.get(i) > 0){
				if(pos.get(i).x + speed.get(i) * delta > camera.viewportWidth)
					pos.get(i).x = -layer.get(i).getWidth();
			} else if(speed.get(i) < 0){
				if(pos.get(i).x + speed.get(i) * delta < -layer.get(i).getWidth())
					pos.get(i).x = layer.get(i).getWidth();
			}
			
			
			pos.get(i).x += speed.get(i) * delta;
		}
	}
	
	public void draw(SpriteBatch batch){
		for(int i = 0; i < layer.size(); i++){
			batch.draw(layer.get(i), pos.get(i).x - layer.get(i).getWidth(), pos.get(i).y);
			batch.draw(layer.get(i), pos.get(i).x, pos.get(i).y);
			batch.draw(layer.get(i), pos.get(i).x + layer.get(i).getWidth(), pos.get(i).y);
		}
	}
}
