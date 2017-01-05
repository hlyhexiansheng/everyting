import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by noodles on 16/10/29 下午4:36.
 */
public class MakeMemHiger {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<byte[]> list = new ArrayList<>();
        for(int i = 0; i < 1000; i++){
            list.add(new byte[1024 * 1024]); //一次写入1MB
            System.out.println(i);

            Thread.sleep(100);

        }
    }

}
