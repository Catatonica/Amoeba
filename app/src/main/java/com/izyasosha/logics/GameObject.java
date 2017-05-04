package com.izyasosha.logics;

import android.graphics.Bitmap;

/**
 * Created by Алексей on 02.05.2017.
 */

public abstract class GameObject
{
    protected double x=0, y=0;
    protected byte size=0;
    protected Bitmap bmp=null;

    abstract void draw();

}
