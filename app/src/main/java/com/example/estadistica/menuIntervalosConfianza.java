package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.estadistica.databinding.ActivityMenuTeoremaCentralLimiteBinding;

public class menuIntervalosConfianza extends AppCompatActivity {

    private ActivityMenuTeoremaCentralLimiteBinding menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menu = ActivityMenuTeoremaCentralLimiteBinding.inflate(getLayoutInflater());
        View vista = menu.getRoot();
        setContentView(vista);
        menu.TCL1.setText("Calcular Intervalo de confianza para la media de poblaciones normales cuando se conoce la varianza poblacional");
        menu.TCL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(getApplicationContext(),INTERVALOCONFIANZA1IU.class);
                startActivity(intento);
            }
        });

        menu.TCL11.setText("Calcular Intervalo de confianza para la media de poblaciones normales cuando se desconoce la varianza poblacional");
        menu.TCL11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(getApplicationContext(),INTERVALOSCONFIANZAIU2.class);
                startActivity(intento);
            }
        });

        menu.button7.setText("Calcular Intervalo de confianza para la diferencia de medias de poblaciones normales cuando se conocen las varianzas poblacionales");
        menu.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(getApplicationContext(),INTERFAZICDIFERENCIAMEDIASVARIANZASCONOCIDAS.class);
                startActivity(intento);
            }
        });

        menu.TCL2.setText("Calcular intervalo de confianza para la diferencia de medias de poblaciones normales cuando se desconocen las varianzas poblacionales, pero se sabe que son iguales");
        menu.TCL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),INTERFAZICDIFMEDIAS2.class);
                startActivity(intent);
            }
        });

        menu.TCL3.setText("Calcular intervalo de confianza para la diferencia de medias de poblaciones normales cuando se desconocen las varianzas poblacionales, pero se sabe que son diferentes");
        menu.TCL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),INTERFAZICDIFMEDIAS3.class);
                startActivity(intent);
            }
        });

        menu.button16.setText("Calcular intervalo de confianza para la varianza o desviacion estandar");
        menu.button16.setVisibility(View.VISIBLE);
        menu.button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt = new Intent(getApplicationContext(),INTERFAZICVARIANZA.class);
                startActivity(intnt);
            }
        });

       menu.button17.setText("Calcular intervalo de confianza para la razon entre varianzas o razon entre desviaciones estandar. También servible cuando se requiere calcular el intervalo de confianza para la diferencia de medias si se desconocen las varianzas poblacionales y si se desconoce si son iguales o diferentes.");
       menu.button17.setVisibility(View.VISIBLE);
       menu.button17.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intento = new Intent(getApplicationContext(),INTERFAZICRAZONENTREVARIANZAS.class);
               startActivity(intento);
           }
       });

       menu.button11.setText("Calcular intervalo de confianza para proporciones");
       menu.button11.setVisibility(View.VISIBLE);
       menu.button11.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intento = new Intent(getApplicationContext(),INTERFAZICPROPORCIONES.class);
               startActivity(intento);
           }
       });

       menu.button12.setText("Calcular intervalo de confianza para diferencia de proporciones");
       menu.button12.setVisibility(View.VISIBLE);
       menu.button12.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),INTERFAZICDIFERENCIAPROPORCIONES.class);
               startActivity(intent);
           }
       });
    }
}