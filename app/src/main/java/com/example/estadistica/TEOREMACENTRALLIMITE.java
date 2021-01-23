package com.example.estadistica;

public abstract class TEOREMACENTRALLIMITE implements conversiones{

    CalculoTablas tablas = new CalculoTablas();

    public double calcularEstadisticoZ(double xBarra, double miu,double varianza,int tamMuestra){
     return redondeoDecimales(((xBarra-miu)/(varianza/Math.sqrt(tamMuestra))),5);
    }

   public double calcularEstadisticoZsumaVariables(double T,int tamMuestra, double miu, double varianza){
         return redondeoDecimales(((T-(tamMuestra*miu))/(Math.sqrt(tamMuestra)*varianza)),5);
    }

    public double calcularEstadisticoZDiferenciaMedias(double diferenciaPromedios,double diferenciaMedias,int n1,int n2,double varianza1,double varianza2){
         return redondeoDecimales(((diferenciaPromedios-diferenciaMedias)/Math.sqrt(((varianza1/n1)+(varianza2/n2)))),5);
    }

    public double calcularEstadisticoZProporciones(double probabilidad,double proporcion, int tamMuestra){
         return redondeoDecimales(((proporcion-probabilidad)/Math.sqrt(((probabilidad*(1-probabilidad))/tamMuestra))),5);
    }

    public double calcularEstadisticoZDiferenciaProporciones(double diferenciaProporciones,double prob1,double prob2,int n1, int n2){
         return redondeoDecimales(((diferenciaProporciones-(prob1-prob2))/Math.sqrt((((prob1*(1-prob1))/n1)+((prob2*(1-prob2))/n2)))),5);
    }

    public double calcularProbabilidad(double z){
        return tablas.tablazetaAcumulada(z);
    }

}
