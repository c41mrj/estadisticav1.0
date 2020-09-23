package com.example.estadistica;

public class TEOREMACENTRALLIMITEDIFERENCIAPROPORCIONES extends TEOREMACENTRALLIMITE {

    private double z = 0,valTablas;
    private CalculoTablas tablas = new CalculoTablas();

    public double getZ() {
        return z;
    }

    public double getValTablas() {
        return valTablas;
    }

    public TEOREMACENTRALLIMITEDIFERENCIAPROPORCIONES(double proporcion,double proporcion2,double prob1,double prob2, int n1, int n2){
        this.z = calcularEstadisticoZDiferenciaProporciones(proporcion,proporcion2,prob1,prob2,n1,n2);
        valTablas = calcularProbabilidad(z);
    }

}
