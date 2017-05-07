package com.izyasosha.amoeba;

import android.app.Activity;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.izyasosha.logics.Amoeba;
import com.izyasosha.logics.Model;
import com.izyasosha.logics.State;

import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AmoebaActivity extends Activity  {

    TextView stateLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amoeba);

        ProgressBar progress =(ProgressBar) findViewById(R.id.progressSatiety);
        progress.getProgressDrawable().setColorFilter(ContextCompat.getColor(this, R.color.yellow), PorterDuff.Mode.SRC_IN);
        stateLabel = (TextView) findViewById(R.id.StateLabel);
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

}
