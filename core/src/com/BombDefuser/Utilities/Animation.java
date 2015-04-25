package com.BombDefuser.Utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Animation {

	int start, end, frameY, width, height, currentFrame;
	float frameTime, currentTime;
	Texture texture;
	Rectangle recSource;
	int timesDone = 0;
	public Animation(Texture texture, int start, int end, int frameY, int width, int height, float frameTime)
	{
		this.frameY = frameY;
		this.start = start;
		this.end = end;
		this.width = width;
		this.height = height;
		this.frameTime = frameTime;
		this.texture = texture;
		this.currentFrame = start;
		this.recSource = new Rectangle((currentFrame * (width+4)) + 2, (frameY * (height+4)) + 2, width, height);
	}
	
	public void update(float delta)
	{
		currentTime += delta;
		if(currentTime >= frameTime)
		{
			currentTime = 0;
			currentFrame++;
			if(currentFrame + 1 > end)
				timesDone++;
			if(currentFrame > end)
				currentFrame = start;	
			recSource = new Rectangle((currentFrame * (width+4)) + 2, (frameY * (height+4)) + 2, width, height);
		}
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
