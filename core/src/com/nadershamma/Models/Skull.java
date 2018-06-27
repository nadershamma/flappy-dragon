package com.nadershamma.Models;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by Nader on 10/04/2016.
 */
public class Skull extends EnemyCharacter {
    private Random r;
    private Circle phisicalBoundry;
    private float screenHeight, yPos, minY, maxY, minMaxY;

    public Skull(float x, float y,float scrollSpeed) {
        super(x, y, scrollSpeed);
        r = new Random();
        screenHeight = y;
        minY = 10;
        maxY = screenHeight/2;
        minMaxY = (maxY - minY)-minY;
        yPos = r.nextFloat()*minMaxY;
        coordinates.y = yPos;
        width = 30;
        height = 30;

        phisicalBoundry = new Circle();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        phisicalBoundry.set(coordinates.x+(width/2), coordinates.y+(height/2)+1, 7.5f);
    }

    public void reset(float newX) {
        // Call the reset method in the superclass (Scrollable)
        super.reset(newX);
        coordinates.y = r.nextFloat()*minMaxY;
    }

    public void onRestart(float x, float scrollSpeed) {
        vector.x = scrollSpeed;
        reset(x);
    }

    public Circle getPhisicalBoundry()
    {
        return phisicalBoundry;
    }

    public boolean collision(Dragon dragon) {
        if (coordinates.x < dragon.getX() + dragon.getWidth()) {
            return (Intersector.overlaps(phisicalBoundry, dragon.getPhisicalBoundry()));
        }
        return false;
    }
}
