package com.example.estadistica;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class PruebaHipDEDesconocida extends AppCompatActivity {

    private PHIPDEDESC teorema = new PHIPDEDESC();
    private PruebaHipotesisDVconocida teorema1 = new PruebaHipotesisDVconocida();
    private double significancia,valTablas,mediaPoblacional,alfamedios,desviacionEstandarPob,promedioMuestral,mediaPoblacional1,liminf,limsup;
    private int tamMuestra1;
    String conclusion;
    private ActivityPruebaHipBinding lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        lienzo.cambia2.setText("desviacion estandar muestral");
        lienzo.imageCambia.setImageResource(R.drawable.desviacionestandar_muestralchi);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.potenciaPrueba.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE);

        lienzo.imageButton2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
                lienzo.LinearLayoutDato7Opcional.setVisibility(View.GONE);
                if(faltaDatoPruebaHipotesis()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa todos los datos requeridos");
                }else{
                    lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                    lienzo.calcularIntervaloConfianza.setText("Calcular potencia de prueba");
                    mediaPoblacional = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    desviacionEstandarPob = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());


                    if((tamMuestra1-1)<=100){
                        teorema = new PHIPDEDESC(desviacionEstandarPob,tamMuestra1,mediaPoblacional,promedioMuestral,(float)significancia);
                        limsup = teorema.Caso1();
                        conclusion = teorema.decision;
                        String decision = "Con un intervalo de la región de aceptación comprendido desde -∞  hasta " + limsup + " podemos concluir que:\n\n" + conclusion ;
                        SpannableString ss  = new SpannableString(decision);
                        lienzo.output.setText(decision);
                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("Podemos entender a la región de aceptación, como el intervalo de valores tales que si la prueba estadística cae dentro de este rango, decidimos aceptar la hipotesis nula")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la región de aceptación?");
                                glosario1.show();
                            }
                        };

                        ss.setSpan(clickableSpan,20,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.output.setText(ss);
                        lienzo.output.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.teoremapruebashipotesisdedesconocida1);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val2.setText("="+mediaPoblacional);
                        lienzo.val3.setText("="+significancia);
                        lienzo.desviation.setImageResource(R.drawable.varianza_muestralchi);
                        lienzo.val4.setText("="+desviacionEstandarPob);
                        lienzo.val5.setText("="+tamMuestra1);
                        lienzo.dtoOpcional.setVisibility(View.GONE);

                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                        lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ ≤ µ0\n\nContra:\n\nHipótesis alterna: µ>µ0\n\nSe hace necesario calcular el límite superior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema:");
                        String aux = lienzo.paso1Phip.getText().toString();
                        SpannableString ss2  = new SpannableString(aux);


                        ClickableSpan clickableSpan2 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la hipótesis nula?");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };


                        ClickableSpan clickableSpan1 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region1.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario2 = region1.create();
                                glosario2.setTitle("¿Qué es la hipótesis alterna");
                                LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista1 = imagen_alert.inflate(R.layout.activity_p_r_u_e_b_a_h_i_p_o_t_e_s_i_s_a_l_t_e_r_n_a_t_o_a_s_t,null);
                                glosario2.setView(vista1);
                                glosario2.show();
                            }
                        };
                        ss2.setSpan(clickableSpan2,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso1Phip.setText(ss2);
                        lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.imageView2.setImageResource(R.drawable.limsupteoremapruebashipotesisdedesconocida1);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                        lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.desviacionestandar_muestralchi);
                        lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                        lienzo.tlcDato3TV.setText("="+tamMuestra1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.tealfa);
                        lienzo.tlcDato4TV.setText("="+teorema.getValTablaT());
                        lienzo.llz5.setVisibility(View.GONE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.layCalc1.setVisibility(View.GONE);
                        lienzo.layCalc2.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.VISIBLE);
                        lienzo.textView8.setText("Al observar la fórmula podemos percatarnos de que es necesario buscar el valor de t(α) en las tablas de la distribución t-student, donde:\n\nα = " + significancia+ "\n\nv= n - 1 = " + tamMuestra1 +" - 1 = " + teorema.gradosLibertad + " grados de libertad. \n\n En éste caso el valor encontrado en las tablas es de:\n t(α) = t("+significancia +") =" + teorema.getValTablaT() + "\n\nUna vez obtenido el valor de t(α) nos resta sustituir los valores correspondientes:" );
                        lienzo.layoutDesaparece2.setVisibility(View.GONE);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        valTablas = teorema.getValTablaT();
                        lienzo.liminferior.setText("Para el límite superior de la región de aceptación:");
                        lienzo.textView15.setText(mediaPoblacional+ " + [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+valTablas+"] = " + limsup);
                        lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite superior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                        lienzo.reglaDecision.setImageResource(R.drawable.teoremapruebashipotesisdedesconocida1);
                        if(conclusion.contains("rechaza la hipotesis nula")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  +" es mayor" + " que el límite superior del intervalo de aceptación:\n\n"+promedioMuestral+">"+limsup+"\n\nPor lo tanto: \n\n"  + conclusion );
                        }else if(conclusion.contains("no existe evidencia suficiente para rechazar H0")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  +" es menor o igual" + " que el límite superior del intervalo de aceptación:\n\n"+promedioMuestral+"≤"+limsup+"\n\nPor lo tanto: \n\n"  + conclusion );
                        }

                        lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                        String aux2 = lienzo.paso2Phipodc.getText().toString();
                        SpannableString ss3 = new SpannableString(aux2);


                        ClickableSpan clickableSpan3 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("Región de aceptación");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casob,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };
                        ss3.setSpan(clickableSpan3,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso2Phipodc.setText(ss3);
                        lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
                        lienzo.cambia7.setText("Nueva media poblacional");
                        lienzo.imageCambia7.setVisibility(View.GONE);
                        lienzo.cambia7.setTextColor(Color.RED);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(lienzo.editTextCambia7.getText().toString().isEmpty()){
                                    desplegarNotificacion("Por favor ingresa una nueva media poblacional");
                                }else{
                                    double nuevaMiu = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                                    double potenciaPrueba = teorema.potPrueba(nuevaMiu,"caso1");
                                    lienzo.output.setText("La potencia de la prueba calculada es:\n\n" + potenciaPrueba);
                                    lienzo.procedimiento.setVisibility(View.GONE);
                                    lienzo.layoutPotencia.setVisibility(View.VISIBLE);
                                    lienzo.caso1.setText("La potencia de la prueba cuantifica la probabilidad de rechazar la hipótesis nula de manera acertada. Es decir, rechazar la hipótesis nula cuando esta es falsa\n\nPara  calcular la potencia de la prueba utilizamos la región de rechazo\n\nSi observamos la gráfica de campana para las regiones de la prueba deseada podemos observar que el intervalo de la región de rechazo está comprendido desde " + limsup + " hasta ∞.\n\nUna vez comprendido el punto anterior, es necesario calcular el valor de la estadística de prueba T que posteriormente buscaremos en las tablas acumuladas de la distribución t-student. Para ello nos podemos apoyar de la siguiente fórmula:");
                                    lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                                    lienzo.imagenPotencia.setImageResource(R.drawable.estadisticot);
                                    lienzo.textoVal1Estadisticoz.setText("=" + limsup);
                                    lienzo.textoVal2Estadisticoz.setText("=" + nuevaMiu);
                                    lienzo.val3EstadisticoZ.setImageResource(R.drawable.desviacionestandar_muestralchi);
                                    lienzo.textoVal3Estadisticoz.setText("=" + desviacionEstandarPob);
                                    lienzo.textoVal4Estadisticoz.setText("=" + tamMuestra1);
                                    lienzo.paso2PotPrueba.setText("Una vez calculado el valor de la estadística de prueba Z, cómo anteriormente se mencionó, es necesario buscar el valor calculado \n\nT = "+teorema.valEstadisticoT+"\n\nEn las tablas de la distribución acumulada de la normal estándar, donde en este caso el valor encontrado en tablas es: " + teorema.redondeoDecimales((1-potenciaPrueba),5) + "\n\nComo se dijo al inicio de la explicación, es necesario tomar en cuenta que para el cálculo de la potencia de la prueba se requiere utilizar la región de rechazo. En éste caso la región de rechazo está comprendida desde " + limsup + " hasta ∞, por lo tanto para realizar el cálculo para la probabilidad de que se rechace de forma acertada la hipótesis nula se define por:\n\nP(T>" + teorema.valEstadisticoT + ") = " + potenciaPrueba);
                                    lienzo.layoutEstadistico2.setVisibility(View.GONE);
                                    lienzo.resultadoPotPrueba.setVisibility(View.GONE);
                                    lienzo.textView60.setVisibility(View.GONE);
                                }
                            }
                        });



                    }else{
                        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                        lienzo.calcularIntervaloConfianza.setText("Calcular potencia de prueba");
                        mediaPoblacional = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                        desviacionEstandarPob = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                        promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                        tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                        significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                        conclusion = teorema1.Aproxcasob((float)significancia,tamMuestra1,desviacionEstandarPob,promedioMuestral,mediaPoblacional);
                        limsup = teorema1.getLim_sup();
                        switch (teorema1.decision){
                            case "es igual":
                            case " está dentro del intervalo de no rechazo ":
                            case "es mayor":
                                String decision = "Con un intervalo de la región de aceptación comprendido desde -∞  hasta " + limsup + " podemos concluir que:\n\n" + conclusion ;
                                SpannableString ss  = new SpannableString(decision);
                                lienzo.output.setText(decision);
                                ClickableSpan clickableSpan = new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View view) {
                                        AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                        region.setMessage("Podemos entender a la región de aceptación, como el intervalo de valores tales que si la prueba estadística cae dentro de este rango, decidimos aceptar la hipotesis nula")
                                                .setCancelable(false)
                                                .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                });
                                        AlertDialog glosario1 = region.create();
                                        glosario1.setTitle("¿Qué es la región de aceptación?");
                                        glosario1.show();
                                    }
                                };

                                ss.setSpan(clickableSpan,20,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                lienzo.output.setText(ss);
                                lienzo.output.setMovementMethod(LinkMovementMethod.getInstance());
                                break;
                            default: break;
                        }

                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.teoremaaproxpruebaship1);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val2.setText("="+mediaPoblacional);
                        lienzo.val3.setText("="+significancia);
                        lienzo.val4.setText("="+desviacionEstandarPob);
                        lienzo.val5.setText("="+tamMuestra1);
                        lienzo.dtoOpcional.setVisibility(View.GONE);

                        lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ ≤ µ0\n\nContra:\n\nHipótesis alterna: µ>µ0\n\nSe hace necesario calcular el límite superior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema1:\n\nNotece que estamos usando una aproximación al resultado por la distribución normal estándar, ya que el tamaño de muestra es muy grande como para buscar valores en las tablas de la distribución t-student");
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                        String aux = lienzo.paso1Phip.getText().toString();
                        SpannableString ss  = new SpannableString(aux);


                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la hipótesis nula?");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };


                        ClickableSpan clickableSpan1 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region1.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario2 = region1.create();
                                glosario2.setTitle("¿Qué es la hipótesis alterna");
                                LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista1 = imagen_alert.inflate(R.layout.activity_p_r_u_e_b_a_h_i_p_o_t_e_s_i_s_a_l_t_e_r_n_a_t_o_a_s_t,null);
                                glosario2.setView(vista1);
                                glosario2.show();
                            }
                        };
                        ss.setSpan(clickableSpan,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso1Phip.setText(ss);
                        lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.imageView2.setImageResource(R.drawable.limsupteoremaaproxpruebaship1);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                        lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                        lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                        lienzo.tlcDato3TV.setText("="+tamMuestra1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.gammaenalfa);
                        lienzo.tlcDato4TV.setText("="+teorema1.getValTablas());
                        lienzo.llz5.setVisibility(View.GONE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.layCalc1.setVisibility(View.GONE);
                        lienzo.layCalc2.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.VISIBLE);
                        lienzo.textView8.setText("Al observar la fórmula podemos percatarnos de que es necesario realizar una aproximación del límite superior por la distribución normal estándar. Por lo cual es necesario buscar el valor de ø(1-α) en las tablas de la distribución normál estándar, donde:\n\nα = " + significancia+ "\n\n1-α = " + teorema1.tab.redondeoDecimales((1-significancia),5) + "\n\nEn éste caso el valor encontrado en las tablas es de:\n ø(α) = ø("+significancia +") =" + teorema1.getValTablas() + "\n\nUna vez obtenido el valor de ø(α) nos resta sustituir los valores correspondientes:" );
                        lienzo.layoutDesaparece2.setVisibility(View.GONE);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Para el límite superior de la región de aceptación:");
                        lienzo.textView15.setText(mediaPoblacional+ " + [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema1.getValTablas()+"] = " + limsup);
                        lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite superior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                        lienzo.reglaDecision.setImageResource(R.drawable.teoremaaproxpruebaship1);
                        switch (teorema1.decision){
                            case "es igual":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + " que el límite superior del intervalo de aceptación:\n\n"+promedioMuestral+"="+limsup+"\n\nPor lo tanto: \n\n"  + conclusion );
                                break;
                            case " está dentro del intervalo de no rechazo ":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + ":\n\n"+promedioMuestral+"<"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                                break;
                            case "es mayor":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + " que el límite superior del intervalo de aceptación\n\n"+promedioMuestral+">"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                                break;

                            default: break;
                        }

                        lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                        String aux2 = lienzo.paso2Phipodc.getText().toString();
                        SpannableString ss2 = new SpannableString(aux2);


                        ClickableSpan clickableSpan2 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("Región de aceptación");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casob,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };
                        ss2.setSpan(clickableSpan2,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso2Phipodc.setText(ss2);
                        lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
                        lienzo.cambia7.setText("Nueva media poblacional");
                        lienzo.imageCambia7.setVisibility(View.GONE);
                        lienzo.cambia7.setTextColor(Color.RED);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(lienzo.editTextCambia7.getText().toString().isEmpty()){
                                    desplegarNotificacion("Por favor ingresa una nueva media poblacional");
                                }else{
                                    double nuevaMiu = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                                    double potenciaPrueba = teorema1.potenciaPrueba(limsup,nuevaMiu,desviacionEstandarPob,tamMuestra1,"caso2");
                                    lienzo.output.setText("La potencia de la prueba calculada es:\n\n" + potenciaPrueba);
                                    lienzo.procedimiento.setVisibility(View.GONE);
                                    lienzo.layoutPotencia.setVisibility(View.VISIBLE);
                                    lienzo.caso1.setText("La potencia de la prueba cuantifica la probabilidad de rechazar la hipótesis nula de manera acertada. Es decir, rechazar la hipótesis nula cuando esta es falsa\n\nPara  calcular la potencia de la prueba utilizamos la región de rechazo\n\nSi observamos la gráfica de campana para las regiones de la prueba deseada podemos observar que el intervalo de la región de rechazo está comprendido desde " + limsup + " hasta ∞.\n\nUna vez comprendido el punto anterior, es necesario calcular el valor de la estadística de prueba Z que posteriormente buscaremos en las tablas acumuladas de la distribución normal. Para ello nos podemos apoyar de la siguiente fórmula:");
                                    lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                                    lienzo.imagenPotencia.setImageResource(R.drawable.imagen_estadistico_zeta);
                                    lienzo.textoVal1Estadisticoz.setText("=" + limsup);
                                    lienzo.textoVal2Estadisticoz.setText("=" + nuevaMiu);
                                    lienzo.textoVal3Estadisticoz.setText("=" + desviacionEstandarPob);
                                    lienzo.textoVal4Estadisticoz.setText("=" + tamMuestra1);
                                    lienzo.paso2PotPrueba.setText("Una vez calculado el valor de la estadística de prueba Z, cómo anteriormente se mencionó, es necesario buscar el valor calculado \n\nZ = "+teorema1.estadisticoZeta+"\n\nEn las tablas de la distribución acumulada de la normal estándar, donde en este caso el valor encontrado en tablas es: " + teorema1.tab.redondeoDecimales((1-potenciaPrueba),5) + "\n\nComo se dijo al inicio de la explicación, es necesario tomar en cuenta que para el cálculo de la potencia de la prueba se requiere utilizar la región de rechazo. En éste caso la región de rechazo está comprendida desde " + limsup + " hasta ∞, por lo tanto para realizar el cálculo para la probabilidad de que se rechace de forma acertada la hipótesis nula se define por:\n\nP(Z>" + teorema1.estadisticoZeta + ") = 1 - P(Z≤" + teorema1.estadisticoZeta + ") = " + potenciaPrueba);
                                    lienzo.layoutEstadistico2.setVisibility(View.GONE);
                                    lienzo.resultadoPotPrueba.setVisibility(View.GONE);
                                    lienzo.textView60.setVisibility(View.GONE);
                                }
                            }
                        });

                    }
                }
            }
        });
        //caso 2 *******************************************************************************************************************************

        lienzo.imageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
                lienzo.LinearLayoutDato7Opcional.setVisibility(View.GONE);
                if(faltaDatoPruebaHipotesis()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa todos los datos requeridos");
                }else{
                    lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                    lienzo.calcularIntervaloConfianza.setText("Calcular potencia de prueba");
                    mediaPoblacional = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    desviacionEstandarPob = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                    if(tamMuestra1-1<=100){
                        teorema = new PHIPDEDESC(desviacionEstandarPob,tamMuestra1,mediaPoblacional,promedioMuestral,(float) teorema.redondeoDecimales((significancia/2),5));
                        String intervalo = teorema.caso2();
                        conclusion = teorema.decision;
                        liminf = teorema.getLimInf();
                        limsup = teorema.getLimSup();
                        String decision = "Con un intervalo de la región de aceptación " + intervalo + " podemos concluir que:\n\n" + conclusion ;
                        SpannableString ss  = new SpannableString(decision);
                        lienzo.output.setText(decision);
                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("Podemos entender a la región de aceptación, como el intervalo de valores tales que si la prueba estadística cae dentro de este rango, decidimos aceptar la hipotesis nula")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la región de aceptación?");
                                glosario1.show();
                            }
                        };

                        ss.setSpan(clickableSpan,20,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.output.setText(ss);
                        lienzo.output.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.teoremapruebashipotesisdedesconocida2);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val2.setText("="+mediaPoblacional);
                        lienzo.val3.setText("="+significancia);
                        lienzo.val4.setText("="+desviacionEstandarPob);
                        lienzo.desviation.setImageResource(R.drawable.desviacionestandar_muestralchi);
                        lienzo.val5.setText("="+tamMuestra1);
                        lienzo.dtoOpcional.setVisibility(View.GONE);

                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                        lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ = µ0\n\nContra:\n\nHipótesis alterna: µ ≠ µ0\n\nSe hace necesario calcular el intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del teorema presentado anteriormente.\n\nSi observamos el teorema, nos damos cuenta de que es necesario buscar el valor de t(α/2) en las tablas de la distribucion t-student. Donde:\n\nα=" + significancia + "\n\nα/2 = " +teorema.redondeoDecimales((significancia/2),5)+"\n\nv = n - 1 = "+ tamMuestra1 +" -1 = "+ teorema.gradosLibertad +" grados de libertad\n\nBuscando el valor en tablas encontramos que:\nt(α/2)=t("+teorema.redondeoDecimales((significancia/2),5)+")="+teorema.getValTablaT());
                        lienzo.layCalc1.setVisibility(View.VISIBLE);
                        lienzo.layCalc2.setVisibility(View.VISIBLE);
                        String aux = lienzo.paso1Phip.getText().toString();
                        SpannableString ss2  = new SpannableString(aux);


                        ClickableSpan clickableSpan2 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la hipótesis nula?");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.activityhipotesisnula2,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };


                        ClickableSpan clickableSpan1 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region1.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario2 = region1.create();
                                glosario2.setTitle("¿Qué es la hipótesis alterna");
                                LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna2,null);
                                glosario2.setView(vista1);
                                glosario2.show();
                            }
                        };
                        ss2.setSpan(clickableSpan2,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso1Phip.setText(ss2);
                        lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.imageView2.setImageResource(R.drawable.limsupteoremapruebashipotesis1);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                        lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                        lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                        lienzo.tlcDato3TV.setText("="+tamMuestra1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.talfamedios);
                        lienzo.tlcDato4TV.setText("="+teorema.getValTablaT());
                        lienzo.llz5.setVisibility(View.GONE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.VISIBLE);
                        lienzo.textView8.setText("Sutituyendo los valores correspondientes en el càlculo del intervalo para la regiòn de aceptacion, cuyo càlculo se consigue sustituyendo los valores correspondientes: ");
                        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                        lienzo.calculo1.setText("Para el lìmite inferior:");
                        lienzo.calcOp1.setImageResource(R.drawable.liminfteoremapruebashipotesisdedesconocida2);
                        lienzo.calculo2.setText("Para el lìmite superior:");
                        lienzo.calcOp2.setImageResource(R.drawable.limsupteoremapruebashipotesisdedesconocida2);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Para el límite inferior de la región de aceptación:");
                        lienzo.textView15.setText(mediaPoblacional+ "-[("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema.getValTablaT()+"] = " + liminf);
                        lienzo.limitsuperior.setText("Para el lìmite superior de la regiòn de aceptaciòn:");
                        lienzo.limitsuperior.setVisibility(View.VISIBLE);
                        lienzo.zeta2.setText(mediaPoblacional+ "+ [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema.getValTablaT()+"] = " + limsup);
                        lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el intervalo de la región de aceptación:\n["+liminf+","+limsup+"]\n\n Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                        lienzo.reglaDecision.setImageResource(R.drawable.teoremapruebashipotesisdedesconocida2);
                        if (teorema.decision.contains("se rechaza la hipotesis nula")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral se encuentra fuera del intervalo de la regiòn de no rechazo :\n\n" + promedioMuestral + "<" + liminf + " ò " + promedioMuestral + ">" +  limsup + "\n\nPor lo tanto: \n\n" + conclusion);
                        }else if(teorema.decision.contains("no existe evidencia suficiente para rechazar H0")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral se encuentra dentro del intervalo de la regiòn de no rechazo :\n\n" +liminf + "≤" + promedioMuestral + "≤" + limsup + "\n\nPor lo tanto: \n\n" + conclusion);
                        }

                        lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                        String aux2 = lienzo.paso2Phipodc.getText().toString();
                        SpannableString ss3 = new SpannableString(aux2);


                        ClickableSpan clickableSpan3 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("Región de aceptación");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casoc,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };
                        ss3.setSpan(clickableSpan3,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso2Phipodc.setText(ss3);
                        lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
                        lienzo.cambia7.setText("Nueva media poblacional");
                        lienzo.imageCambia7.setVisibility(View.GONE);
                        lienzo.cambia7.setTextColor(Color.RED);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(lienzo.editTextCambia7.getText().toString().isEmpty()){
                                    desplegarNotificacion("Por favor ingresa una nueva media poblacional");
                                }else{
                                    double nuevaMiu = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                                    double potenciaPrueba = teorema.potPrueba(nuevaMiu,"caso2");
                                    lienzo.output.setText("La potencia de la prueba calculada es:\n\n" + potenciaPrueba);
                                    lienzo.procedimiento.setVisibility(View.GONE);
                                    lienzo.layoutPotencia.setVisibility(View.VISIBLE);
                                    lienzo.caso1.setText("La potencia de la prueba cuantifica la probabilidad de rechazar la hipótesis nula de manera acertada. Es decir, rechazar la hipótesis nula cuando esta es falsa\n\nPara  calcular la potencia de la prueba utilizamos la región de rechazo\n\nSi observamos la gráfica de campana para las regiones de la prueba deseada podemos observar que el intervalo de la región de rechazo está 'partido' en dos partes, la parte inferior  comprendida desde -∞ hasta" + liminf + " y la parte superior comprendida desde " + limsup + " hasta ∞.\n\nUna vez comprendido el punto anterior, es necesario calcular el valor de la estadística de prueba Z que posteriormente buscaremos en las tablas acumuladas de la distribución t-student. Para ello nos podemos apoyar de las siguientes fórmulas:");
                                    lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                                    lienzo.imagenPotencia.setImageResource(R.drawable.estadisticot);
                                    lienzo.val1EstadisticoZ.setImageResource(R.drawable.imagen_limiteinf_region_no_rechazo);
                                    lienzo.textoVal1Estadisticoz.setText("=" + liminf);
                                    lienzo.textoVal2Estadisticoz.setText("=" + nuevaMiu);
                                    lienzo.val3EstadisticoZ.setImageResource(R.drawable.desviacionestandar_muestralchi);
                                    lienzo.textoVal3Estadisticoz.setText("=" + desviacionEstandarPob);
                                    lienzo.textoVal4Estadisticoz.setText("=" + tamMuestra1);
                                    lienzo.layoutEstadistico2.setVisibility(View.VISIBLE);
                                    lienzo.imagenPotencia2.setImageResource(R.drawable.estadisticot);
                                    lienzo.imagenDato1.setImageResource(R.drawable.imagen_limitesup_region_no_rechazo);
                                    lienzo.DatoPotencia1.setText("=" + limsup);
                                    lienzo.DatoPotencia2.setText("=" + nuevaMiu);
                                    lienzo.imagenDato3.setImageResource(R.drawable.desviacionestandar_muestralchi);
                                    lienzo.DatoPotencia3.setText("=" + desviacionEstandarPob);
                                    lienzo.DatoPotencia4.setText("=" + tamMuestra1);
                                    lienzo.resultadoPotPrueba.setText("Una vez calculado el valor de la estadística de prueba T para ambos límites del intervalo para la región de rechazo, cómo anteriormente se mencionó, es necesario buscar los valores calculados \n\nT para el límite inferior: = "+teorema.valEstadisticoT+"\n\nZ para el límite superior = " + teorema.valEstadisticoTs + "\n\nEn las tablas de la distribución acumulada de la distribución t-student, donde en este caso los valores encontrados en tablas son: \n\n" + teorema.valtTablas1 + "\ny\n"+teorema.valTablas2+"\n\nRespectivamente, como se dijo al inicio de la explicación, es necesario tomar en cuenta que para el cálculo de la potencia de la prueba se requiere utilizar la región de rechazo. En éste caso la región de rechazo está comprendida desde -∞ hasta " + liminf + " y desde " + limsup + " hasta ∞, por lo tanto para realizar el cálculo para la probabilidad de que se rechace de forma acertada la hipótesis nula se define por:\n\nP(T<" + teorema.valEstadisticoT + ") + P(T>" + teorema.valEstadisticoTs + ") = " + potenciaPrueba);
                                    lienzo.paso2PotPrueba.setVisibility(View.VISIBLE);
                                    lienzo.paso2PotPrueba.setText("Para el límite superior:");
                                    lienzo.textView60.setVisibility(View.VISIBLE);
                                    lienzo.textView60.setText("Para el límite inferior:");
                                }
                            }
                        });


                    }else{
                        conclusion = teorema1.Aproxcasoc((float)significancia,tamMuestra1,desviacionEstandarPob,promedioMuestral,mediaPoblacional);
                        liminf = teorema1.getLim_inf();
                        limsup = teorema1.getLim_sup();
                                String decision1 = "Con un intervalo de la región de aceptación comprendido desde " + liminf+"  hasta " + limsup + " podemos concluir que:\n\n" + conclusion ;
                                SpannableString ss  = new SpannableString(decision1);
                                lienzo.output.setText(decision1);
                                ClickableSpan clickableSpan = new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View view) {
                                        AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                        region.setMessage("Podemos entender a la región de aceptación, como el intervalo de valores tales que si la prueba estadística cae dentro de este rango, decidimos aceptar la hipotesis nula")
                                                .setCancelable(false)
                                                .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                });
                                        AlertDialog glosario1 = region.create();
                                        glosario1.setTitle("¿Qué es la región de aceptación?");
                                        glosario1.show();
                                    }
                                };

                                ss.setSpan(clickableSpan,20,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                lienzo.output.setText(ss);
                                lienzo.output.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.teoremaaproxpruebahip2);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val2.setText("="+mediaPoblacional);
                        lienzo.val3.setText("="+significancia);
                        lienzo.val4.setText("="+desviacionEstandarPob);
                        lienzo.val5.setText("="+tamMuestra1);
                        lienzo.dtoOpcional.setVisibility(View.GONE);

                        lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ = µ0\n\nContra:\n\nHipótesis alterna: µ ≠ µ0\n\nSe hace necesario calcular el intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del teorema presentado anteriormente.\n\nSi observamos el teorema nos percatamos de que estamos realizando una aproximación al resultado por la distribución normal estándar, ya que el tamaño de muestra es demasiado grande y nos hace imposible buscar valores en las tablas de la distribución t-student, por lo cual es necesario buscar los valores de ø(α/2)  y  ø[1-(α/2)]  en las tablas acumuladas de la distribucion normal. Donde:\n\nα=" + significancia + "\n\nα/2 = " +teorema1.tab.redondeoDecimales((significancia/2),5)+"\n\n1-(α/2) = " + teorema1.tab.redondeoDecimales((1-(significancia/2)),5) + "\n\nBuscando el valor en tablas encontramos que:\n\nø(α/2)=ø("+teorema1.tab.redondeoDecimales((significancia/2),5)+")="+teorema1.valTablas1 + "\n\nø[1-(α/2)]=ø("+teorema1.tab.redondeoDecimales((1-(significancia/2)),5)+")="+teorema1.valtTablas2);
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                        lienzo.layCalc1.setVisibility(View.VISIBLE);
                        lienzo.layCalc2.setVisibility(View.VISIBLE);
                        String aux = lienzo.paso1Phip.getText().toString();
                        SpannableString ss5  = new SpannableString(aux);


                        ClickableSpan clickableSpan5 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la hipótesis nula?");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.activityhipotesisnula2,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };


                        ClickableSpan clickableSpan1 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region1.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario2 = region1.create();
                                glosario2.setTitle("¿Qué es la hipótesis alterna");
                                LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna2,null);
                                glosario2.setView(vista1);
                                glosario2.show();
                            }
                        };
                        ss5.setSpan(clickableSpan5,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss5.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso1Phip.setText(ss5);
                        lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.imageView2.setImageResource(R.drawable.limsupteoremaaproxpruebaship2);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                        lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                        lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                        lienzo.tlcDato3TV.setText("="+tamMuestra1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.zetaalfa);
                        lienzo.tlcDato4TV.setText("="+teorema1.valTablas1);
                        lienzo.llz5.setVisibility(View.GONE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.VISIBLE);
                        lienzo.textView8.setText("Sutituyendo los valores correspondientes en el càlculo del intervalo para la regiòn de aceptacion, cuyo càlculo se consigue sustituyendo los valores correspondientes: ");
                        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                        lienzo.calculo1.setText("Para el lìmite inferior:");
                        lienzo.calcOp1.setImageResource(R.drawable.liminfteoremaaproxpruebaship2);
                        lienzo.calculo2.setText("Para el lìmite superior:");
                        lienzo.calcOp2.setImageResource(R.drawable.limsupteoremaaproxpruebaship2);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Para el límite inferior de la región de aceptación:");
                        lienzo.textView15.setText(mediaPoblacional+ "+ [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema1.valTablas1+"] = " + liminf);
                        lienzo.limitsuperior.setText("Para el lìmite superior de la regiòn de aceptaciòn:");
                        lienzo.limitsuperior.setVisibility(View.VISIBLE);
                        lienzo.zeta2.setText(mediaPoblacional+ "+ [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema1.valtTablas2+"] = " + limsup);
                        lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el intervalo de la región de aceptación:\n["+liminf+","+limsup+"]\n\n Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                        lienzo.reglaDecision.setImageResource(R.drawable.teoremaaproxpruebahip2);
                        switch (teorema1.decision){
                            case "es menor que el límite inferior del intervalo de aceptación":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + " :\n\n"+promedioMuestral+"<"+liminf+"\n\nPor lo tanto: \n\n"  + conclusion );
                                break;
                            case " es mayor que el límite superior del intervalo de aceptación":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + ":\n\n"+promedioMuestral+">"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                                break;
                            case "está dentro del intervalo aceptación":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + ":\n\n"+liminf+"≤"+promedioMuestral+"≤"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                                break;

                            default: break;
                        }

                        lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                        String aux2 = lienzo.paso2Phipodc.getText().toString();
                        SpannableString ss2 = new SpannableString(aux2);


                        ClickableSpan clickableSpan2 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("Región de aceptación");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casoc,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };
                        ss2.setSpan(clickableSpan2,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso2Phipodc.setText(ss2);
                        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
                        lienzo.cambia7.setText("Nueva media poblacional");
                        lienzo.imageCambia7.setVisibility(View.GONE);
                        lienzo.cambia7.setTextColor(Color.RED);
                        lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(lienzo.editTextCambia7.getText().toString().isEmpty()){
                                    desplegarNotificacion("Por favor ingresa una nueva media poblacional");
                                }else{
                                    double nuevaMiu = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                                    double potenciaPrueba = teorema1.potenciaPrueba(liminf,limsup,desviacionEstandarPob,nuevaMiu,tamMuestra1);
                                    lienzo.output.setText("La potencia de la prueba calculada es:\n\n" + potenciaPrueba);
                                    lienzo.procedimiento.setVisibility(View.GONE);
                                    lienzo.layoutPotencia.setVisibility(View.VISIBLE);
                                    lienzo.caso1.setText("La potencia de la prueba cuantifica la probabilidad de rechazar la hipótesis nula de manera acertada. Es decir, rechazar la hipótesis nula cuando esta es falsa\n\nPara  calcular la potencia de la prueba utilizamos la región de rechazo\n\nSi observamos la gráfica de campana para las regiones de la prueba deseada podemos observar que el intervalo de la región de rechazo está \"partido\" en dos, la región inferior que está comprendida desde  -∞ hasta  " + liminf + "  y la región superior, comprendida desde " + limsup + " hasta ∞.\n\nUna vez comprendido el punto anterior, es necesario calcular el valor del estadístico de prueba Z para ambos límites, que posteriormente buscaremos en las tablas acumuladas de la distribución normal. Para ello nos podemos apoyar de las siguientes fórmulas:");
                                    lienzo.textView60.setText("Estadístico Z para el límite inferior:");
                                    lienzo.textView60.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                                    lienzo.imagenPotencia.setImageResource(R.drawable.imagen_estadistico_zeta);
                                    lienzo.textoVal1Estadisticoz.setText("=" + liminf);
                                    lienzo.val1EstadisticoZ.setImageResource(R.drawable.imagen_limiteinf_region_no_rechazo);
                                    lienzo.textoVal2Estadisticoz.setText("=" + nuevaMiu);
                                    lienzo.textoVal3Estadisticoz.setText("=" + desviacionEstandarPob);
                                    lienzo.textoVal4Estadisticoz.setText("=" + tamMuestra1);
                                    lienzo.layoutEstadistico2.setVisibility(View.VISIBLE);
                                    lienzo.imagenPotencia2.setImageResource(R.drawable.imagen_estadistico_zeta);
                                    lienzo.imagenDato1.setImageResource(R.drawable.imagen_limitesup_region_no_rechazo);
                                    lienzo.DatoPotencia1.setText("=" + limsup);
                                    lienzo.DatoPotencia2.setText("=" + nuevaMiu);
                                    lienzo.DatoPotencia3.setText("=" + desviacionEstandarPob);
                                    lienzo.DatoPotencia4.setText("=" + tamMuestra1);
                                    lienzo.paso2PotPrueba.setText("Estadístico Z para el límite superior:");
                                    lienzo.resultadoPotPrueba.setText("Una vez calculado el valor de la estadística de prueba Z para ambos límites de la región de no rechazo, cómo anteriormente se mencionó, es necesario buscar el valor calculado \n\nZ límite inferior = "+teorema1.estadisticoZeta1+"\n\nZ límite superior = " + teorema1.estadisticoZeta2 + "\n\nEn las tablas de la distribución acumulada de la normal estándar, donde:\n\nEn este caso el valor encontrado en tablas para el estadístico Z del límite inferior es es: " + teorema1.valTablas1 + "\ny para el estadístico Z del límite superior es de: " + teorema1.tab.redondeoDecimales((1-teorema1.valtTablas2),5) + "\n\nComo se dijo al inicio de la explicación, es necesario tomar en cuenta que para el cálculo de la potencia de la prueba se requiere utilizar la región de rechazo. En éste caso la región de rechazo está comprendida desde -∞ hata " + liminf + " y  desde " + limsup + " hasta ∞, por lo tanto para realizar el cálculo para la probabilidad de que se rechace de forma acertada la hipótesis nula se define por:\n\nP(Z<" + teorema1.estadisticoZeta1 + ") + P(Z>" + teorema1.estadisticoZeta2 + ") = P(Z<" + teorema1.estadisticoZeta1 + ") + (1-P(Z≤"+teorema1.estadisticoZeta2+")) = " + teorema1.valTablas1 + "+" + teorema1.valtTablas2 + " = " + potenciaPrueba);
                                    lienzo.resultadoPotPrueba.setVisibility(View.VISIBLE);
                                    lienzo.textView60.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }

                }
            }
        });


        //***********************************Caso3********************************************************************************************************

        lienzo.imageButton3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
                if(faltaDatoPruebaHipotesis()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa todos los datos requeridos");
                }else{
                    lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                    lienzo.calcularIntervaloConfianza.setText("Calcular potencia de prueba");
                    mediaPoblacional = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    desviacionEstandarPob = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                    if((tamMuestra1-1)<=100){
                        teorema = new PHIPDEDESC(desviacionEstandarPob,tamMuestra1,mediaPoblacional,promedioMuestral,(float)(significancia));
                        liminf = teorema.Caso3();
                        conclusion = teorema.decision;
                        String decision = "Con un intervalo de la región de aceptación comprendido desde "+liminf+"  hasta ∞, podemos concluir que:\n\n" + conclusion ;
                        SpannableString ss  = new SpannableString(decision);
                        lienzo.output.setText(decision);
                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("Podemos entender a la región de aceptación, como el intervalo de valores tales que si la prueba estadística cae dentro de este rango, decidimos aceptar la hipotesis nula")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la región de aceptación?");
                                glosario1.show();
                            }
                        };

                        ss.setSpan(clickableSpan,20,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.output.setText(ss);
                        lienzo.output.setMovementMethod(LinkMovementMethod.getInstance());


                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.teoremapruebashipotesisdedesconocida3);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val2.setText("="+mediaPoblacional);
                        lienzo.val3.setText("="+significancia);
                        lienzo.desviation.setImageResource(R.drawable.desviacionestandar_muestralchi);
                        lienzo.val4.setText("="+desviacionEstandarPob);
                        lienzo.val5.setText("="+tamMuestra1);
                        lienzo.dtoOpcional.setVisibility(View.GONE);

                        lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ ≥ µ0\n\nContra:\n\nHipótesis alterna: µ<µ0\n\nSe hace necesario calcular el límite inferior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema:");
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                        String aux = lienzo.paso1Phip.getText().toString();
                        SpannableString ss4  = new SpannableString(aux);


                        ClickableSpan clickableSpan4 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la hipótesis nula?");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.hipotesisnula3,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };


                        ClickableSpan clickableSpan5 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region1.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario2 = region1.create();
                                glosario2.setTitle("¿Qué es la hipótesis alterna");
                                LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna3,null);
                                glosario2.setView(vista1);
                                glosario2.show();
                            }
                        };
                        ss4.setSpan(clickableSpan4,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss4.setSpan(clickableSpan5,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso1Phip.setText(ss4);
                        lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.imageView2.setImageResource(R.drawable.liminfteoremapruebashipotesisdedesconocida3);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                        lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.desviacionestandar_muestralchi);
                        lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                        lienzo.tlcDato3TV.setText("="+tamMuestra1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.tealfa);
                        lienzo.tlcDato4TV.setText("="+teorema.getValTablaT());
                        lienzo.llz5.setVisibility(View.GONE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.layCalc1.setVisibility(View.GONE);
                        lienzo.layCalc2.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.VISIBLE);
                        lienzo.textView8.setText("Al observar la fórmula podemos percatarnos de que es necesario buscar el valor de t(α) en las tablas de la distribución t-student, donde:\n\nα = " + significancia+ "\n\nv = n - 1 = "+ tamMuestra1 +" - 1  = " + teorema.gradosLibertad +  "\n\nEn éste caso el valor encontrado en las tablas es de:\n t(α) = t("+significancia +") =" + teorema.getValTablaT() + "\n\nUna vez obtenido el valor de t(α) nos resta sustituir los valores correspondientes:" );
                        lienzo.layoutDesaparece2.setVisibility(View.GONE);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Para el límite inferior de la región de aceptación:");
                        lienzo.textView15.setText(mediaPoblacional+ " - [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+valTablas+"] = " + liminf);
                        lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite inferior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                        lienzo.reglaDecision.setImageResource(R.drawable.teoremapruebashipotesisdedesconocida3);
                        if(teorema.decision.contains("se rechaza la hipotesis nula")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral es menor que el límite inferior del intervalo de aceptación:\n\n"+promedioMuestral+"<"+liminf+"\n\nPor lo tanto: \n\n"  + conclusion );
                        }else if(teorema.decision.contains("no existe evidencia suficiente para rechazar H0")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral està dentro del intervalo de aceptación:\n\n"+promedioMuestral+"≥"+liminf+"\n\nPor lo tanto: \n\n"  + conclusion );
                        }

                        lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                        String aux2 = lienzo.paso2Phipodc.getText().toString();
                        SpannableString ss2 = new SpannableString(aux2);


                        ClickableSpan clickableSpan2 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("Región de aceptación");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casoa,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };
                        ss2.setSpan(clickableSpan2,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso2Phipodc.setText(ss2);
                        lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
                        lienzo.cambia7.setText("Nueva media poblacional");
                        lienzo.imageCambia7.setVisibility(View.GONE);
                        lienzo.cambia7.setTextColor(Color.RED);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(lienzo.editTextCambia7.getText().toString().isEmpty()){
                                    desplegarNotificacion("Por favor ingresa una nueva media poblacional");
                                }else{
                                    double nuevaMiu = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                                    double potenciaPrueba = teorema.potPrueba(nuevaMiu,"caso3");
                                    lienzo.output.setText("La potencia de la prueba calculada es:\n\n" + potenciaPrueba);
                                    lienzo.procedimiento.setVisibility(View.GONE);
                                    lienzo.layoutPotencia.setVisibility(View.VISIBLE);
                                    lienzo.caso1.setText("La potencia de la prueba cuantifica la probabilidad de rechazar la hipótesis nula de manera acertada. Es decir, rechazar la hipótesis nula cuando esta es falsa\n\nPara  calcular la potencia de la prueba utilizamos la región de rechazo\n\nSi observamos la gráfica de campana para las regiones de la prueba deseada podemos observar que el intervalo de la región de rechazo está comprendido desde -∞ hasta " + liminf + " .\n\nUna vez comprendido el punto anterior, es necesario calcular el valor de la estadística de prueba T que posteriormente buscaremos en las tablas acumuladas de la distribución t-student. Para ello nos podemos apoyar de la siguiente fórmula:");
                                    lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                                    lienzo.imagenPotencia.setImageResource(R.drawable.estadisticot);
                                    lienzo.textoVal1Estadisticoz.setText("=" + liminf);
                                    lienzo.textoVal2Estadisticoz.setText("=" + nuevaMiu);
                                    lienzo.val3EstadisticoZ.setImageResource(R.drawable.desviacionestandar_muestralchi);
                                    lienzo.textoVal3Estadisticoz.setText("=" + desviacionEstandarPob);
                                    lienzo.textoVal4Estadisticoz.setText("=" + tamMuestra1);
                                    lienzo.paso2PotPrueba.setText("Una vez calculado el valor de la estadística de prueba T, cómo anteriormente se mencionó, es necesario buscar el valor calculado \n\nT = "+teorema.valEstadisticoT+"\n\nEn las tablas de la distribución acumulada de la normal estándar, donde en este caso el valor encontrado en tablas es: " + potenciaPrueba + "\n\nComo se dijo al inicio de la explicación, es necesario tomar en cuenta que para el cálculo de la potencia de la prueba se requiere utilizar la región de rechazo. En éste caso la región de rechazo está comprendida desde -∞ hasta " + liminf + ". Por lo tanto para realizar el cálculo para la probabilidad de que se rechace de forma acertada la hipótesis nula se define por:\n\nP(T<" + teorema.valEstadisticoT + ") = " + potenciaPrueba);
                                    lienzo.layoutEstadistico2.setVisibility(View.GONE);
                                    lienzo.resultadoPotPrueba.setVisibility(View.GONE);
                                    lienzo.textView60.setVisibility(View.GONE);
                                }
                            }
                        });




                    }else{
                        conclusion = teorema1.casoa((float)significancia,tamMuestra1,desviacionEstandarPob,promedioMuestral,mediaPoblacional);
                        liminf = teorema1.getLim_inf();
                        switch (teorema1.decision){
                            case "es igual":
                            case "es menor":
                            case "no es menor":
                                String decision = "Con un intervalo de la región de aceptación comprendido desde "+liminf+"  hasta ∞, podemos concluir que:\n\n" + conclusion ;
                                SpannableString ss  = new SpannableString(decision);
                                lienzo.output.setText(decision);
                                ClickableSpan clickableSpan = new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View view) {
                                        AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                        region.setMessage("Podemos entender a la región de aceptación, como el intervalo de valores tales que si la prueba estadística cae dentro de este rango, decidimos aceptar la hipotesis nula")
                                                .setCancelable(false)
                                                .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                });
                                        AlertDialog glosario1 = region.create();
                                        glosario1.setTitle("¿Qué es la región de aceptación?");
                                        glosario1.show();
                                    }
                                };

                                ss.setSpan(clickableSpan,20,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                lienzo.output.setText(ss);
                                lienzo.output.setMovementMethod(LinkMovementMethod.getInstance());
                                break;
                            default: break;
                        }

                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.teoremaaproxpruebahip3);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val2.setText("="+mediaPoblacional);
                        lienzo.val3.setText("="+significancia);
                        lienzo.val4.setText("="+desviacionEstandarPob);
                        lienzo.val5.setText("="+tamMuestra1);
                        lienzo.dtoOpcional.setVisibility(View.GONE);

                        lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ ≥ µ0\n\nContra:\n\nHipótesis alterna: µ<µ0\n\nSe hace necesario calcular el límite inferior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema1:");
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                        String aux = lienzo.paso1Phip.getText().toString();
                        SpannableString ss  = new SpannableString(aux);


                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la hipótesis nula?");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.hipotesisnula3,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };


                        ClickableSpan clickableSpan1 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region1.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario2 = region1.create();
                                glosario2.setTitle("¿Qué es la hipótesis alterna");
                                LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna3,null);
                                glosario2.setView(vista1);
                                glosario2.show();
                            }
                        };
                        ss.setSpan(clickableSpan,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso1Phip.setText(ss);
                        lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.imageView2.setImageResource(R.drawable.liminfteoremaaproxpruebahip3);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                        lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                        lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                        lienzo.tlcDato3TV.setText("="+tamMuestra1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.gammaenalfa);
                        lienzo.tlcDato4TV.setText("="+teorema1.getValTablas());
                        lienzo.llz5.setVisibility(View.GONE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.layCalc1.setVisibility(View.GONE);
                        lienzo.layCalc2.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.VISIBLE);
                        lienzo.textView8.setText("Al observar la fórmula podemos percatarnos de que el cálculo a obtener es una aproximación por la distribución normal, por lo cual es necesario buscar el valor de ø(α) en las tablas de la distribución normál estándar, donde:\n\nα = " + significancia+ "\n\nEn éste caso el valor encontrado en las tablas es de:\n ø(α) = ø("+significancia +") =" + teorema1.getValTablas() + "\n\nUna vez obtenido el valor de ø(α) nos resta sustituir los valores correspondientes:" );
                        lienzo.layoutDesaparece2.setVisibility(View.GONE);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Para el límite inferior de la región de aceptación:");
                        lienzo.textView15.setText(mediaPoblacional+ " + [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema1.getValTablas()+"] = " + liminf);
                        lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite inferior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                        lienzo.reglaDecision.setImageResource(R.drawable.teoremaaproxpruebahip3);
                        switch (teorema1.decision){
                            case "es igual":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + " que el límite inferior del intervalo de aceptación:\n\n"+promedioMuestral+"="+liminf+"\n\nPor lo tanto: \n\n"  + conclusion );
                                break;
                            case "es menor":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision +" que el lìmite inferior del intervalo de aceptaciòn. Por lo tanto no està dentro del intervalo de aceptaciòn:\n\n"+promedioMuestral+"<"+liminf+".\n\nPor lo tanto: \n\n"  + conclusion );
                                break;
                            case "no es menor":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + " que el límite inferior del intervalo de aceptación\n\n"+promedioMuestral+">"+liminf+".\n\nPor lo tanto: \n\n"  + conclusion );
                                break;

                            default: break;
                        }

                        lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                        String aux2 = lienzo.paso2Phipodc.getText().toString();
                        SpannableString ss2 = new SpannableString(aux2);


                        ClickableSpan clickableSpan2 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("Región de aceptación");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casoa,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };
                        ss2.setSpan(clickableSpan2,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso2Phipodc.setText(ss2);
                        lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
                        lienzo.cambia7.setText("Nueva media poblacional");
                        lienzo.imageCambia7.setVisibility(View.GONE);
                        lienzo.cambia7.setTextColor(Color.RED);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.textView60.setVisibility(View.GONE);
                        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(lienzo.editTextCambia7.getText().toString().isEmpty()){
                                    desplegarNotificacion("Por favor ingresa una nueva media poblacional");
                                }else{
                                    double nuevaMiu = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                                    double potenciaPrueba = teorema1.potenciaPrueba(liminf,nuevaMiu,desviacionEstandarPob,tamMuestra1,"caso1");
                                    lienzo.output.setText("La potencia de la prueba calculada es:\n\n" + potenciaPrueba);
                                    lienzo.procedimiento.setVisibility(View.GONE);
                                    lienzo.layoutPotencia.setVisibility(View.VISIBLE);
                                    lienzo.caso1.setText("La potencia de la prueba cuantifica la probabilidad de rechazar la hipótesis nula de manera acertada. Es decir, rechazar la hipótesis nula cuando esta es falsa\n\nPara  calcular la potencia de la prueba utilizamos la región de rechazo\n\nSi observamos la gráfica de campana para las regiones de la prueba deseada podemos observar que el intervalo de la región de rechazo está comprendido desde -∞ hasta " + liminf + " .\n\nUna vez comprendido el punto anterior, es necesario calcular el valor de la estadística de prueba Z que posteriormente buscaremos en las tablas acumuladas de la distribución normal. Para ello nos podemos apoyar de la siguiente fórmula:");
                                    lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                                    lienzo.imagenPotencia.setImageResource(R.drawable.imagen_estadistico_zeta);
                                    lienzo.textoVal1Estadisticoz.setText("=" + liminf);
                                    lienzo.textoVal2Estadisticoz.setText("=" + nuevaMiu);
                                    lienzo.textoVal3Estadisticoz.setText("=" + desviacionEstandarPob);
                                    lienzo.textoVal4Estadisticoz.setText("=" + tamMuestra1);
                                    lienzo.paso2PotPrueba.setText("Una vez calculado el valor de la estadística de prueba Z, cómo anteriormente se mencionó, es necesario buscar el valor calculado \n\nZ = "+teorema1.estadisticoZeta+"\n\nEn las tablas de la distribución acumulada de la normal estándar, donde en este caso el valor encontrado en tablas es: " + teorema1.tab.redondeoDecimales((1-potenciaPrueba),5) + "\n\nComo se dijo al inicio de la explicación, es necesario tomar en cuenta que para el cálculo de la potencia de la prueba se requiere utilizar la región de rechazo. En éste caso la región de rechazo está comprendida desde " + limsup + " hasta ∞, por lo tanto para realizar el cálculo para la probabilidad de que se rechace de forma acertada la hipótesis nula se define por:\n\nP(Z≤" + teorema1.estadisticoZeta + ") = " + potenciaPrueba);
                                    lienzo.layoutEstadistico2.setVisibility(View.GONE);
                                    lienzo.resultadoPotPrueba.setVisibility(View.GONE);
                                    lienzo.textView60.setVisibility(View.GONE);
                                }
                            }
                        });
                    }
                }

            }
        });

        /******************************************************caso 4 *********************************************************/


        lienzo.imageButton7.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
                lienzo.calcularIntervaloConfianza.setVisibility(View.GONE);
                lienzo.LinearLayoutDato7Opcional.setVisibility(View.GONE);
                if(faltaDatoPruebaHipotesis1()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa todos los datos requeridos");
                }else{
                    lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                    lienzo.calcularIntervaloConfianza.setText("Calcular potencia de prueba");
                    mediaPoblacional = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    desviacionEstandarPob = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                    mediaPoblacional1 = Double.parseDouble(lienzo.miu1.getText().toString());
                    if(tamMuestra1-1<=100){
                        teorema = new PHIPDEDESC(desviacionEstandarPob,tamMuestra1,mediaPoblacional,promedioMuestral,(float)significancia);
                        String intervalo = teorema.caso4(mediaPoblacional1);
                        conclusion = teorema.decision;
                        liminf = teorema.getLimInf();
                        limsup = teorema.getLimSup();

                        String decision = "Con un intervalo de la región de aceptación comprendido desde " + liminf+"  hasta " + limsup + " podemos concluir que:\n\n" + conclusion ;
                        SpannableString ss  = new SpannableString(decision);
                        lienzo.output.setText(decision);
                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("Podemos entender a la región de aceptación, como el intervalo de valores tales que si la prueba estadística cae dentro de este rango, decidimos aceptar la hipotesis nula")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la región de aceptación?");
                                glosario1.show();
                            }
                        };

                        ss.setSpan(clickableSpan,20,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.output.setText(ss);
                        lienzo.output.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.teoremapruebashipotesisdedesconocida4);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val2.setText("="+mediaPoblacional);
                        lienzo.val3.setText("="+significancia);
                        lienzo.val4.setText("="+desviacionEstandarPob);
                        lienzo.val5.setText("="+tamMuestra1);
                        lienzo.val6.setText("=" +mediaPoblacional1);
                        lienzo.dtoOpcional.setVisibility(View.VISIBLE);

                        lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ0<µ<µ1\n\nContra:\n\nHipótesis alterna: µ<µ0 ó µ>µ1\n\nSe hace necesario calcular el intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del teorema presentado anteriormente.\n\nSi observamos el teorema, nos damos cuenta de que es necesario buscar el valor de t(α/2) en las tablas de la distribucion normal estandar. Donde:\n\nα=" + significancia + "\n\nα/2 = " +teorema.redondeoDecimales((significancia/2),5)+"\n\nv = n - 1 = " + tamMuestra1 + " - 1 = " + teorema.gradosLibertad+"\n\nBuscando el valor en tablas encontramos que:\nt(α/2)= t("+teorema.redondeoDecimales((significancia/2),5)+")="+teorema.getValTablaT());
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                        lienzo.layCalc1.setVisibility(View.VISIBLE);
                        lienzo.layCalc2.setVisibility(View.VISIBLE);
                        String aux = lienzo.paso1Phip.getText().toString();
                        SpannableString ss4  = new SpannableString(aux);


                        ClickableSpan clickableSpan4 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la hipótesis nula?");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.hipotesisnula4,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };


                        ClickableSpan clickableSpan1 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region1.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario2 = region1.create();
                                glosario2.setTitle("¿Qué es la hipótesis alterna");
                                LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna4,null);
                                glosario2.setView(vista1);
                                glosario2.show();
                            }
                        };
                        ss4.setSpan(clickableSpan4,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss4.setSpan(clickableSpan1,93,111,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso1Phip.setText(ss4);
                        lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.imageView2.setImageResource(R.drawable.limsupteoremapruebashipotesis1);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                        lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.desviacionestandar_muestralchi);
                        lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                        lienzo.tlcDato3TV.setText("="+tamMuestra1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.talfamedios);
                        lienzo.tlcDato4TV.setText("="+teorema.getValTablaT());
                        lienzo.tlcDato5TV.setText("=" + mediaPoblacional1);
                        lienzo.tlcImageDato5.setImageResource(R.drawable.dato_h);
                        lienzo.llz5.setVisibility(View.VISIBLE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.VISIBLE);
                        lienzo.textView8.setText("Sutituyendo los valores correspondientes en el càlculo del intervalo para la regiòn de aceptacion, cuyo càlculo se consigue sustituyendo los valores correspondientes: ");
                        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                        lienzo.calculo1.setText("Para el lìmite inferior:");
                        lienzo.calcOp1.setImageResource(R.drawable.liminfteoremapruebashipotesisdedesconocida4);
                        lienzo.calculo2.setText("Para el lìmite superior:");
                        lienzo.calcOp2.setImageResource(R.drawable.limsupteoremapruebashipotesisdedesconocida4);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Para el límite inferior de la región de aceptación:");
                        lienzo.textView15.setText(mediaPoblacional+ "-[("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema.getValTablaT()+"] = " + liminf);
                        lienzo.limitsuperior.setText("Para el lìmite superior de la regiòn de aceptaciòn:");
                        lienzo.limitsuperior.setVisibility(View.VISIBLE);
                        lienzo.zeta2.setText(mediaPoblacional1+ "+ [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema.getValTablaT()+"] = " + limsup);
                        lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el intervalo de la región de aceptación:\n["+liminf+","+limsup+"]\n\n Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                        lienzo.reglaDecision.setImageResource(R.drawable.teoremapruebashipotesisdedesconocida4);
                        if (teorema.decision.contains("se rechaza la hipotesis nula")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral se encuentra fuera del intervalo de la regiòn de no rechazo :\n\n" + promedioMuestral + "<" + liminf + " ò " + promedioMuestral + ">" +  limsup + "\n\nPor lo tanto: \n\n" + conclusion);
                        }else if(teorema.decision.contains("no existe evidencia suficiente para rechazar H0")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral se encuentra dentro del intervalo de la regiòn de no rechazo :\n\n" +liminf + "≤" + promedioMuestral + "≤" + limsup + "\n\nPor lo tanto: \n\n" + conclusion);
                        }

                        lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                        String aux2 = lienzo.paso2Phipodc.getText().toString();
                        SpannableString ss2 = new SpannableString(aux2);


                        ClickableSpan clickableSpan2 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("Región de aceptación");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casoc,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };
                        ss2.setSpan(clickableSpan2,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso2Phipodc.setText(ss2);
                        lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());
                    }else{
                        conclusion = teorema1.Aproxcasod((float)significancia,tamMuestra1,desviacionEstandarPob,promedioMuestral,mediaPoblacional,mediaPoblacional1);
                        liminf = teorema1.getLim_inf();
                        limsup = teorema1.getLim_sup();
                                String decision = "Con un intervalo de la región de aceptación comprendido desde " + liminf+"  hasta " + limsup + " podemos concluir que:\n\n" + conclusion ;
                                SpannableString sss  = new SpannableString(decision);
                                lienzo.output.setText(decision);
                                ClickableSpan clickableSpan = new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View view) {
                                        AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                        region.setMessage("Podemos entender a la región de aceptación, como el intervalo de valores tales que si la prueba estadística cae dentro de este rango, decidimos aceptar la hipotesis nula")
                                                .setCancelable(false)
                                                .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        dialogInterface.cancel();
                                                    }
                                                });
                                        AlertDialog glosario1 = region.create();
                                        glosario1.setTitle("¿Qué es la región de aceptación?");
                                        glosario1.show();
                                    }
                                };

                                sss.setSpan(clickableSpan,20,43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                lienzo.output.setText(sss);
                                lienzo.output.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.teoremaaproxpruebashipotesis4);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val2.setText("="+mediaPoblacional);
                        lienzo.val3.setText("="+significancia);
                        lienzo.val4.setText("="+desviacionEstandarPob);
                        lienzo.val5.setText("="+tamMuestra1);
                        lienzo.val6.setText("=" + mediaPoblacional1);
                        lienzo.dtoOpcional.setVisibility(View.VISIBLE);

                        lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ0<µ<µ1\n\nContra:\n\nHipótesis alterna: µ<µ0 ó µ>µ1\n\nSe hace necesario calcular el intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del teorema presentado anteriormente.\n\nSi observamos dicho teorema, nos damos cuenta de que lo que realizaremos no es mas que una aproximación al resultado por la distribución normal, ya que el tamaño de muestra es demasiado grande y con éstas condiciones se nos hace imposible buscar valores en las tablas de la distribución t-student. Por lo cual es necesario buscar los valores de Ø(α/2) y Ø[1-(α/2)] en las tablas de la distribucion normal estandar. Donde:\n\nα=" + significancia + "\n\nα/2 = " +teorema1.tab.redondeoDecimales((significancia/2),5)+"\n\n1-(α/2) = "+teorema1.tab.redondeoDecimales((1-(significancia/2)),5) + "\n\nBuscando el valor en tablas encontramos que:\nØ(α/2)=Ø("+teorema1.tab.redondeoDecimales((significancia/2),5)+")="+teorema1.valTablas1 + "\n\nØ[1-(α/2)]=Ø("+teorema1.tab.redondeoDecimales((1-(significancia/2)),5)+")="+teorema1.valtTablas2);
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                        lienzo.layCalc1.setVisibility(View.VISIBLE);
                        lienzo.layCalc2.setVisibility(View.VISIBLE);
                        String aux = lienzo.paso1Phip.getText().toString();
                        SpannableString ss  = new SpannableString(aux);


                        ClickableSpan clickableSpan2 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("¿Qué es la hipótesis nula?");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.hipotesisnula4,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };


                        ClickableSpan clickableSpan1 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region1.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario2 = region1.create();
                                glosario2.setTitle("¿Qué es la hipótesis alterna");
                                LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna4,null);
                                glosario2.setView(vista1);
                                glosario2.show();
                            }
                        };
                        ss.setSpan(clickableSpan2,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss.setSpan(clickableSpan1,93,111,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso1Phip.setText(ss);
                        lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.imageView2.setImageResource(R.drawable.limsupteoremaaproxpruebashipotesis4);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                        lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                        lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                        lienzo.tlcDato3TV.setText("="+tamMuestra1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.zetaalfa);
                        lienzo.tlcDato4TV.setText("="+teorema1.getValTablas());
                        lienzo.tlcDato5TV.setText("=" + mediaPoblacional1);
                        lienzo.tlcImageDato5.setImageResource(R.drawable.dato_h);
                        lienzo.llz5.setVisibility(View.VISIBLE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.VISIBLE);
                        lienzo.textView8.setText("Sutituyendo los valores correspondientes en el càlculo del intervalo para la regiòn de aceptacion, cuyo càlculo se consigue sustituyendo los valores correspondientes: ");
                        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                        lienzo.calculo1.setText("Para el lìmite inferior:");
                        lienzo.calcOp1.setImageResource(R.drawable.liminfteoremaaproxpruebashipotesis4);
                        lienzo.calculo2.setText("Para el lìmite superior:");
                        lienzo.calcOp2.setImageResource(R.drawable.limsupteoremaaproxpruebashipotesis4);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Para el límite inferior de la región de aceptación:");
                        lienzo.textView15.setText(mediaPoblacional+ "-[("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema1.getValTablas()+"] = " + liminf);
                        lienzo.limitsuperior.setText("Para el lìmite superior de la regiòn de aceptaciòn:");
                        lienzo.limitsuperior.setVisibility(View.VISIBLE);
                        lienzo.zeta2.setText(mediaPoblacional1+ "+ [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema1.getValTablas()+"] = " + limsup);
                        lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el intervalo de la región de aceptación:\n["+liminf+","+limsup+"]\n\n Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                        lienzo.reglaDecision.setImageResource(R.drawable.teoremaaproxpruebashipotesis4);
                        switch (teorema1.decision){
                            case " es menor que el límite inferior":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + " del intervalo para el área de aceptación:\n\n"+promedioMuestral+"<"+liminf+"\n\nPor lo tanto: \n\n"  + conclusion );
                                break;
                            case " es mayor que el límite superior":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + " del intervalo para el área de aceptación:\n\n"+promedioMuestral+">"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                                break;
                            case " está dentro del intervalo de no rechazo ":
                                lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema1.decision + ":\n\n"+liminf+"≤"+promedioMuestral+"≤"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                                break;

                            default: break;
                        }

                        lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                        String aux2 = lienzo.paso2Phipodc.getText().toString();
                        SpannableString ss2 = new SpannableString(aux2);


                        ClickableSpan clickableSpan5 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                                region.setMessage("")
                                        .setCancelable(false)
                                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog glosario1 = region.create();
                                glosario1.setTitle("Región de aceptación");
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipDEDesconocida.this);
                                final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casoc,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };
                        ss2.setSpan(clickableSpan5,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso2Phipodc.setText(ss2);
                        lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());
                    }
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
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.output.setText("");
                lienzo.mediaPoblacional.setText("");
                lienzo.desviacionEstandarPob.setText("");
                lienzo.promedioMuestral.setText("");
                lienzo.tamMuestral.setText("");
                lienzo.significancia.setText("");
                lienzo.miu1.setText("");
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
            }
        });


        lienzo.imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                region.setMessage("Se trata del primer caso para nuestro contraste de hipótesis.\n\nGeneralmente valida ó niega (según sea el caso) afirmaciones cómo las siguientes:\n\nAfirmación 1: Se dice que la media poblacional es menor o igual que cierta cantidad\n\nAfirmación 2: Se dice que la media poblacional es unicamente mayor que cierta cantidad")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog glosario1 = region.create();
                glosario1.setTitle("¿Cómo uso éste contraste de hipótesis?");
                glosario1.show();
            }
        });


        lienzo.imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                region.setMessage("Se trata del segundo caso para nuestro contraste de hipótesis.\n\nGeneralmente valida ó niega (según sea el caso) afirmaciones cómo las siguientes:\n\nAfirmación 1: Se dice que la media poblacional es unicamente igual que cierta cantidad\n\nAfirmación 2: Se dice que la media poblacional es siempre diferente que cierta cantidad")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog glosario1 = region.create();
                glosario1.setTitle("¿Cómo uso éste contraste de hipótesis?");
                glosario1.show();
            }
        });


        lienzo.imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                region.setMessage("Se trata del tercer caso para nuestro contraste de hipótesis.\n\nGeneralmente valida ó niega (según sea el caso) afirmaciones cómo las siguientes:\n\nAfirmación 1: Se dice que la media poblacional es mayor o igual que cierta cantidad\n\nAfirmación 2: Se dice que la media poblacional es unicamente menor que cierta cantidad")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog glosario1 = region.create();
                glosario1.setTitle("¿Cómo uso éste contraste de hipótesis?");
                glosario1.show();
            }
        });


        lienzo.imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipDEDesconocida.this);
                region.setMessage("Se trata del cuarto caso para nuestro contraste de hipótesis.\n\nGeneralmente valida ó niega (según sea el caso) afirmaciones cómo las siguientes:\n\nAfirmación 1: Se dice que la media poblacional siempre se encuentra dentro de cierto intervalo\n\nAfirmación 2: Se dice que la media poblacional nunca se va a encontrar entre cierto intervalo dado")
                        .setCancelable(false)
                        .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog glosario1 = region.create();
                glosario1.setTitle("¿Cómo uso éste contraste de hipótesis?");
                glosario1.show();
            }
        });

    }

    public boolean faltaDatoPruebaHipotesis(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()) return true;
        else return false;
    }


    public boolean faltaDatoPruebaHipotesis1(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()) return true;
        else return false;
    }


    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }
}