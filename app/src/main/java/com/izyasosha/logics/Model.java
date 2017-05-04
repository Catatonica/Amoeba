package com.izyasosha.logics;

import java.util.ArrayList;

/**
 * Created by Alexander on 04.05.2017.
 */

public class Model {

    private enum CreationMode{
        FOOD,
        ENEMY
    }

    //private - чтобы нельзя было создать объект этого класса - объекты нам ни к чему
    private Model(){    }

    public static Amoeba getAmoeba() {
        return amoeba;
    }

    public static void setAmoeba(Amoeba amoeba) {
        Model.amoeba = amoeba;
    }

    private static Amoeba amoeba;

    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Food> foods = new ArrayList<>();

    public static CreationMode getMode() {
        return mode;
    }

    public static void setMode(CreationMode mode) {
        Model.mode = mode;
    }

    private static CreationMode mode = CreationMode.FOOD;
    //при запуске игры - режим создания хавчика

    public static int getGameHeight() {
        return gameHeight;
    }

    public static void setGameHeight(int gameHeight) {
        Model.gameHeight = gameHeight;
    }

    private static int gameHeight;

    public static int getGameWidth() {
        return gameWidth;
    }

    public static void setGameWidth(int gameWidth) {
        Model.gameWidth = gameWidth;
    }

    private static int gameWidth;

}
