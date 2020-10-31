package com.example.estadistica;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.estadistica.databinding.ActivityCTMBinding;

import java.util.ArrayList;

public class CTM extends AppCompatActivity implements AdapterView.OnItemSelectedListener,TeoremaCentralDelLimite {

    Spinner spinner;
    RelativeLayout primer,segundo,tres;
    boolean swit;
    String [] media = {"P(X≤)","P(X>)","Calcular tamaño mínimo de muestra si se conoce D0 y se sabe que : P(|x-μ|<D0)","Calcular tamaño mínimo de muestra si se conoce D0 y se sabe que : P(|x-μ|>D0)","Calcular tamaño mínimo de muestra si se conoce X0"};

    ActivityCTMBinding lienzo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityCTMBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);
        lienzo.botoncalcular.setImageResource(R.drawable.calculadora);
        swit = false;
        lienzo.botoncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lienzo.botoncalcular.setAnimation(R.raw.loading);
                lienzo.botoncalcular.playAnimation();
                lienzo.botoncalcular.setRepeatCount(3);
                lienzo.botoncalcular.animate()
                        .alpha(0f)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                lienzo.botoncalcular.setImageResource(R.drawable.calculadora);
                                lienzo.botoncalcular.setAlpha(1f);
                            }
                        });
            }
        });
       // lienzo.botoncalcular.setVisibility(View.GONE);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        lienzo.subtitulo.setText(lienzo.spinner.getSelectedItem().toString());
        if(lienzo.spinner.getSelectedItemId()==0||lienzo.spinner.getSelectedItemId()==1)
        {
            lienzo.datosTCLMedia.setVisibility(View.VISIBLE);
            lienzo.datosTamMinimoMuestraMedia1.setVisibility(View.GONE);
            lienzo.tres.setVisibility(View.GONE);
            lienzo.layoutProcedimientoIC.setVisibility(View.VISIBLE);
            lienzo.layoutProcedimientoTamMinimoMuestra.setVisibility(View.GONE);
        }
        if(lienzo.spinner.getSelectedItemId()==2||lienzo.spinner.getSelectedItemId()==3){

            lienzo.datosTCLMedia.setVisibility(View.GONE);
            lienzo.datosTamMinimoMuestraMedia1.setVisibility(View.VISIBLE);
            lienzo.tres.setVisibility(View.GONE);

            lienzo.layoutProcedimientoIC.setVisibility(View.GONE);
            lienzo.layoutProcedimientoTamMinimoMuestra.setVisibility(View.VISIBLE);

        }
        else
        {

            lienzo.datosTCLMedia.setVisibility(View.GONE);
            lienzo.datosTamMinimoMuestraMedia1.setVisibility(View.GONE);
            lienzo.tres.setVisibility(View.VISIBLE);

            lienzo.layoutProcedimientoIC.setVisibility(View.GONE);
            lienzo.layoutProcedimientoTamMinimoMuestra.setVisibility(View.VISIBLE);
        }

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}