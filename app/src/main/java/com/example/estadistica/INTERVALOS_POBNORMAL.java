package com.example.estadistica;

public interface INTERVALOS_POBNORMAL {

    public double calcLimInf();
    public double calcLimSup();
    public double calcGradoConfianza(char limite,double valLimite);
    public double obtenerLimite(double valLimite, double mediaMuestral);
    public int obtenerTamMinimoMuestra();

}
