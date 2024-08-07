package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedSimpleServer {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            // wait for a connection to a client by calling accept
            while (true) {
                Socket socket = serverSocket.accept(); // app will block here waiting for a client to initiate connection
                System.out.println("Server accepts client connection");
                socket.setSoTimeout(900_000); // if there's no communication with client after time, drop client
                executorService.submit(() -> handleClientRequest(socket));
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        } finally {
            System.out.println("Server shutdown!");
        }
    }

    private static void handleClientRequest(Socket socket) {
        // if an exception occurs in this block, close the socket and its resources cleanly
        try (
                socket;
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (true) {
                String echoString = input.readLine();
                System.out.println("Server got request data: " + echoString);
                if (echoString.equalsIgnoreCase("exit")) break;
                output.println("Echo from server: " + echoString);
            }
        } catch (IOException e) {
            System.out.println("Client socket shutdown here");
        }
    }
}
