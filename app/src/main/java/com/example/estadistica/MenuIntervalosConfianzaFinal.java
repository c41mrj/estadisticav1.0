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
                botonDesplegarIntervalos1();
            }
        });

        lienzo.imageButton33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuIntervalosConfianzaFinal.this);
                alertDialog.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog botonAyuda1 = alertDialog.create();
                botonAyuda1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagenAlert = LayoutInflater.from(MenuIntervalosConfianzaFinal.this);
                final View vista = imagenAlert.inflate(R.layout.ejemplosintervalosconfianzamedia,null);
                botonAyuda1.setView(vista);
                botonAyuda1.show();
            }
        });


        lienzo.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos2();
            }
        });

        lienzo.imageButton34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuIntervalosConfianzaFinal.this);
                alertDialog.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog botonAyuda1 = alertDialog.create();
                botonAyuda1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagenAlert = LayoutInflater.from(MenuIntervalosConfianzaFinal.this);
                final View vista = imagenAlert.inflate(R.layout.ejemplointervalosconfianzamediavarconocida,null); //aunque la vista referenciada dice que es varianza conocida es para varianza no conocida.
                botonAyuda1.setView(vista);
                botonAyuda1.show();
            }
        });

        lienzo.tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos6();
            }
        });

        lienzo.imageButton35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuIntervalosConfianzaFinal.this);
                alertDialog.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog botonAyuda1 = alertDialog.create();
                botonAyuda1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagenAlert = LayoutInflater.from(MenuIntervalosConfianzaFinal.this);
                final View vista = imagenAlert.inflate(R.layout.ejemplointervalodeconfianzaparalavarianza,null); //aunque la vista referenciada dice que es varianza conocida es para varianza no conocida.
                botonAyuda1.setView(vista);
                botonAyuda1.show();
            }
        });

        lienzo.tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos3();
            }
        });

        lienzo.imageButton36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuIntervalosConfianzaFinal.this);
                alertDialog.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog botonAyuda1 = alertDialog.create();
                botonAyuda1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagenAlert = LayoutInflater.from(MenuIntervalosConfianzaFinal.this);
                final View vista = imagenAlert.inflate(R.layout.ejemplointervalosdeconfianzaparaladiferenciademediasdesviacionesconocidas,null); //aunque la vista referenciada dice que es varianza conocida es para varianza no conocida.
                botonAyuda1.setView(vista);
                botonAyuda1.show();
            }
        });

        lienzo.tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos5();
            }
        });

        lienzo.imageButton37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuIntervalosConfianzaFinal.this);
                alertDialog.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog botonAyuda1 = alertDialog.create();
                botonAyuda1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagenAlert = LayoutInflater.from(MenuIntervalosConfianzaFinal.this);
                final View vista = imagenAlert.inflate(R.layout.ejemplointervaloconfianzadiferenciademediasdesviacionesiguales,null); //aunque la vista referenciada dice que es varianza conocida es para varianza no conocida.
                botonAyuda1.setView(vista);
                botonAyuda1.show();
            }
        });


        lienzo.tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonDesplegarIntervalos4();
            }
        });

        lienzo.imageButton38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuIntervalosConfianzaFinal.this);
                alertDialog.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog botonAyuda1 = alertDialog.create();
                botonAyuda1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagenAlert = LayoutInflater.from(MenuIntervalosConfianzaFinal.this);
                final View vista = imagenAlert.inflate(R.layout.ejemplointervalosdeconfianzadiferenciademediasdesvdiferentes,null); //aunque la vista referenciada dice que es varianza conocida es para varianza no conocida.
                botonAyuda1.setView(vista);
                botonAyuda1.show();
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