package com.example.estadistica;

public class ICFVARIANZA extends frameworkICF1 {

    private double confianza1 = 0;
    private int gradosLibertad;
    private double valTablas1 = 0;
    private double determinante;


    public double getDeterminante(){return this.determinante;}




    public ICFVARIANZA(double varianzaPob,int tamMuestra,double gradosConfianza){
        this.confianza = tablas.redondeoDecimales(((1-gradosConfianza)/2),4);
        this.confianza1 = 1-confianza;
        this.multiplicador = tamMuestra -1;
        this.gradosLibertad = tamMuestra -1;
        this.desviacionEstandar = varianzaPob;
    }


    public double getConfianza1() {
        return confianza1;
    }

    public int getGradosLibertad() {
        return gradosLibertad;
    }

    public double getValTablas1() {
        return valTablas1;
    }

    @Override
    public double calcLimInf() {
        valorTablas = tablas.tablaChi(confianza,gradosLibertad);
        limInf = (multiplicador*desviacionEstandar)/valorTablas;
        return limInf;
    }

    @Override
    public double calcLimSup() {
        valTablas1 = tablas.tablaChi(confianza1,gradosLibertad);
        limSup = (multiplicador*desviacionEstandar)/valTablas1;
        return limSup;
    }

    public ICFVARIANZA(int tamMuestra, double varianzaM){
        this.gradosLibertad = tamMuestra-1;
        this.desviacionEstandar = varianzaM;
        confianza = (1-confianza)/2;

    }


    @Override
    public double calcGradoConfianza(char limite, double valLimite) {
        double gradoConfianza = 0;
        double aux = (gradosLibertad * desviacionEstandar) / valLimite;
        aux = tablas.redondeoDecimales(aux,5);
        determinante = aux;
        valorTablas = tablas.tablaChiPotenciaPrueba(aux,this.gradosLibertad);
        if(limite == 'a'){
             gradoConfianza = 1-(2*valorTablas);
             gradoConfianza = tablas.redondeoDecimales(gradoConfianza,7);
        }else if(limite == 'b'){
            gradoConfianza = (2*valorTablas)-1;
            gradoConfianza = tablas.redondeoDecimales(gradoConfianza,7);
        }
        return gradoConfianza;
    }

}
