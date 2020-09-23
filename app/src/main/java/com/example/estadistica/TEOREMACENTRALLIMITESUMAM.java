package com.example.estadistica;

public class TEOREMACENTRALLIMITESUMAM extends TEOREMACENTRALLIMITE {
    private double z = 0,valTablas;
    private CalculoTablas tablas = new CalculoTablas();

    public double getZ() {
        return z;
    }

    public double getValTablas() {
        return valTablas;
    }

    public TEOREMACENTRALLIMITESUMAM(double T, double miu, double varianza, int tamMuestra){
        this.z = calcularEstadisticoZsumaVariables(T,tamMuestra,miu,varianza);
        valTablas = calcularProbabilidad(z);
    }

}
