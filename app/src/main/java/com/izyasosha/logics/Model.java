package com.izyasosha.logics;

import java.util.ArrayList;
import java.util.Iterator;

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

    public static void killOutOfScreen()
    {
        Iterator<Enemy> iterEnemy = enemyArrayList.iterator();
        while (iterEnemy.hasNext()) {
            Enemy enemy = iterEnemy.next();

            if(enemy.getX()>=Model.getGameWidth()||enemy.getY()>=Model.getGameHeight()||
                    enemy.getX()<=0||enemy.getY()<=0) {
                iterEnemy.remove();
            }
        }

        Iterator<Child> iterChild = childArrayList.iterator();
        while (iterChild.hasNext()) {
            Child child = iterChild.next();

            if(child.getX()>=Model.getGameWidth()||child.getY()>=Model.getGameHeight()||
                    child.getX()<=0||child.getY()<=0) {
                iterChild.remove();
            }
        }
    }

    public static void checkIntersections()
    {
        Iterator<Food> iterFood = foodArrayList.iterator();
        while(iterFood.hasNext())
        {
            Food food = iterFood.next();
            double d = Math.sqrt(Math.pow(food.x - amoeba.x, 2) + Math.pow(food.y - amoeba.y, 2));
            if(d<amoeba.size)
            {
                remove(food);
                amoeba.increaseSatiety();
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
        for(Child child:childArrayList){
            child.moveRandomly();
        }
        switch (amoeba.getState())
        {
            case NEUTRAL:
                amoeba.moveRandomly(); break;
            case HUNGRINESS:
                amoeba.findFood(); break;
            case WARNING:
                amoeba.runAway(amoeba.findNearestEnemy()); break;
            default:
                return;
        }
    }

    public static ArrayList<Food> foodArrayList=new ArrayList<>();
    public static ArrayList<Enemy> enemyArrayList=new ArrayList<>();
    public static ArrayList<Child> childArrayList=new ArrayList<>();

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
