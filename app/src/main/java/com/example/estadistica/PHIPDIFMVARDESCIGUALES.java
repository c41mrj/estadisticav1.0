package com.example.estadistica;

public class PHIPDIFMVARDESCIGUALES extends CalculoTablas {

    private double estadisticoT,Sp,varianza1,varianza2,diferenciaDePromedios,diferenciaDeMedias,significancia,multiplicador,diferenciaDeMedias1;
    private int tamMuestra1,tamMuestra2,gradosLibertad;
    private double valTablas,valTablas2,limInf,limSup;
    private String conclusion;


    public PHIPDIFMVARDESCIGUALES(double varianza1, double varianza2, double diferenciaDePromedios, double diferenciaDeMedias, double significancia,int tamMuestra1, int tamMuestra2){
        this.varianza1 = varianza1;
        this.varianza2 = varianza2;
        this.diferenciaDePromedios = diferenciaDePromedios;
        this.diferenciaDeMedias = diferenciaDeMedias;
        this.significancia = significancia;
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        this.gradosLibertad = tamMuestra1+tamMuestra2 - 2;
        this.Sp = Math.sqrt((((tamMuestra1-1)*varianza1)+((tamMuestra2-1)*varianza2))/this.gradosLibertad);
        this.multiplicador = Math.sqrt((1/tamMuestra1)+(1/tamMuestra2));
    }

    public PHIPDIFMVARDESCIGUALES(double varianza1, double varianza2, double diferenciaDePromedios, double diferenciaDeMedias,double diferenciaDeMedias1, double significancia,int tamMuestra1, int tamMuestra2){
        this.varianza1 = varianza1;
        this.varianza2 = varianza2;
        this.diferenciaDePromedios = diferenciaDePromedios;
        this.diferenciaDeMedias = diferenciaDeMedias;
        this.diferenciaDeMedias1 = diferenciaDeMedias1;
        this.significancia = significancia;
        this.tamMuestra1 = tamMuestra1;
        this.tamMuestra2 = tamMuestra2;
        this.gradosLibertad = tamMuestra1+tamMuestra2 - 2;
        this.Sp = Math.sqrt((((tamMuestra1-1)*varianza1)+((tamMuestra2-1)*varianza2))/this.gradosLibertad);
        this.multiplicador = Math.sqrt((1/tamMuestra1)+(1/tamMuestra2));
    }

    public double getValTablas(){
        return this.valTablas;
    }

    public double getValTablas2(){
        return this.valTablas2;
    }

    public double getEstadisticoT() {
        return estadisticoT;
    }

    public double getSp() {
        return Sp;
    }

    public double getMultiplicador(){
        return this.multiplicador;
    }

    public String getConclusion(){
        return this.conclusion;
    }

    public double getLimInf(){
        return this.limInf;
    }

    public double getLimSup(){
        return this.limSup;
    }


    public double casoA(){
        this.limSup = diferenciaDeMedias + (tablaTeStudent(this.gradosLibertad,(float)this.significancia)*this.Sp*this.multiplicador);
        if(estaDentroDeLaRegionDeRechazoCasoa()){
            this.conclusion = "A un nivel de significancia del " + this.significancia + " se rechaza la hipotesis nula";
            valTablas = tablaTeStudent(this.gradosLibertad,(float)this.significancia);
            return this.limSup;
        }else
            this.conclusion = "A un nivel de significancia del " + this.significancia + " no existen evidencias para rechazar la hipotesis nula";
            valTablas = tablaTeStudent(this.gradosLibertad,(float)this.significancia);
            return this.limSup;
    }

    public String casoB(){
        this.limInf = diferenciaDeMedias - (tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2))*this.Sp*this.multiplicador);
        this.valTablas = tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2));
        this.limSup = diferenciaDeMedias + (tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2))*this.Sp*this.multiplicador);
        String intervalo = "[" + this.limInf +"," + this.limSup+ "]";
        if(estaDentroDeLaRegionDeRechazoCasobd()){
            this.conclusion = "A un nivel de significancia del " + this.significancia + " se rechaza la hipotesis nula";
            valTablas = tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2));
            return intervalo;
        }else
            this.conclusion = "A un nivel de significancia del " + this.significancia + "  no existe evidencia para rechazar la hipotesis nula";
            valTablas = tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2));
            return intervalo;
    }

    public double casoC(){
        this.limInf = diferenciaDeMedias - (tablaTeStudent(this.gradosLibertad,(float)this.significancia)*this.Sp*this.multiplicador);
        if(estaDentroDeLaRegionDeRechazoCasoc()){
            this.conclusion = "A un nivel de significancia del " + this.significancia + " se rechaza la hipotesis nula";
            valTablas = tablaTeStudent(this.gradosLibertad,(float)this.significancia);
            return this.limInf;
        }else
            this.conclusion = "A un nivel de significancia del " + this.significancia + " no existen evidencias para rechazar la hipotesis nula";
        valTablas = tablaTeStudent(this.gradosLibertad,(float)this.significancia);
        return this.limInf;
    }

    public String casoD(){
        this.limInf = diferenciaDeMedias - (tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2))*this.Sp*this.multiplicador);
        this.valTablas = tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2));
        this.limSup = diferenciaDeMedias1 + (tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2))*this.Sp*this.multiplicador);
        String intervalo = "[" + this.limInf +"," + this.limSup+ "]";
        if(estaDentroDeLaRegionDeRechazoCasobd()){
            this.conclusion = "A un nivel de significancia del " + this.significancia + " se rechaza la hipotesis nula";
            valTablas = tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2));
            return intervalo;
        }else
            this.conclusion = "A un nivel de significancia del " + this.significancia + "  no existe evidencia para rechazar la hipotesis nula";
            valTablas = tablaTeStudent(this.gradosLibertad,(float)(this.significancia/2));
            return intervalo;
    }


    public double potenciaPrueba(double nuevaDiferenciaDeMedias,double limite){
        this.estadisticoT = ((limite-nuevaDiferenciaDeMedias)/(this.Sp*this.multiplicador));
        if(estadisticoT<0) estadisticoT = estadisticoT*-1;
        double probabilidad = tablaTstudentPotenciaPrueba(estadisticoT,this.gradosLibertad);
        return probabilidad;
    }



    public boolean estaDentroDeLaRegionDeRechazoCasoa(){
        if(this.diferenciaDePromedios>limSup){
            return true;
        }else return false;
    }

    public boolean estaDentroDeLaRegionDeRechazoCasobd(){
        if(this.diferenciaDePromedios>limSup || this.diferenciaDePromedios<limInf){
            return true;
        }else return false;
    }

    public boolean estaDentroDeLaRegionDeRechazoCasoc(){
        if(this.diferenciaDePromedios<limInf){
            return true;
        }else return false;
    }


}
