package com.example.estadistica;

public class TEOREMACENTRALLIMITEDIFERENCIAMEDIAS extends TEOREMACENTRALLIMITE {

    private double z = 0,valTablas;
    private CalculoTablas tablas = new CalculoTablas();

    public double getZ() {
        return z;
    }

    public double getValTablas() {
        return valTablas;
    }

    public TEOREMACENTRALLIMITEDIFERENCIAMEDIAS(double diferenciaPromedios,double diferenciaMedias,int n1,int n2, double varianza1,double varianza2){
        this.z = calcularEstadisticoZDiferenciaMedias(diferenciaPromedios,diferenciaMedias,n1,n2,varianza1,varianza2);
        valTablas = calcularProbabilidad(z);
    }


}
