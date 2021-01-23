package com.example.estadistica;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public interface OpcionContrasteHipotesis {



    public default void desplegarContrasteHipotesisMediaVarConocidaODesconocida(boolean varianzaConocida,Context context){

        if(varianzaConocida == true){

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            final View view = layoutInflater.inflate(R.layout.seleccionar_contraste_hipotesis,null);
            ImageView ayuda1 = view.findViewById(R.id.imageView51);
            ImageView ayuda2 = view.findViewById(R.id.imageView53);
            ImageView ayuda3 = view.findViewById(R.id.imageView58);
            ImageView ayuda4 = view.findViewById(R.id.imageView61);

            ImageView select1 = view.findViewById(R.id.imageView54);
            ImageView select2 = view.findViewById(R.id.imageView55);
            ImageView select3 = view.findViewById(R.id.imageView59);
            ImageView select4 = view.findViewById(R.id.imageView62);



            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setCancelable(false)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

            AlertDialog alertDialog1 = builder.create();
            alertDialog1.setView(view);
            alertDialog1.show();

            select1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,PruebasDeHipotesis.class);
                    intent.putExtra("key","Caso a");
                    context.startActivity(intent);
                }
            });

            select2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,PruebasDeHipotesis.class);
                    intent.putExtra("key","Caso b");
                    context.startActivity(intent);
                }
            });

            select3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,PruebasDeHipotesis.class);
                    intent.putExtra("key","Caso c");
                    context.startActivity(intent);
                }
            });

            select4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,PruebasDeHipotesis.class);
                    intent.putExtra("key","Caso d");
                    context.startActivity(intent);
                }
            });

        }else{

        }

    }

}
