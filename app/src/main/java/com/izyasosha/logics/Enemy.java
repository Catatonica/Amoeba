package com.izyasosha.logics;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.izyasosha.amoeba.GameView;

/**
 * Created by Алексей on 02.05.2017.
 */

public final class Enemy extends Creature
{
    public Enemy(GameView gameView, Bitmap bmp)
    {
        this.bmp = bmp;
    }

    public void draw(Canvas cv)
    {
        cv.drawBitmap(bmp, Math.round(x), Math.round(y), null);
    }
}
