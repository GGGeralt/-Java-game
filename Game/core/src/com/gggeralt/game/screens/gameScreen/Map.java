package com.gggeralt.game.screens.gameScreen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Map {
    private final ArrayList<Vector2> coords;
    private final ArrayList<CollisionElement> bricks;
    private final float mapWidth;
    private final float mapHeight;

    public Map() {
        coords = new ArrayList<>();
        bricks = new ArrayList<>();

        IntStream.range(0, 100).forEach(i -> coords.add(new Vector2(i * 64, 0)));
        IntStream.range(5, 25).forEach(i -> coords.add(new Vector2(i * 64, 128)));
        IntStream.range(25, 29).forEach(i -> coords.add(new Vector2(i * 64, 192)));
        IntStream.range(35, 45).forEach(i -> coords.add(new Vector2(i * 64, 128)));
        IntStream.range(39, 41).forEach(i -> coords.add(new Vector2(i * 64, 192)));
        IntStream.range(50, 52).forEach(i -> coords.add(new Vector2(i * 64, 128)));
        IntStream.range(53, 57).forEach(i -> coords.add(new Vector2(i * 64, 192)));
        IntStream.range(56, 67).forEach(i -> coords.add(new Vector2(i * 64, 256)));
        IntStream.range(75, 80).forEach(i -> coords.add(new Vector2(i * 64, 128)));
        IntStream.range(83, 93).forEach(i -> coords.add(new Vector2(i * 64, 192)));
        IntStream.range(95, 99).forEach(i -> coords.add(new Vector2(i * 64, 320)));

        coords.forEach(coord -> bricks.add(new CollisionElement(new Texture(Gdx.files.internal("bricks.png")), coord.x, coord.y)));

        mapWidth = 100 * 64;
        mapHeight = 320 + 64;
    }

    public ArrayList<CollisionElement> getBricks() {
        return bricks;
    }
    public float getMapWidth() {
        return mapWidth;
    }

    public float getMapHeight() {
        return mapHeight;
    }
}

