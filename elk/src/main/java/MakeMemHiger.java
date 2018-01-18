import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by noodles on 16/10/29 下午4:36.
 */
public class MakeMemHiger {

    public static void main(String[] args) throws IOException {

        Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        final HashMap map = new HashMap();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 100; i++) {
                    map.put(String.valueOf(i), new byte[1024 * 1024 * 1]); //一次写入1MB

                    System.out.println(i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Object o = new Object();
        synchronized (o) {
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
