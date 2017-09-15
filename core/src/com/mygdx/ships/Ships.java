package com.mygdx.ships;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.ships.state.GameStateManager;
import com.mygdx.ships.state.PlayState;

public class Ships extends ApplicationAdapter {
    private GameStateManager gameStateManager;
    SpriteBatch batch;
    Texture img;



    @Override
    public void create() {
        batch = new SpriteBatch();
        gameStateManager = new GameStateManager();
        gameStateManager.push(new PlayState(gameStateManager));
    }

    @Override
    public void render() {
        gameStateManager.render(batch);
        gameStateManager.update(Gdx.graphics.getDeltaTime());

    }


    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
