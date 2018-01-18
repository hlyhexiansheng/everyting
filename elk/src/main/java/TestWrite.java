import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by noodles on 2017/4/1 17:36.
 */
public class TestWrite {

    private static final String filename = "bb.txt";

    public static void main(String[] args) throws IOException {
        long l = System.currentTimeMillis();
        write();
        reader();
        System.out.println("cost:" + (System.currentTimeMillis() - l));
    }

    private static void reader() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "r");
        long count = 0;
        int len = 1024 * 8;
        while (true) {
            final int read = randomAccessFile.read(new byte[len], 0, len);
            if (read == -1) {
                break;
            }
            count++;
        }
        System.out.println("---" + count);
    }


    private static void write() throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        for (int i = 0; i < 1024 * 1024 * 1024 / 3; i++) {
            fileWriter.write("aaaaaaaa");
        }
        fileWriter.flush();
    }
}
