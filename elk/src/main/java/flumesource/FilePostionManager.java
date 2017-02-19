package flumesource;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by noodles on 2017/2/19 12:19.
 */
public class FilePostionManager {

    private RandomAccessFile randomAccessFile;

    private List<String> watchDirs;

    private List<FilePostionInfo> postionInfos;

    public FilePostionManager(String positionFile, List<String> watchDirs) throws IOException {
        this.watchDirs = watchDirs;
        this.randomAccessFile = new RandomAccessFile(new File(positionFile), "rw");

        reloadPostionInfo();

        sync();
    }


    public void sync() throws IOException {
        final String content = JSON.toJSONString(postionInfos);
        randomAccessFile.setLength(0);
        randomAccessFile.write(content.getBytes(Charset.forName("utf-8")));
    }

    public List<FilePostionInfo> getPostionInfos() {
        return this.postionInfos;
    }

    public void notifyFileCreate(String inode, String fileName) throws IOException {
//        FilePostionInfo info = new FilePostionInfo(inode, fileName, 0);
//        postionInfos.add(info);

        sync();

        reloadPostionInfo();

        sync();

    }


    public void updatePostion(String inode, long offset) throws IOException {
        for (FilePostionInfo info : postionInfos) {
            if (info.getInode().equals(inode)) {
                info.setOffset(offset);
                break;
            }
        }
    }

    private void reloadPostionInfo() throws IOException {

        int length = (int) randomAccessFile.length();
        byte[] bytes = new byte[length];
        randomAccessFile.seek(0);
        randomAccessFile.read(bytes);

        final String jsonContent = new String(bytes, Charset.forName("utf-8"));
        if (jsonContent.equals("")) {
            postionInfos = new ArrayList<>();
        } else {
            postionInfos = JSON.parseArray(jsonContent, FilePostionInfo.class);
        }


        final List<String> allInode = getAllNode(this.watchDirs);
        if (allInode == null || allInode.size() == 0) {
            postionInfos.clear();
        } else {
            for (String inode : allInode) {
                if (!containsInode(inode, postionInfos)) {
                    FilePostionInfo info = new FilePostionInfo(inode, getFileName(inode, this.watchDirs), 0);
                    postionInfos.add(info);
                } else {
                    for (FilePostionInfo info : postionInfos) {
                        info.setFilename(getFileName(inode, this.watchDirs));
                    }
                }
            }
        }
    }

    private static boolean containsInode(String inode, List<FilePostionInfo> infos) {
        for (FilePostionInfo info : infos) {
            if (info.getInode().equals(inode)) {
                return true;
            }
        }
        return false;
    }

    public String getFileName(String inode, List<String> directorys) {
        for (String dir : directorys) {
            final String fileName = FileINodeUtil.getFileName(dir, inode);
            if (fileName != null) {
                return fileName;
            }
        }
        return null;
    }

    public List<String> getAllNode(List<String> watchDirs) {
        final List<String> allInode = new ArrayList<>();
        for (String dir : watchDirs) {
            final List<String> nodes = FileINodeUtil.getAllInode(dir);
            if (nodes != null) {
                allInode.addAll(nodes);
            }
        }
        return allInode;
    }

}
