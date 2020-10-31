package com.example.estadistica;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


public interface TeoremaCentralDelLimite {


    RelativeLayout datosTCLMedia = null;
    RelativeLayout datosTamMinimoMuestra1 = null;
    RelativeLayout datosTamMinimoMuestra2 = null;
    RelativeLayout datosTamMinimoMuestra3 = null;

    String [] media = {"P(X≤)","P(X>)","Calcular tamaño mínimo de muestra si se conoce D0 y se sabe que : P(|x-μ|<D0)","Calcular tamaño mínimo de muestra si se conoce D0 y se sabe que : P(|x-μ|>D0)","Calcular tamaño mínimo de muestra si se conoce X0"};

    public default void prepararEntornoInicialMedia(Spinner spinner, Context context,RelativeLayout datos1, RelativeLayout datos2, RelativeLayout datos3, TextView subtitulo){
        ArrayAdapter adapter =  new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,media);
        spinner.setAdapter(adapter);

        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                subtitulo.setText(spinner.getSelectedItem().toString());

                if(i == 0 || i == 1){

                    datos1.setVisibility(View.VISIBLE);
                    datos2.setVisibility(View.GONE);
                    datos3.setVisibility(View.GONE);

                }else if(i == 2 || i==3){

                    datos1.setVisibility(View.GONE);
                    datos2.setVisibility(View.VISIBLE);
                    datos3.setVisibility(View.GONE);

                }else if(i == 3 || i == 4){

                    datos1.setVisibility(View.GONE);
                    datos2.setVisibility(View.GONE);
                    datos3.setVisibility(View.VISIBLE);

                }
            }
        });

    }


    public default void ponerMensajes(){

    }

}
