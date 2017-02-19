package flumesource;

import lombok.Data;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by noodles on 2017/2/18 23:29.
 */
@Data
public class TailFile {

    private String inode;
    private String filename;
    private long offset;
    private RandomAccessFile randomAccessFile;

    public TailFile(String inode, String filename, long offset) {
        try {
            this.inode = inode;
            this.filename = filename;
            this.offset = offset;
            this.randomAccessFile = new RandomAccessFile(filename, "r");
            this.randomAccessFile.seek(offset);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        if (this.randomAccessFile != null) {
            this.randomAccessFile.close();
        }
    }

    public String nextLine() throws IOException {
        final String s = randomAccessFile.readLine();
        offset = randomAccessFile.getFilePointer();
        return s;
    }

}
