package com.BombDefuser.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.BombDefuser.BombMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//16:9
		//config.width = 640;
		//config.height = 320;
		
		//3:2
		//config.width = 480;
		//config.height = 320;
		
		//4:3
		//config.width = 320;
		//config.height = 240;
		
		//HD Ready
		config.width = 1280;
		config.height = 720;
		config.useGL30 = false;
		config.fullscreen = true;
		config.title = "Bomb Defuser";
		if(arg.length != 0)
			BombMain.debug = true;
		new LwjglApplication(new BombMain(), config);
	}
}
