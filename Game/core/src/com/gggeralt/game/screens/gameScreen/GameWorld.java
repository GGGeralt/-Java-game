package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.print.attribute.standard.PagesPerMinute;


public class GameWorld {
    private final SpriteBatch spriteBatch;
    private final Viewport viewport;
   // private final Texture backgroundTexture;
    private final CollisionElement hero;
    private final Array<Texture> bikerIdle;
    private final float gravity;
    private float jumpVelocity;
    private boolean isJumping, isMovingRight, isMovingLeft, isMovingUp;
    private final Map map;
    private OrthographicCamera cam;



    public GameWorld() {
        cam=new OrthographicCamera();
        cam.update();
        map = new Map();

        this.spriteBatch = new SpriteBatch();
        bikerIdle = new Array<>();
        bikerIdle.addAll(new Texture(Gdx.files.internal("bikerIdle1.png")), new Texture(Gdx.files.internal("bikerIdle2.png")),
                new Texture(Gdx.files.internal("bikerIdle3.png")), new Texture(Gdx.files.internal("bikerIdle4.png")));
        this.hero = new CollisionElement(bikerIdle, 0, 64);
        this.viewport = new ExtendViewport(1024, 768,cam);
       // this.backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        gravity = 0.75f;
        jumpVelocity = 70;
        isJumping = false;
    }

    public void update(float delta) {
        handleInput();
        cameraUpdate();
    }

    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        if (!isJumping && hero.getY() > 64) {
            hero.setY(hero.getY() - gravity);
        }
        for (CollisionElement brick : map.getBricks()) {
            brick.draw();
        }
        hero.drawAnimated();
        spriteBatch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
        isMovingLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        isMovingRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        isMovingUp = Gdx.input.isKeyJustPressed(Input.Keys.UP);

        if (isMovingLeft&&hero.getX()>0) {
            hero.moveBy(-200 * Gdx.graphics.getDeltaTime(), 0);
        }
        if (isMovingRight&&hero.getX()<6400) {
            hero.moveBy(200 * Gdx.graphics.getDeltaTime(), 0);
        }
        if (isMovingUp && !isJumping) {
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


    public Viewport getViewport() {
        return viewport;
    }
    public void cameraUpdate(){

        cam.position.set(10000,10000,0);
        cam.update();
        System.out.println(cam.position.x);


    }


}
