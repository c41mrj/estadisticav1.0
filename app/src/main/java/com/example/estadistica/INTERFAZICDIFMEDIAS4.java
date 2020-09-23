package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

import java.util.ArrayList;

public class INTERFAZICDIFMEDIAS4 extends AppCompatActivity {
    private ActivityPruebaHipBinding lienzo;
    private double limInf,limSup,confianza,desviacionEstandar,promedioMuestral,valTablas,errorMuestral,coeficienteConfianza;
    private int tamMuestra,tamMinimoMuestra;
    private ICFMEDIADESEDESCONOCIDA teorema1;
    private ICFVARIANZA icfvarianza;
    private ArrayList<String> datosMuestra1;
    private ArrayList<String> datosMuestra2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
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
        lienzo.calcularIntervaloConfianza.setText("Calcular intervalo de confianza si se conocen los ");
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
        lienzo.button2.setText("Calcular grado de confianza si se conoce el limite inferior");
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setText("Calcular grado de confianza si se conoce el limite superior");
        lienzo.button.setText("Calcular intervalo de confianza para la varianza");
        lienzo.button.setVisibility(View.VISIBLE);
        lienzo.imCambia6.setImageResource(R.drawable.error_muestral);
        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDato()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia5.setTextColor(Color.RED);
                }else{
                    lienzo.tableRowMiu.setVisibility(View.GONE);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.teorema.setImageResource(R.drawable.formula1);
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.GONE);
                    confianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    teorema1 = new ICFMEDIADESEDESCONOCIDA(desviacionEstandar,tamMuestra,confianza,promedioMuestral);
                    limInf = teorema1.calcLimInf();
                    limSup = teorema1.calcLimSup();
                    valTablas = teorema1.getValorTablas();
                    lienzo.output.setText("El intervalo de confianza es: "+limInf+"<μ<"+limSup);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setVisibility(View.VISIBLE);
                    lienzo.paso1Phip.setText("Para calcular los límites de confianza se hace necesaria la busqueda del valor: (1-" + confianza+")/2 = " +((1-confianza)/2) +" en las tablas de la distribución t-student con n-1 grados de libertad, en éste caso el valor en tablas es: " + valTablas+". Obtenido el valor nos limitaremos a sustituir valores en las siguientes fórmulas:" );
                    lienzo.calcOp1.setImageResource(R.drawable.liminfformula1);
                    lienzo.calculo1.setText("Límite inferior: ");
                    lienzo.calculo2.setText("Límite superior: ");
                    lienzo.calcOp2.setImageResource(R.drawable.limsupformula1);
                    lienzo.imageView.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.textView15.setText(promedioMuestral+"-["+valTablas+"*("+desviacionEstandar+"/√"+tamMuestra+")] = " +limInf);
                    lienzo.zeta2.setText(promedioMuestral+"+["+valTablas+"*("+desviacionEstandar+"/√"+tamMuestra+")]" + limSup);
                    lienzo.paso2Phipodc.setVisibility(View.GONE);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.layoutPotencia.setVisibility(View.GONE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                }
            }
        });

        lienzo.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDesviacionEstandar()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                }else{
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
                    lienzo.button9.setText("Calcular coeficiente de confianza si se tiene conoce el limite inferior");
                    lienzo.button9.setVisibility(View.VISIBLE);
                    lienzo.button10.setText("Calcular coeficiente de confianza si se tiene conoce el limite superior");
                    lienzo.button10.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
                    lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
                }
            }
        });

        lienzo.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaErrorMuestral()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
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
                }
            }
        });

        lienzo.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaLimite()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }
                else{
                    limInf = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    teorema1 = new ICFMEDIADESEDESCONOCIDA(promedioMuestral,tamMuestra,desviacionEstandar);
                    coeficienteConfianza = teorema1.calcGradoConfianza('a',limInf);
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"= " + coeficienteConfianza*100 + "%");
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
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
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza, únicamente se sustituyen los valores correspondientes en la fórmula del coeficiente de confianza: ");
                    lienzo.liminferior.setText("Coeficiente de confianza:");
                    lienzo.textView15.setText("(2*ø{[("+promedioMuestral+"-"+limInf+")/"+desviacionEstandar+"] * √" + tamMuestra +"}) -1 = " + coeficienteConfianza);
                }
            }
        });

        lienzo.otroBotonAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaLimite()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia8.setTextColor(Color.RED);
                }
                else{
                    limSup = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    promedioMuestral = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    teorema1 = new ICFMEDIADESEDESCONOCIDA(promedioMuestral,tamMuestra,desviacionEstandar);
                    coeficienteConfianza = teorema1.calcGradoConfianza('b',limSup);
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"= " + coeficienteConfianza*100 + "%");
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
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
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza, únicamente se sustituyen los valores correspondientes en la fórmula del coeficiente de confianza: ");
                    lienzo.liminferior.setText("Coeficiente de confianza:");
                    lienzo.textView15.setText(" 1 - (2 * ø{[("+promedioMuestral+"-"+limSup+")/"+desviacionEstandar+"] * √" + tamMuestra +"} )= " + coeficienteConfianza);
                }
            }
        });

        lienzo.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaLimite()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }
                else{
                    limInf = Double.parseDouble(lienzo.editTextCambia7.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    icfvarianza = new ICFVARIANZA(tamMuestra,(Math.pow(desviacionEstandar,2)));
                    coeficienteConfianza = icfvarianza.calcGradoConfianza('a',limInf);
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"= " + coeficienteConfianza*100 + "%");
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.liminfcoeficienteconfianza);
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
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza, únicamente se sustituyen los valores correspondientes en la fórmula del coeficiente de confianza: ");
                    lienzo.liminferior.setText("Coeficiente de confianza:");
                    lienzo.textView15.setText("1-2X[("+ (tamMuestra-1) + "*" +(Math.pow(desviacionEstandar,2)) + ")/" + limInf + "]");
                }
            }
        });

        lienzo.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaLimite()){
                    desplegarNotificacion("Por favor completa todos los campos");
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.cambia7.setTextColor(Color.RED);
                }
                else{
                    limSup = Double.parseDouble(lienzo.editTextCambia8.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    icfvarianza = new ICFVARIANZA(tamMuestra,(Math.pow(desviacionEstandar,2)));
                    coeficienteConfianza = icfvarianza.calcGradoConfianza('b',limSup);
                    lienzo.output.setText("El grado de confianza calculado es de: " + coeficienteConfianza +"= " + coeficienteConfianza*100 + "%");
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.limsupcoeficienteconfianza);
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
                    lienzo.paso1Phip.setText("Para calcular el coeficiente de confianza, únicamente se sustituyen los valores correspondientes en la fórmula del coeficiente de confianza: ");
                    lienzo.liminferior.setText("Coeficiente de confianza:");
                    lienzo.textView15.setText("2X[("+ (tamMuestra-1) + "*" +(Math.pow(desviacionEstandar,2)) + ")/" + limSup + "] -1 = " + coeficienteConfianza);
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
                lienzo.cambia1.setTextColor(Color.RED);
                lienzo.cambia2.setTextColor(Color.RED);
                lienzo.cambia3.setTextColor(Color.RED);
                lienzo.cambia4.setTextColor(Color.RED);
                lienzo.cambia5.setTextColor(Color.RED);
                lienzo.cambia6.setTextColor(Color.RED);
                lienzo.cambia7.setTextColor(Color.RED);
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