package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.estadistica.databinding.ActivityPruebaHipBinding;

public class PHIPDIFMVARDESCON extends AppCompatActivity {

    private ActivityPruebaHipBinding lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lienzo = ActivityPruebaHipBinding.inflate(getLayoutInflater());
        View view = lienzo.getRoot();
        setContentView(view);

        

    }
}