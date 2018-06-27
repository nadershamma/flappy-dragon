package com.nadershamma.Models;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Nader on 14/04/2016.
 */
public class Background {

    protected Vector2 coordinates;
    protected Vector2 vector;
    protected int width;
    protected int height;
    protected boolean isScrolled;

    public Background(float x, float y, int width, int height, float scrollSpeed) {
        this.width = width;
        this.height = height;
        coordinates = new Vector2(x, y);
        vector = new Vector2(scrollSpeed, 0);
        isScrolled = false;
    }

    public void update(float delta) {
        coordinates.add(vector.x*delta, vector.y*delta);

        if (coordinates.x + width < 0) {
            isScrolled = true;
        }
    }

    public void reset(float newX) {
        coordinates.x = newX;
        isScrolled = false;
    }

    public void onRestart(float x, float scrollSpeed) {
        coordinates.x = x;
        vector.x = scrollSpeed;
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

}
