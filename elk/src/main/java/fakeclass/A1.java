package fakeclass;

/**
 * Created by noodles on 16/11/4 上午10:44.
 */
public class A1 {

    public void princeStackTrace() {
        System.out.println("A1 origin princeStackTrace");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newMethod();
    }

    public void newMethod(){
        System.out.println("A1 newMethod princeStackTrace");
    }
}
