package com.example.estadistica;

import com.example.estadistica.CalculoTablas;

public class PruebaHipotesisDVconocida  {

    private double operador;
    private double lim_inf;
    private double lim_sup;
    public String decision;
    public CalculoTablas tab;
    private double valTablas;
    public double estadisticoZeta;
    public double valTablas1,valtTablas2;
    public double estadisticoZeta1,estadisticoZeta2;
    public PruebaHipotesisDVconocida(){
        this.operador = 0.0;
        tab = new CalculoTablas();
    }

    public double getOperador(){
        return this.operador;
    }
    public double getLim_inf(){return this.lim_inf;}
    public double getLim_sup(){return this.lim_sup;}
    public double getValTablas(){return this.valTablas;}

    public String casoa(float significancia,int n, double desviacion,double discriminante, double media){
        CalculoTablas tab1 = new CalculoTablas();
        String conclusion;
        double operador = (desviacion/Math.sqrt(n))*tab1.tablazeta(significancia);
        this.operador = operador;
        valTablas = tab1.tablazeta(significancia);
        double res= media-operador;
        this.lim_inf = tab.redondeoDecimales(res,4);
        if(discriminante<res){
            conclusion = "Con una significancia de" + significancia*100 + "% se rechaza H0";
            this.decision = "es menor";
        }else{
            if(discriminante == res){
                conclusion = "Con una significancia de " + significancia*100 + "% no existe evidencia para rechazar H0";
                this.decision = "es igual";
            }else{
                conclusion = "Con una significancia de " + significancia*100 + "% no existe evidencia para rechazar H0";
                this.decision = "no es menor";
            }

        }

        return conclusion;
    }

    public String Aproxcasoa(float significancia,int n, double desviacion,double discriminante, double media){
        CalculoTablas tab1 = new CalculoTablas();
        String conclusion;
        double operador = (desviacion/Math.sqrt(n))*tab1.tablazeta(significancia);
        this.operador = operador;
        valTablas = tab1.tablazetaAcumuladaPotencia(significancia);
        double res= media-operador;
        this.lim_inf = tab.redondeoDecimales(res,4);
        if(discriminante<res){
            conclusion = "Con una significancia de" + significancia*100 + "% se rechaza H0";
            this.decision = "es menor";
        }else{
            if(discriminante == res){
                conclusion = "Con una significancia de " + significancia*100 + "% no existe evidencia para rechazar H0";
                this.decision = "es igual";
            }else{
                conclusion = "Con una significancia de " + significancia*100 + "% no existe evidencia para rechazar H0";
                this.decision = "no es menor";
            }

        }

        return conclusion;
    }


    public String casob(float significancia,int n, double desviacion,double discriminante, double media){
        CalculoTablas tab2 = new CalculoTablas();
        String conc ="";
        double operador = (desviacion/Math.sqrt(n))*tab2.tablazeta(significancia);
        double result = media+operador;
        valTablas = tab2.tablazeta(significancia);
        this.lim_sup = tab.redondeoDecimales(result,4);
        this.operador = operador;

        if(discriminante>result){
            conc = "Con un nivel de significancia de " + significancia + " se rechaza H0";
            this.decision = "es mayor";
        }else{
            if(discriminante == result){
                conc = "A una significancia del " + tab.redondeoDecimales((significancia*100),4) + "% no hay evidencia suficiente para rechazar H0";
                this.decision ="es igual";
            }else {
                conc = "A una significancia del " + significancia * 100 + "% no hay evidencia suficiente para rechazar H0";
                this.decision = " está dentro del intervalo de no rechazo ";
            }
        }

        return conc;
    }

    public String Aproxcasob(float significancia,int n, double desviacion,double discriminante, double media){
        CalculoTablas tab2 = new CalculoTablas();
        String conc ="";
        double operador = (desviacion/Math.sqrt(n))*tab2.tablazeta(significancia);
        double result = media+operador;
        double signif = 1- significancia;
        signif = (float)tab2.redondeoDecimales(signif,4);
        valTablas = tab2.tablazetaAcumuladaPotencia(signif);
        this.lim_sup = tab.redondeoDecimales(result,4);
        this.operador = operador;

        if(discriminante>result){
            conc = "Con un nivel de significancia de " + significancia + " se rechaza H0";
            this.decision = "es mayor";
        }else{
            if(discriminante == result){
                conc = "A una significancia del " + tab.redondeoDecimales((significancia*100),4) + "% no hay evidencia suficiente para rechazar H0";
                this.decision ="es igual";
            }else {
                conc = "A una significancia del " + significancia * 100 + "% no hay evidencia suficiente para rechazar H0";
                this.decision = " está dentro del intervalo de no rechazo ";
            }
        }

        return conc;
    }


