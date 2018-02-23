package com.mangnaik.yogesh.calculator;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Yogesh on 2/7/2018.
 */

public class Calculator{

    //call to evaluate the string query
    public double evaluate(String query) {
        query = query.replaceAll("pi", Math.PI+"");
        List<String> list = new ArrayList<>();
        query = normalize(query);
        convertToPostFix(list, query);
        return evaluate(list);
    }

    //evaluate the postfixed expression stored in list
    private double evaluate(@NotNull List<String> list){
        Stack<String> stack = new Stack<>();
        for (String aList : list) {
            if (aList.equals("+") || aList.equals("-") || aList.equals("*") || aList.equals("/")) {
                double a = Double.parseDouble(stack.pop());
                double b = Double.parseDouble(stack.pop());
                double[] values = new double[]{a, b};
                stack.push("" + calculate(values, aList.charAt(0) + ""));
            } else {
                stack.push(aList);
            }
        }
        return Double.valueOf(stack.pop());
    }

    //evaluate and replace all the trigonometric and statistical terms
    private String normalize(String query){
        String[] functions = new String[]{"sin", "cos", "tan", "atan", "asin", "acos", "log"};
        for (String function : functions) {
            while (query.contains(function)) {
                int index = query.indexOf(function);
                int start = index + function.length();
                int end = start;
                int count = 0;
                for (int j = start; j < query.length(); j++) {
                    end++;
                    if (query.charAt(j) == '(') {
                        count++;
                    } else if (query.charAt(j) == ')') {
                        count--;
                        if (count == 0) {
                            break;
                        }
                    }
                }
                String subquery = query.substring(start, end);
                query = query.substring(0, index) + calculate(new double[]{evaluate(subquery)}, function) + query.substring(end, query.length());
            }
        }
        functions = new String[]{"min", "max", "avg", "sum"};
        for (String function : functions) {
            while (query.contains(function)) {
                int index = query.indexOf(function);
                int start = index + 3;
                int end = start;
                int count = 0;
                for (int j = start; j < query.length(); j++) {
                    end++;
                    if (query.charAt(j) == '{') {
                        count++;
                    } else if (query.charAt(j) == '}') {
                        count--;
                        if (count == 0) {
                            break;
                        }
                    }
                }
                String subquery = query.substring(start + 1, end - 1);
                double[] values = splitWithComma(subquery);
                query = query.substring(0, index) + calculate(values, function) + query.substring(end, query.length());
            }
        }
        return query;
    }

    //convert the string to postfix and store the result in a list
    private void convertToPostFix(List<String> list, String query) {
        Stack<Character> operators = new Stack<>();
        char[] tokens = query.toCharArray();

        for(int i=0; i<tokens.length; i++){
            if (tokens[i]>='0'&&tokens[i]<='9'){
                StringBuilder stringBuilder = new StringBuilder();
                while (i < tokens.length && (tokens[i]=='.' || (tokens[i] >= '0' && tokens[i] <= '9'))){
                    stringBuilder.append(tokens[i++]);
                }
                i--;
                list.add(stringBuilder.toString());
            }

            else if(tokens[i] == '(')
                operators.push('(');

            else if (tokens[i] == ')'){
                while (operators.peek() != '(')
                    list.add(operators.pop()+"");
                operators.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'){
                while (!operators.empty() && hasPrecedence(tokens[i], operators.peek())){
                    list.add(operators.pop()+"");
                }
                operators.push(tokens[i]);
            }
        }
        while (!operators.empty())
            list.add(operators.pop()+"");
    }

    //check for precedence
    private boolean hasPrecedence(char op1, char op2) {
        return op2 != '(' && op2 != ')' && ((op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-'));
    }

    //call the evalutate function of calculators
    private double calculate(double[] values, String function){
        BasicCalculatorInterface calculator = CalculatorFactory.getCalculator(function);
        return calculator.calculate(values, function);
    }

    //splitting with comma
    private double[] splitWithComma(String subquery){
        List<String> elementList = new ArrayList<>();
        int bracketCount = 0;
        int s = 0;
        for(int k=0; k<subquery.length(); k++){
            if(subquery.charAt(k)=='{'){
                bracketCount++;
            }
            else if(subquery.charAt(k)=='}'){
                bracketCount--;
            }
            if(bracketCount==0){
                if(subquery.charAt(k)==','){
                    String st = subquery.substring(s,k);
                    elementList.add(st);
                    s=k+1;
                }
            }
        }
        if(!subquery.substring(s,subquery.length()).equals("")){
            String st = subquery.substring(s, subquery.length());
            elementList.add(st);
        }
        String[] elements = new String[elementList.size()];
        for(int k=0; k<elementList.size(); k++){
            elements[k] = elementList.get(k);
        }
        double[] values = new double[elements.length];
        for(int j=0; j<values.length; j++){
            values[j] = evaluate(elements[j]);
        }
        return values;
    }
}