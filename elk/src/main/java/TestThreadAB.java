import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by noodles on 2017/11/3 21:22.
 */
public class TestThreadAB {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock(true);
        Condition[] conditions = new Condition[10];

        for (int i = 0; i < 10; i++) {
            conditions[i] = lock.newCondition();
        }

        Thread[] t = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Condition me = conditions[i];
            Condition other;
            if (i == 9) {
                other = conditions[0];
            } else {
                other = conditions[i + 1];
            }
            t[i] = new TT1(String.valueOf(i), me, other, lock);
        }

        for (int i = 0; i < 10; i++) {
            t[i].start();
        }


        Thread.sleep(1000);
    }
}

class TT1 extends Thread {

    private final Lock lock;
    private Condition other;
    private Condition me;

    private String c;

    TT1(String c, Condition me, Condition other, Lock lock) {
        this.c = c;
        this.other = other;
        this.me = me;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(c);
                other.signal();
                me.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();

            }

        }
    }
}
