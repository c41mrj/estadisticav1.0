package com.example.estadistica.procedimiento.pruebaship.difmedias;

import com.example.estadistica.R;
import com.example.estadistica.TEOREMACENTRALLIMITE;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.estadistica.conversiones;

public interface ControllerPruebasHipotesisDiferenciaMedias extends conversiones {





    public default void setPaso1DiferenciaMediasVarianzasConocidas(Activity context, String caso,double significancia) {

        String paso1_1 = "";

        switch(caso){
            case "Caso a":

                final TextView textViewPaso1CasoA = context.findViewById(R.id.textView144);
                paso1_1 = "Cómo se puede observar en la expresión dada. Es necesario buscar el valor de α = " + significancia + "En las tablas de la distribución normal estándar.\n" +
                        "En este caso:";
                break;

            case "Caso b":

                final TextView textViewPaso1CasoB = context.findViewById(R.id.textView144);
                textViewPaso1CasoB.setText("Donde, en este caso solo se hace necesario calcular el límite superior para el área de no rechazo: ");
                paso1_1 = "Cómo se puede observar en la expresión dada. Es necesario buscar el valor de α = " + significancia + " en las tablas de la distribución normal estándar.\n" +
                        "En este caso:";

                break;


            case "Caso c":
            case "Caso d":

                final LinearLayout layoutDosLimites= context.findViewById(R.id.layoutDosLimites);
                layoutDosLimites.setVisibility(View.VISIBLE);
                final LinearLayout layoutUnLimite = context.findViewById(R.id.layoutLimiteInferior);
                layoutUnLimite.setVisibility(View.VISIBLE);

                paso1_1 = "Cómo se puede observar en las expresiones dadas. Es necesario buscar el valor de α/2 = " + (redondeoDecimales((significancia/2),4)) + " en las tablas de la distribución normal estándar.\n" +
                        "En este caso:";

                break;

            default:
                Toast.makeText(context, "Caso invalido", Toast.LENGTH_SHORT).show();
                break;
        }

        final TextView textViewPaso1 = context.findViewById(R.id.textViewNormalEstandar);
        textViewPaso1.setText(paso1_1);
    }

    public default void setValoresEnTablasDiferenciaMediasVarianzasConocidas(Activity activity, String caso, double d0, double diferenciaPromedios,double varianza1, double varianza2, double significancia, int tamMuestra1, int tamMuestra2){


    }


