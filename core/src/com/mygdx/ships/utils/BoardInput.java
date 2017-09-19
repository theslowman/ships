package com.mygdx.ships.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.ships.state.PlayState;

/**
 * Created by commandcentral on 9/19/2017.
 */

public class BoardInput {

    int boardX;
    int boardY;

    public int getBoardX() {
        return boardX;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
    }

    public BoardInput(Vector2 input){
        boardX = (int) (input.x / PlayState.TEXTURE_SIZE);
        boardY = (int) (input.y / PlayState.TEXTURE_SIZE);
    }
}
