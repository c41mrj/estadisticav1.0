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
import android.widget.Toast;

public class CTMSumatorias extends AppCompatActivity implements TeoremaCentralDelLimite, conversiones{

    private com.example.estadistica.databinding.ActivityCTMBinding lienzo;
    private double mediaPob,desvEstandar,suma;
    private int tamMuestra;
    private TEOREMACENTRALLIMITESUMAM teorema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = com.example.estadistica.databinding.ActivityCTMBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);

        lienzo.botonayuda.setVisibility(View.GONE);

        String elementos[] = {"P(T≤)","P(T>)"};

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
                                lienzo.mediaPoblacionalTCLSumas.setText("");
                                lienzo.desvEstandarTCLSumas.setText("");
                                lienzo.totalTCLSumas.setText("");
                                lienzo.tamMuestraTCLSumas.setText("");
                                lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);
                            }
                        });




            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CTMSumatorias.this, android.R.layout.simple_spinner_dropdown_item,elementos);
        lienzo.spinner.setAdapter(adapter);

        lienzo.datosTCLSumatorias.setVisibility(View.VISIBLE);

        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());

                lienzo.estadisticozConcepto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        lienzo.estadisticozConcepto.setAnimation(R.raw.thinkinglamp);
                        lienzo.estadisticozConcepto.playAnimation();
                        lienzo.estadisticozConcepto.animate()
                                .alpha(0f)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        lienzo.estadisticozConcepto.setImageResource(R.drawable.pregunta);
                                        lienzo.estadisticozConcepto.setAlpha(1f);
                                        LayoutInflater imagenAlert = LayoutInflater.from(CTMSumatorias.this);
                                        final View vista = imagenAlert.inflate(R.layout.estadisticozconcepto,null);
                                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTMSumatorias.this);
                                        alertDialog
                                                .setCancelable(false)
                                                .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                });
                                        AlertDialog botonAyuda1 = alertDialog.create();
                                        botonAyuda1.setTitle("¿Qué es el estadístico Z?");
                                        botonAyuda1.setView(vista);
                                        botonAyuda1.show();
                                    }
                                }).start();



                    }
                });

                if(lienzo.spinner.getSelectedItemPosition() == 0){



                    lienzo.botoncalcular.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);
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
                                                mediaPob = convertirStringADouble(lienzo.mediaPoblacionalTCLSumas.getText().toString());
                                                desvEstandar = convertirStringADouble(lienzo.desvEstandarTCLSumas.getText().toString());
                                                suma = convertirStringADouble(lienzo.totalTCLSumas.getText().toString());
                                                tamMuestra = convertirStringAInt(lienzo.tamMuestraTCLSumas.getText().toString());
                                                teorema = new TEOREMACENTRALLIMITESUMAM(suma,mediaPob,desvEstandar,tamMuestra);
                                                lienzo.Resultado.setText("La probabilidad calculada es: " + teorema.getValTablas());
                                                lienzo.layoutProcedimientoTCL.setVisibility(View.VISIBLE);
                                                colocarDatosEnTablas(mediaPob,desvEstandar,suma,tamMuestra);
                                                lienzo.paso1TCLMedia.setText(getPaso1TCLMedia());
                                                lienzo.imageView17.setImageResource(R.drawable.estadisticozsumas);
                                                lienzo.Paso2TCL.setText(paso2TCLMedia1+teorema.getZ()+paso2TCLMedia2);
                                                lienzo.textView101.setText("El valor encontrado en las tablas de la distribución normal estándar es: " + teorema.getValTablas() + "\n\nPor lo tanto la probabilidad de que la suma sea menor o igual que " + suma + " es de: \n\nP(T≤"+suma+") = " + teorema.getValTablas());
                                            }
                                            catch(Exception exception){
                                                lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTMSumatorias.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                        }
                    });


                }else if(lienzo.spinner.getSelectedItemPosition()==1){


                   lienzo.botoncalcular.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);
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
                                               mediaPob = convertirStringADouble(lienzo.mediaPoblacionalTCLSumas.getText().toString());
                                               desvEstandar = convertirStringADouble(lienzo.desvEstandarTCLSumas.getText().toString());
                                               suma = convertirStringADouble(lienzo.totalTCLSumas.getText().toString());
                                               tamMuestra = convertirStringAInt(lienzo.tamMuestraTCLSumas.getText().toString());

                                               teorema = new TEOREMACENTRALLIMITESUMAM(suma,mediaPob,desvEstandar,tamMuestra);
                                               lienzo.Resultado.setText("La probabilidad calculada es: " + redondeoDecimales((1-teorema.getValTablas()),5));
                                               lienzo.layoutProcedimientoTCL.setVisibility(View.VISIBLE);
                                               colocarDatosEnTablas(mediaPob,desvEstandar,suma,tamMuestra);
                                               lienzo.paso1TCLMedia.setText(getPaso1TCLMedia());
                                               lienzo.imageView17.setImageResource(R.drawable.estadisticozsumas);
                                               lienzo.Paso2TCL.setText(paso2TCLMedia1+teorema.getZ()+paso2TCLMedia2);
                                               lienzo.textView101.setText("El valor encontrado en las tablas de la distribución normal estándar es: " + teorema.getValTablas() + "\n\nEs necesario saber que las tablas acumuladas de la distribución normal estándar nos muestra los valores desde cero hasta Z, es decir que para calcular valores mayores o iguales que Z es necesario calcular el complemento al valor encontrado previamente en tablas.\n\n Por lo tanto la probabilidad de que la suma sea mayor que " + suma + " es de: \n\nP(X>"+suma+") = " + redondeoDecimales((1-teorema.getValTablas()),5));
                                           }
                                           catch(Exception exception){
                                               lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);
                                               lienzo.Resultado.setText("El resultado es:");
                                               Toast.makeText(CTMSumatorias.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
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
    public void colocarDatosEnTablas(double dato1, double dato2, double dato3, int dato4) {
        lienzo.imageView10.setImageResource(R.drawable.t);
        lienzo.imageView13.setImageResource(R.drawable.mediap);
        lienzo.imageView14.setImageResource(R.drawable.tmuestra);
        lienzo.imagenDato1tamMinMuestraTclMedia.setImageResource(R.drawable.desviacionestandar);

        lienzo.dato1TabTCLMedia.setText("= " + suma);
        lienzo.dato2Tab1TCL.setText("= " + mediaPob);
        lienzo.dato3TabTCL.setText("= " + tamMuestra);
        lienzo.dato4TabTCL.setText("= " + desvEstandar);

    }

    @Override
    public void colocarDatosEnTablas(double dato1, double dato2, double dato3, int dato4, int dato5, double dato6, double dato7) {

    }
}