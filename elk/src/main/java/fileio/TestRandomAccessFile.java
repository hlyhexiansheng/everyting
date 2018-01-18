package fileio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by noodles on 2017/2/19 00:18.
 */
public class TestRandomAccessFile {

    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/noodles/logs/log1/a.log", "r");
        while (true) {
            final String s = randomAccessFile.readLine();
            if(s == null){
                Thread.sleep(1000);
                continue;
            }
            System.out.println(s);
        }
    }
}
