package com.nadershamma.Control;

import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.nadershamma.Models.Background;
import com.nadershamma.Models.Coin;
import com.nadershamma.Models.Dragon;
import com.nadershamma.Models.Skull;
import com.nadershamma.Models.Zombie;

import java.util.Random;

/**
 * Created by Nader on 10/04/2016.
 */
public class GameMotionControl {
    private Random r;
    public static final int SCROLL_SPEED = -70;
    public static final int COIN_SPEED = -60;
    public static final int BG_SCROLL = -60;
    public static final int ENEMY_GAP = 60;
    public float gameHeight;
    private float maxY, minY;
    private float minMaxY;

    //game objects
    private Zombie zombie1, zombie2, zombie3;
    private Skull skull1,skull2, skull3, skull4;
    private Coin coin1, coin2, coin3;
    private Background fronBg;
    private Background backBg;

    //game control
    private GameControl gameControl;

    public GameMotionControl(GameControl gameControl, float yPos)
    {
        r = new Random();
        this.gameControl = gameControl;
        gameHeight = gameControl.getGameHight();

        maxY = gameHeight/2;
        minY = 1;
        minMaxY = (maxY - minY) + minY;

        //create world
        fronBg = new Background(0, 0, 200, (int)gameHeight, BG_SCROLL);
        backBg = new Background(fronBg.getTailX(), 0, 200, (int)gameHeight, BG_SCROLL);

        //create zombies
        zombie1 = new Zombie(220,gameHeight, SCROLL_SPEED);
        zombie2 = new Zombie(zombie1.getTailX() + ENEMY_GAP, gameHeight, SCROLL_SPEED);
        zombie3 = new Zombie(zombie2.getTailX() + ENEMY_GAP, gameHeight, SCROLL_SPEED);

        skull1 = new Skull(250, gameHeight, SCROLL_SPEED);
        skull2 = new Skull(skull1.getTailX()+ENEMY_GAP, gameHeight, SCROLL_SPEED);
        skull3 = new Skull(skull2.getTailX()+ENEMY_GAP, gameHeight, SCROLL_SPEED);
        skull4 = new Skull(skull3.getTailX()+ENEMY_GAP, gameHeight, SCROLL_SPEED);

        coin1 = new Coin(275,gameHeight, COIN_SPEED);
        coin2 = new Coin(coin1.getTailX() + ENEMY_GAP, gameHeight, COIN_SPEED);
        coin3 = new Coin(coin2.getTailX() +ENEMY_GAP, gameHeight, COIN_SPEED);
    }

    public void updateReady(float delta) {
        /*fronBg.update(delta);
        backBg.update(delta);
        */

        if(fronBg.isScrolled())
        {
            fronBg.reset(backBg.getTailX());
        }
        else if(backBg.isScrolled())
        {
            backBg.reset(fronBg.getTailX());
        }

        if(coin1.isScrolled())
        {
            coin1.reset(coin3.getTailX()+ ENEMY_GAP);
        }
        else if (coin2.isScrolled()) {
            coin2.reset(coin1.getTailX() + ENEMY_GAP);

        } else if (coin3.isScrolled()) {
            coin3.reset(coin2.getTailX() + ENEMY_GAP);
        }

        if(zombie1.isScrolled())
        {
            zombie1.reset(zombie3.getTailX()+ ENEMY_GAP);
        }
        else if (zombie2.isScrolled()) {
            zombie2.reset(zombie1.getTailX() + ENEMY_GAP);

        } else if (zombie3.isScrolled()) {
            zombie3.reset(zombie2.getTailX() + ENEMY_GAP);
        }

        if(skull1.isScrolled())
        {
            skull1.reset(skull4.getTailX()+ ENEMY_GAP);
        }
        else if (skull2.isScrolled()) {
            skull2.reset(skull1.getTailX() + ENEMY_GAP);

        } else if (skull3.isScrolled()) {
            skull3.reset(skull2.getTailX() + ENEMY_GAP);
        }
        else if (skull4.isScrolled()) {
            skull4.reset(skull3.getTailX() + ENEMY_GAP);
        }

    }