    public String casoc(float significancia,int n, double desviacion,double discriminante, double media){
        CalculoTablas tab3 = new CalculoTablas();
        String conclusion = "";
        double operador1 = (desviacion/Math.sqrt(n))*tab3.tablazeta((float) tab.redondeoDecimales((significancia/2),5));
        double result = media - operador1;
        this.lim_inf = tab.redondeoDecimales(result,4);
        double result1 = media + operador1;
        this.lim_sup = tab.redondeoDecimales(result1,4);
        this.operador=operador1;
        this.valTablas = tab3.tablazeta((float) tab.redondeoDecimales((significancia/2),5));

        if((discriminante<result) || (discriminante>result1)){
            conclusion = "Con un nivel de significancia de " + significancia*100 + " se rechaza H0";
            if(discriminante<result){
                this.decision = "es menor que el límite inferior del intervalo de aceptación";
            }else if(discriminante>result1){
                this.decision=" es mayor que el límite superior del intervalo de aceptación";
            }
        }else{
            conclusion = "A una significancia del " + significancia*100 + "% no hay evidencia suficiente para rechazar H0";
            this.decision ="está dentro del intervalo aceptación";

        }

        return conclusion;
    }


    public String Aproxcasoc(float significancia,int n, double desviacion,double discriminante, double media){
        CalculoTablas tab3 = new CalculoTablas();
        String conclusion = "";
        double operador1 = (desviacion/Math.sqrt(n))*tab3.tablazetaAcumuladaPotencia((float) tab.redondeoDecimales((significancia/2),5));
        double operador2 = (desviacion/Math.sqrt(n))*tab3.tablazetaAcumuladaPotencia((float) tab.redondeoDecimales(((1-(significancia/2))),5));
        double result = media + operador1;
        this.lim_inf = tab.redondeoDecimales(result,4);
        double result1 = media + operador2;
        this.lim_sup = tab.redondeoDecimales(result1,4);
        this.operador=operador1;
        this.valTablas1 = tab3.tablazetaAcumuladaPotencia((float) tab.redondeoDecimales((significancia/2),5));
        this.valtTablas2 = tab3.tablazetaAcumuladaPotencia((float) tab.redondeoDecimales((1-(significancia/2)),5));

        if((discriminante<result) || (discriminante>result1)){
            conclusion = "Con un nivel de significancia de " + significancia*100 + " se rechaza H0";
            if(discriminante<result){
                this.decision = "es menor que el límite inferior del intervalo de aceptación";
            }else if(discriminante>result1){
                this.decision=" es mayor que el límite superior del intervalo de aceptación";
            }
        }else{
            conclusion = "A una significancia del " + significancia*100 + "% no hay evidencia suficiente para rechazar H0";
            this.decision ="está dentro del intervalo aceptación";

        }

        return conclusion;
    }


    public String casod(float significancia,int n, double desviacion,double discriminante, double media0, double media1){
        CalculoTablas tab3 = new CalculoTablas();
        String conclusion = "";
        this.valTablas = tab3.tablazeta(significancia);
        double operador1 = (desviacion/Math.sqrt((double)n))*tab3.tablazeta(significancia);
        double result = media0 - operador1;
        this.lim_inf = result;
        this.lim_inf = tab3.redondeoDecimales(lim_inf,6);
        double result1 = media1 + operador1;
        this.lim_sup = result1;
        this.lim_sup = tab3.redondeoDecimales(lim_sup,6);
        this.operador=operador1;
        tab3.tablazeta(significancia);
        if((discriminante<result) || (discriminante>result1)){
            conclusion = "Con un nivel de significancia de " + significancia*100 + "% se rechaza H0";
            if(discriminante<result){
                this.decision = " es menor que el límite inferior";
            }else if(discriminante>result1){
                this.decision=" es mayor que el límite superior";
            }
        }else{
            conclusion = "A una significancia del " + significancia*100 + "% no hay evidencia suficiente para rechazar H0";
            this.decision =" está dentro del intervalo de no rechazo ";

        }

        return conclusion;
    }


