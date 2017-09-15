package com.mygdx.ships.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by commandcentral on 4/29/2017.
 */

public class GameStateManager {

    private Stack<State> stateStack;

    public GameStateManager() {
        this.stateStack = new Stack<State>();
    }

    public void push(State state){
        stateStack.push(state);
    }

    public void pop(){
        stateStack.pop();
    }

    public void set(State state){
        stateStack.pop();
        stateStack.push(state);
    }
    public void update(float timeDifference){
         stateStack.peek().update(timeDifference);
    }
    public void render(SpriteBatch spriteBatch){
        stateStack.peek().render(spriteBatch);
    }
}
