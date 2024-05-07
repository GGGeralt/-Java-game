package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Hero {
    private int lastKeyCode;
    private Array<Texture> bikerIdle;

    private float gravity;
    private float jumpVelocity;
    private boolean isJumping, isMovingRight, isMovingLeft, isMovingUp;
    private CollisionElement hero;

    void repair(){
        isMovingLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        isMovingRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        isMovingUp = Gdx.input.isKeyJustPressed(Input.Keys.UP);
        bikerIdle = new Array<>();
        bikerIdle.addAll(new Texture(Gdx.files.internal("bikerIdle1.png")), new Texture(Gdx.files.internal("bikerIdle2.png")),
                new Texture(Gdx.files.internal("bikerIdle3.png")), new Texture(Gdx.files.internal("bikerIdle4.png")));

        gravity = 0.8f;
        jumpVelocity = 15;
        isJumping = false;
        this.hero = new CollisionElement(bikerIdle, 0, 64);
    }

    public void Update(){

    }

    public void drawAnimated()
    {
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
    private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

//    private void HandleMovement() {
//        if (hero.checkCollision(brick)) {
//            switch (lastKeyCode) {
//                case Input.Keys.LEFT:
//                    hero.moveBy(+200 * Gdx.graphics.getDeltaTime(), 0);
//                    break;
//                case Input.Keys.RIGHT:
//                    hero.moveBy(-200 * Gdx.graphics.getDeltaTime(), 0);
//                    break;
//            }
//        } else {
//            if (isMovingLeft) {
//                lastKeyCode = Input.Keys.LEFT;
//                hero.moveBy(-200 * Gdx.graphics.getDeltaTime(), 0);
//            }
//            if (isMovingRight) {
//                lastKeyCode = Input.Keys.RIGHT;
//                hero.moveBy(+200 * Gdx.graphics.getDeltaTime(), 0);
//            }
//            if (isMovingUp && !isJumping) {
//                lastKeyCode = Input.Keys.UP;
//                isJumping = true;
//                jumpVelocity = 10; // Initial jump velocity
//            }
//            if (isJumping) {
//                hero.setY(hero.getY() + jumpVelocity);
//                jumpVelocity -= gravity * 5;
//                if (jumpVelocity <= 0) {
//                    isJumping = false;
//                }
//            }
//        }
//
//        HandleGravity();
//    }
    private void  HandleGravity(){
        if (!isJumping && hero.getY() > 64) {
            hero.setY(hero.getY() - gravity * 2);
        }
    }


}