    public void update(float delta)
    {
        fronBg.update(delta);
        backBg.update(delta);

        if(fronBg.isScrolled())
        {
            fronBg.reset(backBg.getTailX());
        }
        else if(backBg.isScrolled())
        {
            backBg.reset(fronBg.getTailX());
        }


        coin1.update(delta);
        coin2.update(delta);
        coin3.update(delta);
        if(coin1.isScrolled())
        {
            coin1.reset(coin3.getTailX()+ ENEMY_GAP);
        }
        else if (coin2.isScrolled()) {
            coin2.reset(coin1.getTailX() +ENEMY_GAP);

        } else if (coin3.isScrolled()) {
            coin3.reset(coin2.getTailX() + ENEMY_GAP);
        }

        zombie1.update(delta);
        zombie2.update(delta);
        zombie3.update(delta);
        if(zombie1.isScrolled())
        {
            zombie1.reset(zombie3.getTailX()+ ENEMY_GAP);
        }
        else if (zombie2.isScrolled()) {
            zombie2.reset(zombie1.getTailX() + ENEMY_GAP);

        } else if (zombie3.isScrolled()) {
            zombie3.reset(zombie2.getTailX() + ENEMY_GAP);
        }

        skull1.update(delta);
        skull2.update(delta);
        skull3.update(delta);
        skull4.update(delta);
        if(skull1.isScrolled())
        {
            skull1.reset(skull4.getTailX()+ ENEMY_GAP);
        }
        else if (skull2.isScrolled()) {
            skull2.reset(skull1.getTailX() + ENEMY_GAP);

        } else if (skull3.isScrolled()) {
            skull3.reset(skull2.getTailX() + ENEMY_GAP);
        }
        else if (skull4.isScrolled()) {
            skull4.reset(skull3.getTailX() + ENEMY_GAP);
        }

    }

    public void stop()
    {

        fronBg.stop();
        backBg.stop();

        coin1.stop();
        coin2.stop();
        coin3.stop();

        zombie1.stop();
        zombie2.stop();
        zombie3.stop();

        skull1.stop();
        skull2.stop();
        skull3.stop();
        skull4.stop();
    }

    public boolean coinGrab(Dragon dragon)
    {
        if(coin1.coinGrab(dragon))
        {
            AssetBuilder.point.play();
            if(coin3.isScrolled())
                coin1.reset(coin3.getTailX() + ENEMY_GAP);
            else if(coin2.isScrolled()){
                coin1.reset(coin2.getTailX()+ ENEMY_GAP);
            }
            else {
                coin1.reset(220);
            }

            return true;
        }
        else if(coin2.coinGrab(dragon))
        {
            AssetBuilder.point.play();
            if(coin1.isScrolled())
                coin2.reset(coin1.getTailX() + ENEMY_GAP);
            else if(coin3.isScrolled()){
                coin2.reset(coin3.getTailX()+ ENEMY_GAP);
            }
            else {
                coin2.reset(250);
            }
            return true;
        }
        else if(coin3.coinGrab(dragon))
        {
            AssetBuilder.point.play();
            if(coin2.isScrolled())
                coin3.reset(coin2.getTailX() + ENEMY_GAP);
            else if(coin1.isScrolled()){
                coin3.reset(coin1.getTailX()+ ENEMY_GAP);
            }
            else {
                coin3.reset(270);
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean collision(Dragon dragon)
    {
        if(zombie1.collision(dragon)) {
            return true;
        }

        else if(zombie2.collision(dragon)) {
            return true;
        }

        else if(zombie3.collision(dragon)) {
            return true;
        }
        else if(skull1.collision(dragon))
        {
            return true;
        }
        else if(skull2.collision(dragon))
        {
            return true;
        }
        else if(skull3.collision(dragon))
        {
            return true;
        }
        else if(skull4.collision(dragon))
        {
            return true;
        }
        else {
            return false;
        }
    }

    public void onRestart() {
        fronBg.onRestart(0,BG_SCROLL);
        backBg.onRestart(fronBg.getTailX(), BG_SCROLL);

        coin1.onRestart(220, COIN_SPEED);
        coin2.onRestart(coin1.getTailX()+ENEMY_GAP, COIN_SPEED);
        coin3.onRestart(coin2.getTailX()+ENEMY_GAP, COIN_SPEED);

        zombie1.onRestart(250, SCROLL_SPEED);
        zombie2.onRestart(zombie1.getTailX()+ ENEMY_GAP, SCROLL_SPEED);
        zombie3.onRestart(zombie2.getTailX()+ ENEMY_GAP, SCROLL_SPEED);

        skull1.onRestart(275, SCROLL_SPEED);
        skull2.onRestart(skull1.getTailX()+ ENEMY_GAP, SCROLL_SPEED);
        skull3.onRestart(skull2.getTailX()+ ENEMY_GAP, SCROLL_SPEED);
        skull4.onRestart(skull3.getTailX()+ENEMY_GAP, SCROLL_SPEED);


    }

    Skull getSkull1()
    {
        return skull1;
    }

    Skull getSkull2()
    {
        return skull2;
    }

    Skull getSkull3()
    {
        return skull3;
    }
    Skull getSkull4()
    {
        return skull4;
    }

    Zombie getZombie1()
    {
        return zombie1;
    }

    Zombie getZombie2()
    {
        return zombie2;
    }

    Zombie getZombie3()
    {
        return zombie3;
    }

    Coin getCoin1()
    {
        return coin1;
    }

    Coin getCoin2()
    {
        return coin2;
    }

    Coin getCoin3()
    {
        return coin3;
    }

    Background getFronBg()
    {
        return fronBg;
    }
    Background getBackBg()
    {
        return backBg;
    }

}
