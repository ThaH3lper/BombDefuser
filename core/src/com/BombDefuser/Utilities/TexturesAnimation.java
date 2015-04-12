package com.BombDefuser.Utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TexturesAnimation {
	
	private Texture[] textures;
	private int index;
	private float interval, timer;
	private boolean isDone;
	
	public TexturesAnimation(Texture[] textures, float interval){
		this.textures = textures;
		this.interval = interval;
		init();
	}
	
	private void init(){
		index = 0;
		timer = 0;
		isDone = false;
	}
	
	public boolean isDone(){
		return isDone;
	}
	
	public void update(float delta){
		if(!isDone)
			timer += delta;
			
		if(timer >= interval){
			index++;
			timer = 0;
			if(index >= textures.length)
				isDone = true;
		}
	}
	
	public void render(SpriteBatch batch){
		if(!isDone)
			batch.draw(textures[index], 0, 0);
	}
}
