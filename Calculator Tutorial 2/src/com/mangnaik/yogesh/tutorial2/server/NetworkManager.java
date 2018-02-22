package com.mangnaik.yogesh.tutorial2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Yogesh on 2/8/2018.
 */
public class NetworkManager {
    final private DataInputStream dis;
    final private DataOutputStream dos;
    final private Socket socket;

    public NetworkManager(DataInputStream dis, DataOutputStream dos, Socket socket){
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
    }

    public int getPort(){
        return socket.getPort();
    }

    public String receive() throws IOException{
        String received;
        received = dis.readUTF();
        if (received.equals("Hello")) {
            System.out.println("Working");
            return "Hello";
        }
        else{
            System.out.println(received);
            return received;
        }
    }

    public void send(String answer) throws IOException {
        dos.writeUTF(answer);
    }
}
