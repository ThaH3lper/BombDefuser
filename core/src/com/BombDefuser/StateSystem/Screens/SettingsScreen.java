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

public class SettingsScreen extends BaseScreen implements IScreen {
	
	private OrthographicCamera mCamera;
	private ScrollingBackground bg;
	
	private Button btnMenu;
	
	public SettingsScreen(){
		// init meny camera
		mCamera = new OrthographicCamera(1280, 720);
		mCamera.position.x += mCamera.viewportWidth/2;
		mCamera.position.y += mCamera.viewportHeight/2;
		mCamera.update();
		
		camera.position.y += camera.viewportHeight/2;
		
		bg = new ScrollingBackground(camera);
		btnMenu = new Button(mCamera, BombMain.assets.get("btn/btnmenu.png", Texture.class), 5, 5);
		btnMenu.setScale(0.8f);
	}
	
	@Override
	public void update(float delta) {
		mCamera.update();
		camera.update();
		
		if(btnMenu.isPressed()){
			BombMain.soundBank.playSound(ESounds.select);
			BombMain.stateManager.setState(EScreen.meny);
		}
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		bg.render(batch);
		batch.setProjectionMatrix(mCamera.combined);
		
		btnMenu.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		
	}

}
