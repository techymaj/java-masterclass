package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPAudioClient {

    private static final int SERVER_PORT = 5_000;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {
        // the client doesn't need to include the port
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            byte[] audioFileName = "paradise.wav".getBytes();
            // pass data to the server in form of a packet
            DatagramPacket packet1 = new DatagramPacket(
                    audioFileName,
                    audioFileName.length,
                    InetAddress.getLocalHost(),
                    SERVER_PORT
            );
            clientSocket.send(packet1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
