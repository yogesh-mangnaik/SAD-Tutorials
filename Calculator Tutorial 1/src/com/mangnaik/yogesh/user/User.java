package com.mangnaik.yogesh.user;

import java.io.IOException;

/**
 * Created by Yogesh on 2/2/2018.
 */
public class User {

    UserUI ui;
    NetworkManager networkManager;

    public static void main(String args[]) {
        new User();
    }

    public User() {
        ui = new UserUI();
        init();
    }

    private void init(){
        networkManager = new NetworkManager("localhost", 8192);
        networkManager.createConnection();
        String query = "";
        while(!query.equals("exit")){
            query = ui.getInput();
            System.out.println("Query : " + query);
            if(!query.equals("")){
                String ans;
                try {
                    ans = networkManager.query(query);
                } catch (IOException e) {
                    System.out.println("Connection Reset");
                    networkManager.close();
                    return;
                }
                ui.showResult(ans);
            }
        }
    }
}
