package com.izyasosha.amoeba;

import android.app.Activity;
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

import org.w3c.dom.Text;

public class AmoebaActivity extends Activity  {

    TextView stateLabel;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amoeba);
        stateLabel = (TextView) findViewById(R.id.StateLabel);
        gameView=(GameView) findViewById(R.id.view);
        runTimer();
    }

    public void onClickSetFood(View view)
    {
        Model.setMode(Model.CreationMode.FOOD);
    }

    public void onClickSetDanger(View view)
    {
        Model.setMode(Model.CreationMode.ENEMY);
    }

    public void setStateLabel(State state){
        stateLabel.setText(state.toString());
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                gameView.renderFrame();
                handler.postDelayed(this, 1000);
            }
        });
    }

}
