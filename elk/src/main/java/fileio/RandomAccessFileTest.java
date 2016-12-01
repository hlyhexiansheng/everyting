package fileio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by noodles on 16/10/14 下午1:58.
 */
public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {

        String randomFileName = "/Users/noodles/Documents/bppe-2.0/workspace/test/randomfiletest.txt";

        String mapperFileName = "/Users/noodles/Documents/bppe-2.0/workspace/test/mapperfiletest.txt";

//        randomWrite(randomFileName);
//
//        randomRead(randomFileName);
//
//        mapperWrite(mapperFileName);
//
//        mapperRead(mapperFileName);

        try {
            trace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void trace() throws Exception {
        try {
            trace2();
        }catch (Exception e){
            throw e;
        }
    }

    private static void trace2() throws Exception {
        throw new Exception("inert");
    }

    private static void mapperWrite(String fileName) throws IOException {
        final long startTime = System.currentTimeMillis();
        RandomAccessFile rf = new RandomAccessFile(fileName, "rw");
        final FileChannel channel = rf.getChannel();
        MappedByteBuffer out = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1000 * 1000 * 500);

        for(int i = 0; i < 1000 * 5000;i++){
            out.put(new byte[100]);
        }

        channel.close();
        rf.close();

        System.out.println("mapper write cost:" + (System.currentTimeMillis() - startTime));
    }

    private static void mapperRead(String fileName) throws IOException {
        final long startTime = System.currentTimeMillis();
        RandomAccessFile rf = new RandomAccessFile(fileName, "rw");
        final FileChannel channel = rf.getChannel();
        MappedByteBuffer out = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1000 * 1000 * 500);

        for(int i = 0; i < 1000 * 5000;i++){
            out.get(new byte[100]);
        }

        channel.close();
        rf.close();

        System.out.println("mapper read cost:" + (System.currentTimeMillis() - startTime));
    }

    private static void randomRead(String fileName) throws IOException {
        final long startTime = System.currentTimeMillis();
        RandomAccessFile rf = new RandomAccessFile(fileName, "rw");

        while (true){

            final int read = rf.read(new byte[100]);
            if(read < 100){
                break;
            }
        }
        rf.close();

        System.out.println("random read cost:" + (System.currentTimeMillis() - startTime));
    }

    public static void randomWrite(String fileName) throws IOException{
        final long startTime = System.currentTimeMillis();
        RandomAccessFile rf = new RandomAccessFile(fileName, "rw");

        for (int i = 0; i < 1000 * 5000; i++) {
            rf.write(new byte[100]);
        }
        rf.close();

        System.out.println("random write cost:" + (System.currentTimeMillis() - startTime));

    }

}
