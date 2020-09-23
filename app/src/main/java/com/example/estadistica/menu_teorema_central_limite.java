package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu_teorema_central_limite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_teorema_central_limite);
    }

    public void botonTLC1(View vista){
        Intent intent = new Intent(this,TCLMEDIAIU.class);
        startActivity(intent);
    }

    public void botonTLC2(View vista){
        Intent intento = new Intent(this,TLCPROPORCIONESIU.class);
        startActivity(intento);
    }

    public void botonTLC3(View vista){
        Intent intent = new Intent(this,TCLIMITEDIFERENCIAMEDIASIU.class);
        startActivity(intent);
    }

    public void botonTLC4(View vista){
        Intent intent = new Intent(this,TCLIMITESUMAIU.class);
        startActivity(intent);
    }

    public void botonTLC5(View vista){
        Intent intento = new Intent(this,TCLIMITEDIFERENCIAPROPORCIONES.class);
        startActivity(intento);
    }



}