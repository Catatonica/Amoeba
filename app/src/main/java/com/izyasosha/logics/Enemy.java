package com.izyasosha.logics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Алексей on 02.05.2017.
 */

public final class Enemy extends Creature
{

    public Enemy(double x, double y, Bitmap bmp)
    {
        this.bmp = bmp;                    //возвращаем рисунок
        this.x = x;
        this.y = y;
    }
}
