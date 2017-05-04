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
import com.izyasosha.logics.Enemy;
import com.izyasosha.logics.Food;
import com.izyasosha.logics.GameObject;
import com.izyasosha.logics.Model;
import java.util.ArrayList;

import static com.izyasosha.logics.Model.gameObjects;

//import com.izyasosha.logics.Amoeba;


public class GameView extends View {
    Bitmap amoebaBMP= BitmapFactory.decodeResource(getResources(), R.drawable.amoeba);
    Bitmap enemyBMP= BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
    Bitmap foodBMP= BitmapFactory.decodeResource(getResources(), R.drawable.food1);

    public float X=0;
    public float Y=0;
    Canvas mCanvas;
    GameView gameView;
  //  ArrayList<GameObject> objectArrayList=new ArrayList<>();


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

        Model.setAmoeba(new Amoeba(Model.getGameWidth()/2,Model.getGameHeight()/2, amoebaBMP));
        gameObjects.add(Model.getAmoeba());
        for(GameObject obj: gameObjects)
        {
            obj.draw(mCanvas);
             /* try
+                {
+                    TimeUnit.SECONDS.sleep(1);
+                }
+            catch (InterruptedException ex)
+            {
+
+            } */
        }
    }

    public boolean onTouchEvent( MotionEvent event) {
        // координаты нажатия
        X=event.getX();
        Y=event.getY();
        GameObject object=null;
        // переключатель в зависимости от типа события
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                switch (Model.getMode())
                {
                    case FOOD:
                          Model.setFood(new Food(X,Y,foodBMP));
                          object=Model.getFood();
                          break;
                    case ENEMY:
                          Model.setEnemy(new Enemy(X,Y,enemyBMP));
                          object=Model.getEnemy();
                          break;
                }
                gameObjects.add(object);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE: // движени
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:// ничего не делаем
        }
        return true;
    }
}
