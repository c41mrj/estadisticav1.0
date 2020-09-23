package com.example.estadistica;

public class PHIPDEDESC extends CalculoTablas{

    private double limInf;
    private double limSup;
    private double desvEstandar;
    private int tamMuestra;
    private double miu0;
    private double promedioMuestral;
    private float signif;
    public int gradosLibertad;
    private double valTablaT;
    public double valtTablas1,valTablas2;
    public String decision;
    private double estadisticoT,estadisticoTs;
    public double valEstadisticoT,valEstadisticoTs;
    public String decisionPotencia;

    public PHIPDEDESC(){
        this.limInf = 0;
        this.limSup = 0;
        this.desvEstandar = 0;
        this.tamMuestra = 0;
        this.miu0 = 0;
        this. promedioMuestral = 0;
        this.signif = 0;
        this.gradosLibertad = 0;
        this.valTablaT = 0;
    }

    public PHIPDEDESC(double desvEstandar, int tamMuestra, double miu0, double promedioMuestral, float signif){
        this.desvEstandar = desvEstandar;
        this.tamMuestra = tamMuestra;
        this.miu0 = miu0;
        this.promedioMuestral = promedioMuestral;
        this.signif = signif;
        this.gradosLibertad = tamMuestra-1;
    }

    public double getLimInf(){
        return this.limInf;
    }

    public double getLimSup(){
        return this.limSup;
    }

    public double getValTablaT(){ return this.valTablaT;}


    public double Caso1(){  //menor o igual que
        double limiteSuperior = this.miu0+(((this.desvEstandar)/Math.sqrt(this.tamMuestra))*tablaTeStudent(this.gradosLibertad,this.signif));
        limiteSuperior = redondeoDecimales(limiteSuperior,4);
        this.limSup = limiteSuperior;
        this.limSup = redondeoDecimales(this.limSup,5);
        valTablaT = tablaTeStudent(this.gradosLibertad,this.signif);
        if(discriminante(limiteSuperior)){
            this.decision = "A un "+ this.signif + " de significancia se rechaza la hipotesis nula";
            return limiteSuperior;
        }else
            this.decision = "A un " + this.signif + " de significancia se concluye que no existe evidencia suficiente para rechazar H0";
            return limiteSuperior;
    }

    public String caso2(){
        double limiteInferior = this.miu0-(((this.desvEstandar)/Math.sqrt(this.tamMuestra))*tablaTeStudent(this.gradosLibertad,(float) redondeoDecimales((this.signif/2),5)));
        limiteInferior = redondeoDecimales(limiteInferior,5);
        this.limInf = limiteInferior;
        this.limInf = redondeoDecimales(this.limInf,5);
        double limiteSuperior = this.miu0+(((this.desvEstandar)/Math.sqrt(this.tamMuestra))*tablaTeStudent(this.gradosLibertad,(float) redondeoDecimales((this.signif/2),5)));
        limiteSuperior = redondeoDecimales(limiteSuperior,5);
        this.limSup = limiteSuperior;
        this.limSup = redondeoDecimales(this.limSup,5);
        String intervalo = "["+limiteInferior+","+limiteSuperior+"]";
        valTablaT = tablaTeStudent(this.gradosLibertad,(float) redondeoDecimales((this.signif/2),5));
        if(discriminante2(limiteInferior) || discriminante(limiteSuperior)){
            this.decision = "A un " + this.signif + " de significancia se rechaza la hipotesis nula";
            return intervalo;
        }else
            this.decision = "A un " + this.signif + " de significancia se concluye que no existe evidencia suficiente para rechazar H0";
            return intervalo;
    }

    public double Caso3(){
        double limiteInferior = this.miu0-(((this.desvEstandar)/Math.sqrt(this.tamMuestra))*tablaTeStudent(this.gradosLibertad,this.signif));
        limiteInferior = redondeoDecimales(limiteInferior,6);
        this.limInf =limiteInferior;
        this.limInf = redondeoDecimales(this.limInf,5);
        valTablaT = tablaTeStudent(this.gradosLibertad,this.signif);
        if(discriminante2(limiteInferior)){
            this.decision = "A un "+ this.signif + " de significancia se rechaza la hipotesis nula";
            return limiteInferior;
        }else
            this.decision = "A un " + this.signif + " de significancia se concluye que no existe evidencia suficiente para rechazar H0";
        return limiteInferior;
    }

