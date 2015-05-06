package com.BombDefuser.StateSystem.Screens;

import java.io.File;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.Load.Load;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.World.World;
import com.BombDefuser.World.Hud.Hud;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class GameScreen extends BaseScreen implements IScreen {
	
	private OrthographicCamera hudCamera;
	private World world;
	private Hud hud;
	
	private float lerp = 0.1f;
	
	public GameScreen(File file) {
		world = Load.mapToWorld(file);
		world.init();
		
		hud = new Hud(world);
		
		camera.position.x += Globals.CAMERA_SIDE_PADDING/2;
		camera.update();
		
		hudCamera = new OrthographicCamera(1280, 720);
		hudCamera.position.x += hudCamera.viewportWidth/2;
		hudCamera.position.y += hudCamera.viewportHeight/2;
		hudCamera.update();
		
		//world.addEnemy(10, 400, 40, 40, 20, Color.WHITE);
		Globals.gameReset();
		
		// Sound 
		BombMain.soundBank.stopMusic();
		BombMain.soundBank.playSound(ESounds.music2);
	}

	@Override
	public void update(float delta) {
		world.update(delta, camera);
		hud.update(delta);
		
		hudCamera.update();
		Vector3 position = camera.position;
		position.x += (world.getHero().getPos().x - position.x) * lerp;
		//position.y += (world.getHero().getPos().y - position.y) * lerp;
		camera.update();
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		world.render(batch);
		batch.setProjectionMatrix(hudCamera.combined);
		hud.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

    public OrthographicCamera getHudCamera(){
        return hudCamera;
    }
}
