package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.event.KeyListener;

public class GameWorld {
    private AssetManager assetManager;
    private Map map;
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private Texture backgroundTexture;
    private CollisionElement brick, brickCeil, brickFloor;
    private Array<CollisionElement> bricks;

    private Hero hero;

    public GameWorld() {
        bricks = new Array<>();
        hero = new Hero();
        this.assetManager = assetManager;
        this.spriteBatch = new SpriteBatch();
        this.brick = new CollisionElement(new Texture(Gdx.files.internal("bricks.png")), 64, 64);
        this.brickCeil = new CollisionElement(new Texture(Gdx.files.internal("bricks.png")), 0, 256);
        this.brickFloor = new CollisionElement(new Texture(Gdx.files.internal("bricks.png")), 0, 0);
        bricks.addAll(brick, brickCeil, brickFloor);
        this.viewport = new ExtendViewport(1024, 768);
        this.backgroundTexture = new Texture(Gdx.files.internal("background.png"));

    }


    public void update(float delta) {
        hero.Update();
    }


    public void draw() {
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, 1024, 768);
        spriteBatch.draw(new Texture("bricks.png"), 0, 0);
        brick.draw();
        brickFloor.draw();
        brickCeil.draw();
        spriteBatch.end();
    }

    public Viewport getViewport() {
        return viewport;
    }
}
