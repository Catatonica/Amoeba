package com.izyasosha.logics;

/**
 * Created by Алексей on 01.05.2017.
 */
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.izyasosha.amoeba.AmoebaActivity;
import com.izyasosha.amoeba.R;

import java.lang.Math;

import static com.izyasosha.logics.Model.childArrayList;

public final class Amoeba extends Creature
{
    private double satiety;

    public double getSatiety() {
        return satiety;
    }
    public void setSatiety(double newValue){
        if(newValue<=0){
            satiety = 0;
            return;
        }
        if(newValue > 100){
            satiety = 100;
            return;
        }
        satiety = newValue;
    }

    public double getEnergies() {
        return energies;
    }
    public void setEnergies(double newValue){
        if(newValue<=0){
            energies = 0;
            return;
        }
        if(newValue > 100){
            energies = 100;
            return;
        }
        energies = newValue;
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
            size=35;
            neutralTime=0;
            divisionTime=0;
            setState(State.NEUTRAL);
            setX(x);
            setY(y);
        }

    public void increaseSatiety()
    {
        setSatiety(satiety+5);
    }
    public void increaseEnergies()
    {
        setEnergies(energies+1);
    }
    public void decreaseEnergies()
    {
        setEnergies(energies-3);
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

    private void divide(){
        childArrayList.add(new Child(x, y));
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

    private final int WARNING_DISTANCE = 100;
    private final int SAFE_DISTANCE = 150;

    private final double SATIETY_DECR = 0.35;
    private final double ENERGIES_DECR_COEFF = 0.01;

    private final int HUNGRY = 70;
    private final int SATE = 85;

    private final int TIME_TO_START_DIVISION = 20;
    private final int DIVISION_DURATION = 10;

    private final int TIRED = 50;
    private final int RESTED = 70;

    private double HUNGRINESS_TO_REST_COEFF = 0.3;
    private double REST_TO_HUNGRINESS_COEFF = 0.7;


    public void setNextState(){
        satiety-=SATIETY_DECR;
        energies-=ENERGIES_DECR_COEFF*velocity;
        Enemy nearestEnemy=findNearestEnemy();


        switch (state) {
            case NEUTRAL:
                if (nearestEnemy!=null && nearestEnemy.distanceTo < WARNING_DISTANCE) {
                    setState(State.WARNING);
                    return;
                }
                if (energies < TIRED) {
                    setState(State.REST);
                    return;
                }
                if (satiety < HUNGRY) {
                setState(State.HUNGRINESS);
                return;
                 }
                if (neutralTime == TIME_TO_START_DIVISION) {
                    divisionTime = 0;
                    setState(State.DIVISION);
                    return;
                }
                if(satiety>=95){
                    setState(State.REST);
                    return;
                }
                setState(State.NEUTRAL);
                return;
            case WARNING:
                if(energies <= 0){
                    setState(State.REST);
                    return;
                }
                if (nearestEnemy!=null && nearestEnemy.distanceTo < size) {
                    setState(State.DEATH);
                    return;
                }
                if (nearestEnemy!=null && nearestEnemy.distanceTo < SAFE_DISTANCE) {
                    setState(State.WARNING);
                    return;
                }
                if (satiety < HUNGRY) {
                    setState(State.HUNGRINESS);
                    return;
                }
                if(energies < TIRED){
                    setState(State.REST);
                    return;
                }
                neutralTime = 0;
                setState(State.NEUTRAL);
                return;
            case HUNGRINESS:
                if (satiety <= 0) {
                    setState(State.DEATH);
                    return;
                }
                if (nearestEnemy!=null && nearestEnemy.distanceTo < WARNING_DISTANCE) {
                    setState(State.WARNING);
                    return;
                }
                if (energies < HUNGRINESS_TO_REST_COEFF * satiety) {
                    setState(State.REST);
                    return;
                }
                if (satiety >= SATE) {
                    neutralTime = 0;
                    setState(State.NEUTRAL);
                    return;
                }
                setState(State.HUNGRINESS);
                return;
            case REST:
                if (nearestEnemy!=null && nearestEnemy.distanceTo < WARNING_DISTANCE) {
                    setState(State.WARNING);
                    return;
                }
                if (satiety<HUNGRY && energies > satiety*REST_TO_HUNGRINESS_COEFF) {
                    setState(State.HUNGRINESS);
                    return;
                }
                if (energies >= RESTED && satiety<=95) {
                    neutralTime = 0;
                    setState(State.NEUTRAL);
                    return;
                }
                setState(State.REST);
                return;
            case DIVISION:
                if (nearestEnemy!=null && nearestEnemy.distanceTo < size) {
                    setState(State.DEATH);
                    return;
                }
                if (divisionTime == DIVISION_DURATION && nearestEnemy!=null && nearestEnemy.distanceTo < WARNING_DISTANCE) {
                    divide();
                    setState(State.WARNING);
                    return;
                }
                if (divisionTime == DIVISION_DURATION && satiety < HUNGRY) {
                    divide();
                    setState(State.HUNGRINESS);
                    return;
                }
                if (divisionTime == DIVISION_DURATION) {
                    divide();
                    setState(State.REST);
                    return;
                }
                setState(State.DIVISION);
                return;
            }
        }
    }



