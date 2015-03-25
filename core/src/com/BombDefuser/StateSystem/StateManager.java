package com.BombDefuser.StateSystem;

import com.BombDefuser.StateSystem.Screens.GameScreen;
import com.BombDefuser.StateSystem.Screens.LoadingScreen;
import com.BombDefuser.StateSystem.Screens.MenuState;

public class StateManager{
		
	//This is all states in one
	private IState currentState;
		
	public void update(float delta) {
		if(currentState != null)
			currentState.update(delta);
	}

	public void render() {
		if(currentState != null)
			currentState.render();
	}
	
	public void setState(EStates state)
	{
		if(currentState != null)
			currentState.dispose();
		switch (state) {
		case meny:
			currentState = new MenuState();
			break;
		case game:
			currentState = new GameScreen();
			break;
		case levelselect:
			
			break;
		case losing:
			
			break;
		case winning:
			
			break;
		case loading:
			currentState = new LoadingScreen();
			break;
		}
	}
}
