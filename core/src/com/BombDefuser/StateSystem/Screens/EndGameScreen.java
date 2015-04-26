package com.BombDefuser.StateSystem.Screens;

import java.io.File;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.Button;
import com.BombDefuser.Utilities.GameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public class EndGameScreen extends BaseScreen implements IScreen {
	
	private OrthographicCamera mCamera;
	private GameObject logo;
	private GameObject bglayer;
	private Texture bg;
	
	private BitmapFont titleFont, font;
	private String title, para;
	
	private Button btnRetry, btnLevels;
	
	public EndGameScreen(){
		mCamera = new OrthographicCamera(1280, 720);
		mCamera.position.x += 1280/2;
		mCamera.position.y += 720/2;
		mCamera.update();
		
		camera.position.x += camera.viewportWidth/2;
		camera.position.y += camera.viewportHeight/2;
		camera.update();
		
		bg = BombMain.assets.get("gameart.png", Texture.class);
		titleFont = BombMain.assets.get("arial64white.fnt", BitmapFont.class);
		para = "";
		font = BombMain.assets.get("font.fnt", BitmapFont.class);
		
		logo = new GameObject(BombMain.assets.get("logo.png", Texture.class));
		logo.setScaleX(0.4f);
		logo.setScaleY(0.4f);
		logo.setPos(new Vector2(-350, 150));
		bglayer = new GameObject(BombMain.assets.get("dot.png", Texture.class));
		bglayer.setWidth(mCamera.viewportWidth * 0.62f);
		bglayer.setHeight(mCamera.viewportHeight * 0.3f);
		bglayer.setColor(new Color(0, 0, 0, 0));
		bglayer.setAlpha(0.7f);
		bglayer.setPos(new Vector2(200, 280));
		
		// Create buttons
		btnRetry = new Button(mCamera, BombMain.assets.get("btn/btnretry.png", Texture.class), 0, 0);
		btnRetry.setScale(0.7f);
		btnRetry.setPosition(bglayer.getX(), bglayer.getY());
		
		btnLevels = new Button(mCamera, BombMain.assets.get("btn/btnlevels.png", Texture.class), 0, 0);
		btnLevels.setScale(0.7f);
		btnLevels.setPosition(bglayer.getX() + btnRetry.getWidth(), bglayer.getY());
		
		// Stop all the music
		BombMain.soundBank.stopMusic();
		
		// Set values depending on player made it through the level
		if(Globals.failed){
			BombMain.soundBank.playSound(ESounds.explosion);
			
			title = "Bomb Exploded - You Failed! :(";
			if(Globals.cutWrongWire)
				para = "You cut the wrong wire. You exploded into thousand of bits...";
			if(Globals.runOutOfTime)
				para = "You ran out of time. You exploded into thousand of bits...";
			if(Globals.deadFromEnemies)
				para = "Hammered by terrorist. Lägg till sen...";
		} else {
			title = "Bomb Defused - You Won! :)";
			para = "Wow! Good job son, you made it!";
		}
	}
	
	@Override
	public void update(float delta) {
		mCamera.update();
		camera.update();
		
		if(btnLevels.isPressed()){
			BombMain.stateManager.setState(EScreen.levelselect);
		}
		
		if(btnRetry.isPressed()){
			BombMain.stateManager.setState(EScreen.game, new File("levels\\" + Globals.currentLevel));
			BombMain.soundBank.playSound(ESounds.select);
		}
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// BG STUFF
		batch.draw(bg, 0, 0, camera.viewportWidth, camera.viewportHeight);
		
		// MENU STUFF
		batch.setProjectionMatrix(mCamera.combined);
		bglayer.render(batch);
		
		// TEXT STUFF
		titleFont.setColor(Color.WHITE);
		titleFont.draw(batch, title, 220, 475);
		font.draw(batch, para, 220, 410);
		
		// Buttons
		btnRetry.render(batch);
		btnLevels.render(batch);
		
		logo.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		
	}

}
