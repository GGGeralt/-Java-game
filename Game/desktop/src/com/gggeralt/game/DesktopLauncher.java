package com.gggeralt.game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.gggeralt.game.JavaGame;
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Tomasz Wpie**** The Game");
		config.setWindowedMode(512,384);
		config.useVsync(true);
		config.setForegroundFPS(300);
		new Lwjgl3Application(new JavaGame(), config);
	}
}