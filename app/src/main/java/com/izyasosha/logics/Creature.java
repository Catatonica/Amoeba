package com.izyasosha.logics;

import java.util.Random;

/**
 * Created by Алексей on 02.05.2017.
 */

public abstract class Creature extends GameObject
{
    protected byte velocity=5;

    protected void moveRandomly()
    {
        double direction = (int)(new Random()).nextInt(360) / (2*Math.PI);
        //Генерирование случайного направления в градусах и перевод его в радианы

        setX(x + velocity*Math.cos(direction));
        setY(y + velocity*Math.sin(direction));
    }
}

