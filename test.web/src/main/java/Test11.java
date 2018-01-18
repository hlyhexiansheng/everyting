import java.util.Random;

/**
 * Created by noodles on 2017/11/21 11:57.
 */
public class Test11 {

    public static void main(String[] args) throws InterruptedException {
        final Test11 test11 = new Test11();
        for (int i = 0; i < 1000; i++) {
            test11.add();
        }
    }

    public void add() throws InterruptedException {
        System.out.println("---");
        System.out.println(System.currentTimeMillis());
        Thread.sleep(Math.abs(new Random(10000).nextInt(10000)));
    }
}
