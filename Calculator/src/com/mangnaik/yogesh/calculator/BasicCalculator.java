package com.mangnaik.yogesh.calculator;

/**
 * Created by Yogesh on 2/10/2018.
 */
public class BasicCalculator implements BasicCalculatorInterface{

    @Override
    public double add(double a, double b) {
        return a+b;
    }
    @Override
    public double subtract(double a, double b) {
        return b-a;
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
            case "+":
                return add(values[0], values[1]);
            case "-":
                return subtract(values[1], values[0]);
            case "*":
                return multiply(values[0], values[1]);
            case "/":
                return divide(values[1], values[0]);
        }
        return 0;
    }
}
