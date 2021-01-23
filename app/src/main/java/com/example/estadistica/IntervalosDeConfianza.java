package com.example.estadistica;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public interface IntervalosDeConfianza {


    String paso1IntervalosConfianzaMediaVarConocida = "Al observar el teorema presentado anteriormente nos podemos percatar de que es necesario buscar el valor de Z(α/2) en las tablas de la distribución normal estándar donde:";

    String paso1TamMinimoMuestra = "Para calcular el tamaño mínimo de muestra se hace necesario buscar el valor de Z(α/2) en las de la distribución normal estándar, donde en este caso el valor encontrado en tablas es \n\n";

    String paso2CoeficienteConfianzaMediaVarConocida = "EL siguiente paso es buscar el determinante calculado anteriormente en las tablas de la distribución normál estándar.\n\nÉn éste caso el valor encontrado en tablas es:";

    String paso1IntervalosConfianzaMediaVarDesconocida = "Al observar el teorema presentado anteriormente nos podemos percatar de que es necesario buscar el valor de t(α/2) en las tablas de la distribución t-student con n-1 grados de libertad donde:";



    String paso2CoeficienteConfianzaMediaVarDesconocida = "EL siguiente paso es buscar el determinante calculado anteriormente en las tablas acumuladas de la distribución t-student.\n\nÉn éste caso el valor encontrado en tablas es:";

    String[] elementosICMedias2 = {"Calcular intervalo de confianza","Calcular nivel de confianza si se conoce el límite infeior del I.C", "Calcular nivel de confianza si se conoce el límite del I.C"};


    public default String getPaso1IntervalosConfianzaMediaVarConocida(){return paso1IntervalosConfianzaMediaVarConocida;}

    public default String getPaso1TamMinimoMuestra(){return paso1TamMinimoMuestra;}

    public default String getPaso2CoeficienteConfianzaMediaVarConocida(){return paso2CoeficienteConfianzaMediaVarConocida;}

    public default String getPaso1IntervalosConfianzaMediaVarDesconocida(){return paso1IntervalosConfianzaMediaVarDesconocida;}

    public default String[] getElementosICMedias2(){return elementosICMedias2;}

    public default String getPaso2CoeficienteConfianzaVarDesconocida(){return paso2CoeficienteConfianzaMediaVarDesconocida;}

    public default String getConclusionCoeficienteConfianzaMedia(double limite,double coeficienteConfianza) {
        return "Ya que el límite conocido es: " + limite + "\nPodemos asegurar que el intervalo de confianza tendrá un nivel de confianza de: " + coeficienteConfianza;
    }



    public default void prepararEntornoIntervalosConfianzaMedia(){
        VistaIntervalosConfianza.datosIC.setVisibility(View.VISIBLE);
        VistaIntervalosConfianza.datosCoeficienteConfianza.setVisibility(View.GONE);
        VistaIntervalosConfianza.datosTamMinimoMuestra.setVisibility(View.GONE);
        VistaIntervalosConfianza.resultado.setText("El resultado es:");
        VistaIntervalosConfianza.imageTeorema.setImageResource(R.drawable.teoremaicmedia1);
        VistaIntervalosConfianza.imageLiminf.setImageResource(R.drawable.liminfteoremaicmedia1);
        VistaIntervalosConfianza.imageLimsup.setImageResource(R.drawable.limsupteoremaicmedia1);
        VistaIntervalosConfianza.imageValTablas.setImageResource(R.drawable.zetaenalfamedios);
        VistaIntervalosConfianza.alfaOpcional.setImageResource(R.drawable.alfamedios);
        desaparecerLayoutsProcedimientos();
    }

    public default void prepararEntornoTamMinimoMuestraMediaVarConocida(){

        VistaIntervalosConfianza.datosIC.setVisibility(View.GONE);
        VistaIntervalosConfianza.datosCoeficienteConfianza.setVisibility(View.GONE);
        VistaIntervalosConfianza.datosTamMinimoMuestra.setVisibility(View.VISIBLE);
        VistaIntervalosConfianza.resultado.setText("El resultado es: ");
        VistaIntervalosConfianza.imageTeoremaTamMinimoMuestra.setImageResource(R.drawable.tamminimomuestraicmedias1);
        VistaIntervalosConfianza.imageViewDistribucion.setImageResource(R.drawable.zetaenalfamedios);
        desaparecerLayoutsProcedimientos();
    }



    public default void prepararEntornoCoeficienteConfianzaMediaVarConocida(char limiteConocido){

        VistaIntervalosConfianza.datosIC.setVisibility(View.GONE);
        if(limiteConocido == 'a'){

            VistaIntervalosConfianza.limiteConocido.setImageResource(R.drawable.a);
            VistaIntervalosConfianza.textViewOp.setText("Límite inferior del intervalo de confianza");
            VistaIntervalosConfianza.ab.setImageResource(R.drawable.a);

        }else if(limiteConocido == 'b'){

            VistaIntervalosConfianza.limiteConocido.setImageResource(R.drawable.b);
            VistaIntervalosConfianza.textViewOp.setText("Límite superior del intervalo de confianza");
            VistaIntervalosConfianza.ab.setImageResource(R.drawable.b);

        }
        VistaIntervalosConfianza.datosCoeficienteConfianza.setVisibility(View.VISIBLE);
        VistaIntervalosConfianza.datosTamMinimoMuestra.setVisibility(View.GONE);
        desaparecerLayoutsProcedimientos();

    }

    public default void desaparecerLayoutsProcedimientos(){
        VistaIntervalosConfianza.layoutProcedimientoTamMinimoMuestra.setVisibility(View.GONE);
        VistaIntervalosConfianza.layoutProcedimientoIC.setVisibility(View.GONE);
        VistaIntervalosConfianza.layoutCoeficienteConfianza.setVisibility(View.GONE);
        VistaIntervalosConfianza.resultado.setText("El resultado es:");
    }

    public default void prepararTeoremasCoeficienteConfianzaMediaVarConocida(char limiteConocido, Context context){
        VistaIntervalosConfianza.imageTeoremaCoeficienteConfianza.setImageResource(R.drawable.coeficienteconfianza1);

        if(limiteConocido == 'a'){
            VistaIntervalosConfianza.igualdad.setImageResource(R.drawable.liminfcoeficienteconfianza1);
            VistaIntervalosConfianza.determinante.setImageResource(R.drawable.determinanteacoeficienteconfianza1);

            VistaIntervalosConfianza.botonPorque1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VistaIntervalosConfianza.botonPorque1.setAnimation(R.raw.thinkinglamp);
                    VistaIntervalosConfianza.botonPorque1.setSpeed((float) 999999999);
                    VistaIntervalosConfianza.botonPorque1.playAnimation();
                    VistaIntervalosConfianza.botonPorque1.setRepeatCount(1000000);
                    VistaIntervalosConfianza.botonPorque1.animate()
                            .alpha(0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    VistaIntervalosConfianza.botonPorque1.setImageResource(R.drawable.pregunta);
                                    VistaIntervalosConfianza.botonPorque1.setAlpha(1f);

                                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                    alertDialog
                                            .setCancelable(true)
                                            .setMessage("Es necesario elegir esta igualdad si lo que se conoce es el límite inferior del intervalo de confianza")
                                            .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.cancel();
                                                }
                                            });
                                    AlertDialog botonAyuda1 = alertDialog.create();
                                    botonAyuda1.setTitle("¿Por qué se debe elegir ésta igualdad?");
                                    botonAyuda1.show();

                                }
                            });
                }
            });

        }else if(limiteConocido == 'b'){
            VistaIntervalosConfianza.igualdad.setImageResource(R.drawable.limsupcoeficienteconfianza1);
            VistaIntervalosConfianza.determinante.setImageResource(R.drawable.determinantebcoeficienteconfianza1);
            VistaIntervalosConfianza.botonPorque1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VistaIntervalosConfianza.botonPorque1.setAnimation(R.raw.thinkinglamp);
                    VistaIntervalosConfianza.botonPorque1.setSpeed((float) 999999999);
                    VistaIntervalosConfianza.botonPorque1.playAnimation();
                    VistaIntervalosConfianza.botonPorque1.setRepeatCount(1000000);
                    VistaIntervalosConfianza.botonPorque1.animate()
                            .alpha(0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    VistaIntervalosConfianza.botonPorque1.setImageResource(R.drawable.pregunta);
                                    VistaIntervalosConfianza.botonPorque1.setAlpha(1f);

                                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                    alertDialog
                                            .setCancelable(true)
                                            .setMessage("Es necesario elegir esta igualdad si lo que se conoce es el límite superior del intervalo de confianza")
                                            .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.cancel();
                                                }
                                            });
                                    AlertDialog botonAyuda1 = alertDialog.create();
                                    botonAyuda1.setTitle("¿Por qué se debe elegir ésta igualdad?");
                                    botonAyuda1.show();

                                }
                            });
                }
            });

        }
        VistaIntervalosConfianza.layoutCoeficienteConfianza.setVisibility(View.VISIBLE);
    }




    public default void prepararEntornoCCMediasVarDesc(char limiteConocido){
        VistaIntervalosConfianzaMediaDesEstandarDesconocida.datosIC.setVisibility(View.VISIBLE);
        VistaIntervalosConfianzaMediaDesEstandarDesconocida.datosCoeficienteConf.setVisibility(View.GONE);
        VistaIntervalosConfianzaMediaDesEstandarDesconocida.imageDatoOpcional.setImageResource(R.drawable.snmenosuno);
        VistaIntervalosConfianzaMediaDesEstandarDesconocida.textDesvEstOp.setText("Desviación estándar muestral");
        VistaIntervalosConfianzaMediaDesEstandarDesconocida.teoremaCC.setImageResource(R.drawable.coeficienteconfianzamedias2);


        if(limiteConocido == 'a'){
            VistaIntervalosConfianzaMediaDesEstandarDesconocida.igualdad.setImageResource(R.drawable.igualdadacoeficienteconfianzamedias2);
            VistaIntervalosConfianzaMediaDesEstandarDesconocida.determinante.setImageResource(R.drawable.determinanteacoeficienteconfianzamedias2);
        }else if(limiteConocido == 'b'){
            VistaIntervalosConfianzaMediaDesEstandarDesconocida.igualdad.setImageResource(R.drawable.igualdadbcoeficienteconfianzamedias2);
            VistaIntervalosConfianzaMediaDesEstandarDesconocida.determinante.setImageResource(R.drawable.determinantebcoeficienteconfianzamedias2);
        }

    }

    public default void prepararEntornoAproximacion(){

    }

    void prepararEntornoIC();


    public default void mostrarMensajeErrorDatos(Context context){
        Toast.makeText(context, "Error, por favor ingresa todos los datos correctamente", Toast.LENGTH_SHORT).show();
    }

    public default  void mostrarMensajeErrorException(Context context,Exception exception){
        Toast.makeText(context, "Error, " + exception.toString(), Toast.LENGTH_SHORT).show();
    }


    public default void desplegarBotonBorrar(){

    }


}
