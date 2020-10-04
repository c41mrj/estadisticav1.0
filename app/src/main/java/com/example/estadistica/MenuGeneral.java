package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuGeneral extends AppCompatActivity  implements View.OnClickListener {

     CardView tcl, intC,PH,RL,PM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_general);

        tcl = (CardView) findViewById(R.id.tcl);
        intC = (CardView) findViewById(R.id.ic);
        PH = (CardView) findViewById(R.id.ph);
        RL = (CardView) findViewById(R.id.rl);


        tcl.setOnClickListener(this);
        intC.setOnClickListener(this);
        PH.setOnClickListener(this);
        RL.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()){
            case R.id.tcl:
                i = new Intent(this,menuIntervalosConfianza.class);
                startActivity(i);
                break;
            case R.id.ic:
                i = new Intent(this,menuIntervalosConfianza.class);
                startActivity(i);
                break;
            case R.id.ph:
                i = new Intent(this,menuIntervalosConfianza.class);
                startActivity(i);
                break;
            case R.id.rl:
                i = new Intent(this,menuIntervalosConfianza.class);
                startActivity(i);
                break;


        }

    }

}