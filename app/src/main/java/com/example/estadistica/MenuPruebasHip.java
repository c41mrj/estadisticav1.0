package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.estadistica.databinding.ActivityMenuPruebasHipBinding;

public class MenuPruebasHip extends AppCompatActivity implements OpcionContrasteHipotesis{

    ActivityMenuPruebasHipBinding lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityMenuPruebasHipBinding.inflate(getLayoutInflater());
        View vista = lienzo.getRoot();
        setContentView(vista);

        lienzo.imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desplegarContrasteHipotesisMediaVarConocidaODesconocida(true,MenuPruebasHip.this);
            }
        });


    }
}