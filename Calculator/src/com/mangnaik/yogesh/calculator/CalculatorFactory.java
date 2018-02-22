package com.mangnaik.yogesh.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yogesh on 2/7/2018.
 */
public class CalculatorFactory {

    private static List<String> scientificFunctions;
    private static List<String> basicFunctions;
    private static List<String> statisticalFunctions;

    static{
        scientificFunctions = new ArrayList<>();
        basicFunctions = new ArrayList<>();
        statisticalFunctions = new ArrayList<>();
        scientificFunctions.add("sin");
        scientificFunctions.add("cos");
        scientificFunctions.add("tan");
        basicFunctions.add("+");
        basicFunctions.add("-");
        basicFunctions.add("*");
        basicFunctions.add("/");
        statisticalFunctions.add("max");
        statisticalFunctions.add("min");
        statisticalFunctions.add("avg");
        statisticalFunctions.add("sum");
    }
    static BasicCalculatorInterface getCalculator(String function){
        BasicCalculatorInterface basicCalculatorInterface = null;
        if(scientificFunctions.contains(function)){
            basicCalculatorInterface = new ScientificCalculator();
        }
        else if(basicFunctions.contains(function)){
            basicCalculatorInterface = new BasicCalculator();
        }
        else if(statisticalFunctions.contains(function)){
            basicCalculatorInterface = new StatisticalCalculator();
        }
        return basicCalculatorInterface;
    }
}
