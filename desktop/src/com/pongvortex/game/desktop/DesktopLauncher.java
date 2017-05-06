package com.pongvortex.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pongvortex.game.PongVortex;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 40; //Limit to 40fps in background
		config.foregroundFPS = 60; //Limit to 60fps
		config.title = "PongVortex";
		config.resizable = false; //Leave this be or figure out what we want to do when it's resized
		config.fullscreen = false; //MUST be false
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new PongVortex(), config);
	}
}
