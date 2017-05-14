package com.izyasosha.logics;

import java.util.ArrayList;

/**
 * Created by Alexander on 04.05.2017.
 */

public class Model {

    public enum CreationMode{
        FOOD,
        ENEMY,
        NONE
    }

    //private - чтобы нельзя было создать объект этого класса - объекты нам ни к чему
    private Model(){    }

    private static Amoeba amoeba;
    public static Amoeba getAmoeba() {return amoeba;}
    public static void setAmoeba(Amoeba amoeba) {Model.amoeba = amoeba;}

    public static void remove(Food food)
    {
        foodArrayList.remove(foodArrayList.indexOf(food));
    }
    public static void remove(Enemy enemy)
    {
        enemyArrayList.remove(enemyArrayList.indexOf(enemy));
    }

    public static void killEnemies()
    {
        for(Enemy enemy:enemyArrayList)
        {
            if(enemy.getX()>=Model.getGameWidth()||enemy.getY()>=Model.getGameHeight()||
                    enemy.getX()<=0||enemy.getY()<=0)
            {
                remove(enemy);
            }
        }
    }

    public static void checkIntersections()
    {
        for(Food food:foodArrayList)
        {
            if(Model.getAmoeba().getX()==food.getX()&&Model.getAmoeba().getY()==food.getY())
            {
                remove(food);
                return;
            }
        }

    }

    public static void moveObjects()
    {
        for(Enemy enemy:enemyArrayList)
        {
            enemy.moveRandomly();
        }
        amoeba.setNextState();
    }

    public static ArrayList<Food> foodArrayList=new ArrayList<>();
    public static ArrayList<Enemy> enemyArrayList=new ArrayList<>();

    private static CreationMode mode = CreationMode.NONE;
    public static CreationMode getMode() {
        return mode;
    }
    public static void setMode(CreationMode mode) {
        Model.mode = mode;
    }

    private static int gameHeight;
    public static int getGameHeight() {
        return gameHeight;
    }
    public static void setGameHeight(int gameHeight) {
        Model.gameHeight = gameHeight;
    }


    private static int gameWidth;
    public static int getGameWidth() {
        return gameWidth;
    }
    public static void setGameWidth(int gameWidth) {
        Model.gameWidth = gameWidth;
    }

}
