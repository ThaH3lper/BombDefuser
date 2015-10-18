package com.BombDefuser.Utilities;


import com.BombDefuser.BombMain;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingBackground {
	
	private OrthographicCamera camera;
	private BackgroundLayer lower, middle, top;
	
	public ScrollingBackground(OrthographicCamera camera){
		this.camera = camera;
		lower = new BackgroundLayer(BombMain.assets.get("background/skyline1_layer3_sky.png", Texture.class), 1f, 0);
		middle = new BackgroundLayer(BombMain.assets.get("background/skyline1_layer2_houses.png", Texture.class), 0.5f, 0);
		top = new BackgroundLayer(BombMain.assets.get("background/skyline1_layer1_houses.png", Texture.class), 0f, 0);
	}
	
	public void update(float delta){
		lower.update(delta, camera);
		middle.update(delta, camera);
		top.update(delta, camera);
	}
	
	public void render(SpriteBatch batch){
		lower.render(batch);
		middle.render(batch);
		top.render(batch);
	}
}
