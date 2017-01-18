import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by noodles on 16/9/26 上午10:58.
 */
@Slf4j
public class SystemPrint {

    public static void main(String[] args) throws IOException, InterruptedException {

        FileWriter fileWriter;
        fileWriter = new FileWriter("/tmp/fieldGroup.log",false);

        for(int i = 0; i < 30;i++){
            for(int j = 0; j < 500; j++){
                fileWriter.append("line-" + i + "\n");
            }
            fileWriter.flush();
        }

        fileWriter.close();

    }
}
