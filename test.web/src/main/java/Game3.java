import static java.lang.Math.sin;

/**
 * Created by noodles on 2017/11/30 17:21.
 */
public class Game3 {


    private static long starttime;

    public static void main(String[] args) throws InterruptedException {
        int processorsCount = Runtime.getRuntime().availableProcessors();
        Thread[] t = new Thread[processorsCount];
        for (int i = 0; i < processorsCount; i++) {
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        curve();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t[i].start();
            t[i].join();
        }
    }

    private static void curve() throws InterruptedException {
        double[] busytime = new double[200];
        double[] idletime = new double[200];
        double split = 0.01;
        double alltime = 300;
        double half = alltime / 2;
        double PI = 3.1415926;
        double r = 0;
        int i;
        for (i = 0; i < 200; i++) {
            busytime[i] = half + sin(PI * r) * half;
            idletime[i] = alltime - busytime[i];
            r += split;
        }
        while (true) {
            i = i % 200;
            starttime = System.currentTimeMillis();
            while (System.currentTimeMillis() - starttime < busytime[i]) ;
            Thread.sleep((long) idletime[i]);
            i++;
        }
    }

}
