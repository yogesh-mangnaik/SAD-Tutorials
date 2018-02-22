package com.mangnaik.yogesh.calculator;

/**
 * Created by Yogesh on 2/3/2018.
 */
public class ScientificCalculator implements ScientificCalculatorInterface{

    @Override
    public double sin(double angle) {
        System.out.println("sin + " + angle);
        System.out.println(Math.sin(angle));
        return Math.sin(angle);
    }
    @Override
    public double cos(double angle) {
        return Math.cos(angle);
    }
    @Override
    public double tan(double angle) {
        return Math.tan(angle);
    }
    @Override
    public double asin(double value) {
        return Math.asin(value);
    }
    @Override
    public double acos(double value) {
        return Math.acos(value);
    }
    @Override
    public double atan(double value) {
        return Math.atan(value);
    }
    @Override
    public double log(double number) {
        return Math.log(number);
    }
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
    public double calculate(double[] values, String function) {
        switch(function){
            case "sin":
                return sin(values[0]);
            case "cos":
                return cos(values[0]);
            case "tan":
                return tan(values[0]);
            case "asin":
                return asin(values[0]);
            case "acos":
                return acos(values[0]);
            case "atan":
                return atan(values[0]);
            case "log":
                return log(values[0]);
        }
        return 0;
    }

}
