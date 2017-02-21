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

        syncDisk();
    }


    /**
     * 同步数据到磁盘
     *
     * @throws IOException
     */
    public void syncDisk() throws IOException {
        final String content = JSON.toJSONString(postionInfos);
        randomAccessFile.setLength(0);
        randomAccessFile.write(content.getBytes(Charset.forName("utf-8")));
    }

    public List<FilePostionInfo> getPostionInfos() {
        return this.postionInfos;
    }

    public void notifyFileCreate() throws IOException {

        syncDisk();

        reloadPostionInfo();

        syncDisk();

    }


    public void updatePostion(String inode, long offset) throws IOException {
        for (FilePostionInfo info : postionInfos) {
            if (info.getInode().equals(inode)) {
                info.setOffset(offset);
                break;
            }
        }
    }

    /**
     * 加载文件位置信息
     *
     * @throws IOException
     */
    private void reloadPostionInfo() throws IOException {

        //1.读出文件
        int length = (int) randomAccessFile.length();
        byte[] bytes = new byte[length];
        randomAccessFile.seek(0);
        randomAccessFile.read(bytes);

        //2.转换成JavaBean
        final String jsonContent = new String(bytes, Charset.forName("utf-8"));
        if (jsonContent.equals("")) {
            postionInfos = new ArrayList<>();
        } else {
            postionInfos = JSON.parseArray(jsonContent, FilePostionInfo.class);
        }

        //3.获得所有文件对应的File Node
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
                        info.setFilename(getFileName(info.getInode(), this.watchDirs)); //文件发生滚动之后，原来的inode，对应的文件名称变了，这里重新设置文件名
                    }
                }
            }
            removePostionInfoIfFileNodeNotExist(postionInfos, allInode);//最后去掉文件被删了，但是postionInfo还在的
        }
    }


    private static void removePostionInfoIfFileNodeNotExist(List<FilePostionInfo> infos, List<String> allNodes) {
        if (infos == null || infos.size() == 0) {
            return;
        }
        if (allNodes == null || allNodes.size() == 0) {
            infos.clear();
            return;
        }

        List<String> toDeleteINode = new ArrayList<>();
        for (FilePostionInfo info : infos) {
            String inode = info.getInode();
            if (!allNodes.contains(inode)) {
                toDeleteINode.add(inode);
            }
        }

        for (String delNode : toDeleteINode) {
            removePostionInfoByINode(infos, delNode);
        }
    }


    private static void removePostionInfoByINode(List<FilePostionInfo> infos, String node) {
        if (infos == null || infos.size() == 0 || node == null || node.equals("")) {
            return;
        }
        for (int i = 0; i < infos.size(); i++) {
            if (infos.get(i).getInode().equals(node)) {
                infos.remove(i);
                break;
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
