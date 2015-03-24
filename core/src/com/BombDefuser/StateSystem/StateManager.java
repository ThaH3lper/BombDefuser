package com.BombDefuser.StateSystem;

import com.BombDefuser.StateSystem.States.GameState;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StateManager implements IStateManager{
	
	// StateManager stuff
	private AssetManager assets;
	private EStates currentState;
	
	// All states here
	private GameState game;
	
	public StateManager(AssetManager assets){
		this.assets = assets;
		
		game = new GameState(assets);
		
		currentState = EStates.game;
	}
	
	@Override
	public void update(float delta) {
		switch (currentState) {
		case meny:
			break;
		case game:
			game.update(delta);
			break;
		case winning:
			break;
		case losing:
			break;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		switch (currentState) {
		case meny:
			break;
		case game:
			game.render(batch);
			break;
		case winning:
			break;
		case losing:
			break;
		}
	}
}
