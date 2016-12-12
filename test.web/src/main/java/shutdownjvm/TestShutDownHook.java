package shutdownjvm;

/**
 * Created by noodles on 16/12/9 下午6:18.
 */
public class TestShutDownHook {

    public static void main(String[] args) throws InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("shutdown hood called close.........");
            }
        });

        System.out.println("running...");
        Thread.sleep(100000);
    }

}
