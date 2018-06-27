package com.nadershamma.Models;

import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nadershamma.Control.AssetBuilder;
import com.badlogic.gdx.math.Circle;

/**
 * Created by Nader on 10/04/2016.
 */
public class Dragon {
    private Vector2 coordinate;
    private Vector2 ascend;
    private Vector2 descend;

    private float rotation;
    private int width;
    private float height;

    private float originalY;

    private boolean isAlive;

    private Circle phisicalBoundry;

    public Dragon(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.originalY = y;
        coordinate = new Vector2(x, y);
        ascend = new Vector2(0, 0);
        descend = new Vector2(0, 266);
        phisicalBoundry = new Circle();
        isAlive = true;
    }

    public void update(float delta) {
        //descend.scl(delta);
        //ascend.add(descend.cpy().scl(delta));
        ascend.add(descend.x*delta, descend.y*delta);

        phisicalBoundry.set(coordinate.x+20, coordinate.y+10, 7.5f);

        if (ascend.y > 133) {
            ascend.y = 133;
        }

        // CEILING CHECK
        if (coordinate.y < -13) {
            coordinate.y = -13;
            ascend.y = 0;
        }

        //coordinate.add(ascend.cpy().scl(delta));
        coordinate.add(ascend.x*delta, ascend.y*delta);

        // Rotate counterclockwise
        if (ascend.y < 0) {
            rotation -= 600 * delta;

            if (rotation < -20) {
                rotation = -20;
            }
        }

        // Rotate clockwise
        if (isFalling() || !isAlive) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }

        }

    }

    public void updateReady(float runTime) {
        coordinate.y = 2 * (float) Math.sin(7 * runTime) + originalY;
        phisicalBoundry.set(coordinate.x+20, coordinate.y+10, 7.5f);
    }

    public boolean isFalling() {
        return ascend.y > 55;
    }

    public boolean shouldntFlap() {
        return ascend.y > 35 || !isAlive;
    }

    public void onClick() {
        if (isAlive) {
            AssetBuilder.flap.play();
            ascend.y = -105;
        }
    }

    public void die() {
        isAlive = false;
        ascend.y = 0;
    }

    public void decelerate() {
        descend.y = 0;
    }

    public void onRestart(float y) {
        rotation = 0;
        coordinate.y = y;
        phisicalBoundry.set(coordinate.x+20, coordinate.y+10, 7.5f);
        ascend.x = 0;
        ascend.y = 0;
        descend.x = 0;
        descend.y = 266;
        isAlive = true;
    }

    public float getX() {
        return coordinate.x;
    }

    public float getY() {
        return coordinate.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getPhisicalBoundry() {
        return phisicalBoundry;
    }

    public boolean isAlive() {
        return isAlive;
    }


}
