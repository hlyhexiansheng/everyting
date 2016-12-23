import java.util.ArrayList;
import java.util.List;

/**
 * Created by noodles on 16/12/23 上午10:30.
 */
public class TestJprofiler {

    public static void main(String[] args) {

        startOneThread();

        createManyObject();
    }

    private static void createManyObject() {

        List<TestEntity> list = new ArrayList<TestEntity>();
        for(int i = 0; i < Integer.MAX_VALUE;i++){
            try {
                Thread.sleep(10);
                list.add(new TestEntity());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startOneThread() {

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true){
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.setName("T1");
        t.start();

    }
}

class TestEntity{
    private byte[] bytes = new byte[1024 * 10];
}
