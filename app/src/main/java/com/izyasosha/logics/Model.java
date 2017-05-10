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


    public static void Die(GameObject gameObject)
    {
        if(gameObject==amoeba)
        {
         return;
        }
        if(Food.class.isInstance(gameObject)){
            foodArrayList.remove(foodArrayList.indexOf(gameObject));
            return;
        }
        if(Enemy.class.isInstance(gameObject)){
            enemyArrayList.remove(enemyArrayList.indexOf(gameObject));
            return;
        }
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
