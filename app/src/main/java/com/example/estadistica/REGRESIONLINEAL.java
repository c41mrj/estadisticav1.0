package com.example.estadistica;

import java.util.ArrayList;

public class REGRESIONLINEAL {
    private double x[],y[],sumaX,sumaY,sumaXY,sumaYCuadrada,sumaXCuadrada,sumaYTestada,yTestada[],errorEstandarDeLaEstimacion,limInf,limSup,valTablas,estadisticoT,valTablas1;
    private int tamMuestra;
    private double SCxx;
    private CalculoTablas tab = new CalculoTablas();
    private String decision;

    public double getSumaX() {
        return sumaX;
    }

    public double getSumaY() {
        return sumaY;
    }

    public double getSumaXY() {
        return sumaXY;
    }

    public double getSumaYCuadrada() {
        return sumaYCuadrada;
    }

    public double getSumaXCuadrada() {
        return sumaXCuadrada;
    }

    public double getLimInf() {
        return limInf;
    }

    public double getLimSup() {
        return limSup;
    }

    public double getValTablas() {
        return valTablas;
    }

    public double getEstadisticoT() {
        return estadisticoT;
    }

    public double getValTablas1() {
        return valTablas1;
    }

    public double getSCxx() {
        return SCxx;
    }

    public String getDecision() {
        return decision;
    }

    public REGRESIONLINEAL(double x[], double y[], int tamMuestra){
     this.x = x;
     this.y = y;
     this.tamMuestra = tamMuestra;
     sumarX();
     sumarY();
     sumarXY();
     sumarXCuadrada();
     sumarYCuadrada();
    }

    private void sumarX(){
        for(int i = 0; i<x.length; i++){
            sumaX = sumaX + x[i];
        }
    }

    private void sumarY(){
        for(int j = 0; j<y.length; j++){
            sumaY = sumaY+y[j];
        }
    }

    private void sumarXY(){
        for(int j = 0; j<y.length; j++){
            sumaXY = sumaXY + (x[j]*y[j]);
        }
    }

    private void sumarXCuadrada(){
        for(int i = 0; i<x.length; i++){
            sumaXCuadrada = sumaXCuadrada + Math.pow(x[i],2);
        }
    }
    private void sumarYCuadrada() {
        for (int i = 0; i < x.length; i++) {
            sumaYCuadrada = sumaYCuadrada + Math.pow(y[i], 2);
        }
    }

    private double promedioX(){
        return sumaX/x.length;
    }

    private double promedioY(){
        return sumaY/y.length;
    }




    public double calcularPendiente(){
        return ((sumaXY-(sumaX*sumaY))/(sumaXCuadrada-Math.pow(sumaX,2)));
    }


    public double calcularOrdenadaAlOrigen(){
        return (promedioY()-(calcularPendiente()*promedioX()));
    }

    public String obtenerRectaMejorAjuste(){
        return ("y=" + calcularPendiente()+"x + " + calcularOrdenadaAlOrigen());
    }

    public double calcularValor(double valor){
        return (calcularPendiente()*valor)+calcularOrdenadaAlOrigen();
    }


    public double calcularCoeficienteCorrelacionPearson(){
        double divisor = (tamMuestra*sumaXY)-(sumaX*sumaY);
        double dividendo = Math.sqrt((sumaXCuadrada-(Math.pow(sumaX,2)))*Math.sqrt((tamMuestra*sumaYCuadrada)-Math.pow(sumaY,2)));
        return (divisor/dividendo);
    }

    public double calcularErrorEstandarDeEstimacion(double pendiente){
        double suma = 0;
        yTestada = new double[x.length];
        for(int i = 0; i<x.length; i++){
            sumaYTestada = sumaYTestada+((calcularPendiente()*x[i])+calcularOrdenadaAlOrigen());
            yTestada[i] =  (calcularPendiente()*x[i])+calcularOrdenadaAlOrigen();
        }
        for(int j = 0; j<yTestada.length;j++){
            suma = suma + (Math.pow((y[j]-yTestada[j]),2));
        }
        errorEstandarDeLaEstimacion =  Math.sqrt((suma/tamMuestra-2));
        return errorEstandarDeLaEstimacion;
    }

