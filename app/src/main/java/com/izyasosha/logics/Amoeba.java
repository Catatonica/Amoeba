package com.izyasosha.logics;

/**
 * Created by Алексей on 01.05.2017.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.lang.Math;
import com.izyasosha.amoeba.GameView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
public final class Amoeba extends Creature
{

        //конструктор
        public Amoeba(GameView gameView, Bitmap bmp)
        {
            this.bmp = bmp;
            x = gameView.getWidth() / 2;
            y = gameView.getHeight() / 2; //делаем по центру
        }

    public void draw(Canvas cv)
    {
        cv.drawBitmap(bmp, Math.round(x), Math.round(y), null);
    }

}
