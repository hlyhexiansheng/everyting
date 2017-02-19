package fileio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by noodles on 2017/2/19 00:18.
 */
public class TestRandomAccessFile {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/noodles/logs/localproject/log4j2.log", "r");
        while (true) {
            final String s = randomAccessFile.readLine();
            if(s == null){
                System.out.println("end...");
                break;
            }
            System.out.println(s);
        }
    }
}
