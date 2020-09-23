package com.example.estadistica;

public class PruebaHipotesisDifMediasVarConocidas extends CalculoTablas {
    private double diferenciaMedias,diferenciaMedias1,varianza1,varianza2,significancia;
    private int tamMuestra1, tamMuestra2;
    private double limInf,limSup;
    private double varianza,discriminante,diferenciaPromedios,valTablas,valTablas2,estadisticoZ;
    private String decision;

    public double getVarianza(){
        return this.varianza;
    }

    public String getDecision(){
        return this.decision;
    }

    public double getLimInf(){
        return this.limInf;
    }

    public double getLimSup(){
        return this.limSup;
    }

    public double getValTablas(){
        return this.valTablas;
    }

    public double getEstadisticoZ(){return this.estadisticoZ;}

    public double getValTablas2(){
        return this.valTablas2;
    }

    public PruebaHipotesisDifMediasVarConocidas(){
    }

    public PruebaHipotesisDifMediasVarConocidas(double diferenciaMedias, double varianza1, double varianza2, int tamMuestra1, int tamMuestra2, double significancia,double diferenciaPromedios){
        this.diferenciaMedias = diferenciaMedias;
        this.varianza1 = varianza1;
        this.varianza2 = varianza2;
        this.significancia = significancia;
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        double aux = (this.varianza1 / (double)this.tamMuestra1)+(this.varianza2/(double)this.tamMuestra2);
        this.varianza = Math.sqrt(aux);
        this.diferenciaPromedios = diferenciaPromedios;
        this.discriminante = diferenciaPromedios;
    }

    public PruebaHipotesisDifMediasVarConocidas(double diferenciaMedias,double diferenciaMedias1, double varianza1, double varianza2, int tamMuestra1, int tamMuestra2, double significancia,double diferenciaPromedios){
        this.diferenciaMedias = diferenciaMedias;
        this.varianza1 = varianza1;
        this.varianza2 = varianza2;
        this.significancia = significancia;
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        this.varianza = Math.sqrt((this.varianza1 / this.tamMuestra1)+(this.varianza2/this.tamMuestra2));
        this.diferenciaPromedios = diferenciaPromedios;
        this.discriminante = diferenciaPromedios;
        this.diferenciaMedias1 = diferenciaMedias1;
    }

    public double caso1(){
         this.limSup =diferenciaMedias + (tablazeta((float)this.significancia)*this.varianza);
         this.limSup = redondeoDecimales(this.limSup,5);
        if(estaDentroDeLaRegionDeRechazocasoA()){
            decision = "A un nivel de significancia del " + this.significancia +" se rechaza la hipotesis nula";
            valTablas = tablazeta((float) this.significancia);
            return this.limSup;
        }else
            decision = "A un nivel de significancia del " + this.significancia + " se acepta la hipotesis nula";
            valTablas = tablazeta((float) this.significancia);
            return this.limSup;
    }

    public String caso2(){
        this.limInf = this.diferenciaMedias-(tablazeta((float)(this.significancia/2))*this.varianza);
        this.limInf = redondeoDecimales(this.limInf,5);
        this.valTablas = tablazeta((float)redondeoDecimales((this.significancia/2),4));
        this.limSup = this.diferenciaMedias+(tablazeta((float)(this.significancia/2))*this.varianza);
        this.limSup = redondeoDecimales(this.limSup,5);
        this.valTablas2 = tablazeta((float)redondeoDecimales((this.significancia/2),5));
        String intervalo = "["+limInf+","+limSup+"]";
        if(estaDentroDeLaRegionDeRechazocasoBD()){
            this.decision = "A una significancia del " + this.significancia + " se rechaza la hipotesis nula";
            return intervalo;
        }else
            this.decision = "A una significancia del " + this.significancia + " se acepta la hipotesis nula";
            return intervalo;
    }

    public double caso3(){
        this.limInf = this.diferenciaMedias - ((tablazeta((float)this.significancia))*this.varianza);
        this.limInf = redondeoDecimales(this.limInf,5);
        this.valTablas = tablazeta((float)this.significancia);
        if(estaDentroDeLaRegionDeRechazocasoC()){
            this.decision = "A una significancia del " + this.significancia + " se rechaza la hipotesis nula";
            return this.limInf;
        }else
            this.decision = "A una significancia del " + this.significancia + " se acepta la hipotesis nula";
            return this.limInf;
    }

    public String caso4(){
        this.limInf = this.diferenciaMedias - ((tablazeta((float)(this.significancia/2)))*this.varianza);
        this.limInf = redondeoDecimales(this.limInf,5);
        this.valTablas = tablazeta((float)redondeoDecimales((this.significancia/2),5));
        this.limSup = this.diferenciaMedias1 + ((tablazeta((float)redondeoDecimales((this.significancia/2),5)))*this.varianza);
        this.limSup = redondeoDecimales(this.limSup,5);
        if(estaDentroDeLaRegionDeRechazocasoBD()){
            this.decision = "A una significancia del " + this.significancia + " se rechaza la hipotesis nula";
            return "[" + this.limInf + "," + this.limSup+"]";
        }else
            this.decision = "A una significancia del " + this.significancia + " se acepta la hipotesis nula";
            return "[" + this.limInf + "," + this.limSup+"]";
    }

    public double potenciaPrueba(double limite,double nuevaDifMedias){
        estadisticoZ = (limite- nuevaDifMedias) / this.varianza;
        estadisticoZ = redondeoDecimales(estadisticoZ,4);
        double probabilidad = tablazetaAcumulada(estadisticoZ);
        return probabilidad;
    }

    public boolean estaDentroDeLaRegionDeRechazocasoA(){
        if(this. discriminante>limSup){
            return true;
        }else return false;
    }

    public boolean estaDentroDeLaRegionDeRechazocasoBD(){
        if(this.discriminante<limInf || this.discriminante > limSup){
            return true;
        }else return false;
    }

    public boolean estaDentroDeLaRegionDeRechazocasoC(){
        if(this.discriminante<limInf){
            return true;
        }else return false;
    }

}
