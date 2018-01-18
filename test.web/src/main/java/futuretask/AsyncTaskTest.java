package futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by noodles on 16/5/30.
 */
public class AsyncTaskTest {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Object> submit = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("running....");
                return null;
            }
        });

        Thread.sleep(100000);
        System.out.println("...");
    }
}
