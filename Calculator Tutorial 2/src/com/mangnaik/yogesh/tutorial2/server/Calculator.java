package com.mangnaik.yogesh.tutorial2.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Yogesh on 2/8/2018.
 */
public class Calculator implements CalculatorInterface {
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
    public double evaluate(String query) {
        query = query.replaceAll("pi", "3.1415926535");
        List<String> list = new ArrayList<>();
        //query = normalize(query);
        convertToPostFix(list, query);
        return evaluate(list);
    }

    private double evaluate(List<String> list){
        Stack<String> stack = new Stack<>();
        for(int i=0; i<list.size(); i++){
            if(list.get(i).equals("+")||list.get(i).equals("-")||list.get(i).equals("*")||list.get(i).equals("/")){
                double a = Double.parseDouble(stack.pop());
                double b = Double.parseDouble(stack.pop());
                stack.push(""+calculate(a,b,list.get(i).charAt(0)+""));
            }
            else{
                stack.push(list.get(i));
            }
        }
        return Double.valueOf(stack.pop());
    }

//    private String normalize(String query){
//        String[] functions = new String[]{"sin", "cos", "tan"};
//        for (String function : functions) {
//            while (query.contains(function)) {
//                int index = query.indexOf(function);
//                int start = index + function.length();
//                int end = start;
//                int count = 0;
//                for (int j = start; j < query.length(); j++) {
//                    end++;
//                    if (query.charAt(j) == '(') {
//                        count++;
//                    } else if (query.charAt(j) == ')') {
//                        count--;
//                        if (count == 0) {
//                            break;
//                        }
//                    }
//                }
//                String subquery = query.substring(start, end);
//                query = query.substring(0, index) + calculate(evaluate(subquery), function) + query.substring(end, query.length());
//            }
//        }
//        functions = new String[]{"min", "max", "avg", "sum"};
//        for (String function : functions) {
//            while (query.contains(function)) {
//                int index = query.indexOf(function);
//                int start = index + 3;
//                int end = start;
//                int count = 0;
//                for (int j = start; j < query.length(); j++) {
//                    end++;
//                    if (query.charAt(j) == '{') {
//                        count++;
//                    } else if (query.charAt(j) == '}') {
//                        count--;
//                        if (count == 0) {
//                            break;
//                        }
//                    }
//                }
//                String subquery = query.substring(start + 1, end - 1);
//                double[] values = splitWithComma(subquery);
//                query = query.substring(0, index) + calculate(values, function) + query.substring(end, query.length());
//            }
//        }
//        return query;
//    }

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

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    //calling arithmetic functions
    private double calculate(double a, double b, String function){
        switch (function){
            case "+":
                return add(a,b);
            case "-":
                return subtract(b,a);
            case "*":
                return multiply(a,b);
            case "/":
                return divide(b,a);
        }
        return 0;
    }
}
