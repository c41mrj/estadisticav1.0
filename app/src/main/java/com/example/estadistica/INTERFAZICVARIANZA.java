package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class INTERFAZICVARIANZA extends AppCompatActivity {

    private ActivityPruebaHipBinding lienzo;
    private double varianza, desviacionEstandar, confianza,coeficienteConfianza,limInf,limSup,valTablas;
    private int tamMuestra;
    private ICFVARIANZA icfvarianza,icfvarianza_aux;
    private CalculoTablas redondeo = new CalculoTablas();
    private Activity activity;
    String titulo = "I.C para varianza";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        this.setTitle(titulo);
        lienzo.imageCambia.setImageResource(R.drawable.varianza_muestralchi);
        lienzo.llc1.setVisibility(View.VISIBLE);
        lienzo.imageViewCambia1.setImageResource(R.drawable.desviacionestandar_muestralchi);
        lienzo.cambia1.setText("Desviación estándar muestral:");
        lienzo.linearLayoutDato3.setVisibility(View.GONE);
        lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.VISIBLE);
        lienzo.cambia2.setText("Varianza muestral:");
        lienzo.cambia5.setText("Nivel de confianza:");
        lienzo.imageCambia4.setImageResource(R.drawable.unomenosalfa);
        lienzo.cambia6.setText("Límite inferior:");
        lienzo.cambia7.setText("Límite superior:");
        lienzo.imCambia6.setImageResource(R.drawable.a);
        lienzo.imageCambia7.setImageResource(R.drawable.b);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setText("Calcular intervalo de confianza para la varianza");
        lienzo.button8.setVisibility(View.VISIBLE);
        lienzo.button8.setText("Calcular intervalo de confianza para la desviación estándar");
        lienzo.button2.setText("Calcular nivel de confianza si se conoce el límite inferior del I.C para la varianza");
        lienzo.button2.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setText("Calcular nivel de confianza si se conoce el límite superior del I.C para la varianza");
        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
        lienzo.potenciaPrueba.setVisibility(View.GONE);
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.caso1.setVisibility(View.GONE);
        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                if(faltaDatoParaIntervaloVarianza()){
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa todos los datos marcados en rojo");
                }else if(Integer.parseInt(lienzo.tamMuestral.getText().toString())>121){
                    desplegarNotificacion("Imposible calcular, nuestro teorema se restringe a tamaños de muestra menores o iguales a 121");
                }else{
                    lienzo.liminferior.setText("Lìmite inferior:");
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.imagePromedio.setImageResource(R.drawable.chialfamedios);
                    lienzo.imageMiu.setImageResource(R.drawable.chiunomenosalfamedios);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.reglaDecision.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.zeta2.setVisibility(View.VISIBLE);
                    lienzo.tableRowMiu.setVisibility(View.VISIBLE);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.teorema.setImageResource(R.drawable.intervaloconfianzavarianzia);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                    confianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    varianza = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    icfvarianza = new ICFVARIANZA(varianza,tamMuestra,confianza);
                    limInf = icfvarianza.calcLimInf();
                    limInf =  icfvarianza.tablas.redondeoDecimales(limInf,6);
                    limSup = icfvarianza.calcLimSup();
                    limSup = icfvarianza.tablas.redondeoDecimales(limSup,6);
                    valTablas = icfvarianza.getValorTablas();
                    double valTablas1 = icfvarianza.getValTablas1();
                    lienzo.output.setText("El intervalo de confianza calculado es:\n"+limInf+"< σ^2 <"+limSup);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.paso1Phip.setText("Para calcular los límites de confianza se hace necesaria la busqueda de los valores: (α/2)="+ redondeo.redondeoDecimales(((1-confianza)/2),5)  + " y :" + " [1-(α/2)]=" + icfvarianza.getConfianza1()+" en las tablas de la distribución chi cuadrada con n-1= "+ (tamMuestra-1) + " grados libertad.\n\nEn éste caso el valor en tablas es: " + valTablas+" y " + valTablas1 +". Obtenido los valores nos limitaremos a sustituir valores en las siguientes fórmulas:" );
                    lienzo.calcOp1.setImageResource(R.drawable.liminfintervaloconfianzavarianza);
                    lienzo.calculo1.setText("Límite inferior: ");
                    lienzo.calculo2.setText("Límite superior: ");
                    lienzo.calcOp2.setImageResource(R.drawable.limsupintervaloconfianzavarianza);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.textView15.setText("("+(tamMuestra-1)+"*"+varianza+")/" + valTablas + "= " +limInf);
                    lienzo.zeta2.setText("("+(tamMuestra-1)+"*"+varianza+")/" + valTablas1 + "= " +limSup);
                    lienzo.paso2Phipodc.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.desviation.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.val3.setText("="+icfvarianza.tablas.redondeoDecimales((1-confianza),4));
                    lienzo.val4.setText("="+confianza);
                    lienzo.val5.setText("="+varianza);
                    lienzo.imageN.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.dtoOpcional.setVisibility(View.VISIBLE);
                    lienzo.imageDato6.setImageResource(R.drawable.dato_f);
                    lienzo.val6.setText("="+tamMuestra);
                    lienzo.val1.setText("="+icfvarianza.getValorTablas());
                    lienzo.val2.setText("="+icfvarianza.getValTablas1());
                    lienzo.paso2.setText("Interpretación del resultado:\n\nCon una probabilidad del " + icfvarianza.tablas.redondeoDecimales((confianza*100),6) + "% se asegura que el parametro varianza de nuestra población estudiada se encuentra entre " + limInf + " y " + limSup);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.desviation.setImageResource(R.drawable.unomenosalfa);
                }

            }
        });

        lienzo.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                if(faltaDatoParaIntervaloDesvEstandar()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                    desplegarNotificacion("Por favor ingresa los datos maracados con rojo");
                }else if(Integer.parseInt(lienzo.tamMuestral.getText().toString())>120){
                    desplegarNotificacion("Imposible calcular, nuestro teorema se restringe a tamaños de muestra menores o iguales a 121");
                }else{
                    lienzo.liminferior.setText("Lìmite inferior:");
                    lienzo.limitsuperior.setVisibility(View.VISIBLE);
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.reglaDecision.setVisibility(View.GONE);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);

                    lienzo.zeta2.setVisibility(View.VISIBLE);
                    lienzo.tableRowMiu.setVisibility(View.GONE);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.teorema.setImageResource(R.drawable.intervaloconfianzavarianzia);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                    confianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    double desvEstandarAux = desviacionEstandar;
                    desviacionEstandar = Math.pow(desviacionEstandar,2);
                    desviacionEstandar = redondeo.redondeoDecimales(desviacionEstandar,5);
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    icfvarianza = new ICFVARIANZA(desviacionEstandar,tamMuestra,confianza);
                    limInf = icfvarianza.calcLimInf();
                    limInf = Math.sqrt(limInf);
                    limInf = redondeo.redondeoDecimales(limInf,6);
                    limSup = icfvarianza.calcLimSup();
                    limSup = Math.sqrt(limSup);
                    limSup = redondeo.redondeoDecimales(limSup,6);
                    valTablas = icfvarianza.getValorTablas();
                    double valTablas1 = icfvarianza.getValTablas1();
                    String intervalo = limInf+"<σ<"+limSup;
                    lienzo.output.setText("El intervalo de confianza es: "+limInf+"<σ<"+limSup);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.paso1Phip.setText("Para calcular los límites de confianza se hace necesaria la busqueda de los valores: (α/2)="+ redondeo.redondeoDecimales(((1-confianza)/2),5)  + " y : [1-(α/2)]=" + icfvarianza.getConfianza1()+" en las tablas de la distribución chi cuadrada con n-1="+(tamMuestra-1)+" grados libertad.\n\nEn éste caso el valor en tablas es: " + valTablas+" y " + valTablas1 +" respectivamente. Obtenido los valores nos limitaremos a sustituir valores en las siguientes fórmulas:" );
                    lienzo.calcOp1.setImageResource(R.drawable.liminfintervaloconfianzavarianza);
                    lienzo.calculo1.setText("Límite inferior: ");
                    lienzo.calculo2.setText("Límite superior: ");
                    lienzo.calcOp2.setImageResource(R.drawable.limsupintervaloconfianzavarianza);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.textView15.setText("√[("+(tamMuestra-1)+"*"+desviacionEstandar+")/" + valTablas + "]=" +limInf);
                    lienzo.zeta2.setText("√[("+(tamMuestra-1)+"*"+desviacionEstandar+")/" + valTablas1 + "]= " +limSup);
                    lienzo.paso2Phipodc.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.LinearLayoutDato6.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                    lienzo.paso2.setVisibility(View.VISIBLE);
                    lienzo.paso2.setText("Paso 2: \n\nRecordemos que las formulas utilizadas anteriormente son utiles cuando se necesita calcular el intervalo de confianza para la varianza de una población normal o aproximadamente normal, en el caso de que se requiera calcular el intervalo de confianza para la desviación estándar de una población normal o aproximadamente normal simplemente se calculara el intervalo de confianza para la varianza y posteriormente se ontendrá la raíz cuadrada de ambos límites:\n\n\t\t"+intervalo + "\n\n\nInterpretación del resultado:\n\nCon una probabilidad del " + icfvarianza.tablas.redondeoDecimales((confianza*100),6)+"% se asegura que la desviación estándar de nuestra población se encuentra en el intervalo:\n" + intervalo);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.imagePromedio.setImageResource(R.drawable.dato_g);
                    lienzo.val1.setText("="+confianza);
                    lienzo.imageSignificancia.setImageResource(R.drawable.unomenosalfa);
                    lienzo.val2.setText("="+icfvarianza.tablas.redondeoDecimales((1-confianza),6));
                    lienzo.desviation.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.val3.setText("="+icfvarianza.tablas.redondeoDecimales((1-confianza),6));
                    lienzo.val4.setText("="+desviacionEstandar);
                    lienzo.imageN.setImageResource(R.drawable.dato_f);
                    lienzo.val5.setText("="+tamMuestra);
                    lienzo.dtoOpcional.setVisibility(View.VISIBLE);
                    lienzo.imageDato6.setImageResource(R.drawable.chialfamedios);
                    lienzo.val6.setText("="+valTablas);
                    lienzo.tableRow7.setVisibility(View.VISIBLE);
                    lienzo.imageDato7.setImageResource(R.drawable.chiunomenosalfamedios);
                    lienzo.val7.setText("="+valTablas1);
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
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                if(faltaDatoParaCoeficienteConfVarianza()){
                    desplegarNotificacion("Por favor ingresa todos los datos marcados en rojo");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia6.setTextColor(Color.RED);
                }else{
                    limInf = Double.parseDouble(lienzo.miu1.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    icfvarianza = new ICFVARIANZA(tamMuestra,desviacionEstandar);
                    coeficienteConfianza = icfvarianza.calcGradoConfianza('a',limInf);
                    icfvarianza_aux = new ICFVARIANZA(desviacionEstandar,tamMuestra,coeficienteConfianza);
                    limSup = icfvarianza_aux.calcLimSup();
                    limSup = icfvarianza_aux.tablas.redondeoDecimales(limSup,6);
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"≈ " + coeficienteConfianza*100 + "%\nCon un límite superior de:" + limSup);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.liminfcoeficienteconfianzavarianza);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.a);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato1TV.setText("="+limInf);
                    lienzo.tlcDato3TV.setText("="+desviacionEstandar);
                    lienzo.tlcDato4TV.setText("="+tamMuestra);
                    lienzo.llz2.setVisibility(View.GONE);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza se hace necesario calcular primero el valor del determinante que posteriormente buscaremos en las tablas de la distribución ji-cuadrada, para ubicar dicho determinante es necesario observar la fórmula siguiente, el determinante es aquel encerrado entre corchetes. En éste caso el valor de nuestro determinante es: "+icfvarianza.getDeterminante()+"\n\nUna vez calculado el determinante, procedemos a buscar dicho valor en las tablas ji cuadrada con "+(tamMuestra-1) + " grados de libertad. El valor encontrado en tablas es:" + icfvarianza.getValorTablas());
                    lienzo.liminferior.setText("Coeficiente de confianza:");
                    lienzo.textView15.setText("1-α = 1-2X[("+icfvarianza.getValorTablas()+"] ≈ " + coeficienteConfianza);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzavarianza);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.paso2.setText("Para el límite superior:\n\nPara calcular el límite superior del intervalo de confianza se hace necesario buscar  el valor de [(1-" + coeficienteConfianza + ")/2] = " + icfvarianza_aux.tablas.redondeoDecimales(((1-confianza)/2),6) +" en las tablas de la distribución chi cuadrada.\n\nEn éste caso el valor encontrado en tablas es: " + icfvarianza_aux.getValTablas1()+". Posteriormente nos limitamos a sustituir los valores correspondientes en la fórmula siguiente: ");
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.VISIBLE);
                    lienzo.textView14.setVisibility(View.GONE);
                    lienzo.reglaDecision.setImageResource(R.drawable.limsupintervaloconfianzavarianza);
                    lienzo.potprueba.setVisibility(View.GONE);
                    lienzo.nuevoMiu.setVisibility(View.GONE);
                    lienzo.niumiu.setVisibility(View.GONE);
                    lienzo.paso2Phipodc.setText("Sustituyendo valores:");
                    lienzo.paso2Phipodc.setTextColor(Color.MAGENTA);
                    lienzo.textView7.setVisibility(View.VISIBLE);
                    lienzo.textView7.setText("("+(tamMuestra-1)+"*"+desviacionEstandar+")/"+icfvarianza_aux.getValTablas1()+"=" + limSup);
                    lienzo.reglaDecision.setVisibility(View.VISIBLE);
                }
            }
        });

        lienzo.otroBotonAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                if(faltaDatoParaCoeficienteConfVarianza()){
                    desplegarNotificacion("Por favor ingresa todos los datos marcados en rojo");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }else{
                    limSup = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    icfvarianza = new ICFVARIANZA(tamMuestra,desviacionEstandar);
                    coeficienteConfianza = icfvarianza.calcGradoConfianza('b',limSup);
                    icfvarianza_aux = new ICFVARIANZA(desviacionEstandar,tamMuestra,coeficienteConfianza);
                    limInf = icfvarianza_aux.calcLimInf();
                    limInf = icfvarianza.tablas.redondeoDecimales(limInf,6);
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"≈ " + coeficienteConfianza*100 + "%\nCon un límite inferior de: " + limInf);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.limsupcoeficienteconfianzavarianza);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.b);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.varianza_muestralchi);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.tlcDato1TV.setText("="+limSup);
                    lienzo.tlcDato3TV.setText("="+desviacionEstandar);
                    lienzo.tlcDato4TV.setText("="+tamMuestra);
                    lienzo.llz2.setVisibility(View.GONE);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.GONE);
                    lienzo.limitsuperior.setVisibility(View.GONE);
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza se hace necesario calcular primero el valor del determinante que posteriormente buscaremos en las tablas de la distribución ji-cuadrada, para ubicar dicho determinante es necesario observar la fórmula siguiente, el determinante es aquel encerrado entre corchetes. En éste caso el valor de nuestro determinante es: "+icfvarianza.getDeterminante()+"\n\nUna vez calculado el determinante, procedemos a buscar dicho valor en las tablas ji cuadrada con "+(tamMuestra-1) + " grados de libertad. El valor encontrado en tablas es:" + icfvarianza.getValorTablas());
                    lienzo.liminferior.setText("Coeficiente de confianza:");
                    lienzo.textView15.setText("1-α= 2X[("+icfvarianza.getValorTablas()+"] - 1  ≈ " + coeficienteConfianza);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.GONE);
                    lienzo.tvDatos.setVisibility(View.GONE);
                    lienzo.teorema.setImageResource(R.drawable.coeficienteconfianzavarianza);
                    lienzo.procedimiento.setVisibility(View.GONE);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.paso2.setText("Para el límite inferior:\n\nPara calcular el límite inferior del intervalo de confianza se hace necesario buscar  el valor de (1-" + coeficienteConfianza + ")/2 = " + icfvarianza_aux.getConfianza() +" en las tablas de la distribución chi cuadrada.\n\nEn éste caso el valor encontrado en tablas es: " + icfvarianza_aux.getValorTablas()+". Posteriormente nos limitamos a sustituir los valores correspondientes en la fórmula siguiente: ");
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.VISIBLE);
                    lienzo.textView14.setVisibility(View.GONE);
                    lienzo.reglaDecision.setImageResource(R.drawable.liminfintervaloconfianzavarianza);
                    lienzo.potprueba.setVisibility(View.GONE);
                    lienzo.nuevoMiu.setVisibility(View.GONE);
                    lienzo.niumiu.setVisibility(View.GONE);
                    lienzo.paso2Phipodc.setText("Sustituyendo valores:");
                    lienzo.paso2Phipodc.setTextColor(Color.MAGENTA);
                    lienzo.textView7.setVisibility(View.VISIBLE);
                    lienzo.textView7.setText("("+(tamMuestra-1)+"*"+desviacionEstandar+")/"+icfvarianza_aux.getValorTablas()+"=" + limInf);
                    lienzo.reglaDecision.setVisibility(View.VISIBLE);
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
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.cambia5.setTextColor(Color.BLACK);
                lienzo.cambia6.setTextColor(Color.BLACK);
                lienzo.cambia7.setTextColor(Color.BLACK);
                lienzo.mediaPoblacional.setText("");
                lienzo.desviacionEstandarPob.setText("");
                lienzo.tamMuestral.setText("");
                lienzo.significancia.setText("");
                lienzo.miu1.setText("");
                lienzo.editTextCambia7.setText("");
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.output.setText("");
            }
        });

    }


    public boolean faltaDatoParaIntervaloVarianza(){
        if(lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()) return true;
        else return false;
    }

    public boolean faltaDatoParaIntervaloDesvEstandar(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty())return true;
        else return false;
    }

    public boolean faltaDatoParaCoeficienteConfVarianza(){
        if(lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||(lienzo.miu1.getText().toString().isEmpty()&&lienzo.editTextCambia7.getText().toString().isEmpty()))return true;
        else return false;
    }

    public boolean faltaDatoParaCoeficienteConfDesvEstandar(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||(lienzo.miu1.getText().toString().isEmpty()&&lienzo.editTextCambia7.getText().toString().isEmpty()))return true;
        else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }
}