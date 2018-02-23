package com.mangnaik.yogesh.networkmanager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkManagerServer {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;

    public NetworkManagerServer(Socket socket) {
        this.socket = socket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String listen(){
        String received;
        try {
            received = dis.readUTF();
            return received;
        } catch (IOException e) {
            close();
            e.printStackTrace();
        }
        return "";
    }

    public void send(String answer){
        try{
            dos.writeUTF(answer);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
