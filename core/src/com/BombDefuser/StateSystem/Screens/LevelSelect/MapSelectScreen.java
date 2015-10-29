package com.BombDefuser.StateSystem.Screens.LevelSelect;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.Button;
import com.BombDefuser.Utilities.ScrollingBackground;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MapSelectScreen extends BaseScreen implements IScreen {
	
	private OrthographicCamera bgCamera;
	private ScrollingBackground bg;
	private MapHandler maps;
	private Button nextBtn;
	
	public MapSelectScreen(){
		bgCamera = new OrthographicCamera(Globals.VIRTUAL_WIDTH, Globals.VIRTUAL_HEIGHT);
		bgCamera.position.x += Globals.VIRTUAL_WIDTH/2;
		bgCamera.position.y += Globals.VIRTUAL_HEIGHT/2;
		bg = new ScrollingBackground(bgCamera);
		
		camera = new OrthographicCamera(1280, 720);
		camera.position.x += 1280/2;
		camera.position.y += 720/2;
		camera.update();
		
		maps = new MapHandler();
		maps.init(camera);
		
		nextBtn = new Button(camera, BombMain.assets.get("btn/btnnext.png", Texture.class), 0, 0);
		nextBtn.setPosition(1280 - nextBtn.getWidth(), 0);
	}
	
	@Override
	public void update(float delta) {
		camera.update();
		bgCamera.update();
		if(bgCamera.position.x > 1750)
			bgCamera.position.x = -500;
		bgCamera.position.x += 10 * delta;
		
		if(nextBtn.isPressed()){
			BombMain.soundBank.playSound(ESounds.select);
			maps.nextPage();
		}
		else{
			maps.update(delta);		
		}
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(bgCamera.combined);
		batch.begin();
		bg.render(batch);
		batch.setProjectionMatrix(camera.combined);
		maps.render(batch);
		nextBtn.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		
	}
}
