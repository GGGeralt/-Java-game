package com.gggeralt.game.screens.homeScreen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gggeralt.game.Assets;
import com.gggeralt.game.screens.gameScreen.GameScreen;

import javax.swing.plaf.nimbus.State;

public class HomeScreen extends ScreenAdapter {

    private Stage stage;
    private Viewport viewport;
    private AssetManager assetManager;
    private Skin skin;
    private Table mainTable;
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private BitmapFont bitmapFont;
    public HomeScreen(AssetManager assetManager) {
        this.assetManager = assetManager;
        skin = assetManager.get(Assets.SKIN);
    }
    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        viewport = new ExtendViewport(1024, 768);
        stage = new Stage(viewport);
        mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);


        addButton("Play").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });
        addButton("EXIT").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Gdx.app.exit();
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    private TextButton addButton(String name) {
        TextButton button = new TextButton(name, skin);

        mainTable.add(button).width(150).height(75).padBottom(20);
        mainTable.row();
        return button;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, 1024, 768);
        bitmapFont.setColor(Color.WHITE);
        bitmapFont.getData().setScale(3);
        bitmapFont.draw(spriteBatch, "Tomasz Wpie**** The Game", Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 5f);
        spriteBatch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        backgroundTexture.dispose();
        bitmapFont.dispose();
    }

    private void handleInput(){

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
        }

    }


}
