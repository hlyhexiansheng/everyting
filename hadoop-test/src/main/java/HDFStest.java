import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Created by noodles on 16/10/13 上午11:51.
 */
public class HDFStest {

    static{//JVM只能调用一次此方法，所以选择此静态方法。这个限制意味着玉如其他程序的其他组件，已经声明了一个URLStreamHandlerFactory，将无法再使用上述方法从Hadoop中读取数据。
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());//通过FsurlStreamHandlerFactory示例调用setURLStreamHandlerFactory实现java能够实现Hadoop的hdfs url
    }


    public static void main(String[] args) throws IOException {
        list();
        createDir("/input3");
    }

    private static void createDir(String dir) throws IOException {
        String uri = "hdfs://192.168.56.104:9000/";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path path = new Path(dir);
        fs.mkdirs(path);
        System.out.println("new dir \t" + conf.get("fs.default.name") + dir);
    }


    private static void list() throws IOException {

        String uri = "hdfs://192.168.56.104:9000/";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path f = new Path("/user/hadoop/input");
        FileStatus[] status = fs.listStatus(f);

        for (FileStatus statu : status) {
            System.out.println(statu.getPath().toString());
        }
    }


}
