package com.example.estadistica;

public class frameworkICF1 implements INTERVALOS_POBNORMAL {
    protected double desviacionEstandar,valorTablas,mediaMuestral,mediaPoblacional,errorMuestral,confianza,multiplicador;
    protected int tamMuestra;
    protected double a,b,limInf,limSup;
    protected CalculoTablas tablas = new CalculoTablas();

    public double getConfianza() {return confianza;}

    public double getValorTablas() {
        return valorTablas;
    }

    public double getLimInf() {
        return limInf;
    }

    public double getLimSup() {
        return limSup;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    @Override
    public double calcLimInf() {
        return 0;
    }

    @Override
    public double calcLimSup() {
        return 0;
    }

    @Override
    public double calcGradoConfianza(char limite, double valLimite) {
        return 0;
    }

    @Override
    public double obtenerLimite(double valLimite,double mediaMuestral) {
        return 0;
    }

    @Override
    public int obtenerTamMinimoMuestra() {
        return 0;
    }
}
