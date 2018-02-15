package com.mangnaik.yogesh.server;

public interface ScientificCalculatorInterface extends BasicCalculatorInterface {

    //Trigonometric
    public double sin(double angle);
    public double cos(double angle);
    public double tan(double angle);

    //Inverse Trigonometric
    public double asin(double angle);
    public double acos(double angle);
    public double atan(double angle);

    //Logarithmic
    public double log(double number);
}
