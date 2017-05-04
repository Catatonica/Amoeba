package com.izyasosha.amoeba;

/**
 * Created by Алексей on 01.05.2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;

import com.izyasosha.logics.Amoeba;


public class GameView extends View implements View.OnTouchListener{
    private Amoeba amoeba;
    Bitmap amoebaBMP;
    public float X;
    public float Y;
    Canvas cv;


    public GameView(Context cxt, AttributeSet attrs) {
        super(cxt, attrs);
        setMinimumHeight(100);
        setMinimumWidth(100);
        setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event) {

        // переключатель в зависимости от типа события
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            {
                X=event.getX();
                Y=event.getY();// нажатие

                amoebaBMP = BitmapFactory.decodeResource(getResources(), R.drawable.amoeba);
                amoeba= new Amoeba(this, amoebaBMP);
                onDraw(cv);
            }
            break;
            case MotionEvent.ACTION_MOVE: // движени
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                // ничего не делаем

        }
        return true;
    }


    protected void onDraw(Canvas cv) {

        this.cv=cv;
        cv.drawColor(Color.argb(100,175,244,228));
        cv.drawBitmap(amoebaBMP,X , Y, null);
       /* Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStrokeWidth(5);
        cv.drawLine(20, 0, 20, cv.getHeight(), p);
        */

    }



}
