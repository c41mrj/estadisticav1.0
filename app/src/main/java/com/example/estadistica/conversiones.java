package com.example.estadistica;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface conversiones {

    public default double convertirStringADouble(String number){
        return redondeoDecimales(((Double.parseDouble(number))),5);
    }

    public default float convertirStringAFloat(String number){
        return redondeoDecimales(((Float.parseFloat(number))),5);
    }

    public default int convertirStringAInt(String number){
        return (Integer.parseInt(number));
    }

    public  default double redondeoDecimales(double numero, int numeroDecimales) {
        BigDecimal redondeado = new BigDecimal(numero)
                .setScale(numeroDecimales, RoundingMode.HALF_EVEN);
        return redondeado.doubleValue();
    }

    public default float redondeoDecimales(float numero, int numeroDecimales){
        BigDecimal redondeado = new BigDecimal(numero)
                .setScale(numeroDecimales, RoundingMode.HALF_EVEN);
        return redondeado.floatValue();
    }

}
