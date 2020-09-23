package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuIntervalosConfianzaFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_intervalos_confianza_final);
    }

    public void botonDesplegarIntervalos1(View vista){
        Intent intento = new Intent(getApplicationContext(),INTERVALOCONFIANZA1IU.class);
        startActivity(intento);
    }

    public void botonDesplegarIntervalos2(View v){
        Intent intento = new Intent(getApplicationContext(),INTERVALOSCONFIANZAIU2.class);
        startActivity(intento);
    }

    public void botonDesplegarIntervalos3(View v){
        Intent intento = new Intent(getApplicationContext(),INTERFAZICDIFERENCIAMEDIASVARIANZASCONOCIDAS.class);
        startActivity(intento);
    }


    public void botonDesplegarIntervalos4(View v){
        Intent intent = new Intent(getApplicationContext(),INTERFAZICDIFMEDIAS3.class);
        startActivity(intent);
    }

    public void botonDesplegarIntervalos5(View v){
        Intent intent = new Intent(getApplicationContext(),INTERFAZICDIFMEDIAS2.class);
        startActivity(intent);
    }

    public void botonDesplegarIntervalos6(View v){
        Intent intnt = new Intent(getApplicationContext(),INTERFAZICVARIANZA.class);
        startActivity(intnt);
    }

    public void botonDesplegarIntervalos7(View v){
        Intent intento = new Intent(getApplicationContext(),INTERFAZICRAZONENTREVARIANZAS.class);
        startActivity(intento);
    }

    public void botonDesplegarIntervalos8(View v){
        Intent intento = new Intent(getApplicationContext(),INTERFAZICPROPORCIONES.class);
        startActivity(intento);
    }

    public void botonDesplegarIntervalos9(View v){
        Intent intent = new Intent(getApplicationContext(),INTERFAZICDIFERENCIAPROPORCIONES.class);
        startActivity(intent);
    }

    public void botonQueHago(View view){
        Toast anuncio = Toast.makeText(getApplicationContext(),"Elige la calculadora para la razón entre varianzas y sigue las instrucciones",Toast.LENGTH_LONG);
        anuncio.show();
    }

}