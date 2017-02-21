package flumesource;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by noodles on 2017/2/18 23:39.
 */
@Slf4j
public class FileReaderWorker extends Thread {

    private volatile boolean running = true;
    @Getter
    @Setter
    private volatile boolean isFileRolling = false;

    private Map<String, TailFile> fileMap = new ConcurrentHashMap<>();

    private BlockingQueue<String> readEventQueue = new LinkedBlockingQueue<>();

    private FilePostionManager filePostionManager;

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();


    public FileReaderWorker(FilePostionManager filePostionManager) {
        this.filePostionManager = filePostionManager;
    }

    @Override
    public void run() {
        this.scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {

                    fireAllFileEvent();

                    filePostionManager.syncDisk();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 5, 5, TimeUnit.SECONDS);

        while (running) {
            try {
                final String inode = readEventQueue.take();

                final TailFile tailFile = fileMap.get(inode);

                if (tailFile == null) {
                    System.out.println("tailFile is null,inode=" + inode);
                    continue;
                }
                doRead(tailFile);

                this.filePostionManager.updatePostion(tailFile.getInode(), tailFile.getOffset());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doRead(TailFile tailFile) throws IOException {

        while (true) {
            if (isFileRolling) {
                return;
            }
            final String line = tailFile.nextLine();
            if (line == null) {
                break;
            }
            log.info(line);
            System.out.println(line);
        }
    }

    public void addNewFile(String inode, TailFile tailFile) {
        if (!fileMap.containsKey(inode)) {
            fileMap.put(inode, tailFile);
        }
    }

    public void fireAllFileEvent() {
        final Set<String> nodes = this.fileMap.keySet();
        for (String inode : nodes) {
            readEventQueue.offer(inode);
        }
    }

    public void closeAllFile() throws IOException {

        final Collection<TailFile> values = fileMap.values();
        for (TailFile file : values) {
            file.close();
        }
        fileMap.clear();
    }

    public List<FilePostionInfo> getPostionInfo() {
        List<FilePostionInfo> infoList = new ArrayList<>();
        final Collection<TailFile> values = fileMap.values();
        for (TailFile file : values) {
            FilePostionInfo info = new FilePostionInfo(file.getInode(), file.getFilename(), file.getOffset());
            infoList.add(info);
        }
        return infoList;
    }


}
