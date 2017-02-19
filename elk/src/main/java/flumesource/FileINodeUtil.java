package flumesource;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by noodles on 2017/2/18 17:32.
 */
public class FileINodeUtil {

    public static String getFileName(String directory, String inode) {
        if (inode == null || !isExist(directory) || !isDirectory(directory)) {
            return null;
        }

        final File[] allFiles = new File(directory).listFiles();
        if (allFiles == null) {
            return null;
        }

        for (File file : allFiles) {
            if (inode.equals(getInode(file.getAbsolutePath()))) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    /**
     * 获取目录下的所有文件的inode，不包含目录的
     * @param directory
     * @return inodeList
     */
    public static List<String> getAllInode(String directory) {

        if (!isExist(directory) || !isDirectory(directory)) {
            return null;
        }

        final File dir = new File(directory);

        final File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !new File(dir.getAbsolutePath() + "/" + name).isDirectory();
            }
        });

        if (files == null || files.length == 0) {
            return null;
        }

        List<String> resultList = new ArrayList<>();
        for (File file : files) {
            final String inode = getInode(file.getAbsolutePath());
            resultList.add(inode);
        }

        return resultList;
    }

    public static boolean isExist(String filename) {
        return new File(filename).exists();
    }

    public static boolean isDirectory(String filename) {
        return new File(filename).isDirectory();
    }

    public static String getInode(String fileName) {
        String inode;
        try {
            Path path = Paths.get(fileName);
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

            Object fileKey = attr.fileKey();
            String s = fileKey.toString();
            inode = s.substring(s.indexOf("ino=") + 4, s.indexOf(")"));
        } catch (Exception e) {
            return null;
        }

        return inode;
    }


    public static void main(String[] args) throws IOException {
        String fileName = "/Users/noodles/logs/localproject/log4j2.log";
        String dir = "/Users/noodles/logs/localproject";
//        final String inode = getInode(fileName);

//        final List<String> allInode = getAllInode(dir);

        System.out.println(getFileName(dir,"53191568"));

    }
}
