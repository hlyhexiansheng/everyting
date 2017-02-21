package log;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Created by noodles on 16/11/18 下午11:32.
 */
public class Log4jBootStrap {


    //103315  //77742

    //-javaagent:/Users/noodles/Documents/bppe-2.0/workspace/test/javaAgent/target/javaAgent-1.0-SNAPSHOT-jar-with-dependencies.jar
    private static Logger logger = Logger.getLogger(Log4jBootStrap.class);

    public static void main(String[] args) throws InterruptedException {

//        final long l = System.currentTimeMillis();
//
//        for(int i = 0; i < 10000 * 100;i++){
//
////            Thread.sleep(5000);
//            logger.error(i + " log4j hehehehh\n\n\nehefdfdfsfasfasfasdf");
//            break;
//        }
//
//        System.out.println(System.currentTimeMillis() - l);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            final String s = scanner.nextLine();

            printLong(Integer.parseInt(s));
        }


    }

    public static void printLong(int size) {
        for (int i = 0; i < size; i++) {
            logger.error("                      [[[[HH" + size + "-{" + i + "}]]]]]                      mmanager,{}");
        }
    }


    public void triggerCpuHigher() {
        logger.error("cpu isjifdsjoi ");
    }

    public void triggerMemHigher() {
        logger.error("cpu isjifdsjoi ");
    }
}