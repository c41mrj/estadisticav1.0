package com.example.estadistica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MenuTCL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_t_c_l);
    }

    public void botonTLC1(View vista){
        Intent intent = new Intent(this, CTM.class);
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

    public void botonAyuda1(View vista){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuTCL.this);
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
        LayoutInflater imagenAlert = LayoutInflater.from(MenuTCL.this);
        final View view = imagenAlert.inflate(R.layout.ejemplotclmedia,null);
        botonAyuda1.setView(view);
        botonAyuda1.show();
    }

    public void botonAyuda2(View vista){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuTCL.this);
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
        LayoutInflater imagenAlert = LayoutInflater.from(MenuTCL.this);
        final View view = imagenAlert.inflate(R.layout.ejemplotclsumatorias,null);
        botonAyuda1.setView(view);
        botonAyuda1.show();
    }

    public void botonAyuda3(View vista){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuTCL.this);
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
        LayoutInflater imagenAlert = LayoutInflater.from(MenuTCL.this);
        final View view = imagenAlert.inflate(R.layout.ejemplotcldiferenciamedias,null);
        botonAyuda1.setView(view);
        botonAyuda1.show();
    }
     public void botonAyuda4(View vista){
         AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuTCL.this);
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
         LayoutInflater imagenAlert = LayoutInflater.from(MenuTCL.this);
         final View view = imagenAlert.inflate(R.layout.ejemplotclproporciones,null);
         botonAyuda1.setView(view);
         botonAyuda1.show();
     }

     public void botonAyuda5(View vista){
         AlertDialog.Builder alertDialog = new AlertDialog.Builder(MenuTCL.this);
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
         LayoutInflater imagenAlert = LayoutInflater.from(MenuTCL.this);
         final View view = imagenAlert.inflate(R.layout.ejemplotcldiferenciaproporciones,null);
         botonAyuda1.setView(view);
         botonAyuda1.show();
     }


}