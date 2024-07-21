import java.util.Random;

class MessageRepository {

    private String message;
    private boolean hasMessage = false;

    public synchronized String read() {
        while (!hasMessage) {
            try {
                wait(); // wait for the producer to write a message
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = false;
        notify(); // notify the waiting thread
        return message;
    }

    public synchronized void write(String message) {
        while (hasMessage) {
            try {
                wait(); // wait for the consumer to read the message
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = true;
        notify(); // notify the waiting thread
        this.message = message;
    }
}

// Producer class
class MessageWriter implements Runnable {

    private MessageRepository outgoingMessage;

    private final String text = """
            Humpty Dumpty sat on a wall,
            Humpty Dumpty had a great fall.
            All the king's horses and all the king's men
            Couldn't put Humpty together again.
            """;

    public MessageWriter(MessageRepository outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] lines = text.split("\n");

        for (String line : lines) {
            outgoingMessage.write(line);
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        outgoingMessage.write("DONE");
    }
}

// Consumer class
class MessageReader implements Runnable {

    private MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String latestMessage = "";

        do {
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            latestMessage = incomingMessage.read();
            System.out.println(latestMessage);
        } while (!latestMessage.equals("DONE"));
    }
}

public class Main {

    public static void main(String[] args) {

        MessageRepository messageRepository = new MessageRepository(); // shared object

        Thread reader = new Thread(new MessageReader(messageRepository));
        Thread writer = new Thread(new MessageWriter(messageRepository));

        reader.start();
        writer.start();
    }
}
