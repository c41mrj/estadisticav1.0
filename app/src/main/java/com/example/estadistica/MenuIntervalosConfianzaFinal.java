package com.example.estadistica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.UiAutomation;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
                Intent intent = new Intent(MenuIntervalosConfianzaFinal.this,VistaIntervalosConfianza.class);
                startActivity(intent);
            }
        });

        lienzo.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuIntervalosConfianzaFinal.this,VistaIntervalosConfianzaMediaDesEstandarDesconocida.class);
                startActivity(intent);
            }
        });

        lienzo.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuIntervalosConfianzaFinal.this,VistaIntervalosConfianzaVarianza.class);
                startActivity(intent);
            }
        });

        lienzo.tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuIntervalosConfianzaFinal.this,VistaIntervalosConfianzaDifMediasDesviacionesConocidas.class);
                startActivity(intent);
            }
        });

        lienzo.tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuIntervalosConfianzaFinal.this,VistaIntervalosConfianzaDifMediasDesviacionesDesconocidasPeroIguales.class);
                startActivity(intent);
            }
        });

        lienzo.tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuIntervalosConfianzaFinal.this,VistaIntervalosConfianzaDiferenciaMediasVarDesconocidasPeroDiferentes.class);
                startActivity(intent);
            }
        });

        lienzo.tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuIntervalosConfianzaFinal.this,VistaIntervalosConfianzaRazonEntreVarianzas.class);
                startActivity(intent);
            }
        });

        lienzo.tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuIntervalosConfianzaFinal.this,VistaIntervalosConfianzaProporciones.class);
                startActivity(intent);
            }
        });


        lienzo.tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuIntervalosConfianzaFinal.this,VistaIntervalosConfianzaDiferenciaProporciones.class);
                startActivity(intent);
            }
        });

    }
    public void botonQueHago(){
        Toast anuncio = Toast.makeText(getApplicationContext(),"Elige la calculadora para la razón entre varianzas y sigue las instrucciones",Toast.LENGTH_LONG);
        anuncio.show();
    }
}
