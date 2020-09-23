package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PruebaHip extends AppCompatActivity {
    Toast toast;
    ActivityPruebaHipBinding lienzo;
    PruebaHipotesisDVconocida prueba;
    CalculoTablas valorTabla;
    EditText miu0;
    EditText prom_muestral;
    EditText significancia;
    EditText desvEstandar;
    EditText tamMuestra;
    TextView salida;
    TextView paso1;
    String pasoAux;
    String mensajeSalida;
    LinearLayout procedimiento;
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    ImageView teorema;
    TextView output2;
    TextView liminf;
    TextView limSup;
    TextView obtenerZ;
    TextView sustitucion1;
    TextView sustitucion2;
    TextView caso;
    TextView numerador1;
    TextView denominador;
    TextView denominador1;
    TextView zeta;
    TextView zeta2;
    TextView outputLimInf;
    TextView outputLimSup;
    ImageView imLimI;
    ImageView imLimInf;
    ImageView imLimS;
    ImageView imLimSup;
    ImageView imZ;
    ImageView imagenSustitucion0;
    ImageView imagenSustitucion1;
    ImageView reglaDecision;
    TextView interpretacion1;
    Button botonInterpretacion1;
    Button botonInterpretacion2;
    Button verProcedimiento;
    String conclusion1;
    String conclusion2;
    TextView val1,val2,val3,val4,val5,val6;
    LinearLayout margenDinamico,opcional2;
    TableRow datoOpc;
    LinearLayout desaparece2;
    private ActivityPruebaHipBinding binding;
    private double limiteinferior,limitesuperior;
    private String ultimo;
    private double mediaPoblacional;
    private int tamanioMuestral;
    private double desEstandar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        miu0 = findViewById(R.id.mediaPoblacional);
        prom_muestral = findViewById(R.id.promedioMuestral);
        significancia = findViewById(R.id.significancia);
        desvEstandar = findViewById(R.id.desviacionEstandarPob);
        tamMuestra = findViewById(R.id.tamMuestral);
        salida = findViewById(R.id.output);
        procedimiento = findViewById(R.id.procedimiento);
        procedimiento.setVisibility(View.GONE);
        paso1 = findViewById(R.id.paso1_phip);
        teorema = findViewById(R.id.teorema);
        liminf = findViewById(R.id.calculo1);
        imLimI = findViewById(R.id.calcOp1);
        limSup = findViewById(R.id.calculo2);
        imLimS = findViewById(R.id.calcOp2);
        obtenerZ = findViewById(R.id.obtenerZ);
        imZ = findViewById(R.id.imZ);
        sustitucion1 = findViewById(R.id.textView15);
        imLimInf = findViewById(R.id.imageView);
        imLimSup = findViewById(R.id.sustLimSup);
        zeta2 = findViewById(R.id.zeta2);
        output2 = findViewById(R.id.paso2_phipodc);
        verProcedimiento = findViewById(R.id.verProcedimiento);
        reglaDecision = findViewById(R.id.regla_decision);
        caso = findViewById(R.id.caso);
        val1 = findViewById(R.id.val1);
        val2 = findViewById(R.id.val2);
        val3 = findViewById(R.id.val3);
        val4 = findViewById(R.id.val4);
        val5 = findViewById(R.id.val5);
        val6 = findViewById(R.id.val6);
        verProcedimiento.setVisibility(View.INVISIBLE);
        opcional2 = findViewById(R.id.layoutDesaparece);
        datoOpc = findViewById(R.id.dto_opcional);
        margenDinamico = findViewById(R.id.margen_dinamico);
        desaparece2 = findViewById(R.id.layoutDesaparece2);
        binding.cleanit.setVisibility(View.GONE);
        binding.potprueba.setVisibility(View.GONE);
        binding.layoutPotencia.setVisibility(View.GONE);
    }

    public void botonCaso1(View vista){
        if(faltaDato()){
            desplegarNotificacion("Por favor completa todos los campos para continuar");

        }else {
            binding.cleanit.setVisibility(View.VISIBLE);
            binding.potprueba.setVisibility(View.VISIBLE);
            binding.limitsuperior.setVisibility(View.VISIBLE);
            sustitucion2.setVisibility(View.VISIBLE);

            liminf.setVisibility(View.VISIBLE);
            imLimI.setVisibility(View.VISIBLE);
            imLimI.setImageResource(R.drawable.liminfcasoa);
            imLimInf.setVisibility(View.VISIBLE);
            imLimInf.setImageResource(R.drawable.liminfcasoa);

            limSup.setVisibility(View.VISIBLE);
            imLimS.setVisibility(View.VISIBLE);
            imLimS.setImageResource(R.drawable.limsupa);
            imLimSup.setImageResource(R.drawable.limsupa);

            opcional2.setVisibility(View.VISIBLE);
            verProcedimiento.setText("Ver procedimiento");
            procedimiento.setVisibility(View.GONE);
            datoOpc.setVisibility(View.INVISIBLE);
            verProcedimiento.setVisibility(View.VISIBLE);
            valorTabla = new CalculoTablas();
            prueba = new PruebaHipotesisDVconocida();
            String valMiu0 = miu0.getText().toString();
            double conv1 = Double.parseDouble(valMiu0);
            val2.setText("="+valMiu0);

            String valpromMuestral = prom_muestral.getText().toString();
            double conv2 = Double.parseDouble(valpromMuestral);
            val1.setText("="+valpromMuestral);

            String valdesDestandar = desvEstandar.getText().toString();
            double conv3 = Double.parseDouble(valdesDestandar);
            val4.setText("="+valdesDestandar);

            String valSignificancia = significancia.getText().toString();
            float conv4 = Float.parseFloat(valSignificancia);
            float conv4aux = conv4;
            conv4 = conv4/2;
            val3.setText("="+conv4aux);

            String valTamMuestra = tamMuestra.getText().toString();
            int conv5 = Integer.parseInt(valTamMuestra);
            val5.setText("="+conv5);
            String mensaje = prueba.casoc(conv4,conv5,conv3,conv2,conv1);
            salida.setText(mensaje);

            teorema.setImageResource(R.drawable.rechazar_h0_casoa);
            lienzo.paso1Phip.setText("Una vez que conocemos el contraste de hipótesis, es necesario aplicar la metodología siguiente, y así poder realizar el cálculo de el intervalo de la región de rechazo correspondiente, que utilizaremos posteriormente para tomar una decisión sobre si se niega o acepta la hipótesis nula.\n\nPara realizar el calculo de dicho intervalo, cómo podemos observar en el teorema presentado al inicio del procedimiento. Es necesario obtener el valor de Z(α/2) de las tablas de la distribución normal estándar, donde:\n\nα=" + valSignificancia + "\n\nα/2="+valorTabla.redondeoDecimales((Double.parseDouble(valSignificancia)/2),5) + "\n\nBuscando los valores en tablas encontramos que:\nZ(α/2) = " );
            liminf.setText("Para obtener el limite inferior  calculamos: ");
            imLimI.setImageResource(R.drawable.liminfcasoa);
            limSup.setText("Para obtener el limite superior calculamos: ");
            double varAux = valorTabla.tablazeta(conv4);
            obtenerZ.setText("Es necesario buscar el valor resultante de: "+conv4aux + "/2" +", en las tablas de la distribucion normal, en este caso el valor que encontramos en tablas es: " + valorTabla.tablazeta(conv4));
            imZ.setImageResource(R.drawable.imagen_zeta);
            imLimInf.setImageResource(R.drawable.liminfcasoa);
            sustitucion1.setText("="+ miu0.getText().toString()+ "-" + "("+ desvEstandar.getText().toString() + "/√"+tamMuestra.getText().toString()+")*"+ valorTabla.tablazeta(conv4)+"=" + prueba.tab.redondeoDecimales(prueba.getLim_inf(),5));//sustitucion1  /***********ojo*****************/
            sustitucion2.setText("="+ miu0.getText().toString()+"+"+"("+desvEstandar.getText().toString() + "/√"+tamMuestra.getText().toString()+")*"+ valorTabla.tablazeta(conv4)+"=" + prueba.tab.redondeoDecimales(prueba.getLim_sup(),5));

            switch(prueba.decision){
                case " es menor que el operador1":
                    output2.setText( "Se rechaza la hipotesis nula si: el parametro promedio muestral no aparece en el intervalo de valores: " + "("+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+","+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+")");
                    reglaDecision.setImageResource(R.drawable.regla_decision);
                    caso.setText("Nos damos cuenta de que el parametro promedio muestral no aparece en dicho intervalo ya que es menor que el limite inferior del intervalo: " + prom_muestral.getText().toString()+"<"+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+"\n\nComo nuestro parametro no aparece en el intervalo rechazamos la hipotesis nula.\n\n\n Si el contraste es que: Se afirma que el parametro media poblacional es siempre igual a " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es invalida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre diferente de: "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es valida");
                    break;
                case " es mayor que el operador2":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral no aparece en el intervalo de valores: " + "\n\t\t\t\t["+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+","+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"]");
                    reglaDecision.setImageResource(R.drawable.regla_decision);
                    caso.setText("Nos damos cuenta de que el parametro promedio muestral no aparece en dicho intervalo ya que es mayor que el limite superior del intervalo: " + prom_muestral.getText().toString()+">"+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"\n\nComo nuestro parametro no aparece en el intervalo rechazamos la hipotesis nula. \n\n\nSi el contraste es que: Se afirma que el parametro media poblacional es siempre igual a " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es invalida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre diferente de: "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es valida");
                    break;
                case " está dentro del intervalo de no rechazo ":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral no aparece en el intervalo de valores: " + "\n\t\t\t\t["+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+","+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"]");
                    reglaDecision.setImageResource(R.drawable.regla_decision);
                    caso.setText("Nos damos cuenta de que el parametro promedio muestral si aparece en dicho intervalo : " + prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+"<" + prom_muestral.getText().toString() + "<" +prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"\n\nComo nuestro parametro està dentro del intervalo, aceptamos la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional es siempre igual a " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es valida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre diferente de: "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es invalida");
                    break;
                default:
                    output2.setText("");
                    break;
            }
            binding.calculo2.setGravity(Gravity.CENTER_HORIZONTAL);
            binding.layoutDesaparece2.setVisibility(View.VISIBLE);
            binding.calculo2.setVisibility(View.VISIBLE);
            binding.calcOp2.setVisibility(View.VISIBLE);
            binding.liminferior.setVisibility(View.VISIBLE);
            this.limiteinferior = prueba.tab.redondeoDecimales(prueba.getLim_inf(),5);
            this.limitesuperior = prueba.tab.redondeoDecimales(prueba.getLim_sup(),5);
            this.ultimo="=";
            this.mediaPoblacional = Double.parseDouble(miu0.getText().toString());
            this.tamanioMuestral = Integer.parseInt(tamMuestra.getText().toString());
            this.desEstandar = Double.parseDouble(desvEstandar.getText().toString());

        }
    }

    public void botonCaso2(View vista){
        if(faltaDato()){
            desplegarNotificacion("Por favor completa todos los campos para continuar");

        }else {
            binding.imageButton.setVisibility(View.INVISIBLE);
            binding.imageButton5.setVisibility(View.INVISIBLE);
            binding.imageButton3.setVisibility(View.INVISIBLE);
            binding.imageButton6.setVisibility(View.INVISIBLE);
            binding.imageButton7.setVisibility(View.INVISIBLE);
            binding.imageButton8.setVisibility(View.INVISIBLE);
            binding.cleanit.setVisibility(View.VISIBLE);
            binding.potprueba.setVisibility(View.VISIBLE);

            liminf.setVisibility(View.INVISIBLE);
            imLimI.setVisibility(View.INVISIBLE);
            //imLimI.setImageResource(R.drawable.liminfcasoa);
            imLimInf.setVisibility(View.INVISIBLE);
            //imLimInf.setImageResource(R.drawable.liminfcasoa);
            sustitucion2.setVisibility(View.VISIBLE);

            limSup.setVisibility(View.VISIBLE);
            imLimS.setVisibility(View.VISIBLE);
            imLimS.setImageResource(R.drawable.limsup_pruebas_hipotesis_prima);
            imLimSup.setImageResource(R.drawable.limsup_pruebas_hipotesis_prima);

            opcional2.setVisibility(View.INVISIBLE);
            verProcedimiento.setText("Ver procedimiento");
            datoOpc.setVisibility(View.INVISIBLE);
            verProcedimiento.setVisibility(View.VISIBLE);
            procedimiento.setVisibility(View.GONE);
            valorTabla = new CalculoTablas();
            prueba = new PruebaHipotesisDVconocida();

            String valMiu0 = miu0.getText().toString();
            double conv1 = Double.parseDouble(valMiu0);
            val2.setText("="+valMiu0);

            String valpromMuestral = prom_muestral.getText().toString();
            double conv2 = Double.parseDouble(valpromMuestral);
            val1.setText("="+valpromMuestral);

            String valdesDestandar = desvEstandar.getText().toString();
            double conv3 = Double.parseDouble(valdesDestandar);
            val4.setText("="+valdesDestandar);

            String valSignificancia = significancia.getText().toString();
            float conv4 = Float.parseFloat(valSignificancia);
            float conv4aux = conv4;
            conv4 = conv4/2;
            val3.setText("="+conv4aux);

            String valTamMuestra = tamMuestra.getText().toString();
            int conv5 = Integer.parseInt(valTamMuestra);
            val5.setText("="+conv5);
            String mensaje = prueba.casob(conv4aux,conv5,conv3,conv2,conv1);
            salida.setText(mensaje);

            teorema.setImageResource(R.drawable.regla_decision2);
            pasoAux = "El primer paso es obtener el limite superior del intervalo de valores que nos va a servir para aceptar o rechazar la hipotesis nula:";
            paso1.setText(pasoAux);
            opcional2.setVisibility(View.INVISIBLE);
            limSup.setText("Para obtener el limite superior calculamos: ");
            imLimS.setImageResource(R.drawable.limsup_pruebas_hipotesis);
            obtenerZ.setText("Es necesario buscar el valor de nuestro nivel de significancia: "+conv4aux +", en las tablas de la distribucion normal, en este caso el valor que encontramos en tablas es: " + valorTabla.tablazeta(conv4aux));
            imZ.setImageResource(R.drawable.valz);
            liminf.setVisibility(View.GONE);
            imLimInf.setVisibility(View.GONE);
            binding.liminferior.setVisibility(View.INVISIBLE);
            //sustitucion1.setText("="+ miu0.getText().toString()+ "-" + "("+ desvEstandar.getText().toString() + "/√"+tamMuestra.getText().toString()+")*"+ valorTabla.tablazeta(conv4aux)+"=" + prueba.tab.redondeoDecimales(prueba.getLim_inf(),2));//sustitucion1  /***********ojo*****************/
            sustitucion2.setText("="+ miu0.getText().toString()+"+"+"("+desvEstandar.getText().toString() + "/√"+tamMuestra.getText().toString()+")*"+ valorTabla.tablazeta(conv4aux)+"=" + prueba.tab.redondeoDecimales(prueba.getLim_sup(),5));
            switch(prueba.decision){

                case "es mayor":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral es mayor al limite superior del intervalo de valores de la region de no rechazo: "+ "\n\t\t\t\t"+prom_muestral.getText().toString()+">"+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"?");
                    reglaDecision.setImageResource(R.drawable.limsup_pruebas_hipotesis);
                    caso.setText("Como podemos observar en éste caso: "  +prom_muestral.getText().toString()+">"+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"\n\n El promedio muestral es mayor que el limite superior de nuestra region de no rechazo, por lo tanto: se rechaza la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional siempre va a ser menor o igual que: " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es invalida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre mayor que: "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es valida");
                    break;
                case " está dentro del intervalo de no rechazo ":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral es mayor al limite superior del intervalo de valores de la region de no rechazo: " + "\n\t\t\t\t"+ prom_muestral.getText().toString()+">"+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"?");
                    reglaDecision.setImageResource(R.drawable.limsup_pruebas_hipotesis);
                    caso.setText("Como podemos observar en éste caso: "+"\n\t\t\t\t"+prom_muestral.getText().toString()+"<"+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"\n\n Nuestro parametro es menor al limite superior de la region de no rechazo, aceptamos la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional es siempre menor o igual a " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es valida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre mayor que : "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es invalida");
                    break;
                case "es igual":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral es mayor al limite superior del intervalo de valores de la region de no rechazo: " + "\n\t\t\t\t"+ prom_muestral.getText().toString()+">"+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"?");
                    reglaDecision.setImageResource(R.drawable.limsup_pruebas_hipotesis);
                    caso.setText("Como podemos observar en éste caso: "+"\n\t\t\t\t"+prom_muestral.getText().toString()+"="+prueba.tab.redondeoDecimales(prueba.getLim_sup(),5)+"\n\n Nuestro parametro es igual al limite superior de la region de no rechazo, aceptamos la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional es siempre menor o igual a " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es valida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre mayor que : "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es invalida");
                    break;
                default:
                    output2.setText("");
                    break;
            }
            binding.calcOp2.setImageResource(R.drawable.limsup_pruebas_hipotesis_prima);
            binding.liminferior.setVisibility(View.INVISIBLE);
            binding.layoutDesaparece2.setVisibility(View.VISIBLE);
            binding.calculo2.setVisibility(View.VISIBLE);
            binding.calcOp2.setVisibility(View.VISIBLE);
            binding.calculo2.setGravity(Gravity.CENTER_VERTICAL);
            binding.limitsuperior.setVisibility(View.VISIBLE);
            this.limitesuperior = prueba.tab.redondeoDecimales(prueba.getLim_sup(),5);
            this.ultimo = "<=";
            this.mediaPoblacional = Double.parseDouble(miu0.getText().toString());
            this.tamanioMuestral = Integer.parseInt(tamMuestra.getText().toString());
            this.desEstandar = Double.parseDouble(desvEstandar.getText().toString());
        }
    }

    public void botonCaso3(View vista){
        if(faltaDato()){
            desplegarNotificacion("Por favor completa todos los campos para continuar");

        }else {
            binding.imageButton2.setVisibility(View.INVISIBLE);
            binding.imageButton.setVisibility(View.INVISIBLE);
            binding.imageButton4.setVisibility(View.INVISIBLE);
            binding.imageButton5.setVisibility(View.INVISIBLE);
            binding.imageButton7.setVisibility(View.INVISIBLE);
            binding.imageButton8.setVisibility(View.INVISIBLE);
            binding.cleanit.setVisibility(View.VISIBLE);
            binding.potprueba.setVisibility(View.VISIBLE);

            liminf.setVisibility(View.VISIBLE);
            imLimI.setVisibility(View.VISIBLE);
            imLimI.setImageResource(R.drawable.liminf_pruebas_hipotesis);
            imLimInf.setVisibility(View.VISIBLE);
            imLimInf.setImageResource(R.drawable.liminf_pruebas_hipotesis);

            limSup.setVisibility(View.INVISIBLE);
            imLimS.setVisibility(View.INVISIBLE);
           /* imLimS.setImageResource(R.drawable.limsupa);
            imLimSup.setImageResource(R.drawable.limsupa);*/


            verProcedimiento.setText("Ver procedimiento");
            datoOpc.setVisibility(View.INVISIBLE);
            verProcedimiento.setVisibility(View.VISIBLE);
            procedimiento.setVisibility(View.GONE);
            valorTabla = new CalculoTablas();
            prueba = new PruebaHipotesisDVconocida();

            String valMiu0 = miu0.getText().toString();
            double conv1 = Double.parseDouble(valMiu0);
            val2.setText("=" + valMiu0);

            String valpromMuestral = prom_muestral.getText().toString();
            double conv2 = Double.parseDouble(valpromMuestral);
            val1.setText("=" + valpromMuestral);

            String valdesDestandar = desvEstandar.getText().toString();
            double conv3 = Double.parseDouble(valdesDestandar);
            val4.setText("=" + valdesDestandar);

            String valSignificancia = significancia.getText().toString();
            float conv4 = Float.parseFloat(valSignificancia);
            float conv4aux = conv4;
            conv4 = conv4 / 2;
            val3.setText("=" + conv4aux);

            String valTamMuestra = tamMuestra.getText().toString();
            int conv5 = Integer.parseInt(valTamMuestra);
            val5.setText("=" + conv5);
            String mensaje = prueba.casoa(conv4aux, conv5, conv3, conv2, conv1);
            salida.setText(mensaje);

            teorema.setImageResource(R.drawable.regla_decision_casoa);
            pasoAux = "El primer paso es obtener el limite inferior del intervalo de valores que nos va a servir para aceptar o rechazar la hipotesis nula:";
            paso1.setText(pasoAux);
            opcional2.setVisibility(View.INVISIBLE);
            liminf.setText("Para obtener el limite inferior calculamos: ");
            imLimI.setImageResource(R.drawable.liminf666jpg);
            double varAux = valorTabla.tablazeta(conv4aux);
            obtenerZ.setText("Es necesario buscar el valor de nuestro nivel de significancia: " + conv4aux + ", en las tablas de la distribucion normal, en este caso el valor que encontramos en tablas es: " + valorTabla.tablazeta(conv4aux));
            imZ.setImageResource(R.drawable.valz);
            limSup.setVisibility(View.GONE);
            imLimS.setVisibility(View.GONE);
            sustitucion1.setText("=" + miu0.getText().toString() + "-" + "(" + desvEstandar.getText().toString() + "/√" + tamMuestra.getText().toString() + ")*" + valorTabla.tablazeta(conv4aux) + "=" + prueba.tab.redondeoDecimales(prueba.getLim_inf(), 2));//sustitucion1  /***********ojo*****************/
            //sustitucion2.setText("="+ miu0.getText().toString()+"-"+"("+desvEstandar.getText().toString() + "/√"+tamMuestra.getText().toString()+")*"+ valorTabla.tablazeta(conv4aux)+"=" + prueba.tab.redondeoDecimales(prueba.getLim_sup(),2));
            sustitucion2.setVisibility(View.GONE);
            switch (prueba.decision) {

                case "es menor":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral es menor al limite inferior del intervalo de valores de la region de no rechazo: "+ "\n\t\t\t\t"+prom_muestral.getText().toString()+"<"+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+"?");
                    reglaDecision.setImageResource(R.drawable.limsup_pruebas_hipotesis);
                    caso.setText("Como podemos observar en éste caso: "  +prom_muestral.getText().toString()+"<"+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+"\n\n El promedio muestral es menor que el limite inferior de nuestra region de no rechazo, por lo tanto: se rechaza la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional es siempre mayor o igual que: " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es invalida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre menor que: "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es valida");
                    break;
                case "no es menor":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral es menor al limite inferior del intervalo de valores de la region de no rechazo: " + "\n\t\t\t\t"+ prom_muestral.getText().toString()+"<"+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+"?");
                    reglaDecision.setImageResource(R.drawable.limsup_pruebas_hipotesis);
                    caso.setText("Como podemos observar en éste caso: "+"\n\t\t\t\t"+prom_muestral.getText().toString()+">"+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+"\n\nNuestro  promedio muestral es mayor al limite inferior de la region de no rechazo, por lo tanto: se acepta la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional es siempre mayor o igual a: " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es valida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre menor que : "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es invalida");
                    break;
                case "es igual":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral es menor al limite inferior del intervalo de valores de la region de no rechazo: " + "\n\t\t\t\t"+ prom_muestral.getText().toString()+"<"+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+"?");
                    reglaDecision.setImageResource(R.drawable.limsup_pruebas_hipotesis);
                    caso.setText("Como podemos observar en éste caso: "+"\n\t\t\t\t"+prom_muestral.getText().toString()+"="+prueba.tab.redondeoDecimales(prueba.getLim_inf(),5)+"\n\nNuestro promedio muestral es igual al limite inferior de la region de no rechazo, por lo tanto: se acepta la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional es siempre mayor o igual a: " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es valida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre menor que : "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es invalida");
                    break;

                default:
                    output2.setText("");
                    break;
            }

            binding.calcOp1.setImageResource(R.drawable.limite_nferior);
            binding.imageView.setImageResource(R.drawable.limite_nferior);
            binding.imageView.setVisibility(View.VISIBLE);
            binding.layoutDesaparece2.setVisibility(View.INVISIBLE);
            binding.limitsuperior.setVisibility(View.INVISIBLE);
            binding.layoutDesaparece.setVisibility(View.VISIBLE);
            binding.liminferior.setVisibility(View.VISIBLE);
            binding.reglaDecision.setImageResource(R.drawable.limite_inferior);
            this.limiteinferior = prueba.tab.redondeoDecimales(prueba.getLim_inf(),5);
            this.ultimo=">=";
            this.mediaPoblacional = Double.parseDouble(miu0.getText().toString());
            this.tamanioMuestral = Integer.parseInt(tamMuestra.getText().toString());
            this.desEstandar = Double.parseDouble(desvEstandar.getText().toString());
        }
    }

    public void casoCuatro(View vista){

        if(faltaDatoOpc()){
            desplegarNotificacion("Por favor completa todos los campos para continuar");

        }else {
            binding.imageButton2.setVisibility(View.INVISIBLE);
            binding.imageButton3.setVisibility(View.INVISIBLE);
            binding.imageButton4.setVisibility(View.INVISIBLE);
            binding.imageButton6.setVisibility(View.INVISIBLE);
            binding.imageButton.setVisibility(View.INVISIBLE);
            binding.imageButton5.setVisibility(View.INVISIBLE);
            binding.cleanit.setVisibility(View.VISIBLE);
            binding.potprueba.setVisibility(View.VISIBLE);
            sustitucion2.setVisibility(View.VISIBLE);

            liminf.setVisibility(View.VISIBLE);
            imLimI.setVisibility(View.VISIBLE);
            imLimI.setImageResource(R.drawable.liminfcasoa);
            imLimInf.setVisibility(View.VISIBLE);
            imLimInf.setImageResource(R.drawable.liminfcasoa);

            limSup.setVisibility(View.VISIBLE);
            imLimS.setVisibility(View.VISIBLE);
            imLimS.setImageResource(R.drawable.rechazar_h0_casoa_prima);
            imLimSup.setImageResource(R.drawable.rechazar_h0_casoa_prima);

            opcional2.setVisibility(View.VISIBLE);
            verProcedimiento.setText("Ver procedimiento");
            procedimiento.setVisibility(View.GONE);
            datoOpc.setVisibility(View.INVISIBLE);
            verProcedimiento.setVisibility(View.VISIBLE);
            valorTabla = new CalculoTablas();
            prueba = new PruebaHipotesisDVconocida();
            String valMiu0 = miu0.getText().toString();
            double conv1 = Double.parseDouble(valMiu0);
            val2.setText("="+valMiu0);

            String valpromMuestral = prom_muestral.getText().toString();
            double conv2 = Double.parseDouble(valpromMuestral);
            val1.setText("="+valpromMuestral);

            String valdesDestandar = desvEstandar.getText().toString();
            double conv3 = Double.parseDouble(valdesDestandar);
            val4.setText("="+valdesDestandar);

            String valSignificancia = significancia.getText().toString();
            float conv4 = Float.parseFloat(valSignificancia);
            float conv4aux = conv4;
            conv4 = conv4/2;
            val3.setText("="+conv4aux);

            double mediauno = Double.parseDouble(binding.miu1.getText().toString());

            String valTamMuestra = tamMuestra.getText().toString();
            int conv5 = Integer.parseInt(valTamMuestra);
            val5.setText("="+conv5);
            String mensaje = prueba.casod(conv4,conv5,conv3,conv2,conv1,mediauno);
            salida.setText(mensaje);

            teorema.setImageResource(R.drawable.rechazar_h0_casoa);
            pasoAux = "El primer paso es obtener el intervalo de valores que nos va a servir para aceptar o rechazar la hipotesis nula:";
            paso1.setText(pasoAux);
            liminf.setText("Para obtener el limite inferior  calculamos: ");
            imLimI.setImageResource(R.drawable.liminfcasoa);
            limSup.setText("Para obtener el limite superior calculamos: ");
            double varAux = valorTabla.tablazeta(conv4);
            obtenerZ.setText("Es necesario buscar el valor resultante de: "+conv4aux + "/2" +", en las tablas de la distribucion normal, en este caso el valor que encontramos en tablas es: " + valorTabla.tablazeta(conv4));
            imZ.setImageResource(R.drawable.imagen_zeta);
            imLimInf.setImageResource(R.drawable.liminfcasoa);
            sustitucion1.setText("="+ miu0.getText().toString()+ "-" + "("+ desvEstandar.getText().toString() + "/√"+tamMuestra.getText().toString()+")*"+ valorTabla.tablazeta(conv4)+"=" + prueba.tab.redondeoDecimales(prueba.getLim_inf(),2));//sustitucion1  /***********ojo*****************/
            sustitucion2.setText("="+ miu0.getText().toString()+"+"+"("+desvEstandar.getText().toString() + "/√"+tamMuestra.getText().toString()+")*"+ valorTabla.tablazeta(conv4)+"=" + prueba.tab.redondeoDecimales(prueba.getLim_sup(),2));

            switch(prueba.decision){
                case " es menor que el operador1":
                    output2.setText( "Se rechaza la hipotesis nula si: el parametro promedio muestral no aparece en el intervalo de valores: " + "("+prueba.tab.redondeoDecimales(prueba.getLim_inf(),2)+","+prueba.tab.redondeoDecimales(prueba.getLim_sup(),2)+")");
                    reglaDecision.setImageResource(R.drawable.regla_decision1);
                    caso.setText("Nos damos cuenta de que el parametro promedio muestral no aparece en dicho intervalo ya que es menor que el limite inferior del intervalo: " + prom_muestral.getText().toString()+"<"+prueba.tab.redondeoDecimales(prueba.getLim_inf(),2)+"\n\nComo nuestro parametro no aparece en el intervalo rechazamos la hipotesis nula.\n\n\n Si el contraste es que: Se afirma que el parametro media poblacional es siempre igual a " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es invalida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre diferente de: "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es valida");
                    break;
                case " es mayor que el operador2":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral no aparece en el intervalo de valores: " + "\n\t\t\t\t["+prueba.tab.redondeoDecimales(prueba.getLim_inf(),2)+","+prueba.tab.redondeoDecimales(prueba.getLim_sup(),2)+"]");
                    reglaDecision.setImageResource(R.drawable.regla_decision1);
                    caso.setText("Nos damos cuenta de que el parametro promedio muestral no aparece en dicho intervalo ya que es mayor que el limite superior del intervalo: " + prom_muestral.getText().toString()+">"+prueba.tab.redondeoDecimales(prueba.getLim_sup(),2)+"\n\nComo nuestro parametro no aparece en el intervalo rechazamos la hipotesis nula. \n\n\nSi el contraste es que: Se afirma que el parametro media poblacional es siempre igual a " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es invalida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre diferente de: "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es valida");
                    break;
                case " está dentro del intervalo de no rechazo ":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral no aparece en el intervalo de valores: " + "\n\t\t\t\t["+prueba.tab.redondeoDecimales(prueba.getLim_inf(),2)+","+prueba.tab.redondeoDecimales(prueba.getLim_sup(),2)+"]");
                    reglaDecision.setImageResource(R.drawable.regla_decision1);
                    caso.setText("Nos damos cuenta de que el parametro promedio muestral si aparece en dicho intervalo : " + prueba.tab.redondeoDecimales(prueba.getLim_inf(),2)+"<" + prom_muestral.getText().toString() + "<" +prueba.tab.redondeoDecimales(prueba.getLim_sup(),2)+"\n\nComo nuestro parametro està dentro del intervalo, aceptamos la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional es siempre igual a " + miu0.getText().toString()+ ". Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es valida\n\n Por otro lado si el contraste es: El parametro media poblacional es siempre diferente de: "+miu0.getText().toString()+". Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es invalida");
                    break;
                case "es igual que el limInf":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral no aparece en el intervalo de valores: " + "\n\t\t\t\t["+prueba.tab.redondeoDecimales(prueba.getLim_inf(),2)+","+prueba.tab.redondeoDecimales(prueba.getLim_sup(),2)+"]");
                    reglaDecision.setImageResource(R.drawable.regla_decision1);
                    caso.setText("Nos damos cuenta de que el parametro promedio muestral es igual al limite inferior del intervalo :" + prueba.tab.redondeoDecimales(prueba.getLim_inf(),2)+"=" + prom_muestral.getText().toString() +"\n\nComo nuestro parametro està dentro del intervalo, aceptamos la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional estará siempre en el intervalo: [" + miu0.getText().toString()+","+binding.miu1.getText().toString()+"]. Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es valida\n\n Por otro lado si el contraste es: El parametro media poblacional no estará nunca en el intervalo: ["+miu0.getText().toString()+","+binding.miu1.getText().toString()+"]. Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es invalida");
                    break;
                case "es igual que el limSup":
                    output2.setText("Se rechaza la hipotesis nula si: el parametro promedio muestral no aparece en el intervalo de valores: " + "\n\t\t\t\t["+prueba.tab.redondeoDecimales(prueba.getLim_inf(),2)+","+prueba.tab.redondeoDecimales(prueba.getLim_sup(),2)+"]");
                    reglaDecision.setImageResource(R.drawable.regla_decision1);
                    caso.setText("Nos damos cuenta de que el parametro promedio muestral es igual al limite superior del intervalo :" + prueba.tab.redondeoDecimales(prueba.getLim_sup() ,2)+"=" + prom_muestral.getText().toString() +"\n\nComo nuestro parametro està dentro del intervalo, aceptamos la hipotesis nula. \n\n\n Si el contraste es que: Se afirma que el parametro media poblacional estará siempre en el intervalo: [" + miu0.getText().toString()+","+binding.miu1.getText().toString()+"]. Entonces concluimos con una significancia de " + significancia.getText().toString() + " que la afirmacion es valida\n\n Por otro lado si el contraste es: El parametro media poblacional no estará nunca en el intervalo: ["+miu0.getText().toString()+","+binding.miu1.getText().toString()+"]. Podemos concluir con una significancia de: "+significancia.getText().toString()+ " que la afirmacion es invalida");
                    break;
                default:
                    output2.setText("");
                    break;
            }
            binding.calculo2.setGravity(Gravity.CENTER_HORIZONTAL);
            binding.layoutDesaparece2.setVisibility(View.VISIBLE);
            binding.calculo2.setVisibility(View.VISIBLE);
            binding.calcOp2.setVisibility(View.VISIBLE);
            binding.liminferior.setVisibility(View.VISIBLE);
            binding.reglaDecision.setImageResource(R.drawable.liminf665jpg);
            binding.teorema.setImageResource(R.drawable.teorema_final);
        }
    }

    public boolean faltaDato(){
        boolean validar=false;
        if(miu0.getText().toString().isEmpty()||prom_muestral.getText().toString().isEmpty()||significancia.getText().toString().isEmpty()||desvEstandar.getText().toString().isEmpty()||tamMuestra.getText().toString().isEmpty()){
            validar = true;
            binding.cambia1.setTextColor(Color.RED);
            binding.cambia2.setTextColor(Color.RED);
            binding.cambia3.setTextColor(Color.RED);
            binding.cambia4.setTextColor(Color.RED);
            binding.cambia5.setTextColor(Color.RED);
            binding.cambia6.setTextColor(Color.BLACK);
        }
            return validar;
    }

    public boolean faltaDatoOpc(){
        boolean validar=false;
        if(miu0.getText().toString().isEmpty()||prom_muestral.getText().toString().isEmpty()||significancia.getText().toString().isEmpty()||desvEstandar.getText().toString().isEmpty()||tamMuestra.getText().toString().isEmpty()||binding.miu1.getText().toString().isEmpty()){
            validar = true;
            binding.cambia1.setTextColor(Color.RED);
            binding.cambia2.setTextColor(Color.RED);
            binding.cambia3.setTextColor(Color.RED);
            binding.cambia4.setTextColor(Color.RED);
            binding.cambia5.setTextColor(Color.RED);
            binding.cambia6.setTextColor(Color.RED);
        }
        return validar;
    }

    public void desplegarNotificacion(String message){
       Toast mensaje =
               Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
       mensaje.show();
    }


    public void botonVerProcedimiento(View vista){
        if(verProcedimiento.getText().toString().equals("Ver procedimiento")){
            procedimiento.setVisibility(View.VISIBLE);
            verProcedimiento.setText("Ocultar procedimiento");
        }else if(verProcedimiento.getText().toString().equals("Ocultar procedimiento")){
            procedimiento.setVisibility(View.GONE);
            verProcedimiento.setText("Ver procedimiento");
        }
    }

    public void cleanIt(View view){
        binding.mediaPoblacional.setText("");
        binding.promedioMuestral.setText("");
        binding.significancia.setText("");
        binding.miu1.setText("");
        binding.desviacionEstandarPob.setText("");
        binding.tamMuestral.setText("");
        binding.imageButton.setVisibility(View.VISIBLE);
        binding.imageButton5.setVisibility(View.VISIBLE);
        binding.imageButton2.setVisibility(View.VISIBLE);
        binding.imageButton4.setVisibility(View.VISIBLE);
        binding.imageButton3.setVisibility(View.VISIBLE);
        binding.imageButton7.setVisibility(View.VISIBLE);
        binding.imageButton8.setVisibility(View.VISIBLE);
        binding.imageButton6.setVisibility(View.VISIBLE);
        binding.cleanit.setVisibility(View.GONE);
        binding.potprueba.setVisibility(View.GONE);
        binding.verProcedimiento.setVisibility(View.GONE);
        binding.verProcedimiento.setText("Ver procedimiento");
        binding.output.setText("");
        binding.procedimiento.setVisibility(View.GONE);

        binding.layoutPotencia.setVisibility(View.GONE);
        binding.niumiu.setText("");
    }


    public void botonOk(View view){
      binding.caso1.setVisibility(View.VISIBLE);
      binding.nuevoMiu.setText("La potencia de la prueba cuantifica la probabilidad de rechazar la hipotesis nula de manera acertada.Por lo cual debes ingresar cualquier valor más grande que: "+ mediaPoblacional);
      binding.layoutPotencia.setVisibility(View.VISIBLE);
        if(binding.niumiu.getText().toString().isEmpty()){
            binding.nuevoMiu.setTextColor(Color.RED);
        }else{
            binding.layoutPotencia.setVisibility(View.VISIBLE);
            if(this.ultimo.equals("<=")){
                binding.caso1.setText(prueba.potenciaPrueba(this.limitesuperior,Double.parseDouble(binding.niumiu.getText().toString()),this.desEstandar,this.tamanioMuestral,"caso2")+"");
               // binding.caso1.setText(valorTabla.tabzAcumulada[5][0]);
            }else if(this.ultimo.equals(">=")){
                binding.caso1.setText(prueba.potenciaPrueba(this.limiteinferior,Double.parseDouble(binding.niumiu.getText().toString()),this.desEstandar,this.tamanioMuestral,"caso1")+"");
               // binding.caso1.setText(valorTabla.tabzAcumulada[5][0]);
            }else if(this.ultimo.equals("=")){
                binding.caso1.setText(prueba.potenciaPrueba(this.limiteinferior,this.limitesuperior,this.desEstandar,Double.parseDouble(binding.niumiu.getText().toString()),this.tamanioMuestral)+"");
              //  binding.caso1.setText(valorTabla.tabzAcumulada[5][0]);
            }
        }
    }
}
