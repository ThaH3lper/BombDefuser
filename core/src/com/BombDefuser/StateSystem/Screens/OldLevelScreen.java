package com.BombDefuser.StateSystem.Screens;

import java.io.File;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.Button;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class OldLevelScreen extends BaseScreen implements IScreen {
	
	private Texture europe;
	private Texture red_dot;
	
	private Button btnMenu;
	private Button btnLevel0, btnLevel1, btnLevel2, btnLevel3, btnLevel4;
	
	public OldLevelScreen(){
		// Override current camera
		camera = new OrthographicCamera(1280, 720);
		camera.position.x = camera.viewportWidth/2;
		camera.position.y = camera.viewportHeight/2;
		camera.update();
		
		// Get level screen textures 
		europe = BombMain.assets.get("europe.png", Texture.class);
		red_dot = BombMain.assets.get("dotselect.png", Texture.class);
		
		/*
		 * Buttons
		 */
		
		btnMenu = new Button(camera, BombMain.assets.get("btn/btnmenu.png", Texture.class), 5, 5);
		btnMenu.setScale(0.6f);
		
		btnLevel0 = new Button(camera, red_dot, camera.viewportWidth/2 - 50, camera.viewportHeight/2 + 100);
		btnLevel1 = new Button(camera, red_dot, camera.viewportWidth/2 - 380, camera.viewportHeight/2 + 70);
		btnLevel2 = new Button(camera, red_dot, camera.viewportWidth/2 - 200, camera.viewportHeight/2 -200);
		btnLevel3 = new Button(camera, red_dot, camera.viewportWidth/2 - 130, camera.viewportHeight/2 + 180);
		btnLevel4 = new Button(camera, red_dot, camera.viewportWidth/2 - 300, camera.viewportHeight/2);
	}
	
	@Override
	public void update(float delta) {
		if(btnMenu.isHoldDown())
			BombMain.stateManager.setState(EScreen.meny);
		
		if(btnLevel0.isHoldDown())
			setLevel("2cool4school.bdmap");
		
		if(btnLevel1.isHoldDown()){
			setLevel("leveleasylife.bdmap");
		}
		if(btnLevel2.isHoldDown()){
			setLevel("pidda_map.bdmap");
		}
		if(btnLevel3.isHoldDown()){
			setLevel("RosenGard.bdmap");
		}
		if(btnLevel4.isHoldDown()){
			setLevel("terrorist_house.bdmap");
		}
	}
	
	private void setLevel(String level)
	{
		Globals.currentLevel = level;
		BombMain.stateManager.setState(EScreen.game, new File("levels\\" + level));
		BombMain.soundBank.playSound(ESounds.select);
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(europe, 0, 0, camera.viewportWidth, camera.viewportHeight);
		btnLevel1.render(batch);
		btnMenu.render(batch);
		btnLevel2.render(batch);
		btnLevel3.render(batch);
		btnLevel0.render(batch);
		btnLevel4.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
