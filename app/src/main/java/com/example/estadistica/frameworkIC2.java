package com.example.estadistica;

public class frameworkIC2 implements INTERVALOS_DIFMEDIASPOBNORMAL {
    protected double varianzaPob1,varianzaPob2,nivelConfianza,diferenciaDeMedias,valTablas,multiplicador,limInf,limSup,limite,coeficienteConfianza,desviacionEstandard,mediaPareada,errorMuestral;
    protected int tamMuestra1, tamMuestra2, gradosLibertad,tamMinimoMuestra;
    protected CalculoTablas tabla = new CalculoTablas();

    public double getValTablas() {
        return valTablas;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public double getLimInf() {
        return limInf;
    }

    public double getLimSup() {
        return limSup;
    }

    public double getCoeficienteConfianza(){return this.coeficienteConfianza;}

    public int getTamMinimoMuestra(){return  this.tamMinimoMuestra;}

    public int getGradosLibertad() {
        return gradosLibertad;
    }

    public frameworkIC2(double varianzaPob1, double varianzaPob2, int tamMuestra1, int tamMuestra2, double diferenciaDeMedias, double significancia){
        this.varianzaPob1 = varianzaPob1;
        this.varianzaPob2 = varianzaPob2;
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        this.nivelConfianza = tabla.redondeoDecimales(((1-significancia)/2),5);
        this.diferenciaDeMedias = diferenciaDeMedias;
    }


    public frameworkIC2(int tamMuestra1, int tamMuestra2, double varianzaPob1, double varianzaPob2, double diferenciaDeMedias,double limite){
        this.varianzaPob1 = varianzaPob1;
        this.varianzaPob2 = varianzaPob2;
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        this.diferenciaDeMedias = diferenciaDeMedias;
        this.limite = limite;
    }

    public frameworkIC2(double nivelConfianza){
        this.nivelConfianza = 1- nivelConfianza;
    }

    public frameworkIC2(double confianza,double mediaPareada, double desviacionEstandard){
        this.nivelConfianza = confianza;
        this.mediaPareada = mediaPareada;
        this.desviacionEstandard = desviacionEstandard;
    }

    public frameworkIC2(double confianza,double errorMuestral,double varianzaPob1,double varianzaPob2){
        this.nivelConfianza = tabla.redondeoDecimales(((1-confianza)/2),5);
        this.errorMuestral = errorMuestral;
        this.varianzaPob1 = varianzaPob1;
        this.varianzaPob2 = varianzaPob2;
    }






    @Override
    public double calcLimInf() {
        return 0;
    }

    @Override
    public double calcLimSup() {
        return 0;
    }

    @Override
    public double calcCoeficienteConfianza() {
        return 0;
    }

    public int calcGradosLibertad(){
        return 0;
    }
}
