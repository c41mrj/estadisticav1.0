package com.example.estadistica;

public class PHIPVARIANZA extends CalculoTablas {

    private double valConfianza;
    private int tamMuestra;
    private double varianzaMuestral,varianzaPoblacional,varianzaPoblacional1;
    public String decision;
    private double limiteSuperior;
    private double limiteInferior;
    private double valTablas,valTablas1;
    public double estadisticoChi;

    public double getValTablas(){
        return this.valTablas;
    }

    public double getValTablas1(){
        return this.valTablas1;
    }

    public double getLimiteInferior(){
        return this.limiteInferior;
    }

    public double getLimiteSuperior(){
        return this.limiteSuperior;
    }

    public PHIPVARIANZA(){
        this.valConfianza = 0;
        this.tamMuestra = 0;
        this.varianzaMuestral=0;
        this.varianzaPoblacional = 0;
        this.estadisticoChi = 0;
        this.varianzaPoblacional1 = 0;
    }

    public PHIPVARIANZA(double significancia,int tamMuestra, double varianzaMuestral,double varianzaPoblacional){
        this.valConfianza = significancia;
        this.tamMuestra = tamMuestra;
        this.varianzaMuestral = varianzaMuestral;
        this.varianzaPoblacional = varianzaPoblacional;
    }

    public PHIPVARIANZA(double signif, int tamMuestra,double varianzaMuestral,double varianzaPoblacional,double varianzaPoblacional1){
        this.valConfianza = signif;
        this.tamMuestra = tamMuestra;
        this.varianzaMuestral = varianzaMuestral;
        this.varianzaPoblacional = varianzaPoblacional;
        this.varianzaPoblacional1 = varianzaPoblacional1;
    }

    public double caso1(){
        limiteSuperior = (this.varianzaPoblacional/(tamMuestra-1))*tablaChi((this.valConfianza),(this.tamMuestra-1));
        limiteSuperior = redondeoDecimales(limiteSuperior,5);
        valTablas = tablaChi((this.valConfianza),(this.tamMuestra-1));
        if(estaDentroDeLaZonaDeRechazoCaso1()){
            decision = "A un " + this.valConfianza + " de significancia se rechaza la hipotesis nula";
        }else {
            decision = "A un " + this.valConfianza + " de significancia no existe evidencia para rechazar la hipotesis nula";
        }
        return limiteSuperior;
    }

    public String caso2(){
        limiteInferior = (this.varianzaPoblacional/(tamMuestra-1))*tablaChi((1-(this.valConfianza/2)),(this.tamMuestra-1));
        limiteInferior = redondeoDecimales(limiteInferior,5);
        valTablas = tablaChi(1-(this.valConfianza/2),(this.tamMuestra-1));
        limiteSuperior = (this.varianzaPoblacional/(tamMuestra-1))*tablaChi((this.valConfianza/2),(this.tamMuestra-1));
        limiteSuperior = redondeoDecimales(limiteSuperior,5);
        valTablas1 = tablaChi((this.valConfianza/2),(this.tamMuestra-1));
        if(estaDentroDeLaZonaDeRechazoCaso2()){
            decision = "A un " + this.valConfianza + " de significancia se rechaza la hipotesis nula";
        }else{
            decision = "A una " + this.valConfianza + " de significancia no existe evidencia para rechazar la hipotesis nula";
        }
        String intervalo = "[" + this.limiteInferior + "," + this.limiteSuperior + "]";
        return intervalo;
    }

    public double caso3(){
        limiteInferior = (this.varianzaPoblacional/(tamMuestra-1))*tablaChi((1-this.valConfianza),(this.tamMuestra-1));
        valTablas = tablaChi(redondeoDecimales((1-this.valConfianza),4),(this.tamMuestra-1));
        if(estaDentroDeLaZonaDeRechazoCaso3()){
            decision = "A un " + this.valConfianza + " de significancia se rechaza la hipotesis nula";
        }else {
            decision = "A una " + this.valConfianza + " de significancia no existe evidencia para rechazar la hipotesis nula";
        }
        return limiteInferior;
    }

    public String caso4(){
        this.valTablas = tablaChi(redondeoDecimales((this.valConfianza/2),4),(this.tamMuestra-1));
        this.valTablas1 = tablaChi(redondeoDecimales((1-(this.valConfianza/2)),4),(this.tamMuestra-1));
        this.limiteInferior = (this.varianzaPoblacional/(this.tamMuestra-1))*valTablas1;
        this.limiteInferior = redondeoDecimales(this.limiteInferior,5);
        this.limiteSuperior = (this.varianzaPoblacional1/(this.tamMuestra-1))*valTablas;
        this.limiteSuperior = redondeoDecimales(this.limiteSuperior,5);
        if(estaDentroDeLaZonaDeRechazoCaso4()){
            decision = "A un " + this.valConfianza + " de significancia se rechaza la hipotesis nula";
        }else {
            decision = "A una " + this.valConfianza + " de significancia no existe evidencia para rechazar la hipotesis nula";
        }
        String intervalo = "[" + this.limiteInferior + "," + this.limiteSuperior + "]";
        return intervalo;
    }

    public double potenciaPrueba(double limite,double nuevaVarPob){
        estadisticoChi = ((this.tamMuestra-1)*limite)/nuevaVarPob;
        estadisticoChi = redondeoDecimales(estadisticoChi,4);
        double potencia = tablaChiPotenciaPrueba(estadisticoChi,(this.tamMuestra-1));
        return potencia;
    }


    public boolean estaDentroDeLaZonaDeRechazoCaso1(){
        if(this.varianzaMuestral>this.limiteSuperior){
            return true;
        }else
            return false;
    }

    public boolean estaDentroDeLaZonaDeRechazoCaso2(){
        if(this.varianzaMuestral<this.limiteInferior || this.varianzaMuestral>this.limiteSuperior){
            return true;
        }else return false;
    }

    public boolean estaDentroDeLaZonaDeRechazoCaso3(){
        if(this.varianzaMuestral<this.limiteInferior){
            return true;
        }else return false;
    }

    public boolean estaDentroDeLaZonaDeRechazoCaso4(){
        if(this.varianzaMuestral<this.limiteInferior || this.varianzaMuestral>this.limiteSuperior){
            return true;
        }else return false;
    }

}
