package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.ScrollingBackground;
import com.BombDefuser.World.World;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends BaseScreen implements IScreen {
	
	private OrthographicCamera hudCamera;
	private World world;
	private ScrollingBackground bg;
	
	public GameScreen() {
		world = new World(-10);
		camera.position.y -= Globals.CAMERA_TOP_PADDING/2;
		camera.update();
		
		hudCamera = new OrthographicCamera(1280, 720);
		hudCamera.position.x += hudCamera.viewportWidth/2;
		hudCamera.position.y += hudCamera.viewportHeight/2;
		hudCamera.update();
		
		bg = new ScrollingBackground(hudCamera);
		bg.addBackground(BombMain.assets.get("background/skyline1_layer3_sky.png", Texture.class), new Vector2(0, 0), 10);
		bg.addBackground(BombMain.assets.get("background/skyline1_layer2_houses.png", Texture.class), new Vector2(0, 0), 20);
		bg.addBackground(BombMain.assets.get("background/skyline1_layer1_houses.png", Texture.class), new Vector2(0, 0), 30);
	}

	@Override
	public void update(float delta) {
		camera.update();
		
		hudCamera.update();
		world.update(delta);
		bg.update(delta);
	}

	@Override
	public void render() {
		
		batch.begin();
		batch.setProjectionMatrix(hudCamera.combined);
		bg.draw(batch);
		batch.setProjectionMatrix(camera.combined);
		world.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
