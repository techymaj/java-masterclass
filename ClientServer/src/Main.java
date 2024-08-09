import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        Consumer<ByteBuffer> byteBufferConsumer = byteBuffer -> {
            byte[] data = new byte[byteBuffer.limit()];
            byteBuffer.get(data);
            System.out.printf("\"%s\" ", new String(data, StandardCharsets.UTF_8));
        };
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        doOperation("Print: ", buffer, b -> System.out.print(b + " "));
        doOperation("Write: ", buffer, b -> b.put("This is a test ".getBytes()));
        doOperation("Flip (from write to read): ", buffer, ByteBuffer::flip);
        doOperation("Read and print the value: ", buffer, byteBufferConsumer);
        doOperation("Flip (from read to write): ", buffer, ByteBuffer::flip);
//        doOperation("1. Move position to end of text", buffer, b -> b.position(buffer.limit()));
//        doOperation("2. Change limit to capacity", buffer, b -> b.limit(buffer.capacity()));
        doOperation("Compact Op", buffer, ByteBuffer::compact);
        doOperation("Append: ", buffer, b -> b.put("you will excel ".getBytes()));
//        doOperation("Flip (from write to read)", buffer, ByteBuffer::flip);
        doOperation("Read and print the value: ", buffer.slice(0, buffer.position()), byteBufferConsumer);
        doOperation("Append: ", buffer, b -> b.put("gracefully".getBytes()));
        doOperation("Read and print the value: ", buffer.slice(0, buffer.position()), byteBufferConsumer);

    }

    private static void doOperation(String op, ByteBuffer byteBuffer, Consumer<ByteBuffer> c) {
        System.out.printf("%-30s", op);
        c.accept(byteBuffer);
        System.out.printf("Capacity = %d, Limit = %d, Position = %d, Remaining = %d%n",
                byteBuffer.capacity(),
                byteBuffer.limit(),
                byteBuffer.position(),
                byteBuffer.remaining()
        );
    }
}