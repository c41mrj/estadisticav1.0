package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.estadistica.databinding.ActivityVistaIntervalosConfianzaBinding;

public class VistaIntervalosConfianzaMediaDesEstandarDesconocida extends AppCompatActivity implements  IntervalosDeConfianza, conversiones, activityManager{

    public static RelativeLayout datosIC;
    public static ImageView imageDatoOpcional,imagenTablaOpcional,teoremaIC,teoremaCC,imageLimInf,imageLimSup,alfaOp,tablOp,ab,igualdad,determinante;
    public static TextView textDesvEstOp;
    public static LinearLayout layoutProcedimientoIC,layoutProcedimientoCC,datosCoeficienteConf;
    public static LottieAnimationView adhesiva;
    private ActivityVistaIntervalosConfianzaBinding lienzo;
    private ICFMEDIADESEDESCONOCIDA teorema;
    private ICFMEDIADESCONOCIDA teoremaAproximacion;
    private double desvEstandar,nivelConfianza,limInf,limSup,promedioMuestral;
    private int tamMuestra;
    private String intervalo = "≤μ≤";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityVistaIntervalosConfianzaBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);


        datosIC = lienzo.datosICMedia;
        datosCoeficienteConf = lienzo.datosGradoConfianza;
        imageDatoOpcional = lienzo.desvEstandarOpcional;
        textDesvEstOp = lienzo.textDesviacionEstandarPob;
        imagenTablaOpcional = lienzo.imageView13;
        teoremaIC = lienzo.imageView18;
        imageLimInf = lienzo.imageView20;
        imageLimSup = lienzo.imageView21;
        alfaOp = lienzo.alfaopcional;
        tablOp = lienzo.tablaopcional;
        ab = lienzo.ab;
        igualdad = lienzo.imageView6;
        teoremaCC = lienzo.teoremaCoeficienteConfianza1;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(VistaIntervalosConfianzaMediaDesEstandarDesconocida.this,android.R.layout.simple_spinner_dropdown_item,getElementosICMedias2());
        lienzo.spinner.setAdapter(adapter);

        layoutProcedimientoIC = lienzo.layoutProcedimientoIc;
        layoutProcedimientoCC = lienzo.layoutProcedimientoCoeficienteConfianza;

        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(lienzo.spinner.getSelectedItemPosition() == 0){

                    prepararEntornoIntervalosConfianzaMedia();
                    desaparecerLayoutsProcedimientos();

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
                                                prepararEntornoAproximacion();
                                                desvEstandar = redondeoDecimales(convertirStringADouble(lienzo.desvEstandarIC.getText().toString()),5);
                                                tamMuestra = convertirStringAInt(lienzo.tamMuestraIC.getText().toString());
                                                promedioMuestral = redondeoDecimales(convertirStringADouble(lienzo.promedioMuestralIC.getText().toString()),5);
                                                nivelConfianza = redondeoDecimales(convertirStringADouble(lienzo.nivelConfianzaIC.getText().toString()),5);
                                                if(tamMuestra>101){
                                                    teoremaAproximacion = new ICFMEDIADESCONOCIDA(desvEstandar,tamMuestra,nivelConfianza,promedioMuestral);
                                                    limInf = teoremaAproximacion.calcLimInf();
                                                    limSup = teoremaAproximacion.calcLimSup();
                                                    intervalo = limInf + "<μ<" + limSup;
                                                    lienzo.textView80.setText("=" + promedioMuestral);
                                                    lienzo.desvEstandar.setText("=" + desvEstandar);
                                                    lienzo.dato3TabTCL.setText("=" + nivelConfianza);
                                                    lienzo.dato4TabTCL.setText("=" + tamMuestra);
                                                    lienzo.valoralfaopcional.setText("="+ teoremaAproximacion.getConfianza());
                                                    lienzo.valortablaopcional.setText("=" + teoremaAproximacion.getValorTablas());
                                                    lienzo.resultadoIC.setText("El intervalo de confianza calculado es:\n" + intervalo);
                                                    lienzo.layoutProcedimientoIc.setVisibility(View.VISIBLE);
                                                    lienzo.paso1IC.setVisibility(View.VISIBLE);
                                                    lienzo.conclusionIC1.setText("Se puede asegurar con un nivel de confianza del " + redondeoDecimales((nivelConfianza*100)
                                                            ,5) + "% que el parámetro poblacional se encontrará en el intervalo:\n\n" + intervalo);
                                                }else if(tamMuestra>0){
                                                    teorema = new ICFMEDIADESEDESCONOCIDA(desvEstandar,tamMuestra,nivelConfianza,promedioMuestral);
                                                    limInf = teorema.calcLimInf();
                                                    limSup = teorema.calcLimSup();
                                                    intervalo = limInf + "<μ<" + limSup;
                                                    lienzo.textView80.setText("=" + promedioMuestral);
                                                    lienzo.desvEstandar.setText("=" + desvEstandar);
                                                    lienzo.dato3TabTCL.setText("=" + nivelConfianza);
                                                    lienzo.dato4TabTCL.setText("=" + tamMuestra);
                                                    lienzo.valoralfaopcional.setText("="+ teorema.getConfianza());
                                                    lienzo.valortablaopcional.setText("=" + teorema.getValorTablas());
                                                    lienzo.resultadoIC.setText("El intervalo de confianza calculado es:\n" + intervalo);
                                                    lienzo.layoutProcedimientoIc.setVisibility(View.VISIBLE);
                                                    lienzo.paso1IC.setVisibility(View.VISIBLE);
                                                    lienzo.conclusionIC1.setText("Se puede asegurar con un nivel de confianza del " + redondeoDecimales((nivelConfianza*100)
                                                            ,5) + "% que el parámetro poblacional se encontrará en el intervalo:\n\n" + intervalo);
                                                }else{
                                                    Toast.makeText(VistaIntervalosConfianzaMediaDesEstandarDesconocida.this, "Ingresa un valor diferente a 0 como tamaño de muestra", Toast.LENGTH_SHORT).show();
                                                }
                                            }catch(Exception e){
                                                Toast.makeText(VistaIntervalosConfianzaMediaDesEstandarDesconocida.this, "Por favor ingresa correctamente los datos", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                        }
                    });

                }else if(lienzo.spinner.getSelectedItemPosition() == 1){

                    desaparecerLayoutsProcedimientos();
                    prepararEntornoCCMediasVarDesc('a');


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
                                                limInf = redondeoDecimales(convertirStringADouble(lienzo.lim.getText().toString()),5);
                                                desvEstandar = redondeoDecimales(convertirStringADouble(lienzo.de.getText().toString()),5);
                                                promedioMuestral = redondeoDecimales(convertirStringADouble(lienzo.equis.getText().toString()),5);
                                                tamMuestra = convertirStringAInt(lienzo.ene.getText().toString());
                                                teorema = new ICFMEDIADESEDESCONOCIDA(promedioMuestral,tamMuestra,desvEstandar);
                                                double coeficienteConfianza = teorema.calcGradoConfianza('a',limInf);
                                                lienzo.val1CoeficienteConf.setText("= " + promedioMuestral);
                                                lienzo.val2CoeficienteConf.setText("= " + desvEstandar);
                                                lienzo.val4CoefConf.setText("= " + tamMuestra);
                                                lienzo.val3CoeficienteConf.setText("= " + limSup);
                                                if(coeficienteConfianza<0){
                                                    lienzo.resultadoIC.setText("Resultado imposible, no existen las probabilidades negativas\n\n¿Haz ingresado correctamente los datos?\n\nCONSEJO: Asegurate de haber ingresado correctamente el valor del LIMITE INFERIOR");
                                                    lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
                                                }else if(coeficienteConfianza>1){
                                                    lienzo.resultadoIC.setText("Resultado imposible, no existen las probabilidades mayores que 1\n\n¿Haz ingresado correctamente los datos?\n\nCONSEJO: Asegurate de haber ingresado correctamente el valor del LIMITE INFERIOR");
                                                    lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
                                                }else{
                                                    lienzo.resultadoIC.setText("Con un límite inferior de " + limInf +", el coeficiente de confianza calculado es:\n"+coeficienteConfianza);
                                                    lienzo.textView66.setText(getPaso2CoeficienteConfianzaVarDesconocida()+ teorema.getValorTablas());
                                                    lienzo.valDeterminante.setText("= " + teorema.getDeterminante());
                                                    lienzo.textView95.setText("Podemos concluir que con un grado de confianza del " + redondeoDecimales((coeficienteConfianza*100),5)
                                                            +"% el intervalo de confianza estará entre " + limInf + " y " + teorema.getLimSup());
                                                    lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.VISIBLE);
                                                    lienzo.textView92.setText("1-α = (2* " + teorema.getValorTablas()+") - 1 = " + coeficienteConfianza);
                                                }
                                            }catch(Exception e){
                                                Toast.makeText(VistaIntervalosConfianzaMediaDesEstandarDesconocida.this, "Por favor ingresa correctamente todos los datos requeridos", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    });


                }else if(lienzo.spinner.getSelectedItemPosition() == 2){

                    desaparecerLayoutsProcedimientos();
                    prepararEntornoCCMediasVarDesc('b');

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
                                                limSup = redondeoDecimales(convertirStringADouble(lienzo.lim.getText().toString()),5);
                                                desvEstandar = redondeoDecimales(convertirStringADouble(lienzo.de.getText().toString()),5);
                                                promedioMuestral = redondeoDecimales(convertirStringADouble(lienzo.equis.getText().toString()),5);
                                                tamMuestra = convertirStringAInt(lienzo.ene.getText().toString());
                                                teorema = new ICFMEDIADESEDESCONOCIDA(promedioMuestral,tamMuestra,desvEstandar);
                                                double coeficienteConfianza = teorema.calcGradoConfianza('b',limSup);
                                                lienzo.val1CoeficienteConf.setText("= " + promedioMuestral);
                                                lienzo.val2CoeficienteConf.setText("= " + desvEstandar);
                                                lienzo.val4CoefConf.setText("= " + tamMuestra);
                                                lienzo.val3CoeficienteConf.setText("= " + limSup);
                                                if(coeficienteConfianza<0){
                                                    lienzo.resultadoIC.setText("Resultado imposible, no existen las probabilidades negativas\n\n¿Haz ingresado correctamente los datos?\n\nCONSEJO: Asegurate de haber ingresado correctamente el valor del LIMITE INFERIOR");
                                                    lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
                                                }else if(coeficienteConfianza>1){
                                                    lienzo.resultadoIC.setText("Resultado imposible, no existen las probabilidades mayores que 1\n\n¿Haz ingresado correctamente los datos?\n\nCONSEJO: Asegurate de haber ingresado correctamente el valor del LIMITE INFERIOR");
                                                    lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
                                                }else{
                                                   lienzo.resultadoIC.setText("Con un límite superior de " + limSup +", el coeficiente de confianza calculado es:\n"+coeficienteConfianza);
                                                   lienzo.textView66.setText(getPaso2CoeficienteConfianzaVarDesconocida()+teorema.getValorTablas());
                                                   lienzo.valDeterminante.setText("= " + teorema.getDeterminante());
                                                   lienzo.textView95.setText("Podemos concluir que con un grado de confianza del " + redondeoDecimales((coeficienteConfianza*100),5)
                                                           +"% el intervalo de confianza estará entre " + teorema.getLimInf() + " y " + limSup);
                                                   lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.VISIBLE);
                                                   lienzo.textView92.setText("1-α = 1 - (2* " + teorema.getValorTablas()+") = " + coeficienteConfianza);
                                               }
                                            }catch(Exception e){
                                                Toast.makeText(VistaIntervalosConfianzaMediaDesEstandarDesconocida.this, "Por favor ingresa correctamente todos los datos requeridos", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                        }
                    });


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        lienzo.delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.lim.setText("");
                lienzo.de.setText("");
                lienzo.equis.setText("");
                lienzo.ene.setText("");
                lienzo.desvEstandarIC.setText("");
                lienzo.tamMuestraIC.setText("");
                lienzo.promedioMuestralIC.setText("");
                lienzo.nivelConfianzaIC.setText("");
                desaparecerLayoutsProcedimientos();
            }
        });

        lienzo.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               desplegarNotaCalcularLimiteCoeficienteConfianza(VistaIntervalosConfianzaMediaDesEstandarDesconocida.this);
            }
        });


    }

    @Override
    public void desaparecerLayoutsProcedimientos() {
        lienzo.resultadoIC.setText("El resultado es: ");
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
    }

    @Override
    public void prepararEntornoIntervalosConfianzaMedia() {
        lienzo.datosICMedia.setVisibility(View.VISIBLE);
        lienzo.datosGradoConfianza.setVisibility(View.GONE);
        lienzo.imageView10.setImageResource(R.drawable.promediomuestral);
        lienzo.desvEstandarOpcional.setImageResource(R.drawable.snmenosuno);
        lienzo.imageView13.setImageResource(R.drawable.snmenosuno);
        lienzo.imageView14.setImageResource(R.drawable.unomenosalfa);
        lienzo.imageView18.setImageResource(R.drawable.teoremaicmedias);
        lienzo.alfaopcional.setImageResource(R.drawable.alfamedios);
        lienzo.tablaopcional.setImageResource(R.drawable.tealfamedios);
        lienzo.imageView20.setImageResource(R.drawable.liminfteoremaicmedias);
        lienzo.imageView21.setImageResource(R.drawable.limsupteoremaicmedias);
        lienzo.paso1IC.setText(getPaso1IntervalosConfianzaMediaVarDesconocida());
    }


    @Override
    public void prepararEntornoCCMediasVarDesc(char limiteConocido){
        lienzo.datosICMedia.setVisibility(View.GONE);
        lienzo.datosGradoConfianza.setVisibility(View.VISIBLE);
        lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.coeficienteconfianza2);
        if(limiteConocido == 'a'){
            lienzo.imageOp.setImageResource(R.drawable.a);
            lienzo.textViewLimite.setText("Límite inferior del intervalo de confianza");
            lienzo.ab.setImageResource(R.drawable.a);
            lienzo.desviacionEstandarCoeficienteConfianza.setImageResource(R.drawable.snmenosuno);
            lienzo.segundo.setImageResource(R.drawable.snmenosuno);
            lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.coeficienteconfianzamediavardesconocida);
            lienzo.imageView6.setImageResource(R.drawable.liminfcoeficienteconfianzamediavardesconocida);
            lienzo.determinante.setImageResource(R.drawable.liminfdiscricoeficienteconfianzamediavardesconocida);
        }else if(limiteConocido == 'b'){
            lienzo.imageOp.setImageResource(R.drawable.b);
            lienzo.textViewLimite.setText("Límite superior del intervalo de confianza");
            lienzo.ab.setImageResource(R.drawable.b);
            lienzo.desviacionEstandarCoeficienteConfianza.setImageResource(R.drawable.snmenosuno);
            lienzo.segundo.setImageResource(R.drawable.snmenosuno);
            lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.coeficienteconfianzamediavardesconocida);
            lienzo.imageView6.setImageResource(R.drawable.limsupcoeficienteconfianzamediavardesconocida);
            lienzo.determinante.setImageResource(R.drawable.limsupdiscricoeficienteconfianzamediavardesconocida);
        }
    }

    @Override
    public void prepararEntornoAproximacion() {
        lienzo.datosICMedia.setVisibility(View.VISIBLE);
        lienzo.datosGradoConfianza.setVisibility(View.GONE);
        lienzo.imageView10.setImageResource(R.drawable.promediomuestral);
        lienzo.desvEstandarOpcional.setImageResource(R.drawable.snmenosuno);
        lienzo.imageView13.setImageResource(R.drawable.snmenosuno);
        lienzo.imageView14.setImageResource(R.drawable.unomenosalfa);
        lienzo.imageView18.setImageResource(R.drawable.teoremaicmediasaproximacion);
        lienzo.alfaopcional.setImageResource(R.drawable.alfamedios);
        lienzo.tablaopcional.setImageResource(R.drawable.zetaalfamedios);
        lienzo.imageView20.setImageResource(R.drawable.liminfteoremaicmediasaproximacion);
        lienzo.imageView21.setImageResource(R.drawable.limsupteoremaicmediasaproximacion);
        lienzo.paso1IC.setText("Al observar el teorema presentado anteriormente, nos podemos percatar que es necesario buscarel valor de:\nZ(α/2)\nEn las tablas de la " +
                "distribución normal estándar. Normalmente deberiamos buscar el valor de: \nt(α/2)\nEn las tablas de la distribución t-student, en casos cómo este, donde el tamaño de muestra sea mayor que 101 (Este software solo puede encontrar valores con un grado de confianza menor a 100), se utilizará el teorema para aproximar el resultado a la distribución normal estándar. Donde:");
    }

    @Override
    public void prepararEntornoIC() {

    }
}