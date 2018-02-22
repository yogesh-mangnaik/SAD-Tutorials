package com.mangnaik.yogesh.tutorial2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yogesh on 2/8/2018.
 */
public class Server {
    ServerSocket socket;
    List<Client> clients = new ArrayList<>();
    Calculator calculator;

    public Server(){
        calculator = new Calculator();
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
