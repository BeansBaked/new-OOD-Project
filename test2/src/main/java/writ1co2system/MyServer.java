package writ1co2system;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private ServerSocket serverSocket;
    private final int MAX_CONNECTIONS = 4;
    private int currentConnections = 0;

    public MyServer() {
        try {
            serverSocket = new ServerSocket(5000); // Replace with your desired port number
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        //always checking for a new connection to the server
        while (true) {
            try {
                //create a connection socket and accept the socket connection
                Socket socket = serverSocket.accept();
                //if theres enough space, we allow them to connect
                if (currentConnections < MAX_CONNECTIONS) {
                    currentConnections++;
                    System.out.println("new connection.");
                } 
                //go here if there are already 4 connections
                else 
                {
                    System.out.println("Max connections reached. Connection rejected.");
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized boolean hasSpaceForConnection() {
        return currentConnections < MAX_CONNECTIONS;
    }
    
}