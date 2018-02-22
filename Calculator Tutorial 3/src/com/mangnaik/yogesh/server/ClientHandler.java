package com.mangnaik.yogesh.server;

import com.mangnaik.yogesh.calculator.Calculator;
import com.mangnaik.yogesh.networkmanager.NetworkManagerServer;

import java.net.Socket;

/**
 * Created by Yogesh on 2/2/2018.
 */
class ClientHandler {

    private Thread thread;
    private Runnable runnable;
    private String history;
    private Calculator calculator;

    ClientHandler(Socket socket) {
        init(socket);
    }

    private void init(Socket socket){
        NetworkManagerServer networkManager = new NetworkManagerServer(socket);
        calculator = new Calculator();
        history = "";
        runnable = () -> {
            while (true) {
                System.out.println("ClientHandler is receiving");
                String query = networkManager.listen();
                System.out.println(query);
                double answer = calculator.evaluate(query);
                networkManager.send(answer+"");
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }
}

