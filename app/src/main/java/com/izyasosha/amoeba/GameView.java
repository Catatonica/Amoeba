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

import com.izyasosha.logics.Amoeba;

public class GameView extends View {
    private Amoeba amoeba;
    Bitmap amoebas;

    public GameView(Context cxt, AttributeSet attrs) {
        super(cxt, attrs);
        setMinimumHeight(100);
        setMinimumWidth(100);
        amoebas= BitmapFactory.decodeResource(getResources(), R.drawable.amoeba);
        amoeba= new Amoeba(this, amoebas);
    }


    @Override
    protected void onDraw(Canvas cv) {
        cv.drawColor(Color.WHITE);
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStrokeWidth(5);
        cv.drawLine(20, 0, 20, cv.getHeight(), p);
        cv.drawBitmap(amoebas, 5, 120, null);
    }
}
