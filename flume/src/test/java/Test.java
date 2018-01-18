import java.util.StringTokenizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by noodles on 2017/2/23 14:05.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        String value = "hello this is fuck hadoop";
        StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()) {
            System.out.println(itr.nextToken());
        }
    }
}
