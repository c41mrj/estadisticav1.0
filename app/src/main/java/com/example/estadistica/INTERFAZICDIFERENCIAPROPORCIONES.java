package com.example.estadistica;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

import java.text.NumberFormat;
import java.util.StringJoiner;
import java.util.function.DoublePredicate;

public class INTERFAZICDIFERENCIAPROPORCIONES extends AppCompatActivity {

    ActivityPruebaHipBinding lienzo;
    private double proporcion1,proporcion2,complemento1,complemento2,nivelConfianza,limInf,limSup,errorMuestral;
    private int tamMuestra1,tamMuestra2,tamMinimoMuestra,cotaInferior;
    private ICDIFERENCIAPROPORCIONES teoremaDiferenciaProporciones;
    private CalculoTablas redondeo = new CalculoTablas();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        lienzo.paso2PotPrueba.setVisibility(View.GONE);
        lienzo.resultadoPotPrueba.setVisibility(View.GONE);
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.cambia1.setText("Proporción 1:");
        lienzo.imageViewCambia1.setImageResource(R.drawable.proporcion1);
        lienzo.cambia2.setText("Proporción 2:");
        lienzo.imageCambia.setImageResource(R.drawable.tclproporcion2);
        lienzo.cambia3.setText("Tamaño muestral 1:");
        lienzo.imageCambia2.setImageResource(R.drawable.tlcn1);
        lienzo.cambia4.setText("Tamaño muestral 2:");
        lienzo.imageCambia3.setImageResource(R.drawable.tlcn2);
        lienzo.cambia5.setText("Nivel de confianza:");
        lienzo.imageCambia4.setImageResource(R.drawable.unomenosalfa);
        lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
        lienzo.cambia6.setText("Error muestral:");
        lienzo.imCambia6.setImageResource(R.drawable.error_muestral);
        lienzo.imageCambia7.setImageResource(R.drawable.a);
        lienzo.cambia7.setText("para el límite inferior:");
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        lienzo.imageCambia8.setImageResource(R.drawable.b);
        lienzo.cambia8.setText("Para el límite superior:");
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
        lienzo.imagePromedio.setImageResource(R.drawable.proporcion1);
        lienzo.imageMiu.setImageResource(R.drawable.tclproporcion2);
        lienzo.desviation.setImageResource(R.drawable.tlcn1);
        lienzo.imageN.setImageResource(R.drawable.tlcn2);
        lienzo.dtoOpcional.setVisibility(View.VISIBLE);
        lienzo.imageDato6.setImageResource(R.drawable.q1);
        lienzo.imageDato7.setImageResource(R.drawable.q2);
        lienzo.tableRow7.setVisibility(View.VISIBLE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setText("Calcular intervalo de confianza");
        lienzo.button8.setText("Calcular tamaño mínimo de muestra");
        lienzo.button8.setVisibility(View.VISIBLE);
        lienzo.button2.setText("Calcular coeficiente de confianza si se conoce el límite inferior del I.C");
        lienzo.button2.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setText("Calcular coeficiente de confianza si se conoce el límite superior del I.C");
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.button.setVisibility(View.VISIBLE);
        lienzo.button.setText("Calcular error muestral");
        lienzo.potenciaPrueba.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE );
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
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos marcados con rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                }else {
                    lienzo.textView5.setVisibility(View.VISIBLE);
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                    proporcion1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    complemento1 = 1-proporcion1;
                    complemento1 = redondeo.redondeoDecimales(complemento1,5);
                    proporcion2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    complemento2 = 1-proporcion2;
                    complemento2 = redondeo.redondeoDecimales(complemento2,5);
                    tamMuestra1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                    tamMuestra2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    nivelConfianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    teoremaDiferenciaProporciones = new ICDIFERENCIAPROPORCIONES(proporcion1,proporcion2,tamMuestra1,tamMuestra2,nivelConfianza);
                    limInf = teoremaDiferenciaProporciones.calcLimInf();
                    limInf = teoremaDiferenciaProporciones.redondeoDecimales(limInf,6);
                    limSup = teoremaDiferenciaProporciones.calcLimSup();
                    limSup = teoremaDiferenciaProporciones.redondeoDecimales(limSup,6);
                    String intervalo =limInf+ "<(p1-p2)<" + limSup;
                    lienzo.val1.setText("=" + proporcion1);
                    lienzo.val2.setText("=" + proporcion2);
                    lienzo.val3.setText("=" + nivelConfianza);
                    lienzo.imageSignificancia.setImageResource(R.drawable.unomenosalfa);
                    lienzo.val4.setText("=" + tamMuestra1);
                    lienzo.val5.setText("=" + tamMuestra2);
                    lienzo.val6.setText("=" + complemento1);
                    lienzo.val7.setText("=" + complemento2);
                    lienzo.output.setText("El intervalo de confianza calculado es: " + intervalo);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setImageResource(R.drawable.teorema1diferenciaproporciones);
                    lienzo.calculo1.setText("Para el límite inferior:");
                    lienzo.calculo2.setText("Para el límite superior:");
                    lienzo.paso1Phip.setText("Al calcular el intervalo de confianza para la diferencia de proporciones de dos muestras normales o aproximadamente normales se hace necesario, primero buscar el valor de Z(α/2) en las tablas de la distribución normal estándar donde:\n\n1-α=" + nivelConfianza + "\n\nα=" + teoremaDiferenciaProporciones.redondeoDecimales((1-nivelConfianza),6) + "\n\nα/2="+teoremaDiferenciaProporciones.redondeoDecimales(((1-nivelConfianza)/2),6) + "\n\nEl valor encontrado en las tablas de la distribución normal estándar de 0-z es de: \nZ(α/2) = " + teoremaDiferenciaProporciones.getValTablas());
                    lienzo.calcOp1.setImageResource(R.drawable.liminfteorema1diferenciaproporciones);
                    lienzo.calcOp2.setImageResource(R.drawable.limsupteorema1diferenciaproporciones);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.textView15.setText("("+proporcion1+"-"+proporcion2+")-("+teoremaDiferenciaProporciones.getValTablas()+"√[(("+proporcion1+"*"+complemento1+")/"+tamMuestra1+")+(("+proporcion2+"*"+complemento2+")/"+tamMuestra2+")] = " + limInf);
                    lienzo.zeta2.setText("("+proporcion1+"+"+proporcion2+")-("+teoremaDiferenciaProporciones.getValTablas()+"√[(("+proporcion1+"*"+complemento1+")/"+tamMuestra1+")+(("+proporcion2+"*"+complemento2+")/"+tamMuestra2+")] = " + limSup);
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.textView8.setText("Paso 2:\n\nSe sustituyen los valores correspondientes en el teorema presentado al inicio del procedimiento:");
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.step1.setText("Paso 1:");
                    lienzo.textView6.setVisibility(View.GONE);
                    lienzo.layoutCotaInferior.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.liminferior.setText("Límite inferior:");
                    lienzo.limitsuperior.setText("Límite superior:");
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
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.mediaPoblacional.setText("");
                lienzo.desviacionEstandarPob.setText("");
                lienzo.promedioMuestral.setText("");
                lienzo.tamMuestral.setText("");
                lienzo.significancia.setText("");
                lienzo.miu1.setText("");
                lienzo.editTextCambia7.setText("");
                lienzo.editTextCambia8.setText("");
            }
        });

        lienzo.button8.setOnClickListener(new View.OnClickListener() {
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
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaErrorMuestral()){
                    desplegarNotificacion("Por favor ingresa todos los datos marcados en rojo.");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                }else{
                    lienzo.step1.setText("En el caso del calculo para el tamaño mínimo de muestra en la diferencia de proporciones, únicamente es posible realizar el cálculo si los tamaños de muestra son iguales.\n\nAl igual que en las proporciones, existen dos maneras de realizar el cálculo del tamaño mínimo de muestra:");
                    lienzo.step1.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                    proporcion1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    proporcion2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    complemento1 = 1-proporcion1;
                    complemento2 = 1-proporcion2;
                    nivelConfianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    errorMuestral = Double.parseDouble(lienzo.miu1.getText().toString());
                    teoremaDiferenciaProporciones = new ICDIFERENCIAPROPORCIONES(proporcion1,proporcion2,errorMuestral,nivelConfianza);
                    tamMinimoMuestra = teoremaDiferenciaProporciones.calcularTamMinimoMuestra();
                    lienzo.output.setText("El tamaño mínimo de muestra que se necesita para satisfacer las condiciones es de:\n\nSin cota inferior:" + tamMinimoMuestra + "\n\nCon cota inferior: " + teoremaDiferenciaProporciones.calcularCotaInferior());
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.layoutCotaInferior.setVisibility(View.VISIBLE);
                    lienzo.imageView3.setImageResource(R.drawable.cotainferiordiferenciaproporciones);
                    lienzo.imageView5.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.textView2.setText("=" + teoremaDiferenciaProporciones.getValTablas());
                    lienzo.imageView4.setImageResource(R.drawable.error_muestral);
                    lienzo.textView.setText("=" + errorMuestral);
                    lienzo.textView.setVisibility(View.VISIBLE);
                    lienzo.llci3.setVisibility(View.GONE);
                    lienzo.llci4.setVisibility(View.GONE);
                    lienzo.textView6.setText("La segunda manera de calcular el tamaño mínimo de muestra es sin el uso de la cota inferior. Para ello se hace necesario buscar el valor de Z(α/2) en las tablas de la distribución normal estándar, donde:\n\n1-α=" + nivelConfianza + "\n\nα=" + teoremaDiferenciaProporciones.redondeoDecimales((1-nivelConfianza),5) + "\n\nα/2=" + teoremaDiferenciaProporciones.redondeoDecimales(((1-nivelConfianza)/2),5) + "\n\nAl buscar el valor de Z(α/2) encontramos que es igual a:\nZ(α/2) = " + teoremaDiferenciaProporciones.getValTablas() + "\n\nPara calcular el tamaño mínimo de muestra nos apoyaremos en la siguiente fórmula:");
                    lienzo.textView6.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.tamminimomuestraicdifproporciones);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.error_muestral);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.proporcion1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tclproporcion2);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.complemento1);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.complemento2);
                    lienzo.tlcDato1TV.setText("=" + errorMuestral);
                    lienzo.tlcDato2TV.setText("=" + teoremaDiferenciaProporciones.getValTablas());
                    lienzo.tlcDato3TV.setText("=" + proporcion1);
                    lienzo.tlcDato4TV.setText("="+proporcion2);
                    lienzo.tlcDato5TV.setText("="+complemento1);
                    lienzo.tlcDato6TV.setText("=" + complemento2);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setText("Sin una cota inferior:");
                    lienzo.paso1Phip.setText("La primer manera de calcular el tamaño mínimo de muestra es mediante el uso de la cota inferior. Para ello se hace necesario buscar el valor de Z(α/2) en las tablas de la distribución normal estándar, donde:\n\n1-α=" + nivelConfianza + "\n\nα=" + teoremaDiferenciaProporciones.redondeoDecimales((1-nivelConfianza),5) + "\n\nα/2=" + teoremaDiferenciaProporciones.redondeoDecimales(((1-nivelConfianza)/2),5) + "\n\nAl buscar el valor de Z(α/2) encontramos que es igual a:\nZ(α/2) = " + teoremaDiferenciaProporciones.getValTablas() + "\n\nPara calcular el tamaño mínimo de muestra nos apoyaremos en la siguiente fórmula:");
                    lienzo.zeta2.setText("[("+teoremaDiferenciaProporciones.getValTablas()+"/"+errorMuestral+")^2 * (("+proporcion1+"*"+complemento1+")+("+proporcion2+"*"+complemento2+")) + 1]  = " + tamMinimoMuestra);
                    lienzo.textView15.setText("[(1/2)*("+teoremaDiferenciaProporciones.getValTablas()+"/"+errorMuestral+")^2] + 1 = " + teoremaDiferenciaProporciones.getCotaInferior());
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.textView8.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.liminferior.setText("Con una cota inferior:");
                }
            }
        });


        lienzo.button2.setOnClickListener(new View.OnClickListener() {
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
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaValorLimite()||lienzo.editTextCambia7.getText().toString().isEmpty()){
                    desplegarNotificacion("Por favor ingresa todos los datos requeridos");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }else{
                    lienzo.textView5.setVisibility(View.VISIBLE);
                    lienzo.step1.setText("Paso 1: ");
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzaicdifproporciones);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.liminfcoeficienteconfianzaicdifproporciones);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.a);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.proporcion1);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.complemento1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tclproporcion2);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.complemento2);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcn1);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.tlcn2);
                    proporcion1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    complemento1 = 1 - proporcion1;
                    complemento1 = redondeo.redondeoDecimales(complemento1,6);
                    proporcion2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    complemento2 = 1- proporcion2;
                    complemento2 = redondeo.redondeoDecimales(complemento2,6);
                    tamMuestra1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                    tamMuestra2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    limInf = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    teoremaDiferenciaProporciones = new ICDIFERENCIAPROPORCIONES(proporcion1,proporcion2,limInf,tamMuestra1,tamMuestra2);
                    double coeficienteConfianza = teoremaDiferenciaProporciones.calcularCoeficienteDeConfianza('a');
                    lienzo.output.setText("El nivel de confianza requerido para cumplir con las condiciones del problema es de:\n" + coeficienteConfianza);
                    lienzo.tlcDato1TV.setText("=" + limInf);
                    lienzo.tlcDato2TV.setText("=" + proporcion1);
                    lienzo.tlcDato3TV.setText("=" + complemento1);
                    lienzo.tlcDato4TV.setText("=" + proporcion2);
                    lienzo.tlcDato5TV.setText("=" + complemento2);
                    lienzo.tlcDato6TV.setText("=" + tamMuestra1);
                    lienzo.tlcDato7TV.setText("=" + tamMuestra2);
                    lienzo.paso1Phip.setText("El primer paso para calcular el coeficiente de confianza para la diferencia de proporciones es calcular el valor del determinante que, posteriormente vamos a buscar en las tablas de la distribución acumulada de la normal estándar.\n\nNOTA: EL DETERMINANTE ES AQUEL ENCERRADO ENTRE CORCHETES EN LA IGUALDAD SIGUIENTE DEL TEOREMA PRESENTADO ANTERIORMENTE:");
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Coeficiente de confianza:");
                    lienzo.textView15.setText("1-α=1-(2*"+teoremaDiferenciaProporciones.getValTablas()+") = " + coeficienteConfianza);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.paso2.setText("Conclusión:\n\nEl intervalo de confianza para la diferencia de proporciones tendrá una confianza de " + coeficienteConfianza);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutCotaInferior.setVisibility(View.GONE);
                    lienzo.textView6.setVisibility(View.GONE);
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("En éste caso el valor del determinante calculado anteriormente es igual a: " + teoremaDiferenciaProporciones.getDeterminante() + "\n\nPaso 2: \n\nComo se mencionó anteriormente, el siguiente paso es buscar el valor del determinante en las tablas de la distribución acumulada de la normal estándar. Én este caso el valor en tablas es: " + teoremaDiferenciaProporciones.getValTablas() + ".\n\nPaso 3:\n\nEl último paso es sustituir los valores correspondientes en el teorema presentado anteriormente:");
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    lienzo.tvDatos.setVisibility(View.GONE);
                }
            }
        });


        lienzo.otroBotonAux.setOnClickListener(new View.OnClickListener() {
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
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaValorLimite()||lienzo.editTextCambia8.getText().toString().isEmpty()){
                    desplegarNotificacion("Por favor ingresa todos los datos requeridos");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                }else{
                    lienzo.textView5.setVisibility(View.VISIBLE);
                    lienzo.step1.setText("Paso 1: ");
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzaicdifproporciones);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.limsupcoeficienteconfianzaicdifproporciones);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.b);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.proporcion1);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.complemento1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tclproporcion2);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.complemento2);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcn1);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.tlcn2);
                    proporcion1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    complemento1 = 1 - proporcion1;
                    complemento1 = redondeo.redondeoDecimales(complemento1,6);
                    proporcion2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    complemento2 = 1- proporcion2;
                    complemento2 = redondeo.redondeoDecimales(complemento2,6);
                    tamMuestra1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                    tamMuestra2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    limSup = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    teoremaDiferenciaProporciones = new ICDIFERENCIAPROPORCIONES(proporcion1,proporcion2,limSup,tamMuestra1,tamMuestra2);
                    double coeficienteConfianza = teoremaDiferenciaProporciones.calcularCoeficienteDeConfianza('b');
                    lienzo.output.setText("El nivel de confianza requerido para cumplir con las condiciones del problema es de:\n" + coeficienteConfianza);
                    lienzo.tlcDato1TV.setText("=" + limSup);
                    lienzo.tlcDato2TV.setText("=" + proporcion1);
                    lienzo.tlcDato3TV.setText("=" + complemento1);
                    lienzo.tlcDato4TV.setText("=" + proporcion2);
                    lienzo.tlcDato5TV.setText("=" + complemento2);
                    lienzo.tlcDato6TV.setText("=" + tamMuestra1);
                    lienzo.tlcDato7TV.setText("=" + tamMuestra2);
                    lienzo.paso1Phip.setText("El primer paso para calcular el coeficiente de confianza para la diferencia de proporciones es calcular el valor del determinante que, posteriormente vamos a buscar en las tablas de la distribución acumulada de la normal estándar.\n\nNOTA: EL DETERMINANTE ES AQUEL ENCERRADO ENTRE CORCHETES EN LA IGUALDAD SIGUIENTE DEL TEOREMA PRESENTADO ANTERIORMENTE:");
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Coeficiente de confianza:");
                    lienzo.textView15.setText("1-α=(2*"+teoremaDiferenciaProporciones.getValTablas()+")-1 = " + coeficienteConfianza);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.paso2.setText("Una vez calculado el valor de nuestro determinante procedemos a buscar el valor correspondiente en las tablas de la distribución acumulada normal estándar y seguimos la fórmula mostrada en el paso1\n\n Sustituyendo valores en dicha fórmula llegamos al resultado: " + coeficienteConfianza);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutCotaInferior.setVisibility(View.GONE);
                    lienzo.textView6.setVisibility(View.GONE);
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("En éste caso el valor del determinante calculado anteriormente es igual a: " + teoremaDiferenciaProporciones.getDeterminante() + "\n\nPaso 2: \n\nComo se mencionó anteriormente, el siguiente paso es buscar el valor del determinante en las tablas de la distribución acumulada de la normal estándar. Én este caso el valor en tablas es: " + teoremaDiferenciaProporciones.getValTablas() + ".\n\nPaso 3:\n\nEl último paso es sustituir los valores correspondientes en el teorema presentado anteriormente:");
                    lienzo.paso2.setText("Conclusión:\n\nEl intervalo de confianza para la diferencia de proporciones tendrá una confianza de " + coeficienteConfianza);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.paso2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    lienzo.tvDatos.setVisibility(View.GONE);
                }
            }
        });


        lienzo.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaDatoErrorMuestral()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa todos los datos requeridos");
                }else{
                    proporcion1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    complemento1 = redondeo.redondeoDecimales((1-proporcion1),5);
                    proporcion2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    complemento2 = redondeo.redondeoDecimales((1-proporcion2),5);
                    tamMuestra1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                    tamMuestra2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    nivelConfianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    if(tamMuestra1 != tamMuestra2)desplegarNotificacion("Para realizar el calculo es necesario tener tamaños de muestra iguales");
                    else{
                        lienzo.teorema.setVisibility(View.GONE);
                        lienzo.textView5.setVisibility(View.GONE);
                        lienzo.tablaDatos.setVisibility(View.GONE);
                        lienzo.tvDatos.setVisibility(View.GONE);
                        double aux1 = (proporcion1*complemento1) /(double) tamMuestra1;
                        double aux2 = (proporcion2*complemento2) /(double) tamMuestra2;
                        double multiplicador = Math.sqrt((aux1+aux2));
                        double unomenosalfamedios = redondeo.redondeoDecimales(((1-nivelConfianza)/2),4);
                        double valTablas = redondeo.tablazeta((float)(unomenosalfamedios));
                        errorMuestral = valTablas * multiplicador;
                        errorMuestral = redondeo.redondeoDecimales(errorMuestral,6);
                        lienzo.output.setText("El error muestral calculado que satisface las condiciones del problema es de:\n\n" + errorMuestral);
                        lienzo.step1.setText("Paso 1:");
                        lienzo.paso1Phip.setText("Al calcular el error muestral para la diferencia de proporciones de dos muestras normales o aproximadamente normales se hace necesario, primero buscar el valor de Z(α/2) en las tablas de la distribución normal estándar donde:\n\n1-α=" + nivelConfianza + "\n\nα=" + redondeo.redondeoDecimales((1-nivelConfianza),6) + "\n\nα/2="+redondeo.redondeoDecimales(((1-nivelConfianza)/2),6) + "\n\nEl valor encontrado en las tablas de la distribución normal estándar de 0-z es de: \nZ(α/2) = " + valTablas);
                        lienzo.textView6.setVisibility(View.VISIBLE);
                        lienzo.textView6.setText("Paso 2:\n\nEl segundo paso es sustituir los valores correspondientes en el teorema siguiente:");
                        lienzo.layoutCotaInferior.setVisibility(View.GONE);
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                        lienzo.imageView2.setImageResource(R.drawable.errormuestraldiferenciaproporciones);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.zetaalfamedios);
                        lienzo.tlcDato1TV.setText("=" + valTablas);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.proporcion1);
                        lienzo.tlcDato2TV.setText("=" + proporcion1);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.complemento1);
                        lienzo.tlcDato3TV.setText("=" + complemento1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.tclproporcion2);
                        lienzo.tlcDato4TV.setText("=" + proporcion2);
                        lienzo.tlcImageDato5.setImageResource(R.drawable.complemento2);
                        lienzo.tlcDato5TV.setText("=" + complemento2);
                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.layCalc1.setVisibility(View.GONE);
                        lienzo.layCalc2.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.layoutDesaparece2.setVisibility(View.GONE);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Error muestral:");
                        lienzo.textView15.setText("ε= "+ valTablas + "*√[(("+proporcion1+"*"+complemento1+")/"+tamMuestra1+")+(("+proporcion2+"*"+complemento2+")/"+tamMuestra2+")] = " + errorMuestral);
                        lienzo.paso2.setVisibility(View.GONE);
                        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                        lienzo.potenciaPrueba.setVisibility(View.GONE);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.textView8.setVisibility(View.GONE);
                    }
                }
            }
        });


    }

    public boolean faltaDato(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()|| lienzo.significancia.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltaErrorMuestral(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltaValorLimite(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||(lienzo.editTextCambia8.getText().toString().isEmpty()&&lienzo.editTextCambia7.getText().toString().isEmpty())) return true;
        else return false;
    }

    public boolean faltaDatoErrorMuestral(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()) return true;
        else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }

}