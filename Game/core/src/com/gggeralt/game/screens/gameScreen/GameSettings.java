package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gggeralt.game.Assets;
import com.gggeralt.game.screens.homeScreen.HomeScreen;


public class GameSettings extends ScreenAdapter {

    private Stage stage;
    private Viewport viewport;
    private AssetManager assetManager;
    private Skin skin;
    private Table mainTable;
    public GameSettings(AssetManager assetManager) {
        this.assetManager = assetManager;
        skin = assetManager.get(Assets.SKIN);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(1024, 768);
        stage = new Stage(viewport);
        mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        addButton("BACK TO  MENU").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new HomeScreen(assetManager));
            }
        });
    }

    private TextButton addButton(String name) {
        TextButton button = new TextButton(name, skin);

        mainTable.add(button).width(150).height(75).padBottom(20);
        mainTable.row();
        return button;
    }

/*
    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }
*/
    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
