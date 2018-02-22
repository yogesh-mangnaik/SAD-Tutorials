package com.mangnaik.yogesh.tutorial2.user;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Yogesh on 2/8/2018.
 */
public class User {
    Scanner scan = new Scanner(System.in);
    UserUI ui;

    public static void main(String args[]) {
        new User();
    }

    public User() {
        ui = new UserUI();
        start();
    }

    public void start(){
        NetworkManager networkManager = new NetworkManager("localhost", 8192);
        networkManager.createConnection();
        String query = "";
        while(!query.equals("exit")){
            query = ui.getInput();
            if(!query.equals("")){
                String ans;
                try {
                    ans = networkManager.query(query);
                } catch (IOException e) {
                    ui.showResult("Connection Reset");
                    networkManager.close();
                    return;
                }
                ui.showResult(ans);
            }
        }
    }
}
