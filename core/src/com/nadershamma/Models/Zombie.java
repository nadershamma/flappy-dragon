package com.nadershamma.Models;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by Nader on 10/04/2016.
 */
public class Zombie extends EnemyCharacter {
    private Random r;
    private Rectangle phisicalBoundry;

    private static float groundY;
    private static int maxHeight;
    public Zombie(float x, float y, float scrollSpeed) {

        super(x, y, scrollSpeed);
        r = new Random();
        maxHeight = (int)y/2;
        height = r.nextInt(maxHeight/2)+ (maxHeight/2);

        width = height;
        phisicalBoundry = new Rectangle();
        groundY = y;
        coordinates.y =  groundY - height;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        phisicalBoundry.set(coordinates.x+(width/3), coordinates.y+(height/5), width/3, height-(height/4));
    }

    public void reset(float newX) {
        // Call the reset method in the superclass (Scrollable)
        super.reset(newX);
        height = r.nextInt(maxHeight/2)+ (maxHeight/2);
        width = height;
        coordinates.y =  groundY - height;
        phisicalBoundry.set(coordinates.x+(width/3), coordinates.y+(height/5), width/3, height-(height/4));
    }

    public void onRestart(float x, float scrollSpeed) {
        vector.x = scrollSpeed;
        reset(x);
    }

    public Rectangle getPhisicalBoundry()
    {
        return phisicalBoundry;
    }

    public boolean collision(Dragon dragon) {
        if (coordinates.x < dragon.getX() + dragon.getWidth()) {
            return (Intersector.overlaps(dragon.getPhisicalBoundry(), phisicalBoundry));
        }
        return false;
    }
}
