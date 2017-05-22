package com.izyasosha.logics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Алексей on 02.05.2017.
 */

public abstract class GameObject
{

    protected double x=0;
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }


    protected double y=0;
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    protected byte size=0;
    protected Bitmap bmp=null;

    public void draw(Canvas c)
    {
        c.drawBitmap(bmp, Math.round(x)- bmp.getWidth()/2, Math.round(y) - bmp.getHeight()/2, null);
    }

}
