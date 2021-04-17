 package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebasDeHipotesisBinding;

public class VistaPruebasDeHipotesisVarianza extends AppCompatActivity implements conversiones,conclusionPotenciaPrueba{

    private ActivityPruebasDeHipotesisBinding lienzo;
    private String key;
    private double varianzaPoblacional0,varianzaPoblacional1,significancia,varianzaMuestral,nuevaVarianzaPoblacional,potenciaPrueba;
    private double estadisticoPrueba1, estadisticoPrueba2;
    private double potenciaAux1, potenciaAux2;
    private double valEnTablas1,valEnTablas2;
    private double limInf,limSup;
    private int tamMuestra;
    private String intervalo;

    private PHIPVARIANZA teorema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lienzo = ActivityPruebasDeHipotesisBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);

        key = getIntent().getStringExtra("key");
        setInputs(key);
        setContrasteHipotesis(key);

        lienzo.botoncalcular.setOnClickListener(onClickListenerBotonCalcular(key));
        lienzo.delete.setOnClickListener(onClickListenerBotonBorrar());
        lienzo.editTextNumberDecimal16.addTextChangedListener(textWatcherSignificancia());
        lienzo.editTextNumber5.addTextChangedListener(textWatcherTamMuestra());
        lienzo.imageView83.setOnClickListener(onClickListenerBotonCalcularPotenciaPrueba());

    }

    private View.OnClickListener onClickListenerBotonCalcular(String caso){
        return new View.OnClickListener() {
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

                                switch(caso){

                                    case "Caso a":
                                    case "Caso b":

                                        if(faltaUnDato()){

                                            Toast.makeText(VistaPruebasDeHipotesisVarianza.this, "Por favor ingresa todos los datos requeridos", Toast.LENGTH_SHORT).show();

                                        }else{

                                           convertirValoresEntrantesCasosABC(key);
                                           setResultado(key);
                                           setTeoremas(key);
                                           setContrasteHipotesis(key);
                                           actualizarValoresEnTablas(key);
                                           setPaso1(key);
                                           setPaso2(key);
                                           setPaso3(key);
                                           setConclusion(key);

                                        }

                                    break;

                                    case "Caso c":

                                        if(faltaUnDato()){

                                            Toast.makeText(VistaPruebasDeHipotesisVarianza.this, "Por favor ingresa todos los datos requeridos", Toast.LENGTH_SHORT).show();

                                        }else{

                                            convertirValoresEntrantesCasosABC(key);
                                            setResultado(key);
                                            setTeoremas(key);
                                            setContrasteHipotesis(key);
                                            actualizarValoresEnTablas(key);
                                            setPaso1(key);
                                            setPaso2(key);
                                            setPaso3(key);
                                            setConclusion(key);

                                        }

                                        break;

                                    case "Caso d":

                                        if(faltaUnDatoCasod()){

                                            Toast.makeText(VistaPruebasDeHipotesisVarianza.this, "Por favor ingresa todos los datos requeridos", Toast.LENGTH_SHORT).show();

                                        }else{

                                            convertirValoresEntrantesCasoD();
                                            setResultado(key);
                                            setTeoremas(key);
                                            setContrasteHipotesis(key);
                                            actualizarValoresEnTablas(key);
                                            setPaso1(key);
                                            setPaso2(key);
                                            setPaso3(key);
                                            setConclusion(key);
                                            lienzo.imageView83.setVisibility(View.GONE);

                                        }

                                    break;

                                    default:
                                        Toast.makeText(VistaPruebasDeHipotesisVarianza.this, "Caso inexistente", Toast.LENGTH_SHORT).show();
                                    break;
                                }

                            }
                        });
            }
        };
    }

    private View.OnClickListener onClickListenerBotonBorrar(){
        return new View.OnClickListener() {
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
                                lienzo.Resultado.setText("El resultado es:");
                                lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.GONE);
                                lienzo.imageView83.setVisibility(View.GONE);
                                lienzo.editTextNumberDecimal14.setText("");
                                lienzo.editTextNumberDecimal15.setText("");
                                lienzo.editTextNumberDecimal16.setText("");
                                lienzo.editTextNumber5.setText("");
                                lienzo.editTextNumber6.setText("");

                            }
                        });
            }
        };
    }

    private View.OnClickListener onClickListenerBotonCalcularPotenciaPrueba(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(VistaPruebasDeHipotesisVarianza.this);
                View view1 = layoutInflater.inflate(R.layout.ingresar_nueva_mediapob,null);
                ImageView imagen = view1.findViewById(R.id.imageView88);
                imagen.setImageResource(R.drawable.sigmacuadrada0);
                EditText valorNuevaVarianzaPob = view1.findViewById(R.id.editTextNumberDecimal13);

                AlertDialog.Builder builder = new AlertDialog.Builder(VistaPruebasDeHipotesisVarianza.this);
                builder.setTitle("Potencia de la prueba")
                        .setMessage("Por favor ingresa un nuevo valor esperado para la varianza poblacional")
                        .setCancelable(false)
                        .setPositiveButton("Calcular potencia", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(valorNuevaVarianzaPob.getText().toString().isEmpty()){

                                    Toast.makeText(VistaPruebasDeHipotesisVarianza.this, "Por favor ingresa un nuevo valor para la varianza poblacional", Toast.LENGTH_SHORT).show();

                                }else{

                                    nuevaVarianzaPoblacional = convertirStringADouble(valorNuevaVarianzaPob.getText().toString());

                                    calcularPotenciaDeLaPrueba(key);
                                    setTeoremasPotenciaPrueba(key);
                                    setPaso1PotenciaPrueba(key);
                                    setPaso2PotenciaPrueba(key);
                                    actualizarValoresEnTablasPotenciaPrueba(key);


                                }

                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog1 = builder.create();
                alertDialog1.setView(view1);
                alertDialog1.show();

            }
        };
    }



    private TextWatcher textWatcherSignificancia(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(lienzo.editTextNumberDecimal16.getText().toString().isEmpty()){

                }else{
                    double numAux = convertirStringADouble(lienzo.editTextNumberDecimal16.getText().toString());

                    if(numAux >= 1){

                        Toast.makeText(VistaPruebasDeHipotesisVarianza.this, "Por favor ingresa el valor de la significancia en decimales", Toast.LENGTH_SHORT).show();
                        double otroNumAux = redondeoDecimales((numAux/100),5);

                        lienzo.editTextNumberDecimal16.setText(String.valueOf(otroNumAux));

                    }
                }

            }
        };
    }

    private TextWatcher textWatcherTamMuestra(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(lienzo.editTextNumber5.getText().toString().isEmpty()){
                    lienzo.botoncalcular.setVisibility(View.VISIBLE);
                }else{
                    int tamMuestraAux = convertirStringAInt(lienzo.editTextNumber5.getText().toString());
                    if(tamMuestraAux>121){
                        Toast.makeText(VistaPruebasDeHipotesisVarianza.this, "Imposible calcular la prueba de hipótesis con un tamaño de muestra mayor que 121", Toast.LENGTH_SHORT).show();
                        lienzo.botoncalcular.setVisibility(View.GONE);
                    }else{
                        lienzo.botoncalcular.setVisibility(View.VISIBLE);
                    }
                }
            }
        };
    }

    private void setInputs(String caso){
        lienzo.gridLayoutMedia.setVisibility(View.GONE);
        lienzo.gridLayoutDatosPruebaHipotesisVarianza.setVisibility(View.VISIBLE);

        if(caso.equals("Caso d")){

            lienzo.cardViewOpcionalVarianza1.setVisibility(View.VISIBLE);
            lienzo.cardViewOpcionalVarianza2.setVisibility(View.VISIBLE);



            lienzo.imageView96.setImageResource(R.drawable.sigmacuadrada1);
            lienzo.textView174.setText("Segundo valor esperado para la varianza poblacional");

            lienzo.imageView100.setImageResource(R.drawable.sigmacuadrada0);
            lienzo.textView178.setText("Primer valor esperado para la varianza poblacional");

        }

    }

    private void setTeoremas(String caso){

        lienzo.tbR3.setVisibility(View.GONE);

        lienzo.imageView63.setImageResource(R.drawable.sigmacuadrada0);
        lienzo.imageView69.setImageResource(R.drawable.scuadradanmenosuno);
        lienzo.imageView83.setVisibility(View.VISIBLE);

        setContrasteHipotesis(caso);

        switch (caso){

            case "Caso a":

                lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesisvarianzacasoa);

                lienzo.imageView77.setImageResource(R.drawable.teoremapruebashipotesisvarianzaliminfcasoa);

                lienzo.imageView78.setImageResource(R.drawable.unomenosalfa);

                lienzo.imageView79.setImageResource(R.drawable.chienunomenosalfa);



                break;

            case "Caso b":

                lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesisvarianzacasob);

                lienzo.imageView77.setImageResource(R.drawable.teoremapruebashipotesisvarianzalimsupcasob);

                lienzo.imageView78.setImageResource(R.drawable.alfa);

                lienzo.imageView79.setImageResource(R.drawable.chienalfa);

                break;

            case "Caso c":

                lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesisvarianzacasoc);

                lienzo.layoutLimiteInferior.setVisibility(View.GONE);

                lienzo.layoutDosLimites.setVisibility(View.VISIBLE);

                lienzo.imageView81.setImageResource(R.drawable.teoremapruebashipotesisvarianzaliminfcasoc);

                lienzo.imageView82.setImageResource(R.drawable.teoremapruebashipotesisvarianzalimsupcasoc);

                lienzo.tbRow0.setVisibility(View.VISIBLE);
                lienzo.tbRow1.setVisibility(View.VISIBLE);

                lienzo.imageView102.setImageResource(R.drawable.unomenosalfamedios);
                lienzo.imageView101.setImageResource(R.drawable.chienunomenosalfamedios);

                lienzo.imageView78.setImageResource(R.drawable.alfamedios);
                lienzo.imageView79.setImageResource(R.drawable.chienalfamedios);



                break;

            case "Caso d":

                lienzo.tableRowOpcional1.setVisibility(View.VISIBLE);
                lienzo.imageView73.setImageResource(R.drawable.sigmacuadrada1);

                lienzo.imageView75.setImageResource(R.drawable.teoremapruebashipotesisvarianzacasod);

                lienzo.layoutLimiteInferior.setVisibility(View.GONE);

                lienzo.layoutDosLimites.setVisibility(View.VISIBLE);

                lienzo.imageView81.setImageResource(R.drawable.teoremapruebashipotesisvarianzaliminfcasod);

                lienzo.imageView82.setImageResource(R.drawable.teoremapruebashipotesisvarianzalimsupcasod);

                lienzo.tbRow0.setVisibility(View.VISIBLE);
                lienzo.tbRow1.setVisibility(View.VISIBLE);

                lienzo.imageView102.setImageResource(R.drawable.unomenosalfamedios);
                lienzo.imageView101.setImageResource(R.drawable.chienunomenosalfamedios);

                lienzo.imageView78.setImageResource(R.drawable.alfamedios);
                lienzo.imageView79.setImageResource(R.drawable.chienalfamedios);

                break;


            default:

                Toast.makeText(this, "Caso desconocido", Toast.LENGTH_SHORT).show();

                break;

        }
    }

    private void actualizarValoresEnTablas(String caso){

        lienzo.textView133.setText("= " + varianzaPoblacional0);
        lienzo.textView134.setText("= " + varianzaMuestral);
        lienzo.textView136.setText("= " + significancia);
        lienzo.textView137.setText("= " + tamMuestra);

        switch (caso){

            case "Caso a":

                lienzo.textView143.setText("= " + redondeoDecimales((1-significancia),5));
                lienzo.textView145.setText("= " + teorema.getValTablas());


            case "Caso b":

                lienzo.textView143.setText("= " + significancia);
                lienzo.textView145.setText("= " + teorema.getValTablas());

                break;
            case "Caso c":
            case "Caso d":

                lienzo.tbRow0.setVisibility(View.VISIBLE);
                lienzo.tbRow1.setVisibility(View.VISIBLE);

                lienzo.textView180.setText("= " + redondeoDecimales((1-(significancia/2)),5));
                lienzo.textView179.setText("= " + teorema.getValTablas());

                lienzo.textView143.setText("= " + redondeoDecimales((significancia/2),5));
                lienzo.textView145.setText("= " + teorema.getValTablas1());


                break;

            default:

                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    private void setResultado(String caso){

        String resultado1 = "Con una significancia de " + significancia + "\n";
        String resultado2;
        switch (caso){

            case "Caso a":

                resultado2 = "Y un límite inferior para la región de aceptación: " + limInf + "\n\nSe puede concluir que:\n\n" + teorema.decision;

                break;

            case "Caso b":

                resultado2 = "Y un límite superior para la región de aceptación:" + limSup + "\nSe puede concluir que\n" + teorema.decision;

                break;

            case "Caso c":
            case "Caso d":

                resultado2 = "Y un intervalo para la región de aceptación: " + intervalo + "\nSe puede concluir que\n" + teorema.decision;

                break;

            default:

                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();
                resultado2 = "";

                break;

        }
        lienzo.Resultado.setText(resultado1 + resultado2);
        lienzo.procedimiento.setVisibility(View.VISIBLE);
        lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.GONE);

    }


    private void setPaso1(String caso){

        switch (caso){

            case "Caso a":

                lienzo.textViewNormalEstandar.setText("Cómo podemos darnos cuenta se hace necesario buscar el valor de 1-α en las tablas de la distribución ji cuadrada con n-1 = " + (tamMuestra-1) +" grados de libertad. En éste caso:");

                break;

            case "Caso b":

                lienzo.textView144.setText("Donde, en este caso solo se hace necesario calcular el límite superior para el área de no rechazo: ");
                lienzo.textViewNormalEstandar.setText("Cómo podemos darnos cuenta se hace necesario buscar el valor de 1-α/2 en las tablas de la distribución ji cuadrada con n-1 = " + (tamMuestra-1) +" grados de libertad. En éste caso:");

                break;

            case "Caso c":
            case "Caso d":

                lienzo.textViewNormalEstandar.setText("Cómo podemos darnos cuenta se hace necesario buscar los valores de 1-α/2 y de α/2 en las tablas de la distribución ji cuadrada con n-1 = " + (tamMuestra-1) +" grados de libertad. En éste caso:");

                break;

            default:

                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    private void setPaso2(String caso){

        switch(caso){

            case "Caso a":

                lienzo.textView152.setText("Una vez encontrado el valor de 1-α en las tablas de la distribución ji cuadrada; se hace necesario sustituir los valores en la expresión presentada anteriormente para el límite inferior de la región de no rechazo. \nEn éste caso: " + limInf);

                break;

            case "Caso b":

                lienzo.textView152.setText("Una vez encontrado el valor de α en las tablas de la distribución ji cuadrada; se hace necesario sustituir los valores en la expresión presentada anteriormente para el límite superior de la región de no rechazo. \nEn éste caso: " + limSup);

                break;

            case "Caso c":
            case "Caso d":

                lienzo.textView152.setText("Una vez encontrado los valores de 1-α/2 y α/2 en las tablas de la distribución ji cuadrada; se hace necesario sustituir los valores en las expresiónes correspondientes para el intervalo de la región de no rechazo. En éste caso:\nLímite inferior:  " + limInf
                +"\nLímite superior:" + limSup);

                break;
                
            default:

                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();

                break;

        }

    }

    private void setPaso3(String caso){

        String paso3_1 = "Analizando la regla de decisión contenida en el teorema presentado en el inicio del procedimiento obtenemos la siguiente interpretación de la misma:\n";
        String paso3_2;
        String aux;



        switch (caso){

            case "Caso a":

                if(varianzaMuestral<limInf){

                    aux = varianzaMuestral + "<" + limInf;

                }else if(varianzaMuestral == limInf){

                    aux = varianzaMuestral + "=" + limInf;

                }else {

                    aux =varianzaMuestral + ">" + limInf;

                }

                paso3_2 = "Se rechaza la hipótesis nula (H0) si el valor de la varianza muestral es menor que el límite inferior de la región de aceptación\n\nEn éste caso:\n" + aux;

                break;

            case "Caso b":

                if(varianzaMuestral<limSup){

                    aux = varianzaMuestral + "<" + limSup;

                }else if(varianzaMuestral == limSup){

                    aux = varianzaMuestral + "=" + limSup;

                }else {

                    aux = varianzaMuestral + ">" + limSup;

                }

                paso3_2 = "Se rechaza la hipótesis nula (H0) si el valor de la varianza muestral es mayor que el límite superior de la región de aceptación\n\nEn éste caso:\n" + aux;
                break;

            case "Caso c":
            case "Caso d":


                if(varianzaMuestral<limInf){
                    aux = varianzaMuestral +  "<" + limInf;
                }else if(varianzaMuestral>limSup){
                    aux= varianzaMuestral + "> " + limSup;
                }else {
                    aux = limInf + "≤" + varianzaMuestral + "≤" + limSup;
                }

                paso3_2 = "Se rechaza la hipótesis nula (H0) si el valor de la varianza muestral no está dentro del intervalo de la región de aceptación.\n\nEn éste caso:" + aux;
                break;

            default:

                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();
                paso3_2 = "";

                break;

        }

        lienzo.textView153.setText(paso3_1+paso3_2);

    }

    private void setConclusion(String caso){

        String conclusion2;

        switch (caso){

            case "Caso a":

                conclusion2 = "Con un límite inferior para la región de no rechazo de: \n" + limInf+"\n";

                break;

            case "Caso b":

                conclusion2 = "Con un límite superior para la región de no rechazo de: \n" + limSup+"\n";

                break;

            case "Caso c":
            case "Caso d":

                conclusion2 = "Con un límite intervalo para la región de no rechazo: \n" + intervalo+"\n";

                break;

            default:

                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();
                conclusion2 = "";
                break;

        }

        lienzo.textView155.setText(conclusion2 + "\n\nPodemos concluir que:\n" + teorema.decision);


    }

    private void convertirValoresEntrantesCasosABC(String caso){

        varianzaPoblacional0 = convertirStringADouble(lienzo.editTextNumberDecimal14.getText().toString());
        varianzaMuestral = convertirStringADouble(lienzo.editTextNumberDecimal15.getText().toString());
        significancia = convertirStringADouble(lienzo.editTextNumberDecimal16.getText().toString());
        tamMuestra = convertirStringAInt(lienzo.editTextNumber5.getText().toString());
        teorema = new PHIPVARIANZA(significancia,tamMuestra,varianzaMuestral,varianzaPoblacional0);

        switch(caso){
            case "Caso a":

                limInf = teorema.caso3();
                break;

            case "Caso b":

                limSup = teorema.caso1();
                break;
                
            case "Caso c":
                
                intervalo = teorema.caso2();
                limInf = teorema.getLimiteInferior();
                limSup = teorema.getLimiteSuperior();
                break;
            default:

                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();

        }

    }

    private void convertirValoresEntrantesCasoD(){

        varianzaPoblacional0 = convertirStringADouble(lienzo.editTextNumber6.getText().toString());
        varianzaPoblacional1 = convertirStringADouble(lienzo.editTextNumberDecimal14.getText().toString());
        varianzaMuestral = convertirStringADouble(lienzo.editTextNumberDecimal15.getText().toString());
        significancia = convertirStringADouble(lienzo.editTextNumberDecimal16.getText().toString());
        tamMuestra = convertirStringAInt(lienzo.editTextNumber5.getText().toString());
        teorema = new PHIPVARIANZA(significancia,tamMuestra,varianzaMuestral,varianzaPoblacional0,varianzaPoblacional1);
        intervalo = teorema.caso4();
    }

    private boolean faltaUnDato(){
        if(lienzo.editTextNumberDecimal14.getText().toString().isEmpty()||lienzo.editTextNumberDecimal15.getText().toString().isEmpty()||lienzo.editTextNumberDecimal16.getText().toString().isEmpty()||lienzo.editTextNumber5.getText().toString().isEmpty()){
            lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.GONE);
            lienzo.procedimiento.setVisibility(View.GONE);
            lienzo.Resultado.setText("El resultado calculado es:");
            return true;
        }else return false;
    }

    private boolean faltaUnDatoCasod(){
        if(faltaUnDato()||lienzo.editTextNumber6.getText().toString().isEmpty()){
            lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.GONE);
            lienzo.procedimiento.setVisibility(View.GONE);
            lienzo.Resultado.setText("El resultado calculado es:");
            return true;
        }else return false;
    }

    private void setContrasteHipotesis(String caso){

        if(caso.equals("Caso a")){

            lienzo.imageView84.setImageResource(R.drawable.contrastehipotesiscasoavarianza);
            lienzo.imageView74.setImageResource(R.drawable.contrastehipotesiscasoavarianza);


        }else if(caso.equals("Caso b")){

            lienzo.imageView84.setImageResource(R.drawable.contrastehipotesiscasobvarianza);
            lienzo.imageView74.setImageResource(R.drawable.contrastehipotesiscasobvarianza);

        }else if(caso.equals("Caso c")){

            lienzo.imageView84.setImageResource(R.drawable.contrastehipotesiscasocvarianza);
            lienzo.imageView74.setImageResource(R.drawable.contrastehipotesiscasocvarianza);

        }else if(caso.equals("Caso d")){

            lienzo.imageView84.setImageResource(R.drawable.contrastehipotesiscasodvarianza);
            lienzo.imageView74.setImageResource(R.drawable.contrastehipotesiscasodvarianza);

        }

    }

    private void calcularPotenciaDeLaPrueba(String caso){

        lienzo.layoutProcedimientoPruebasHipotesis.setVisibility(View.VISIBLE);
        lienzo.procedimiento.setVisibility(View.GONE);

        switch (caso){

            case "Caso a":

                potenciaPrueba = redondeoDecimales((1-teorema.potenciaPrueba(limInf,nuevaVarianzaPoblacional)),5);

                lienzo.Resultado.setText("Con un límite inferior para la región de aceptación de " + limInf + "\n La potencia de la prueba calculada es: " + potenciaPrueba);

                break;

            case "Caso b":

                potenciaPrueba = teorema.potenciaPrueba(limSup,nuevaVarianzaPoblacional);

                lienzo.Resultado.setText("Con un límite superior para la región de aceptación de " + limSup + "\n La potencia de la prueba calculada es: " + potenciaPrueba);

                break;

            case "Caso c":
            case "Caso d":

                double potencia1 = teorema.potenciaPrueba(limInf,nuevaVarianzaPoblacional);
                potencia1 = redondeoDecimales((1-potencia1),5);
                estadisticoPrueba1 = teorema.estadisticoChi;
                valEnTablas1 = potencia1;
                double potencia2 = teorema.potenciaPrueba(limSup,nuevaVarianzaPoblacional);
                valEnTablas2 = potencia2;
                estadisticoPrueba2 = teorema.estadisticoChi;
                potenciaPrueba = redondeoDecimales((potencia1 + potencia2),5);

                lienzo.Resultado.setText("Con un intervalo para la región de aceptación de:" + intervalo + "\n la potencia de la prueba calculada es:" + potenciaPrueba);

        }

    }

    private void setTeoremasPotenciaPrueba(String caso){

        switch(caso){
            case "Caso a":

                lienzo.imageView85.setImageResource(R.drawable.graficaestadisticochiliminf);
                lienzo.imageView87.setImageResource(R.drawable.estadisticochiliminf);

                lienzo.tableRow2.setVisibility(View.GONE);
                lienzo.imageView91.setImageResource(R.drawable.sigmacuadrada0);

                break;

            case "Caso b":

                lienzo.imageView85.setImageResource(R.drawable.graficaestadisticochilimsup);
                lienzo.imageView87.setImageResource(R.drawable.estadisticochilimsup);

                lienzo.tableRow2.setVisibility(View.GONE);
                lienzo.imageView91.setImageResource(R.drawable.sigmacuadrada0);

                break;

            case "Caso c":
            case "Caso d":

                lienzo.imageView85.setImageResource(R.drawable.graficaestadisticochiintervalo);
                lienzo.linearLayoutDosLimites.setVisibility(View.VISIBLE);
                lienzo.layoutUnLimite.setVisibility(View.GONE);
                lienzo.rowOpcional.setVisibility(View.GONE);

                lienzo.imageView93.setImageResource(R.drawable.estadisticochiliminf);
                lienzo.imageView94.setImageResource(R.drawable.estadisticochilimsup);
                lienzo.imagenSigmaCero.setImageResource(R.drawable.sigmacuadrada0);


                break;

            default:
                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setPaso1PotenciaPrueba(String caso){

        switch(caso){

            case "Caso a":

                lienzo.textView162.setText("Si se observa la gráfica presentada anteriormente y se toma en cuenta lo dicho al inicio del procedimiento; podrás percatarte de que se requiere calcular la probabilidad de que la varianza muestral sea menor que " + limInf + " " +
                        "tal que el nuevo valor esperado de la varianza poblacional es " + nuevaVarianzaPoblacional + ":\n\nP(S<" + limInf + "| σ^2 = " + nuevaVarianzaPoblacional+ ").\n\nEl primer paso para calcular dicha probabilidad es " +
                        "obtener el valor de la estadística de prueba X que posteriormente buscaremos en las tablas de la distribución ji cuadrada con n-1 = " + (tamMuestra- 1 ) + " grados de libertad. " +
                        "Para realizar el cálculo de la estadística X es posible utilizar la siguiente expresión:");

                break;

            case "Caso b":

                lienzo.textView162.setText("Si se observa la gráfica presentada anteriormente y se toma en cuenta lo dicho al inicio del procedimiento; podrás percatarte de que se requiere calcular la probabilidad de que la varianza muestral sea mayor que " + limSup + " " +
                        "tal que el nuevo valor esperado de la varianza poblacional es " + nuevaVarianzaPoblacional + ":\n\nP(S>" + limSup + "| σ^2 = " + nuevaVarianzaPoblacional+ ") = 1 - P(S≤" + limSup + "| σ^2 = " + nuevaVarianzaPoblacional+ ").\n\nEl primer paso para calcular dicha probabilidad es " +
                        "obtener el valor de la estadística de prueba X que posteriormente buscaremos en las tablas de la distribución ji cuadrada con n-1 = " + (tamMuestra- 1 ) + " grados de libertad. " +
                        "Para realizar el cálculo de la estadística X es posible utilizar la siguiente expresión:");

                break;

            case "Caso c":
            case "Caso d":


                lienzo.textView162.setText("Si se observa la gráfica presentada anteriormente y se toma en cuenta lo dicho al inicio del procedimiento; podrás percatarte de que se requiere calcular la probabilidad de que la varianza muestral sea menor que " + limInf + " y de que sea mayor que " + limSup +
                        " tal que el nuevo valor esperado de la varianza poblacional es " + nuevaVarianzaPoblacional + ":\n\nP(S<" + limInf + " ∪ S> " + limSup +" | σ^2 = " + nuevaVarianzaPoblacional+ ").\n\nEl primer paso para calcular dicha probabilidad es " +
                        "obtener los valores de las estadísticas de prueba X que posteriormente buscaremos en las tablas de la distribución ji cuadrada con n-1 = " + (tamMuestra- 1 ) + " grados de libertad. " +
                        "Para realizar el cálculo de las estadísticas X es posible utilizar las siguientes expresiónes:");
                break;

            default:
                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    private void setPaso2PotenciaPrueba(String caso){

        String paso2_1 = "";

        switch(caso){

            case "Caso a":

                lienzo.textView163.setText("Una vez obtenido el valor del estadístico de prueba chi, se hace necesario buscarlo en las tablas de la distribución chi-cuadrada con n-1 = " +(tamMuestra-1) + " grados de libertad:\n\n" +
                        "En éste caso el valor encontrado es:\n\nP(X<" + teorema.estadisticoChi+") = " + potenciaPrueba + "\n\nCon lo cual la potencia de la prueba es: " + potenciaPrueba);

                break;

            case "Caso b":

                lienzo.textView163.setText("Una vez obtenido el valor del estadístico de prueba chi, se hace necesario buscarlo en las tablas de la distribución chi-cuadrada con n-1 = " +(tamMuestra-1) + " grados de libertad:\n\n" +
                        "En éste caso el valor encontrado es:\n\nP(X>" + teorema.estadisticoChi+") = " + teorema.getValTablas() + "\nSi se obtiene el complemento:\n P(X>" + teorema.estadisticoChi+") = 1- P(X≤" + teorema.estadisticoChi+") = " + potenciaPrueba +
                        "\n\nCon lo cual la potencia de la prueba es: " + potenciaPrueba);

                break;

            case "Caso c":

                lienzo.textView163.setText("Una vez obtenidos los valores de las estadísticas de prueba chi, se hace necesario buscar dichos valores en las tablas de la distribución chi-cuadrada con n-1 = " +(tamMuestra-1) + " grados de libertad:\n\n" +
                                "En éste caso los valores encontrados son:\n\nP(X<" + estadisticoPrueba1+") = " + valEnTablas1 + "\nY\nP(X> "+ estadisticoPrueba2 + " ) = " + valEnTablas2 + "\nRespectivamente. Si se calcula la probabilidad:" +
                                "\n\nP(X< " + estadisticoPrueba1 +") + P(X>" + estadisticoPrueba2 +") = P(X< " + estadisticoPrueba1 +") + [ 1- P(X>" + estadisticoPrueba2 +")] =" + valEnTablas1 + " + " + valEnTablas2 +" = " + potenciaPrueba +
                        "\n\nCon lo cual la potencia de la prueba es: " + potenciaPrueba);
                break;

            case "Caso d":

                break;

            default:

                Toast.makeText(this, "Caso inválido", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    private void actualizarValoresEnTablasPotenciaPrueba (String caso){

        switch (caso){

            case "Caso a":

                lienzo.textView165.setText(" = " + limInf);
                lienzo.textView167.setText(" = " + nuevaVarianzaPoblacional);
                lienzo.textView168.setText(" = " + tamMuestra);

                break;

            case "Caso b":

                lienzo.textView165.setText(" = " + limSup);
                lienzo.textView167.setText(" = " + nuevaVarianzaPoblacional);
                lienzo.textView168.setText(" = " + tamMuestra);

                break;

            case "Caso c":

                lienzo.valLimInf.setText(" = " + limInf);
                lienzo.valLimSup.setText(" = " + limSup);
                lienzo.valSigmaCero.setText(" = " + nuevaVarianzaPoblacional);
                lienzo.valN.setText(" = " + tamMuestra);

                break;

            case "Caso d":

                break;

            default:
                Toast.makeText(this, "Caso invalido", Toast.LENGTH_SHORT).show();
                break;

        }

    }


}