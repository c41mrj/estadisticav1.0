package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class INTERFAZICDIFMEDIAS2 extends AppCompatActivity {


    private ActivityPruebaHipBinding lienzo;
    private ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES teoremaDiferenciaMedias2;
    private double valTablas,sp,limInf,limSup,promedio1,promedio2,diferenciaPromedios,varianzaM1,varianzaM2,coeficienteConfianza,nivelConfianza;
    private int tamMuestral1,tamMuestral2;
    private CalculoTablas redondeo = new CalculoTablas();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
            lienzo.constraintLayoutButtons.setVisibility(View.GONE);
            lienzo.imageViewCambia1.setImageResource(R.drawable.promedio1);
            lienzo.imageCambia.setImageResource(R.drawable.promedio2);
            lienzo.imageCambia2.setImageResource(R.drawable.tlcn1);
            lienzo.imageCambia3.setImageResource(R.drawable.tlcn2);
            lienzo.imageCambia4.setImageResource(R.drawable.scuadrada1);
            lienzo.imCambia6.setImageResource(R.drawable.scuadrada2);
            lienzo.imageCambia7.setImageResource(R.drawable.dato_g);
            lienzo.imageCambia8.setImageResource(R.drawable.a);
            lienzo.imageCambia9.setImageResource(R.drawable.b);
            lienzo.LinearLayoutDato8Opcional.setVisibility(View.VISIBLE);
            lienzo.LinearLayoutDato9Opcional.setVisibility(View.VISIBLE);
            lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
            lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
            lienzo.potenciaPrueba.setVisibility(View.GONE);
            lienzo.layoutPotencia.setVisibility(View.GONE);
            lienzo.cambia1.setText("Promedio muestral 1:");
            lienzo.cambia2.setText("Promedio muestral 2:");
            lienzo.cambia3.setText("Tamaño de muestra 1:");
            lienzo.cambia4.setText("Tamaño de muestra 2:");
            lienzo.cambia5.setText("Varianza de muestra 1:");
            lienzo.cambia6.setText("Varianza de muestra 2:");
            lienzo.cambia7.setText("Significancia:");
            lienzo.cambia8.setText("Límite inferior del I.C:");
            lienzo.cambia9.setText("Límite superior del I.C");
            lienzo.procedimiento.setVisibility(View.GONE);
            lienzo.verProcedimiento.setVisibility(View.GONE);
            lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
            lienzo.calcularIntervaloConfianza.setText("Calcular intervalo de confianza");
            lienzo.button2.setText("Calcular nivel de confianza si se conoce el límite superior del intervalo de confianza");
            lienzo.button2.setVisibility(View.VISIBLE);
            lienzo.button8.setText("Calcular nivel de confianza si se conoce el límite inferior del intervalo de confianza");
            lienzo.button8.setVisibility(View.VISIBLE);


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
                    lienzo.verProcedimiento.setVisibility(View.GONE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    if(faltaDato()){
                        desplegarNotificacion("Por favor ingresa todos los datos marcados en rojo");
                        lienzo.cambia1.setTextColor(Color.RED);
                        lienzo.cambia2.setTextColor(Color.RED);
                        lienzo.cambia3.setTextColor(Color.RED);
                        lienzo.cambia4.setTextColor(Color.RED);
                        lienzo.cambia5.setTextColor(Color.RED);
                        lienzo.cambia6.setTextColor(Color.RED);
                        lienzo.cambia7.setTextColor(Color.RED);
                        lienzo.cambia8.setTextColor(Color.BLACK);
                    }else{
                        lienzo.layoutCotaInferior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Límite inferior:");
                        lienzo.limitsuperior.setVisibility(View.VISIBLE);
                        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                        promedio1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                        promedio2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                        diferenciaPromedios = promedio1-promedio2;
                        diferenciaPromedios = redondeo.redondeoDecimales(diferenciaPromedios,6);
                        tamMuestral1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                        tamMuestral2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                        varianzaM1 = Double.parseDouble(lienzo.significancia.getText().toString());
                        varianzaM2 = Double.parseDouble(lienzo.miu1.getText().toString());
                        nivelConfianza = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                        teoremaDiferenciaMedias2 = new ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES(varianzaM1,varianzaM2,tamMuestral1,tamMuestral2,diferenciaPromedios,nivelConfianza);
                        limInf = teoremaDiferenciaMedias2.getLimInf();
                        limInf = teoremaDiferenciaMedias2.tabla.redondeoDecimales(limInf,6);
                        limSup = teoremaDiferenciaMedias2.getLimSup();
                        limSup = teoremaDiferenciaMedias2.tabla.redondeoDecimales(limSup,6);
                        String intervalo = limInf+" <μ1-μ2< "+limSup;
                        lienzo.output.setText("El intervalo de confianza que cumple con las condiciones del problema es de: " + intervalo);
                        lienzo.teorema.setImageResource(R.drawable.teoremaicdifmedias2);
                        lienzo.paso1Phip.setText("Para calcular el intervalo de confianza primero tenemos que calcular el valor de la estimación de la desviación estándar poblacional(Sp), para ello únicamente sutituiremos los correspondientes valores en la siguiente fórmula obteniendo así una estimación de: " + teoremaDiferenciaMedias2.getSp());
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                        lienzo.imageView2.setImageResource(R.drawable.sp);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.scuadrada1);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.scuadrada2);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.tlcn1);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.tlcn2);
                        lienzo.tlcDato1TV.setText("="+varianzaM1);
                        lienzo.tlcDato2TV.setText("="+varianzaM2);
                        lienzo.tlcDato3TV.setText("="+tamMuestral1);
                        lienzo.tlcDato4TV.setText("="+tamMuestral2);
                        lienzo.llz5.setVisibility(View.GONE);
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.llz8.setVisibility(View.GONE);
                        lienzo.tablaDatos.setVisibility(View.VISIBLE);
                        lienzo.imagePromedio.setImageResource(R.drawable.esepe);
                        lienzo.val1.setText("="+teoremaDiferenciaMedias2.getSp());
                        lienzo.imageMiu.setImageResource(R.drawable.talfamedios);
                        lienzo.val2.setText("="+teoremaDiferenciaMedias2.getValTablas());
                        lienzo.imageSignificancia.setImageResource(R.drawable.tlcn1);
                        lienzo.val3.setText("="+tamMuestral1);
                        lienzo.desviation.setImageResource(R.drawable.tlcn2);
                        lienzo.val4.setText("="+tamMuestral2);
                        lienzo.imageN.setImageResource(R.drawable.scuadrada1);
                        lienzo.val5.setText("="+varianzaM1);
                        lienzo.dtoOpcional.setVisibility(View.VISIBLE);
                        lienzo.imageDato6.setImageResource(R.drawable.scuadrada2);
                        lienzo.val6.setText("="+varianzaM2);
                        lienzo.tableRow7.setVisibility(View.VISIBLE);
                        lienzo.imageDato7.setImageResource(R.drawable.promedio1);
                        lienzo.val7.setText("="+promedio1);
                        lienzo.tableRow8.setVisibility(View.VISIBLE);
                        lienzo.imageDato8.setImageResource(R.drawable.promedio2);
                        lienzo.val8.setText("="+promedio2);
                        lienzo.layCalc1.setVisibility(View.VISIBLE);
                        lienzo.layCalc2.setVisibility(View.VISIBLE);
                        lienzo.calcOp1.setVisibility(View.GONE);
                        lienzo.calculo1.setText("Paso 2: \n\nUna vez calculado el valor de nuestra estimación Sp, procedemos a buscar el valor de:[(1-"+nivelConfianza+")/2] = "+teoremaDiferenciaMedias2.tabla.redondeoDecimales(((1-nivelConfianza)/2),5)+" en las tablas t-student para areas derechas con v=n1+n2-2 grados de libertad, en éste caso el valor encontrado en tablas es de: " + teoremaDiferenciaMedias2.getValTablas()+". \n\nUna vez encontrado el valor en las tablas procedemos a sustituir los respectivos valores en las fórmula correspondiente:");
                        lienzo.calculo1.setVisibility(View.VISIBLE);
                        lienzo.calculo2.setVisibility(View.VISIBLE);
                        lienzo.calculo2.setText("Para el límite inferior del intervalo:");
                        lienzo.calcOp2.setVisibility(View.VISIBLE);
                        lienzo.calcOp2.setImageResource(R.drawable.liminfteoremaicdifmedias2);
                        lienzo.LinearLayoutLimSup.setVisibility(View.VISIBLE);
                        lienzo.imZ.setVisibility(View.VISIBLE);
                        lienzo.imZ.setImageResource(R.drawable.limsupteoremaicdifmedias2);
                        lienzo.obtenerZ.setText("Para el límite superior del intervalo:");
                        lienzo.obtenerZ.setVisibility(View.VISIBLE);
                        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                        lienzo.imageView.setVisibility(View.GONE);
                        lienzo.sustLimSup.setVisibility(View.GONE);
                        lienzo.textView15.setText(diferenciaPromedios+"-("+teoremaDiferenciaMedias2.getValTablas()+"*Sp√((1/"+tamMuestral1+")+(1/"+tamMuestral2+")) = " + limInf);
                        lienzo.zeta2.setText(diferenciaPromedios+"+("+teoremaDiferenciaMedias2.getValTablas()+"*Sp√((1/"+tamMuestral1+")+(1/"+tamMuestral2+")) = " + limSup);
                        lienzo.paso2.setText("Conclusión:\n\nCon una confianza de " + nivelConfianza + " el valor de la diferencia de medias estará incluido en el intervalo: " + intervalo);
                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.tvDatos.setVisibility(View.VISIBLE);
                        lienzo.textView6.setVisibility(View.GONE);
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
                    lienzo.verProcedimiento.setVisibility(View.GONE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    if(faltaValLimite()){
                        desplegarNotificacion("Por favor ingresa todos los datos marcados en rojo");
                        lienzo.cambia1.setTextColor(Color.RED);
                        lienzo.cambia2.setTextColor(Color.RED);
                        lienzo.cambia3.setTextColor(Color.RED);
                        lienzo.cambia4.setTextColor(Color.RED);
                        lienzo.cambia5.setTextColor(Color.RED);
                        lienzo.cambia6.setTextColor(Color.RED);
                        lienzo.cambia7.setTextColor(Color.BLACK);
                        lienzo.cambia8.setTextColor(Color.BLACK);
                        lienzo.cambia9.setTextColor(Color.RED);
                    }else{

                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.tablaDatos.setVisibility(View.GONE);
                        limSup = Double.parseDouble(lienzo.editTextCambia9.getText().toString());
                        promedio1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                        promedio2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                        diferenciaPromedios = promedio1 - promedio2;
                        diferenciaPromedios = redondeo.redondeoDecimales(diferenciaPromedios,5);
                        tamMuestral1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                        tamMuestral2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                        varianzaM1 = Double.parseDouble(lienzo.significancia.getText().toString());
                        varianzaM2 = Double.parseDouble(lienzo.miu1.getText().toString());
                        teoremaDiferenciaMedias2 = new ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES(tamMuestral1,tamMuestral2,varianzaM1,varianzaM2,diferenciaPromedios,limSup,'b');
                        sp = teoremaDiferenciaMedias2.getSp();
                        coeficienteConfianza = teoremaDiferenciaMedias2.getCoeficienteConfianza();
                        lienzo.output.setText("El coeficiente de confianza que se requiere para satisfacer las condiciones del problema es de: " + coeficienteConfianza + "≈" + teoremaDiferenciaMedias2.tabla.redondeoDecimales((coeficienteConfianza*100),6)+"%");
                        lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzateoremaicdifmedias2);
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                        lienzo.imageView2.setImageResource(R.drawable.limsupcoeficienteconfianzateoremaicdifmedias2);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.b);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.esepe);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.difpromedios);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.tlcn1);
                        lienzo.llz5.setVisibility(View.VISIBLE);
                        lienzo.tlcImageDato5.setImageResource(R.drawable.tlcn2);
                        lienzo.tlcDato1TV.setText("="+limSup);
                        lienzo.tlcDato2TV.setText("="+sp);
                        lienzo.tlcDato3TV.setText("="+diferenciaPromedios);
                        lienzo.tlcDato4TV.setText("="+tamMuestral1);
                        lienzo.tlcDato5TV.setText("="+tamMuestral2);
                        lienzo.paso1Phip.setText("Ya que, el valor conocido es el del limite inferior del intervalo de confianza se utilizará la igualdad correspondiente en la fórmula presentada anteriormente, analizando dicha fórmula encontramos que es necesario calcular el  valor de la estimación común de la desviación estándar poblacional, por lo cual procedemos a calcularlo apoyándonos de la fórmula para su respectivo cálculo.\n\nEn éste caso el valor de la estimación es: " + sp );
                        lienzo.textView6.setVisibility(View.VISIBLE);
                        lienzo.textView6.setText("Paso 2:\n\nUna vez obtenido el valor vamos a calcular el valor del determinante(el cual está encerrado entre corchetes en el teorema siguiente) que posteriormente buscaremos en las tablas acumuladas de la distribución t-student para areas derechas con v=n1+n2-2 grados de libertad.\n\nEn éste caso el valor del determinante es: " + teoremaDiferenciaMedias2.getDeterminante() + ", buscando el valor en las tablas de la distribución t-student con: " + (tamMuestral1+tamMuestral2-2) +" grados de libertad encontramos que es igual a: " + teoremaDiferenciaMedias2.getValTablas());
                        lienzo.layoutCotaInferior.setVisibility(View.VISIBLE);
                        lienzo.imageView3.setImageResource(R.drawable.sp);
                        lienzo.imageView5.setImageResource(R.drawable.tlcn1);
                        lienzo.imageView4.setImageResource(R.drawable.tlcn2);
                        lienzo.imageView7.setImageResource(R.drawable.scuadrada1);
                        lienzo.imageView6.setImageResource(R.drawable.scuadrada2);
                        lienzo.textView2.setText("="+tamMuestral1);
                        lienzo.textView.setText("="+tamMuestral2);
                        lienzo.textView4.setText("="+varianzaM1);
                        lienzo.textView3.setText("="+varianzaM2);
                        lienzo.layCalc1.setVisibility(View.GONE);
                        lienzo.layCalc2.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Coeficiente de confianza:");
                        lienzo.textView15.setText("1-α=1-(2*"+teoremaDiferenciaMedias2.getValTablas()+") = " + coeficienteConfianza);
                        lienzo.imageView.setVisibility(View.GONE);
                        lienzo.layoutDesaparece2.setVisibility(View.GONE);
                        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                        lienzo.potenciaPrueba.setVisibility(View.GONE);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        coeficienteConfianza = coeficienteConfianza * 100;
                        coeficienteConfianza = teoremaDiferenciaMedias2.tabla.redondeoDecimales(coeficienteConfianza,6);
                        lienzo.paso2.setText("Conclusión:\n\n Con una confianza del " + coeficienteConfianza + "% el valor de la diferencia de medias de dos poblaciones normales o aproximadamente normales se encontrará dentro del intervalo con un límite superior de: " + limSup );
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
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
                    lienzo.verProcedimiento.setVisibility(View.GONE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    if(faltaValLimite()){
                        desplegarNotificacion("Por favor ingresa todos los datos marcados en rojo");
                        lienzo.cambia1.setTextColor(Color.RED);
                        lienzo.cambia2.setTextColor(Color.RED);
                        lienzo.cambia3.setTextColor(Color.RED);
                        lienzo.cambia4.setTextColor(Color.RED);
                        lienzo.cambia5.setTextColor(Color.RED);
                        lienzo.cambia6.setTextColor(Color.RED);
                        lienzo.cambia7.setTextColor(Color.BLACK);
                        lienzo.cambia8.setTextColor(Color.RED);
                        lienzo.cambia9.setTextColor(Color.BLACK);
                    }else{
                        lienzo.tablaDatos.setVisibility(View.GONE);
                        limInf = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                        promedio1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                        promedio2 = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                        double diferenciaPromedios = promedio1 - promedio2;
                        diferenciaPromedios = redondeo.redondeoDecimales(diferenciaPromedios,5);
                        tamMuestral1 = Integer.parseInt(lienzo.promedioMuestral.getText().toString());
                        tamMuestral2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                        varianzaM1 = Double.parseDouble(lienzo.significancia.getText().toString());
                        varianzaM2 = Double.parseDouble(lienzo.miu1.getText().toString());
                        teoremaDiferenciaMedias2 = new ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES(tamMuestral1,tamMuestral2,varianzaM1,varianzaM2,diferenciaPromedios,limInf,'a');
                        sp = teoremaDiferenciaMedias2.getSp();
                        coeficienteConfianza = teoremaDiferenciaMedias2.getCoeficienteConfianza();
                        lienzo.output.setText("El coeficiente de confianza que se requiere para satisfacer las condiciones del problema es de: " + coeficienteConfianza + "≈" + teoremaDiferenciaMedias2.tabla.redondeoDecimales((coeficienteConfianza*100),6));
                        lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzateoremaicdifmedias2);
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                        lienzo.imageView2.setImageResource(R.drawable.liminfcoeficienteconfianzateoremaicdifmedias2);
                        lienzo.tlcimageDato1.setImageResource(R.drawable.a);
                        lienzo.tlcImageDato2.setImageResource(R.drawable.esepe);
                        lienzo.tlcImageDato3.setImageResource(R.drawable.difpromedios);
                        lienzo.tlcImageDato4.setImageResource(R.drawable.tlcn1);
                        lienzo.llz5.setVisibility(View.VISIBLE);
                        lienzo.tlcImageDato5.setImageResource(R.drawable.tlcn2);
                        lienzo.tlcDato1TV.setText("="+limInf);
                        lienzo.tlcDato2TV.setText("="+sp);
                        lienzo.tlcDato3TV.setText("="+diferenciaPromedios);
                        lienzo.tlcDato4TV.setText("="+tamMuestral1);
                        lienzo.tlcDato5TV.setText("="+tamMuestral2);
                        lienzo.paso1Phip.setText("Ya que, el valor conocido es el del limite inferior del intervalo de confianza se utilizará la igualdad correspondiente en la fórmula presentada anteriormente, analizando dicha fórmula encontramos que es necesario calcular el  valor de la estimación común de la desviación estándar poblacional, por lo cual procedemos a calcularlo apoyándonos de la fórmula para su respectivo cálculo. En éste caso el valor de la estimación es: " + sp );
                        lienzo.textView6.setVisibility(View.VISIBLE);
                        lienzo.textView6.setText("Paso 2:\n\nUna vez obtenido el valor vamos a calcular el valor del determinante(el cual está encerrado entre corchetes en el teorema siguiente) que posteriormente buscaremos en las tablas acumuladas de la distribución t-student para areas derechas con v=n1+n2-2 grados de libertad.\n\n En éste caso el valor del determinante es: " + teoremaDiferenciaMedias2.getDeterminante() + ", buscando el valor en las tablas de la distribución t-student con: " + (tamMuestral1+tamMuestral2-2) +" grados de libertad encontramos que es igual a: " + teoremaDiferenciaMedias2.getValTablas());
                        lienzo.layoutCotaInferior.setVisibility(View.VISIBLE);
                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.imageView3.setImageResource(R.drawable.sp);
                        lienzo.imageView5.setImageResource(R.drawable.tlcn1);
                        lienzo.imageView4.setImageResource(R.drawable.tlcn2);
                        lienzo.imageView7.setImageResource(R.drawable.scuadrada1);
                        lienzo.imageView6.setImageResource(R.drawable.scuadrada2);
                        lienzo.textView2.setText("="+tamMuestral1);
                        lienzo.textView.setText("="+tamMuestral2);
                        lienzo.textView4.setText("="+varianzaM1);
                        lienzo.textView3.setText("="+varianzaM2);
                        lienzo.layCalc1.setVisibility(View.GONE);
                        lienzo.layCalc2.setVisibility(View.GONE);
                        lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                        lienzo.limitsuperior.setVisibility(View.GONE);
                        lienzo.liminferior.setText("Coeficiente de confianza:");
                        lienzo.textView15.setText("1-α= (2*"+teoremaDiferenciaMedias2.getValTablas()+")-1 = " + coeficienteConfianza);
                        lienzo.imageView.setVisibility(View.GONE);
                        lienzo.layoutDesaparece2.setVisibility(View.GONE);
                        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                        lienzo.potenciaPrueba.setVisibility(View.GONE);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        coeficienteConfianza = coeficienteConfianza * 100;
                        coeficienteConfianza = teoremaDiferenciaMedias2.tabla.redondeoDecimales(coeficienteConfianza,6);
                        lienzo.paso2.setText("Conclusión:\n\n Con una confianza del " + coeficienteConfianza + "% el valor de la diferencia de medias de dos poblaciones normales o aproximadamente normales se encontrará dentro del intervalo con un límite inferior de: " + limInf );
                        lienzo.llz6.setVisibility(View.GONE);
                        lienzo.llz7.setVisibility(View.GONE);
                        lienzo.textView6.setVisibility(View.VISIBLE);
                        lienzo.textView6.setText("Paso 2:\n\nUna vez obtenido el valor vamos a calcular el valor del determinante(el cual está encerrado entre corchetes en el teorema siguiente) que posteriormente buscaremos en las tablas acumuladas de la distribución t-student para areas derechas con v=n1+n2-2 grados de libertad.\n\nEn éste caso el valor del determinante es: " + teoremaDiferenciaMedias2.getDeterminante() + ", buscando el valor en las tablas de la distribución t-student con: " + (tamMuestral1+tamMuestral2-2) +" grados de libertad encontramos que es igual a: " + teoremaDiferenciaMedias2.getValTablas());

                    }
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
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.output.setText("");
                }
            });


            lienzo.verProcedimiento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lienzo.procedimiento.setVisibility(View.VISIBLE);
                }
            });
    }

    public boolean faltaDato(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()||lienzo.editTextCambia7.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltaValLimite(){
            if(faltaDato()||(lienzo.editTextCambia8.getText().toString().isEmpty()&&lienzo.editTextCambia9.getText().toString().isEmpty())) return true;
            else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }

}