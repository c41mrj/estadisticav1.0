package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class BUSCARVALORENTABLAZSIU extends AppCompatActivity {

    private ActivityPruebaHipBinding lienzo;
    private CalculoTablas tabla = new CalculoTablas();
    private double gradosConfianza,valorEnTablas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);
        lienzo.cambia1.setText("valor: ");
        lienzo.imageViewCambia1.setVisibility(View.GONE);
        lienzo.llc2.setVisibility(View.GONE);
        lienzo.linearLayoutDato3.setVisibility(View.GONE);
        lienzo.llc4.setVisibility(View.GONE);
        lienzo.llc5.setVisibility(View.GONE);
        lienzo.LinearLayoutDato6.setVisibility(View.GONE);
        lienzo.LinearLayoutDato7Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutDato8Opcional.setVisibility(View.GONE);
        lienzo.LinearLayoutDato9Opcional.setVisibility(View.GONE);
        lienzo.procedimiento.setVisibility(View.GONE);
        lienzo.verProcedimiento.setVisibility(View.GONE);
        lienzo.output.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setVisibility(View.VISIBLE);
        lienzo.otroBotonAux.setVisibility(View.VISIBLE);
        lienzo.calcularIntervaloConfianza.setText("Buscar valor en las tablas normal estandar de 0-Z");
        lienzo.button8.setVisibility(View.VISIBLE);
        lienzo.button8.setText("Buscar valor en las tablas porcentuales de la normal estándar de 0-z");
        lienzo.button2.setVisibility(View.VISIBLE);
        lienzo.button2.setText("Buscar valor en las tablas acumuladas de la normal estándar");
        lienzo.otroBotonAux.setText("Buscar valor en las tablas porcentuales de la distribución acumulada normal estándar");
        lienzo.constraintLayoutButtons.setVisibility(View.GONE);
        lienzo.layoutPotencia.setVisibility(View.GONE);

        lienzo.calcularIntervaloConfianza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDato()){
                   desplegarNotificacion("Por favor ingresa un valor a buscar");
                }else {
                    gradosConfianza = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    lienzo.output.setText("El valor encontrado en las tablas de la distribución normal estándar es de: " + tabla.tablaz(gradosConfianza));
                }
            }
        });

        lienzo.otroBotonAux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa un valor a buscar");
                }else {
                    gradosConfianza = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                   lienzo.output.setText("El valor encontrado en las tablas acumuladas de la distribución normal estándar es de: " + tabla.tablazetaAcumulada(gradosConfianza));
                }
            }

        });

        lienzo.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa un valor a buscar");
                }else {
                    gradosConfianza = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    lienzo.output.setText("El valor encontrado en las tablas de la distribución normal estándar es de: " + tabla.tablazPotencia(gradosConfianza));
                }
            }
        });

        lienzo.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faltaDato()){
                    desplegarNotificacion("Por favor ingresa un valor a buscar");
                }else {
                    gradosConfianza = Double.parseDouble(lienzo.mediaPoblacional.getText().toString());
                    lienzo.output.setText("El valor encontrado en las tablas porcentuales de la distribución acumulada normal estándar es de: " + tabla.tablazetaAcumuladaPotencia(gradosConfianza));
                }
            }
        });

        lienzo.cleanit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public boolean faltaDato(){
        if(lienzo.mediaPoblacional.getText().toString().isEmpty()) return true;
        else return false;
    }

    public void desplegarNotificacion(String mensaje){
        Toast anuncio = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        anuncio.show();
    }

}