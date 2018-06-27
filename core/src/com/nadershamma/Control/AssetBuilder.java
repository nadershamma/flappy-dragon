package com.nadershamma.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.TextArea;

/**
 * Created by Nader on 10/04/2016.
 * The asset builder loads all the assets including sprites, background and sound using the texture
 * class and texture region class. Using an asset builder class we can load all assests at the start to reduce
 * overhead during the game run time.
 */
public class AssetBuilder {
    //Texture gives us a bitmap file as a whole and Texture Region allows us to splice it
    public static Texture dragonTexture, coinTexture, monsterOneTexture, monsterTwoTexture, bgTexture;

    public static TextureRegion background;

    //public static TextureRegion dragon1, dragon2, dragon3, dragon4;
    public static TextureRegion[] dragon;

    //public static TextureRegion coin1, coin2, coin3, coin4,coin5, coin6;
    public static TextureRegion[] coin;

    //public static TextureRegion flyMonster1, flyMonster2, flyMonster3, flyMonster4, flyMonster5;
    public static TextureRegion[] flyMonster;

    //public static TextureRegion walkMonster1, walkMonster2, walkMonster3, walkMonster4;
    public static TextureRegion[] walkMonster;

    public static Animation flyingDragon;
    public static Animation flyingMonster;
    public static Animation walkingMonster;
    public static Animation spinningCoin;

    public static BitmapFont font, shadow;

    public static Sound dead, flap, point, fall;

    public static Preferences scorePref;

    public static Music themeTune;

   public static void innit()
    {
        //set up back ground
        bgTexture = new Texture(Gdx.files.internal("Data/background.png"));
        background = new TextureRegion(bgTexture, 0 , 0, 500, 250);
        background.flip(false, true);

        //backgrounds = new TextureRegion[2];
     /*   backgrounds[0].setTexture(bgTexture);
        backgrounds[0].setRegion(0,0,500, 250);
        backgrounds[0].flip(false,true);
        backgrounds[0].setTexture(bgTexture);
        backgrounds[0].setRegion(0,0,500, 250);
        backgrounds[0].flip(false,true);*/

        //background = new TextureRegion(bgTexture,0, 0, 1130, 566);
       // background.flip(false,true);

        //set up dragon
        dragonTexture = new Texture(Gdx.files.internal("Data/DragonSprite.png"));
        //dragonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        dragon = new TextureRegion[4];
        frameBuilder(dragonTexture, dragon, 939, 678,2, 2);
        imageFlip(dragon, 4,false, true);
        flyingDragon = new Animation(0.06f, dragon);

        //set up coin
        coinTexture = new Texture(Gdx.files.internal("Data/coin.png"));
        coin = new TextureRegion[6];
        frameBuilder(coinTexture, coin, 155, 155,3, 2);
        imageFlip(coin, 6, false, true);
        spinningCoin = new Animation(0.32f, coin);

        //set up flying monster
        monsterOneTexture = new Texture(Gdx.files.internal("Data/flyingMonster.png"));
        flyMonster = new TextureRegion[5];
        frameBuilder(monsterOneTexture, flyMonster, 64, 64,5,1);
        imageFlip(flyMonster, 5, true, true);
        flyingMonster = new Animation(0.06f, flyMonster);

        //set up walking monster
        monsterTwoTexture = new Texture(Gdx.files.internal("Data/walkingMonster.png"));
        walkMonster = new TextureRegion[4];
        frameBuilder(monsterTwoTexture, walkMonster, 64, 64, 4,1);
        imageFlip(walkMonster, 4,true, true);
        walkingMonster = new Animation(0.06f, walkMonster);

        dead = Gdx.audio.newSound(Gdx.files.internal("Data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("Data/flap.wav"));
        point = Gdx.audio.newSound(Gdx.files.internal("Data/coin.wav"));
        fall = Gdx.audio.newSound(Gdx.files.internal("Data/fall.wav"));

        font = new BitmapFont(Gdx.files.internal("Data/text.fnt"));
        font.getData().setScale(.25f, -.25f);

        shadow = new BitmapFont(Gdx.files.internal("Data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        // Create / or retrieve High score data in preference
        scorePref = Gdx.app.getPreferences("DragonRun");

        if (!scorePref.contains("highScore")) {
            scorePref.putInteger("highScore", 0);
        }

        themeTune = Gdx.audio.newMusic(Gdx.files.internal("Data/Intense.mp3"));

    }

    //Slice texture into frame slices
    private static void frameBuilder(Texture texture, TextureRegion[] textures , int splitX, int splitY, int columns, int rows)
    {
        int index = 0;
        TextureRegion[][] tmpFrames = TextureRegion.split(texture,splitX, splitY);

        for (int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++) {
                textures[index] = tmpFrames[j][i];
                index++;
            }
        }

    }

    private static void  imageFlip(TextureRegion[] textures, int numImages, boolean x, boolean y)
    {
        for(int i = 0; i < numImages; i++)
        {
            textures[i].flip(x,y);
        }
    }

    public static void setHighScore(int score) {
        scorePref.putInteger("HighScore", score);
        scorePref.flush();
    }

    public static int getHighScore() {
        return scorePref.getInteger("HighScore");
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        dragonTexture.dispose();
        bgTexture.dispose();
        coinTexture.dispose();
        monsterOneTexture.dispose();
        monsterTwoTexture.dispose();

        // Dispose sounds
        dead.dispose();
        flap.dispose();
        point.dispose();
        fall.dispose();

        font.dispose();
        shadow.dispose();
        themeTune.dispose();
    }

}

