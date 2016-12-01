package timewindow;

/**
 * Created by noodles on 16/11/23 下午5:10.
 */
public class Util {

    public static void sleep(long millTime){
        try {
            Thread.sleep(millTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
