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
		// Zarokhan's maps
		String[] text = new String[2];
		text[0] = "Author: Zarokhan";
		text[1] = "A breif tutorial introducing main gameplay.";
		addEntry(camera, "tutorial.bdmap", 0, 0, "#0 Tutorial", text);
		text = new String[5];
		text[0] = "Author: Zarokhan";
		text[1] = "Dear Bomb Defuser, please help out Malmo of its troubles with explosives.";
		text[2] = "We need your assistance now!";
		text[3] = "Best regards";
		text[4] = "City council of Malmo";
		addEntry(camera, "map2.bdmap", 1, 0, "#1 Terrorist Outpost", text);
		addEntry(camera, "map3.bdmap", 2, 0, "#2 Skyscrapers", text);
		addEntry(camera, "map4.bdmap", 3, 0, "#3 Fan House", text);
		
		// HolyDuFF's maps
		text = new String[2];
		text[0] = "Author: HolyDuFF";
		text[1] = "House Infidels map pack.";
		addEntry(camera, "lv1.bdmap", 3, 0, "#4 House Infidels: IzI PiZi", text);
		addEntry(camera, "lv2.bdmap", 3, 0, "#5 House Infidels: Lemon Squeezy!", text);
		addEntry(camera, "lv3.bdmap", 3, 0, "#6 House Infidels: Drop", text);
		addEntry(camera, "lv4.bdmap", 3, 0, "#7 House Infidels: Climb", text);
		addEntry(camera, "lv5.bdmap", 3, 0, "#8 House Infidels: Spawn Kill", text);
		addEntry(camera, "lv6.bdmap", 3, 0, "#9 House Infidels: Long Jump", text);
		addEntry(camera, "lv7.bdmap", 3, 0, "#10 House Infidels: Dafuq?", text);
		addEntry(camera, "lv8.bdmap", 3, 0, "#11 House Infidels: Tower of do.Om!", text);
		addEntry(camera, "lv9.bdmap", 3, 0, "#12 House Infidels: CrossFire", text);
		addEntry(camera, "lv10.bdmap", 3, 0, "#13 House Infidels: CrossFireX of dO.om!", text);
		addEntry(camera, "lv11.bdmap", 3, 0, "#14 House Infidels: OMGWTFBBQ!?", text);
		
		// Old developer maps
		text = new String[1];
		text[0] = "Created by the developers of the game.";
		addEntry(camera, "2cool4school.bdmap", 4, 0, "#15 Outdated: 2cool4school", text);
		addEntry(camera, "leveleasylife.bdmap", 4, 0, "#16 Outdated: leveleasylife", text);
		addEntry(camera, "pidda_map.bdmap", 4, 0, "#17 Outdated: pidda map", text);
		addEntry(camera, "RosenGard.bdmap", 4, 0, "#18 Outdated: RosenGard", text);
		addEntry(camera, "terrorist_house.bdmap", 4, 0, "#19 Outdated: terrorist house", text);
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
