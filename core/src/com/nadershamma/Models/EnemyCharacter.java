package com.nadershamma.Models;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Nader on 10/04/2016.
 */
public class EnemyCharacter {
    // Protected is similar to private, but allows inheritance by subclasses.
    protected Vector2 coordinates;
    protected Vector2 vector;
    protected int width;
    protected int height;
    protected boolean isScrolled;

    public EnemyCharacter(float x, float y, float scrollSpeed) {
        coordinates = new Vector2(x, y);
        vector = new Vector2(scrollSpeed, 0);
        isScrolled = false;
    }

    public void update(float delta) {
        //coordinates.add(vector.cpy().scl(delta));
        coordinates.add(vector.x*delta, vector.y*delta);
        // If the Scrollable object is no longer visible:
        if (coordinates.x + width < 0) {
            isScrolled = true;
        }
    }
    public void reset(float newX) {
        coordinates.x = newX;
        isScrolled = false;
    }

    public void stop() {
        vector.x = 0;
    }

    // Getters for instance variables
    public boolean isScrolled() {
        return isScrolled;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void onRestart(float x, float scrollSpeed) {
        vector.x = scrollSpeed;
        reset(x);
    }
}


