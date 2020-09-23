package com.example.estadistica;

public class ICFDIFERENCIAPROPORCIONES {

    private double complemento1,complemento2,limInf,limSup,valTablas,cotaInferior,cotaSuperior,confianza,diferenciaProporciones,multiplicador,coeficienteConfianza;
    private CalculoTablas tablas = new CalculoTablas();
    private int tamMinimoMuestra;


    public ICFDIFERENCIAPROPORCIONES(double probabilidad1,double probabilidad2,double nivelConfianza,int tamMuestra1, int tamMuestra2){
        this.diferenciaProporciones =probabilidad1-probabilidad2;
        this.diferenciaProporciones = tablas.redondeoDecimales(this.diferenciaProporciones,5);
        this.confianza = 1-nivelConfianza;
        this.complemento1 = 1-probabilidad1;
        this.complemento2 = 1-probabilidad2;
        valTablas = tablas.tablazeta((float)(confianza/2));
        this.multiplicador = Math.sqrt(((probabilidad1*complemento1)/tamMuestra1)+((probabilidad2*complemento2)/tamMuestra2));
        this.limInf = diferenciaProporciones - (valTablas*multiplicador);
        this.limSup = diferenciaProporciones + (valTablas*multiplicador);
    }


    public ICFDIFERENCIAPROPORCIONES(double probabilidad1,double probabilidad2, double errorMuestral,double nivelConf){ //tamaño minimo de muestra
        this.confianza = 1-nivelConf;
        this.valTablas = tablas.tablazeta((float)(this.confianza/2));
        this.multiplicador = Math.pow((valTablas/errorMuestral/2),2);
        this.complemento1 = 1- probabilidad1;
        this.complemento2 = 1- probabilidad2;
        this.tamMinimoMuestra = (int) Math.floor((multiplicador*((probabilidad1*complemento1)+(probabilidad2*complemento2))))+1;
    }

    public ICFDIFERENCIAPROPORCIONES(double errorMuestral,double nivelCOnfianza){
        this.confianza = 1 - nivelCOnfianza;
        this.valTablas = tablas.tablazeta((float)(this.confianza/2));
        this.multiplicador = Math.pow((valTablas/errorMuestral),2);
        this.tamMinimoMuestra = (int) Math.floor((multiplicador/2))+1;
    }

    public ICFDIFERENCIAPROPORCIONES(double probabilidad1,double probabilidad2,int tamMuestra1,int tamMuestra2,double valLimite,char limite){
       this.complemento1 = 1-probabilidad1;
       this.complemento2 = 1-probabilidad2;
       this.multiplicador = ((valLimite-(probabilidad1-probabilidad2))/Math.sqrt(((probabilidad1*complemento1)/tamMuestra1)+((probabilidad2*complemento2)/tamMuestra2)));
       valTablas = tablas.tablazetaAcumulada(multiplicador);
       if(limite == 'a'){
           coeficienteConfianza = 1-(2*valTablas);
       }else if(limite == 'b'){
           coeficienteConfianza = (2*valTablas) - 1;
       }
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

    public int getTamMinimoMuestra() {
        return tamMinimoMuestra;
    }

    public double getMultiplicador(){
        return multiplicador;
    }

    public double getCoeficienteConfianza(){
        return coeficienteConfianza;
    }
}
