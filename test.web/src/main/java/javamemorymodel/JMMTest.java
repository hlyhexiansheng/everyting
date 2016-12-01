package javamemorymodel;

/**
 * Created by Administrator on 2015/12/10.
 */
public class JMMTest {

    public HeadObject object;

    private Object lock = new Object();

    public void run(){
//        synchronized (lock){
            int i = 0;
            while (i++ < 1000){
//                try {
                    object.id++;
                    System.out.println("changed Object------------>>" + object);
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    public static void main(String[] args){
        HeadObject headObject = new HeadObject(1,"value1");

        JMMTest setThread = new JMMTest();
        setThread.object = headObject;

        AccessThread accessThread = new AccessThread(headObject);
        accessThread.start();
        setThread.run();

    }

}

class AccessThread extends Thread{

    private HeadObject headObject;

    public AccessThread(HeadObject headObject){
        this.headObject = headObject;
    }

    @Override
    public void run() {
        int i = 0;
        while (i++ < 1000){
            System.out.println("access headObject-------<<<<<" + headObject);
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}


class HeadObject{

    public int id;

    public String name;

    public HeadObject(int id , String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + this.id + " name=" + this.name;
    }
}
