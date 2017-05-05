package com.izyasosha.logics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Алексей on 02.05.2017.
 */

public abstract class GameObject
{

    protected double x=0;
    protected double y=0;
    protected byte size=0;
    protected Bitmap bmp=null;

    public void draw(Canvas c)
    {
        c.drawBitmap(bmp, Math.round(x)- bmp.getWidth()/2, Math.round(y) - bmp.getHeight()/2, null);
    }

}
