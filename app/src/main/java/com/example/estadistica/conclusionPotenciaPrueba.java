package com.example.estadistica;

public interface conclusionPotenciaPrueba {

    default String getConclusionPotenciaPrueba(double potenciaPrueba){
        String conclusion = "";
        if(potenciaPrueba>= 0 && potenciaPrueba<=0.5){
            conclusion = "La potencia de la prueba es baja";
        }else if(potenciaPrueba>0.5 && potenciaPrueba<= 0.8){
            conclusion = "La potencia de la prueba es aceptable";
        }else if(potenciaPrueba>0.8){
            conclusion = "La potencia de la prueba es buena";
        }
        return conclusion;
    }

}
