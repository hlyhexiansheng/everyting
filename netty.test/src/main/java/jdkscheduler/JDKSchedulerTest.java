package jdkscheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by noodles on 16/8/29 下午11:40.
 */
public class JDKSchedulerTest {

    public static void main(String[] args) {

        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("1");
            }
        },1L,10L, TimeUnit.SECONDS);

    }

}
