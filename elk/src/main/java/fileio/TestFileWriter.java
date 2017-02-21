package fileio;

import com.alibaba.fastjson.JSON;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by noodles on 2017/2/19 12:02.
 */
public class TestFileWriter {

    public static void main(String[] args) throws IOException {
//        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/noodles/json","rw");
//        while (true){
//            Scanner scanner = new Scanner(System.in);
//            final String s = scanner.nextLine();
//            randomAccessFile.setLength(0);
//            randomAccessFile.readLine();
//        }
//        testVoidJson();

        testWrite();

    }

    private static void testWrite() throws IOException {
        String dir = "/Users/noodles/logs/localproject/";
        FileWriter fileWriter1 = new FileWriter(dir + "e1.log",false);
        FileWriter fileWriter2 = new FileWriter(dir + "e3.log",false);


        FileWriter fileWriter3 = new FileWriter(dir + "e4.log",false);


        Map<String, FileWriter> fileWriterMap = new HashMap<>();
        fileWriterMap.put("1", fileWriter1);
        fileWriterMap.put("2", fileWriter2);
        fileWriterMap.put("3", fileWriter3);


        while (true) {
            Scanner scanner = new Scanner(System.in);
            final String s = scanner.nextLine();
            final String[] split = s.split(" ");
            if (split.length != 2) {
                continue;
            }

            final FileWriter fileWriter = fileWriterMap.get(split[0]);
            if (fileWriter != null) {
                for (int i = 0; i < Integer.parseInt(split[1]); i++){
                    fileWriter.write("MM" + split[1] + "\n");
                }
                fileWriter.flush();
            }
        }
    }

    private static void testVoidJson() {
        List<String> s = new ArrayList<>();
        final String s1 = JSON.toJSONString(s);
        System.out.println(s1);

        final List<String> strings = JSON.parseArray(s1, String.class);

    }
}
