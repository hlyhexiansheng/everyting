import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by noodles on 2017/3/6 09:45.
 */
public class SoftLinkTest {
    private static Map<WatchKey, Path> keys = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {

        String dir = "/Users/noodles/logs/log";

        final String[] list = new File(dir).list();


        final boolean symbolicLink = Files.isSymbolicLink(Paths.get(dir));
        System.out.println("is symbolicLink=" + symbolicLink);

        if (symbolicLink) {
            System.out.println(Paths.get(dir).toRealPath());
        }

        testResisterSingle(dir);
    }

    private static void testResisterSingle(String dir) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        final Path path = Paths.get(dir).normalize();

        final WatchKey watchKey = path.register(watchService,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE);
        keys.put(watchKey, path);
        while (true) {
            final WatchKey key = watchService.take();
            final Path paths = keys.get(key);
            List<WatchEvent<?>> watchEvents = key.pollEvents();
            for (WatchEvent<?> event : watchEvents) {
                if (event.context() != null) {

                    String fileName = paths.toAbsolutePath().toString() + "/" + event.context().toString();

                    if (new File(fileName).isDirectory()) {
                        System.out.println("directory....");
                        continue;
                    }

                    if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_MODIFY.name())) {
                        // 文件更改
                        System.out.println(fileName + " file  changed");
                    } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_DELETE.name())) {
                        // 文件删除
                        System.out.println(fileName + " file  deleted");
                    } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_CREATE.name())) {
                        // 文件创建
                        System.out.println(fileName + " file  created");
                    }
                }
            }
            key.reset();
        }
    }

}
