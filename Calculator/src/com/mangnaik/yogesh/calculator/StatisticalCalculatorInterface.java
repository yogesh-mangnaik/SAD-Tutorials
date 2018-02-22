package com.mangnaik.yogesh.calculator;

/**
 * Created by Yogesh on 2/3/2018.
 */
public interface StatisticalCalculatorInterface extends BasicCalculatorInterface {

    public double average(double[] numbers);
    public double max(double[] numbers);
    public double min(double[] numbers);
    public double sum(double[] numbers);
}
