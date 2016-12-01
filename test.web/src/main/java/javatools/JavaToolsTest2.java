package javatools;

/**
 * Created by noodles on 16/9/7 下午3:04.
 */
public class JavaToolsTest2 {

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (; ; ) {

                    }
                }
            });
            threads[i].setName("fuckThread");
            threads[i].start();
        }


    }

}
