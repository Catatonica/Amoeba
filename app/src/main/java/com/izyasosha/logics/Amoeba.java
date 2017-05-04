package com.izyasosha.logics;

/**
 * Created by Алексей on 01.05.2017.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.izyasosha.amoeba.GameView;

public final class Amoeba extends Creature
{
        /**Объект главного класса*/
        GameView gameView;

        //конструктор
        public Amoeba(GameView gameView, Bitmap bmp)
        {
            this.gameView = gameView;
            this.bmp = bmp;                    //возвращаем рисунок

            this.x = 0;                        //отступ по х нет
            this.y = gameView.getHeight() / 2; //делаем по центру
        }

        //рисуем наш спрайт
        public void onDraw(Canvas c)
        {
            c.drawBitmap(bmp, x, y, null);
        }

    void draw()
    {

    }

}
