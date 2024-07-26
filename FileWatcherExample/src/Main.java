import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try (
                // a service that lets us monitor file system changes
                WatchService watchService = FileSystems.getDefault().newWatchService();
        ) {
            Path directory = Paths.get(".");
            // step 1. register with the watch service
            // WatchKey -> A handle to the service. the java docs call it a token
            WatchKey watchKey = directory.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE
            );

            while (true) {
                watchKey = watchService.take();
                List<WatchEvent<?>> events = watchKey.pollEvents();

                // get the context of the event
                for (WatchEvent<?> event : events) {
                    Path context = (Path) event.context();
                    System.out.printf("Event type: %s - Context: %s%n", event.kind(), context);
                    if (event.kind().toString().equals("ENTRY_DELETE")) {
                        break;
                    }
                }
                watchKey.reset();
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
