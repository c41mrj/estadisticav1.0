package com.example.estadistica;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public interface activityManager {

    public default void desplegarNotaCalcularLimiteCoeficienteConfianza(Context context){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View vista = layoutInflater.inflate(R.layout.como_obtener_limites,null);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setCancelable(false)
                .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog botonNota = alertDialog.create();
        botonNota.setTitle("¿Cómo obtener un límite si ya tengo el otro?");
        botonNota.setView(vista);
        botonNota.show();
    }





}
