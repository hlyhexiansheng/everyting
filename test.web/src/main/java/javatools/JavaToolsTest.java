package javatools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noodles on 16/9/7 上午10:00.
 *
 *
 *  jmap -histo 3574
 */
public class JavaToolsTest {

    public static void main(String[] args) throws InterruptedException {

        new JavaToolsTest().lockForMinute();

        System.gc();

        Object lock = new Object();
        synchronized (lock){
            lock.wait();
        }
    }

    private void lockForMinute() throws InterruptedException {
        Object lock = new Object();
        synchronized (lock){
            List<FakeObject> list = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                list.add(new FakeObject());
            }
            lock.wait(1000 * 30);
        }
        System.out.println("look, memery is decrease");
    }

}
