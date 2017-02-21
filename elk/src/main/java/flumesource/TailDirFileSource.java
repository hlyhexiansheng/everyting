package flumesource;

import com.google.common.collect.Maps;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by noodles on 2017/2/18 23:00.
 */
public class TailDirFileSource {


    private String positionFile = "/Users/noodles/data/taildirsource/position.json";

    private WatchService watchService;

    private static Map<WatchKey, Path> keys = Maps.newHashMap();

    private FileReaderWorker fileReaderWorker;

    private FilePostionManager filePostionManager;


    public void start() throws IOException, InterruptedException {
        String watchDir1 = "/Users/noodles/logs/localproject";
        String watchDir2 = "/Users/noodles/logs/localproject2";
        final List<String> watchList = Arrays.asList(watchDir1, watchDir2);
        this.filePostionManager = new FilePostionManager(positionFile, watchList);

        this.fileReaderWorker = new FileReaderWorker(filePostionManager);

        this.watchService = FileSystems.getDefault().newWatchService();

        resetTailFiles();

        this.fileReaderWorker.fireAllFileEvent();

        for (String dir : watchList) {
            final Path path = Paths.get(dir);

            final WatchKey watchKey = path.register(watchService,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE);

            keys.put(watchKey, path);
        }


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
        //1.更新offset
        final List<FilePostionInfo> curPostionInfos = this.fileReaderWorker.getPostionInfo();
        for (FilePostionInfo info : curPostionInfos) {
            this.filePostionManager.updatePostion(info.getInode(), info.getOffset());
        }
        this.filePostionManager.syncDisk();

        //2.关闭旧的文件
        this.fileReaderWorker.closeAllFile();

        //3.重新加入文件.
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

                if (FileINodeUtil.isDirectory(fileName)) {
                    continue;
                }

                if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_MODIFY.name())) {       // 文件更改
                    System.out.println(fileName + "file Modify");
                } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_DELETE.name())) {// 文件删除
                } else if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_CREATE.name())) {// 文件创建
                    System.out.println(fileName + "file Created");
                    processCreate(fileName);
                }
            }
        }
    }

    private void processCreate(String fileName) throws IOException {
        //1.加锁，让work暂时不要再读了 （使用volatile变量当作轻量级锁）
        this.fileReaderWorker.setFileRolling(true);

        //2.触发create事件，主要是刷新文件的位置信息
        this.filePostionManager.notifyFileCreate();

        //3.重置work监控的文件
        this.resetTailFiles();

        //4.释放标志位
        this.fileReaderWorker.setFileRolling(false);

    }
}
