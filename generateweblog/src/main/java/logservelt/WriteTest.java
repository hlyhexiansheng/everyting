package logservelt;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by noodles on 17/1/2 下午6:14.
 */
public class WriteTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        String parentLog = "/Users/noodles/logs/localproject/";



        FileWriter defaultLog = new FileWriter(parentLog + "default/default.log");
        FileWriter log1 = new FileWriter(parentLog + "dir1/1.log");
        FileWriter log2 = new FileWriter(parentLog + "dir2/2.log");
        FileWriter log3 = new FileWriter(parentLog + "dir3/3.log");

        for(int i = 0; i < 1000; i++){
            defaultLog.write("defaultLog ---" + "\n");
            log1.write("log-1  ----" + i + "\n");
            log2.write("log-2  ----" + i + "\n");
            log3.write("log-3  ----" + i + "\n");

            defaultLog.flush();
            log1.flush();
            log2.flush();
            log3.flush();

            Thread.sleep(3000L);
        }

    }
}
