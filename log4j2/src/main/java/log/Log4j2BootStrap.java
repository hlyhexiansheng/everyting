package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by noodles on 16/11/19 上午12:12.
 */
public class Log4j2BootStrap {

    private static final Logger LOGGER = LogManager.getLogger(Log4j2BootStrap.class);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            LOGGER.error("--[" + i + "]----\n\n----infsdfasdfo,{}", 34);

            Thread.sleep(100);
        }

    }
}
