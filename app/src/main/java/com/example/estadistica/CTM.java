package com.example.estadistica;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.estadistica.databinding.ActivityCTMBinding;

import org.w3c.dom.Text;

public class CTM extends AppCompatActivity implements TeoremaCentralDelLimite,conversiones{

    Spinner spinner;
    public static RelativeLayout primer,segundo,tres;
    public static LinearLayout layoutProcedimientoTCLMedia, layoutProcedimientoTCLTamMinimoMuestra;
    public static TableRow opcionalRow;
    public static ImageView teorematcl,teoremaTamMinimoMuestra;
    public static TextView resultado;
    public Context context = this;
    public double mediaPob, promedioMuestral,desvEstandar,probabilidad,d0,x0;
    public int tamMuestra,tamMinimoMuestra;
    public CalculoTablas tabla = new CalculoTablas();
    private String ultimo;
    private TEOREMACENTRALLIMITEMEDIA teorema;


    boolean swit;
    String [] media = {"P(X≤)","P(X>)","Calcular tamaño mínimo de muestra si se conoce D0 y se sabe que : P(|x-μ|<D0)","Calcular tamaño mínimo de muestra si se conoce D0 y se sabe que : P(|x-μ|>D0)","Calcular tamaño mínimo de muestra si se conoce X0"};

    ActivityCTMBinding lienzo;

    public Context getContext(){
        return context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityCTMBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        primer = findViewById(R.id.datosTCLMedia);
        segundo = findViewById(R.id.datosTamMinimoMuestraMedia1);
        tres = findViewById(R.id.datosTamMinimoMuestraMedia2);
        resultado = findViewById(R.id.Resultado);
        layoutProcedimientoTCLMedia = findViewById(R.id.layoutProcedimientoTCL);
        layoutProcedimientoTCLTamMinimoMuestra = findViewById(R.id.layoutProcedimientoTamMinimoMuestra);
        opcionalRow = findViewById(R.id.tableRow4Layout2);


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



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CTM.this, android.R.layout.simple_spinner_dropdown_item,getMedia());
        lienzo.spinner.setAdapter(adapter);

        lienzo.botoncalcular.setImageResource(R.drawable.calculadora);
        swit = false;

       lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                desaparecerLayoutsProcedimientosTCL();
                lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                if(lienzo.spinner.getSelectedItemPosition()==0) {    // (Px<)

                    lienzo.botonayuda.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            LayoutInflater imagenAlert = LayoutInflater.from(CTM.this);
                            final View vista = imagenAlert.inflate(R.layout.ejemplotclmedia,null);
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTM.this);
                            alertDialog.setMessage("")
                                    .setCancelable(false)
                                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog botonAyuda1 = alertDialog.create();
                            botonAyuda1.setTitle("¿Debo elegir esta opción?");
                            botonAyuda1.setView(vista);
                            botonAyuda1.show();

                        }
                    });


                    desplegarDatosTCLMedia(context);

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
                                            lienzo.mediaPoblacionalTCLMedia.setText("");
                                            lienzo.desvEstandarTCLMedia.setText("");
                                            lienzo.promMuestralTCLMedia.setText("");
                                            lienzo.tamMuestraTCLMedia.setText("");
                                            desaparecerLayoutsProcedimientosTCL();
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
                                                lienzo.imageView17.setImageResource(R.drawable.tlc_estadisticoz);
                                                mediaPob = convertirStringADouble(lienzo.mediaPoblacionalTCLMedia.getText().toString());
                                                desvEstandar = convertirStringADouble(lienzo.desvEstandarTCLMedia.getText().toString());
                                                promedioMuestral = convertirStringADouble(lienzo.promMuestralTCLMedia.getText().toString());
                                                tamMuestra = convertirStringAInt(lienzo.tamMuestraTCLMedia.getText().toString());
                                                teorema = new TEOREMACENTRALLIMITEMEDIA(promedioMuestral,mediaPob,desvEstandar,tamMuestra);
                                                lienzo.Resultado.setText("La probabilidad calculada es: " + teorema.getValTablas());
                                                colocarDatosEnTablas(mediaPob,desvEstandar,promedioMuestral,tamMuestra);
                                                lienzo.paso1TCLMedia.setText(paso1TCLMedia);
                                                lienzo.Paso2TCL.setText(paso2TCLMedia1+teorema.getZ()+paso2TCLMedia2);
                                                lienzo.textView101.setText("El valor encontrado en las tablas de la distribución normal estándar es: " + teorema.getValTablas() + "\n\nPor lo tanto la probabilidad de que el promedio muestral sea menor o igual que " + promedioMuestral + " es de: \n\nP(X≤"+promedioMuestral+") = " + teorema.getValTablas());
                                                desplegarLayoutProcedimientoMedia();
                                            }
                                            catch(Exception exception){
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTM.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                        }
                    });

                }else if(lienzo.spinner.getSelectedItemPosition() == 1){ //*********P(X>)************


                    lienzo.botonayuda.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LayoutInflater imagenAlert = LayoutInflater.from(CTM.this);
                            final View vista = imagenAlert.inflate(R.layout.ejemplotclmedia2,null);
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTM.this);
                            alertDialog.setMessage("")
                                    .setCancelable(false)
                                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog botonAyuda1 = alertDialog.create();
                            botonAyuda1.setTitle("¿Debo elegir esta opción?");
                            botonAyuda1.setView(vista);
                            botonAyuda1.show();
                        }
                    });



                    desplegarDatosTCLMedia(context);


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
                                            lienzo.mediaPoblacionalTCLMedia.setText("");
                                            lienzo.desvEstandarTCLMedia.setText("");
                                            lienzo.promMuestralTCLMedia.setText("");
                                            lienzo.tamMuestraTCLMedia.setText("");
                                            desaparecerLayoutsProcedimientosTCL();
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
                                                lienzo.imageView17.setImageResource(R.drawable.tlc_estadisticoz);
                                                mediaPob = convertirStringADouble(lienzo.mediaPoblacionalTCLMedia.getText().toString());
                                                desvEstandar = convertirStringADouble(lienzo.desvEstandarTCLMedia.getText().toString());
                                                promedioMuestral = convertirStringADouble(lienzo.promMuestralTCLMedia.getText().toString());
                                                tamMuestra = convertirStringAInt(lienzo.tamMuestraTCLMedia.getText().toString());
                                                teorema = new TEOREMACENTRALLIMITEMEDIA(promedioMuestral,mediaPob,desvEstandar,tamMuestra);
                                                lienzo.Resultado.setText("La probabilidad calculada es: " + tabla.redondeoDecimales((1-teorema.getValTablas()),5));
                                                colocarDatosEnTablas(mediaPob,desvEstandar,promedioMuestral,tamMuestra);
                                                lienzo.paso1TCLMedia.setText(paso1TCLMedia);
                                                lienzo.Paso2TCL.setText(paso2TCLMedia1+teorema.getZ()+paso2TCLMedia2);
                                                lienzo.textView101.setText("El valor encontrado en las tablas de la distribución normal estándar es: " + teorema.getValTablas() + "\n\nEs necesario saber que las tablas acumuladas de la distribución normal estándar nos muestra los valores desde cero hasta Z, es decir que para calcular valores mayores o iguales que Z es necesario calcular el complemento al valor encontrado previamente en tablas.\n\n Por lo tanto la probabilidad de que el promedio muestral sea mayor que " + promedioMuestral + " es de: \n\nP(X>"+promedioMuestral+") = " + tabla.redondeoDecimales((1-teorema.getValTablas()),5));
                                                desplegarLayoutProcedimientoMedia();
                                            }
                                            catch(Exception exception){
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTM.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                        }
                    });


                }else if(lienzo.spinner.getSelectedItemPosition() == 2){  //Caso tamaño mínimo de muestra 1


                    lienzo.botonayuda.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LayoutInflater imagenAlert = LayoutInflater.from(CTM.this);
                            final View vista = imagenAlert.inflate(R.layout.botonayudatclmedia1,null);
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTM.this);
                            alertDialog.setMessage("")
                                    .setCancelable(false)
                                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog botonAyuda1 = alertDialog.create();
                            botonAyuda1.setTitle("¿Debo elegir esta opción?");
                            botonAyuda1.setView(vista);
                            botonAyuda1.show();
                        }
                    });


                    desplegarDatosTamMinimoMuestraMedia(context);


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
                                            lienzo.desvEstandarTCLTamMinimoMuestra1.setText("");
                                            lienzo.probabilidadTCLTamMinimoMuestra.setText("");
                                            lienzo.d0TCLTamMinimoMuestra1.setText("");
                                            desaparecerLayoutsProcedimientosTCL();
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
                                                desvEstandar = convertirStringADouble(lienzo.desvEstandarTCLTamMinimoMuestra1.getText().toString());
                                                probabilidad = convertirStringAFloat(lienzo.probabilidadTCLTamMinimoMuestra.getText().toString());
                                                probabilidad = redondeoDecimales(probabilidad,5);
                                                d0 = convertirStringADouble(lienzo.d0TCLTamMinimoMuestra1.getText().toString());
                                                teorema = new TEOREMACENTRALLIMITEMEDIA();
                                                tamMinimoMuestra = teorema.calcularTamañoMinimoMuestraCaso1(desvEstandar,probabilidad,d0);
                                                lienzo.Resultado.setText("El tamaño mínimo de muestra calculado es: " + tamMinimoMuestra);
                                                lienzo.teoremaTamMinimoMuestraMedias.setImageResource(R.drawable.tamminimomuestratclmedia1);
                                                lienzo.imageView2.setImageResource(R.drawable.gammaenunomenosalfamedios);
                                                lienzo.imagen1Tabla2.setImageResource(R.drawable.desviacionestandar);
                                                lienzo.dato1Tabla2.setText("= " + desvEstandar);
                                                lienzo.imagen2Tabla2.setImageResource(R.drawable.dados);
                                                lienzo.dato2Tabla2.setText("= " + probabilidad);
                                                lienzo.imagen3Tabla2.setImageResource(R.drawable.d0);
                                                lienzo.dato3Tabla2.setText("= "+d0);
                                                lienzo.tableRow4Layout2.setVisibility(View.GONE);
                                                lienzo.paso1TCLTamMinimoMuestra.setText("El primer paso para calcular el tamaño mínimo de muestra es buscar el valor de (1-"+probabilidad+")/2 en las tablas acumuladas de la distribución normal estándar, donde en éste caso el valor encontrado en tablas es: \n\nZ[(1-"+probabilidad+")/2] = " + teorema.getValTablas());
                                                lienzo.conclusionTamMinimoMuestra.setText("El tamaño mínimo de muestra que se requiere para satisfacer las condiciones del problema es: " + tamMinimoMuestra);
                                                desplegarLayoutProcedimientoTamMinimoMuestra();
                                            }
                                            catch(Exception exception){
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTM.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                        }
                    });

                }else if(lienzo.spinner.getSelectedItemPosition() == 3){




                    desplegarDatosTamMinimoMuestraMedia(context);

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
                                            lienzo.desvEstandarTCLTamMinimoMuestra1.setText("");
                                            lienzo.probabilidadTCLTamMinimoMuestra.setText("");
                                            lienzo.d0TCLTamMinimoMuestra1.setText("");
                                            desaparecerLayoutsProcedimientosTCL();
                                        }
                                    });




                        }
                    });

                    lienzo.botonayuda.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LayoutInflater imagenAlert = LayoutInflater.from(CTM.this);
                            final View vista = imagenAlert.inflate(R.layout.botonayudatclmedia2,null);
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTM.this);
                            alertDialog.setMessage("")
                                    .setCancelable(false)
                                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog botonAyuda1 = alertDialog.create();
                            botonAyuda1.setTitle("¿Debo elegir esta opción?");
                            botonAyuda1.setView(vista);
                            botonAyuda1.show();
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
                                                desvEstandar = convertirStringADouble(lienzo.desvEstandarTCLTamMinimoMuestra1.getText().toString());
                                                probabilidad = convertirStringAFloat(lienzo.probabilidadTCLTamMinimoMuestra.getText().toString());
                                                probabilidad = redondeoDecimales(probabilidad,5);
                                                d0 = convertirStringADouble(lienzo.d0TCLTamMinimoMuestra1.getText().toString());
                                                teorema = new TEOREMACENTRALLIMITEMEDIA();
                                                tamMinimoMuestra = teorema.calcularTamañoMinimoMuestraCaso2(desvEstandar,probabilidad,d0);
                                                lienzo.Resultado.setText("El tamaño mínimo de muestra calculado es: " + tamMinimoMuestra);
                                                lienzo.teoremaTamMinimoMuestraMedias.setImageResource(R.drawable.tamminimomuestratclmedia2);
                                                lienzo.imageView2.setImageResource(R.drawable.gammaenalfamedios);
                                                lienzo.imagen1Tabla2.setImageResource(R.drawable.desviacionestandar);
                                                lienzo.dato1Tabla2.setText("= " + desvEstandar);
                                                lienzo.imagen2Tabla2.setImageResource(R.drawable.dados);
                                                lienzo.dato2Tabla2.setText("= " + probabilidad);
                                                lienzo.imagen3Tabla2.setImageResource(R.drawable.d0);
                                                lienzo.dato3Tabla2.setText("= "+d0);
                                                lienzo.tableRow4Layout2.setVisibility(View.GONE);
                                                lienzo.paso1TCLTamMinimoMuestra.setText("El primer paso para calcular el tamaño mínimo de muestra es buscar el valor de ("+probabilidad+"/2) en las tablas acumuladas de la distribución normal estándar, donde en éste caso el valor encontrado en tablas es: \n\nZ("+probabilidad+"/2) = " + teorema.getValTablas());
                                                lienzo.conclusionTamMinimoMuestra.setText("El tamaño mínimo de muestra que se requiere para satisfacer las condiciones del problema es: " + tamMinimoMuestra);
                                                desplegarLayoutProcedimientoTamMinimoMuestra();
                                            }
                                            catch(Exception exception){
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTM.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                        }
                    });

                }else if(lienzo.spinner.getSelectedItemPosition() == 4){

                    lienzo.botonayuda.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LayoutInflater imagenAlert = LayoutInflater.from(CTM.this);
                            final View vista = imagenAlert.inflate(R.layout.botonayudatclmedia3,null);
                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTM.this);
                            alertDialog.setMessage("")
                                    .setCancelable(false)
                                    .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog botonAyuda1 = alertDialog.create();
                            botonAyuda1.setTitle("¿Debo elegir esta opción?");
                            botonAyuda1.setView(vista);
                            botonAyuda1.show();
                        }
                    });

                    desplegarDatosTamMinimoMuestraMedia2(context);

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
                                            lienzo.mediaPoblacionalTamMinimoMuestra2.setText("");
                                            lienzo.desvEstandarTamMinimoMuestra2.setText("");
                                            lienzo.nuevoPromedioTamMinimoMuestra2.setText("");
                                            lienzo.probabilidadTamMinimoMuestra2.setText("");
                                            lienzo.layoutProcedimientoTamMinimoMuestra.setVisibility(View.GONE);
                                            lienzo.Resultado.setText("El resultado es:");
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
                                                mediaPob = convertirStringADouble(lienzo.mediaPoblacionalTamMinimoMuestra2.getText().toString());
                                                desvEstandar = convertirStringAFloat(lienzo.desvEstandarTamMinimoMuestra2.getText().toString());
                                                x0 = convertirStringADouble(lienzo.nuevoPromedioTamMinimoMuestra2.getText().toString());
                                                probabilidad = convertirStringADouble(lienzo.probabilidadTamMinimoMuestra2.getText().toString());
                                                teorema = new TEOREMACENTRALLIMITEMEDIA();
                                                tamMinimoMuestra = teorema.calcularTamañoMinimoMuestraCaso3(desvEstandar,probabilidad,x0,mediaPob);
                                                lienzo.Resultado.setText("El tamaño mínimo de muestra calculado es: " + tamMinimoMuestra);
                                                lienzo.teoremaTamMinimoMuestraMedias.setImageResource(R.drawable.tamminimomuestratclmedia3);
                                                lienzo.imageView2.setImageResource(R.drawable.gammaenalfa);
                                                lienzo.imagen1Tabla2.setImageResource(R.drawable.desviacionestandar);
                                                lienzo.dato1Tabla2.setText("= " + desvEstandar);
                                                lienzo.imagen2Tabla2.setImageResource(R.drawable.dados);
                                                lienzo.dato2Tabla2.setText("= " + probabilidad);
                                                lienzo.imagen3Tabla2.setImageResource(R.drawable.nuevopromedio);
                                                lienzo.dato3Tabla2.setText("= "+x0);
                                                lienzo.tableRow4Layout2.setVisibility(View.GONE);
                                                lienzo.imagen4Tabla2.setImageResource(R.drawable.mediap);
                                                lienzo.dato4Tabla2.setText("= " +mediaPob);
                                                lienzo.paso1TCLTamMinimoMuestra.setText("El primer paso para calcular el tamaño mínimo de muestra es buscar el valor de ("+probabilidad+") en las tablas acumuladas de la distribución normal estándar, donde en éste caso el valor encontrado en tablas es: \n\nZ("+probabilidad+") = " + teorema.getValTablas());
                                                lienzo.conclusionTamMinimoMuestra.setText("El tamaño mínimo de muestra que se requiere para satisfacer las condiciones del problema es: " + tamMinimoMuestra);
                                                desplegarLayoutProcedimientoTamMinimoMuestraCaso2();
                                            }
                                            catch(Exception exception){
                                                lienzo.Resultado.setText("El resultado es:");
                                                Toast.makeText(CTM.this,"Imposible calcular, por favor verifica si se ingresaron correctamente todos los datos",Toast.LENGTH_LONG).show();
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
        LayoutInflater imagenAlert = LayoutInflater.from(CTM.this);
        final View vista = imagenAlert.inflate(R.layout.estadisticozconcepto,null);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(CTM.this);
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
    public void colocarDatosEnTablas(double media, double desvEstandar, double promedioMuestral, int tamMuestra) {
        lienzo.dato1TabTCLMedia.setText("= " + media);
        lienzo.dato2Tab1TCL.setText("= "+desvEstandar);
        lienzo.dato3TabTCL.setText("=" + promedioMuestral);
        lienzo.dato4TabTCL.setText("=" + tamMuestra);
    }

    @Override
    public void colocarDatosEnTablas(double dato1, double dato2, double dato3, int dato4, int dato5, double dato6, double dato7) {

    }
}