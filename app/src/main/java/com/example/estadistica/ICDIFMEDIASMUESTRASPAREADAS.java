package com.example.estadistica;

public class ICDIFMEDIASMUESTRASPAREADAS extends frameworkIC2 {
    double mediaPareada,desviacionEstandar;

    public double getMediaPareada() {
        return mediaPareada;
    }

    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public ICDIFMEDIASMUESTRASPAREADAS(double varianzaPob1, double varianzaPob2, int tamMuestra1, int tamMuestra2, double mediaPareada, double significancia) {
        super(varianzaPob1, varianzaPob2, tamMuestra1, tamMuestra2, mediaPareada, significancia);
    }

    public ICDIFMEDIASMUESTRASPAREADAS(double desviacionEstandar,double mediaPareada, int tamMuestra,double nivelConfianza){
        super(nivelConfianza);
        multiplicador = desviacionEstandar/Math.sqrt(tamMuestra);
        gradosLibertad = tamMuestra-1;
        valTablas = tabla.tablaTeStudent(gradosLibertad,(float)tabla.redondeoDecimales((nivelConfianza/2),5));
        limInf = mediaPareada -(valTablas * multiplicador);
        limInf = mediaPareada + (valTablas * multiplicador);
    }

    public ICDIFMEDIASMUESTRASPAREADAS(double desviacionEstandar,double mediaPareada, int tamMuestra,double limite,char limi){
        super(limite);
        multiplicador = desviacionEstandar/Math.sqrt(tamMuestra);
        gradosLibertad = tamMuestra-1;
        valTablas = tabla.tablaTeStudent(gradosLibertad,(float)(nivelConfianza/2));
        limInf = mediaPareada -(valTablas * multiplicador);
        limInf = mediaPareada + (valTablas * multiplicador);
    }

    public ICDIFMEDIASMUESTRASPAREADAS(double limite, double mediaPareada,double desviacionEstandar,int tamMuestra,char limi){
        super(limite,mediaPareada,desviacionEstandar);
        double aux = (((mediaPareada-limite)/desviacionEstandar)*Math.sqrt(tamMuestra));
        if(aux<0) aux =aux*-1;
        gradosLibertad = tamMuestra-1;
        valTablas = tabla.tablaTstudentPotenciaPrueba(aux,gradosLibertad);
        if(limi =='a'){
            coeficienteConfianza = (2*valTablas) - 1;
        }else if(limi == 'b') coeficienteConfianza = 1-(2*valTablas);
    }

}
