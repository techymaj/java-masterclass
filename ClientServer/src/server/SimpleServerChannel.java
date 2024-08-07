package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SimpleServerChannel {

    public static void main(String[] args) {

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            ServerSocket internalSocketObject = serverSocketChannel.socket();
            internalSocketObject.bind(new InetSocketAddress(5000));
            System.out.println("Server listening on port: " + internalSocketObject.getLocalPort());

            while (true) {
                SocketChannel clientChannel = serverSocketChannel.accept();
                System.out.printf("Client %s connected%n", clientChannel.getRemoteAddress());
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = clientChannel.read(byteBuffer);
                // the number of bytes can never be greater than the capacity
                if (readBytes > 0) {
                    // once the channel has written to the buffer, read from it
                    byteBuffer.flip(); // flips the buffer's state from writeable to readable
                    clientChannel.write(ByteBuffer.wrap("Echo from server: ".getBytes()));
                    // iterate through the data in the buffer created for the client req data
                    while (byteBuffer.hasRemaining()) {
                        // continue to write data while we still have some data
                        clientChannel.write(byteBuffer);
                    }
                    byteBuffer.clear(); // when you're done echoing the data
                } else if (readBytes == -1) {
                    System.out.printf("Connection to %s lost%n", clientChannel.socket().getRemoteSocketAddress());
                    clientChannel.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }
}
