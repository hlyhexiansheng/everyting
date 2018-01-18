import java.io.*;

/**
 * * Created by noodles on 16/10/12 下午3:29.
 */
public class MakeCpuHigher {


    public static void main(String[] args) throws IOException, InterruptedException {

        T1[] threads = new T1[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new T1();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();

        }
        Thread.sleep(100000000);


    }
}

class T1 extends Thread {
    @Override
    public void run() {
        long sum = 0;
        while (true) {
            for (int i = 0; i < 10000000; i++) {
                sum += i;
            }
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}