package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            // wait for a connection to a client by calling accept
            try (Socket socket = serverSocket.accept()) { // app will block here waiting for a client to initiate connection
                System.out.println("Server accepts client connection");
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
                    String echoString = input.readLine();
                    System.out.println("Server got request data: " + echoString);
                    if (echoString.equalsIgnoreCase("exit")) break;
                    output.println("Echo from server: " + echoString);
                }
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        } finally {
            System.out.println("Server shutdown!");
        }
    }
}
