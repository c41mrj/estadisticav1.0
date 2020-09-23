package com.example.estadistica;

public class TEOREMACENTRALLIMITEMEDIA extends TEOREMACENTRALLIMITE {
    private double z = 0,valTablas,z1=0,valTablas1,probabilidad;
    private CalculoTablas tablas = new CalculoTablas();

    public double getZ() {
        return z;
    }

    public double getValTablas() {
        return valTablas;
    }

    public double getZ1() {
        return z1;
    }

    public double getValTablas1() {
        return valTablas1;
    }


    public double getProbabilidad(){
        return probabilidad;
    }

    public int calcularTamañoMinimoMuestra(double varianza, double nivelConfianza, double d0){
        valTablas = tablas.tablazetaAcumuladaPotencia(((1-nivelConfianza)/2));
        return (int)Math.floor(Math.pow(((varianza*valTablas)/d0),2) + 1);
    }

    public TEOREMACENTRALLIMITEMEDIA(){

    }

    public TEOREMACENTRALLIMITEMEDIA(double xBarra, double miu, double varianza, int tamMuestra){
        this.z = calcularEstadisticoZ(xBarra,miu,varianza,tamMuestra);
        valTablas = calcularProbabilidad(z);
    }

    public TEOREMACENTRALLIMITEMEDIA(double xBarra, double miu, double varianza, int tamMuestra,double xBarra1){
        this.z = calcularEstadisticoZ(xBarra,miu,varianza,tamMuestra);
        this.z1 = calcularEstadisticoZ(xBarra1,miu,varianza,tamMuestra);
        valTablas = calcularProbabilidad(z);
        valTablas1 = calcularProbabilidad(z1);
        probabilidad = valTablas + (1-valTablas1);
    }


}
