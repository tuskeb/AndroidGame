package com.csany.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.csany.game.MyGame;
import com.csany.game.MyScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Endless Ball Running";
		config.width = (int)MyScreen.WORLD_WIDTH;
		config.height = (int)MyScreen.WORLD_HEIGHT;
		new LwjglApplication(new MyGame(), config);
	}
}
