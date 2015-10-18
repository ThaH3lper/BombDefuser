package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.SoundManager.EMusic;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.Button;
import com.BombDefuser.Utilities.GameObject;
import com.BombDefuser.Utilities.ScrollingBackground;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class MenuScreen extends BaseScreen implements IScreen {
	
	private ScrollingBackground bg;
	private OrthographicCamera bgCamera;
	private GameObject logo;
	private Button btnPlay, btnCredits, btnMuteMusic, btnMuteSound;
	private BitmapFont font;
	
	private float rotation;
	private float rotVel;
	
	public MenuScreen(){
		rotVel = -1.5f;
		
		bgCamera = new OrthographicCamera(Globals.VIRTUAL_WIDTH, Globals.VIRTUAL_HEIGHT);
		bgCamera.position.x += bgCamera.viewportWidth/2;
		bgCamera.position.y += bgCamera.viewportHeight/2;
		bgCamera.update();
		
		bg = new ScrollingBackground(bgCamera);
		
		camera = new OrthographicCamera(1280, 720);
		camera.position.x += 1280/2;
		camera.position.y += 720/2;
		camera.update();
		
		float yPadding = 20;
		
		btnCredits = new Button(camera, BombMain.assets.get("btn/btncredits.png", Texture.class), 0, 0);
		btnCredits.setPosition((camera.viewportWidth - btnCredits.getWidth())/2, yPadding);
		
		btnPlay = new Button(camera, BombMain.assets.get("btn/btnplay.png", Texture.class), 0, 0);
		btnPlay.setPosition((camera.viewportWidth - btnPlay.getWidth())/2, yPadding * 2 + btnCredits.getHeight());
		
		btnMuteMusic = new Button(camera, BombMain.assets.get("icons.png", Texture.class), 0, 0);
		btnMuteMusic.setSource(0, 0, 128, 128);
		
		logo = new GameObject(BombMain.assets.get("logo.png", Texture.class));
		logo.translatePositon(0, 120);
		logo.setScaleX(0.5f);
		logo.setScaleY(0.5f);
		
		font = BombMain.assets.get("font.fnt", BitmapFont.class);
		
		BombMain.soundBank.playMusic(EMusic.music);
	}
	
	@Override
	public void update(float delta) {
		if(rotation > 5 && rotVel > 0 || rotation < -2 && rotVel < 0)
			rotVel = -rotVel;
		
		rotation += rotVel * delta;
		logo.setRotation(rotation);
		
		camera.update();
		bgCamera.update();
		if(bgCamera.position.x > 1750)
			bgCamera.position.x = -500;
		bgCamera.position.x += 10 * delta;
			
		// Buttons
		// Play btn
		if(btnPlay.isPressed()){
			BombMain.stateManager.setState(EScreen.levelselect);
			BombMain.soundBank.playSound(ESounds.select);
		}
		// Credits btn
		if(btnCredits.isPressed()){
			BombMain.stateManager.setState(EScreen.credits);
			BombMain.soundBank.playSound(ESounds.select);
		}
		if(btnMuteMusic.isPressed())
		{
			BombMain.soundBank.isMusicMuted = !BombMain.soundBank.isMusicMuted;
			if(BombMain.soundBank.isMusicMuted)
			{
				btnMuteMusic.setSource(128, 0, 128, 128);
				BombMain.soundBank.stopMusic();
			}
			else
			{
				btnMuteMusic.setSource(0, 0, 128, 128);
				BombMain.soundBank.playMusic(EMusic.music);
			}
		}
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(bgCamera.combined);
		batch.begin();
		bg.render(batch);
		batch.setProjectionMatrix(camera.combined);
		logo.render(batch);
		//batch.draw(logo, (camera.viewportWidth - logo.getWidth() * 0.5f)/2, 300, logo.getWidth() * 0.5f, logo.getHeight() * 0.5f);
		btnPlay.render(batch);
		btnCredits.render(batch);
		btnMuteMusic.renderWithSourceRec(batch);
		font.draw(batch, "V " + BombMain.GAME_VERSION, 5, 25);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}
	
}
