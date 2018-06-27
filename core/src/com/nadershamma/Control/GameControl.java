package com.nadershamma.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.nadershamma.Models.Dragon;

/**
 * Created by Nader on 10/04/2016.
 */
public class GameControl {
    private Dragon dragon;
    private Rectangle worldGround;
    private int playerScore = 0;
    private float runTime = 0;
    private float yAxisMidPoint;
    private  float gameHight, gameWidth;

    private GfxRenderer gfxBuilder;
    private GameMotionControl motionControl;

    public enum GameState{
        READY, RUNNING,GAMEOVER,HIGHSCORE;
    }

    private GameState gameState;

    private Music themeTune;
    //constructor
    public GameControl(float gameWidth, float gameHeight)
    {
        this.gameHight = gameHeight;
        this.gameWidth = gameWidth;
        yAxisMidPoint = gameHeight/2;
        motionControl = new GameMotionControl(this, yAxisMidPoint);
        dragon = new Dragon((gameWidth/2) - 30, yAxisMidPoint-10, 28, 20);
        worldGround = new Rectangle(0, gameHeight-10, 200, 10);
        gameState = GameState.READY;
        themeTune = AssetBuilder.themeTune;
    }

    public void update(float delta) {
       if(isGameOver() || isHighScore() || isReady())
        {
            themeTune.play();
        }
        else
        {
            themeTune.stop();
        }
        runTime += delta;

        switch (gameState) {
            case READY:
                setStateReady(delta);
                break;
            case RUNNING:
                realtimeUpdate(delta);
                break;
            default:
                break;
        }
    }

    private void setStateReady(float delta) {
        dragon.updateReady(runTime);
        motionControl.updateReady(delta);



    }

    public void realtimeUpdate(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        dragon.update(delta);
        motionControl.update(delta);

        if(motionControl.coinGrab(dragon) && dragon.isAlive())
        {
            addScore(1);
        }

        if(motionControl.collision(dragon) && dragon.isAlive())
        {
            motionControl.stop();
            vibratePhone();
            dragon.die();
            AssetBuilder.dead.play();
            AssetBuilder.fall.play();
        }
        if (Intersector.overlaps(dragon.getPhisicalBoundry(), worldGround)) {

            if (dragon.isAlive()) {
                AssetBuilder.dead.play();
                vibratePhone();
                dragon.die();
            }

            motionControl.stop();
            dragon.decelerate();
            gameState = GameState.GAMEOVER;

            if (playerScore > AssetBuilder.getHighScore()) {
                AssetBuilder.setHighScore(playerScore);
                gameState = GameState.HIGHSCORE;
            }
        }

    }

    public Dragon getDragon() {
        return dragon;

    }

    public float getScreenMidPointY() {
        return yAxisMidPoint;
    }


    public int getPlayerScore() {
        return playerScore;
    }

    public void addScore(int increment) {
        playerScore += increment;
    }

    public void start() {
        gameState = GameState.RUNNING;
    }

    public void ready() {
        gameState = GameState.READY;
    }

    public void restart() {
        playerScore = 0;
        dragon.onRestart(yAxisMidPoint - 5);
        motionControl.onRestart();
        ready();
    }

    public boolean isReady() {
        if(gameState == GameState.READY)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isGameOver() {
        if(gameState == GameState.GAMEOVER)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isHighScore() {
        if(gameState == GameState.HIGHSCORE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isRunning() {
        if(gameState == GameState.RUNNING)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setRenderer(GfxRenderer gfxBuilder) {
        this.gfxBuilder = gfxBuilder;
    }

    public GameMotionControl getMotionControl()
    {
        return motionControl;
    }

    public float getGameHight()
    {
        return gameHight;
    }

    public void vibratePhone()
    {
        if(dragon.isAlive())
        {
            Gdx.input.vibrate(400);
        }
    }
}
