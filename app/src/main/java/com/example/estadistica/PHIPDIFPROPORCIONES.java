package com.example.estadistica;

public class PHIPDIFPROPORCIONES implements PROPORCIONES {

    CalculoTablas tablas = new CalculoTablas();
    private double probabilidad1,complemento1,probabilidad2,complemento2,diferenciaProporcionesMuestrales,diferenciaProporcionesPoblacionales,significancia,multiplicador,diferenciaProporcionesPoblacionales2;
    private int tamMuestra1,tamMuestra2;
    protected String decision;
    protected double estadisticoZ;
    private double valTablas,valTablas1,limInf,limSup;

    public PHIPDIFPROPORCIONES(double probabilidad1, double complemento1, double probabilidad2, double complemento2, double diferenciaProporcionesMuestrales, double diferenciaProporcionesPoblacionales, double significancia, int tamMuestra1, int tamMuestra2) {
        this.probabilidad1 = probabilidad1;
        this.complemento1 = complemento1;
        this.probabilidad2 = probabilidad2;
        this.complemento2 = complemento2;
        this.diferenciaProporcionesMuestrales = diferenciaProporcionesMuestrales;
        this.diferenciaProporcionesPoblacionales = diferenciaProporcionesPoblacionales;
        this.significancia = significancia;
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        this.decision = "";
        this.multiplicador = Math.sqrt((((probabilidad1*complemento1)/tamMuestra1)+((probabilidad2*complemento2)/tamMuestra2)));
    }

    public PHIPDIFPROPORCIONES(double probabilidad1, double complemento1, double probabilidad2, double complemento2, double diferenciaProporcionesMuestrales, double diferenciaProporcionesPoblacionales, double significancia, int tamMuestra1, int tamMuestra2,double diferenciaProporcionesPoblacionales2) {
        this.probabilidad1 = probabilidad1;
        this.complemento1 = complemento1;
        this.probabilidad2 = probabilidad2;
        this.complemento2 = complemento2;
        this.diferenciaProporcionesMuestrales = diferenciaProporcionesMuestrales;
        this.diferenciaProporcionesPoblacionales = diferenciaProporcionesPoblacionales;
        this.significancia = significancia;
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        this.diferenciaProporcionesPoblacionales2 = diferenciaProporcionesPoblacionales2;
        this.decision = "";
        this.multiplicador = Math.sqrt((((probabilidad1*complemento1)/tamMuestra1)+((probabilidad2*complemento2)/tamMuestra2)));
    }




    @Override
    public double casoA() {
        valTablas = tablas.tablazetaAcumuladaPotencia((1-significancia));
        limSup = diferenciaProporcionesPoblacionales + (valTablas*multiplicador);
        if(estaDentroDeLaZonaDeRechazoCasoa()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return limSup;
        }else
            decision ="A una significancia de " + significancia + " no existe evidencia para rechazar la hipotesis nula";
            return limSup;
    }

    @Override
    public String casoB() {
        valTablas = tablas.tablazetaAcumuladaPotencia((significancia/2));
        valTablas1 = tablas.tablazetaAcumuladaPotencia((1-(significancia/2)));
        limInf = diferenciaProporcionesPoblacionales + valTablas;
        limSup = diferenciaProporcionesPoblacionales + valTablas1;
        String intervalo = "["+limInf + "," + limSup + "]";
        if(estaDentroDeLaZonaDeRechazoCasobd()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return intervalo;
        }else
            decision ="A una significancia de " + significancia + " no existe evidencia para rechazar la hipotesis nula";
        return intervalo;
    }

    @Override
    public double casoC() {
        valTablas = tablas.tablazetaAcumuladaPotencia(significancia);
        limInf = diferenciaProporcionesPoblacionales + (valTablas*multiplicador);
        if(estaDentroDeLaZonaDeRechazoCasoc()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return limInf;
        }else
            decision ="A una significancia de " + significancia + " no existe evidencia para rechazar la hipotesis nula";
        return limInf;
    }

    @Override
    public String casoD() {
        valTablas = tablas.tablazetaAcumuladaPotencia((significancia/2));
        valTablas1 = tablas.tablazetaAcumuladaPotencia((1-(significancia/2)));
        limInf = diferenciaProporcionesPoblacionales + valTablas;
        limSup = diferenciaProporcionesPoblacionales2 + valTablas1;
        String intervalo = "["+limInf + "," + limSup + "]";
        if(estaDentroDeLaZonaDeRechazoCasobd()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return intervalo;
        }else
            decision ="A una significancia de " + significancia + " no existe evidencia para rechazar la hipotesis nula";
        return intervalo;
    }

    @Override
    public double potenciaPrueba(double nuevaProporcionPoblacional, double limite) {
        estadisticoZ = (limite-nuevaProporcionPoblacional)/(Math.sqrt((((this.probabilidad1*this.complemento1)/this.tamMuestra1)+((this.probabilidad2*this.complemento2)/this.tamMuestra2))));
        double valor = tablas.tablazetaAcumulada(estadisticoZ);
        return valor;
    }

    @Override
    public boolean estaDentroDeLaZonaDeRechazoCasoa() {
        if(diferenciaProporcionesMuestrales>limSup){
            return true;
        }else return false;
    }

    @Override
    public boolean estaDentroDeLaZonaDeRechazoCasobd() {
        if(diferenciaProporcionesMuestrales<limInf || diferenciaProporcionesMuestrales>limSup){
            return true;
        }return false;
    }

    @Override
    public boolean estaDentroDeLaZonaDeRechazoCasoc() {
        if(diferenciaProporcionesMuestrales<limInf){
            return true;
        }else return false;
    }
}
