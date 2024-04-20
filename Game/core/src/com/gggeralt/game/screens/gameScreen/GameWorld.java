package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.event.KeyListener;


public class GameWorld {
    private int lastKeyCode;
    private AssetManager assetManager;
    private Map map;
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private Texture backgroundTexture;
    private CollisionElement hero;
    private CollisionElement brick, brickCeil, brickFloor;

    private Array<Texture> bikerIdle;
    private float gravity;
    private float jumpVelocity;
    private boolean isJumping, isMovingRight, isMovingLeft, isMovingUp;
    private Array<CollisionElement> bricks;


    public GameWorld() {
        bricks = new Array<>();
        this.assetManager = assetManager;
        this.spriteBatch = new SpriteBatch();
        bikerIdle = new Array<>();
        bikerIdle.addAll(new Texture(Gdx.files.internal("bikerIdle1.png")), new Texture(Gdx.files.internal("bikerIdle2.png")),
                new Texture(Gdx.files.internal("bikerIdle3.png")), new Texture(Gdx.files.internal("bikerIdle4.png")));

        this.hero = new CollisionElement(bikerIdle, 0, 64);
        this.brick = new CollisionElement(new Texture(Gdx.files.internal("bricks.png")), 64, 64);
        this.brickCeil = new CollisionElement(new Texture(Gdx.files.internal("bricks.png")), 0, 256);
        this.brickFloor = new CollisionElement(new Texture(Gdx.files.internal("bricks.png")), 0, 0);
        bricks.addAll(brick, brickCeil, brickFloor);
        this.viewport = new ExtendViewport(1024, 768);
        this.backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        gravity = 0.8f;
        jumpVelocity = 10;
        isJumping = false;
    }


    public void update(float delta) {

    }

    public void draw() {
        handleInput();
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, 1024, 768);
        spriteBatch.draw(new Texture("bricks.png"), 0, 0);
        hero.drawAnimated();
        if (!isJumping && hero.getY() > 64) {
            hero.setY(hero.getY() - gravity);
        }
        brick.draw();
        brickFloor.draw();
        brickCeil.draw();
        spriteBatch.end();
    }

    private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

        isMovingLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        isMovingRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        isMovingUp = Gdx.input.isKeyJustPressed(Input.Keys.UP);

        if (hero.checkCollision(brick)) {
            switch (lastKeyCode) {
                case Input.Keys.LEFT:
                    hero.moveBy(+200 * Gdx.graphics.getDeltaTime(), 0);
                    break;
                case Input.Keys.RIGHT:
                    hero.moveBy(-200 * Gdx.graphics.getDeltaTime(), 0);
                    break;
            }
        } else {
            if (isMovingLeft) {
                lastKeyCode = Input.Keys.LEFT;
                hero.moveBy(-200 * Gdx.graphics.getDeltaTime(), 0);
            }
            if (isMovingRight) {
                lastKeyCode = Input.Keys.RIGHT;
                hero.moveBy(+200 * Gdx.graphics.getDeltaTime(), 0);
            }
            if (isMovingUp && !isJumping) {
                lastKeyCode = Input.Keys.UP;
                isJumping = true;
                jumpVelocity = 10; // Initial jump velocity
            }
            if (isJumping) {
                hero.setY(hero.getY() + jumpVelocity);
                jumpVelocity -= gravity;
                if (jumpVelocity <= 0) {
                    isJumping = false;
                }
            }
        }

    }


    public Viewport getViewport() {
        return viewport;
    }

    public void jump() {
        float gravity = 0.8f;
        float velocity = 10;
        if (isJumping) {
            hero.setY(hero.getY() + jumpVelocity);
            jumpVelocity -= gravity;
            if (jumpVelocity <= 0) {
                isJumping = false;
            }
        }
    }


}
