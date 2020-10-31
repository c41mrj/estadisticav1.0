package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.estadistica.databinding.ActivityVistaIntervalosConfianzaBinding;

public class VistaIntervalosConfianza extends AppCompatActivity {

    ActivityVistaIntervalosConfianzaBinding lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityVistaIntervalosConfianzaBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        String elementos[] = {"Intervalo de confianza para la media","Tamaño mínimo de muestra","Coeficiente de confianza"};
        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,elementos);
        lienzo.spinner.setAdapter(adapter);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
        if(lienzo.spinner.getSelectedItemId()==0){

            lienzo.datosICMedia.setVisibility(View.VISIBLE);
            lienzo.datosTamMinimoMuestra.setVisibility(View.GONE);
            lienzo.datosGradoConfianza.setVisibility(View.GONE);
        }
        if(lienzo.spinner.getSelectedItemId()==1){

            lienzo.datosICMedia.setVisibility(View.GONE);
            lienzo.datosTamMinimoMuestra.setVisibility(View.VISIBLE);
            lienzo.datosGradoConfianza.setVisibility(View.GONE);

        }
        else
        {

            lienzo.datosICMedia.setVisibility(View.GONE);
            lienzo.datosTamMinimoMuestra.setVisibility(View.GONE);
            lienzo.datosGradoConfianza.setVisibility(View.VISIBLE);

        }

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}