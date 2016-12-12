package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by noodles on 16/11/19 上午12:12.
 */
public class Log4j2BootStrap {

    private static final Logger LOGGER = LogManager.getLogger(Log4j2BootStrap.class);

    public static void main(String[] args) {
        LOGGER.info("----------info",new RuntimeException("fffddddd"));
        LOGGER.warn("----------warn");
        LOGGER.error("----------error");
        LOGGER.error("");
    }
}
