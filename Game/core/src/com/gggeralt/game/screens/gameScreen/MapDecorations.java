package com.gggeralt.game.screens.gameScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class MapDecorations {
    private final List<Vector2> coords;

    public MapDecorations() {
        coords = new ArrayList<>();
        coords.add(new Vector2(64,64));
        int counter=0;
        for(int i=1;i<28;i++){
            counter++;
            if(i%3==0){
                if(counter%2==0){
                    coords.add(new Vector2(i*256,360));
                }else
                    coords.add(new Vector2(i*256,420));
            }


           // coords.add(new Vector2(i*320,480));
           // coords.add(new Vector2(i*180,420));
        }
    }
    public List<Vector2> getCoords() {
        return coords;
    }


}
