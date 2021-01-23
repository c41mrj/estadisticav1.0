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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.estadistica.databinding.ActivityCTMBinding;

public class CTMDifMedias extends AppCompatActivity implements TeoremaCentralDelLimite,conversiones{

    public static RelativeLayout datosTCL,datosTamMinimoMuestra1,datosTamMinimoMuestra2;
    public static ImageView botonAyuda;
    private ActivityCTMBinding lienzo;
    private TEOREMACENTRALLIMITEDIFERENCIAMEDIAS teorema;
    private double diferenciaPromedios,mediaPob1,mediaPob2,varianza1,varianza2,probabilidad,nuevaDiferenciaMedias,nuevaDiferenciaPromedios;
    private int tamMuestra1,tamMuestra2,tamMinimoMuestras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityCTMBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        datosTCL = vista.findViewById(R.id.datosTCLDiferenciaMedias);
        datosTamMinimoMuestra1 = vista.findViewById(R.id.datosTamMinimoMuestraDifMedias);
        datosTamMinimoMuestra2 = vista.findViewById(R.id.datosTamMinimoMuestraDifMedias2);
        botonAyuda = vista.findViewById(R.id.botonayuda);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CTMDifMedias.this, android.R.layout.simple_spinner_dropdown_item,getDiferenciaMedias());
        lienzo.spinner.setAdapter(adapter);

        lienzo.estadisticozConcepto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.estadisticozConcepto2.setAnimation(R.raw.thinkinglamp);
                lienzo.estadisticozConcepto2.playAnimation();
                lienzo.estadisticozConcepto2.animate()
                        .alpha(0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                lienzo.estadisticozConcepto2.setImageResource(R.drawable.pregunta);
                                lienzo.estadisticozConcepto2.setAlpha(1f);
                                LayoutInflater imagenAlert = LayoutInflater.from(CTMDifMedias.this);
                                final View vista = imagenAlert.inflate(R.layout.estadisticozconcepto,null);
                                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTMDifMedias.this);
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

        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(lienzo.spinner.getSelectedItemPosition() == 0){

                    desplegarDatosTCLDifMedias();

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
                                            lienzo.diferenciaDePromediosTCLDiferenciaMedias.setText("");
                                            lienzo.mediaPob1TCLDiferenciaMedias.setText("");
                                            lienzo.varianza1TCLDifMedias.setText("");
                                            lienzo.tamMuestra1TCLDifMedias.setText("");
                                            lienzo.mediaPob2TCLDiferenciaMedias.setText("");
                                            lienzo.varianza2TCLDifMedias.setText("");
                                            lienzo.tamMuestra2TCLDifMedias.setText("");
                                            lienzo.layoutProcedimientoTCLDifMedias.setVisibility(View.GONE);
                                        }
                                    });
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
                                                diferenciaPromedios = convertirStringADouble(lienzo.diferenciaDePromediosTCLDiferenciaMedias.getText().toString());
                                                mediaPob1 = convertirStringADouble(lienzo.mediaPob1TCLDiferenciaMedias.getText().toString());
                                                varianza1 = convertirStringADouble(lienzo.varianza1TCLDifMedias.getText().toString());
                                                tamMuestra1 = convertirStringAInt(lienzo.tamMuestra1TCLDifMedias.getText().toString());
                                                mediaPob2 = convertirStringADouble(lienzo.mediaPob2TCLDiferenciaMedias.getText().toString());
                                                varianza2 = convertirStringADouble(lienzo.varianza2TCLDifMedias.getText().toString());
                                                tamMuestra2 = convertirStringAInt(lienzo.tamMuestra2TCLDifMedias.getText().toString());
                                                double diferenciaMedias = redondeoDecimales((mediaPob1-mediaPob2),5);
                                                teorema = new TEOREMACENTRALLIMITEDIFERENCIAMEDIAS(diferenciaPromedios,diferenciaMedias,tamMuestra1,tamMuestra2,varianza1,varianza2);
                                                lienzo.Resultado.setText("La probabilidad calculada es: " + redondeoDecimales((teorema.getValTablas()),5));
                                                colocarDatosEnTablas(diferenciaPromedios,mediaPob1,varianza1,tamMuestra1,tamMuestra2,mediaPob2,varianza2);
                                                lienzo.paso1TCLDifMedias.setText(paso1TCLDifMedias);
                                                lienzo.Paso2TCLDifMedias.setText(paso2TCLDifMedias1+teorema.getZ()+paso2TCLDifMedias2);
                                                lienzo.conclusionTCLDifMedias.setText("El valor encontrado en las tablas acumuladas de la distribución normal estándar es: " + teorema.getValTablas()+ ".\n\nPor lo tanto la probabilidad de que la diferencia de promedios sea mayor o igual que " + diferenciaPromedios +" es de: \n\n" + redondeoDecimales((teorema.getValTablas()),5) + " = " + redondeoDecimales(((teorema.getValTablas())*100),5) + "%");
                                            }
                                            catch(Exception exception){
                                                lienzo.layoutProcedimientoTCLDifMedias.setVisibility(View.GONE);
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTMDifMedias.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                        }
                    });

                }else if(lienzo.spinner.getSelectedItemPosition() == 1){

                    desplegarDatosTCLDifMedias();

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
                                            lienzo.diferenciaDePromediosTCLDiferenciaMedias.setText("");
                                            lienzo.mediaPob1TCLDiferenciaMedias.setText("");
                                            lienzo.varianza1TCLDifMedias.setText("");
                                            lienzo.tamMuestra1TCLDifMedias.setText("");
                                            lienzo.mediaPob2TCLDiferenciaMedias.setText("");
                                            lienzo.varianza2TCLDifMedias.setText("");
                                            lienzo.tamMuestra2TCLDifMedias.setText("");
                                            lienzo.layoutProcedimientoTCLDifMedias.setVisibility(View.GONE);
                                        }
                                    });
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
                                                diferenciaPromedios = convertirStringADouble(lienzo.diferenciaDePromediosTCLDiferenciaMedias.getText().toString());
                                                mediaPob1 = convertirStringADouble(lienzo.mediaPob1TCLDiferenciaMedias.getText().toString());
                                                varianza1 = convertirStringADouble(lienzo.varianza1TCLDifMedias.getText().toString());
                                                tamMuestra1 = convertirStringAInt(lienzo.tamMuestra1TCLDifMedias.getText().toString());
                                                mediaPob2 = convertirStringADouble(lienzo.mediaPob2TCLDiferenciaMedias.getText().toString());
                                                varianza2 = convertirStringADouble(lienzo.varianza2TCLDifMedias.getText().toString());
                                                tamMuestra2 = convertirStringAInt(lienzo.tamMuestra2TCLDifMedias.getText().toString());
                                                double diferenciaMedias = redondeoDecimales((mediaPob1-mediaPob2),5);
                                                teorema = new TEOREMACENTRALLIMITEDIFERENCIAMEDIAS(diferenciaPromedios,diferenciaMedias,tamMuestra1,tamMuestra2,varianza1,varianza2);
                                                lienzo.Resultado.setText("La probabilidad calculada es: " + redondeoDecimales((1-teorema.getValTablas()),5));
                                                colocarDatosEnTablas(diferenciaPromedios,mediaPob1,varianza1,tamMuestra1,tamMuestra2,mediaPob2,varianza2);
                                                lienzo.paso1TCLDifMedias.setText(paso1TCLDifMedias);
                                                lienzo.Paso2TCLDifMedias.setText(paso2TCLDifMedias1+teorema.getZ()+paso2TCLDifMedias2);
                                                lienzo.conclusionTCLDifMedias.setText("El valor encontrado en las tablas acumuladas de la distribución normal estándar es: " + teorema.getValTablas()+ ".\n\nEs necesario saber que las tablas acumuladas de la distribución normal estándar nos dan los valores desde 0 hasta Z, para calcular valores mayores que Z es necesario obtener el complemento de la probabilidad encontrada en tablas.\n\n Por lo tanto la probabilidad de que la diferencia de promedios sea mayor que " + diferenciaPromedios +" es de:  (1-" + teorema.getValTablas() + ") = " + redondeoDecimales((1-teorema.getValTablas()),5) + " = " +redondeoDecimales(((1-teorema.getValTablas())*100),5) + "%");
                                            }
                                            catch(Exception exception){
                                                lienzo.layoutProcedimientoTCLDifMedias.setVisibility(View.GONE);
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTMDifMedias.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
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

    }

    @Override
    public void colocarDatosEnTablas(double diferenciaPromedios, double miu1, double varianza1, int tamMuestra1, int tamMuestra2, double miu2, double varianza2) {
        lienzo.dato1TCLDifMedias.setText("= " + diferenciaPromedios);
        lienzo.dato2TCLDifMedias.setText("= " + miu1);
        lienzo.dato3TCLDifMedias.setText("= " + tamMuestra1);
        lienzo.dato4TCLDifMedias.setText("= " + varianza1);
        lienzo.dato5TCLDifMedias.setText("= " + miu2);
        lienzo.dato6TCLDifMedias.setText("= " + tamMuestra2);
        lienzo.dato7TCLDifMedias.setText("= " + varianza2);
        lienzo.layoutProcedimientoTCLDifMedias.setVisibility(View.VISIBLE);
    }
}