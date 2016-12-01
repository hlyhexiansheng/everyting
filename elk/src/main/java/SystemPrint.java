import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by noodles on 16/9/26 上午10:58.
 */
@Slf4j
public class SystemPrint {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(System.getProperty("java.io.tmpdir"));


            Random random = new Random();
            while (true){
                if(random.nextInt(100000000) < 3){
                    System.out.println("-----");
                }

            }

//        FileWriter fileWriter = new FileWriter("/tmp/system_print.log",false);
//
//        for(int i = 0; i < 30000;i++){
//            Thread.sleep(300);
//            fileWriter.append("line-" + i + "\n");
//            fileWriter.flush();
//            log.info("{}",i);
//        }
//
//        fileWriter.close();

    }
}
