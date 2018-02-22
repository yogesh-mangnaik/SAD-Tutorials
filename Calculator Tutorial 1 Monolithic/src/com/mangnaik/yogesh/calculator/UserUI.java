package com.mangnaik.yogesh.calculator;

import java.util.Scanner;

/**
 * Created by Yogesh on 2/15/2018.
 */
public class UserUI {
    Scanner scan = new Scanner(System.in);

    public String getInput(){
        String query = scan.nextLine();
        return query;
    }

    public void showResult(String answer){
        System.out.println(answer);
    }
}
