package com.mangnaik.yogesh.calculator;

/**
 * Created by Yogesh on 2/2/2018.
 */
public interface BasicCalculatorInterface {
    public double add(double a, double b);
    public double subtract(double a, double b);
    public double multiply(double a, double b);
    public double divide(double a, double b);

    public double calculate(double[] values, String function);
}
