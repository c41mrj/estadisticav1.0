package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class INTERFAZICRAZONENTREVARIANZAS extends AppCompatActivity {
    private ActivityPruebaHipBinding lienzo;
    private String intervalo;
    private double coeficienteConfianza,varianza1,varianza2,nivelConfianza;
    private int tamMuestra1,tamMuestra2;
    private ICRAZONENTREVARIANZAS teorema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.cambia1.setText("Varianza muestral 1:");
        lienzo.imageViewCambia1.setImageResource(R.drawable.scuadrada1);
        lienzo.cambia2.setText("Tamaño de muestra 1:");
        lienzo.imageCambia.setImageResource(R.drawable.tlcn1);
        lienzo.cambia3.setText("Varianza muestral 2:");
        lienzo.imageCambia2.setImageResource(R.drawable.scuadrada2);
        lienzo.cambia4.setText("Tamaño de muestra 2:");
        lienzo.imageCambia3.setImageResource(R.drawable.tlcn2);
        lienzo.cambia5.setText("Nivel de confianza:");
        lienzo.imageCambia4.setImageResource(R.drawable.unomenosalfa);
        lienzo.LinearLayoutDato6.setVisibility(View.GONE);
        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setText("Calcular intervalo de confianza para la razón entre varianzas");
        lienzo.layoutPotencia.setVisibility(View.GONE);
        lienzo.button2.setText("Calcular intervalo de confianza para la diferencia de medias, según la conclusión obtenida.");
        lienzo.button8.setText("Calcular el coeficiente de confianza para la razón entre varianzas");
        lienzo.button8.setVisibility(View.VISIBLE);

        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDatoIntervalo()){
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                    lienzo.verProcedimiento.setVisibility(View.GONE);
                    desplegarNotificacion("Por favor ingresa todos los datos solicitados");
                }else{
                    varianza1 = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    tamMuestra1 = Integer.parseInt(lienzo.desviacionEstandarPob.getText().toString());
                    varianza2 = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra2 = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    nivelConfianza = Double.parseDouble(lienzo.significancia.getText().toString());
                    teorema = new ICRAZONENTREVARIANZAS(varianza1,varianza2,nivelConfianza,tamMuestra1,tamMuestra2);
                    intervalo = teorema.getLimInf() + "< (σ1)^2 / (σ2)^2 < " + teorema.getLimSup();
                    lienzo.output.setText("El intervalo de confianza para la razón entre varianzas es:\n"+intervalo+"\n\n"+teorema.getConclusion());
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.teorema.setImageResource(R.drawable.teoremaintervalorazonvarianzas);
                    lienzo.imagePromedio.setImageResource(R.drawable.scuadrada1);
                    lienzo.val1.setText("="+ varianza1);
                    lienzo.imageMiu.setImageResource(R.drawable.tlcn1);
                    lienzo.val2.setText("="+tamMuestra1);
                    lienzo.imageSignificancia.setImageResource(R.drawable.scuadrada2);
                    lienzo.val3.setText("="+varianza2);
                    lienzo.desviation.setImageResource(R.drawable.tlcn2);
                    lienzo.val4.setText("="+tamMuestra2);
                    lienzo.imageN.setImageResource(R.drawable.unomenosalfa);
                    lienzo.val5.setText("="+nivelConfianza);
                    lienzo.dtoOpcional.setVisibility(View.GONE);
                    lienzo.tableRow7.setVisibility(View.GONE);
                    lienzo.tableRow8.setVisibility(View.GONE);
                    String valPorcentualConfianza = teorema.tablas.redondeoDecimales((nivelConfianza*100),4) + "%";
                    double alfa = 1-nivelConfianza;
                    alfa = teorema.tablas.redondeoDecimales(alfa,4);
                    double alfamedios = teorema.tablas.redondeoDecimales((alfa/2),4);
                    lienzo.paso1Phip.setText("Para calcular el intervalo de confianza para la razón entre varianzas, primero se hace necesario encontrar, en las tablas porcentuales de la distribución F a los valores de: f(α)(v1,v2) y f(α/2)(v2,v1), con una confianza de " + valPorcentualConfianza + " donde:\n\n1-α = "+ nivelConfianza + "\n\nα="+alfa+"\n\nα/2="+alfamedios+"\n\nv1= " + tamMuestra1 + " -1 = " + teorema.getV1() +" grados de libertad en el numerador.\n\nv2 =" + tamMuestra2 +" - 1 ="+ teorema.getV2()+" grados de libertad en el denominador.\n\nAl buscar en las tablas obtenemos: \n\nf(α)(v1,v2)= f("+alfa+")("+teorema.getV1()+","+teorema.getV2()+") = " + teorema.getValTablas() + "\n\nf(α/2)(v2,v1)= f("+alfamedios+")("+teorema.getV2()+","+teorema.getV1()+") = " + teorema.getValTablas2()+"\n\nPosteriormente se sustituyen los valores correspondientes, en el teorema del intervalo de confianza para la razón entre varianzas:");
                    lienzo.layCalc1.setVisibility(View.VISIBLE);
                    lienzo.calculo1.setText("Para el límite inferior del intervalo:");
                    lienzo.calcOp1.setImageResource(R.drawable.liminfteoremaintervalorazonvarianzas);
                    lienzo.layCalc2.setVisibility(View.VISIBLE);
                    lienzo.calculo2.setText("Para el límite superior del intervalo:");
                    lienzo.calcOp2.setImageResource(R.drawable.limsupteoremaintervalorazonvarianzas);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.textView15.setText("[("+varianza1+"/"+varianza2+")]*(1/"+teorema.getValTablas()+")");
                    lienzo.zeta2.setText("[("+varianza1+"/"+varianza2+")]*"+teorema.getValTablas2());
                    lienzo.paso2.setText("Conclusión:\n\nLos valores de la razón entre varianzas nuestras dos muestras, con una confianza de " +valPorcentualConfianza +", estarán en el intervalo:\n\n" + intervalo + "\n\n " + teorema.getConclusion());
                    lienzo.button2.setVisibility(View.VISIBLE );

                    if(teorema.getConclusion().contains("las varianzas son iguales")){
                    lienzo.button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),INTERFAZDIFMEDIASVARDESCONOCIDASPEROIGUALESPASANDOPARAMETROS.class);
                            intent.putExtra("varianza1",varianza1);
                            intent.putExtra("varianza2",varianza2);
                            intent.putExtra("tamMuestra1",tamMuestra1);
                            intent.putExtra("tamMuestra2",tamMuestra2);
                            startActivity(intent);
                        }
                    });
                    }else if(teorema.getConclusion().contains("las varianzas son diferentes")){
                        Intent intent = new Intent(getApplicationContext(),ICDIFMEDIASVARDESCONOCIDASPERODIFERENTESPASANDOPARAMETROS.class);
                        intent.putExtra("varianza1",varianza1);
                        intent.putExtra("varianza2",varianza2);
                        intent.putExtra("tamMuestra1",tamMuestra1);
                        intent.putExtra("tamMuestra2",tamMuestra2);
                        startActivity(intent);
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


    }

    public boolean faltaDatoIntervalo(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()||lienzo.significancia.getText().toString().isEmpty()) return true;
        else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }
}