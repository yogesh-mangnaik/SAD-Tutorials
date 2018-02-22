package com.mangnaik.yogesh.tutorial2.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Yogesh on 2/8/2018.
 */
public class NetworkManager {

    Socket socket = null;
    DataInputStream din = null;
    DataOutputStream dout = null;
    int port;
    String host;

    public NetworkManager(String host, int port){
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
            System.out.println ("Failed to Create Socket");
            return;
        }
        try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the server");
            return;
        }
    }
    public String query(String query) throws IOException {
        dout.writeUTF(query);
        return din.readUTF();
    }
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
