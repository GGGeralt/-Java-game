package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Model {
    private Integer width;
    private Integer height;
    private Texture texture;
    private Integer x;
    private Integer y;

    public Model(Texture texture, Integer width, Integer height, Integer x, Integer y) {
        this.width = width;
        this.height = height;
        this.texture =texture;
        this.x = x;
        this.y = y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
