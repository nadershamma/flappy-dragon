package com.nadershamma.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.nadershamma.Control.GameControl;
import com.nadershamma.Control.GfxRenderer;
import com.nadershamma.Control.InputControl;

/**
 * Created by Nader on 10/04/2016.
 */
public class MainScreen implements Screen{
    private GameControl gameControl;
    private GfxRenderer gameGfx;
    private float runTime;
    private float gameHeight, gameWidth;

    public MainScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        gameWidth = 200;
        gameHeight = gameWidth * (screenHeight / screenWidth);


       //float gameWidth = 136;
        //gameHeight = gameWidth * (screenHeight / screenWidth);
        //int midPointY = (int) (gameHeight / 2);

        gameControl = new GameControl(gameWidth, gameHeight);
        Gdx.input.setInputProcessor(new InputControl(gameControl));
        gameGfx = new GfxRenderer(gameControl, gameWidth, gameHeight);
        gameControl.setRenderer(gameGfx);
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        runTime+=delta;
        gameControl.update(delta);
        gameGfx.render(runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public float getGamHeight()
    {
        return gameHeight;
    }
}
