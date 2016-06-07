package sample.online;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Closeable {
    private ServerSocket socketserver  ;

    public Socket getSocketduserveur() {
        return socketduserveur;
    }

    public ServerSocket getSocketserver() {
        return socketserver;
    }

    private Socket socketduserveur ;
    public Server(Integer port){

        try {
            socketserver = new ServerSocket(port);
            socketduserveur = socketserver.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        try {
            socketduserveur.close();
            socketserver.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
