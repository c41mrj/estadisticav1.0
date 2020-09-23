package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class INTERVALOSCONFIANZAIU2 extends AppCompatActivity {

    private ActivityPruebaHipBinding lienzo;
    private double limInf,limSup,confianza,desviacionEstandar,promedioMuestral,valTablas,errorMuestral,coeficienteConfianza;
    private int tamMuestra,tamMinimoMuestra;
    private ICFMEDIADESEDESCONOCIDA teorema1;
    private ICFMEDIADESCONOCIDA teoremaAprox;
    private ICFVARIANZA icfvarianza;
    private CalculoTablas redondeo = new CalculoTablas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        this.setTitle("I.C para la media(σ desconocida)");
        lienzo.llc1.setVisibility(View.GONE);
        lienzo.LinearLayoutDato6.setVisibility(View.GONE);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.GONE);
        lienzo.imageCambia.setImageResource(R.drawable.desviacionestandar_muestralchi);
        lienzo.cambia2.setText("Desviacion estandar muestral");
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.cambia5.setText("Nivel de confianza:");
        lienzo.calcularIntervaloConfianza.setText("Calcular intervalo de confianza");
        lienzo.layoutPotencia.setVisibility(View.GONE);
        lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
        lienzo.cambia6.setText("Error muestral:");
        lienzo.button8.setVisibility(View.VISIBLE);
        lienzo.button8.setText("Calcular tamaño mínimo de la muestra");
        lienzo.cambia7.setText("limite inferior: ");
        lienzo.imageCambia7.setImageResource(R.drawable.a);
        lienzo.cambia8.setText("Limite superior: ");
        lienzo.imageCambia8.setImageResource(R.drawable.b);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.VISIBLE);
        lienzo.button2.setVisibility(View.VISIBLE);
        lienzo.button2.setText("Calcular grado de confianza si se conoce el limite inferior del I.C");
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setText("Calcular grado de confianza si se conoce el limite superior del I.C");
        lienzo.imCambia6.setImageResource(R.drawable.error_muestral);
        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
        lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                lienzo.layCalc1.setVisibility(View.VISIBLE);
                lienzo.layCalc2.setVisibility(View.VISIBLE);
                lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                lienzo.tablaDatos.setVisibility(View.VISIBLE);
                lienzo.teorema.setVisibility(View.VISIBLE);
                lienzo.textView5.setVisibility(View.VISIBLE);
                lienzo.tvDatos.setVisibility(View.VISIBLE);
                lienzo.limitsuperior.setVisibility(View.VISIBLE);
                lienzo.liminferior.setText("Limite inferior:");
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                if(faltaDato()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                }else{
                    if(Integer.parseInt(lienzo.tamMuestral.getText().toString())<=101){
                        lienzo.tableRowMiu.setVisibility(View.GONE);
                        lienzo.dtoOpcional.setVisibility(View.GONE);
                        lienzo.teorema.setImageResource(R.drawable.formula2);
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                        confianza = Double.parseDouble(lienzo.significancia.getText().toString());
                        desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                        tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                        promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                        teorema1 = new ICFMEDIADESEDESCONOCIDA(desviacionEstandar,tamMuestra,confianza,promedioMuestral);
                        limInf = teorema1.calcLimInf();
                        limInf = teorema1.tablas.redondeoDecimales(limInf,6);
                        limSup = teorema1.calcLimSup();
                        limSup = teorema1.tablas.redondeoDecimales(limSup,6);
                        valTablas = teorema1.getValorTablas();
                        lienzo.output.setText("El intervalo de confianza es: "+limInf+"<μ<"+limSup);
                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.paso1Phip.setText("Para calcular los límites de confianza se hace necesaria la busqueda del valor: (1-" + confianza+")/2 = " +redondeo.redondeoDecimales(((1-confianza)/2),6) +" en las tablas de la distribución t-student con n-1 grados de libertad, en éste caso el valor en tablas es: " + valTablas+". Obtenido el valor nos limitaremos a sustituir valores en las siguientes fórmulas:" );
                        lienzo.calcOp1.setImageResource(R.drawable.formula2liminf);
                        lienzo.calculo1.setText("Límite inferior: ");
                        lienzo.calculo2.setText("Límite superior: ");
                        lienzo.calcOp2.setImageResource(R.drawable.formula2limsup);
                        lienzo.imageView.setVisibility(View.GONE);
                        lienzo.sustLimSup.setVisibility(View.GONE);
                        lienzo.textView15.setText(promedioMuestral+"-["+valTablas+"*("+desviacionEstandar+"/√"+tamMuestra+")] = " +limInf);
                        lienzo.zeta2.setText(promedioMuestral+"+["+valTablas+"*("+desviacionEstandar+"/√"+tamMuestra+")]= " + limSup);
                        lienzo.paso2Phipodc.setVisibility(View.GONE);
                        lienzo.potenciaPrueba.setVisibility(View.GONE);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.desviation.setImageResource(R.drawable.desviacionestandar_muestralchi);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val3.setText("="+confianza);
                        lienzo.val4.setText("="+desviacionEstandar);
                        lienzo.val5.setText("="+tamMuestra);
                        lienzo.dtoOpcional.setVisibility(View.VISIBLE);
                        lienzo.imageDato6.setImageResource(R.drawable.talfamedios);
                        lienzo.val6.setText("="+valTablas);
                        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                        String intervalo = limInf+"<μ<"+limSup;
                        double prob = confianza *100;
                        prob = teorema1.tablas.redondeoDecimales(prob,6);
                        lienzo.paso2.setText("Conclusión:\n\nLa media de nuestra muestra se encontrará entre los valores:\n\n" + intervalo + "\n\nEl " + prob +"% de las veces.");
                    }else{
                        lienzo.tableRowMiu.setVisibility(View.GONE);
                        lienzo.dtoOpcional.setVisibility(View.GONE);
                        lienzo.teorema.setImageResource(R.drawable.formula3);
                        lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                        confianza = Double.parseDouble(lienzo.significancia.getText().toString());
                        desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                        tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                        promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                        teoremaAprox = new ICFMEDIADESCONOCIDA(desviacionEstandar,tamMuestra,confianza,promedioMuestral);
                        limInf = teoremaAprox.calcLimInf();
                        limInf = teoremaAprox.tablas.redondeoDecimales(limInf,6);
                        limSup = teoremaAprox.calcLimSup();
                        limSup = teoremaAprox.tablas.redondeoDecimales(limSup,6);
                        valTablas = teoremaAprox.getValorTablas();
                        lienzo.output.setText("El intervalo de confianza es: "+limInf+"<μ<"+limSup);
                        lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                        lienzo.teorema.setVisibility(View.VISIBLE);
                        lienzo.paso1Phip.setText("Para calcular el intervalo de confianza es necesario hacer una aproximacion por la distribucion normal estandar, puesto que las tablas de la distribucion t-student solo continen valores para hasta 100 grados de libertad.\n\n Por lo que tendremos que buscar el valor de: (1-" + confianza+")/2 = " +redondeo.redondeoDecimales(((1-confianza)/2),6) +" en las tablas de la distribucion normal estandar de 0 a z, en éste caso el valor en tablas es: " + valTablas+". Obtenido el valor nos limitaremos a sustituir valores en las siguientes fórmulas:" );
                        lienzo.calcOp1.setImageResource(R.drawable.formula3liminf);
                        lienzo.calculo1.setText("Límite inferior: ");
                        lienzo.calculo2.setText("Límite superior: ");
                        lienzo.calcOp2.setImageResource(R.drawable.formula3limsup);
                        lienzo.imageView.setVisibility(View.GONE);
                        lienzo.sustLimSup.setVisibility(View.GONE);
                        lienzo.textView15.setText(promedioMuestral+"-["+valTablas+"*("+desviacionEstandar+"/√"+tamMuestra+")] = " +limInf);
                        lienzo.zeta2.setText(promedioMuestral+"+["+valTablas+"*("+desviacionEstandar+"/√"+tamMuestra+")]= " + limSup);
                        lienzo.paso2Phipodc.setVisibility(View.GONE);
                        lienzo.potenciaPrueba.setVisibility(View.GONE);
                        lienzo.layoutPotencia.setVisibility(View.GONE);
                        lienzo.desviation.setImageResource(R.drawable.desviacionestandar_muestralchi);
                        lienzo.val1.setText("="+promedioMuestral);
                        lienzo.val3.setText("="+confianza);
                        lienzo.val4.setText("="+desviacionEstandar);
                        lienzo.val5.setText("="+tamMuestra);
                        lienzo.dtoOpcional.setVisibility(View.VISIBLE);
                        lienzo.imageDato6.setImageResource(R.drawable.zetaalfamedios);
                        lienzo.val6.setText("="+valTablas);
                        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                        String intervalo = limInf+"<μ<"+limSup;
                        double prob = confianza *100;
                        prob = teorema1.tablas.redondeoDecimales(prob,6);
                        lienzo.paso2.setText("Conclusión:\n\nLa media de nuestra muestra se encontrará entre los valores:\n\n" + intervalo + "\n\nEl " + prob +"% de las veces.");
                    }
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
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaErrorMuestral()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                }else{
                    confianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    errorMuestral = Double.parseDouble(lienzo.miu1.getText().toString());
                    teorema1 = new ICFMEDIADESEDESCONOCIDA(desviacionEstandar,confianza,(float)errorMuestral);
                    valTablas = teorema1.getValorTablas();
                    tamMinimoMuestra = teorema1.obtenerTamMinimoMuestra();
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.output.setText("El tamaño mínimo de la muestra requerido para cumplir con la condición requerida es: " + tamMinimoMuestra);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.tamaniomuestralformula2);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.error_muestral);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.tlcDato1TV.setText("="+valTablas);
                    lienzo.tlcDato2TV.setText("="+errorMuestral);
                    lienzo.tlcDato3TV.setText("="+(Math.pow(desviacionEstandar,2)));
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Para calcular el tamaño mínimo de muestra se hace necesario primero obtener el valor de : (1-"+confianza+")/2, en éste caso el valor encontrado en tablas es: " + valTablas + ". Posteriormente procedemos a sustituir los valores correspondientes en la fórmula:");
                    lienzo.textView15.setText("[("+valTablas+"/"+errorMuestral+")^2 * ("+desviacionEstandar+")^2] + 1 = " + tamMinimoMuestra);
                    lienzo.liminferior.setText("Tamaño mínimo de muestra:");
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.llz4.setVisibility(View.GONE);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.llz8.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.teorema.setVisibility(View.GONE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                }
            }
        });

        lienzo.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                if(faltaLimite()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }else if(Integer.parseInt(lienzo.tamMuestral.getText().toString())>101){
                   desplegarNotificacion("Imposible calcular el coeficiente de confianza");
                   desplegarNotificacion("Tamaño de muestra muy grande");
                   lienzo.output.setText("");
                   lienzo.verProcedimiento.setVisibility(View.GONE);
                }else{
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    limInf = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    teorema1 = new ICFMEDIADESEDESCONOCIDA(promedioMuestral,tamMuestra,desviacionEstandar);
                    coeficienteConfianza = teorema1.calcGradoConfianza('a',limInf);
                    limSup = (2*promedioMuestral)-limInf;
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"= " + coeficienteConfianza*100 + "%\n\nCon un límite superior de:"+limSup);
                    valTablas = teorema1.getValorTablas();
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.coeficienteconfianza2liminf);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.a);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_a);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.desviacionestandar_muestralchi);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato1TV.setText("="+limInf);
                    lienzo.tlcDato2TV.setText("="+promedioMuestral);
                    lienzo.tlcDato3TV.setText("="+desviacionEstandar);
                    lienzo.tlcDato4TV.setText("="+tamMuestra);
                    lienzo.llz4.setVisibility(View.VISIBLE);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza cuando se desconoce σ se hace necesario primero calcular el valor del determinante que posteriormente buscaremos en las tablas de la función acumulada t-student en el renglón de n-1="+(tamMuestra-1)+" grados de libertad. El determinante a calcular está entre corchetes en la fórmula siguiente.En éste caso el determinante calculado es igual a:"+teorema1.getDeterminante());
                    lienzo.textView15.setText("[(("+promedioMuestral+"-"+limSup+")÷"+desviacionEstandar+")*√"+tamMuestra+"]=" + teorema1.getDeterminante());
                    lienzo.teorema.setImageResource(R.drawable.coeficienteconfianza2);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    String linea;
                    linea = ("Una vez calculado el valor del determinante, se procede a buscarlo en las tablas de la distribución acumulada t-student en el renglón de n-1="+(tamMuestra-1)+", en éste caso el valor encontrado en tablas es: " + teorema1.getValorTablas()+".\n\nPor último se hace necesario sustituir los valores correspondientes en la fórmula: \n\n\t\t1-α= (2*"+teorema1.getValorTablas()+")-1≈ "+coeficienteConfianza);
                    linea = linea + ("\n\nPara conocer el limite superior del intervalo de confianza podemos apoyarnos en la siguiente igualdad\n\n[x-a=b-x]\n\nDonde 'x' es el promedio muestral, a y b son los limites del intervalo.\nPuesto que se requiere conocer el limite superior, despejamos la igualdad obteniendo: b=2x-a. Sustituyendo valores llegamos al resultado:\n\n\t\t\ta=(2*"+promedioMuestral+")-"+limInf+"="+limSup);
                    lienzo.paso2.setText(linea);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Determinante:");
                }
            }
        });

        lienzo.otroBotonAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                if(faltaLimite()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                }else if(Integer.parseInt(lienzo.tamMuestral.getText().toString())>101){
                    desplegarNotificacion("Imposible calcular el coeficiente de confianza");
                    desplegarNotificacion("Tamaño de muestra muy grande");
                    lienzo.output.setText("");
                }else{
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    limSup = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    teorema1 = new ICFMEDIADESEDESCONOCIDA(promedioMuestral,tamMuestra,desviacionEstandar);
                    coeficienteConfianza = teorema1.calcGradoConfianza('b',limSup);
                    limInf = (2*promedioMuestral)-limSup;
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"= " + coeficienteConfianza*100 + "%\n\nCon un límite inferior de: " + limInf);
                    valTablas = teorema1.getValorTablas();
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.coeficienteconfianza2limsup);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.b);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_a);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.desviacionestandar_muestralchi);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato1TV.setText("="+limSup);
                    lienzo.tlcDato2TV.setText("="+promedioMuestral);
                    lienzo.tlcDato3TV.setText("="+desviacionEstandar);
                    lienzo.tlcDato4TV.setText("="+tamMuestra);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza cuando se desconoce σ se hace necesario primero calcular el valor del determinante que posteriormente buscaremos en las tablas de la función acumulada t-student en el renglón de n-1="+(tamMuestra-1)+" grados de libertad. El determinante a calcular está entre corchetes en la fórmula siguiente.En éste caso el determinante calculado es igual a:"+teorema1.getDeterminante());
                    lienzo.textView15.setText("[(("+promedioMuestral+"-"+limSup+")÷"+desviacionEstandar+")*√"+tamMuestra+"]=" + teorema1.getDeterminante());
                    lienzo.teorema.setImageResource(R.drawable.coeficienteconfianza2);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.paso2.setText("Para conocer el limite inferior del intervalo de confianza podemos apoyarnos en la siguiente igualdad[x-a=b-x], donde 'x' es el promedio muestral, a y b son los limites del intervalo.\nPuesto que se requiere conocer el limite inferior, despejamos la igualdad obteniendo: a=2x-b. Sustituyendo valores llegamos al resultado:\n\n\t\t\ta=(2*"+promedioMuestral+")-"+limSup+"="+limInf);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.liminferior.setText("Determinante:");
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    String linea;
                    linea = ("Una vez calculado el valor del determinante, se procede a buscarlo en las tablas de la distribución acumulada t-student en el renglón de n-1="+(tamMuestra-1)+", en éste caso el valor encontrado en tablas es: " + teorema1.getValorTablas()+".\n\nPor último se hace necesario sustituir los valores correspondientes en la fórmula: \n\n\t\t1-α= 1-(2*"+teorema1.getValorTablas()+")≈ "+coeficienteConfianza);
                    linea = linea + ("\n\nPara conocer el limite superior del intervalo de confianza podemos apoyarnos en la siguiente igualdad\n\n[x-a=b-x]\n\nDonde 'x' es el promedio muestral, a y b son los limites del intervalo.\nPuesto que se requiere conocer el limite inferior, despejamos la igualdad obteniendo: a=2x-b. Sustituyendo valores llegamos al resultado:\n\n\t\t\tb=(2*"+promedioMuestral+")-"+limSup+"="+limInf);
                    lienzo.paso2.setText(linea);
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
                lienzo.promedioMuestral.setText("");
                lienzo.tamMuestral.setText("");
                lienzo.significancia.setText("");
                lienzo.miu1.setText("");
                lienzo.editTextCambia7.setText("");
                lienzo.editTextCambia8.setText("");
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.cambia8.setTextColor(Color.BLACK);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.output.setText("");
                lienzo.procedimiento.setVisibility(View.GONE);
            }
        });

    }

    public boolean faltaDato(){
        if(lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltaErrorMuestral(){
        if(lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()) return true;
        else return false;
    }
    public boolean faltaLimite(){
        if(lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||(lienzo.editTextCambia7.getText().toString().isEmpty()&&lienzo.editTextCambia8.getText().toString().isEmpty())||lienzo.promedioMuestral.getText().toString().isEmpty()) return true;
        else return false;
    }



    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }

    public boolean faltaDesviacionEstandar(){
        if(lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty())return true;
        else return false;
    }

}
