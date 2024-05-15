package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.Vector;

public class Hero extends Actor {
    private int lastKeyCode;
    private Array<Texture> heroIdle = new Array<>();;
    private float gravity = 0.8f;
    private float speed = 5;
    private float jumpVelocity=15;
    private boolean isJumping=false, isMovingRight, isMovingLeft, isMovingUp;
    private CollisionElement hero;

    public Hero(Vector2 startPosition){
        super();

        heroIdle.addAll(new Texture(Gdx.files.internal("bikerIdle1.png")), new Texture(Gdx.files.internal("bikerIdle2.png")),
                new Texture(Gdx.files.internal("bikerIdle3.png")), new Texture(Gdx.files.internal("bikerIdle4.png")));

        this.hero = new CollisionElement(heroIdle, startPosition.x, startPosition.y);
    }

    public void Update(){
        handleInput();
        HandleMovement();
    }

    private void handleInput() {
        isMovingLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
        isMovingRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
        isMovingUp = Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

    private void HandleMovement() {
//        if (hero.) {
//            switch (lastKeyCode) {
//                case Input.Keys.LEFT:
//                    hero.moveBy(+200 * Gdx.graphics.getDeltaTime(), 0);
//                    break;
//                case Input.Keys.RIGHT:
//                    hero.moveBy(-200 * Gdx.graphics.getDeltaTime(), 0);
//                    break;
//            }
        {
            if(hero.collisionRectangle.overlaps())

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
                jumpVelocity -= gravity * 5;
                if (jumpVelocity <= 0) {
                    isJumping = false;
                }
            }
        }

        HandleGravity();
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
    private void  HandleGravity(){
        if (!isJumping && hero.getY() > 64) {
            hero.setY(hero.getY() - gravity * 2);
        }
    }

    public void drawAnimated() {
        hero.drawAnimated();
    }
}
