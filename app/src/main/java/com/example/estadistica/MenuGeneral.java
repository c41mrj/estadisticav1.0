package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuGeneral extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_general);
    }


    public void botonDesplegarMenuTCL(View vista){
        Intent intento = new Intent(this,menu_teorema_central_limite.class);
        startActivity(intento);
    }

    public void botonDesplegarMenuIntervalosConfianza(View view){
        Intent intento = new Intent(this,MenuIntervalosConfianzaFinal.class);
        startActivity(intento);
    }

    public void botonDesplegarMenuPruebasHipotesis(View view){
        Intent intento = new Intent(this,MenuActivity.class);
        startActivity(intento);
    }

    public void botonDesplegarMenuRegresionLinealSimpleyMultiple(){
        Intent intento = new Intent(this,MenuRegresionLineal.class);
        startActivity(intento);
    }

}