package com.mygdx.ships.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.ships.Ships;
import com.mygdx.ships.state.PlayState;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = PlayState.BOXES_HEIGHT * PlayState.TEXTURE_SIZE;
        config.width = PlayState.BOXES_WIDTH * PlayState.TEXTURE_SIZE;
        new LwjglApplication(new Ships(), config);
    }
}
