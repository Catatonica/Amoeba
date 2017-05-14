package com.izyasosha.amoeba;

import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.SystemClock;

import com.izyasosha.logics.Model;
import com.izyasosha.logics.State;

import java.util.concurrent.TimeUnit;

/**
 * Created by Alexander on 14.05.2017.
 */

class AmoebaAsyncTask extends AsyncTask<Canvas, Void, Void> {

    private final static int MAX_FPS = 2;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;
    @Override
    protected Void doInBackground(Canvas... params) {

        long beginTime, timeDiff, sleepTime;
        sleepTime = 0;
        while(Model.getAmoeba().getState() != State.DEATH){
            beginTime = System.currentTimeMillis();
            timeDiff = System.currentTimeMillis() - beginTime;



            sleepTime = (int)(FRAME_PERIOD - timeDiff);

            if (sleepTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {}
            }
        }
        return null;
    }
}
