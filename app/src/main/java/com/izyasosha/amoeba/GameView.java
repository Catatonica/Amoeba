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

    public float X=0;
    public float Y=0;
    static Canvas mCanvas;

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
        mCanvas.drawColor(Color.argb(255,228,219,138));
        runTimer();
        renderFrame();
    }
    private void runTimer() {
        final Handler handler = new Handler();
        boolean post = handler.post(new Runnable() {
            @Override
            public void run() {
                if (Model.getAmoeba().getState() == State.DEATH) {
                    //showMessage(this);
                    return;
                }
                Model.getAmoeba().setNextState();
                Model.moveObjects();
                Model.checkIntersections();
                Model.killEnemies();
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void renderFrame()
    {
        Model.getAmoeba().draw(mCanvas);
        for(Food food:foodArrayList)
        {
            food.draw(mCanvas);
        }
        for(Enemy enemy:enemyArrayList)
        {
            enemy.draw(mCanvas);
        }
        invalidate();
    }


    public boolean onTouchEvent( MotionEvent event) {
        // координаты нажатия
        X=event.getX();
        Y=event.getY();
        // переключатель в зависимости от типа события
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
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
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE: // движени
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL: // ничего не делаем
        }
        return true;
    }
}
