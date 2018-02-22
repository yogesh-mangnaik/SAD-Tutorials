package com.mangnaik.yogesh.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkManager {
    final private DataInputStream dis;
    final private DataOutputStream dos;
    final private Socket socket;

    public NetworkManager(DataInputStream dis, DataOutputStream dos, Socket socket){
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
    }

    public String receive(){
        String received;
        try {
            received = dis.readUTF();
            System.out.println("Received String : " + received);
            return received;
        } catch (IOException e) {
            System.out.println("Client Disconnected");
            try {
                socket.close();
                return "";
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return "";
    }

    public void send(String answer){
        try{
            System.out.println("Sending String" + answer);
            dos.writeUTF(answer);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
