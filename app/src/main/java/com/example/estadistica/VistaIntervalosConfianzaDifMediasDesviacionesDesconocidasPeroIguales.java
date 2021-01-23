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

public class VistaIntervalosConfianzaDifMediasDesviacionesDesconocidasPeroIguales extends AppCompatActivity implements conversiones{

    private ActivityVistaIntervalosConfianzaBinding lienzo;
    private String elementos[] = {"Calcular intervalo de confianza si se desconocen las desviaciones estándar poblacionales pero se sabe que son iguales","Calcular grado de confianza si se conoce el límite inferior del intervalo de confianza","Calcular grado de confianza si se conoce el límite superior del intervalo de confianza"};
    private ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES teorema;
    private double varianzaMuestral1,varianzaMuestral2,diferenciaPromedios,nivelConfianza;
    private int tamMuestra1,tamMuestra2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityVistaIntervalosConfianzaBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,elementos);

        lienzo.spinner.setAdapter(arrayAdapter);


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
                                lienzo.varPob1.setText("");
                                lienzo.tamMuestra1.setText("");
                                lienzo.varPob2.setText("");
                                lienzo.tamMuestra2.setText("");
                                lienzo.nivelConfianzaDifMedias1.setText("");
                                lienzo.diferenciaPromedios1.setText("");
                                lienzo.varianzaPoblacional1CoefConfianzaDifMedias.setText("");
                                lienzo.tamMuestral1CoefConfianzaDifMedias.setText("");
                                lienzo.varianzaPoblacional2CoefConfianzaDifMedias.setText("");
                                lienzo.tamMuestral2CoefConfianzaDifMedias.setText("");
                                lienzo.diferenciaPromediosMuestrales.setText("");
                                lienzo.limiteConocido.setText("");
                                lienzo.resultadoIC.setText("El resultado es:");
                                lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
                                lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);

                            }
                        });
            }
        });


        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(lienzo.spinner.getSelectedItemPosition() == 0){
                    prepararEntornoIC();
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                }
                else if(lienzo.spinner.getSelectedItemPosition() == 1){
                    prepararEntornoCoeficienteConfianza('a');
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                }
                else if(lienzo.spinner.getSelectedItemPosition() == 2){
                    prepararEntornoCoeficienteConfianza('b');
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }
        });

    }

    private void prepararEntornoIC(){
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.datosICDiferenciaMedias.setVisibility(View.VISIBLE);
        lienzo.datosNivelConfianzaDiferenciaMedias.setVisibility(View.GONE);

        lienzo.sigmacuadrada1.setImageResource(R.drawable.scuadrada1);
        lienzo.sigmacuadrada2.setImageResource(R.drawable.scuadrada2);
        lienzo.tvsigmacuadrada1.setText("Varianza muestral 1");
        lienzo.tvsigmacuadrada2.setText("Varianza muestral 2");

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
                                    lienzo.tableRowOpcional2.setVisibility(View.VISIBLE);
                                    lienzo.tableRowOpcional3.setVisibility(View.VISIBLE);
                                    lienzo.imageView27.setImageResource(R.drawable.scuadrada1);
                                    lienzo.imageView28.setImageResource(R.drawable.n1);
                                    lienzo.imageView10.setImageResource(R.drawable.scuadrada2);
                                    lienzo.imageView13.setImageResource(R.drawable.n2);
                                    lienzo.imageView14.setImageResource(R.drawable.unomenosalfa);
                                    lienzo.imagenDato1tamMinMuestraTclMedia.setImageResource(R.drawable.diferenciapromedios);

                                    lienzo.imageView18.setImageResource(R.drawable.teoremaintervaloconfianzadiferenciamediasvardesconocidasperoiguales);
                                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                                    lienzo.alfaopcional.setImageResource(R.drawable.alfamedios);
                                    lienzo.tablaopcional.setImageResource(R.drawable.tenalfamedios);
                                    lienzo.imageView20.setImageResource(R.drawable.liminfteoremaintervaloconfianzadiferenciamediasvardesconocidasperoiguales);
                                    lienzo.imageView21.setImageResource(R.drawable.limsupteoremaintervaloconfianzadiferenciamediasvardesconocidasperoiguales);


                                    varianzaMuestral1 = convertirStringADouble(lienzo.varPob1.getText().toString());
                                    varianzaMuestral2 = convertirStringADouble(lienzo.varPob2.getText().toString());
                                    tamMuestra1 = convertirStringAInt(lienzo.tamMuestra1.getText().toString());
                                    tamMuestra2 = convertirStringAInt(lienzo.tamMuestra2.getText().toString());
                                    nivelConfianza = convertirStringADouble(lienzo.nivelConfianzaDifMedias1.getText().toString());
                                    diferenciaPromedios = convertirStringADouble(lienzo.diferenciaPromedios1.getText().toString());
                                    teorema = new ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES(varianzaMuestral1,varianzaMuestral2,tamMuestra1,tamMuestra2,diferenciaPromedios,nivelConfianza);
                                    String intervalo = teorema.getLimInf() +  "<μ<" + teorema.getLimSup();
                                    lienzo.resultadoIC.setText("Con un nivel de confianza de " + nivelConfianza + "\n\nEl intervalo de confianza calculado es:\n" + intervalo);
                                    lienzo.layoutProcedimientoIc.setVisibility(View.VISIBLE);

                                    lienzo.textView99.setText(" = " + varianzaMuestral1);
                                    lienzo.textView103.setText(" = " + tamMuestra1);
                                    lienzo.textView80.setText(" = " + varianzaMuestral2);
                                    lienzo.desvEstandar.setText(" = " + tamMuestra2);
                                    lienzo.dato3TabTCL.setText(" = " + nivelConfianza);
                                    lienzo.dato4TabTCL.setText(" = " + diferenciaPromedios);

                                    lienzo.paso1IC.setText("Al observar el teorema anteriormente presentado podemos percatarnos de que es necesario calcular un valor para la estimación común de la desviación estándar poblacional (Sp).\n\nPara ello se utilizará la siguiente fórmula:");
                                    lienzo.textView107.setText("En éste caso el valor calculado es: " + teorema.getSp());
                                    lienzo.textView108.setText("Una vez que calculamos el valor de Sp podemos percatarnos de que es necesario buscar el valor de t(α/2) en  las tablas de la distribución t-student con V = n1 + n2 - 2 = "  + teorema.getGradosLibertad() +
                                            " grados de libertad. Donde:");
                                    lienzo.valoralfaopcional.setText(" = " + redondeoDecimales(((1-nivelConfianza)/2),4));
                                    lienzo.valortablaopcional.setText(" = " + teorema.getValTablas());
                                    lienzo.conclusionIC1.setText("Con un nivel de confianza de " + nivelConfianza + " podemos asegurar que la diferencia de medias se encontrará en el intervalo:\n" + intervalo);

                                }catch (NumberFormatException numberFormatException){
                                    Toast.makeText(VistaIntervalosConfianzaDifMediasDesviacionesDesconocidasPeroIguales.this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

            }
        });
    }

    public void prepararEntornoCoeficienteConfianza(char limite){
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
        lienzo.datosICDiferenciaMedias.setVisibility(View.GONE);
        lienzo.datosNivelConfianzaDiferenciaMedias.setVisibility(View.VISIBLE);

        if(limite == 'a'){

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

                                        varianzaMuestral1 = convertirStringADouble(lienzo.varianzaPoblacional1CoefConfianzaDifMedias.getText().toString());
                                        varianzaMuestral2 = convertirStringADouble(lienzo.varianzaPoblacional2CoefConfianzaDifMedias.getText().toString());
                                        tamMuestra1 = convertirStringAInt(lienzo.tamMuestral1CoefConfianzaDifMedias.getText().toString());
                                        tamMuestra2 = convertirStringAInt(lienzo.tamMuestral2CoefConfianzaDifMedias.getText().toString());
                                        diferenciaPromedios = convertirStringADouble(lienzo.diferenciaPromediosMuestrales.getText().toString());
                                        double limInf = convertirStringADouble(lienzo.limiteConocido.getText().toString());
                                        teorema = new ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES(tamMuestra1,tamMuestra2,varianzaMuestral1,varianzaMuestral2,diferenciaPromedios,limInf,'a');
                                        lienzo.resultadoIC.setText("Con un límite inferior para el intervalo de confianza de: " + limInf + "\n\nEl coeficiente de confianza calculado es: " + teorema.getCoeficienteConfianza());


                                        lienzo.tbRowOpcional1.setVisibility(View.VISIBLE);
                                        lienzo.imageView29.setImageResource(R.drawable.sigmacuadrada1);
                                        lienzo.textView104.setText(" = " + varianzaMuestral1);
                                        lienzo.imageView30.setImageResource(R.drawable.n1);
                                        lienzo.textView105.setText(" = " + tamMuestra1);
                                        lienzo.uno.setImageResource(R.drawable.diferenciapromedios);
                                        lienzo.val1CoeficienteConf.setText(" =  " + diferenciaPromedios);
                                        lienzo.segundo.setImageResource(R.drawable.a);
                                        lienzo.val2CoeficienteConf.setText(" = " + limInf);

                                        lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremacoeficienteconfianzadiferenciamediasdesvdesconocidas);
                                        lienzo.imageView6.setImageResource(R.drawable.liminfteoremacoeficienteconfianzadiferenciamediasdesvdesconocidas);
                                        lienzo.determinante.setImageResource(R.drawable.determinanteliminfteoremacoeficienteconfianzadiferenciamediasdesvdesconocidas);
                                        lienzo.valDeterminante.setText(" = " + teorema.getDeterminante());

                                        lienzo.linearLayoutOpcionalCoeficienteConf.setVisibility(View.VISIBLE);
                                        lienzo.layoutSp.setVisibility(View.VISIBLE);


                                        lienzo.imageView33.setVisibility(View.GONE);
                                        lienzo.imageView33.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lienzo.layoutSp.setVisibility(View.GONE);
                                                lienzo.imageView33.setVisibility(View.GONE);
                                                lienzo.imageView34.setVisibility(View.VISIBLE);
                                            }
                                        });

                                        lienzo.imageView34.setVisibility(View.VISIBLE);
                                        lienzo.imageView34.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lienzo.layoutSp.setVisibility(View.VISIBLE);
                                                lienzo.imageView33.setVisibility(View.VISIBLE);
                                                lienzo.imageView34.setVisibility(View.GONE);
                                            }
                                        });

                                        lienzo.layoutSp.setVisibility(View.GONE);
                                        lienzo.textView109.setText("En éste caso el valor calculado para Sp es: " + teorema.getSp());
                                        lienzo.textView66.setText("Una vez obtenido el valor se busca en las tablas de la distribuciòn acumulada t-student.\nEn este caso el valor encontrado en tablas es:\nT("+teorema.getDeterminante()+") = " + teorema.getValTablas());

                                        lienzo.textView92.setText("1 - α = (2*"+teorema.getValTablas()+") -1 = " + teorema.getCoeficienteConfianza());
                                        lienzo.textView95.setText("Con un limite inferior de: " + limInf + ".\nEl coeficiente de confianza calculado es: " + teorema.getCoeficienteConfianza());

                                        lienzo.imageView5.setVisibility(View.GONE);



                                    }catch (NumberFormatException numberFormatException){
                                        Toast.makeText(VistaIntervalosConfianzaDifMediasDesviacionesDesconocidasPeroIguales.this, "Todos los datos son requeridos para hacer el cálculo", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                }
            });

        }else if(limite == 'b'){

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

                                        varianzaMuestral1 = convertirStringADouble(lienzo.varianzaPoblacional1CoefConfianzaDifMedias.getText().toString());
                                        varianzaMuestral2 = convertirStringADouble(lienzo.varianzaPoblacional2CoefConfianzaDifMedias.getText().toString());
                                        tamMuestra1 = convertirStringAInt(lienzo.tamMuestral1CoefConfianzaDifMedias.getText().toString());
                                        tamMuestra2 = convertirStringAInt(lienzo.tamMuestral2CoefConfianzaDifMedias.getText().toString());
                                        diferenciaPromedios = convertirStringADouble(lienzo.diferenciaPromediosMuestrales.getText().toString());
                                        double limSup = convertirStringADouble(lienzo.limiteConocido.getText().toString());
                                        teorema = new ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES(tamMuestra1,tamMuestra2,varianzaMuestral1,varianzaMuestral2,diferenciaPromedios,limSup,'b');
                                        lienzo.resultadoIC.setText("Con un límite superior para el intervalo de confianza de: " + limSup + "\n\nEl coeficiente de confianza calculado es: " + teorema.getCoeficienteConfianza());


                                        lienzo.tbRowOpcional1.setVisibility(View.VISIBLE);
                                        lienzo.imageView29.setImageResource(R.drawable.sigmacuadrada1);
                                        lienzo.textView104.setText(" = " + varianzaMuestral1);
                                        lienzo.imageView30.setImageResource(R.drawable.n1);
                                        lienzo.textView105.setText(" = " + tamMuestra1);
                                        lienzo.uno.setImageResource(R.drawable.diferenciapromedios);
                                        lienzo.val1CoeficienteConf.setText(" =  " + diferenciaPromedios);
                                        lienzo.segundo.setImageResource(R.drawable.b);
                                        lienzo.val2CoeficienteConf.setText(" = " + limSup);

                                        lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremacoeficienteconfianzadiferenciamediasdesvdesconocidas);
                                        lienzo.imageView6.setImageResource(R.drawable.limsupteoremacoeficienteconfianzadiferenciamediasdesvdesconocidas);
                                        lienzo.determinante.setImageResource(R.drawable.determinantelimsupteoremacoeficienteconfianzadiferenciamediasdesvdesconocidas);
                                        lienzo.valDeterminante.setText(" = " + teorema.getDeterminante());

                                        lienzo.linearLayoutOpcionalCoeficienteConf.setVisibility(View.VISIBLE);
                                        lienzo.layoutSp.setVisibility(View.VISIBLE);


                                        lienzo.imageView33.setVisibility(View.GONE);
                                        lienzo.imageView33.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lienzo.layoutSp.setVisibility(View.GONE);
                                                lienzo.imageView33.setVisibility(View.GONE);
                                                lienzo.imageView34.setVisibility(View.VISIBLE);
                                            }
                                        });

                                        lienzo.imageView34.setVisibility(View.VISIBLE);
                                        lienzo.imageView34.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                lienzo.layoutSp.setVisibility(View.VISIBLE);
                                                lienzo.imageView33.setVisibility(View.VISIBLE);
                                                lienzo.imageView34.setVisibility(View.GONE);
                                            }
                                        });

                                        lienzo.layoutSp.setVisibility(View.GONE);
                                        lienzo.textView109.setText("En éste caso el valor calculado para Sp es: " + teorema.getSp());
                                        lienzo.textView66.setText("Una vez obtenido el valor se busca en las tablas de la distribuciòn acumulada t-student.\nEn este caso el valor encontrado en tablas es:\nT("+teorema.getDeterminante()+") = " + teorema.getValTablas());

                                        lienzo.textView92.setText("1 - α = 1- (2*"+teorema.getValTablas()+") = " + teorema.getCoeficienteConfianza());
                                        lienzo.textView95.setText("Con un limite superior de: " + limSup + ".\nEl coeficiente de confianza calculado es: " + teorema.getCoeficienteConfianza());

                                        lienzo.imageView5.setVisibility(View.GONE);



                                    }catch (NumberFormatException numberFormatException){
                                        Toast.makeText(VistaIntervalosConfianzaDifMediasDesviacionesDesconocidasPeroIguales.this, "Todos los datos son requeridos para hacer el cálculo", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });




                }
            });

        }
    }



}