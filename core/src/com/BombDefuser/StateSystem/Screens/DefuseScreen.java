package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.IScreen;

public class DefuseScreen extends BaseScreen implements IScreen {
	
	public DefuseScreen(){
		
	}
	
	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
