package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.BombMain;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.Button;
import com.BombDefuser.Utilities.ScrollingBackground;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen extends BaseScreen implements IScreen {
	
	private Texture logo;
	private Button btnPlay;
	private Texture bg;
	
	public MenuScreen(){
		camera = new OrthographicCamera(1280, 720);
		camera.position.x += 1280/2;
		camera.position.y += 720/2;
		camera.update();
		
		btnPlay = new Button(camera, BombMain.assets.get("btnplay.png", Texture.class), 0, 0);
		btnPlay.setPosition((camera.viewportWidth - btnPlay.getWidth())/2, 200);
		
		logo = BombMain.assets.get("logo.png", Texture.class);
		bg = BombMain.assets.get("gameart.png", Texture.class);
		
		BombMain.soundBank.playSound(ESounds.music);
	}
	
	@Override
	public void update(float delta) {
		camera.update();
		
		// Buttons
		if(btnPlay.isPressed()){
			BombMain.stateManager.setState(EScreen.levelselect);
			BombMain.soundBank.playSound(ESounds.select);
		}
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bg, 0, 0, camera.viewportWidth, camera.viewportHeight);
		batch.draw(logo, (camera.viewportWidth - logo.getWidth() * 0.5f)/2, 300, logo.getWidth() * 0.5f, logo.getHeight() * 0.5f);
		btnPlay.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}
	
}
