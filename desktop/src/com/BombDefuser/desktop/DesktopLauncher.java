package com.BombDefuser.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.BombDefuser.BombMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
<<<<<<< HEAD
		config.width = 1280;
		config.height = 720;
		config.useGL30 = false;
=======
>>>>>>> 595b4e9d706f690a58fdba45768a1fffe852bd51
		new LwjglApplication(new BombMain(), config);
	}
}
