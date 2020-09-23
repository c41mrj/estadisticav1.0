package com.example.estadistica;

public class ICFPROPORCIONES {

    private double limInf,limSup,probabilidad,complemento,valTablas,confianza,multiplicador,coeficienteConfianza,errorMuestral;
    private CalculoTablas tabla = new CalculoTablas();
    private int tamMuestra,tamMinimoMuestra, cotaInferior;

    public double getLimInf() {
        return limInf;
    }

    public double getLimSup() {
        return limSup;
    }

    public double getValTablas() {
        return valTablas;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public int getCotaInferior(){
        return cotaInferior;
    }

    public int getTamMinimoMuestra() {
        return tamMinimoMuestra;
    }

    public double getCoeficienteConfianza(){
        return this.coeficienteConfianza;
    }

    public double getConfianza() {
        return confianza;
    }

    public double redondeoDecimales(double numero, int decimales){
        return tabla.redondeoDecimales(numero,decimales);
    }

    public double getErrorMuestral() {
        return errorMuestral;
    }

    public double getComplemento(){
        return complemento;
    }

    public ICFPROPORCIONES(double probabilidad, double nivelConfianza, int tamMuestra){
        this.complemento = redondeoDecimales((1-probabilidad),5);
        this.confianza = redondeoDecimales((1-nivelConfianza),4);
        this.tamMuestra = tamMuestra;
        this.valTablas = tabla.tablazeta((float)redondeoDecimales((this.confianza/2),4));
        this.multiplicador = Math.sqrt(((probabilidad*complemento)/tamMuestra));
        this.limInf = probabilidad - (this.valTablas*this.multiplicador);
        this.limSup = probabilidad + (this.valTablas*this.multiplicador);
    }

    public ICFPROPORCIONES(double nivelConfianza,double errorMuestral, double probabilidad){
        this.confianza = 1- nivelConfianza;
        this.confianza = redondeoDecimales(confianza,4);
        this.complemento = 1-probabilidad;
        this.complemento = redondeoDecimales(this.complemento,5);
        this.valTablas = tabla.tablazeta((float)(redondeoDecimales((this.confianza/2),5)));
        this.tamMinimoMuestra = (int)Math.floor(((Math.pow((valTablas/errorMuestral),2)*(probabilidad*complemento))))+1;
        this.cotaInferior =(int) Math.floor((Math.pow((valTablas/errorMuestral),2))/4) + 1;
    }

    public ICFPROPORCIONES(double valLimite,int tamMuestra,double proporcion,char limite){
        this.complemento = 1-proporcion;
        this.complemento = redondeoDecimales(this.complemento,6);
        this.multiplicador = (valLimite  - proporcion) * Math.sqrt((tamMuestra/(proporcion*complemento)));
        this.multiplicador = redondeoDecimales(this.multiplicador,4);
        this.valTablas = tabla.tablazetaAcumulada(multiplicador);
        if(limite == 'a'){
            coeficienteConfianza = (1 - (2*valTablas));
            coeficienteConfianza = redondeoDecimales(coeficienteConfianza,6);
        }else if(limite == 'b'){
            coeficienteConfianza = (2*valTablas) -1 ;
            coeficienteConfianza = redondeoDecimales(coeficienteConfianza,6);
        }
    }

    public ICFPROPORCIONES(double nivelConfianza, int tamMuestra,double proporcion){
        this.complemento = redondeoDecimales((1-proporcion),6);
        this.multiplicador = Math.sqrt(((proporcion*complemento)/tamMuestra));
        this.confianza = redondeoDecimales(((1-nivelConfianza)/2),4);
        this.valTablas = tabla.tablazeta((float)(this.confianza));
        this.errorMuestral = valTablas * multiplicador;
        this.errorMuestral = redondeoDecimales(errorMuestral,5);
    }
}
