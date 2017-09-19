package com.mygdx.ships.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.ships.AIPlayer;
import com.mygdx.ships.HumanPlayer;
import com.mygdx.ships.Plansza;
import com.mygdx.ships.Player;

/**
 * Created by commandcentral on 4/30/2017.
 */

public class PlayState extends State {
    public static final int TEXTURE_SIZE = 50;
    public static final int BOXES_HEIGHT = 12;
    public static final int BOXES_WIDTH = 23;
    public static final int HEIGHT = BOXES_HEIGHT * TEXTURE_SIZE;

    public static final String T = "trafiony";
    public static final String ST = "statek";
    public static final String P = "pudlo";
    public static final String B = "pole bia³e";
    boolean isYourTurn = true;
    Plansza AIBoard;
    Player enemy = new AIPlayer();
    HumanPlayer human = new HumanPlayer();
    Player generator = new AIPlayer();
    Viewport viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(true, PlayState.BOXES_WIDTH * PlayState.TEXTURE_SIZE / 2, PlayState.BOXES_HEIGHT * PlayState.TEXTURE_SIZE);
        enemy.addShips();
        AIBoard = enemy.getPlansza();
        generator.addShips();
        human.copyPlansza(generator.getPlansza());
    }


    @Override
    protected void handleInput() {
        if (yourTurn()) {
            if (Gdx.input.justTouched()) {
                enemy.displayBattlefield();
                Vector2 input = new Vector2(Gdx.input.getX(), HEIGHT - Gdx.input.getY());
                SpriteBatch touched = new SpriteBatch();
                touched.begin();
                if (isInEnemyBattlefield(input)) {
                    if (wasEnemyFieldAttacked(input)) {
                        System.out.println("nie wolno");
                        return;
                    }
                    isYourTurn = human.attack(enemy, (int) input.x / TEXTURE_SIZE - 12, (int) input.y / TEXTURE_SIZE - 1);
                    if (enemy.getPlansza().allDrowned()){
                        gameStateManager.set(new PlayState(gameStateManager));
                    }
                }
                touched.draw(new Texture("chars/0.png")
                        , (float) (Math.floor(input.x / TEXTURE_SIZE) * TEXTURE_SIZE)
                        , (float) (Math.floor((input.y) / TEXTURE_SIZE) * TEXTURE_SIZE));
                touched.end();

            }
        } else {
            isYourTurn = !enemy.attack(human);
            if (human.getPlansza().allDrowned())
                gameStateManager.set(new PlayState(gameStateManager));
            blastDelay();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch batch) {

        batch.begin();
        drawWater(batch);
        drawGolden(batch);
        drawNumbers(batch);
        drawLetters(batch);
        drawShips(batch);
        batch.end();
    }

    @Override
    public void dispose() {

    }


    public void drawNumbers(SpriteBatch batch) {
        for (int i = 1; i < 11; i++) {
            batch.draw(new Texture("chars/" + i + ".png"), 0 * TEXTURE_SIZE, i * TEXTURE_SIZE);
            batch.draw(new Texture("chars/" + i + ".png"), 11 * TEXTURE_SIZE, i * TEXTURE_SIZE);
            batch.draw(new Texture("chars/" + i + ".png"), 22 * TEXTURE_SIZE, i * TEXTURE_SIZE);
        }
    }

    public void drawLetters(SpriteBatch batch) {
        char c = 'A';
        for (int i = 1; i < 11; i++, c++) {
            batch.draw(new Texture("chars/" + c + ".png"), i * TEXTURE_SIZE, 0 * TEXTURE_SIZE);
            batch.draw(new Texture("chars/" + c + ".png"), (i + 11) * TEXTURE_SIZE, 0 * TEXTURE_SIZE);
            batch.draw(new Texture("chars/" + c + ".png"), i * TEXTURE_SIZE, (BOXES_HEIGHT - 1) * TEXTURE_SIZE);
            batch.draw(new Texture("chars/" + c + ".png"), (i + 11) * TEXTURE_SIZE, (BOXES_HEIGHT - 1) * TEXTURE_SIZE);
        }
    }

    public void drawGolden(SpriteBatch batch) {
        batch.draw(new Texture("chars/0.png"), 0 * TEXTURE_SIZE, 0);
        batch.draw(new Texture("chars/0.png"), 11 * TEXTURE_SIZE, 0);
        batch.draw(new Texture("chars/0.png"), 22 * TEXTURE_SIZE, 0);
        batch.draw(new Texture("chars/0.png"), 253 % 23 * TEXTURE_SIZE, (BOXES_HEIGHT - 1) * TEXTURE_SIZE);
        batch.draw(new Texture("chars/0.png"), 264 % 23 * TEXTURE_SIZE, (BOXES_HEIGHT - 1) * TEXTURE_SIZE);
        batch.draw(new Texture("chars/0.png"), 275 % 23 * TEXTURE_SIZE, (BOXES_HEIGHT - 1) * TEXTURE_SIZE);
    }

    public void drawWater(SpriteBatch batch) {
        for (int i = 0; i < BOXES_HEIGHT * BOXES_WIDTH; i++) {
            batch.draw(new Texture("effect/water.png"), (i % BOXES_WIDTH) * TEXTURE_SIZE, (i % BOXES_HEIGHT) * TEXTURE_SIZE);
        }
    }

    public void drawShips(SpriteBatch batch) {
        for (int x = 0; x < 10; x++)
            for (int y = 0; y < 10; y++) {
                batch.draw(human.getEnemyPlansza().getPoleTexture(x, y), (x + 12) * TEXTURE_SIZE, (y + 1) * TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
                batch.draw(human.getPlansza().getPoleTexture(x, y), (x + 1) * TEXTURE_SIZE, (y + 1) * TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
            }
    }

    public boolean isInEnemyBattlefield(Vector2 input) {
        return input.x / TEXTURE_SIZE > 12 && input.x / TEXTURE_SIZE < (BOXES_WIDTH - 1)
                && input.y / TEXTURE_SIZE > 0 && input.y / TEXTURE_SIZE < (BOXES_HEIGHT - 1);
    }

    public void blastDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean yourTurn() {
        return isYourTurn;
    }

    boolean wasEnemyFieldAttacked(Vector2 input) {
        return AIBoard.getPole((int) input.y / TEXTURE_SIZE - 1, (int) input.x / TEXTURE_SIZE - 12).getStatus().equals(T) || AIBoard.getPole((int) input.y / TEXTURE_SIZE - 1, (int) input.x / TEXTURE_SIZE - 12).getStatus().equals(P);
    }
}
