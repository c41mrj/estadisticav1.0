package com.example.estadistica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

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

public class TCLIMITEDIFERENCIAPROPORCIONES extends AppCompatActivity {
    private com.example.estadistica.databinding.ActivityPruebaHipBinding lienzo;
    private double diferenciaProporciones,probabilidad1,probabilidad2,complemento1,complemento2;
    private int tamMuestra1,tamMuestra2,tamMinimoMuestra;
    private double estadisticoZ,resultado,valTablas,p0,confianza;
    private CalculoTablas tabla = new CalculoTablas();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        lienzo = com.example.estadistica.databinding.ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        lienzo.cambia1.setText("Diferencia de proporciones:");
        lienzo.imageViewCambia1.setImageResource(R.drawable.tcldiferenciaproporciones);
        lienzo.cambia2.setText("Confiabilidad 1:");
        lienzo.imageCambia.setImageResource(R.drawable.tclprobabilidad1);
        lienzo.cambia3.setText("Confiabilidad 2: ");
        lienzo.imageCambia2.setImageResource(R.drawable.tclprobabilidad2);
        lienzo.cambia4.setText("Tamaño de muestra 1");
        lienzo.cambia5.setText("Tamaño de muestra 2: ");
        lienzo.imageCambia3.setImageResource(R.drawable.tlcn1);
        lienzo.imageCambia4.setImageResource(R.drawable.tlcn2);
        lienzo.imCambia6.setImageResource(R.drawable.p0);
        lienzo.cambia6.setText("Nueva diferencia de proporciones: ");
        lienzo.imageCambia7.setImageResource(R.drawable.dato_g);
        lienzo.cambia7.setText("Probabilidad: ");
        lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
        lienzo.imageView2.setImageResource(R.drawable.tcldiferenciaproporcionesz);
        lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
        lienzo.imageView.setVisibility(View.GONE);
        lienzo.sustLimSup.setVisibility(View.GONE);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setText("P(p1-p2≤)");
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setText("P(p1-p2>)");
        lienzo.tablaDatos.setVisibility(View.GONE);
        lienzo.liminferior.setVisibility(View.GONE);
        lienzo.layCalc1.setVisibility(View.GONE);
        lienzo.layCalc2.setVisibility(View.GONE);
        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE);
        lienzo.limitsuperior.setVisibility(View.GONE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.button2.setVisibility(View.VISIBLE);
        lienzo.button2.setText("Calcular tamaño mínimo de muestra");

        lienzo.imageButton11.setVisibility(View.VISIBLE);
        lienzo.imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TCLIMITEDIFERENCIAPROPORCIONES.this);
                alertDialog.setMessage("Ésta opción UNICAMENTE SE PUEDE ELEGIR si lo que se conoce es la nueva diferencia de proporciones. Y NO SE PUEDE ELEGIR cuando lo que se conoce es la diferencia entre la diferencia de proporciones y la verdadera diferencia de proporciones")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog botonAyuda1 = alertDialog.create();
                botonAyuda1.setTitle("¿Debo elegir esta opción?");

                botonAyuda1.show();
            }
        });

        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.mediaPoblacional.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lienzo.calcularIntervaloConfianza.setText("P(p1-p2≤"+lienzo.mediaPoblacional.getText().toString()+")");
                lienzo.otroBotonAux.setText("P(p1-p2>"+lienzo.mediaPoblacional.getText().toString()+")");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                lienzo.calcularIntervaloConfianza.setText("P(p1-p2≤"+lienzo.mediaPoblacional.getText().toString()+")");
                lienzo.otroBotonAux.setText("P(p1-p2>"+lienzo.mediaPoblacional.getText().toString()+")");
            }
        });

        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos que se indican en rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);

                }else{
                    lienzo.liminferior.setText("Estadístico Z:");
                    lienzo.liminferior.setVisibility(View.VISIBLE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    diferenciaProporciones = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    probabilidad1 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    probabilidad2 = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    tamMuestra2 = (int) Math.floor(Double.parseDouble(lienzo.significancia.getText().toString()));
                    complemento1 = tabla.redondeoDecimales((1-probabilidad1),3);
                    complemento2 = tabla.redondeoDecimales((1-probabilidad2),3);
                    lienzo.imageView2.setImageResource(R.drawable.tcldiferenciaproporcionesz);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.tcldiferenciaproporciones);
                    lienzo.imagePromedio.setImageResource(R.drawable.tcldiferenciaproporciones);
                    lienzo.val1.setText("="+diferenciaProporciones);
                    lienzo.tlcDato1TV.setText("="+diferenciaProporciones);
                    lienzo.imageMiu.setImageResource(R.drawable.tclprobabilidad1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.tclprobabilidad1);
                    lienzo.val2.setText("="+probabilidad1);
                    lienzo.tlcDato2TV.setText("="+probabilidad1);
                    lienzo.imageSignificancia.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento);
                    lienzo.val3.setText("="+complemento1);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento);
                    lienzo.tlcDato3TV.setText("="+complemento1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tclprobabilidad2);
                    lienzo.desviation.setImageResource(R.drawable.tclprobabilidad2);
                    lienzo.val4.setText("="+probabilidad2);
                    lienzo.tlcDato4TV.setText("="+probabilidad2);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento2);
                    lienzo.imageN.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento2);
                    lienzo.tlcDato5TV.setText("="+complemento2);
                    lienzo.val5.setText("="+complemento2);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcn1);
                    lienzo.imageDato6.setImageResource(R.drawable.tlcn1);
                    lienzo.val6.setText("="+tamMuestra1);
                    lienzo.tlcDato6TV.setText("="+tamMuestra1);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.imageDato7.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcDato7TV.setText("="+tamMuestra2);
                    lienzo.val7.setText("="+tamMuestra2);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    estadisticoZ = tabla.redondeoDecimales(((diferenciaProporciones-(probabilidad1-probabilidad2))/(Math.sqrt((((probabilidad1*complemento1)/tamMuestra1)+((probabilidad2*complemento2)/tamMuestra2))))),3);
                    resultado = tabla.tablazetaAcumulada(estadisticoZ);
                    lienzo.output.setText("P(p1-p2≤"+diferenciaProporciones+")=" +resultado + "=" + tabla.redondeoDecimales(( resultado*100),5) + "%");
                    lienzo.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución para la diferencia de proporciones [Z], que posteriormente buscaremos en las tablas de la distribución acumulada normal estándar.\nPara calcular nuestro estádistico de prueba nos apoyaremos en la siguiente fórmula:" );
                    String aux2 = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TCLIMITEDIFERENCIAPROPORCIONES.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TCLIMITEDIFERENCIAPROPORCIONES.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());lienzo.liminferior.setText("Estadístico Z: ");
                    lienzo.textView15.setText("Z=["+diferenciaProporciones+"-("+probabilidad1+"-"+probabilidad2+")]÷√[(("+probabilidad1+"*"+complemento1+")÷"+tamMuestra1+")+(("+probabilidad2+"*"+complemento2+")/"+tamMuestra2+")] =" + estadisticoZ);
                    lienzo.paso2.setText("Paso 2: \n\n El segundo paso es buscar el valor resultante de la variable Z calculada anteriormente:\nZ = "+ estadisticoZ+"\n\nEn las tablas de la distribución acumulada normal estándar, en éste caso el valor encontrado en tablas es de:\n" + resultado + "\n\nConclusión: La probabilidad de que la diferencia de proporciones sea menor o igual que: " + diferenciaProporciones+ " es de: " + resultado + "= " +tabla.redondeoDecimales(( resultado*100),5) + "%");
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                }
            }
        });

        lienzo.otroBotonAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos que se indican en rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                }else{
                    lienzo.liminferior.setText("Estadístico Z:");
                    lienzo.liminferior.setVisibility(View.VISIBLE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.textView5.setVisibility(View.GONE);
                    diferenciaProporciones = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    probabilidad1 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    probabilidad2 = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    tamMuestra2 = (int) Math.floor(Double.parseDouble(lienzo.significancia.getText().toString()));
                    complemento1 = tabla.redondeoDecimales((1-probabilidad1),3);
                    complemento2 = tabla.redondeoDecimales((1-probabilidad2),3);
                    lienzo.imageView2.setImageResource(R.drawable.tcldiferenciaproporcionesz);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.tcldiferenciaproporciones);
                    lienzo.imagePromedio.setImageResource(R.drawable.tcldiferenciaproporciones);
                    lienzo.val1.setText("="+diferenciaProporciones);
                    lienzo.tlcDato1TV.setText("="+diferenciaProporciones);
                    lienzo.imageMiu.setImageResource(R.drawable.tclprobabilidad1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.tclprobabilidad1);
                    lienzo.val2.setText("="+probabilidad1);
                    lienzo.tlcDato2TV.setText("="+probabilidad1);
                    lienzo.imageSignificancia.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento);
                    lienzo.val3.setText("="+complemento1);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento);
                    lienzo.tlcDato3TV.setText("="+complemento1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tclprobabilidad2);
                    lienzo.desviation.setImageResource(R.drawable.tclprobabilidad2);
                    lienzo.val4.setText("="+probabilidad2);
                    lienzo.tlcDato4TV.setText("="+probabilidad2);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento2);
                    lienzo.imageN.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento2);
                    lienzo.tlcDato5TV.setText("="+complemento2);
                    lienzo.val5.setText("="+complemento2);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcn1);
                    lienzo.imageDato6.setImageResource(R.drawable.tlcn1);
                    lienzo.val6.setText("="+tamMuestra1);
                    lienzo.tlcDato6TV.setText("="+tamMuestra1);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.imageDato7.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcDato7TV.setText("="+tamMuestra2);
                    lienzo.val7.setText("="+tamMuestra2);
                    estadisticoZ = tabla.redondeoDecimales(((diferenciaProporciones-(probabilidad1-probabilidad2))/(Math.sqrt((((probabilidad1*complemento1)/tamMuestra1)+((probabilidad2*complemento2)/tamMuestra2))))),3);
                    resultado = 1-tabla.tablazetaAcumulada(estadisticoZ);
                    resultado = tabla.redondeoDecimales(resultado,6);
                    lienzo.output.setText("P(p1-p2>"+diferenciaProporciones+")=" +resultado + " = " + tabla.redondeoDecimales((resultado*100),5) + "%");
                    lienzo.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución para la diferencia de proporciones [Z], que posteriormente buscaremos en las tablas de la distribución acumulada normal estándar.\nPara calcular nuestro estádistico de prueba nos apoyaremos en la siguiente fórmula:" );
                    String aux2 = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TCLIMITEDIFERENCIAPROPORCIONES.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TCLIMITEDIFERENCIAPROPORCIONES.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());lienzo.liminferior.setText("Estadístico Z: ");
                    lienzo.liminferior.setText("Estadístico Z: ");
                    lienzo.textView15.setText("Z=["+diferenciaProporciones+"-("+probabilidad1+"-"+probabilidad2+")]÷√[(("+probabilidad1+"*"+complemento1+")÷"+tamMuestra1+")+(("+probabilidad2+"*"+complemento2+")/"+tamMuestra2+")]= " + estadisticoZ);
                    lienzo.paso2.setText("Paso 2:\n\nEl segundo paso es buscar el valor calculado de la estadística de prueba Z:\n" + estadisticoZ + "\nEn las tablas de la distribución normal estándar. En éste caso el valor encontrado en tablas es:\n" + tabla.redondeoDecimales((1-resultado),5)+"\n\nLas tablas de la distribución normal estándar entrega valores de PROBABILIDAD para los distintos valores menores o iguales que Z. Por lo tanto si se requiere calcular un valor mayor que Z, se hace necesario calcular el complemento de la probabilidad encontrada en tablas:\n\n1-"+tabla.redondeoDecimales((1-resultado),5) + "=" + resultado + " = " + tabla.redondeoDecimales((resultado*100),5));
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                }
            }
        });

        lienzo.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                if(faltanDatos()){
                    desplegarNotificacion("Por favor ingresa todos los datos que se indican en rojo");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }else{
                    probabilidad1 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    probabilidad2 = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    complemento1 = tabla.redondeoDecimales((1-probabilidad1),3);
                    complemento2 = tabla.redondeoDecimales((1-probabilidad2),3);
                    confianza = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    valTablas = tabla.tablazetaAcumuladaPotencia(confianza);
                    p0 = Double.parseDouble(lienzo.miu1.getText().toString());
                    tamMinimoMuestra =(int) Math.floor(((probabilidad1*complemento1)+(probabilidad2*complemento2))*(Math.pow((valTablas/(p0-(probabilidad1-probabilidad2))),2))) + 1;
                    lienzo.output.setText("El tamaño mínimo de las muestras suponiendo que son iguales es de: " + tamMinimoMuestra);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.tclprobabilidad1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.tclprobabilidad2);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.p0);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tcldiferenciaproporcionescomplemento2);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.gammaunomenosalfa);
                    lienzo.imageView2.setImageResource(R.drawable.tcltamminimodiferemciaproporciones);
                    lienzo.tlcDato1TV.setText("="+probabilidad1);
                    lienzo.tlcDato2TV.setText("="+probabilidad2);
                    lienzo.tlcDato3TV.setText("="+p0);
                    lienzo.tlcDato4TV.setText("="+complemento1);
                    lienzo.tlcDato5TV.setText("="+complemento2);
                    lienzo.tlcDato6TV.setText("="+valTablas);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("El primer paso es buscar el valor de " + confianza + " en las tablas acumuladas de la distribución normal estándar, en éste caso el valor encontrado en tablas es: " + valTablas + " sustituyendo valores en la fórmula para calcular los tamaños mínimos de las muestras: ");
                    lienzo.liminferior.setVisibility(View.GONE);
                    lienzo.textView15.setText("n={("+probabilidad1+"*"+complemento1+")+("+probabilidad2+"*"+complemento2+")*["+valTablas+"÷"+p0+"-("+probabilidad1+"-"+probabilidad2+")]^2} + 1 = " + tamMinimoMuestra);
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.paso2Phipodc.setVisibility(View.GONE);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.liminferior.setVisibility(View.VISIBLE);
                    lienzo.liminferior.setText("Tamaño mínimo de la muestra:");
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
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
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.mediaPoblacional.setText("");
                lienzo.desviacionEstandarPob.setText("");
                lienzo.promedioMuestral.setText("");
                lienzo.tamMuestral.setText("");
                lienzo.significancia.setText("");
                lienzo.miu1.setText("");
                lienzo.editTextCambia7.setText("");
                lienzo.output.setText("");
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
            }
        });

    }


    public boolean faltaDato(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()|| lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty())return true;
        else return false;
    }
    public boolean faltanDatos(){
       if(lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty())return true;
       else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }

}