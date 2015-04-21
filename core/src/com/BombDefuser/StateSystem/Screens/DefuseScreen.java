package com.BombDefuser.StateSystem.Screens;

import java.util.ArrayList;
import java.util.List;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.GameObject;
import com.BombDefuser.Utilities.Sladd;
import com.BombDefuser.Utilities.TexturesAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class DefuseScreen extends BaseScreen implements IScreen {
	
	private GameObject bg;
	private Texture[] cutscene;
	private TexturesAnimation animation;
	
	private Sladd[] sladd = new Sladd[5];
	private Texture klippTex;
	private List<GameObject> klippt;
	
	public DefuseScreen(){
		bg = new GameObject(BombMain.assets.get("dot.png", Texture.class));
		bg.setWidth(Globals.VIRTUAL_WIDTH);
		bg.setHeight(Globals.VIRTUAL_HEIGHT);
		bg.setColor(Color.GRAY);
		
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
		
		klippTex = BombMain.assets.get("klipp.png", Texture.class);
		klippt = new ArrayList<GameObject>();
		
		// Sladdar
		for(int i = 0; i < sladd.length; i++){
			sladd[i] = new Sladd(Globals.VIRTUAL_WIDTH/10 + Globals.VIRTUAL_WIDTH/5 * i, 0);
			switch(i){
			case 0:
				sladd[i].setColor(Color.RED);
				break;
			case 1:
				sladd[i].setColor(Color.GREEN);
				break;
			case 2:
				sladd[i].setColor(Color.YELLOW);
				break;
			case 3:
				sladd[i].setColor(Color.PINK);
				break;
			case 4:
				sladd[i].setColor(Color.BLUE);
				break;
			}
		}
	}
	
	@Override
	public void update(float delta) {
		camera.update();
		if(animation.isDone()){
			if(Gdx.input.isTouched()){
				Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(mouse);
				
				for(int i = 0; i < sladd.length; i++){
					if(!sladd[i].getKlippt() && sladd[i].getRecDraw().contains(mouse.x, mouse.y)){
						GameObject temp = new GameObject(klippTex);
						temp.setPos(new Vector2(mouse.x - klippTex.getWidth()/2, mouse.y - klippTex.getHeight()/2));
						
						klippt.add(temp);
						sladd[i].klippt();
					}
				}
			}
			
		}	else
		animation.update(delta);
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		// Draws BG
		bg.render(batch);
		
		// Draws sladdar
		for(Sladd s : sladd)
			s.render(batch);
		for(GameObject k : klippt)
			k.render(batch);
		
		// Draws animation
		animation.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
