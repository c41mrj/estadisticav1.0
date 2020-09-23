package com.example.estadistica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class TLCPROPORCIONESIU extends AppCompatActivity {
    private double probabilidad, estadisticoZ;
    private ActivityPruebaHipBinding lienzo;
    private CalculoTablas tablaZ = new CalculoTablas();
    private double proporcion,confiabilidad,p0,confianza,valTablas;
    private int tamMuestra,tamMinimoMuestra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        this.setTitle("T.C.L para proporciones");
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.linearLayoutDato3.setVisibility(View.GONE);
        lienzo.llc5.setVisibility(View.GONE);
        lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
        lienzo.cambia6.setText("nueva proporcion: ");
        lienzo.imCambia6.setImageResource(R.drawable.p0);
        lienzo.cambia7.setText("Probabilidad: ");
        lienzo.imageCambia7.setImageResource(R.drawable.dato_g);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.VISIBLE);
        lienzo.cambia8.setText("nueva diferencia:");
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE);
        lienzo.imageViewCambia1.setImageResource(R.drawable.probabilidadmayorque1);
        lienzo.cambia1.setText("Proporcion: ");
        lienzo.cambia2.setText("Confiabilidad: ");
        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
        lienzo.imageView2.setImageResource(R.drawable.estadisticozproporcion);
        lienzo.tlcimageDato1.setImageResource(R.drawable.probabilidadmayorque1);
        lienzo.tlcImageDato2.setImageResource(R.drawable.confiabilidad);
        lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
        lienzo.tlcImageDato4.setImageResource(R.drawable.tclcomplemento);
        lienzo.llz5.setVisibility(View.GONE);
        lienzo.llz6.setVisibility(View.GONE);
        lienzo.llz7.setVisibility(View.GONE);
        lienzo.imageCambia.setImageResource(R.drawable.confiabilidad);
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setText("P(X≤)");
        lienzo.otroBotonAux.setText("P(X>)");
        lienzo.imageView2.setImageResource(R.drawable.estadisticozproporcion);
        lienzo.imageView.setVisibility(View.GONE);
        lienzo.tablaDatos.setVisibility(View.GONE);
        lienzo.button2.setVisibility(View.VISIBLE);
        lienzo.tvDatos.setVisibility(View.GONE);
        lienzo.imageView.setVisibility(View.GONE);
        lienzo.button2.setText("Calcular tamaño minimo de la muestra si se conoce el valor de la nueva proporción a tomar en cuenta");
        lienzo.mediaPoblacional.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lienzo.calcularIntervaloConfianza.setText("P(X≤)");
                lienzo.otroBotonAux.setText("P(X>)");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lienzo.calcularIntervaloConfianza.setText("P(X≤"+lienzo.mediaPoblacional.getText().toString()+")");
                lienzo.otroBotonAux.setText("P(X>"+lienzo.mediaPoblacional.getText().toString()+")");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        lienzo.imageButton11.setVisibility(View.VISIBLE);
        lienzo.imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TLCPROPORCIONESIU.this);
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
                LayoutInflater imagenAlert = LayoutInflater.from(TLCPROPORCIONESIU.this);
                final View vista = imagenAlert.inflate(R.layout.botonayudatclproporciones1,null);
                botonAyuda1.setView(vista);
                botonAyuda1.show();
            }
        });



        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                }else{
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    confiabilidad = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    proporcion = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    double complemento = 1-confiabilidad;
                    complemento = tablaZ.redondeoDecimales(complemento,6);
                    estadisticoZ = tablaZ.redondeoDecimales(((proporcion-confiabilidad)/Math.sqrt((confiabilidad*complemento)/tamMuestra)),4);
                    probabilidad = tablaZ.tablazetaAcumulada(estadisticoZ);
                    lienzo.output.setText("P(X≤"+proporcion + ")= " + probabilidad + " = " + tablaZ.redondeoDecimales((probabilidad*100),5));
                    lienzo.cleanit.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.tablerowsignificancia.setVisibility(View.GONE);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución para proporciones [Z], que posteriormente buscaremos en las tablas de la distribución acumulada normal estándar.\nPara calcular nuestro estádistico de prueba nos apoyaremos en la siguiente fórmula:" );
                    String aux2 = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TLCPROPORCIONESIU.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TLCPROPORCIONESIU.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.teorema.setVisibility(View.GONE);
                    lienzo.calculo2.setVisibility(View.GONE);
                    lienzo.calcOp1.setImageResource(R.drawable.estadisticozproporcion);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Estadístico Z:");
                    lienzo.tlcDato1TV.setText("="+proporcion);
                    lienzo.tlcDato2TV.setText("="+confiabilidad);
                    lienzo.tlcDato3TV.setText("="+tamMuestra);
                    lienzo.tlcDato4TV.setText("="+complemento);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.estadisticozproporcion);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.probabilidadmayorque1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.confiabilidad);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tclcomplemento);
                    lienzo.val1.setText("="+proporcion);
                    lienzo.val2.setText("="+confiabilidad);
                    lienzo.val4.setText("="+tamMuestra);
                    lienzo.val5.setText("="+complemento);
                    lienzo.imagePromedio.setImageResource(R.drawable.probabilidadmayorque1);
                    lienzo.imageMiu.setImageResource(R.drawable.confiabilidad);
                    lienzo.desviation.setImageResource(R.drawable.dato_f);
                    lienzo.imageN.setImageResource(R.drawable.complemento);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.textView15.setText("Z =("+proporcion + "-" + confiabilidad +")" + "/" + "√((" + confiabilidad + "*"+ complemento + ")/ " + tamMuestra + ") = " + estadisticoZ);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso 2:");
                    lienzo.reglaDecision.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso 2:\n\nEl segundo paso es buscar el valor calculado de la variable z:\n\nZ=" + estadisticoZ + " \n\nEn las tablas de la distribucion acumulada de la normal estándar, en este caso el valor en tablas es: " + probabilidad);
                    lienzo.paso2Phipodc.setText("Conclusión: Cómo podemos observar, la probabilidad de que la proporcion sea menor o igual que: "+ proporcion + " es de: " + probabilidad + "=" + tablaZ.redondeoDecimales((probabilidad*100),5) + "%");
                    lienzo.paso2Phipodc.setTextColor(Color.RED);
                    lienzo.caso.setVisibility(View.GONE);
                    lienzo.potprueba.setVisibility(View.GONE);
                    lienzo.nuevoMiu.setVisibility(View.GONE);
                    lienzo.niumiu.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                }
            }
        });


        lienzo.otroBotonAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                }else{
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    confiabilidad = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    proporcion = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    double complemento = 1-confiabilidad;
                    complemento = tablaZ.redondeoDecimales(complemento,6);
                    estadisticoZ = tablaZ.redondeoDecimales(((proporcion-confiabilidad)/Math.sqrt((confiabilidad*complemento)/tamMuestra)),4);
                    probabilidad = tablaZ.tablazetaAcumulada(estadisticoZ);
                    double aux = probabilidad;
                    probabilidad = 1-probabilidad;
                    probabilidad = tablaZ.redondeoDecimales(probabilidad,6);
                    lienzo.output.setText("P(X>"+proporcion + ")= " + probabilidad + " = " + tablaZ.redondeoDecimales((probabilidad*100),5));
                    lienzo.cleanit.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.tablerowsignificancia.setVisibility(View.GONE);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución para proporciones [Z], que posteriormente buscaremos en las tablas de la distribución acumulada normal estándar.\nPara calcular nuestro estádistico de prueba nos apoyaremos en la siguiente fórmula:" );
                    String aux2 = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TLCPROPORCIONESIU.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TLCPROPORCIONESIU.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.teorema.setVisibility(View.GONE);
                    lienzo.calculo2.setVisibility(View.GONE);
                    lienzo.calcOp1.setImageResource(R.drawable.estadisticozproporcion);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Estadístico Z:");
                    lienzo.tlcDato1TV.setText("="+proporcion);
                    lienzo.tlcDato2TV.setText("="+confiabilidad);
                    lienzo.tlcDato3TV.setText("="+tamMuestra);
                    lienzo.tlcDato4TV.setText("="+complemento);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.estadisticozproporcion);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.probabilidadmayorque1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.confiabilidad);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tclcomplemento);
                    lienzo.val1.setText("="+proporcion);
                    lienzo.val2.setText("="+confiabilidad);
                    lienzo.val4.setText("="+tamMuestra);
                    lienzo.val5.setText("="+complemento);
                    lienzo.imagePromedio.setImageResource(R.drawable.probabilidadmayorque1);
                    lienzo.imageMiu.setImageResource(R.drawable.confiabilidad);
                    lienzo.desviation.setImageResource(R.drawable.dato_f);
                    lienzo.imageN.setImageResource(R.drawable.complemento);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.textView15.setText("Z =("+proporcion + "-" + confiabilidad +")" + "/" + "√((" + confiabilidad + "*"+ complemento + ")/ " + tamMuestra + ") = " + estadisticoZ);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso 2:");
                    lienzo.reglaDecision.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso 2:\n\nEl segundo paso es buscar el valor calculado de la estadística de prueba Z:\n" + estadisticoZ + "\nEn las tablas de la distribución normal estándar. En éste caso el valor encontrado en tablas es:\n" + tablaZ.redondeoDecimales((1-probabilidad),5)+"\n\nLas tablas de la distribución normal estándar entrega valores de PROBABILIDAD para los distintos valores menores o iguales que Z. Por lo tanto si se requiere calcular un valor mayor que Z, se hace necesario calcular el complemento de la probabilidad encontrada en tablas:\n\n1-"+tablaZ.redondeoDecimales((1-probabilidad),5) + "=" + probabilidad);
                    lienzo.paso2Phipodc.setText("Cómo podemos observar, la probabilidad de que la proporción muestral sea mayor que: "+proporcion+":\n\nP(x>"+proporcion+") es de: " + (probabilidad) + "=" + tablaZ.redondeoDecimales((probabilidad*100),5) + "%");
                    lienzo.paso2Phipodc.setTextColor(Color.RED);
                    lienzo.caso.setVisibility(View.GONE);
                    lienzo.potprueba.setVisibility(View.GONE);
                    lienzo.nuevoMiu.setVisibility(View.GONE);
                    lienzo.niumiu.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                }
            }
        });

        lienzo.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltanDatos()){
                    desplegarNotificacion("Por favor llena todos los espacios en rojo");
                    lienzo.cambia7.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                }else{
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    confiabilidad = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    double complemento = 1-confiabilidad;
                    complemento = tablaZ.redondeoDecimales(complemento,6);
                    confianza = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    double signif = 1-confianza;
                    signif = tablaZ.redondeoDecimales(signif,4);
                    p0 = Double.parseDouble(lienzo.miu1.getText().toString());
                    valTablas = tablaZ.tablazetaAcumuladaPotencia(signif);
                    valTablas = tablaZ.redondeoDecimales(valTablas,6);
                    tamMinimoMuestra =(int) Math.floor((confiabilidad*complemento)*(Math.pow((valTablas/(p0-confiabilidad)),2))) + 1;
                    lienzo.output.setText("El tamaño mínimo de muestra deberá ser de = " + tamMinimoMuestra);
                    lienzo.imageView2.setImageResource(R.drawable.tlctamminimomuestraproporciones);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.confiabilidad);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.complemento);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.p0);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.gammaunomenosalfa);
                    lienzo.tlcDato1TV.setText("="+confiabilidad);
                    lienzo.tlcDato2TV.setText("="+complemento);
                    lienzo.tlcDato3TV.setText("="+p0);
                    lienzo.tlcDato4TV.setText("="+valTablas);
                    lienzo.paso1Phip.setText("Para calcular el tamaño minimo de la muestra requerido para cumplir la condición deseada se hace necesaria la busqueda del valor de 1-α donde: \n\nα = "+confianza +"\n\n1-α = "+ tablaZ.redondeoDecimales((1-confianza),5) + "\n\nEn las tablas de la distribución acumulada normal estándar, en este caso el valor encontrado en las tablas es:  " + valTablas+ "\n\nSustituyendo en la fórmula del tamaño mínimo de la muestra para proporciones:");
                    lienzo.textView15.setText("{("+confiabilidad+"*"+complemento+")*["+valTablas+"÷("+p0+"-"+confiabilidad+")]^2} + 1 = " + tamMinimoMuestra);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Tamaño mínimo de muestra:");

                }
            }
        });

        lienzo.button8.setVisibility(View.VISIBLE);
        lienzo.button8.setText("Calcular tamaño mínimo de muestra si se conoce la diferencia entre la proporción y la verdadera proporción");
        lienzo.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltanDatos2()){
                    desplegarNotificacion("Por favor llena todos los espacios en rojo");
                    lienzo.cambia7.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                }else{
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    confiabilidad = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    double complemento = 1-confiabilidad;
                    complemento = tablaZ.redondeoDecimales(complemento,6);
                    confianza = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    double signif = (1-confianza)/2;
                    signif = tablaZ.redondeoDecimales(signif,4);
                    p0 = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    valTablas = tablaZ.tablazetaAcumuladaPotencia(signif);
                    valTablas = tablaZ.redondeoDecimales(valTablas,6);
                    tamMinimoMuestra =(int) Math.floor((confiabilidad*complemento)*(Math.pow((valTablas/(p0)),2))) + 1;
                    lienzo.output.setText("El tamaño mínimo de muestra deberá ser de = " + tamMinimoMuestra);
                    lienzo.imageView2.setImageResource(R.drawable.tamminimomuestraproporciones2);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.confiabilidad);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.complemento);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.p0);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.unomenosalfamedios);
                    lienzo.tlcDato1TV.setText("="+confiabilidad);
                    lienzo.tlcDato2TV.setText("="+complemento);
                    lienzo.tlcDato3TV.setText("="+p0);
                    lienzo.tlcDato4TV.setText("="+signif);
                    lienzo.paso1Phip.setText("Para calcular el tamaño minimo de la muestra requerido para cumplir la condición deseada se hace necesaria la busqueda del valor de 1-α donde: \n\nα = "+confianza +"\n\n1-α = "+ tablaZ.redondeoDecimales((1-confianza),5) + "\n\n(1-α)2 = "+ tablaZ.redondeoDecimales(((1-confianza)/2),5) + "\n\nEn las tablas de la distribución acumulada normal estándar, en este caso el valor encontrado en las tablas es:  " + valTablas+ "\n\nSustituyendo en la fórmula del tamaño mínimo de la muestra para proporciones:");
                    lienzo.textView15.setText("{("+confiabilidad+"*"+complemento+")*["+valTablas+"÷("+p0+")]^2} + 1 = " + tamMinimoMuestra);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Tamaño mínimo de muestra:");

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
                lienzo.tamMuestral.setText("");
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.output.setText("");
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.editTextCambia7.setText("");
                lienzo.editTextCambia8.setText("");
                lienzo.miu1.setText("");
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
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltanDatos(){
       if(lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty())return true;
       else return false;
    }

    public boolean faltanDatos2(){
        if(lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty()||lienzo.editTextCambia8.getText().toString().isEmpty())return true;
        else return false;
    }




    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }



}