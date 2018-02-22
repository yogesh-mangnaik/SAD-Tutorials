package com.mangnaik.yogesh.calculator;

import java.io.IOException;

/**
 * Created by Yogesh on 2/2/2018.
 */
public class User {

    UserUI ui;
    Calculator calculator;

    public static void main(String args[]) {
        new User();
    }

    public User() {
        ui = new UserUI();
        calculator = new Calculator();
        init();
    }

    private void init(){
        String query = "";
        while(!query.equals("exit")){
            query = ui.getInput();
            if(!query.equals("")){
                double answer = calculator.evaluate(query);
                ui.showResult(answer+"");
            }
        }
    }
}
