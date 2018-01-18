/**
 * Created by noodles on 2017/11/3 21:49.
 */
public class TestThreadUseObject {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        TTT t1 = new TTT("A", lock);
        TTT t2 = new TTT("B", lock);
        t1.start();
        t2.start();
        Thread.sleep(100);
    }
}

class TTT extends Thread {

    private final String c;
    final Object lock;

    TTT(String c, Object lock) {
        this.lock = lock;
        this.c = c;
    }

    @Override
    public void run() {
        int i = 0;
        while (i++ < 5) {
            synchronized (this.lock) {
                System.out.println(this.c);
                try {
                    this.lock.notify();
                    this.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
