import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by noodles on 16/10/29 下午5:09.
 */
public class MakeIOHigher {

    public static void main(String[] args) throws IOException, InterruptedException {
        randomWrite("/tmp/iotest.txt");
    }

    public static void randomWrite(String fileName) throws IOException, InterruptedException {

        final long startTime = System.currentTimeMillis();
        RandomAccessFile rf = new RandomAccessFile(fileName, "rw");

        for (int i = 0; i < 1000 * 500; i++) {
            for(int j = 0; j < 100;j++){
                rf.write(new byte[10]);
            }
        }
        rf.close();

        System.out.println("random write cost:" + (System.currentTimeMillis() - startTime));

    }

}
