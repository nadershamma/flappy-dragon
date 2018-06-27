package com.nadershamma.Models;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Nader on 10/04/2016.
 */
public class Coin {
    private Random r;
    protected Vector2 coordinates;
    protected Vector2 vector;
    protected int width;
    protected int height;
    protected boolean isScrolled;
    private Circle phisicalBoundry;
    private float screenHeight, yPos, minY, maxY, minMaxY;

    public Coin(float x, float y, float scrollSpeed) {
        r = new Random();
        screenHeight = y;
        minY = 10;
        maxY = screenHeight-40;
        minMaxY = (maxY - minY)-minY;
        yPos = r.nextFloat()*minMaxY;

        coordinates = new Vector2(x, yPos);

        vector = new Vector2(scrollSpeed, 0);
        isScrolled = false;
        phisicalBoundry = new Circle();
        width = 15;
        height = 15;
    }

    public void update(float delta) {
        //coordinates.add(vector.cpy().scl(delta));
        //coordinates.y = r.nextInt(90)+15;
        coordinates.add(vector.x*delta, vector.y*delta);
        
        if (coordinates.x + width < 0) {
            isScrolled = true;
        }
        phisicalBoundry.set(coordinates.x+(width/2), coordinates.y+(width/2)+1, 7.5f);
    }

    public void reset(float newX) {
        coordinates.x = newX;
        coordinates.y = r.nextFloat()*minMaxY;
        isScrolled = false;
    }

    public void stop() {
        vector.x = 0;
    }

    public Circle getPhisicalBoundry()
    {
        return phisicalBoundry;
    }
    // Getters for instance variables
    public boolean isScrolled() {
        return isScrolled;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getTailX() {
        return coordinates.x + width;
    }

    public float getX() {
        return coordinates.x;
    }
    public float getY() {
        return coordinates.y;
    }

    public void onRestart(float x, float scrollSpeed) {
        vector.x = scrollSpeed;
        reset(x);
    }

    public boolean coinGrab(Dragon dragon) {
        if (coordinates.x < dragon.getX() + dragon.getWidth()) {
            return (Intersector.overlaps(phisicalBoundry, dragon.getPhisicalBoundry()));
        }
        return false;
    }
}
