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

public class VistaIntervalosConfianzaDifMediasDesviacionesConocidas extends AppCompatActivity implements conversiones,IntervalosDeConfianza{

    private ActivityVistaIntervalosConfianzaBinding lienzo;
    private double varianzaPob1,varianzaPob2;
    private int tamMuestra1, tamMuestra2;
    private double nivelConfianxa,diferenciaPromedios;
    private ICFDIFMEDIASVARIANZASCONOCIDAS teorema;
    private String[] contenedor = {"Calcular intervalo de confianza para la diferencia de medias si se conocen las desviaciones estándar poblacionales","Calcular nivel de confianza si se conoce el límite inferior del intervalo de confianza","Calcular nivel de confianza si se conoce el límite superior del intervalo de confianza"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityVistaIntervalosConfianzaBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(VistaIntervalosConfianzaDifMediasDesviacionesConocidas.this, android.R.layout.simple_spinner_dropdown_item,contenedor);
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
                                desplegarBotonBorrar();
                            }
                        });
            }
        });



        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(lienzo.spinner.getSelectedItemPosition() == 0){
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                    prepararEntornoIC();

                    lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try{
                                varianzaPob1 = convertirStringADouble(lienzo.varPob1.getText().toString());
                                varianzaPob2 = convertirStringADouble(lienzo.varPob2.getText().toString());
                                tamMuestra1 = convertirStringAInt(lienzo.tamMuestra1.getText().toString());
                                tamMuestra2 = convertirStringAInt(lienzo.tamMuestra2.getText().toString());
                                nivelConfianxa = convertirStringADouble(lienzo.nivelConfianzaDifMedias1.getText().toString());
                                diferenciaPromedios = convertirStringADouble(lienzo.diferenciaPromedios1.getText().toString());
                                teorema = new ICFDIFMEDIASVARIANZASCONOCIDAS(varianzaPob1,varianzaPob2,tamMuestra1,tamMuestra2,diferenciaPromedios,nivelConfianxa);
                                double liminf = redondeoDecimales(teorema.getLimInf(),4);
                                double limsup = redondeoDecimales(teorema.getLimSup(),4);
                                lienzo.layoutProcedimientoIc.setVisibility(View.VISIBLE);
                                String intervalo = liminf +  "< μ <" + limsup;
                                lienzo.resultadoIC.setText("Con un nivel de confianza de " + nivelConfianxa + " el intervalo de confianza calculado es: \n" + intervalo);

                                lienzo.textView99.setText(" = " + varianzaPob1);
                                lienzo.textView103.setText(" = " + tamMuestra1);
                                lienzo.textView80.setText(" = " + varianzaPob2);
                                lienzo.desvEstandar.setText(" = " + tamMuestra2);
                                lienzo.dato3TabTCL.setText(" = " + nivelConfianxa);
                                lienzo.dato4TabTCL.setText(" = " + diferenciaPromedios);

                                lienzo.valoralfaopcional.setText(" = " + redondeoDecimales(((1-nivelConfianxa)/2),4));
                                lienzo.valortablaopcional.setText(" = " + teorema.getValTablas());
                                lienzo.conclusionIC1.setText("Con un nivel de confianza de 1-α = " + nivelConfianxa + " es posible asegurar que la diferencia de promedios se encuentra en el intervalo: " + intervalo);

                            }catch(NumberFormatException e){
                                Toast.makeText(VistaIntervalosConfianzaDifMediasDesviacionesConocidas.this, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }else if(lienzo.spinner.getSelectedItemPosition() == 1){

                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());

                    preparEntornoGradoConfianza('a');
                    lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try{

                                varianzaPob1 = convertirStringADouble(lienzo.varianzaPoblacional1CoefConfianzaDifMedias.getText().toString());
                                tamMuestra1 = convertirStringAInt(lienzo.tamMuestral1CoefConfianzaDifMedias.getText().toString());
                                varianzaPob2 = convertirStringADouble(lienzo.varianzaPoblacional2CoefConfianzaDifMedias.getText().toString());
                                tamMuestra2 = convertirStringAInt(lienzo.tamMuestral2CoefConfianzaDifMedias.getText().toString());
                                diferenciaPromedios = convertirStringADouble(lienzo.diferenciaPromediosMuestrales.getText().toString());
                                double limInf = convertirStringADouble(lienzo.limiteConocido.getText().toString());
                                teorema = new ICFDIFMEDIASVARIANZASCONOCIDAS(tamMuestra1,tamMuestra2,varianzaPob1,varianzaPob2,diferenciaPromedios,limInf,'a');
                                lienzo.resultadoIC.setText("Con un límite inferior de " + limInf + "\n\nEl grado de confianza calculado es: " + redondeoDecimales(teorema.getCoeficienteConfianza(),4));
                                lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.VISIBLE);
                                lienzo.textView104.setText(" = " + varianzaPob1);
                                lienzo.textView105.setText(" = " + tamMuestra1);
                                lienzo.val1CoeficienteConf.setText(" = " + varianzaPob2);
                                lienzo.val2CoeficienteConf.setText(" = " + tamMuestra2);
                                lienzo.val3CoeficienteConf.setText(" = " + diferenciaPromedios);
                                lienzo.val4CoefConf.setText(" = " + limInf);
                                lienzo.valDeterminante.setText(" = " + teorema.getDeterminante());
                                lienzo.textView66.setText("El segundo paso es buscar el valor de nuestro determinante en las tablas de  la distribución normal estándar. En éste caso el valor encontrado en tablas es: " + teorema.getValTablas());
                                lienzo.textView92.setText("1 - α = 2Φ("+teorema.getDeterminante() + ") - 1 = " + redondeoDecimales(teorema.getCoeficienteConfianza(),4));
                                lienzo.textView93.setText("Con un límite inferior de: "  + limInf + "\n\nEl coeficiente de confianza calculado es: " + redondeoDecimales(teorema.getCoeficienteConfianza(),4));


                            }catch(NumberFormatException e){
                                Toast.makeText(VistaIntervalosConfianzaDifMediasDesviacionesConocidas.this, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else if(lienzo.spinner.getSelectedItemPosition() == 2){

                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());

                    preparEntornoGradoConfianza('b');

                    lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try{

                                varianzaPob1 = convertirStringADouble(lienzo.varianzaPoblacional1CoefConfianzaDifMedias.getText().toString());
                                tamMuestra1 = convertirStringAInt(lienzo.tamMuestral1CoefConfianzaDifMedias.getText().toString());
                                varianzaPob2 = convertirStringADouble(lienzo.varianzaPoblacional2CoefConfianzaDifMedias.getText().toString());
                                tamMuestra2 = convertirStringAInt(lienzo.tamMuestral2CoefConfianzaDifMedias.getText().toString());
                                diferenciaPromedios = convertirStringADouble(lienzo.diferenciaPromediosMuestrales.getText().toString());
                                double limSup = convertirStringADouble(lienzo.limiteConocido.getText().toString());
                                teorema = new ICFDIFMEDIASVARIANZASCONOCIDAS(tamMuestra1,tamMuestra2,varianzaPob1,varianzaPob2,diferenciaPromedios,limSup,'b');
                                lienzo.resultadoIC.setText("Con un límite inferior de " + limSup + "\n\nEl grado de confianza calculado es: " + redondeoDecimales(teorema.getCoeficienteConfianza(),4));
                                lienzo.textView104.setText(" = " + varianzaPob1);
                                lienzo.textView105.setText(" = " + tamMuestra1);
                                lienzo.val1CoeficienteConf.setText(" = " + varianzaPob2);
                                lienzo.val2CoeficienteConf.setText(" = " + tamMuestra2);
                                lienzo.val3CoeficienteConf.setText(" = " + diferenciaPromedios);
                                lienzo.val4CoefConf.setText(" = " + limSup);
                                lienzo.valDeterminante.setText(" = " + teorema.getDeterminante());
                                lienzo.textView66.setText("El segundo paso es buscar el valor de nuestro determinante en las tablas de  la distribución normal estándar. En éste caso el valor encontrado en tablas es: " + teorema.getValTablas());
                                lienzo.textView92.setText("1 - α = 2Φ("+teorema.getDeterminante() + ") - 1 = " + redondeoDecimales(teorema.getCoeficienteConfianza(),4));
                                lienzo.textView93.setText("Con un límite inferior de: "  + limSup + "\n\nEl coeficiente de confianza calculado es: " + redondeoDecimales(teorema.getCoeficienteConfianza(),4));
                                lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.VISIBLE);


                            }catch(NumberFormatException e){
                                Toast.makeText(VistaIntervalosConfianzaDifMediasDesviacionesConocidas.this, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public void prepararEntornoIC() {
        lienzo.datosICDiferenciaMedias.setVisibility(View.VISIBLE);
        lienzo.datosNivelConfianzaDiferenciaMedias.setVisibility(View.GONE);
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.resultadoIC.setText("El resultado es:");


        lienzo.tableRowOpcional2.setVisibility(View.VISIBLE);
        lienzo.tableRowOpcional3.setVisibility(View.VISIBLE);

        lienzo.imageView27.setImageResource(R.drawable.sigmacuadrada1);
        lienzo.imageView28.setImageResource(R.drawable.n1);
        lienzo.imageView10.setImageResource(R.drawable.sigmacuadrada2);
        lienzo.imageView13.setImageResource(R.drawable.n2);
        lienzo.imageView14.setImageResource(R.drawable.unomenosalfa);
        lienzo.imagenDato1tamMinMuestraTclMedia.setImageResource(R.drawable.diferenciapromedios);
        lienzo.imageView18.setImageResource(R.drawable.teoremaicdiferenciamediasvarconocidas);
        lienzo.paso1IC.setText(getPaso1IntervalosConfianzaMediaVarConocida());

        lienzo.imageView20.setImageResource(R.drawable.liminfteoremaicdiferenciamediasvarconocidas);
        lienzo.imageView21.setImageResource(R.drawable.limsupteoremaicdiferenciamediasvarconocidas);
        lienzo.alfaopcional.setImageResource(R.drawable.alfamedios);
        lienzo.tablaopcional.setImageResource(R.drawable.zetaalfamedios);

    }

    public void preparEntornoGradoConfianza(char limite){
        lienzo.datosICDiferenciaMedias.setVisibility(View.GONE);
        lienzo.datosNivelConfianzaDiferenciaMedias.setVisibility(View.VISIBLE);
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.resultadoIC.setText("El resultado es:");
        lienzo.tbRowOpcional1.setVisibility(View.VISIBLE);
        lienzo.tbRowopcional2.setVisibility(View.VISIBLE);
        lienzo.imageView5.setVisibility(View.GONE);

        if(limite == 'a'){

            lienzo.imageViewLimiteConocido.setImageResource(R.drawable.a);
            lienzo.imageView29.setImageResource(R.drawable.sigmacuadrada1);
            lienzo.imageView30.setImageResource(R.drawable.n1);
            lienzo.uno.setImageResource(R.drawable.sigmacuadrada2);
            lienzo.segundo.setImageResource(R.drawable.n2);
            lienzo.ab.setImageResource(R.drawable.diferenciapromedios);
            lienzo.ultimo.setImageResource(R.drawable.a);
            lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremacoeficienteconfianzadifmedias1);
            lienzo.imageView6.setImageResource(R.drawable.liminfteoremacoeficienteconfianzadifmedias1);
            lienzo.determinante.setImageResource(R.drawable.determinanteliminfteoremacoeficienteconfianzadifmedias1);
            lienzo.textViewLimiteConocido.setText("Límite inferior conocido");


        }else if(limite == 'b'){

            lienzo.imageViewLimiteConocido.setImageResource(R.drawable.b);
            lienzo.imageView29.setImageResource(R.drawable.sigmacuadrada1);
            lienzo.imageView30.setImageResource(R.drawable.n1);
            lienzo.uno.setImageResource(R.drawable.sigmacuadrada2);
            lienzo.segundo.setImageResource(R.drawable.n2);
            lienzo.ab.setImageResource(R.drawable.diferenciapromedios);
            lienzo.ultimo.setImageResource(R.drawable.b);
            lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremacoeficienteconfianzadifmedias1);
            lienzo.imageView6.setImageResource(R.drawable.limsupteoremacoeficienteconfianzadifmedias1);
            lienzo.determinante.setImageResource(R.drawable.determinantelimsupteoremacoeficienteconfianzadifmedias1);
            lienzo.textViewLimiteConocido.setText("Límite superior conocido");

        }

    }

    @Override
    public void desplegarBotonBorrar() {
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
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

    }
}