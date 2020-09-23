package com.example.estadistica;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
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


public class TCLMEDIAIU extends AppCompatActivity {
    public ActivityPruebaHipBinding b1;
    public CalculoTablas tablaZ = new CalculoTablas();
    public TEOREMACENTRALLIMITEMEDIA tlc;
    public double promedio,miu,desviacionEstandar;
    public int tamMuestra;
    public double estadisticoZ,probabilidad,promedio1,estadisticoZ1,probabilidad1,confianza,d0,valTablas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b1 = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = b1.getRoot();
        setContentView(vista);
        this.setTitle("T.C.L para la media");
        b1.cleanit.setVisibility(View.GONE);
        b1.verProcedimiento.setVisibility(View.GONE);
        b1.imageButton.setVisibility(View.GONE);
        b1.imageButton2.setImageResource(R.drawable.p_x_menorigual);
        b1.imageButton3.setImageResource(R.drawable.probabilidadmayorque1);
        b1.imageButton5.setVisibility(View.GONE);
        b1.imageButton7.setVisibility(View.GONE);
        b1.imageButton8.setVisibility(View.GONE);
        b1.constraintLayoutButtons.setVisibility(View.GONE);
        b1.calcularIntervaloConfianza.setText("P(X≤)");
        b1.button2.setText("P(X>)");
        b1.cambia7.setText("Nuevo promedio:");
        b1.otroBotonAux.setText("Calcular tamaño minimo de muestra si se conoce d0 y se sabe que: P(|X-mu|<d0)");
        b1.otroBotonAux.setVisibility(View.VISIBLE);
        b1.button.setVisibility(View.VISIBLE);
        b1.button.setText("Calcular tamaño mínimo de muestra si se conoce d0 y se sabe que: P(|x-mu|>d0)");
        b1.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        b1.button9.setVisibility(View.VISIBLE);
        b1.button9.setText("Calcular tamaño mínimo de muestra si se conoce x0");
        b1.imageButton14.setVisibility(View.VISIBLE);
        b1.procedimiento.setVisibility(View.GONE);
        b1.llc5.setVisibility(View.VISIBLE);
        b1.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        b1.LinearLayoutDato6.setVisibility(View.VISIBLE);
        b1.LinearLayoutDato8Opcional.setVisibility(View.GONE);
        b1.LinearLayoutDato9Opcional.setVisibility(View.GONE);
        b1.imCambia6.setImageResource(R.drawable.d0);
        b1.imageCambia7.setImageResource(R.drawable.x0);
        b1.LinearLayoutLimSup.setVisibility(View.GONE);
        b1.calcOp1.setVisibility(View.VISIBLE);
        b1.layoutPotencia.setVisibility(View.GONE);
        b1.cambia5.setText("Probabilidad: ");
        b1.imageCambia4.setVisibility(View.GONE);
        b1.imageCambia4.setImageResource(R.drawable.dato_g);
        b1.button2.setVisibility(View.VISIBLE);
        b1.cambia6.setText("Diferencia : ");
        b1.tlcImageDato4.setImageResource(R.drawable.dato_f);
        b1.cleanit.setVisibility(View.VISIBLE);
        b1.promedioMuestral.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                b1.calcularIntervaloConfianza.setText("P(X≤)");
                b1.button2.setText("P(X>)");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                b1.calcularIntervaloConfianza.setText("P(X≤ "+b1.promedioMuestral.getText() +")");
                b1.button2.setText("P(X> "+b1.promedioMuestral.getText() +")");
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        b1.imageButton12.setVisibility(View.VISIBLE);
        b1.imageButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TCLMEDIAIU.this);
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
                LayoutInflater imagenAlert = LayoutInflater.from(TCLMEDIAIU.this);
                final View vista = imagenAlert.inflate(R.layout.botonayudatclmedia1,null);
                botonAyuda1.setView(vista);
                botonAyuda1.show();
            }
        });

        b1.imageButton13.setVisibility(View.VISIBLE);
        b1.imageButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder region = new AlertDialog.Builder(TCLMEDIAIU.this);
                region.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog glosario1 = region.create();
                glosario1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagen_alert1 = LayoutInflater.from(TCLMEDIAIU.this);
                final View vista = imagen_alert1.inflate(R.layout.botonayudatclmedia2,null);
                glosario1.setView(vista);
                glosario1.show();
            }
        });

        b1.imageButton14.setVisibility(View.VISIBLE);
        b1.imageButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder region = new AlertDialog.Builder(TCLMEDIAIU.this);
                region.setMessage("")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog glosario1 = region.create();
                glosario1.setTitle("¿Debo elegir esta opción?");
                LayoutInflater imagen_alert1 = LayoutInflater.from(TCLMEDIAIU.this);
                final View vista = imagen_alert1.inflate(R.layout.botonayudatclmedia3,null);
                glosario1.setView(vista);
                glosario1.show();
            }
        });


        b1.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                b1.procedimiento.setVisibility(View.GONE);
                b1.verProcedimiento.setVisibility(View.GONE);
                b1.cambia1.setTextColor(Color.BLACK);
                b1.cambia2.setTextColor(Color.BLACK);
                b1.cambia3.setTextColor(Color.BLACK);
                b1.cambia4.setTextColor(Color.BLACK);
                b1.cambia5.setTextColor(Color.BLACK);
                b1.cambia6.setTextColor(Color.BLACK);
                b1.cambia7.setTextColor(Color.BLACK);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos");
                    b1.cambia1.setTextColor(Color.RED);
                    b1.cambia2.setTextColor(Color.RED);
                    b1.cambia3.setTextColor(Color.RED);
                    b1.cambia4.setTextColor(Color.RED);
                }else{
                    b1.tvDatos.setVisibility(View.VISIBLE);
                    b1.tablaDatos.setVisibility(View.VISIBLE);
                    b1.calcOp2.setImageResource(R.drawable.tlc_estadisticoz);
                    b1.calcOp2.setVisibility(View.VISIBLE);
                    tamMuestra = Integer.parseInt(b1.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(b1.desviacionEstandarPob.getText().toString());
                    promedio = Double.parseDouble(b1.promedioMuestral.getText().toString());
                    miu = Double.parseDouble(b1.mediaPoblacional.getText().toString());
                    b1.tlcDato1TV.setText("="+promedio);
                    b1.tlcDato2TV.setText("="+miu);
                    b1.tlcDato3TV.setText("="+desviacionEstandar);
                    b1.tlcDato4TV.setText("="+tamMuestra);
                    estadisticoZ = tablaZ.redondeoDecimales(((promedio-miu)/(desviacionEstandar/Math.sqrt(tamMuestra))),3);
                    probabilidad = tablaZ.redondeoDecimales((tablaZ.tablazetaAcumulada(estadisticoZ)),5);
                    b1.output.setText("P(X≤"+promedio + ")= " + probabilidad +"≈" + tablaZ.redondeoDecimales((probabilidad*100),5) + "%");
                    b1.cleanit.setVisibility(View.VISIBLE);
                    b1.verProcedimiento.setVisibility(View.VISIBLE);
                    b1.tablerowsignificancia.setVisibility(View.GONE);
                    b1.imageView2.setImageResource(R.drawable.tlc_estadisticoz);
                    b1.val1.setText(""+promedio);
                    b1.val2.setText(""+miu);
                    b1.val4.setText(""+desviacionEstandar);
                    b1.val5.setText(""+tamMuestra);
                    b1.dtoOpcional.setVisibility(View.GONE);
                    b1.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución para la media [Z], que posteriormente buscaremos en las tablas de la distribución acumulada normal estándar.\nPara calcular nuestro estádistico de prueba nos apoyaremos en la siguiente fórmula:" );
                    String aux2 = b1.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TCLMEDIAIU.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TCLMEDIAIU.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    b1.paso1Phip.setText(ss2);
                    b1.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    b1.teorema.setVisibility(View.GONE);
                    b1.calculo2.setVisibility(View.GONE);
                    b1.calcOp1.setImageResource(R.drawable.tlc_estadisticoz);
                    b1.layCalc1.setVisibility(View.GONE);
                    b1.layCalc2.setVisibility(View.GONE);
                    b1.liminferior.setText("Estadístico Z:");
                    b1.imageView.setImageResource(R.drawable.tlc_estadisticoz);
                    b1.textView15.setText("Z = ("+promedio + "-" + miu +")" + "÷" + "(" + desviacionEstandar + "÷" + "√"+tamMuestra + ") = " + estadisticoZ);
                    b1.layoutDesaparece2.setVisibility(View.GONE);
                    b1.limitsuperior.setVisibility(View.GONE);
                    b1.reglaDecision.setVisibility(View.GONE);
                    b1.paso2.setText("Paso 2:\n\nEl segundo paso es buscar el valor calculado de la forma límite de la distribución:\nZ=" + estadisticoZ + "\nen las tablas de la distribucion acumulada de la normal estándar, en este caso el valor en tablas es:\n\n" + probabilidad);
                    b1.textView14.setText("Conclusión:");
                    b1.paso2Phipodc.setText("Cómo podemos observar, la probabilidad de que el promedio muestral sea menor o igual que "+promedio+": \n\nP(x≤"+promedio+")"+" es de: " + probabilidad + "=" + tablaZ.redondeoDecimales((probabilidad*100),5) + "%");
                    b1.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    b1.caso.setVisibility(View.GONE);
                    b1.potprueba.setVisibility(View.GONE);
                    b1.nuevoMiu.setVisibility(View.GONE);
                    b1.niumiu.setVisibility(View.GONE);
                    b1.layoutPotencia.setVisibility(View.GONE);
                    b1.textView5.setVisibility(View.GONE);
                    b1.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    b1.tlcimageDato1.setImageResource(R.drawable.dato_a);
                    b1.tlcImageDato2.setImageResource(R.drawable.dato_b);
                    b1.tlcImageDato3.setImageResource(R.drawable.dato_e);
                    b1.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    b1.llz1.setVisibility(View.VISIBLE);
                    b1.llz2.setVisibility(View.VISIBLE);
                    b1.llz3.setVisibility(View.VISIBLE);
                    b1.llz4.setVisibility(View.VISIBLE);
                    b1.llz5.setVisibility(View.GONE);
                    b1.llz6.setVisibility(View.GONE);
                    b1.llz7.setVisibility(View.GONE);
                    b1.imageView2.setImageResource(R.drawable.tlc_estadisticoz);
                    b1.linearLayoutReglaDecision.setVisibility(View.GONE);
                    b1.paso2.setVisibility(View.VISIBLE);
                    b1.paso2Phipodc.setVisibility(View.VISIBLE);
                    b1.linearLayoutDesapareceMil.setVisibility(View.VISIBLE);
                }
            }
        });


        b1.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.procedimiento.setVisibility(View.GONE);
                b1.verProcedimiento.setVisibility(View.GONE);
                b1.cambia1.setTextColor(Color.BLACK);
                b1.cambia2.setTextColor(Color.BLACK);
                b1.cambia3.setTextColor(Color.BLACK);
                b1.cambia4.setTextColor(Color.BLACK);
                b1.cambia5.setTextColor(Color.BLACK);
                b1.cambia6.setTextColor(Color.BLACK);
                b1.cambia7.setTextColor(Color.BLACK);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos");
                    b1.cambia1.setTextColor(Color.RED);
                    b1.cambia2.setTextColor(Color.RED);
                    b1.cambia3.setTextColor(Color.RED);
                    b1.cambia4.setTextColor(Color.RED);
                }else{
                    b1.tvDatos.setVisibility(View.VISIBLE);
                    b1.tablaDatos.setVisibility(View.VISIBLE);
                    tamMuestra = Integer.parseInt(b1.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(b1.desviacionEstandarPob.getText().toString());
                    promedio = Double.parseDouble(b1.promedioMuestral.getText().toString());
                    miu = Double.parseDouble(b1.mediaPoblacional.getText().toString());
                    estadisticoZ = tablaZ.redondeoDecimales(((promedio-miu)/(desviacionEstandar/Math.sqrt(tamMuestra))),3);
                    double probabilidad1 = tablaZ.redondeoDecimales((tablaZ.tablazetaAcumulada(estadisticoZ)),5);
                    probabilidad = tablaZ.redondeoDecimales((1-tablaZ.tablazetaAcumulada(estadisticoZ)),5);
                    b1.output.setText("P(X>"+promedio + ")= " + probabilidad +"≈" + tablaZ.redondeoDecimales((probabilidad*100),5) + "%");
                    b1.cleanit.setVisibility(View.VISIBLE);
                    b1.verProcedimiento.setVisibility(View.VISIBLE);
                    b1.tablerowsignificancia.setVisibility(View.GONE);
                    b1.val1.setText(""+promedio);
                    b1.val2.setText(""+miu);
                    b1.val4.setText(""+desviacionEstandar);
                    b1.val5.setText(""+tamMuestra);
                    b1.dtoOpcional.setVisibility(View.GONE);
                    b1.teorema.setVisibility(View.GONE);
                    b1.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución para la media[z], que posteriormente buscaremos en las tablas de la distribución acumulada normal estándar.\nPara calcular nuestro estádistico de prueba nos apoyaremos en la siguiente fórmula:" );
                    String aux2 = b1.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TCLMEDIAIU.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TCLMEDIAIU.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    b1.paso1Phip.setText(ss2);
                    b1.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    b1.calculo2.setVisibility(View.GONE);
                    b1.calcOp2.setImageResource(R.drawable.tlc_estadisticoz);
                    b1.layCalc1.setVisibility(View.GONE);
                    b1.liminferior.setText("Estadístico Z:");
                    b1.imageView.setImageResource(R.drawable.tlc_estadisticoz);
                    b1.textView15.setText("Z= ("+promedio + "-" + miu +")" + "÷" + "(" + desviacionEstandar + "÷" + "√"+tamMuestra + ") = " +estadisticoZ);
                    b1.layoutDesaparece2.setVisibility(View.GONE);
                    b1.limitsuperior.setVisibility(View.GONE);
                    b1.reglaDecision.setVisibility(View.GONE);
                    b1.paso2.setText("Paso 2:\n\nEl segundo paso es buscar el valor calculado de la estadística de prueba Z:\n" + estadisticoZ + "\nEn las tablas de la distribución normal estándar. En éste caso el valor encontrado en tablas es:\n" + tablaZ.redondeoDecimales((1-probabilidad),5)+"\n\nLas tablas de la distribución normal estándar entrega valores de PROBABILIDAD para los distintos valores menores o iguales que Z. Por lo tanto si se requiere calcular un valor mayor que Z, se hace necesario calcular el complemento de la probabilidad encontrada en tablas:\n\n1-"+tablaZ.redondeoDecimales((1-probabilidad),5) + "=" + probabilidad);
                    b1.paso2Phipodc.setText("Cómo podemos observar, la probabilidad de que el promedio muestral sea mayor que: "+promedio+":\n\nP(x>"+promedio+") es de: " + (probabilidad) + "=" + tablaZ.redondeoDecimales((probabilidad*100),5) + "%");
                    b1.textView14.setText("Conclusión:");
                    b1.caso.setVisibility(View.GONE);
                    b1.potprueba.setVisibility(View.GONE);
                    b1.nuevoMiu.setVisibility(View.GONE);
                    b1.niumiu.setVisibility(View.GONE);
                    b1.layoutPotencia.setVisibility(View.GONE);
                    b1.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    b1.tlcimageDato1.setImageResource(R.drawable.dato_a);
                    b1.tlcImageDato2.setImageResource(R.drawable.dato_b);
                    b1.tlcImageDato3.setImageResource(R.drawable.dato_e);
                    b1.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    b1.llz1.setVisibility(View.VISIBLE);
                    b1.llz2.setVisibility(View.VISIBLE);
                    b1.llz3.setVisibility(View.VISIBLE);
                    b1.llz4.setVisibility(View.VISIBLE);
                    b1.llz5.setVisibility(View.GONE);
                    b1.llz6.setVisibility(View.GONE);
                    b1.llz7.setVisibility(View.GONE);
                    b1.imageView2.setImageResource(R.drawable.tlc_estadisticoz);
                    b1.linearLayoutDesapareceMil.setVisibility(View.VISIBLE);
                    b1.paso2.setVisibility(View.VISIBLE);
                    b1.tlcDato1TV.setText("="+promedio);
                    b1.tlcDato2TV.setText("="+miu);
                    b1.tlcDato3TV.setText("="+desviacionEstandar);
                    b1.tlcDato4TV.setText("="+tamMuestra);
                    b1.paso2Phipodc.setVisibility(View.VISIBLE);
                    b1.layCalc1.setVisibility(View.GONE);
                    b1.layCalc2.setVisibility(View.GONE);
                    b1.LinearLayoutLimSup.setVisibility(View.GONE);
                }
            }
        });

        b1.otroBotonAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.procedimiento.setVisibility(View.GONE);
                b1.verProcedimiento.setVisibility(View.GONE);
                b1.cambia1.setTextColor(Color.BLACK);
                b1.cambia2.setTextColor(Color.BLACK);
                b1.cambia3.setTextColor(Color.BLACK);
                b1.cambia4.setTextColor(Color.BLACK);
                b1.cambia5.setTextColor(Color.BLACK);
                b1.cambia6.setTextColor(Color.BLACK);
                b1.cambia7.setTextColor(Color.BLACK);
                if(faltanDatos()){
                    desplegarNotificacion("Por favor rellena todos los campos");
                    b1.cambia2.setTextColor(Color.RED);
                    b1.cambia5.setTextColor(Color.RED);
                    b1.cambia6.setTextColor(Color.RED);
                }else{
                    b1.textView5.setVisibility(View.GONE);
                    b1.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    b1.paso2Phipodc.setVisibility(View.GONE);
                    b1.layoutDesaparece2.setVisibility(View.GONE);
                    b1.limitsuperior.setVisibility(View.GONE);
                    b1.layCalc1.setVisibility(View.GONE);
                    b1.layCalc2.setVisibility(View.GONE);
                    b1.tablaDatos.setVisibility(View.GONE);
                    b1.tvDatos.setVisibility(View.GONE);
                    b1.llz3.setVisibility(View.GONE);
                    b1.llz5.setVisibility(View.GONE);
                    b1.llz6.setVisibility(View.GONE);
                    b1.llz7.setVisibility(View.GONE);
                    b1.tlcImageDato4.setImageResource(R.drawable.unomenosalfamedios);
                    b1.tlcimageDato1.setImageResource(R.drawable.dato_e);
                    b1.tlcImageDato2.setImageResource(R.drawable.d0);
                    b1.imageView2.setImageResource(R.drawable.tcltamminimomuestramedia);
                    desviacionEstandar = Double.parseDouble(b1.desviacionEstandarPob.getText().toString());
                    confianza =Double.parseDouble(b1.significancia.getText().toString());
                    d0 = Double.parseDouble(b1.miu1.getText().toString());
                    valTablas = tablaZ.tablazetaAcumuladaPotencia(tablaZ.redondeoDecimales(((1-confianza)/2),4));
                    int tamañoMinimoMuestra =(int) (Math.floor(Math.pow(((desviacionEstandar*valTablas)/d0),2))) + 1;
                    b1.output.setText("El tamaño mínimo de muestra para cumplir las condiciones requeridas es de: " + tamañoMinimoMuestra);
                    b1.tlcDato1TV.setText("=" + desviacionEstandar);
                    b1.tlcDato4TV.setText("=" + tablaZ.redondeoDecimales(((1-confianza)/2),5));
                    b1.tlcDato2TV.setText("=" + d0);
                    b1.paso1Phip.setText("Para poder calcular el tamaño mínimo de muestra, se hace necesario buscar el valor de ø((1-α)/2) en las tablas acumuladas de la distribución normal estándar, donde:\n\n α = "+confianza+"\n\n1-α = " + tablaZ.redondeoDecimales((1-confianza),5)+"\n\n(1-α)/2 =" + tablaZ.redondeoDecimales(((1-confianza)/2),5)+ "\n\nEn éste caso el valor encontrado en tablas es: " + valTablas + "\n\nPosteriormente se sustituyen valores en la fórmula del tamaño mínimo de muestra, hay que tomar en cuenta que es necesario redondear el resultado para abajo. ");
                    b1.liminferior.setText("Tamaño mínimo de muestra: ");
                    b1.textView15.setText("{[("+desviacionEstandar+"*"+valTablas+")÷"+d0+"]^2}+1 = " + tamañoMinimoMuestra);
                    b1.verProcedimiento.setVisibility(View.VISIBLE);
                    b1.textView14.setVisibility(View.GONE);
                    b1.sustLimSup.setVisibility(View.GONE);
                    b1.imageView.setVisibility(View.GONE);
                    b1.paso2.setVisibility(View.GONE);
                    b1.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);

                }
            }
        });



        b1.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.procedimiento.setVisibility(View.GONE);
                b1.verProcedimiento.setVisibility(View.GONE);
                b1.cambia1.setTextColor(Color.BLACK);
                b1.cambia2.setTextColor(Color.BLACK);
                b1.cambia3.setTextColor(Color.BLACK);
                b1.cambia4.setTextColor(Color.BLACK);
                b1.cambia5.setTextColor(Color.BLACK);
                b1.cambia6.setTextColor(Color.BLACK);
                b1.cambia7.setTextColor(Color.BLACK);
                if(faltanDatos2()){
                    desplegarNotificacion("Por favor rellena todos los campos");
                    b1.cambia1.setTextColor(Color.RED);
                    b1.cambia2.setTextColor(Color.RED);
                    b1.cambia5.setTextColor(Color.RED);
                    b1.cambia7.setTextColor(Color.RED);
                }else{
                    b1.textView5.setVisibility(View.GONE);
                    b1.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    b1.paso2Phipodc.setVisibility(View.GONE);
                    b1.layoutDesaparece2.setVisibility(View.GONE);
                    b1.limitsuperior.setVisibility(View.GONE);
                    b1.layCalc1.setVisibility(View.GONE);
                    b1.layCalc2.setVisibility(View.GONE);
                    b1.tablaDatos.setVisibility(View.GONE);
                    b1.tvDatos.setVisibility(View.GONE);
                    b1.llz3.setVisibility(View.VISIBLE);
                    b1.llz5.setVisibility(View.GONE);
                    b1.llz6.setVisibility(View.GONE);
                    b1.llz7.setVisibility(View.GONE);
                    b1.tlcImageDato4.setImageResource(R.drawable.alfa);
                    b1.tlcimageDato1.setImageResource(R.drawable.dato_e);
                    b1.tlcImageDato2.setImageResource(R.drawable.x0);
                    b1.tlcImageDato3.setImageResource(R.drawable.dato_b);
                    b1.imageView2.setImageResource(R.drawable.tamminimomuestratclcaso1);
                    desviacionEstandar = Double.parseDouble(b1.desviacionEstandarPob.getText().toString());
                    miu = Double.parseDouble(b1.mediaPoblacional.getText().toString());
                    confianza =Double.parseDouble(b1.significancia.getText().toString());
                    double x0 = Double.parseDouble(b1.editTextCambia7.getText().toString());
                    valTablas = tablaZ.tablazetaAcumuladaPotencia(confianza);
                    int tamañoMinimoMuestra =(int) (Math.floor(Math.pow(((desviacionEstandar*valTablas)/(x0-miu)),2))) + 1;
                    b1.output.setText("El tamaño mínimo de muestra para cumplir las condiciones requeridas es de: " + tamañoMinimoMuestra);
                    b1.tlcDato1TV.setText("=" + desviacionEstandar);
                    b1.tlcDato4TV.setText("=" + confianza);
                    b1.tlcDato2TV.setText("=" + x0);
                    b1.tlcDato3TV.setText("=" + miu);
                    b1.paso1Phip.setText("Para poder calcular el tamaño mínimo de muestra, se hace necesario buscar el valor de ø(α) en las tablas acumuladas de la distribución normal estándar, donde:\n\n α = "+confianza+ "\n\nEn éste caso el valor encontrado en tablas es: " + valTablas + "\n\nPosteriormente se sustituyen valores en la fórmula del tamaño mínimo de muestra, hay que tomar en cuenta que es necesario redondear el resultado para abajo. ");
                    b1.liminferior.setText("Tamaño mínimo de muestra: ");
                    b1.textView15.setText("{[("+desviacionEstandar+"*"+valTablas+")÷("+x0+"-"+miu+")]^2}+1 = " + tamañoMinimoMuestra);
                    b1.verProcedimiento.setVisibility(View.VISIBLE);
                    b1.textView14.setVisibility(View.GONE);
                    b1.sustLimSup.setVisibility(View.GONE);
                    b1.imageView.setVisibility(View.GONE);
                    b1.paso2.setVisibility(View.GONE);
                    b1.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);

                }
            }
        });



        b1.verProcedimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               b1.procedimiento.setVisibility(View.VISIBLE);
            }
        });

        b1.cleanit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.mediaPoblacional.setText("");
                b1.desviacionEstandarPob.setText("");
                b1.tamMuestral.setText("");
                b1.promedioMuestral.setText("");
                b1.significancia.setText("");
                b1.miu1.setText("");
                b1.verProcedimiento.setVisibility(View.GONE);
                b1.output.setText("");
                b1.procedimiento.setVisibility(View.GONE);
                b1.imageButton3.setVisibility(View.VISIBLE);
                b1.imageButton2.setVisibility(View.VISIBLE);
                b1.procedimiento.setVisibility(View.GONE);
                b1.cambia1.setTextColor(Color.BLACK);
                b1.cambia2.setTextColor(Color.BLACK);
                b1.cambia3.setTextColor(Color.BLACK);
                b1.cambia4.setTextColor(Color.BLACK);
                b1.cambia5.setTextColor(Color.BLACK);
                b1.cambia6.setTextColor(Color.BLACK);
            }
        });

        b1.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.procedimiento.setVisibility(View.GONE);
                b1.verProcedimiento.setVisibility(View.GONE);
                b1.cambia1.setTextColor(Color.BLACK);
                b1.cambia2.setTextColor(Color.BLACK);
                b1.cambia3.setTextColor(Color.BLACK);
                b1.cambia4.setTextColor(Color.BLACK);
                b1.cambia5.setTextColor(Color.BLACK);
                b1.cambia6.setTextColor(Color.BLACK);
                b1.cambia7.setTextColor(Color.BLACK);
                if(faltanDatos()){
                    desplegarNotificacion("Por favor rellena todos los campos");

                    b1.cambia2.setTextColor(Color.RED);
                    b1.cambia5.setTextColor(Color.RED);
                    b1.cambia6.setTextColor(Color.RED);
                }else{
                    b1.textView5.setVisibility(View.GONE);
                    b1.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    b1.paso2Phipodc.setVisibility(View.GONE);
                    b1.layoutDesaparece2.setVisibility(View.GONE);
                    b1.limitsuperior.setVisibility(View.GONE);
                    b1.layCalc1.setVisibility(View.GONE);
                    b1.layCalc2.setVisibility(View.GONE);
                    b1.tablaDatos.setVisibility(View.GONE);
                    b1.tvDatos.setVisibility(View.GONE);
                    b1.llz3.setVisibility(View.GONE);
                    b1.llz5.setVisibility(View.GONE);
                    b1.llz6.setVisibility(View.GONE);
                    b1.llz7.setVisibility(View.GONE);
                    b1.tlcImageDato4.setImageResource(R.drawable.alfamedios);
                    b1.tlcimageDato1.setImageResource(R.drawable.dato_e);
                    b1.tlcImageDato2.setImageResource(R.drawable.d0);
                    b1.imageView2.setImageResource(R.drawable.tamminimomuestratclcaso3);
                    desviacionEstandar = Double.parseDouble(b1.desviacionEstandarPob.getText().toString());
                    confianza =Double.parseDouble(b1.significancia.getText().toString());
                    d0 = Double.parseDouble(b1.miu1.getText().toString());
                    valTablas = tablaZ.tablazetaAcumuladaPotencia(tablaZ.redondeoDecimales((confianza/2),4));
                    int tamañoMinimoMuestra =(int) (Math.floor(Math.pow(((desviacionEstandar*valTablas)/d0),2))) + 1;
                    b1.output.setText("El tamaño mínimo de muestra para cumplir las condiciones requeridas es de: " + tamañoMinimoMuestra);
                    b1.tlcDato1TV.setText("=" + desviacionEstandar);
                    b1.tlcDato4TV.setText("=" + tablaZ.redondeoDecimales((confianza/2),5));
                    b1.tlcDato2TV.setText("=" + d0);
                    b1.paso1Phip.setText("Para poder calcular el tamaño mínimo de muestra, se hace necesario buscar el valor de ø(α/2) en las tablas acumuladas de la distribución normal estándar, donde:\n\n α = "+confianza+"\n\nα/2 =" + tablaZ.redondeoDecimales((confianza/2),5)+ "\n\nEn éste caso el valor encontrado en tablas es:\nø(" + tablaZ.redondeoDecimales((confianza/2),5)+") = "+ valTablas + "\n\nPosteriormente se sustituyen valores en la fórmula del tamaño mínimo de muestra, hay que tomar en cuenta que es necesario redondear el resultado para abajo. ");
                    b1.liminferior.setText("Tamaño mínimo de muestra: ");
                    b1.textView15.setText("{[("+desviacionEstandar+"*"+valTablas+")÷"+d0+"]^2}+1 = " + tamañoMinimoMuestra);
                    b1.verProcedimiento.setVisibility(View.VISIBLE);
                    b1.textView14.setVisibility(View.GONE);
                    b1.sustLimSup.setVisibility(View.GONE);
                    b1.imageView.setVisibility(View.GONE);
                    b1.paso2.setVisibility(View.GONE);
                    b1.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    public boolean faltaDato(){
        if(b1.mediaPoblacional.getText().toString().isEmpty() || b1.desviacionEstandarPob.getText().toString().isEmpty()||b1.tamMuestral.getText().toString().isEmpty()||b1.promedioMuestral.getText().toString().isEmpty()){
            return true;
        }else return false;
    }

    public boolean faltanDatos(){
        if(b1.desviacionEstandarPob.getText().toString().isEmpty()||b1.significancia.getText().toString().isEmpty()||b1.miu1.getText().toString().isEmpty()){
            return true;
        }else return false;
    }

    public boolean faltanDatos2(){
        if(b1.mediaPoblacional.getText().toString().isEmpty()||b1.desviacionEstandarPob.getText().toString().isEmpty()||b1.significancia.getText().toString().isEmpty()||b1.editTextCambia7.getText().toString().isEmpty()){
            return true;
        }else return false;
    }


    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }
}