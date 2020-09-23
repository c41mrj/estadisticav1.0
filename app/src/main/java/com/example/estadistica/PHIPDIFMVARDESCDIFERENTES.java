package com.example.estadistica;

public class PHIPDIFMVARDESCDIFERENTES extends CalculoTablas {

    private int gradosLibertad, tamMuestra1, tamMuestra2;
    private double diferenciaMedias,diferenciaMedias1,diferenciaPromedios,varianza1,varianza2,multiplicador,valTablas,limInf,limSup,significancia,estadisticoT;
    private String decision;

    public PHIPDIFMVARDESCDIFERENTES( int tamMuestra1, int tamMuestra2, double diferenciaMedias, double diferenciaPromedios, double varianza1, double varianza2,double significancia) {
        this.gradosLibertad =(int)Math.round(((Math.pow(((varianza1/tamMuestra1)+(varianza2/tamMuestra2)),2))/(((Math.pow((varianza1/tamMuestra1),2))*(1/(tamMuestra1-1)))+((Math.pow((varianza2/tamMuestra2),2))*(1/(tamMuestra2-1))))));
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        this.diferenciaMedias = diferenciaMedias;
        this.diferenciaPromedios = diferenciaPromedios;
        this.varianza1 = varianza1;
        this.varianza2 = varianza2;
        this.multiplicador = Math.sqrt(((varianza1/tamMuestra1)+(varianza2/tamMuestra2)));
        this.significancia = significancia;
    }

    public PHIPDIFMVARDESCDIFERENTES(int gradosLibertad, int tamMuestra1, int tamMuestra2, double diferenciaMedias, double diferenciaMedias1, double diferenciaPromedios, double varianza1, double varianza2) {
        this.gradosLibertad =(int)Math.round(((Math.pow(((varianza1/tamMuestra1)+(varianza2/tamMuestra2)),2))/(((Math.pow((varianza1/tamMuestra1),2))*(1/(tamMuestra1-1)))+((Math.pow((varianza2/tamMuestra2),2))*(1/(tamMuestra2-1))))));
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        this.diferenciaMedias = diferenciaMedias;
        this.diferenciaMedias1 = diferenciaMedias1;
        this.diferenciaPromedios = diferenciaPromedios;
        this.varianza1 = varianza1;
        this.varianza2 = varianza2;
    }

    public int getGradosLibertad() {
        return gradosLibertad;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public double getValTablas() {
        return valTablas;
    }

    public String getDecision() {
        return decision;
    }

    public double casoA(){
        limSup = diferenciaMedias + (multiplicador*tablaTeStudent(this.gradosLibertad,(float)significancia));
        valTablas = tablaTeStudent(this.gradosLibertad,(float)significancia);
        if(estaDentroDeLaZonaDeRechazoCasoa()){
            this.decision = "A una significancia de: " + significancia + "se rechaza la hipotesis nula";
            return limSup;
        }else
            this.decision = "A una significancia de: " + significancia + " no hay evidencias suficientes para rechazar la hipotesis nula";
            return limSup;
    }

    public String casoB(){
        limInf = diferenciaMedias - (multiplicador * tablaTeStudent(this.gradosLibertad,(float)(significancia/2)));
        valTablas = tablaTeStudent(this.gradosLibertad,(float)(significancia/2));
        limSup = diferenciaMedias + (multiplicador * tablaTeStudent(this.gradosLibertad,(float)(significancia/2)));
        String intervalo = "[" + limInf + "," + limSup+"]";
        if(estaDentroDeLaZonaDeRechazoCasobd()){
            this.decision = "A una significancia de " + significancia+ " se rechaza la hipotesis nula ";
            return intervalo;
        }else
            this.decision = "A una significancia de " + significancia + " no hay evidencias suficientes para rechazar la hipotesis nula";
            return intervalo;
    }

    public double casoC(){
        limSup = diferenciaMedias - (multiplicador*tablaTeStudent(this.gradosLibertad,(float)significancia));
        valTablas = tablaTeStudent(this.gradosLibertad,(float)significancia);
        if(estaDentroDeLaZonaDeRechazoCasoc()){
            this.decision = "A una significancia de: " + significancia + "se rechaza la hipotesis nula";
            return limInf;
        }else
            this.decision = "A una significancia de: " + significancia + " no hay evidencias suficientes para rechazar la hipotesis nula";
        return limInf;
    }

    public String casoD(){
        limInf = diferenciaMedias - (multiplicador * tablaTeStudent(this.gradosLibertad,(float)(significancia/2)));
        valTablas = tablaTeStudent(this.gradosLibertad,(float)(significancia/2));
        limSup = diferenciaMedias1 + (multiplicador * tablaTeStudent(this.gradosLibertad,(float)(significancia/2)));
        String intervalo = "[" + limInf + "," + limSup+"]";
        if(estaDentroDeLaZonaDeRechazoCasobd()){
            this.decision = "A una significancia de " + significancia+ " se rechaza la hipotesis nula ";
            return intervalo;
        }else
            this.decision = "A una significancia de " + significancia + " no hay evidencias suficientes para rechazar la hipotesis nula";
        return intervalo;
    }

    public double potenciaPrueba(double limite,double nuevaDiferenciaMedias){
        this.estadisticoT = (limite-nuevaDiferenciaMedias)/(multiplicador);
        if(estadisticoT<0) estadisticoT = estadisticoT*-1;
        double probabilidad = tablaTstudentPotenciaPrueba(estadisticoT,this.gradosLibertad);
        return probabilidad;
    }

    private boolean estaDentroDeLaZonaDeRechazoCasoa(){
        if(this.diferenciaPromedios>this.limSup){
            return true;
        }else return false;
    }

    private boolean estaDentroDeLaZonaDeRechazoCasobd(){
        if(this.diferenciaPromedios<this.limInf ||this.diferenciaPromedios>this.limSup){
            return true;
        }else return false;
    }

    private boolean estaDentroDeLaZonaDeRechazoCasoc(){
        if(this.diferenciaPromedios<this.limInf){
            return true;
        }else return false;
    }
}
