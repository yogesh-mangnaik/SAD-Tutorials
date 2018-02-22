package com.mangnaik.yogesh.tutorial2.server;

/**
 * Created by Yogesh on 2/8/2018.
 */
public interface CalculatorInterface {

    //basic
    public double add(double a, double b);
    public double subtract(double a, double b);
    public double multiply(double a, double b);
    public double divide(double a, double b);

    public double evaluate(String query);
}
