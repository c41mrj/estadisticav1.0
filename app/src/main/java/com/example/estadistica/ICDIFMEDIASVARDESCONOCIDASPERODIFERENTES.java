package com.example.estadistica;

public class ICDIFMEDIASVARDESCONOCIDASPERODIFERENTES extends frameworkIC2 {

    private double determinante;


    public double getDeterminante(){return determinante;}


    public ICDIFMEDIASVARDESCONOCIDASPERODIFERENTES(double varianzaPob1, double varianzaPob2, int tamMuestra1, int tamMuestra2, double diferenciaDeMedias, double significancia) {
        super(varianzaPob1, varianzaPob2, tamMuestra1, tamMuestra2, diferenciaDeMedias, significancia);
        this.gradosLibertad  = (int)Math.round((Math.pow(((varianzaPob1/(double)tamMuestra1)+(varianzaPob2/(double) tamMuestra2)),2))/((Math.pow((varianzaPob1/(double)tamMuestra1),2)*(1/(double)(tamMuestra1-1)))+((Math.pow((varianzaPob2/(double)tamMuestra2),2))*(1/(double)(tamMuestra2-1)))));
        valTablas = tabla.tablaTeStudent(gradosLibertad,(float)tabla.redondeoDecimales(((1-significancia)/2),5));
        this.multiplicador = Math.sqrt(((this.varianzaPob1/tamMuestra1)+(this.varianzaPob2/tamMuestra2)));
        this.limInf = diferenciaDeMedias - (valTablas * this.multiplicador);
        this.limSup = diferenciaDeMedias + (valTablas*this.multiplicador);
    }

    public ICDIFMEDIASVARDESCONOCIDASPERODIFERENTES(int tamMuestra1, int tamMuestra2, double varianzaPob1, double varianzaPob2, double diferenciaDeMedias, double limite,char interval) {
        super(tamMuestra1,tamMuestra2,varianzaPob1,varianzaPob2,diferenciaDeMedias,limite);
        this.gradosLibertad  = (int)Math.round((Math.pow(((varianzaPob1/(double)tamMuestra1)+(varianzaPob2/(double) tamMuestra2)),2))/((Math.pow((varianzaPob1/(double)tamMuestra1),2)*(1/(double)(tamMuestra1-1)))+((Math.pow((varianzaPob2/(double)tamMuestra2),2))*(1/(double)(tamMuestra2-1)))));
        this.multiplicador = Math.sqrt(((this.varianzaPob1/tamMuestra1)+(this.varianzaPob2/tamMuestra2)));
        double aux = tabla.redondeoDecimales(((diferenciaDeMedias-limite)/multiplicador),5);
        determinante = aux;
        if(aux<0){
            aux = aux *-1;
            valTablas = tabla.tablaTstudentPotenciaPrueba(aux,gradosLibertad);
        }else{
            valTablas = 1-tabla.tablaTstudentPotenciaPrueba(determinante,gradosLibertad);
        }
        if(interval == 'a'){
            coeficienteConfianza = (2*valTablas)-1;
        }else if(interval == 'b'){
            coeficienteConfianza = 1-(2*valTablas);
        }
    }
}
