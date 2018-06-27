package com.nadershamma.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nadershamma.Control.AssetBuilder;
import com.nadershamma.View.MainScreen;

public class GameMain extends Game {
    @Override
    public void create() {
        Gdx.app.log("DRGame", "Created");
        AssetBuilder.innit();
        setScreen(new MainScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetBuilder.dispose();
    }

}
