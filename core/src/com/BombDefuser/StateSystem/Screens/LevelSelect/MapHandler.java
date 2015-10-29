package com.BombDefuser.StateSystem.Screens.LevelSelect;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.BombDefuser.BombMain;
import com.BombDefuser.Globals;
import com.BombDefuser.SoundManager.ESounds;
import com.BombDefuser.StateSystem.EScreen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MapHandler {
	
	List<MapEntry> entry;
	int width, height;
	
	public MapHandler() {
		entry = new ArrayList<MapEntry>();
		width = 1280;
		height = 720/4;
	}
	
	public void init(OrthographicCamera camera){
		String[] text = new String[2];
		text[0] = "Author: Zarokhan";
		text[1] = "A breif tutorial introducing main gameplay.";
		addEntry(camera, "tutorial.bdmap", 0, 0, "Tutorial", text);
		addEntry(camera, "map2.bdmap", 1, 0, "Map 2", text);
		addEntry(camera, "map3.bdmap", 2, 0, "Map 3", text);
		addEntry(camera, "map4.bdmap", 3, 0, "Map 4", text);
		
		// Old maps
		text = new String[3];
		text[0] = "Created by one of the developers of the game.";
		text[1] = "At of this moment they are outdated because of";
		text[2] = "poor level design and changes made to the game.";
		addEntry(camera, "2cool4school.bdmap", 4, 0, "Outdated: 2cool4school", text);
		addEntry(camera, "leveleasylife.bdmap", 4, 0, "Outdated: leveleasylife", text);
		addEntry(camera, "pidda_map.bdmap", 4, 0, "Outdated: pidda map", text);
		addEntry(camera, "RosenGard.bdmap", 4, 0, "Outdated: RosenGard", text);
		addEntry(camera, "terrorist_house.bdmap", 4, 0, "Outdated: terrorist house", text);
	}
	
	public void nextPage(){
		if(Globals.endIndex + 4 <= entry.size()){
			Globals.startIndex += 4;
			Globals.endIndex += 4;
		} else if (Globals.endIndex + 4 > entry.size() && Globals.endIndex < entry.size()){
			Globals.startIndex = Globals.endIndex;
			Globals.endIndex = entry.size();
		} else{
			Globals.startIndex = 0;
			Globals.endIndex = 4;
		}
	}
	
	public void addEntry(OrthographicCamera camera, String level, int col, int row, String title, String[] text){
		int i = entry.size() % 4;
		entry.add(new MapEntry(camera, BombMain.assets.get("maps.png", Texture.class), new Rectangle(0, 720 - ((i + 1) * height), width, height), GetImage(col, row), level, title, text));
	}
	
	private Rectangle GetImage(int col, int row){
		return new Rectangle(col * 180, row * 180, 180, 180);
	}
	
	public void update(float delta){
		for(int i = Globals.startIndex; i < Globals.endIndex; i++){
			if(entry.get(i).isPressed())
				setLevel(entry.get(i).level);
		}
	}
	
	private void setLevel(String level)
	{
		Globals.currentLevel = level;
		BombMain.stateManager.setState(EScreen.game, new File("levels\\" + level));
		BombMain.soundBank.playSound(ESounds.select);
	}
	
	public void render(SpriteBatch batch){
		for (int i = Globals.startIndex; i < Globals.endIndex; i++) {
			entry.get(i).render(batch);
		}
	}
}
