package com.izyasosha.logics;

import java.util.Random;

/**
 * Created by Алексей on 02.05.2017.
 */

public abstract class Creature extends GameObject
{
    protected static Random rnd = new Random();
    protected byte velocity=10;

    private double direction = (int)rnd.nextInt(360) / 360. *2*Math.PI;

    public void moveRandomly(){
        direction+=(rnd.nextInt(100) - 50) / 360. *2*Math.PI;
        setX(x + velocity*Math.cos(direction));
        setY(y + velocity*Math.sin(direction));
    }
}

