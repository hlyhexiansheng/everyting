import java.util.concurrent.CountDownLatch;

/**
 * Created by noodles on 2017/11/30 16:17.
 */
public class Game1 {

    public static void main(String[] args) throws InterruptedException {

        final long l = System.currentTimeMillis();

        int processorsCount = Runtime.getRuntime().availableProcessors();

        CountDownLatch countDownLatch = new CountDownLatch(processorsCount);

        int num = 100000000;
        int avg = num / processorsCount;

        GT1[] gt1s = new GT1[processorsCount];
        int ts = 0;
        for (int i = 0; i < processorsCount; i++) {
            gt1s[i] = new GT1(ts, ts + avg, 0, countDownLatch);
            ts = ts + avg;
            gt1s[i].start();

        }
        countDownLatch.await();
        int total = 0;
        for (int i = 0; i < processorsCount; i++) {
            total = total + gt1s[i].count;

        }


        System.out.println(total);
        System.out.println(System.currentTimeMillis() - l);


    }
}

class GT1 extends Thread {

    public int start;
    public int end;
    public int count;
    private CountDownLatch countDownLatch;

    public GT1(int ts, int i, int count, CountDownLatch countDownLatch) {
        this.start = ts;
        this.end = i;
        this.count = count;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (; start < end; start++) {
            if (start % 12 == 0 || start % 13 == 0) {
                count++;
            }
        }
        countDownLatch.countDown();
    }
}
