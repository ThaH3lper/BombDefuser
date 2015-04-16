package com.BombDefuser.StateSystem;

import com.BombDefuser.StateSystem.Screens.DefuseScreen;
import com.BombDefuser.StateSystem.Screens.GameScreen;
import com.BombDefuser.StateSystem.Screens.LevelScreen;
import com.BombDefuser.StateSystem.Screens.LoadingScreen;
import com.BombDefuser.StateSystem.Screens.MenuScreen;
import com.badlogic.gdx.graphics.OrthographicCamera;

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
		if(currentScreen != null)
			currentScreen.dispose();
		switch (screen) {
		case meny:
			currentScreen = new MenuScreen();
			break;
		case game:
			currentScreen = new GameScreen();
			break;
		case levelselect:
			currentScreen = new LevelScreen();
			break;
		case losing:
			
			break;
		case winning:
			
			break;
		case loading:
			currentScreen = new LoadingScreen();
			break;
		case defuse:
			currentScreen = new DefuseScreen();
			break;
		}
	}

    public OrthographicCamera getHudCamera(){
        if(currentScreen instanceof GameScreen){
            return ((GameScreen) currentScreen).getHudCamera();
        }
        return new OrthographicCamera(0, 0);
    }
}
