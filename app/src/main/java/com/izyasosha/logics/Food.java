package com.izyasosha.logics;

import android.graphics.Canvas;

/**
 * Created by Алексей on 02.05.2017.
 */

public final class Food extends GameObject
{
    public void draw(Canvas cv)
    {
        cv.drawBitmap(bmp, Math.round(x), Math.round(y), null);
    }
}
