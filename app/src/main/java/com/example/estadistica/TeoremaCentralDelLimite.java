package com.example.estadistica;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public interface TeoremaCentralDelLimite{


    String paso1TCLMedia = "El primer paso para calcular la probabilidad deseada es calcular el valor de la estadística de prueba Z para la media, que posteriormente usaremos para buscar en las tablas de la distribución normal estándar.\n\nPara poder realizar el cálculo es necesario el siguiente teorema:";
    String paso2TCLMedia1 = "Después de sustituir los valores correspondientes en el teorema presentado anteriormente obtenemos que en éste caso el valor de Z es: \n\nZ = ";
    String paso2TCLMedia2 = "\n\nAhora se hace necesario buscar el valor calculado de Z en las tablas de la distribución normal estándar.";
    String paso1 = "";

    String paso1TCLDifMedias = "El primer paso para calcular la probabilidad deseada es calcular el valor de la estadística de prueba Z para la diferencia de medias, que posteriormente usaremos para buscar en las tablas de la distribución normal estándar.\n\nPara poder realizar el cálculo es necesario el siguiente teorema:";
    String paso2TCLDifMedias1 = "Después de sustituir los valores correspondientes en el teorema presentado anteriormente obtenemos que en éste caso el valor de Z es: \n\nZ = ";
    String paso2TCLDifMedias2 = "\n\nAhora se hace necesario buscar el valor calculado de Z en las tablas de la distribución normal estándar.";

    String paso1TCLDifProporciones = "El primer paso para calcular la probabilidad deseada es calcular el valor de la estadística de prueba Z para la diferencia de proporciones, que posteriormente usaremos para buscar en las tablas de la distribución normal estándar.\n\nPara poder realizar el cálculo es necesario el siguiente teorema:";
    String paso2TCLDifProporciones1 = "Después de sustituir los valores correspondientes en el teorema presentado anteriormente obtenemos que en éste caso el valor de Z es: \n\nZ = ";
    String paso2TCLDifProporciones2 = "\n\nAhora se hace necesario buscar el valor calculado de Z en las tablas de la distribución normal estándar.";


    String paso1TCLProporciones = "El primer paso para calcular la probabilidad deseada es calcular el valor de la estadística de prueba Z para proporciones, que posteriormente usaremos para buscar en las tablas de la distribución normal estándar.\n\nPara poder realizar el cálculo es necesario el siguiente teorema:";
    String paso2TCLProporciones1 = "Después de sustituir los valores correspondientes en el teorema presentado anteriormente obtenemos que en éste caso el valor de Z es: \n\nZ = ";
    String paso2TCLProporciones2 = "\n\nAhora se hace necesario buscar el valor calculado de Z en las tablas de la distribución normal estándar.";




    String paso1TCLTamMinimoMuestraDifMedias = "Para calcular el tamaño mínimo de muestra se hace necesario primero buscar el valor de en las tablas acumuladas de la distribución normal estándar.\n\nDonde en este caso el valor encontrado en tablas es ";




    String paso1TamMinimoMuestraDiferenciaMedias = "El primer";


    String [] media = {"P(X≤)","P(X>)","Calcular tamaño mínimo de muestra si se conoce D0 y se sabe que : P(|x-μ|<D0)","Calcular tamaño mínimo de muestra si se conoce D0 y se sabe que : P(|x-μ|>D0)","Calcular tamaño mínimo de muestra si se conoce X0"};
    String [] suma = null;
    String [] diferenciaMedias = {"P(X-Y≤)","P(X-Y>)"};
    String [] proporciones = {"P(X≤)","P(X>)"};
    String [] diferenciaProporciones = {"P(p1-p2≤)","P(p1-p2>)"};



    public default Spinner llenarSpinner(Spinner spinner, Context context){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,media);
        spinner.setAdapter(adapter);
        return spinner;
    }

    public default void desaparecerLayoutsProcedimientosTCL(){
        CTM.layoutProcedimientoTCLMedia.setVisibility(View.GONE);
        CTM.layoutProcedimientoTCLTamMinimoMuestra.setVisibility(View.GONE);
        CTM.resultado.setText("El resultado es: ");
    }

    public default String [] getMedia(){
        return media;
    }

    public default String[] getDiferenciaMedias(){ return diferenciaMedias;}

    public default  String[] getProporciones() { return proporciones;}

    public default String[] getDiferenciaProporciones(){return diferenciaProporciones;}

    public default String getPaso1TCLMedia(){return paso1TCLMedia;}

    public default String getPaso1TCLProporciones(){return paso1TCLProporciones;}

    public default String getPaso2TCLProporciones1(){return paso2TCLDifMedias1;}

    public default String getPaso2TCLProporciones2(){return paso2TCLProporciones2;}

    public default String getPaso1TCLDifProporciones(){return paso1TCLDifProporciones;}

    public default String getPaso2TCLDifProporciones1(){return paso2TCLDifProporciones1;}

    public default String getPaso2TCLDifProporciones2(){return paso2TCLDifProporciones2;}


    public default void desplegarDatosTCLMedia(Context context){
       try{
            CTM.primer.setVisibility(View.VISIBLE);
            CTM.segundo.setVisibility(View.GONE);
            CTM.tres.setVisibility(View.GONE);


       }catch(Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG);
       }
    }
 
    public default void desplegarDatosTamMinimoMuestraMedia(Context context){
        try{
            CTM.primer.setVisibility(View.GONE);
            CTM.segundo.setVisibility(View.VISIBLE);
            CTM.tres.setVisibility(View.GONE);


        }catch(Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG);
        }
    }

    public default void desplegarDatosTamMinimoMuestraMedia2(Context context){
        try{
            CTM.primer.setVisibility(View.GONE);
            CTM.segundo.setVisibility(View.GONE);
            CTM.tres.setVisibility(View.VISIBLE);

        }catch(Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG);
        }
    }

    public default void desplegarLayoutProcedimientoMedia(){
        CTM.layoutProcedimientoTCLMedia.setVisibility(View.VISIBLE);
        CTM.layoutProcedimientoTCLTamMinimoMuestra.setVisibility(View.GONE);
        CTM.opcionalRow.setVisibility(View.GONE);
    }

    public default void desplegarLayoutProcedimientoTamMinimoMuestra(){
        CTM.layoutProcedimientoTCLTamMinimoMuestra.setVisibility(View.VISIBLE);
        CTM.layoutProcedimientoTCLMedia.setVisibility(View.GONE);
        CTM.opcionalRow.setVisibility(View.GONE);
    }

    public default void desplegarLayoutProcedimientoTamMinimoMuestraCaso2(){
        CTM.layoutProcedimientoTCLTamMinimoMuestra.setVisibility(View.VISIBLE);
        CTM.layoutProcedimientoTCLMedia.setVisibility(View.GONE);
        CTM.opcionalRow.setVisibility(View.VISIBLE);
    }

    abstract void colocarDatosEnTablas(double dato1,double dato2,double dato3, int dato4);

    abstract void colocarDatosEnTablas(double dato1,double dato2,double dato3, int dato4, int dato5, double dato6, double dato7);


    public default void desplegarMensajeFaltanDatos(Context context){
        Toast.makeText(context,"Por favor ingresa todos los datos requeridos",Toast.LENGTH_LONG).show();
    }


    public default void desplegarDatosTCLDifMedias(){
        CTMDifMedias.datosTCL.setVisibility(View.VISIBLE);
        CTMDifMedias.datosTamMinimoMuestra1.setVisibility(View.GONE);
        CTMDifMedias.datosTamMinimoMuestra2.setVisibility(View.GONE);
        CTMDifMedias.botonAyuda.setVisibility(View.GONE);
    }

    public default void desplegarDatosTCLDifMediasTamMinimoMuestra1(){
        CTMDifMedias.datosTCL.setVisibility(View.GONE);
        CTMDifMedias.datosTamMinimoMuestra1.setVisibility(View.VISIBLE);
        CTMDifMedias.datosTamMinimoMuestra2.setVisibility(View.GONE);
        CTMDifMedias.botonAyuda.setVisibility(View.VISIBLE);
    }

    public default void desplegarDatosTCLDifMediasTamMInimoMuestra2(){
        CTMDifMedias.datosTCL.setVisibility(View.GONE);
        CTMDifMedias.datosTamMinimoMuestra1.setVisibility(View.GONE);
        CTMDifMedias.datosTamMinimoMuestra2.setVisibility(View.VISIBLE);
        CTMDifMedias.botonAyuda.setVisibility(View.VISIBLE);
    }


    public default void prepararEntornoProporciones(){
        CTMProporciones.datos.setVisibility(View.VISIBLE);
        CTMProporciones.tableRow.setVisibility(View.GONE);
        CTMProporciones.imagenProporcion.setImageResource(R.drawable.proporcion);
        CTMProporciones.imagenConfiabilidad.setImageResource(R.drawable.confiabilidad);
        CTMProporciones.imagenMuestra.setImageResource(R.drawable.tmuestra);
    }

    public default void prepararEntornoDiferenciaProporciones(){
        CTMDifProporciones.datos.setVisibility(View.VISIBLE);
        CTMDifProporciones.resultado.setText("El resultado es:");
        CTMDifProporciones.tableRow.setVisibility(View.GONE);
        CTMDifProporciones.imageView1.setImageResource(R.drawable.proporcion1);
        CTMDifProporciones.imageView2.setImageResource(R.drawable.confiabilidad1);
        CTMDifProporciones.imageView3.setImageResource(R.drawable.n1);
        CTMDifProporciones.imageView4.setImageResource(R.drawable.proporcion2);
        CTMDifProporciones.imageView5.setImageResource(R.drawable.confiabilidad2);
        CTMDifProporciones.imageView6.setImageResource(R.drawable.n2);
        CTMDifProporciones.layoutProcedimiento.setVisibility(View.GONE);
    }




}
