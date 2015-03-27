package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.Button;
import com.BombDefuser.Utilities.ScrollingBackground;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class MenuScreen extends BaseScreen implements IScreen {
	
	private ScrollingBackground bg;
	private Texture logo;
	private Button btnPlay;
	
	public MenuScreen(){
		camera = new OrthographicCamera(1280, 720);
		camera.position.x += 1280/2;
		camera.position.y += 720/2;
		camera.update();
		
		bg = new ScrollingBackground(camera);
		bg.addBackground(BombMain.assets.get("background/skyline1_layer3_sky.png", Texture.class), new Vector2(0, 0), 10);
		bg.addBackground(BombMain.assets.get("background/skyline1_layer2_houses.png", Texture.class), new Vector2(0, 0), 20);
		bg.addBackground(BombMain.assets.get("background/skyline1_layer1_houses.png", Texture.class), new Vector2(0, 0), 30);
		
		btnPlay = new Button(camera, BombMain.assets.get("btnplay.png", Texture.class), 0, 0);
		btnPlay.setPosition((camera.viewportWidth - btnPlay.getWidth())/2, 200);
		
		logo = BombMain.assets.get("logo.png", Texture.class);
		
		BombMain.soundBank.playSound(ESounds.music);
	}
	
	@Override
	public void update(float delta) {
		camera.update();
		
		bg.update(delta);
		
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
		bg.draw(batch);
		batch.draw(logo, (camera.viewportWidth - logo.getWidth() * 0.5f)/2, 300, logo.getWidth() * 0.5f, logo.getHeight() * 0.5f);
		btnPlay.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}
	
}
