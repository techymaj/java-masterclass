import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class What {

    public static void main(String[] args) {

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            Path path = Path.of(".");
            // WatchKeys are thread-safe
            WatchKey relativePathWatchKey = path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE
            ); // register directory to watch and get a key instance
            whileLoop:
            while (true) {
                relativePathWatchKey = watchService.take();
                List<WatchEvent<?>> events = relativePathWatchKey.pollEvents();
                for (var event : events) {
                    System.out.println(event.kind() + " ---> " + event.context());
                    if (event.kind().toString().equals("ENTRY_DELETE")) {
                        break whileLoop;
                    }
                }
                relativePathWatchKey.reset();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
