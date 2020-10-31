package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuAuxiliar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_auxiliar);
    }

    public void botonTCL(View vista){
        Intent intent = new Intent(this,CTM.class);
        startActivity(intent);
    }

    public void botonIC(View vista){
        Intent intent = new Intent(this,VistaIntervalosConfianza.class);
        startActivity(intent);
    }
}