package com.example.estadistica;

public abstract class TEOREMACENTRALLIMITE {

    CalculoTablas tablas = new CalculoTablas();

    public double calcularEstadisticoZ(double xBarra, double miu,double varianza,int tamMuestra){
     return ((xBarra-miu)/(varianza/Math.sqrt(tamMuestra)));
    }

   public double calcularEstadisticoZsumaVariables(double T,int tamMuestra, double miu, double varianza){
         return ((T-(tamMuestra*miu))/(Math.sqrt(tamMuestra)*varianza));
    }

    public double calcularEstadisticoZDiferenciaMedias(double diferenciaPromedios,double diferenciaMedias,int n1,int n2,double varianza1,double varianza2){
         return ((diferenciaPromedios-diferenciaMedias)/Math.sqrt(((varianza1/n1)+(varianza2/n2))));
    }

    public double calcularEstadisticoZProporciones(double probabilidad,double proporcion, int tamMuestra){
         return (proporcion-probabilidad)/Math.sqrt(((probabilidad*(1-probabilidad))/tamMuestra));
    }

    double calcularEstadisticoZDiferenciaProporciones(double proporcion1,double proporcion2,double prob1,double prob2,int n1, int n2){
         return (((proporcion1-proporcion2)-(prob1-prob2))/Math.sqrt((((prob1*(1-prob1))/n1)+((prob2*(1-prob2))/n2))));
    }

    double calcularProbabilidad(double z){
        return tablas.tablazetaAcumulada(z);
    }

}
