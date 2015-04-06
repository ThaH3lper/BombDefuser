package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.Globals;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.World.World;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class GameScreen extends BaseScreen implements IScreen {
	
	private OrthographicCamera hudCamera;
	private World world;
	
	private float lerp = 0.1f;
	
	public GameScreen() {
		world = new World(-10);
		camera.position.y -= Globals.CAMERA_TOP_PADDING/2;
		camera.update();
		
		hudCamera = new OrthographicCamera(1280, 720);
		hudCamera.position.x += hudCamera.viewportWidth/2;
		hudCamera.position.y += hudCamera.viewportHeight/2;
		hudCamera.update();
		
		//world.addEnemy(10, 400, 40, 40, 20, Color.WHITE);
	}

	@Override
	public void update(float delta) {
		world.update(delta, camera);
		
		hudCamera.update();
		Vector3 position = camera.position;
		position.x += (world.getHero().getPos().x - position.x) * lerp;
		position.y += (world.getHero().getPos().y - position.y) * lerp;
		camera.update();
	}

	@Override
	public void render() {
		
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		world.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
