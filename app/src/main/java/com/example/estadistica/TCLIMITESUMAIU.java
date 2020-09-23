package com.example.estadistica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class TCLIMITESUMAIU extends AppCompatActivity {

    private double suma,miu,desviacionEstandar;
    private int tamMuestra;
    private double estadisticoZ,probabilidad;
    private CalculoTablas tabla = new CalculoTablas();
    private ActivityPruebaHipBinding lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        lienzo.cambia1.setText("Media poblacional: ");
        lienzo.cambia2.setText("Desviacion estandar poblacional:");
        lienzo.cambia3.setText("Suma: " );
        lienzo.imageCambia2.setImageResource(R.drawable.tlcsumat);
        lienzo.llc5.setVisibility(View.GONE);
        lienzo.LinearLayoutDato6.setVisibility(View.GONE);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.GONE);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setText("P(T≤)");
        lienzo.otroBotonAux.setText("P(T>)");
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.potenciaPrueba.setVisibility(View.GONE);
        lienzo.potprueba.setVisibility(View.GONE);
        lienzo.limitsuperior.setText("Estadístico Z:");
        lienzo.layoutDesaparece2.setVisibility(View.VISIBLE);
        lienzo.tablaDatos.setVisibility(View.GONE);
        lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
        lienzo.potenciaPrueba.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.promedioMuestral.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lienzo.calcularIntervaloConfianza.setText("P(T≤"+lienzo.promedioMuestral.getText().toString()+")");
                lienzo.otroBotonAux.setText("P(T>"+lienzo.promedioMuestral.getText().toString()+")");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                lienzo.calcularIntervaloConfianza.setText("P(T≤"+lienzo.promedioMuestral.getText().toString()+")");
                lienzo.otroBotonAux.setText("P(T>"+lienzo.promedioMuestral.getText().toString()+")");
            }
        });

        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos que se remarcan en rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                }else{
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.tableRowPromedio.setVisibility(View.GONE);
                    lienzo.tablerowsignificancia.setVisibility(View.GONE);
                    miu = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    suma = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    estadisticoZ = tabla.redondeoDecimales(((suma-(tamMuestra*miu))/(Math.sqrt(tamMuestra)*desviacionEstandar)),3);
                    probabilidad = tabla.tablazetaAcumulada(estadisticoZ);
                    lienzo.output.setText("P(T≤"+suma+")=" + probabilidad +"≈"+ tabla.redondeoDecimales((probabilidad*100),6));
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución de la variable [Z], que posteriormente buscaremos en las tablas de la distribución acumulada de la normal estándar. Para calcular el valor deseado nos guiaremos de la siguiente fórmula:");
                    lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.tlcsumamiuz);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.tlcsumamiu);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.tlcsumat);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.tlcDato1TV.setText("="+miu);
                    lienzo.tlcDato2TV.setText("="+desviacionEstandar);
                    lienzo.tlcDato3TV.setText("=" + suma);
                    lienzo.tlcDato4TV.setText("="+tamMuestra);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.liminferior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.zeta2.setText("["+suma+"-("+tamMuestra+"*"+miu+")]÷[(√"+tamMuestra+"*"+desviacionEstandar+")]= "+ estadisticoZ);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso 2: \n\nEl segundo paso es buscar el valor del estadístico de prueba:\n\nZ= "+ estadisticoZ+"\n\nEn las tablas acumuladas de la distribucion normal estándar, en éste caso el valor en tablas es: " + probabilidad);
                    lienzo.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución de la variable [Z], que posteriormente buscaremos en las tablas de la distribución acumulada de la normal estándar. Para calcular el valor deseado nos guiaremos de la siguiente fórmula:");
                    String aux2 = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TCLIMITESUMAIU.this);
                            region.setMessage("")
                                    .setCancelable(false)
                                    .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog glosario1 = region.create();
                            glosario1.setTitle("¿Qué representa el valor de la fórma límite de la distribución (Z)?");
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TCLIMITESUMAIU.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance());
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.val2.setText("="+miu);
                    lienzo.val4.setText("="+desviacionEstandar);
                    lienzo.val5.setText("="+tamMuestra);
                    lienzo.imageDato6.setImageResource(R.drawable.tlcsumat);
                    lienzo.val6.setText("="+suma);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.paso2Phipodc.setText("Conclusión: Cómo podemos observar, la probabilidad de que la suma sea menor o igual que: "+suma+" es de: " + (probabilidad) + "=" + tabla.redondeoDecimales((probabilidad*100),5) + "%");
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.VISIBLE);
                    lienzo.paso2Phipodc.setVisibility(View.VISIBLE);
                    lienzo.linearLayoutReglaDecision.setVisibility(View.GONE);
                }
            }
        });

        lienzo.otroBotonAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa todos los datos que se remarcan en rojo");
                    lienzo.cambia1.setTextColor(Color.RED);
                    lienzo.cambia2.setTextColor(Color.RED);
                    lienzo.cambia3.setTextColor(Color.RED);
                    lienzo.cambia4.setTextColor(Color.RED);
                }else{
                    lienzo.textView5.setVisibility(View.GONE);
                    lienzo.tablaDatos.setVisibility(View.VISIBLE);
                    lienzo.tableRowPromedio.setVisibility(View.GONE);
                    lienzo.tablerowsignificancia.setVisibility(View.GONE);
                    miu = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    desviacionEstandar = Double.parseDouble(lienzo.desviacionEstandarPob.getText().toString());
                    suma = Double.parseDouble(lienzo.promedioMuestral.getText().toString());
                    tamMuestra = Integer.parseInt(lienzo.tamMuestral.getText().toString());
                    estadisticoZ = tabla.redondeoDecimales(((suma-(tamMuestra*miu))/(Math.sqrt(tamMuestra)*desviacionEstandar)),3);
                    probabilidad = tabla.tablazetaAcumulada(estadisticoZ);
                    double aux = probabilidad;
                    probabilidad = 1-probabilidad;
                    probabilidad = tabla.redondeoDecimales(probabilidad,6);
                    lienzo.output.setText("P(T>"+suma+")=" + probabilidad +"≈"+ tabla.redondeoDecimales((probabilidad*100),6));
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.paso1Phip.setText("El primer paso es calcular el valor de la forma límite de la distribución de la variable [Z], que posteriormente buscaremos en las tablas de la distribución acumulada de la normal estándar. Para calcular el valor deseado nos guiaremos de la siguiente fórmula:");
                    String aux2 = lienzo.paso1Phip.getText().toString();
                    SpannableString ss2 = new SpannableString(aux2);


                    ClickableSpan clickableSpan2 = new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            AlertDialog.Builder region = new AlertDialog.Builder(TCLIMITESUMAIU.this);
                            region.setMessage("")
                                    .setCancelable(false)
                                    .setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog glosario1 = region.create();
                            glosario1.setTitle("¿Qué representa el valor de la fórma límite de la distribución (Z)?");
                            LayoutInflater imagen_alert1 = LayoutInflater.from(TCLIMITESUMAIU.this);
                            final View vista = imagen_alert1.inflate(R.layout.estadisticozconcepto,null);
                            glosario1.setView(vista);
                            glosario1.show();
                        }
                    };
                    ss2.setSpan(clickableSpan2,42,73, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    lienzo.paso1Phip.setText(ss2);
                    lienzo.paso1Phip.setMovementMethod(LinkMovementMethod.getInstance()); lienzo.LinearLayoutEstadisticoZ.setVisibility(View.VISIBLE);
                    lienzo.imageView2.setImageResource(R.drawable.tlcsumamiuz);
                    lienzo.tlcimageDato1.setImageResource(R.drawable.tlcsumamiu);
                    lienzo.tlcImageDato2.setImageResource(R.drawable.dato_e);
                    lienzo.tlcImageDato3.setImageResource(R.drawable.tlcsumat);
                    lienzo.tlcImageDato4.setImageResource(R.drawable.dato_f);
                    lienzo.llz5.setVisibility(View.GONE);
                    lienzo.llz6.setVisibility(View.GONE);
                    lienzo.llz7.setVisibility(View.GONE);
                    lienzo.tlcDato1TV.setText("="+miu);
                    lienzo.tlcDato2TV.setText("="+desviacionEstandar);
                    lienzo.tlcDato3TV.setText("=" + suma);
                    lienzo.tlcDato4TV.setText("="+tamMuestra);
                    lienzo.layCalc1.setVisibility(View.GONE);
                    lienzo.layCalc2.setVisibility(View.GONE);
                    lienzo.LinearLayoutLimSup.setVisibility(View.GONE);
                    lienzo.liminferior.setVisibility(View.GONE);
                    lienzo.layoutDesaparece.setVisibility(View.GONE);
                    lienzo.sustLimSup.setVisibility(View.GONE);
                    lienzo.zeta2.setText("["+suma+"-("+tamMuestra+"*"+miu+")]÷[(√"+tamMuestra+"*"+desviacionEstandar+")]= "+ estadisticoZ);
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.GONE);
                    lienzo.paso2.setText("Paso 2: \n\nEl segundo paso es buscar el valor del estadístico de prueba\nZ= "+ estadisticoZ+"\nEn las tablas acumuladas de la distribucion normal estándar, en éste caso el valor en tablas es: " + aux+".\n\nLas tablas de la distribución normal estándar entrega valores de PROBABILIDAD para los distintos valores menores o iguales que Z. Por lo tanto si se requiere calcular un valor mayor que Z, se hace necesario calcular el complemento de la probabilidad encontrada en tablas:\n\n1-"+ aux + "=" + probabilidad);
                    lienzo.potenciaPrueba.setVisibility(View.GONE);
                    lienzo.val2.setText("="+miu);
                    lienzo.val4.setText("="+desviacionEstandar);
                    lienzo.val5.setText("="+tamMuestra);
                    lienzo.imageDato6.setImageResource(R.drawable.tlcsumat);
                    lienzo.val6.setText("="+suma);
                    lienzo.verProcedimiento.setVisibility(View.VISIBLE);
                    lienzo.paso2Phipodc.setText("Conclusión: Cómo podemos observar, la probabilidad de que la suma sea mayor que: "+suma+" es de: " + (probabilidad) + "=" + tabla.redondeoDecimales((probabilidad*100),5) + "%");
                    lienzo.linearLayoutDesapareceMil.setVisibility(View.VISIBLE);
                    lienzo.paso2Phipodc.setVisibility(View.VISIBLE);
                    lienzo.linearLayoutReglaDecision.setVisibility(View.GONE);
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
                lienzo.verProcedimiento.setVisibility(View.GONE);
                lienzo.cambia1.setTextColor(Color.BLACK);
                lienzo.cambia2.setTextColor(Color.BLACK);
                lienzo.cambia3.setTextColor(Color.BLACK);
                lienzo.cambia4.setTextColor(Color.BLACK);
                lienzo.procedimiento.setVisibility(View.GONE);
                lienzo.output.setVisibility(View.GONE);
            }
        });


    }
    public boolean faltaDato(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()||lienzo.desviacionEstandarPob.getText().toString().isEmpty()||lienzo.promedioMuestral.getText().toString().isEmpty()||lienzo.tamMuestral.getText().toString().isEmpty()) return true;
        else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }


}