package fileio;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        testVoidJson();

    }

    private static void testVoidJson(){
        List<String> s = new ArrayList<>();
        final String s1 = JSON.toJSONString(s);
        System.out.println(s1);

        final List<String> strings = JSON.parseArray(s1, String.class);

    }
}
