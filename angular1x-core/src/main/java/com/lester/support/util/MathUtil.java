package com.lester.support.util;

public class MathUtil {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public static double round2(double d, int scale) {
        long temp = 1;
        for (int i = scale; i > 0; i--) {
            temp *= 10;
        }
        d *= temp;
        long dl = Math.round(d);
        return (double) (dl) / temp;
    }
}
