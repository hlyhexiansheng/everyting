package futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by noodles on 16/5/30.
 */
public class AsyncTaskTest {

    public static void main(String[] args){

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Object> submit = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("running....");
                return null;
            }
        });

        System.out.println("...");
    }
}
