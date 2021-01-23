package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityVistaIntervalosConfianzaBinding;

public class VistaIntervalosConfianzaDiferenciaProporciones extends AppCompatActivity implements conversiones{

    private ActivityVistaIntervalosConfianzaBinding lienzo;
    private String elementos[] = {"Calcular intervalo de confianza","Calcular grado de confianza si se conoce el límite inferior del intervalo de confianza",
    "Calcular grado de confianza si se conoce el límite superior del intervalo de confianza"};
    private double proporcion1,proporcion2,q1,q2,limInf,limSup,nivelConfianza,gradoConfianza;
    private int tamMuestra1,tamMuestra2;
    private ICDIFERENCIAPROPORCIONES teorema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityVistaIntervalosConfianzaBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);

        lienzo.titulo.setText("INTERVALOS DE CONFIANZA PARA LA DIFERENCIA DE PROPORCIONES");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,elementos);
        lienzo.spinner.setAdapter(arrayAdapter);

        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(lienzo.spinner.getSelectedItemPosition() == 0){

                    setIntervalosConfianza();
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());

                }else if(lienzo.spinner.getSelectedItemPosition() == 1){

                    setGradoConfianza('a');
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());

                }else if(lienzo.spinner.getSelectedItemPosition() == 2){

                    setGradoConfianza('b');
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lienzo.delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.delete2.setAnimation(R.raw.delete);
                lienzo.delete2.setSpeed((float) 999999999);
                lienzo.delete2.playAnimation();
                lienzo.delete2.setRepeatCount(1000000);
                lienzo.delete2.animate()
                        .alpha(0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                lienzo.delete2.setImageResource(R.drawable.gomadeborrar);
                                lienzo.delete2.setAlpha(1f);
                                lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
                                lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
                                lienzo.resultadoIC.setText("El resultado es:");

                                lienzo.editTextNumberSigned3.setText("");
                                lienzo.editTextNumberDecimal6.setText("");
                                lienzo.editTextNumberDecimal7.setText("");
                                lienzo.editTextNumberDecimal8.setText("");
                                lienzo.editTextNumber3.setText("");
                                lienzo.editTextNumberDecimal9.setText("");

                            }
                        });
            }
        });


    }


    public void setIntervalosConfianza(){

        lienzo.datosDiferenciaProporciones.setVisibility(View.VISIBLE);

        lienzo.cardViewOpcional.setVisibility(View.GONE);
        lienzo.cardViewOpcional2.setVisibility(View.GONE);

        lienzo.cardViewNivelConfianza.setVisibility(View.VISIBLE);
        lienzo.CardViewEdittTextNivelConfianza.setVisibility(View.VISIBLE);

        lienzo.tableRowOpcional1.setVisibility(View.VISIBLE);
        lienzo.tableRowOpcional2.setVisibility(View.VISIBLE);
        lienzo.tableRowOpcional3.setVisibility(View.VISIBLE);

        lienzo.imageView26.setImageResource(R.drawable.proporcion1);
        lienzo.imageView27.setImageResource(R.drawable.q1);
        lienzo.imageView28.setImageResource(R.drawable.n1);
        lienzo.imageView10.setImageResource(R.drawable.proporcion2);
        lienzo.imageView13.setImageResource(R.drawable.q2);
        lienzo.imageView14.setImageResource(R.drawable.n2);
        lienzo.imagenDato1tamMinMuestraTclMedia.setImageResource(R.drawable.unomenosalfa);

        lienzo.imageView18.setImageResource(R.drawable.teoremaicdiferenciaproporciones);
        lienzo.alfaopcional.setImageResource(R.drawable.alfamedios);
        lienzo.tablaopcional.setImageResource(R.drawable.zetaalfamedios);
        lienzo.imageView20.setImageResource(R.drawable.liminfteoremaicdiferenciaproporciones);
        lienzo.imageView21.setImageResource(R.drawable.limsupteoremaicdiferenciaproporciones);

        lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                lienzo.botonCalcularIC.setAnimation(R.raw.loading);
                lienzo.botonCalcularIC.setSpeed((float) 999999999);
                lienzo.botonCalcularIC.playAnimation();
                lienzo.botonCalcularIC.setRepeatCount(1000000);
                lienzo.botonCalcularIC.animate()
                        .alpha(0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                lienzo.botonCalcularIC.setImageResource(R.drawable.calculadora);
                                lienzo.botonCalcularIC.setAlpha(1f);

                                try{

                                    proporcion1 = convertirStringADouble(lienzo.editTextNumberDecimal6.getText().toString());
                                    tamMuestra1 = convertirStringAInt(lienzo.editTextNumberDecimal7.getText().toString());
                                    proporcion2 = convertirStringADouble(lienzo.editTextNumberDecimal8.getText().toString());
                                    tamMuestra2 = convertirStringAInt(lienzo.editTextNumber3.getText().toString());
                                    nivelConfianza = convertirStringADouble(lienzo.editTextNumberDecimal9.getText().toString());
                                    teorema = new ICDIFERENCIAPROPORCIONES(proporcion1,proporcion2,tamMuestra1,tamMuestra2,nivelConfianza);
                                    String intervalo = teorema.calcLimInf() + "< µ < " + teorema.calcLimSup();
                                    lienzo.resultadoIC.setText("Con un nivel de confianza de " + nivelConfianza + "\nEl intervalo de confianza obtenido es : " + intervalo);
                                    lienzo.paso1IC.setText("Al observar el teorema presentado anteriormente podemos percatarnos de que es necesario buscar el valor de α/2 en las tablas de la distribución normal estándar. Donde:");


                                    lienzo.textView25.setText(" = " + proporcion1);
                                    lienzo.textView99.setText(" = " + teorema.q1);
                                    lienzo.textView103.setText(" = " + tamMuestra1);
                                    lienzo.textView80.setText(" = " + proporcion2);
                                    lienzo.desvEstandar.setText(" = " + teorema.q2);
                                    lienzo.dato3TabTCL.setText(" = " + tamMuestra2);
                                    lienzo.dato4TabTCL.setText(" = " + nivelConfianza);


                                    lienzo.valoralfaopcional.setText(" = " + redondeoDecimales(((1-nivelConfianza)/2),4));
                                    lienzo.valortablaopcional.setText(" = " + teorema.getValTablas());
                                    lienzo.conclusionIC1.setText("Se puede asegurar que con una confianza del " + redondeoDecimales((nivelConfianza*100),4) + "% el parametro binomial p1-p2 se encontrará en el intervalo\n\n" + intervalo);

                                }catch (NumberFormatException numberFormatException){
                                    Toast.makeText(VistaIntervalosConfianzaDiferenciaProporciones.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });





            }
        });


    }

    public void setGradoConfianza(char limite){

        lienzo.datosDiferenciaProporciones.setVisibility(View.VISIBLE);

        lienzo.cardViewOpcional.setVisibility(View.VISIBLE);
        lienzo.cardViewOpcional2.setVisibility(View.VISIBLE);

        lienzo.cardViewNivelConfianza.setVisibility(View.GONE);
        lienzo.CardViewEdittTextNivelConfianza.setVisibility(View.GONE);

        lienzo.tbRowOpcional1.setVisibility(View.VISIBLE);
        lienzo.tbRowopcional2.setVisibility(View.VISIBLE);
        lienzo.tbRowOpcional3.setVisibility(View.VISIBLE);

        lienzo.imageView29.setImageResource(R.drawable.proporcion1);
        lienzo.imageView30.setImageResource(R.drawable.q1);
        lienzo.uno.setImageResource(R.drawable.n1);
        lienzo.segundo.setImageResource(R.drawable.proporcion2);
        lienzo.ab.setImageResource(R.drawable.q2);
        lienzo.ultimo.setImageResource(R.drawable.n2);
        lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremacoeficienteconfianzadiferenciaproporciones);


        if(limite == 'a'){
            lienzo.imageView49.setImageResource(R.drawable.a);
            lienzo.textView124.setText("Límite inferior conocido");

            lienzo.imageView48.setImageResource(R.drawable.a);
            lienzo.imageView6.setImageResource(R.drawable.liminfteoremacoeficienteconfianzadiferenciaproporciones);
            lienzo.determinante.setImageResource(R.drawable.determinanteliminfteoremacoeficienteconfianzadiferenciaproporciones);

            lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    lienzo.botonCalcularIC.setAnimation(R.raw.loading);
                    lienzo.botonCalcularIC.setSpeed((float) 999999999);
                    lienzo.botonCalcularIC.playAnimation();
                    lienzo.botonCalcularIC.setRepeatCount(1000000);
                    lienzo.botonCalcularIC.animate()
                            .alpha(0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    lienzo.botonCalcularIC.setImageResource(R.drawable.calculadora);
                                    lienzo.botonCalcularIC.setAlpha(1f);

                                    try{

                                        proporcion1 = convertirStringADouble(lienzo.editTextNumberDecimal6.getText().toString());
                                        tamMuestra1 = convertirStringAInt(lienzo.editTextNumberDecimal7.getText().toString());
                                        proporcion2 = convertirStringADouble(lienzo.editTextNumberDecimal8.getText().toString());
                                        tamMuestra2 = convertirStringAInt(lienzo.editTextNumber3.getText().toString());
                                        limInf = convertirStringADouble(lienzo.editTextNumberSigned3.getText().toString());
                                        teorema = new ICDIFERENCIAPROPORCIONES(proporcion1,proporcion2,limInf,tamMuestra1,tamMuestra2);
                                        gradoConfianza = teorema.calcularCoeficienteDeConfianza('a',limInf);
                                        String intervalo = teorema.limInf + "< µ < " + teorema.limSup;
                                        lienzo.textView104.setText(" = " + proporcion1);
                                        lienzo.textView105.setText(" = " + teorema.q2);
                                        lienzo.val1CoeficienteConf.setText(" = " + tamMuestra1);
                                        lienzo.val2CoeficienteConf.setText(" = " + proporcion2);
                                        lienzo.val3CoeficienteConf.setText(" = " + teorema.q2);
                                        lienzo.val4CoefConf.setText(" = " + tamMuestra2);
                                        lienzo.textView123.setText(" = " + limInf);

                                        lienzo.valDeterminante.setText(" = " + teorema.getDeterminante());
                                        lienzo.textView66.setText("Una vez obtenido el valor de nuestro determinante vamos a proceder a buscar ese valor en las tablas de la distribución acumulada normal estándar. Donde, en éste caso:\n\nØ("+ teorema.getDeterminante()+") = " + teorema.getValTablas());
                                        lienzo.textView92.setText("1-α = 1 - (2*"+teorema.getValTablas()+") = " + gradoConfianza);

                                        lienzo.conclusionIC1.setText("Se puede asegurar que aproximadamente el " + redondeoDecimales((nivelConfianza*100),4) + "% de las veces el parametro binomial 'p1 - p2' se encontrará dentro del intervalo:\n" + intervalo);



                                    }catch (NumberFormatException numberFormatException){
                                        Toast.makeText(VistaIntervalosConfianzaDiferenciaProporciones.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            });


        }else if(limite == 'b'){
            lienzo.imageView49.setImageResource(R.drawable.b);
            lienzo.textView124.setText("Límite superior conocido");

            lienzo.imageView48.setImageResource(R.drawable.b);
            lienzo.imageView6.setImageResource(R.drawable.limsupteoremacoeficienteconfianzadiferenciaproporciones);
            lienzo.determinante.setImageResource(R.drawable.determinantelimsupteoremacoeficienteconfianzadiferenciaproporciones);

            lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    lienzo.botonCalcularIC.setAnimation(R.raw.loading);
                    lienzo.botonCalcularIC.setSpeed((float) 999999999);
                    lienzo.botonCalcularIC.playAnimation();
                    lienzo.botonCalcularIC.setRepeatCount(1000000);
                    lienzo.botonCalcularIC.animate()
                            .alpha(0f)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    lienzo.botonCalcularIC.setImageResource(R.drawable.calculadora);
                                    lienzo.botonCalcularIC.setAlpha(1f);

                                    try{

                                        proporcion1 = convertirStringADouble(lienzo.editTextNumberDecimal6.getText().toString());
                                        tamMuestra1 = convertirStringAInt(lienzo.editTextNumberDecimal7.getText().toString());
                                        proporcion2 = convertirStringADouble(lienzo.editTextNumberDecimal8.getText().toString());
                                        tamMuestra2 = convertirStringAInt(lienzo.editTextNumber3.getText().toString());
                                        limSup = convertirStringADouble(lienzo.editTextNumberSigned3.getText().toString());
                                        teorema = new ICDIFERENCIAPROPORCIONES(proporcion1,proporcion2,limSup,tamMuestra1,tamMuestra2);
                                        gradoConfianza = teorema.calcularCoeficienteDeConfianza('b',limSup);
                                        String intervalo = teorema.limInf + "< µ < " + teorema.limSup;
                                        lienzo.textView104.setText(" = " + proporcion1);
                                        lienzo.textView105.setText(" = " + teorema.q2);
                                        lienzo.val1CoeficienteConf.setText(" = " + tamMuestra1);
                                        lienzo.val2CoeficienteConf.setText(" = " + proporcion2);
                                        lienzo.val3CoeficienteConf.setText(" = " + teorema.q2);
                                        lienzo.val4CoefConf.setText(" = " + tamMuestra2);
                                        lienzo.textView123.setText(" = " + limSup);

                                        lienzo.valDeterminante.setText(" = " + teorema.getDeterminante());
                                        lienzo.textView66.setText("Una vez obtenido el valor de nuestro determinante vamos a proceder a buscar ese valor en las tablas de la distribución acumulada normal estándar. Donde, en éste caso:\n\nØ("+ teorema.getDeterminante()+") = " + teorema.getValTablas());
                                        lienzo.textView92.setText("1-α = (2*"+teorema.getValTablas()+") - 1 = " + gradoConfianza);

                                        lienzo.conclusionIC1.setText("Se puede asegurar que aproximadamente el " + redondeoDecimales((nivelConfianza*100),4) + "% de las veces el parametro binomial 'p1 - p2' se encontrará dentro del intervalo:\n" + intervalo);



                                    }catch (NumberFormatException numberFormatException){
                                        Toast.makeText(VistaIntervalosConfianzaDiferenciaProporciones.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            });
        }
    }


}