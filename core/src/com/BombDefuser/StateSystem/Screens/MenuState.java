package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.StateSystem.BaseState;
import com.BombDefuser.StateSystem.EStates;
import com.BombDefuser.StateSystem.IState;
import com.BombDefuser.Utilities.Button;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MenuState extends BaseState implements IState {
	
	private Texture logo, bg;
	private Button btnPlay;
	
	public MenuState(){
		camera = new OrthographicCamera(1280, 720);
		camera.position.x += 1280/2;
		camera.position.y += 720/2;
		camera.update();
		
		btnPlay = new Button(camera, BombMain.assets.get("btnplay.png", Texture.class), 0, 0);
		btnPlay.setPosition((camera.viewportWidth - btnPlay.getWidth())/2, 200);
		logo = BombMain.assets.get("logo.png", Texture.class);
		bg = BombMain.assets.get("bg.png", Texture.class);
	}
	
	@Override
	public void update(float delta) {
		camera.update();
		
		if(btnPlay.isPressed())
			BombMain.stateManager.setState(EStates.game);
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bg, 0, -100, bg.getWidth() * 1.75f, bg.getHeight() * 1.75f);
		batch.draw(logo, (camera.viewportWidth - logo.getWidth() * 0.5f)/2, 300, logo.getWidth() * 0.5f, logo.getHeight() * 0.5f);
		btnPlay.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}
	
}