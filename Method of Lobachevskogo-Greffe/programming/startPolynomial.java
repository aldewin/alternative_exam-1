package com.company;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.*;
import java.util.Scanner;

public class startPolynomial {
    long start,finish;
     int cyclesCount = 0;
     int startPolyPow = 15;
     BigDecimal[] polyСoefficient = new BigDecimal[startPolyPow + 1];
    BigDecimal[] randomRoots = new BigDecimal[startPolyPow];

    public void setZeroCoef(){
        for (int k = 0;k <= startPolyPow;k++){
            polyСoefficient[k] = BigDecimal.valueOf(0.);
        }
    }

    public int setSign(){
        double forSign = Math.random();
        if (forSign > 0.5){
            return 1;
        } else {
            return -1;
        }
    }

    public void setRandomRoots(){

        System.out.println("Случайные корни :");
        for (int i = 0; i < startPolyPow;i++){
            randomRoots[i] = BigDecimal.valueOf((i + 1 + Math.random()) * setSign());
            System.out.println("x" + (i + 1) + "= " + randomRoots[i]);
        }

    }

    public void getCoefFromRoots(BigDecimal[] a, BigDecimal[] c, int i,BigDecimal current, int p) {

        if (i > 0) {
            // Выбрать в качестве множителя X, current * 1, степень при X увеличивается на 1.
            getCoefFromRoots(a, c, i - 1, current, p + 1);

            // Выбрать в качестве множителя a[i], current * a[i], степень при X не изменяется.
            getCoefFromRoots(a, c, i - 1, current.multiply(a[i - 1]).multiply(BigDecimal.valueOf(-1)), p);
        } else {
            c[p] = c[p].add(current);
        }

    }//получаем коэффициенты из рандомно заданных корней

    public void showCoef(){
        System.out.println("Коэффициенты полинома ");
        BigDecimal[] specValue = new BigDecimal[startPolyPow+1];
        for (int i = 0;i<=startPolyPow;i++){
            specValue[startPolyPow - i] = polyСoefficient[i];
        }
    for (int i = 0;i<=startPolyPow;i++){
        polyСoefficient[i] = specValue[i];
        System.out.println(polyСoefficient[i].setScale(30, RoundingMode.HALF_UP) + "x^" + (startPolyPow - i));
    }
}


    BigDecimal[] specStartPoly = new BigDecimal[startPolyPow + 1];
    BigDecimal[] specResultPoly = new BigDecimal[startPolyPow + 1];

    public void setResultCofPoly() {
        start = System.currentTimeMillis();
        for (int n = 0;n <=startPolyPow;n++){
            specStartPoly[n] = polyСoefficient[n];
        }
        boolean chekLoopExit = true;
        while (chekLoopExit){
            this.specResultPoly[0] = BigDecimal.valueOf(1);
            for(int k = 1; k<=startPolyPow; k++){
                specResultPoly[k] = specStartPoly[k].pow(2);
                for (int j = 1;j <= k; j++){
                    if(k - j < 0 || k + j > startPolyPow){
                        break;
                    }
                    specResultPoly[k] = specResultPoly[k].add(specStartPoly[k - j].multiply(specStartPoly[k + j].multiply(BigDecimal.valueOf(2 * Math.pow(-1,j)))).setScale(30, RoundingMode.HALF_UP));
                }
            }

            for (int i = 0; i <=startPolyPow;i++){
                specResultPoly[i] = specResultPoly[i].multiply(BigDecimal.valueOf(Math.pow(-1,i)));
            }

            int unsatisfactoryСonditionCount = 0;//проверка выхода из цикла
            for (int i = 1; i <=startPolyPow;i++){

                if(specResultPoly[i].divide(specStartPoly[i].pow(2),30, RoundingMode.HALF_UP).abs().subtract(BigDecimal.valueOf(1)).abs().compareTo(BigDecimal.valueOf(0.000001)) == 1){
                    unsatisfactoryСonditionCount++;
                }
            }

            if (unsatisfactoryСonditionCount == 0){
                chekLoopExit = false;
            }


            for (int i = 0;i <= startPolyPow;i++){
                specStartPoly[i] = specResultPoly[i].setScale(30, RoundingMode.HALF_UP);


            }

            cyclesCount++;
        }

    }//

    BigDecimal[] asbRootArr = new BigDecimal[startPolyPow];
    public void getRoots(){
        asbRootArr[0] = specResultPoly[1].multiply(BigDecimal.valueOf(-1));

        for (int i = 1;i < startPolyPow; i++){
            asbRootArr[i] = specResultPoly[i+1].divide(specResultPoly[i],30, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(-1));

        }


        MathContext mc = new MathContext(30);
        for (int i = 0;i < startPolyPow;i++){
            for (int k = 0;k < cyclesCount;k++){
                asbRootArr[i] = asbRootArr[i].sqrt(mc);
            }

        }
    }

    public void printans() {
        System.out.println("------------");
        for (int k = 0; k < startPolyPow; k++) {
            BigDecimal testroot = BigDecimal.valueOf(0);
            for (int i = 0; i <= startPolyPow; i++) {
                testroot = testroot.add(polyСoefficient[i].multiply(asbRootArr[k].pow(startPolyPow - i)));
            }
            if (testroot.abs().compareTo(BigDecimal.valueOf(0.5)) == -1) {
                System.out.println(asbRootArr[k]);
            } else {
                System.out.println(asbRootArr[k].multiply(BigDecimal.valueOf(-1)));
            }
        }
        System.out.println("количество циклов " + cyclesCount);
        finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Прошло времени, мс: " + elapsed);
    }
    public startPolynomial(){

}
}
