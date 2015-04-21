package com.BombDefuser.StateSystem.Screens;

import java.util.ArrayList;
import java.util.List;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.StateSystem.BaseScreen;
import com.BombDefuser.StateSystem.EScreen;
import com.BombDefuser.StateSystem.IScreen;
import com.BombDefuser.Utilities.GameObject;
import com.BombDefuser.Utilities.TexturesAnimation;
import com.BombDefuser.World.Bomb.Sladd;
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
	
	private int active;
	private Sladd[] sladd = new Sladd[5];
	private Texture klippTex;
	private List<GameObject> klippt;
	private List<Integer> beenActive = new ArrayList<Integer>();
	
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
			Color c = Color.BLACK;
			switch(i){
			case 0:
				c = Color.RED;
				break;
			case 1:
				c = Color.GREEN;
				break;
			case 2:
				c = Color.YELLOW;
				break;
			case 3:
				c = Color.PINK;
				break;
			case 4:
				c = Color.BLUE;
				break;
			}
			sladd[i] = new Sladd(Globals.VIRTUAL_WIDTH/10 + Globals.VIRTUAL_WIDTH/5 * i, 0, c);
		}
		active = BombMain.rnd.nextInt(5);
		sladd[active].setActive();
	}
	
	@Override
	public void update(float delta) {
		camera.update();
		if(animation.isDone()){
			if(Gdx.input.isTouched()){
				Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(mouse);
				
				for(int i = 0; i < sladd.length; i++){
					if(!sladd[i].getKlippt()){
						if(sladd[i].getRecDraw().contains(mouse.x, mouse.y)){
							GameObject temp = new GameObject(klippTex);
							temp.setPos(new Vector2(mouse.x - klippTex.getWidth()/2, mouse.y - klippTex.getHeight()/2));
							
							klippt.add(temp);
							sladd[i].klippt();
							beenActive.add(active);
							
							if(beenActive.size() == 5)
								BombMain.stateManager.setState(EScreen.game);
							else{
								// Next sladd
								active = BombMain.rnd.nextInt(5);
								checkSameInstanse();
								System.out.println("" + active);
								sladd[active].setActive();
							}
							
						}
					}
				}
			}
			
		}	else
		animation.update(delta);
	}
	
	private void checkSameInstanse(){
		for(int t = 0; t < beenActive.size(); t++){
			if(beenActive.get(t) == active){
				t = 0;
				active = BombMain.rnd.nextInt(5);
				checkSameInstanse();
			}
		}
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
