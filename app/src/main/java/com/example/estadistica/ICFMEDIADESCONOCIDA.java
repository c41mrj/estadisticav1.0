package com.example.estadistica;

public class ICFMEDIADESCONOCIDA extends frameworkICF1{

    private double determinante;
    public double getDeterminante(){return determinante;}

    public ICFMEDIADESCONOCIDA(double desviacionEstandar, int tamMuestra, double Confianza, double promedioMuestral) {  //constructor para calcular los límites
        this.desviacionEstandar = desviacionEstandar;
        this.confianza = tablas.redondeoDecimales(((1-Confianza)/2),5);
        this.tamMuestra = tamMuestra;
        this.multiplicador = this.desviacionEstandar/(Math.sqrt(tamMuestra));
        this.multiplicador = tablas.redondeoDecimales(this.multiplicador,6);
        this.mediaMuestral = promedioMuestral;
    }

    public ICFMEDIADESCONOCIDA(double desviacionEstandar, double Confianza, float errorMuestral){  //constructor para calcular tamaño mínimo de la muestra
        this.desviacionEstandar = desviacionEstandar;
        this.confianza = tablas.redondeoDecimales(((1-Confianza)/2),5);
        this.errorMuestral = errorMuestral;
        this.valorTablas = tablas.tablaz(confianza);
        this.multiplicador = Math.pow((this.valorTablas/this.errorMuestral),2);
        this.multiplicador = tablas.redondeoDecimales(this.multiplicador,6);
    }

    public ICFMEDIADESCONOCIDA(double mediaMuestral, int tamMuestra, double desviacionEstandar){  //constructor para calcular el nivel de confianza
        this.mediaMuestral = mediaMuestral;
        this.tamMuestra = tamMuestra;
        this.desviacionEstandar = desviacionEstandar;
    }

    @Override
    public double calcLimInf() {
       this.valorTablas = tablas.tablaz(this.confianza);
       this.limInf = this.mediaMuestral-(this.valorTablas*this.multiplicador);
       return limInf;
    }

    @Override
    public double calcLimSup() {
        this.valorTablas = tablas.tablaz(this.confianza);
        this.limInf = this.mediaMuestral+(this.valorTablas*this.multiplicador);
        return limInf;
    }


    @Override
    public int obtenerTamMinimoMuestra() {
        int tamMinimo = (int)Math.floor(this.multiplicador*Math.pow(this.desviacionEstandar,2));
        return tamMinimo+1;
    }

    @Override
    public double calcGradoConfianza(char limite,double valLimite) {
        double gradoConf = 0;
        if(limite == 'a'){
            this.multiplicador = (this.mediaMuestral - valLimite)/this.desviacionEstandar;
            double aux = multiplicador*Math.sqrt(tamMuestra);
            aux = tablas.redondeoDecimales(aux,2);
            determinante = aux;
            valorTablas = tablas.tablazetaAcumulada(aux);
            gradoConf = (2*valorTablas)-1;
        }else if(limite == 'b') {
            this.multiplicador = ( this.mediaMuestral - valLimite) / this.desviacionEstandar;
            double aux = multiplicador * Math.sqrt(tamMuestra);
            aux = tablas.redondeoDecimales(aux,2);
            determinante = aux;
            valorTablas = tablas.tablazetaAcumulada(aux);
            gradoConf = 1-(2*valorTablas);
        }
        return gradoConf;
    }

    @Override
    public double obtenerLimite(double valLimite,double mediaMuestral) {
       return (mediaMuestral*2) - valLimite;
    }
}
