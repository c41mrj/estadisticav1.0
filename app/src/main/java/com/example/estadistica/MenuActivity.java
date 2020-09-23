package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void botonDesplegarPruebasDeHipotesisVarConocida(View view){
        Intent intent = new Intent(this,INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.class);  //ir a pruebas de hipotesis desviacion estandar poblacional conocida
        startActivity(intent);
    }

    public void botonDesplegarPruebasDeHipotesisDesEDesconocida(View view){
        Intent intent = new Intent(this,PruebaHipDEDesconocida.class);
        startActivity(intent);
    }


    public void botonDesplegarPruebasHipotesisVarianza(View vista){
        Intent intent = new Intent(this,PruebaHipotesisVarianza.class);
        startActivity(intent);
    }

    public void botonDesplegarPruebasHipotesisDiferenciaMediasVarConocidas(View vista){
        Intent intento = new Intent(this,INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.class);
        startActivity(intento);
    }

}
