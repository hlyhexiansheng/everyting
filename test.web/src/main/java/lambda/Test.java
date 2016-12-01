package lambda;

import java.util.Scanner;

/**
 * Created by noodles on 16/4/10.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        final long l = System.currentTimeMillis();

        Thread.sleep(2000);

        System.out.println(System.currentTimeMillis() - l);
    }
}
