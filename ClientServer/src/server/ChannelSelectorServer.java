package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChannelSelectorServer {

    public static void main(String[] args) {

        try (
                ServerSocketChannel serverChannel = ServerSocketChannel.open();
                Selector selector = Selector.open()
        ) {
            serverChannel.bind(new InetSocketAddress(5000));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT); // registration token
            while (true) {
                selector.select(); // is a blocking operation
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = serverChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("Client connected " + clientChannel.getRemoteAddress());
                    } else if (key.isReadable()) {
                        // true if a client has made a req
                        echoData(key);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void echoData(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesRead = clientChannel.read(buffer); // read is writing req data from channel to buffer
        if (bytesRead > 0) {
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data); // buffer transfers to data
            String message = "Echo: " + new String(data);
            clientChannel.write(ByteBuffer.wrap(message.getBytes())); // write the res to the clientChannel
        } else if (bytesRead == -1) {
            System.out.println("Client disconnected" + clientChannel.getRemoteAddress());
            key.cancel(); // clean up the selection key
            clientChannel.close(); // only close the clientChannel if no bytes are read.
        }
    }
}