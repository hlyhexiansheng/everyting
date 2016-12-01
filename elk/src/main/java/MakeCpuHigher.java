import java.io.*;

/**
 *  * Created by noodles on 16/10/12 下午3:29.
 *   */
public class MakeCpuHigher{



    public static void main(String[] args) throws IOException, InterruptedException {
        long sum = 0;
        while (true){
            for(int i = 0; i < 10000000;i++){
                sum += i;
            }
            Thread.sleep(1);
        }
    }
}