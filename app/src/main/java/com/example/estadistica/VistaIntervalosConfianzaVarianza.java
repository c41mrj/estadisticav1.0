package com.example.estadistica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityVistaIntervalosConfianzaBinding;

public class VistaIntervalosConfianzaVarianza extends AppCompatActivity implements IntervalosDeConfianza,conversiones,activityManager{

    private ActivityVistaIntervalosConfianzaBinding lienzo;
    private double varianza,nivelConfianza,limInf,limSup;
    private int tamMuestra;
    private ICFVARIANZA teorema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityVistaIntervalosConfianzaBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        String elementos[] = {"Calcular intervalo de confianza para la varianza o desviación estándar","Calcular grado de confianza si se conoce el límite inferior","Calcular grado de confianza si se conoce el límite inferior"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,elementos);
        lienzo.spinner.setAdapter(arrayAdapter);

        lienzo.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desplegarNotaCalcularLimiteCoeficienteConfianza(VistaIntervalosConfianzaVarianza.this);
            }
        });

        lienzo.delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.editTextVarianzaMuestralIcVarianza.setText("");
                lienzo.editTextNivelConfianzaIcVarianza.setText("");
                lienzo.editTextTamMuestraIcVarianza.setText("");
                lienzo.editTextVarianzaMuestralCoeficienteConfianzaVarianza.setText("");
                lienzo.editTextLimiteInferiorCoeficienteConfianzaVarianza.setText("");
                lienzo.tamMuestraCoeficienteConfianzaVarianza.setText("");
                lienzo.resultadoIC.setText("");
                lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
                lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
            }
        });


        lienzo.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if(lienzo.spinner.getSelectedItemPosition() == 0){
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                    prepararEntornoIntervalosConfianzaMedia();
                    lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try{
                                varianza = redondeoDecimales((convertirStringADouble(lienzo.editTextVarianzaMuestralIcVarianza.getText().toString())),4);
                                nivelConfianza = redondeoDecimales(convertirStringADouble(lienzo.editTextNivelConfianzaIcVarianza.getText().toString()),4);
                                tamMuestra = convertirStringAInt(lienzo.editTextTamMuestraIcVarianza.getText().toString());
                                if(tamMuestra>=122){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(VistaIntervalosConfianzaVarianza.this);
                                    builder.setCancelable(false)
                                            .setMessage("Por favor asegurate de ingresar un tamaño de muestra menor que 122, ya que las tablas de la distribución chi cuadrada que incluye este software solo contiene valores para los grados de libertad menores o iguales que 121")
                                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.cancel();
                                                }
                                            });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.setTitle("¡ALERTA!");
                                    alertDialog.show();
                                }else{
                                    teorema = new ICFVARIANZA(varianza,tamMuestra,nivelConfianza);
                                    limInf = teorema.calcLimInf();
                                    limSup = teorema.calcLimSup();
                                    String intervalo =limInf + "<μ<" + limSup;
                                    desplegarProcedimientoIC();
                                    lienzo.resultadoIC.setText("Con un nivel de confianza de " + nivelConfianza + " el intervalo de confianza calculado es:\n\n" + intervalo);
                                    lienzo.desvEstandar.setText("= " + varianza);
                                    lienzo.dato3TabTCL.setText("= " + nivelConfianza);
                                    lienzo.dato4TabTCL.setText("= " + tamMuestra);
                                    double aux =redondeoDecimales((1-nivelConfianza)/2,4);
                                    lienzo.textViewOpcional1.setText("= " + aux);
                                    lienzo.textViewOpcional2.setText("= " + teorema.getValTablas1());
                                    lienzo.valoralfaopcional.setText("= " + redondeoDecimales((1-aux),4));
                                    lienzo.valortablaopcional.setText("= " + teorema.getValorTablas());
                                    lienzo.conclusionIC1.setText("Se puede asegurar con un nivel de confianza del " + redondeoDecimales((nivelConfianza*100)
                                            ,5) + "% que el parámetro varianza poblacional se encontrará en el intervalo:\n\n" + intervalo);
                                }
                            }catch (Exception e){
                                Toast.makeText(VistaIntervalosConfianzaVarianza.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else if(lienzo.spinner.getSelectedItemPosition() == 1){
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                    prepararEntornoCoeficienteConfianzaMediaVarConocida('a');
                    lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try{
                                varianza = redondeoDecimales(convertirStringADouble(lienzo.editTextVarianzaMuestralCoeficienteConfianzaVarianza.getText().toString()),4);
                                limInf = redondeoDecimales(convertirStringADouble(lienzo.editTextLimiteInferiorCoeficienteConfianzaVarianza.getText().toString()),4);
                                tamMuestra = convertirStringAInt(lienzo.tamMuestraCoeficienteConfianzaVarianza.getText().toString());
                                teorema = new ICFVARIANZA(tamMuestra,varianza);
                                double coeficienteConfianza = teorema.calcGradoConfianza('a',limInf);
                                desplegarProcedimientoGradoC();
                                lienzo.resultadoIC.setText("Con un límite inferior de: " + limInf + "\n\nEl grado de confianza calculado es: " + coeficienteConfianza);
                                lienzo.val2CoeficienteConf.setText("= " + varianza);
                                lienzo.val3CoeficienteConf.setText("= " + limInf);
                                lienzo.val4CoefConf.setText("= " + tamMuestra);
                                lienzo.valDeterminante.setText("= " + teorema.getDeterminante());
                                lienzo.textView66.setText("El segundo paso es buscar el valor del determinante calculado hace un momento en las tablas acumuladas de la distribución chi-cuadrada.Donde:\n\nX("+teorema.getDeterminante()+") = " + teorema.getValorTablas());
                                lienzo.textView92.setText("1-α = 1 - (2*X(" + teorema.getDeterminante()+ ")) = 1 - (2*" + teorema.getValorTablas() + ") = " + coeficienteConfianza);
                            }catch(Exception e){
                                Toast.makeText(VistaIntervalosConfianzaVarianza.this, "Por favor ingresa correctamente todos los datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else if(lienzo.spinner.getSelectedItemPosition() == 2){
                    lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
                    prepararEntornoCoeficienteConfianzaMediaVarConocida('b');
                    lienzo.botonCalcularIC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try{
                                varianza = redondeoDecimales(convertirStringADouble(lienzo.editTextVarianzaMuestralCoeficienteConfianzaVarianza.getText().toString()),4);
                                limSup = redondeoDecimales(convertirStringADouble(lienzo.editTextLimiteInferiorCoeficienteConfianzaVarianza.getText().toString()),4);
                                tamMuestra = convertirStringAInt(lienzo.tamMuestraCoeficienteConfianzaVarianza.getText().toString());
                                teorema = new ICFVARIANZA(tamMuestra,varianza);
                                desplegarProcedimientoGradoC();
                                double coeficienteConfianza = teorema.calcGradoConfianza('b',limSup);
                                lienzo.resultadoIC.setText("Con un límite superior de: " + limSup + "\n\nEl grado de confianza calculado es: " + coeficienteConfianza);
                                lienzo.val2CoeficienteConf.setText("= " + varianza);
                                lienzo.val3CoeficienteConf.setText("= " + limSup);
                                lienzo.val4CoefConf.setText("= " + tamMuestra);
                                lienzo.valDeterminante.setText("= " + teorema.getDeterminante());
                                lienzo.textView66.setText("El segundo paso es buscar el valor del determinante calculado hace un momento en las tablas acumuladas de la distribución chi-cuadrada. Donde:\n\nX("+teorema.getDeterminante()+") = " + teorema.getValorTablas());
                                lienzo.textView92.setText("1-α = (2*X(" + teorema.getDeterminante()+ ")) -1 = (2*" + teorema.getValorTablas() + ") - 1 = " + coeficienteConfianza);
                            }catch(Exception e){
                                Toast.makeText(VistaIntervalosConfianzaVarianza.this, "Por favor ingresa correctamente todos los datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void desplegarProcedimientoIC(){
        lienzo.layoutProcedimientoIc.setVisibility(View.VISIBLE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
    }

    public void desplegarProcedimientoGradoC(){
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.VISIBLE);
    }

    @Override
    public void prepararEntornoIntervalosConfianzaMedia() {
        lienzo.datosICVarianza.setVisibility(View.VISIBLE);
        lienzo.datosGradosConfianzaVarianza.setVisibility(View.GONE);
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
        lienzo.resultadoIC.setText("El resultado es:");
        lienzo.imageView18.setImageResource(R.drawable.teoremaicvarianza);
        lienzo.rowOpcionalIcVarianza1.setVisibility(View.VISIBLE);
        lienzo.imageViewOpcional1.setImageResource(R.drawable.unomenosalfamedios);
        lienzo.rowOpcionalIcVarianza2.setVisibility(View.VISIBLE);
        lienzo.ImageViewOpcional2.setImageResource(R.drawable.chienunomenosalfamedios);
        lienzo.alfaopcional.setImageResource(R.drawable.alfamedios);
        lienzo.tablaopcional.setImageResource(R.drawable.chienalfamedios);
        lienzo.tableRowDatoPromedio.setVisibility(View.GONE);
        lienzo.imageView13.setImageResource(R.drawable.scuadradanmenosuno);
        lienzo.paso1IC.setText("Al observar el teorema presentado anteriormente nos podemos percatar de que es necesario buscar los valores de:\nα/2 y de 1-(α/2)\nEn las tablas de la" +
                "distribución chi-cuadrada con:\n\nv = n-1. Donde:");
        lienzo.imageView20.setImageResource(R.drawable.liminfteoremaicvarianza);
        lienzo.imageView21.setImageResource(R.drawable.limsupteoremaicvarianza);
    }

    @Override
    public void prepararEntornoCoeficienteConfianzaMediaVarConocida(char limiteConocido) {
        lienzo.datosICVarianza.setVisibility(View.GONE);
        lienzo.datosGradosConfianzaVarianza.setVisibility(View.VISIBLE);
        lienzo.layoutProcedimientoIc.setVisibility(View.GONE);
        lienzo.layoutProcedimientoCoeficienteConfianza.setVisibility(View.GONE);
        lienzo.resultadoIC.setText("El resultado es:");
        lienzo.tableRowDesaparece.setVisibility(View.GONE);
        lienzo.segundo.setImageResource(R.drawable.scuadradanmenosuno);
        if(limiteConocido == 'a'){
            lienzo.imageViewLiminf.setImageResource(R.drawable.a);
            lienzo.textViewLiminf.setText("Límite inferior conocido");
            lienzo.ab.setImageResource(R.drawable.a);
            lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremacoeficienteconfianzavarianza);
            lienzo.imageView6.setImageResource(R.drawable.liminfteoremacoeficienteconfianzavarianza);
            lienzo.determinante.setImageResource(R.drawable.liminfdeterminanteteoremacoeficienteconfianzavarianza);
        } else if(limiteConocido == 'b'){
            lienzo.imageViewLiminf.setImageResource(R.drawable.b);
            lienzo.textViewLiminf.setText("Límite superior conocido");
            lienzo.ab.setImageResource(R.drawable.b);
            lienzo.teoremaCoeficienteConfianza1.setImageResource(R.drawable.teoremacoeficienteconfianzavarianza);
            lienzo.imageView6.setImageResource(R.drawable.limsupteoremacoeficienteconfianzavarianza);
            lienzo.determinante.setImageResource(R.drawable.limsupdeterminanteteoremacoeficienteconfianzavarianza);
        }
    }

    @Override
    public void prepararEntornoIC() {

    }


}