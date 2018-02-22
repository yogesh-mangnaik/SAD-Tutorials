import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkManager {
    private DataInputStream dis;
    private DataOutputStream dos;
    private Socket socket;
    final String host;
    final int port;

    public NetworkManager(DataInputStream dis, DataOutputStream dos, Socket socket){
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
        this.host = "";
        this.port = 0;
    }

    public NetworkManager(String host, int port){
        this.port = port;
        this.host = host;
    }

    public void createConnection(){

    }

    public String receive(){
        String received;
        try {
            received = dis.readUTF();
            System.out.println("Received String : " + received);
            return received;
        } catch (IOException e) {
            System.out.println("Disconnected");
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

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
