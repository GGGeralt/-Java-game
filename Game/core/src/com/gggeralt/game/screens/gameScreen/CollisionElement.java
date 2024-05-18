package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class CollisionElement extends Actor {

    private float stateTime;
    private Animation<Texture> animation;
    private SpriteBatch spriteBatch;
    private Texture currentFrame;
    private boolean isMovingLeft, isMovingRight, isJumping;
    private float jumpVelocity;
    private float gravity;
    private com.badlogic.gdx.math.Rectangle collisionRectangle;
    private Texture texture;

    public CollisionElement(Array<Texture> pngSet, float x, float y) {

        this.setBounds(x, y, 64, 64);
        this.collisionRectangle = new Rectangle(x, y, getWidth(), getHeight());
        animation = new Animation<>(0.1f, pngSet);
        this.spriteBatch = new SpriteBatch();
    }

    public CollisionElement(Texture texture, float x, float y) {
        this.setBounds(x, y, 64, 64);
        this.texture = texture;
        this.collisionRectangle = new Rectangle(x, y, getWidth(), getHeight());
        this.spriteBatch = new SpriteBatch();

    }

    public void drawAnimated() {

        if (isMovingLeft || isMovingRight) {
            stateTime += Gdx.graphics.getDeltaTime();
        } else {
            stateTime = 0; // Reset state time when not moving
        }
        currentFrame = animation.getKeyFrame(stateTime, isMovingLeft || isMovingRight);
        if (!isJumping && collisionRectangle.getY() > 0) {
            collisionRectangle.setY(collisionRectangle.getY() - gravity);
        }


        spriteBatch.begin();
        spriteBatch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
        spriteBatch.end();
    }

    public void draw() {
        spriteBatch.begin();
        spriteBatch.draw(texture, getX(), getY(), getWidth(), getHeight());
        spriteBatch.end();
    }

    public void handleInput(CollisionElement other) {
        float x = getX();
        if (checkCollision(other)) {
            System.out.println("sciana");
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) setX(x -= 200 * Gdx.graphics.getDeltaTime());
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) setX(x + 200 * Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void moveBy(float x, float y) {
        super.moveBy(x, y);
        collisionRectangle.setPosition((int) getX(), (int) getY());
    }

    public boolean checkCollision(CollisionElement other) {
        return collisionRectangle.overlaps(other.collisionRectangle);
    }

}
