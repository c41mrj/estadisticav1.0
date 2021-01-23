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
import android.widget.TableRow;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityCTMBinding;

public class CTMProporciones extends AppCompatActivity implements TeoremaCentralDelLimite, conversiones{


    public static TableRow tableRow;
    public static RelativeLayout datos;
    public static ImageView imagenConfiabilidad, imagenProporcion, imagenMuestra;
    private ActivityCTMBinding lienzo;
    private TEOREMACENTRALLIMITEPROPORCIONES teorema;
    private double confiabilidad,proporcion;
    private int tamMuestra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityCTMBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        tableRow = findViewById(R.id.tbrow);
        datos = findViewById(R.id.datosProporciones);
        imagenConfiabilidad = findViewById(R.id.imageView10);
        imagenProporcion = findViewById(R.id.imageView13);
        imagenMuestra = findViewById(R.id.imageView14);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CTMProporciones.this, android.R.layout.simple_spinner_dropdown_item,getProporciones());
        lienzo.spinner.setAdapter(adapter);

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
                                desplegarMensajeEstadisticoZ();
                            }
                        }).start();
            }
        });

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
                                lienzo.confiabilidad.setText("");
                                lienzo.proporcion.setText("");
                                lienzo.tamMuestra.setText("");
                                lienzo.Resultado.setText("El resultado es:");
                                lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);
                            }
                        });
            }
        });

        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());


                if(lienzo.spinner.getSelectedItemPosition() == 0){

                    prepararEntornoProporciones();
                    lienzo.Resultado.setText("El resultado es: ");
                    lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);

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
                                                lienzo.layoutProcedimientoTCL.setVisibility(View.VISIBLE);
                                                confiabilidad = convertirStringADouble(lienzo.confiabilidad.getText().toString());
                                                proporcion = convertirStringADouble(lienzo.proporcion.getText().toString());
                                                tamMuestra = convertirStringAInt(lienzo.tamMuestra.getText().toString());
                                                teorema = new TEOREMACENTRALLIMITEPROPORCIONES(confiabilidad,proporcion,tamMuestra);
                                                lienzo.Resultado.setText("La probabilidad calculada es: " + teorema.getValTablas());
                                                lienzo.dato1TabTCLMedia.setText("= " + proporcion);
                                                lienzo.dato2Tab1TCL.setText("= " + confiabilidad);
                                                lienzo.dato3TabTCL.setText("= " + tamMuestra);
                                                lienzo.paso1TCLMedia.setText(getPaso1TCLProporciones());
                                                lienzo.imageView17.setImageResource(R.drawable.estadisticozproporcion);
                                                lienzo.Paso2TCL.setText(getPaso2TCLProporciones1()+teorema.getZ()+getPaso2TCLProporciones2());
                                                lienzo.textView101.setText("El valor encontrado en las tablas acumuladas de la distribución normal estándar es de: " + teorema.getValTablas() + "\n\n" +
                                                        "Por lo tanto la probabilidad de que la proporción muestral sea menor o igual que " + proporcion + " es de: \n\nP(p≤"+proporcion+") = " +
                                                        "" + teorema.getValTablas() + "= " + redondeoDecimales((teorema.getValTablas()*100),5) + "%");

                                            }catch(Exception e){
                                                lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTMProporciones.this, "Imposible realizar el cálculo, por favor verifica que ingresaste todos los datos", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });



                        }
                    });

                }else if(lienzo.spinner.getSelectedItemPosition() == 1){
                    prepararEntornoProporciones();
                    lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);
                    lienzo.Resultado.setText("El resultado es: ");

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
                                                lienzo.layoutProcedimientoTCL.setVisibility(View.VISIBLE);
                                                confiabilidad = convertirStringADouble(lienzo.confiabilidad.getText().toString());
                                                proporcion = convertirStringADouble(lienzo.proporcion.getText().toString());
                                                tamMuestra = convertirStringAInt(lienzo.tamMuestra.getText().toString());
                                                teorema = new TEOREMACENTRALLIMITEPROPORCIONES(confiabilidad,proporcion,tamMuestra);
                                                lienzo.Resultado.setText("La probabilidad calculada es: " + redondeoDecimales((1-teorema.getValTablas()),5));
                                                lienzo.dato1TabTCLMedia.setText("= " + proporcion);
                                                lienzo.dato2Tab1TCL.setText("= " + confiabilidad);
                                                lienzo.dato3TabTCL.setText("= " + tamMuestra);
                                                lienzo.paso1TCLMedia.setText(getPaso1TCLProporciones());
                                                lienzo.imageView17.setImageResource(R.drawable.estadisticozproporcion);
                                                lienzo.Paso2TCL.setText(getPaso2TCLProporciones1()+teorema.getZ()+getPaso2TCLProporciones2());
                                                lienzo.textView101.setText("El valor encontrado en las tablas acumuladas de la distribución normal estándar es de: " + teorema.getValTablas() + "\n\n" +
                                                        "Es necesario recordar que las tablas acumuladas de la distribución normal estándar únicamente dan valores desde 0 hasta Z, por lo que" +
                                                        " si se requieren valores de probabilidad mayores que Z es indispensable calcular el complemento al valor encontrado en tablas.\n\nPor lo tanto la probabilidad de que la proporción muestral sea menor o igual que " + proporcion + " es de: \n\nP(p≤"+proporcion+") = " +
                                                        "" + redondeoDecimales((1-teorema.getValTablas()),5) + "= " + redondeoDecimales(((1-teorema.getValTablas())*100),5) + "%");

                                            }catch(Exception e){
                                                lienzo.layoutProcedimientoTCL.setVisibility(View.GONE);
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTMProporciones.this, "Imposible realizar el cálculo, por favor verifica que ingresaste todos los datos", Toast.LENGTH_SHORT).show();
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

    public void desplegarMensajeEstadisticoZ(){
        LayoutInflater imagenAlert = LayoutInflater.from(CTMProporciones.this);
        final View vista = imagenAlert.inflate(R.layout.estadisticozconcepto,null);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTMProporciones.this);
        alertDialog
                .setCancelable(true)
                .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
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

    @Override
    public void colocarDatosEnTablas(double dato1, double dato2, double dato3, int dato4) {

    }

    @Override
    public void colocarDatosEnTablas(double dato1, double dato2, double dato3, int dato4, int dato5, double dato6, double dato7) {

    }
}