package com.example.estadistica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.nio.channels.Channel;
import java.util.ArrayList;

public class CTM extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    RelativeLayout primer,segundo,tres;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_t_m);
        // Llenado del spinner
        spinner =(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ctm,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //Instancia de CardViews
        primer = (RelativeLayout) findViewById(R.id.parte);
        segundo =(RelativeLayout) findViewById(R.id.dos);
        tres = (RelativeLayout) findViewById(R.id.tres);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(spinner.getSelectedItemId()==0||spinner.getSelectedItemId()==1)
        {
            segundo.setVisibility(View.INVISIBLE);
            primer.setVisibility(View.VISIBLE);
            tres.setVisibility(View.INVISIBLE);
        }
        if(spinner.getSelectedItemId()==2||spinner.getSelectedItemId()==3){

            primer.setVisibility(View.INVISIBLE);
            segundo.setVisibility(View.VISIBLE);
            tres.setVisibility(View.INVISIBLE);

        }
        else
        {
            primer.setVisibility(View.INVISIBLE);
            segundo.setVisibility(View.INVISIBLE);
            tres.setVisibility(View.VISIBLE);
        }


    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}