    public String Aproxcasod(float significancia,int n, double desviacion,double discriminante, double media0, double media1){
        CalculoTablas tab3 = new CalculoTablas();
        String conclusion = "";
        this.valTablas = tab3.tablazeta(significancia);
        double operador1 = (desviacion/Math.sqrt((double)n))*tab3.tablazetaAcumuladaPotencia(tab3.redondeoDecimales((significancia/2),4));
        this.valTablas1 = tab3.tablazetaAcumuladaPotencia(tab3.redondeoDecimales((significancia/2),4));
        double operador2 = (desviacion/Math.sqrt((double)n))*tab3.tablazetaAcumuladaPotencia(tab3.redondeoDecimales((1-(significancia/2)),4));
        this.valtTablas2 = tab3.tablazetaAcumuladaPotencia(tab3.redondeoDecimales((1-(significancia/2)),4));
        double result = media0 + operador1;
        this.lim_inf = result;
        this.lim_inf = tab3.redondeoDecimales(lim_inf,6);
        double result1 = media1 + operador2;
        this.lim_sup = result1;
        this.lim_sup = tab3.redondeoDecimales(lim_sup,6);
        this.operador=operador1;
        tab3.tablazeta(significancia);
        if((discriminante<lim_inf) || (discriminante>lim_sup)){
            conclusion = "Con un nivel de significancia de " + significancia*100 + "% se rechaza H0";
            if(discriminante<lim_inf){
                this.decision = " es menor que el límite inferior";
            }else if(discriminante>lim_sup){
                this.decision=" es mayor que el límite superior";
            }
        }else{
            conclusion = "A una significancia del " + significancia*100 + "% no hay evidencia suficiente para rechazar H0";
            this.decision =" está dentro del intervalo de no rechazo ";

        }

        return conclusion;
    }



    public double potenciaPrueba(double limite,double NuevaMiu0,double desv_estandar,int n,String swit){
        double potencia = 0;
         estadisticoZeta = ( (limite-NuevaMiu0)/(desv_estandar/Math.sqrt(n)));
         estadisticoZeta = tab.redondeoDecimales(estadisticoZeta,5);
        if(swit.equals("caso1")){
            if(estadisticoZeta<-3.59){
                potencia = 0;
            }else if(estadisticoZeta>3.59){
                potencia = 1;
            }else {
                potencia = tab.redondeoDecimales(( tab.tablazetaAcumulada(estadisticoZeta)),4);
            }
            }else if(swit.equals("caso2")) {
            if(estadisticoZeta<-3.59){
                potencia = 0;
            }else if(estadisticoZeta>3.59){
                potencia = 1;
            }else {
                potencia = tab.redondeoDecimales((1 - tab.tablazetaAcumulada(estadisticoZeta)),4);
            }
        }
        return potencia;
    }

    public double potenciaPrueba(double limite_inf, double limite_sup,double desv_estandar,double NuevaMiu0, int n){
        double potencia;
        double val1;
        double val2;
        double valres;
         estadisticoZeta1 = tab.redondeoDecimales((limite_inf-NuevaMiu0)/(desv_estandar/Math.sqrt(n)),4);
         estadisticoZeta2 = tab.redondeoDecimales(((limite_sup-NuevaMiu0)/(desv_estandar/Math.sqrt(n)) ),4);
        if(estadisticoZeta1<-3.59&&estadisticoZeta2>3.59){
            potencia = 0;
            valTablas1 = 0;
            valtTablas2 = 1;
        }else if((estadisticoZeta1>3.59 && estadisticoZeta2>3.59)){
            potencia = 1;
            valTablas1 = 1;
            valtTablas2 = 1;
        }else if((estadisticoZeta1<-3.59 && estadisticoZeta2<-3.59)){
            potencia = 1;
            valTablas1 = 0;
            valtTablas2 = 0;
        }else if(estadisticoZeta1<-3.59){
            valTablas1 = 0;
            valtTablas2 = 1-tab.tablazetaAcumulada(estadisticoZeta2);
            valtTablas2 = tab.redondeoDecimales(valtTablas2,4);
            valres = valTablas1 + valtTablas2;
            potencia = tab.redondeoDecimales(valres,4);
        }else if(estadisticoZeta2>3.59){
            valTablas1 = tab.tablazetaAcumulada(estadisticoZeta1);
            valTablas1 = tab.redondeoDecimales(valTablas1,4);
            valtTablas2 = 0;
            valres = valTablas1+valtTablas2;
            potencia = tab.redondeoDecimales(valres,4);
        }else{
            valTablas1 = tab.tablazetaAcumulada(estadisticoZeta1);
            valTablas1 = tab.redondeoDecimales(valTablas1,4);
            valtTablas2 = 1 - tab.tablazetaAcumulada(estadisticoZeta2);
            valtTablas2 = tab.redondeoDecimales(valtTablas2,4);
            potencia = tab.redondeoDecimales((valTablas1 + valtTablas2),5);
        }
        return potencia;
    }

}
