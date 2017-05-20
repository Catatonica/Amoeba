package com.izyasosha.logics;

/**
 * Created by Алексей on 01.05.2017.
 */
import android.app.Application;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.izyasosha.amoeba.AmoebaActivity;

import java.lang.Math;

public final class Amoeba extends Creature
{
    private double satiety;

    public double getSatiety() {
        return satiety;
    }

    public double getEnergies() {
        return energies;
    }

    private double energies;
    private int neutralTime;
    private int divisionTime;

        //конструктор
        public Amoeba(Bitmap bmp, double x, double y)
        {
            this.bmp = bmp;
            satiety=80;
            energies=80;
            size=25;
            neutralTime=0;
            divisionTime=0;
            setState(State.NEUTRAL);
            setX(x);
            setY(y);
        }

    public void increaseSatiety()
    {
        satiety+=5;
    }
    public void increaseEnergies()
    {
        energies+=1;
    }
    public void decreaseEnergies()
    {
        energies-=1;
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

    public Enemy findNearestEnemy()
    {
        Enemy nearestEnemy = null;
        double minDist = Double.MAX_VALUE;
        for(Enemy e: Model.enemyArrayList)
        {
            e.distanceTo = Math.sqrt(Math.pow(e.x - x, 2) + Math.pow(e.y - y, 2));
            if(e.distanceTo < minDist) {
                minDist = e.distanceTo;
                nearestEnemy = e;
            }
        }
        return nearestEnemy;
    }

    public void runAway(Enemy nearestEnemy) {

        double enemyX = nearestEnemy.x;
        double enemyY = nearestEnemy.y;

        //Двигается от него
        setX(x - velocity / Math.sqrt(Math.pow(enemyX - x, 2) + Math.pow(enemyY - y, 2)) * (enemyX - x));
        setY(y - velocity / Math.sqrt(Math.pow(enemyX - x, 2) + Math.pow(enemyY - y, 2)) * (enemyY - y));
    }


    private State state;
    public State getState() {
        return state;
    }



    public void setState(State state) {
        AmoebaActivity.setStateLabel(state);
        this.state = state;
        switch (state)
        {
            case NEUTRAL:
                velocity=10;
                neutralTime++;
                break;
            case HUNGRINESS:
                velocity=20;
                break;
            case WARNING:
                velocity=30;
                break;
            case REST:
                velocity=0;
                increaseEnergies();
                break;
            case DEATH:
                velocity=0;
                break;
            case DIVISION:
                velocity=0;
                divisionTime++;
                decreaseEnergies();
                break;
        }
    }

    public void setNextState(){
        satiety-=0.1;
        energies-=0.01*velocity;
        Enemy nearestEnemy=findNearestEnemy();


        switch (state) {
            case NEUTRAL:
                if (nearestEnemy!=null && nearestEnemy.distanceTo < 100) {
                    setState(State.WARNING);
                    return;
                }
                if (energies < 0.5*satiety) {
                    setState(State.REST);
                    return;
                }
                if (satiety < 70) {
                setState(State.HUNGRINESS);
                return;
                 }
                if (neutralTime == 20) {
                    divisionTime = 0;
                    setState(State.DIVISION);
                    return;
                }
                setState(State.NEUTRAL);
                break;
            case WARNING:
                if (nearestEnemy!=null && nearestEnemy.distanceTo < size) {
                    setState(State.DEATH);
                    return;
                }
                if (satiety < 70) {
                    setState(State.HUNGRINESS);
                    return;
                }
                if (nearestEnemy==null || nearestEnemy.distanceTo >= 150) {
                    neutralTime = 0;
                    setState(State.NEUTRAL);
                    return;
                }
                setState(State.WARNING);
                break;
            case HUNGRINESS:
                if (satiety <= 0) {
                    setState(State.DEATH);
                    return;
                }
                if (nearestEnemy!=null && nearestEnemy.distanceTo < 100) {
                    setState(State.WARNING);
                    return;
                }
                if (energies < 0.3 * satiety) {
                    setState(State.REST);
                    return;
                }
                if (satiety >= 85) {
                    neutralTime = 0;
                    setState(State.NEUTRAL);
                    return;
                }
                setState(State.HUNGRINESS);
                break;
            case REST:
                if (nearestEnemy!=null && nearestEnemy.distanceTo < 100) {
                    setState(State.WARNING);
                    return;
                }
                if (energies > 0.7 * satiety) {
                    setState(State.HUNGRINESS);
                    return;
                }
                if (energies >= 85) {
                    neutralTime = 0;
                    setState(State.NEUTRAL);
                    return;
                }
                setState(State.REST);
                break;
            case DIVISION:
                if (nearestEnemy!=null && nearestEnemy.distanceTo < size) {
                    setState(State.DEATH);
                    return;
                }
                if (divisionTime == 10 && nearestEnemy!=null && nearestEnemy.distanceTo < 100) {
                    setState(State.WARNING);
                    return;
                }
                if (divisionTime == 10 && satiety < 70) {
                    setState(State.HUNGRINESS);
                    return;
                }
                if (divisionTime == 10 && energies < 70) {
                    setState(State.REST);
                    return;
                }
                setState(State.DIVISION);
                break;
        }
        }
    }



