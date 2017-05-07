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

import com.izyasosha.amoeba.AmoebaActivity;
import com.izyasosha.amoeba.GameView;
import com.izyasosha.amoeba.R;

public final class Amoeba extends Creature
{
        //конструктор
        public Amoeba(double x, double y, Bitmap bmp)
        {
            this.bmp = bmp;                    //возвращаем рисунок
            this.x = x;
            this.y = y;
        }


    //не даёт выйти координатам за край поля, вместо этого телепортирует на противоположный край
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;
    public void update(){};


}
