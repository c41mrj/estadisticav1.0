package com.example.estadistica;


import android.widget.TextView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CalculoTablas {
  ArrayList<String> lineas;
  ArrayList<String> lineasAcum;
  ArrayList<String> lineasTabTstudent;
  ArrayList<String> lineasIzquierdaTabChi;
  ArrayList<String> lineasTabFisher;
  String tabz[][] = new String[32][11];
  String tabzAcumulada[][];
  String tablaTstudent[][];
  double tabFisher0_9[][];
  double tabFisher0_95[][];
  double tabFisher0_975[][];
  double tabFisher0_99[][];

  String tabIzquierdaChi[][];
  String linea_aux[];
  String linea_aux2[];
  private double fila;
  private double columna;
  private double columna1;
  private double valor;
  private double valor1;
  private boolean sw;
  private TextView textView;
  public int pepelepeu;

  public CalculoTablas(){
      this.fila = 0;
      this.columna = 0;
      this.columna1 = 0;
      this.valor = 0;
      this.valor1 = 0;
      this.sw = false;
      llenarLineas();
      llenarLineasTabZAcum();
      llenarLineasTablaT();
      llenarTablasJi();
      llenarLineasTabFisher0_99();
      llenarTablasFisher0_975();
      llenarTablasFisher0_95();
      llenarTablasFisher0_9();
  }

  public  double tablaz(double grados_confianza) {
      double grado_confianza = grados_confianza;
      double result = 0;

      for(int i = 1; i<37;i++){
          this.fila =Double.parseDouble(tabz[i][0]);
          if(i<36){
              for(int j = 1; j<11; j++){
                  this.valor = Double.parseDouble(tabz[i][j]);
                  if(j<10){
                      this.columna = Double.parseDouble(tabz[0][j]);
                      this.columna1 = Double.parseDouble(tabz[0][j+1]);
                      this.valor1 = Double.parseDouble(tabz[i][j+1]);

                      if(grado_confianza == this.valor){
                          result = this.fila+this.columna;
                          break;
                      }else if((valor1<grado_confianza) && (grado_confianza<valor)){
                          result = interpolacion(valor1,grado_confianza,valor,(this.fila+this.columna),(this.fila+this.columna1));
                          break;
                      }
                  }
                  else if(j == 10){
                      this.columna = Double.parseDouble(tabz[0][j]);
                      if(this.valor == grado_confianza){
                          result = this.fila+this.columna;
                          break;
                      }else if(grado_confianza<(Double.parseDouble(tabz[i][j])) && grado_confianza>(Double.parseDouble(tabz[i+1][0]))){
                          result = interpolacion(Double.parseDouble(tabz[i][j]),grado_confianza,Double.parseDouble(tabz[i+1][0]),(Double.parseDouble(tabz[i][0])+Double.parseDouble(tabz[0][j])),(Double.parseDouble(tabz[i+0][0])+Double.parseDouble(tabz[0][1])));
                      }else if(i==36 && j ==10){
                          result = 0;
                          break;
                      }
                  }
              }
          } else if(i == 36){
              for(int j = 1; j<11; j++){
                  this.valor = Double.parseDouble(tabz[i][j]);
                  if(j<10){
                      this.columna = Double.parseDouble(tabz[0][j]);
                      this.columna1 = Double.parseDouble(tabz[0][j+1]);
                      this.valor1 = Double.parseDouble(tabz[i][j+1]);

                      if(grado_confianza == this.valor){
                          result = this.fila+this.columna;
                          break;
                      }else if((valor1<grado_confianza) && (grado_confianza<valor)){
                          result = interpolacion(valor1,grado_confianza,valor,(this.fila+this.columna),(this.fila+this.columna1));
                          break;
                      }
                  }
                  else if(j == 10){
                      this.columna = Double.parseDouble(tabz[0][j]);
                      if(this.valor == grado_confianza){
                          result = this.fila+this.columna;
                          break;
                      }else if(i==36 && j ==10 && this.valor == grado_confianza){
                          result = this.fila+this.columna;
                          break;
                      }
                  }
              }
          }
          }
      return result;
      }

    public  double tablazPotencia(double grados_confianza) {
        double grado_confianza = grados_confianza;
        double result = 0;
        double fila1;
        double suma1;

        for(int i = 1; i<37;i++){
            this.fila =Double.parseDouble(tabz[i][0]);
            if(i<36){
                fila1 = Double.parseDouble(tabz[i+1][0]);
                for(int j = 1; j<11; j++){
                    this.valor = Double.parseDouble(tabz[i][j]);
                    if(j<10){
                        valor1 = Double.parseDouble(tabz[i][j+1]);
                        this.columna = Double.parseDouble(tabz[0][j]);
                        this.columna1 = Double.parseDouble(tabz[0][j+1]);
                        this.valor1 = Double.parseDouble(tabz[i][j+1]);
                        double suma = this.fila+this.columna;
                        if(grado_confianza == suma){
                            result = valor;
                            break;
                        }else if((grado_confianza<(this.fila+this.columna1)) && (grado_confianza>(this.fila+this.columna))){
                            result = interpolacion((this.fila+this.columna),grado_confianza,(this.fila+this.columna1),valor,valor1);
                            break;
                        }
                    }
                    else if(j == 10){
                        this.columna = Double.parseDouble(tabz[0][j]);
                        if(this.valor == grado_confianza){
                            result = this.fila+this.columna;
                            break;
                        }
                    }
                }
            } else if(i == 36){
                for(int j = 1; j<11; j++){
                    this.valor = Double.parseDouble(tabz[i][j]);
                    if(j<10){
                        this.columna = Double.parseDouble(tabz[0][j]);
                        this.columna1 = Double.parseDouble(tabz[0][j+1]);
                        this.valor1 = Double.parseDouble(tabz[i][j+1]);

                        if(grado_confianza == this.valor){
                            result = this.fila+this.columna;
                            break;
                        }else if((grado_confianza<(this.fila+this.columna1)) && (grado_confianza>(this.fila+this.columna))){
                            result = interpolacion((this.fila+this.columna),grado_confianza,(this.fila+this.columna1),valor,valor1);
                            break;
                        }
                    }
                    else if(j == 10){
                        this.columna = Double.parseDouble(tabz[0][j]);
                        if(this.valor == grado_confianza){
                            result = this.fila+this.columna;
                            break;
                        }else if(i==36 && j ==10 && this.valor == grado_confianza){
                            result = this.fila+this.columna;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }


  public double tablazeta(float grado_confianza){
      double result = 0;
      for(int i = 1; i<37;i++){
          this.fila =Double.parseDouble(tabz[i][0]);
          if(i<36){
              for(int j = 1; j<11; j++){
                  this.valor = Double.parseDouble(tabz[i][j]);
                  if(j<10){
                      this.columna = Double.parseDouble(tabz[0][j]);
                      this.columna1 = Double.parseDouble(tabz[0][j+1]);
                      this.valor1 = Double.parseDouble(tabz[i][j+1]);
                      if(grado_confianza == this.valor){
                          result = this.fila+this.columna;
                          break;
                      }else if((grado_confianza<valor)&&(grado_confianza>valor1)){
                          result = interpolacion(valor,grado_confianza,valor1,(this.fila+this.columna),(this.fila+this.columna1));
                          break;
                      }
                  }
                  else if(j == 10){
                      this.columna = Double.parseDouble(tabz[0][j]);
                      if(this.valor == grado_confianza){
                          result = this.fila+this.columna;
                          break;
                      }else if(grado_confianza<(Double.parseDouble(tabz[i][j])) && grado_confianza>(Double.parseDouble(tabz[i+1][0]))){
                          result = interpolacion(Double.parseDouble(tabz[i][j]),grado_confianza,Double.parseDouble(tabz[i+1][0]),(Double.parseDouble(tabz[i][0])+Double.parseDouble(tabz[0][j])),(Double.parseDouble(tabz[i+0][0])+Double.parseDouble(tabz[0][1])));
                      }else if(i==36 && j ==10){
                          result = 0;
                          break;
                      }
                  }
              }
          } else if(i == 36){
              for(int j = 1; j<11; j++){
                  this.valor = Double.parseDouble(tabz[i][j]);
                  if(j<10){
                      this.columna = Double.parseDouble(tabz[0][j]);
                      this.columna1 = Double.parseDouble(tabz[0][j+1]);
                      this.valor1 = Double.parseDouble(tabz[i][j+1]);
                      if(grado_confianza == this.valor){
                          result = this.fila+this.columna;
                          break;
                      }else if((grado_confianza<valor) && (grado_confianza>valor1)){
                          result = interpolacion(valor,grado_confianza,valor1 ,(this.fila+this.columna),(this.fila+this.columna1));
                          break;
                      }
                  }
                  else if(j == 10){
                      this.columna = Double.parseDouble(tabz[0][j]);
                      if(this.valor == grado_confianza){
                          result = this.fila+this.columna;
                          break;
                      }else if(i==36 && j ==10 && this.valor == grado_confianza){
                          result = this.fila+this.columna;
                          break;
                      }
                  }
              }
          }
      }
      return result;
  }

    public double tablazetaAcumulada(double valor){

        double grado_confianza;
        double result = 0;
        double fila;
        double columna;
        boolean breik = false;

        for(int i = 1; i<73; i++){
            if(breik == true) break;
            fila = Double.parseDouble(this.tabzAcumulada[i][0]);
           if(i<72){
               for(int j = 1;  j<11; j++){
                   columna = Double.parseDouble(this.tabzAcumulada[0][j]);
                   if(j<10){
                       double columna1 = Double.parseDouble(this.tabzAcumulada[0][j+1]);
                       if(fila<0){
                           grado_confianza = fila-columna;
                           double grado_confianza1 = fila-columna1;
                           if(grado_confianza == valor){
                               result = Double.parseDouble(this.tabzAcumulada[i][j]);
                               breik = true;
                               break;
                           }else if(valor<grado_confianza && valor>grado_confianza1){
                               result = interpolacion(grado_confianza,valor,grado_confianza1,Double.parseDouble(this.tabzAcumulada[i][j]),Double.parseDouble(this.tabzAcumulada[i][j+1]));
                               breik = true;
                               break;
                           }
                       }else if(fila>=0){
                           grado_confianza = fila+columna;
                           double grado_confianza1 = fila + columna1;
                           if(grado_confianza == valor){
                               result = Double.parseDouble(tabzAcumulada[i][j]);
                               break;
                           }else if(valor>grado_confianza && valor<grado_confianza1){
                               result = interpolacion(grado_confianza,valor,grado_confianza1,Double.parseDouble(this.tabzAcumulada[i][j]),Double.parseDouble(this.tabzAcumulada[i][j+1]));
                               break;
                           }
                       }
                   }else if(j == 10){

                       if(fila<0){
                           grado_confianza = fila-columna;
                           if(valor<grado_confianza && valor<Double.parseDouble(this.tabzAcumulada[i+1][0])){
                               result = interpolacion((fila-columna),valor,Double.parseDouble(this.tabzAcumulada[i+1][0]),Double.parseDouble(this.tabzAcumulada[i][j]),Double.parseDouble(this.tabzAcumulada[i+1][1]));
                               breik = true;
                               break;
                           }else if(valor == grado_confianza){
                               result = Double.parseDouble(this.tabzAcumulada[i][j]);
                               breik = true;
                               break;
                           }
                       }else if(fila>=0) {
                           grado_confianza = fila+columna;
                           if(grado_confianza == valor){
                               result = Double.parseDouble(this.tabzAcumulada[i][j]);
                               break;
                           }else if(valor>grado_confianza&& valor<Double.parseDouble(this.tabzAcumulada[i+1][0])){
                               result = interpolacion(grado_confianza,valor,Double.parseDouble(this.tabzAcumulada[i+1][0]),Double.parseDouble(this.tabzAcumulada[i][j]),Double.parseDouble(this.tabzAcumulada[i+1][1]));
                               break;
                           }
                       }

                   }

               }
           }else if(i == 72){
               for(int j = 1;  j<11; j++){
                   columna = Double.parseDouble(this.tabzAcumulada[0][j]);
                   if(j<10){
                       double columna1 = Double.parseDouble(this.tabzAcumulada[0][j+1]);
                       if(fila<0){
                           grado_confianza = fila-columna;
                           double grado_confianza1 = fila-columna1;
                           if(grado_confianza == valor){
                               result = Double.parseDouble(this.tabzAcumulada[i][j]);
                               breik = true;
                               break;
                           }else if(valor<grado_confianza && valor>grado_confianza1){
                               result = interpolacion(grado_confianza,valor,grado_confianza1,Double.parseDouble(this.tabzAcumulada[i][j]),Double.parseDouble(this.tabzAcumulada[i][j+1]));
                               breik = true;
                               break;
                           }
                       }else if(fila>=0){
                           grado_confianza = fila+columna;
                           double grado_confianza1 = fila + columna1;
                           if(grado_confianza == valor){
                               result = Double.parseDouble(tabzAcumulada[i][j]);
                               break;
                           }else if(valor>grado_confianza && valor<grado_confianza1){
                               result = interpolacion(grado_confianza,valor,grado_confianza1,Double.parseDouble(this.tabzAcumulada[i][j]),Double.parseDouble(this.tabzAcumulada[i][j+1]));
                               break;
                           }
                       }
                   }else if(j == 10){
                       if(fila<0){
                           grado_confianza = fila-columna;
                           if(grado_confianza == valor){
                               result = Double.parseDouble(this.tabzAcumulada[i][j]);
                               breik = true;
                               break;
                           }else if(fila>=0){
                               grado_confianza = fila+columna;
                               if(grado_confianza == valor){
                                   result = Double.parseDouble(this.tabzAcumulada[i][j]);
                                   break;
                               }
                           }
                       }
                   }
               }
           }
       }
        return result;
    }

    public double tablazetaAcumuladaPotencia(double valor){
        double grado_confianza;
        double result = 0;
        double fila=0;
        double columna;
        double parteDecimal;
        double parteEntera;
        int fi;
        boolean breik = false;

        for(int i = 1; i<73; i++){
            if(breik == true) break;
            fila = Double.parseDouble(this.tabzAcumulada[i][0]);
            if(i<72){
                for(int j = 1;  j<11; j++){
                    columna = Double.parseDouble(this.tabzAcumulada[0][j]);
                    if(j<10){
                        double columna1 = Double.parseDouble(this.tabzAcumulada[0][j+1]);
                        if(fila<0){
                            grado_confianza = fila-columna;
                            double grado_confianza1 = fila-columna1;
                            if(Double.parseDouble(this.tabzAcumulada[i][j]) == valor){
                                result  = grado_confianza;
                                breik = true;
                                break;
                            }else if(valor<Double.parseDouble(this.tabzAcumulada[i][j]) && valor>Double.parseDouble(this.tabzAcumulada[i][j+1])){
                               result = interpolacion(Double.parseDouble(this.tabzAcumulada[i][j]),valor,Double.parseDouble(this.tabzAcumulada[i][j+1]),grado_confianza,grado_confianza1);
                               breik = true;
                                break;
                            }
                        }else if(fila>=0){
                            grado_confianza = fila+columna;
                            double grado_confianza1 = fila + columna1;
                            if(Double.parseDouble(this.tabzAcumulada[i][j]) == valor){
                                result = grado_confianza;
                                break;
                            }else if(valor>Double.parseDouble(this.tabzAcumulada[i][j]) && valor<Double.parseDouble(this.tabzAcumulada[i][j+1])){
                                result = interpolacion(Double.parseDouble(this.tabzAcumulada[i][j]),valor,Double.parseDouble(this.tabzAcumulada[i][j+1]),grado_confianza,grado_confianza1);
                                break;
                            }
                        }
                    }else if(j == 10){
                        if(fila<0){
                            grado_confianza = fila-columna;
                            if(Double.parseDouble(this.tabzAcumulada[i][j]) == valor){
                                result = grado_confianza;
                                breik = true;
                                break;
                            }else if(valor<Double.parseDouble(this.tabzAcumulada[i][j])&&valor>Double.parseDouble(this.tabzAcumulada[i+1][1])){
                                double grad1 = ((Double.parseDouble(this.tabzAcumulada[i][0]))-(Double.parseDouble(this.tabzAcumulada[i+1][1])));
                                result = interpolacion(Double.parseDouble(this.tabzAcumulada[i][j]),valor,Double.parseDouble(this.tabzAcumulada[i+1][1]),grado_confianza,grad1);
                                breik = true;
                                break;
                            }
                        }else if(fila>=0){
                            grado_confianza = fila-columna;
                            if(Double.parseDouble(this.tabzAcumulada[i][j]) == valor){
                                result = grado_confianza;
                                break;
                            }else if(valor>Double.parseDouble(this.tabzAcumulada[i][j])&&valor<Double.parseDouble(this.tabzAcumulada[i+1][1])){
                                double grad1 = ((Double.parseDouble(this.tabzAcumulada[i][0]))+(Double.parseDouble(this.tabzAcumulada[i+1][1])));
                                result = interpolacion(Double.parseDouble(this.tabzAcumulada[i][j]),valor,Double.parseDouble(this.tabzAcumulada[i+1][1]),grado_confianza,grad1);
                                break;
                            }
                        }
                    }

                }
            }else if(i == 72){
                for(int j = 1;  j<11; j++){
                    columna = Double.parseDouble(this.tabzAcumulada[0][j]);
                    if(j<10){
                        double columna1 = Double.parseDouble(this.tabzAcumulada[0][j+1]);
                        if(fila<0){
                            grado_confianza = fila-columna;
                            double grado_confianza1 = fila-columna1;
                            if(Double.parseDouble(this.tabzAcumulada[i][j]) == valor){
                                result = grado_confianza;
                                breik = true;
                                break;
                            }else if(valor<Double.parseDouble(this.tabzAcumulada[i][j]) && valor>Double.parseDouble(this.tabzAcumulada[i][j+1])){
                                result = interpolacion(Double.parseDouble(this.tabzAcumulada[i][j]),valor,Double.parseDouble(this.tabzAcumulada[i][j+1]),grado_confianza,grado_confianza1);
                                breik = true;
                                break;
                            }
                        }else if(fila>=0){
                            grado_confianza = fila+columna;
                            double grado_confianza1 = fila + columna1;
                            if(Double.parseDouble(this.tabzAcumulada[i][j]) == valor){
                                result = Double.parseDouble(this.tabzAcumulada[i][j]);
                                break;
                            }else if(valor>Double.parseDouble(this.tabzAcumulada[i][j]) && valor<Double.parseDouble(this.tabzAcumulada[i][j+1])){
                                result = interpolacion(Double.parseDouble(this.tabzAcumulada[i][j]),valor,Double.parseDouble(this.tabzAcumulada[i][j+1]),grado_confianza,grado_confianza1);
                                break;
                            }
                        }
                    }else if(j == 10){
                        if(fila<0){
                            grado_confianza = fila-columna;
                            if(Double.parseDouble(this.tabzAcumulada[i][j]) == valor){
                                result = grado_confianza;
                                breik = true;
                                break;
                            }
                        }else if(fila>=0){
                            grado_confianza = fila+columna;
                            if(Double.parseDouble(this.tabzAcumulada[i][j]) == valor){
                                result = grado_confianza;
                                break;
                            }
                        }
                    }

                }
            }
        }
        return result;
    }

    public double tablaTeStudent(int grados_libertad,float significancia){
      double result=0;
      float signif;
      float signif1;
      double interpolacion1;
      double interpolacion2;
      int liberta;
      int liberta1;

      for(int i = 1; i<38; i++){
           liberta = Integer.parseInt(this.tablaTstudent[i][0]);
          if(i<37){
               liberta1 = Integer.parseInt(this.tablaTstudent[i+1][0]);
              for(int j = 1; j<46;j++){
                  signif = Float.parseFloat(this.tablaTstudent[0][j]);
                  if(j<45){
                      signif1 = Float.parseFloat(this.tablaTstudent[0][j + 1]);
                      if ((liberta == grados_libertad) && (signif == significancia)) {
                          result = Double.parseDouble(this.tablaTstudent[i][j]);
                          break;
                      } else if ((liberta == grados_libertad) && (significancia > signif) && (significancia < signif1)) {
                          result = interpolacion(signif, significancia, signif1, Double.parseDouble(this.tablaTstudent[i][j]), Double.parseDouble(this.tablaTstudent[i][j + 1]));
                          break;
                      } else if ((grados_libertad > liberta) && (grados_libertad < liberta1) && (significancia == signif)) {
                          result = interpolacion(liberta, grados_libertad, liberta1, Double.parseDouble(this.tablaTstudent[i][j]), Double.parseDouble(this.tablaTstudent[i + 1][j]));
                          break;
                      } else if (((grados_libertad > liberta) && (grados_libertad < liberta1)) && ((significancia > signif) && (significancia < signif1))) {
                          interpolacion1 = interpolacion(signif, significancia, signif1, Double.parseDouble(this.tablaTstudent[i][j]), Double.parseDouble(this.tablaTstudent[i][j + 1]));
                          interpolacion2 = interpolacion(signif, significancia, signif1, Double.parseDouble(this.tablaTstudent[i + 1][j]), Double.parseDouble(this.tablaTstudent[i + 1][j + 1]));
                          result = interpolacion(liberta, grados_libertad, liberta1, interpolacion1, interpolacion2);
                          break;
                      }
                  }else if(j==45){
                      if ((liberta == grados_libertad) && (signif == significancia)) {
                          result = Double.parseDouble(this.tablaTstudent[i][j]);
                          break;
                      }else if ((grados_libertad > liberta) && (grados_libertad < liberta1) && (significancia == signif)) {
                          result = interpolacion(liberta, grados_libertad, liberta1, Double.parseDouble(this.tablaTstudent[i][j]), Double.parseDouble(this.tablaTstudent[i + 1][j]));
                          break;
                      }
                  }
              }
          }else if(i==37){
              for(int j = 1; j<46;j++){
                  signif = Float.parseFloat(this.tablaTstudent[37][j]);
                  if(j<45){
                      signif1 = Float.parseFloat(this.tablaTstudent[37][j+1]);
                      if ((liberta == grados_libertad) && (signif == significancia)) {
                          result = Double.parseDouble(this.tablaTstudent[i][j]);
                          break;
                      }else if ((liberta == grados_libertad) && ((significancia < signif) && (significancia > signif1))) {
                          result = interpolacion(signif, significancia, signif1, Double.parseDouble(this.tablaTstudent[i][j]), Double.parseDouble(this.tablaTstudent[i][j + 1]));
                          break;
                      }
                  }else if(j==45){
                      if ((liberta == grados_libertad) && (signif == significancia)) {
                          result = Double.parseDouble(this.tablaTstudent[i][j]);
                          break;
                      }
                  }
              }
          }
      }
      return result;
    }





    public double tablaTstudentPotenciaPrueba(double estadisticoTe,int gradosLibertad){
      double aux;
      double aux2;
      double estadisticoT = estadisticoTe;
      double valorEnTablas = 0;
      double guardaValorgradoS=0;
      double guardaValorGradoS1=0;

      if(estadisticoT<0){
          estadisticoT = estadisticoT*-1;
      }
      for(int i = 1; i<38;i++){
          int gradoC = Integer.parseInt(this.tablaTstudent[i][0]);
          if(i <37){
              int gradoC1 = Integer.parseInt(this.tablaTstudent[i+1][0]);
              if(gradoC == gradosLibertad){
                  for(int j =1; j<46;j++){
                      double guardaValor = Double.parseDouble(this.tablaTstudent[i][j]);
                      guardaValorgradoS =Double.parseDouble(this.tablaTstudent[0][j]) ;
                      if(j<45){
                          guardaValorGradoS1 = Double.parseDouble(this.tablaTstudent[0][j+1]);
                          double guardaValor1 = Double.parseDouble(this.tablaTstudent[i][j+1]);
                          if(guardaValor == estadisticoT){
                              valorEnTablas = guardaValorgradoS;
                              break;
                          }else if(estadisticoT<guardaValor && estadisticoT>guardaValor1){
                              valorEnTablas = interpolacion(guardaValor,estadisticoT,guardaValor1,guardaValorgradoS,guardaValorGradoS1);
                              break;
                          }
                      }else if(j==45){
                          if(guardaValor == estadisticoT){
                              valorEnTablas = guardaValorgradoS;
                              break;
                          }
                      }
                  }
              }else if(gradosLibertad>gradoC && gradosLibertad<gradoC1){
                  for(int j = 1; j<46; j++){
                      aux = interpolacion(gradoC,gradosLibertad,gradoC1,Double.parseDouble(this.tablaTstudent[i][j]),Double.parseDouble(this.tablaTstudent[i+1][j]));
                      if(j<45){
                          aux2 = interpolacion(gradoC,gradosLibertad,gradoC1,Double.parseDouble(this.tablaTstudent[i][j+1]),Double.parseDouble(this.tablaTstudent[i+1][j+1]));
                          if(estadisticoT<aux &&estadisticoT>aux2){
                              valorEnTablas = interpolacion(aux,estadisticoT,aux2,Double.parseDouble(this.tablaTstudent[0][j]),Double.parseDouble(this.tablaTstudent[0][j+1]));
                              break;
                          }else if(estadisticoT == aux){
                              valorEnTablas=Double.parseDouble(this.tablaTstudent[0][j]);
                              break;
                          }
                      }else if(j==45){
                          if(estadisticoT == aux)
                          valorEnTablas = Double.parseDouble(this.tablaTstudent[0][j]);
                      }
                  }
              }
          }else if(i==37){
              if(gradoC == gradosLibertad){
                  for(int j =1; j<46;j++){
                      double guardaValor = Double.parseDouble(this.tablaTstudent[i][j]);
                      guardaValorgradoS =Double.parseDouble(this.tablaTstudent[0][j]) ;
                      if(j<45){
                          guardaValorGradoS1 = Double.parseDouble(this.tablaTstudent[0][j+1]);
                          double guardaValor1 = Double.parseDouble(this.tablaTstudent[i][j+1]);
                          if(guardaValor == estadisticoT){
                              valorEnTablas = guardaValorgradoS;
                              break;
                          }else if(guardaValor>estadisticoT && estadisticoT>guardaValor1){
                              valorEnTablas = interpolacion(guardaValor,estadisticoT,guardaValor1,guardaValorgradoS,guardaValorGradoS1);
                              break;
                          }
                      }else if(j==45){
                          if(guardaValor == estadisticoT){
                              valorEnTablas = guardaValorgradoS;
                              break;
                          }
                      }
                  }
              }
          }
      }
      return valorEnTablas;
    }


    public double tablaChi(double significancia, int gradosLibertad){
      double valTabla = 0;
      for(int i = 1; i<40; i++){
          if(i<39){
              if(Integer.parseInt(this.tabIzquierdaChi[i][0]) == gradosLibertad){
                  for(int j = 1; j<72;j++){
                      if(j<71){
                          if(Double.parseDouble(this.tabIzquierdaChi[0][j]) == significancia){
                              valTabla = Double.parseDouble(this.tabIzquierdaChi[i][j]);
                              break;
                          }else if(significancia>Double.parseDouble(this.tabIzquierdaChi[0][j]) && significancia<Double.parseDouble(this.tabIzquierdaChi[0][j+1])){
                              valTabla = interpolacion(Double.parseDouble(this.tabIzquierdaChi[0][j]),significancia,Double.parseDouble(this.tabIzquierdaChi[0][j+1]),Double.parseDouble(this.tabIzquierdaChi[i][j]),Double.parseDouble(this.tabIzquierdaChi[i][j+1]));
                              break;
                          }
                      }else if(j==71){
                          if(Double.parseDouble(this.tabIzquierdaChi[0][j]) == significancia){
                              valTabla = Double.parseDouble(this.tabIzquierdaChi[i][j]);
                              break;
                          }
                      }
                  }
              }else if(gradosLibertad>Integer.parseInt(this.tabIzquierdaChi[i][0]) && gradosLibertad<Integer.parseInt(this.tabIzquierdaChi[i+1][0])){
                  for(int j = 1; j<72; j++){
                      if(j<71){
                          if(Double.parseDouble(this.tabIzquierdaChi[0][j]) == significancia){
                              valTabla = interpolacion(Integer.parseInt(this.tabIzquierdaChi[i][0]),gradosLibertad,Integer.parseInt(this.tabIzquierdaChi[i+1][0]),Double.parseDouble(this.tabIzquierdaChi[i][j]),Double.parseDouble(this.tabIzquierdaChi[i+1][j]));
                              break;
                          }else if(significancia>Double.parseDouble(this.tabIzquierdaChi[0][j]) && significancia<Double.parseDouble(this.tabIzquierdaChi[0][j+1])){
                              double interpolacion1 = interpolacion(Double.parseDouble(this.tabIzquierdaChi[0][j]),significancia,Double.parseDouble(this.tabIzquierdaChi[0][j+1]),Double.parseDouble(this.tabIzquierdaChi[i][j]),Double.parseDouble(this.tabIzquierdaChi[i][j+1]));
                              double interpolacion2 = interpolacion(Double.parseDouble(this.tabIzquierdaChi[0][j]),significancia,Double.parseDouble(this.tabIzquierdaChi[0][j+1]),Double.parseDouble(this.tabIzquierdaChi[i+1][j]),Double.parseDouble(this.tabIzquierdaChi[i+1][j+1]));
                              valTabla = interpolacion(Integer.parseInt(this.tabIzquierdaChi[i][0]),gradosLibertad,Integer.parseInt(this.tabIzquierdaChi[i+1][0]),interpolacion1,interpolacion2);
                              break;
                          }
                      }else if(j == 71){
                          if(Double.parseDouble(this.tabIzquierdaChi[0][j]) == significancia){
                              valTabla = interpolacion(Integer.parseInt(this.tabIzquierdaChi[i][0]),gradosLibertad,Integer.parseInt(this.tabIzquierdaChi[i+1][0]),Double.parseDouble(this.tabIzquierdaChi[i][j]),Double.parseDouble(this.tabIzquierdaChi[i+1][j]));
                              break;
                          }
                      }
                  }
              }
          }else if(i==39){
              if(gradosLibertad == Integer.parseInt(this.tabIzquierdaChi[i][0])){
                  for(int j = 1; j<72;j++){
                      if(j<71){
                          if(Double.parseDouble(this.tabIzquierdaChi[0][j]) == significancia){
                              valTabla = Double.parseDouble(this.tabIzquierdaChi[i][j]);
                              break;
                          }else if(significancia>Double.parseDouble(this.tabIzquierdaChi[0][j]) && significancia<Double.parseDouble(this.tabIzquierdaChi[0][j+1])) {
                              valTabla = interpolacion(Double.parseDouble(this.tabIzquierdaChi[0][j]), significancia, Double.parseDouble(this.tabIzquierdaChi[0][j + 1]), Double.parseDouble(this.tabIzquierdaChi[i][j]), Double.parseDouble(this.tabIzquierdaChi[i][j + 1]));
                              break;
                          }
                      }else if(j==71){
                          if(Double.parseDouble(this.tabIzquierdaChi[0][j]) == significancia){
                              valTabla = Double.parseDouble(this.tabIzquierdaChi[i][j]);
                              break;
                          }
                      }
                  }
              }
          }
      }
      return valTabla;
    }


    public double tablaChiPotenciaPrueba(double estadisticoChi, int grados_libertad){
      double result = 0;
      int gradosLib;
      int gradosLib1;
      double prob,prob1;
      for(int i = 1; i<40; i++){
          gradosLib = Integer.parseInt(this.tabIzquierdaChi[i][0]);
          if(i<39){
          gradosLib1 = Integer.parseInt(this.tabIzquierdaChi[i+1][0]);
          if(gradosLib == grados_libertad){
              for(int j = 1; j<72; j++){
                  prob = Double.parseDouble(this.tabIzquierdaChi[0][j]);
                  if(j<71){
                      prob1 = Double.parseDouble(this.tabIzquierdaChi[0][j+1]);
                      if(estadisticoChi == Double.parseDouble(this.tabIzquierdaChi[i][j])){
                          result = prob;
                          break;
                      }else if(estadisticoChi<Double.parseDouble(this.tabIzquierdaChi[i][j]) && estadisticoChi>Double.parseDouble(this.tabIzquierdaChi[i][j+1])){
                          result = interpolacion(Double.parseDouble(this.tabIzquierdaChi[i][j]),estadisticoChi,Double.parseDouble(this.tabIzquierdaChi[i][j+1]),prob,prob1);
                          break;
                      }
                  }else if(j == 71){
                      if(estadisticoChi == Double.parseDouble(this.tabIzquierdaChi[i][j])){
                          result = prob;
                          break;
                      }else if(estadisticoChi<Double.parseDouble(this.tabIzquierdaChi[i][j])){
                          result = 1;
                          break;
                      }
                  }
              }
          }else if(grados_libertad>Integer.parseInt(this.tabIzquierdaChi[i][0])&& grados_libertad<Integer.parseInt(this.tabIzquierdaChi[i+1][0])){
              for(int j = 1; j<72;j++){
                  double interpolacion = interpolacion(Integer.parseInt(this.tabIzquierdaChi[i][0]),grados_libertad,Integer.parseInt(this.tabIzquierdaChi[i+1][0]),Double.parseDouble(this.tabIzquierdaChi[i][j]),Double.parseDouble(this.tabIzquierdaChi[i+1][j]));
                  prob = Double.parseDouble(this.tabIzquierdaChi[0][j]);
                  if(j<71){
                      double interpolacion1 = interpolacion(Integer.parseInt(this.tabIzquierdaChi[i][0]),grados_libertad,Integer.parseInt(this.tabIzquierdaChi[i+1][0]),Double.parseDouble(this.tabIzquierdaChi[i][j+1]),Double.parseDouble(this.tabIzquierdaChi[i+1][j+1]));
                      prob1 = Double.parseDouble(this.tabIzquierdaChi[0][j+1]);
                      if(estadisticoChi == interpolacion){
                          result = prob;
                          break;
                      }else if(estadisticoChi<interpolacion && estadisticoChi>interpolacion1){
                          result = interpolacion(interpolacion,estadisticoChi,interpolacion1,prob,prob1);
                          break;
                      }
                  }else if(j == 71){
                      if(estadisticoChi == Double.parseDouble(this.tabIzquierdaChi[i][j])){
                          result = prob;
                          break;
                      }else if(estadisticoChi<Double.parseDouble(this.tabIzquierdaChi[i][j])){
                          result = 1;
                          break;
                      }
                  }
              }
          }
          }else if(i == 39){
              if(grados_libertad == Integer.parseInt(this.tabIzquierdaChi[i][0])){
                  for(int j = 1; j<72; j++){
                      prob = Double.parseDouble(this.tabIzquierdaChi[0][j]);
                      if(j<71){
                          prob1 = Double.parseDouble(this.tabIzquierdaChi[0][j+1]);
                          if(estadisticoChi == Double.parseDouble(this.tabIzquierdaChi[i][j])){
                              result = prob;
                              break;
                          }else if(estadisticoChi<Double.parseDouble(this.tabIzquierdaChi[i][j]) && estadisticoChi>Double.parseDouble(this.tabIzquierdaChi[i][j+1])){
                              result = interpolacion(Double.parseDouble(this.tabIzquierdaChi[i][j]),estadisticoChi,Double.parseDouble(this.tabIzquierdaChi[i][j+1]),prob,prob1);
                              break;
                          }
                      }else if(j == 71){
                          if(estadisticoChi == Double.parseDouble(this.tabIzquierdaChi[i][j])){
                              result = prob;
                              break;
                          }else if(estadisticoChi<Double.parseDouble(this.tabIzquierdaChi[i][j])){
                              result = 1;
                              break;
                          }
                      }
                  }
              }
          }
      }
      return result;
    }

    public double tablaFisher(int gradosLib1, int gradosLib2, double significancia){
      double valtablas = 0;
      double y0 = 0,y1 = 0;
      if(significancia == 0.9){
          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_9[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_9[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_9[i][j];
                                  break;
                              }else if(this.tabFisher0_9[i][0]<gradosLib2 && this.tabFisher0_9[i+1][0]>gradosLib2){
                                  valtablas = interpolacion(this.tabFisher0_9[i][0],gradosLib1,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j],this.tabFisher0_9[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_9[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_9[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_9[0][j] && gradosLib1<this.tabFisher0_9[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_9[i][0] == gradosLib2){
                              valtablas = interpolacion(this.tabFisher0_9[0][j],gradosLib1,this.tabFisher0_9[0][j+1],this.tabFisher0_9[i][j],this.tabFisher0_9[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_9[i][0] && gradosLib2<this.tabFisher0_9[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_9[i][0],gradosLib2,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j],this.tabFisher0_9[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_9[i][0],gradosLib2,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j+1],this.tabFisher0_9[i+1][j+1]);
                              valtablas = interpolacion(this.tabFisher0_9[0][j],gradosLib1,this.tabFisher0_9[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_9[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_9[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_9[i][j];
                                  break;
                              }else if(this.tabFisher0_9[i][0]<gradosLib2 && this.tabFisher0_9[i+1][0]>gradosLib2){
                                  valtablas = interpolacion(this.tabFisher0_9[i][0],gradosLib1,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j],this.tabFisher0_9[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_9[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_9[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }
      }else if(significancia> 0.9 && significancia<0.95){
          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_9[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_9[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_9[i][j];
                                  break;
                              }else if(this.tabFisher0_9[i][0]<gradosLib2 && this.tabFisher0_9[i+1][0]>gradosLib2){
                                  y0 = interpolacion(this.tabFisher0_9[i][0],gradosLib1,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j],this.tabFisher0_9[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_9[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_9[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_9[0][j] && gradosLib1<this.tabFisher0_9[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_9[i][0] == gradosLib2){
                              y0 = interpolacion(this.tabFisher0_9[0][j],gradosLib1,this.tabFisher0_9[0][j+1],this.tabFisher0_9[i][j],this.tabFisher0_9[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_9[i][0] && gradosLib2<this.tabFisher0_9[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_9[i][0],gradosLib2,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j],this.tabFisher0_9[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_9[i][0],gradosLib2,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j+1],this.tabFisher0_9[i+1][j+1]);
                              y0 = interpolacion(this.tabFisher0_9[0][j],gradosLib1,this.tabFisher0_9[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_9[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_9[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_9[i][j];
                                  break;
                              }else if(this.tabFisher0_9[i][0]<gradosLib2 && this.tabFisher0_9[i+1][0]>gradosLib2){
                                  y0 = interpolacion(this.tabFisher0_9[i][0],gradosLib1,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j],this.tabFisher0_9[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_9[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_9[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }


          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_95[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_95[i][j];
                                  break;
                              }else if(this.tabFisher0_95[i][0]<gradosLib2 && this.tabFisher0_95[i+1][0]>gradosLib2){
                                  y1 = interpolacion(this.tabFisher0_95[i][0],gradosLib1,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j],this.tabFisher0_95[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_95[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_95[0][j] && gradosLib1<this.tabFisher0_95[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_95[i][0] == gradosLib2){
                              y1 = interpolacion(this.tabFisher0_95[0][j],gradosLib1,this.tabFisher0_95[0][j+1],this.tabFisher0_95[i][j],this.tabFisher0_95[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_95[i][0] && gradosLib2<this.tabFisher0_95[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_95[i][0],gradosLib2,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j],this.tabFisher0_95[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_95[i][0],gradosLib2,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j+1],this.tabFisher0_95[i+1][j+1]);
                              y1 = interpolacion(this.tabFisher0_95[0][j],gradosLib1,this.tabFisher0_95[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_95[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_95[i][j];
                                  break;
                              }else if(this.tabFisher0_95[i][0]<gradosLib2 && this.tabFisher0_95[i+1][0]>gradosLib2){
                                  y1 = interpolacion(this.tabFisher0_95[i][0],gradosLib1,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j],this.tabFisher0_95[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_95[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }

          valtablas = interpolacion(0.9,significancia,0.95,y0,y1);


      }else  if(significancia == 0.95){
          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_95[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_95[i][j];
                                  break;
                              }else if(this.tabFisher0_95[i][0]<gradosLib2 && this.tabFisher0_95[i+1][0]>gradosLib2){
                                  valtablas = interpolacion(this.tabFisher0_95[i][0],gradosLib1,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j],this.tabFisher0_95[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_95[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_95[0][j] && gradosLib1<this.tabFisher0_95[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_95[i][0] == gradosLib2){
                              valtablas = interpolacion(this.tabFisher0_95[0][j],gradosLib1,this.tabFisher0_95[0][j+1],this.tabFisher0_95[i][j],this.tabFisher0_95[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_95[i][0] && gradosLib2<this.tabFisher0_95[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_95[i][0],gradosLib2,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j],this.tabFisher0_95[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_95[i][0],gradosLib2,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j+1],this.tabFisher0_95[i+1][j+1]);
                              valtablas = interpolacion(this.tabFisher0_95[0][j],gradosLib1,this.tabFisher0_95[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_95[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_95[i][j];
                                  break;
                              }else if(this.tabFisher0_95[i][0]<gradosLib2 && this.tabFisher0_95[i+1][0]>gradosLib2){
                                  valtablas = interpolacion(this.tabFisher0_95[i][0],gradosLib1,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j],this.tabFisher0_95[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_95[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }
      }else if(significancia> 0.95 && significancia<0.975){
          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_95[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_95[i][j];
                                  break;
                              }else if(this.tabFisher0_95[i][0]<gradosLib2 && this.tabFisher0_95[i+1][0]>gradosLib2){
                                  y0 = interpolacion(this.tabFisher0_95[i][0],gradosLib1,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j],this.tabFisher0_95[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_95[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_95[0][j] && gradosLib1<this.tabFisher0_95[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_95[i][0] == gradosLib2){
                              y0 = interpolacion(this.tabFisher0_95[0][j],gradosLib1,this.tabFisher0_95[0][j+1],this.tabFisher0_95[i][j],this.tabFisher0_95[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_95[i][0] && gradosLib2<this.tabFisher0_95[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_95[i][0],gradosLib2,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j],this.tabFisher0_95[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_95[i][0],gradosLib2,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j+1],this.tabFisher0_95[i+1][j+1]);
                              y0 = interpolacion(this.tabFisher0_95[0][j],gradosLib1,this.tabFisher0_95[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_95[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_95[i][j];
                                  break;
                              }else if(this.tabFisher0_95[i][0]<gradosLib2 && this.tabFisher0_95[i+1][0]>gradosLib2){
                                  y0 = interpolacion(this.tabFisher0_95[i][0],gradosLib1,this.tabFisher0_95[i+1][0],this.tabFisher0_95[i][j],this.tabFisher0_95[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_95[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_95[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }


          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_975[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_975[i][j];
                                  break;
                              }else if(this.tabFisher0_975[i][0]<gradosLib2 && this.tabFisher0_975[i+1][0]>gradosLib2){
                                  y1 = interpolacion(this.tabFisher0_975[i][0],gradosLib1,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j],this.tabFisher0_975[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_975[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_975[0][j] && gradosLib1<this.tabFisher0_975[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_975[i][0] == gradosLib2){
                              y1 = interpolacion(this.tabFisher0_975[0][j],gradosLib1,this.tabFisher0_975[0][j+1],this.tabFisher0_975[i][j],this.tabFisher0_975[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_975[i][0] && gradosLib2<this.tabFisher0_975[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_975[i][0],gradosLib2,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j],this.tabFisher0_975[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_975[i][0],gradosLib2,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j+1],this.tabFisher0_975[i+1][j+1]);
                              y1 = interpolacion(this.tabFisher0_975[0][j],gradosLib1,this.tabFisher0_975[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_975[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_975[i][j];
                                  break;
                              }else if(this.tabFisher0_975[i][0]<gradosLib2 && this.tabFisher0_975[i+1][0]>gradosLib2){
                                  y1 = interpolacion(this.tabFisher0_975[i][0],gradosLib1,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j],this.tabFisher0_975[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_975[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }

          valtablas = interpolacion(0.95,significancia,0.975,y0,y1);


      }else  if(significancia == 0.975){
          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_975[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_975[i][j];
                                  break;
                              }else if(this.tabFisher0_975[i][0]<gradosLib2 && this.tabFisher0_975[i+1][0]>gradosLib2){
                                  valtablas = interpolacion(this.tabFisher0_975[i][0],gradosLib1,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j],this.tabFisher0_975[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_975[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_975[0][j] && gradosLib1<this.tabFisher0_975[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_975[i][0] == gradosLib2){
                              valtablas = interpolacion(this.tabFisher0_975[0][j],gradosLib1,this.tabFisher0_975[0][j+1],this.tabFisher0_975[i][j],this.tabFisher0_975[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_975[i][0] && gradosLib2<this.tabFisher0_975[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_975[i][0],gradosLib2,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j],this.tabFisher0_975[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_975[i][0],gradosLib2,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j+1],this.tabFisher0_975[i+1][j+1]);
                              valtablas = interpolacion(this.tabFisher0_975[0][j],gradosLib1,this.tabFisher0_975[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_975[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_975[i][j];
                                  break;
                              }else if(this.tabFisher0_975[i][0]<gradosLib2 && this.tabFisher0_975[i+1][0]>gradosLib2){
                                  valtablas = interpolacion(this.tabFisher0_975[i][0],gradosLib1,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j],this.tabFisher0_975[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_975[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }
      }else if(significancia> 0.975 && significancia<0.99){
          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_975[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_975[i][j];
                                  break;
                              }else if(this.tabFisher0_975[i][0]<gradosLib2 && this.tabFisher0_975[i+1][0]>gradosLib2){
                                  y0 = interpolacion(this.tabFisher0_975[i][0],gradosLib1,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j],this.tabFisher0_975[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_975[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_975[0][j] && gradosLib1<this.tabFisher0_975[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_975[i][0] == gradosLib2){
                              y0 = interpolacion(this.tabFisher0_975[0][j],gradosLib1,this.tabFisher0_975[0][j+1],this.tabFisher0_975[i][j],this.tabFisher0_975[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_975[i][0] && gradosLib2<this.tabFisher0_975[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_975[i][0],gradosLib2,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j],this.tabFisher0_975[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_975[i][0],gradosLib2,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j+1],this.tabFisher0_975[i+1][j+1]);
                              y0 = interpolacion(this.tabFisher0_975[0][j],gradosLib1,this.tabFisher0_975[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_975[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_975[i][j];
                                  break;
                              }else if(this.tabFisher0_975[i][0]<gradosLib2 && this.tabFisher0_975[i+1][0]>gradosLib2){
                                  y0 = interpolacion(this.tabFisher0_975[i][0],gradosLib1,this.tabFisher0_975[i+1][0],this.tabFisher0_975[i][j],this.tabFisher0_975[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_975[i][0] == gradosLib2){
                                  y0 = this.tabFisher0_975[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }


          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_99[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_99[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_99[i][j];
                                  break;
                              }else if(this.tabFisher0_99[i][0]<gradosLib2 && this.tabFisher0_99[i+1][0]>gradosLib2){
                                  y1 = interpolacion(this.tabFisher0_99[i][0],gradosLib1,this.tabFisher0_99[i+1][0],this.tabFisher0_99[i][j],this.tabFisher0_99[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_99[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_99[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_99[0][j] && gradosLib1<this.tabFisher0_99[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_99[i][0] == gradosLib2){
                              y1 = interpolacion(this.tabFisher0_99[0][j],gradosLib1,this.tabFisher0_99[0][j+1],this.tabFisher0_99[i][j],this.tabFisher0_99[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_99[i][0] && gradosLib2<this.tabFisher0_99[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_99[i][0],gradosLib2,this.tabFisher0_99[i+1][0],this.tabFisher0_99[i][j],this.tabFisher0_99[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_99[i][0],gradosLib2,this.tabFisher0_99[i+1][0],this.tabFisher0_99[i][j+1],this.tabFisher0_99[i+1][j+1]);
                              y1 = interpolacion(this.tabFisher0_99[0][j],gradosLib1,this.tabFisher0_99[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_99[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_99[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_99[i][j];
                                  break;
                              }else if(this.tabFisher0_99[i][0]<gradosLib2 && this.tabFisher0_99[i+1][0]>gradosLib2){
                                  y1 = interpolacion(this.tabFisher0_99[i][0],gradosLib1,this.tabFisher0_99[i+1][0],this.tabFisher0_99[i][j],this.tabFisher0_99[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_99[i][0] == gradosLib2){
                                  y1 = this.tabFisher0_99[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }

          valtablas = interpolacion(0.975,significancia,0.99,y0,y1);


      } else  if(significancia == 0.99){
          for(int j =1; j<41; j++){
              if(j<40){
                  if(this.tabFisher0_99[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_99[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_99[i][j];
                                  break;
                              }else if(this.tabFisher0_99[i][0]<gradosLib2 && this.tabFisher0_99[i+1][0]>gradosLib2){
                                  valtablas = interpolacion(this.tabFisher0_99[i][0],gradosLib1,this.tabFisher0_99[i+1][0],this.tabFisher0_99[i][j],this.tabFisher0_99[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_99[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_99[i][j];
                                  break;
                              }
                          }
                      }
                  }else if(gradosLib1>this.tabFisher0_99[0][j] && gradosLib1<this.tabFisher0_99[0][j+1]){
                      for(int i=1; i<41;i++){
                          if(this.tabFisher0_99[i][0] == gradosLib2){
                              valtablas = interpolacion(this.tabFisher0_99[0][j],gradosLib1,this.tabFisher0_99[0][j+1],this.tabFisher0_99[i][j],this.tabFisher0_99[i][j+1]);
                              break;
                          }else if(gradosLib2>this.tabFisher0_99[i][0] && gradosLib2<this.tabFisher0_99[i+1][0]){
                              double interpolacion1 = interpolacion(this.tabFisher0_99[i][0],gradosLib2,this.tabFisher0_99[i+1][0],this.tabFisher0_99[i][j],this.tabFisher0_99[i+1][j]);
                              double interpolacion2 = interpolacion(this.tabFisher0_99[i][0],gradosLib2,this.tabFisher0_99[i+1][0],this.tabFisher0_99[i][j+1],this.tabFisher0_99[i+1][j+1]);
                              valtablas = interpolacion(this.tabFisher0_99[0][j],gradosLib1,this.tabFisher0_99[0][j+1],interpolacion1,interpolacion2);
                              break;
                          }
                      }
                  }

              }else if(j == 40){
                  if(this.tabFisher0_99[0][j] ==gradosLib1){
                      for(int i = 1; i<41;i++){
                          if(i<40){
                              if(this.tabFisher0_99[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_99[i][j];
                                  break;
                              }else if(this.tabFisher0_99[i][0]<gradosLib2 && this.tabFisher0_99[i+1][0]>gradosLib2){
                                  valtablas = interpolacion(this.tabFisher0_99[i][0],gradosLib1,this.tabFisher0_99[i+1][0],this.tabFisher0_99[i][j],this.tabFisher0_99[i+1][j]);
                                  break;
                              }
                          }else if(i == 40){
                              if(this.tabFisher0_99[i][0] == gradosLib2){
                                  valtablas = this.tabFisher0_99[i][j];
                                  break;
                              }
                          }
                      }
                  }
              }
          }
      }
      return valtablas;
    }

    public double tablaFisherPotenciaPrueba(double estadisticoF,int gradosLib1,int gradosLib2){
      double valtablas = 0;
      for(int j =1; j<41; j++){
            if(j<40){
                if(this.tabFisher0_9[0][j] ==gradosLib1){
                    for(int i = 1; i<41;i++){
                        if(i<40){
                            if(this.tabFisher0_9[i][0] == gradosLib2 && estadisticoF == this.tabFisher0_9[i][j]){
                                valtablas = 0.90;
                                break;
                            }else if(this.tabFisher0_9[i][0]<gradosLib2 && this.tabFisher0_9[i+1][0]>gradosLib2){
                                double aux = interpolacion(this.tabFisher0_9[i][0],gradosLib1,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j],this.tabFisher0_9[i+1][j]);
                                if(aux == estadisticoF){
                                    valtablas = 0.90;
                                    break;
                                }
                            }
                        }else if(i == 40){
                            if(this.tabFisher0_9[i][0] == gradosLib2){
                                valtablas = this.tabFisher0_9[i][j];
                                break;
                            }
                        }
                    }
                }else if(gradosLib1>this.tabFisher0_9[0][j] && gradosLib1<this.tabFisher0_9[0][j+1]){
                    for(int i=0; i<41;i++){
                        if(this.tabFisher0_9[i][0] == gradosLib2){
                            valtablas = interpolacion(this.tabFisher0_9[0][j],gradosLib1,this.tabFisher0_9[0][j+1],this.tabFisher0_9[i][j],this.tabFisher0_9[i][j+1]);
                            break;
                        }else if(gradosLib2>this.tabFisher0_9[i][0] && gradosLib2<this.tabFisher0_9[i+1][0]){
                            double interpolacion1 = interpolacion(this.tabFisher0_9[i][0],gradosLib2,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j],this.tabFisher0_9[i+1][j]);
                            double interpolacion2 = interpolacion(this.tabFisher0_9[i][0],gradosLib2,this.tabFisher0_9[i+1][0],this.tabFisher0_9[i][j+1],this.tabFisher0_9[i+1][j+1]);
                            valtablas = interpolacion(this.tabFisher0_9[0][j],gradosLib1,this.tabFisher0_9[0][j+1],interpolacion1,interpolacion2);
                            break;
                        }
                    }
                }

            }else if(j == 40){
                if(this.tabFisher0_9[0][j] ==gradosLib1){
                    for(int i = 1; i<41;i++){
                        if(i<40){

                        }else if(i == 40){
                            if(this.tabFisher0_9[i][0] == gradosLib2){
                                valtablas = this.tabFisher0_9[i][j];
                                break;
                            }
                        }
                    }
                }
            }
        }

      return valtablas;
    }



    public double interpolacion(double x1,double val_conocido, double x2,double y1,double y2){
      double y=y1+(((y2-y1)/(x2-x1))*(val_conocido-x1));
      return redondeoDecimales(y,5);
  }

    public double interpolacion(int x1,int val_conocido, int x2,double y1,double y2){
        double y=y1+(((y2-y1)/(x2-x1))*(val_conocido-x1));
        return redondeoDecimales(y,5);
    }


    public  double redondeoDecimales(double numero, int numeroDecimales) {
        BigDecimal redondeado = new BigDecimal(numero)
                .setScale(numeroDecimales, RoundingMode.HALF_EVEN);
        return redondeado.doubleValue();
    }



  public void llenarLineas(){
       lineas = new ArrayList<>();
      lineas.add("z--0.00--0.01--0.02--0.03--0.04--0.05--0.06--0.07--0.08--0.09--");
      lineas.add("0.0--0.5000--0.4960--0.4920--0.4880--0.4840--0.4801--0.4761--0.4721--0.4681--0.4641--");
      lineas.add("0.1--0.4602--0.4562--0.4522--0.4483--0.4443--0.4404--0.4364--0.4325--0.4286--0.4247--");
      lineas.add("0.2--0.4207--0.4168--0.4129--0.4090--0.4052--0.4013--0.3974--0.3936--0.3897--0.3859--");
      lineas.add("0.3--0.3821--0.3783--0.3745--0.3707--0.3669--0.3632--0.3594--0.3557--0.3520--0.3483--");
      lineas.add("0.4--0.3446--0.3409--0.3372--0.3336--0.3300--0.3264--0.3228--0.3192--0.3156--0.3121--");
      lineas.add("0.5--0.3085--0.3050--0.3015--0.2981--0.2946--0.2912--0.2877--0.2843--0.2810--0.2776--");
      lineas.add("0.6--0.2743--0.2709--0.2676--0.2643--0.2611--0.2578--0.2546--0.2514--0.2483--0.2451--");
      lineas.add("0.7--0.2420--0.2389--0.2358--0.2327--0.2296--0.2266--0.2236--0.2206--0.2177--0.2148--");
      lineas.add("0.8--0.2119--0.2090--0.2061--0.2033--0.2005--0.1977--0.1949--0.1922--0.1894--0.1867--");
      lineas.add("0.9--0.1841--0.1814--0.1788--0.1762--0.1736--0.1711--0.1685--0.1660--0.1635--0.1611--");
      lineas.add("1.0--0.1587--0.1562--0.1539--0.1515--0.1492--0.1469--0.1446--0.1423--0.1401--0.1379--");
      lineas.add("1.1--0.1357--0.1335--0.1314--0.1292--0.1271--0.1251--0.1230--0.1210--0.1190--0.1170--");
      lineas.add("1.2--0.1151--0.1131--0.1112--0.1093--0.1075--0.1056--0.1038--0.1020--0.1003--0.0985--");
      lineas.add("1.3--0.0968--0.0951--0.0934--0.0918--0.0901--0.0885--0.0869--0.0853--0.0838--0.0823--");
      lineas.add("1.4--0.0808--0.0793--0.0778--0.0764--0.0749--0.0735--0.0721--0.0708--0.0694--0.0681--");
      lineas.add("1.5--0.0668--0.0655--0.0643--0.0630--0.0618--0.0606--0.0594--0.0582--0.0571--0.0559--");
      lineas.add("1.6--0.0548--0.0537--0.0526--0.0516--0.0505--0.0495--0.0485--0.0475--0.0465--0.0455--");
      lineas.add("1.7--0.0446--0.0436--0.0427--0.0418--0.0409--0.0401--0.0392--0.0384--0.0375--0.0367--");
      lineas.add("1.8--0.0359--0.0351--0.0344--0.0336--0.0329--0.0322--0.0314--0.0307--0.0301--0.0294--");
      lineas.add("1.9--0.0287--0.0281--0.0274--0.0268--0.0262--0.0256--0.0250--0.0244--0.0239--0.0233--");
      lineas.add("2.0--0.0228--0.0222--0.0217--0.0212--0.0207--0.0202--0.0197--0.0192--0.0188--0.0183--");
      lineas.add("2.1--0.0179--0.0174--0.0170--0.0166--0.0162--0.0158--0.0154--0.0150--0.0146--0.0143--");
      lineas.add("2.2--0.0139--0.0136--0.0132--0.0129--0.0125--0.0122--0.0119--0.0116--0.0113--0.0110--");
      lineas.add("2.3--0.0107--0.0104--0.0102--0.0099--0.0096--0.0094--0.0091--0.0089--0.0087--0.0084--");
      lineas.add("2.4--0.0082--0.0080--0.0078--0.0075--0.0073--0.0071--0.0069--0.0068--0.0066--0.0064--");
      lineas.add("2.5--0.0062--0.0060--0.0059--0.0057--0.0055--0.0054--0.0052--0.0051--0.0049--0.0048--");
      lineas.add("2.6--0.0047--0.0045--0.0044--0.0043--0.0041--0.0040--0.0039--0.0038--0.0037--0.0036--");
      lineas.add("2.7--0.0035--0.0034--0.0033--0.0032--0.0031--0.0030--0.0029--0.0028--0.0027--0.0026--");
      lineas.add("2.8--0.0026--0.0025--0.0024--0.0023--0.0023--0.0022--0.0021--0.0021--0.0020--0.0019--");
      lineas.add("2.9--0.0019--0.0018--0.0018--0.0017--0.0016--0.0016--0.0015--0.0015--0.0014--0.0014--");
      lineas.add("3.0--0.0013--0.0013--0.0013--0.0012--0.0012--0.0011--0.0011--0.0011--0.0010--0.0010--");
      lineas.add("3.1--0.0010--0.0009--0.0009--0.0009--0.0008--0.0008--0.0008--0.0008--0.0007--0.0007--");
      lineas.add("3.2--0.0007--0.0007--0.0006--0.0006--0.0006--0.0006--0.0006--0.0005--0.0005--0.0005--");
      lineas.add("3.3--0.0005--0.0005--0.0005--0.0004--0.0004--0.0004--0.0004--0.0004--0.0004--0.0003--");
      lineas.add("3.4--0.0003--0.0003--0.0003--0.0003--0.0003--0.0003--0.0003--0.0003--0.0003--0.0002--");
      lineas.add("3.5--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--");

      this.tabz = new String[lineas.size()][11];
      for(int i = 0; i<lineas.size(); i++){
          linea_aux = lineas.get(i).split("\\-\\-");
          for(int j = 0; j<linea_aux.length; j++){
              tabz[i][j] = linea_aux[j];
          }
          linea_aux = null;
      }
  }

  public void llenarLineasTabZAcum(){
      lineasAcum = new ArrayList<>();
      lineasAcum.add("z--0.00--0.01--0.02--0.03--0.04--0.05--0.06--0.07--0.08--0.09--");
      lineasAcum.add("-3.5--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--0.0002--");
      lineasAcum.add("-3.4--0.0003--0.0003--0.0003--0.0003--0.0003--0.0003--0.0003--0.0003--0.0003--0.0002--");
      lineasAcum.add("-3.3--0.0005--0.0005--0.0005--0.0004--0.0004--0.0004--0.0004--0.0004--0.0004--0.0003--");
      lineasAcum.add("-3.2--0.0007--0.0007--0.0006--0.0006--0.0006--0.0006--0.0006--0.0005--0.0005--0.0005--");
      lineasAcum.add("-3.1--0.0010--0.0009--0.0009--0.0009--0.0008--0.0008--0.0008--0.0008--0.0007--0.0007--");
      lineasAcum.add("-3.0--0.0013--0.0013--0.0013--0.0012--0.0012--0.0011--0.0011--0.0011--0.0010--0.0010--");
      lineasAcum.add("-2.9--0.0019--0.0018--0.0018--0.0017--0.0016--0.0016--0.0015--0.0015--0.0014--0.0014--");
      lineasAcum.add("-2.8--0.0026--0.0025--0.0024--0.0023--0.0023--0.0022--0.0021--0.0021--0.0020--0.0019--");
      lineasAcum.add("-2.7--0.0035--0.0034--0.0033--0.0032--0.0031--0.0030--0.0029--0.0028--0.0027--0.0026--");
      lineasAcum.add("-2.6--0.0047--0.0045--0.0044--0.0043--0.0041--0.0040--0.0039--0.0038--0.0037--0.0036--");
      lineasAcum.add("-2.5--0.0062--0.0060--0.0059--0.0057--0.0055--0.0054--0.0052--0.0051--0.0049--0.0048--");
      lineasAcum.add("-2.4--0.0082--0.0080--0.0078--0.0075--0.0073--0.0071--0.0069--0.0068--0.0066--0.0064--");
      lineasAcum.add("-2.3--0.0107--0.0104--0.0102--0.0099--0.0096--0.0094--0.0091--0.0089--0.0087--0.0084--");
      lineasAcum.add("-2.2--0.0139--0.0136--0.0132--0.0129--0.0125--0.0122--0.0119--0.0116--0.0113--0.0110--");
      lineasAcum.add("-2.1--0.0179--0.0174--0.0170--0.0166--0.0162--0.0158--0.0154--0.0150--0.0146--0.0143--");
      lineasAcum.add("-2.0--0.0228--0.0222--0.0217--0.0212--0.0207--0.0202--0.0197--0.0192--0.0188--0.0183--");
      lineasAcum.add("-1.9--0.0287--0.0281--0.0274--0.0268--0.0262--0.0256--0.0250--0.0244--0.0239--0.0233--");
      lineasAcum.add("-1.8--0.0359--0.0351--0.0344--0.0336--0.0329--0.0322--0.0314--0.0307--0.0301--0.0294--");
      lineasAcum.add("-1.7--0.0446--0.0436--0.0427--0.0418--0.0409--0.0401--0.0392--0.0384--0.0375--0.0367--");
      lineasAcum.add("-1.6--0.0548--0.0537--0.0526--0.0516--0.0505--0.0495--0.0485--0.0475--0.0465--0.0455--");
      lineasAcum.add("-1.5--0.0668--0.0655--0.0643--0.0630--0.0618--0.0606--0.0594--0.0582--0.0571--0.0559--");
      lineasAcum.add("-1.4--0.0808--0.0793--0.0778--0.0764--0.0749--0.0735--0.0721--0.0708--0.0694--0.0681--");
      lineasAcum.add("-1.3--0.0968--0.0951--0.0934--0.0918--0.0901--0.0885--0.0869--0.0853--0.0838--0.0823--");
      lineasAcum.add("-1.2--0.1151--0.1131--0.1112--0.1093--0.1075--0.1056--0.1038--0.1020--0.1003--0.0985--");
      lineasAcum.add("-1.1--0.1357--0.1335--0.1314--0.1292--0.1271--0.1251--0.1230--0.1210--0.1190--0.1170--");
      lineasAcum.add("-1.0--0.1587--0.1562--0.1539--0.1515--0.1492--0.1469--0.1446--0.1423--0.1401--0.1379--");
      lineasAcum.add("-0.9--0.1841--0.1814--0.1788--0.1762--0.1736--0.1711--0.1685--0.1660--0.1635--0.1611--");
      lineasAcum.add("-0.8--0.2119--0.2090--0.2061--0.2033--0.2005--0.1977--0.1949--0.1922--0.1894--0.1867--");
      lineasAcum.add("-0.7--0.2420--0.2389--0.2358--0.2327--0.2296--0.2266--0.2236--0.2206--0.2177--0.2148--");
      lineasAcum.add("-0.6--0.2743--0.2709--0.2676--0.2643--0.2611--0.2578--0.2546--0.2514--0.2483--0.2451--");
      lineasAcum.add("-0.5--0.3085--0.3050--0.3015--0.2981--0.2946--0.2912--0.2877--0.2843--0.2810--0.2776--");
      lineasAcum.add("-0.4--0.3446--0.3409--0.3372--0.3336--0.3300--0.3264--0.3228--0.3192--0.3156--0.3121--");
      lineasAcum.add("-0.3--0.3821--0.3783--0.3745--0.3707--0.3669--0.3632--0.3594--0.3557--0.3520--0.3483--");
      lineasAcum.add("-0.2--0.4207--0.4168--0.4129--0.4090--0.4052--0.4013--0.3974--0.3936--0.3897--0.3859--");
      lineasAcum.add("-0.1--0.4602--0.4562--0.4522--0.4483--0.4443--0.4404--0.4364--0.4325--0.4286--0.4247--");
      lineasAcum.add("-0.0--0.5000--0.4960--0.4920--0.4880--0.4840--0.4801--0.4761--0.4721--0.4681--0.4641--");
      lineasAcum.add("0.0--0.5000--0.5040--0.5080--0.5120--0.5160--0.5199--0.5239--0.5279--0.5319--0.5359--");
      lineasAcum.add("0.1--0.5398--0.5438--0.5478--0.5517--0.5557--0.5596--0.5636--0.5675--0.5714--0.5753--");
      lineasAcum.add("0.2--0.5793--0.5832--0.5871--0.5910--0.5948--0.5987--0.6026--0.6064--0.6103--0.6141--");
      lineasAcum.add("0.3--0.6179--0.6217--0.6255--0.6293--0.6331--0.6368--0.6406--0.6443--0.6480--0.6517--");
      lineasAcum.add("0.4--0.6554--0.6591--0.6628--0.6664--0.6700--0.6736--0.6772--0.6808--0.6844--0.6879--");
      lineasAcum.add("0.5--0.6915--0.6950--0.6985--0.7019--0.7054--0.7088--0.7123--0.7157--0.7190--0.7224--");
      lineasAcum.add("0.6--0.7257--0.7291--0.7324--0.7357--0.7389--0.7422--0.7454--0.7486--0.7517--0.7549--");
      lineasAcum.add("0.7--0.7580--0.7611--0.7642--0.7673--0.7704--0.7734--0.7764--0.7794--0.7823--0.7852--");
      lineasAcum.add("0.8--0.7881--0.7910--0.7939--0.7967--0.7995--0.8023--0.8051--0.8078--0.8106--0.8133--");
      lineasAcum.add("0.9--0.8159--0.8186--0.8212--0.8238--0.8264--0.8289--0.8315--0.8340--0.8365--0.8389--");
      lineasAcum.add("1.0--0.8413--0.8438--0.8461--0.8485--0.8508--0.8531--0.8554--0.8577--0.8599--0.8621--");
      lineasAcum.add("1.1--0.8643--0.8665--0.8686--0.8708--0.8729--0.8749--0.8770--0.8790--0.8810--0.8830--");
      lineasAcum.add("1.2--0.8849--0.8869--0.8888--0.8907--0.8925--0.8944--0.8962--0.8980--0.8997--0.9015--");
      lineasAcum.add("1.3--0.9032--0.9049--0.9066--0.9082--0.9099--0.9115--0.9131--0.9147--0.9162--0.9177--");
      lineasAcum.add("1.4--0.9192--0.9207--0.9222--0.9236--0.9251--0.9265--0.9279--0.9292--0.9306--0.9319--");
      lineasAcum.add("1.5--0.9332--0.9345--0.9357--0.9370--0.9382--0.9394--0.9406--0.9418--0.9429--0.9441--");
      lineasAcum.add("1.6--0.9452--0.9463--0.9474--0.9484--0.9495--0.9505--0.9515--0.9525--0.9535--0.9545--");
      lineasAcum.add("1.7--0.9554--0.9564--0.9573--0.9582--0.9591--0.9599--0.9608--0.9616--0.9625--0.9633--");
      lineasAcum.add("1.8--0.9641--0.9649--0.9656--0.9664--0.9671--0.9678--0.9686--0.9693--0.9699--0.9706--");
      lineasAcum.add("1.9--0.9713--0.9719--0.9726--0.9732--0.9738--0.9744--0.9750--0.9756--0.9761--0.9767--");
      lineasAcum.add("2.0--0.9772--0.9778--0.9783--0.9788--0.9793--0.9798--0.9803--0.9808--0.9812--0.9817--");
      lineasAcum.add("2.1--0.9821--0.9826--0.9830--0.9834--0.9838--0.9842--0.9846--0.9850--0.9854--0.9857--");
      lineasAcum.add("2.2--0.9861--0.9864--0.9868--0.9871--0.9875--0.9878--0.9881--0.9884--0.9887--0.9890--");
      lineasAcum.add("2.3--0.9893--0.9896--0.9898--0.9901--0.9904--0.9906--0.9909--0.9911--0.9913--0.9916--");
      lineasAcum.add("2.4--0.9918--0.9920--0.9922--0.9925--0.9927--0.9929--0.9931--0.9932--0.9934--0.9936--");
      lineasAcum.add("2.5--0.9938--0.9940--0.9941--0.9943--0.9945--0.9946--0.9948--0.9949--0.9951--0.9952--");
      lineasAcum.add("2.6--0.9953--0.9955--0.9956--0.9957--0.9959--0.9960--0.9961--0.9962--0.9963--0.9964--");
      lineasAcum.add("2.7--0.9965--0.9966--0.9967--0.9968--0.9969--0.9970--0.9971--0.9972--0.9973--0.9974--");
      lineasAcum.add("2.8--0.9974--0.9975--0.9976--0.9977--0.9977--0.9978--0.9979--0.9979--0.9980--0.9981--");
      lineasAcum.add("2.9--0.9981--0.9982--0.9982--0.9983--0.9984--0.9984--0.9985--0.9985--0.9986--0.9986--");
      lineasAcum.add("3.0--0.9987--0.9987--0.9987--0.9988--0.9988--0.9989--0.9989--0.9989--0.9990--0.9990--");
      lineasAcum.add("3.1--0.9990--0.9991--0.9991--0.9991--0.9992--0.9992--0.9992--0.9992--0.9993--0.9993--");
      lineasAcum.add("3.2--0.9993--0.9993--0.9994--0.9994--0.9994--0.9994--0.9994--0.9995--0.9995--0.9995--");
      lineasAcum.add("3.3--0.9995--0.9995--0.9995--0.9996--0.9996--0.9996--0.9996--0.9996--0.9996--0.9997--");
      lineasAcum.add("3.4--0.9997--0.9997--0.9997--0.9997--0.9997--0.9997--0.9997--0.9997--0.9997--0.9998--");
      lineasAcum.add("3.5--0.9998--0.9998--0.9998--0.9998--0.9998--0.9998--0.9998--0.9998--0.9998--0.9998--");

      this.tabzAcumulada = new String[lineasAcum.size()][11];
      this.pepelepeu = lineasAcum.size();


      for(int i = 0; i<lineasAcum.size(); i++){
          linea_aux2 = lineasAcum.get(i).split("\\-\\-");
          for(int j = 0; j<linea_aux2.length; j++){
              this.tabzAcumulada[i][j] = linea_aux2[j];
          }
          linea_aux2 = null;
      }

  }
  public void llenarLineasTablaT(){
      lineasTabTstudent = new ArrayList<>();
      lineasTabTstudent.add("n--0.0005--0.0010--0.0015--0.0020--0.0025--0.0030--0.0035--0.0040--0.0045--0.0050--0.0055--0.0060--0.0065--0.0070--0.0075--0.0080--0.0085--0.0090--0.0095--0.010--0.015--0.020--0.025--0.030--0.035--0.040--0.045--0.050--0.055--0.060--0.065--0.070--0.075--0.080--0.085--0.090--0.095--0.10--0.15--0.20--0.25--0.30--0.35--0.40--0.45--");
      lineasTabTstudent.add("1--636.58--318.29--212.19--159.14--127.32--106.10--90.944--79.572--70.729--63.656--57.868--53.046--48.964--45.465--42.433--39.780--37.439--35.359--33.496--31.821--21.205--15.894--12.706--10.579--9.058--7.916--7.026--6.314--5.730--5.242--4.8288--4.4737--4.1653--3.8947--3.6554--3.4420--3.2506--3.0777--1.9626--1.3764--1.0000--0.7265--0.5095--0.3249--0.1584--");
      lineasTabTstudent.add("2--31.600--22.328--18.217--15.764--14.089--12.852--11.889--11.113--10.470--9.925--9.456--9.046--8.685--8.363--8.073--7.810--7.572--7.353--7.151--6.965--5.643--4.849--4.303--3.896--3.578--3.320--3.104--2.920--2.760--2.620--2.4954--2.3834--2.2819--2.1894--2.1045--2.0261--1.9534--1.8856--1.3862--1.0607--0.8165--0.6172--0.4447--0.2887--0.1421--");
      lineasTabTstudent.add("3--12.924--10.214--8.891--8.052--7.453--6.994--6.627--6.322--6.064--5.841--5.645--5.472--5.316--5.175--5.047--4.930--4.821--4.721--4.628--4.541--3.896--3.482--3.182--2.951--2.763--2.605--2.471--2.353--2.249--2.156--2.0719--1.9950--1.9243--1.8589--1.7981--1.7413--1.6880--1.6377--1.2498--0.9785--0.7649--0.5844--0.4242--0.2767--0.1366--");
      lineasTabTstudent.add("4--8.610--7.173--6.435--5.951--5.598--5.321--5.096--4.908--4.746--4.604--4.479--4.367--4.265--4.173--4.088--4.010--3.937--3.870--3.806--3.747--3.298--2.999--2.776--2.601--2.456--2.333--2.226--2.132--2.048--1.971--1.9016--1.8375--1.7782--1.7229--1.6712--1.6226--1.5767--1.5332--1.1896--0.9410--0.7407--0.5686--0.4142--0.2707--0.1338--");
      lineasTabTstudent.add("5--6.869--5.894--5.376--5.030--4.773--4.570--4.403--4.262--4.140--4.032--3.936--3.850--3.772--3.700--3.634--3.573--3.516--3.462--3.412--3.365--3.003--2.757--2.571--2.422--2.297--2.191--2.098--2.015--1.941--1.873--1.8104--1.7529--1.6994--1.6493--1.6023--1.5579--1.5158--1.4759--1.1558--0.9195--0.7267--0.5594--0.4082--0.2672--0.1322--");
      lineasTabTstudent.add("6--5.959--5.208--4.800--4.524--4.317--4.152--4.015--3.898--3.797--3.707--3.627--3.555--3.489--3.428--3.372--3.320--3.272--3.226--3.183--3.143--2.829--2.612--2.447--2.313--2.201--2.104--2.019--1.943--1.874--1.812--1.7538--1.7002--1.6502--1.6033--1.5590--1.5172--1.4775--1.4398--1.1342--0.9057--0.7176--0.5534--0.4043--0.2648--0.1311--");
      lineasTabTstudent.add("7--5.408--4.785--4.442--4.207--4.029--3.887--3.768--3.667--3.578--3.499--3.429--3.365--3.307--3.253--3.203--3.157--3.113--3.073--3.034--2.998--2.715--2.517--2.365--2.241--2.136--2.046--1.966--1.895--1.830--1.770--1.7153--1.6643--1.6166--1.5718--1.5295--1.4894--1.4513--1.4149--1.1192--0.8960--0.7111--0.5491--0.4015--0.2632--0.1303--");
      lineasTabTstudent.add("8--5.041--4.501--4.199--3.991--3.833--3.705--3.598--3.507--3.427--3.355--3.291--3.233--3.180--3.131--3.085--3.043--3.003--2.965--2.930--2.896--2.634--2.449--2.306--2.189--2.090--2.004--1.928--1.860--1.797--1.740--1.6874--1.6383--1.5922--1.5489--1.5079--1.4691--1.4321--1.3968--1.1081--0.8889--0.7064--0.5459--0.3995--0.2619--0.1297--");
      lineasTabTstudent.add("9--4.781--4.297--4.024--3.835--3.690--3.573--3.474--3.390--3.316--3.250--3.190--3.136--3.087--3.041--2.998--2.958--2.921--2.886--2.853--2.821--2.574--2.398--2.262--2.150--2.055--1.973--1.899--1.833--1.773--1.718--1.6663--1.6185--1.5737--1.5315--1.4916--1.4537--1.4175--1.3830--1.0997--0.8834--0.7027--0.5435--0.3979--0.2610--0.1293--");
      lineasTabTstudent.add("10--4.587--4.144--3.892--3.716--3.581--3.472--3.380--3.301--3.231--3.169--3.113--3.062--3.015--2.972--2.932--2.894--2.859--2.825--2.794--2.764--2.527--2.359--2.228--2.120--2.028--1.948--1.877--1.812--1.754--1.700--1.6498--1.6031--1.5592--1.5179--1.4788--1.4416--1.4061--1.3722--1.0931--0.8791--0.6998--0.5415--0.3966--0.2602--0.1289--");
      lineasTabTstudent.add("11--4.437--4.025--3.789--3.624--3.497--3.393--3.306--3.231--3.165--3.106--3.052--3.004--2.959--2.917--2.879--2.843--2.809--2.777--2.747--2.718--2.491--2.328--2.201--2.096--2.007--1.928--1.859--1.796--1.738--1.686--1.6365--1.5906--1.5476--1.5069--1.4684--1.4318--1.3969--1.3634--1.0877--0.8755--0.6974--0.5399--0.3956--0.2596--0.1286--");
      lineasTabTstudent.add("12--4.318--3.930--3.707--3.550--3.428--3.330--3.247--3.175--3.111--3.055--3.003--2.956--2.913--2.873--2.836--2.801--2.769--2.738--2.709--2.681--2.461--2.303--2.179--2.076--1.989--1.912--1.844--1.782--1.726--1.674--1.6256--1.5804--1.5380--1.4979--1.4599--1.4237--1.3892--1.3562--1.0832--0.8726--0.6955--0.5386--0.3947--0.2590--0.1283--");
      lineasTabTstudent.add("13--4.221--3.852--3.639--3.489--3.372--3.278--3.198--3.128--3.067--3.012--2.963--2.917--2.876--2.837--2.801--2.767--2.736--2.706--2.677--2.650--2.436--2.282--2.160--2.060--1.974--1.899--1.832--1.771--1.715--1.664--1.6164--1.5718--1.5299--1.4903--1.4528--1.4170--1.3829--1.3502--1.0795--0.8702--0.6938--0.5375--0.3940--0.2586--0.1281--");
      lineasTabTstudent.add("14--4.140--3.787--3.583--3.438--3.326--3.234--3.157--3.089--3.030--2.977--2.929--2.885--2.844--2.807--2.771--2.739--2.708--2.678--2.651--2.624--2.415--2.264--2.145--2.046--1.962--1.887--1.821--1.761--1.706--1.656--1.6087--1.5646--1.5231--1.4839--1.4467--1.4113--1.3774--1.3450--1.0763--0.8681--0.6924--0.5366--0.3933--0.2582--0.1280--");
      lineasTabTstudent.add("15--4.073--3.733--3.535--3.395--3.286--3.197--3.122--3.056--2.998--2.947--2.900--2.857--2.817--2.780--2.746--2.714--2.684--2.655--2.628--2.602--2.397--2.249--2.131--2.034--1.951--1.878--1.812--1.753--1.699--1.649--1.6020--1.5583--1.5172--1.4784--1.4415--1.4063--1.3728--1.3406--1.0735--0.8662--0.6912--0.5357--0.3928--0.2579--0.1278--");
      lineasTabTstudent.add("16--4.015--3.686--3.494--3.358--3.252--3.165--3.092--3.028--2.971--2.921--2.875--2.833--2.794--2.758--2.724--2.693--2.663--2.635--2.609--2.583--2.382--2.235--2.120--2.024--1.942--1.869--1.805--1.746--1.692--1.642--1.5962--1.5529--1.5121--1.4736--1.4369--1.4021--1.3687--1.3368--1.0711--0.8647--0.6901--0.5350--0.3923--0.2576--0.1277--");
      lineasTabTstudent.add("17--3.965--3.646--3.459--3.326--3.222--3.138--3.066--3.003--2.948--2.898--2.853--2.812--2.774--2.739--2.706--2.675--2.645--2.618--2.592--2.567--2.368--2.224--2.110--2.015--1.934--1.862--1.798--1.740--1.686--1.637--1.5911--1.5482--1.5077--1.4694--1.4330--1.3983--1.3652--1.3334--1.0690--0.8633--0.6892--0.5344--0.3919--0.2573--0.1276--");
      lineasTabTstudent.add("18--3.922--3.610--3.428--3.298--3.197--3.113--3.043--2.982--2.927--2.878--2.834--2.794--2.756--2.721--2.689--2.658--2.630--2.603--2.577--2.552--2.356--2.214--2.101--2.007--1.926--1.855--1.792--1.734--1.681--1.632--1.5867--1.5439--1.5037--1.4656--1.4295--1.3950--1.3620--1.3304--1.0672--0.8620--0.6884--0.5338--0.3915--0.2571--0.1274--");
      lineasTabTstudent.add("19--3.883--3.579--3.401--3.273--3.174--3.092--3.023--2.962--2.909--2.861--2.817--2.777--2.740--2.706--2.674--2.644--2.616--2.589--2.564--2.539--2.346--2.205--2.093--2.000--1.920--1.850--1.786--1.729--1.677--1.628--1.5827--1.5402--1.5002--1.4623--1.4263--1.3920--1.3592--1.3277--1.0655--0.8610--0.6876--0.5333--0.3912--0.2569--0.1274--");
      lineasTabTstudent.add("20--3.850--3.552--3.376--3.251--3.153--3.073--3.005--2.945--2.893--2.845--2.802--2.763--2.727--2.693--2.661--2.631--2.603--2.577--2.552--2.528--2.336--2.197--2.086--1.994--1.914--1.844--1.782--1.725--1.672--1.624--1.5791--1.5369--1.4970--1.4593--1.4235--1.3894--1.3567--1.3253--1.0640--0.8600--0.6870--0.5329--0.3909--0.2567--0.1273--");
      lineasTabTstudent.add("21--3.819--3.527--3.355--3.231--3.135--3.056--2.989--2.930--2.878--2.831--2.789--2.750--2.714--2.681--2.649--2.620--2.592--2.566--2.541--2.518--2.328--2.189--2.080--1.988--1.909--1.840--1.777--1.721--1.669--1.621--1.5759--1.5338--1.4942--1.4567--1.4210--1.3870--1.3544--1.3232--1.0627--0.8591--0.6864--0.5325--0.3906--0.2566--0.1272--");
      lineasTabTstudent.add("22--3.792--3.505--3.335--3.214--3.119--3.041--2.974--2.916--2.865--2.819--2.777--2.738--2.703--2.670--2.639--2.610--2.582--2.556--2.532--2.508--2.320--2.183--2.074--1.983--1.905--1.835--1.773--1.717--1.665--1.618--1.5730--1.5311--1.4916--1.4542--1.4187--1.3848--1.3524--1.3212--1.0614--0.8583--0.6858--0.5321--0.3904--0.2564--0.1271--");
      lineasTabTstudent.add("23--3.768--3.485--3.318--3.198--3.104--3.027--2.961--2.904--2.853--2.807--2.766--2.728--2.692--2.660--2.629--2.600--2.573--2.547--2.523--2.500--2.313--2.177--2.069--1.978--1.900--1.832--1.770--1.714--1.662--1.615--1.5703--1.5286--1.4893--1.4520--1.4166--1.3828--1.3505--1.3195--1.0603--0.8575--0.6853--0.5317--0.3902--0.2563--0.1271--");
      lineasTabTstudent.add("24--3.745--3.467--3.302--3.183--3.091--3.014--2.949--2.892--2.842--2.797--2.756--2.718--2.683--2.651--2.620--2.592--2.565--2.539--2.515--2.492--2.307--2.172--2.064--1.974--1.896--1.828--1.767--1.711--1.660--1.612--1.5679--1.5263--1.4871--1.4500--1.4147--1.3810--1.3488--1.3178--1.0593--0.8569--0.6848--0.5314--0.3900--0.2562--0.1270--");
      lineasTabTstudent.add("25--3.725--3.450--3.287--3.170--3.078--3.003--2.938--2.882--2.832--2.787--2.747--2.709--2.675--2.642--2.612--2.584--2.557--2.532--2.508--2.485--2.301--2.167--2.060--1.970--1.893--1.825--1.764--1.708--1.657--1.610--1.5657--1.5242--1.4852--1.4482--1.4130--1.3794--1.3472--1.3163--1.0584--0.8562--0.6844--0.5312--0.3898--0.2561--0.1269--");
      lineasTabTstudent.add("26--3.707--3.435--3.274--3.158--3.067--2.992--2.928--2.873--2.823--2.779--2.738--2.701--2.667--2.635--2.605--2.577--2.550--2.525--2.501--2.479--2.296--2.162--2.056--1.967--1.890--1.822--1.761--1.706--1.655--1.608--1.5636--1.5223--1.4834--1.4464--1.4113--1.3778--1.3458--1.3150--1.0575--0.8557--0.6840--0.5309--0.3896--0.2560--0.1269--");
      lineasTabTstudent.add("27--3.689--3.421--3.261--3.146--3.057--2.982--2.919--2.864--2.815--2.771--2.731--2.694--2.660--2.628--2.598--2.570--2.544--2.519--2.495--2.473--2.291--2.158--2.052--1.963--1.887--1.819--1.758--1.703--1.653--1.606--1.5617--1.5205--1.4817--1.4449--1.4098--1.3764--1.3444--1.3137--1.0567--0.8551--0.6837--0.5306--0.3894--0.2559--0.1268--");
      lineasTabTstudent.add("28--3.674--3.408--3.250--3.136--3.047--2.973--2.911--2.856--2.807--2.763--2.723--2.687--2.653--2.621--2.592--2.564--2.538--2.513--2.490--2.467--2.286--2.154--2.048--1.960--1.884--1.817--1.756--1.701--1.651--1.604--1.5600--1.5189--1.4801--1.4434--1.4085--1.3751--1.3432--1.3125--1.0560--0.8546--0.6834--0.5304--0.3893--0.2558--0.1268--");
      lineasTabTstudent.add("29--3.660--3.396--3.239--3.127--3.038--2.965--2.903--2.848--2.800--2.756--2.717--2.680--2.647--2.615--2.586--2.558--2.532--2.508--2.484--2.462--2.282--2.150--2.045--1.957--1.881--1.814--1.754--1.699--1.649--1.602--1.5583--1.5174--1.4787--1.4421--1.4072--1.3739--1.3420--1.3114--1.0553--0.8542--0.6830--0.5302--0.3892--0.2557--0.1268--");
      lineasTabTstudent.add("30--3.646--3.385--3.230--3.118--3.030--2.957--2.895--2.841--2.793--2.750--2.711--2.674--2.641--2.610--2.581--2.553--2.527--2.503--2.479--2.457--2.278--2.147--2.042--1.955--1.879--1.812--1.752--1.697--1.647--1.600--1.5568--1.5159--1.4774--1.4408--1.4060--1.3728--1.3410--1.3104--1.0547--0.8538--0.6828--0.5300--0.3890--0.2556--0.1267--");
      lineasTabTstudent.add("40--3.551--3.307--3.160--3.055--2.971--2.902--2.843--2.792--2.746--2.704--2.667--2.632--2.600--2.570--2.542--2.516--2.491--2.467--2.445--2.423--2.250--2.123--2.021--1.936--1.862--1.796--1.737--1.684--1.635--1.589--1.5459--1.5057--1.4677--1.4317--1.3974--1.3646--1.3332--1.3031--1.0500--0.8507--0.6807--0.5286--0.3881--0.2550--0.1265--");
      lineasTabTstudent.add("50--3.496--3.261--3.120--3.018--2.937--2.870--2.813--2.763--2.718--2.678--2.641--2.607--2.576--2.547--2.519--2.494--2.469--2.446--2.424--2.403--2.234--2.109--2.009--1.924--1.852--1.787--1.729--1.676--1.627--1.582--1.5394--1.4996--1.4620--1.4263--1.3923--1.3598--1.3286--1.2987--1.0473--0.8489--0.6794--0.5278--0.3875--0.2547--0.1263--");
      lineasTabTstudent.add("60--3.460--3.232--3.094--2.994--2.915--2.849--2.793--2.744--2.700--2.660--2.624--2.591--2.560--2.531--2.504--2.479--2.455--2.432--2.411--2.390--2.223--2.099--2.000--1.917--1.845--1.781--1.723--1.671--1.622--1.577--1.5352--1.4956--1.4582--1.4227--1.3889--1.3566--1.3256--1.2958--1.0455--0.8477--0.6786--0.5272--0.3872--0.2545--0.1262--");
      lineasTabTstudent.add("70--3.435--3.211--3.075--2.977--2.899--2.834--2.779--2.730--2.687--2.648--2.612--2.579--2.549--2.521--2.494--2.469--2.445--2.423--2.401--2.381--2.215--2.093--1.994--1.912--1.840--1.776--1.719--1.667--1.619--1.574--1.5321--1.4927--1.4555--1.4202--1.3865--1.3543--1.3234--1.2938--1.0442--0.8468--0.6780--0.5268--0.3869--0.2543--0.1261--");
      lineasTabTstudent.add("80--3.416--3.195--3.061--2.964--2.887--2.823--2.768--2.720--2.677--2.639--2.603--2.571--2.541--2.512--2.486--2.461--2.438--2.415--2.394--2.374--2.209--2.088--1.990--1.908--1.836--1.773--1.716--1.664--1.616--1.572--1.5298--1.4906--1.4535--1.4183--1.3847--1.3526--1.3218--1.2922--1.0432--0.8461--0.6776--0.5265--0.3867--0.2542--0.1261--");
      lineasTabTstudent.add("90--3.402--3.183--3.051--2.954--2.878--2.815--2.760--2.713--2.670--2.632--2.596--2.564--2.534--2.506--2.480--2.455--2.432--2.410--2.389--2.368--2.205--2.084--1.987--1.905--1.834--1.771--1.714--1.662--1.614--1.570--1.5281--1.4889--1.4519--1.4168--1.3833--1.3513--1.3205--1.2910--1.0424--0.8456--0.6772--0.5263--0.3866--0.2541--0.1260--");
      lineasTabTstudent.add("100--3.390--3.174--3.042--2.946--2.871--2.808--2.754--2.706--2.664--2.626--2.591--2.559--2.529--2.501--2.475--2.451--2.427--2.405--2.384--2.364--2.201--2.081--1.984--1.902--1.832--1.769--1.712--1.660--1.613--1.568--1.5267--1.4876--1.4507--1.4156--1.3822--1.3502--1.3195--1.2901--1.0418--0.8452--0.6770--0.5261--0.3864--0.2540--0.1260--");

      tablaTstudent = new String[38][46];
      this.linea_aux2 = null;

      for(int i = 0; i<lineasTabTstudent.size(); i++){
          linea_aux2 = lineasTabTstudent.get(i).split("\\-\\-");
          for(int j = 0; j<linea_aux2.length; j++){
              this.tablaTstudent[i][j] = linea_aux2[j];
          }
          linea_aux2 = null;
      }

  }

  public void llenarTablasJi(){
      this.lineasIzquierdaTabChi = new ArrayList<>();

      lineasIzquierdaTabChi.add("gl--0.005--0.01--0.015--0.02--0.025--0.03--0.035--0.04--0.045--0.05--0.055--0.06--0.065--0.07--0.075--0.08--0.085--0.09--0.095--0.1--0.125--0.15--0.175--0.2--0.225--0.25--0.275--0.3--0.325--0.35--0.375--0.4--0.425--0.45--0.475--0.5--0.525--0.55--0.575--0.6--0.625--0.65--0.675--0.7--0.725--0.75--0.775--0.8--0.825--0.85--0.875--0.9--0.905--0.91--0.915--0.92--0.925--0.93--0.935--0.94--0.945--0.95--0.955--0.96--0.965--0.97--0.975--0.98--0.985--0.99--0.995--");
      lineasIzquierdaTabChi.add("1--7.8794--6.6349--5.9165--5.4119--5.0239--4.7093--4.4452--4.2179--4.0186--3.8415--3.6821--3.5374--3.405--3.283--3.1701--3.0649--2.9666--2.8744--2.7875--2.7055--2.3535--2.0722--1.8396--1.6424--1.4722--1.3233--1.1916--1.0742--0.9687--0.8735--0.787--0.7083--0.6364--0.5707--0.5103--0.4549--0.4041--0.3573--0.3144--0.275--0.2389--0.2059--0.1758--0.1485--0.1238--0.1015--0.0817--0.0642--0.0489--0.0358--0.0247--0.0158--0.0142--0.0128--0.0114--0.0101--0.0089--0.0077--0.0067--0.0057--0.0048--0.0039--0.0032--0.0025--0.0019--0.0014--0.001--0.0006--0.0004--0.0002--0--");
      lineasIzquierdaTabChi.add("2--10.5965--9.2104--8.3994--7.8241--7.3778--7.0131--6.7048--6.4377--6.2022--5.9915--5.8008--5.6268--5.4667--5.3185--5.1805--5.0515--4.9302--4.8159--4.7078--4.6052--4.1589--3.7942--3.4859--3.2189--2.9833--2.7726--2.582--2.4079--2.2479--2.0996--1.9617--1.8326--1.7113--1.597--1.4889--1.3863--1.2887--1.1957--1.1068--1.0217--0.94--0.8616--0.7861--0.7133--0.6432--0.5754--0.5098--0.4463--0.3847--0.325--0.2671--0.2107--0.1996--0.1886--0.1777--0.1668--0.1559--0.1451--0.1344--0.1238--0.1131--0.1026--0.0921--0.0816--0.0713--0.0609--0.0506--0.0404--0.0302--0.0201--0.01--");
      lineasIzquierdaTabChi.add("3--12.8381--11.3449--10.4651--9.8374--9.3484--8.9473--8.6069--8.3112--8.0495--7.8147--7.6018--7.4069--7.2271--7.0603--6.9046--6.7587--6.6213--6.4915--6.3684--6.2514--5.7394--5.317--4.9566--4.6416--4.3613--4.1083--3.8775--3.6649--3.4675--3.2831--3.1098--2.9462--2.7909--2.643--2.5016--2.366--2.2354--2.1095--1.9875--1.8692--1.754--1.6416--1.5316--1.4237--1.3174--1.2125--1.1086--1.0052--0.9018--0.7978--0.6924--0.5844--0.5623--0.5401--0.5176--0.4949--0.472--0.4487--0.4251--0.4012--0.3768--0.3518--0.3263--0.3002--0.2731--0.2451--0.2158--0.1848--0.1516--0.1148--0.0717--");
      lineasIzquierdaTabChi.add("4--14.8602--13.2767--12.3391--11.6678--11.1433--10.7119--10.345--10.0255--9.7423--9.4877--9.2564--9.0444--8.8485--8.6664--8.4963--8.3365--8.1859--8.0434--7.9082--7.7794--7.214--6.7449--6.3423--5.9886--5.6722--5.3853--5.1221--4.8784--4.6511--4.4377--4.2361--4.0446--3.862--3.6871--3.519--3.3567--3.1996--3.0469--2.8982--2.7528--2.6103--2.4701--2.3317--2.1947--2.0585--1.9226--1.7862--1.6488--1.5093--1.3665--1.2188--1.0636--1.0314--0.9987--0.9654--0.9315--0.8969--0.8616--0.8255--0.7884--0.7502--0.7107--0.6698--0.6271--0.5824--0.5351--0.4844--0.4294--0.3682--0.2971--0.207--");
      lineasIzquierdaTabChi.add("5--16.7496--15.0863--14.0978--13.3882--12.8325--12.3746--11.9846--11.6443--11.3423--11.0705--10.8232--10.5962--10.3863--10.191--10.0083--9.8366--9.6745--9.5211--9.3753--9.2363--8.6248--8.1152--7.6763--7.2893--6.9419--6.6257--6.3347--6.0644--5.8115--5.5731--5.3471--5.1319--4.9258--4.7278--4.5366--4.3515--4.1715--3.9959--3.8242--3.6555--3.4893--3.3251--3.1622--2.9999--2.8376--2.6746--2.5099--2.3425--2.1711--1.9938--1.8082--1.6103--1.5688--1.5264--1.4832--1.439--1.3937--1.3472--1.2993--1.2499--1.1987--1.1455--1.0898--1.0313--0.9693--0.9031--0.8312--0.7519--0.6618--0.5543--0.4118--");
      lineasIzquierdaTabChi.add("6--18.5475--16.8119--15.7774--15.0332--14.4494--13.9676--13.5567--13.1978--12.8789--12.5916--12.3299--12.0896--11.8671--11.6599--11.4659--11.2835--11.1112--10.9479--10.7926--10.6446--9.9917--9.4461--8.9748--8.5581--8.183--7.8408--7.5251--7.2311--6.9553--6.6948--6.4472--6.2108--5.9839--5.7652--5.5536--5.3481--5.1478--4.9519--4.7596--4.5702--4.383--4.1973--4.0124--3.8276--3.6419--3.4546--3.2644--3.0701--2.8698--2.6613--2.4411--2.2041--2.154--2.1029--2.0505--1.9967--1.9415--1.8846--1.8258--1.7649--1.7016--1.6354--1.5659--1.4924--1.414--1.3296--1.2373--1.1344--1.016--0.8721--0.6757--");
      lineasIzquierdaTabChi.add("7--20.2777--18.4753--17.3984--16.6224--16.0128--15.5091--15.079--14.703--14.3686--14.0671--13.7924--13.5397--13.3058--13.0877--12.8834--12.6912--12.5095--12.3372--12.1734--12.017--11.3264--10.7479--10.2471--9.8032--9.403--9.0371--8.6989--8.3834--8.0868--7.8061--7.5389--7.2832--7.0374--6.8--6.5698--6.3458--6.127--5.9125--5.7015--5.4932--5.2869--5.0816--4.8768--4.6713--4.4644--4.2549--4.0414--3.8223--3.5956--3.3583--3.1063--2.8331--2.7751--2.7157--2.6548--2.5921--2.5277--2.4611--2.3921--2.3205--2.2457--2.1673--2.0848--1.9971--1.9033--1.8016--1.6899--1.5643--1.4184--1.239--0.9893--");
      lineasIzquierdaTabChi.add("8--21.9549--20.0902--18.9738--18.1682--17.5345--17.0105--16.5626--16.1708--15.822--15.5073--15.2203--14.9563--14.7117--14.4836--14.2697--14.0684--13.8781--13.6975--13.5256--13.3616--12.6361--12.0271--11.4989--11.0301--10.6066--10.2189--9.8599--9.5245--9.2087--8.9094--8.624--8.3505--8.0872--7.8325--7.5852--7.3441--7.1082--6.8766--6.6484--6.4226--6.1986--5.9753--5.7519--5.5274--5.3008--5.0706--4.8355--4.5936--4.3422--4.0782--3.7965--3.4895--3.4241--3.357--3.2881--3.2172--3.144--3.0683--2.9897--2.908--2.8225--2.7326--2.6377--2.5367--2.4281--2.3101--2.1797--2.0325--1.8603--1.6465--1.3444--");
      lineasIzquierdaTabChi.add("9--23.5893--21.666--20.5125--19.679--19.0228--18.4796--18.015--17.6083--17.246--16.919--16.6206--16.3459--16.0913--15.8537--15.6309--15.4211--15.2226--15.0342--14.8549--14.6837--13.9255--13.288--12.7343--12.2421--11.797--11.3887--11.0104--10.6564--10.3227--10.006--9.7037--9.4136--9.134--8.8632--8.5999--8.3428--8.091--7.8434--7.5991--7.357--7.1164--6.8763--6.6356--6.3933--6.1482--5.8988--5.6435--5.3801--5.1057--4.8165--4.507--4.1682--4.0957--4.0214--3.9449--3.8661--3.7847--3.7004--3.6128--3.5215--3.4258--3.3251--3.2185--3.1047--2.9821--2.8485--2.7004--2.5324--2.3348--2.0879--1.7349--");
      lineasIzquierdaTabChi.add("10--25.1881--23.2093--22.0206--21.1608--20.4832--19.9219--19.4415--19.0208--18.6457--18.307--17.9978--17.7131--17.449--17.2026--16.9714--16.7535--16.5473--16.3516--16.1652--15.9872--15.1982--14.5339--13.9562--13.442--12.9763--12.5489--12.1522--11.7807--11.4302--11.0971--10.7789--10.4732--10.1782--9.8922--9.6138--9.3418--9.075--8.8124--8.5528--8.2955--8.0393--7.7832--7.5263--7.2672--7.0047--6.7372--6.4628--6.1791--5.8829--5.5701--5.2341--4.8652--4.7861--4.7049--4.6213--4.535--4.4459--4.3534--4.2572--4.1567--4.0514--3.9403--3.8225--3.6965--3.5606--3.4121--3.247--3.0591--2.8372--2.5582--2.1558--");
      lineasIzquierdaTabChi.add("11--26.7569--24.725--23.5028--22.6179--21.92--21.3416--20.8462--20.412--20.0249--19.6752--19.3557--19.0614--18.7884--18.5334--18.2942--18.0687--17.8553--17.6526--17.4595--17.275--16.4568--15.7671--15.1665--14.6314--14.1464--13.7007--13.2867--12.8987--12.5321--12.1836--11.8503--11.5298--11.2203--10.9199--10.6272--10.341--10.06--9.7831--9.5092--9.2373--8.9663--8.6952--8.4228--8.1479--7.8689--7.5841--7.2916--6.9887--6.6719--6.3364--5.9754--5.5778--5.4924--5.4046--5.3142--5.2209--5.1242--5.024--4.9195--4.8104--4.6958--4.5748--4.4463--4.3087--4.16--3.9972--3.8157--3.6087--3.3634--3.0535--2.6032--");
      lineasIzquierdaTabChi.add("12--28.2997--26.217--24.9628--24.0539--23.3367--22.7418--22.2321--21.7851--21.3864--21.0261--20.6968--20.3934--20.1118--19.8488--19.602--19.3692--19.1488--18.9395--18.74--18.5493--17.7033--16.9893--16.367--15.812--15.3085--14.8454--14.4149--14.0111--13.6294--13.2661--12.9184--12.5838--12.2604--11.9463--11.6401--11.3403--11.0458--10.7553--10.4677--10.182--9.897--9.6115--9.3244--9.0343--8.7396--8.4384--8.1286--7.8073--7.4708--7.1138--6.7288--6.3038--6.2124--6.1183--6.0214--5.9212--5.8175--5.7098--5.5975--5.48--5.3565--5.226--5.0873--4.9385--4.7775--4.6009--4.4038--4.1783--3.9103--3.5706--3.0738--");
      lineasIzquierdaTabChi.add("13--29.8193--27.6882--26.4034--25.4715--24.7356--24.1249--23.6015--23.1423--22.7326--22.362--22.0234--21.7113--21.4215--21.1507--20.8966--20.6568--20.4298--20.214--20.0084--19.8119--18.9392--18.202--17.5588--16.9848--16.4636--15.9839--15.5376--15.1187--14.7224--14.3451--13.9836--13.6356--13.2989--12.9717--12.6525--12.3398--12.0323--11.7288--11.4281--11.1291--10.8307--10.5315--10.2303--9.9257--9.6159--9.2991--8.9727--8.6339--8.2784--7.9008--7.4929--7.0415--6.9443--6.8442--6.741--6.6343--6.5238--6.4088--6.289--6.1635--6.0315--5.8919--5.7432--5.5838--5.4109--5.221--5.0087--4.7654--4.4757--4.1069--3.565--");
      lineasIzquierdaTabChi.add("14--31.3194--29.1412--27.8268--26.8727--26.1189--25.4931--24.9564--24.4854--24.0651--23.6848--23.3371--23.0166--22.7189--22.4408--22.1795--21.9331--21.6996--21.4778--21.2663--21.0641--20.1658--19.4062--18.7431--18.1508--17.6126--17.1169--16.6555--16.2221--15.8118--15.4209--15.0463--14.6853--14.3359--13.9961--13.6644--13.3393--13.0194--12.7034--12.3902--12.0785--11.7671--11.4548--11.1401--10.8215--10.4973--10.1653--9.8231--9.4673--9.0937--8.6963--8.2662--7.7895--7.6867--7.5809--7.4716--7.3587--7.2415--7.1197--6.9925--6.8593--6.7191--6.5706--6.4125--6.2426--6.0583--5.8556--5.6287--5.3682--5.0573--4.6604--4.0747--");
      lineasIzquierdaTabChi.add("15--32.8015--30.578--29.2349--28.2595--27.4884--26.848--26.2985--25.8161--25.3855--24.9958--24.6395--24.3108--24.0055--23.7202--23.4522--23.1992--22.9597--22.7319--22.5148--22.3071--21.3841--20.603--19.9206--19.3107--18.7561--18.2451--17.7691--17.3217--16.898--16.494--16.1066--15.7332--15.3715--15.0197--14.676--14.3389--14.007--13.679--13.3537--13.0298--12.7059--12.3809--12.0532--11.7212--11.383--11.0365--10.679--10.307--9.9158--9.4993--9.0479--8.5468--8.4386--8.3271--8.2121--8.093--7.9695--7.841--7.7068--7.5661--7.4179--7.2609--7.0936--6.9137--6.7183--6.5032--6.2621--5.9849--5.6534--5.2294--4.6009--");
      lineasIzquierdaTabChi.add("16--34.2671--31.9999--30.6292--29.6332--28.8453--28.1908--27.6289--27.1356--26.695--26.2962--25.9315--25.595--25.2824--24.9901--24.7155--24.4564--24.2108--23.9774--23.7547--23.5418--22.5949--21.7931--21.092--20.4651--19.8947--19.3689--18.8788--18.4179--17.9812--17.5646--17.165--16.7795--16.406--16.0425--15.6872--15.3385--14.9951--14.6555--14.3185--13.9827--13.6469--13.3096--12.9693--12.6243--12.2728--11.9122--11.5399--11.1521--10.7441--10.309--9.837--9.3122--9.1988--9.082--8.9613--8.8363--8.7067--8.5717--8.4307--8.2827--8.1269--7.9616--7.7854--7.5958--7.3896--7.1625--6.9077--6.6142--6.2628--5.8122--5.1422--");
      lineasIzquierdaTabChi.add("17--35.7184--33.4087--32.0111--30.995--30.191--29.5227--28.9489--28.4449--27.9947--27.5871--27.2142--26.8701--26.5504--26.2514--25.9705--25.7053--25.454--25.215--24.9871--24.769--23.799--22.977--22.258--21.6146--21.0289--20.4887--19.9849--19.511--19.0617--18.633--18.2215--17.8244--17.4394--17.0646--16.698--16.3382--15.9836--15.6328--15.2845--14.9373--14.5898--14.2406--13.8882--13.5307--13.1661--12.7919--12.4053--12.0023--11.5778--11.1249--10.6329--10.0852--9.9667--9.8446--9.7184--9.5878--9.4522--9.3109--9.1633--9.0083--8.845--8.6718--8.4868--8.2878--8.0712--7.8324--7.5642--7.255--6.8842--6.4077--5.6973--");
      lineasIzquierdaTabChi.add("18--37.1564--34.8052--33.3817--32.3462--31.5264--30.8447--30.2594--29.745--29.2855--28.8693--28.4885--28.137--27.8104--27.5049--27.2178--26.9467--26.6898--26.4455--26.2124--25.9894--24.997--24.1555--23.419--22.7595--22.1591--21.6049--21.0879--20.6014--20.1399--19.6993--19.2763--18.8679--18.4718--18.086--17.7086--17.3379--16.9724--16.6108--16.2515--15.8932--15.5345--15.1738--14.8095--14.4399--14.0626--13.6753--13.2747--12.857--12.4166--11.9462--11.4349--10.8649--10.7416--10.6143--10.4829--10.3467--10.2053--10.0579--9.9038--9.7421--9.5715--9.3904--9.1971--8.9889--8.7622--8.512--8.2307--7.9062--7.5165--7.0149--6.2648--");
      lineasIzquierdaTabChi.add("19--38.5821--36.1908--34.7419--33.6874--32.8523--32.1577--31.561--31.0367--30.568--30.1435--29.755--29.3964--29.063--28.7512--28.4581--28.1813--27.919--27.6695--27.4314--27.2036--26.1893--25.3289--24.5753--23.9004--23.2855--22.7178--22.188--21.6891--21.2158--20.7638--20.3295--19.9102--19.5034--19.1069--18.7189--18.3376--17.9617--17.5894--17.2195--16.8504--16.4808--16.1089--15.7332--15.3517--14.9621--14.562--14.1479--13.7158--13.26--12.7727--12.2425--11.6509--11.5228--11.3906--11.254--11.1124--10.9653--10.812--10.6517--10.4833--10.3056--10.117--9.9155--9.6983--9.4617--9.2004--8.9065--8.567--8.1589--7.6327--6.8439--");
      lineasIzquierdaTabChi.add("20--39.9969--37.5663--36.0926--35.0196--34.1696--33.4623--32.8547--32.3206--31.843--31.4104--31.0144--30.6488--30.3089--29.991--29.692--29.4097--29.142--28.8874--28.6444--28.412--27.3765--26.4976--25.7275--25.0375--24.4086--23.8277--23.2854--22.7745--22.2897--21.8265--21.3813--20.9514--20.534--20.1272--19.7289--19.3374--18.9512--18.5687--18.1884--17.8088--17.4285--17.0458--16.6589--16.2659--15.8644--15.4518--15.0246--14.5784--14.1076--13.6039--13.0553--12.4426--12.3098--12.1728--12.0311--11.8843--11.7317--11.5727--11.4062--11.2314--11.0468--10.8508--10.6413--10.4154--10.1691--9.8971--9.5908--9.2367--8.8105--8.2604--7.4338--");
      lineasIzquierdaTabChi.add("21--41.4009--38.9322--37.4345--36.3434--35.4789--34.7593--34.1409--33.5972--33.1111--32.6706--32.2673--31.8949--31.5486--31.2246--30.92--30.6322--30.3594--30.0998--29.8521--29.6151--28.5589--27.662--26.8759--26.1711--25.5285--24.9348--24.3803--23.8578--23.3617--22.8876--22.4319--21.9915--21.5639--21.147--20.7387--20.3372--19.941--19.5485--19.1581--18.7683--18.3776--17.9843--17.5865--17.1823--16.7692--16.3444--15.9044--15.4446--14.9591--14.4393--13.8728--13.2396--13.1023--12.9605--12.814--12.662--12.5041--12.3394--12.167--11.9858--11.7945--11.5913--11.374--11.1395--10.8839--10.6013--10.2829--9.9145--9.4708--8.8972--8.0336--");
      lineasIzquierdaTabChi.add("22--42.7957--40.2894--38.7681--37.6595--36.7807--36.0491--35.4203--34.8672--34.3726--33.9245--33.514--33.135--32.7825--32.4526--32.1424--31.8494--31.5715--31.3071--31.0548--30.8133--29.7369--28.8224--28.0206--27.3015--26.6455--26.0393--25.4729--24.939--24.432--23.9473--23.4812--23.0307--22.5931--22.1663--21.7482--21.337--20.9311--20.5288--20.1285--19.7288--19.3279--18.9243--18.5159--18.1007--17.6763--17.2396--16.7871--16.314--15.8142--15.2787--14.6948--14.0415--13.8997--13.7534--13.602--13.4451--13.2819--13.1117--12.9335--12.7461--12.5483--12.338--12.1131--11.8703--11.6055--11.3125--10.9823--10.6--10.139--9.5425--8.6427--");
      lineasIzquierdaTabChi.add("23--44.1814--41.6383--40.0941--38.9683--38.0756--37.3323--36.6932--36.131--35.6282--35.1725--34.7551--34.3696--34.011--33.6754--33.3597--33.0616--32.7788--32.5096--32.2527--32.0069--30.9108--29.9792--29.162--28.4288--27.7598--27.1413--26.5633--26.0184--25.5006--25.0055--24.5293--24.0689--23.6216--23.1852--22.7576--22.3369--21.9214--21.5095--21.0997--20.6902--20.2795--19.8657--19.447--19.0211--18.5855--18.1373--17.6726--17.1865--16.6726--16.1219--15.5209--14.848--14.7019--14.551--14.395--14.2331--14.0648--13.8892--13.7053--13.512--13.3077--13.0905--12.8581--12.6072--12.3334--12.0303--11.6885--11.2926--10.8147--10.1957--9.2604--");
      lineasIzquierdaTabChi.add("24--45.5584--42.9798--41.4129--40.2703--39.3641--38.6093--37.9601--37.3891--36.8782--36.415--35.9908--35.5989--35.2344--34.8932--34.5723--34.269--33.9814--33.7077--33.4464--33.1962--32.0809--31.1325--30.3002--29.5533--28.8716--28.2412--27.6518--27.096--26.5678--26.0625--25.5764--25.1064--24.6495--24.2037--23.7667--23.3367--22.912--22.4908--22.0715--21.6525--21.2321--20.8084--20.3795--19.9432--19.4968--19.0373--18.5606--18.0618--17.5343--16.9686--16.3508--15.6587--15.5083--15.3531--15.1925--15.0259--14.8525--14.6716--14.4822--14.2829--14.0723--13.8484--13.6087--13.3498--13.0672--12.7543--12.4011--11.9918--11.4974--10.8563--9.8862--");
      lineasIzquierdaTabChi.add("25--46.928--44.314--42.7252--41.566--40.6465--39.8804--39.2214--38.6417--38.1228--37.6525--37.2216--36.8235--36.4531--36.1065--35.7803--35.4721--35.1798--34.9015--34.6359--34.3816--33.2473--32.2825--31.4355--30.6752--29.981--29.3388--28.7384--28.1719--27.6335--27.1183--26.6226--26.143--25.6769--25.2218--24.7757--24.3366--23.9027--23.4724--23.0439--22.6156--22.1857--21.7524--21.3136--20.867--20.41--19.9393--19.451--18.9397--18.3988--17.8184--17.1843--16.4734--16.3189--16.1594--15.9942--15.8229--15.6447--15.4586--15.2637--15.0587--14.8419--14.6114--14.3645--14.0978--13.8066--13.4839--13.1197--12.6973--12.1867--11.524--10.5196--");
      lineasIzquierdaTabChi.add("26--48.2898--45.6416--44.0312--42.8558--41.9231--41.1461--40.4774--39.8891--39.3625--38.8851--38.4477--38.0435--37.6674--37.3154--36.9841--36.6711--36.3741--36.0914--35.8216--35.5632--34.4104--33.4295--32.5681--31.7946--31.0882--30.4346--29.8232--29.2463--28.6978--28.173--27.6677--27.1789--26.7036--26.2395--25.7845--25.3365--24.8937--24.4544--24.0169--23.5794--23.1403--22.6975--22.249--21.7924--21.325--20.8434--20.3436--19.8202--19.2661--18.6714--18.0212--17.2919--17.1333--16.9695--16.8--16.6241--16.441--16.2499--16.0497--15.839--15.6162--15.3792--15.1253--14.8509--14.5512--14.219--13.8439--13.4086--12.8821--12.1982--11.1602--");
      lineasIzquierdaTabChi.add("27--49.645--46.9628--45.3311--44.1399--43.1945--42.4066--41.7285--41.1318--40.5977--40.1133--39.6694--39.2593--38.8776--38.5202--38.184--37.8662--37.5647--37.2777--37.0036--36.7412--35.5703--34.5736--33.6981--32.9117--32.1933--31.5284--30.9064--30.3193--29.761--29.2266--28.712--28.2141--27.7299--27.2569--26.7931--26.3363--25.8848--25.4367--24.9904--24.544--24.0957--23.6437--23.1856--22.7192--22.2416--21.7494--21.2383--20.703--20.136--19.5272--18.8613--18.1139--17.9513--17.7834--17.6096--17.4291--17.2414--17.0453--16.8398--16.6235--16.3948--16.1514--15.8906--15.6088--15.3007--14.9592--14.5734--14.1254--13.5833--12.8785--11.8077--");
      lineasIzquierdaTabChi.add("28--50.9936--48.2782--46.6255--45.4188--44.4608--43.6622--42.9749--42.3699--41.8283--41.3372--40.887--40.471--40.0838--39.7213--39.3801--39.0577--38.7517--38.4604--38.1823--37.9159--36.7272--35.715--34.8256--34.0266--33.2964--32.6205--31.988--31.3909--30.8229--30.2791--29.7555--29.2486--28.7556--28.274--27.8016--27.3362--26.8761--26.4195--25.9644--25.5092--25.0521--24.5909--24.1235--23.6475--23.1598--22.6572--22.1351--21.588--21.0084--20.3857--19.7044--18.9392--18.7728--18.6008--18.4227--18.2378--18.0454--17.8444--17.6338--17.4121--17.1775--16.9279--16.6603--16.3711--16.0549--15.7042--15.3079--14.8475--14.29--13.5647--12.4613--");
      lineasIzquierdaTabChi.add("29--52.3355--49.5878--47.9147--46.6926--45.7223--44.9132--44.2169--43.6038--43.0548--42.5569--42.1006--41.6789--41.2863--40.9187--40.5727--40.2456--39.9353--39.6398--39.3577--39.0875--37.8812--36.8538--35.9509--35.1394--34.3977--33.7109--33.0681--32.4612--31.8837--31.3308--30.7982--30.2825--29.7809--29.2908--28.8099--28.3361--27.8676--27.4025--26.9389--26.4751--26.0092--25.5391--25.0625--24.577--24.0795--23.5666--23.0337--22.4751--21.883--21.2468--20.5503--19.7677--19.5974--19.4214--19.2392--19.05--18.853--18.6472--18.4315--18.2044--17.9641--17.7084--17.4342--17.1377--16.8134--16.4538--16.0471--15.5745--15.0019--14.2564--13.1211--");
      lineasIzquierdaTabChi.add("30--53.6719--50.8922--49.1988--47.9618--46.9792--46.16--45.4546--44.8335--44.2774--43.773--43.3106--42.8831--42.4852--42.1126--41.7619--41.4303--41.1157--40.8161--40.53--40.256--39.0326--37.9902--37.0739--36.2502--35.4972--34.7997--34.1468--33.5302--32.9435--32.3815--31.8401--31.3159--30.8058--30.3073--29.8181--29.336--28.8592--28.3858--27.9139--27.4416--26.9671--26.4881--26.0026--25.5078--25.0007--24.4776--23.9341--23.3641--22.7599--22.1103--21.3989--20.5992--20.4251--20.2452--20.0588--19.8654--19.6639--19.4534--19.2327--19.0004--18.7545--18.4927--18.212--17.9083--17.5761--17.2076--16.7908--16.3062--15.7188--14.9535--13.7867--");
      lineasIzquierdaTabChi.add("40--66.766--63.6908--61.8117--60.4361--59.3417--58.4278--57.6401--56.9459--56.3235--55.7585--55.2401--54.7606--54.3139--53.8952--53.501--53.128--52.7738--52.4364--52.114--51.805--50.4236--49.2438--48.2046--47.2685--46.4113--45.616--44.8703--44.1649--43.4926--42.8477--42.2256--41.6222--41.0343--40.4589--39.8934--39.3353--38.7826--38.2328--37.684--37.134--36.5803--36.0207--35.4522--34.8719--34.2761--33.6603--33.019--32.3449--31.6286--30.8563--30.0078--29.0505--28.8416--28.6255--28.4014--28.1686--27.9258--27.672--27.4055--27.1245--26.8268--26.5093--26.1684--25.7989--25.394--24.9437--24.4331--23.8376--23.113--22.1642--20.7066--");
      lineasIzquierdaTabChi.add("50--79.4898--76.1538--74.1111--72.6132--71.4202--70.4229--69.5627--68.8039--68.1232--67.5048--66.9372--66.4117--65.922--65.4629--65.0303--64.6209--64.2319--63.8612--63.5068--63.1671--61.6466--60.346--59.1986--58.1638--57.215--56.3336--55.5062--54.7228--53.9753--53.2576--52.5645--51.8916--51.2353--50.5923--49.9597--49.3349--48.7154--48.0986--47.4822--46.8638--46.2406--45.61--44.9688--44.3133--43.6395--42.9421--42.2148--41.4492--40.6342--39.7539--38.7848--37.6886--37.449--37.201--36.9438--36.6762--36.3971--36.105--35.7981--35.4743--35.1308--34.7642--34.3702--33.9426--33.4734--32.9509--32.3574--31.6638--30.8179--29.7067--27.9908--");
      lineasIzquierdaTabChi.add("60--91.9518--88.3794--86.1882--84.5799--83.2977--82.2251--81.2992--80.482--79.7486--79.082--78.4697--77.9029--77.3743--76.8785--76.4113--75.9689--75.5485--75.1477--74.7645--74.397--72.7509--71.3411--70.0961--68.9721--67.9405--66.9815--66.0804--65.2265--64.4112--63.6277--62.8705--62.1348--61.4168--60.7128--60.0197--59.3347--58.6548--57.9775--57.3002--56.62--55.9341--55.2394--54.5324--53.8091--53.0649--52.2938--51.4889--50.6406--49.7365--48.7587--47.6805--46.4589--46.1915--45.9147--45.6274--45.3285--45.0165--44.6898--44.3464--43.9838--43.599--43.188--42.7458--42.2656--41.7383--41.1503--40.4817--39.6994--38.7435--37.4848--35.5344--");
      lineasIzquierdaTabChi.add("70--104.2148--100.4251--98.0975--96.3875--95.0231--93.8813--92.895--92.0241--91.2422--90.5313--89.8781--89.2731--88.7088--88.1794--87.6802--87.2076--86.7583--86.3299--85.92--85.527--83.7654--82.2553--80.9206--79.7147--78.6071--77.5766--76.6079--75.6893--74.8116--73.9677--73.1516--72.3583--71.5836--70.8236--70.0749--69.3345--68.5993--67.8664--67.133--66.3961--65.6526--64.899--64.1316--63.346--62.537--61.6983--60.822--59.8978--58.9118--57.8443--56.6659--55.3289--55.0361--54.7327--54.4178--54.09--53.7478--53.3893--53.0123--52.6141--52.1911--51.7393--51.2528--50.7243--50.1434--49.4953--48.7575--47.8934--46.8361--45.4417--43.2753--");
      lineasIzquierdaTabChi.add("80--116.3209--112.3288--109.8741--108.0693--106.6285--105.422--104.3796--103.4588--102.6317--101.8795--101.1882--100.5477--99.9502--99.3895--98.8606--98.3598--97.8837--97.4295--96.995--96.5782--94.7091--93.1058--91.6875--90.4053--89.2271--88.1303--87.0986--86.1197--85.1841--84.284--83.4131--82.5663--81.7387--80.9266--80.1263--79.3343--78.5476--77.7631--76.9776--76.1879--75.3908--74.5825--73.7589--72.9153--72.0461--71.1445--70.2018--69.207--68.1447--66.9938--65.7221--64.2778--63.9612--63.6332--63.2926--62.938--62.5676--62.1795--61.7712--61.3397--60.8814--60.3915--59.8638--59.2902--58.6595--57.9553--57.1532--56.2128--55.0613--53.54--51.1719--");
      lineasIzquierdaTabChi.add("90--128.2987--124.1162--121.5424--119.6484--118.1359--116.8688--115.7735--114.8057--113.9363--113.1452--112.4182--111.7444--111.1156--110.5255--109.9688--109.4415--108.9401--108.4617--108.0041--107.565--105.5951--103.904--102.4074--101.0537--99.8091--98.6499--97.5591--96.5238--95.5337--94.5809--93.6586--92.7614--91.8844--91.0234--90.1745--89.3342--88.4992--87.6661--86.8317--85.9925--85.1451--84.2854--83.4091--82.5111--81.5854--80.6247--79.6197--78.5584--77.4247--76.1954--74.8362--73.2911--72.9522--72.601--72.2363--71.8564--71.4596--71.0436--70.606--70.1433--69.6517--69.126--68.5596--67.9438--67.2661--66.5092--65.6466--64.6347--63.3942--61.754--59.1963--");
      lineasIzquierdaTabChi.add("100--140.1697--135.8069--133.1196--131.1417--129.5613--128.2367--127.0915--126.0793--125.1698--124.3421--123.5812--122.8759--122.2175--121.5996--121.0166--120.4643--119.939--119.4378--118.9582--118.498--116.4327--114.6588--113.0881--111.6667--110.3593--109.1412--107.9946--106.9058--105.8642--104.8615--103.8907--102.9459--102.0221--101.1149--100.2201--99.3341--98.4534--97.5744--96.6938--95.8078--94.9128--94.0046--93.0784--92.129--91.1498--90.1332--89.0693--87.9453--86.7439--85.4406--83.9985--82.3581--81.9982--81.6251--81.2375--80.8338--80.4119--79.9697--79.5042--79.012--78.489--77.9294--77.3265--76.6705--75.9485--75.1418--74.2219--73.1423--71.8177--70.065--67.3275--");
      lineasIzquierdaTabChi.add("110--151.9482--147.4143--144.6197--142.5617--140.9165--139.5375--138.3448--137.2904--136.3427--135.4802--134.6871--133.9517--133.2653--132.6209--132.0129--131.4367--130.8888--130.3658--129.8654--129.3852--127.2291--125.3764--123.7352--122.2495--120.8825--119.6084--118.4086--117.269--116.1785--115.1284--114.1114--113.1214--112.1531--111.2018--110.2635--109.3341--108.4099--107.4873--106.5627--105.6323--104.692--103.7376--102.764--101.7656--100.7357--99.666--98.5461--97.3624--96.0967--94.7229--93.2021--91.471--91.091--90.6971--90.2878--89.8613--89.4157--88.9485--88.4566--87.9363--87.3833--86.7916--86.1538--85.4598--84.6956--83.8415--82.8671--81.7227--80.3184--78.4582--75.5498--");
      lineasIzquierdaTabChi.add("120--163.6485--158.95--156.053--153.9182--152.2113--150.7802--149.5422--148.4474--147.4633--146.5673--145.7434--144.9795--144.2663--143.5966--142.9646--142.3657--141.7961--141.2524--140.732--140.2326--137.9899--136.062--134.3534--132.8063--131.3822--130.0546--128.804--127.6159--126.4787--125.3833--124.3222--123.289--122.2782--121.285--120.3049--119.334--118.3683--117.4041--116.4375--115.4646--114.4811--113.4825--112.4637--111.4186--110.3401--109.2197--108.0462--106.8056--105.4784--104.0374--102.4413--100.6236--100.2245--99.8106--99.3806--98.9324--98.4641--97.9729--97.4557--96.9087--96.3271--95.7046--95.0335--94.303--93.4986--92.5991--91.5726--90.3668--88.8859--86.9233--83.8517--");



      tabIzquierdaChi = new String[lineasIzquierdaTabChi.size()][72];
      this.linea_aux2 = null;

      for(int i = 0; i<lineasIzquierdaTabChi.size(); i++){
          linea_aux2 = lineasIzquierdaTabChi.get(i).split("\\-\\-");
          for(int j = 0; j<linea_aux2.length; j++){
              this.tabIzquierdaChi[i][j] = linea_aux2[j];
          }
          linea_aux2 = null;
      }
  }

  public void llenarTablasFisher0_9(){
      lineasTabFisher = new ArrayList<>();
      lineasTabFisher.add("0.9--1--2--3--4--5--6--7--8--9--10--11--12--13--14--15--16--17--18--19--20--21--22--23--24--25--26--27--28--29--30--40--50--60--70--80--90--100--200--500--1000--");
      lineasTabFisher.add("1--39.864--49.500--53.593--55.833--57.240--58.204--58.906--59.439--59.857--60.195--60.473--60.705--60.902--61.073--61.220--61.350--61.465--61.566--61.658--61.740--61.815--61.883--61.945--62.002--62.055--62.103--62.148--62.189--62.229--62.265--62.529--62.688--62.794--62.871--62.927--62.972--63.007--63.167--63.264--63.296--");
      lineasTabFisher.add("2--8.526--9.000--9.162--9.243--9.293--9.326--9.349--9.367--9.381--9.392--9.401--9.408--9.415--9.420--9.425--9.429--9.433--9.436--9.439--9.441--9.444--9.446--9.448--9.450--9.451--9.453--9.454--9.456--9.457--9.458--9.466--9.471--9.475--9.477--9.479--9.480--9.481--9.486--9.489--9.490--");
      lineasTabFisher.add("3--5.538--5.462--5.391--5.343--5.309--5.285--5.266--5.252--5.240--5.230--5.222--5.216--5.210--5.205--5.200--5.196--5.193--5.190--5.187--5.184--5.182--5.180--5.178--5.176--5.175--5.173--5.172--5.170--5.169--5.168--5.160--5.155--5.151--5.149--5.147--5.145--5.144--5.139--5.136--5.135--");
      lineasTabFisher.add("4--4.545--4.325--4.191--4.107--4.051--4.010--3.979--3.955--3.936--3.920--3.907--3.896--3.886--3.878--3.870--3.864--3.858--3.853--3.848--3.844--3.841--3.837--3.834--3.831--3.828--3.826--3.823--3.821--3.819--3.817--3.804--3.795--3.790--3.786--3.782--3.780--3.778--3.769--3.764--3.762--");
      lineasTabFisher.add("5--4.060--3.780--3.619--3.520--3.453--3.405--3.368--3.339--3.316--3.297--3.282--3.268--3.257--3.247--3.238--3.230--3.223--3.217--3.212--3.207--3.202--3.198--3.194--3.191--3.187--3.184--3.181--3.179--3.176--3.174--3.157--3.147--3.140--3.135--3.132--3.129--3.126--3.116--3.109--3.107--");
      lineasTabFisher.add("6--3.776--3.463--3.289--3.181--3.108--3.055--3.014--2.983--2.958--2.937--2.920--2.905--2.892--2.881--2.871--2.863--2.855--2.848--2.842--2.836--2.831--2.827--2.822--2.818--2.815--2.811--2.808--2.805--2.803--2.800--2.781--2.770--2.762--2.756--2.752--2.749--2.746--2.734--2.727--2.725--");
      lineasTabFisher.add("7--3.589--3.257--3.074--2.961--2.883--2.827--2.785--2.752--2.725--2.703--2.684--2.668--2.654--2.643--2.632--2.623--2.615--2.607--2.601--2.595--2.589--2.584--2.580--2.575--2.571--2.568--2.564--2.561--2.558--2.555--2.535--2.523--2.514--2.508--2.504--2.500--2.497--2.484--2.476--2.473--");
      lineasTabFisher.add("8--3.458--3.113--2.924--2.806--2.726--2.668--2.624--2.589--2.561--2.538--2.519--2.502--2.488--2.475--2.464--2.454--2.446--2.438--2.431--2.425--2.419--2.414--2.409--2.404--2.400--2.396--2.392--2.389--2.386--2.383--2.361--2.348--2.339--2.333--2.328--2.324--2.321--2.307--2.298--2.295--");
      lineasTabFisher.add("9--3.360--3.006--2.813--2.693--2.611--2.551--2.505--2.469--2.440--2.416--2.396--2.379--2.364--2.351--2.340--2.330--2.320--2.312--2.305--2.298--2.292--2.287--2.282--2.277--2.272--2.268--2.265--2.261--2.258--2.255--2.232--2.218--2.208--2.202--2.196--2.192--2.189--2.174--2.165--2.162--");
      lineasTabFisher.add("10--3.285--2.924--2.728--2.605--2.522--2.461--2.414--2.377--2.347--2.323--2.302--2.284--2.269--2.255--2.244--2.233--2.224--2.215--2.208--2.201--2.194--2.189--2.183--2.178--2.174--2.170--2.166--2.162--2.159--2.155--2.132--2.117--2.107--2.100--2.095--2.090--2.087--2.071--2.062--2.059--");
      lineasTabFisher.add("11--3.225--2.860--2.660--2.536--2.451--2.389--2.342--2.304--2.274--2.248--2.227--2.209--2.193--2.179--2.167--2.156--2.147--2.138--2.130--2.123--2.117--2.111--2.105--2.100--2.095--2.091--2.087--2.083--2.080--2.076--2.052--2.036--2.026--2.019--2.013--2.009--2.005--1.989--1.979--1.975--");
      lineasTabFisher.add("12--3.177--2.807--2.606--2.480--2.394--2.331--2.283--2.245--2.214--2.188--2.166--2.147--2.131--2.117--2.105--2.094--2.084--2.075--2.067--2.060--2.053--2.047--2.041--2.036--2.031--2.027--2.022--2.019--2.015--2.011--1.986--1.970--1.960--1.952--1.946--1.942--1.938--1.921--1.911--1.907--");
      lineasTabFisher.add("13--3.136--2.763--2.560--2.434--2.347--2.283--2.234--2.195--2.164--2.138--2.116--2.097--2.080--2.066--2.053--2.042--2.032--2.023--2.014--2.007--2.000--1.994--1.988--1.983--1.978--1.973--1.969--1.965--1.961--1.958--1.931--1.915--1.904--1.896--1.890--1.886--1.882--1.864--1.853--1.850--");
      lineasTabFisher.add("14--3.102--2.726--2.522--2.395--2.307--2.243--2.193--2.154--2.122--2.095--2.073--2.054--2.037--2.022--2.010--1.998--1.988--1.978--1.970--1.962--1.955--1.949--1.943--1.938--1.933--1.928--1.923--1.919--1.916--1.912--1.885--1.869--1.857--1.849--1.843--1.838--1.834--1.816--1.805--1.801--");
      lineasTabFisher.add("15--3.073--2.695--2.490--2.361--2.273--2.208--2.158--2.119--2.086--2.059--2.037--2.017--2.000--1.985--1.972--1.961--1.950--1.941--1.932--1.924--1.917--1.911--1.905--1.899--1.894--1.889--1.885--1.880--1.876--1.873--1.845--1.828--1.817--1.808--1.802--1.797--1.793--1.774--1.763--1.759--");
      lineasTabFisher.add("16--3.048--2.668--2.462--2.333--2.244--2.178--2.128--2.088--2.055--2.028--2.005--1.985--1.968--1.953--1.940--1.928--1.917--1.908--1.899--1.891--1.884--1.877--1.871--1.866--1.860--1.855--1.851--1.847--1.843--1.839--1.811--1.793--1.782--1.773--1.766--1.761--1.757--1.738--1.726--1.722--");
      lineasTabFisher.add("17--3.026--2.645--2.437--2.308--2.218--2.152--2.102--2.061--2.028--2.001--1.978--1.958--1.940--1.925--1.912--1.900--1.889--1.879--1.870--1.862--1.855--1.848--1.842--1.836--1.831--1.826--1.821--1.817--1.813--1.809--1.781--1.763--1.751--1.742--1.735--1.730--1.726--1.706--1.694--1.690--");
      lineasTabFisher.add("18--3.007--2.624--2.416--2.286--2.196--2.130--2.079--2.038--2.005--1.977--1.954--1.933--1.916--1.900--1.887--1.875--1.864--1.854--1.845--1.837--1.829--1.823--1.816--1.810--1.805--1.800--1.795--1.791--1.787--1.783--1.754--1.736--1.723--1.714--1.707--1.702--1.698--1.678--1.665--1.661--");
      lineasTabFisher.add("19--2.990--2.606--2.397--2.266--2.176--2.109--2.058--2.017--1.984--1.956--1.932--1.912--1.894--1.878--1.865--1.852--1.841--1.831--1.822--1.814--1.807--1.800--1.793--1.787--1.782--1.777--1.772--1.767--1.763--1.759--1.730--1.711--1.699--1.690--1.683--1.677--1.673--1.652--1.639--1.635--");
      lineasTabFisher.add("20--2.975--2.589--2.380--2.249--2.158--2.091--2.040--1.999--1.965--1.937--1.913--1.892--1.875--1.859--1.845--1.833--1.821--1.811--1.802--1.794--1.786--1.779--1.773--1.767--1.761--1.756--1.751--1.746--1.742--1.738--1.708--1.690--1.677--1.667--1.660--1.655--1.650--1.629--1.616--1.612--");
      lineasTabFisher.add("21--2.961--2.575--2.365--2.233--2.142--2.075--2.023--1.982--1.948--1.920--1.896--1.875--1.857--1.841--1.827--1.815--1.803--1.793--1.784--1.776--1.768--1.761--1.754--1.748--1.742--1.737--1.732--1.728--1.723--1.719--1.689--1.670--1.657--1.647--1.640--1.634--1.630--1.608--1.595--1.591--");
      lineasTabFisher.add("22--2.949--2.561--2.351--2.219--2.128--2.060--2.008--1.967--1.933--1.904--1.880--1.859--1.841--1.825--1.811--1.798--1.787--1.777--1.768--1.759--1.751--1.744--1.737--1.731--1.726--1.720--1.715--1.711--1.706--1.702--1.671--1.652--1.639--1.629--1.622--1.616--1.611--1.590--1.576--1.571--");
      lineasTabFisher.add("23--2.937--2.549--2.339--2.207--2.115--2.047--1.995--1.953--1.919--1.890--1.866--1.845--1.827--1.811--1.796--1.784--1.772--1.762--1.753--1.744--1.736--1.729--1.722--1.716--1.710--1.705--1.700--1.695--1.691--1.686--1.655--1.636--1.622--1.613--1.605--1.599--1.594--1.572--1.558--1.554--");
      lineasTabFisher.add("24--2.927--2.538--2.327--2.195--2.103--2.035--1.983--1.941--1.906--1.877--1.853--1.832--1.814--1.797--1.783--1.770--1.759--1.748--1.739--1.730--1.722--1.715--1.708--1.702--1.696--1.691--1.686--1.681--1.676--1.672--1.641--1.621--1.607--1.597--1.590--1.584--1.579--1.556--1.542--1.538--");
      lineasTabFisher.add("25--2.918--2.528--2.317--2.184--2.092--2.024--1.971--1.929--1.895--1.866--1.841--1.820--1.802--1.785--1.771--1.758--1.746--1.736--1.726--1.718--1.710--1.702--1.695--1.689--1.683--1.678--1.672--1.668--1.663--1.659--1.627--1.607--1.593--1.583--1.576--1.569--1.565--1.542--1.527--1.523--");
      lineasTabFisher.add("26--2.909--2.519--2.307--2.174--2.082--2.014--1.961--1.919--1.884--1.855--1.830--1.809--1.790--1.774--1.760--1.747--1.735--1.724--1.715--1.706--1.698--1.690--1.683--1.677--1.671--1.666--1.660--1.656--1.651--1.647--1.615--1.594--1.581--1.570--1.562--1.556--1.551--1.528--1.514--1.509--");
      lineasTabFisher.add("27--2.901--2.511--2.299--2.165--2.073--2.005--1.952--1.909--1.874--1.845--1.820--1.799--1.780--1.764--1.749--1.736--1.724--1.714--1.704--1.695--1.687--1.680--1.673--1.666--1.660--1.655--1.649--1.645--1.640--1.636--1.603--1.583--1.569--1.558--1.550--1.544--1.539--1.515--1.501--1.496--");
      lineasTabFisher.add("28--2.894--2.503--2.291--2.157--2.064--1.996--1.943--1.900--1.865--1.836--1.811--1.790--1.771--1.754--1.740--1.726--1.715--1.704--1.694--1.685--1.677--1.669--1.662--1.656--1.650--1.644--1.639--1.634--1.630--1.625--1.592--1.572--1.558--1.547--1.539--1.533--1.528--1.504--1.489--1.484--");
      lineasTabFisher.add("29--2.887--2.495--2.283--2.149--2.057--1.988--1.935--1.892--1.857--1.827--1.802--1.781--1.762--1.745--1.731--1.717--1.705--1.695--1.685--1.676--1.668--1.660--1.653--1.647--1.640--1.635--1.630--1.625--1.620--1.616--1.583--1.562--1.547--1.537--1.529--1.522--1.517--1.493--1.478--1.472--");
      lineasTabFisher.add("30--2.881--2.489--2.276--2.142--2.049--1.980--1.927--1.884--1.849--1.819--1.794--1.773--1.754--1.737--1.722--1.709--1.697--1.686--1.676--1.667--1.659--1.651--1.644--1.638--1.632--1.626--1.621--1.616--1.611--1.606--1.573--1.552--1.538--1.527--1.519--1.512--1.507--1.482--1.467--1.462--");
      lineasTabFisher.add("40--2.835--2.440--2.226--2.091--1.997--1.927--1.873--1.829--1.793--1.763--1.737--1.715--1.695--1.678--1.662--1.649--1.636--1.625--1.615--1.605--1.596--1.588--1.581--1.574--1.568--1.562--1.556--1.551--1.546--1.541--1.506--1.483--1.467--1.455--1.447--1.439--1.434--1.406--1.389--1.383--");
      lineasTabFisher.add("50--2.809--2.412--2.197--2.061--1.966--1.895--1.840--1.796--1.760--1.729--1.703--1.680--1.660--1.643--1.627--1.613--1.600--1.588--1.578--1.568--1.559--1.551--1.543--1.536--1.529--1.523--1.517--1.512--1.507--1.502--1.465--1.441--1.424--1.412--1.402--1.395--1.388--1.359--1.340--1.333--");
      lineasTabFisher.add("60--2.791--2.393--2.177--2.041--1.946--1.875--1.819--1.775--1.738--1.707--1.680--1.657--1.637--1.619--1.603--1.589--1.576--1.564--1.553--1.543--1.534--1.526--1.518--1.511--1.504--1.498--1.492--1.486--1.481--1.476--1.437--1.413--1.395--1.382--1.372--1.364--1.358--1.326--1.306--1.299--");
      lineasTabFisher.add("70--2.779--2.380--2.164--2.027--1.931--1.860--1.804--1.760--1.723--1.691--1.665--1.641--1.621--1.603--1.587--1.572--1.559--1.547--1.536--1.526--1.517--1.508--1.500--1.493--1.486--1.479--1.473--1.467--1.462--1.457--1.418--1.392--1.374--1.361--1.350--1.342--1.335--1.302--1.281--1.273--");
      lineasTabFisher.add("80--2.769--2.370--2.154--2.016--1.921--1.849--1.793--1.748--1.711--1.680--1.653--1.629--1.609--1.590--1.574--1.559--1.546--1.534--1.523--1.513--1.503--1.495--1.487--1.479--1.472--1.465--1.459--1.453--1.448--1.443--1.403--1.377--1.358--1.344--1.334--1.325--1.318--1.284--1.261--1.253--");
      lineasTabFisher.add("90--2.762--2.363--2.146--2.008--1.912--1.841--1.785--1.739--1.702--1.670--1.643--1.620--1.599--1.581--1.564--1.550--1.536--1.524--1.513--1.503--1.493--1.484--1.476--1.468--1.461--1.455--1.448--1.442--1.437--1.432--1.391--1.365--1.346--1.332--1.321--1.312--1.304--1.269--1.245--1.237--");
      lineasTabFisher.add("100--2.756--2.356--2.139--2.002--1.906--1.834--1.778--1.732--1.695--1.663--1.636--1.612--1.592--1.573--1.557--1.542--1.528--1.516--1.505--1.494--1.485--1.476--1.468--1.460--1.453--1.446--1.440--1.434--1.428--1.423--1.382--1.355--1.336--1.321--1.310--1.301--1.293--1.257--1.232--1.223--");
      lineasTabFisher.add("200--2.731--2.329--2.111--1.973--1.876--1.804--1.747--1.701--1.663--1.631--1.603--1.579--1.558--1.539--1.522--1.507--1.493--1.480--1.468--1.458--1.448--1.438--1.430--1.422--1.414--1.407--1.400--1.394--1.388--1.383--1.339--1.310--1.289--1.273--1.261--1.250--1.242--1.199--1.168--1.157--");
      lineasTabFisher.add("500--2.716--2.313--2.095--1.956--1.859--1.786--1.729--1.683--1.644--1.612--1.583--1.559--1.537--1.518--1.501--1.485--1.471--1.458--1.446--1.435--1.425--1.416--1.407--1.399--1.391--1.384--1.377--1.370--1.364--1.358--1.313--1.282--1.260--1.243--1.229--1.218--1.209--1.160--1.122--1.106--");
      lineasTabFisher.add("1000--2.711--2.308--2.089--1.950--1.853--1.780--1.723--1.676--1.638--1.605--1.577--1.552--1.531--1.511--1.494--1.478--1.464--1.451--1.439--1.428--1.418--1.408--1.399--1.391--1.383--1.376--1.369--1.362--1.356--1.350--1.304--1.273--1.250--1.232--1.218--1.207--1.197--1.145--1.103--1.084--");

      tabFisher0_9 = new double[lineasTabFisher.size()][41];
      this.linea_aux2 = null;

      for(int i = 0; i<lineasTabFisher.size(); i++){
          linea_aux2 = lineasTabFisher.get(i).split("\\-\\-");
          for(int j = 0; j<linea_aux2.length; j++){
              this.tabFisher0_9[i][j] = Double.parseDouble(linea_aux2[j]);
          }
          linea_aux2 = null;
      }

  }

  public void llenarTablasFisher0_95(){
      lineasTabFisher = new ArrayList<>();

      lineasTabFisher.add("0.95--1--2--3--4--5--6--7--8--9--10--11--12--13--14--15--16--17--18--19--20--21--22--23--24--25--26--27--28--29--30--40--50--60--70--80--90--100--200--500--1000--");
      lineasTabFisher.add("1--161.446--199.499--215.707--224.583--230.160--233.988--236.767--238.884--240.543--241.882--242.981--243.905--244.690--245.363--245.949--246.466--246.917--247.324--247.688--248.016--248.307--248.579--248.823--249.052--249.260--249.453--249.631--249.798--249.951--250.096--251.144--251.774--252.196--252.498--252.723--252.898--253.043--253.676--254.062--254.186--");
      lineasTabFisher.add("2--18.513--19.000--19.164--19.247--19.296--19.329--19.353--19.371--19.385--19.396--19.405--19.412--19.419--19.424--19.429--19.433--19.437--19.440--19.443--19.446--19.448--19.450--19.452--19.454--19.456--19.457--19.459--19.460--19.461--19.463--19.471--19.476--19.479--19.481--19.483--19.485--19.486--19.491--19.494--19.495--");
      lineasTabFisher.add("3--10.128--9.552--9.277--9.117--9.013--8.941--8.887--8.845--8.812--8.785--8.763--8.745--8.729--8.715--8.703--8.692--8.683--8.675--8.667--8.660--8.654--8.648--8.643--8.638--8.634--8.630--8.626--8.623--8.620--8.617--8.594--8.581--8.572--8.566--8.561--8.557--8.554--8.540--8.532--8.529--");
      lineasTabFisher.add("4--7.709--6.944--6.591--6.388--6.256--6.163--6.094--6.041--5.999--5.964--5.936--5.912--5.891--5.873--5.858--5.844--5.832--5.821--5.811--5.803--5.795--5.787--5.781--5.774--5.769--5.763--5.759--5.754--5.750--5.746--5.717--5.699--5.688--5.679--5.673--5.668--5.664--5.646--5.635--5.632--");
      lineasTabFisher.add("5--6.608--5.786--5.409--5.192--5.050--4.950--4.876--4.818--4.772--4.735--4.704--4.678--4.655--4.636--4.619--4.604--4.590--4.579--4.568--4.558--4.549--4.541--4.534--4.527--4.521--4.515--4.510--4.505--4.500--4.496--4.464--4.444--4.431--4.422--4.415--4.409--4.405--4.385--4.373--4.369--");
      lineasTabFisher.add("6--5.987--5.143--4.757--4.534--4.387--4.284--4.207--4.147--4.099--4.060--4.027--4.000--3.976--3.956--3.938--3.922--3.908--3.896--3.884--3.874--3.865--3.856--3.849--3.841--3.835--3.829--3.823--3.818--3.813--3.808--3.774--3.754--3.740--3.730--3.722--3.716--3.712--3.690--3.678--3.673--");
      lineasTabFisher.add("7--5.591--4.737--4.347--4.120--3.972--3.866--3.787--3.726--3.677--3.637--3.603--3.575--3.550--3.529--3.511--3.494--3.480--3.467--3.455--3.445--3.435--3.426--3.418--3.410--3.404--3.397--3.391--3.386--3.381--3.376--3.340--3.319--3.304--3.294--3.286--3.280--3.275--3.252--3.239--3.234--");
      lineasTabFisher.add("8--5.318--4.459--4.066--3.838--3.688--3.581--3.500--3.438--3.388--3.347--3.313--3.284--3.259--3.237--3.218--3.202--3.187--3.173--3.161--3.150--3.140--3.131--3.123--3.115--3.108--3.102--3.095--3.090--3.084--3.079--3.043--3.020--3.005--2.994--2.986--2.980--2.975--2.951--2.937--2.932--");
      lineasTabFisher.add("9--5.117--4.256--3.863--3.633--3.482--3.374--3.293--3.230--3.179--3.137--3.102--3.073--3.048--3.025--3.006--2.989--2.974--2.960--2.948--2.936--2.926--2.917--2.908--2.900--2.893--2.886--2.880--2.874--2.869--2.864--2.826--2.803--2.787--2.776--2.768--2.761--2.756--2.731--2.717--2.712--");
      lineasTabFisher.add("10--4.965--4.103--3.708--3.478--3.326--3.217--3.135--3.072--3.020--2.978--2.943--2.913--2.887--2.865--2.845--2.828--2.812--2.798--2.785--2.774--2.764--2.754--2.745--2.737--2.730--2.723--2.716--2.710--2.705--2.700--2.661--2.637--2.621--2.609--2.601--2.594--2.588--2.563--2.548--2.543--");
      lineasTabFisher.add("11--4.844--3.982--3.587--3.357--3.204--3.095--3.012--2.948--2.896--2.854--2.818--2.788--2.761--2.739--2.719--2.701--2.685--2.671--2.658--2.646--2.636--2.626--2.617--2.609--2.601--2.594--2.588--2.582--2.576--2.570--2.531--2.507--2.490--2.478--2.469--2.462--2.457--2.431--2.415--2.410--");
      lineasTabFisher.add("12--4.747--3.885--3.490--3.259--3.106--2.996--2.913--2.849--2.796--2.753--2.717--2.687--2.660--2.637--2.617--2.599--2.583--2.568--2.555--2.544--2.533--2.523--2.514--2.505--2.498--2.491--2.484--2.478--2.472--2.466--2.426--2.401--2.384--2.372--2.363--2.356--2.350--2.323--2.307--2.302--");
      lineasTabFisher.add("13--4.667--3.806--3.411--3.179--3.025--2.915--2.832--2.767--2.714--2.671--2.635--2.604--2.577--2.554--2.533--2.515--2.499--2.484--2.471--2.459--2.448--2.438--2.429--2.420--2.412--2.405--2.398--2.392--2.386--2.380--2.339--2.314--2.297--2.284--2.275--2.267--2.261--2.234--2.218--2.212--");
      lineasTabFisher.add("14--4.600--3.739--3.344--3.112--2.958--2.848--2.764--2.699--2.646--2.602--2.565--2.534--2.507--2.484--2.463--2.445--2.428--2.413--2.400--2.388--2.377--2.367--2.357--2.349--2.341--2.333--2.326--2.320--2.314--2.308--2.266--2.241--2.223--2.210--2.201--2.193--2.187--2.159--2.142--2.136--");
      lineasTabFisher.add("15--4.543--3.682--3.287--3.056--2.901--2.790--2.707--2.641--2.588--2.544--2.507--2.475--2.448--2.424--2.403--2.385--2.368--2.353--2.340--2.328--2.316--2.306--2.297--2.288--2.280--2.272--2.265--2.259--2.253--2.247--2.204--2.178--2.160--2.147--2.137--2.130--2.123--2.095--2.078--2.072--");
      lineasTabFisher.add("16--4.494--3.634--3.239--3.007--2.852--2.741--2.657--2.591--2.538--2.494--2.456--2.425--2.397--2.373--2.352--2.333--2.317--2.302--2.288--2.276--2.264--2.254--2.244--2.235--2.227--2.220--2.212--2.206--2.200--2.194--2.151--2.124--2.106--2.093--2.083--2.075--2.068--2.039--2.022--2.016--");
      lineasTabFisher.add("17--4.451--3.592--3.197--2.965--2.810--2.699--2.614--2.548--2.494--2.450--2.413--2.381--2.353--2.329--2.308--2.289--2.272--2.257--2.243--2.230--2.219--2.208--2.199--2.190--2.181--2.174--2.167--2.160--2.154--2.148--2.104--2.077--2.058--2.045--2.035--2.027--2.020--1.991--1.973--1.967--");
      lineasTabFisher.add("18--4.414--3.555--3.160--2.928--2.773--2.661--2.577--2.510--2.456--2.412--2.374--2.342--2.314--2.290--2.269--2.250--2.233--2.217--2.203--2.191--2.179--2.168--2.159--2.150--2.141--2.134--2.126--2.119--2.113--2.107--2.063--2.035--2.017--2.003--1.993--1.985--1.978--1.948--1.929--1.923--");
      lineasTabFisher.add("19--4.381--3.522--3.127--2.895--2.740--2.628--2.544--2.477--2.423--2.378--2.340--2.308--2.280--2.256--2.234--2.215--2.198--2.182--2.168--2.155--2.144--2.133--2.123--2.114--2.106--2.098--2.090--2.084--2.077--2.071--2.026--1.999--1.980--1.966--1.955--1.947--1.940--1.910--1.891--1.884--");
      lineasTabFisher.add("20--4.351--3.493--3.098--2.866--2.711--2.599--2.514--2.447--2.393--2.348--2.310--2.278--2.250--2.225--2.203--2.184--2.167--2.151--2.137--2.124--2.112--2.102--2.092--2.082--2.074--2.066--2.059--2.052--2.045--2.039--1.994--1.966--1.946--1.932--1.922--1.913--1.907--1.875--1.856--1.850--");
      lineasTabFisher.add("21--4.325--3.467--3.072--2.840--2.685--2.573--2.488--2.420--2.366--2.321--2.283--2.250--2.222--2.197--2.176--2.156--2.139--2.123--2.109--2.096--2.084--2.073--2.063--2.054--2.045--2.037--2.030--2.023--2.016--2.010--1.965--1.936--1.916--1.902--1.891--1.883--1.876--1.845--1.825--1.818--");
      lineasTabFisher.add("22--4.301--3.443--3.049--2.817--2.661--2.549--2.464--2.397--2.342--2.297--2.259--2.226--2.198--2.173--2.151--2.131--2.114--2.098--2.084--2.071--2.059--2.048--2.038--2.028--2.020--2.012--2.004--1.997--1.990--1.984--1.938--1.909--1.889--1.875--1.864--1.856--1.849--1.817--1.797--1.790--");
      lineasTabFisher.add("23--4.279--3.422--3.028--2.796--2.640--2.528--2.442--2.375--2.320--2.275--2.236--2.204--2.175--2.150--2.128--2.109--2.091--2.075--2.061--2.048--2.036--2.025--2.014--2.005--1.996--1.988--1.981--1.973--1.967--1.961--1.914--1.885--1.865--1.850--1.839--1.830--1.823--1.791--1.771--1.764--");
      lineasTabFisher.add("24--4.260--3.403--3.009--2.776--2.621--2.508--2.423--2.355--2.300--2.255--2.216--2.183--2.155--2.130--2.108--2.088--2.070--2.054--2.040--2.027--2.015--2.003--1.993--1.984--1.975--1.967--1.959--1.952--1.945--1.939--1.892--1.863--1.842--1.828--1.816--1.808--1.800--1.768--1.747--1.740--");
      lineasTabFisher.add("25--4.242--3.385--2.991--2.759--2.603--2.490--2.405--2.337--2.282--2.236--2.198--2.165--2.136--2.111--2.089--2.069--2.051--2.035--2.021--2.007--1.995--1.984--1.974--1.964--1.955--1.947--1.939--1.932--1.926--1.919--1.872--1.842--1.822--1.807--1.796--1.787--1.779--1.746--1.725--1.718--");
      lineasTabFisher.add("26--4.225--3.369--2.975--2.743--2.587--2.474--2.388--2.321--2.265--2.220--2.181--2.148--2.119--2.094--2.072--2.052--2.034--2.018--2.003--1.990--1.978--1.966--1.956--1.946--1.938--1.929--1.921--1.914--1.907--1.901--1.853--1.823--1.803--1.788--1.776--1.767--1.760--1.726--1.705--1.698--");
      lineasTabFisher.add("27--4.210--3.354--2.960--2.728--2.572--2.459--2.373--2.305--2.250--2.204--2.166--2.132--2.103--2.078--2.056--2.036--2.018--2.002--1.987--1.974--1.961--1.950--1.940--1.930--1.921--1.913--1.905--1.898--1.891--1.884--1.836--1.806--1.785--1.770--1.758--1.749--1.742--1.708--1.686--1.679--");
      lineasTabFisher.add("28--4.196--3.340--2.947--2.714--2.558--2.445--2.359--2.291--2.236--2.190--2.151--2.118--2.089--2.064--2.041--2.021--2.003--1.987--1.972--1.959--1.946--1.935--1.924--1.915--1.906--1.897--1.889--1.882--1.875--1.869--1.820--1.790--1.769--1.754--1.742--1.733--1.725--1.691--1.669--1.662--");
      lineasTabFisher.add("29--4.183--3.328--2.934--2.701--2.545--2.432--2.346--2.278--2.223--2.177--2.138--2.104--2.075--2.050--2.027--2.007--1.989--1.973--1.958--1.945--1.932--1.921--1.910--1.901--1.891--1.883--1.875--1.868--1.861--1.854--1.806--1.775--1.754--1.738--1.726--1.717--1.710--1.675--1.653--1.645--");
      lineasTabFisher.add("30--4.171--3.316--2.922--2.690--2.534--2.421--2.334--2.266--2.211--2.165--2.126--2.092--2.063--2.037--2.015--1.995--1.976--1.960--1.945--1.932--1.919--1.908--1.897--1.887--1.878--1.870--1.862--1.854--1.847--1.841--1.792--1.761--1.740--1.724--1.712--1.703--1.695--1.660--1.637--1.630--");
      lineasTabFisher.add("40--4.085--3.232--2.839--2.606--2.449--2.336--2.249--2.180--2.124--2.077--2.038--2.003--1.974--1.948--1.924--1.904--1.885--1.868--1.853--1.839--1.826--1.814--1.803--1.793--1.783--1.775--1.766--1.759--1.751--1.744--1.693--1.660--1.637--1.621--1.608--1.597--1.589--1.551--1.526--1.517--");
      lineasTabFisher.add("50--4.034--3.183--2.790--2.557--2.400--2.286--2.199--2.130--2.073--2.026--1.986--1.952--1.921--1.895--1.871--1.850--1.831--1.814--1.798--1.784--1.771--1.759--1.748--1.737--1.727--1.718--1.710--1.702--1.694--1.687--1.634--1.599--1.576--1.558--1.544--1.534--1.525--1.484--1.457--1.448--");
      lineasTabFisher.add("60--4.001--3.150--2.758--2.525--2.368--2.254--2.167--2.097--2.040--1.993--1.952--1.917--1.887--1.860--1.836--1.815--1.796--1.778--1.763--1.748--1.735--1.722--1.711--1.700--1.690--1.681--1.672--1.664--1.656--1.649--1.594--1.559--1.534--1.516--1.502--1.491--1.481--1.438--1.409--1.399--");
      lineasTabFisher.add("70--3.978--3.128--2.736--2.503--2.346--2.231--2.143--2.074--2.017--1.969--1.928--1.893--1.863--1.836--1.812--1.790--1.771--1.753--1.737--1.722--1.709--1.696--1.685--1.674--1.664--1.654--1.646--1.637--1.629--1.622--1.566--1.530--1.505--1.486--1.471--1.459--1.450--1.404--1.374--1.364--");
      lineasTabFisher.add("80--3.960--3.111--2.719--2.486--2.329--2.214--2.126--2.056--1.999--1.951--1.910--1.875--1.845--1.817--1.793--1.772--1.752--1.734--1.718--1.703--1.689--1.677--1.665--1.654--1.644--1.634--1.626--1.617--1.609--1.602--1.545--1.508--1.482--1.463--1.448--1.436--1.426--1.379--1.347--1.336--");
      lineasTabFisher.add("90--3.947--3.098--2.706--2.473--2.316--2.201--2.113--2.043--1.986--1.938--1.897--1.861--1.830--1.803--1.779--1.757--1.737--1.720--1.703--1.688--1.675--1.662--1.650--1.639--1.629--1.619--1.610--1.601--1.593--1.586--1.528--1.491--1.465--1.445--1.429--1.417--1.407--1.358--1.326--1.314--");
      lineasTabFisher.add("100--3.936--3.087--2.696--2.463--2.305--2.191--2.103--2.032--1.975--1.927--1.886--1.850--1.819--1.792--1.768--1.746--1.726--1.708--1.691--1.676--1.663--1.650--1.638--1.627--1.616--1.607--1.598--1.589--1.581--1.573--1.515--1.477--1.450--1.430--1.415--1.402--1.392--1.342--1.308--1.296--");
      lineasTabFisher.add("200--3.888--3.041--2.650--2.417--2.259--2.144--2.056--1.985--1.927--1.878--1.837--1.801--1.769--1.742--1.717--1.694--1.674--1.656--1.639--1.623--1.609--1.596--1.583--1.572--1.561--1.551--1.542--1.533--1.524--1.516--1.455--1.415--1.386--1.364--1.346--1.332--1.321--1.263--1.221--1.205--");
      lineasTabFisher.add("500--3.860--3.014--2.623--2.390--2.232--2.117--2.028--1.957--1.899--1.850--1.808--1.772--1.740--1.712--1.686--1.664--1.643--1.625--1.607--1.592--1.577--1.563--1.551--1.539--1.528--1.518--1.508--1.499--1.490--1.482--1.419--1.376--1.345--1.322--1.303--1.288--1.275--1.210--1.159--1.138--");
      lineasTabFisher.add("1000--3.851--3.005--2.614--2.381--2.223--2.108--2.019--1.948--1.889--1.840--1.798--1.762--1.730--1.702--1.676--1.654--1.633--1.614--1.597--1.581--1.566--1.553--1.540--1.528--1.517--1.507--1.497--1.488--1.479--1.471--1.406--1.363--1.332--1.308--1.289--1.273--1.260--1.190--1.134--1.110--");


      tabFisher0_95 = new double[lineasTabFisher.size()][41];
      this.linea_aux2 = null;

      for(int i = 0; i<lineasTabFisher.size(); i++){
          linea_aux2 = lineasTabFisher.get(i).split("\\-\\-");
          for(int j = 0; j<linea_aux2.length; j++){
              this.tabFisher0_95[i][j] = Double.parseDouble(linea_aux2[j]);
          }
          linea_aux2 = null;
      }


  }


  public void llenarTablasFisher0_975(){
      lineasTabFisher = new ArrayList<>();
      lineasTabFisher.add("0.975--1--2--3--4--5--6--7--8--9--10--11--12--13--14--15--16--17--18--19--20--21--22--23--24--25--26--27--28--29--30--40--50--60--70--80--90--100--200--500--1000--");
      lineasTabFisher.add("1--647.793--799.482--864.151--899.599--921.835--937.114--948.203--956.643--963.279--968.634--973.028--976.725--979.839--982.545--984.874--986.911--988.715--990.345--991.800--993.081--994.303--995.351--996.341--997.272--998.087--998.843--999.542--1000.240--1000.823--1001.405--1005.596--1008.098--1009.787--1011.009--1011.911--1012.610--1013.163--1015.724--1017.237--1017.761--");
      lineasTabFisher.add("2--38.506--39.000--39.166--39.248--39.298--39.331--39.356--39.373--39.387--39.398--39.407--39.415--39.421--39.427--39.431--39.436--39.439--39.442--39.446--39.448--39.450--39.452--39.455--39.457--39.458--39.459--39.461--39.462--39.463--39.465--39.473--39.478--39.481--39.484--39.486--39.487--39.488--39.493--39.496--39.497--");
      lineasTabFisher.add("3--17.443--16.044--15.439--15.101--14.885--14.735--14.624--14.540--14.473--14.419--14.374--14.337--14.305--14.277--14.253--14.232--14.213--14.196--14.181--14.167--14.155--14.144--14.134--14.124--14.115--14.107--14.100--14.093--14.086--14.081--14.036--14.010--13.992--13.979--13.970--13.962--13.956--13.929--13.913--13.908--");
      lineasTabFisher.add("4--12.218--10.649--9.979--9.604--9.364--9.197--9.074--8.980--8.905--8.844--8.794--8.751--8.715--8.684--8.657--8.633--8.611--8.592--8.575--8.560--8.546--8.533--8.522--8.511--8.501--8.492--8.483--8.475--8.468--8.461--8.411--8.381--8.360--8.346--8.335--8.326--8.319--8.288--8.270--8.264--");
      lineasTabFisher.add("5--10.007--8.434--7.764--7.388--7.146--6.978--6.853--6.757--6.681--6.619--6.568--6.525--6.488--6.456--6.428--6.403--6.381--6.362--6.344--6.329--6.314--6.301--6.289--6.278--6.268--6.258--6.250--6.242--6.234--6.227--6.175--6.144--6.123--6.107--6.096--6.087--6.080--6.048--6.028--6.022--");
      lineasTabFisher.add("6--8.813--7.260--6.599--6.227--5.988--5.820--5.695--5.600--5.523--5.461--5.410--5.366--5.329--5.297--5.269--5.244--5.222--5.202--5.184--5.168--5.154--5.141--5.128--5.117--5.107--5.097--5.088--5.080--5.072--5.065--5.012--4.980--4.959--4.943--4.932--4.923--4.915--4.882--4.862--4.856--");
      lineasTabFisher.add("7--8.073--6.542--5.890--5.523--5.285--5.119--4.995--4.899--4.823--4.761--4.709--4.666--4.628--4.596--4.568--4.543--4.521--4.501--4.483--4.467--4.452--4.439--4.426--4.415--4.405--4.395--4.386--4.378--4.370--4.362--4.309--4.276--4.254--4.239--4.227--4.218--4.210--4.176--4.156--4.149--");
      lineasTabFisher.add("8--7.571--6.059--5.416--5.053--4.817--4.652--4.529--4.433--4.357--4.295--4.243--4.200--4.162--4.130--4.101--4.076--4.054--4.034--4.016--3.999--3.985--3.971--3.959--3.947--3.937--3.927--3.918--3.909--3.901--3.894--3.840--3.807--3.784--3.768--3.756--3.747--3.739--3.705--3.684--3.677--");
      lineasTabFisher.add("9--7.209--5.715--5.078--4.718--4.484--4.320--4.197--4.102--4.026--3.964--3.912--3.868--3.831--3.798--3.769--3.744--3.722--3.701--3.683--3.667--3.652--3.638--3.626--3.614--3.604--3.594--3.584--3.576--3.568--3.560--3.505--3.472--3.449--3.433--3.421--3.411--3.403--3.368--3.347--3.340--");
      lineasTabFisher.add("10--6.937--5.456--4.826--4.468--4.236--4.072--3.950--3.855--3.779--3.717--3.665--3.621--3.583--3.550--3.522--3.496--3.474--3.453--3.435--3.419--3.403--3.390--3.377--3.365--3.355--3.345--3.335--3.327--3.319--3.311--3.255--3.221--3.198--3.182--3.169--3.160--3.152--3.116--3.094--3.087--");
      lineasTabFisher.add("11--6.724--5.256--4.630--4.275--4.044--3.881--3.759--3.664--3.588--3.526--3.474--3.430--3.392--3.359--3.330--3.304--3.282--3.261--3.243--3.226--3.211--3.197--3.184--3.173--3.162--3.152--3.142--3.133--3.125--3.118--3.061--3.027--3.004--2.987--2.974--2.964--2.956--2.920--2.898--2.890--");
      lineasTabFisher.add("12--6.554--5.096--4.474--4.121--3.891--3.728--3.607--3.512--3.436--3.374--3.321--3.277--3.239--3.206--3.177--3.152--3.129--3.108--3.090--3.073--3.057--3.043--3.031--3.019--3.008--2.998--2.988--2.979--2.971--2.963--2.906--2.871--2.848--2.831--2.818--2.808--2.800--2.763--2.740--2.733--");
      lineasTabFisher.add("13--6.414--4.965--4.347--3.996--3.767--3.604--3.483--3.388--3.312--3.250--3.197--3.153--3.115--3.082--3.053--3.027--3.004--2.983--2.965--2.948--2.932--2.918--2.905--2.893--2.882--2.872--2.862--2.853--2.845--2.837--2.780--2.744--2.720--2.703--2.690--2.680--2.671--2.634--2.611--2.603--");
      lineasTabFisher.add("14--6.298--4.857--4.242--3.892--3.663--3.501--3.380--3.285--3.209--3.147--3.095--3.050--3.012--2.979--2.949--2.923--2.900--2.879--2.861--2.844--2.828--2.814--2.801--2.789--2.778--2.767--2.758--2.749--2.740--2.732--2.674--2.638--2.614--2.597--2.583--2.573--2.565--2.526--2.503--2.495--");
      lineasTabFisher.add("15--6.200--4.765--4.153--3.804--3.576--3.415--3.293--3.199--3.123--3.060--3.008--2.963--2.925--2.891--2.862--2.836--2.813--2.792--2.773--2.756--2.740--2.726--2.713--2.701--2.689--2.679--2.669--2.660--2.652--2.644--2.585--2.549--2.524--2.506--2.493--2.482--2.474--2.435--2.411--2.403--");
      lineasTabFisher.add("16--6.115--4.687--4.077--3.729--3.502--3.341--3.219--3.125--3.049--2.986--2.934--2.889--2.851--2.817--2.788--2.761--2.738--2.717--2.698--2.681--2.665--2.651--2.637--2.625--2.614--2.603--2.594--2.584--2.576--2.568--2.509--2.472--2.447--2.429--2.415--2.405--2.396--2.357--2.333--2.324--");
      lineasTabFisher.add("17--6.042--4.619--4.011--3.665--3.438--3.277--3.156--3.061--2.985--2.922--2.870--2.825--2.786--2.753--2.723--2.697--2.673--2.652--2.633--2.616--2.600--2.585--2.572--2.560--2.548--2.538--2.528--2.519--2.510--2.502--2.442--2.405--2.380--2.362--2.348--2.337--2.329--2.289--2.264--2.256--");
      lineasTabFisher.add("18--5.978--4.560--3.954--3.608--3.382--3.221--3.100--3.005--2.929--2.866--2.814--2.769--2.730--2.696--2.667--2.640--2.617--2.596--2.576--2.559--2.543--2.529--2.515--2.503--2.491--2.481--2.471--2.461--2.453--2.445--2.384--2.347--2.321--2.303--2.289--2.278--2.269--2.229--2.204--2.195--");
      lineasTabFisher.add("19--5.922--4.508--3.903--3.559--3.333--3.172--3.051--2.956--2.880--2.817--2.765--2.720--2.681--2.647--2.617--2.591--2.567--2.546--2.526--2.509--2.493--2.478--2.465--2.452--2.441--2.430--2.420--2.411--2.402--2.394--2.333--2.295--2.270--2.251--2.237--2.226--2.217--2.176--2.150--2.142--");
      lineasTabFisher.add("20--5.871--4.461--3.859--3.515--3.289--3.128--3.007--2.913--2.837--2.774--2.721--2.676--2.637--2.603--2.573--2.547--2.523--2.501--2.482--2.464--2.448--2.434--2.420--2.408--2.396--2.385--2.375--2.366--2.357--2.349--2.287--2.249--2.223--2.205--2.190--2.179--2.170--2.128--2.103--2.094--");
      lineasTabFisher.add("21--5.827--4.420--3.819--3.475--3.250--3.090--2.969--2.874--2.798--2.735--2.682--2.637--2.598--2.564--2.534--2.507--2.483--2.462--2.442--2.425--2.409--2.394--2.380--2.368--2.356--2.345--2.335--2.325--2.317--2.308--2.246--2.208--2.182--2.163--2.148--2.137--2.128--2.086--2.060--2.051--");
      lineasTabFisher.add("22--5.786--4.383--3.783--3.440--3.215--3.055--2.934--2.839--2.763--2.700--2.647--2.602--2.563--2.528--2.498--2.472--2.448--2.426--2.407--2.389--2.373--2.358--2.344--2.332--2.320--2.309--2.299--2.289--2.280--2.272--2.210--2.171--2.145--2.125--2.111--2.099--2.090--2.047--2.021--2.012--");
      lineasTabFisher.add("23--5.750--4.349--3.750--3.408--3.183--3.023--2.902--2.808--2.731--2.668--2.615--2.570--2.531--2.497--2.466--2.440--2.416--2.394--2.374--2.357--2.340--2.325--2.312--2.299--2.287--2.276--2.266--2.256--2.247--2.239--2.176--2.137--2.111--2.091--2.077--2.065--2.056--2.013--1.986--1.977--");
      lineasTabFisher.add("24--5.717--4.319--3.721--3.379--3.155--2.995--2.874--2.779--2.703--2.640--2.586--2.541--2.502--2.468--2.437--2.411--2.386--2.365--2.345--2.327--2.311--2.296--2.282--2.269--2.257--2.246--2.236--2.226--2.217--2.209--2.146--2.107--2.080--2.060--2.045--2.034--2.024--1.981--1.954--1.945--");
      lineasTabFisher.add("25--5.686--4.291--3.694--3.353--3.129--2.969--2.848--2.753--2.677--2.613--2.560--2.515--2.476--2.441--2.411--2.384--2.360--2.338--2.318--2.300--2.284--2.269--2.255--2.242--2.230--2.219--2.209--2.199--2.190--2.182--2.118--2.079--2.052--2.032--2.017--2.005--1.996--1.952--1.924--1.915--");
      lineasTabFisher.add("26--5.659--4.265--3.670--3.329--3.105--2.945--2.824--2.729--2.653--2.590--2.536--2.491--2.452--2.417--2.387--2.360--2.335--2.314--2.294--2.276--2.259--2.244--2.230--2.217--2.205--2.194--2.184--2.174--2.165--2.157--2.093--2.053--2.026--2.006--1.991--1.979--1.969--1.925--1.897--1.888--");
      lineasTabFisher.add("27--5.633--4.242--3.647--3.307--3.083--2.923--2.802--2.707--2.631--2.568--2.514--2.469--2.429--2.395--2.364--2.337--2.313--2.291--2.271--2.253--2.237--2.222--2.208--2.195--2.183--2.171--2.161--2.151--2.142--2.133--2.069--2.029--2.002--1.982--1.966--1.954--1.945--1.900--1.872--1.862--");
      lineasTabFisher.add("28--5.610--4.221--3.626--3.286--3.063--2.903--2.782--2.687--2.611--2.547--2.494--2.448--2.409--2.374--2.344--2.317--2.292--2.270--2.251--2.232--2.216--2.201--2.187--2.174--2.161--2.150--2.140--2.130--2.121--2.112--2.048--2.007--1.980--1.959--1.944--1.932--1.922--1.877--1.848--1.839--");
      lineasTabFisher.add("29--5.588--4.201--3.607--3.267--3.044--2.884--2.763--2.669--2.592--2.529--2.475--2.430--2.390--2.355--2.325--2.298--2.273--2.251--2.231--2.213--2.196--2.181--2.167--2.154--2.142--2.131--2.120--2.110--2.101--2.092--2.028--1.987--1.959--1.939--1.923--1.911--1.901--1.855--1.827--1.817--");
      lineasTabFisher.add("30--5.568--4.182--3.589--3.250--3.026--2.867--2.746--2.651--2.575--2.511--2.458--2.412--2.372--2.338--2.307--2.280--2.255--2.233--2.213--2.195--2.178--2.163--2.149--2.136--2.124--2.112--2.102--2.092--2.083--2.074--2.009--1.968--1.940--1.920--1.904--1.892--1.882--1.835--1.806--1.797--");
      lineasTabFisher.add("40--5.424--4.051--3.463--3.126--2.904--2.744--2.624--2.529--2.452--2.388--2.334--2.288--2.248--2.213--2.182--2.154--2.129--2.107--2.086--2.068--2.051--2.035--2.020--2.007--1.994--1.983--1.972--1.962--1.952--1.943--1.875--1.832--1.803--1.781--1.764--1.751--1.741--1.691--1.659--1.648--");
      lineasTabFisher.add("50--5.340--3.975--3.390--3.054--2.833--2.674--2.553--2.458--2.381--2.317--2.263--2.216--2.176--2.140--2.109--2.081--2.056--2.033--2.012--1.993--1.976--1.960--1.945--1.931--1.919--1.907--1.895--1.885--1.875--1.866--1.796--1.752--1.721--1.698--1.681--1.667--1.656--1.603--1.569--1.557--");
      lineasTabFisher.add("60--5.286--3.925--3.343--3.008--2.786--2.627--2.507--2.412--2.334--2.270--2.216--2.169--2.129--2.093--2.061--2.033--2.008--1.985--1.964--1.944--1.927--1.911--1.896--1.882--1.869--1.857--1.845--1.835--1.825--1.815--1.744--1.699--1.667--1.643--1.625--1.611--1.599--1.543--1.507--1.495--");
      lineasTabFisher.add("70--5.247--3.890--3.309--2.975--2.754--2.595--2.474--2.379--2.302--2.237--2.183--2.136--2.095--2.059--2.028--1.999--1.974--1.950--1.929--1.910--1.892--1.876--1.861--1.847--1.833--1.821--1.810--1.799--1.789--1.779--1.707--1.660--1.628--1.604--1.585--1.570--1.558--1.500--1.463--1.449--");
      lineasTabFisher.add("80--5.218--3.864--3.284--2.950--2.730--2.571--2.450--2.355--2.277--2.213--2.158--2.111--2.071--2.035--2.003--1.974--1.948--1.925--1.904--1.884--1.866--1.850--1.835--1.820--1.807--1.795--1.783--1.772--1.762--1.752--1.679--1.632--1.599--1.574--1.555--1.540--1.527--1.467--1.428--1.414--");
      lineasTabFisher.add("90--5.196--3.844--3.265--2.932--2.711--2.552--2.432--2.336--2.259--2.194--2.140--2.092--2.051--2.015--1.983--1.955--1.929--1.905--1.884--1.864--1.846--1.830--1.814--1.800--1.787--1.774--1.763--1.752--1.741--1.731--1.657--1.610--1.576--1.551--1.531--1.516--1.503--1.441--1.401--1.386--");
      lineasTabFisher.add("100--5.179--3.828--3.250--2.917--2.696--2.537--2.417--2.321--2.244--2.179--2.124--2.077--2.036--2.000--1.968--1.939--1.913--1.890--1.868--1.849--1.830--1.814--1.798--1.784--1.770--1.758--1.746--1.735--1.725--1.715--1.640--1.592--1.558--1.532--1.512--1.496--1.483--1.420--1.378--1.363--");
      lineasTabFisher.add("200--5.100--3.758--3.182--2.850--2.630--2.472--2.351--2.256--2.178--2.113--2.058--2.010--1.969--1.932--1.900--1.870--1.844--1.820--1.798--1.778--1.759--1.742--1.726--1.712--1.698--1.685--1.673--1.661--1.650--1.640--1.562--1.511--1.474--1.447--1.425--1.407--1.393--1.320--1.269--1.250--");
      lineasTabFisher.add("500--5.054--3.716--3.142--2.811--2.592--2.434--2.313--2.217--2.139--2.074--2.019--1.971--1.929--1.892--1.859--1.830--1.803--1.779--1.757--1.736--1.717--1.700--1.684--1.669--1.655--1.641--1.629--1.617--1.606--1.596--1.515--1.462--1.423--1.394--1.370--1.351--1.336--1.254--1.192--1.166--");
      lineasTabFisher.add("1000--5.039--3.703--3.129--2.799--2.579--2.421--2.300--2.204--2.126--2.061--2.006--1.958--1.916--1.879--1.846--1.816--1.789--1.765--1.743--1.722--1.703--1.686--1.670--1.654--1.640--1.627--1.614--1.603--1.591--1.581--1.499--1.445--1.406--1.376--1.352--1.332--1.316--1.230--1.162--1.132--");

      tabFisher0_975 = new double[lineasTabFisher.size()][41];
      this.linea_aux2 = null;

      for(int i = 0; i<lineasTabFisher.size(); i++){
          linea_aux2 = lineasTabFisher.get(i).split("\\-\\-");
          for(int j = 0; j<linea_aux2.length; j++){
              this.tabFisher0_975[i][j] = Double.parseDouble(linea_aux2[j]);
          }
          linea_aux2 = null;
      }

  }

  public void llenarLineasTabFisher0_99(){
      lineasTabFisher = new ArrayList<>();

      lineasTabFisher.add("0.99--1--2--3--4--5--6--7--8--9--10--11--12--13--14--15--16--17--18--19--20--21--22--23--24--25--26--27--28--29--30--40--50--60--70--80--90--100--200--500--1000--");
      lineasTabFisher.add("1--4052.185--4999.340--5403.534--5624.257--5763.955--5858.950--5928.334--5980.954--6022.397--6055.925--6083.399--6106.682--6125.774--6143.004--6156.974--6170.012--6181.188--6191.432--6200.746--6208.662--6216.113--6223.097--6228.685--6234.273--6239.861--6244.518--6249.174--6252.900--6257.091--6260.350--6286.427--6302.260--6312.970--6320.886--6326.474--6330.665--6333.925--6349.757--6359.536--6362.796--");
      lineasTabFisher.add("2--98.502--99.000--99.164--99.251--99.302--99.331--99.357--99.375--99.390--99.397--99.408--99.419--99.422--99.426--99.433--99.437--99.441--99.444--99.448--99.448--99.451--99.455--99.455--99.455--99.459--99.462--99.462--99.462--99.462--99.466--99.477--99.477--99.484--99.484--99.484--99.488--99.491--99.491--99.499--99.499--");
      lineasTabFisher.add("3--34.116--30.816--29.457--28.710--28.237--27.911--27.671--27.489--27.345--27.228--27.132--27.052--26.983--26.924--26.872--26.826--26.786--26.751--26.719--26.690--26.664--26.639--26.617--26.597--26.579--26.562--26.546--26.531--26.517--26.504--26.411--26.354--26.316--26.289--26.269--26.253--26.241--26.183--26.148--26.137--");
      lineasTabFisher.add("4--21.198--18.000--16.694--15.977--15.522--15.207--14.976--14.799--14.659--14.546--14.452--14.374--14.306--14.249--14.198--14.154--14.114--14.079--14.048--14.019--13.994--13.970--13.949--13.929--13.911--13.894--13.878--13.864--13.850--13.838--13.745--13.690--13.652--13.626--13.605--13.590--13.577--13.520--13.486--13.475--");
      lineasTabFisher.add("5--16.258--13.274--12.060--11.392--10.967--10.672--10.456--10.289--10.158--10.051--9.963--9.888--9.825--9.770--9.722--9.680--9.643--9.609--9.580--9.553--9.528--9.506--9.485--9.466--9.449--9.433--9.418--9.404--9.391--9.379--9.291--9.238--9.202--9.176--9.157--9.142--9.130--9.075--9.042--9.032--");
      lineasTabFisher.add("6--13.745--10.925--9.780--9.148--8.746--8.466--8.260--8.102--7.976--7.874--7.790--7.718--7.657--7.605--7.559--7.519--7.483--7.451--7.422--7.396--7.372--7.351--7.331--7.313--7.296--7.281--7.266--7.253--7.240--7.229--7.143--7.091--7.057--7.032--7.013--6.998--6.987--6.934--6.901--6.891--");
      lineasTabFisher.add("7--12.246--9.547--8.451--7.847--7.460--7.191--6.993--6.840--6.719--6.620--6.538--6.469--6.410--6.359--6.314--6.275--6.240--6.209--6.181--6.155--6.132--6.111--6.092--6.074--6.058--6.043--6.029--6.016--6.003--5.992--5.908--5.858--5.824--5.799--5.781--5.766--5.755--5.702--5.671--5.660--");
      lineasTabFisher.add("8--11.259--8.649--7.591--7.006--6.632--6.371--6.178--6.029--5.911--5.814--5.734--5.667--5.609--5.559--5.515--5.477--5.442--5.412--5.384--5.359--5.336--5.316--5.297--5.279--5.263--5.248--5.234--5.221--5.209--5.198--5.116--5.065--5.032--5.007--4.989--4.975--4.963--4.911--4.880--4.869--");
      lineasTabFisher.add("9--10.562--8.022--6.992--6.422--6.057--5.802--5.613--5.467--5.351--5.257--5.178--5.111--5.055--5.005--4.962--4.924--4.890--4.860--4.833--4.808--4.786--4.765--4.746--4.729--4.713--4.698--4.684--4.672--4.660--4.649--4.567--4.517--4.483--4.459--4.441--4.426--4.415--4.363--4.332--4.321--");
      lineasTabFisher.add("10--10.044--7.559--6.552--5.994--5.636--5.386--5.200--5.057--4.942--4.849--4.772--4.706--4.650--4.601--4.558--4.520--4.487--4.457--4.430--4.405--4.383--4.363--4.344--4.327--4.311--4.296--4.283--4.270--4.258--4.247--4.165--4.115--4.082--4.058--4.039--4.025--4.014--3.962--3.930--3.920--");
      lineasTabFisher.add("11--9.646--7.206--6.217--5.668--5.316--5.069--4.886--4.744--4.632--4.539--4.462--4.397--4.342--4.293--4.251--4.213--4.180--4.150--4.123--4.099--4.077--4.057--4.038--4.021--4.005--3.990--3.977--3.964--3.952--3.941--3.860--3.810--3.776--3.752--3.734--3.719--3.708--3.656--3.624--3.613--");
      lineasTabFisher.add("12--9.330--6.927--5.953--5.412--5.064--4.821--4.640--4.499--4.388--4.296--4.220--4.155--4.100--4.052--4.010--3.972--3.939--3.910--3.883--3.858--3.836--3.816--3.798--3.780--3.765--3.750--3.736--3.724--3.712--3.701--3.619--3.569--3.535--3.511--3.493--3.478--3.467--3.414--3.382--3.372--");
      lineasTabFisher.add("13--9.074--6.701--5.739--5.205--4.862--4.620--4.441--4.302--4.191--4.100--4.025--3.960--3.905--3.857--3.815--3.778--3.745--3.716--3.689--3.665--3.643--3.622--3.604--3.587--3.571--3.556--3.543--3.530--3.518--3.507--3.425--3.375--3.341--3.317--3.298--3.284--3.272--3.219--3.187--3.176--");
      lineasTabFisher.add("14--8.862--6.515--5.564--5.035--4.695--4.456--4.278--4.140--4.030--3.939--3.864--3.800--3.745--3.698--3.656--3.619--3.586--3.556--3.529--3.505--3.483--3.463--3.444--3.427--3.412--3.397--3.383--3.371--3.359--3.348--3.266--3.215--3.181--3.157--3.138--3.124--3.112--3.059--3.026--3.015--");
      lineasTabFisher.add("15--8.683--6.359--5.417--4.893--4.556--4.318--4.142--4.004--3.895--3.805--3.730--3.666--3.612--3.564--3.522--3.485--3.452--3.423--3.396--3.372--3.350--3.330--3.311--3.294--3.278--3.264--3.250--3.237--3.225--3.214--3.132--3.081--3.047--3.022--3.004--2.989--2.977--2.923--2.891--2.880--");
      lineasTabFisher.add("16--8.531--6.226--5.292--4.773--4.437--4.202--4.026--3.890--3.780--3.691--3.616--3.553--3.498--3.451--3.409--3.372--3.339--3.310--3.283--3.259--3.237--3.216--3.198--3.181--3.165--3.150--3.137--3.124--3.112--3.101--3.018--2.967--2.933--2.908--2.889--2.875--2.863--2.808--2.775--2.764--");
      lineasTabFisher.add("17--8.400--6.112--5.185--4.669--4.336--4.101--3.927--3.791--3.682--3.593--3.518--3.455--3.401--3.353--3.312--3.275--3.242--3.212--3.186--3.162--3.139--3.119--3.101--3.083--3.068--3.053--3.039--3.026--3.014--3.003--2.920--2.869--2.835--2.810--2.791--2.776--2.764--2.709--2.676--2.664--");
      lineasTabFisher.add("18--8.285--6.013--5.092--4.579--4.248--4.015--3.841--3.705--3.597--3.508--3.434--3.371--3.316--3.269--3.227--3.190--3.158--3.128--3.101--3.077--3.055--3.035--3.016--2.999--2.983--2.968--2.955--2.942--2.930--2.919--2.835--2.784--2.749--2.724--2.705--2.690--2.678--2.623--2.589--2.577--");
      lineasTabFisher.add("19--8.185--5.926--5.010--4.500--4.171--3.939--3.765--3.631--3.523--3.434--3.360--3.297--3.242--3.195--3.153--3.116--3.084--3.054--3.027--3.003--2.981--2.961--2.942--2.925--2.909--2.894--2.880--2.868--2.855--2.844--2.761--2.709--2.674--2.649--2.630--2.614--2.602--2.547--2.512--2.501--");
      lineasTabFisher.add("20--8.096--5.849--4.938--4.431--4.103--3.871--3.699--3.564--3.457--3.368--3.294--3.231--3.177--3.130--3.088--3.051--3.018--2.989--2.962--2.938--2.916--2.895--2.877--2.859--2.843--2.829--2.815--2.802--2.790--2.778--2.695--2.643--2.608--2.582--2.563--2.548--2.535--2.479--2.445--2.433--");
      lineasTabFisher.add("21--8.017--5.780--4.874--4.369--4.042--3.812--3.640--3.506--3.398--3.310--3.236--3.173--3.119--3.072--3.030--2.993--2.960--2.931--2.904--2.880--2.857--2.837--2.818--2.801--2.785--2.770--2.756--2.743--2.731--2.720--2.636--2.584--2.548--2.523--2.503--2.488--2.476--2.419--2.384--2.372--");
      lineasTabFisher.add("22--7.945--5.719--4.817--4.313--3.988--3.758--3.587--3.453--3.346--3.258--3.184--3.121--3.067--3.019--2.978--2.941--2.908--2.879--2.852--2.827--2.805--2.785--2.766--2.749--2.733--2.718--2.704--2.691--2.679--2.667--2.583--2.531--2.495--2.469--2.450--2.434--2.422--2.365--2.329--2.317--");
      lineasTabFisher.add("23--7.881--5.664--4.765--4.264--3.939--3.710--3.539--3.406--3.299--3.211--3.137--3.074--3.020--2.973--2.931--2.894--2.861--2.832--2.805--2.780--2.758--2.738--2.719--2.702--2.686--2.671--2.657--2.644--2.632--2.620--2.536--2.483--2.447--2.421--2.401--2.386--2.373--2.316--2.280--2.268--");
      lineasTabFisher.add("24--7.823--5.614--4.718--4.218--3.895--3.667--3.496--3.363--3.256--3.168--3.094--3.032--2.977--2.930--2.889--2.852--2.819--2.789--2.762--2.738--2.716--2.695--2.676--2.659--2.643--2.628--2.614--2.601--2.589--2.577--2.492--2.440--2.403--2.377--2.357--2.342--2.329--2.271--2.235--2.223--");
      lineasTabFisher.add("25--7.770--5.568--4.675--4.177--3.855--3.627--3.457--3.324--3.217--3.129--3.056--2.993--2.939--2.892--2.850--2.813--2.780--2.751--2.724--2.699--2.677--2.657--2.638--2.620--2.604--2.589--2.575--2.562--2.550--2.538--2.453--2.400--2.364--2.337--2.317--2.302--2.289--2.230--2.194--2.182--");
      lineasTabFisher.add("26--7.721--5.526--4.637--4.140--3.818--3.591--3.421--3.288--3.182--3.094--3.021--2.958--2.904--2.857--2.815--2.778--2.745--2.715--2.688--2.664--2.642--2.621--2.602--2.585--2.569--2.554--2.540--2.526--2.514--2.503--2.417--2.364--2.327--2.301--2.281--2.265--2.252--2.193--2.156--2.144--");
      lineasTabFisher.add("27--7.677--5.488--4.601--4.106--3.785--3.558--3.388--3.256--3.149--3.062--2.988--2.926--2.872--2.824--2.783--2.746--2.713--2.683--2.656--2.632--2.609--2.589--2.570--2.552--2.536--2.521--2.507--2.494--2.481--2.470--2.384--2.330--2.294--2.267--2.247--2.231--2.218--2.159--2.122--2.109--");
      lineasTabFisher.add("28--7.636--5.453--4.568--4.074--3.754--3.528--3.358--3.226--3.120--3.032--2.959--2.896--2.842--2.795--2.753--2.716--2.683--2.653--2.626--2.602--2.579--2.559--2.540--2.522--2.506--2.491--2.477--2.464--2.451--2.440--2.354--2.300--2.263--2.236--2.216--2.200--2.187--2.127--2.090--2.077--");
      lineasTabFisher.add("29--7.598--5.420--4.538--4.045--3.725--3.499--3.330--3.198--3.092--3.005--2.931--2.868--2.814--2.767--2.726--2.689--2.656--2.626--2.599--2.574--2.552--2.531--2.512--2.495--2.478--2.463--2.449--2.436--2.423--2.412--2.325--2.271--2.234--2.207--2.187--2.171--2.158--2.097--2.060--2.047--");
      lineasTabFisher.add("30--7.562--5.390--4.510--4.018--3.699--3.473--3.305--3.173--3.067--2.979--2.906--2.843--2.789--2.742--2.700--2.663--2.630--2.600--2.573--2.549--2.526--2.506--2.487--2.469--2.453--2.437--2.423--2.410--2.398--2.386--2.299--2.245--2.208--2.181--2.160--2.144--2.131--2.070--2.032--2.019--");
      lineasTabFisher.add("40--7.314--5.178--4.313--3.828--3.514--3.291--3.124--2.993--2.888--2.801--2.727--2.665--2.611--2.563--2.522--2.484--2.451--2.421--2.394--2.369--2.346--2.325--2.306--2.288--2.271--2.256--2.241--2.228--2.215--2.203--2.114--2.058--2.019--1.991--1.969--1.952--1.938--1.874--1.833--1.819--");
      lineasTabFisher.add("50--7.171--5.057--4.199--3.720--3.408--3.186--3.020--2.890--2.785--2.698--2.625--2.563--2.508--2.461--2.419--2.382--2.348--2.318--2.290--2.265--2.242--2.221--2.202--2.183--2.167--2.151--2.136--2.123--2.110--2.098--2.007--1.949--1.909--1.880--1.857--1.839--1.825--1.757--1.713--1.698--");
      lineasTabFisher.add("60--7.077--4.977--4.126--3.649--3.339--3.119--2.953--2.823--2.718--2.632--2.559--2.496--2.442--2.394--2.352--2.315--2.281--2.251--2.223--2.198--2.175--2.153--2.134--2.115--2.098--2.083--2.068--2.054--2.041--2.028--1.936--1.877--1.836--1.806--1.783--1.764--1.749--1.678--1.633--1.617--");
      lineasTabFisher.add("70--7.011--4.922--4.074--3.600--3.291--3.071--2.906--2.777--2.672--2.585--2.512--2.450--2.395--2.348--2.306--2.268--2.234--2.204--2.176--2.150--2.127--2.106--2.086--2.067--2.050--2.034--2.019--2.005--1.992--1.980--1.886--1.826--1.785--1.754--1.730--1.711--1.695--1.622--1.574--1.558--");
      lineasTabFisher.add("80--6.963--4.881--4.036--3.563--3.255--3.036--2.871--2.742--2.637--2.551--2.478--2.415--2.361--2.313--2.271--2.233--2.199--2.169--2.141--2.115--2.092--2.070--2.050--2.032--2.015--1.999--1.983--1.969--1.956--1.944--1.849--1.788--1.746--1.714--1.690--1.671--1.655--1.579--1.530--1.512--");
      lineasTabFisher.add("90--6.925--4.849--4.007--3.535--3.228--3.009--2.845--2.715--2.611--2.524--2.451--2.389--2.334--2.286--2.244--2.206--2.172--2.142--2.114--2.088--2.065--2.043--2.023--2.004--1.987--1.971--1.956--1.942--1.928--1.916--1.820--1.759--1.716--1.684--1.659--1.639--1.623--1.546--1.494--1.476--");
      lineasTabFisher.add("100--6.895--4.824--3.984--3.513--3.206--2.988--2.823--2.694--2.590--2.503--2.430--2.368--2.313--2.265--2.223--2.185--2.151--2.120--2.092--2.067--2.043--2.021--2.001--1.983--1.965--1.949--1.934--1.919--1.906--1.893--1.797--1.735--1.692--1.659--1.634--1.614--1.598--1.518--1.466--1.447--");
      lineasTabFisher.add("200--6.763--4.713--3.881--3.414--3.110--2.893--2.730--2.601--2.497--2.411--2.338--2.275--2.220--2.172--2.129--2.091--2.057--2.026--1.997--1.971--1.947--1.925--1.905--1.886--1.868--1.851--1.836--1.821--1.807--1.794--1.694--1.629--1.583--1.548--1.521--1.499--1.481--1.391--1.328--1.304--");
      lineasTabFisher.add("500--6.686--4.648--3.821--3.357--3.054--2.838--2.675--2.547--2.443--2.356--2.283--2.220--2.166--2.117--2.075--2.036--2.002--1.970--1.942--1.915--1.891--1.869--1.848--1.829--1.810--1.794--1.778--1.763--1.749--1.735--1.633--1.566--1.517--1.481--1.452--1.428--1.408--1.308--1.232--1.201--");
      lineasTabFisher.add("1000--6.660--4.626--3.801--3.338--3.036--2.820--2.657--2.529--2.425--2.339--2.265--2.203--2.148--2.099--2.056--2.018--1.983--1.952--1.923--1.897--1.872--1.850--1.829--1.810--1.791--1.774--1.758--1.743--1.729--1.716--1.613--1.544--1.495--1.458--1.428--1.404--1.383--1.278--1.195--1.159--");

      tabFisher0_99 = new double[lineasTabFisher.size()][41];
      this.linea_aux2 = null;

      for(int i = 0; i<lineasTabFisher.size(); i++){
          linea_aux2 = lineasTabFisher.get(i).split("\\-\\-");
          for(int j = 0; j<linea_aux2.length; j++){
              this.tabFisher0_99[i][j] = Double.parseDouble(linea_aux2[j]);
          }
          linea_aux2 = null;
      }

  }

}
