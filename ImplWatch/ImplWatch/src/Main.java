import java.io.IOException;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("/home/helder/Documentos/pasta");
        path.register( watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ( (key = watchService.take()) != null ) {
            for (WatchEvent<?> event : key.pollEvents() ) {
                System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + "." );
            }
            key.reset();
        }
    }
}