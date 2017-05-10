package com.izyasosha.logics;

/**
 * Created by Алексей on 01.05.2017.
 */
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.TextView;

import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;

import com.izyasosha.amoeba.AmoebaActivity;
import com.izyasosha.amoeba.GameView;
import com.izyasosha.amoeba.R;

public final class Amoeba extends Creature
{
    int satiety;
    int energies;
    byte R;
    byte t=0;
    int d=0;
        //конструктор
        public Amoeba(double x, double y, Bitmap bmp)
        {
            this.bmp = bmp;                    //возвращаем рисунок
            this.x = x;
            this.y = y;
            satiety=80;
            energies=80;
            R=5;
            setState(State.NEUTRAL);
        }


    //не даёт выйти координатам за край поля, вместо этого телепортирует на противоположный край
    @Override
    public void setX(double x) {
        if(x<0){
            this.x = Model.getGameWidth() + x;
        }
        else if (x>=Model.getGameWidth()){
            this.x = x - Model.getGameWidth();
        }
        else {
            this.x = x;
        }
    }

    @Override
    public void setY(double y) {
        if(y<0){
            this.y = Model.getGameHeight() + y;
        }
        else if (y >= Model.getGameHeight()){
            this.y = y - Model.getGameHeight();
        }
        else {
            this.y = y;
        }
    }
    private State state;
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
        switch (state)
        {
            case NEUTRAL:
                velocity=5;
                t++;
                break;
            case HUNGRINESS: velocity=10; break;
            case WARNING: velocity=15; break;
            case REST: velocity=0; break;
            case DEATH:
                velocity=0;
                Model.Die(this);
                break;
            case DIVISION: velocity=0; break;
        }
    }


    public void update(){};
    public void findFood() {
        if (Model.foodArrayList.size() == 0) {
            moveRandomly(); //Если еды нет, вызвать случайное передвижение
            return;
        }

        Double foodX, foodY;
        double minDist = Double.MAX_VALUE;
        Food nearestFood = null;
        for(Food f : Model.foodArrayList) {
            double d = Math.sqrt(Math.pow(f.x - x, 2) + Math.pow(f.y - y, 2));
            if(d < minDist) {
                minDist = d;
                nearestFood = f;
            }
        }
        //Находит ближайшую еду
        foodX = nearestFood.x;
        foodY = nearestFood.y;

        //Двигается к ней
        setX(x + velocity / Math.sqrt(Math.pow(foodX - x, 2) + Math.pow(foodY - y, 2)) * (foodX - x));
        setY(y + velocity / Math.sqrt(Math.pow(foodX - x, 2) + Math.pow(foodY - y, 2)) * (foodY - y));
    }

    public void runAway(Enemy nearestEnemy) {

        double enemyX = nearestEnemy.x;
        double enemyY = nearestEnemy.y;

        //Двигается от него
        setX(x - velocity / Math.sqrt(Math.pow(enemyX - x, 2) + Math.pow(enemyY - y, 2)) * (enemyX - x));
        setY(y - velocity / Math.sqrt(Math.pow(enemyX - x, 2) + Math.pow(enemyY - y, 2)) * (enemyY - y));
    }


    public void setNextState(){
        satiety--;

        switch (state)
        {
            case NEUTRAL:
                if(d<20)
                {
                   setState(State.WARNING);
                    return;
                }
                if(satiety<70)
                {
                    setState(State.HUNGRINESS);
                    return;
                }
                if(energies<50)
                {
                    setState(State.REST);
                    return;
                }
                if(t==10)
                {
                    setState(State.DIVISION);
                    return;
                }
                break;
            case WARNING:
                if(d<2*R)
                {
                    setState(State.DEATH);
                    return;
                }
                if(satiety<70)
                {
                    setState(State.HUNGRINESS);
                    return;
                }
                if(d>=30)
                {
                    setState(State.NEUTRAL);
                    t=0;
                    return;
                }
                break;
            case HUNGRINESS:
                if(satiety==0)
                {
                    setState(State.DEATH);
                    return;
                }
                if(d<20)
                {
                    setState(State.WARNING);
                    return;
                }
                if(energies<0.2*satiety)
                {
                    setState(State.REST);
                    return;
                }
                if(satiety>=85)
                {
                    setState(State.NEUTRAL);
                    return;
                }









        }
    }


}
