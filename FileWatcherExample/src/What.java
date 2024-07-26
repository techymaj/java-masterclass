import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

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
                events.forEach(watchEvent -> {
                    WatchEvent.Kind<?> kind = watchEvent.kind();
                    System.out.println(kind + " ---> " + watchEvent.context());
                    if (kind == OVERFLOW) {
                        System.out.println("Lost some events");
                    }
                });
                var active = relativePathWatchKey.reset();
                if (!active) break whileLoop;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
