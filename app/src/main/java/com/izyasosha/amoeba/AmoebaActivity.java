package com.izyasosha.amoeba;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;

import com.izyasosha.logics.Amoeba;
import com.izyasosha.logics.Model;
import com.izyasosha.logics.State;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AmoebaActivity extends Activity  {

    static TextView stateLabel;
    static ProgressBar energiesBar, satietyBar;
    GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap amoebaBMP= BitmapFactory.decodeResource(getResources(), R.drawable.amoeba);
        setContentView(R.layout.activity_amoeba);
        stateLabel = (TextView) findViewById(R.id.State);
        energiesBar=(ProgressBar) findViewById(R.id.progressEnergies);
        satietyBar=(ProgressBar) findViewById(R.id.progressSatiety);
        gameView=(GameView) findViewById(R.id.view);
        Model.setAmoeba(new Amoeba(amoebaBMP,100,100));
        runTimer();
    }

    public static void setProgress(double energies, double satiety)
    {
        energiesBar.setProgress((int)energies);
        satietyBar.setProgress((int)satiety);
    }

    public void onClickSetFood(View view)
    {
        Model.setMode(Model.CreationMode.FOOD);
    }

    public void onClickSetDanger(View view)
    {
        Model.setMode(Model.CreationMode.ENEMY);
    }

    public static void setStateLabel(State state){
        stateLabel.setText(state.toString());
    }

    private void runTimer() {
        final Handler handler = new Handler();
        boolean post = handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    gameView.invalidate();
                    if (Model.getAmoeba().getState() == State.DEATH) {
                        showMessage();
                        return;
                    }
                    setProgress(Model.getAmoeba().getEnergies(), Model.getAmoeba().getSatiety());
                    Model.getAmoeba().setNextState();
                    Model.moveObjects();
                    Model.checkIntersections();
                    Model.killEnemies();
                }
                catch (Exception e){
                    Toast.makeText(AmoebaActivity.this, e.getMessage()+e.getStackTrace(), Toast.LENGTH_LONG);
                }
                handler.postDelayed(this, 500);
            }
        });
    }


    private void showMessage()
    {
        CharSequence text = "The end";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

}
