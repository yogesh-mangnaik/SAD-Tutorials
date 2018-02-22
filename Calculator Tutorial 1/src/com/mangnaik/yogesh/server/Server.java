package com.mangnaik.yogesh.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Yogesh on 2/8/2018.
 */
public class Server {
    ServerSocket socket;
    Calculator calculator;
    DataInputStream dis;
    DataOutputStream dos;
    NetworkManager networkManager;

    public Server(){
        calculator = new Calculator();
        System.out.println(calculator.evaluate("5+6"));
        start();
    }

    public void start(){
        try {
            socket = new ServerSocket(8192);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create Server!!");
        }
        Socket s;
        try{
            s = socket.accept();
            System.out.println("A new client has connected");
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            networkManager = new NetworkManager(dis, dos, s);
            String query = "";
            while(!query.equals("exit")){
                query = networkManager.receive();
                System.out.println("Inside query : " + query);
                double answer = calculator.evaluate(query);
                networkManager.send(answer + "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        new Server();
    }
}
