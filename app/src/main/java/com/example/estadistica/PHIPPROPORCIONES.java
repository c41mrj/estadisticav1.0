package com.example.estadistica;

public class PHIPPROPORCIONES implements PROPORCIONES {
    private CalculoTablas tablas = new CalculoTablas();
    private double limInf,limSup,limInf1,limSup1;
    private double proporcionPoblacional,proporcionMuestral,probabilidad,complemento,valTablas,valTablas1,significancia,multiplicador, proporcionPoblacional1;
    private  String decision;
    protected double estadisticoZ;
    private int tamMuestra;

    public PHIPPROPORCIONES(double porporcionPoblacional, double proporcionMuestral, double probabilidad, double complemento, int tamMuestra, double significancia) {
        this.proporcionPoblacional = porporcionPoblacional;
        this.proporcionMuestral = proporcionMuestral;
        this.probabilidad = probabilidad;
        this.complemento = complemento;
        this.tamMuestra = tamMuestra;
        this.significancia = significancia;
        this.multiplicador = Math.sqrt(((probabilidad*complemento)/tamMuestra));
        this.decision = "";
    }

    public PHIPPROPORCIONES(double porporcionPoblacional,double proporcionPoblacional1, double proporcionMuestral, double probabilidad, double complemento, int tamMuestra, double significancia) {
        this.proporcionPoblacional = porporcionPoblacional;
        this.proporcionMuestral = proporcionMuestral;
        this.probabilidad = probabilidad;
        this.complemento = complemento;
        this.tamMuestra = tamMuestra;
        this.significancia = significancia;
        this.multiplicador = Math.sqrt(((probabilidad*complemento)/tamMuestra));
        this.decision = "";
        this.proporcionPoblacional1 = proporcionPoblacional1;
    }


    public double getEstadisticoZ(){
        return this.estadisticoZ;
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

    @Override
    public double casoA() {
        valTablas = tablas.tablazetaAcumuladaPotencia((1-significancia));
        valTablas1 = tablas.tablazeta((float)significancia);
        limInf = proporcionPoblacional +(valTablas*multiplicador);
        limInf1 = proporcionPoblacional + (valTablas1*multiplicador);
        if(estaDentroDeLaZonaDeRechazoCasoa()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return limInf;
        }else
            decision = "A una significancia de " + significancia + " no existe evidencia suficiente para rechazar la hipotesis nula";
            return limInf;
    }

    @Override
    public String casoB() {
        valTablas = tablas.tablazetaAcumuladaPotencia((float)(significancia/2));
        valTablas1 = tablas.tablazetaAcumuladaPotencia((float)(1-(significancia/2)));
        limInf = proporcionPoblacional + (valTablas*multiplicador);
        limSup = proporcionPoblacional + (valTablas1 * multiplicador);
        String intervalo = "[" +limInf +  "," + limSup + "]";
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
        valTablas1 = tablas.tablazeta((float)significancia);
        limInf = proporcionPoblacional +(valTablas*multiplicador);
        limInf1 = proporcionPoblacional - (valTablas1*multiplicador);
        if(estaDentroDeLaZonaDeRechazoCasoc()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return limInf;
        }else
            decision = "A una significancia de " + significancia + " no existe evidencia suficiente para rechazar la hipotesis nula";
        return limInf;
    }

    @Override
    public String casoD() {
        valTablas = tablas.tablazetaAcumuladaPotencia((float)(significancia/2));
        valTablas1 = tablas.tablazetaAcumuladaPotencia((float)(1-(significancia/2)));
        limInf = proporcionPoblacional + (valTablas*multiplicador);
        limSup = proporcionPoblacional1 + (valTablas1 * multiplicador);
        String intervalo = "[" +limInf +  "," + limSup + "]";
        if(estaDentroDeLaZonaDeRechazoCasobd()){
            decision = "A una significancia de " + significancia + " se rechaza la hipotesis nula";
            return intervalo;
        }else
            decision = "A una significancia de " + significancia + " no existe evidencia suficiente para rechazar la hipotesis nula";
        return intervalo;
    }

    @Override
    public double potenciaPrueba(double nuevaProporcionPoblacional,double limite) {
        estadisticoZ = (limite-nuevaProporcionPoblacional)/(Math.sqrt(((this.probabilidad*this.complemento)/this.tamMuestra)));
        valTablas = tablas.tablazetaAcumulada(estadisticoZ);
        return valTablas;
    }

    @Override
    public boolean estaDentroDeLaZonaDeRechazoCasoa() {
        if(this.proporcionMuestral>limSup || this.proporcionMuestral>limSup1){
            return true;
        }else return false;
    }

    @Override
    public boolean estaDentroDeLaZonaDeRechazoCasobd() {
        if(this.proporcionMuestral<limInf || this.proporcionMuestral>limSup){
            return true;
        }else return false;
    }

    @Override
    public boolean estaDentroDeLaZonaDeRechazoCasoc() {
        if(this.proporcionMuestral<limInf){
            return true;
        }else return false;
    }

}
