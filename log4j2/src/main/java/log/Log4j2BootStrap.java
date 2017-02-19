package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by noodles on 16/11/19 上午12:12.
 */
public class Log4j2BootStrap {

    private static final Logger LOGGER = LogManager.getLogger(Log4j2BootStrap.class);

    //-javaagent:/Users/noodles/Documents/code/Documents/globalgrow/infrastructure/elog/trunk/elog/elog-java-agent/target/apm.jar
    public static void main(String[] args) throws InterruptedException {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            final String s = scanner.nextLine();

            printLong(Integer.parseInt(s));
        }


    }

    public static void printLong(int size) {
        for (int i = 0; i < size; i++) {
            LOGGER.error("                      [[[[GG0-{}]]]]]                      mmanager,{}", i);
        }
    }


    public static void test() {
        for (int i = 0; i < 5000; i++) {
            LOGGER.error("                      [[[[GG0-{}]]]]]                      mmanager,{}", i);
//            Thread.sleep(5);
        }
        for (int i = 0; i < 5000; i++) {
            LOGGER.error("                      [[[[GG1-{}]]]]]                      mmanager,{}", i);
//            Thread.sleep(5);
        }
        for (int i = 0; i < 5000; i++) {
            LOGGER.error("                      [[[[GG2-{}]]]]]                      mmanager,{}", i);
//            Thread.sleep(5);
        }
        for (int i = 0; i < 5000; i++) {
            LOGGER.error("                      [[[[GG3-{}]]]]]                      mmanager,{}", i);
//            Thread.sleep(5);
        }
        for (int i = 0; i < 5000; i++) {
            LOGGER.error("                      [[[[GG4-{}]]]]]                      mmanager,{}", i);
//            Thread.sleep(5);
        }

    }
}
