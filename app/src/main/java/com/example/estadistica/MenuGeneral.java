package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuGeneral extends AppCompatActivity {

     private CardView tcl, intC,PH,RL,PM;
     private FirebaseUser userAut;
     private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_general);

        tcl = (CardView) findViewById(R.id.tcl);
        intC = (CardView) findViewById(R.id.ic);
        PH = (CardView) findViewById(R.id.ph);
        RL = (CardView) findViewById(R.id.rl);

        firebaseAuth = FirebaseAuth.getInstance();
        userAut = FirebaseAuth.getInstance().getCurrentUser();


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
                Intent intent = new Intent(getApplicationContext(),MenuPruebasHip.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        firebaseAuth.signOut();
        Intent intent = new Intent(this,LogIn.class);
        startActivity(intent);
    }


}