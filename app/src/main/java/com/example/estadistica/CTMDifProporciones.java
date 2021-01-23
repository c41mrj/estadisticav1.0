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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CTMDifProporciones extends AppCompatActivity implements TeoremaCentralDelLimite, conversiones {

    public static ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6;
    public static RelativeLayout datos;
    public static TextView resultado;
    public static TableRow tableRow;
    public static LinearLayout layoutProcedimiento;
    private TEOREMACENTRALLIMITEDIFERENCIAPROPORCIONES teorema;
    private double diferenciaProporciones,confiabilidad1,confiabilidad2;
    private int tamMuestra1,tamMuestra2;
    private com.example.estadistica.databinding.ActivityCTMBinding lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = com.example.estadistica.databinding.ActivityCTMBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        imageView3 = findViewById(R.id.image3);
        imageView4 = findViewById(R.id.image4);
        imageView5 = findViewById(R.id.image5);
        imageView6 = findViewById(R.id.image6);
        datos = findViewById(R.id.datosDiferenciaProporciones);
        tableRow = findViewById(R.id.tableRowOpcional);
        resultado = findViewById(R.id.Resultado);
        layoutProcedimiento = findViewById(R.id.layoutProcedimientoTCLDifMedias);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CTMDifProporciones.this, android.R.layout.simple_spinner_dropdown_item,getDiferenciaProporciones());
        lienzo.spinner.setAdapter(adapter);

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
                                lienzo.diferenciaProporciones.setText("");
                                lienzo.confiabilidad1.setText("");
                                lienzo.confiabilidad2.setText("");
                                lienzo.tamMuestra1.setText("");
                                lienzo.tamMuestra2.setText("");
                            }
                        });
            }
        });



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
                                desplegarMensajeEstadisticoZ();
                            }
                        }).start();
            }
        });



        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());


                if(lienzo.spinner.getSelectedItemPosition() == 0){

                    prepararEntornoDiferenciaProporciones();

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
                                                lienzo.layoutProcedimientoTCLDifMedias.setVisibility(View.VISIBLE);
                                                diferenciaProporciones = convertirStringADouble(lienzo.diferenciaProporciones.getText().toString());
                                               confiabilidad1 = convertirStringADouble(lienzo.confiabilidad1.getText().toString());
                                               tamMuestra1 = convertirStringAInt(lienzo.tamMuestra1.getText().toString());
                                               confiabilidad2 = convertirStringADouble(lienzo.confiabilidad2.getText().toString());
                                               tamMuestra2 = convertirStringAInt(lienzo.tamMuestra2.getText().toString());
                                               teorema = new TEOREMACENTRALLIMITEDIFERENCIAPROPORCIONES(diferenciaProporciones,confiabilidad1,confiabilidad2,tamMuestra1,tamMuestra2);
                                               lienzo.Resultado.setText("La probabilidad calculada es: " + teorema.getValTablas());
                                               lienzo.paso1TCLDifMedias.setText(getPaso1TCLDifProporciones());
                                               lienzo.teoremaTCLDifMedias.setImageResource(R.drawable.estadisticozdiferenciaproporciones);
                                               lienzo.Paso2TCLDifMedias.setText(getPaso2TCLDifProporciones1()+teorema.getZ()+getPaso2TCLDifProporciones2());
                                               lienzo.conclusionTCLDifMedias.setText("El valor encontrado en las tablas acumuladas de la distribución normal estándar es " + teorema.getValTablas() + "\n\nPor lo tanto la probabilidad de que la diferencia de proporciones sea menor que: " + diferenciaProporciones + " es de: \n\n" + teorema.getValTablas());

                                            }
                                            catch(Exception exception){
                                                lienzo.layoutProcedimientoTCLDifMedias.setVisibility(View.GONE);
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTMDifProporciones.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                        }
                    });



                }else if(lienzo.spinner.getSelectedItemPosition() == 1){

                    prepararEntornoDiferenciaProporciones();

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
                                                lienzo.layoutProcedimientoTCLDifMedias.setVisibility(View.VISIBLE);
                                                diferenciaProporciones = convertirStringADouble(lienzo.diferenciaProporciones.getText().toString());
                                                confiabilidad1 = convertirStringADouble(lienzo.confiabilidad1.getText().toString());
                                                tamMuestra1 = convertirStringAInt(lienzo.tamMuestra1.getText().toString());
                                                confiabilidad2 = convertirStringADouble(lienzo.confiabilidad2.getText().toString());
                                                tamMuestra2 = convertirStringAInt(lienzo.tamMuestra2.getText().toString());
                                                teorema = new TEOREMACENTRALLIMITEDIFERENCIAPROPORCIONES(diferenciaProporciones,confiabilidad1,confiabilidad2,tamMuestra1,tamMuestra2);
                                                lienzo.Resultado.setText("La probabilidad calculada es: " + redondeoDecimales((1-teorema.getValTablas()),5));
                                                lienzo.paso1TCLDifMedias.setText(getPaso1TCLDifProporciones());
                                                lienzo.teoremaTCLDifMedias.setImageResource(R.drawable.estadisticozdiferenciaproporciones);
                                                lienzo.Paso2TCLDifMedias.setText(getPaso2TCLDifProporciones1()+teorema.getZ()+getPaso2TCLDifProporciones2());
                                                lienzo.conclusionTCLDifMedias.setText("El valor encontrado en las tablas acumuladas de la distribución normal estándar es " + teorema.getValTablas() + "\n\nEs necesario recordar que las tablas acumuladas de la distribución normal estándar unicamente nos dan los valores de las probabilidades de 0 a Z, por lo que si se requieren valores mayores que Z es necesario calcular el complemento al valor obtenido en las tablas.\n\nPor lo tanto la probabilidad de que la diferencia de proporciones sea menor que: " + diferenciaProporciones + " es de: \n\n" + redondeoDecimales((1-teorema.getValTablas()),5));

                                            }
                                            catch(Exception exception){
                                                lienzo.layoutProcedimientoTCLDifMedias.setVisibility(View.GONE);
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTMDifProporciones.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
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
    public void colocarDatosEnTablas(double dato1, double dato2, double dato3, int dato4, int dato5, double dato6, double dato7) {

    }

    public void desplegarMensajeEstadisticoZ(){
        LayoutInflater imagenAlert = LayoutInflater.from(CTMDifProporciones.this);
        final View vista = imagenAlert.inflate(R.layout.estadisticozconcepto,null);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTMDifProporciones.this);
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

}