package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2015/12/18.
 */
public class ZookeeperTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 100000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        System.out.println("connected zookeeper");
//        zooKeeper.create("/","test".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        final List<String> children = zooKeeper.getChildren("/", true);

        for(String s : children){
            System.out.println(s);
        }
    }
}
