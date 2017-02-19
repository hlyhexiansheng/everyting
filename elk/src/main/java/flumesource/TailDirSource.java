package flumesource;

import com.google.common.collect.Maps;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by noodles on 2017/2/18 23:00.
 */
public class TailDirSource {

    private String watchDir = "/Users/noodles/logs/localproject";

    private String positionFile = "/Users/noodles/data/taildirsource/position.json";

    private WatchService watchService;

    private static Map<WatchKey, Path> keys = Maps.newHashMap();

    private FileReaderWorker fileReaderWorker;

    private FilePostionManager filePostionManager;

    public static void main(String[] args) throws Exception {
        new TailDirSource().start();
    }

    public void start() throws IOException, InterruptedException {

        this.filePostionManager = new FilePostionManager(positionFile, Arrays.asList(watchDir));

        this.fileReaderWorker = new FileReaderWorker(filePostionManager);

        this.watchService = FileSystems.getDefault().newWatchService();

        resetTailFiles();

        this.fileReaderWorker.fireAllFileEvent();

        final Path path = Paths.get(this.watchDir);

        final WatchKey watchKey = path.register(watchService,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE);

        keys.put(watchKey, path);


        this.fileReaderWorker.start();

        while (true) {

            final WatchKey key = watchService.take();
            final Path filePath = keys.get(key);

            List<WatchEvent<?>> watchEvents = key.pollEvents();

            process(watchEvents, filePath);

            key.reset();
        }

    }

    private void resetTailFiles() throws IOException {

        final List<FilePostionInfo> curPostionInfos = this.fileReaderWorker.getPostionInfo();


        for (FilePostionInfo info : curPostionInfos) {
            this.filePostionManager.updatePostion(info.getInode(), info.getOffset());
        }
        this.filePostionManager.sync();

        this.fileReaderWorker.closeAllFile();

        final List<FilePostionInfo> postionInfos = this.filePostionManager.getPostionInfos();


        for (FilePostionInfo info : postionInfos) {
            String inode = info.getInode();
            TailFile tailFile = new TailFile(inode, info.getFilename(), info.getOffset());
            this.fileReaderWorker.addNewFile(inode, tailFile);
        }
    }


    private void process(List<WatchEvent<?>> watchEvents, final Path path) throws IOException {
        for (WatchEvent<?> event : watchEvents) {
            if (event.context() != null) {

                String fileName = path.toAbsolutePath().toString() + "/" + event.context().toString();

                if (new File(fileName).isDirectory()) {
                    continue;
                }

                if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_MODIFY.name())) {       // 文件更改
                    System.out.println(fileName + "file Modify");
                    processModify(fileName);
                } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_DELETE.name())) {// 文件删除
                } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_CREATE.name())) {// 文件创建
                    System.out.println(fileName + "file Created");
                    processCreate(fileName);
                }
            }
        }
    }

    private void processCreate(String fileName) throws IOException {

        final String inode = FileINodeUtil.getInode(fileName);
//
        this.filePostionManager.notifyFileCreate(inode, fileName);

        resetTailFiles();

        this.fileReaderWorker.fireAllFileEvent();
    }

    private void processModify(String fileName) {
        final String inode = FileINodeUtil.getInode(fileName);
        this.fileReaderWorker.fireReadEvent(inode);
    }
}
