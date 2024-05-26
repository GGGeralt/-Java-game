package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Map {
    private final List<Vector2> coords;
    private final Vector2 chestCoord;

    public Map() {
        coords = new ArrayList<>();

        IntStream.range(0, 100).forEach(i -> coords.add(new Vector2(i * 64, 0)));
        IntStream.range(5, 25).forEach(i -> coords.add(new Vector2(i * 64, 128)));
        IntStream.range(25, 29).forEach(i -> coords.add(new Vector2(i * 64, 192)));
        IntStream.range(2, 6).forEach(i -> coords.add(new Vector2( 20*64, i*64)));
        IntStream.range(1, 4).forEach(i -> coords.add(new Vector2( 29*64, i*64)));

        IntStream.range(35, 45).forEach(i -> coords.add(new Vector2(i * 64, 128)));
        IntStream.range(39, 41).forEach(i -> coords.add(new Vector2(i * 64, 192)));
        IntStream.range(50, 52).forEach(i -> coords.add(new Vector2(i * 64, 128)));
        IntStream.range(53, 57).forEach(i -> coords.add(new Vector2(i * 64, 192)));
        IntStream.range(56, 67).forEach(i -> coords.add(new Vector2(i * 64, 256)));
        IntStream.range(75, 80).forEach(i -> coords.add(new Vector2(i * 64, 128)));
        IntStream.range(83, 93).forEach(i -> coords.add(new Vector2(i * 64, 192)));
        IntStream.range(95, 99).forEach(i -> coords.add(new Vector2(i * 64, 320)));

        chestCoord = new Vector2(27 * 64, 64);

    }

    public List<Vector2> getCoords() {
        return coords;
    }
    public Vector2 getChestCoords() {
        return chestCoord;
    }
}

