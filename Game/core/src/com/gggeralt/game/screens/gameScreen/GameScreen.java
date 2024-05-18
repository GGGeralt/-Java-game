package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.List;

public class GameScreen extends ScreenAdapter {

    private Map map;
    private Texture brickTexture;
    private Texture backgroundTexture;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Array<Texture> bikerIdle; // do animacji jesli bedzie trzeba
    private CollisionElement hero;
    private boolean isJumping = false;
    private boolean isSecondJumpAvailable = false;
    private float jumpVelocity = 0;
    private final float gravity = -0.5f;
    private final float jump_force = 10;


    public GameScreen() {
        map = new Map();
        batch = new SpriteBatch();
         brickTexture = new Texture("bricks.png");
        backgroundTexture = new Texture("background.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.hero = new CollisionElement(new Texture(Gdx.files.internal("bikerIdle.png")), 0, 64);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        float backgroundX = camera.position.x - camera.viewportWidth / 2;
        float backgroundY = camera.position.y - camera.viewportHeight / 2;
        batch.begin();
        batch.draw(backgroundTexture, backgroundX, backgroundY, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        handleInput();

        updateJump();

        batch.begin();
        renderMap();
        renderHero();
        batch.end();
    }

    private void renderMap() {
        List<Vector2> coords = map.getCoords();
        for (Vector2 coord : coords) {
            batch.draw(brickTexture, coord.x, coord.y);
        }
    }

    private void renderHero() {
        batch.draw(hero.getTexture(), hero.getX(), hero.getY());
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (camera.position.x + camera.viewportWidth / 2 < 6400 + camera.viewportWidth) {
                camera.position.x += 5;
                hero.setX(camera.position.x - camera.viewportWidth / 2);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (camera.position.x - camera.viewportWidth / 2 > 0) {
                camera.position.x -= 5;
                hero.setX(camera.position.x - camera.viewportWidth / 2);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (!isJumping) {
                jump();
            } else if (isSecondJumpAvailable) {
                secondJump();
            }
        }
    }

    private void jump() {
        isJumping = true;
        isSecondJumpAvailable = true;
        jumpVelocity = jump_force;
    }

    private void secondJump() {
        isSecondJumpAvailable = false;
        jumpVelocity = jump_force;
    }

    private void updateJump() {
        if (isJumping) {
            hero.moveBy(0, jumpVelocity);
            jumpVelocity += gravity;
            if (hero.getY() <= 64) {
                hero.setY(64);
                isJumping = false;
                jumpVelocity = 0;
            }
        }
        List<Vector2> coords = map.getCoords();
        Rectangle heroBounds = new Rectangle(
                hero.getX(),
                hero.getY(),
                hero.getTexture().getWidth(),
                hero.getTexture().getHeight()
        );
        for (Vector2 coord : coords) {
            Rectangle brickBounds = new Rectangle(coord.x, coord.y, brickTexture.getWidth(), brickTexture.getHeight());
            if (heroBounds.overlaps(brickBounds)) {
                if (hero.getY() + hero.getTexture().getHeight() > coord.y &&
                        hero.getY() < coord.y + brickBounds.getHeight()) {

                    isJumping = false;
                    isSecondJumpAvailable = true;
                    //jumpVelocity = 0;
                    // hero.setY(coord.y - hero.getTexture().getHeight()); // jak sie odkomentuje to bedzie od góry kolizje lapało
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
       brickTexture.dispose();
        hero.getTexture().dispose();
    }


}
