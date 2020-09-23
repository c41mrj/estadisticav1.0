package com.example.estadistica;

public interface PROPORCIONES{

     double casoA();
     String casoB();
     double casoC();
     String casoD();
     double potenciaPrueba(double nuevaProporcionPoblacional, double limite);

     boolean estaDentroDeLaZonaDeRechazoCasoa();
     boolean estaDentroDeLaZonaDeRechazoCasobd();
     boolean estaDentroDeLaZonaDeRechazoCasoc();
}
