package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityVistaIntervalosConfianzaBinding;

public class VistaIntervalosConfianzaProporciones extends AppCompatActivity implements conversiones {

    private ActivityVistaIntervalosConfianzaBinding lienzo;
    private ICFPROPORCIONES teorema;
    private double proporcion,nivelConfianza;
    private int tamMuestra1;
    private String[] elementos = {"Calcular intervalo de confianza para la proporcion","Calcular grado de " +
            "confianza si se conoce el límite inferior del Intervalo de confianza","Calcular grado de confianza si se conoce el límite superior del Intervalo de confianza"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityVistaIntervalosConfianzaBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(VistaIntervalosConfianzaProporciones.this, android.R.layout.simple_spinner_dropdown_item,
                elementos);

        lienzo.spinner.setAdapter(arrayAdapter);

        lienzo.datosICProporcion.setVisibility(View.VISIBLE);

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
                                lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
                                lienzo.editTextNumberDecimal2.setText("");
                                lienzo.editTextNumber.setText("");
                                lienzo.editTextNumberDecimal3.setText("");
                                lienzo.resultadoIC.setText("El resultado es:");
                            }
                        });
            }
        });

        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(lienzo.spinner.getSelectedItemPosition() == 0){
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                    prepararIC();

                }else if(lienzo.spinner.getSelectedItemPosition() == 1){
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                    prepararCoeficienteConfianza('a');

                }else if(lienzo.spinner.getSelectedItemPosition() == 2){
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                    prepararCoeficienteConfianza('b');
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void desaparecerLayoutProcedimientos(){
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
    }

    public void prepararIC(){
        desaparecerLayoutProcedimientos();

        lienzo.imageView39.setImageResource(R.drawable.unomenosalfa);
        lienzo.textView114.setText("Nivel de confianza");

        lienzo.imageView10.setImageResource(R.drawable.proporcion);
        lienzo.imageView13.setImageResource(R.drawable.unomenosn);
        lienzo.imageView14.setImageResource(R.drawable.unomenosalfa);
        lienzo.imagenDato1tamMinMuestraTclMedia.setImageResource(R.drawable.n);

        lienzo.imageView18.setImageResource(R.drawable.teoremaicproporciones);
        lienzo.alfaopcional.setImageResource(R.drawable.alfamedios);
        lienzo.tablaopcional.setImageResource(R.drawable.zetaalfamedios);

        lienzo.imageView20.setImageResource(R.drawable.liminfteoremaicproporciones);
        lienzo.imageView21.setImageResource(R.drawable.limsupteoremaicproporciones);


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

                                    proporcion = convertirStringADouble(lienzo.editTextNumberDecimal2.getText().toString());
                                    tamMuestra1 = convertirStringAInt(lienzo.editTextNumber.getText().toString());
                                    nivelConfianza = convertirStringADouble(lienzo.editTextNumberDecimal3.getText().toString());
                                    teorema = new ICFPROPORCIONES(proporcion,nivelConfianza,tamMuestra1);
                                    String intervalo = teorema.getLimInf() +"<μ<" + teorema.getLimSup();
                                    lienzo.resultadoIC.setText("Con un nivel de confianza de " + nivelConfianza +"\nEl intervalo de confianza calculado es:\n"+intervalo);
                                    lienzo.layoutProcedimientoIc.setVisibility(View.VISIBLE);

                                    lienzo.textView80.setText(" = " + proporcion);
                                    lienzo.desvEstandar.setText(" = " + teorema.getComplemento());
                                    lienzo.dato3TabTCL.setText(" = " + nivelConfianza);
                                    lienzo.dato4TabTCL.setText(" = " + tamMuestra1);

                                    lienzo.valoralfaopcional.setText(" = " + redondeoDecimales(((1-nivelConfianza)/2),4));
                                    lienzo.valortablaopcional.setText(" = " + teorema.getValTablas());

                                    lienzo.paso1IC.setText("Al observar el teorema presentado anteriormente podemos percatarnos de que se hace necesario buscar el valor de:\n" +
                                            "α/2 en las tablas de la distribucion normal estandar. Donde:");

                                    lienzo.conclusionIC1.setText("Se puede asegurar que en una muestra de tamaño " + tamMuestra1+" aproximadamente el " + redondeoDecimales((nivelConfianza*100),4) + "% de las veces el parametro binomial 'p' se encontrarà dentro del intervalo:\n" + intervalo);


                                }catch(NumberFormatException numberFormatException){
                                    Toast.makeText(VistaIntervalosConfianzaProporciones.this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });


    }

    public void prepararCoeficienteConfianza(char limite){
        desaparecerLayoutProcedimientos();

        if(limite == 'a'){

            lienzo.imageView39.setImageResource(R.drawable.a);
            lienzo.textView114.setText("Limite inferior conocido");

            lienzo.uno.setImageResource(R.drawable.proporcion);
            lienzo.segundo.setImageResource(R.drawable.unomenosn);
            lienzo.ab.setImageResource(R.drawable.a);
            lienzo.ultimo.setImageResource(R.drawable.n);

            lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremagradoconfianzaproporciones);
            lienzo.imageView6.setImageResource(R.drawable.liminfteoremagradoconfianzaproporciones);
            lienzo.determinante.setImageResource(R.drawable.discriminanteliminfteoremagradoconfianzaproporciones);

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

                                        proporcion = convertirStringADouble(lienzo.editTextNumberDecimal2.getText().toString());
                                        tamMuestra1 = convertirStringAInt(lienzo.editTextNumber.getText().toString());
                                        double limInf = convertirStringADouble(lienzo.editTextNumberDecimal3.getText().toString());
                                        teorema = new ICFPROPORCIONES(limInf,tamMuestra1,proporcion,'a');
                                        lienzo.resultadoIC.setText("Con un limite inferior para el intervalo de confianza de " + limInf + ".\nEl nivel de confianza calculado es " + teorema.getCoeficienteConfianza());

                                        lienzo.val1CoeficienteConf.setText(" = " + proporcion);
                                        lienzo.val2CoeficienteConf.setText(" = " + teorema.getComplemento());
                                        lienzo.val3CoeficienteConf.setText(" = " + limInf);
                                        lienzo.val4CoefConf.setText(" = " + tamMuestra1);

                                        lienzo.valDeterminante.setText(" = " + teorema.getMultiplicador());

                                        lienzo.textView66.setText("Una vez obtenido el valor de nuestro determinante procederemos a buscarlo en las tablas de la distribucion acumulada normal estandar. Donde, en este caso el valor encontrado en tablas es:\nØ(" + teorema.getMultiplicador()+ ") = " + teorema.getValTablas());

                                        lienzo.textView92.setText("1-α = 1- (2* " + teorema.getValTablas()+") = " + teorema.getCoeficienteConfianza());
                                        lienzo.imageView5.setVisibility(View.GONE);

                                        lienzo.textView95.setText("La proporcion deseada se encuentra en el intervalo: " + teorema.getLimInf() + "< p <" + teorema.getLimSup() +
                                        " con una confianza de " + redondeoDecimales((teorema.getCoeficienteConfianza()*100),4) + "%");

                                    }catch(NumberFormatException numberFormatException){
                                        Toast.makeText(VistaIntervalosConfianzaProporciones.this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                }
            });

        }else if(limite == 'b'){

            lienzo.imageView39.setImageResource(R.drawable.b);
            lienzo.textView114.setText("Limite superior conocido");

            lienzo.uno.setImageResource(R.drawable.proporcion);
            lienzo.segundo.setImageResource(R.drawable.unomenosn);
            lienzo.ab.setImageResource(R.drawable.b);
            lienzo.ultimo.setImageResource(R.drawable.n);

            lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremagradoconfianzaproporciones);
            lienzo.imageView6.setImageResource(R.drawable.limsupteoremagradoconfianzaproporciones);
            lienzo.determinante.setImageResource(R.drawable.discriminantelimsupteoremagradoconfianzaproporciones);

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

                                        proporcion = convertirStringADouble(lienzo.editTextNumberDecimal2.getText().toString());
                                        tamMuestra1 = convertirStringAInt(lienzo.editTextNumber.getText().toString());
                                        double limSup = convertirStringADouble(lienzo.editTextNumberDecimal3.getText().toString());
                                        teorema = new ICFPROPORCIONES(limSup,tamMuestra1,proporcion,'b');
                                        lienzo.resultadoIC.setText("Con un limite superior para el intervalo de confianza de " + limSup + ".\nEl nivel de confianza calculado es " + teorema.getCoeficienteConfianza());

                                        lienzo.val1CoeficienteConf.setText(" = " + proporcion);
                                        lienzo.val2CoeficienteConf.setText(" = " + teorema.getComplemento());
                                        lienzo.val3CoeficienteConf.setText(" = " + limSup);
                                        lienzo.val4CoefConf.setText(" = " + tamMuestra1);

                                        lienzo.valDeterminante.setText(" = " + teorema.getMultiplicador());

                                        lienzo.textView66.setText("Una vez obtenido el valor de nuestro determinante procederemos a buscarlo en las tablas de la distribucion acumulada normal estandar. Donde, en este caso el valor encontrado en tablas es:\nØ(" + teorema.getMultiplicador()+ ") = " + teorema.getValTablas());

                                        lienzo.textView92.setText("1-α = 1- (2* " + teorema.getValTablas()+") = " + teorema.getCoeficienteConfianza());
                                        lienzo.imageView5.setVisibility(View.GONE);

                                        lienzo.textView95.setText("La proporcion deseada se encuentra en el intervalo: " + teorema.getLimInf() + "< p <" + teorema.getLimSup() +
                                                " con una confianza de " + redondeoDecimales((teorema.getCoeficienteConfianza()*100),4) + "%");

                                    }catch(NumberFormatException numberFormatException){
                                        Toast.makeText(VistaIntervalosConfianzaProporciones.this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }
            });


        }


    }

}