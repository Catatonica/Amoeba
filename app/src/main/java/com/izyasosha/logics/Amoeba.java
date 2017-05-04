package com.izyasosha.logics;

/**
 * Created by Алексей on 01.05.2017.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.lang.Math;
import com.izyasosha.amoeba.GameView;

public final class Amoeba extends Creature
{

        //конструктор
        public Amoeba(GameView gameView, Bitmap bmp)
        {
            this.bmp = bmp;                    //возвращаем рисунок

            x = 0;                        //отступ по х нет
            y = gameView.getHeight() / 2; //делаем по центру
        }

        //рисуем наш спрайт
        public void onDraw(Canvas c)
        {
            c.drawBitmap(bmp, Math.round(x), Math.round(y), null);
        }

    void draw()
    {

    }

}
