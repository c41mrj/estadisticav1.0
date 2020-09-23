package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class INTERFAZICDIFERENCIAMEDIASVARIANZASCONOCIDAS extends AppCompatActivity {

    private ActivityPruebaHipBinding lienzo;
    private ICFDIFMEDIASVARIANZASCONOCIDAS teoremaDifMedias1;
    private double confianza,promedioMuestral1,promedioMuestral2,limInferior,limSuperior,valtablas,varianza1,varianza2;
    private int tamMuestral1,tamMuestral2;
    CalculoTablas tablas = new CalculoTablas();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        this.setTitle("I.C para diferencia de medias con varianzas conocidas");
        lienzo.imageViewCambia1.setImageResource(R.drawable.promedio1);
        lienzo.imageCambia.setImageResource(R.drawable.promedio2);
        lienzo.imageCambia2.setImageResource(R.drawable.tlcn1);
        lienzo.imageCambia3.setImageResource(R.drawable.tlcn2);
        lienzo.imageCambia4.setImageResource(R.drawable.tlcvarianza1);
        lienzo.imCambia6.setImageResource(R.drawable.tlcvarianza2);
        lienzo.imageCambia7.setImageResource(R.drawable.dato_g);
        lienzo.imageCambia8.setImageResource(R.drawable.error_muestral);
        lienzo.imageCambia9.setImageResource(R.drawable.a);
        lienzo.imageCambia10.setImageResource(R.drawable.b);
        lienzo.cambia1.setText("Promedio muestral 1:");
        lienzo.cambia2.setText("Promedio muestral 2:");
        lienzo.cambia3.setText("Tamaño de la muestra 1:");
        lienzo.cambia4.setText("Tamaño de la muestra 2:");
        lienzo.cambia5.setText("Varianza poblacional 1:");
        lienzo.cambia6.setText("Varianza poblacional 2:");
        lienzo.cambia7.setText("Nivel de confianza:");
        lienzo.cambia8.setText("Error muestral: ");
        lienzo.cambia9.setText("Límite inferior del IC:");
        lienzo.cambia10.setText("Límite superior del IC:");
        lienzo.LinearLayoutDato10Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato10Opcional.setVisibility(View.VISIBLE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setText("Calcular intervalo de confianza");
        lienzo.button2.setVisibility(View.VISIBLE);
        lienzo.button8.setText("Calcular tamaño mínimo de muestras");
        lienzo.button2.setText("Calcular nivel de confianza si se conoce el límite inferior del IC");
        lienzo.button8.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setText("Calcular nivel de confianza si se conoce el límite superior del IC");
        lienzo.layoutPotencia.setVisibility(View.GONE);
        lienzo.potenciaPrueba.setVisibility(View.GONE);



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
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos marcados en rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }else{
                    promedioMuestral1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    promedioMuestral2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestral1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                    tamMuestral2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    varianza1 = Double.parseDouble(lienzo.significancia.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.miu1.getText().toString());
                    confianza = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    double diferenciaMedias = tablas.redondeoDecimales((promedioMuestral1-promedioMuestral2),5);
                    teoremaDifMedias1 = new ICFDIFMEDIASVARIANZASCONOCIDAS(varianza1,varianza2,tamMuestral1,tamMuestral2,diferenciaMedias,confianza);
                    limInferior = teoremaDifMedias1.getLimInf();
                    limInferior = teoremaDifMedias1.tabla.redondeoDecimales(limInferior,6);
                    limSuperior = teoremaDifMedias1.getLimSup();
                    limSuperior = teoremaDifMedias1.tabla.redondeoDecimales(limSuperior,6);
                    String intervalo = limInferior+" <µ1-µ2< "+limSuperior;
                    valtablas = teoremaDifMedias1.getValTablas();
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.output.setText("El intervalo de confianza calculado es: " + intervalo);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setImageResource(R.drawable.teoremaicdifmedias1);
                    lienzo.imagePromedio.setImageResource(R.drawable.promedio1);
                    lienzo.imageMiu.setImageResource(R.drawable.promedio2);
                    lienzo.imageSignificancia.setImageResource(R.drawable.tlcn1);
                    lienzo.desviation.setImageResource(R.drawable.tlcn2);
                    lienzo.imageN.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.imageDato6.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.imageDato7.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.val1.setText("="+promedioMuestral1);
                    lienzo.val2.setText("="+promedioMuestral2);
                    lienzo.val3.setText("="+tamMuestral1);
                    lienzo.val4.setText("="+tamMuestral2);
                    lienzo.val5.setText("="+varianza1);
                    lienzo.val6.setText("="+varianza2);
                    lienzo.val7.setText("="+valtablas);
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    lienzo.calculo1.setText("Límite inferior:");
                    lienzo.calcOp1.setImageResource(R.drawable.liminficdifmedias);
                    lienzo.calculo2.setText("Límite superior:");
                    lienzo.calcOp2.setImageResource(R.drawable.limsupicdifmedias);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.obtenerZ.setText("Valor de la distribución normal estándar a la derecha con un área de α/2");
                    lienzo.imZ.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    valtablas = teoremaDifMedias1.getValTablas();
                    lienzo.paso1Phip.setText("El primer paso para calcular el intervalo de confianza deseado es buscar el valor de: (1-"+confianza+")/2 = " + tablas.redondeoDecimales(((1-confianza)/2),5) + " en las tablas de la distribución normal estándar a la derecha, en éste caso el valor encontrado en tablas es: " + valtablas + "\n\nUna vez encontrado el valor, nos limitamos a sustituir valores en la fórmula presentada anteriormente:");
                    lienzo.textView15.setText(diferenciaMedias+"-["+valtablas+"*√(("+varianza1+"÷"+tamMuestral1+")+("+varianza2+"÷"+tamMuestral2+"))] = "+limInferior);
                    lienzo.zeta2.setText(diferenciaMedias+"+["+valtablas+"*√(("+varianza1+"÷"+tamMuestral1+")+("+varianza2+"÷"+tamMuestral2+"))] = "+limSuperior);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.liminferior.setText("Límite inferior:");
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.VISIBLE);
                    double prob = Double.parseDouble(lienzo.editTextCambia7.getText().toString()) * 100;
                    prob = tablas.redondeoDecimales(prob,6);
                    lienzo.paso2.setText("Conclusión:\n\nLa diferencia de medias se va a encontrar en el intervalo:\n\n" + intervalo + "\n\nCon una probabilidad del " + prob +"%");
                }
            }
        });

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
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
                if(faltaErrorMuestral()){
                    desplegarNotificacion("Por favor ingresa los datos marcados en rojo");
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                }else{
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    varianza1 = Double.parseDouble(lienzo.significancia.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.miu1.getText().toString());
                    double errorMuestral = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    confianza = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    teoremaDifMedias1 = new ICFDIFMEDIASVARIANZASCONOCIDAS(confianza,errorMuestral,varianza1,varianza2);
                    int TamMinimoMuestra = teoremaDifMedias1.getTamMinimoMuestra();
                    valtablas = teoremaDifMedias1.getValTablas();
                    lienzo.output.setText("El tamaño mínimo de muestra necesario para cumplir con las condiciones del problema es de: " + TamMinimoMuestra);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.tamminimomuestraicdifmedias1);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.error_muestral);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.tlcDato1TV.setText("=" + valtablas);
                    lienzo.tlcDato2TV.setText("=" + errorMuestral);
                    lienzo.tlcDato3TV.setText("=" + varianza1);
                    lienzo.tlcDato4TV.setText("=" + varianza2);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.llz8.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Para calcular el tamaño mínimo de muestra se hace necesaria la busqueda de: (1-"+confianza+")/2= " + tablas.redondeoDecimales(((1-confianza)/2),5) + " en las tablas de la distribución normal estándar a la derecha, en éste caso el valor encontrado es: "+valtablas+". \n\nUna vez encontrado el valor en tablas nos limitamos a sustituir los valores respectivos en la fórmula:");
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Tamaño mínimo de muestra:");
                    lienzo.textView15.setText("[("+valtablas+"/"+errorMuestral+")^2 * ("+varianza1+"+"+varianza2+")] + 1 = " +TamMinimoMuestra);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.procedimiento.setVisibility(View.GONE);
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
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
                if(faltaValLimite()){
                    desplegarNotificacion("Por favor ingresa los datos marcados con rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.BLACK);
                    lienzo.cambia8.setTextColor(Color.BLACK);
                    lienzo.cambia9.setTextColor(Color.RED);
                    lienzo.cambia10.setTextColor(Color.BLACK);
                }else{
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    promedioMuestral1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    promedioMuestral2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestral1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                    tamMuestral2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    varianza1 = Double.parseDouble(lienzo.significancia.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.miu1.getText().toString());
                    limInferior = Double.parseDouble(lienzo.editTextCambia9.getText().toString());
                    double diferenciaMedias = promedioMuestral1 - promedioMuestral2;
                    diferenciaMedias =tablas.redondeoDecimales(diferenciaMedias,6);
                    teoremaDifMedias1 = new ICFDIFMEDIASVARIANZASCONOCIDAS(tamMuestral1,tamMuestral2,varianza1,varianza2,diferenciaMedias,limInferior,'a');
                    double coeficienteConfianza = teoremaDifMedias1.getCoeficienteConfianza();
                    valtablas = teoremaDifMedias1.getValTablas();
                    lienzo.output.setText("El nivel de confianza requerido es de: " + coeficienteConfianza);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzaicdifmedias1);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.liminfcoeficienteconfianzaicdifmedias1);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.promedio1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.promedio2);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.a);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcn1);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcDato1TV.setText("="+promedioMuestral1);
                    lienzo.tlcDato2TV.setText("="+promedioMuestral2);
                    lienzo.tlcDato3TV.setText("="+limInferior);
                    lienzo.tlcDato4TV.setText("="+varianza1);
                    lienzo.tlcDato5TV.setText("="+varianza2);
                    lienzo.tlcDato6TV.setText("="+tamMuestral1);
                    lienzo.tlcDato7TV.setText("="+tamMuestral2);
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza, primero es necesario calcular el valor del determinante que posteriormente vamos a buscar en las tablas de la distribución acumulada normal estándar, el determinante a calcular está encerrado entre paréntesis en la fórmula siguiente: ");
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Cálculo del determinante: ");
                    lienzo.textView15.setText("("+diferenciaMedias+"- "+limInferior+")/√[("+varianza1+"/"+tamMuestral1+")+("+varianza2+"/"+tamMuestral2+")] = " + teoremaDifMedias1.getDeterminante());
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso2:\n\nUna vez calculado el determinante procedemos a buscar dicho valor["+teoremaDifMedias1.getDeterminante()+"] en las tablas de la distribución acumulada normal estándar, en éste caso el valor encontrado es: " + teoremaDifMedias1.getValTablas()+ "\n\nUna vez encontrado dicho valor nos limitamos a sustituir los correspondientes valores en la fórmula presentada en el paso 1: \n\n\n \t\t\t\t1-α = (2*" + teoremaDifMedias1.getValTablas()+") - 1 = " + coeficienteConfianza);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
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
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
                if(faltaValLimite()){
                    desplegarNotificacion("Por favor ingresa los datos marcados con rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.BLACK);
                    lienzo.cambia8.setTextColor(Color.BLACK);
                    lienzo.cambia9.setTextColor(Color.BLACK);
                    lienzo.cambia10.setTextColor(Color.RED);

                }else{
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    promedioMuestral1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    promedioMuestral2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestral1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                    tamMuestral2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    varianza1 = Double.parseDouble(lienzo.significancia.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.miu1.getText().toString());
                    limSuperior = Double.parseDouble(lienzo.editTextCambia10.getText().toString());
                    double diferenciaMedias = promedioMuestral1 - promedioMuestral2;
                    diferenciaMedias = tablas.redondeoDecimales(diferenciaMedias,6);
                    teoremaDifMedias1 = new ICFDIFMEDIASVARIANZASCONOCIDAS(tamMuestral1,tamMuestral2,varianza1,varianza2,diferenciaMedias,limSuperior,'b');
                    double coeficienteConfianza = teoremaDifMedias1.getCoeficienteConfianza();
                    valtablas = teoremaDifMedias1.getValTablas();
                    lienzo.output.setText("El nivel de confianza requerido es de: " + coeficienteConfianza);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzaicdifmedias1);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.limsupcoeficienteconfianzadifmedias1);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.promedio1);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.promedio2);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.b);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.tlcvarianza1);
                    lienzo.tlcImageDato5.setImageResource(R.drawable.tlcvarianza2);
                    lienzo.tlcImageDato6.setImageResource(R.drawable.tlcn1);
                    lienzo.tlcImageDato7.setImageResource(R.drawable.tlcn2);
                    lienzo.tlcDato1TV.setText("="+promedioMuestral1);
                    lienzo.tlcDato2TV.setText("="+promedioMuestral2);
                    lienzo.tlcDato3TV.setText("="+limSuperior);
                    lienzo.tlcDato4TV.setText("="+varianza1);
                    lienzo.tlcDato5TV.setText("="+varianza2);
                    lienzo.tlcDato6TV.setText("="+tamMuestral1);
                    lienzo.tlcDato7TV.setText("="+tamMuestral2);
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza, primero es necesario calcular el valor del determinante que posteriormente vamos a buscar en las tablas de la distribución acumulada normal estándar, el determinante a calcular está encerrado entre paréntesis en la fórmula siguiente: ");
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Cálculo del determinante: ");
                    lienzo.textView15.setText("[("+diferenciaMedias+"- "+limSuperior+")/√(("+varianza1+"/"+tamMuestral1+")+("+varianza2+"/"+tamMuestral2+"))] = " + teoremaDifMedias1.getDeterminante());
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso2:\n\nUna vez calculado el determinante procedemos a buscar dicho valor["+teoremaDifMedias1.getDeterminante()+"] en las tablas de la distribución acumulada normal estándar, en éste caso el vaor encontrado es: " + teoremaDifMedias1.getValTablas()+ "\n\nUna vez encontrado dicho valor nos limitamos a sustituir los correspondientes valores en la fórmula presentada en el paso 1: \n\n\n \t\t\t\t1-α = 1- (2*" + teoremaDifMedias1.getValTablas()+") = " + coeficienteConfianza);
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
                lienzo.cambia9.setTextColor(Color.BLACK);
                lienzo.cambia10.setTextColor(Color.BLACK);
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
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.output.setText("");
            }
        });



    }

    public boolean faltaDato(){
       if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()) return true;
       else return false;
    }

    public boolean faltaErrorMuestral(){
        if(lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty()||lienzo.editTextCambia8.getText().toString().isEmpty()){
            return true;
        }else return false;
    }

    public boolean faltaValLimite(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()||(lienzo.editTextCambia9.getText().toString().isEmpty()&&lienzo.editTextCambia10.getText().toString().isEmpty())) return true;
        else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }
}