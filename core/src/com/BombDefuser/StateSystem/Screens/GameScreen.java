package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.Globals;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.World.World;

public class GameScreen extends BaseScreen implements IScreen {
	
	private World world;
	
	public GameScreen() {
		world = new World(-10);
		camera.position.y -= Globals.CAMERA_TOP_PADDING/2;
		camera.update();
	}

	@Override
	public void update(float delta) {
		camera.update();
		world.update(delta);
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		world.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
