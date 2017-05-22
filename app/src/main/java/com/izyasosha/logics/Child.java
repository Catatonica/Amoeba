package com.izyasosha.logics;

import android.graphics.Bitmap;

/**
 * Created by Alexander on 21.05.2017.
 */

public class Child extends Creature {
    private static Bitmap childBmp;
    public static void setBmp(Bitmap bmp){
        childBmp = bmp;
    }
    public Child(double x, double y) {
        this.x = x;
        this.y = y;
        bmp = childBmp;
    }
}
