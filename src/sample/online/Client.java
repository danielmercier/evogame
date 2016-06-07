package sample.online;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Closeable {
    public Socket getSocket() {
        return socket;
    }

    private Socket socket;
    public Client(InetAddress address, Integer port) {
        try {
            socket = new Socket(address, port);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
