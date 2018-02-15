package com.mangnaik.yogesh.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yogesh on 2/2/2018.
 */
public class Server {

    ServerSocket socket;
    List<Client> clients = new ArrayList<>();

    public Server(){
        Calculator calculator = new Calculator();
        double ans = calculator.evaluate("asin(1)");
        System.out.println(ans);
        init();
    }

    public void init(){
        try {
            socket = new ServerSocket(8192);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to create Server!!");
        }
        while(true){
            Socket s;
            try{
                s = socket.accept();
                System.out.println("A new client has connected");
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                clients.add(new Client(dis, dos, s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new Server();
    }
}