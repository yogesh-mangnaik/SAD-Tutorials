package com.mangnaik.yogesh.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import com.mangnaik.yogesh.calculator.Calculator;

/**
 * Created by Yogesh on 2/2/2018.
 */
public class Client {

    private Thread thread;
    private Runnable runnable;
    private String history;
    private Calculator calculator;

    public Client(DataInputStream dis, DataOutputStream dos, Socket socket) {
        init(dis, dos, socket);
    }

    public void init(DataInputStream dis, DataOutputStream dos, Socket socket){
        NetworkManager networkManager = new NetworkManager(dis, dos, socket);
        calculator = new Calculator();
        history = "";
        runnable = () -> {
            while (true) {
                System.out.println("Client is receiving");
                String query = networkManager.receive();
                System.out.println(query);
                double answer = calculator.evaluate(query);
                networkManager.send(answer+"");
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }
}

