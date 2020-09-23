package com.example.estadistica;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class INTERFAZICPROPORCIONES extends AppCompatActivity {

    private double proporcion,complemento,valTablas,significancia,limInf,limSup,coeficienteConfianza,errorMuestral;
    private int tamMuestra,tamMinimoMuestra, cotaInferior;
    private ICFPROPORCIONES teoremaProporciones;
    private ActivityPruebaHipBinding lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE);
        lienzo.potenciaPrueba.setVisibility(View.GONE);
        lienzo.llc2.setVisibility(View.GONE);
        lienzo.linearLayoutDato3.setVisibility(View.GONE);
        lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.GONE);
        lienzo.cambia1.setText("Proporción muestral:");
        lienzo.cambia5.setText("Nivel de confianza: ");
        lienzo.cambia6.setText("Error muestral");
        lienzo.cambia7.setText("Limite inferior: ");
        lienzo.cambia8.setText("Limite superior: ");
        lienzo.imageViewCambia1.setImageResource(R.drawable.proporcion);
        lienzo.imageCambia4.setImageResource(R.drawable.unomenosalfa);
        lienzo.imCambia6.setImageResource(R.drawable.error_muestral);
        lienzo.imageCambia7.setImageResource(R.drawable.a);
        lienzo.imageCambia8.setImageResource(R.drawable.b);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setText("Calcular intervalo de confianza");
        lienzo.button8.setText("Calcular tamaño mínimo de muestra");
        lienzo.button8.setVisibility(View.VISIBLE);
        lienzo.button2.setVisibility(View.VISIBLE);
        lienzo.button2.setText("Calcular coeficiente de confianza si se conoce el valor del límite inferior del intervalo de confianza");
        lienzo.button.setVisibility(View.VISIBLE);
        lienzo.button.setText("Calcular coeficiente de confianza si se conoce el valor del límite superior del intervalo de confianza");
        lienzo.button9.setVisibility(View.VISIBLE);
        lienzo.button9.setText("Calcular error muestral");
        lienzo.llc2.setVisibility(View.GONE);
        lienzo.linearLayoutDato3.setVisibility(View.GONE);
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);

        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos marcados en rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                }else{
                    lienzo.textView.setVisibility(View.VISIBLE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.layoutCotaInferior.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    proporcion = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                    teoremaProporciones = new ICFPROPORCIONES(proporcion,significancia,tamMuestra);
                    valTablas = teoremaProporciones.getValTablas();
                    limInf = teoremaProporciones.getLimInf();
                    limInf = teoremaProporciones.redondeoDecimales(limInf,6);
                    limSup = teoremaProporciones.getLimSup();
                    limSup = teoremaProporciones.redondeoDecimales(limSup,6);
                    String intervalo = limInf+"<p<"+limSup;
                    lienzo.output.setText("El intervalo de confianza calculado es: " + intervalo);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.imageMiu.setImageResource(R.drawable.proporcion);
                    lienzo.imageN.setImageResource(R.drawable.dato_g);
                    lienzo.imageSignificancia.setImageResource(R.drawable.unomenosalfa);
                    lienzo.imagePromedio.setImageResource(R.drawable.complemento);
                    lienzo.val1.setText("=" + teoremaProporciones.redondeoDecimales((1-proporcion),6));
                    lienzo.textView15.setText("");
                    lienzo.val2.setText("=" + proporcion);
                    lienzo.val3.setText("="+ significancia);
                    lienzo.tableRowSigma.setVisibility(View.GONE);
                    lienzo.val5.setText("=" + teoremaProporciones.redondeoDecimales((1-significancia),5));
                    lienzo.imageDato6.setImageResource(R.drawable.dato_f);
                    lienzo.val6.setText("="+tamMuestra);
                    lienzo.teorema.setImageResource(R.drawable.intervaloconfianzaproporciones);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                    lienzo.calcOp1.setImageResource(R.drawable.liminfintervaloconfianzaproporciones);
                    lienzo.calcOp2.setImageResource(R.drawable.limsupintervaloconfianzaproporciones);
                    lienzo.calculo1.setText("Para el límite inferior:");
                    lienzo.calculo1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    lienzo.calculo2.setText("Para el límite superior:");
                    lienzo.calculo2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    lienzo.imZ.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.paso1Phip.setText("Al calcular el intervalo de confianza para la proporcion de una muestra se hace necesario, primero buscar el valor de Z(α/2) en las tablas de la distribución normal estándar donde:\n\n1-α=" + significancia + "\n\nα=" + teoremaProporciones.redondeoDecimales((1-significancia),6) + "\n\nα/2="+teoremaProporciones.redondeoDecimales(((1-significancia)/2),6) + "\n\nEl valor encontrado en las tablas de la distribución normal estándar de 0-z es de: \nZ(α/2) = " + teoremaProporciones.getValTablas());
                    lienzo.paso1Phip.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                    lienzo.textView6.setVisibility(View.VISIBLE);
                    lienzo.textView6.setText("Paso 2:\n\nEl segundo y último paso es, sustituir los valores correspondientes en el teorema de intervalos de confianza para proporciones:");
                    lienzo.calcOp1.setVisibility(View.VISIBLE);
                    lienzo.calcOp2.setVisibility(View.VISIBLE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.textView15.setText(proporcion+"-("+valTablas+"√(("+proporcion+"*"+complemento+")/"+tamMuestra+") = " + limInf);
                    lienzo.zeta2.setVisibility(View.VISIBLE);
                    lienzo.zeta2.setText(proporcion+"+("+valTablas+"√(("+proporcion+"*"+complemento+")/"+tamMuestra+") = " + limSup);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    String valPorcentualSignificancia = teoremaProporciones.redondeoDecimales((significancia*100),5) + "%";
                    lienzo.paso2.setText("Conclusión:\n\nComo podemos observar en el intervalo de confianza obtenido, al " + valPorcentualSignificancia + " los valores de la proporcion de la muestra estarán entre:\n\n" + limInf + " y " + limSup);
                    lienzo.textView5.setVisibility(View.VISIBLE);
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                    lienzo.step1.setText("Paso 1:");
                    lienzo.liminferior.setText("Límite inferior:");
                    lienzo.limitsuperior.setText("Límite superior:");
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.paso2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    lienzo.textView8.setVisibility(View.GONE);
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
                lienzo.mediaPoblacional.setText("");
                lienzo.tamMuestral.setText("");
                lienzo.significancia.setText("");
                lienzo.miu1.setText("");
                lienzo.editTextCambia8.setText("");
                lienzo.editTextCambia7.setText("");
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.output.setText("");
                lienzo.verProcedimiento.setVisibility(View.GONE);
            }
        });

        lienzo.button8.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaErrorMuestral()){
                    desplegarNotificacion("Por favor ingresa todos los datos marcados con rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                }else{
                    lienzo.textView8.setVisibility(View.GONE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                    proporcion = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    errorMuestral = Double.parseDouble(lienzo.miu1.getText().toString());
                    complemento = 1-proporcion;
                    teoremaProporciones = new ICFPROPORCIONES(significancia,errorMuestral,proporcion);
                    valTablas = teoremaProporciones.getValTablas();
                    tamMinimoMuestra = teoremaProporciones.getTamMinimoMuestra();
                    cotaInferior = teoremaProporciones.getCotaInferior();
                    lienzo.output.setText("El tamaño mínimo de muestra necesario para que las condiciones deseadas se cumplan es de: \n\nPor una estimación puntual p: " + tamMinimoMuestra + "\n\nPor cota inferior: " + cotaInferior);
                    lienzo.paso1Phip.setText("La primer manera de calcular el tamaño mínimo de muestra es utilizando la cota inferior.\nPara ello se hace necesario encontrar el valor de Z(α/2) en las tablas de la distribución normal estándar donde:\n\n1-α=" + significancia +"\n\nα = " + teoremaProporciones.getConfianza() + "\n\nα/2=" + teoremaProporciones.redondeoDecimales((teoremaProporciones.getConfianza()/2),5) + "\n\nBuscando el valor en las tablas de la distribución Z obtenemos:\n\nZ(α/2) =" + "Z("+teoremaProporciones.redondeoDecimales((teoremaProporciones.getConfianza()/2),5)+") = " + teoremaProporciones.getValTablas() + "\n\nUna vez obtenido el valor en tablas, nos disponemos a sustituir los valores correspondientes en la fórmula siguiente:");
                    lienzo.paso1Phip.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.layoutCotaInferior.setVisibility(View.VISIBLE);
                    lienzo.textView6.setVisibility(View.VISIBLE);
                    lienzo.textView6.setText("La segunda manera de calcular el tamaño mínimo de muestra es utilizando el valor de la estimación puntual p.\nPara ello se hace necesario encontrar el valor de Z(α/2) en las tablas de la distribución normal estándar donde:\n\n1-α=" + significancia +"\n\nα = " + teoremaProporciones.getConfianza() + "\n\nα/2=" + teoremaProporciones.redondeoDecimales((teoremaProporciones.getConfianza()/2),5) + "\n\nBuscando el valor en las tablas de la distribución Z obtenemos:\n\nZ(α/2) =" + "Z("+teoremaProporciones.redondeoDecimales((teoremaProporciones.getConfianza()/2),5)+") = " + teoremaProporciones.getValTablas() + "\n\nUna vez obtenido el valor en tablas, nos disponemos a sustituir los valores correspondientes en la fórmula siguiente:");
                    lienzo.imageView3.setImageResource(R.drawable.cotainferior);
                    lienzo.imageView5.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.textView2.setText("=" + valTablas);
                    lienzo.imageView4.setImageResource(R.drawable.error_muestral);
                    lienzo.textView.setText("=" + errorMuestral);
                    lienzo.teorema.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.tamminimomuestraintervaloconfianzaproporciones);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.proporcion);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.complemento);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.error_muestral);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.tlcDato1TV.setText("="+proporcion);
                    lienzo.tlcDato2TV.setText("="+complemento);
                    lienzo.tlcDato3TV.setText("="+errorMuestral);
                    lienzo.tlcDato4TV.setText("="+valTablas);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Para el tamaño mínimo de muestra por la cota inferior:");
                    lienzo.textView15.setText("n = [(1/4)*("+valTablas+"/"+errorMuestral+")^2 ]+1 = " + cotaInferior);
                    lienzo.limitsuperior.setText("Para el tamaño mínimo de muestra por estimación puntual");
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.zeta2.setText("n = [("+valTablas+"/"+errorMuestral+")^2 * ("+proporcion+"*"+complemento+")]+1 = " + tamMinimoMuestra);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.step1.setText("En el caso del cálculo para el tamaño mínimo de muestra en proporciones de una muestra con distribución normal o aproximadamente normal, se puede proceder de dos maneras distintas:");
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.llci3.setVisibility(View.GONE);
                    lienzo.llci4.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                }
            }
        });

        lienzo.button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaValorLimite() || lienzo.editTextCambia7.getText().toString().isEmpty()){
                    desplegarNotificacion("Por favor ingresa los datos marcados con rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }else{
                        lienzo.tvDatos.setVisibility(View.GONE);
                        lienzo.textView5.setVisibility(View.VISIBLE);
                        lienzo.step1.setText("Paso 1:");
                        lienzo.textView6.setVisibility(View.GONE);
                        lienzo.layoutCotaInferior.setVisibility(View.GONE);
                        lienzo.procedimiento.setVisibility(View.GONE);
                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        proporcion = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                        tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                        complemento = 1-proporcion;
                        limInf = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                        teoremaProporciones = new ICFPROPORCIONES(limInf,tamMuestra,proporcion,'a');
                        coeficienteConfianza = teoremaProporciones.getCoeficienteConfianza();
                        valTablas = teoremaProporciones.getValTablas();
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzaproporciones);
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                        lienzo.layoutCotaInferior.setVisibility(View.GONE);
                        lienzo.tablaDatos.setVisibility(View.GONE);
                        lienzo.layCalc1.setVisibility(View.GONE);
                        lienzo.layCalc2.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.output.setText("El coeficiente de confianza es igual a:\n\n" + coeficienteConfianza);
                        lienzo.paso1Phip.setText("El primer paso consiste en calcular el valor del determinante que, posteriormente se va a buscar en las tablas acumuladas de la distribución normal estándar.\n\nPara calcular el valor de nuestro determinante, unicamente es necesario sustituir los valores correspondientes en la fórmula presentada a continuación:\n\nNOTA: NOTECE QUE EL DISCRIMINANTE ESTÁ AGRUPADO ENTRE PARÉNTESIS");
                        lienzo.textView8.setText("Paso 2:\n\nUna vez calculado el valor del determinante (que en éste caso es igual a " + teoremaProporciones.getMultiplicador() + "), procedemos a buscarlo en las tablas de la distribución acumulada normal estándar.\n\nEn éste caso el valor encontrado en tablas es: " + teoremaProporciones.getValTablas()+"\n\nPor último es necesario sustituir los valores correspondientes en el teorema presentado anteriormente:");
                        lienzo.textView8.setVisibility(View.VISIBLE);
                        lienzo.imageView2.setImageResource(R.drawable.coeficienteconfianzaproporcionesa);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.a);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.proporcion);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.complemento);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                        lienzo.tlcDato1TV.setText("=" + limInf);
                        lienzo.tlcDato2TV.setText("=" + proporcion);
                        lienzo.tlcDato3TV.setText("=" + complemento);
                        lienzo.tlcDato4TV.setText("=" + tamMuestra);
                        lienzo.llz5.setVisibility(View.GONE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Para el coeficiente de confianza:");
                        lienzo.textView15.setText("1-α = 1- (2*"+teoremaProporciones.getValTablas()+") = " + coeficienteConfianza);
                        lienzo.layoutDesaparece2.setVisibility(View.GONE);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.paso2.setText("Conclusión:\n\nLa proporción deseada tendrá una confianza de " + coeficienteConfianza);
                        lienzo.paso2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        lienzo.paso2.setVisibility(View.VISIBLE);
                    }
                }
        });


        lienzo.button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                if(faltaValorLimite() || lienzo.editTextCambia8.getText().toString().isEmpty()){
                    desplegarNotificacion("Por favor ingresa los datos marcados con rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                }else{
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.VISIBLE);
                    lienzo.step1.setText("Paso 1:");
                    lienzo.textView6.setVisibility(View.GONE);
                    lienzo.layoutCotaInferior.setVisibility(View.GONE);
                    proporcion = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    complemento = 1-proporcion;
                    limSup = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    teoremaProporciones = new ICFPROPORCIONES(limSup,tamMuestra,proporcion,'b');
                    coeficienteConfianza = teoremaProporciones.getCoeficienteConfianza();
                    valTablas = teoremaProporciones.getValTablas();
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzaproporciones);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.layoutCotaInferior.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.VISIBLE);
                    lienzo.output.setText("El coeficiente de confianza es igual a:" + coeficienteConfianza);
                    lienzo.paso1Phip.setText("El primer paso consiste en calcular el valor del determinante que, posteriormente se va a buscar en las tablas acumuladas de la distribución normal estándar.\n\nPara calcular el valor de nuestro determinante, unicamente es necesario sustituir los valores correspondientes en la fórmula presentada a continuación:\n\nNOTA: NOTECE QUE EL DISCRIMINANTE ESTÁ AGRUPADO ENTRE PARÉNTESIS");
                    lienzo.textView8.setVisibility(View.VISIBLE);
                    lienzo.textView8.setText("Paso 2:\n\nUna vez calculado el valor del determinante (que en éste caso es igual a " + teoremaProporciones.getMultiplicador() + "), procedemos a buscarlo en las tablas de la distribución acumulada normal estándar.\n\nEn éste caso el valor encontrado en tablas es: " + teoremaProporciones.getValTablas());
                    lienzo.imageView2.setImageResource(R.drawable.coeficienteconfianzaproporcionesb);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.b);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.proporcion);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.complemento);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato1TV.setText("=" + limSup);
                    lienzo.tlcDato2TV.setText("=" + proporcion);
                    lienzo.tlcDato3TV.setText("=" + complemento);
                    lienzo.tlcDato4TV.setText("=" + tamMuestra);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Para el coeficiente de confianza:");
                    lienzo.textView15.setText("1-α = (2*"+teoremaProporciones.getValTablas()+")-1 = " + coeficienteConfianza);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.paso2.setText("Conclusión:\n\nEl intervalo de confianza de " + coeficienteConfianza);
                    lienzo.paso2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                }
            }
        });


        lienzo.button9.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaDatoErrorMuestral()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa todos los datos requeridos");
                }else{
                    proporcion = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    significancia = Double.parseDouble(lienzo.significancia.getText().toString());
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    teoremaProporciones = new ICFPROPORCIONES(significancia,tamMuestra,proporcion);
                    lienzo.output.setText("El error por estimación calculado que cumple con las condiciones necesarias es:\n"+teoremaProporciones.getErrorMuestral());
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.teorema.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Al calcular el error muestral se hace necesario, primero buscar el valor de Z(α/2) en las tablas de la distribución normal estándar donde:\n\n1-α=" + significancia + "\n\nα=" + teoremaProporciones.redondeoDecimales((1-significancia),6) + "\n\nα/2="+teoremaProporciones.redondeoDecimales(((1-significancia)/2),6) + "\n\nEl valor encontrado en las tablas de la distribución normal estándar de 0-z es de: \nZ(α/2) = " + teoremaProporciones.getValTablas() + "\n\nUna vez encontrado el valor en las tablas de la distribución normál estándar, procedemos a sustituir los valores correspondientes en la fórmula siguiente:");
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.teoremaerrormuestral);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.tlcDato1TV.setText("="+teoremaProporciones.getValTablas());
                    lienzo.tlcImageDato2.setImageResource(R.drawable.proporcion);
                    lienzo.tlcDato2TV.setText("="+proporcion);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.complemento);
                    lienzo.tlcDato3TV.setText("=" + teoremaProporciones.getComplemento());
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato4TV.setText("="+tamMuestra);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.llz8.setVisibility(View.GONE);
                    lienzo.paso1Phip.setTextAlignment(View.TEXT_ALIGNMENT_INHERIT);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.textView15.setText(teoremaProporciones.getValTablas()+"*√[("+proporcion+"*"+teoremaProporciones.getComplemento()+")/"+tamMuestra+"] = " + teoremaProporciones.getErrorMuestral());
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.textView6.setVisibility(View.GONE);
                    lienzo.textView8.setVisibility(View.GONE);
                    lienzo.layoutCotaInferior.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.linearLayoutReglaDecision.setVisibility(View.GONE);
                }
            }
        });


    }


    public boolean faltaDato(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltaErrorMuestral(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()) return true;
        else return false;
    }


    public boolean faltaValorLimite(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||(lienzo.editTextCambia7.getText().toString().isEmpty() && lienzo.editTextCambia8.getText().toString().isEmpty())) return true;
        else return false;
    }

    public boolean faltaDatoErrorMuestral(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()) return true;
        else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }
}