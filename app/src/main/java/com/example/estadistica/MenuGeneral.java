package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuGeneral extends AppCompatActivity {

     CardView tcl, intC,PH,RL,PM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_general);

        tcl = (CardView) findViewById(R.id.tcl);
        intC = (CardView) findViewById(R.id.ic);
        PH = (CardView) findViewById(R.id.ph);
        RL = (CardView) findViewById(R.id.rl);


        tcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MenuTCL.class);
                startActivity(intent);
            }
        });

        intC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MenuIntervalosConfianzaFinal.class);
                startActivity(intent);
            }
        });

        PH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        });



    }

}