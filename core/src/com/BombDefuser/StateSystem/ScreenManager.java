package com.BombDefuser.StateSystem;

import java.io.File;

import com.BombDefuser.StateSystem.Screens.CreditsScreen;
import com.BombDefuser.StateSystem.Screens.DefuseScreen;
import com.BombDefuser.StateSystem.Screens.EndGameScreen;
import com.BombDefuser.StateSystem.Screens.GameScreen;
import com.BombDefuser.StateSystem.Screens.LoadingScreen;
import com.BombDefuser.StateSystem.Screens.MenuScreen;
import com.BombDefuser.StateSystem.Screens.LevelSelect.MapSelectScreen;

public class ScreenManager{
		
	//This is all states in one
	private IScreen currentScreen;
		
	public void update(float delta) {
		if(currentScreen != null)
			currentScreen.update(delta);
	}

	public void render() {
		if(currentScreen != null)
			currentScreen.render();
	}
	public void setState(EScreen screen)
	{
		setState(screen, null);
	}
	public void setState(EScreen screen, File file)
	{
		if(currentScreen != null)
			currentScreen.dispose();
		switch (screen) {
		case meny:
			currentScreen = new MenuScreen();
			break;
		case game:
			currentScreen = new GameScreen(file);
			break;	
		case levelselect:
			currentScreen = new MapSelectScreen();
			break;
		case endscreen:
			currentScreen = new EndGameScreen();
			break;
		case loading:
			currentScreen = new LoadingScreen();
			break;
		case defuse:
			currentScreen = new DefuseScreen();
			break;
		case credits:
			currentScreen = new CreditsScreen();
			break;
		}
	}
}
