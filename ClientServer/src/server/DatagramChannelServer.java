package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class DatagramChannelServer {

    private static final int PORT = 5_000;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {

        try (
                DatagramChannel channel = DatagramChannel.open();
        ) {
            channel.bind(new InetSocketAddress(PORT));
            System.out.println("Server listening on port: " + PORT);
            // Selectors make the code event driven
            Selector selector = Selector.open();
            channel.configureBlocking(false);
            // register the read selection key with the selector
            channel.register(selector, SelectionKey.OP_READ);
            ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
            // listen for events with a while loop
            while (true) {
                selector.select(); // blocks if there's nothing to do
                // get the selected keys from the selector
                var selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectedKeys.iterator();
                // go through each selectionKey
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey key = selectionKeyIterator.next();
                    // once you have the key, you can remove it since we are now going to process the event
                    if (key.isReadable()) {
                        // get the channel registered for this event
                        var registeredChannel = (DatagramChannel) key.channel();
                        // there's a Datagram packet ready to be read
                        buffer.clear();
                        // return the clientAddress but also write the incoming data into this buffer
                        var clientAddress = registeredChannel.receive(buffer);
                        // flip from write to read
                        buffer.flip();
                        byte[] data = new byte[buffer.remaining()];
                        buffer.get(data);
                        String audioFilePath = new String(data);
                        System.out.println("Client requested to listen to: " + audioFilePath);
                        new Thread(() -> sendDataToClient(audioFilePath, clientAddress, registeredChannel)).start();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    // send the audio packets using a Datagram channel
    private static void sendDataToClient(String file, SocketAddress address, DatagramChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);
        try (FileChannel fileChannel = FileChannel.open(Path.of(file), StandardOpenOption.READ)) {
            // read data from the file
            while (true) {
                buffer.clear();
                // read writes to the buffer
                int bytesRead = fileChannel.read(buffer);
                if (bytesRead == -1) {
                    // reached end of the file
                    break;
                }
                // flip from write to read
                buffer.flip();
                // for the data in the buffer
                while (buffer.hasRemaining()) {
                    channel.send(buffer, address);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(4);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}