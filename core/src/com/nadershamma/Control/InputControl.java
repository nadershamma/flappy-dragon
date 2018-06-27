package com.nadershamma.Control;

import com.badlogic.gdx.InputProcessor;
import com.nadershamma.Models.Dragon;

/**
 * Created by Nader on 10/04/2016.
 */
public class InputControl implements InputProcessor {

    private GameControl gameControl;
    private Dragon gameDragon;

    public InputControl(GameControl gameControl)
    {
        this.gameControl = gameControl;
        this.gameDragon = gameControl.getDragon();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (gameControl.isReady()) {
            gameControl.start();
        }

        gameDragon.onClick();

        if (gameControl.isGameOver() || gameControl.isHighScore()) {
            // Reset all variables, go to GameState.READ
            gameControl.restart();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
