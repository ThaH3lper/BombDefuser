package com.BombDefuser.Utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Animation {

	int start, end, frameY, width, height, currentFrame;
	float frameTime, currentTime;
	Texture texture;
	Rectangle recSource;
	int timesDone = 0;
	
	// Added values
	int xSpacing, ySpacing, xOffset, yOffset;
	
	public Animation(Texture texture, int start, int end, int frameY, int width, int height, int xSpacing, int ySpacing, int xOffset, int yOffset, float frameTime)
	{
		this.frameY = frameY;
		this.start = start;
		this.end = end;
		this.width = width;
		this.height = height;
		this.xSpacing = xSpacing;
		this.ySpacing = ySpacing;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.frameTime = frameTime;
		this.texture = texture;
		this.currentFrame = start;
		// Note: this needs to be fixed.
		this.recSource = new Rectangle((currentFrame * (width + xSpacing)) + xOffset, (frameY * (height + ySpacing)) + yOffset, width, height);
		
	}
	
	
	
	public boolean update(float delta)
	{
		currentTime += delta;
		if(currentTime >= frameTime)
		{
			currentTime = 0;
			currentFrame++;
			if(currentFrame + 1 > end)
				timesDone++;
			if(currentFrame > end){
				currentFrame = start;
				return true;
			}
			recSource = new Rectangle((currentFrame * (width + xSpacing)) + xOffset, (frameY * (height + ySpacing)) + yOffset, width, height);
		}
		
		return false;
	}
	
	public boolean lastFrame(){
		return currentFrame > end;
	}
	
	public void setEnemyAimingPose(){
		recSource = new Rectangle(68 * 9, 68 * 2, width, height);
	}
	
	public void setCurrentFrame(int frame){
		currentFrame = frame;
	}
	
	public Rectangle getRecSource(){
		return recSource;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public void resetTimes(){
		timesDone = 0;
		currentFrame = start;
	}
	
	public int getTimes(){
		return timesDone;
	}
	
	
}
