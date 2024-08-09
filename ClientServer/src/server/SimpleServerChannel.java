package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class SimpleServerChannel {

    public static void main(String[] args) {

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            ServerSocket internalSocketObject = serverSocketChannel.socket();
            internalSocketObject.bind(new InetSocketAddress(5000));
            serverSocketChannel.configureBlocking(false); // accept() will no longer block
            System.out.println("Server listening on port: " + internalSocketObject.getLocalPort());

            List<SocketChannel> clients = new ArrayList<>();

            while (true) {
                SocketChannel clientChannel = serverSocketChannel.accept();
                if (clientChannel != null) {
                    clientChannel.configureBlocking(false);
                    clients.add(clientChannel);
                    System.out.printf("Client %s connected%n", clientChannel.socket().getRemoteSocketAddress());
                }

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                for (int i = 0; i < clients.size(); i++) {
                    var socketChannel = clients.get(i);
                    int readBytes = socketChannel.read(byteBuffer);
                    // the number of bytes can never be greater than the capacity
                    if (readBytes > 0) {
                        // once the channel has written to the buffer, read from it
                        byteBuffer.flip(); // flips the buffer's state from writeable to readable
                        socketChannel.write(ByteBuffer.wrap("Echo from server: ".getBytes()));
                        // iterate through the data in the buffer created for the client req data
                        while (byteBuffer.hasRemaining()) {
                            // continue to write data while we still have some data
                            socketChannel.write(byteBuffer);
                        }
                        byteBuffer.clear(); // when you're done echoing the data
                    } else if (readBytes == -1) {
                        System.out.printf("Connection to %s lost%n", socketChannel.socket().getRemoteSocketAddress());
                        socketChannel.close();
                        var removedClient = clients.remove(i);
                        System.out.println("Removed " + removedClient.getRemoteAddress());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }
}