    public double calcularLimInfIntervalosPendiente(double nivelConfianza,int tamMuestra,double pendiente){
        int gradosLibertad = tamMuestra - 2;
        double confianza = 1-nivelConfianza;
        valTablas = tab.tablaTeStudent(gradosLibertad,(float)(confianza/2));
        limInf = pendiente - ((errorEstandarDeLaEstimacion/Math.sqrt(SCxx))*valTablas);
        return limInf;
    }

    public double calcularLimSupIntervalosPendiente(double nivelConfianza,int tamMuestra,double pendiente){
        int gradosLibertad = tamMuestra - 2;
        double confianza = 1-nivelConfianza;
        valTablas = tab.tablaTeStudent(gradosLibertad,(float)(confianza/2));
        limSup = pendiente + ((errorEstandarDeLaEstimacion/Math.sqrt(SCxx))*valTablas);
        return limSup;
    }

    public double calcularSCxx(){
        for(int i = 0; i<x.length; i++){
            SCxx = SCxx+ Math.pow((x[i]-promedioX()),2);
        }
        return SCxx;
    }


    public String intervaloConfianzaPendiente(double pendiente, int tamMuestra, double nivelConfianza){
        for(int i = 0; i<x.length; i++){
            SCxx = SCxx+ Math.pow((x[i]-promedioX()),2);
        }
        return ("[" + calcularLimInfIntervalosPendiente(nivelConfianza,tamMuestra,pendiente) + "," + calcularLimSupIntervalosPendiente(nivelConfianza,tamMuestra,pendiente)+"]");
    }


    public double pruebaHipotesisPendienteCasoA(double pendiente1, double pendiente0, double significancia,double errorEstimacion,double SCxx){
        estadisticoT = ((pendiente1-pendiente0)/errorEstimacion)*Math.sqrt(SCxx);
        int gradosLibertad = tamMuestra-2;
        valTablas = tab.tablaTeStudent(gradosLibertad,(float)significancia);
        limSup = valTablas;
        if(estaDentroDeLaReionRechazoCasoA()){
            this.decision = "A una significanzia de " + significancia + " se rechaza la hipotesis nula";
            return limSup;
        }else
            this.decision = "A una significancia de " + significancia + " no existe evidencia suficiente para rechazar H0";
            return limSup;
    }


    public String pruebHipotesisPendienteCasoB(double pendiente1, double pendiente0, double significancia,double errorEstimacion,double SCxx){
        estadisticoT = ((pendiente1-pendiente0)/errorEstimacion)*Math.sqrt(SCxx);
        double confianza = significancia;
        int gradosLibertad = tamMuestra -2;
        valTablas = tab.tablaTeStudent(gradosLibertad,(float)(confianza/2));
        valTablas1 = valTablas;
        String intervalo = "[" + valTablas + "," + valTablas1+"]";
        if(estaDentroDeLaRegionRechazoCasoB()){
            this.decision = "A una significancia de " +  confianza + " se rechaza la hipotesis nula";
            return intervalo;
        }else
            this.decision = "A una significancia de " + confianza + " se acepta la hipotesis nula";
            return intervalo;
     }

    public double pruebaHipotesisPendienteCasoC(double pendiente1, double pendiente0, double significancia,double errorEstimacion,double SCxx){
        estadisticoT = ((pendiente1-pendiente0)/errorEstimacion)*Math.sqrt(SCxx);
        int gradosLibertad = tamMuestra-2;
        valTablas = tab.tablaTeStudent(gradosLibertad,(float)significancia);
        limInf = valTablas;
        if(estaDentroDeLaRegionRechazoCasoC()){
            this.decision = "A una significanzia de " + significancia + " se rechaza la hipotesis nula";
            return limInf;
        }else
            this.decision = "A una significancia de " + significancia + " no existe evidencia suficiente para rechazar H0";
        return limInf;
    }


    public boolean estaDentroDeLaReionRechazoCasoA(){
        if(estadisticoT>valTablas){
            return true;
        }else return false;
    }

    public boolean estaDentroDeLaRegionRechazoCasoB(){
        if(estadisticoT<valTablas || estadisticoT>valTablas1) return true;
        else return false;
    }


    public boolean estaDentroDeLaRegionRechazoCasoC(){
        if(estadisticoT<valTablas){
            return true;
        }else return false;
    }

}
