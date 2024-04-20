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
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Map map;
    private HomeScreen homeScreen;

    @Override
    public void create() {
        Assets assets=new Assets();
        assets.loadAll();
        assets.getAssetManager().finishLoading();
//        camera = new OrthographicCamera();
//        camera.setToOrtho(false, 600, 386);
//        batch = new SpriteBatch();
//        map = new Map();
        //homeScreen=new HomeScreen();
        setScreen(new HomeScreen(assets.getAssetManager()));
    }

//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(.1f,.1f,15f,1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//                camera.update();
//        inputHandler();
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//        map.render(batch);
//        batch.end();
//    }
//
//    private void inputHandler() {
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) camera.position.x -= 200 * Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) camera.position.x += 200 * Gdx.graphics.getDeltaTime();
//        if (camera.position.x < 300) camera.position.x = 300; //half of camera width vision
//        if (camera.position.x > 3072 - 300) camera.position.x = 3072 - 300;
//        map.backgroundMoveAnimation();
//    }
//    @Override
//    public void dispose() {
//        map.dispose();
//        batch.dispose();
//    }
}
