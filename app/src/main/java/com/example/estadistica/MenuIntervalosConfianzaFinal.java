package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiAutomation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityMenuIntervalosConfianzaFinal2Binding;

public class MenuIntervalosConfianzaFinal extends AppCompatActivity {

    private ActivityMenuIntervalosConfianzaFinal2Binding lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityMenuIntervalosConfianzaFinal2Binding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);

        lienzo.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos1();
            }
        });

        lienzo.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos2();
            }
        });

        lienzo.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos6();
            }
        });

        lienzo.tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos3();
            }
        });

        lienzo.tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos5();
            }
        });


        lienzo.tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos4();
            }
        });

        lienzo.tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos7();
            }
        });

        lienzo.tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos8();
            }
        });

        lienzo.tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos9();
            }
        });

        lienzo.button28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonQueHago();
            }
        });

    }

    public void botonDesplegarIntervalos1(){
        Intent intento = new Intent(getApplicationContext(),INTERVALOCONFIANZA1IU.class);
        startActivity(intento);
    }

    public void botonDesplegarIntervalos2(){
        Intent intento = new Intent(getApplicationContext(),INTERVALOSCONFIANZAIU2.class);
        startActivity(intento);
    }

    public void botonDesplegarIntervalos3(){
        Intent intento = new Intent(getApplicationContext(),INTERFAZICDIFERENCIAMEDIASVARIANZASCONOCIDAS.class);
        startActivity(intento);
    }


    public void botonDesplegarIntervalos4(){
        Intent intent = new Intent(getApplicationContext(),INTERFAZICDIFMEDIAS3.class);
        startActivity(intent);
    }

    public void botonDesplegarIntervalos5(){
        Intent intent = new Intent(getApplicationContext(),INTERFAZICDIFMEDIAS2.class);
        startActivity(intent);
    }

    public void botonDesplegarIntervalos6(){
        Intent intnt = new Intent(getApplicationContext(),INTERFAZICVARIANZA.class);
        startActivity(intnt);
    }

    public void botonDesplegarIntervalos7(){
        Intent intento = new Intent(getApplicationContext(),INTERFAZICRAZONENTREVARIANZAS.class);
        startActivity(intento);
    }

    public void botonDesplegarIntervalos8(){
        Intent intento = new Intent(getApplicationContext(),INTERFAZICPROPORCIONES.class);
        startActivity(intento);
    }

    public void botonDesplegarIntervalos9(){
        Intent intent = new Intent(getApplicationContext(),INTERFAZICDIFERENCIAPROPORCIONES.class);
        startActivity(intent);
    }

    public void botonQueHago(){
        Toast anuncio = Toast.makeText(getApplicationContext(),"Elige la calculadora para la razón entre varianzas y sigue las instrucciones",Toast.LENGTH_LONG);
        anuncio.show();
    }

}