package com.nadershamma.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.nadershamma.Models.Background;
import com.nadershamma.Models.Coin;
import com.nadershamma.Models.Dragon;
import com.nadershamma.Models.Skull;
import com.nadershamma.Models.Zombie;

import java.security.PrivateKey;

/**
 * Created by Nader on 10/04/2016.
 */
public class GfxRenderer {
    private GameControl gameControl;
    private GameMotionControl motionControl;

    private OrthographicCamera gameCam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch spriateBatch;

    private float screenMidYPoint;

    // Game Objects
    private Dragon dragon;
    private TextureRegion[] dragonSprites;
    private Animation flyingDragon;

    private Zombie zombie1;
    private Zombie zombie2;
    private Zombie zombie3;
    private TextureRegion[] zombieSprites;
    private Animation walkingZombie;

    private Skull skull1;
    private Skull skull2;
    private Skull skull3;
    private Skull skull4;
    private TextureRegion[] skullSprites;
    private Animation flyingSkull;

    private Coin coin1;
    private Coin coin2;
    private Coin coin3;
    private TextureRegion[] coinSprites;
    private Animation spinningCoin;

    // Game wordl
    private TextureRegion background;
    private Background frontBg;
    private Background backBg;

    public GfxRenderer(GameControl gameControl, float gameWidth, float gameHeight) {
        this.gameControl = gameControl;
        motionControl = gameControl.getMotionControl();
        screenMidYPoint = gameHeight/2;

        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(true, gameWidth , gameHeight);

        spriateBatch = new SpriteBatch();
        spriateBatch.setProjectionMatrix(gameCam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(gameCam.combined);

        initGameCharacters();
        initGameWorld();
    }

    private void initGameCharacters() {
        dragon = gameControl.getDragon();
        zombie1 = motionControl.getZombie1();
        zombie2 = motionControl.getZombie2();
        zombie3 = motionControl.getZombie3();
        skull1 = motionControl.getSkull1();
        skull2 = motionControl.getSkull2();
        skull3 = motionControl.getSkull3();
        skull4 = motionControl.getSkull4();
        coin1 = motionControl.getCoin1();
        coin2 = motionControl.getCoin2();
        coin3 = motionControl.getCoin3();

    }

    private void initGameWorld() {
        frontBg = motionControl.getFronBg();
        backBg = motionControl.getBackBg();
        background = AssetBuilder.background;
        //dragon
        flyingDragon = AssetBuilder.flyingDragon;
        dragonSprites = AssetBuilder.dragon;

        //zombie
        walkingZombie = AssetBuilder.walkingMonster;
        zombieSprites = AssetBuilder.walkMonster;

        //skulls
        flyingSkull = AssetBuilder.flyingMonster;
        skullSprites = AssetBuilder.flyMonster;

        //coin
        spinningCoin = AssetBuilder.spinningCoin;
        coinSprites = AssetBuilder.coin;
    }

    private void drawZombie(float runTime)
    {
        TextureRegion currentFrame = walkingZombie.getKeyFrame(runTime,true);
        spriateBatch.draw(currentFrame, zombie1.getX(), zombie1.getY(), zombie1.getWidth(),zombie1.getHeight());
        spriateBatch.draw(currentFrame, zombie2.getX(), zombie2.getY(), zombie2.getWidth(),zombie2.getHeight());
        spriateBatch.draw(currentFrame, zombie3.getX(), zombie3.getY(), zombie3.getWidth(),zombie3.getHeight());

    }

    private void drawSkull(float runTime)
    {
        String log =  skull1.getX() + "";
        Gdx.app.log("DrawSkullFunction", "Redering Skulls");
        Gdx.app.log("Redering Skull1",log);
        TextureRegion currentFrame = flyingSkull.getKeyFrame(runTime,true);
        spriateBatch.draw(currentFrame, skull1.getX(), skull1.getY(), skull1.getWidth(), skull1.getHeight());
        spriateBatch.draw(currentFrame, skull2.getX(), skull2.getY(), skull2.getWidth(),skull2.getHeight());
        spriateBatch.draw(currentFrame, skull3.getX(), skull3.getY(), skull3.getWidth(),skull3.getHeight());
        spriateBatch.draw(currentFrame, skull4.getX(), skull4.getY(), skull4.getWidth(),skull4.getHeight());
    }

    private void drawCoin(float runTime)
    {
        TextureRegion currentFrame = spinningCoin.getKeyFrame(runTime,true);
        spriateBatch.draw(currentFrame, coin1.getX(), coin1.getY(), coin1.getWidth(),coin1.getHeight());
        spriateBatch.draw(currentFrame, coin2.getX(), coin2.getY(), coin2.getWidth(),coin2.getHeight());
        spriateBatch.draw(currentFrame, coin3.getX(), coin3.getY(), coin3.getWidth(),coin3.getHeight());
    }


    private void drawDragon(float runTime) {

        TextureRegion currentFrame = flyingDragon.getKeyFrame(runTime,true);
        if (dragon.shouldntFlap()) {
            spriateBatch.draw(dragonSprites[1], dragon.getX(), dragon.getY(),
                    dragon.getWidth() / 2.0f, dragon.getHeight() / 2.0f,
                    dragon.getWidth(), dragon.getHeight(), 1, 1, dragon.getRotation());

        } else {
            spriateBatch.draw(currentFrame, dragon.getX(),
                    dragon.getY(), dragon.getWidth() / 2.0f,
                    dragon.getHeight() / 2.0f, dragon.getWidth(), dragon.getHeight(),
                    1, 1, dragon.getRotation());
        }
    }

    private void drawReady()
    {
        String ready = "Ready: Player 1";
        String touch = "Touch Me.";
        // Draw shadow first
        AssetBuilder.shadow.draw(spriateBatch, "" + ready,(200 / 2) - (ready.length()*4) , 12);
        /*AssetBuilder.shadow.draw(spriateBatch, "Player 1!", (200 / 2)
                - (37), 32);*/
        AssetBuilder.shadow.draw(spriateBatch, "" + touch,(200 / 2) - (touch.length()*4) , 32);
        // Draw text
        AssetBuilder.font.draw(spriateBatch, "" + ready,(200 / 2) - (ready.length()*4) , 12);
        /*AssetBuilder.font.draw(spriateBatch, "Player 1!", (200 / 2)
                - (37), 32);*/
        AssetBuilder.font.draw(spriateBatch, "" + touch,(200 / 2) - (touch.length()*4) , 32);
    }

    private  void drawGameOver()
    {
        String gameOver = "Game Over";
        String strhighScore = "High Score:";
        String tryAgain = "Try again?";
        AssetBuilder.shadow.draw(spriateBatch, "" + gameOver, (200 / 2) - (4 * gameOver.length()), 12);
        AssetBuilder.font.draw(spriateBatch, "" + gameOver, (200 / 2) - (4 * gameOver.length()), 12);

        AssetBuilder.shadow.draw(spriateBatch, ""+strhighScore, (200 / 2) - (4 * strhighScore.length()), 32);
        AssetBuilder.font.draw(spriateBatch,"" + strhighScore, (200 / 2) - (4 * strhighScore.length()), 32);

        String highScore = AssetBuilder.getHighScore() + "";

        // Draw shadow first
        AssetBuilder.shadow.draw(spriateBatch, highScore, (200 / 2)
                - (4 * highScore.length()),52 );
        // Draw text
        AssetBuilder.font.draw(spriateBatch, highScore, (200 / 2)
                - (4 * highScore.length() - 1), 52);

        AssetBuilder.shadow.draw(spriateBatch, "" + tryAgain, (200 / 2) - (4 *tryAgain.length()), 76);
        AssetBuilder.font.draw(spriateBatch, "" + tryAgain, (200 / 2) - (4 * tryAgain.length()), 76);
    }

    private void drawScore()
    {
        // Convert integer into String
        String score = gameControl.getPlayerScore() + "";

        // Draw shadow first
        AssetBuilder.shadow.draw(spriateBatch, "" + gameControl.getPlayerScore(), (200 / 2) - (4 * score.length()), 12);
        // Draw text
        AssetBuilder.font.draw(spriateBatch, "" + gameControl.getPlayerScore(), (200 / 2) - (4 * score.length() - 1), 12);

    }
    public void render(float runTime) {

        String strHighScore = "High Score!";

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriateBatch.begin();

        spriateBatch.disableBlending();
        spriateBatch.draw(background, frontBg.getX(), frontBg.getY(), frontBg.getWidth(),frontBg.getHeight());
        spriateBatch.draw(background, backBg.getX(), backBg.getY(), backBg.getWidth(),backBg.getHeight());

        spriateBatch.enableBlending();
        drawCoin(runTime);
        drawSkull(runTime);
        drawZombie(runTime);

        if (gameControl.isRunning()) {
            drawDragon(runTime);
            drawScore();
        } else if (gameControl.isReady()) {
            drawReady();
            drawDragon(runTime);
        }
        else if (gameControl.isGameOver()) {
            drawGameOver();
            drawDragon(runTime);
        } else if (gameControl.isHighScore()) {
            AssetBuilder.shadow.draw(spriateBatch,  ""+strHighScore, (200 / 2) - (3 * strHighScore.length()), 32);
            AssetBuilder.font.draw(spriateBatch,  ""+strHighScore, (200 / 2) - (3 * strHighScore.length()), 32);
            drawDragon(runTime);
        }

        spriateBatch.end();

        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(coin1.getPhisicalBoundry().x,
                coin1.getPhisicalBoundry().y, coin1.getPhisicalBoundry().radius);
        shapeRenderer.circle(coin2.getPhisicalBoundry().x,
                coin2.getPhisicalBoundry().y, coin2.getPhisicalBoundry().radius);
        shapeRenderer.circle(coin3.getPhisicalBoundry().x,
                coin3.getPhisicalBoundry().y, coin3.getPhisicalBoundry().radius);

        shapeRenderer.circle(dragon.getPhisicalBoundry().x,
                dragon.getPhisicalBoundry().y, dragon.getPhisicalBoundry().radius);

        shapeRenderer.rect(zombie1.getPhisicalBoundry().x, zombie1.getPhisicalBoundry().y, zombie1.getPhisicalBoundry().width,
                zombie1.getPhisicalBoundry().height);
        shapeRenderer.rect(zombie2.getPhisicalBoundry().x, zombie2.getPhisicalBoundry().y, zombie2.getPhisicalBoundry().width,
                zombie2.getPhisicalBoundry().height);
        shapeRenderer.rect(zombie3.getPhisicalBoundry().x, zombie3.getPhisicalBoundry().y, zombie3.getPhisicalBoundry().width,
                zombie3.getPhisicalBoundry().height);

        shapeRenderer.circle(skull1.getPhisicalBoundry().x,
                skull1.getPhisicalBoundry().y, skull1.getPhisicalBoundry().radius);
        shapeRenderer.circle(skull2.getPhisicalBoundry().x,
                skull2.getPhisicalBoundry().y, skull2.getPhisicalBoundry().radius);

        shapeRenderer.end();
*/
    }
}
