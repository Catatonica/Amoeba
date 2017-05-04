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

import com.izyasosha.logics.Amoeba;
import com.izyasosha.logics.Model;

//import com.izyasosha.logics.Amoeba;


public class GameView extends View {
    Bitmap amoebaBMP= BitmapFactory.decodeResource(getResources(), R.drawable.amoeba);

    public float X=0;
    public float Y=0;
    Canvas mCanvas;


    public GameView(Context cxt, AttributeSet attrs) {
        super(cxt, attrs);
        setMinimumHeight(100);
        setMinimumWidth(100);
    }

    protected void onDraw(Canvas cv) {
        Model.setGameHeight(cv.getHeight());
        Model.setGameWidth(cv.getWidth());

        mCanvas=cv;
        super.onDraw(mCanvas);
        mCanvas.drawColor(Color.argb(100,175,244,228));
        mCanvas.drawBitmap(amoebaBMP, X, Y, null);
       // invalidate();
    }

    public boolean onTouchEvent( MotionEvent event) {
        // координаты нажатия
        X=event.getX();
        Y=event.getY();
        // переключатель в зависимости от типа события
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Model.setAmoeba(new Amoeba(X, Y, amoebaBMP));


                mCanvas.drawBitmap(amoebaBMP, X, Y, null);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE: // движени
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:// ничего не делаем
        }
        return true;
    }
}
