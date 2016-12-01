package p;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AccountService;
import service.OrderService;

/**
 * Created by noodles on 16/11/15 下午4:29.
 */
public class LogBootStrap {//86097  //116488

    private static final Logger logger = LoggerFactory.getLogger(LogBootStrap.class);

    //-javaagent:/Users/noodles/Documents/bppe-2.0/workspace/test/javaAgent/target/javaAgent-1.0-SNAPSHOT-jar-with-dependencies.jar
    public static void main(String[] args) throws InterruptedException {


        final long l = System.currentTimeMillis();

        for (int i = 0; i < 10000 * 100; i++) {

            Thread.sleep(100);

            logger.error(" " + i +  " hehehehhehefdfdfsfasfasfasdf");

        }

        System.out.println(System.currentTimeMillis() - l);

    }
}
