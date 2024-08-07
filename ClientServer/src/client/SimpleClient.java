package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            // autoflush = true, any print statement will flush the buffer
            Scanner scanner = new Scanner(System.in);
            String req, res;
            do {
                System.out.print("Enter string to be echoed (sent to server): ");
                req = scanner.nextLine();
                output.println(req);
                if (!req.equalsIgnoreCase("exit")) {
                    res = input.readLine();
                    System.out.println(res);
                }
            } while (!req.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            System.out.println("Client disconnected");
        }
    }
}
