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

 public class VistaPruebasDeHipotesisMediaDesviacionDesconocida extends AppCompatActivity implements conversiones,conclusionPotenciaPrueba{

    private ActivityPruebasDeHipotesisBinding lienzo;
    private double desviacionEstandar,promedioMuestral,mediaPoblacional,significancia,limInf,limSup,nuevaMediaPob,potenciaPrueba,mediaPoblacional1;
    private int tamMuestra;
    private String key,ultimoCaso;
    private PHIPDEDESC teorema;
    private PruebaHipotesisDVconocida teoremaAprox = new PruebaHipotesisDVconocida();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebasDeHipotesisBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        key = getIntent().getStringExtra("key");

        lienzo.subtitulo.setText("Para la media si se conoce la desviación estándar poblacional.");

        lienzo.imageView65.setImageResource(R.drawable.snmenosuno);
        lienzo.textView128.setText("Desviación estándar muestral");

        lienzo.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lienzo.delete.setAnimation(R.raw.delete);
                lienzo.delete.setSpeed((float) 999999999);
                lienzo.delete.playAnimation();
                lienzo.delete.setRepeatCount(1000000);

                lienzo.imageView65.setImageResource(R.drawable.snmenosuno);
                lienzo.textView128.setText("Desviación estándar muestral");

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

                if(tamMuestra<=101){

                    potenciaPrueba = teorema.potPrueba(nuevaMediaPob,"caso1");


                    lienzo.Resultado.setText("Si el límite inferior calculado para el área de no rechazo es: " + limInf + "\nY la nueva media poblacional es: " + nuevaMediaPob
                            +"\n\nLa potencia de la prueba calculada es: " + potenciaPrueba+ "\n\n" + getConclusionPotenciaPrueba(potenciaPrueba));

                    lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.VISIBLE);
                    lienzo.procedimiento.setVisibility(View.GONE);

                    lienzo.imageView85.setImageResource(R.drawable.graficagausscasoa);
                    lienzo.imageView87.setImageResource(R.drawable.arearechazopruebashipotesisdesviacionestandardesconocidacasoa);

                    lienzo.textView162.setText("Si observamos la campana de gauss presentada anteriormente y tomamos en cuenta lo dicho al inicio del procedimiento; podemos percatarnos de que se requiere calcular la probabilidad de que el promedio sea menor que " + limInf +" tal que la nueva media poblacional es de " + nuevaMediaPob+ ":\n" +
                            "P(X<" + limInf + "│ μ = " + nuevaMediaPob+ ").\nPara poder calcular dicha probabilidad; primero es necesario el valor de T que posteriormente vamos a buscar en las tablas de la distribucion t-student con n-1 grados de libertad. Para realizar el calculo es posible utilizar la siguiente expresion:");

                    lienzo.textView165.setText(" = " + limInf);
                    lienzo.textView166.setText(" = " + nuevaMediaPob);
                    lienzo.textView167.setText(" = " + desviacionEstandar);
                    lienzo.textView168.setText(" = " + tamMuestra);

                    lienzo.textView163.setText("Una vez obtenido el valor para T será necesario buscarlo en las tablas para la distribución t-student con n-1 grados de libertad, como se muestra a continuación:\n\nP(T<" + teorema.valEstadisticoT+") = " + potenciaPrueba +"\n\n Con lo cual la potencia de la prueba calculada es: " + potenciaPrueba);


                }else{

                    potenciaPrueba = teoremaAprox.potenciaPrueba(limInf,nuevaMediaPob,desviacionEstandar,tamMuestra,"caso1");
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

                    lienzo.textView163.setText("Una vez obtenido el valor para el determinante será necesario buscarlo en las tablas para la distribución normal estándar, como se muestra a continuación:\n\nP(Z<" + teoremaAprox.estadisticoZeta+") = " + teoremaAprox.getValTablas()
                            +"\n\nCon lo cual la potencia de la prueba calculada es: " + potenciaPrueba);

                }


                 break;
             case "Caso b":

                 if(tamMuestra<=101){

                     potenciaPrueba = teorema.potPrueba(nuevaMediaPob,"caso1");


                     lienzo.Resultado.setText("Si el límite superior calculado para el área de no rechazo es: " + limSup + "\nY la nueva media poblacional es: " + nuevaMediaPob
                             +"\n\nLa potencia de la prueba calculada es: " + potenciaPrueba+ "\n\n" + getConclusionPotenciaPrueba(potenciaPrueba));

                     lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.VISIBLE);
                     lienzo.procedimiento.setVisibility(View.GONE);

                     lienzo.imageView85.setImageResource(R.drawable.graficagausscasob);
                     lienzo.imageView87.setImageResource(R.drawable.arearechazopruebashipotesisdesviacionestandardesconocidacasob);
                     lienzo.imageView89.setImageResource(R.drawable.ls);

                     lienzo.textView162.setText("Si observamos la campana de gauss presentada anteriormente y tomamos en cuenta lo dicho al inicio del procedimiento; podemos percatarnos de que se requiere calcular la probabilidad de que el promedio sea menor que " + limInf +" tal que la nueva media poblacional es de " + nuevaMediaPob+ ":\n" +
                             "P(X>" + limSup + "│ μ = " + nuevaMediaPob+ ").\nPara poder calcular dicha probabilidad; primero es necesario el valor de T que posteriormente vamos a buscar en las tablas de la distribucion t-student con n-1 grados de libertad. Para realizar el calculo es posible utilizar la siguiente expresion:");

                     lienzo.textView165.setText(" = " + limSup);
                     lienzo.textView166.setText(" = " + nuevaMediaPob);
                     lienzo.textView167.setText(" = " + desviacionEstandar);
                     lienzo.textView168.setText(" = " + tamMuestra);

                     lienzo.textView163.setText("Una vez obtenido el valor para T será necesario buscarlo en las tablas para la distribución t-student con n-1 grados de libertad, como se muestra a continuación:\n\nP(T>" + teorema.valEstadisticoT+") = 1 - P(T<" + teorema.valEstadisticoT+") = " + potenciaPrueba +"\n\n Con lo cual la potencia de la prueba calculada es: " + potenciaPrueba);


                 }else{

                     potenciaPrueba = teoremaAprox.potenciaPrueba(limSup,nuevaMediaPob,desviacionEstandar,tamMuestra,"caso2");
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

                     lienzo.textView163.setText("Una vez obtenido el valor para el determinante será necesario buscarlo en las tablas para la distribución normal estándar, como se muestra a continuación:\n\nP(Z>" + teoremaAprox.estadisticoZeta+") = 1 - P(Z<" + teoremaAprox.estadisticoZeta+") = " + teoremaAprox.getValTablas()
                             +"\n\nCon lo cual la potencia de la prueba calculada es: " + potenciaPrueba);

                 }
                 break;

             case "Caso c":

                 potenciaPrueba = teorema.potPrueba(nuevaMediaPob,"caso2");

                 if(tamMuestra<=101){

                     String intervalo = "[" + limInf + "," + limSup + "]";
                     lienzo.Resultado.setText("Si el intervalo para la región de no rechazo es: " + intervalo + ".\nY la nueva media poblacional es: " + nuevaMediaPob
                             +"\n\nLa potencia de la prueba calculada es: " + potenciaPrueba+ "\n\n" + getConclusionPotenciaPrueba(potenciaPrueba));

                     lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.VISIBLE);
                     lienzo.procedimiento.setVisibility(View.GONE);

                     lienzo.imageView85.setImageResource(R.drawable.graficagausscasoc);
                     lienzo.imageView89.setImageResource(R.drawable.ls);

                     lienzo.imageView93.setImageResource(R.drawable.arearechazopruebashipotesisdesviacionestandardesconocidacasoa);
                     lienzo.imageView94.setImageResource(R.drawable.arearechazopruebashipotesisdesviacionestandardesconocidacasob);

                     lienzo.textView162.setText("Si observamos la campana de gauss presentada anteriormente y tomamos en cuenta lo dicho al inicio del procedimiento; podemos percatarnos de que se requiere calcular la probabilidad de que el promedio sea menor que " + limInf +" y mayor que " + limSup + " tal que la nueva media poblacional es de " + nuevaMediaPob+ ":\n" +
                             "P(" + limInf + "< X < " + limSup + "│ μ = " + nuevaMediaPob+ ").\nPara poder calcular dicha probabilidad; primero es necesario los valores de T que posteriormente vamos a buscar en las tablas de la distribucion t-student con n-1 grados de libertad. Para realizar los calculos es posible utilizar las siguientes expresiones:");


                     lienzo.layoutUnLimite.setVisibility(View.GONE);
                     lienzo.linearLayoutDosLimites.setVisibility(View.VISIBLE);
                     lienzo.valLimInf.setText(" = " + limInf);
                     lienzo.valLimSup.setText(" = " + limSup);
                     lienzo.valMediaP.setText(" = " + nuevaMediaPob);
                     lienzo.valSigmaCero.setText(" = " + desviacionEstandar);
                     lienzo.valN.setText(" = " + tamMuestra);

                     lienzo.textView163.setText("Una vez obtenido los valores de T será necesario buscar ambos valores en las tablas para la distribución t-student estándar y sumar las probabilidades encontradas, como se muestra a continuación:\n\nP(T<" + teorema.valEstadisticoT +") + P(T>" + teorema.valEstadisticoTs+") = P(Z<" + teorema.valEstadisticoT +") +  [1 - P(Z<" + teorema.valEstadisticoTs+")] = "
                             +teorema.valtTablas1 + " + [1 - " + teorema.valTablas2 + "] = " + potenciaPrueba
                             +"\n\nCon lo cual la potencia de la prueba calculada es: " + potenciaPrueba);

                 }else{

                     potenciaPrueba = teoremaAprox.potenciaPrueba(limInf,limSup,desviacionEstandar,nuevaMediaPob,tamMuestra);
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

                     lienzo.textView163.setText("Una vez obtenido el valor para los determinantes será necesario buscar ambos valores en las tablas para la distribución normal estándar y sumar las probabilidades encontradas, como se muestra a continuación:\n\nP(Z<" + teoremaAprox.estadisticoZeta1 +") + P(Z>" + teoremaAprox.estadisticoZeta2+") = P(Z<" + teoremaAprox.estadisticoZeta1 +") +  [1 - P(Z<" + teoremaAprox.estadisticoZeta2+")] = "
                             +teoremaAprox.valTablas1 + " + [1 - " + teoremaAprox.valtTablas2 + "] = " + potenciaPrueba
                             +"\n\nCon lo cual la potencia de la prueba calculada es: " + potenciaPrueba);

                 }

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
                                 Toast.makeText(VistaPruebasDeHipotesisMediaDesviacionDesconocida.this, "El valor de la nueva media poblacional debe ser mayor al anterior", Toast.LENGTH_LONG).show();
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
                     textView1.setText(" = " + promedioMuestral);
                     textView2.setText("Y la región de no rechazo está dada por el intervalo:\n\n["+limInf + ", ∞]");

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


             lienzo.layoutLimiteInferior.setVisibility(View.VISIBLE);
             lienzo.layoutDosLimites.setVisibility(View.VISIBLE);

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

                                         mediaPoblacional = convertirStringADouble(lienzo.editTextNumberDecimal10.getText().toString());
                                         promedioMuestral = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                                         desviacionEstandar = convertirStringADouble(lienzo.editTextNumberDecimal11.getText().toString());
                                         significancia = convertirStringADouble(lienzo.editTextNumberSigned4.getText().toString());
                                         tamMuestra = convertirStringAInt(lienzo.editTextNumber4.getText().toString());

                                         lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.GONE);

                                         if(tamMuestra<= 101){

                                             lienzo.imageView74.setImageResource(R.drawable.casoacontrastehipotesis);
                                             lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesiscasoa2);
                                             lienzo.textView144.setText("Donde, en éste caso se hace necesario calcular el límite inferior para el área de no rechazo:");

                                             lienzo.imageView70.setImageResource(R.drawable.snmenosuno);
                                             lienzo.imageView77.setImageResource(R.drawable.teoremacasoapruebashipdesvestandardescononcidaliminf);

                                             lienzo.textViewNormalEstandar.setText("Cómo podemos percatarnos en la expresión dada para el límite inferior de la región de rechazo, es necesario buscar el valor de α en las tablas de la distribución t-student con n-1 grados de libertad.\n\nEn éste caso:");

                                             lienzo.imageView78.setImageResource(R.drawable.alfa);
                                             lienzo.imageView79.setImageResource(R.drawable.tenalfa);


                                             teorema = new PHIPDEDESC(desviacionEstandar,tamMuestra,mediaPoblacional,promedioMuestral,(float)significancia);
                                             limInf = teorema.Caso3();
                                             String conclusion = teorema.decision;
                                             lienzo.Resultado.setText("Con un límite inferior para la región de no rechazo de: " + limInf + "\nPodemos concluir que:\n\n" + conclusion);
                                             actualizarValoresEnTablas();

                                             lienzo.imageView83.setVisibility(View.VISIBLE);

                                             lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución t-student; se hace necesario sustituir los valores en la " +
                                                     "expresión para el límite inferior de la región de no rechazo. Donde el límite inferior es igual a: " + limInf);

                                             String aux;

                                             if(promedioMuestral<limInf){

                                                 aux = promedioMuestral + "<" + limInf;

                                             }else{
                                                 aux = promedioMuestral + "≥" + limInf;
                                             }

                                             lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado en el inicio del procedimiento tenemos la siguiente interpretación de la misma:\n\n" +
                                                     "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es menor al límite inferior de la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                     aux + "\n\nPor lo tanto:");

                                             lienzo.textView155.setText("Con un límite inferior para la región de no rechazo de: " + limInf + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + conclusion);

                                             lienzo.procedimiento.setVisibility(View.VISIBLE);


                                         }else{

                                             lienzo.imageView74.setImageResource(R.drawable.casoacontrastehipotesis);
                                             lienzo.imageView75.setImageResource(R.drawable.teoremacasoapruebashipdesvestandardescononcidaaproximacion);
                                             lienzo.textView144.setText("Donde, en éste caso se hace necesario calcular el límite inferior para el área de no rechazo.\nPara ello nos apoyaremos en la siguiente expresión:");

                                             lienzo.imageView70.setImageResource(R.drawable.snmenosuno);
                                             lienzo.imageView77.setImageResource(R.drawable.teoremacasoapruebashipdesvestandardescononcidaaproximacionliminf);
                                             lienzo.imageView78.setImageResource(R.drawable.alfa);
                                             lienzo.imageView79.setImageResource(R.drawable.zetaalfa);

                                             teoremaAprox = new PruebaHipotesisDVconocida();
                                             String conclusion = teoremaAprox.Aproxcasoa((float)significancia,tamMuestra,desviacionEstandar,promedioMuestral,mediaPoblacional);
                                             limInf = teoremaAprox.getLim_inf();

                                             lienzo.Resultado.setText("Con un límite inferior para la región de no rechazo de: " + limInf + "\nUn tamaño muestral de:" + tamMuestra + "\n\nPodemos concluir que:\n\n" + conclusion);
                                             actualizarValoresEnTablasAproximacion();


                                             lienzo.textView140.setText("Cómo el tamaño de muestra es mayor que 101 entonces podemos usar el siguiente teorema para aproximar un resultado por la distribución normal:");

                                             lienzo.imageView83.setVisibility(View.GONE);

                                             lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en la " +
                                                     "expresión para el límite inferior de la región de no rechazo. Donde el límite inferior es igual a: " + limInf);

                                             String aux;

                                             if(promedioMuestral<limInf){

                                                 aux = promedioMuestral + "<" + limInf;

                                             }else{
                                                 aux = promedioMuestral + "≥" + limInf;
                                             }

                                             lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado en el inicio del procedimiento tenemos la siguiente interpretación de la misma:\n\n" +
                                                     "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es menor al límite inferior de la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                     aux + "\n\nPor lo tanto:");

                                             lienzo.textView155.setText("Con un límite inferior para la región de no rechazo de: " + limInf + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + conclusion);

                                             lienzo.procedimiento.setVisibility(View.VISIBLE);

                                         }

                                     }catch (NumberFormatException numberFormatException){
                                         Toast.makeText(context, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
                                         lienzo.procedimiento.setVisibility(View.GONE);
                                         lienzo.imageView83.setVisibility(View.GONE);
                                     }
                                 }
                             });


                 }
             });


             lienzo.textView152.setText("Una vez encontrado el valor en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en la expresión presentada anteriormente para la región de rechazo. Donde:");



         }else if(caso.equals("Caso b")){


             lienzo.imageView74.setImageResource(R.drawable.casobcontrastehipotesis);
             lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesiscasob2);
             lienzo.textView144.setText("Donde, en éste caso se hace necesario calcular el límite superior para el área de no rechazo:");
             lienzo.imageView77.setImageResource(R.drawable.limsupregionnorechazocasob2);
             lienzo.imageView78.setImageResource(R.drawable.alfa);
             lienzo.imageView79.setImageResource(R.drawable.tenalfa);
             lienzo.layoutLimiteInferior.setVisibility(View.VISIBLE);

             lienzo.imageView84.setImageResource(R.drawable.casobcontrastehipotesis);



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
                     textView1.setText(" = " + promedioMuestral);
                     textView2.setText("Y la región de no rechazo está dada por el intervalo:\n\n[-∞, " + limSup + "]");

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

                                         mediaPoblacional = convertirStringADouble(lienzo.editTextNumberDecimal10.getText().toString());
                                         promedioMuestral = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                                         desviacionEstandar = convertirStringADouble(lienzo.editTextNumberDecimal11.getText().toString());
                                         significancia = convertirStringADouble(lienzo.editTextNumberSigned4.getText().toString());
                                         tamMuestra = convertirStringAInt(lienzo.editTextNumber4.getText().toString());

                                         lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.GONE);

                                         if(tamMuestra<= 101){

                                             lienzo.imageView74.setImageResource(R.drawable.casobcontrastehipotesis);
                                             lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesiscasob2);
                                             lienzo.textView144.setText("Donde, en éste caso se hace necesario calcular el límite superior para el área de no rechazo.\nPara ello nos apoyaremos en la siguiente expresión");

                                             lienzo.imageView70.setImageResource(R.drawable.snmenosuno);
                                             lienzo.imageView77.setImageResource(R.drawable.teoremacasobpruebashipdesvestandardescononcidalimsup);
                                             lienzo.imageView78.setImageResource(R.drawable.alfa);
                                             lienzo.imageView79.setImageResource(R.drawable.tenalfa);

                                             lienzo.textViewNormalEstandar.setText("Cómo podemos darnos cuenta se hace necesario buscar el valor de α en las tablas de la distribución t-student con n-1 = " + (tamMuestra - 1) + " grados de libertad. En éste caso:");


                                             teorema = new PHIPDEDESC(desviacionEstandar,tamMuestra,mediaPoblacional,promedioMuestral,(float)significancia);
                                             limSup = teorema.Caso1();
                                             String conclusion = teorema.decision;
                                             lienzo.Resultado.setText("Con un límite superior para la región de no rechazo de: " + limSup + "\nPodemos concluir que:\n\n" + conclusion);
                                             actualizarValoresEnTablas();

                                             lienzo.imageView83.setVisibility(View.VISIBLE);

                                             lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución t-student; se hace necesario sustituir los valores en la " +
                                                     "expresión para el límite superior de la región de no rechazo. Donde el límite superior es igual a: " + limSup);

                                             String aux;

                                             if(promedioMuestral<limSup){

                                                 aux = promedioMuestral + "<" + limSup;

                                             }else{
                                                 aux = promedioMuestral + "≥" + limSup;
                                             }

                                             lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado al inicio del procedimiento, podemos tomar en cuenta la siguiente interpretación de la misma:\n\n" +
                                                     "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es mayor al límite superior de la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                     aux);

                                             lienzo.textView155.setText("Con un límite superior para la región de no rechazo de: " + limSup + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + conclusion);

                                             lienzo.procedimiento.setVisibility(View.VISIBLE);


                                         }else{

                                             lienzo.imageView74.setImageResource(R.drawable.casoacontrastehipotesis);
                                             lienzo.imageView75.setImageResource(R.drawable.teoremacasobpruebashipdesvestandardescononcidaaproximacion);
                                             lienzo.textView144.setText("Donde, en éste caso se hace necesario calcular el límite superior para el área de no rechazo.\nPara ello nos apoyaremos en la siguiente expresión:");

                                             lienzo.imageView70.setImageResource(R.drawable.snmenosuno);
                                             lienzo.imageView77.setImageResource(R.drawable.teoremacasobpruebashipdesvestandardescononcidaaproximacionlimsup);
                                             lienzo.imageView78.setImageResource(R.drawable.alfa);
                                             lienzo.imageView79.setImageResource(R.drawable.zetaalfa);

                                             teoremaAprox = new PruebaHipotesisDVconocida();
                                             String conclusion = teoremaAprox.Aproxcasob((float)significancia,tamMuestra,desviacionEstandar,promedioMuestral,mediaPoblacional);
                                             limSup = teoremaAprox.getLim_sup();

                                             lienzo.Resultado.setText("Con un límite superior para la región de no rechazo de: " + limSup + "\nUn tamaño muestral de: " + tamMuestra + "\n\nPodemos concluir que:\n\n" + conclusion);
                                             actualizarValoresEnTablasAproximacion();


                                             lienzo.textView140.setText("Cómo el tamaño de muestra es mayor que 101 entonces podemos usar el siguiente teorema para aproximar un resultado por la distribución normal:");

                                             lienzo.imageView83.setVisibility(View.GONE);

                                             lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en la " +
                                                     "expresión para el límite suoerior de la región de no rechazo. Donde el límite superior es igual a: " + limSup);

                                             String aux;

                                             if(promedioMuestral<limSup){

                                                 aux = promedioMuestral + "<" + limSup;

                                             }else{
                                                 aux = promedioMuestral + "≥" + limSup;
                                             }

                                             lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado en el inicio del procedimiento tenemos la siguiente interpretación de la misma:\n\n" +
                                                     "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es mayor al límite superior de la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                     aux + "\n\nPor lo tanto:");

                                             lienzo.textView155.setText("Con un límite superior para la región de no rechazo de: " + limSup + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + conclusion);

                                             lienzo.procedimiento.setVisibility(View.VISIBLE);

                                         }

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

             lienzo.layoutDosLimites.setVisibility(View.VISIBLE);

             lienzo.imageView78.setImageResource(R.drawable.alfamedios);
             lienzo.imageView79.setImageResource(R.drawable.zetaalfamedios);


             lienzo.imageView84.setImageResource(R.drawable.casoccontrastehipotesis);


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
                     textView1.setText(" = " + promedioMuestral);
                     textView2.setText("Y la región de no rechazo está dada por el intervalo:\n\n[" + limInf + "," + limSup+"]");

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

                                         mediaPoblacional = convertirStringADouble(lienzo.editTextNumberDecimal10.getText().toString());
                                         promedioMuestral = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                                         desviacionEstandar = convertirStringADouble(lienzo.editTextNumberDecimal11.getText().toString());
                                         significancia = convertirStringADouble(lienzo.editTextNumberSigned4.getText().toString());
                                         tamMuestra = convertirStringAInt(lienzo.editTextNumber4.getText().toString());

                                         lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.GONE);

                                         if(tamMuestra<= 101){

                                             lienzo.imageView75.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcida);


                                             lienzo.imageView70.setImageResource(R.drawable.snmenosuno);
                                             lienzo.imageView81.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcidaliminf);
                                             lienzo.imageView82.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcidalimsup);
                                             lienzo.imageView78.setImageResource(R.drawable.alfa);
                                             lienzo.imageView79.setImageResource(R.drawable.tealfamedios);


                                             teorema = new PHIPDEDESC(desviacionEstandar,tamMuestra,mediaPoblacional,promedioMuestral,(float)significancia);
                                             String intervalo = teorema.caso2();
                                             limInf = teorema.getLimInf();
                                             limSup = teorema.getLimSup();
                                             lienzo.Resultado.setText("Con un intervalo para la región de rechazo de: " + intervalo + "\nPodemos concluir que:\n\n" + teorema.decision);
                                             actualizarValoresEnTablas();

                                             lienzo.imageView83.setVisibility(View.VISIBLE);

                                             lienzo.textViewNormalEstandar.setText("Cómo podemos observar se hace necesario buscar el valor de α en las tablas de la distribución t-student con n-1 grados de libertad." +
                                                     "En éste caso:");

                                             lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución t-student; se hace necesario sustituir los valores en las " +
                                                     "expresiones correspondientes para los límites de la región de no rechazo.\nEn éste caso el intervalo calculado es:  " + intervalo);

                                             String aux;

                                             if(promedioMuestral>=limInf && promedioMuestral<= limSup){

                                                 if(promedioMuestral == limInf){
                                                     aux = promedioMuestral +" = " + limInf;
                                                 }else if(promedioMuestral == limSup){
                                                     aux = promedioMuestral + " = " + limSup;
                                                 }else{
                                                     aux = limInf + " < " + promedioMuestral + " < " + limSup;
                                                 }

                                             }else{
                                                 if(promedioMuestral<limInf){
                                                     aux = promedioMuestral+" < " + limInf;
                                                 }else{
                                                     aux = promedioMuestral + " > " + limSup;
                                                 }
                                             }

                                             lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado al inicio del procedimiento, podemos tomar en cuenta la siguiente interpretación de la misma:\n\n" +
                                                     "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es menor que el límite inferior de la región de no rechazo ó  mayor que el límite superior de la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                     aux + "\n\nPor lo tanto:");

                                             lienzo.textView155.setText("Con un intervalo para la región de no rechazo de: " + intervalo + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + teorema.decision);

                                             lienzo.procedimiento.setVisibility(View.VISIBLE);


                                         }else{

                                             lienzo.imageView75.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcida);


                                             lienzo.imageView70.setImageResource(R.drawable.snmenosuno);
                                             lienzo.imageView81.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcidaliminf);
                                             lienzo.imageView82.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcidalimsup);
                                             lienzo.imageView78.setImageResource(R.drawable.alfa);
                                             lienzo.imageView79.setImageResource(R.drawable.tealfamedios);


                                             teoremaAprox = new PruebaHipotesisDVconocida();
                                             String intervalo = teoremaAprox.Aproxcasoc((float)significancia,tamMuestra,desviacionEstandar,promedioMuestral,mediaPoblacional);
                                             limInf = teoremaAprox.getLim_inf();
                                             limSup = teoremaAprox.getLim_sup();
                                             lienzo.Resultado.setText("Un resultado aproximado por la distribución normal sería:\n\nCon un intervalo para la región de aceptación: " + intervalo + "\nPodemos concluir que:\n\n" + teoremaAprox.decision);
                                             actualizarValoresEnTablasAproximacion();

                                             lienzo.imageView83.setVisibility(View.GONE);

                                             lienzo.textViewNormalEstandar.setText("Cómo podemos observar se hace necesario buscar el valor de α en las tablas de la distribución normal estándar." +
                                                     "En éste caso:");

                                             lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en las " +
                                                     "expresiones correspondientes para los límites de la región de no rechazo.\nEn éste caso la región de aceptación encontrada es:  " + intervalo);

                                             String aux;
                                             limInf = teoremaAprox.getLim_inf();
                                             limSup = teoremaAprox.getLim_sup();

                                             if(promedioMuestral>=limInf && promedioMuestral<= limSup){

                                                 if(promedioMuestral == limInf){
                                                     aux = promedioMuestral +" = " + limInf;
                                                 }else if(promedioMuestral == limSup){
                                                     aux = promedioMuestral + " = " + limSup;
                                                 }else{
                                                     aux = limInf + " < " + promedioMuestral + " < " + limSup;
                                                 }

                                             }else{
                                                 if(promedioMuestral<limInf){
                                                     aux = promedioMuestral+" < " + limInf;
                                                 }else{
                                                     aux = promedioMuestral + " > " + limSup;
                                                 }
                                             }

                                             lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado al inicio del procedimiento, podemos tomar en cuenta la siguiente interpretación de la misma:\n\n" +
                                                     "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es menor que el límite inferior de la región de aceptación ó  mayor que el límite superior de la región de aceptación.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                     aux + "\n\nPor lo tanto:");

                                             lienzo.textView155.setText("Con un intervalo para la región de no rechazo de: " + intervalo + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + teoremaAprox.decision);

                                             lienzo.procedimiento.setVisibility(View.VISIBLE);

                                         }

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

             lienzo.textView152.setText("Una vez encontrado el valor en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en las expresións presentadas anteriormente para la región de rechazo. Donde:");


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
                     textView1.setText(" = " + promedioMuestral);
                     textView2.setText("Y la región de no rechazo está dada por el intervalo:\n\n[" + limInf + "," + limSup+"]");

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

                                         mediaPoblacional = convertirStringADouble(lienzo.editTextNumberDecimal10.getText().toString());
                                         promedioMuestral = convertirStringADouble(lienzo.editTextNumberSigned2.getText().toString());
                                         desviacionEstandar = convertirStringADouble(lienzo.editTextNumberDecimal11.getText().toString());
                                         significancia = convertirStringADouble(lienzo.editTextNumberSigned4.getText().toString());
                                         tamMuestra = convertirStringAInt(lienzo.editTextNumber4.getText().toString());
                                         mediaPoblacional1 = convertirStringADouble(lienzo.editTextNumberDecimal12.getText().toString());

                                         lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.GONE);

                                         if(tamMuestra<= 101){

                                             lienzo.imageView75.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcida);


                                             lienzo.imageView70.setImageResource(R.drawable.snmenosuno);
                                             lienzo.imageView81.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcidaliminf);
                                             lienzo.imageView82.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcidalimsup);
                                             lienzo.imageView78.setImageResource(R.drawable.alfa);
                                             lienzo.imageView79.setImageResource(R.drawable.tealfamedios);


                                             teorema = new PHIPDEDESC(desviacionEstandar,tamMuestra,mediaPoblacional,promedioMuestral,(float)significancia);
                                             String intervalo = teorema.caso4(mediaPoblacional1);
                                             lienzo.Resultado.setText("Con un intervalo para la región de rechazo de: " + intervalo + "\nPodemos concluir que:\n\n" + teorema.decision);
                                             actualizarValoresEnTablasCasoC();

                                             lienzo.imageView83.setVisibility(View.VISIBLE);

                                             lienzo.textViewNormalEstandar.setText("Cómo podemos observar se hace necesario buscar el valor de α en las tablas de la distribución t-student con n-1 grados de libertad." +
                                                     "En éste caso:");

                                             lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución t-student; se hace necesario sustituir los valores en las " +
                                                     "expresiones correspondientes para los límites de la región de no rechazo.\nEn éste caso el intervalo calculado es:  " + intervalo);

                                             String aux;

                                             if(promedioMuestral>=limInf && promedioMuestral<= limSup){

                                                 if(promedioMuestral == limInf){
                                                     aux = promedioMuestral +" = " + limInf;
                                                 }else if(promedioMuestral == limSup){
                                                     aux = promedioMuestral + " = " + limSup;
                                                 }else{
                                                     aux = limInf + " < " + promedioMuestral + " < " + limSup;
                                                 }

                                             }else{
                                                 if(promedioMuestral<limInf){
                                                     aux = promedioMuestral+" < " + limInf;
                                                 }else{
                                                     aux = promedioMuestral + " > " + limSup;
                                                 }
                                             }

                                             lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado al inicio del procedimiento, podemos tomar en cuenta la siguiente interpretación de la misma:\n\n" +
                                                     "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es menor que el límite inferior de la región de no rechazo ó  mayor que el límite superior de la región de no rechazo.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                     aux + "\n\nPor lo tanto:");

                                             lienzo.textView155.setText("Con un intervalo para la región de no rechazo de: " + intervalo + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + teorema.decision);

                                             lienzo.procedimiento.setVisibility(View.VISIBLE);


                                         }else{

                                             lienzo.imageView75.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcida);


                                             lienzo.imageView70.setImageResource(R.drawable.snmenosuno);
                                             lienzo.imageView81.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcidaliminf);
                                             lienzo.imageView82.setImageResource(R.drawable.teoremacasocpruebashipdesvestandardescononcidalimsup);
                                             lienzo.imageView78.setImageResource(R.drawable.alfa);
                                             lienzo.imageView79.setImageResource(R.drawable.tealfamedios);


                                             teoremaAprox = new PruebaHipotesisDVconocida();
                                             String intervalo = teoremaAprox.Aproxcasod((float)significancia,tamMuestra,desviacionEstandar,promedioMuestral,mediaPoblacional,mediaPoblacional1);

                                             lienzo.Resultado.setText("Un resultado aproximado por la distribución normal sería:\n\nCon un intervalo para la región de aceptación: " + intervalo + "\nPodemos concluir que:\n\n" + teoremaAprox.decision);
                                             actualizarValoresEnTablasCasoCAprox();

                                             lienzo.imageView83.setVisibility(View.GONE);

                                             lienzo.textViewNormalEstandar.setText("Cómo podemos observar se hace necesario buscar el valor de α en las tablas de la distribución normal estándar." +
                                                     "En éste caso:");

                                             lienzo.textView152.setText("Una vez encontrado el valor de la significancia en las tablas de la distribución normal estándar; se hace necesario sustituir los valores en las " +
                                                     "expresiones correspondientes para los límites de la región de no rechazo.\nEn éste caso la región de aceptación encontrada es:  " + intervalo);

                                             String aux;
                                             limInf = teoremaAprox.getLim_inf();
                                             limSup = teoremaAprox.getLim_sup();

                                             if(promedioMuestral>=limInf && promedioMuestral<= limSup){

                                                 if(promedioMuestral == limInf){
                                                     aux = promedioMuestral +" = " + limInf;
                                                 }else if(promedioMuestral == limSup){
                                                     aux = promedioMuestral + " = " + limSup;
                                                 }else{
                                                     aux = limInf + " < " + promedioMuestral + " < " + limSup;
                                                 }

                                             }else{
                                                 if(promedioMuestral<limInf){
                                                     aux = promedioMuestral+" < " + limInf;
                                                 }else{
                                                     aux = promedioMuestral + " > " + limSup;
                                                 }
                                             }

                                             lienzo.textView153.setText("Analizando la regla de decisión contenida en el teorema presentado al inicio del procedimiento, podemos tomar en cuenta la siguiente interpretación de la misma:\n\n" +
                                                     "Se rechaza la hipótesis nula (H0) si el parámetro promedio poblacional es menor que el límite inferior de la región de aceptación ó  mayor que el límite superior de la región de aceptación.\nHaciendo la comparación podemos percatarnos que:\n\n" +
                                                     aux + "\n\nPor lo tanto:");

                                             lienzo.textView155.setText("Con un intervalo para la región de no rechazo de: " + intervalo + ", y un nivel de significancia de :" + significancia +"\nPodemos concluir que:\n\n" + teoremaAprox.decision);

                                             lienzo.procedimiento.setVisibility(View.VISIBLE);

                                         }

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

     public void actualizarValoresEnTablas() {

        lienzo.tableRowOpcional1.setVisibility(View.GONE);

        lienzo.textView133.setText(" = " + mediaPoblacional);
        lienzo.textView134.setText(" = " + promedioMuestral);
        lienzo.textView135.setText(" = " + desviacionEstandar);
        lienzo.textView136.setText(" = " + significancia);
        lienzo.textView137.setText(" = " + tamMuestra);
        lienzo.textView143.setText(" = " + significancia);
        lienzo.textView145.setText(" = " + teorema.getValTablaT());


     }

     public void actualizarValoresEnTablasAproximacion(){

        lienzo.tableRowOpcional1.setVisibility(View.GONE);

        lienzo.textView133.setText(" = " + mediaPoblacional);
        lienzo.textView134.setText(" = " + promedioMuestral);
        lienzo.textView135.setText(" = " + desviacionEstandar);
        lienzo.textView136.setText(" = " + significancia);
        lienzo.textView137.setText(" = " + tamMuestra);
        lienzo.textView143.setText(" = " + significancia);
        lienzo.textView145.setText(" = " + teoremaAprox.getValTablas());

     }

     public void actualizarValoresEnTablasCasoC(){

        lienzo.tableRowOpcional1.setVisibility(View.VISIBLE);

        lienzo.textView133.setText(" = " + mediaPoblacional);
        lienzo.textView134.setText(" = " + promedioMuestral);
        lienzo.textView135.setText(" = " + desviacionEstandar);
        lienzo.textView136.setText(" = " + significancia);
        lienzo.textView137.setText(" = " + tamMuestra);
        lienzo.textView138.setText(" = " + mediaPoblacional1);
        lienzo.textView143.setText(" = " + significancia);
        lienzo.textView145.setText(" = " + teorema.getValTablaT());

     }


     public void actualizarValoresEnTablasCasoCAprox(){

         lienzo.tableRowOpcional1.setVisibility(View.VISIBLE);

         lienzo.textView133.setText(" = " + mediaPoblacional);
         lienzo.textView134.setText(" = " + promedioMuestral);
         lienzo.textView135.setText(" = " + desviacionEstandar);
         lienzo.textView136.setText(" = " + significancia);
         lienzo.textView137.setText(" = " + tamMuestra);
         lienzo.textView138.setText(" = " + mediaPoblacional1);
         lienzo.textView143.setText(" = " + significancia);
         lienzo.textView145.setText(" = " + teoremaAprox.getValTablas());
     }


 }
