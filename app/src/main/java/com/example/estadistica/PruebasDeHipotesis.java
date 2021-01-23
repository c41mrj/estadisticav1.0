package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebasDeHipotesisBinding;

public class PruebasDeHipotesis extends AppCompatActivity implements ProcedimientoPruebasHipotesisMediaDesviacionConocida{


    public static double desviacionEstandar,mediaPoblacional,promedioMuestral,significancia,mediaPoblacional1,limInf,limSup;
    public static int tamMuestra;
    public PruebaHipotesisDVconocida teorema;
    private ActivityPruebasDeHipotesisBinding lienzo;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebasDeHipotesisBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        key = getIntent().getStringExtra("key");

        lienzo.subtitulo.setText("Para la media si se conoce la desviación estándar poblacional.");

        if(key.equals("Caso a")){
            Toast.makeText(this, "Caso a", Toast.LENGTH_SHORT).show();
            prepararEntornoPruebaHipotesis(this,"Caso a",this);
        }else if(key.equals("Caso b")){
            prepararEntornoPruebaHipotesis(this,"Caso b",this);
        }else if(key.equals("Caso c")){
            prepararEntornoPruebaHipotesis(this,"Caso c",this);
        }else if(key.equals("Caso d")){
            prepararEntornoPruebaHipotesis(this,"Caso d",this);
        }

    }

    public void prepararEntornoPruebaHipotesis(Activity activity, String caso, Context context){


        if(caso.equals("Caso a")){

            lienzo.imageView74.setImageResource(R.drawable.casoacontrastehipotesis);
            lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesiscasoa1);
            lienzo.textView144.setText("Donde, en éste caso se hace necesario calcular el límite inferior para el área de no rechazo:");

            lienzo.imageView76.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    final View view1 = layoutInflater.inflate(R.layout.region_no_rechazo_limite_inferior,null);
                    final ImageView imageView1 = view1.findViewById(R.id.imageView9);
                    final TextView textView = view1.findViewById(R.id.textView158);
                    final ImageView  imageView2 = view1.findViewById(R.id.imageView11);
                    final TextView textView1 = view1.findViewById(R.id.textView159);
                    final TextView textView2 = view1.findViewById(R.id.textView156);

                    imageView1.setImageResource(R.drawable.graficagausscasoa);
                    textView.setText("En éste caso el valor crítico está representado por el promedio muestral");
                    imageView2.setImageResource(R.drawable.promediomuestral);
                    textView1.setText(" = " + PruebasDeHipotesis.promedioMuestral);
                    textView2.setText("Y la región de no rechazo está dada por el intervalo:\n\n["+PruebasDeHipotesis.limInf + ", ∞]");

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false)
                            .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog1 = builder.create();
                    alertDialog1.setView(view);
                    alertDialog1.show();
                }
            });

            lienzo.imageView77.setImageResource(R.drawable.liminfregionnorechazocasoa);
            lienzo.imageView78.setImageResource(R.drawable.alfa);
            lienzo.imageView79.setImageResource(R.drawable.zetaalfa);
            lienzo.layoutLimiteInferior.setVisibility(View.VISIBLE);

            lienzo.imageView84.setImageResource(R.drawable.casoacontrastehipotesis);

            lienzo.botoncalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    lienzo.botoncalcular.setAnimation(R.raw.loading);
                    lienzo.botoncalcular.setSpeed((float) 999999999);
                    lienzo.botoncalcular.playAnimation();
                    lienzo.botoncalcular.setRepeatCount(1000000);
                    lienzo.botoncalcular.animate()
                            .alpha(0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    lienzo.botoncalcular.setImageResource(R.drawable.calculadora);
                                    lienzo.botoncalcular.setAlpha(1f);
                                    try {

                                        PruebasDeHipotesis.mediaPoblacional = convertirStringADouble(lienzo.editTextNumberDecimal10.getText().toString());
                                        PruebasDeHipotesis.promedioMuestral = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                                        PruebasDeHipotesis.desviacionEstandar = convertirStringADouble(lienzo.editTextNumberDecimal11.getText().toString());
                                        PruebasDeHipotesis.significancia = convertirStringADouble(lienzo.editTextNumberSigned4.getText().toString());
                                        PruebasDeHipotesis.tamMuestra = convertirStringAInt(lienzo.editTextNumber4.getText().toString());
                                        teorema = new PruebaHipotesisDVconocida();
                                        String conclusion = teorema.casoa((float) PruebasDeHipotesis.significancia, PruebasDeHipotesis.tamMuestra, PruebasDeHipotesis.desviacionEstandar, PruebasDeHipotesis.promedioMuestral, PruebasDeHipotesis.mediaPoblacional);
                                        limInf = teorema.getLim_inf();
                                        lienzo.Resultado.setText("Con un límite inferior para la región de no rechazo de: " + PruebasDeHipotesis.limInf + "\nPodemos concluir que:\n\n" + conclusion);
                                        actualizarValoresEnTablas();

                                        lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en la " +
                                                "fórmula para el límite inferior de la región de no rechazo. Donde el límite inferior es igual a: " + limInf);

                                        String aux;

                                        if(promedioMuestral<limInf){

                                            aux = promedioMuestral + "<" + limInf;

                                        }else{
                                            aux = promedioMuestral + "≥" + limInf;
                                        }

                                        lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado en el inicio del procedimiento tenemos la siguiente interpretación de la misma:\n\n" +
                                                "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es menor al límite inferior de la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                aux + "\n\nPor lo tanto:");

                                        lienzo.textView155.setText("Con un límite inferior para la región de no rechazo de: " + PruebasDeHipotesis.limInf + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + conclusion);

                                        lienzo.procedimiento.setVisibility(View.VISIBLE);

                                    }catch (NumberFormatException numberFormatException){
                                        Toast.makeText(context, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
                                        lienzo.procedimiento.setVisibility(View.GONE);
                                    }
                                }
                            });


                }
            });


            lienzo.textView152.setText("Una vez encontrado el valor en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en la fórmula presentada anteriormente para la región de rechazo. Donde:");



        }else if(caso.equals("Caso b")){


            lienzo.imageView74.setImageResource(R.drawable.casobcontrastehipotesis);
            lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesiscasob1);
            lienzo.textView144.setText("Donde, en éste caso se hace necesario calcular el límite superior para el área de no rechazo:");
            lienzo.imageView77.setImageResource(R.drawable.limsupregionnorechazocasob1);
            lienzo.imageView78.setImageResource(R.drawable.alfa);
            lienzo.imageView79.setImageResource(R.drawable.zetaalfa);
            lienzo.layoutLimiteInferior.setVisibility(View.VISIBLE);

            lienzo.imageView84.setImageResource(R.drawable.casobcontrastehipotesis);


            lienzo.textView152.setText("Una vez encontrado el valor en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en la fórmula presentada anteriormente para la región de rechazo. Donde:");


            lienzo.imageView76.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    final View view1 = layoutInflater.inflate(R.layout.region_no_rechazo_limite_inferior,null);
                    final ImageView imageView1 = view1.findViewById(R.id.imageView9);
                    final TextView  textView = view1.findViewById(R.id.textView158);
                    final ImageView  imageView2 = view1.findViewById(R.id.imageView11);
                    final TextView textView1 = view1.findViewById(R.id.textView159);
                    final TextView textView2 = view1.findViewById(R.id.textView156);

                    imageView1.setImageResource(R.drawable.graficagausscasob);
                    textView.setText("En éste caso el valor crítico está representado por el promedio muestral");
                    imageView2.setImageResource(R.drawable.promediomuestral);
                    textView1.setText(" = " + PruebasDeHipotesis.promedioMuestral);
                    textView2.setText("Y la región de no rechazo está dada por el intervalo:\n\n[-∞, " + PruebasDeHipotesis.limSup + "]");

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false)
                            .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog1 = builder.create();
                    alertDialog1.setView(view);
                    alertDialog1.show();
                }
            });

            lienzo.botoncalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    lienzo.botoncalcular.setAnimation(R.raw.loading);
                    lienzo.botoncalcular.setSpeed((float) 999999999);
                    lienzo.botoncalcular.playAnimation();
                    lienzo.botoncalcular.setRepeatCount(1000000);
                    lienzo.botoncalcular.animate()
                            .alpha(0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    lienzo.botoncalcular.setImageResource(R.drawable.calculadora);
                                    lienzo.botoncalcular.setAlpha(1f);
                                    try{

                                        PruebasDeHipotesis.mediaPoblacional = convertirStringADouble(lienzo.editTextNumberDecimal10.getText().toString());
                                        PruebasDeHipotesis.promedioMuestral = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                                        PruebasDeHipotesis.desviacionEstandar = convertirStringADouble(lienzo.editTextNumberDecimal11.getText().toString());
                                        PruebasDeHipotesis.significancia = convertirStringADouble(lienzo.editTextNumberSigned4.getText().toString());
                                        PruebasDeHipotesis.tamMuestra = convertirStringAInt(lienzo.editTextNumber4.getText().toString());
                                        teorema = new PruebaHipotesisDVconocida();
                                        String conclusion = teorema.casob((float)PruebasDeHipotesis.significancia,PruebasDeHipotesis.tamMuestra,PruebasDeHipotesis.desviacionEstandar,PruebasDeHipotesis.promedioMuestral,PruebasDeHipotesis.mediaPoblacional);
                                        limSup = teorema.getLim_sup();
                                        lienzo.Resultado.setText("Con un límite superior para la región de no rechazo de: " + PruebasDeHipotesis.limSup + "\nPodemos concluir que:\n\n" + conclusion);
                                        actualizarValoresEnTablas();

                                        lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en la " +
                                                "fórmula para el límite superior de la región de no rechazo. Donde el límite superior es igual a: " + limSup);

                                        String aux;

                                        if(promedioMuestral<limInf){

                                            aux = promedioMuestral + "<" + limInf;

                                        }else{
                                            aux = promedioMuestral + "≥" + limInf;
                                        }

                                        lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado en el inicio del procedimiento tenemos la siguiente interpretación de la misma:\n\n" +
                                                "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es mayor al límite superior de la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                aux + "\n\nPor lo tanto:");

                                        lienzo.textView155.setText("Con un límite superior para la región de no rechazo de: " + PruebasDeHipotesis.limSup + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + conclusion);

                                        lienzo.procedimiento.setVisibility(View.VISIBLE);

                                    }catch (NumberFormatException numberFormatException){
                                        Toast.makeText(context, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
                                        lienzo.procedimiento.setVisibility(View.GONE);
                                    }
                                }
                            });


                }
            });


        }else if(caso.equals("Caso c")){


            lienzo.imageView74.setImageResource(R.drawable.casoccontrastehipotesis);
            lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesiscasoc1);
            lienzo.imageView81.setImageResource(R.drawable.liminfregionnorechazocasoc1);
            lienzo.imageView82.setImageResource(R.drawable.limsupregionnorechazocasoc1);

            lienzo.layoutDosLimites.setVisibility(View.VISIBLE);

            lienzo.imageView78.setImageResource(R.drawable.alfamedios);
            lienzo.imageView79.setImageResource(R.drawable.zetaalfamedios);


            lienzo.imageView84.setImageResource(R.drawable.casoccontrastehipotesis);

            lienzo.textView152.setText("Una vez encontrado el valor en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en las fórmulas presentadas anteriormente para la región de rechazo. Donde:");

            lienzo.imageView76.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    final View view1 = layoutInflater.inflate(R.layout.region_no_rechazo_limite_inferior,null);
                    final ImageView imageView1 = view1.findViewById(R.id.imageView9);
                    final TextView  textView = view1.findViewById(R.id.textView158);
                    final ImageView  imageView2 = view1.findViewById(R.id.imageView11);
                    final TextView textView1 = view1.findViewById(R.id.textView159);
                    final TextView textView2 = view1.findViewById(R.id.textView156);

                    imageView1.setImageResource(R.drawable.graficagausscasoc);
                    textView.setText("En éste caso el valor crítico está representado por el promedio muestral");
                    imageView2.setImageResource(R.drawable.promediomuestral);
                    textView1.setText(" = " + PruebasDeHipotesis.promedioMuestral);
                    textView2.setText("Y la región de no rechazo está dada por el intervalo:\n\n[" + PruebasDeHipotesis.limInf + "," + PruebasDeHipotesis.limSup+"]");

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false)
                            .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog1 = builder.create();
                    alertDialog1.setView(view);
                    alertDialog1.show();
                }
            });

            lienzo.botoncalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    lienzo.botoncalcular.setAnimation(R.raw.loading);
                    lienzo.botoncalcular.setSpeed((float) 999999999);
                    lienzo.botoncalcular.playAnimation();
                    lienzo.botoncalcular.setRepeatCount(1000000);
                    lienzo.botoncalcular.animate()
                            .alpha(0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    lienzo.botoncalcular.setImageResource(R.drawable.calculadora);
                                    lienzo.botoncalcular.setAlpha(1f);
                                    try{

                                        PruebasDeHipotesis.mediaPoblacional = convertirStringADouble(lienzo.editTextNumberDecimal10.getText().toString());
                                        PruebasDeHipotesis.promedioMuestral = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                                        PruebasDeHipotesis.desviacionEstandar = convertirStringADouble(lienzo.editTextNumberDecimal11.getText().toString());
                                        PruebasDeHipotesis.significancia = convertirStringADouble(lienzo.editTextNumberSigned4.getText().toString());
                                        PruebasDeHipotesis.tamMuestra = convertirStringAInt(lienzo.editTextNumber4.getText().toString());
                                        teorema = new PruebaHipotesisDVconocida();
                                        String conclusion = teorema.casoc((float)PruebasDeHipotesis.significancia,PruebasDeHipotesis.tamMuestra,PruebasDeHipotesis.desviacionEstandar,PruebasDeHipotesis.promedioMuestral,PruebasDeHipotesis.mediaPoblacional);
                                        limInf = teorema.getLim_inf();
                                        limSup = teorema.getLim_sup();
                                        lienzo.Resultado.setText("Para un intervalo de la región de no rechazo de:\n\n[" + PruebasDeHipotesis.limInf + "," + PruebasDeHipotesis.limSup+"]\n\nPodemos concluir que:\n" + conclusion);
                                        actualizarValoresEnTablas();

                                        lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en la " +
                                                "fórmula para el intervalo de la región de no rechazo. En éste caso: [" + limInf + "," + limInf + "]");

                                        String aux;

                                        if(promedioMuestral<limInf){

                                            aux = promedioMuestral + "<" + limInf;

                                        }else if(promedioMuestral>limSup){
                                            aux = promedioMuestral + ">" + limInf;
                                        }else{
                                            aux = "El promedio muestral está dentro del intervalo para la región de no rechazo";
                                        }

                                        lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado en el inicio del procedimiento tenemos la siguiente interpretación de la misma:\n\n" +
                                                "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional está fuera del intervalo para la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                aux + "\n\nPor lo tanto:");

                                        lienzo.textView155.setText("Con un intervalo para la región de no rechazo: " + "[" + limInf + "," + limInf + "]" + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + conclusion);

                                        lienzo.procedimiento.setVisibility(View.VISIBLE);

                                    }catch (NumberFormatException numberFormatException){
                                        Toast.makeText(context, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
                                        lienzo.procedimiento.setVisibility(View.GONE);
                                    }
                                }
                            });


                }
            });


        }else if(caso.equals("Caso d")){

            lienzo.cardOpcional1.setVisibility(View.VISIBLE);
            lienzo.cardOpcional2.setVisibility(View.VISIBLE);

            lienzo.imageView74.setImageResource(R.drawable.casoccontrastehipotesis);
            lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesiscasoc1);
            lienzo.imageView81.setImageResource(R.drawable.liminfregionnorechazocasoc1);
            lienzo.imageView82.setImageResource(R.drawable.limsupregionnorechazocasoc1);

            lienzo.imageView84.setImageResource(R.drawable.casodcontrastehipotesis);

            lienzo.layoutDosLimites.setVisibility(View.VISIBLE);

            lienzo.imageView78.setImageResource(R.drawable.alfamedios);
            lienzo.imageView79.setImageResource(R.drawable.zetaalfamedios);

            lienzo.textView152.setText("Una vez encontrado el valor en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en las fórmulas presentadas anteriormente para la región de rechazo. Donde:");


            lienzo.imageView76.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    final View view1 = layoutInflater.inflate(R.layout.region_no_rechazo_limite_inferior,null);
                    final ImageView imageView1 = view1.findViewById(R.id.imageView9);
                    final TextView  textView = view1.findViewById(R.id.textView158);
                    final ImageView  imageView2 = view1.findViewById(R.id.imageView11);
                    final TextView textView1 = view1.findViewById(R.id.textView159);
                    final TextView textView2 = view1.findViewById(R.id.textView156);

                    imageView1.setImageResource(R.drawable.graficagausscasoc);
                    textView.setText("En éste caso el valor crítico está representado por el promedio muestral");
                    imageView2.setImageResource(R.drawable.promediomuestral);
                    textView1.setText(" = " + PruebasDeHipotesis.promedioMuestral);
                    textView2.setText("Y la región de no rechazo está dada por el intervalo:\n\n[" + PruebasDeHipotesis.limInf + "," + PruebasDeHipotesis.limSup+"]");

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setCancelable(false)
                            .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alertDialog1 = builder.create();
                    alertDialog1.setView(view);
                    alertDialog1.show();
                }
            });

            lienzo.botoncalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    lienzo.botoncalcular.setAnimation(R.raw.loading);
                    lienzo.botoncalcular.setSpeed((float) 999999999);
                    lienzo.botoncalcular.playAnimation();
                    lienzo.botoncalcular.setRepeatCount(1000000);
                    lienzo.botoncalcular.animate()
                            .alpha(0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    lienzo.botoncalcular.setImageResource(R.drawable.calculadora);
                                    lienzo.botoncalcular.setAlpha(1f);
                                    try{

                                        PruebasDeHipotesis.mediaPoblacional = convertirStringADouble(lienzo.editTextNumberDecimal10.getText().toString());
                                        PruebasDeHipotesis.promedioMuestral = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                                        PruebasDeHipotesis.desviacionEstandar = convertirStringADouble(lienzo.editTextNumberDecimal11.getText().toString());
                                        PruebasDeHipotesis.significancia = convertirStringADouble(lienzo.editTextNumberSigned4.getText().toString());
                                        PruebasDeHipotesis.tamMuestra = convertirStringAInt(lienzo.editTextNumber4.getText().toString());
                                        PruebasDeHipotesis.mediaPoblacional1 = convertirStringADouble(lienzo.editTextNumberDecimal12.getText().toString());
                                        teorema = new PruebaHipotesisDVconocida();
                                        String conclusion = teorema.casod((float)PruebasDeHipotesis.significancia,PruebasDeHipotesis.tamMuestra,PruebasDeHipotesis.desviacionEstandar,PruebasDeHipotesis.promedioMuestral,PruebasDeHipotesis.mediaPoblacional,PruebasDeHipotesis.mediaPoblacional1);
                                        limInf = teorema.getLim_inf();
                                        limSup = teorema.getLim_sup();
                                        lienzo.Resultado.setText("Para un intervalo de la región de no rechazo de:\n\n[" + PruebasDeHipotesis.limInf + "," + PruebasDeHipotesis.limSup+"]\n\nPodemos concluir que:\n" + conclusion);
                                        actualizarValoresEnTablas();

                                        lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en la " +
                                                "fórmula para el intervalo de la región de no rechazo. En éste caso: [" + limInf + "," + limInf + "]");

                                        String aux;

                                        if(promedioMuestral<limInf){

                                            aux = promedioMuestral + "<" + limInf;

                                        }else if(promedioMuestral>limSup){
                                            aux = promedioMuestral + ">" + limInf;
                                        }else{
                                            aux = "El promedio muestral está dentro del intervalo para la región de no rechazo";
                                        }

                                        lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado en el inicio del procedimiento tenemos la siguiente interpretación de la misma:\n\n" +
                                                "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional está fuera del intervalo para la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                aux + "\n\nPor lo tanto:");

                                        lienzo.textView155.setText("Con un intervalo para la región de no rechazo: " + "[" + limInf + "," + limInf + "]" + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + conclusion);

                                        lienzo.procedimiento.setVisibility(View.VISIBLE);

                                    }catch (NumberFormatException numberFormatException){
                                        Toast.makeText(context, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
                                        lienzo.procedimiento.setVisibility(View.GONE);
                                    }
                                }
                            });


                }
            });

        }

        lienzo.editTextNumberSigned4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().isEmpty()){

                }else{

                    if(editable.toString().contains(".")){

                    }else{
                        if(convertirStringAInt(editable.toString())>=1){
                            Toast.makeText(context, "Por favor ingresa el valor de la significancia en decimal...", Toast.LENGTH_SHORT).show();
                            double aux = convertirStringADouble(editable.toString());
                            lienzo.editTextNumberSigned4.setText(String.valueOf(redondeoDecimales((aux/100),4)));
                        }
                    }
                }

            }
        });

    }



    @Override
    public void actualizarValoresEnTablas() {

        lienzo.textView133.setText(" = " + mediaPoblacional);
        lienzo.textView134.setText(" = " + promedioMuestral);
        lienzo.textView135.setText(" = " + desviacionEstandar);
        lienzo.textView136.setText(" = " + significancia);
        lienzo.textView137.setText(" = " + tamMuestra);
        lienzo.textView138.setText(" = " + mediaPoblacional1);

        lienzo.textView143.setText(" = " + significancia);
        lienzo.textView145.setText(" = " + teorema.getValTablas());


    }
}