package com.mangnaik.yogesh.calculator;

/**
 * Created by Yogesh on 2/7/2018.
 */
public class StatisticalCalculator implements StatisticalCalculatorInterface{

    @Override
    public double add(double a, double b) {
        return a+b;
    }
    @Override
    public double subtract(double a, double b) {
        return a-b;
    }
    @Override
    public double multiply(double a, double b) {
        return a*b;
    }
    @Override
    public double divide(double a, double b) {
        return a/b;
    }
    @Override
    public double average(double[] values) {
        double sum = 0;
        for (double number : values) {
            sum += number;
        }
        return sum/values.length;
    }
    @Override
    public double max(double[] values) {
        double max = -Integer.MAX_VALUE;
        for (double number : values) {
            if (number > max) {
                max = number;
            }
        }
        return max;
    }
    @Override
    public double min(double[] values) {
        double min = Integer.MAX_VALUE;
        for (double number : values) {
            if (number < min) {
                min = number;
            }
        }
        return min;
    }
    @Override
    public double sum(double[] values) {
        double sum = 0;
        for (double number : values) {
            sum += number;
        }
        return sum;
    }

    @Override
    public double calculate(double[] values, String function) {
        switch (function){
            case "max":
                return max(values);
            case "min":
                return min(values);
            case "avg":
                return average(values);
            case "sum":
                return sum(values);
        }
        return 0;
    }
}
