package stringtest;

/**
 * Created by noodles on 16/8/23 上午11:28.
 */
public class BasicTest{

    public static void trace(char[] array){
        assert array != null;
        for (char c : array) {
            System.out.print(c);
        }
        System.out.println();
    }
}
