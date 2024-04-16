package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends ScreenAdapter {


    private GameWorld gameWorld;

    public GameScreen() {

    }

    @Override
    public void show() {
        this.gameWorld = new GameWorld();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameWorld.update(delta);
        gameWorld.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameWorld.getViewport().update(width, height);
    }


}
