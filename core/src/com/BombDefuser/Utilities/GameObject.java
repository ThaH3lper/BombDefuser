package com.BombDefuser.Utilities;

import com.BombDefuser.BombMain;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
	
	private Texture tex;
	protected Vector2 pos, origin;
	protected float width, height, scale, scaleX, scaleY, rotation;
	protected Rectangle recSource, recDraw;
	protected Color color;
	
	
	public GameObject(Texture tex){
		this(tex, 0, 0, tex.getWidth(), tex.getHeight(), 0, 0, tex.getWidth(), tex.getHeight(), Color.WHITE);
	}
	public GameObject(float x, float y, float width, float height){
		this(BombMain.assets.get("dot.png", Texture.class), 0, 0, 1, 1, x, y, width, height, Color.WHITE);
	}
	public GameObject(Texture tex, float sourceX, float sourceY, float sourceWidth, float sourceHeight, float x, float y, float width, float height, Color color){
		this.tex = tex;
		this.width = width;
		this.height = height;
		this.recSource = new Rectangle(sourceX, sourceY, sourceWidth, sourceHeight);
		this.recDraw = new Rectangle(x, y, width, height);
		this.pos = new Vector2(x, y);
		this.origin = new Vector2(width/2, height/2);
		this.rotation = 0f;
		this.scaleX = 1f;
		this.scaleY = 1f;
		this.color = color;
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(color);
		batch.draw(tex, pos.x, pos.y, origin.x, origin.y, width, 
				height, scaleX, scaleY, rotation, (int)recSource.x, (int)recSource.y, (int)recSource.width, (int)recSource.height, false, false);
		batch.setColor(Color.WHITE);
	}
	
	public Rectangle getRecSource(){
		return recSource;
	}
	
	public Rectangle getRecDraw(){
		return recDraw;
	}
	
	public Vector2 getCenterPosition(){
		return new Vector2(pos.x + origin.x, pos.y + origin.y);
	}
	
	public Vector2 getPos() {
		return pos;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
		this.recDraw = new Rectangle(pos.x, pos.y, recDraw.width, recDraw.height);
	}

	public Vector2 getOrigin() {
		return origin;
	}

	public void setOrigin(Vector2 origin) {
		this.origin = origin;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		this.recDraw = new Rectangle(pos.x, pos.y, width, recDraw.height);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		this.recDraw = new Rectangle(pos.x, pos.y, recDraw.width, height);
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setRecSource(Rectangle source) {
		this.recSource = source;
	}
	
	public void setTexture(Texture tex) {
		this.tex = tex;
	}

	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
}