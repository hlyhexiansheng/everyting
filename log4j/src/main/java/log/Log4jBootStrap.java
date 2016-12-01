package log;

import org.apache.log4j.Logger;

/**
 * Created by noodles on 16/11/18 下午11:32.
 */
public class Log4jBootStrap {


    //103315  //77742

    //-javaagent:/Users/noodles/Documents/bppe-2.0/workspace/test/javaAgent/target/javaAgent-1.0-SNAPSHOT-jar-with-dependencies.jar
    private static Logger logger = Logger.getLogger(Log4jBootStrap.class);

    public static void main(String[] args) throws InterruptedException {

        final long l = System.currentTimeMillis();

        for(int i = 0; i < 10000 * 100;i++){

            Thread.sleep(5000);
            logger.error(i + " log4j hehehehhehefdfdfsfasfasfasdf");

        }

        System.out.println(System.currentTimeMillis() - l);

    }
}