    public String caso4(double nuevaMiu){
        double limiteInferior = this.miu0-(((this.desvEstandar)/Math.sqrt((double) this.tamMuestra))*tablaTeStudent(this.gradosLibertad,(float) redondeoDecimales((this.signif/2),5)));
        limiteInferior = redondeoDecimales(limiteInferior,6);
        this.limInf = limiteInferior;
        double limiteSuperior = nuevaMiu+(((this.desvEstandar)/Math.sqrt((double) this.tamMuestra))*tablaTeStudent(this.gradosLibertad,(float) redondeoDecimales((this.signif/2),5)));
        limiteSuperior = redondeoDecimales(limiteSuperior,6);
        this.limSup = limiteSuperior;
        String intervalo = "["+limiteInferior+","+limiteSuperior+"]";
        valTablaT = tablaTeStudent(this.gradosLibertad,(float) redondeoDecimales((this.signif/2),5));
        if(discriminante2(limiteInferior) || discriminante(limiteSuperior)){
            this.decision = "A un " + this.signif + " de significancia se rechaza la hipotesis nula";
            return intervalo;
        }else
            this.decision = "A un " + this.signif + " de significancia se concluye que no existe evidencia suficiente para rechazar H0";
        return intervalo;
    }

    public boolean discriminante(double limiteSuperior){
        if(this.promedioMuestral>limiteSuperior){
            return true;
        }else
            return false;
    }

    public boolean discriminante2(double limiteInferior){
        if(this.promedioMuestral<limiteInferior){
            return true;
        } else
            return false;
    }


    public double potPrueba(double nuevaMiu,String decision){
        double probabilidad = 0;
        if(decision.equals("caso1")){
             estadisticoT =  ((this.limSup-nuevaMiu)/(this.desvEstandar/Math.sqrt(this.tamMuestra)));
             estadisticoT = redondeoDecimales(estadisticoT,4);
             valEstadisticoT = estadisticoT;
            if(estadisticoT<0){
                estadisticoT = estadisticoT*-1;
                probabilidad =redondeoDecimales((1-super.tablaTstudentPotenciaPrueba(estadisticoT,this.gradosLibertad)),5);
            }else{
                probabilidad = redondeoDecimales(super.tablaTstudentPotenciaPrueba(estadisticoT,this.gradosLibertad),5);
            }
        }else if(decision.equals("caso3")){
             estadisticoT =  ((this.limInf-nuevaMiu)/(this.desvEstandar/Math.sqrt(this.tamMuestra)));
            estadisticoT = redondeoDecimales(estadisticoT,4);
            valEstadisticoT = estadisticoT;
            if(estadisticoT<0){
                estadisticoT = estadisticoT*-1;
                probabilidad = redondeoDecimales((1-super.tablaTstudentPotenciaPrueba(estadisticoT,this.gradosLibertad)),5);
            }else{
                probabilidad = tablaTstudentPotenciaPrueba(estadisticoT,gradosLibertad);
            }
        }else if(decision.equals("caso2")){
             estadisticoT = ((this.limInf-nuevaMiu)/(this.desvEstandar/Math.sqrt(this.tamMuestra)));
            estadisticoT = redondeoDecimales(estadisticoT,4);
            valEstadisticoT = estadisticoT;
            if(estadisticoT<0){
                estadisticoT = estadisticoT*-1;
                this.valtTablas1 = tablaTstudentPotenciaPrueba(estadisticoT,this.gradosLibertad);
                this.valtTablas1 = redondeoDecimales((1-this.valtTablas1),5);

            }else if(estadisticoT>=0){
                this.valtTablas1 = tablaTstudentPotenciaPrueba(estadisticoT,this.gradosLibertad);
            }
            estadisticoTs = ((this.limSup-nuevaMiu)/(this.desvEstandar/Math.sqrt(this.tamMuestra)));
            estadisticoTs = redondeoDecimales(estadisticoTs,4);
            valEstadisticoTs = estadisticoTs;
            if(estadisticoTs<0){
                estadisticoTs = estadisticoTs*-1;
                this.valTablas2 = tablaTstudentPotenciaPrueba(estadisticoT,this.gradosLibertad);
                this.valTablas2 = redondeoDecimales((1-valTablas2),5);
            }else if(estadisticoTs>=0){
                this.valTablas2 = tablaTstudentPotenciaPrueba(estadisticoT,this.gradosLibertad);
            }
            probabilidad = valtTablas1 + valTablas2;
        }
        return probabilidad;
    }

    public double potPrueba(float nuevaMiu,float nuevaMiu1){
        double estadisticoT = (this.limInf-nuevaMiu)/(this.desvEstandar/Math.sqrt(this.tamMuestra));
        if(estadisticoT<0) estadisticoT = estadisticoT*-1;
        double estadisticoTs = (this.limSup-nuevaMiu1)/(this.desvEstandar/Math.sqrt(this.tamMuestra));
        if(estadisticoTs<0) estadisticoTs = estadisticoTs*-1;
        double probabilidad = super.tablaTstudentPotenciaPrueba((float)estadisticoT,this.gradosLibertad) + (1-super.tablaTstudentPotenciaPrueba((float)estadisticoTs,this.gradosLibertad));
        return probabilidad;
    }

}
