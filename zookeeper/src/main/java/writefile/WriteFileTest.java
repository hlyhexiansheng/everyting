package writefile;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by noodles on 16/11/30 下午5:20.
 */
public class WriteFileTest {

    public static void main(String[] args) throws Exception {

        final CuratorFramework client = CuratorFrameworkFactory.newClient("10.4.4.31:2181",new ExponentialBackoffRetry(1000, 3));
        client.start();

        //收集
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/flume/collect/collector",getFlumeConfigBytes());
//        client.setData().forPath("/flume/collect/collector",getFlumeConfigBytes());
        //搜索
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/flume/agent/SearchSystem",getFlumeConfigBytes());
//        client.setData().forPath("/flume/agent/SearchSystem",getFlumeConfigBytes());
        //ejob
//        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/flume/agent/Ejob",getFlumeConfigBytes());
        client.setData().forPath("/flume/agent/Ejob",getFlumeConfigBytes());
    }

    private static byte[] getFlumeConfigBytes() throws IOException {
//        File file = new File("/Users/noodles/Documents/bppe-2.0/workspace/test/zookeeper/src/main/resources/collector.conf");
//        File file = new File("/Users/noodles/Documents/bppe-2.0/workspace/test/zookeeper/src/main/resources/SearchSystem-Agent.conf");
        File file = new File("/Users/noodles/Documents/bppe-2.0/workspace/test/zookeeper/src/main/resources/Ejob-Agent.conf");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[10000];
        final int read = inputStream.read(bytes);

        byte[] result = new byte[read];
        System.arraycopy(bytes,0,result,0,read);
        return result;
    }

}
