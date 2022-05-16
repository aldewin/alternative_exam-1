package com.company;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.SocketOption;

public class Main {
    public static void main(String[] args) {
        startPolynomial test = new startPolynomial();



        test.setRandomRoots();
        test.setZeroCoef();
        test.getCoefFromRoots(test.randomRoots, test.polyСoefficient, test.startPolyPow,BigDecimal.valueOf(1.),0);
        test.showCoef();
        test.setResultCofPoly();
        test.getRoots();
        test.printans();


    }


}
