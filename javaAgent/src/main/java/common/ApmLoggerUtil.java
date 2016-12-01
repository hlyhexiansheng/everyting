package common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by noodles on 16/11/18 下午4:01.
 */
public class ApmLoggerUtil {

    private static FileWriter writer;

    public static void log(String msg){
        if(writer == null){
            init();
        }
        writeLog(msg);
    }

    private static void writeLog(String msg){
        try {
            writer.write(msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init(){
        try {
            writer = new FileWriter(new File(ConfigReader.instance.getStringValue("appLogStorePath","/tmp/apm/logs") + "apm.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
