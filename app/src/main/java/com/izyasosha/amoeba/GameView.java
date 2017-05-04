package com.izyasosha.amoeba;

/**
 * Created by Алексей on 01.05.2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import com.izyasosha.logics.Amoeba;
import com.izyasosha.logics.Enemy;
import com.izyasosha.logics.Food;
import com.izyasosha.logics.GameObject;

import java.util.ArrayList;



public class GameView extends View {

    Bitmap amoebaBMP= BitmapFactory.decodeResource(getResources(), R.drawable.amoeba);
    public float X=0;
    public float Y=0;
    Canvas mCanvas;
    GameView gameView;


    public GameView(Context cxt, AttributeSet attrs) {
        super(cxt, attrs);
        setMinimumHeight(100);
        setMinimumWidth(100);
    }

    protected void onDraw(Canvas cv) {
        mCanvas=cv;
        super.onDraw(mCanvas);
        mCanvas.drawColor(Color.argb(100,175,244,228));
       // mCanvas.drawBitmap(amoebaBMP, X, Y, null);

        ArrayList <GameObject> objectArrayList=new ArrayList<>();
        Amoeba amoeba=new Amoeba(gameView,amoebaBMP);
        objectArrayList.add(amoeba);
        for(GameObject obj:objectArrayList)
        {
          obj.draw(mCanvas);
           /* try
                {
                    TimeUnit.SECONDS.sleep(1);
                }
            catch (InterruptedException ex)
            {

            } */
        }
    }

    public boolean onTouchEvent( MotionEvent event) {
        // координаты нажатия
        X=event.getX();
        Y=event.getY();
        // переключатель в зависимости от типа события
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
              //  mCanvas.drawBitmap(amoebaBMP, X, Y, null);
                String button="";
                GameObject object;
                switch (button)
                {
                    case "buttonFood":
                        Food food=new Food();
                        object=food;
                        break;
                    case "buttonDanger":
                        Enemy enemy=new Enemy();
                        object=enemy;
                        break;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE: // движени
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:// ничего не делаем
        }
        return true;
    }
}
