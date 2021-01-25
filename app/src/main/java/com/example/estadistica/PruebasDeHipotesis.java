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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebasDeHipotesisBinding;

public class PruebasDeHipotesis extends AppCompatActivity implements ProcedimientoPruebasHipotesisMediaDesviacionConocida,conversiones,conclusionPotenciaPrueba{


    public static double desviacionEstandar,mediaPoblacional,promedioMuestral,significancia,mediaPoblacional1,limInf,limSup;
    public static int tamMuestra;
    public PruebaHipotesisDVconocida teorema;
    private double nuevaMediaPob,nuevaMediaPob1,potenciaPrueba;
    private ActivityPruebasDeHipotesisBinding lienzo;
    private String key;
    private String ultimoCaso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebasDeHipotesisBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        key = getIntent().getStringExtra("key");

        lienzo.subtitulo.setText("Para la media si se conoce la desviación estándar poblacional.");

        lienzo.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lienzo.delete.setAnimation(R.raw.delete);
                lienzo.delete.setSpeed((float) 999999999);
                lienzo.delete.playAnimation();
                lienzo.delete.setRepeatCount(1000000);
                lienzo.delete.animate()
                        .alpha(0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                lienzo.delete.setImageResource(R.drawable.gomadeborrar);
                                lienzo.delete.setAlpha(1f);
                                lienzo.procedimiento.setVisibility(View.GONE);
                                lienzo.editTextNumberDecimal10.setText("");
                                lienzo.editTextNumberSigned2.setText("");
                                lienzo.editTextNumberDecimal11.setText("");
                                lienzo.editTextNumberSigned4.setText("");
                                lienzo.editTextNumber4.setText("");
                                lienzo.Resultado.setText("El resultado es:");
                                lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.GONE);
                                lienzo.imageView83.setVisibility(View.GONE);
                            }
                        });

            }
        });

        if(key.equals("Caso a")){
            Toast.makeText(this, "Caso a", Toast.LENGTH_SHORT).show();
            prepararEntornoPruebaHipotesis(this,"Caso a",this);
            ultimoCaso = "Caso a";
        }else if(key.equals("Caso b")){
            prepararEntornoPruebaHipotesis(this,"Caso b",this);
            ultimoCaso = "Caso b";
        }else if(key.equals("Caso c")){
            prepararEntornoPruebaHipotesis(this,"Caso c",this);
            ultimoCaso = "Caso c";
        }else if(key.equals("Caso d")){
            prepararEntornoPruebaHipotesis(this,"Caso d",this);
        }

        lienzo.imageView83.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desplegarOpciones();
            }
        });

    }


    public void calcularPotenciaDeLaPrueba(String caso){
       switch (caso){
           case "Caso a":

               potenciaPrueba = teorema.potenciaPrueba(limInf,nuevaMediaPob,desviacionEstandar,tamMuestra,"caso1");
               lienzo.Resultado.setText("Si el límite inferior calculado para el área de no rechazo es: " + limInf + "\nY la nueva media poblacional es: " + nuevaMediaPob
               +"\n\nLa potencia de la prueba calculada es: " + potenciaPrueba+ "\n\n" + getConclusionPotenciaPrueba(potenciaPrueba));

               lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.VISIBLE);
               lienzo.procedimiento.setVisibility(View.GONE);

               lienzo.imageView85.setImageResource(R.drawable.graficagausscasoa);
               lienzo.imageView87.setImageResource(R.drawable.regionrechazocasoa1);

               lienzo.textView165.setText(" = " + limInf);
               lienzo.textView166.setText(" = " + mediaPoblacional);
               lienzo.textView167.setText(" = " + desviacionEstandar);
               lienzo.textView168.setText(" = " + tamMuestra);

               lienzo.textView163.setText("Una vez obtenido el valor para el determinante será necesario buscarlo en las tablas para la distribución normal estándar, como se muestra a continuación:\n\nP(Z<" + teorema.estadisticoZeta+") = " + teorema.getValTablas()
                       +"\n\nCon lo cual la potencia de la prueba calculada es: " + potenciaPrueba);




               break;
           case "Caso b":

               potenciaPrueba = teorema.potenciaPrueba(limSup,nuevaMediaPob,desviacionEstandar,tamMuestra,"caso2");
               lienzo.Resultado.setText("Si el límite superior calculado para el área de no rechazo es: " + limInf + "\nY la nueva media poblacional es: " + nuevaMediaPob
                       +"\n\nLa potencia de la prueba calculada es: " + potenciaPrueba+ "\n\n" + getConclusionPotenciaPrueba(potenciaPrueba));

               lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.VISIBLE);
               lienzo.procedimiento.setVisibility(View.GONE);

               lienzo.imageView85.setImageResource(R.drawable.graficagausscasob);
               lienzo.imageView87.setImageResource(R.drawable.determinanteregionrechazocasob);
               lienzo.imageView89.setImageResource(R.drawable.ls);

               lienzo.textView165.setText(" = " + limInf);
               lienzo.textView166.setText(" = " + mediaPoblacional);
               lienzo.textView167.setText(" = " + desviacionEstandar);
               lienzo.textView168.setText(" = " + tamMuestra);

               lienzo.textView163.setText("Una vez obtenido el valor para el determinante será necesario buscarlo en las tablas para la distribución normal estándar, como se muestra a continuación:\n\nP(Z>" + teorema.estadisticoZeta+") = 1 - P(Z<" + teorema.estadisticoZeta+") = " + teorema.getValTablas()
                       +"\n\nCon lo cual la potencia de la prueba calculada es: " + potenciaPrueba);



               break;

           case "Caso c":

               potenciaPrueba = teorema.potenciaPrueba(limInf,limSup,desviacionEstandar,nuevaMediaPob,tamMuestra);
               String intervalo = "[" + limInf + "," + limSup + "]";
               lienzo.Resultado.setText("Si el intervalo para la región de no rechazo es: " + intervalo + ".\nY la nueva media poblacional es: " + nuevaMediaPob
                       +"\n\nLa potencia de la prueba calculada es: " + potenciaPrueba+ "\n\n" + getConclusionPotenciaPrueba(potenciaPrueba));

               lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.VISIBLE);
               lienzo.procedimiento.setVisibility(View.GONE);

               lienzo.imageView85.setImageResource(R.drawable.graficagausscasoc);
               lienzo.imageView89.setImageResource(R.drawable.ls);
               lienzo.textView162.setText("Para ello primero es necesario calcular el valor de los siguientes determinantes:");

               lienzo.layoutUnLimite.setVisibility(View.GONE);
               lienzo.linearLayoutDosLimites.setVisibility(View.VISIBLE);
               lienzo.valLimInf.setText(" = " + limInf);
               lienzo.valLimSup.setText(" = " + limSup);
               lienzo.valMediaP.setText(" = " + nuevaMediaPob);
               lienzo.valSigmaCero.setText(" = " + desviacionEstandar);
               lienzo.valN.setText(" = " + tamMuestra);

               lienzo.textView163.setText("Una vez obtenido el valor para los determinantes será necesario buscar ambos valores en las tablas para la distribución normal estándar y sumar las probabilidades encontradas, como se muestra a continuación:\n\nP(Z<" + teorema.estadisticoZeta1 +") + P(Z>" + teorema.estadisticoZeta2+") = P(Z<" + teorema.estadisticoZeta1 +") +  [1 - P(Z<" + teorema.estadisticoZeta2+")] = "
                       +teorema.valTablas1 + " + [1 - " + teorema.valtTablas2 + "] = " + potenciaPrueba
                       +"\n\nCon lo cual la potencia de la prueba calculada es: " + potenciaPrueba);

               break;
       }
    }


    public void ingresarNuevaMediaPob(){

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View view  =  layoutInflater.inflate(R.layout.ingresar_nueva_mediapob,null);
        final EditText editTextNuevaMedia = view.findViewById(R.id.editTextNumberDecimal13);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setPositiveButton("Ingresar dato", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(editTextNuevaMedia.getText().toString().isEmpty()){
                            dialogInterface.cancel();
                            ingresarNuevaMediaPob();
                        }else{
                            nuevaMediaPob = convertirStringADouble(editTextNuevaMedia.getText().toString());
                            if(nuevaMediaPob<=mediaPoblacional){
                                Toast.makeText(PruebasDeHipotesis.this, "El valor de la nueva media poblacional debe ser mayor al anterior", Toast.LENGTH_LONG).show();
                            }else{
                                calcularPotenciaDeLaPrueba(ultimoCaso);
                            }
                        }
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog1 = builder.create();
        alertDialog1.setView(view);
        alertDialog1.setTitle("Por favor ingresa un nuevo valor para la media poblacional.");
        alertDialog1.show();

    }

    public void desplegarOpciones(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Deseas calcular la potencia de la prueba?")
                .setCancelable(false)
                .setPositiveButton("Calcular", (dialogInterface, i) -> {

                    ingresarNuevaMediaPob();

                }).setNegativeButton("Cancelar", ((dialogInterface, i) -> {

                    dialogInterface.cancel();
        }));
        AlertDialog alertDialog1 = builder.create();
        alertDialog1.show();

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

                                        lienzo.imageView83.setVisibility(View.VISIBLE);

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
                                        lienzo.imageView83.setVisibility(View.GONE);
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

                                        lienzo.imageView83.setVisibility(View.VISIBLE);

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
                                        lienzo.imageView83.setVisibility(View.GONE);
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

                                        lienzo.imageView83.setVisibility(View.VISIBLE);

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

                                        lienzo.imageView83.setVisibility(View.GONE);
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