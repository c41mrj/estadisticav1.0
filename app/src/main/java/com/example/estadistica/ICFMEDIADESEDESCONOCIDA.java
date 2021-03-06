package com.example.estadistica;

public class ICFMEDIADESEDESCONOCIDA extends frameworkICF1{

    private int gradosLibertad;
    private double determinante;
    public double getDeterminante(){return determinante;}

    public int getGradosLibertad() {
        return gradosLibertad;
    }

    public ICFMEDIADESEDESCONOCIDA(double desviacionEstandar, int tamMuestra, double Confianza, double promedioMuestral) {  //constructor para calcular los límites
        this.desviacionEstandar = desviacionEstandar;
        this.confianza = (1-Confianza)/2;
        this.confianza = tablas.redondeoDecimales(this.confianza,5);
        this.tamMuestra = tamMuestra;
        this.multiplicador = this.desviacionEstandar/(Math.sqrt(tamMuestra));
        this.mediaMuestral = promedioMuestral;
        this.gradosLibertad = tamMuestra -1;
    }


    public ICFMEDIADESEDESCONOCIDA(double mediaMuestral, int tamMuestra, double desviacionEstandar){  //constructor para calcular el nivel de confianza
        this.mediaMuestral = mediaMuestral;
        this.tamMuestra = tamMuestra;
        this.desviacionEstandar = desviacionEstandar;
        this.gradosLibertad = tamMuestra - 1;
    }


    @Override
    public double calcLimInf() {
        this.valorTablas = tablas.tablaTeStudent(this.gradosLibertad,(float)confianza);
        this.limInf = mediaMuestral-(valorTablas*multiplicador);
        this.limInf = tablas.redondeoDecimales(this.limInf,5);
        return limInf;
    }

    @Override
    public double calcLimSup() {
        this.valorTablas = tablas.tablaTeStudent(this.gradosLibertad,(float)confianza);
        this.limSup = mediaMuestral + (valorTablas * multiplicador);
        this.limSup = tablas.redondeoDecimales(this.limSup,5);
        return limSup;
    }

    @Override
    public double calcGradoConfianza(char limite, double valLimite) {
        double aux = (((mediaMuestral-valLimite)/this.desviacionEstandar)*Math.sqrt(this.tamMuestra));
        aux = tablas.redondeoDecimales(aux,5);
        determinante = aux;
        valorTablas = tablas.tablaTstudentPotenciaPrueba(aux,this.gradosLibertad);
        if(aux>0) valorTablas = 1- valorTablas;
        if(limite == 'a'){
            limSup = (2*mediaMuestral) - valLimite;
            return (2*valorTablas)-1;
        }
        else if (limite == 'b') {
            limInf = (2*mediaMuestral) - valLimite;
            return 1-(2*valorTablas);
        }
        else return 0;

    }

    @Override
    public int obtenerTamMinimoMuestra() {
        int tamMinimo = (int)Math.floor(this.multiplicador*Math.pow(this.desviacionEstandar,2));
        return tamMinimo + 1;
    }
}
