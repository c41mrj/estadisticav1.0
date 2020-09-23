package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class INTERVALOCONFIANZA1IU extends AppCompatActivity {
    private ActivityPruebaHipBinding lienzo;
    private double limInf,limSup,confianza,desviacionEstandar,promedioMuestral,valTablas,errorMuestral,coeficienteConfianza;
    private int tamMuestra,tamMinimoMuestra;
    private ICFMEDIADESCONOCIDA teorema1;
    private ICFVARIANZA icfvarianza;
    private CalculoTablas redondeo = new CalculoTablas();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        this.setTitle("I.C para la media(σ conocida)");
        lienzo.llc1.setVisibility(View.GONE);
        lienzo.LinearLayoutDato6.setVisibility(View.GONE);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.GONE);
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
        lienzo.button2.setText("Calcular grado de confianza si se conoce el limite inferior del I.C para la media");
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setText("Calcular grado de confianza si se conoce el limite superior del I.C para la media");
        lienzo.imCambia6.setImageResource(R.drawable.error_muestral);


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
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                if(faltaDato()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                }else{
                    lienzo.textView5.setVisibility(View.VISIBLE);
                    lienzo.liminferior.setText("Límite inferior:");
                    lienzo.limitsuperior.setText("Límite superior:");
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.tvDatos.setVisibility(View.VISIBLE);
                    lienzo.tableRowMiu.setVisibility(View.GONE);
                    lienzo.dtoOpcional.setVisibility(View.VISIBLE);
                    lienzo.teorema.setImageResource(R.drawable.formula1);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                    confianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    teorema1 = new ICFMEDIADESCONOCIDA(desviacionEstandar,tamMuestra,confianza,promedioMuestral);
                    limInf = teorema1.calcLimInf();
                    limSup = teorema1.calcLimSup();
                    valTablas = teorema1.getValorTablas();
                    lienzo.val1.setText("="+promedioMuestral);
                    lienzo.val3.setText("="+confianza);
                    lienzo.val4.setText("="+desviacionEstandar);
                    lienzo.val5.setText("=" + tamMuestra);
                    lienzo.val6.setText("="+valTablas);
                    lienzo.imageDato6.setImageResource(R.drawable.zetaalfamedios);
                    valTablas = teorema1.getValorTablas();
                    String intervalo = limInf+"<μ<"+limSup;
                    lienzo.output.setText("El intervalo de confianza es: "+limInf+"<μ<"+limSup);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.paso1Phip.setText("Para calcular los límites de confianza se hace necesaria la busqueda del valor: (1-" + confianza+")/2 = " + redondeo.redondeoDecimales(((1-confianza)/2),5) +" en las tablas de la distribución normal estándar de 0 a z, en éste caso el valor en tablas es: " + valTablas+". Obtenido el valor nos limitaremos a sustituir valores en las siguientes fórmulas:" );
                    lienzo.calcOp1.setImageResource(R.drawable.liminfformula1);
                    lienzo.calculo1.setText("Límite inferior: ");
                    lienzo.calculo2.setText("Límite superior: ");
                    lienzo.calcOp2.setImageResource(R.drawable.limsupformula1);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.textView15.setText(promedioMuestral+"-["+valTablas+"*("+desviacionEstandar+"/√"+tamMuestra+")] = " +limInf);
                    lienzo.zeta2.setText(promedioMuestral+"+["+valTablas+"*("+desviacionEstandar+"/√"+tamMuestra+")]=" + limSup);

                    lienzo.paso2Phipodc.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    double prob = confianza *100;
                    prob = teorema1.tablas.redondeoDecimales(prob,6);
                    lienzo.paso2.setText("Conclusión:\n\nLa media de nuestra muestra se encontrará entre los valores:\n\n" + intervalo + "\n\nEl " + prob +"% de las veces.");
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
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
                if(faltaDesviacionEstandar()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                }else{
                    lienzo.reglaDecision.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.zeta2.setVisibility(View.GONE);
                    lienzo.tableRowMiu.setVisibility(View.GONE);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.teorema.setImageResource(R.drawable.intervaloconfianzavarianzia);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                    confianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    icfvarianza = new ICFVARIANZA((Math.pow(desviacionEstandar,2)),tamMuestra,confianza);
                    limInf = icfvarianza.calcLimInf();
                    limSup = icfvarianza.calcLimSup();
                    valTablas = icfvarianza.getValorTablas();
                    double valTablas1 = icfvarianza.getValTablas1();
                    lienzo.output.setText("El intervalo de confianza es: "+limInf+"<σ^2<"+limSup);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.paso1Phip.setText("Para calcular los límites de confianza se hace necesaria la busqueda de los valores: "+ ((1-confianza)/2)  + " y :" + icfvarianza.getConfianza1()+" en las tablas de la distribución chi cuadrada con n-1 grados libertad, en éste caso el valor en tablas es: " + valTablas+" y " + valTablas1 +". Obtenido los valores nos limitaremos a sustituir valores en las siguientes fórmulas:" );
                    lienzo.calcOp1.setImageResource(R.drawable.liminfintervaloconfianzavarianza);
                    lienzo.calculo1.setText("Límite inferior: ");
                    lienzo.calculo2.setText("Límite superior: ");
                    lienzo.calcOp2.setImageResource(R.drawable.limsupintervaloconfianzavarianza);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.textView15.setText("("+(tamMuestra-1)+"*"+(Math.pow(desviacionEstandar,2))+")/" + valTablas + "= " +limInf);
                    lienzo.zeta2.setText("("+(tamMuestra-1)+"*"+(Math.pow(desviacionEstandar,2))+")/" + valTablas1 + "= " +limSup);
                    lienzo.paso2Phipodc.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
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
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaErrorMuestral()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                }else{
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    confianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    errorMuestral = Double.parseDouble(lienzo.miu1.getText().toString());
                    teorema1 = new ICFMEDIADESCONOCIDA(desviacionEstandar,confianza,(float)errorMuestral);
                    valTablas = teorema1.getValorTablas();
                    tamMinimoMuestra = teorema1.obtenerTamMinimoMuestra();
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.output.setText("El tamaño mínimo de la muestra requerido para cumplir con la condición requerida es: " + tamMinimoMuestra);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.tamaniomuestralformula1);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.zetaalfamedios);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.error_muestral);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.varianza_poblacionalchi);
                    lienzo.tlcDato1TV.setText("="+valTablas);
                    lienzo.tlcDato2TV.setText("="+errorMuestral);
                    lienzo.tlcDato3TV.setText("="+(Math.pow(desviacionEstandar,2)));
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Para calcular el tamaño mínimo de muestra se hace necesario primero obtener el valor de : (1-"+confianza+")/2, en éste caso el valor encontrado en tablas es: " + valTablas + ". Posteriormente procedemos a sustituir los valores correspondientes en la fórmula:");
                    lienzo.textView15.setText("[("+valTablas+"/"+errorMuestral+")^2 * ("+desviacionEstandar+")^2] + 1 = " + tamMinimoMuestra);
                    lienzo.liminferior.setText("Tamaño mínimo de muestra:");
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.llz4.setVisibility(View.GONE);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.llz8.setVisibility(View.GONE);
                    lienzo.teorema.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.textView5.setVisibility(View.GONE);
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
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaLimite()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }
                else{
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    limInf = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    teorema1 = new ICFMEDIADESCONOCIDA(promedioMuestral,tamMuestra,desviacionEstandar);
                    coeficienteConfianza = teorema1.calcGradoConfianza('a',limInf);
                    limSup = (2*promedioMuestral)-limInf;
                    limSup = redondeo.redondeoDecimales(limSup,6);
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"= " + coeficienteConfianza*100 + "%\n\nCon un lìmite superior de: " + limSup);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.imageView2.setImageResource(R.drawable.coeficienteconfianzaliminf);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.a);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_a);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.dato_e);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato1TV.setText("="+limInf);
                    lienzo.tlcDato2TV.setText("="+promedioMuestral);
                    lienzo.tlcDato3TV.setText("="+desviacionEstandar);
                    lienzo.tlcDato4TV.setText("="+tamMuestra);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    valTablas = teorema1.getValorTablas();
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza, primero es necesario calcular el valor del determinante que posteriormente vamos a buscar en las tablas de la distribución acumulada normal estándar, el determinante a calcular está encerrado entre paréntesis en la fórmula siguiente: ");
                    lienzo.liminferior.setText("Calculo del determinante:");
                    lienzo.textView15.setText("[("+promedioMuestral+"-"+limInf+")/"+desviacionEstandar+"]*√"+tamMuestra+"= " +teorema1.getDeterminante());
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setVisibility(View.VISIBLE );
                    lienzo.paso2.setText("Paso 2:\n\nUna vez calculado el valor del determinante, procedemos a buscar dicho valor["+teorema1.getDeterminante()+"] en las tablas de la distribución normal estándar, en éste caso el valor encontrado es: " + teorema1.getValorTablas()+"\n\nUna vez encontrado dicho valor nos limitamos a sustituir los correspondientes valores en la fórmula presentada en el paso 1:\n\n\t\t1-α=(2*"+valTablas+")-1 ≈ "+ coeficienteConfianza);
                    lienzo.teorema.setImageResource(R.drawable.coeficientedeconfianzamediavarconocida);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.textView5.setVisibility(View.VISIBLE);
                    String texto = lienzo.paso2.getText().toString();
                    texto = texto+"\n\nPara conocer el limite superior del intervalo de confianza podemos apoyarnos en la siguiente igualdad:\n\n[x-a=b-x]\n donde 'x' es el promedio muestral, a y b son los limites del intervalo.\nPuesto que se requiere conocer el limite superior, despejamos la igualdad obteniendo: b=2x-a. Sustituyendo valores llegamos al resultado:\n\n\t\t\tb=(2*"+promedioMuestral+")-"+limInf+"="+limSup;
                    lienzo.paso2.setText(texto);
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
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaLimite()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                }
                else{
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    limSup = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    teorema1 = new ICFMEDIADESCONOCIDA(promedioMuestral,tamMuestra,desviacionEstandar);
                    coeficienteConfianza = teorema1.calcGradoConfianza('b',limSup);
                    limInf = (2*promedioMuestral)-limSup;
                    limInf = redondeo.redondeoDecimales(limInf,6);
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"= " + coeficienteConfianza*100 + "%\n\nCon un lìmite inferior de: " + limInf+"");
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.imageView2.setImageResource(R.drawable.coeficienteconfianzalimsup);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.b);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_a);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.dato_e);
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
                    valTablas = teorema1.getValorTablas();
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza, primero es necesario calcular el valor del determinante que posteriormente vamos a buscar en las tablas de la distribución acumulada normal estándar, el determinante a calcular está encerrado entre paréntesis en la fórmula siguiente: ");
                    lienzo.liminferior.setText("Calculo del determinante:");
                    lienzo.textView15.setText("[("+promedioMuestral+"-"+limSup+")/"+desviacionEstandar+"]*√"+tamMuestra+"= " +teorema1.getDeterminante());
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso 2:\n\nUna vez calculado el valor del determinante, procedemos a buscar dicho valor["+teorema1.getDeterminante()+"] en las tablas de la distribución normal estándar, en éste caso el valor encontrado es: " + teorema1.getValorTablas()+"\n\nUna vez encontrado dicho valor nos limitamos a sustituir los correspondientes valores en la fórmula presentada en el paso 1:\n\n\t\t1-α=1-(2*"+valTablas+")≈ "+coeficienteConfianza);
                    lienzo.teorema.setImageResource(R.drawable.coeficientedeconfianzamediavarconocida);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.textView5.setVisibility(View.VISIBLE);
                    String texto = lienzo.paso2.getText().toString();
                    texto = texto+"\n\nPara conocer el limite superior del intervalo de confianza podemos apoyarnos en la siguiente igualdad\n\n[x-a=b-x]\n\nDonde 'x' es el promedio muestral, a y b son los limites del intervalo.\nPuesto que se requiere conocer el limite inferior, despejamos la igualdad obteniendo: a=2x-b. Sustituyendo valores llegamos al resultado:\n\n\t\t\ta=(2*"+promedioMuestral+")-"+limSup+"="+limInf;
                    lienzo.paso2.setText(texto);
                    lienzo.paso2.setVisibility(View.VISIBLE);
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
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.output.setText("");
            }
        });

    }

    public boolean faltaDato(){
       if(lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()) return true;
       else return false;
    }

    public boolean faltaErrorMuestral(){
        if(lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()||lienzo.miu1.getText().toString().isEmpty()) return true;
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