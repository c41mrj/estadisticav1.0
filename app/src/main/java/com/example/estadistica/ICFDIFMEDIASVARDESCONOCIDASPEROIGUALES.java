package com.example.estadistica;

public class ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES extends frameworkIC2 {

    private double Sp,determinante;
    private int gradosLibertad;

    public double getSp() {
        return Sp;
    }
    public double getDeterminante(){return determinante;}


    public ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES(double varianzaPob1, double varianzaPob2, int tamMuestra1, int tamMuestra2, double diferenciaDeMedias, double significancia) {
        super(varianzaPob1, varianzaPob2, tamMuestra1, tamMuestra2, diferenciaDeMedias, significancia);
        gradosLibertad = tamMuestra1 + tamMuestra2 -2;
        Sp = tabla.redondeoDecimales((Math.sqrt(((((tamMuestra1-1)*varianzaPob1)+((tamMuestra2-1)*varianzaPob2))/gradosLibertad))),2);
        double confianza = (1-significancia)/2;
        confianza = tabla.redondeoDecimales(confianza,5);
        this.valTablas = tabla.tablaTeStudent(gradosLibertad,(float)confianza);
        double valt = this.valTablas;
        this.multiplicador = Math.sqrt((1/(double)tamMuestra1)+(1/(double)tamMuestra2));
        this.multiplicador = tabla.redondeoDecimales(multiplicador,6);
        limInf = diferenciaDeMedias - (valt*multiplicador*Sp);
        limSup = diferenciaDeMedias + (valt*multiplicador*Sp);
    }

    public ICFDIFMEDIASVARDESCONOCIDASPEROIGUALES(int tamMuestra1, int tamMuestra2, double varianzaPob1, double varianzaPob2, double diferenciaDeMedias, double limite,char lim) {
        super(tamMuestra1,tamMuestra2,varianzaPob1,varianzaPob2,diferenciaDeMedias,limite);
        this.multiplicador = Math.sqrt(((1/(double)tamMuestra1)+(1/(double)tamMuestra2)));
        this.gradosLibertad = tamMuestra1 + tamMuestra2 -2;
        Sp = tabla.redondeoDecimales((Math.sqrt(((((tamMuestra1-1)*varianzaPob1)+((tamMuestra2-1)*varianzaPob2))/gradosLibertad))),2);
        double aux = (diferenciaDeMedias-limite)/(Sp*multiplicador);
        aux = tabla.redondeoDecimales(aux,3);
        determinante = aux;
        if(aux < 0){
            aux = aux*-1;
            valTablas = tabla.tablaTstudentPotenciaPrueba(aux,gradosLibertad);
        }else{
            valTablas = 1-tabla.tablaTstudentPotenciaPrueba(aux,gradosLibertad);
        }
        if(lim == 'a'){
            coeficienteConfianza = (2*valTablas)-1;
        }else if (lim == 'b'){
            coeficienteConfianza = 1- (2*valTablas);
        }
    }
}
