package com.mangnaik.yogesh.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import com.mangnaik.yogesh.calculator.Calculator;

/**
 * Created by Yogesh on 2/2/2018.
 */
public class Server {

    private ServerSocket socket;
    private List<ClientHandler> clients = new ArrayList<>();

    private Server(){
        Calculator calculator = new Calculator();
        init();
    }

    private void init(){
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
                clients.add(new ClientHandler(s));
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args){
        new Server();
    }
}