package com.mangnaik.yogesh.tutorial2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Yogesh on 2/8/2018.
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
                String query = null;
                try {
                    query = networkManager.receive();
                    history += query + "\n";
                    System.out.println("Request from : " + networkManager.getPort());
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
                System.out.println(query);
                if(!query.equals("exit")){
                    String answer = calculator.evaluate(query) + "";
                    history += answer + "\n";
                    try {
                        networkManager.send(answer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        networkManager.send(history);
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }
}
