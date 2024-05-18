package com.gggeralt.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gggeralt.game.screens.gameScreen.Map;
import com.gggeralt.game.screens.homeScreen.HomeScreen;

public class JavaGame extends Game {
    private SpriteBatch batch;
    private Map map;
    private HomeScreen homeScreen;

    @Override
    public void create() {
        Assets assets=new Assets();
        assets.loadAll();
        assets.getAssetManager().finishLoading();
        setScreen(new HomeScreen(assets.getAssetManager()));
    }

}
