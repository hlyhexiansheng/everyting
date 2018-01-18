package fileio;

import com.google.common.collect.Maps;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Created by noodles on 16/10/15 上午12:45.
 */
public class WatcherTest {

    private static Map<WatchKey, Path> keys = Maps.newHashMap();

    private static WatchService watchService;

    private static String dir = "/Users/noodles/logs/log";

    public static void main(String[] args) throws Exception {

        try {


            testResisterSingle(dir);

//        testWalkFileTree(dir);

//        testWatchMultiPath(dir,true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testWalkFileTree(String dir) throws IOException {
        final Path path = Paths.get(dir);
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println(dir.toAbsolutePath().toString());
                return FileVisitResult.CONTINUE;
            }
        });

    }

    private static void testResisterSingle(String dir) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        final Path path = Paths.get(dir);
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
                        System.out.println(fileName + "file changed");
                    } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_DELETE.name())) {
                        // 文件删除
                        System.out.println(fileName + "file deleted");
                    } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_CREATE.name())) {
                        // 文件创建
                        System.out.println(fileName + "file created");
                    }
                }
            }
            key.reset();
        }
    }

    public static void testWatchMultiPath(String path, boolean isRecursion) throws Exception {
        File file = new File(path);
        if (!file.isDirectory())
            throw new Exception(file.getAbsolutePath() + "is not a directory!");

        watchService = FileSystems.getDefault().newWatchService();
        keys = new HashMap<>();

        Path dir = Paths.get(file.getAbsolutePath());

        if (isRecursion) {
            registerAll(dir);
        } else {
            register(dir);
        }
        processEvent();
    }

    public static void processEvent() throws InterruptedException, IOException {
        while (true) {
            final WatchKey key = watchService.take();
            List<WatchEvent<?>> watchEvents = key.pollEvents();
            final Path paths = keys.get(key);
            for (WatchEvent<?> event : watchEvents) {
                if (event.context() != null) {

                    String fileName = paths.toAbsolutePath().toString() + "/" + event.context().toString();
                    final File tmpFile = new File(fileName);
                    if (tmpFile.isDirectory()) {
                        registerIfNot(fileName);
                        continue;
                    }

                    if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_MODIFY.name())) {
                        // 文件更改
                        System.out.println(fileName + " file changed");
                    } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_DELETE.name())) {
                        // 文件删除
                        System.out.println(fileName + " file deleted");
                    } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_CREATE.name())) {
                        // 文件创建
                        System.out.println(fileName + " file created");
                    }
                }
            }
            key.reset();
        }

    }

    private static void registerIfNot(String dir) throws IOException {
        final Iterator<Path> iterator = keys.values().iterator();
        while (iterator.hasNext()) {
            final Path next = iterator.next();
            if (next.toAbsolutePath().toString().equals(dir)) {
                return;
            }
        }
        System.out.println("registerIfNot " + dir);
        final Path path = Paths.get(dir);
        register(path);
    }

    private static void register(Path dir) throws IOException {
        WatchKey key = dir.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        keys.put(key, dir);
    }

    private static void registerAll(Path dir) throws IOException {
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}

