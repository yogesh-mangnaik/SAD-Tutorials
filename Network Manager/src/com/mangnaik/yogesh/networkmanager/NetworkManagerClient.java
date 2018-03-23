package com.mangnaik.yogesh.networkmanager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkManagerClient {

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private int port;
    private String host;

    public NetworkManagerClient(String host, int port){
        this.port = port;
        this.host = host;
    }

    private Socket getSocket() throws IOException {
        return new Socket(host, port);
    }

    public void createConnection(){
        try {
            socket = getSocket();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String send(String query) {
        String answer = "";
        try {
            dos.writeUTF(query);
            answer = dis.readUTF();
        } catch (IOException e) {
            close();
            e.printStackTrace();
        }
        return answer;
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
