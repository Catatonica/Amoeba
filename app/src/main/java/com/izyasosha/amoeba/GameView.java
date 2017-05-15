package com.izyasosha.amoeba;

/**
 * Created by Алексей on 01.05.2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.widget.Toast;

import com.izyasosha.logics.Amoeba;
import com.izyasosha.logics.Enemy;
import com.izyasosha.logics.Food;
import com.izyasosha.logics.Model;
import com.izyasosha.logics.State;

import static com.izyasosha.logics.Model.enemyArrayList;
import static com.izyasosha.logics.Model.foodArrayList;

public class GameView extends View {

    Bitmap enemyBMP= BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
    Bitmap foodBMP= BitmapFactory.decodeResource(getResources(), R.drawable.food1);


    static Canvas mCanvas;

    public GameView(Context cxt, AttributeSet attrs) {
        super(cxt, attrs);
        mCanvas = new Canvas();
        setMinimumHeight(100);
        setMinimumWidth(100);
        setWillNotDraw(false);
    }

    boolean initialized = false;
    @Override
    protected void onDraw(Canvas cv) {
        if(!initialized) {
            initialized = true;
            Model.setGameHeight(cv.getHeight());
            Model.setGameWidth(cv.getWidth());
        }
        super.onDraw(cv);
        cv.drawColor(Color.argb(255, 228, 219, 138));
        Model.getAmoeba().draw(cv);
        for(Food food:foodArrayList)
        {
            food.draw(cv);
        }
        for(Enemy enemy:enemyArrayList)
        {
            enemy.draw(cv);
        }
    }


    public boolean onTouchEvent( MotionEvent event) {
        // координаты нажатия
        double X=event.getX();
        double Y=event.getY();
        // переключатель в зависимости от типа события
        if (event.getAction() == MotionEvent.ACTION_DOWN)
                switch (Model.getMode())
                {
                    case FOOD:
                        foodArrayList.add(new Food(X,Y,foodBMP));
                        break;
                    case ENEMY:
                        enemyArrayList.add(new Enemy(X,Y,enemyBMP));
                        break;
                    case NONE:
                        break;
                }
        return true;
    }
}
