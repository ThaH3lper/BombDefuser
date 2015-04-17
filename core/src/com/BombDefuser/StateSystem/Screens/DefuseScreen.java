package com.BombDefuser.StateSystem.Screens;

import com.BombDefuser.BombMain;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.TexturesAnimation;
import com.badlogic.gdx.graphics.Texture;

public class DefuseScreen extends BaseScreen implements IScreen {
	
	private Texture[] cutscene;
	private TexturesAnimation animation;
	
	public DefuseScreen(){
		// Camera fix
		camera.position.x += camera.viewportWidth/2f;
		camera.position.y += camera.viewportHeight/2f;
		camera.update();
		
		// Cutscene
		cutscene = new Texture[70];
		for(int i = 0; i < cutscene.length; i++){
			String t = "" + i;
			if(i < 10)
				t = "0" + i;
			cutscene[i] = BombMain.assets.get("bombcutscene/Final_Animation00" + t + ".jpg", Texture.class);
		}
		animation = new TexturesAnimation(cutscene, 1/24f);
	}
	
	@Override
	public void update(float delta) {
		if(animation.isDone()){
			
			
			
		}	else
		animation.update(delta);
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		animation.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
