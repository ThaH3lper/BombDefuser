package com.BombDefuser.StateSystem;


public interface IState {
	
	void update(float delta);
	
	void render();
	
	void dispose();
}
