package com.BombDefuser.StateSystem.Screens.LevelSelect;

import com.BombDefuser.BombMain;
import com.BombDefuser.Utilities.Button;
import com.BombDefuser.Utilities.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MapEntry {
	Texture img;
	String level;
	String title;
	String[] text;
	Rectangle bounds, source;
	GameObject bglayer;
	BitmapFont titleFont;
	BitmapFont textFont;
	Button btn;
	
	public MapEntry(OrthographicCamera camera, Texture img, Rectangle bounds, Rectangle source, String level, String title, String[] text){
		this.img = img;
		this.bounds = bounds;
		this.source = source;
		this.level = level;
		this.title = title;
		this.text = text;
		this.titleFont = BombMain.assets.get("font.fnt", BitmapFont.class);
		this.textFont = BombMain.assets.get("arial16.fnt", BitmapFont.class);
		
		btn = new Button(camera, BombMain.assets.get("dot.png", Texture.class), bounds.x, bounds.y);
		btn.setBounds(bounds.width - 152, bounds.height);
		btn.setAlpha(0.0f);
		
		bglayer = new GameObject(BombMain.assets.get("dot.png", Texture.class));
		bglayer.setWidth(bounds.width);
		bglayer.setHeight(bounds.height);
		bglayer.setColor(new Color(0, 0, 0, 0));
		bglayer.setAlpha(0.7f);
		bglayer.setPos(new Vector2(bounds.x, bounds.y));
	}
	
	public boolean isPressed(){
		return btn.isPressed();
	}
	
	public void render(SpriteBatch batch){
		// Draw bg
		bglayer.render(batch);
		// Draw image
		batch.draw(img, (int)bounds.x, (int)bounds.y, (int)source.width, (int)source.width, (int)source.x, (int)source.y, (int)source.width, (int)source.width, false, false);
		// Draw title
		titleFont.draw(batch, title, bounds.x + source.width + 30, bounds.y + bounds.height - titleFont.getBounds("Y").height);
		// Draw text
		for (int i = 0; i < text.length; i++) {
			textFont.draw(batch, text[i], bounds.x + source.width + 45, bounds.y + bounds.height - titleFont.getBounds("Y").height - 30 - ((textFont.getBounds(text[i]).height + 7) * i));
		}
	}
}
