package com.example.estadistica;

public class ICDIFERENCIAPROPORCIONES extends CalculoTablas {

    private double p1,p2,significancia,limInf,limSup,diferenciaProporciones,valTablas,multiplicador,q1,q2,errorMuestral,determinante,coeficienteConfianza;
    private int n1,n2,tamMinimoMuestra,cotaInferior;


    public double getDiferenciaProporciones() {
        return diferenciaProporciones;
    }

    public double getValTablas() {
        return valTablas;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public double getDeterminante() {
        return determinante;
    }

    public double getCoeficienteConfianza() {
        return coeficienteConfianza;
    }

    public int getTamMinimoMuestra() {
        return tamMinimoMuestra;
    }

    public int getCotaInferior() {
        return cotaInferior;
    }

    public double getSignificancia(){
        return this.significancia;
    }

    public ICDIFERENCIAPROPORCIONES(double p1, double p2, int tamMuestra1, int tamMuestra2, double gradosConfianza){
        this.p1 = p1;
        this.q1 = 1-p1;
        this.q1 = redondeoDecimales(this.q1,5);
        this.p2 = p2;
        this.q2 = 1-p2;
        this.q2 = redondeoDecimales(this.q2,5);
        this.significancia = redondeoDecimales(((1-gradosConfianza)/2),5);
        this.n1 = tamMuestra1;
        this.n2 = tamMuestra2;
        diferenciaProporciones = p1-p2;
        diferenciaProporciones = redondeoDecimales(diferenciaProporciones,6);
        this.valTablas = tablazeta((float)significancia);
        double multi1 = (p1*q1) / tamMuestra1;
        double multi2 = (p2*q2) / tamMuestra2;
        this.multiplicador = Math.sqrt((multi1+multi2));
    }

    public ICDIFERENCIAPROPORCIONES(double p1, double p2,double errorMuestral, double confianza){
        this.p1 = p1;
        this.q1 = 1-p1;
        this.q1 = redondeoDecimales(this.q1,5);
        this.p2 = p2;
        this.q2 = 1-p2;
        this.q2 = redondeoDecimales(this.q2,5);
        this.significancia = redondeoDecimales(((1-confianza)/2),5);
        this.diferenciaProporciones = p1 - p2;
        this.valTablas = tablazeta((float)significancia);
        this.errorMuestral = errorMuestral;
        multiplicador = ((p1*q1)+(p2*q2));
    }


    public ICDIFERENCIAPROPORCIONES(double p1,double p2, double valLimite, int tamMuestra1, int tamMuestra2){
        this.p1 = p1;
        this.q1 = 1-p1;
        this.q1 = redondeoDecimales(this.q1,5);
        this.p2 = p2;
        this.q2 = 1-p2;
        this.q2 = redondeoDecimales(this.q2,5);
        double multi1 = (p1*q1) / tamMuestra1;
        double multi2 = (p2*q2) / tamMuestra2;
        this.multiplicador = Math.sqrt((multi1+multi2));
        this.determinante = redondeoDecimales(((valLimite-(p1-p2))/multiplicador),5);
        valTablas = tablazetaAcumulada(this.determinante);
    }


    public double calcLimInf(){
        limInf = diferenciaProporciones - (valTablas*multiplicador);
        return limInf;
    }

    public double calcLimSup(){
        limSup = diferenciaProporciones + (valTablas*multiplicador);
        return limSup;
    }

    public int calcularTamMinimoMuestra(){
        tamMinimoMuestra =(int) Math.floor(((Math.pow((valTablas/errorMuestral),2))*multiplicador)) +1;
        return tamMinimoMuestra;
    }

    public int calcularCotaInferior(){
        cotaInferior = (int) Math.floor(((Math.pow((valTablas/errorMuestral),2)))/2) + 1;
        return cotaInferior;
    }


    public double calcularCoeficienteDeConfianza(char lim){
        if(lim == 'a'){
            coeficienteConfianza = 1-(2*valTablas);
        }else if(lim == 'b'){
            coeficienteConfianza = (2*valTablas) -1;
        }
        return coeficienteConfianza;
    }




}
