package com.mygdx.ships.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by commandcentral on 4/18/2017.
 */

public abstract class State {
    protected GameStateManager gameStateManager;
    OrthographicCamera camera;
    protected State(GameStateManager gameStateManager) {
        this.gameStateManager
                = gameStateManager;
        camera= new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    protected abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void dispose();
}
