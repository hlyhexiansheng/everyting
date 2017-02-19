package flumesource;

import lombok.Data;

/**
 * Created by noodles on 2017/2/19 01:18.
 */
@Data
public class FilePostionInfo {
    private String inode;
    private String filename;
    private long offset;

    public FilePostionInfo() {

    }

    public FilePostionInfo(String inode, String filename, long offset) {
        this.inode = inode;
        this.filename = filename;
        this.offset = offset;
    }
}
