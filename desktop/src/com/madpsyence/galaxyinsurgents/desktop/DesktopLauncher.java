package com.madpsyence.galaxyinsurgents.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.madpsyence.galaxyinsurgents.CONST;
import com.madpsyence.galaxyinsurgents.GalaxyInsurgentsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = CONST.SCREEN_HEIGHT;
		config.width = CONST.SCREEN_WIDTH;
		new LwjglApplication(new GalaxyInsurgentsGame(), config);
	}
}
