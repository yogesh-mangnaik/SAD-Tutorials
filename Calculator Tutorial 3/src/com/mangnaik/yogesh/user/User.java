package com.mangnaik.yogesh.user;

import com.mangnaik.yogesh.networkmanager.NetworkManagerClient;

/**
 * Created by Yogesh on 2/2/2018.
 */
public class User {

    private UserUI ui;
    private NetworkManagerClient networkManager;

    public static void main(String args[]) {
        new User();
    }

    private User() {
        ui = new UserUI();
        networkManager = new NetworkManagerClient("localhost", 8192);
        init();
    }

    private void init(){
        networkManager.createConnection();
        String query = "";
        while(!query.equals("exit")){
            query = ui.getInput();
            if(!query.equals("")){
                String ans;
                ans = networkManager.send(query);
                ui.showResult(ans);
            }
        }
    }
}
