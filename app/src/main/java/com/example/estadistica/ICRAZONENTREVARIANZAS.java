package com.example.estadistica;

public class ICRAZONENTREVARIANZAS  {

    private double varianza1,varianza2,nivelConfianza,valTablas,valTablas2,limInf,limSup;
    private int v1,v2;
    CalculoTablas tablas = new CalculoTablas();
    private String conclusion;

    public double getLimInf() {
        return limInf;
    }

    public double getLimSup() {
        return limSup;
    }

    public int getV1(){
        return v1;
    }

    public int getV2(){
        return v2;
    }

    public double getValTablas(){
        return valTablas;
    }

    public double getValTablas2() {
        return valTablas2;
    }

    public String getConclusion(){return this.conclusion;}

    public ICRAZONENTREVARIANZAS(double varianza1, double varianza2, double nivelConfianza,int tamMuestra1, int tamMuestra2){
        this.varianza1 = varianza1;
        this.varianza2 = varianza2;
        this.nivelConfianza = (1-nivelConfianza)/2;
        this.nivelConfianza = 1- this.nivelConfianza;
        this.nivelConfianza = tablas.redondeoDecimales(this.nivelConfianza,3);
        this.v1 = tamMuestra1 -1;
        this.v2 = tamMuestra2 -1;
        valTablas = tablas.tablaFisher(v1,v2,(this.nivelConfianza));
        valTablas2 = tablas.tablaFisher(v2,v1,(this.nivelConfianza));
        limInf = (varianza1/varianza2)*(1/valTablas);
        limInf = tablas.redondeoDecimales(limInf,6);
        limSup = (varianza1/varianza2)*(valTablas2);
        limSup = tablas.redondeoDecimales(limSup,6);
        if((limInf>=0 && limInf<=1)&&(limSup>=0 && limSup<=1)){
            this.conclusion = "Cómo podemos observar, tanto el límite inferior, como el límite superior están entre 0 y 1, esto significa que al "+nivelConfianza+" de confianza se puede suponer que la varianza 1 es menor que la varianza 2.\n\nPor lo tanto suponemos que las varianzas son diferentes";
        }else if((limInf>1)&&(limSup>1)){
            this.conclusion = "Cómo podemos observar, tanto el límite inferior, como el límite superior son mayores que 1, esto significa que al "+nivelConfianza+" de confianza se puede suponer que la varianza 1 es mayor que la varianza 2.\n\nPor lo tanto suponemos que las varianzas son diferentes";
        }else if(((limInf>=0)&&(limInf<=1))&&limSup>1){
            this.conclusion = "Cómo podemos observar, el límite inferior está entre 0 y 1, y el límite superior es mayor que 1, esto significa que al "+nivelConfianza+" de confianza se puede suponer que la varianza 1 es igual que la varianza 2.\n\nPor lo tanto suponemos que las varianzas son iguales";
        }
    }

    public ICRAZONENTREVARIANZAS(double varianza1, double varianza2, int tamMuestra1, int tamMuestra2, char limite ,double valLimite){

    }
}
