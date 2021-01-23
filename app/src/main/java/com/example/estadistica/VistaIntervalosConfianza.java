package com.example.estadistica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
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

public class VistaIntervalosConfianza extends AppCompatActivity implements IntervalosDeConfianza,conversiones {


    public static RelativeLayout datosIC,datosTamMinimoMuestra;
    public static LinearLayout datosCoeficienteConfianza;
    public static TextView resultado,textViewOp;
    public static LinearLayout layoutProcedimientoIC,layoutProcedimientoTamMinimoMuestra,layoutCoeficienteConfianza;
    public static ImageView imageTeorema,imageLiminf,imageLimsup,alfaOpcional,imageValTablas,imageTeoremaTamMinimoMuestra,imageViewDistribucion,imageTeoremaCoeficienteConfianza,igualdad,determinante,limiteConocido,ab;
    public static LottieAnimationView botonPorque1,botonNotas;
    private double promedioMuestral,valTablas,desvEstandar,nivelConfianza,limInf,limSup,coeficienteConfianza;
    private float errorMuestral;
    private int tamMuestra, tamMinimoMuestra;
    private String intervalo = "≤μ≤";
    private ICFMEDIADESCONOCIDA teorema;
    ActivityVistaIntervalosConfianzaBinding lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityVistaIntervalosConfianzaBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        String elementos[] = {"Intervalo de confianza para la media","Tamaño mínimo de muestra","Coeficiente de confianza si se conoce el límite inferior del intervalo de confianza","Coeficiente de confianza si se conoce el límite superior del intervalo de confianza"};
        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,elementos);
        lienzo.spinner.setAdapter(adapter);
        datosIC = findViewById(R.id.datosICMedia);
        datosTamMinimoMuestra = lienzo.datosTamMinimoMuestra;
        datosCoeficienteConfianza = lienzo.datosGradoConfianza;
        resultado = lienzo.resultadoIC;
        layoutProcedimientoIC = lienzo.layoutProcedimientoIc;
        imageTeorema = findViewById(R.id.imageView18);
        imageLiminf = findViewById(R.id.imageView20);
        imageLimsup = findViewById(R.id.imageView21);
        alfaOpcional = findViewById(R.id.alfaopcional);
        imageValTablas = lienzo.tablaopcional;
        imageTeoremaTamMinimoMuestra = findViewById(R.id.imageView3);
        imageViewDistribucion = findViewById(R.id.imageView4);
        imageTeoremaCoeficienteConfianza = findViewById(R.id.teoremaCoeficienteConfianza1);
        igualdad = findViewById(R.id.imageView6);
        determinante = findViewById(R.id.determinante);
        botonPorque1 = findViewById(R.id.imageView7);
        botonNotas = findViewById(R.id.imageView5);
        limiteConocido = findViewById(R.id.imageOp);
        textViewOp = findViewById(R.id.textViewLimite);
        ab = findViewById(R.id.ab);
        layoutProcedimientoTamMinimoMuestra = lienzo.layoutProcedimientoTamMinimoMuestra;
        layoutCoeficienteConfianza = lienzo.layoutProcedimientoCoeficienteConfianza;

        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());

                if(lienzo.spinner.getSelectedItemPosition()==0){

                    prepararEntornoIntervalosConfianzaMedia();

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
                                            lienzo.desvEstandarIC.setText("");
                                            lienzo.promedioMuestralIC.setText("");
                                            lienzo.tamMuestraIC.setText("");
                                            lienzo.nivelConfianzaIC.setText("");
                                        }
                                    });
                        }
                    });


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
                                                lienzo.layoutProcedimientoIc.setVisibility(View.VISIBLE);
                                                desvEstandar = convertirStringADouble(lienzo.desvEstandarIC.getText().toString());
                                                promedioMuestral = convertirStringADouble(lienzo.promedioMuestralIC.getText().toString());
                                                tamMuestra = convertirStringAInt(lienzo.tamMuestraIC.getText().toString());
                                                nivelConfianza = convertirStringADouble(lienzo.nivelConfianzaIC.getText().toString());
                                                teorema = new ICFMEDIADESCONOCIDA(desvEstandar,tamMuestra,nivelConfianza,promedioMuestral);
                                                limInf = teorema.calcLimInf();
                                                limSup = teorema.calcLimSup();
                                                lienzo.resultadoIC.setText("El intervalo de confianza calculado es:\n\n\t\t\t\t"+limInf+intervalo+limSup);
                                                lienzo.textView80.setText("= " + promedioMuestral);
                                                lienzo.desvEstandar.setText("= " + desvEstandar);
                                                lienzo.dato3TabTCL.setText("= " +nivelConfianza);
                                                lienzo.dato4TabTCL.setText("= " +tamMuestra);
                                                lienzo.paso1IC.setText(getPaso1IntervalosConfianzaMediaVarConocida());
                                                lienzo.valoralfaopcional.setText("= " + redondeoDecimales(((1-nivelConfianza)/2),5));
                                                lienzo.valortablaopcional.setText("= " + teorema.getValorTablas());
                                                lienzo.conclusionIC1.setText("Se puede asegurar que con una confianza de " + nivelConfianza + " el parámetro media poblacional se va a encontrar en el intervalo:\n\n" + limInf+intervalo+limSup);
                                            }
                                            catch(Exception exception){
                                                desaparecerLayoutsProcedimientos();
                                                lienzo.resultadoIC.setText("El resultado es:");
                                                Toast.makeText(VistaIntervalosConfianza.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                        }
                    });

                }else if(lienzo.spinner.getSelectedItemPosition()==1){


                    prepararEntornoTamMinimoMuestraMediaVarConocida();

                    lienzo.delete2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lienzo.delete2.setAnimation(R.raw.delete);
                            lienzo.delete2.animate()
                                    .alpha(0f)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            lienzo.delete2.setImageResource(R.drawable.gomadeborrar);
                                            lienzo.delete2.setAlpha(1f);
                                            lienzo.errorMuestralICmedias.setText("");
                                            lienzo.desvEstandarTamMinimoMuestraIC.setText("");
                                            lienzo.nivelConfianzaICTamMinimoMuestra.setText("");
                                            prepararEntornoTamMinimoMuestraMediaVarConocida();
                                        }
                                    });
                        }
                    });


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
                                                lienzo.layoutProcedimientoTamMinimoMuestra.setVisibility(View.VISIBLE);
                                                errorMuestral = convertirStringAFloat(lienzo.errorMuestralICmedias.getText().toString());
                                                desvEstandar = convertirStringADouble(lienzo.desvEstandarTamMinimoMuestraIC.getText().toString());
                                                valTablas = convertirStringADouble(lienzo.nivelConfianzaICTamMinimoMuestra.getText().toString());
                                                teorema = new ICFMEDIADESCONOCIDA(desvEstandar,valTablas,errorMuestral);
                                                tamMinimoMuestra = teorema.obtenerTamMinimoMuestra();
                                                lienzo.resultadoIC.setText("El tamaño mínimo de muestra calculado es: " + tamMinimoMuestra);
                                                lienzo.paso1Layout2.setText(getPaso1TamMinimoMuestra());
                                                lienzo.epsilon.setText("= " + errorMuestral);
                                                lienzo.sigma.setText("= "+ desvEstandar);
                                                lienzo.unomenosalfa.setText("= "+ valTablas);
                                                lienzo.textView3.setText("El tamaño mínimo de muestra necesario para satisfacer las condiciones del problema es de: " + tamMinimoMuestra);
                                            }catch(Exception e){
                                                desaparecerLayoutsProcedimientos();
                                                lienzo.resultadoIC.setText("El resultado es: ");
                                                Toast.makeText(VistaIntervalosConfianza.this, "Error, por favor ingresa correctamente los datos correspondientes", Toast.LENGTH_SHORT).show();
                                                lienzo.layoutProcedimientoTamMinimoMuestra.setVisibility(View.GONE);
                                            }

                                        }
                                    });

                        }
                    });

                }else if(lienzo.spinner.getSelectedItemPosition() == 2){

                    prepararEntornoCoeficienteConfianzaMediaVarConocida('a');

                    lienzo.delete2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lienzo.delete2.setAnimation(R.raw.delete);
                            lienzo.delete2.animate()
                                    .alpha(0f)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            lienzo.delete2.setImageResource(R.drawable.gomadeborrar);
                                            lienzo.delete2.setAlpha(1f);
                                            prepararEntornoCoeficienteConfianzaMediaVarConocida('a');
                                            lienzo.lim.setText("");
                                            lienzo.de.setText("");
                                            lienzo.equis.setText("");
                                            lienzo.ene.setText("");
                                        }
                                    });
                        }
                    });

                    lienzo.imageView5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lienzo.imageView5.setAnimation(R.raw.thinkinglamp);
                            lienzo.imageView5.animate()
                                    .alpha(0f)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            lienzo.imageView5.setImageResource(R.drawable.adhesivas);
                                            lienzo.imageView5.setAlpha(1f);
                                            LayoutInflater imagenAlert = LayoutInflater.from(VistaIntervalosConfianza.this);
                                            final View vista = imagenAlert.inflate(R.layout. como_obtener_limites,null);
                                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(VistaIntervalosConfianza.this);
                                            alertDialog
                                                    .setCancelable(true)
                                                    .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    });
                                            AlertDialog botonAyuda1 = alertDialog.create();
                                            botonAyuda1.setTitle("Si conozco un límite del intervalo de confianza\n¿Cómo calculo el otro límite?");
                                            botonAyuda1.setView(vista);
                                            botonAyuda1.show();
                                        }
                                    });
                        }
                    });

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
                                                prepararTeoremasCoeficienteConfianzaMediaVarConocida('a',VistaIntervalosConfianza.this);
                                                promedioMuestral = convertirStringADouble(lienzo.equis.getText().toString());
                                                limInf = convertirStringADouble(lienzo.lim.getText().toString());
                                                desvEstandar = convertirStringADouble(lienzo.de.getText().toString());
                                                tamMuestra = convertirStringAInt(lienzo.ene.getText().toString());
                                                teorema = new ICFMEDIADESCONOCIDA(promedioMuestral,tamMuestra,desvEstandar);
                                                coeficienteConfianza = teorema.calcGradoConfianza('a',limInf);
                                                limSup = (2*promedioMuestral) - limInf;
                                                limSup = redondeoDecimales(limSup,5);
                                                lienzo.resultadoIC.setText("Con un límite superior igual a: " + limSup+"\n\nEl coeficiente de confianza calculado es: " + coeficienteConfianza);
                                                lienzo.val1CoeficienteConf.setText("= " + promedioMuestral);
                                                lienzo.val2CoeficienteConf.setText("= " + desvEstandar);
                                                lienzo.val3CoeficienteConf.setText("= " +limInf);
                                                lienzo.val4CoefConf.setText("= " +tamMuestra);
                                                lienzo.valDeterminante.setText("= " + teorema.getDeterminante());
                                                lienzo.textView66.setText(getPaso2CoeficienteConfianzaMediaVarConocida() + teorema.getValorTablas());
                                                lienzo.textView92.setText("1-α= (2* " + teorema.getValorTablas() +") -1 = " +coeficienteConfianza);
                                                lienzo.textView95.setText(getConclusionCoeficienteConfianzaMedia(limInf,coeficienteConfianza));
                                            }catch(Exception e){
                                                desaparecerLayoutsProcedimientos();
                                                lienzo.resultadoIC.setText("El resultado es:");
                                                Toast.makeText(VistaIntervalosConfianza.this, "Por favor verifica que los datos fueron ingresados correctamente", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                        }
                    });




                }else if(lienzo.spinner.getSelectedItemPosition() == 3){

                    prepararEntornoCoeficienteConfianzaMediaVarConocida('b');

                    lienzo.delete2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lienzo.delete2.setAnimation(R.raw.delete);
                            lienzo.delete2.animate()
                                    .alpha(0f)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            lienzo.delete2.setImageResource(R.drawable.gomadeborrar);
                                            lienzo.delete2.setAlpha(1f);
                                            lienzo.lim.setText("");
                                            lienzo.de.setText("");
                                            lienzo.equis.setText("");
                                            lienzo.ene.setText("");
                                            prepararEntornoCoeficienteConfianzaMediaVarConocida('b');
                                        }
                                    });
                        }
                    });

                    lienzo.imageView5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lienzo.imageView5.setAnimation(R.raw.thinkinglamp);
                            lienzo.imageView5.animate()
                                    .alpha(0f)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                            lienzo.imageView5.setImageResource(R.drawable.adhesivas);
                                            lienzo.imageView5.setAlpha(1f);
                                            LayoutInflater imagenAlert = LayoutInflater.from(VistaIntervalosConfianza.this);
                                            final View vista = imagenAlert.inflate(R.layout. como_obtener_limites,null);
                                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(VistaIntervalosConfianza.this);
                                            alertDialog
                                                    .setCancelable(true)
                                                    .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            dialogInterface.cancel();
                                                        }
                                                    });
                                            AlertDialog botonAyuda1 = alertDialog.create();
                                            botonAyuda1.setTitle("Si conozco un límite del intervalo de confianza\n¿Cómo calculo el otro límite?");
                                            botonAyuda1.setView(vista);
                                            botonAyuda1.show();
                                        }
                                    });
                        }
                    });

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
                                                prepararTeoremasCoeficienteConfianzaMediaVarConocida('b',VistaIntervalosConfianza.this);
                                                promedioMuestral = convertirStringADouble(lienzo.equis.getText().toString());
                                                limSup = convertirStringADouble(lienzo.lim.getText().toString());
                                                desvEstandar = convertirStringADouble(lienzo.de.getText().toString());
                                                tamMuestra = convertirStringAInt(lienzo.ene.getText().toString());
                                                teorema = new ICFMEDIADESCONOCIDA(promedioMuestral,tamMuestra,desvEstandar);
                                                coeficienteConfianza = teorema.calcGradoConfianza('b',limSup);
                                                limInf = (2*promedioMuestral) - limSup;
                                                limInf = redondeoDecimales(limInf,5);
                                                lienzo.resultadoIC.setText("Con un límite inferior igual a: " + limInf+"\n\nEl coeficiente de confianza calculado es: " + coeficienteConfianza);
                                                lienzo.val1CoeficienteConf.setText("= " + promedioMuestral);
                                                lienzo.val2CoeficienteConf.setText("= " + desvEstandar);
                                                lienzo.val3CoeficienteConf.setText("= " +limSup);
                                                lienzo.val4CoefConf.setText("= " +tamMuestra);
                                                lienzo.valDeterminante.setText("= " + teorema.getDeterminante());
                                                lienzo.textView66.setText(getPaso2CoeficienteConfianzaMediaVarConocida() + teorema.getValorTablas());
                                                lienzo.textView92.setText("1-α= 1 - (2* " + teorema.getValorTablas() +")  = " +coeficienteConfianza);
                                                lienzo.textView95.setText(getConclusionCoeficienteConfianzaMedia(limSup,coeficienteConfianza));
                                            }catch(Exception e){
                                                desaparecerLayoutsProcedimientos();
                                                lienzo.resultadoIC.setText("El resultado es:");
                                                Toast.makeText(VistaIntervalosConfianza.this, "Por favor verifica que los datos fueron ingresados correctamente", Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public void prepararEntornoIC() {

    }
}