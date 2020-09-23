package com.example.estadistica;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;


public class INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA extends AppCompatActivity {

    private PruebaHipotesisDVconocida teorema = new PruebaHipotesisDVconocida();
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
                    conclusion = teorema.casob((float)significancia,tamMuestra1,desviacionEstandarPob,promedioMuestral,mediaPoblacional);
                    limsup = teorema.getLim_sup();
                    switch (teorema.decision){
                        case "es igual":
                        case " está dentro del intervalo de no rechazo ":
                        case "es mayor":
                            String decision = "Con un intervalo de la región de aceptación comprendido desde -∞  hasta " + limsup + " podemos concluir que:\n\n" + conclusion ;
                            SpannableString ss  = new SpannableString(decision);
                            lienzo.output.setText(decision);
                            ClickableSpan clickableSpan = new ClickableSpan() {
                                @Override
                                public void onClick(@NonNull View view) {
                                    AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                    lienzo.teorema.setImageResource(R.drawable.teoremapruebashipotesis1);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.val1.setText("="+promedioMuestral);
                    lienzo.val2.setText("="+mediaPoblacional);
                    lienzo.val3.setText("="+significancia);
                    lienzo.val4.setText("="+desviacionEstandarPob);
                    lienzo.val5.setText("="+tamMuestra1);
                    lienzo.dtoOpcional.setVisibility(View.GONE);

                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ ≤ µ0\n\nContra:\n\nHipótesis alterna: µ>µ0\n\nSe hace necesario calcular el límite superior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema:");
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss  = new SpannableString(aux);


                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
                            final View vista = imagen_alert1.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
                            final View vista1 = imagen_alert.inflate(R.layout.activity_p_r_u_e_b_a_h_i_p_o_t_e_s_i_s_a_l_t_e_r_n_a_t_o_a_s_t,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss.setSpan(clickableSpan,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.imageView2.setImageResource(R.drawable.limsupteoremapruebashipotesis1);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                    lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                    lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato3TV.setText("="+tamMuestra1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.zetaalfa);
                    lienzo.tlcDato4TV.setText("="+teorema.getValTablas());
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Al observar la fórmula podemos percatarnos de que es necesario buscar el valor de Z(α) en las tablas de la distribución normál estándar, donde:\n\nα = " + significancia+ "\n\nEn éste caso el valor encontrado en las tablas es de:\n Z(α) = Z("+significancia +") =" + teorema.getValTablas() + "\n\nUna vez obtenido el valor de Z(α) nos resta sustituir los valores correspondientes:" );
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Para el límite superior de la región de aceptación:");
                    lienzo.textView15.setText(mediaPoblacional+ " + [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+valTablas+"] = " + limsup);
                    lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite superior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                    lienzo.reglaDecision.setImageResource(R.drawable.regladecisionpruebashipotesis1);
                    switch (teorema.decision){
                        case "es igual":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + " que el límite superior del intervalo de aceptación:\n\n"+promedioMuestral+"="+limsup+"\n\nPor lo tanto: \n\n"  + conclusion );
                            break;
                        case " está dentro del intervalo de no rechazo ":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + ":\n\n"+promedioMuestral+"<"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                            break;
                        case "es mayor":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + " que el límite superior del intervalo de aceptación\n\n"+promedioMuestral+">"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                            break;

                        default: break;
                    }

                    lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                    String aux2 = lienzo.paso2Phipodc.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                                double potenciaPrueba = teorema.potenciaPrueba(limsup,nuevaMiu,desviacionEstandarPob,tamMuestra1,"caso2");
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
                                lienzo.paso2PotPrueba.setText("Una vez calculado el valor de la estadística de prueba Z, cómo anteriormente se mencionó, es necesario buscar el valor calculado \n\nZ = "+teorema.estadisticoZeta+"\n\nEn las tablas de la distribución acumulada de la normal estándar, donde en este caso el valor encontrado en tablas es: " + teorema.tab.redondeoDecimales((1-potenciaPrueba),5) + "\n\nComo se dijo al inicio de la explicación, es necesario tomar en cuenta que para el cálculo de la potencia de la prueba se requiere utilizar la región de rechazo. En éste caso la región de rechazo está comprendida desde " + limsup + " hasta ∞, por lo tanto para realizar el cálculo para la probabilidad de que se rechace de forma acertada la hipótesis nula se define por:\n\nP(Z>" + teorema.estadisticoZeta + ") = 1 - P(Z≤" + teorema.estadisticoZeta + ") = " + potenciaPrueba);
                                lienzo.layoutEstadistico2.setVisibility(View.GONE);
                                lienzo.resultadoPotPrueba.setVisibility(View.GONE);
                                lienzo.textView60.setVisibility(View.GONE);
                            }
                        }
                    });

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
                    conclusion = teorema.casoc((float)significancia,tamMuestra1,desviacionEstandarPob,promedioMuestral,mediaPoblacional);
                    liminf = teorema.getLim_inf();
                    limsup = teorema.getLim_sup();
                    switch (teorema.decision){
                        case "es menor que el límite inferior del intervalo de aceptación":
                        case "es mayor que el límite superior del intervalo de aceptación":
                        case "está dentro del intervalo aceptación":
                            String decision = "Con un intervalo de la región de aceptación comprendido desde " + liminf+"  hasta " + limsup + " podemos concluir que:\n\n" + conclusion ;
                            SpannableString ss  = new SpannableString(decision);
                            lienzo.output.setText(decision);
                            ClickableSpan clickableSpan = new ClickableSpan() {
                                @Override
                                public void onClick(@NonNull View view) {
                                    AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                    lienzo.teorema.setImageResource(R.drawable.teoremapruebashipotesismedia2);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.val1.setText("="+promedioMuestral);
                    lienzo.val2.setText("="+mediaPoblacional);
                    lienzo.val3.setText("="+significancia);
                    lienzo.val4.setText("="+desviacionEstandarPob);
                    lienzo.val5.setText("="+tamMuestra1);
                    lienzo.dtoOpcional.setVisibility(View.GONE);

                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ = µ0\n\nContra:\n\nHipótesis alterna: µ ≠ µ0\n\nSe hace necesario calcular el intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del teorema presentado anteriormente.\n\nSi observamos el teorema, nos damos cuenta de que es necesario buscar el valor de Z(α/2) en las tablas de la distribucion normal estandar. Donde:\n\nα=" + significancia + "\n\nα/2 = " +teorema.tab.redondeoDecimales((significancia/2),5)+"\n\nBuscando el valor en tablas encontramos que:\nZ(α/2)=Z("+teorema.tab.redondeoDecimales((significancia/2),5)+")="+teorema.getValTablas());
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss  = new SpannableString(aux);


                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
                            final View vista = imagen_alert1.inflate(R.layout.activityhipotesisnula2,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
                            final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna2,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss.setSpan(clickableSpan,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.imageView2.setImageResource(R.drawable.limsupteoremapruebashipotesis1);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                    lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                    lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato3TV.setText("="+tamMuestra1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.zetaalfa);
                    lienzo.tlcDato4TV.setText("="+teorema.getValTablas());
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Sutituyendo los valores correspondientes en el càlculo del intervalo para la regiòn de aceptacion, cuyo càlculo se consigue sustituyendo los valores correspondientes: ");
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.calculo1.setText("Para el lìmite inferior:");
                    lienzo.calcOp1.setImageResource(R.drawable.liminfteoremapruebashipotesismedia2);
                    lienzo.calculo2.setText("Para el lìmite superior:");
                    lienzo.calcOp2.setImageResource(R.drawable.limsupteoremapruebashipotesismedia2);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Para el límite inferior de la región de aceptación:");
                    lienzo.textView15.setText(mediaPoblacional+ "-[("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema.getValTablas()+"] = " + liminf);
                    lienzo.limitsuperior.setText("Para el lìmite superior de la regiòn de aceptaciòn:");
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.zeta2.setText(mediaPoblacional+ "+ [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema.getValTablas()+"] = " + limsup);
                    lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el intervalo de la región de aceptación:\n["+liminf+","+limsup+"]\n\n Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                    lienzo.reglaDecision.setImageResource(R.drawable.regladecisionteoremapruebashipotesismedia2);
                    switch (teorema.decision){
                        case "es menor que el límite inferior del intervalo de aceptación":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + " :\n\n"+promedioMuestral+"="+liminf+"\n\nPor lo tanto: \n\n"  + conclusion );
                            break;
                        case " es mayor que el límite superior del intervalo de aceptación":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + ":\n\n"+promedioMuestral+"<"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                            break;
                        case "está dentro del intervalo aceptación":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + ":\n\n"+liminf+"≤"+promedioMuestral+"≤"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                            break;

                        default: break;
                    }

                    lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                    String aux2 = lienzo.paso2Phipodc.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                                double potenciaPrueba = teorema.potenciaPrueba(liminf,limsup,desviacionEstandarPob,nuevaMiu,tamMuestra1);
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
                                lienzo.resultadoPotPrueba.setText("Una vez calculado el valor de la estadística de prueba Z para ambos límites de la región de no rechazo, cómo anteriormente se mencionó, es necesario buscar el valor calculado \n\nZ límite inferior = "+teorema.estadisticoZeta1+"\n\nZ límite superior = " + teorema.estadisticoZeta2 + "\n\nEn las tablas de la distribución acumulada de la normal estándar, donde:\n\nEn este caso el valor encontrado en tablas para el estadístico Z del límite inferior es es: " + teorema.valTablas1 + "\ny para el estadístico Z del límite superior es de: " + teorema.tab.redondeoDecimales((1-teorema.valtTablas2),5) + "\n\nComo se dijo al inicio de la explicación, es necesario tomar en cuenta que para el cálculo de la potencia de la prueba se requiere utilizar la región de rechazo. En éste caso la región de rechazo está comprendida desde -∞ hata " + liminf + " y  desde " + limsup + " hasta ∞, por lo tanto para realizar el cálculo para la probabilidad de que se rechace de forma acertada la hipótesis nula se define por:\n\nP(Z<" + teorema.estadisticoZeta1 + ") + P(Z>" + teorema.estadisticoZeta2 + ") = P(Z<" + teorema.estadisticoZeta1 + ") + (1-P(Z≤"+teorema.estadisticoZeta2+")) = " + teorema.valTablas1 + "+" + teorema.valtTablas2 + " = " + potenciaPrueba);
                                lienzo.resultadoPotPrueba.setVisibility(View.VISIBLE);
                                lienzo.textView60.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            }
        });


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
                    conclusion = teorema.casoa((float)significancia,tamMuestra1,desviacionEstandarPob,promedioMuestral,mediaPoblacional);
                    liminf = teorema.getLim_inf();
                    switch (teorema.decision){
                        case "es igual":
                        case "es menor":
                        case "no es menor":
                            String decision = "Con un intervalo de la región de aceptación comprendido desde "+liminf+"  hasta ∞, podemos concluir que:\n\n" + conclusion ;
                            SpannableString ss  = new SpannableString(decision);
                            lienzo.output.setText(decision);
                            ClickableSpan clickableSpan = new ClickableSpan() {
                                @Override
                                public void onClick(@NonNull View view) {
                                    AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                    lienzo.teorema.setImageResource(R.drawable.teoremapruebashipotesismedia1casoa);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.val1.setText("="+promedioMuestral);
                    lienzo.val2.setText("="+mediaPoblacional);
                    lienzo.val3.setText("="+significancia);
                    lienzo.val4.setText("="+desviacionEstandarPob);
                    lienzo.val5.setText("="+tamMuestra1);
                    lienzo.dtoOpcional.setVisibility(View.GONE);

                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ ≥ µ0\n\nContra:\n\nHipótesis alterna: µ<µ0\n\nSe hace necesario calcular el límite inferior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema:");
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss  = new SpannableString(aux);


                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
                            final View vista = imagen_alert1.inflate(R.layout.hipotesisnula3,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
                            final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna3,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss.setSpan(clickableSpan,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.imageView2.setImageResource(R.drawable.liminfpruebashipotesis3);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                    lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                    lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato3TV.setText("="+tamMuestra1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.zetaalfa);
                    lienzo.tlcDato4TV.setText("="+teorema.getValTablas());
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Al observar la fórmula podemos percatarnos de que es necesario buscar el valor de Z(α) en las tablas de la distribución normál estándar, donde:\n\nα = " + significancia+ "\n\nEn éste caso el valor encontrado en las tablas es de:\n Z(α) = Z("+significancia +") =" + teorema.getValTablas() + "\n\nUna vez obtenido el valor de Z(α) nos resta sustituir los valores correspondientes:" );
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Para el límite inferior de la región de aceptación:");
                    lienzo.textView15.setText(mediaPoblacional+ " - [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+valTablas+"] = " + liminf);
                    lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite inferior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                    lienzo.reglaDecision.setImageResource(R.drawable.regladecisionpruebashipotesis3);
                    switch (teorema.decision){
                        case "es igual":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + " que el límite inferior del intervalo de aceptación:\n\n"+promedioMuestral+"="+liminf+"\n\nPor lo tanto: \n\n"  + conclusion );
                            break;
                        case "es menor":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision +" que el lìmite inferior del intervalo de aceptaciòn. Por lo tanto no està dentro del intervalo de aceptaciòn:\n\n"+promedioMuestral+"<"+liminf+".\n\nPor lo tanto: \n\n"  + conclusion );
                            break;
                        case "no es menor":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + " que el límite inferior del intervalo de aceptación\n\n"+promedioMuestral+">"+liminf+".\n\nPor lo tanto: \n\n"  + conclusion );
                            break;

                        default: break;
                    }

                    lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                    String aux2 = lienzo.paso2Phipodc.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                                double potenciaPrueba = teorema.potenciaPrueba(liminf,nuevaMiu,desviacionEstandarPob,tamMuestra1,"caso1");
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
                                lienzo.paso2PotPrueba.setText("Una vez calculado el valor de la estadística de prueba Z, cómo anteriormente se mencionó, es necesario buscar el valor calculado \n\nZ = "+teorema.estadisticoZeta+"\n\nEn las tablas de la distribución acumulada de la normal estándar, donde en este caso el valor encontrado en tablas es: " + teorema.tab.redondeoDecimales((1-potenciaPrueba),5) + "\n\nComo se dijo al inicio de la explicación, es necesario tomar en cuenta que para el cálculo de la potencia de la prueba se requiere utilizar la región de rechazo. En éste caso la región de rechazo está comprendida desde " + limsup + " hasta ∞, por lo tanto para realizar el cálculo para la probabilidad de que se rechace de forma acertada la hipótesis nula se define por:\n\nP(Z≤" + teorema.estadisticoZeta + ") = " + potenciaPrueba);
                                lienzo.layoutEstadistico2.setVisibility(View.GONE);
                                lienzo.resultadoPotPrueba.setVisibility(View.GONE);
                                lienzo.textView60.setVisibility(View.GONE);
                            }
                        }
                    });
                }

            }
        });
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
                    conclusion = teorema.casod((float)significancia,tamMuestra1,desviacionEstandarPob,promedioMuestral,mediaPoblacional,mediaPoblacional1);
                    liminf = teorema.getLim_inf();
                    limsup = teorema.getLim_sup();
                    switch (teorema.decision){
                        case " es menor que el límite inferior":
                        case " es mayor que el límite superior":
                        case " está dentro del intervalo de no rechazo ":
                            String decision = "Con un intervalo de la región de aceptación comprendido desde " + liminf+"  hasta " + limsup + " podemos concluir que:\n\n" + conclusion ;
                            SpannableString ss  = new SpannableString(decision);
                            lienzo.output.setText(decision);
                            ClickableSpan clickableSpan = new ClickableSpan() {
                                @Override
                                public void onClick(@NonNull View view) {
                                    AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                    lienzo.teorema.setImageResource(R.drawable.teoremapruebashipotesis1casod);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.val1.setText("="+promedioMuestral);
                    lienzo.val2.setText("="+mediaPoblacional);
                    lienzo.val3.setText("="+significancia);
                    lienzo.val4.setText("="+desviacionEstandarPob);
                    lienzo.val5.setText("="+tamMuestra1);
                    lienzo.val6.setText("=" + mediaPoblacional1);
                    lienzo.dtoOpcional.setVisibility(View.VISIBLE);

                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ0<µ<µ1\n\nContra:\n\nHipótesis alterna: µ<µ0 ó µ>µ1\n\nSe hace necesario calcular el intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del teorema presentado anteriormente.\n\nSi observamos el teorema, nos damos cuenta de que es necesario buscar el valor de Z(α/2) en las tablas de la distribucion normal estandar. Donde:\n\nα=" + significancia + "\n\nα/2 = " +teorema.tab.redondeoDecimales((significancia/2),5)+"\n\nBuscando el valor en tablas encontramos que:\nZ(α/2)=Z("+teorema.tab.redondeoDecimales((significancia/2),5)+")="+teorema.getValTablas());
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss  = new SpannableString(aux);


                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
                            final View vista = imagen_alert1.inflate(R.layout.hipotesisnula4,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
                            final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna4,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss.setSpan(clickableSpan,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss.setSpan(clickableSpan1,93,111,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.imageView2.setImageResource(R.drawable.limsupteoremapruebashipotesis1);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.dato_b);
                    lienzo.tlcDato1TV.setText("="+mediaPoblacional);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                    lienzo.tlcDato2TV.setText("="+desviacionEstandarPob);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato3TV.setText("="+tamMuestra1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.zetaalfa);
                    lienzo.tlcDato4TV.setText("="+teorema.getValTablas());
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
                    lienzo.calcOp1.setImageResource(R.drawable.liminfteoremapruebashipotesis1casod);
                    lienzo.calculo2.setText("Para el lìmite superior:");
                    lienzo.calcOp2.setImageResource(R.drawable.limsupteoremapruebashipotesis1casod);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Para el límite inferior de la región de aceptación:");
                    lienzo.textView15.setText(mediaPoblacional+ "-[("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema.getValTablas()+"] = " + liminf);
                    lienzo.limitsuperior.setText("Para el lìmite superior de la regiòn de aceptaciòn:");
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.zeta2.setText(mediaPoblacional1+ "+ [("+desviacionEstandarPob+"/√"+tamMuestra1+")*"+teorema.getValTablas()+"] = " + limsup);
                    lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el intervalo de la región de aceptación:\n["+liminf+","+limsup+"]\n\n Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                    lienzo.reglaDecision.setImageResource(R.drawable.regladecisionteoremapruebashipotesis1casod);
                     switch (teorema.decision){
                        case " es menor que el límite inferior":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + " del intervalo para el área de aceptación:\n\n"+promedioMuestral+"<"+liminf+"\n\nPor lo tanto: \n\n"  + conclusion );
                            break;
                        case " es mayor que el límite superior":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + " del intervalo para el área de aceptación:\n\n"+promedioMuestral+">"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                            break;
                        case " está dentro del intervalo de no rechazo ":
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor del promedio muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro promedio muestral "  + teorema.decision + ":\n\n"+liminf+"≤"+promedioMuestral+"≤"+limsup+".\n\nPor lo tanto: \n\n"  + conclusion );
                            break;

                        default: break;
                    }

                    lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                    String aux2 = lienzo.paso2Phipodc.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
                            final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casoc,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso2Phipodc.setText(ss2);
                    lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());
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
                AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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
                AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASDEHIPOTESISVARIANZACONOCIDA.this);
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




    public void desplegarToastHipotesisNula(){
      LayoutInflater inflater = getLayoutInflater();
      View vista = inflater.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,(ViewGroup) findViewById(R.id.hipotesisnula));
      Toast toast = new Toast(getApplicationContext());
      toast.setView(vista);
      toast.show();
    }
}