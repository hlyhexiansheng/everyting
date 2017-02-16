package p;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AccountService;
import service.OrderService;

/**
 * Created by noodles on 16/11/15 下午4:29.
 */
public class LogBootStrap {

    private static final Logger logger = LoggerFactory.getLogger(LogBootStrap.class);

    public static void main(String[] args) throws InterruptedException {




        final long l = System.currentTimeMillis();

        for (int i = 0; i < 10000 * 100; i++) {

            Thread.sleep(100);

            logger.error(" " + i +  " heheh\n\nehh\nehefdfdfsfasfasfasdf");

        }

        System.out.println(System.currentTimeMillis() - l);

    }
}
