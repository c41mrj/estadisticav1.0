package com.example.estadistica;

public class ICFDIFMEDIASVARIANZASCONOCIDAS extends frameworkIC2 {

    private double determinante;

    public double getDeterminante(){return determinante;}


    public ICFDIFMEDIASVARIANZASCONOCIDAS(double varianzaPob1, double varianzaPob2, int tamMuestra1, int tamMuestra2, double diferenciaDeMedias,double significancia) {
        super(varianzaPob1, varianzaPob2, tamMuestra1, tamMuestra2, diferenciaDeMedias,significancia);
        this.multiplicador = Math.sqrt(((this.varianzaPob1/(double)tamMuestra1)+(this.varianzaPob2/(double)tamMuestra2)));
        this.valTablas = tabla.tablazeta((float)tabla.redondeoDecimales((nivelConfianza),5));
        this.limInf = this.diferenciaDeMedias-(multiplicador*valTablas);
        this.limSup = this.diferenciaDeMedias + (multiplicador * valTablas);
    }

    public ICFDIFMEDIASVARIANZASCONOCIDAS(int tamMuestra1, int tamMuestra2,double varianza1, double varianza2, double diferenciaMedias, double valLimite, char limite){
        super(tamMuestra1,tamMuestra2,varianza1,varianza2,diferenciaMedias,valLimite);
        double aux = tabla.redondeoDecimales(((this.diferenciaDeMedias-valLimite)/(Math.sqrt((varianzaPob1/(double)tamMuestra1)+(varianzaPob2/(double)tamMuestra2)))),5);
        determinante = aux;
        valTablas = tabla.tablazetaAcumulada(determinante);
        if(limite == 'a'){this.coeficienteConfianza = (2*valTablas) - 1;}
        else if(limite == 'b'){this.coeficienteConfianza = 1-(2*valTablas);}
    }

    public ICFDIFMEDIASVARIANZASCONOCIDAS(double nivelConfianza, double errorMuestral, double varianza1, double varianza2){
        super(nivelConfianza,errorMuestral,varianza1,varianza2);
        this.valTablas = tabla.tablazeta((float)this.nivelConfianza);
        this.multiplicador = Math.pow((valTablas/this.errorMuestral),2);
        double aux = this.multiplicador*(this.varianzaPob1+this.varianzaPob2);
        this.tamMinimoMuestra = (int) Math.floor(aux) + 1;
    }

}
