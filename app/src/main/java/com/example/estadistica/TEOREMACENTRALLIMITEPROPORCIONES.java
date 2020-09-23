package com.example.estadistica;

public class TEOREMACENTRALLIMITEPROPORCIONES extends TEOREMACENTRALLIMITE {
    private double z = 0,valTablas;
    private CalculoTablas tablas = new CalculoTablas();

    public double getZ() {
        return z;
    }

    public double getValTablas() {
        return valTablas;
    }

    public TEOREMACENTRALLIMITEPROPORCIONES(double probabilidad,double proporcion,int tamMuestra){
        this.z = calcularEstadisticoZProporciones(probabilidad,proporcion,tamMuestra);
        this.valTablas = calcularProbabilidad(z);
    }



}
