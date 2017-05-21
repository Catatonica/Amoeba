package com.izyasosha.logics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Алексей on 02.05.2017.
 */

public final class Enemy extends Creature
{
    public double distanceTo=0;

    public Enemy(double x, double y, Bitmap bmp)
    {
        this.bmp = bmp;
        this.x = x;
        this.y = y;
    }

    private double direction = (int)rnd.nextInt(360) / 360. *2*Math.PI;
    @Override
    public void moveRandomly(){
        direction+=(rnd.nextInt(100) - 50) / 360. *2*Math.PI;
        setX(x + velocity*Math.cos(direction));
        setY(y + velocity*Math.sin(direction));
    }
}
