package com.example.estadistica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS extends AppCompatActivity {

    private ActivityPruebaHipBinding lienzo;
    private PruebaHipotesisDifMediasVarConocidas teorema;
    private double diferenciaMedias,diferenciaPromedios,varianza1,varianza2,significancia,diferenciaMedias1,nuevaDiferenciaMedias;
    private int tamMuestra1,tamMuestra2;

    private String ultimoCaso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        lienzo.imageButton2.setImageResource(R.drawable.caso1pruebashipotesisdiferenciamediasvarconocidas);
        lienzo.imageButton.setImageResource(R.drawable.caso2pruebashipotesisdiferenciamediasvarconocidas);
        lienzo.imageButton3.setImageResource(R.drawable.caso3pruebashipotesisdiferenciamediasvarconocidas);
        lienzo.imageButton7.setImageResource(R.drawable.caso4pruebashipotesisdiferenciamediasvarconocidas);
        lienzo.cambia1.setText("Diferencia de medias:");
        lienzo.imageViewCambia1.setImageResource(R.drawable.d0);
        lienzo.cambia2.setText("Varianza poblacional 1:");
        lienzo.imageCambia.setImageResource(R.drawable.tlcvarianza1);
        lienzo.cambia3.setText("Varianza poblacional 2:");
        lienzo.imageCambia2.setImageResource(R.drawable.tlcvarianza2);
        lienzo.cambia4.setText("Tamaño muestral 1:");
        lienzo.imageCambia3.setImageResource(R.drawable.tlcn1);
        lienzo.cambia5.setText("Tamaño muestral 2:");
        lienzo.imageCambia4.setImageResource(R.drawable.tlcn2);
        lienzo.cambia6.setText("Diferencia de promedios muestrales:");
        lienzo.imCambia6.setImageResource(R.drawable.difpromedios);
        lienzo.cambia7.setText("Significancia:");
        lienzo.imageCambia7.setImageResource(R.drawable.alfa);
        lienzo.cambia8.setText("Diferencia de medias 1:");
        lienzo.imageCambia8.setImageResource(R.drawable.d1);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.VISIBLE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.layoutButtones.setVisibility(View.VISIBLE);
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);

        lienzo.imageButton24.setOnClickListener(new View.OnClickListener() {
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
                lienzo.layoutPotencia.setVisibility(View.GONE);
                lienzo.LinearLayoutDato8Opcional.setVisibility(View.GONE);
                if(faltanDatos()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa los datos correspondientes");
                }else{
                    diferenciaMedias = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    varianza1 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    tamMuestra2 = Integer.parseInt(lienzo.significancia.getText().toString());
                    diferenciaPromedios = Double.parseDouble(lienzo.miu1.getText().toString());
                    significancia = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    teorema = new PruebaHipotesisDifMediasVarConocidas(diferenciaMedias,varianza1,varianza2,tamMuestra1,tamMuestra2,significancia,diferenciaPromedios);
                    lienzo.output.setText("Con un intervalo para la región de no rechazo comprendido desde -∞ hasta " + teorema.caso1() + "\n\n" + teorema.getDecision());
                    lienzo.teorema.setImageResource(R.drawable.teorema1pruebashipotesisdifmediasvarconocidas);
                    lienzo.imagePromedio.setImageResource(R.drawable.d0);
                    lienzo.val1.setText("=" + diferenciaMedias);
                    lienzo.imageMiu.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.val2.setText("=" + varianza1);
                    lienzo.imageSignificancia.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.val3.setText("=" + varianza2);
                    lienzo.desviation.setImageResource(R.drawable.tlcn1);
                    lienzo.val4.setText("=" +tamMuestra1);
                    lienzo.imageN.setImageResource(R.drawable.tlcn2);
                    lienzo.val5.setText("=" + tamMuestra2);
                    lienzo.imageDato6.setImageResource(R.drawable.difpromedios);
                    lienzo.val6.setText("=" + diferenciaPromedios);
                    lienzo.imageDato7.setImageResource(R.drawable.alfa);
                    lienzo.val7.setText("=" + significancia);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ1-µ2 ≤ d0\n\nContra:\n\nHipótesis alterna: µ1-µ2>d0\n\nSe hace necesario calcular el límite superior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema:");
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2  = new SpannableString(aux);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
                            final View vista = imagen_alert1.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
                            final View vista1 = imagen_alert.inflate(R.layout.activity_p_r_u_e_b_a_h_i_p_o_t_e_s_i_s_a_l_t_e_r_n_a_t_o_a_s_t,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,60,74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());

                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.limsupteorema1pruebashipotesisdifmediasvarconocidas);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.d0);
                    lienzo.tlcDato1TV.setText("=" + diferenciaMedias);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.tlcDato2TV.setText("=" + varianza1);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.tlcDato3TV.setText("=" + varianza2);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tlcn1);
                    lienzo.tlcDato4TV.setText("=" + tamMuestra1);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcDato5TV.setText("=" + tamMuestra2);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.difpromedios);
                    lienzo.tlcDato6TV.setText("=" + diferenciaPromedios);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.alfa);
                    lienzo.tlcDato7TV.setText("=" + significancia);

                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Al observar el teorema presentado anteriormente nos percatamos de que es necesario buscar el valor de Z(α) en las tablas de la distribución normal estándar, donde:\n\nα= " + significancia + "\n\nEn este caso el valor encontrado en las tablas de la distribución normal estándar es de:\nZ(α) = Z(" + significancia + ") = " + teorema.getValTablas() + "\n\nUna vez obtenido el valor de Z(α) nos resta sustituir los valores correspondientes :");
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.liminferior.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.limitsuperior.setText("Para el límite superior:");
                    lienzo.zeta2.setText(diferenciaMedias+"+["+teorema.getValTablas()+"*√(("+varianza1+"/"+tamMuestra1+")+("+varianza2+"/"+tamMuestra2+")) = " + teorema.getLimSup());
                    lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite superior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                    lienzo.reglaDecision.setImageResource(R.drawable.teorema1pruebashipotesisdifmediasvarconocidas);
                    if(teorema.getDecision().contains("se rechaza la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir, se rechaza la hipótesis nula si el valor de la diferencia de promedios muestrales es mayor al límite superior del intervalo para la región de aceptación.\n\nComo se puede observar en los cálculos realizados anteriomente, el valor de la diferencia de promedios muestrales es mayor que el límite superior de la región de no rechazo:\n\n"+diferenciaPromedios+">"+teorema.getLimSup()+"\n\nPor lo tanto:\n\n"+teorema.getDecision());
                    }else if(teorema.getDecision().contains("se acepta la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir, se rechaza la hipótesis nula si el valor de la diferencia de promedios muestrales es mayor al límite superior del intervalo para la región de aceptación.\n\nComo se puede observar en los cálculos realizados anteriomente, el valor de la diferencia de promedios muestrales es menor o igual que el límite superior de la región de no rechazo:\n\n"+diferenciaPromedios+"≤" + teorema.getLimSup()+"\n\nPor lo tanto:\n\n" + teorema.getDecision());
                    }

                    ultimoCaso = "Caso1";
                    lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
                    lienzo.calcularIntervaloConfianza.setText("Calcular potencia de la prueba");
                    lienzo.LinearLayoutDato9Opcional.setVisibility(View.VISIBLE);
                    lienzo.cambia9.setText("Nueva diferencia de medias:");
                    lienzo.imageCambia9.setVisibility(View.GONE);
                }
            }
        });


        lienzo.imageButton28.setOnClickListener(new View.OnClickListener() {
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
                lienzo.layoutPotencia.setVisibility(View.GONE);
                lienzo.LinearLayoutDato8Opcional.setVisibility(View.GONE);
                if(faltanDatos()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa los datos correspondientes");
                }else{
                    diferenciaMedias = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    varianza1 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    tamMuestra2 = Integer.parseInt(lienzo.significancia.getText().toString());
                    diferenciaPromedios = Double.parseDouble(lienzo.miu1.getText().toString());
                    significancia = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    teorema = new PruebaHipotesisDifMediasVarConocidas(diferenciaMedias,varianza1,varianza2,tamMuestra1,tamMuestra2,significancia,diferenciaPromedios);
                    lienzo.output.setText("Con un intervalo para la región de no rechazo comprendido desde " + teorema.caso3() + " hasta ∞\n\n" + teorema.getDecision());
                    lienzo.teorema.setImageResource(R.drawable.teorema2pruebashipotesisdifmediasvarconocidas);
                    lienzo.imagePromedio.setImageResource(R.drawable.d0);
                    lienzo.val1.setText("=" + diferenciaMedias);
                    lienzo.imageMiu.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.val2.setText("=" + varianza1);
                    lienzo.imageSignificancia.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.val3.setText("=" + varianza2);
                    lienzo.desviation.setImageResource(R.drawable.tlcn1);
                    lienzo.val4.setText("=" +tamMuestra1);
                    lienzo.imageN.setImageResource(R.drawable.tlcn2);
                    lienzo.val5.setText("=" + tamMuestra2);
                    lienzo.imageDato6.setImageResource(R.drawable.difpromedios);
                    lienzo.val6.setText("=" + diferenciaPromedios);
                    lienzo.imageDato7.setImageResource(R.drawable.alfa);
                    lienzo.val7.setText("=" + significancia);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ1-µ2 ≥ d0\n\nContra:\n\nHipótesis alterna: µ1-µ2 < d0\n\nSe hace necesario calcular el límite inferior del intervalo para la región de aceptación.\nPara realizar el cálculo nos apoyaremos del siguiente teorema:");
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2  = new SpannableString(aux);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
                            final View vista = imagen_alert1.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
                            final View vista1 = imagen_alert.inflate(R.layout.activity_p_r_u_e_b_a_h_i_p_o_t_e_s_i_s_a_l_t_e_r_n_a_t_o_a_s_t,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,60,74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());

                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.liminfteorema2pruebashipotesisdifmediasvarconocidas);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.d0);
                    lienzo.tlcDato1TV.setText("=" + diferenciaMedias);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.tlcDato2TV.setText("=" + varianza1);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.tlcDato3TV.setText("=" + varianza2);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tlcn1);
                    lienzo.tlcDato4TV.setText("=" + tamMuestra1);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcDato5TV.setText("=" + tamMuestra2);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.difpromedios);
                    lienzo.tlcDato6TV.setText("=" + diferenciaPromedios);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.alfa);
                    lienzo.tlcDato7TV.setText("=" + significancia);

                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Al observar el teorema presentado anteriormente nos percatamos de que es necesario buscar el valor de Z(α) en las tablas de la distribución normal estándar, donde:\n\nα= " + significancia + "\n\nEn este caso el valor encontrado en las tablas de la distribución normal estándar es de:\nZ(α) = Z(" + significancia + ") = " + teorema.getValTablas() + "\n\nUna vez obtenido el valor de Z(α) nos resta sustituir los valores correspondientes :");
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.liminferior.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Para el límite inferior:");
                    lienzo.textView15.setText(diferenciaMedias+"-["+teorema.getValTablas()+"*√(("+varianza1+"/"+tamMuestra1+")+("+varianza2+"/"+tamMuestra2+")) = " + teorema.getLimInf());
                    lienzo.paso2.setText("Paso 2:\n\nAhora que conocemos el límite inferior de la región de aceptación. Podemos proponer una regla de decisión para posteriormente, definir si la afirmación dada es válida o no:");
                    lienzo.reglaDecision.setImageResource(R.drawable.teorema2pruebashipotesisdifmediasvarconocidas);
                    if(teorema.getDecision().contains("se rechaza la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir, se rechaza la hipótesis nula si el valor de la diferencia de promedios muestrales es menor al límite inferior del intervalo para la región de aceptación.\n\nComo se puede observar en los cálculos realizados anteriomente, el valor de la diferencia de promedios muestrales es menor que el límite inferior de la región de no rechazo:\n\n"+diferenciaPromedios+"<"+teorema.getLimInf()+"\n\nPor lo tanto:\n\n"+teorema.getDecision());
                    }else if(teorema.getDecision().contains("se acepta la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir, se rechaza la hipótesis nula si el valor de la diferencia de promedios muestrales es menor al límite inferior del intervalo para la región de aceptación.\n\nComo se puede observar en los cálculos realizados anteriomente, el valor de la diferencia de promedios muestrales es mayor o igual que el límite inferior de la región de no rechazo\n\n" + diferenciaPromedios + "≥" + teorema.getLimInf()+"\n\nPor lo tanto:\n\n"+ teorema.getDecision());
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
                lienzo.cambia1.setTextColor(Color.RED);
                lienzo.cambia2.setTextColor(Color.RED);
                lienzo.cambia3.setTextColor(Color.RED);
                lienzo.cambia4.setTextColor(Color.RED);
                lienzo.cambia5.setTextColor(Color.RED);
                lienzo.cambia6.setTextColor(Color.RED);
                lienzo.cambia7.setTextColor(Color.RED);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.layoutPotencia.setVisibility(View.GONE);
                lienzo.LinearLayoutDato8Opcional.setVisibility(View.GONE);
                lienzo.output.setText("");
                lienzo.mediaPoblacional.setText("");
                lienzo.desviacionEstandarPob.setText("");
                lienzo.promedioMuestral.setText("");
                lienzo.tamMuestral.setText("");
                lienzo.significancia.setText("");
                lienzo.miu1.setText("");
                lienzo.editTextCambia7.setText("");
            }
        });


        lienzo.imageButton26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltanDatos()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa los datos correspondientes");
                }else{
                    diferenciaMedias = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    varianza1 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    tamMuestra2 = Integer.parseInt(lienzo.significancia.getText().toString());
                    diferenciaPromedios = Double.parseDouble(lienzo.miu1.getText().toString());
                    significancia = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    teorema = new PruebaHipotesisDifMediasVarConocidas(diferenciaMedias,varianza1,varianza2,tamMuestra1,tamMuestra2,significancia,diferenciaPromedios);
                    lienzo.output.setText("Con un intervalo para la región de aceptación igual a:\n\n"+teorema.caso2()+"\n\n"+teorema.getDecision());
                    lienzo.teorema.setImageResource(R.drawable.teorema3pruebashipotesisdiferenciamediasvarconocida);
                    lienzo.imagePromedio.setImageResource(R.drawable.d0);
                    lienzo.val1.setText("=" + diferenciaMedias);
                    lienzo.imageMiu.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.val2.setText("=" + varianza1);
                    lienzo.imageSignificancia.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.val3.setText("=" + varianza2);
                    lienzo.desviation.setImageResource(R.drawable.tlcn1);
                    lienzo.val4.setText("=" + tamMuestra1);
                    lienzo.imageN.setImageResource(R.drawable.tlcn2);
                    lienzo.val5.setText("=" + tamMuestra2);
                    lienzo.imageDato6.setImageResource(R.drawable.alfa);
                    lienzo.val6.setText("=" + significancia);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.imageDato7.setImageResource(R.drawable.difpromedios);
                    lienzo.val7.setText("= " + diferenciaPromedios);
                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: µ1-µ2 = d0\n\nContra:\n\nHipótesis alterna: µ1-µ2 ≠ d0\n\nSe hace necesario calcular el intervalo para la región de no rechazo, para ello podemos apoyarnos en el teorema presentado anteriormente:");
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2  = new SpannableString(aux);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
                            final View vista = imagen_alert1.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
                            final View vista1 = imagen_alert.inflate(R.layout.activity_p_r_u_e_b_a_h_i_p_o_t_e_s_i_s_a_l_t_e_r_n_a_t_o_a_s_t,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,60,74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);


                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.calculo1.setText("Para el límite inferior:");
                    lienzo.calcOp1.setImageResource(R.drawable.liminfteorema3pruebashipotesisdiferenciamediasvarconocida);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    lienzo.calcOp2.setImageResource(R.drawable.limsupteorema3pruebashipotesisdiferenciamediasvarconocida);
                    lienzo.calculo2.setText("Para el límite superior:");
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Analizando el teorema presentado al inicio del procedimiento podemos percatarnos de que se hace necesario buscar el valor de Z(α/2) en las tablas de la distribución normal estándar, donde:\n\nα = " + significancia + "\n\nα/2 = " + teorema.redondeoDecimales((significancia/2),4) + "\n\nEn éste caso el valor encontrado en las tablas de la distribución normal estándar es:\n\nZ(α/2) = Z(" + teorema.redondeoDecimales((significancia/2),4) + ") = " + teorema.getValTablas()+"\n\nPosterior a esto se sustituyen los valores correspondientes en las siguientes fórmulas:");
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.liminferior.setText("Para el límite inferior:");
                    lienzo.textView15.setText(diferenciaMedias+" - ["+teorema.getValTablas()+"*√(("+varianza1+"/"+tamMuestra1+")+("+varianza2+"/"+tamMuestra2+"))] = " + teorema.getLimInf());
                    lienzo.limitsuperior.setText("Para el límite superior: ");
                    lienzo.zeta2.setText(diferenciaMedias+" + ["+teorema.getValTablas()+"*√(("+varianza1+"/"+tamMuestra1+")+("+varianza2+"/"+tamMuestra2+"))] = " + teorema.getLimSup());
                    lienzo.paso2.setText("Una vez obtenido el intervalo para la región de no rechazo:\n\n" + teorema.caso2()+"\n\nPodemos proceder a establecer una regla de decisión para posteriormente aceptar o negar la afirmación deseada:");
                    lienzo.reglaDecision.setImageResource(R.drawable.teorema3pruebashipotesisdiferenciamediasvarconocida);
                    if(teorema.getDecision().contains("se rechaza la hipotesis nula") && (diferenciaPromedios<teorema.getLimInf())){
                        lienzo.paso2Phipodc.setText("Es decir se rechaza la hipótesis nula si el valor de la diferencia de promedios está fuera del intervalo para la región de no rechazo, cómo podemos observar el valor de la diferencia de promedios es menor al límite inferior de la región de no rechazo, por lo tanto está fuera del intervalo deseado: "+diferenciaPromedios+" < "+teorema.getLimInf()+"\n\n"+ teorema.getDecision());
                    }else if(teorema.getDecision().contains("se rechaza la hipotesis nula") && (diferenciaPromedios>teorema.getLimSup())){
                        lienzo.paso2Phipodc.setText("Es decir se rechaza la hipótesis nula si el valor de la diferencia de promedios está fuera del intervalo para la región de no rechazo, cómo podemos observar el valor de la diferencia de promedios es mayor al límite superior de la región de no rechazo, por lo tanto está fuera del intervalo deseado: " + diferenciaPromedios +" > "+ teorema.getLimSup()+"\n\n"+ teorema.getDecision());
                    }else if(teorema.getDecision().contains("se acepta la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir se rechaza la hipótesis nula si el valor de la diferencia de promedios está fuera del intervalo para la región de no rechazo, cómo podemos observar el valor de la diferencia de promedios está dentro del intervalo de la región de no rechazo: "+teorema.getLimInf()+" ≤" + diferenciaPromedios +" ≤ "+ teorema.getLimSup()+"\n\nPor lo tanto:\n\n"+ teorema.getDecision());
                    }
                }
            }
        });

        lienzo.imageButton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltanDatos2()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa los datos correspondientes");
                }else{
                    diferenciaMedias = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    varianza1 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    tamMuestra2 = Integer.parseInt(lienzo.significancia.getText().toString());
                    diferenciaPromedios = Double.parseDouble(lienzo.miu1.getText().toString());
                    significancia = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    diferenciaMedias1 = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    teorema = new PruebaHipotesisDifMediasVarConocidas(diferenciaMedias,diferenciaMedias1,varianza1,varianza2,tamMuestra1,tamMuestra2,significancia,diferenciaPromedios);
                    lienzo.output.setText("Con un intervalo para la región de aceptación igual a:\n\n"+teorema.caso4()+"\n\n"+teorema.getDecision());
                    lienzo.teorema.setImageResource(R.drawable.teorema4pruebashipotesisdiferenciamediasvarconocidas);
                    lienzo.imagePromedio.setImageResource(R.drawable.d0);
                    lienzo.val1.setText("=" + diferenciaMedias);
                    lienzo.imageMiu.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.val2.setText("=" + varianza1);
                    lienzo.imageSignificancia.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.val3.setText("=" + varianza2);
                    lienzo.desviation.setImageResource(R.drawable.tlcn1);
                    lienzo.val4.setText("=" + tamMuestra1);
                    lienzo.imageN.setImageResource(R.drawable.tlcn2);
                    lienzo.val5.setText("=" + tamMuestra2);
                    lienzo.imageDato6.setImageResource(R.drawable.alfa);
                    lienzo.val6.setText("=" + significancia);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.imageDato7.setImageResource(R.drawable.difpromedios);
                    lienzo.val7.setText("= " + diferenciaPromedios);
                    lienzo.tableRow8.setVisibility(View.VISIBLE);
                    lienzo.imageDato8.setImageResource(R.drawable.d1);
                    lienzo.val8.setText("=" + diferenciaMedias1);
                    lienzo.paso1Phip.setText("Una vez planteado el contraste de hipótesis. En éste caso:\n\nHipótesis nula: d0 ≤µ1-µ2≤ d1\n\nContra:\n\nHipótesis alterna: µ1-µ2 < d0  ó  µ1-µ2 > d1 \n\nSe hace necesario calcular el intervalo para la región de no rechazo, para ello podemos apoyarnos en el teorema presentado anteriormente:");
                    String aux = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2  = new SpannableString(aux);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
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
                            LayoutInflater imagen_alert1 = LayoutInflater.from(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
                            final View vista = imagen_alert1.inflate(R.layout.activity_h_i_p_o_t_e_s_i_s_n_u_l_a_t_o_a_s_t,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };


                    ClickableSpan clickableSpan1 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region1 =  new AlertDialog.Builder(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
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
                            LayoutInflater imagen_alert = LayoutInflater.from(INTERFAZPRUEBASHIPDIFERENCIAMEDIASVARIANZASCONOCIDAS.this);
                            final View vista1 = imagen_alert.inflate(R.layout.activity_p_r_u_e_b_a_h_i_p_o_t_e_s_i_s_a_l_t_e_r_n_a_t_o_a_s_t,null);
                            glosario2.setView(vista1);
                            glosario2.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,60,74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ss2.setSpan(clickableSpan1,93,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.calculo1.setText("Para el límite inferior:");
                    lienzo.calcOp1.setImageResource(R.drawable.liminfteorema4pruebashipotesisdiferenciamediasvarconocidas);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    lienzo.calcOp2.setImageResource(R.drawable.limsupteorema4pruebashipotesisdiferenciamediasvarconocidas);
                    lienzo.calculo2.setText("Para el límite superior:");
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Analizando el teorema presentado al inicio del procedimiento podemos percatarnos de que se hace necesario buscar el valor de Z(α/2) en las tablas de la distribución normal estándar, donde:\n\nα = " + significancia + "\n\nα/2 = " + teorema.redondeoDecimales((significancia/2),4) + "\n\nEn éste caso el valor encontrado en las tablas de la distribución normal estándar es:\n\nZ(α/2) = Z(" + teorema.redondeoDecimales((significancia/2),4) + ") = " + teorema.getValTablas()+"\n\nPosterior a esto se sustituyen los valores correspondientes en las siguientes fórmulas:");
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.liminferior.setText("Para el límite inferior:");
                    lienzo.textView15.setText(diferenciaMedias+" - ["+teorema.getValTablas()+"*√(("+varianza1+"/"+tamMuestra1+")+("+varianza2+"/"+tamMuestra2+"))] = " + teorema.getLimInf());
                    lienzo.limitsuperior.setText("Para el límite superior: ");
                    lienzo.zeta2.setText(diferenciaMedias1+" + ["+teorema.getValTablas()+"*√(("+varianza1+"/"+tamMuestra1+")+("+varianza2+"/"+tamMuestra2+"))] = " + teorema.getLimSup());
                    lienzo.paso2.setText("Una vez obtenido el intervalo para la región de no rechazo:\n\n" + teorema.caso2()+"\n\nPodemos proceder a establecer una regla de decisión para posteriormente aceptar o negar la afirmación deseada:");
                    lienzo.reglaDecision.setImageResource(R.drawable.teorema3pruebashipotesisdiferenciamediasvarconocida);
                    if(teorema.getDecision().contains("se rechaza la hipotesis nula") && (diferenciaPromedios<teorema.getLimInf())){
                        lienzo.paso2Phipodc.setText("Es decir se rechaza la hipótesis nula si el valor de la diferencia de promedios está fuera del intervalo para la región de no rechazo, cómo podemos observar el valor de la diferencia de promedios es menor al límite inferior de la región de no rechazo, por lo tanto está fuera del intervalo deseado: "+diferenciaPromedios+" < "+teorema.getLimInf()+"\n\n"+ teorema.getDecision());
                    }else if(teorema.getDecision().contains("se rechaza la hipotesis nula") && (diferenciaPromedios>teorema.getLimSup())){
                        lienzo.paso2Phipodc.setText("Es decir se rechaza la hipótesis nula si el valor de la diferencia de promedios está fuera del intervalo para la región de no rechazo, cómo podemos observar el valor de la diferencia de promedios es mayor al límite superior de la región de no rechazo, por lo tanto está fuera del intervalo deseado: " + diferenciaPromedios +" > "+ teorema.getLimSup()+"\n\n"+ teorema.getDecision());
                    }else if(teorema.getDecision().contains("se acepta la hipotesis nula")){
                        lienzo.paso2Phipodc.setText("Es decir se rechaza la hipótesis nula si el valor de la diferencia de promedios está fuera del intervalo para la región de no rechazo, cómo podemos observar el valor de la diferencia de promedios está dentro del intervalo de la región de no rechazo: "+teorema.getLimInf()+" ≤" + diferenciaPromedios +" ≤ "+ teorema.getLimSup()+"\n\nPor lo tanto:\n\n"+ teorema.getDecision());
                    }
                }
            }
        });


        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaNuevaDiferencia()){
                    lienzo.cambia9.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa una nueva diferencia de medias poblacionales");
                }else{
                    nuevaDiferenciaMedias = Double.parseDouble(lienzo.editTextCambia9.getText().toString());
                    if(ultimoCaso.equals("Caso1")){
                        lienzo.output.setText("Si la nueva diferencia de medias es "+nuevaDiferenciaMedias+" la potencia de la prueba calculada es de:\n\n" + teorema.redondeoDecimales((1-teorema.potenciaPrueba(teorema.getLimSup(),nuevaDiferenciaMedias)),5));
                        lienzo.layoutPotencia.setVisibility(View.VISIBLE);
                        lienzo.caso1.setText("La potencia de la prueba no es más que el cálculo de la probabilidad de que en dado caso se rechace la hipótesis nula de manera acertada, para lo cual es necesario tomar en cuenta el area de rechazo que implícitamente obtuvimos anteriormente al calcular el límite superior para el area de no rechazo.\n\nPara calcular dicha probabilidad es necesario primero obtener el valor de Z que posteriormente se va a buscar en las tablas de la distribución normal estándar, para ello podemos apoyarnos de la siguiente fórmula:");
                        lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                        lienzo.imagenPotencia.setImageResource(R.drawable.estadisticozdiferenciamedias);
                        lienzo.val1EstadisticoZ.setImageResource(R.drawable.difpromedios);
                        lienzo.textoVal1Estadisticoz.setText("=" + diferenciaPromedios);
                        lienzo.val2EstadisticoZ.setImageResource(R.drawable.diferenciamedias);
                        lienzo.textoVal2Estadisticoz.setText("=" + nuevaDiferenciaMedias);
                        lienzo.val3EstadisticoZ.setImageResource(R.drawable.tlcvarianza1);
                        lienzo.textoVal3Estadisticoz.setText("=" + varianza1);
                        lienzo.val4EstadisticoZ.setImageResource(R.drawable.tlcvarianza2);
                        lienzo.textoVal4Estadisticoz.setText("=" + varianza2);
                        lienzo.tbrow5.setVisibility(View.VISIBLE);
                        lienzo.tbrow6.setVisibility(View.VISIBLE);
                        lienzo.val5EstadisticoZ.setImageResource(R.drawable.tlcn1);
                        lienzo.textoVal5EstadisticoZ.setText("=" + tamMuestra1);
                        lienzo.val6EstadisticoZ.setImageResource(R.drawable.tlcn2);
                        lienzo.textoVal6EstadisticoZ.setText("=" + tamMuestra2);
                        lienzo.layoutEstadistico2.setVisibility(View.GONE);
                        lienzo.paso2Phipodc.setText("Una vez calculado el valor de la estadística de prueba Z, en este caso:\n\n"+teorema.getEstadisticoZ()+"\n\nProcedemos a buscar dicho valor en las tablas acumuladas de la distribución normal estándar, que en este caso el valor encontrado en tablas es:\n"+teorema.potenciaPrueba(teorema.getLimSup(),nuevaDiferenciaMedias));
                        lienzo.paso2Phipodc.setVisibility(View.VISIBLE);
                        lienzo.resultadoPotPrueba.setText("El último paso es utilizar la región de rechazo, que en este caso está comprendida desde "+ teorema.getLimSup()+ " hasta ∞, por lo cual un contraste correcto para el cálculo de la probabilidad de que se rechace de manera acertada la hipótesis nula es:\n\nP(Z>" + teorema.getEstadisticoZ()+") = 1-P(Z≤"+teorema.getEstadisticoZ()+") = 1 - " + teorema.potenciaPrueba(teorema.getLimSup(),nuevaDiferenciaMedias) +" = " + teorema.redondeoDecimales((1-teorema.potenciaPrueba(teorema.getLimSup(),nuevaDiferenciaMedias)),5));
                    }else if(ultimoCaso.equals("Caso2")){

                    }else if(ultimoCaso.equals("Caso3")){
                        lienzo.output.setText("Si la nueva diferencia de medias es "+nuevaDiferenciaMedias+" la potencia de la prueba calculada es de:\n\n" + teorema.potenciaPrueba(teorema.getLimInf(),nuevaDiferenciaMedias));
                        lienzo.layoutPotencia.setVisibility(View.VISIBLE);
                        lienzo.caso1.setText("La potencia de la prueba no es más que el cálculo de la probabilidad de que en dado caso se rechace la hipótesis nula de manera acertada, para lo cual es necesario tomar en cuenta el area de rechazo que implícitamente obtuvimos anteriormente al calcular el límite superior para el area de no rechazo.\n\nPara calcular dicha probabilidad es necesario primero obtener el valor de Z que posteriormente se va a buscar en las tablas de la distribución normal estándar, para ello podemos apoyarnos de la siguiente fórmula:");
                        lienzo.potenciaPrueba.setVisibility(View.VISIBLE);
                        lienzo.imagenPotencia.setImageResource(R.drawable.estadisticozdiferenciamedias);
                        lienzo.val1EstadisticoZ.setImageResource(R.drawable.difpromedios);
                        lienzo.textoVal1Estadisticoz.setText("=" + diferenciaPromedios);
                        lienzo.val2EstadisticoZ.setImageResource(R.drawable.diferenciamedias);
                        lienzo.textoVal2Estadisticoz.setText("=" + nuevaDiferenciaMedias);
                        lienzo.val3EstadisticoZ.setImageResource(R.drawable.tlcvarianza1);
                        lienzo.textoVal3Estadisticoz.setText("=" + varianza1);
                        lienzo.val4EstadisticoZ.setImageResource(R.drawable.tlcvarianza2);
                        lienzo.textoVal4Estadisticoz.setText("=" + varianza2);
                        lienzo.tbrow5.setVisibility(View.VISIBLE);
                        lienzo.tbrow6.setVisibility(View.VISIBLE);
                        lienzo.val5EstadisticoZ.setImageResource(R.drawable.tlcn1);
                        lienzo.textoVal5EstadisticoZ.setText("=" + tamMuestra1);
                        lienzo.val6EstadisticoZ.setImageResource(R.drawable.tlcn2);
                        lienzo.textoVal6EstadisticoZ.setText("=" + tamMuestra2);
                        lienzo.layoutEstadistico2.setVisibility(View.GONE);
                        lienzo.paso2Phipodc.setText("Una vez calculado el valor de la estadística de prueba Z, en este caso:\n\n"+teorema.getEstadisticoZ()+"\n\nProcedemos a buscar dicho valor en las tablas acumuladas de la distribución normal estándar, que en este caso el valor encontrado en tablas es:\n"+teorema.potenciaPrueba(teorema.getLimSup(),nuevaDiferenciaMedias));
                        lienzo.paso2Phipodc.setVisibility(View.VISIBLE);
                        lienzo.resultadoPotPrueba.setText("El último paso es utilizar la región de rechazo, que en este caso está comprendida desde "+ teorema.getLimInf()+ " hasta ∞, por lo cual un contraste correcto para el cálculo de la probabilidad de que se rechace de manera acertada la hipótesis nula es:\n\nP(Z≤" + teorema.getEstadisticoZ()+") = "+ teorema.potenciaPrueba(teorema.getLimInf(),nuevaDiferenciaMedias));

                    }
                }
            }
        });


    }

    public boolean faltanDatos(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltanDatos2(){
        if(faltanDatos()||lienzo.editTextCambia8.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltaNuevaDiferencia(){
        if(lienzo.editTextCambia9.getText().toString().isEmpty()) return true;
        else return false;
    }


    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }

}