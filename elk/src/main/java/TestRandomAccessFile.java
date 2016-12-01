import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by noodles on 16/10/12 下午11:35.
 */
public class TestRandomAccessFile {

    public static void main(String[] args) throws IOException {
        RandomAccessFile rf = new RandomAccessFile("/tmp/raf.txt", "rw");
        rf.writeInt(65);
        rf.writeInt(66);
        rf.close();
    }
}
