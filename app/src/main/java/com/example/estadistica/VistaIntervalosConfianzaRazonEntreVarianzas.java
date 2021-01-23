package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityVistaIntervalosConfianzaBinding;

public class VistaIntervalosConfianzaRazonEntreVarianzas extends AppCompatActivity implements conversiones{

    private ActivityVistaIntervalosConfianzaBinding lienzo;
    private ICRAZONENTREVARIANZAS teorema;
    private ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES teoremaIguales;
    private ICDIFMEDIASVARDESCONOCIDASPERODIFERENTES teoremaDiferentes;
    private double varianzaMuestral1,varianzaMuestral2, nivelConfianza, diferenciaPromedios;
    private int tamMuestra1, tamMuestra2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        lienzo = ActivityVistaIntervalosConfianzaBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);

        lienzo.subtitulo.setText("Calcular intervalos de confianza para la razón entre varianzas");
        lienzo.spinner.setVisibility(View.GONE);
        desplegarEntornoIC();

        lienzo.delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.delete2.setAnimation(R.raw.delete);
                lienzo.delete2.setSpeed((float) 999999999);
                lienzo.delete2.playAnimation();
                lienzo.delete2.setRepeatCount(1000000);
                lienzo.delete2.animate()
                        .alpha(0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                lienzo.delete2.setImageResource(R.drawable.gomadeborrar);
                                lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
                                lienzo.varianzaMuestral1ICRazonVarianzas.setText("");
                                lienzo.varianzaMuestral2ICRazonVarianzas.setText("");
                                lienzo.tamMuestra1ICRazonVarianzas.setText("");
                                lienzo.tamMuestra2ICRazonVarianzas.setText("");
                                lienzo.nivelConfianzaRazonVarianzas.setText("");
                                lienzo.resultadoIC.setText("El resultado es:");
                            }
                        });

            }
        });


    }

    public void desplegarEntornoIC(){
        
        lienzo.datosICRazonEntreVarianzas.setVisibility(View.VISIBLE);
        lienzo.datosNivelConfianzaRazonEntreVarianzas.setVisibility(View.GONE);
        lienzo.botonayuda.setVisibility(View.GONE);

        lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lienzo.botonCalcularIC.setAnimation(R.raw.loading);
                lienzo.botonCalcularIC.setSpeed((float) 999999999);
                lienzo.botonCalcularIC.playAnimation();
                lienzo.botonCalcularIC.setRepeatCount(1000000);
                lienzo.botonCalcularIC.animate()
                        .alpha(0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                lienzo.botonCalcularIC.setImageResource(R.drawable.calculadora);
                                lienzo.botonCalcularIC.setAlpha(1f);

                                try{
                                    varianzaMuestral1 = convertirStringADouble(lienzo.varianzaMuestral1ICRazonVarianzas.getText().toString());
                                    tamMuestra1 = convertirStringAInt(lienzo.tamMuestra1ICRazonVarianzas.getText().toString());
                                    varianzaMuestral2 = convertirStringADouble(lienzo.varianzaMuestral2ICRazonVarianzas.getText().toString());
                                    tamMuestra2 = convertirStringAInt(lienzo.tamMuestra2ICRazonVarianzas.getText().toString());
                                    nivelConfianza = convertirStringADouble(lienzo.nivelConfianzaRazonVarianzas.getText().toString());
                                    teorema = new ICRAZONENTREVARIANZAS(varianzaMuestral1,varianzaMuestral2,nivelConfianza,tamMuestra1,tamMuestra2);
                                    String intervalo = teorema.getLimInf() + "<μ<"+ teorema.getLimSup();
                                    lienzo.resultadoIC.setText("Con un nivel de confianza de " + nivelConfianza + "\nEl intervalo de confianza calculado es:\n" + intervalo);


                                    lienzo.imageView10.setImageResource(R.drawable.scuadrada1);
                                    lienzo.textView80.setText(" = " + varianzaMuestral1);
                                    lienzo.imageView13.setImageResource(R.drawable.n1);
                                    lienzo.desvEstandar.setText(" = " + tamMuestra1);
                                    lienzo.imageView14.setImageResource(R.drawable.scuadrada2);
                                    lienzo.dato3TabTCL.setText(" = " + tamMuestra2);
                                    lienzo.imagenDato1tamMinMuestraTclMedia.setImageResource(R.drawable.unomenosalfa);
                                    lienzo.dato4TabTCL.setText(" = " + nivelConfianza);


                                    lienzo.imageView18.setImageResource(R.drawable.teoremaintervalodeconfianzarazonentrevarianzas);
                                    lienzo.paso1IC.setText("Al observar el teorema presentado anteriormente nos podemos percatar de que se hace necesario buscar valores en las tablas de la distribución F. Donde:\n\n" +
                                            "V1 = n1 -1 = " + (tamMuestra1 - 1) + " grados de libertad\nV2 = n2 - 1 = " + (tamMuestra2 - 1) + " grados de libertad");

                                    lienzo.rowOpcionalIcVarianza2.setVisibility(View.VISIBLE);
                                    lienzo.ImageViewOpcional2.setImageResource(R.drawable.alfamedios);
                                    lienzo.textViewOpcional2.setText(" = " + redondeoDecimales(((1-nivelConfianza)/2),5));
                                    lienzo.alfaopcional.setImageResource(R.drawable.fenalfamediosv1v2);
                                    lienzo.valoralfaopcional.setText(" = " + teorema.getValTablas());
                                    lienzo.tablaopcional.setImageResource(R.drawable.fenalfamediosv2v1);
                                    lienzo.valortablaopcional.setText(" = " + teorema.getValTablas2());

                                    lienzo.imageView20.setImageResource(R.drawable.liminfteoremaintervalodeconfianzarazonentrevarianzas);
                                    lienzo.imageView21.setImageResource(R.drawable.limsupteoremaintervalodeconfianzarazonentrevarianzas);

                                    lienzo.conclusionIC1.setText("Con un nivel de confianza de: " + nivelConfianza + "\nEl intervalo de confianza calculado es:\n" + intervalo + "\n\n" + teorema.getConclusion());

                                    lienzo.botonayuda.setVisibility(View.VISIBLE);

                                    lienzo.botonayuda.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            AlertDialog.Builder builder = new AlertDialog.Builder(VistaIntervalosConfianzaRazonEntreVarianzas.this);
                                            builder.setMessage(teorema.getConclusion() + "\n\n¿Desea calcular el intervalo de confianza para la diferencia de medias según la conclusión obtenida?")
                                                    .setPositiveButton("Calcular intervalo de confianza", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                            desplegarIngresarDiferenciaDePromedios();
                                                        }
                                                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                }
                                            });

                                        }
                                    });


                                }catch(NumberFormatException numberFormatException){
                                    Toast.makeText(VistaIntervalosConfianzaRazonEntreVarianzas.this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


                
                
            }
        });
                
    }

    public void desplegarIngresarDiferenciaDePromedios(){

        LayoutInflater layoutInflater = LayoutInflater.from(this);

        final View view = layoutInflater.inflate(R.layout.ingresar_datos_diferenciapromedios,null);
        final EditText editTextDiferenciaPromedios = view.findViewById(R.id.editTextNumberSigned);
        final EditText editTextNivelConfianza = view.findViewById(R.id.editTextNumberDecimal);
        editTextNivelConfianza.setText(String.valueOf(nivelConfianza));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("Calcular", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(editTextDiferenciaPromedios.getText().toString().isEmpty() || editTextNivelConfianza.getText().toString().isEmpty()){
                    Toast.makeText(VistaIntervalosConfianzaRazonEntreVarianzas.this, "Por favor asegurate de ingresar todos los datos necesarios", Toast.LENGTH_SHORT).show();
                    desplegarIngresarDiferenciaDePromedios();
                }else{
                    nivelConfianza = convertirStringADouble(editTextNivelConfianza.getText().toString());
                    diferenciaPromedios = convertirStringADouble(editTextDiferenciaPromedios.getText().toString());
                    if(teorema.getConclusion().contains("las varianzas son diferentes")){
                        calcularIntervaloConfianzaDiferenciaMediasVarDesconocidasPeroDiferentes();
                    }else if(teorema.getConclusion().contains("las varianzas son iguales")){
                        calcularIntervaloConfianzaDiferenciaMediasVarDesconocidasIguales();
                    }
                }
            }
        });

        AlertDialog alertDialog1 = builder.create();
        alertDialog1.setTitle("Por favor ingresa los siguientes datos:");
        alertDialog1.setView(view);
        alertDialog1.show();

    }

    public void calcularIntervaloConfianzaDiferenciaMediasVarDesconocidasIguales() {

        lienzo.botonayuda.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);

        lienzo.tableRowOpcional2.setVisibility(View.VISIBLE);
        lienzo.tableRowOpcional3.setVisibility(View.VISIBLE);
        lienzo.imageView27.setImageResource(R.drawable.scuadrada1);
        lienzo.imageView28.setImageResource(R.drawable.n1);
        lienzo.imageView10.setImageResource(R.drawable.scuadrada2);
        lienzo.imageView13.setImageResource(R.drawable.n2);
        lienzo.imageView14.setImageResource(R.drawable.unomenosalfa);
        lienzo.imagenDato1tamMinMuestraTclMedia.setImageResource(R.drawable.diferenciapromedios);

        lienzo.tableRowOpcional2.setVisibility(View.VISIBLE);
        lienzo.tableRowOpcional3.setVisibility(View.VISIBLE);
        lienzo.imageView27.setImageResource(R.drawable.scuadrada1);
        lienzo.imageView28.setImageResource(R.drawable.n1);
        lienzo.imageView10.setImageResource(R.drawable.scuadrada2);
        lienzo.imageView13.setImageResource(R.drawable.n2);
        lienzo.imageView14.setImageResource(R.drawable.unomenosalfa);
        lienzo.imagenDato1tamMinMuestraTclMedia.setImageResource(R.drawable.diferenciapromedios);

        lienzo.imageView18.setImageResource(R.drawable.teoremaintervaloconfianzadiferenciamediasvardesconocidasperoiguales);
        lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
        lienzo.alfaopcional.setImageResource(R.drawable.alfamedios);
        lienzo.tablaopcional.setImageResource(R.drawable.tenalfamedios);
        lienzo.imageView20.setImageResource(R.drawable.liminfteoremaintervaloconfianzadiferenciamediasvardesconocidasperoiguales);
        lienzo.imageView21.setImageResource(R.drawable.limsupteoremaintervaloconfianzadiferenciamediasvardesconocidasperoiguales);

        teoremaIguales = new ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES(varianzaMuestral1,varianzaMuestral2,tamMuestra1,tamMuestra2,diferenciaPromedios,nivelConfianza);
        String intervalo = teoremaIguales.getLimInf() +  "<μ<" + teoremaIguales.getLimSup();
        lienzo.resultadoIC.setText("Con un nivel de confianza de " + nivelConfianza + "\n\nEl intervalo de confianza calculado es:\n" + intervalo);
        lienzo.layoutProcedimientoIc.setVisibility(View.VISIBLE);

        lienzo.textView99.setText(" = " + varianzaMuestral1);
        lienzo.textView103.setText(" = " + tamMuestra1);
        lienzo.textView80.setText(" = " + varianzaMuestral2);
        lienzo.desvEstandar.setText(" = " + tamMuestra2);
        lienzo.dato3TabTCL.setText(" = " + nivelConfianza);
        lienzo.dato4TabTCL.setText(" = " + diferenciaPromedios);

        lienzo.paso1IC.setText("Al observar el teorema anteriormente presentado podemos percatarnos de que es necesario calcular un valor para la estimación común de la desviación estándar poblacional (Sp).\n\nPara ello se utilizará la siguiente fórmula:");
        lienzo.textView107.setText("En éste caso el valor calculado es: " + teoremaIguales.getSp());
        lienzo.textView108.setText("Una vez que calculamos el valor de Sp podemos percatarnos de que es necesario buscar el valor de t(α/2) en  las tablas de la distribución t-student con V = n1 + n2 - 2 = "  + teoremaIguales.getGradosLibertad() +
                " grados de libertad. Donde:");
        lienzo.valoralfaopcional.setText(" = " + redondeoDecimales(((1-nivelConfianza)/2),4));
        lienzo.valortablaopcional.setText(" = " + teoremaIguales.getValTablas());
        lienzo.conclusionIC1.setText("Con un nivel de confianza de " + nivelConfianza + " podemos asegurar que la diferencia de medias se encontrará en el intervalo:\n" + intervalo);



    }

    public void calcularIntervaloConfianzaDiferenciaMediasVarDesconocidasPeroDiferentes(){

        lienzo.botonayuda.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);

        lienzo.tableRowOpcional2.setVisibility(View.VISIBLE);
        lienzo.tableRowOpcional3.setVisibility(View.VISIBLE);
        lienzo.imageView27.setImageResource(R.drawable.scuadrada1);
        lienzo.imageView28.setImageResource(R.drawable.n1);
        lienzo.imageView10.setImageResource(R.drawable.scuadrada2);
        lienzo.imageView13.setImageResource(R.drawable.n2);
        lienzo.imageView14.setImageResource(R.drawable.unomenosalfa);
        lienzo.imagenDato1tamMinMuestraTclMedia.setImageResource(R.drawable.diferenciapromedios);

        lienzo.imageView18.setImageResource(R.drawable.teoremaintervalosconfianzadifmediasvardesconocidasdiferentes);
        lienzo.layoutDesaparece.setVisibility(View.VISIBLE);
        lienzo.alfaopcional.setImageResource(R.drawable.alfamedios);
        lienzo.tablaopcional.setImageResource(R.drawable.tenalfamedios);
        lienzo.imageView20.setImageResource(R.drawable.liminfteoremaintervalosconfianzadifmediasvardesconocidasdiferentes);
        lienzo.imageView21.setImageResource(R.drawable.limsupteoremaintervalosconfianzadifmediasvardesconocidasdiferentes);


        teoremaDiferentes = new ICDIFMEDIASVARDESCONOCIDASPERODIFERENTES(varianzaMuestral1,varianzaMuestral2,tamMuestra1,tamMuestra2,diferenciaPromedios,nivelConfianza);
        String intervalo = teoremaDiferentes.getLimInf() +  "<μ<" + teoremaDiferentes.getLimSup();
        lienzo.resultadoIC.setText("Con un nivel de confianza de " + nivelConfianza + "\n\nEl intervalo de confianza calculado es:\n" + intervalo);
        lienzo.layoutProcedimientoIc.setVisibility(View.VISIBLE);

        lienzo.textView99.setText(" = " + varianzaMuestral1);
        lienzo.textView103.setText(" = " + tamMuestra1);
        lienzo.textView80.setText(" = " + varianzaMuestral2);
        lienzo.desvEstandar.setText(" = " + tamMuestra2);
        lienzo.dato3TabTCL.setText(" = " + nivelConfianza);
        lienzo.dato4TabTCL.setText(" = " + diferenciaPromedios);

        lienzo.paso1IC.setText("Al observar el teoremaDiferentes anteriormente presentado podemos percatarnos de que es necesario calcular un valor para la estimación de los grados de libertad que usaremos posteriormente para buscar valores en tablas.\n\nPara ello se utilizará la siguiente fórmula:");
        lienzo.textView107.setText("En éste caso el valor calculado es: " + teoremaDiferentes.getGradosLibertad() + " grados de libertad");

        lienzo.imageView31.setImageResource(R.drawable.formulavgradoslibertad);

        lienzo.textView108.setText("Una vez que calculamos un valor para la aproximación para v grados de libertad, podemos percatarnos de que es necesario buscar el valor de t(α/2) en  las tablas de la distribución t-student con V = n1 + n2 - 2 = "  + teoremaDiferentes.getGradosLibertad() +
                " grados de libertad. Donde:");
        lienzo.valoralfaopcional.setText(" = " + redondeoDecimales(((1-nivelConfianza)/2),4));
        lienzo.valortablaopcional.setText(" = " + teoremaDiferentes.getValTablas());
        lienzo.conclusionIC1.setText("Con un nivel de confianza de " + nivelConfianza + " podemos asegurar que la diferencia de medias se encontrará en el intervalo:\n" + intervalo);


    }

    /*public void desplegarEntornoGradoConfianza(char limiteConocido){

        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
        lienzo.resultadoIC.setText("El resultado es:");

        lienzo.tbRowOpcional1.setVisibility(View.VISIBLE);
        lienzo.imageView29.setImageResource(R.drawable.scuadrada1);
        lienzo.imageView30.setImageResource(R.drawable.n1);
        lienzo.uno.setImageResource(R.drawable.scuadrada2);
        lienzo.segundo.setImageResource(R.drawable.n2);
        lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremacoeficienteconfianzarazonentrevarianzas);

        if(limiteConocido == 'a'){
            lienzo.TextViewLimiteConocidoRazonVarianzas.setText("Límite inferior conocido");
            lienzo.imagenLimiteConocido.setImageResource(R.drawable.a);

            lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try{
                        varianzaMuestral1 = convertirStringADouble(lienzo.varianzaMuestralCoeficienteConfRazonVarianzas1.getText().toString());
                        varianzaMuestral2 = convertirStringADouble(lienzo.varianzaMuestralCoeficienteConfRazonVarianzas2.getText().toString());
                        tamMuestra1 = convertirStringAInt(lienzo.tamMuestral1CoeficienteConfianzaRazonVarianzas.getText().toString());
                        tamMuestra2 = convertirStringAInt(lienzo.tamMuestral2CoeficienteConfianzaRazonVarianzas.getText().toString());
                        double limInf = convertirStringADouble(lienzo.limiteConocidoCoeficienteConfianzaRazonEntreVarianzas.getText().toString());
                        teorema = new ICRAZONENTREVARIANZAS(varianzaMuestral1,varianzaMuestral2,tamMuestra1,tamMuestra2,'a',limInf);
                    }catch (NumberFormatException numberFormatException){
                        Toast.makeText(VistaIntervalosConfianzaRazonEntreVarianzas.this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }


    }

     */   // Coeficiente de confianza pendiente por hacer

    public void restaurarElementos(){
    }

}