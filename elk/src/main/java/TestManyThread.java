import java.util.ArrayList;
import java.util.List;

public class TestManyThread {

    public static void main(String[] args) throws InterruptedException {

        int headSize = 1000;
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < headSize; i++) {
            list.add(new byte[1024 * 1024]);
        }

        int threadCount = 1000;
        Thread[] t = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {

            t[i] = new Thread(new Runnable() {

                Object[] a = new Object[100];

                @Override
                public void run() {
                    try {
                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t[i].setDaemon(true);
            t[i].start();

        }
        Thread.sleep(1000000);
    }
}