    public default void setInputsDiferenciaMediasVarianzasConocidas(Activity activity, String caso){

        final CardView cardViewSignificancia = activity.findViewById(R.id.cardOpcional1);
        final CardView cardViewValSignif = activity.findViewById(R.id.cardOpcional2);
        cardViewValSignif.setVisibility(View.VISIBLE);
        cardViewSignificancia.setVisibility(View.VISIBLE);

        final ImageView imageViewD0 = activity.findViewById(R.id.imageView57);
        final TextView textViewd0 = activity.findViewById(R.id.textView126);
        imageViewD0.setImageResource(R.drawable.d0);
        textViewd0.setText("Valor esperado de la diferencia de medias poblacionales");

        final ImageView imageViewDiferenciaPromedios = activity.findViewById(R.id.imageView64);
        final TextView textViewdDiferenciaPromedios = activity.findViewById(R.id.textView127);
        imageViewDiferenciaPromedios.setImageResource(R.drawable.diferenciapromedios);
        textViewdDiferenciaPromedios.setText("Valor de la diferencia de promedios muestrales");

        final ImageView imageViewVarianza1 = activity.findViewById(R.id.imageView65);
        final TextView textViewVarianza1 = activity.findViewById(R.id.textView128);
        imageViewVarianza1.setImageResource(R.drawable.scuadrada1);
        textViewVarianza1.setText("Varianza muestral 1");


        final ImageView imageViewTamMuestra1 = activity.findViewById(R.id.imageView66);
        final TextView textViewTamMuestra1 = activity.findViewById(R.id.textView129);
        imageViewTamMuestra1.setImageResource(R.drawable.n1);
        textViewTamMuestra1.setText("Tamaño de la muestra 1");


        final ImageView imageViewVarianza2 = activity.findViewById(R.id.imageView67);
        final TextView textViewVarianza2 = activity.findViewById(R.id.textView130);
        imageViewVarianza2.setImageResource(R.drawable.scuadrada2);
        textViewVarianza2.setText("Varianza muestral 2");

        final ImageView imageViewTamMuestra2 = activity.findViewById(R.id.imageView68);
        final TextView textViewTamMuestra2 = activity.findViewById(R.id.textView129);
        imageViewTamMuestra2.setImageResource(R.drawable.n2);
        textViewTamMuestra2.setText("Tamaño de la muestra 2");


        final ImageView imageViewSignificancia = activity.findViewById(R.id.imageViewSignificanciaDifMedias);
        final TextView textViewSignificancia = activity.findViewById(R.id.textView129);
        imageViewSignificancia.setImageResource(R.drawable.n2);
        textViewSignificancia.setText("Significancia");

        if(caso.equals("Caso d")){

            final CardView cardViewOpcional3 = activity.findViewById(R.id.cardOpcional3);
            cardViewOpcional3.setVisibility(View.VISIBLE);

            final CardView cardViewOpcional4 = activity.findViewById(R.id.cardOpcional4);
            cardViewOpcional4.setVisibility(View.VISIBLE);

            final ImageView imageViewSignificanciaOpcionD = activity.findViewById(R.id.imageViewSignificanciaDifMedias);
            final TextView textViewSignificanciaOpcionD = activity.findViewById(R.id.textViewValSignificanciaDifMedias);

            imageViewD0.setImageResource(R.drawable.d0);
            textViewd0.setText("Primer valor esperado de la diferencia de medias poblacionales");

            imageViewDiferenciaPromedios.setImageResource(R.drawable.d1);
            textViewdDiferenciaPromedios.setText("Segundo valor esperado de la diferencia de medias poblacionales");

            imageViewVarianza1.setImageResource(R.drawable.diferenciapromedios);
            textViewVarianza1.setText("Diferencia de promedios muestrales");

            imageViewTamMuestra1.setImageResource(R.drawable.scuadrada1);
            textViewTamMuestra1.setText("Varianza de la muestra 1");

            imageViewVarianza2.setImageResource(R.drawable.n1);
            textViewVarianza2.setText("Tamaño de la muestra 1");

            imageViewTamMuestra2.setImageResource(R.drawable.scuadrada2);
            textViewTamMuestra2.setText("Varianza de la muestra 2");

            imageViewSignificancia.setImageResource(R.drawable.n2);
            textViewSignificancia.setText("Tamaño de la muestra 2");

            imageViewSignificanciaOpcionD.setImageResource(R.drawable.alfa);
            textViewSignificanciaOpcionD.setText("Significancia");

        }

    }

    public default void setImagenesEnTablasDiferenciaMediasVarianzasConocidas(Activity activity, String caso){

        final ImageView imageView63 = activity.findViewById(R.id.imageView63);
        final ImageView imageView69 = activity.findViewById(R.id.imageView69);
        final ImageView imageView70 = activity.findViewById(R.id.imageView70);
        final ImageView imageView71 = activity.findViewById(R.id.imageView71);
        final ImageView imageView72 = activity.findViewById(R.id.imageView72);
        final ImageView imageView103 = activity.findViewById(R.id.imageView103);
        final ImageView imageView73 = activity.findViewById(R.id.imageView73);
        final ImageView imageView78 = activity.findViewById(R.id.imageView78);
        final ImageView imageView79 = activity.findViewById(R.id.imageView79);

        imageView63.setImageResource(R.drawable.d0);
        imageView69.setImageResource(R.drawable.diferenciapromedios);
        imageView70.setImageResource(R.drawable.scuadrada1);
        imageView71.setImageResource(R.drawable.n1);
        imageView72.setImageResource(R.drawable.scuadrada2);
        imageView103.setImageResource(R.drawable.n2);
        imageView73.setImageResource(R.drawable.alfa);

        final TableRow tableRow6 = activity.findViewById(R.id.tbR6);





        switch (caso){
            case "Caso a":

                final ImageView imageViewContraste1 = activity.findViewById(R.id.imageView84);
                imageViewContraste1.setImageResource(R.drawable.contrastehipotesiscasoadifmediasvarconocidas);

                //final ImageView imageView

                break;

        }

    }



   // public default


}
