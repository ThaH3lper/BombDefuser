package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.BombMain;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.Button;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class LevelScreen extends BaseScreen implements IScreen {
	
	private Texture europe;
	private Texture red_dot;
	
	private Button btnLevel1;
	
	public LevelScreen(){
		camera = new OrthographicCamera(1280, 720);
		camera.position.x = camera.viewportWidth/2;
		camera.position.y = camera.viewportHeight/2;
		camera.update();
		
		europe = BombMain.assets.get("europe.png", Texture.class);
		red_dot = BombMain.assets.get("dotselect.png", Texture.class);
		
		btnLevel1 = new Button(camera, red_dot, 722, 515);
	}
	
	@Override
	public void update(float delta) {
		if(btnLevel1.isPressed()){
			BombMain.stateManager.setState(EScreen.game);
			BombMain.soundBank.playSound(ESounds.select);
		}
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(europe, 0, 0);
		btnLevel1.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
