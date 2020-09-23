package com.example.estadistica;

public class PHIPSUMATORIAS implements PROPORCIONES {
    private CalculoTablas tablas = new CalculoTablas();
    private double limInf,limSup,limInf1,limSup1;
    private double proporcionPoblacional,sumaMuestral,probabilidad,complemento,valTablas,valTablas1,significancia,multiplicador, proporcionPoblacional1;
    private  String decision;
    protected double estadisticoZ;
    private int tamMuestra;

    public PHIPSUMATORIAS(double proporcionPoblacional, double sumaMuestral, double probabilidad, double complemento, double significancia, int tamMuestra) {
        this.proporcionPoblacional = proporcionPoblacional;
        this.sumaMuestral = sumaMuestral;
        this.probabilidad = probabilidad;
        this.complemento = complemento;
        this.significancia = significancia;
        this.tamMuestra = tamMuestra;
        this.multiplicador = Math.sqrt((this.tamMuestra*this.probabilidad*this.complemento));
    }

    public PHIPSUMATORIAS(double proporcionPoblacional, double sumaMuestral, double probabilidad, double complemento, double significancia, int tamMuestra,double proporcionPoblacional1) {
        this.proporcionPoblacional = proporcionPoblacional;
        this.sumaMuestral = sumaMuestral;
        this.probabilidad = probabilidad;
        this.complemento = complemento;
        this.significancia = significancia;
        this.tamMuestra = tamMuestra;
        this.multiplicador = Math.sqrt((this.tamMuestra*this.probabilidad*this.complemento));
        this.proporcionPoblacional = proporcionPoblacional;
    }


    public double getLimInf() {
        return limInf;
    }

    public double getLimSup() {
        return limSup;
    }

    public double getValTablas() {
        return valTablas;
    }

    public double getValTablas1() {
        return valTablas1;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public String getDecision() {
        return decision;
    }

    public double getEstadisticoZ() {
        return estadisticoZ;
    }

    @Override
    public double casoA() {
        valTablas = tablas.tablazetaAcumuladaPotencia((1-significancia));
        valTablas1 = tablas.tablazeta((float)significancia);
        limSup = (this.tamMuestra*this.proporcionPoblacional)+(valTablas*multiplicador);
        limSup1 = (this.tamMuestra*this.proporcionPoblacional)+(valTablas1* multiplicador);
        if(estaDentroDeLaZonaDeRechazoCasoa()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return limSup;
        }else
            decision = "A una significancia de " + significancia + " no existe evidencia suficiente para rechazar la hipotesis nula";
            return limSup;
    }


    @Override
    public String casoB() {
        valTablas = tablas.tablazetaAcumuladaPotencia((significancia/2));
        valTablas1 = tablas.tablazetaAcumuladaPotencia((1-(significancia/2)));
        limInf = (this.tamMuestra * this.proporcionPoblacional)+(valTablas*multiplicador);
        limSup = (this.tamMuestra * this.proporcionPoblacional)+(valTablas1 * multiplicador);
        String intervalo = "[" + limInf + "," + limSup + "]";
        if(estaDentroDeLaZonaDeRechazoCasobd()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return intervalo;
        }else
            decision = "A una significancia de " + significancia + " no existe evidencia suficiente para rechazar la hipotesis nula";
        return intervalo;
    }

    @Override
    public double casoC() {
        valTablas = tablas.tablazetaAcumuladaPotencia(significancia);
        valTablas1 = tablas.tablazeta((float) significancia);
        limInf = (this.tamMuestra*proporcionPoblacional)+(valTablas*multiplicador);
        if(estaDentroDeLaZonaDeRechazoCasoc()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return limInf;
        }else
            decision = "A una significancia de " + significancia + " no existe evidencia suficiente para rechazar la hipotesis nula";
            return limInf;
     }

    @Override
    public String casoD() {
        valTablas = tablas.tablazetaAcumuladaPotencia((significancia/2));
        valTablas1 = tablas.tablazetaAcumuladaPotencia((1-(significancia/2)));
        limInf = (this.tamMuestra * this.proporcionPoblacional)+(valTablas*multiplicador);
        limSup = (this.tamMuestra * this.proporcionPoblacional1)+(valTablas1 * multiplicador);
        String intervalo = "[" + limInf + "," + limSup + "]";
        if(estaDentroDeLaZonaDeRechazoCasobd()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return intervalo;
        }else
            decision = "A una significancia de " + significancia + " no existe evidencia suficiente para rechazar la hipotesis nula";
        return intervalo;
    }

    @Override
    public double potenciaPrueba(double nuevaProporcionPoblacional, double limite) {
        estadisticoZ = (limite-(this.tamMuestra*nuevaProporcionPoblacional))/multiplicador;
        valTablas = tablas.tablazetaAcumulada(estadisticoZ);
        return valTablas;
    }

    @Override
    public boolean estaDentroDeLaZonaDeRechazoCasoa() {
       if(this.sumaMuestral>limSup){
           return true;
       }else return false;
    }

    @Override
    public boolean estaDentroDeLaZonaDeRechazoCasobd() {
        if(this.sumaMuestral< limInf || this.sumaMuestral>limSup){
            return true;
        }else return false;
    }

    @Override
    public boolean estaDentroDeLaZonaDeRechazoCasoc() {
        if(this.sumaMuestral<limInf){
            return true;
        }else return false;
    }
}
