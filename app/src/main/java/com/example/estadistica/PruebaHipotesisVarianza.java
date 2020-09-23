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
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class PruebaHipotesisVarianza extends AppCompatActivity {
    private double varPoblacional,varMuestral,significancia,nuevoVarPob,varPoblacional1;
    PHIPVARIANZA hipVarianza;
    private int tamMuestral;
    private double limInf,limSup;
    private String intervalo;
    private ActivityPruebaHipBinding lienzo;
    String ultimoCaso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View view = this.lienzo.getRoot();
        setContentView(view);
        lienzo.cambia1.setText("Varianza muestral");
        lienzo.imageViewCambia1.setImageResource(R.drawable.varianza_muestralchi);
        lienzo.cambia2.setText("Varianza poblacional");
        lienzo.imageCambia.setImageResource(R.drawable.varianza_poblacionalchi);
        lienzo.cambia6.setText("Nueva varianza poblacional");
        lienzo.imCambia6.setImageResource(R.drawable.nueva_var);
        lienzo.linearLayoutDato3.setVisibility(View.GONE);
        lienzo.imageButton2.setImageResource(R.drawable.casoa_varianzachi);
        lienzo.imageButton.setImageResource(R.drawable.casob_varianzachi);
        lienzo.imageButton3.setImageResource(R.drawable.casoc_varianzachi);
        lienzo.imageButton8.setVisibility(View.GONE);
        lienzo.imageButton7.setVisibility(View.VISIBLE);
        lienzo.imageButton7.setImageResource(R.drawable.caso4varianza);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.potenciaPrueba.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE);

        lienzo.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.layoutPotencia.setVisibility(View.GONE);
                if(faltaDato()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                }else{
                    varMuestral = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    varPoblacional = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestral = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                    hipVarianza = new PHIPVARIANZA(significancia,tamMuestral,varMuestral,varPoblacional);
                    limSup = hipVarianza.caso1();
                    lienzo.output.setText("Con un límite superior para la región de aceptación de: " + limSup + "\n\n" + hipVarianza.decision);
                    ultimoCaso = "caso1";
                    lienzo.teorema.setImageResource(R.drawable.teorema1pruebashipotesisvarianzas);
                    lienzo.imagePromedio.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.val1.setText("=" + varMuestral);
                    lienzo.imageMiu.setImageResource(R.drawable.varianza_poblacionalchi);
                    lienzo.val2.setText("=" + varPoblacional);
                    lienzo.val3.setText("=" + significancia);
                    lienzo.tableRowSigma.setVisibility(View.GONE);
                    lienzo.val5.setText("=" + tamMuestral);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: σ ≤ σ0\n\nContra:\n\nHipótesis alterna: σ>σ0\n\nSe hace necesario calcular el límite superior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema:");
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2  = new SpannableString(aux);
                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipotesisVarianza.this);
                            final View vista = imagen_alert1.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipotesisVarianza.this);
                            final View vista1 = imagen_alert.inflate(R.layout.activity_p_r_u_e_b_a_h_i_p_o_t_e_s_i_s_a_l_t_e_r_n_a_t_o_a_s_t,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,60,74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.limsupteorema1pruebashipotesisvarianzas);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.varianza_poblacionalchi);
                    lienzo.tlcDato1TV.setText("=" + varPoblacional);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.tlcDato2TV.setText("=" + varMuestral);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.chienalfa);
                    lienzo.tlcDato3TV.setText("=" + hipVarianza.getValTablas());
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato4TV.setText("=" + tamMuestral);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.GONE);
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Al observar la fórmula podemos percatarnos de que es necesario buscar el valor de X(α) en las tablas de la distribución chi cuadrada, donde:\n\nα = " + significancia+ "\n\nv= n - 1 = " + tamMuestral  +" - 1 = " + (tamMuestral-1) + " grados de libertad. \n\n En éste caso el valor encontrado en las tablas es de:\n X(α) = X("+significancia +") =" + hipVarianza.getValTablas() + "\n\nUna vez obtenido el valor de X(α) nos resta sustituir los valores correspondientes:" );
                    lienzo.limitsuperior.setText("Para el límite superior:");
                    lienzo.zeta2.setText("["+varPoblacional+"/("+ tamMuestral + "- 1)]*" + hipVarianza.getValTablas() + " = " + limSup);
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.GONE);
                    lienzo.liminferior.setVisibility(View.GONE);
                    lienzo.paso2.setText("Una vez que obtubimos el intervalo para la región de no rechazo, que en este caso está comprendido desde:  ");
                    lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite superior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                    lienzo.reglaDecision.setImageResource(R.drawable.teorema1pruebashipotesisvarianzas);
                    if(hipVarianza.decision.contains("se rechaza la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir, se rechaza la hipótesis nula si el valor de la varianza muestral es mayor al límite superior del intervalo para la región de aceptación\n\nComo podemos observar, el valor de la varianza muestral es mayor que el límite superior de la región de no rechazo:\n\n" + varMuestral + ">" + limSup + "\n\nPor lo tanto:\n\n" +hipVarianza.decision);
                    }else if(hipVarianza.decision.contains("no existe evidencia para rechazar la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir, se rechaza la hipótesis nula si el valor de la varianza muestral es mayor al límite superior del intervalo para la región de aceptación\n\nComo podemos observar, el valor de la varianza muestral es menor que el límite superior de la región de no rechazo, por lo cual podemos decir que está dentro de la región de no rechazo:\n\n" + varMuestral + "≤" + limSup + "\n\nPor lo tanto:\n\n" + hipVarianza.decision);
                    }
                    lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
                    lienzo.cambia7.setText("Nueva varianza poblacional: ");
                    lienzo.cambia7.setTextColor(Color.RED);
                    lienzo.imageCambia7.setVisibility(View.GONE);
                    lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                    lienzo.calcularIntervaloConfianza.setText("Calcular potencia de la prueba");
                    lienzo.textView60.setVisibility(View.GONE);
                }
            }
        });

        lienzo.imageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                lienzo.layoutPotencia.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaDato()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                }else{
                    varMuestral = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    varPoblacional = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestral = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                    hipVarianza = new PHIPVARIANZA(significancia,tamMuestral,varMuestral,varPoblacional);
                    intervalo = hipVarianza.caso2();
                    limInf = hipVarianza.getLimiteInferior();
                    limSup = hipVarianza.getLimiteSuperior();
                    lienzo.output.setText("Con un intervalo para la región de no rechazo definido por:\n\n" + intervalo + "\n\n" + hipVarianza.decision);
                    ultimoCaso = "caso2";
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setImageResource(R.drawable.teorema2pruebashipotesisvarianza);
                    lienzo.imagePromedio.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.val1.setText("=" + varMuestral);
                    lienzo.imageMiu.setImageResource(R.drawable.dato_f);
                    lienzo.val2.setText("=" + tamMuestral);
                    lienzo.val3.setText("=" + significancia);
                    lienzo.tableRowSigma.setVisibility(View.VISIBLE);
                    lienzo.desviation.setImageResource(R.drawable.sigmacuadrada);
                    lienzo.val4.setText("=" + varPoblacional);
                    lienzo.tabkeRowN.setVisibility(View.GONE);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: σ = σ0\n\nContra:\n\nHipótesis alterna: σ ≠ σ0\n\nSe hace necesario calcular el intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del teorema presentado anteriormente.\n\nSi observamos el teorema, nos damos cuenta de que es necesario buscar los valores de X(σ/2) y X[1-(σ/2)]. Donde:\n\nα=" + significancia + "\n\nα/2 = " +hipVarianza.redondeoDecimales((significancia/2),5)+"\n\n1-(α/2) = " +hipVarianza.redondeoDecimales((1-(significancia/2)),5) + "\n\nv = n - 1 = "+ tamMuestral +" -1 = "+ (tamMuestral-1) +" grados de libertad\n\nBuscando el valor en tablas encontramos que:\nX(α/2)=X("+hipVarianza.redondeoDecimales((significancia/2),5)+") = "+ hipVarianza.getValTablas() +"\nX[1-(α/2)] = X("+ hipVarianza.redondeoDecimales((1-(significancia/2)),5) + ") = " + hipVarianza.getValTablas1());
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2  = new SpannableString(aux);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipotesisVarianza.this);
                            final View vista = imagen_alert1.inflate(R.layout.activityhipotesisnula2,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipotesisVarianza.this);
                            final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna2,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.textView25.setText("Una vez encontrados los valores respectivos en las tablas de la distribución chi-cuadrada es necesario sustituir los valores correspondientes en el teorema presentado anteriormente:");
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    lienzo.calcOp1.setImageResource(R.drawable.liminfteorema2pruebashipotesisvarianza);
                    lienzo.calculo1.setText("Para el límite inferior:");
                    lienzo.calcOp2.setImageResource(R.drawable.limsup);
                    lienzo.calculo2.setText("Para el límite superior:");
                    lienzo.textView15.setText("[("+varPoblacional+"/"+(tamMuestral-1)+")*"+hipVarianza.getValTablas1()+"]");
                    lienzo.zeta2.setText("[("+varPoblacional+"/"+(tamMuestral-1)+")*"+hipVarianza.getValTablas()+"]");
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.liminferior.setVisibility(View.VISIBLE);
                    lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el intervalo de la región de aceptación:\n\n" +intervalo +"\n\nPodemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                    lienzo.reglaDecision.setImageResource(R.drawable.teorema2pruebashipotesisvarianza);
                    if(hipVarianza.decision.contains("se rechaza la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor de la varianza muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro varianza muestral se encuentra fuera del intervalo de la regiòn de no rechazo :\n\n" + varMuestral + "<" + limInf + " ò " + varMuestral + ">" +  limSup + "\n\nPor lo tanto: \n\n" + hipVarianza.decision);
                    }else if(hipVarianza.decision.contains("no existe evidencia para rechazar la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor de la varianza muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro varianza muestral se encuentra dentro del intervalo de la regiòn de no rechazo :\n\n" +limInf + "≤" + varMuestral + "≤" + limSup + "\n\nPor lo tanto: \n\n" + hipVarianza.decision);
                    }
                    lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                    String aux2 = lienzo.paso2Phipodc.getText().toString();
                    SpannableString ss3 = new SpannableString(aux2);


                    ClickableSpan clickableSpan3 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipotesisVarianza.this);
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
                    lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                    lienzo.calcularIntervaloConfianza.setText("Calcular potencia de la prueba");

                }
            }
        });

        lienzo.imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDato()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                }else{
                    varMuestral = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    varPoblacional = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestral = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                    hipVarianza = new PHIPVARIANZA(significancia,tamMuestral,varMuestral,varPoblacional);
                    limInf = hipVarianza.caso3();
                    lienzo.output.setText("Con un límite inferior para la región de aceptación de: " + limInf + "\n\n" + hipVarianza.decision);
                    ultimoCaso = "caso3";
                    lienzo.teorema.setImageResource(R.drawable.teorema3pruebashipotesisvarianza);
                    lienzo.imagePromedio.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.val1.setText("=" + varMuestral);
                    lienzo.imageMiu.setImageResource(R.drawable.varianza_poblacionalchi);
                    lienzo.val2.setText("=" + varPoblacional);
                    lienzo.val3.setText("=" + significancia);
                    lienzo.tableRowSigma.setVisibility(View.GONE);
                    lienzo.val5.setText("=" + tamMuestral);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: σ ≥ σ0\n\nContra:\n\nHipótesis alterna: σ<σ0\n\nSe hace necesario calcular el límite superior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema:");
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2  = new SpannableString(aux);
                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipotesisVarianza.this);
                            final View vista = imagen_alert1.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipotesisVarianza.this);
                            final View vista1 = imagen_alert.inflate(R.layout.activity_p_r_u_e_b_a_h_i_p_o_t_e_s_i_s_a_l_t_e_r_n_a_t_o_a_s_t,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,60,74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.liminfteorema3pruebashipotesisvarianza);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.varianza_poblacionalchi);
                    lienzo.tlcDato1TV.setText("=" + varPoblacional);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.tlcDato2TV.setText("=" + varMuestral);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.chienalfa);
                    lienzo.tlcDato3TV.setText("=" + hipVarianza.getValTablas());
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato4TV.setText("=" + tamMuestral);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.GONE);
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Al observar la fórmula podemos percatarnos de que es necesario buscar el valor de X(1-α) en las tablas de la distribución chi cuadrada, donde:\n\nα = " + significancia+ "\n\n1-α = "+ hipVarianza.redondeoDecimales((1-significancia),4) + "\n\nv= n - 1 = " + tamMuestral  +" - 1 = " + (tamMuestral-1) + " grados de libertad. \n\n En éste caso el valor encontrado en las tablas es de:\n\n X(1-α) = X("+hipVarianza.redondeoDecimales((1-significancia),4) +") =" + hipVarianza.getValTablas() + "\n\nUna vez obtenido el valor de X(1-α) nos resta sustituir los valores correspondientes:" );
                    lienzo.liminferior.setText("Para el límite imferior:");
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.liminferior.setVisibility(View.VISIBLE);
                    lienzo.textView15.setText("["+varPoblacional+"/("+ tamMuestral + "- 1)]*" + hipVarianza.getValTablas() + " = " + limInf);
                    lienzo.paso2.setText("Una vez que obtubimos el intervalo para la región de no rechazo, que en este caso está comprendido desde:  ");
                    lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite inferior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                    lienzo.reglaDecision.setImageResource(R.drawable.teorema3pruebashipotesisvarianza);
                    if(hipVarianza.decision.contains("se rechaza la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir, se rechaza la hipótesis nula si el valor de la varianza muestral es menor al límite inferior del intervalo para la región de aceptación\n\nComo podemos observar, el valor de la varianza muestral es menor que el límite inferior de la región de no rechazo:\n\n" + varMuestral + "<" + limInf + "\n\nPor lo tanto:\n\n" +hipVarianza.decision);
                    }else if(hipVarianza.decision.contains("no existe evidencia para rechazar la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir, se rechaza la hipótesis nula si el valor de la varianza muestral es menor al límite inferior del intervalo para la región de aceptación\n\nComo podemos observar, el valor de la varianza muestral es mayor o igual que el límite inferior de la región de no rechazo, por lo cual podemos decir que está dentro de la región de no rechazo:\n\n" + varMuestral + " ≥ " + limInf + "\n\nPor lo tanto:\n\n" + hipVarianza.decision);
                    }
                    lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
                    lienzo.cambia7.setText("Nueva varianza poblacional: ");
                    lienzo.cambia7.setTextColor(Color.RED);
                    lienzo.imageCambia7.setVisibility(View.GONE);
                    lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                    lienzo.calcularIntervaloConfianza.setText("Calcular potencia de la prueba");
                    lienzo.textView60.setVisibility(View.GONE);
                }
            }
        });

        lienzo.imageButton7.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                if(faltanDatos()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa todos los datos requeridos");
                    }else{
                        varMuestral = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                        varPoblacional = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                        varPoblacional1 = Double.parseDouble(lienzo.miu1.getText().toString());
                        tamMuestral = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                        significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                        hipVarianza = new PHIPVARIANZA(significancia,tamMuestral,varMuestral,varPoblacional,varPoblacional1);
                        intervalo = hipVarianza.caso4();
                        limInf = hipVarianza.getLimiteInferior();
                        limSup = hipVarianza.getLimiteSuperior();
                        lienzo.output.setText("Con un intervalo para la región de no rechazo de:\n\n" + intervalo + "\n\n" + hipVarianza.decision);
                        ultimoCaso = "caso4";
                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.teorema4pruebashipotesisvarianza);
                        lienzo.imagePromedio.setImageResource(R.drawable.varianza_muestralchi);
                        lienzo.val1.setText("=" + varMuestral);
                        lienzo.imageMiu.setImageResource(R.drawable.dato_f);
                        lienzo.val2.setText("=" + tamMuestral);
                        lienzo.val3.setText("=" + significancia);
                        lienzo.tableRowSigma.setVisibility(View.VISIBLE);
                        lienzo.desviation.setImageResource(R.drawable.sigmacuadrada);
                        lienzo.val4.setText("=" + varPoblacional);
                        lienzo.val5.setText("=" + varPoblacional1);
                        lienzo.imageN.setImageResource(R.drawable.sigmacuadrada1);
                        lienzo.tabkeRowN.setVisibility(View.VISIBLE);
                        lienzo.dtoOpcional.setVisibility(View.GONE);
                        lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: σ0≤σ≤σ1\n\nContra:\n\nHipótesis alterna: σ<σ0  ó  σ>σ1\n\nSe hace necesario calcular el intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del teorema presentado anteriormente.\n\nSi observamos el teorema, nos damos cuenta de que es necesario buscar los valores de X(σ/2) y X[1-(σ/2)]. Donde:\n\nα=" + significancia + "\n\nα/2 = " +hipVarianza.redondeoDecimales((significancia/2),5)+"\n\n1-(α/2) = " +hipVarianza.redondeoDecimales((1-(significancia/2)),5) + "\n\nv = n - 1 = "+ tamMuestral +" -1 = "+ (tamMuestral-1) +" grados de libertad\n\nBuscando el valor en tablas encontramos que:\nX(α/2)=X("+hipVarianza.redondeoDecimales((significancia/2),5)+") = "+ hipVarianza.getValTablas() +"\nX[1-(α/2)] = X("+ hipVarianza.redondeoDecimales((1-(significancia/2)),5) + ") = " + hipVarianza.getValTablas1());
                        lienzo.layCalc1.setVisibility(View.VISIBLE);
                        lienzo.layCalc2.setVisibility(View.VISIBLE);
                        String aux = lienzo.paso1Phip.getText().toString();
                        SpannableString ss2  = new SpannableString(aux);


                        ClickableSpan clickableSpan2 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipotesisVarianza.this);
                                final View vista = imagen_alert1.inflate(R.layout.activityhipotesisnula2,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };


                        ClickableSpan clickableSpan1 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region1 =  new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                                LayoutInflater imagen_alert = LayoutInflater.from(PruebaHipotesisVarianza.this);
                                final View vista1 = imagen_alert.inflate(R.layout.hipotesisalterna2,null);
                                glosario2.setView(vista1);
                                glosario2.show();
                            }
                        };
                        ss2.setSpan(clickableSpan2,60,74,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso1Phip.setText(ss2);
                        lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                        lienzo.textView25.setText("Una vez encontrados los valores respectivos en las tablas de la distribución chi-cuadrada es necesario sustituir los valores correspondientes en el teorema presentado anteriormente:");
                        lienzo.layCalc1.setVisibility(View.VISIBLE);
                        lienzo.layCalc2.setVisibility(View.VISIBLE);
                        lienzo.calcOp1.setImageResource(R.drawable.liminfteorema4pruebashipotesisvarianza);
                        lienzo.calculo1.setText("Para el límite inferior:");
                        lienzo.calcOp2.setImageResource(R.drawable.limsupteorema4pruebashipotesisvarianza);
                        lienzo.calculo2.setText("Para el límite superior:");
                        lienzo.textView15.setText("[("+varPoblacional+"/"+(tamMuestral-1)+")*"+hipVarianza.getValTablas1()+"] = " + limInf);
                        lienzo.zeta2.setText("[("+varPoblacional1+"/"+(tamMuestral-1)+")*"+hipVarianza.getValTablas()+"] = " + limSup);
                        lienzo.limitsuperior.setVisibility(View.VISIBLE);
                        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                        lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                        lienzo.liminferior.setVisibility(View.VISIBLE);
                        lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el intervalo de la región de aceptación:\n\n" +intervalo +"\n\nPodemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                        lienzo.reglaDecision.setImageResource(R.drawable.teorema4pruebashipotesisvarianza);
                        if(hipVarianza.decision.contains("se rechaza la hipotesis nula")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor de la varianza muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro varianza muestral se encuentra fuera del intervalo de la región de no rechazo :\n\n" + varMuestral + "<" + limInf + " ó " + varMuestral + ">" +  limSup + "\n\nPor lo tanto: \n\n" + hipVarianza.decision);
                        }else if(hipVarianza.decision.contains("no existe evidencia para rechazar la hipotesis nula")){
                            lienzo.paso2Phipodc.setText("Es decir rechazar H0 si el valor de la varianza muestral está fuera del intervalo de el area de aceptación.\n\nVer gráficamente\n\nComo podemos observar en los resultados obtenidos de los calculos realizados anteriormente, el parametro varianza muestral se encuentra dentro del intervalo de la región de no rechazo :\n\n" +limInf + "≤" + varMuestral + "≤" + limSup + "\n\nPor lo tanto: \n\n" + hipVarianza.decision);
                        }
                        lienzo.paso2Phipodc.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                        String aux2 = lienzo.paso2Phipodc.getText().toString();
                        SpannableString ss3 = new SpannableString(aux2);


                        ClickableSpan clickableSpan3 = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View view) {
                                AlertDialog.Builder region = new AlertDialog.Builder(PruebaHipotesisVarianza.this);
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
                                LayoutInflater imagen_alert1 = LayoutInflater.from(PruebaHipotesisVarianza.this);
                                final View vista = imagen_alert1.inflate(R.layout.grafica_gauss_pruebahip1casoc,null);
                                glosario1.setView(vista);
                                glosario1.show();
                            }
                        };
                        ss3.setSpan(clickableSpan3,107,123,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        lienzo.paso2Phipodc.setText(ss3);
                        lienzo.paso2Phipodc.setMovementMethod(LinkMovementMethod.getInstance());

                        lienzo.LinearLayoutDato7Opcional.setVisibility(View.GONE);
                        lienzo.cambia7.setText("Nueva media poblacional");
                        lienzo.imageCambia7.setVisibility(View.GONE);
                        lienzo.cambia7.setTextColor(Color.RED);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.GONE);
                    /*lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                    lienzo.calcularIntervaloConfianza.setText("Calcular potencia de la prueba");*/
                    }

            }
        });

        lienzo.verProcedimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.VISIBLE);
            }
        });



        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaVarianza()){
                    lienzo.cambia7.setTextColor(Color.RED);
                }else{
                    lienzo.layoutPotencia.setVisibility(View.VISIBLE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    nuevoVarPob = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    int gradosLib = tamMuestral -1;
                    double potP;
                    if(ultimoCaso.equals("caso1")){
                        potP = hipVarianza.potenciaPrueba(limSup,nuevoVarPob);
                        lienzo.output.setText("La potencia de la prueba calculada es: " + potP);
                        lienzo.caso1.setText("Para calcular la potencia de la prueba en el caso de pruebas de hipótesis para la varianza se hace necesario primero calcular el valor de la estadística de prueba X que posteriormente buscaremos en las tablas de la distribución chi-cuadrada. Para realizar el cálculo nos podemos apoyar en la siguiente fórmula:");
                        lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                        lienzo.imagenPotencia.setImageResource(R.drawable.estadisticox);
                        lienzo.val1EstadisticoZ.setImageResource(R.drawable.dato_f);
                        lienzo.textoVal1Estadisticoz.setText("=" + tamMuestral);
                        lienzo.val2EstadisticoZ.setImageResource(R.drawable.varianza_muestralchi);
                        lienzo.textoVal2Estadisticoz.setText("=" + limSup);
                        lienzo.val3EstadisticoZ.setImageResource(R.drawable.sigmacuadrada);
                        lienzo.textoVal3Estadisticoz.setText("=" + nuevoVarPob);
                        lienzo.tbrow4.setVisibility(View.GONE);
                        lienzo.paso2PotPrueba.setText("Sustituyendo los valores correspondientes en la fórmula presentada anteriormente obtenemos un resultado de: " + hipVarianza.estadisticoChi + "\n\nPosteriormente, como se mencionó en el primer paso, se hace necesario buscar el valor calculado en las tablas de la distribución chi cuadrada con \n\nv= n-1 = " + tamMuestral + "- 1 = " + (tamMuestral-1) +" grados de libertad\n\nEn éste caso la probabilidad encontrada en tablas es: " + potP + "\n\nPor lo cual la potencia de la prueba es igual a : " + potP );
                        if(potP<0.75){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " la potencia de la prueba es pesima : " + potP);
                        }else if(potP>=0.75 && potP<0.85){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " la potencia de la prueba es aceptable: " + potP);
                        }else if(potP>= 0.85){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " en estas condiciones no hay prueba más potente: " + potP);
                        }
                    }else if(ultimoCaso.equals("caso2")){
                        double potResult;
                        potP = hipVarianza.potenciaPrueba(limInf,nuevoVarPob);
                        double potP1 = hipVarianza.potenciaPrueba(limSup,nuevoVarPob);
                        potResult = (1-potP) + potP1;
                        lienzo.output.setText("La potencia de la prueba calculada es: " + potResult);
                        lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                        lienzo.caso1.setText("Para calcular la potencia de la prueba en el caso de pruebas de hipótesis para la varianza se hace necesario primero calcular el valor de la estadística de prueba X que posteriormente buscaremos en las tablas de la distribución chi-cuadrada. Para realizar el cálculo nos podemos apoyar en la siguiente fórmula:");
                        lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                        lienzo.imagenPotencia.setImageResource(R.drawable.estadisticox);
                        lienzo.val1EstadisticoZ.setImageResource(R.drawable.dato_f);
                        lienzo.textoVal1Estadisticoz.setText("=" + tamMuestral);
                        lienzo.val2EstadisticoZ.setImageResource(R.drawable.varianza_muestralchi);
                        lienzo.textoVal2Estadisticoz.setText("=" + limSup);
                        lienzo.val3EstadisticoZ.setImageResource(R.drawable.sigmacuadrada);
                        lienzo.textoVal3Estadisticoz.setText("=" + nuevoVarPob);
                        lienzo.tbrow4.setVisibility(View.GONE);
                        if(potResult< 0.7 ){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " la potencia de la prueba es pesima: " +potResult );
                        }else if(potResult>=0.70 && potResult<0.85){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " la potencia de la prueba es aceptable:  " + potResult);
                        }else if(potResult>= 0.85){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " en estas condiciones no hay prueba más potente: " + potResult );
                        }
                    } else if(ultimoCaso.equals("caso3")){
                        potP = hipVarianza.redondeoDecimales((1-hipVarianza.potenciaPrueba(limInf,nuevoVarPob)),5);
                        lienzo.output.setText("La potencia de la prueba calculada es: " + potP);
                        lienzo.caso1.setText("Para calcular la potencia de la prueba en el caso de pruebas de hipótesis para la varianza se hace necesario primero calcular el valor de la estadística de prueba X que posteriormente buscaremos en las tablas de la distribución chi-cuadrada. Para realizar el cálculo nos podemos apoyar en la siguiente fórmula:");
                        lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                        lienzo.imagenPotencia.setImageResource(R.drawable.estadisticox);
                        lienzo.val1EstadisticoZ.setImageResource(R.drawable.dato_f);
                        lienzo.textoVal1Estadisticoz.setText("=" + tamMuestral);
                        lienzo.val2EstadisticoZ.setImageResource(R.drawable.varianza_muestralchi);
                        lienzo.textoVal2Estadisticoz.setText("=" + limInf);
                        lienzo.val3EstadisticoZ.setImageResource(R.drawable.sigmacuadrada);
                        lienzo.textoVal3Estadisticoz.setText("=" + nuevoVarPob);
                        lienzo.tbrow4.setVisibility(View.GONE);
                        lienzo.paso2PotPrueba.setText("Sustituyendo los valores correspondientes en la fórmula presentada anteriormente obtenemos un resultado de: " + hipVarianza.estadisticoChi + "\n\nPosteriormente, como se mencionó en el primer paso, se hace necesario buscar el valor calculado en las tablas de la distribución chi cuadrada con \n\nv= n-1 = " + tamMuestral + "- 1 = " + (tamMuestral-1) +" grados de libertad\n\nEn éste caso la probabilidad encontrada en tablas es: " + potP + "\n\nPor lo cual la potencia de la prueba es igual a : " + potP );

                        if(potP<0.75){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " la potencia de la prueba es pesima: " + potP);
                        }else if(potP>=0.75 && potP<0.85){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " la potencia de la prueba es aceptable:  " + potP);
                        }else if(potP>= 0.85){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " en estas condiciones no hay prueba más potente: "+potP);
                        }
                    }else if(ultimoCaso.equals("caso4")){
                        potP = hipVarianza.potenciaPrueba(limInf,nuevoVarPob);
                        double potP1 = hipVarianza.potenciaPrueba(limSup,nuevoVarPob);
                        double potResult = potP + (1-potP1);
                        if(potResult< 0.75 ){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " la potencia de la prueba es pesima: " +potResult );
                        }else if(potResult>=0.75 && potResult<0.85){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " la potencia de la prueba es aceptable:  " + potResult);
                        }else if(potResult>= 0.85){
                            lienzo.resultadoPotPrueba.setText("Si la verdadera varianza es " + nuevoVarPob + " en estas condiciones no hay prueba más potente: " + potResult );
                        }
                    }
                }
            }
        });


    }


    public boolean faltaDato(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()){
            return true;
        }else return false;
    }

    public boolean faltanDatos(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()){
            return true;
        }else return false;
    }

    public boolean faltaVarianza(){
        if(lienzo.editTextCambia7.getText().toString().isEmpty()){
            return true;
        }else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }


}