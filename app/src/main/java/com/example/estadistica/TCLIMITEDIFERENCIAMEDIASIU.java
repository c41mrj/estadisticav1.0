package com.example.estadistica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class TCLIMITEDIFERENCIAMEDIASIU extends AppCompatActivity {
    private ActivityPruebaHipBinding lienzo;
    private CalculoTablas tablas = new CalculoTablas();
    private double diferenciaDePromedios,miu1,miu2,varianza1,varianza2,valTablas,confianza,d0;
    private int tamMuestra1,tamMuestra2,tamMinimoMuestra;
    private double estadisticoZ,probabilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        this.setTitle("T.C.L diferencia de medias");
        lienzo.cambia1.setText("Diferencia de promedios muestrales:");
        lienzo.imageViewCambia1.setImageResource(R.drawable.tlcdiferenciamedias);
        lienzo.cambia2.setText("Media muestral 1:");
        lienzo.imageCambia.setImageResource(R.drawable.tlcmiu1);
        lienzo.cambia3.setText("Media muestral 2:");
        lienzo.imageCambia2.setImageResource(R.drawable.tlcmiu2);
        lienzo.cambia4.setText("Tamaño de la muestra 1:");
        lienzo.imageCambia3.setImageResource(R.drawable.tlcn1);
        lienzo.cambia5.setText("Tamaño de la muestra 2:");
        lienzo.imageCambia4.setImageResource(R.drawable.tlcn2);
        lienzo.cambia6.setText("Varianza de la muestra 1:");
        lienzo.imCambia6.setImageResource(R.drawable.tlcvarianza1);
        lienzo.cambia7.setText("Varianza de la muestra 2:");
        lienzo.imageCambia7.setImageResource(R.drawable.tlcvarianza2);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.VISIBLE);
        lienzo.imageCambia8.setImageResource(R.drawable.dato_g);
        lienzo.cambia8.setText("Probabilidad: ");
        lienzo.cambia9.setText("Nueva diferencia de medias:");
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato10Opcional.setVisibility(View.VISIBLE);
        lienzo.cambia10.setText("Nueva diferencia de promedios muestrales");
        lienzo.imageCambia9.setImageResource(R.drawable.d0);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE);
        lienzo.potprueba.setVisibility(View.GONE);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.liminferior.setVisibility(View.VISIBLE);
        lienzo.sustLimSup.setVisibility(View.GONE);
        lienzo.calcularIntervaloConfianza.setText("P(X-Y≤)");
        lienzo.otroBotonAux.setText("P(X-Y>)");
        lienzo.layCalc1.setVisibility(View.GONE);
        lienzo.layCalc2.setVisibility(View.GONE);
        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
        lienzo.tablaDatos.setVisibility(View.GONE);
        lienzo.tvDatos.setVisibility(View.GONE);
        lienzo.mediaPoblacional.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lienzo.calcularIntervaloConfianza.setText("P(X-Y≤)");
                lienzo.otroBotonAux.setText("P(X-Y>)");

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lienzo.calcularIntervaloConfianza.setText("P(X-Y≤"+lienzo.mediaPoblacional.getText().toString()+")");
                lienzo.otroBotonAux.setText("P(X-Y>"+lienzo.mediaPoblacional.getText().toString()+")");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        lienzo.imageButton10.setVisibility(View.VISIBLE);
        lienzo.imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TCLIMITEDIFERENCIAMEDIASIU.this);
                alertDialog.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog botonAyuda1 = alertDialog.create();
                botonAyuda1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagenAlert = LayoutInflater.from(TCLIMITEDIFERENCIAMEDIASIU.this);
                final View vista = imagenAlert.inflate(R.layout.botonayudatclimitediferenciamedias1,null);
                botonAyuda1.setView(vista);
                botonAyuda1.show();
            }
        });

        lienzo.imageButton11.setVisibility(View.VISIBLE);
        lienzo.imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TCLIMITEDIFERENCIAMEDIASIU.this);
                alertDialog.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog botonAyuda1 = alertDialog.create();
                botonAyuda1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagenAlert = LayoutInflater.from(TCLIMITEDIFERENCIAMEDIASIU.this);
                final View vista = imagenAlert.inflate(R.layout.botonayudatclimitediferenciamedias2,null);
                botonAyuda1.setView(vista);
                botonAyuda1.show();
            }
        });


        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa los campos que se indican en rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }else{
                    diferenciaDePromedios = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    miu1=Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    miu2=Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    tamMuestra2 = (int) Math.floor(Double.parseDouble(lienzo.significancia.getText().toString()));
                    varianza1 = Double.parseDouble(lienzo.miu1.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    estadisticoZ = tablas.redondeoDecimales(((diferenciaDePromedios-(miu1-miu2))/Math.sqrt(((varianza1/tamMuestra1)+(varianza2/tamMuestra2)))),3);
                    probabilidad = tablas.tablazetaAcumulada(estadisticoZ);
                    probabilidad = tablas.redondeoDecimales(probabilidad,6);
                    lienzo.output.setText("P(X-Y≤"+diferenciaDePromedios+")="+probabilidad + tablas.redondeoDecimales((probabilidad*100),5) + "%");
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.tlczetadiferenciamedias);
                    lienzo.tlcDato1TV.setText("="+diferenciaDePromedios);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.tlcdiferenciamedias);
                    lienzo.tlcDato2TV.setText("="+miu1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.tlcmiu1);
                    lienzo.tlcDato3TV.setText("="+miu2);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.tlcmiu2);
                    lienzo.tlcDato4TV.setText("="+tamMuestra1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tlcn1);
                    lienzo.tlcDato5TV.setText("="+tamMuestra2);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcDato6TV.setText("="+varianza1);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.tlcDato7TV.setText("="+varianza2);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución para la diferencia de medias [Z], que posteriormente buscaremos en las tablas de la distribución acumulada normal estándar.\nPara calcular nuestro estádistico de prueba nos apoyaremos en la siguiente fórmula:" );
                    String aux2 = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TCLIMITEDIFERENCIAMEDIASIU.this);
                            region.setMessage("")
                                    .setCancelable(false)
                                    .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog glosario1 = region.create();
                            glosario1.setTitle("¿Qué representa el valor de la fórma límite de la distribución (Z)?");
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TCLIMITEDIFERENCIAMEDIASIU.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());lienzo.liminferior.setText("Estadístico Z:");
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.textView15.setText("["+diferenciaDePromedios+"-("+miu1+"-"+miu2+")]÷√[("+varianza1+"÷"+tamMuestra1+")+("+varianza2+"÷"+tamMuestra2+")]="+estadisticoZ);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso 2:\nEl segundo paso es buscar el valor del estadístico de prueba anteriormente calculado\n\nZ="+estadisticoZ+"\n\nEn las tablas de la distribución acumuldada de la normal estándar, en éste caso el valor encontrado en tablas es: " + probabilidad + "\n\n\nConclusión: La probabilidad de que la diferencia de promedios sea a lo mucho, menor ó igual de " + diferenciaDePromedios + " es de:\nP(X-Y≤"+diferenciaDePromedios+") = " + probabilidad);
                    lienzo.liminferior.setText("Estadístico Z:");
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.dtoOpcional.setVisibility(View.VISIBLE);
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                    lienzo.imagePromedio.setImageResource(R.drawable.tlcdiferenciamedias);
                    lienzo.imageMiu.setImageResource(R.drawable.tlcmiu1);
                    lienzo.imageSignificancia.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.desviation.setImageResource(R.drawable.tlcn1);
                    lienzo.imageN.setImageResource(R.drawable.tlcmiu2);
                    lienzo.imageDato6.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.imageDato7.setImageResource(R.drawable.tlcn2);
                    lienzo.val1.setText("="+diferenciaDePromedios);
                    lienzo.val2.setText("="+miu1);
                    lienzo.val3.setText("="+varianza1);
                    lienzo.val4.setText("="+tamMuestra1);
                    lienzo.val5.setText("="+miu2);
                    lienzo.val6.setText("="+varianza2);
                    lienzo.val7.setText("="+tamMuestra2);
                    lienzo.llz7.setVisibility(View.VISIBLE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                }
            }
        });


        lienzo.otroBotonAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa los campos que se indican en rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }else{
                    diferenciaDePromedios = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    miu1=Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    miu2=Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    tamMuestra2 = (int) Math.floor(Double.parseDouble(lienzo.significancia.getText().toString()));
                    varianza1 = Double.parseDouble(lienzo.miu1.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    estadisticoZ = tablas.redondeoDecimales(((diferenciaDePromedios-(miu1-miu2))/Math.sqrt(((varianza1/tamMuestra1)+(varianza2/tamMuestra2)))),3);
                    probabilidad = (1-tablas.tablazetaAcumulada(estadisticoZ));
                    probabilidad = tablas.redondeoDecimales(probabilidad,6);
                    lienzo.output.setText("P(X-Y>"+diferenciaDePromedios+")="+probabilidad + "= " + tablas.redondeoDecimales((probabilidad*100),5) + "%");
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.tlcDato1TV.setText("="+diferenciaDePromedios);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.tlcdiferenciamedias);
                    lienzo.tlcDato2TV.setText("="+miu1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.tlcmiu1);
                    lienzo.tlcDato3TV.setText("="+miu2);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.tlcmiu2);
                    lienzo.tlcDato4TV.setText("="+tamMuestra1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tlcn1);
                    lienzo.tlcDato5TV.setText("="+tamMuestra2);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcDato6TV.setText("="+varianza1);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.tlcDato7TV.setText("="+varianza2);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución para la media[z], que posteriormente buscaremos en las tablas de la distribución acumulada normal estándar.\nPara calcular nuestro estádistico de prueba nos apoyaremos en la siguiente fórmula:" );
                    String aux2 = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TCLIMITEDIFERENCIAMEDIASIU.this);
                            region.setMessage("")
                                    .setCancelable(false)
                                    .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog glosario1 = region.create();
                            glosario1.setTitle("¿Qué representa el valor de la fórma límite de la distribución (Z)?");
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TCLIMITEDIFERENCIAMEDIASIU.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setText("Estadístico Z:");
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.textView15.setText("["+diferenciaDePromedios+"-("+miu1+"-"+miu2+")]÷√[("+varianza1+"÷"+tamMuestra1+")+("+varianza2+"÷"+tamMuestra2+")]="+estadisticoZ);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso 2:\nEl segundo paso es buscar el valor del estadístico de prueba anteriormente calculado\n\nZ="+estadisticoZ+"\n\nEn las tablas de la distribución acumuldada de la normal estándar, en éste caso el valor encontrado en tablas es: " + tablas.redondeoDecimales((1-probabilidad),5) +" \n\nLas tablas de la distribución normal estándar entrega valores de PROBABILIDAD para los distintos valores menores o iguales que Z. Por lo tanto si se requiere calcular un valor mayor que Z, se hace necesario calcular el complemento de la probabilidad encontrada en tablas:\n1-" +(1-probabilidad) + " = " +probabilidad + "\n\n\nConclusión: La probabilidad de que la diferencia de promedios sea mayor que: " + diferenciaDePromedios + " es de:\nP(X-Y>"+diferenciaDePromedios+")=" + probabilidad + "= " + tablas.redondeoDecimales((probabilidad*100),5));
                    lienzo.liminferior.setText("Estadístico Z:");
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.dtoOpcional.setVisibility(View.VISIBLE);
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                    lienzo.imagePromedio.setImageResource(R.drawable.tlcdiferenciamedias);
                    lienzo.imageMiu.setImageResource(R.drawable.tlcmiu1);
                    lienzo.imageSignificancia.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.desviation.setImageResource(R.drawable.tlcn1);
                    lienzo.imageN.setImageResource(R.drawable.tlcmiu2);
                    lienzo.imageDato6.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.imageDato7.setImageResource(R.drawable.tlcn2);
                    lienzo.val1.setText("="+diferenciaDePromedios);
                    lienzo.val2.setText("="+miu1);
                    lienzo.val3.setText("="+varianza1);
                    lienzo.val4.setText("="+tamMuestra1);
                    lienzo.val5.setText("="+miu2);
                    lienzo.val6.setText("="+varianza2);
                    lienzo.val7.setText("="+tamMuestra2);
                    lienzo.llz7.setVisibility(View.VISIBLE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                }
            }
        });

        lienzo.button2.setVisibility(View.VISIBLE);
        lienzo.button2.setText("Calcular tamaño mínimo de las muestras si se conoce la nueva diferencia de medias");
        lienzo.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltanDatos()){
                    desplegarNotificacion("Por favor ingresa los datos en rojo:");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                    lienzo.cambia9.setTextColor(Color.RED);
                }else{
                    varianza1 = Double.parseDouble(lienzo.miu1.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    confianza = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    d0 = Double.parseDouble(lienzo.editTextCambia9.getText().toString());
                    valTablas = tablas.tablazetaAcumuladaPotencia(tablas.redondeoDecimales(((1-confianza)/2),5));
                    tamMinimoMuestra = (int) Math.floor(Math.pow(((Math.sqrt((varianza1+varianza2))*valTablas)/d0),2)) + 1;
                    lienzo.output.setText("El tamaño mínimo de muestra que se requiere para cumplir la condición requerida es de: " + tamMinimoMuestra);
                    lienzo.imageView2.setImageResource(R.drawable.tamminimomuestradiferenciamedias3);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.unomenosalfamedios);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.deceromayuscula);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tlcmiu1);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcmiu2);
                    lienzo.tlcDato1TV.setText("="+varianza1);
                    lienzo.tlcDato2TV.setText("="+varianza2);
                    lienzo.tlcDato3TV.setText("="+tablas.redondeoDecimales((1-confianza/2),5));
                    lienzo.tlcDato4TV.setText("="+d0);
                    lienzo.tlcDato5TV.setText("="+miu1);
                    lienzo.tlcDato6TV.setText("="+miu2);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Para calcular el tamaño mínimo de muestra se hace necesario primero buscar el valor de (1-α)/2 en las tablas de la distribución acumulada normál estándar donde:\n\nα = " + confianza+"\n\nEn éste caso el valor en tablas es: " + valTablas + " ,una vez obtenido el valor se sustituyen los valores en la fórmula para el cálculo del tamaño mínimo de la muestra para diferencia de medias:");
                    lienzo.textView15.setText("{[(√("+varianza1+"+"+varianza2+")*"+valTablas+")÷"+d0+"]^2} + 1= " + tamMinimoMuestra);
                    lienzo.paso2.setText("Es necesario saber que para calcular el tamaño mínimo de muestra es necesario redondear los decimales hacía abajo, o simplemente ignorarlos.");
                    lienzo.paso2Phipodc.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Tamaño mínimo de muestra:");
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.linearLayoutReglaDecision.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                }
            }
        });


        lienzo.button8.setVisibility(View.VISIBLE);
        lienzo.button8.setText("Calcular tamaño mínimo de las muestras si se conoce la nueva diferencia de promedios muestrales");
        lienzo.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaDatos()){
                    desplegarNotificacion("Por favor ingresa los datos en rojo:");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                    lienzo.cambia10.setTextColor(Color.RED);
                }else{
                    miu1=Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    miu2=Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    varianza1 = Double.parseDouble(lienzo.miu1.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    confianza = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    d0 = Double.parseDouble(lienzo.editTextCambia10.getText().toString());
                    valTablas = tablas.tablazetaAcumuladaPotencia(confianza);
                    tamMinimoMuestra = (int) Math.floor(Math.pow(((Math.sqrt((varianza1+varianza2))*valTablas)/(d0-(miu1-miu2))),2)) + 1;
                    lienzo.output.setText("El tamaño mínimo de muestra que se requiere para cumplir la condición requerida es de: " + tamMinimoMuestra);
                    lienzo.imageView2.setImageResource(R.drawable.tamminimomuestradiferenciamedias1);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.gammaenalfa);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.deceromayuscula);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tlcmiu1);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcmiu2);
                    lienzo.tlcDato1TV.setText("="+varianza1);
                    lienzo.tlcDato2TV.setText("="+varianza2);
                    lienzo.tlcDato3TV.setText("="+valTablas);
                    lienzo.tlcDato4TV.setText("="+d0);
                    lienzo.tlcDato5TV.setText("="+miu1);
                    lienzo.tlcDato6TV.setText("="+miu2);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Para calcular el tamaño mínimo de muestra se hace necesario primero buscar el valor de α en las tablas de la distribución acumulada normál estándar donde:\n\nα = " + confianza+"\n\nEn éste caso el valor en tablas es: " + valTablas + " ,una vez obtenido el valor se sustituyen los valores en la fórmula para el cálculo del tamaño mínimo de la muestra para diferencia de medias:");
                    lienzo.textView15.setText("{[(√("+varianza1+"+"+varianza2+")*"+valTablas+")÷("+d0+"-("+miu1+"-"+miu2+"))]^2} + 1= " + tamMinimoMuestra);
                    lienzo.paso2.setText("Es necesario saber que para calcular el tamaño mínimo de muestra es necesario redondear los decimales hacía abajo, o simplemente ignorarlos.");
                    lienzo.paso2Phipodc.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Tamaño mínimo de muestra:");
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.linearLayoutReglaDecision.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                }
            }
        });


        lienzo.verProcedimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.VISIBLE);
            }
        });

        lienzo.cleanit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.mediaPoblacional.setText("");
                lienzo.desviacionEstandarPob.setText("");
                lienzo.promedioMuestral.setText("");
                lienzo.tamMuestral.setText("");
                lienzo.significancia.setText("");
                lienzo.miu1.setText("");
                lienzo.editTextCambia7.setText("");
                lienzo.editTextCambia8.setText("");
                lienzo.editTextCambia9.setText("");
                lienzo.editTextCambia10.setText("");
                lienzo.output.setText("");
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);

            }
        });


    }


    public boolean faltaDato(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty())return true;
        else return false;
    }

    public boolean faltaDatos(){
        if(lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty()||lienzo.editTextCambia8.getText().toString().isEmpty()||lienzo.editTextCambia10.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltanDatos(){
        if(lienzo.miu1.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty()||lienzo.editTextCambia8.getText().toString().isEmpty()||lienzo.editTextCambia9.getText().toString().isEmpty()) return true;
        else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }

}